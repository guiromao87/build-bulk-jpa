package br.com.cheeper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FollowBatch {
    public static void main(String[] args) {
        Long inicio = System.currentTimeMillis();
        String myConnectionString = "jdbc:mysql://localhost:3306/cheeper?rewriteBatchedStatements=true";

        try (Connection con = DriverManager.getConnection(myConnectionString, "root", "cheeper")) {
            String sql = "insert into relationship (follower_id, followed_id) values (?,?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (int i = 1; i <= 100000; i++) {
                    for(int j = 1; j <= IntegerRandom.getRandomIntegerBetweenRange(1,5); j++) {
                        int id = IntegerRandom.getRandomIntegerBetweenRange(1, 10000);

                        if(id == i) continue;

                        ps.setInt(1, i);
                        ps.setInt(2, id);
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