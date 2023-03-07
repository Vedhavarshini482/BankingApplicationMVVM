package com.bankapplication.admin;

import java.util.ArrayList;
import java.util.List;

import com.bankingapplication.respository.Repository;

public class AdminModel {
	private AdminView view;

	AdminModel(AdminView view) {
		this.view = view;
	}

	public void userDetails() {
		ArrayList<List<String>> usersInfo = Repository.getInstance().userDetails();
		view.showDetails(usersInfo);
	}

	public void searchAccount(String accountNumber) {
		if (Repository.getInstance().validCheck(accountNumber)) {
			ArrayList<String> userInfo = Repository.getInstance().searchAccount(accountNumber);
			view.validAccount(userInfo);
		} else
			view.invalidAccount("There is no account exist");
	}
}