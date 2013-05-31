package homemade.apps.framework.homerlibs.utils;

import homemade.apps.framework.homerlibs.utils.HomerLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVReadProc;

public class CsvHelper {

	private static String mDefaultCsvPath = "csv/data.csv";

	public static String getDefaultCsvPath() {
		return mDefaultCsvPath;
	}

	public static void setDefaultCsvPath(String mDefaultCsvPath) {
		CsvHelper.mDefaultCsvPath = mDefaultCsvPath;
	}

	private static CSV csv = CSV.separator(',').quote('\"').skipLines(1)
			.charset("UTF-8").create();

	public static CSV getCsv() {
		return csv;
	}

	public static void setCsv(CSV csv) {
		CsvHelper.csv = csv;
	}

	public CsvHelper() {
		if (csv != null)
			csv = null;
		csv = CSV.separator(';').quote('\'').skipLines(1).charset("UTF-8")
				.create();
	}

	public CsvHelper(String separator, String quote, int skiplines,
			String charset) {
		if (csv != null)
			csv = null;
		csv = CSV.separator(',').quote('\"').skipLines(1).charset("UTF-8")
				.create();

	}

	/**
	 * below method does exactly as it says .. it uses the opencsvreader lib and
	 * fetches in a list<String[]> all the values from the csv inorder to change
	 * the csv parameters you will have modify this function while initiating
	 * the csvreader you can specify its parameters such as separator and quote
	 * charecter line skip etc etc
	 * 
	 * @param context
	 * @param path
	 * @return
	 */
	public static final List<String[]> parseRawCsvFromAssets(Context context,
			String path) {

		final List<String[]> questionList = new ArrayList<String[]>();

		AssetManager assetManager = context.getAssets();

		String pathtoread = path != null ? path : mDefaultCsvPath;
		try {
			InputStream csvStream = assetManager.open(pathtoread);
			InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
			if (csvStreamReader != null) {
				csv.read(csvStream, new CSVReadProc() {
					@Override
					public void procRow(int rowIndex, String... values) {

						questionList.add(values);

					}
				});
			} else {
				HomerLogger
						.d("NULL POINTER !!!!!! Could not find any csv file on the path :: "
								+ pathtoread
								+ " please specify a correct path where the file actually exists");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}

	/**
	 * below method does exactly as it says .. it uses the opencsvreader lib and
	 * fetches in a list<String[]> all the values from the csv inorder to change
	 * the csv parameters you will have modify this function while initiating
	 * the csvreader you can specify its parameters such as separator and quote
	 * charecter line skip etc etc
	 * 
	 * @param context
	 * @param path
	 * @return
	 */
	public static final List<String[]> parseRawCsvFromSdcard(Context context,
			String path) {

		final List<String[]> questionList = new ArrayList<String[]>();

		AssetManager assetManager = context.getAssets();

		String pathtoread = path != null ? path : mDefaultCsvPath;
		try {

			File file = new File(pathtoread);

			if (file.exists()) {
				InputStream csvStream = new FileInputStream(file);
				InputStreamReader csvStreamReader = new InputStreamReader(
						csvStream);

				csv.read(csvStream, new CSVReadProc() {
					@Override
					public void procRow(int rowIndex, String... values) {

						questionList.add(values);

					}
				});
			} else {
				HomerLogger
						.d("NULL POINTER !!!!!! Could not find any csv file on the path :: "
								+ pathtoread
								+ " please specify a correct path where the file actually exists");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}

	/**
	 * below method is a small helper function that helps log all values fetched
	 * from the parseRawCsvFromAssets(context path) method csv reader
	 */
	public static void LogAllValuesFromParsedRawCsv(List<String[]> csv) {

		for (int i = 0; i < csv.size(); i++) {

			String[] a = csv.get(i);
			for (int j = 0; j < a.length; j++) {
				String h = a[j];
				HomerLogger.d("at row " + i + " at collumn " + j
						+ " the value is " + h);
			}
		}

	}

}
