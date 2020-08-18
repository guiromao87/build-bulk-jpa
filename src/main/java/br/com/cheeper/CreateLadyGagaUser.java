package br.com.cheeper;

import java.sql.*;

public class CreateLadyGagaUser {
    public static void main(String[] args) {

        String myConnectionString = "jdbc:mysql://localhost:3306/cheeper";

        try (Connection con = DriverManager.getConnection(myConnectionString, "root", "cheeper")) {
            String sql = "insert into user (name, email, profile_name, password, bio, verified_email, image)" +
                    "values (?,?,?,?,?,?,?)";

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, "Lady Gaga");
                ps.setString(2, "ladygaga@cheeper.com");
                ps.setString(3, "ladygaga");
                ps.setString(4, "123");
                ps.setString(5, "Cantora e atriz");
                ps.setBoolean(6, true);
                ps.setString(7, "https://cheeper.s3.amazonaws.com/gaga.png");

                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next())
                    System.out.println("Id Lady gaga: " + rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
