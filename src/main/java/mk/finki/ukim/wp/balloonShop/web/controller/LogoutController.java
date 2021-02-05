package mk.finki.ukim.wp.balloonShop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String service(HttpServletRequest request, Model model){
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
