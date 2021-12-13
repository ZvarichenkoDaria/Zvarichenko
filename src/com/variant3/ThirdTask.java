package com.variant3;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThirdTask {

    private static Connection connect;

    public static void makeThirdTask(){
        try {
            DriverManager.registerDriver(new JDBC());
            connect = DriverManager.getConnection("jdbc:sqlite:titanic");
        } catch (SQLException exception) {
            exception.getMessage();
        }

        try {
            var prepareStatement = connect.prepareStatement(
                    "CREATE TABLE listPeople (\n" +
                            "    id INTEGER PRIMARY KEY autoincrement,\n" +
                            "    FemaleId REFERENCES passengers(id) \n );");
            prepareStatement.execute();
        }catch (Exception exception){
            exception.getMessage();
        }

        try{
            var query =  "SELECT * FROM passengers WHERE age BETWEEN 45 AND 60 AND sex = 'male' OR age BETWEEN 20 AND 25 AND sex = 'female'";
            var statementCreate = connect.createStatement();
            var human = statementCreate.executeQuery(query);

            var statement = connect.prepareStatement(
                    "INSERT INTO listPeople( FemaleId) "
                            + "VALUES( ?)");

            while (human.next()){
                statement.setObject(1, human.getString("id"));
                System.out.println("Ticket 3 task: " + toString(human));
                statement.execute();
            }

        } catch (SQLException exception) {
            exception.getMessage();
        }
    }

    public static String toString(ResultSet result) throws SQLException {
        return result.getString("id") + ", " +  result.getString("survived") + ", " +  result.getString("pClass") + ", " +  result.getString("name") + ", " +  result.getString("sex") + ", " +  result.getString("age") + ", " +  result.getString("sibSp") + ", " +  result.getString("parch") + ", " +  result.getString("ticket") + ", " +  result.getString("fare") + ", " +  result.getString("cabin") + ", " +  result.getString("embarked");
    }
}
