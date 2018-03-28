package controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import board.BoardDBMybatis;
import board.BoardDataBean;

@Controller
@RequestMapping("/board")

public class BoardController{
	String boardid = "1";
	String pageNum = "1";
	
	BoardDBMybatis dbPro = BoardDBMybatis.getInstance();
	
	@ModelAttribute
	public void addAttributes(String boardid, String pageNum) {
		if(boardid != null) this.boardid = boardid;
		if(pageNum != null && pageNum != ""){
			this.pageNum = pageNum;}
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("message", "/board/index");
		return "index";
	}
	
	@RequestMapping("/list")
	public String list(Model model) throws Exception {
		int pageSize=5;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage* pageSize;
		int count = 0;
		int number = 0;
		List articleList = null;
		count = dbPro.getArticleCount(boardid);
		if(count > 0){
			articleList = dbPro.getArticles(startRow, endRow, boardid);}
				number=count - (currentPage-1)*pageSize;
		
		int bottomLine=3;
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		int startPage = 1+(currentPage-1)/bottomLine*bottomLine;
		int endPage = startPage+bottomLine-1;
		if(endPage>pageCount) endPage=pageCount;
		
		model.addAttribute("boardid", boardid);
		model.addAttribute("count", count);
		model.addAttribute("articleList", articleList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("bottomLine", bottomLine);
		model.addAttribute("endPage", endPage);
		model.addAttribute("number", number);
				
		return "list";
	}
	
	@RequestMapping("/writeFormUpload")
	public ModelAndView writeFormUpload(BoardDataBean article) throws Exception {
		ModelAndView mv = new ModelAndView();
		/*view addObject 를 통째로 넘길수도있다 (view에 article.xxx로 수정)*/
		mv.addObject("num", article.getNum());
		mv.addObject("ref", article.getRef());
		mv.addObject("re_step", article.getRe_step());
		mv.addObject("re_level", article.getRe_level());
		mv.addObject("pageNum", pageNum);
		mv.addObject("boardid", boardid);
		mv.setViewName("writeFormUpload");
		return mv;
	}
	
	@RequestMapping("/writeProUpload")
	public String writeProUpload(MultipartHttpServletRequest request, BoardDataBean article, Model model) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		MultipartFile multi = request.getFile("uploadfile");
		String filename = multi.getOriginalFilename();
		
		if(filename != null && !filename.equals("")) {
			String uploadPath = request.getRealPath("/")+"fileSave";
			FileCopyUtils.copy(multi.getInputStream(), new FileOutputStream(uploadPath+"/"+multi.getOriginalFilename()));
			article.setFilename(filename);
			article.setFilesize((int) multi.getSize());
			}else {
				article.setFilename("");
				article.setFilesize(0);
			}	
			article.setIp(request.getRemoteAddr());
			dbPro.insertArticle(article);
			mv.addObject("pageNum", pageNum);
		return "redirect:list";
	}
	
	@RequestMapping("/content")
	public String content(int num, Model mv) throws Exception {
			BoardDataBean article = dbPro.getArticle(num, boardid, "content");
			mv.addAttribute("article", article);
			mv.addAttribute("pageNum", pageNum);
		return "content";
	}
	
	@RequestMapping("/updateForm")
	public String process(int num, Model mv) throws Exception {
		BoardDataBean article = dbPro.getArticle(num, boardid, "update");
		mv.addAttribute("article", article);
		mv.addAttribute("pageNum", pageNum);
		return "updateForm";
	}
	
	@RequestMapping("/updatePro")
	public String updatePro(BoardDataBean article, Model mv) throws Exception {
		int chk = dbPro.updateArticle(article);
		mv.addAttribute("chk", chk);
		mv.addAttribute("pageNum", pageNum);
		return "updatePro";
	}
	
	@RequestMapping(value="deleteForm")
	public ModelAndView deleteForm(int num) throws Exception {//리턴타입이 String일때는 modelAndView
		ModelAndView mv = new ModelAndView();
		mv.addObject("num", num);
		mv.addObject("pageNum", pageNum);
		mv.setViewName("deleteForm");
		return mv;
	}
	
	@RequestMapping(value="deletePro")
	public ModelAndView deletePro(int num, String passwd) throws Exception {
		ModelAndView mv = new ModelAndView();
		int check = dbPro.deleteArticle(num, passwd, boardid);
		mv.addObject("check", check);
		mv.addObject("pageNum", pageNum);
		mv.setViewName("deletePro");
		return mv;
	}

}
