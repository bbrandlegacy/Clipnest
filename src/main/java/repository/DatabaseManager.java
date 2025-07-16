package repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    private static Connection connection;

    public static Connection getConnection() throws IOException, SQLException {
        if (connection != null && !connection.isClosed()) return connection;

        InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("db.properties");

        if (input == null) {
            throw new FileNotFoundException("db.properties not found in classpath");
        }

        Properties props = new Properties();
        props.load(input);

        String url = props.getProperty("repository.url");
        String user = props.getProperty("repository.user");
        String password = props.getProperty("repository.password");

        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}
