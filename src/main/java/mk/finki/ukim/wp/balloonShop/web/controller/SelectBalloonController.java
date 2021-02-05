package mk.finki.ukim.wp.balloonShop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/selectBalloonSize")
public class SelectBalloonController {

    @GetMapping
    public String getPage(HttpServletRequest request, Model model){
        model.addAttribute("color", request.getSession().getAttribute("color"));
        model.addAttribute("bodyContent", "selectBalloonSize");
        return "master-template";
    }

    @PostMapping
    public String postPage(HttpServletRequest request){
        String color = request.getParameter("color");
        request.getSession().setAttribute("color", color);
        return "redirect:/selectBalloonSize";
    }

}
