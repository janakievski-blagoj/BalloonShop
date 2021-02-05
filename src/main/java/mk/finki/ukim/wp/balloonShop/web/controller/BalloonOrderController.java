package mk.finki.ukim.wp.balloonShop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/BalloonOrder.do")
public class BalloonOrderController {

    @GetMapping
    public String getPage(HttpServletRequest request, Model model){
        model.addAttribute("color", request.getSession().getAttribute("color"));
        model.addAttribute("size", request.getSession().getAttribute("size"));
        model.addAttribute("bodyContent", "deliveryInfo");
        return "master-template";
    }


    @PostMapping
    public String postPage(HttpServletRequest request, HttpServletResponse response){
        String size = request.getParameter("size");
        request.getSession().setAttribute("size", size);
        return "redirect:/BalloonOrder.do";
    }

}
