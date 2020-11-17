package com.anton.gym.util;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.entity.UserType;
import com.anton.gym.model.service.AdminService;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.AdminServiceImplementation;
import com.anton.gym.model.service.impl.UserServiceImplementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code UsersOnPageHandler} class represents UsersOnPageHandler.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class UsersOnPageHandler {
    private static final String STARTING_PAGE = "1";

    /**
     * find all available users
     * @param request the request
     * @return the users
     * @throws ServiceException
     */
    public static List<User> viewUsersOnPage(HttpServletRequest request) throws ServiceException {
        List<User> allUsers = defineRoleAvailableUsers(request);
        defineRequestPageIndexParameters(request, allUsers.size());
        return allUsers;
    }

    /**
     * view available sorted users
     * @param request the request
     * @param fieldToCompare the field by which sorting goes
     * @return the sorted list of users
     * @throws ServiceException
     */
    public static List<User> viewSortedUsersOnPage(HttpServletRequest request, String fieldToCompare)
            throws ServiceException {
        List<User> allUsers = defineRoleAvailableSortedUsers(request, fieldToCompare);
        defineRequestPageIndexParameters(request, allUsers.size());
        return allUsers;
    }

    /**
     * view searched available users
     * @param request the request
     * @param usernameToFind the part of the name of the user to find
     * @return the list of searched users
     * @throws ServiceException
     */
    public static List<User> viewSearchedUsersOnPage(HttpServletRequest request, String usernameToFind)
            throws ServiceException {
        List<User> allUsers = defineRoleAvailableSearchedUsers(request, usernameToFind);
        defineRequestPageIndexParameters(request, allUsers.size());
        return allUsers;
    }

    private static void defineRequestPageIndexParameters(HttpServletRequest request, int usersSize) {
        HttpSession session = request.getSession();
        String pageIndex = (String) session.getAttribute(Attribute.CURRENT_PAGE_NUMBER);
        int pageNumber = PaginationIndexesDefiner.definePageIndex(pageIndex);
        int maxPagesAmount = PaginationIndexesDefiner.defineMaxPagesAmount(usersSize);
        request.setAttribute(Attribute.PAGES_AMOUNT, maxPagesAmount);
        int startingUserIndex = PaginationIndexesDefiner.defineMinUserIndex(pageNumber);
        request.setAttribute(Attribute.STARTING_INDEX, startingUserIndex);
        int endingUserIndex = PaginationIndexesDefiner.defineMaxUserIndex(usersSize, startingUserIndex);
        request.setAttribute(Attribute.ENDING_INDEX, endingUserIndex);
    }

    private static List<User> defineRoleAvailableUsers(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();

        UserType currentRole = (UserType) session.getAttribute(Attribute.USER_ROLE);
        List<User> users;
        if (currentRole == UserType.ADMIN) {
            AdminService adminService = AdminServiceImplementation.getInstance();
            users = adminService.viewUsers();
        } else {
            UserService userService = UserServiceImplementation.getInstance();
            String trainerName = (String) session.getAttribute(Attribute.USERNAME);
            users = userService.findTrainerUsers(trainerName);
        }
        return users;
    }

    private static List<User> defineRoleAvailableSortedUsers(HttpServletRequest request, String fieldToCompare)
            throws ServiceException {
        HttpSession session = request.getSession();
        session.setAttribute(Attribute.CURRENT_PAGE_NUMBER, STARTING_PAGE);
        UserType userRole = (UserType) session.getAttribute(Attribute.USER_ROLE);
        List<User> sortedUsers = new ArrayList<>();
        if (userRole == UserType.ADMIN) {
            AdminService adminService = AdminServiceImplementation.getInstance();
            sortedUsers = adminService.sortAllUsers(fieldToCompare);
        } else if (userRole == UserType.TRAINER) {
            sortedUsers = sortTrainerUsers(request, fieldToCompare);
        }
        return sortedUsers;
    }

    private static List<User> defineRoleAvailableSearchedUsers(HttpServletRequest request, String username)
            throws ServiceException {
        HttpSession session = request.getSession();
        session.setAttribute(Attribute.CURRENT_PAGE_NUMBER, STARTING_PAGE);
        UserType userRole = (UserType) session.getAttribute(Attribute.USER_ROLE);
        List<User> usersToFind = new ArrayList<>();
        if (userRole == UserType.ADMIN) {
            AdminService adminService = AdminServiceImplementation.getInstance();
            usersToFind = adminService.findUsersByUsername(username);
        } else if (userRole == UserType.TRAINER) {
            usersToFind = findTrainerUserByName(request, username);
        }
        return usersToFind;
    }

    private static List<User> findTrainerUserByName(HttpServletRequest request, String username) throws ServiceException {
        HttpSession session = request.getSession();
        String trainerName = (String) session.getAttribute(Attribute.USERNAME);
        UserService userService = UserServiceImplementation.getInstance();
        return userService.findTrainerUserByUsername(trainerName, username);
    }

    private static List<User> sortTrainerUsers(HttpServletRequest request, String fieldToCompare)
            throws ServiceException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImplementation.getInstance();
        String trainerName = (String) session.getAttribute(Attribute.USERNAME);
        return userService.sortTrainerUsers(trainerName, fieldToCompare);
    }
}
