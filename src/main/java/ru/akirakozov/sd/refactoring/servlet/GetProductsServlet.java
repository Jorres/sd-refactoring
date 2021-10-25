package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.utils.DBController;
import ru.akirakozov.sd.refactoring.utils.ResponseFormer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseFormer rf = new ResponseFormer(response);
        try {
            DBController.makeDBOperationWithRs("SELECT * FROM PRODUCT", rs -> {
                while (rs.next()) {
                    String  name = rs.getString("name");
                    int price  = rs.getInt("price");
                    rf.append(name + "\t" + price + "</br>");
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        rf.finishForming();
    }
}
