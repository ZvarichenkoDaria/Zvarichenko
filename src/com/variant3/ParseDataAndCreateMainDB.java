package com.variant3;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.sqlite.JDBC;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParseDataAndCreateMainDB {

    private static Connection connect;


    public static void parseFail() {
        try {
            DriverManager.registerDriver(new JDBC());
            connect = DriverManager.getConnection("jdbc:sqlite:titanic");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            var read = new CSVReader(new FileReader(new File("Пассажиры Титаника.csv").getAbsolutePath()));
            var lines = read.readAll();
            createMainDB();

            var id = 1l;
            var list = new ArrayList<Passenger>();
            for (var line : lines.subList(1, lines.size())) {
                var passenger = new Passenger(Long.parseLong(line[0]), Integer.parseInt(line[1]) != 0, Short.parseShort(line[2]),
                        line[3], line[4], line[5].length() != 0 ?  Float.parseFloat(line[5]) : null, Integer.parseInt(line[6]),
                        Integer.parseInt(line[7]),
                        line[8],
                        Float.parseFloat(line[9]),
                        line[10],
                        !line[11].isEmpty() ? line[11].toCharArray()[0] : null);
                list.add(passenger);
                addPassenger(passenger);
            }

        }catch (IOException | CsvException | SQLException exception){
            exception.getMessage();
        }
    }

    private static void createMainDB(){
        try {
            var prepareStatement = connect.prepareStatement(
                    "CREATE TABLE passengers (\n" +
                            "    id INTEGER PRIMARY KEY,\n" +
                            "    survived NUMERIC \n," +
                            "    pClass INTEGER \n," +
                            "    name MESSAGE_TEXT \n," +
                            "    sex MESSAGE_TEXT \n," +
                            "    age REAL \n," +
                            "    sibSp INTEGER \n," +
                            "    parch INTEGER \n," +
                            "    ticket MESSAGE_TEXT \n," +
                            "    fare REAL \n," +
                            "    cabin MESSAGE_TEXT \n," +
                            "    embarked MESSAGE_TEXT \n );");
            prepareStatement.execute();
        }catch (Exception exception){
            exception.getMessage();
        }
    }

    private static void addPassenger(Passenger passenger) throws SQLException {
        var prepareStatement = connect.prepareStatement(
                "INSERT INTO passengers( id, survived, pClass, name, sex, age, sibSp, parch, ticket, fare, cabin, embarked) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        prepareStatement.setObject(1, passenger.getId());
        prepareStatement.setObject(2, passenger.getSurvived());
        prepareStatement.setObject(3, passenger.getpClass());
        prepareStatement.setObject(4, passenger.getName());
        prepareStatement.setObject(5, passenger.getSex());
        prepareStatement.setObject(6, passenger.getAge());
        prepareStatement.setObject(7, passenger.getSibSp());
        prepareStatement.setObject(8, passenger.getParch());
        prepareStatement.setObject(9, passenger.getTicket());
        prepareStatement.setObject(10, passenger.getFare());
        prepareStatement.setObject(11, passenger.getCabin());
        prepareStatement.setObject(12, passenger.getEmbarked());

        prepareStatement.execute();
    }
}
