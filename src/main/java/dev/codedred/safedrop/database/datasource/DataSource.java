package dev.codedred.safedrop.database.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {

    Connection getConnection() throws SQLException;

    void closeConnection();

}
