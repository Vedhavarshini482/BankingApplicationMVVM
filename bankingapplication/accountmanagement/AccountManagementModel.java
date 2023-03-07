package com.bankingapplication.accountmanagement;

import com.bankingapplication.dto.Admin;
import com.bankingapplication.dto.User;
import com.bankingapplication.respository.Repository;

public class AccountManagementModel {

	private AccountManagementView view;

	AccountManagementModel(AccountManagementView view) {
		this.view = view;
	}

	public void generateAccountNumber(String name, int age, String mobileNumber, String address) {
		String[] user = Repository.getInstance().generateAccountNumber(name, age, mobileNumber, address);
		view.accountCreated("Successfully your account has been created", user);
	}

	public void validAdmin(String userName, String password) {
		Admin admin = Repository.getInstance().validAdmin(userName, password);
		if (admin != null)
			view.validAdmin("\t\tHello " + admin.getUserName());
		else
			view.invalidAdmin("\t\tInvalid login attempt");
	}

	public void validLogin(String accountNumber, String mPin) {
		User user = Repository.getInstance().validUser(accountNumber, mPin);
		if (user != null)
			view.validUser("\t\tHello " + user.getName());
		else
			view.invalidUser("\t\tInvalid Login attempt");
	}

	public void setPinNumber(String accountNumber, String pinNumber) {
		Repository.getInstance().setPinNumber(accountNumber, pinNumber);
		view.validNumber("\t\tYour Pin number created successfully");
	}

	public void setMPin(String accountNumber, String mPin) {
		Repository.getInstance().setMPin(accountNumber, mPin);
		view.validNumber("\t\tYour M-Pin created successfully");
	}

	public void exit() {
		Repository.getInstance().exit();
	}

}