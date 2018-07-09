package com.kikia.itacon.controllers.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;
	private String url = "http://localhost:";
	private final String introductoryMessage = "Welcome!";

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void defaultPageShouldContainIntroductoryMessage() throws Exception {
		assertThat(this.testRestTemplate.getForObject(url + port + "/itacon/", String.class))
				.contains(introductoryMessage);
	}
}
