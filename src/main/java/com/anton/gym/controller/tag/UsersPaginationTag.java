package com.anton.gym.controller.tag;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Message;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.entity.UserType;
import com.anton.gym.util.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * The {@code UsersPaginationTag} class represents UsersPaginationTag.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class UsersPaginationTag extends TagSupport {
    private Logger LOGGER = LogManager.getLogger();
    private PropertiesReader reader = PropertiesReader.getInstance();
    private int startingIndex;
    private int endingIndex;

    /**
     * sets starting index
     *
     * @param startingIndex the starting index
     */
    public void setStartingIndex(int startingIndex) {
        this.startingIndex = startingIndex;
    }

    /**
     * sets ending index
     *
     * @param endingIndex the ending index
     */
    public void setEndingIndex(int endingIndex) {
        this.endingIndex = endingIndex;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            ServletRequest request = pageContext.getRequest();
            List<User> users = (List<User>) request.getAttribute(Attribute.USERS);
            UserType currentUserRole = (UserType) request.getAttribute(Attribute.USER_ROLE);
            StringBuilder result = new StringBuilder();
            HttpSession session = pageContext.getSession();
            String currentLanguage = (String) session.getAttribute(Attribute.LANGUAGE);
            String buttonName;
            for (int i = startingIndex; i <= endingIndex; i++) {
                result.append(
                        "<tr>\n" +
                                "<td>\n" +
                                "<form action=\"userServlet\" name=\"view_profile\" method=\"post\">\n" +
                                "<input type=\"hidden\" name=\"command\" value=\"view_user_profile\">\n" +
                                "<input class=\"bg-dark\" type=\"submit\" name=\"username\" value=" + users.get(i).getUsername() + ">\n" +
                                "</form>\n" +
                                "</td>\n" +
                                "<td>" + users.get(i).isActive() + "</td>\n" +
                                "<c:choose>\n");
                if (currentUserRole == UserType.ADMIN) {
                    result.append("<td>" + users.get(i).getType().toString() + "</td>\n");
                }
                if (users.get(i).isActive() && currentUserRole == UserType.ADMIN
                        && users.get(i).getType() != UserType.ADMIN) {
                    buttonName = reader.readUserTextProperty(currentLanguage, Message.BLOCK_USER);
                    result.append(
                            "<td>\n" +
                                    "<form action=\"userServlet\" name=\"logout\" method=\"post\">\n" +
                                    "<input type=\"hidden\" name=\"command\" value=\"block_user\">\n" +
                                    "<input type=\"hidden\" value=\"" + users.get(i).getUsername() + "\" name=\"username\"/>\n" +
                                    "<input class=\"bg-dark\" type=\"submit\" value=\"" + buttonName + "\">\n" +
                                    "</form>\n" +
                                    "</td>\n");
                } else if (!users.get(i).isActive() && currentUserRole == UserType.ADMIN
                        && users.get(i).getType() != UserType.ADMIN) {
                    buttonName = reader.readUserTextProperty(currentLanguage, Message.UNBLOCK_USER);
                    result.append(
                            "<td>\n" +
                                    "<form action=\"userServlet\" name=\"logout\" method=\"post\">\n" +
                                    "<input type=\"hidden\" name=\"command\" value=\"unblock_user\">\n" +
                                    "<input type=\"hidden\" value=\"" + users.get(i).getUsername() + "\" name=\"username\"/>\n" +
                                    "<input class=\"bg-dark\" type=\"submit\" value=\"" + buttonName + "\">\n" +
                                    "</form>\n" +
                                    "</td>\n");
                }
                if (currentUserRole == UserType.ADMIN && users.get(i).getType() == UserType.CLIENT) {
                    buttonName = reader.readUserTextProperty(currentLanguage, Message.TRAINER);
                    result.append(
                            "<td>\n" +
                                    "<form action=\"userServlet\" name=\"logout\" method=\"post\">\n" +
                                    "<input type=\"hidden\" name=\"command\" value=\"mark_user_as_trainer\">\n" +
                                    "<input type=\"hidden\" value=\"" + users.get(i).getUsername() + "\" name=\"username\"/>\n" +
                                    "<input class=\"bg-dark\" type=\"submit\" value=\"" + buttonName + "\">\n" +
                                    "</form>\n" +
                                    "</td>\n");
                } else if (currentUserRole == UserType.ADMIN && users.get(i).getType() == UserType.TRAINER) {
                    buttonName = reader.readUserTextProperty(currentLanguage, Message.CLIENT);
                    result.append(
                            "<td>\n" +
                                    "<form action=\"userServlet\" name=\"logout\" method=\"post\">\n" +
                                    "<input type=\"hidden\" name=\"command\" value=\"mark_trainer_as_user\">\n" +
                                    "<input type=\"hidden\" value=\"" + users.get(i).getUsername() + "\" name=\"username\"/>\n" +
                                    "<input class=\"bg-dark\" type=\"submit\" value=\"" + buttonName + "\">\n" +
                                    "</form>\n" +
                                    "</td>\n");
                }
                result.append("</tr>");
            }
            pageContext.getOut().write(result.toString());
        } catch (IOException e) {
            LOGGER.error("Error while writing to out stream", e);
            throw new JspException("Error while writing to out stream", e);
        }
        return SKIP_BODY;
    }
}
