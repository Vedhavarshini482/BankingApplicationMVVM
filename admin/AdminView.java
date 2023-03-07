package com.bankapplication.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bankingapplication.accountmanagement.AccountManagementView;

public class AdminView {

	private AdminModel model;
	private Scanner scanner = new Scanner(System.in);

	public AdminView() {
		model = new AdminModel(this);
	}

	public void adminView() {
		System.out.println("Enter your choice\n");
		System.out.println("1.User Account details\n2.Search user by accountNumber\n3.Back\n4.Exit");
		String choice = scanner.nextLine();
		switch (choice) {
		case "1":
			userDetails();
			adminView();
			break;
		case "2":
			searchAccount();
			adminView();
			break;
		case "3":
			System.out.println("\t\t\t\tYour Account logged out");
			new AccountManagementView().login();
			break;
		case "4":
			exit();
		}
	}

	public void userDetails() {
		model.userDetails();
	}

	public void searchAccount() {
		System.out.println("Enter an account number");
		String accountNumber = scanner.nextLine();
		model.searchAccount(accountNumber);
	}

	public void exit() {
		System.out.println("\t\t\t\tTHANKYOU ADMIN");
		System.exit(0);
	}

	public void showDetails(ArrayList<List<String>> usersInfo) {
		for (int i = 0; i < usersInfo.size(); i++) {
			System.out
					.println("\n=====================================================================================");
			System.out.println("\t\t\t\tINDIAN BANK");
			System.out.println("Name : " + usersInfo.get(i).get(0) + "\tAge : " + usersInfo.get(i).get(1)
					+ "\tMobile Number : " + usersInfo.get(i).get(2));
			System.out.println("Address : " + usersInfo.get(i).get(3));
			System.out.println("Account Number : " + usersInfo.get(i).get(4));
			System.out.println("Balance : " + usersInfo.get(i).get(5));
			System.out
					.println("=====================================================================================\n");
		}
	}

	public void invalidAccount(String string) {
		System.out.println(string);
		adminView();
	}

	public void validAccount(ArrayList<String> userInfo) {
		System.out.println("\n=====================================================================================");
		System.out.println("\t\t\t\tINDIAN BANK");
		System.out.println(
				"Name : " + userInfo.get(0) + "\tAge : " + userInfo.get(1) + "\tMobile Number : " + userInfo.get(2));
		System.out.println("Address : " + userInfo.get(3));
		System.out.println("Account Number : " + userInfo.get(4));
		System.out.println("Balance : " + userInfo.get(5));
		System.out.println("=====================================================================================\n");
	}
}