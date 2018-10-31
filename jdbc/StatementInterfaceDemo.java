package com.marlabs.jdbc;

import java.util.Scanner;

public class StatementInterfaceDemo {

	public static void main(String[] args) {
		System.out.println("Main Starts");
		System.out.println("********************Choice**************************");
		System.out.println("1) Display Department Employee ");
		System.out.println("2) Register Employee Details");
		System.out.println("3) DataBase Meta Data");
		System.out.println("4) Display Data");
		System.out.println("5) Transaction");
		System.out.println("6) Delete Employee");
		System.out.println("****************************************************");

		StatementAPIExample statementAPIExample = new StatementAPIExample();

		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter The Department Number");
			int departmentNumber = scanner.nextInt();
			statementAPIExample.showEmployeeDetails(departmentNumber);
			break;
		case 2:
			System.out.println("Enter Employee Id");
			int empId = scanner.nextInt();
			System.out.println("Enter Employee Name");
			String empName = scanner.next();
			System.out.println("Enter Employee Sal");
			double empSal = scanner.nextDouble();
			System.out.println("Enter Employee Department Number");
			int deptNo = scanner.nextInt();
			statementAPIExample.registerEmployeeDetails(empId, empName, empSal, deptNo);

			break;
		case 3:
			statementAPIExample.showDatabaseMetaData();
			break;
		case 4:
			statementAPIExample.displayFullTable();
			break;
		case 5:
			statementAPIExample.batchTransaction();
			break;
		case 6:
			System.out.println("Enter Employee Id");
			int empNo = scanner.nextInt();
			statementAPIExample.deleteEmployee(empNo);
			break;
		default:
			break;
		}
		scanner.close();

		System.out.println("Main Ends");
	}

}
