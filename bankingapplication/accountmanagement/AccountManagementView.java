package com.bankingapplication.accountmanagement;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bankapplication.admin.AdminView;
import com.bankingapplication.userlogin.UserView;

public class AccountManagementView {

	private AccountManagementModel model;
	private Scanner scanner = new Scanner(System.in);
	private String accountNumber;

	public AccountManagementView() {
		model = new AccountManagementModel(this);
	}

	public static void main(String[] args) {
		new AccountManagementView().welcome();
	}

	public void welcome() {
		System.out.println("\t\t-->WELCOME TO INDIAN BANK<--");
		login();
	}

	public void login() {
		System.out.println("1.Admin Login\n2.User Login\n3.Create New Account\n4.Exit");
		String choice = scanner.nextLine();
		switch (choice) {
		case "1":
			adminLogin();
			break;
		case "2":
			userLogin();
			break;
		case "3":
			createAccount();
			break;
		case "4":
			exit();
		default:
			System.out.println("Enter a valid number");
			login();
		}
	}

	public void createAccount() {
		System.out.println("1.Savings Account\n2.Business Account\n3.Back\n4.Exit");
		String choice = scanner.nextLine();
		switch (choice) {
		case "1":
			savingAccount();
			break;
		case "2":
			businessAccount();
			break;
		case "3":
			login();
		case "4":
			exit();
		default:
			System.out.println("Enter a valid number");
			createAccount();
		}
	}

	public void savingAccount() {
		String name = name();
		int age = age();
		String mobileNumber = mobileNumber();
		String address = address();
		model.generateAccountNumber(name, age, mobileNumber, address);
	}

	public String name() {
		System.out.println("Enter your full name(ex:Varsha R)");
		String name = scanner.nextLine().toUpperCase();
		return name;
	}

	public int age() {
		System.out.println("Enter your age(Age must be in above 17) ");
		int age = scanner.nextInt();
		scanner.nextLine();
		if (age < 18) {
			System.out.println("Enter your age correctly");
			age();
		}
		return age;
	}

	public String mobileNumber() {

		System.out.println("Enter your mobile number");
		String mobileNumber = scanner.nextLine();
		String MOBILE_NUMBER_PATTERN = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
		Pattern pattern = Pattern.compile(MOBILE_NUMBER_PATTERN);
		Matcher matcher = pattern.matcher(mobileNumber);
		if (!matcher.matches()) {
			System.out.println("Enter a valid mobile number");
			mobileNumber();
		}
		return mobileNumber;
	}

	public String address() {
		System.out.println("Enter your address");
		String address = scanner.nextLine();
		return address;
	}

	public void businessAccount() {
		String name = name();
		int age = age();
		String mobileNumber = mobileNumber();
		String address = address();
		model.generateAccountNumber(name, age, mobileNumber, address);
	}

	public void userLogin() {
		System.out.println("Enter your account number");
		accountNumber = scanner.nextLine();
		System.out.println("Enter your M-Pin");
		String mPin = scanner.nextLine();
		model.validLogin(accountNumber, mPin);
	}

	public void adminLogin() {
		System.out.println("Enter Your user name");
		String userName = scanner.nextLine();
		System.out.println("Enter your password");
		String password = scanner.nextLine();
		model.validAdmin(userName, password);
	}

	public void exit() {
		System.out.println("\t\tTHANKYOU FOR VISITING OUR BANKING APPLICATION:)");
		System.exit(0);
	}

	public void accountCreated(String string, String[] user) {
		System.out.println(string);
		model.exit();
		setPinNumber(user);
	}

	private void setPinNumber(String[] user) {
		System.out.println("Set your four digit pin number");
		String pinNumber = scanner.nextLine();
		if (pinNumber.length() != 4) {
			System.out.println("Enter a valid pin number");
			setPinNumber(user);
		}
		accountNumber = user[4];
		model.setPinNumber(accountNumber, pinNumber);
		mPin(accountNumber);
	}

	public void mPin(String accountNumber) {
		System.out.println("Enter your four digit M-Pin");
		String mPin = scanner.nextLine();
		if (mPin.length() != 4) {
			System.out.println("Enter a valid M-Pin");
			mPin(accountNumber);
		}
		model.setMPin(accountNumber, mPin);
		System.out.println("Your Account Number : " + accountNumber);
		new UserView().userAccount(accountNumber);
	}

	public void validAdmin(String string) {
		System.out.println(string);
		new AdminView().adminView();
	}

	public void invalidAdmin(String string) {
		System.out.println(string);
		login();
	}

	public void validUser(String string) {
		System.out.println(string);
		new UserView().userAccount(accountNumber);
	}

	public void invalidUser(String string) {
		System.out.println(string);
		login();
	}

	public void validNumber(String string) {
		System.out.println(string);
	}
}