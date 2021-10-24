package ru.akirakozov.sd.refactoring.app;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.*;
import ru.akirakozov.sd.refactoring.Main;
import ru.akirakozov.sd.refactoring.utils.TestUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class GetProductsTest extends AppTest {
    @BeforeEach
    void flushDb() {
        TestUtils.flushDB();
    }

    @Test
    void TestGetProducts_empty_returns_200OK() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = TestUtils.makeRequest("http://localhost:8081/get-products");
        Assertions.assertEquals(HttpStatus.OK_200, response.statusCode());
    }
}
