package com.example.springbootproject.Testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.example.springbootproject.Controller.HomeController;
import static org.assertj.core.api.Assertions.*;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestingWebApplicationTests {
	@Autowired
	private HomeController controller;
	
	@LocalServerPort 
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void contextLoads(){
//		assertThat(controller).isNull();
		assertThat("Frodo").isEqualTo("Frodo");
	}
	
	@Test
	public void haha() {
		assertThat("haha").isEqualTo("haha");
	}
	
	@Test
	public void shouldReturnTrue(){
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Hello, World");
	}
}
