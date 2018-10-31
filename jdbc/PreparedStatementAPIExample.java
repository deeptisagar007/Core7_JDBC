package com.marlabs.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class PreparedStatementAPIExample {

	Connection connection = DBConnector.getConnection();

	public void preparedStatementInsertDemo(int empId, String empName, double empSal, int deptNo) {

		// String SELECT_WITH_CONDITION = "SELECT * FROM employee WHERE ? = ?";
		String INSERT_QUERY = "INSERT INTO employee VALUES( ?, ?, ?, ?);";

		try {
			System.out.println("In try");
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
			System.out.println("After connection.prepareStatement");
			preparedStatement.setInt(1, empId);
			preparedStatement.setString(2, empName);
			preparedStatement.setDouble(3, empSal);
			preparedStatement.setInt(4, deptNo);

			System.out.println("before preparedStatement.executeQuery");
			System.out.println("Insert Result: " + preparedStatement.executeUpdate());
			System.out.println("After preparedStatement.executeQuery");

		} catch (SQLIntegrityConstraintViolationException e) {
			// e.printStackTrace();
			System.out.println("IN SQL INTEGRITY ERROR");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void preparedStatemenFortDisplay(String tableName) {
		System.out.println("In Display MEthod");
		String SELECT_QUERY = "SELECT * FROM employee;";

		try {
			// System.out.println("In Try");
			// PreparedStatement preparedStatement =
			// connection.prepareStatement(SELECT_QUERY);
			// preparedStatement.setString(1, tableName);
			// System.out.println("This is to string: " + preparedStatement.toString());
			// ResultSet resultSet = preparedStatement.executeQuery();

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			for (int count = 1; count <= resultSetMetaData.getColumnCount(); count++) {
				System.out.print(resultSetMetaData.getColumnName(count) + "\t");

			}
			System.out.println("");
			while (resultSet.next()) {
				for (int count = 1; count <= resultSetMetaData.getColumnCount(); count++) {
					System.out.print(resultSet.getString(count) + "\t");
				}
				System.out.println("");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
