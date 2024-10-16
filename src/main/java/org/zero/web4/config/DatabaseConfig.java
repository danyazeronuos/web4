package org.zero.web4.config;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
@ApplicationScoped
public class DatabaseConfig {
    private final Connection connection;

    public DatabaseConfig() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("org.postgresql.Driver").newInstance();
        this.connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/ds3",
                "daniilmozzhukhin",
                "1111"
        );
    }

}
