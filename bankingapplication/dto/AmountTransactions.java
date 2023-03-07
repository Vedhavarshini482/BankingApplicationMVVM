package com.bankingapplication.dto;

public class AmountTransactions {

	private String ownAccountNumber;
	private String accountNumber;
	private String amount;
	private String date;

	public AmountTransactions(){
		
	}
	
	public AmountTransactions(String ownAccountNumber,String accountNumber,String amount,String date){
		this.ownAccountNumber=ownAccountNumber;
		this.accountNumber=accountNumber;
		this.amount=amount;
		this.date=date;
	}
	
	public String getOwnAccountNumber() {
		return ownAccountNumber;
	}

	
	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAmount() {
		return amount;
	}

	public String getDate() {
		return date;
	}

}