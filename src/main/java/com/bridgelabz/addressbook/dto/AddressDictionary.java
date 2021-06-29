package com.bridgelabz.addressbook.dto;

import java.util.ArrayList;
import java.util.List;

public class AddressDictionary {
	private List<AddressBook> addressBookList = new ArrayList<AddressBook>();

	public AddressDictionary(List<AddressBook> addressBookList) {
		this.addressBookList = addressBookList;
	}

	public AddressDictionary() {

	}

	public List<AddressBook> getAddressBookList() {
		return addressBookList;
	}

	public void setAddressBookList(List<AddressBook> addressBookList) {
		this.addressBookList = addressBookList;
	}

	@Override
	public String toString() {
		return "AddressDictionary [addressBookList=" + addressBookList + "]";
	}
}
