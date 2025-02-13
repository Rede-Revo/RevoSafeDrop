package dev.codedred.safedrop.database.datasource.impl;

import dev.codedred.safedrop.SafeDrop;
import dev.codedred.safedrop.database.datasource.DataSource;
import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL implements DataSource {


    private Connection connection;

    public MySQL(SafeDrop plugin) {
        val databaseSettingsSection = plugin.getConfig().getConfigurationSection("database-settings");

        val host = databaseSettingsSection.getString("host");
        val port = databaseSettingsSection.getString("port");
        val user = databaseSettingsSection.getString("user");
        val password = databaseSettingsSection.getString("password");
        val database = databaseSettingsSection.getString("database");

        // val url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true";
        val connectionUrl = String.format("jdbc:mysql://%s:%s/%s?autoReconnect=true", host, port, database);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.connection = DriverManager.getConnection(connectionUrl, user, password);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }

    @Override
    public void closeConnection() {
        // Not used
    }

}
