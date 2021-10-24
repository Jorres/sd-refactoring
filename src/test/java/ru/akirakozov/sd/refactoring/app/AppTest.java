package ru.akirakozov.sd.refactoring.app;

import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import ru.akirakozov.sd.refactoring.Main;
import ru.akirakozov.sd.refactoring.utils.ServerHolder;
import ru.akirakozov.sd.refactoring.utils.TestUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTest {
    protected ServerHolder application;

    @BeforeAll
    void prepareServer() throws Exception {
        application = TestUtils.startApplication();
    }

    @AfterAll
    void stopServer() throws Exception {
        application.stop();
    }

}
