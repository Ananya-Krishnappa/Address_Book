/**
 * Purpose:Address Book System
 * @author Ananya K
 * @version 1.0
 * @since 24/06/2021
 * 
 */
package com.bridgelabz;

public class AddressBookMain {

	public static void main(String[] args) throws AddressBookException {
		try {
			IAddressBook addressBookName = new AddressBookService();
			addressBookName.addAddressBook("Book1");
			addressBookName.populateAddressBook("Book1");
			addressBookName.findPersonByCity("Bengaluru");
			addressBookName.findPersonByState("Karnataka");
			addressBookName.getPersonCountByCity("Bengaluru");
			addressBookName.getPersonCountByState("Karnataka");
			addressBookName.sortByPersonName();
			addressBookName.sortPersonByAttribute("city");
			addressBookName.sortPersonByAttribute("state");
			addressBookName.sortPersonByAttribute("zip");
		} catch (AddressBookException e) {
			System.exit(1);
		}
		System.exit(0);
	}
}
