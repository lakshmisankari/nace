package com.assignment.nace;

/**
 * @author Lakshmi Subbiah
 *
 */

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

public class NaceHelper {
	public static String TYPE = "text/csv";
	

	/**
	 * @param file
	 * @return
	 */
	public static boolean hasCSVFormat(MultipartFile file) {
		if (TYPE.equals(file.getContentType()) || file.getContentType().equals("application/vnd.ms-excel")) {
			return true;
		}

		return false;
	}

	/**
	 * @param is
	 * @return
	 */
	public static List<NaceDetails> csvToNace(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<NaceDetails> naceDetailsList = new ArrayList<>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {

				NaceDetails naceDetails = new NaceDetails(Integer.parseInt(csvRecord.get("Order")),
						Integer.parseInt(csvRecord.get("Level")), csvRecord.get("Code"), csvRecord.get("Parent"),
						csvRecord.get("Description"), csvRecord.get("This item includes"),
						csvRecord.get("This item also includes"), csvRecord.get("Rulings"),
						csvRecord.get("This item excludes"), csvRecord.get("Reference to ISIC Rev. 4"));

				naceDetailsList.add(naceDetails);
			}

			return naceDetailsList;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	/**
	 * @param naceDetailsList
	 * @return
	 */
	public static ByteArrayInputStream naceDetailsToCSV(List<NaceDetails> naceDetailsList) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (NaceDetails naceDetails : naceDetailsList) {
				List<String> data = Arrays.asList(String.valueOf(naceDetails.getOrder1()),
						String.valueOf(naceDetails.getLevel()), naceDetails.getCode(), naceDetails.getDescription(),
						naceDetails.getItemincludes(), naceDetails.getItemalsoincludes(), naceDetails.getItemexcludes(),
						naceDetails.getParent(), naceDetails.getReference(), naceDetails.getRulings());

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}
}
