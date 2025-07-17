package repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() throws IOException, SQLException {
        Properties props = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IOException("db.properties not found in classpath");
            }
            props.load(input);
        }

        String url = props.getProperty("repository.url");
        String user = props.getProperty("repository.user");
        String password = props.getProperty("repository.password");

        this.connection = DriverManager.getConnection(url, user, password);
    }

    public static synchronized DatabaseManager getInstance() throws IOException, SQLException {
        if (instance == null || instance.connection == null || instance.connection.isClosed()) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}