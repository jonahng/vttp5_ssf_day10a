package vttp.batch5.ssf.noticeboard.services;

import java.net.http.HttpHeaders;
import java.util.Date;

import org.json.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp.batch5.ssf.noticeboard.models.Notice;

@Service
public class NoticeService {
	RestTemplate restTemplate = new RestTemplate();

	// TODO: Task 3
	// You can change the signature of this method by adding any number of parameters
	// and return any type
	public void postToNoticeServer(Notice notice) {
		//JsonObjectBuilder job = new JsonObjectBuilder();
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
 		JsonObject jsonObject = factory.createObjectBuilder()
		.add("title",notice.getTitle())
		.add("poster",notice.getPoster())
		.add("postDate",convertDateToEpoch(notice.getPostDate()))
		//.add(convertArrayToJsonArray(notice.getCategories())) //THIS ONE MAY NEED EXTRA CODE
		.add("text", notice.getText())
		.build();
		jsonObject.put("categories", convertArrayToJsonArray(notice.getCategories()));
		System.out.println("built the json object, here it is" + jsonObject.toString());

		String jsonString = jsonObject.toString();

		//replace this with new url and allow it to be changed externally.
		String url = "https://publishing-production-d35a.up.railway.app/notice";

		HttpHeaders headers = new HttpHeaders(String);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(jsonString, header);

		String response = restTemplate.postForObject(url, , null);
		System.out.println(response);

	}

	public long convertDateToEpoch(Date date){
		long epoch = date.getTime();
		return epoch;
	}

	public JSONArray convertArrayToJsonArray(String[] array){
		JSONArray categories = new JSONArray(array);
		
		return categories;
	}

	
}
