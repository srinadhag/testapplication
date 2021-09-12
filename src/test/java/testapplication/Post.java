package testapplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Post {

	public static void main(String[] args) throws IOException {
		try {

			BufferedReader in = new BufferedReader(new FileReader("src/test/resources/Payload.json"));

			StringBuilder sb = new StringBuilder();

			while (in.read() != -1) {
				sb.append(in.read());
			}

			String contents = sb.toString();
			in.close();

			StringEntity entity = new StringEntity(contents, ContentType.APPLICATION_FORM_URLENCODED);

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost("https://icms-dev.taqadhi.site/prweb/api/FIRegisterCase/v1/FIRegisterCase");

			request.setEntity(entity);

			HttpResponse response = httpClient.execute(request);
			System.out.println(response.getEntity());

			HttpEntity data = response.getEntity();
			String result = EntityUtils.toString(data);

			JSONObject obj = new JSONObject(result);

			System.out.println("Status" + obj.getString("Status"));

		} catch (Exception ex) {
			throw ex;
		}

	}

}
