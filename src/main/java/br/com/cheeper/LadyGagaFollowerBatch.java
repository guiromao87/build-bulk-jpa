package br.com.cheeper;

import br.com.cheeper.util.IntegerRandom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LadyGagaFollowerBatch {
    public static void main(String[] args) {
        Long inicio = System.currentTimeMillis();
        String myConnectionString = "jdbc:mysql://localhost:3306/cheeper?rewriteBatchedStatements=true";

        try (Connection con = DriverManager.getConnection(myConnectionString, "root", "cheeper")) {
            String sql = "insert into relationship (follower_id, followed_id) values (?,?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                for (int i = 1; i <= 10; i++) {
                    int id = IntegerRandom.getRandomIntegerBetweenRange(1, 10);

                    ps.setInt(1, id);
                    // Id da Lady gaga:
//                     ps.setInt(2, 17);
                    ps.addBatch();
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
