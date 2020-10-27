package com.anton.web.controller.servlet;

import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.RequestAttributesWarehouse;
import com.anton.web.controller.provider.ActionProvider;
import com.anton.web.model.dao.pool.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/userServlet")
public class MainProgramServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        Command command = ActionProvider.provideAction(request);
        page = command.execute(request);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        HttpSession session = request.getSession();
        request.setAttribute(Attribute.USER_ROLE, session.getAttribute(Attribute.USER_ROLE));
        session.setAttribute(Attribute.CURRENT_PAGE, page);
        RequestAttributesWarehouse.getInstance().fillMapWithRequestAttributes(request);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().deactivatePool();
    }
}
