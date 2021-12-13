package com.variant3;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SecondTask {
    private static Connection connect;

    public static void makeSecondTask(){
        try {
            DriverManager.registerDriver(new JDBC());
            connect = DriverManager.getConnection("jdbc:sqlite:titanic");
        } catch (SQLException exception) {
            exception.getMessage();
        }

        try {
            var prepareStatement = connect.prepareStatement(
                    "CREATE TABLE female_table (\n" +
                            "    id INTEGER PRIMARY KEY autoincrement,\n" +
                            "    FemaleId REFERENCES passengers(id)\n," +
                            "    ticket_cost REAL \n );");
            prepareStatement.execute();
        }catch (Exception exception){
            exception.getMessage();
        }

        try{
            var query = "SELECT * FROM passengers WHERE age BETWEEN 15 AND 30 AND sex LIKE '%female%'";
            var statementCreate = connect.createStatement();
            var female = statementCreate.executeQuery(query);

            var statement = connect.prepareStatement(
                    "INSERT INTO female_table( FemaleId, ticket_cost) "
                            + "VALUES( ?, ?)");

            var min = Float.MAX_VALUE;
            var max = 0.0f;
            while (female.next()){
                statement.setObject(1, female.getString("id"));
                statement.setObject(2, female.getFloat("fare"));
                if (female.getFloat("fare") < min)
                    min = female.getFloat("fare");
                else if (female.getFloat("fare") > max)
                    max = female.getFloat("fare");
                statement.execute();
            }

            System.out.println(max - min);
        } catch (SQLException exception) {
            exception.getMessage();
        }

    }
}
