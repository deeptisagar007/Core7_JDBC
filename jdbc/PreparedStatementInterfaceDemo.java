package com.marlabs.jdbc;

import java.util.Scanner;

public class PreparedStatementInterfaceDemo {

	public static void main(String[] args) {
		System.out.println("PreparedStatementInterfaceDemo Main Starts");
		Scanner scanner = new Scanner(System.in);

		PreparedStatementAPIExample preparedStatementAPIExample = new PreparedStatementAPIExample();

		preparedStatementAPIExample.preparedStatementInsertDemo(104, "NewDeepti", 100.0001, 40);

		System.out.println("*********************************************************");
		System.out.println("1) Display Data ");
		System.out.println("2) Insert Data ");
		System.out.println("*********************************************************");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter the Table name");
			String tableName = scanner.next();
			preparedStatementAPIExample.preparedStatemenFortDisplay(tableName);
			break;
		case 2:
			System.out.println("Enter Employee Number");
			int empId = scanner.nextInt();
			System.out.println("Enter Employee Name");
			String empName = scanner.next();
			System.out.println("Enter Employee Salary");
			double empSal = scanner.nextDouble();
			System.out.println("Enter Employee DeptNo");
			int deptNo = scanner.nextInt();
			preparedStatementAPIExample.preparedStatementInsertDemo(empId, empName, empSal, deptNo);
			break;
		default:
			break;
		}
		scanner.close();
		System.out.println("PreparedStatementInterfaceDemo Main Ends");
	}

}
