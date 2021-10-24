package ru.akirakozov.sd.refactoring.utils;

public class CommandContent {
    public CommandContent(String responseHeader, String sqlQuery) {
        this.responseHeader = responseHeader;
        this.sqlQuery = sqlQuery;
    }

    public final String responseHeader;
    public final String sqlQuery;
}
