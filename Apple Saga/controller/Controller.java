package controller;

import java.sql.Connection;
import java.sql.SQLException;

abstract class Controller {

	public static Connection conn;

    public abstract void connectToDatabase()throws SQLException;

}
