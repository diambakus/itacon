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
	
	@Test
	public void validEmail() {
		String email = "a@b.c";
		assertTrue(textUtils.isValidEmail(email));
		email = "@b.com";
		assertFalse(textUtils.isValidEmail(email));
		email = "a.b.com";
		assertFalse(textUtils.isValidEmail(email));
		email="a@.com";
		assertFalse(textUtils.isValidEmail(email));
		email="a@com";
		assertTrue(textUtils.isValidEmail(email));
		email="a@";
		assertFalse(textUtils.isValidEmail(email));
		email="a@b.com.br";
		assertTrue(textUtils.isValidEmail(email));
		email="a1@b2.com.br";
		assertTrue(textUtils.isValidEmail(email));
	}
	
	@Test
	public void validUsername() {
		String username = "_0_1..2";
		assertTrue(textUtils.isValidUsername(username));
		username = "/ant";
		assertFalse(textUtils.isValidUsername(username));
		username = "ant";
		assertTrue(textUtils.isValidUsername(username));
		username = "AN";
		assertTrue(textUtils.isValidUsername(username));
		username = "012";
		assertTrue(textUtils.isValidUsername(username));
		username = "*0&2test";
		assertFalse(textUtils.isValidUsername(username));
	}
}
