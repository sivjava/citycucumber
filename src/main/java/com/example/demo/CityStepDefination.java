package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CityStepDefination {

	RestTemplate restTemplate = new RestTemplate();
	
	
	ResponseEntity<City> response ;	
	@When("user sends POST HTTP request")
	public void user_sends_POST_HTTP_request() {
		
		String url =  "http://localhost" + ":" + 9090 + "/city" + "/add";
		System.out.println(url);
		
	   	}

	@Then("user adds  id {}, name {}, pincode {} and state {}")
	public void user_adds_1(Long id,String name,String pincode,String state) {
		 
		String url =  "http://localhost" + ":" + 9090 + "/city" + "/add";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("content", "application/json");
		
		City city = new City();
		city.setId(id);
		city.setName(name);
		city.setPincode(pincode);
        city.setState(state);
        
        HttpEntity<City> entity = new HttpEntity<City>(city,headers);
        
        City result = restTemplate.postForObject(url, entity,City.class);
		assertNotNull(result);
	}
	
	
	@Given("user launches the application and sends a delete request")
	public void user_launches_the_application_and_sends_a_delete_request() throws Exception{
	    String url = "http://localhost" + ":" + 9090;
	}

	@When("user gives id {} to delete a record")
	public void user_gives_id_to_delete_a_record(Long id) {
		String url = "http://localhost" + ":" + 9090 + "/city/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("content", "application/json");
		
		 HttpEntity<Long> entity = new HttpEntity<Long>(id,headers);
		 
		 restTemplate.delete(url, entity);	
		
	}

	@Then("recevies HttpStatus as OK")
	public void recevies_HttpStatus_as_OK() throws Exception{
		System.out.println("OK");
	}

	
	@Given("user launches the application and send getById request")
	public void user_launches_the_application_and_send_getById_request() throws Exception{
	    
		String url = "http://localhost" + ":" + 9090 + "/swagger-ui.html";
		System.out.println(url);
	}

	@When("user gives id {} to see a particular record")
	public void user_gives_id_to_see_a_particular_record(Long id) throws Exception{
	    
		String url = "http://localhost" + ":" + 9090 + "/city/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("content", "application/json");
		
		HttpEntity<Long> entity = new HttpEntity<Long>(id,headers);
		
		City result = restTemplate.getForObject(url, City.class, entity);
		assertNotNull(result);
		
	}

	@Then("user recevies the id {},name {},pincode {},state {}")
	public void user_recevies_the_id_name_pincode_state	(Long id,String name,String pincode,String state) throws Exception {
	   
		String url = "http://localhost" + ":" + 9090 + "/city/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("content", "application/json");
		
		HttpEntity<Long> entity = new HttpEntity<Long>(id,headers);
		
		City result = restTemplate.getForObject(url, City.class, entity);
		assertEquals(id, result.getId());
		assertEquals(name, result.getName());
		assertEquals(pincode, result.getPincode());
		assertEquals(state, result.getState());
		
	}
	
}
