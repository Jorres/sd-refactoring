package ru.akirakozov.sd.refactoring.app;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.core.DB;
import ru.akirakozov.sd.refactoring.utils.DBUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class AddProductTest {
    @BeforeEach
    void flushDb() {
        DBUtils.flushDB();
    }

    @Test
    void TestAddProduct_add_commodity_present() throws IOException, InterruptedException, URISyntaxException {
        String commodity = "iphone";

        HttpResponse<String> addresponse = DBUtils.makeRequest("http://localhost:8081/add-product?name="+ commodity + "&price=300");
        Assertions.assertEquals(HttpStatus.OK_200, addresponse.statusCode());

        HttpResponse<String> response = DBUtils.makeRequest("http://localhost:8081/get-products");
        Assertions.assertEquals(HttpStatus.OK_200, response.statusCode());
        Assertions.assertTrue(response.body().contains(commodity));
    }
}
