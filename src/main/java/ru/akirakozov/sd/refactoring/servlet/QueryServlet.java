package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.utils.CommandContent;
import ru.akirakozov.sd.refactoring.utils.DBController;
import ru.akirakozov.sd.refactoring.utils.ResponseFormer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        ResponseFormer rf = new ResponseFormer(response);

        Map<String, CommandContent> knownCommands = Map.of(
            "max", new CommandContent("<h1>Product with max price:</h1>", "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1"),
            "min", new CommandContent("<h1>Product with min price:</h1>", "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1"),
            "count", new CommandContent("Number of products:", "SELECT COUNT(*) FROM PRODUCT"),
            "sum", new CommandContent("Summary price:", "SELECT SUM(price) FROM PRODUCT")
        );

        if (knownCommands.containsKey(command)) {
            try {
                DBController.makeDBOperationWithRs(knownCommands.get(command).sqlQuery, rs -> {
                    rf.append(knownCommands.get(command).responseHeader);
                    if (command.equals("min") || command.equals("max")) {
                        while (rs.next()) {
                            String name = rs.getString("name");
                            int price = rs.getInt("price");
                            rf.append(name + "\t" + price + "</br>");
                        }
                    } else if (command.equals("count") || command.equals("sum")) {
                        rf.append(Integer.toString(rs.getInt(1)));
                    }
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            rf.append("Unknown command: " + command);
        }
        rf.finishForming();
    }
}
