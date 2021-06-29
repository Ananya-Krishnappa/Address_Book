/**
 * Purpose:Util to read and write json
 * @author Ananya K
 * @version 1.0
 * @since 29/06/2021
 * 
 */
package com.bridgelabz.addressbook.utils;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabz.addressbook.dto.AddressDictionary;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.service.impl.AddressBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static final Logger LOG = LogManager.getLogger(CsvUtil.class);
	private final static String JSON_LOCATION = "src/main/resources/addressBook.json";

	/**
	 * Reading the Json file and convert to java object using Jackson ObjectMapper
	 * readValue method
	 * 
	 * @throws AddressBookException
	 */
	public static void readJson() throws AddressBookException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			// JSON file to Java object
			AddressBookService.addressDictionary = mapper.readValue(new File(JSON_LOCATION), AddressDictionary.class);
			LOG.debug(AddressBookService.addressDictionary.getAddressBookList().toString());
		} catch (IOException e) {
			throw new AddressBookException(e.getMessage());
		}
	}

	/**
	 * Converting the Java objects to JSON file writeValueAsString
	 * 
	 * @throws AddressBookException
	 */
	public static void writeToJson() throws AddressBookException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(JSON_LOCATION), AddressBookService.addressDictionary);
		} catch (IOException e) {
			throw new AddressBookException(e.getMessage());
		}
	}
}
