package com.kikia.itacon.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringProcessingTest {

	@Test
	public void isDeviredOriginalStringWithoutNonASCII() {
		String original ="Ministério das Obras Públicas";
		String derived = original.replaceAll("[^a-z|A-Z]", "");
		assertTrue(derived.equals("MinistriodasObrasPblicas"));
	}
}
