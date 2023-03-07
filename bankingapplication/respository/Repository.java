package com.bankingapplication.respository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bankingapplication.dto.Admin;
import com.bankingapplication.dto.AmountTransactions;
import com.bankingapplication.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Repository {

	private List<Admin> adminDetails = new ArrayList<>();
	private Data data;
	private HashMap<String, User> users = new HashMap<>();
	private static Repository repository;

	private Repository() {
		this.readFile();
	}

	public void exit() {
		this.writeFile();
	}

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
			repository.adminInfo();
		}
		return repository;
	}

	private void adminInfo() {
		adminDetails.add(new Admin("Varsha", "0000"));
		adminDetails.add(new Admin("Admin", "0000"));
	}

	public Admin validAdmin(String userName, String password) {
		for (Admin admin : adminDetails) {
			if (admin.getUserName().equals(userName) && admin.getPassword().equals(password))
				return admin;
		}
		return null;
	}

	public User validUser(String accountNumber, String mPin) {
		for (User user : data.getUsers()) {
			if (user.getAccountNumber().equals(accountNumber) && user.getmPin().equals(mPin))
				return user;
		}
		return null;
	}

	public void removeUser(String accountNumber) {
		for (User user : data.getUsers()) {
			if (accountNumber.equals(user.getAccountNumber())) {
				data.getUsers().remove(user);
				break;
			}
		}
	}

	public void transaction(User user, AmountTransactions transaction) {
		user.getDebitTransactions().add(transaction);
		user.updateBalance(Double.parseDouble(transaction.getAmount()) * (-1));

		AmountTransactions transaction2 = new AmountTransactions(transaction.getAccountNumber(),
				transaction.getOwnAccountNumber(), transaction.getAmount(), transaction.getDate());
		User user2 = this.users.get(transaction.getAccountNumber());
		user2.getCreditTransactions().add(transaction2);
		user2.updateBalance(Double.parseDouble(transaction.getAmount()));
		exit();
	}

	private void writeFile() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("src//com//bankingapplication//respository//Data.json").getAbsoluteFile(), data);
		} catch (IOException e) {
			System.out.println("Cannot write file");
		}
	}

	private void readFile() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			data = (Data) mapper.readValue(
					new File("src//com//bankingapplication//respository//Data.json").getAbsoluteFile(), Data.class);
			for (User user : data.getUsers()) {
				users.put(user.getAccountNumber(), user);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Cannot read file");
		}
	}

	public String[] generateAccountNumber(String name, int age, String mobileNumber, String address) {
		String accountNumber = "";
		for (int i = 0; i < name.length(); i++)
			accountNumber += (int) name.charAt(i);
		accountNumber += age + mobileNumber;
		for (int i = 0; i < address.length(); i++)
			accountNumber += (int) address.charAt(i);
		accountNumber = accountNumber.substring(0, 17);
		User user = new User(name, age, mobileNumber, address, accountNumber, "0.0");
		data.getUsers().add(user);
		users.put(accountNumber, user);
		user = users.get(accountNumber);

		String[] array = new String[] { user.getName(), Integer.toString(user.getAge()), user.getMobileNumber(),
				user.getAddress(), user.getAccountNumber(), user.getBalance() };
		return array;
	}

	public String[] displayAccount(String accountNumber) {
		User user = users.get(accountNumber);
		String[] array = new String[] { user.getName(), Integer.toString(user.getAge()), user.getMobileNumber(),
				user.getAddress(), user.getAccountNumber(), user.getBalance() };
		return array;
	}

	public boolean isValid(String receiverAcc) {
		for (User user : data.getUsers())
			if (user.getAccountNumber().equals(receiverAcc)) {
				return true;
			}
		return false;
	}

	public String validAmount(String accountNumber) {
		User user = users.get(accountNumber);
		return user.getBalance();
	}

	public boolean transactionDetails(String accountNumber, String receiverAcc, String amount, String strDate) {
		User user = users.get(accountNumber);
		String beforeTransaction = user.getBalance();
		AmountTransactions transaction = new AmountTransactions(accountNumber, receiverAcc, amount, strDate);
		transaction(user, transaction);
		String afterTransaction = user.getBalance();
		if (beforeTransaction != afterTransaction)
			return true;
		return false;
	}

	public boolean addAmount(String amount, String accountNumber) {
		User user = users.get(accountNumber);
		String beforeCredit = user.getBalance();
		double money = Double.parseDouble(user.getBalance()) + Double.parseDouble(amount);
		user.setBalance(Double.toString(money));
		if (!beforeCredit.equals(user.getBalance())) {
			exit();
			return true;
		}
		exit();
		return false;
	}

	public boolean debitAmount(String accountNumber, String amount) {
		User user = users.get(accountNumber);
		String beforeDebit = user.getBalance();
		double money = Double.parseDouble(user.getBalance()) - Double.parseDouble(amount);
		user.setBalance(Double.toString(money));
		if (!beforeDebit.equals(user.getBalance()))
			return true;
		return false;
	}

	public String changePinNumber(String mobileNumber, String mPin) {
		String dupPassword = mobileNumber.substring(0, 5);
		String password = "";
		for (int i = 0; i < mPin.length(); i++) {
			char dup = (char) (dupPassword.charAt(i) + mPin.charAt(i));
			password += dup;
		}
		exit();
		return password;
	}

	public boolean pinNumber(String accountNumber, String pinNumber) {
		User user = users.get(accountNumber);
		String oldPin = user.getPinNumber();
		user.setPinNumber(pinNumber);
		if (!oldPin.equals(pinNumber))
			return true;
		return false;
	}

	public boolean validMobileNumber(String accountNumber, String mobileNumber, String mPin) {
		User user = users.get(accountNumber);
		if (user.getAccountNumber().equals(accountNumber) && user.getmPin().equals(mPin))
			return true;
		else
			return false;
	}

	public ArrayList<List<String>> userDetails() {
		ArrayList<List<String>> usersInfo = new ArrayList<>();
		for (User user : data.getUsers()) {
			ArrayList<String> info = new ArrayList<>();
			info.add(user.getName());
			info.add(Integer.toString(user.getAge()));
			info.add(user.getMobileNumber());
			info.add(user.getAddress());
			info.add(user.getAccountNumber());
			info.add(user.getBalance());
			usersInfo.add(info);
		}
		return usersInfo;
	}

	public ArrayList<String> searchAccount(String accountNumber) {
		User user = users.get(accountNumber);
		ArrayList<String> info = new ArrayList<>();
		info.add(user.getName());
		info.add(Integer.toString(user.getAge()));
		info.add(user.getMobileNumber());
		info.add(user.getAddress());
		info.add(user.getAccountNumber());
		info.add(user.getBalance());

		return info;

	}

	public boolean validCheck(String accountNumber) {
		for (User user : data.getUsers()) {
			if (user.getAccountNumber().equals(accountNumber))
				return true;
		}
		return false;
	}

	public void setPinNumber(String accountNumber, String pinNumber) {
		User user = users.get(accountNumber);
		user.setPinNumber(pinNumber);
		exit();
		readFile();
	}

	public void setMPin(String accountNumber, String mPin) {
		User user = users.get(accountNumber);
		user.setmPin(mPin);
		exit();
		readFile();
	}
}