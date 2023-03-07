package com.bankingapplication.dto;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String name;
	private int age;
	private String mobileNumber;
	private String accountNumber;
	private String balance;
	private String address;
	private String pinNumber;
	private String mPin;

	public String getmPin() {
		return mPin;
	}

	public void setmPin(String mPin) {
		this.mPin = mPin;
	}

	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getPinNumber() {
		return pinNumber;
	}

	public String getAddress() {
		return address;
	}

	User() {

	}

	private List<AmountTransactions> creditTransactions;

	private List<AmountTransactions> debitTransactions;

	public List<AmountTransactions> getCreditTransactions() {
		return creditTransactions;
	}

	public void setCreditTransactions(List<AmountTransactions> creditTransactions) {
		this.creditTransactions = creditTransactions;
	}

	public List<AmountTransactions> getDebitTransactions() {
		return debitTransactions;
	}

	public void setDebitTransactions(List<AmountTransactions> debitTransactions) {
		this.debitTransactions = debitTransactions;
	}

	public void updateBalance(double amount) {
		double money=amount+Double.parseDouble(this.balance);
		this.balance=Double.toString(money);
	}

	public User(String name, int age, String mobileNumber, String address, String accountNumber, String balance) {
		this.name = name;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.accountNumber = accountNumber;
		this.address = address;
		this.balance = balance;
		this.creditTransactions = new ArrayList<>();
		this.debitTransactions = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", mobileNumber=" + mobileNumber + "address=" + address
				+ ", accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

}