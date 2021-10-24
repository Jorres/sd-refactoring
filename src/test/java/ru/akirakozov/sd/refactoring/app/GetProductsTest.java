package ru.akirakozov.sd.refactoring.app;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.utils.DBUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class GetProductsTest {
    @BeforeEach
    void flushDb() {
        DBUtils.flushDB();
    }

    @Test
    void TestGetProducts_empty_returns_200OK() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = DBUtils.makeRequest("http://localhost:8081/get-products");
        Assertions.assertEquals(HttpStatus.OK_200, response.statusCode());
    }
}
