package br.com.cheeper;

import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserBatch {
    public static void main(String[] args) {
        Long inicio = System.currentTimeMillis();
        String myConnectionString = "jdbc:mysql://localhost:3306/cheeper?rewriteBatchedStatements=true";

        try (Connection con = DriverManager.getConnection(myConnectionString, "root", "cheeper")) {
            String sql = "insert into user (name, email, profile_name, password, bio, verified_email)" +
                    "values (?,?,?,?,?,?)";

            Faker faker = new Faker();
            String userName = "";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (int i = 1; i <= 1000; i++) {
                    userName = faker.name().username();
//                    userName = "Jack";

                    ps.setString(1, userName.replace(".", " "));
                    ps.setString(2, userName.replace(".", "_").concat("@cheeper.com"));
                    ps.setString(3, userName.replace(".", "_").concat(""+i));
                    ps.setString(4, "123");
                    ps.setString(5, "Gerado pelo build bulk");
                    ps.setBoolean(6, true);

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
