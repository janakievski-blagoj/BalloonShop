package mk.finki.ukim.wp.balloonShop.web.servlets;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SelectBalloon", urlPatterns = "/servlet/selectBalloonSize")
public class SelectBalloonServlet extends HttpServlet {


    private final SpringTemplateEngine springTemplateEngine;

    public SelectBalloonServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String color = req.getParameter("color");
        req.getSession().setAttribute("color", color);
        resp.sendRedirect("/selectBalloonSize");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("color", req.getSession().getAttribute("color"));
        context.setVariable("bodyContent", "selectBalloonSize");
        springTemplateEngine.process("master-template.html", context, resp.getWriter());

    }
}
