package com.anton.gym.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(urlPatterns = "/images/*")
public class ImagesServlet extends HttpServlet {
    private static final int BEGIN_INDEX = 1;
    private static final String UPLOAD_DIR = "C:\\Users\\Mi\\IdeaProjects\\photo\\";
    private static final String CONTENT_DISPOSITION_VALUE = "inline; filename=\"%s\"";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH= "Content-Length";
    private static final String CONTENT_DISPOSITION = "Content-Disposition";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filename = request.getPathInfo().substring(BEGIN_INDEX);
        File file = new File(UPLOAD_DIR, filename);
        response.setHeader(CONTENT_TYPE, getServletContext().getMimeType(filename));
        response.setHeader(CONTENT_LENGTH, String.valueOf(file.length()));
        response.setHeader(CONTENT_DISPOSITION, String.format(CONTENT_DISPOSITION_VALUE, filename));
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
