package com.variant3;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        ParseDataAndCreateMainDB.parseFail();
        SecondTask.makeSecondTask();
        ThirdTask.makeThirdTask();
        FirstTask.makeGraph();
    }
}
