package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.utils.CommandContent;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        Map<String, CommandContent> knownCommands = Map.of(
            "max", new CommandContent("<h1>Product with max price:</h1>", "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1"),
            "min", new CommandContent("<h1>Product with min price:</h1>", "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1"),
            "count", new CommandContent("Number of products:", "SELECT COUNT(*) FROM PRODUCT"),
            "sum", new CommandContent("Summary price:", "SELECT SUM(price) FROM PRODUCT")
        );

        if (knownCommands.containsKey(command)) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery(knownCommands.get(command).sqlQuery);
                    response.getWriter().println("<html><body>");
                    response.getWriter().println(knownCommands.get(command).responseHeader);

                    if (command.equals("min") || command.equals("max")) {
                        while (rs.next()) {
                            String name = rs.getString("name");
                            int price = rs.getInt("price");
                            response.getWriter().println(name + "\t" + price + "</br>");
                        }
                    } else if (command.equals("count") || command.equals("sum")) {
                        response.getWriter().println(rs.getInt(1));
                    }

                    response.getWriter().println("</body></html>");

                    rs.close();
                    stmt.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
