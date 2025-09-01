package com.multi.importer;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CsvImporter {
    static final String URL = "jdbc:mysql://localhost:3306/travel_db?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=utf8";
    static final String USER = "apple";
    static final String PASS = "apple";

    public static void main(String[] args) throws Exception {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO travel(district, title, description, address, phone) VALUES(?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql);
                 //travel.csv를 읽어오기 위한 reder코드 설정
                 CSVReader reader = new CSVReader(new InputStreamReader(CsvImporter.class.getResourceAsStream("/travel.csv"),
                         StandardCharsets.UTF_8))) {
                String[] row;
                boolean headerSkipped = false;
                while ((row = reader.readNext()) != null) {
                    if (!headerSkipped) { headerSkipped = true; continue; } // 헤더 스킵
                    ps.setString(1, row[1]); // district
                    ps.setString(2, row[2]); // title
                    ps.setString(3, row[3]); // description
                    ps.setString(4, row[4]); // address
                    ps.setString(5, row[5]); // phone
                    ps.addBatch();
                }
                ps.executeBatch();
                connection.commit();
                System.out.println("CSV import 완료!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
