package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("message","/member/index");
		return "index";
	}
}
