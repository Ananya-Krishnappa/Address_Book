/**
 * Purpose:Address Book System
 * @author Ananya K
 * @version 1.0
 * @since 29/06/2021
 * 
 */
package com.bridgelabz.addressbook;

import java.io.IOException;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.service.IAddressBook;
import com.bridgelabz.addressbook.service.impl.AddressBookService;
import com.bridgelabz.addressbook.utils.CsvUtil;
import com.bridgelabz.addressbook.utils.JsonUtil;

public class AddressBookMain {

	public static void main(String[] args) throws AddressBookException, IOException {
		try {
			IAddressBook addressBookName = new AddressBookService();
			JsonUtil.readJson();
			addressBookName.findPersonByCity("Bengaluru");
			addressBookName.findPersonByState("Karnataka");
			addressBookName.getPersonCountByCity("Bengaluru");
			addressBookName.getPersonCountByState("Karnataka");
			addressBookName.sortByPersonName();
			addressBookName.sortPersonByAttribute("city");
			addressBookName.sortPersonByAttribute("state");
			addressBookName.sortPersonByAttribute("zip");
			JsonUtil.writeToJson();
			CsvUtil.writeToCsv();
			CsvUtil.readCsv();
		} catch (AddressBookException e) {
			System.exit(1);
		}
		System.exit(0);
	}
}
