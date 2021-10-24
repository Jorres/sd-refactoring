package ru.akirakozov.sd.refactoring.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    public static void flushDB() {
        try (
            Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {

            {
                String sql = "DROP TABLE IF EXISTS PRODUCT";
                Statement stmt = c.createStatement();
                stmt.executeUpdate(sql);
            }

            {
                String sql = "CREATE TABLE IF NOT EXISTS PRODUCT" +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        " NAME           TEXT    NOT NULL, " +
                        " PRICE          INT     NOT NULL)";
                Statement stmt = c.createStatement();

                stmt.executeUpdate(sql);

                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static HttpResponse<String> makeRequest(String uri) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(new URI(uri)).build();
        return HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void fillDB() throws URISyntaxException, IOException, InterruptedException {
        DBUtils.makeRequest("http://localhost:8081/add-product?name=comm1&price=100");
        DBUtils.makeRequest("http://localhost:8081/add-product?name=comm2&price=200");
        DBUtils.makeRequest("http://localhost:8081/add-product?name=comm3&price=300");
    }
}
