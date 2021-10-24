package ru.akirakozov.sd.refactoring.app;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.*;
import ru.akirakozov.sd.refactoring.Main;
import ru.akirakozov.sd.refactoring.utils.TestUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

class AddProductTest extends AppTest {
    @BeforeEach
    void flushDb() {
        TestUtils.flushDB();
    }

    @Test
    void TestAddProduct_add_commodity_present() throws IOException, InterruptedException, URISyntaxException {
        String commodity = "iphone";

        HttpResponse<String> addresponse = TestUtils.makeRequest("http://localhost:8081/add-product?name="+ commodity + "&price=300");
        Assertions.assertEquals(HttpStatus.OK_200, addresponse.statusCode());

        HttpResponse<String> response = TestUtils.makeRequest("http://localhost:8081/get-products");
        Assertions.assertEquals(HttpStatus.OK_200, response.statusCode());
        Assertions.assertTrue(response.body().contains(commodity));
    }
}
