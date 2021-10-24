package ru.akirakozov.sd.refactoring.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseFormer {
    private final HttpServletResponse response;

    public ResponseFormer(HttpServletResponse response) throws IOException {
        this.response = response;
        response.getWriter().println("<html><body>");
    }

    public void append(String str) throws IOException {
        this.response.getWriter().println(str);
    }

    public void finishForming() throws IOException {
        this.response.getWriter().println("</body></html>");
        this.response.setContentType("text/html");
        this.response.setStatus(HttpServletResponse.SC_OK);
    }
}
