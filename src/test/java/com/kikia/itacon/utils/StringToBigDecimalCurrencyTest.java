package com.kikia.itacon.utils;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringToBigDecimalCurrencyTest {

	private DecimalFormatSymbols symbols;
	private String pattern = "###,###,###.##";
	private DecimalFormat decimalFormat = null;
	private final Logger logger = LoggerFactory.getLogger(StringToBigDecimalCurrencyTest.class);

	public StringToBigDecimalCurrencyTest() {
		// Locale ptBr = new Locale("pr", "BR"); /*Locale as parameter of
		// DecimalFormatSymbols(ptBR) below*/
		symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
	}

	@Test
	public void verifyStringToBigDecimalCurrencyTest() {

		BigDecimal expectedValue = null;
		try {
			expectedValue = (BigDecimal) decimalFormat.parse("11,222.33");
			String presentationPrice = "$11,222.33";
			String priceWithoutDollar = presentationPrice.replace("$", "");
			BigDecimal workedValue = (BigDecimal) decimalFormat.parse(priceWithoutDollar);

			assertTrue(workedValue.compareTo(expectedValue) == 0);
		} catch (ParseException e) {
			logger.error("StringToBigDecimalCurrencyTest.verifyStringToBigDecimalCurrencyTest",
					"Falhou a conversão do valor: " + expectedValue, e);
		}

	}
}
