package vttp.batch5.ssf.noticeboard.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import vttp.batch5.ssf.noticeboard.models.Notice;
import vttp.batch5.ssf.noticeboard.models.ServerResponse;
import vttp.batch5.ssf.noticeboard.repositories.NoticeRepository;

@Service
public class NoticeService {

	@Autowired
	NoticeRepository noticeRepository;


	@Value("${apiurl}")
	public String apiurl;



	RestTemplate restTemplate = new RestTemplate();

	// TODO: Task 3
	// You can change the signature of this method by adding any number of parameters
	// and return any type
	public ServerResponse postToNoticeServer(Notice notice) {
		//JsonObjectBuilder job = new JsonObjectBuilder();


		ArrayList<String> categoriesArray = new ArrayList<String>();

		for(String category: notice.getCategories()){
			categoriesArray.add(category);
			
		}
		System.out.println("categoriesArray:" + categoriesArray.toString());
		
		


		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject jsonObject = factory.createObjectBuilder()
		.add("title",notice.getTitle())
		.add("poster",notice.getPoster())
		.add("postDate",convertDateToEpoch(notice.getPostDate()))
		//.add("categories", categoriesArray.toString() ) //THIS ONE MAY NEED EXTRA CODE
		.add("text", notice.getText())
		.build();
		

		System.out.println("built the json object, here it is" + jsonObject.toString());

		String jsonString = jsonObject.toString();

		String unsuccessfulPostContent = "posting this to get error from server";


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		//JSON STRING GOES INTO HTTP ENTITY
		HttpEntity<String> entity = new HttpEntity<String>(jsonString, headers);

		String response = "No response";

		String idReceived = "noid";
		String errorMessage = "nomessage";

		try {
		response = restTemplate.postForObject(apiurl, entity, String.class);
		System.out.println("posted the following to the server: " + entity);
		System.out.println("RESPONSE FROM SERVER:" + response);
		//Make this add when response is successful only.
		noticeRepository.insertNotices("successfulNotice", jsonString); 

		JsonReader jsonReader = Json.createReader(new StringReader(response));
        JsonObject jo1 = jsonReader.readObject();
		String idFromServer = jo1.getString("id");
			System.out.println("ID RECEIVED FROM SERVER:" + idFromServer);
			idReceived = idFromServer;
		
			
		} catch (Exception e) {
			// TODO: handle exception
			//String messageReceived = jo1.getString("message");
			//System.out.println("Message RECEIVED FROM SERVER:" + messageReceived);
			//errorMessage = messageReceived;

		}



		

		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setId(idReceived);
		serverResponse.setMessage(errorMessage);
		return serverResponse;
		
		


	}

	public long convertDateToEpoch(Date date){
		long epoch = date.getTime();
		return epoch;
	}

	public JSONArray convertArrayToJsonArray(String[] array){
		JSONArray categories = new JSONArray(array);
		
		return categories;
	}

	public Boolean redisHealthCheck(){
		String redisRandomKey = noticeRepository.getHealthCheckKey();
		Boolean healthy = true;
		System.out.println("FOR HEALTHCHECK: the redis random key is: " + redisRandomKey);
		if(redisRandomKey.equals("nokeyfromredis")){
			System.out.println("System unhealthy");
			return false;
		}




		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);


		return true;



	}

	
}
