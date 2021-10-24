package ru.akirakozov.sd.refactoring.app;

import org.junit.jupiter.api.*;
import ru.akirakozov.sd.refactoring.utils.TestUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class QueryTest extends AppTest {
    @BeforeEach
    void prepareDB() throws URISyntaxException, IOException, InterruptedException {
        TestUtils.flushDB();
        TestUtils.fillDB();
    }

    @Test
    void TestQueryMin_returns_100_on_fake_data() throws URISyntaxException, IOException, InterruptedException {
        int minResultOnFakeData = 100;
        HttpResponse<String> response = TestUtils.makeRequest("http://localhost:8081/query?command=min");
        Assertions.assertTrue(response.body().contains(Integer.toString(minResultOnFakeData)));
    }

    @Test
    void TestQuerySum_returns_600_on_fake_data() throws URISyntaxException, IOException, InterruptedException {
        int sumResultOnTestData = 600;
        HttpResponse<String> response = TestUtils.makeRequest("http://localhost:8081/query?command=sum");
        Assertions.assertTrue(response.body().contains(Integer.toString(sumResultOnTestData)));
    }

    @Test
    void TestQueryMax_returns_300_on_fake_data() throws URISyntaxException, IOException, InterruptedException {
        int maxResultOnTestData = 300;
        HttpResponse<String> response = TestUtils.makeRequest("http://localhost:8081/query?command=max");
        Assertions.assertTrue(response.body().contains(Integer.toString(maxResultOnTestData)));
    }

    @Test
    void TestQueryCount_returns_3_on_fake_data() throws URISyntaxException, IOException, InterruptedException {
        int countResultOnTestData = 3;
        HttpResponse<String> response = TestUtils.makeRequest("http://localhost:8081/query?command=count");
        Assertions.assertTrue(response.body().contains(Integer.toString(countResultOnTestData)));
    }
}
