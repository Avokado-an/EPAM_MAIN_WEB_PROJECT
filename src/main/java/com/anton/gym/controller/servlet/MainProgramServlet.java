package com.anton.gym.controller.servlet;

import com.anton.gym.controller.ActionProvider;
import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.RequestAttributesWarehouse;
import com.anton.gym.model.dao.pool.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The {@code MainProgramServlet} class represents MainProgramServlet.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
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

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().deactivatePool();
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


}
