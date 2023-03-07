package com.bankingapplication.userlogin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.bankingapplication.accountmanagement.AccountManagementView;

public class UserView  {

	private UserModel model;
	private Scanner scanner = new Scanner(System.in);
	private String accountNumber;
	private String receiverAcc;
	private String amount;

	public UserView() {
		model = new UserModel(this);
	}

	public void userAccount(String accountNumber) {
		this.accountNumber = accountNumber;
		userView();
		model.exit();
	}

	public void userView() {
		System.out.println("Enter your choice");
		System.out.println(
				"\n1.Display account details\n2.Transaction\n3.Debit amount\n4.Credit amount\n5.Change pin number\n6.Back\n7.Exit");
		String choice = scanner.nextLine();
		switch (choice) {
		case "1":
			displayAccount();
			userView();
			break;
		case "2":
			transaction();
			model.exit();
			userView();
			break;
		case "3":
			debitAmount();
			model.exit();
			userView();
			break;
		case "4":
			creditAmount();
			model.exit();
			userView();
		case "5":
			changePinNumber();
			model.exit();
			userView();
			break;
		case "6":
			System.out.println("\t\t\t\tYour Account logged out");
			new AccountManagementView().login();
			break;
		case "7":
			exit();
		}
	}

	private void changePinNumber() {
		System.out.println("Enter your mobile number");
		String mobileNumber = scanner.nextLine();
		System.out.println("Enter your M-Pin");
		String mPin = scanner.nextLine();
		model.validMobileNumber(accountNumber, mobileNumber, mPin);
	}

	public void displayAccount() {
		model.displayAccount(accountNumber);
	}

	public void transaction() {
		System.out.println("Enter the receiver account number");
		receiverAcc = scanner.nextLine();
		model.isValid(receiverAcc);
	}

	public void debitAmount() {
		System.out.println("Enter how much you want to debit");
		String amount = scanner.nextLine();
		model.validAmountDebit(accountNumber, amount);
	}

	public void creditAmount() {
		System.out.println("Enter your amount");
		String amount = scanner.nextLine();
		model.addAmount(amount, accountNumber);
	}

	public void exit() {
		System.out.println("\t\t\tTHANKYOU FOR VISITING OUR BANKING APPLICATION:)");
		System.exit(0);
	}

	public void inValidAccountNumber(String string) {
		System.out.println(string);
		String go = scanner.nextLine();
		if (!go.equals("0"))
			transaction();
		else
			exit();
	}

	public void validAccountNumber() {
		System.out.println("Enter amount");
		amount = scanner.nextLine();
		model.validAmount(accountNumber, amount);
	}

	public void transferMoney() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(date);
		model.transactionDetails(accountNumber, receiverAcc, amount, strDate);
	}

	public void inSufficientBalance(String string) {
		System.out.println(string);
		String go = scanner.nextLine();
		if (!go.equals("0"))
			validAccountNumber();
		else
			exit();
	}

	public void failed(String string) {
		System.out.println(string);
		exit();
	}

	public void success(String string) {
		System.out.println(string);
	}

	public void inValidAmount(String string) {
		System.out.println(string);
		String go = scanner.nextLine();
		if (go.equals("0"))
			exit();
		else
			validAccountNumber();
	}

	public void showUserDetails(String[] userArray) {
		System.out.println("=====================================================================================");
		System.out.println("\t\t\t\tINDIAN BANK");
		System.out.println("Name : " + userArray[0] + "\tAge : " + userArray[1] + "\tMobile Number : " + userArray[2]);
		System.out.println("Address : " + userArray[3]);
		System.out.println("Account Number : " + userArray[4]);
		System.out.println("Balance : " + userArray[5]);
		System.out.println("=====================================================================================");
	}

	public void captcha(String password, String string) {
		System.out.println(string);
		System.out.println(password);
		String dup = scanner.nextLine();
		if (password.equals(dup)) {
			System.out.println("Enter your new pin number");
			String pinNumber = scanner.nextLine();
			model.pinNumber(accountNumber, pinNumber);
		} else {
			System.out.println("You entered a wrong captcha..Do you want to exit enter 0 or otherwise 1");
			String go = scanner.nextLine();
			if (go.equals("0"))
				exit();
			else
				captcha(password, string);
		}
	}

	public void setPin(String mobileNumber, String mPin) {
		model.changePinNumber(mobileNumber, mPin);
	}

	public void invalidMobileNumber(String string) {
		System.out.println(string);
		String go = scanner.nextLine();
		if (go.equals("0"))
			exit();
		else
			changePinNumber();
	}
}