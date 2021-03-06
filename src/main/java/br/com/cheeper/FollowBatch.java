package br.com.cheeper;

import br.com.cheeper.util.IntegerRandom;

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

            IntegerRandom integerRandom = new IntegerRandom(1870);
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (int i = 2; i <= 1871; i++) { // Eu sigo do id 2 ao 1871
//                    for(int j = 1; j <= IntegerRandom.getRandomIntegerBetweenRange(1,5); j++) {
                        int id = integerRandom.proximo();

                        if(id == 1) continue;

                        ps.setInt(1, 1);
                        ps.setInt(2, id);
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
}