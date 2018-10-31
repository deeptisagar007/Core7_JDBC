package com.marlabs.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class StatementAPIExample {

	public void showEmployeeDetails(int departmentNo) {
		// Class.forName(com.mysql.jdbc.Driver);

		// System.out.println("Before Connection");
		Connection connection = null;

		// System.out.println("Before statement");
		Statement statement = null;

		// System.out.println("BeforeresultSet");
		ResultSet resultSet = null;

		try {

			// System.out.println("In Try Before Connection");
			connection = DBConnector.getConnection();

			// System.out.println("In Try Before Statement");
			statement = connection.createStatement();

			String SELECT_ON_DEPARTMENT = "SELECT * FROM employee WHERE deptNo=" + departmentNo;

			// System.out.println("In Try Before ResultSet");
			resultSet = statement.executeQuery(SELECT_ON_DEPARTMENT);

			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			// System.out.println("ResultSetMetaData: " + resultSetMetaData);

			// System.out.println("Total Column Count: " +
			// resultSetMetaData.getColumnCount());
			for (int count = 1; count <= resultSetMetaData.getColumnCount(); count++) {
				System.out.print(resultSetMetaData.getColumnName(count) + "\t");
			}

			while (resultSet.next()) {
				System.out.println("");
				System.out.print(resultSet.getInt(1) + "\t");
				System.out.print(resultSet.getString(2) + "\t");
				System.out.print(resultSet.getDouble(3) + "\t");
				System.out.print(resultSet.getInt(4) + "\t");
			}
			System.out.println("");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void showDatabaseMetaData() {
		// System.out.println("In showDatabaseMetaData");
		Connection connection = null;
		try {
			// System.out.println("In showDatabaseMetaData try");
			connection = DBConnector.getConnection();
			// System.out.println("After Connection");
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			System.out.println("Database getDatabaseProductName : " + databaseMetaData.getDatabaseProductName());
			System.out.println("Database getDatabaseProductVersion: " + databaseMetaData.getDatabaseProductVersion());
			System.out.println("Database getDriverName: " + databaseMetaData.getDriverName());
			System.out.println("Database getDriverMinorVersion: " + databaseMetaData.getDriverMinorVersion());
			System.out.println("Database getDriverMajorVersion: " + databaseMetaData.getDriverMajorVersion());
			System.out.println("Database getSQLKeywords: " + databaseMetaData.getSQLKeywords());
			System.out.println("Database getStringFunctions: " + databaseMetaData.getStringFunctions());
			System.out.println("Database getNumericFunctions: " + databaseMetaData.getNumericFunctions());
			System.out.println("Database getSystemFunctions: " + databaseMetaData.getSystemFunctions());
			System.out.println("Database supportsBatchUpdates: " + databaseMetaData.supportsBatchUpdates());
			System.out.println("Database supportsStoredProcedures: " + databaseMetaData.supportsStoredProcedures());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void registerEmployeeDetails(int empId, String empName, double empSal, int deptNo) {

		Connection connection = DBConnector.getConnection();
		try {
			final String INSERT_QUERY = "INSERT INTO employee VALUES(" + empId + ",'" + empName + "'," + empSal + ","
					+ deptNo + ");";
			// System.out.println("Query: " + INSERT_QUERY);

			Statement statement = connection.createStatement();
			int insertResult = statement.executeUpdate(INSERT_QUERY);

			System.out.println("Insert Result: " + insertResult);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void displayFullTable() {
		String SELECT_QUERY = "SELECT * FROM employee";
		Connection connection = DBConnector.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			for (int counter = 1; counter <= resultSetMetaData.getColumnCount(); counter++) {
				System.out.print(resultSetMetaData.getColumnName(counter) + "\t");
			}
			System.out.println("");
			while (resultSet.next()) {
				for (int counter = 1; counter <= resultSetMetaData.getColumnCount(); counter++) {
					System.out.print(resultSet.getString(counter) + "\t");

				}
				System.out.println("");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteEmployee(int empNo) {
		Connection connection = DBConnector.getConnection();
		try {
			Statement statement = connection.createStatement();
			String DELETE_QUERY = "DELETE FROM employee WHERE empNo=" + empNo;
			System.out.println("Delete Result: " + statement.executeUpdate(DELETE_QUERY));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		displayFullTable();

	}

	public void batchTransaction() {
		Connection connection = DBConnector.getConnection();
		try {
			Statement statement = connection.createStatement();
			connection.setAutoCommit(false);
			String batchInsert1 = "INSERT INTO employee VALUES(101,'Deepti',1000000,79);";
			String batchInsert2 = "INSERT INTO employee VALUES(102,'Deepti',1000000,79);";
			String batchInsert3 = "INSERT INTO employee VALUES(103,'Deepti',1000000,79);";

			statement.addBatch(batchInsert1);
			statement.addBatch(batchInsert2);
			statement.addBatch(batchInsert3);

			//
			// System.out.println("1 Insert " + statement.executeUpdate(batchInsert1));
			// System.out.println("2 Insert " + statement.executeUpdate(batchInsert2));
			// System.out.println("3 Insert " + statement.executeUpdate(batchInsert3));
			//

			int batchExecutionResult[] = statement.executeBatch();
			for (int count = 1; count <= batchExecutionResult.length; count++) {
				System.out.println("Result for " + count + " statement is :" + batchExecutionResult[count - 1]);
			}
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		displayFullTable();

	}

}
