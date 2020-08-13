package br.com.cheeper;

import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheepBatch {
    public static void main(String[] args) {
        Long inicio = System.currentTimeMillis();
        String myConnectionString = "jdbc:mysql://localhost:3306/cheeper?rewriteBatchedStatements=true";

        try (Connection con = DriverManager.getConnection(myConnectionString, "root", "cheeper")) {
            String sql = "insert into cheep (message, profile_id, creation)" +
                    "values (?,?,now())";

            Faker faker = new Faker();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (int i = 1; i <= 100000; i++) {

                    for(int j = 1; j <= IntegerRandom.getRandomIntegerBetweenRange(1,5); j++) {
                        ps.setString(1, faker.lorem().sentence(10));
                        ps.setInt(2, i);
                        ps.addBatch();
                    }
                }
                ps.executeBatch();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Long fim = System.currentTimeMillis();
        System.out.println(" ******* TEMPO: " + (fim - inicio) /1000);
    }
}
