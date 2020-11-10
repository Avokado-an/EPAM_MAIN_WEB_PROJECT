package com.anton.gym.controller.servlet;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.RequestAttributesWarehouse;
import com.anton.gym.controller.ActionProvider;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.dao.MoneyAccountDao;
import com.anton.gym.model.dao.impl.MoneyAccountDaoImplementation;
import com.anton.gym.model.dao.pool.ConnectionPool;
import com.anton.gym.model.entity.UserType;
import com.anton.gym.model.service.MoneyAccountService;
import com.anton.gym.model.service.impl.MoneyAccountServiceImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

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
        viewBalance(request);
        RequestAttributesWarehouse.getInstance().fillMapWithRequestAttributes(request);
        dispatcher.forward(request, response);
    }

    private void viewBalance(HttpServletRequest request) {//todo should it be here???
        HttpSession session = request.getSession();
        MoneyAccountService moneyAccountService = MoneyAccountServiceImplementation.getInstance();
        UserType currentRole = (UserType) session.getAttribute(Attribute.USER_ROLE);
        if(currentRole == UserType.CLIENT) {
            String username = (String) session.getAttribute(Attribute.USERNAME);
            try {
                BigDecimal currentBalance = moneyAccountService.checkMoneyOnAccount(username);
                request.setAttribute(Attribute.BALANCE, currentBalance);
            } catch (ServiceException e) {
                int zero = 0;
                request.setAttribute(Attribute.BALANCE, zero);
            }
        }
    }
}
