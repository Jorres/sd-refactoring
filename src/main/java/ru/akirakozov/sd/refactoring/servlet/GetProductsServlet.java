package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.utils.ResponseFormer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseFormer rf = new ResponseFormer(response);
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");
                while (rs.next()) {
                    String  name = rs.getString("name");
                    int price  = rs.getInt("price");
                    rf.append(name + "\t" + price + "</br>");
                }
                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        rf.finishForming();
    }
}
