package mk.finki.ukim.wp.balloonShop.web.controller;

import mk.finki.ukim.wp.balloonShop.model.Order;
import mk.finki.ukim.wp.balloonShop.model.User;
import mk.finki.ukim.wp.balloonShop.service.OrderService;
import mk.finki.ukim.wp.balloonShop.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ConfirmationInfo")
public class ConfirmationInfoController {

    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public ConfirmationInfoController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getConfirmationInfo(HttpServletRequest request, Model model){

        String clientsBrowser = request.getHeader("User-Agent");
        String clientName = (String) request.getSession().getAttribute("clientName");
        String clientAddress = (String) request.getSession().getAttribute("clientAddress");
        String clientColor = (String) request.getSession().getAttribute("color");
        String clientSize = (String) request.getSession().getAttribute("size");
        String ipAddress = request.getRemoteAddr();

        model.addAttribute("clientBrowser", clientsBrowser);
        model.addAttribute("clientName", clientName);
        model.addAttribute("clientAddress", clientAddress);
        model.addAttribute("clientColor", clientColor);
        model.addAttribute("clientSize", clientSize);
        model.addAttribute("clientIpAddress", ipAddress);

        model.addAttribute("bodyContent", "confirmationInfo");
        return "master-template";
    }

    @PostMapping
    public String postConfirmationInfo(HttpServletRequest request){

        String clientName = request.getParameter("clientName");
        String clientAddress = request.getParameter("clientAddress");
        String clientColor = (String) request.getSession().getAttribute("color");
        String clientSize = (String) request.getSession().getAttribute("size");
        //User user = (User) request.getSession().getAttribute("user");
        String username = (String) request.getRemoteUser();

        request.getSession().setAttribute("clientName", clientName);
        request.getSession().setAttribute("clientAddress", clientAddress);

        Order order = orderService.placeOrder(clientColor, clientSize, username);
        shoppingCartService.addProductToCart(username, order.getOrderId());

        return "redirect:/ConfirmationInfo";
    }



}

