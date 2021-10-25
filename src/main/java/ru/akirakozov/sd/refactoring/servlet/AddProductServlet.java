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
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseFormer rf = new ResponseFormer(response);

        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));

        try {
            DBController.makeDBOperationWithoutRs("INSERT INTO PRODUCT " +
                    "(NAME, PRICE) VALUES (\"" + name + "\"," + price + ")");
            rf.append("OK");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        rf.finishForming();
    }
}
