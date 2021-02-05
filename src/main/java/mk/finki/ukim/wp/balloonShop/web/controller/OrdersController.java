package mk.finki.ukim.wp.balloonShop.web.controller;

import mk.finki.ukim.wp.balloonShop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class OrdersController {

    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String getOrdersPage(HttpServletRequest req, Model model){

        String username = req.getRemoteUser();
        model.addAttribute("orders", orderService.getAllOrdersByUsername(username));
//        model.addAttribute("orders", orderService.getAllOrdersByNameAndAddress(user.getUsername()));
        model.addAttribute("bodyContent", "userOrders");
        return "master-template";
    }
}
