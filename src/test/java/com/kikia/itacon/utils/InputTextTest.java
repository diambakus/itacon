package com.kikia.itacon.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InputTextTest {

	private TextUtils textUtils;
	
	public InputTextTest() {
		textUtils = new TextUtils();
	}
	
	@Test
	public void isValidInputName() {
		
		String inputName = "Guiné-Bissau";
		assertTrue(textUtils.isValidName(inputName));
		inputName = "Guiné-";
		assertFalse(textUtils.isValidName(inputName));
		inputName = "-Guiné";
		assertFalse(textUtils.isValidName(inputName));
		inputName = "Guiné Cabral";
		assertTrue(textUtils.isValidName(inputName));
		inputName = "Guiné ";
		assertFalse(textUtils.isValidName(inputName));
		inputName = " Guiné";
		assertFalse(textUtils.isValidName(inputName));
		inputName = " Guiné ";
		assertFalse(textUtils.isValidName(inputName));
		inputName = "Guiné";
		assertTrue(textUtils.isValidName(inputName));
	    inputName = "Guiné--Bissau";
	    assertTrue(textUtils.isValidName(inputName));
	    inputName = "Guiné- Bissau";
	    assertTrue(textUtils.isValidName(inputName));
	    inputName = "Guiné  Bissau";
	    assertTrue(textUtils.isValidName(inputName));
	    inputName = "Guiné -Bissau";
	    assertTrue(textUtils.isValidName(inputName));
	    inputName = "Guiné-Bis sau";
	    assertTrue(textUtils.isValidName(inputName));
	    inputName = "Guiné-Bis-sau";
	    assertTrue(textUtils.isValidName(inputName));
	    inputName = "Gu iné Cabral";
	    assertTrue(textUtils.isValidName(inputName));
	    inputName = "";
	    assertFalse(textUtils.isValidName(inputName));
	}
}
