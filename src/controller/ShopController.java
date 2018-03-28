package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("message","/shop/index");
		return "index";
	}
}
