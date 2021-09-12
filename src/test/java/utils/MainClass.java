package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class MainClass {

	public static void main(String args[]) throws IOException {

		URL url = new URL("https://icms-dev.taqadhi.site/prweb/api/FIRegisterCase/v1/FIRegisterCase");

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("POST");

		con.setRequestProperty("Content-Type", "application/json; utf-8");

		con.setRequestProperty("Accept", "application/json");

		con.setDoOutput(true);

		File file = new File("src/test/resources/Payload.json");

		BufferedReader br = new BufferedReader(new FileReader(file));
		br.close();

		String st;
		while ((st = br.readLine()) != null) {
			System.out.println(st);
		}

	}

	public static boolean test(String firstString, String secondString) {

		if (firstString.contains(secondString)) {
			return true;
		} else {
			return false;
		}

	}

	public static void readCSVData() {

		String line = "";
		try {
			// parsing a CSV file into BufferedReader
			BufferedReader br = new BufferedReader(new FileReader("CSVDemo.csv"));
			while ((line = br.readLine()) != null) {
				String[] usersCredentials = line.split(","); // use comma as separator
				System.out.println("Username:" + usersCredentials[0] + ", Password:" + usersCredentials[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void testapipostcode(String postcode) {
		try {
			URL url = new URL("http://api.postcodes.io/postcodes/" + postcode);

			// Object test = url;

			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			http.disconnect();

			String s = "hello";
			Object obj = s;
			System.out.println(obj.getClass());
			// System.out.println(test.getClass());

		} catch (Exception ex) {

		}
	}

}
