package com.bankingapplication.userlogin;

import com.bankingapplication.respository.Repository;

public class UserModel {

	private UserView view;

	UserModel(UserView view) {
		this.view = view;
	}

	public void displayAccount(String accountNumber) {
		String userArray[] = Repository.getInstance().displayAccount(accountNumber);
		view.showUserDetails(userArray);
	}

	public void isValid(String receiverAcc) {
		if (!Repository.getInstance().isValid(receiverAcc))
			view.inValidAccountNumber(
					"You entered an invalid account number..Do you want to exit enter 0 or otherwise enter 1");
		else
			view.validAccountNumber();
	}

	public void validAmount(String accountNumber, String amount) {
		if (amount.isEmpty() || amount.equals("0"))
			view.inValidAmount("Please enter valid amount..Do you want to exit enter 0 or otherwise enter 1");
		else {
			String balance = Repository.getInstance().validAmount(accountNumber);
			if (Double.parseDouble(balance) >= Double.parseDouble(amount))
				view.transferMoney();
			else
				view.inSufficientBalance(
						"You have insufficient balance..Do you want to exit enter 0 or otherwise enter 1");
		}
	}

	public void transactionDetails(String accountNumber, String receiverAcc, String amount, String strDate) {
		if (Repository.getInstance().transactionDetails(accountNumber, receiverAcc, amount, strDate))
			view.success("Amount transfered successfully:)");
		else
			view.failed("Oop's something went wrong..Try later");
	}

	public void addAmount(String amount, String accountNumber) {
		if (Repository.getInstance().addAmount(amount, accountNumber))
			view.success("Amount credited successfully");
		else
			view.failed("Oop's something went wrong..Try later");
	}

	public void validAmountDebit(String accountNumber, String amount) {
		if (amount.isEmpty() || amount.equals("0"))
			view.inValidAmount("Please enter valid amount..Do you want to exit enter 0 or otherwise enter 1");
		else {
			String balance = Repository.getInstance().validAmount(accountNumber);
			if (!(Double.parseDouble(balance) >= Double.parseDouble(amount)))
				view.inSufficientBalance(
						"You have insufficient balance..Do you want to exit enter 0 or otherwise enter 1");
			else {
				if (Repository.getInstance().debitAmount(accountNumber, amount))
					view.success("Amount debited successfully");
				else
					view.failed("Oop's something went wrong..Try later");
			}
		}
	}

	public void exit() {
		Repository.getInstance().exit();
	}

	public void changePinNumber(String mobileNumber, String mPin) {
		String password = Repository.getInstance().changePinNumber(mobileNumber, mPin);
		view.captcha(password, "Enter the captcha");
	}

	public void pinNumber(String accountNumber, String pinNumber) {
		if (Repository.getInstance().pinNumber(accountNumber, pinNumber))
			view.success("Pin number set successfully");
		else
			view.failed("Oop's something went wrong..Try later");
	}

	public void validMobileNumber(String accountNumber, String mobileNumber, String mPin) {
		if(Repository.getInstance().validMobileNumber(accountNumber,mobileNumber,mPin))
			view.setPin(mobileNumber,mPin);
		else
			view.invalidMobileNumber("Kindly check your mobile number and M-Pin..Do you want to exit enter 0 or otherwise 1");
	}

}