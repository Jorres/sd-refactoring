package ru.akirakozov.sd.refactoring.utils;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface DBProcessor {
    void processResultSet(ResultSet rs) throws SQLException, IOException;
}
