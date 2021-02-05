package mk.finki.ukim.wp.balloonShop.web.servlets;

import mk.finki.ukim.wp.balloonShop.model.Order;
import mk.finki.ukim.wp.balloonShop.model.User;
import mk.finki.ukim.wp.balloonShop.service.OrderService;
import mk.finki.ukim.wp.balloonShop.service.ShoppingCartService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmationInfo", urlPatterns = "/servlet/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService, ShoppingCartService shoppingCartService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String clientsBrowser = req.getHeader("User-Agent");
        String clientName = (String) req.getSession().getAttribute("clientName");
        String clientAddress = (String) req.getSession().getAttribute("clientAddress");
        String clientsColor = (String) req.getSession().getAttribute("color");
        String clientsSize = (String) req.getSession().getAttribute("size");
        String ipAddress = req.getRemoteAddr();

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("clientBrowser", clientsBrowser);
        context.setVariable("clientName", clientName);
        context.setVariable("clientAddress", clientAddress);
        context.setVariable("clientColor", clientsColor);
        context.setVariable("clientSize", clientsSize);
        context.setVariable("clientIpAddress", ipAddress);

        springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        String clientColor = (String) req.getSession().getAttribute("color");
        String clientSize = (String) req.getSession().getAttribute("size");
        User user = (User) req.getSession().getAttribute("user");

        req.getSession().setAttribute("clientName", clientName);
        req.getSession().setAttribute("clientAddress", clientAddress);

        orderService.placeOrder(clientColor, clientSize, user.getUsername());
        Order order = orderService.placeOrder(clientColor, clientSize, user.getUsername());
        shoppingCartService.addProductToCart(user.getUsername(), order.getOrderId());

        resp.sendRedirect("/ConfirmationInfo");
    }
}
