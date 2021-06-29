/**
 * Purpose:Util to read and write csv
 * @author Ananya K
 * @version 1.0
 * @since 29/06/2021
 * 
 */
package com.bridgelabz.addressbook.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabz.addressbook.dto.AddressDictionary;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.service.impl.AddressBookService;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class CsvUtil {
	private static final Logger LOG = LogManager.getLogger(CsvUtil.class);
	private final static String CSV_LOCATION = "src/main/resources/addressBook.csv";

	/**
	 * This method is used to write to csv
	 * 
	 * @throws AddressBookException
	 * @throws IOException
	 */
	public static void writeToCsv() throws AddressBookException, IOException {
		FileWriter writer = new FileWriter(CSV_LOCATION);
		try {
			ColumnPositionMappingStrategy<AddressDictionary> mappingStrategy = new ColumnPositionMappingStrategy<AddressDictionary>();
			mappingStrategy.setType(AddressDictionary.class);
			String[] columns = new String[] { "addressBookList" };
			mappingStrategy.setColumnMapping(columns);
			StatefulBeanToCsvBuilder<AddressDictionary> builder = new StatefulBeanToCsvBuilder<AddressDictionary>(
					writer);
			StatefulBeanToCsv<AddressDictionary> beanWriter = builder.withMappingStrategy(mappingStrategy).build();
			beanWriter.write(AddressBookService.addressDictionary);
		} catch (Exception e) {
			throw new AddressBookException(e.getMessage());
		} finally {
			writer.close();
		}
	}

	/**
	 * Function to read from csv
	 * 
	 * @throws AddressBookException
	 * @throws IOException
	 */
	public static void readCsv() throws AddressBookException, IOException {
		Reader reader = Files.newBufferedReader(Paths.get(CSV_LOCATION));
		CSVReader csvReader = new CSVReader(reader);
		try {
			String[] nextRecord;
			while ((nextRecord = csvReader.readNext()) != null) {
				LOG.debug("AddressDictionary: " + nextRecord[0]);
			}
		} catch (Exception e) {
			throw new AddressBookException(e.getMessage());
		} finally {
			csvReader.close();
		}
	}
}