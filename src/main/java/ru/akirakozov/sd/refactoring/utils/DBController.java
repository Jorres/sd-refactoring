package ru.akirakozov.sd.refactoring.utils;

import java.io.IOException;
import java.sql.*;

public class DBController {
    public static void makeDBOperationWithRs(String searchQuery, DBProcessor proc) throws SQLException, IOException {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(searchQuery);

            proc.processResultSet(rs);

            rs.close();
            stmt.close();
        }   
    }

    public static void makeDBOperationWithoutRs(String executeQuery) throws SQLException, IOException {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            Statement stmt = c.createStatement();
            stmt.execute(executeQuery);
            stmt.close();
        }
    }
}



