package br.com.cheeper;

import br.com.cheeper.util.IntegerRandom;
import com.github.javafaker.Faker;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CheepBatch {
    public static void main(String[] args) {
        Long inicio = System.currentTimeMillis();
        String myConnectionString = "jdbc:mysql://localhost:3306/cheeper?rewriteBatchedStatements=true";

        try (Connection con = DriverManager.getConnection(myConnectionString, "root", "cheeper")) {
            String sql = "insert into cheep (message, profile_id, creation)" +
                    "values (?,?,?)";

            Faker faker = new Faker();
            LocalDateTime dt = LocalDateTime.now();
            System.out.println(dt);
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (int i = 1; i <= 20; i++) {
//                    for(int j = 1; j <= IntegerRandom.getRandomIntegerBetweenRange(1,5); j++) {
                        ps.setString(1, faker.lorem().sentence(5));
                        ps.setInt(2, 1);
                        ps.setTimestamp(3, Timestamp.valueOf(dt.plusSeconds(i)));
                        ps.addBatch();
//                    }
                }
                ps.executeBatch();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Long fim = System.currentTimeMillis();
        System.out.println(" ******* TEMPO: " + (fim - inicio) /1000);
    }

    private static java.util.Date dt() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusSeconds(1);
        java.util.Date date1 = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date1;
    }
}