package com.kikia.itacon.converter;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigDecimalConverter extends PropertyEditorSupport {

	private DecimalFormatSymbols symbols;
	private String PATTERN = "###,###,###.##";
	private DecimalFormat decimalFormat = null;
	private final Logger logger = LoggerFactory.getLogger(BigDecimalConverter.class);

	public BigDecimalConverter() {
		symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		decimalFormat = new DecimalFormat(PATTERN, symbols);
		decimalFormat.setParseBigDecimal(true);
	}

	public void setAsText(String text) {

		String TAG = "BigDecimalConverter.setAsText";
		try {
			BigDecimal value = (BigDecimal) decimalFormat.parse(text);
			setValue(value);
		} catch (ParseException e) {
			logger.error(TAG, "A conversão de String para BigDecimal falhou: " + text, e);
		}
	}

	public String getAsText() {
		BigDecimal value = (BigDecimal) getValue();
		return value != null ? value.toPlainString() : "";
	}
}
