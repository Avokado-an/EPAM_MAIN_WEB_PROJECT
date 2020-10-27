package com.anton.web.controller.servlet;

import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.impl.RedactUserProfilePictureCommand;
import com.anton.web.controller.exception.ControllerException;
import com.anton.web.controller.util.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/FileUploadingServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final PropertiesReader reader = PropertiesReader.getInstance();
    private static final String UPLOAD_DIR =
            "C:\\Users\\Mi\\IdeaProjects\\photo\\";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File fileSaveDir = new File(UPLOAD_DIR);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        StringBuilder fileName = new StringBuilder("img/photo/");
        request.getParts().forEach(part -> {
            try {
                String path = part.getSubmittedFileName();
                if (path == null) {
                    throw new ControllerException();
                }
                part.write(UPLOAD_DIR + path);
                fileName.append(path);
            } catch (IOException | ControllerException e) {
                LOGGER.error("File was not uploaded");
            }
        });
        request.setAttribute(Attribute.PHOTO_REFERENCE, fileName.toString());
        String pagePath = new RedactUserProfilePictureCommand().execute(request);
        request.getRequestDispatcher(pagePath).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
