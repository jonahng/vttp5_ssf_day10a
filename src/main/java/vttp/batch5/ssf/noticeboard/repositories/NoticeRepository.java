package vttp.batch5.ssf.noticeboard.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository //added this myself
public class NoticeRepository {
	@Autowired
	@Qualifier("notice")
	RedisTemplate<String, Object> template;




	// TODO: Task 4
	// You can change the signature of this method by adding any number of parameters
	// and return any type
	// 
	/*
	 * Write the redis-cli command that you use in this method in the comment. 
	 * For example if this method deletes a field from a hash, then write the following
	 * redis-cli command 
	 * 	hdel myhashmap a_key
	 *
	 *rpush key value
	 */
	public void insertNotices(String key, String value) {
		//THE redis-cli command is: 
		// rpush key value
		template.opsForList().rightPush(key,value);
		//THE redis-cli command is: 
		// rpush key value


		System.out.println("added notice to repo!" + value);

	}


	public String getHealthCheckKey(){

		
		String randomKeyFromRedis = "nokeyfromredis";

		try {
			randomKeyFromRedis = template.randomKey();
			//The redis-cli command is:
			//RANDOMKEY
			//this command will return a random key from the current database

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		return randomKeyFromRedis;
	}


}
