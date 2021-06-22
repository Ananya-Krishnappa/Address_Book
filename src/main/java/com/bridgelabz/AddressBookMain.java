package com.bridgelabz;

public class AddressBookMain {

	public static void main(String[] args) throws AddressBookException {
		try {
			IAddressBook addressBookName = new AddressBookService();
			addressBookName.addAddressBook("Book1");
			addressBookName.populateAddressBook("Book1");
			addressBookName.findPersonByCity("Bengaluru");
		} catch (AddressBookException e) {
			System.exit(1);
		}
		System.exit(0);
	}
}
