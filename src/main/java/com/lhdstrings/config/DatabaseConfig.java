package com.lhdstrings.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private static final HikariDataSource dataSource;

    static {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
        Properties properties = new Properties();
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(properties.getProperty("db.url"));
            config.setUsername(properties.getProperty("db.username"));
            config.setPassword(properties.getProperty("db.password"));
            config.addDataSourceProperty("cachePrepStmts", properties.getProperty("db.cachePrepStmts"));
            config.addDataSourceProperty("prepStmtCacheSize", properties.getProperty("db.prepStmtCacheSize"));
            config.addDataSourceProperty("prepStmtCacheSqlLimit", properties.getProperty("db.prepStmtCacheSqlLimit"));

            dataSource = new HikariDataSource(config);
        } catch (IOException e) {
            throw new RuntimeException("Error reading from application.properties", e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
