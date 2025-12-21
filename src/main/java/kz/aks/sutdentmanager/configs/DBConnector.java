package kz.aks.sutdentmanager.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DBConnector {
    private Connection connection;

    public DBConnector() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bitlab",
                    "postgres", "postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public Connection getConnection() {
        return connection;
    }
}
