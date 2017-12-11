package com.kikia.itacon.utils;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/*TODO replace it by dependence injection*/
public class CodeGenerator {

	private static CodeGenerator codeGeneratorSingleton = null;
	public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String digits = "0123456789";
	public static final String lower = upper.toLowerCase(Locale.ROOT);
	public static String alphanumeric = upper + lower + digits;
	private Random random;
	private char[] symbols = null;
	private char[] buffer = null;

	public void initMainMotor(int length, Random random, String symbols) {
		if (length < 1)
			throw new IllegalArgumentException();
		if (symbols.length() < 2)
			throw new IllegalArgumentException();

		this.random = Objects.requireNonNull(random);
		this.symbols = symbols.toCharArray();
		buffer = new char[length];
	}

	/**
	 * Generate alphanumeric
	 * 
	 * @param length
	 * @param random
	 */
	public void initAlphanumericGenerator(int length, Random random) {
		initMainMotor(length, random, alphanumeric);
	}

	/**
	 * create alphanumeric string from a secure generator
	 * 
	 * @param length
	 */
	public void initAlphanumericSecureGenerator(int length) {
		initAlphanumericGenerator(length, new SecureRandom());
	}

	/*
	 * create session identifier
	 */
	public void initCreateSessionIdentifierGenerator() {
		initAlphanumericSecureGenerator(23);
	}

	public String nextString() {
		for (int i = 0; i < buffer.length; i++)
			buffer[i] = symbols[random.nextInt(symbols.length)];
		return new String(buffer);
	}

	/**
	 * return original String processing result without NON ASCII characters
	 * 
	 * @param uniqueName
	 * @return
	 */
	public String nextString(String uniqueName) {
		String onlyASCIIUniqueString = uniqueName.replaceAll("[^\\x00-\\x7F]", "");
		int onlyASCIIUniqueStringLength = onlyASCIIUniqueString.length();
		int newComposedSymbolsSize = onlyASCIIUniqueStringLength + symbols.length;
		char[] newComposedSymbols = new char[newComposedSymbolsSize];

		for (int i = 0; i < onlyASCIIUniqueStringLength; i++)
			newComposedSymbols[i] = onlyASCIIUniqueString.charAt(i);

		for (int i = 0; i < symbols.length; i++)
			newComposedSymbols[i + onlyASCIIUniqueStringLength] = symbols[i];

		for (int i = 0; i < buffer.length; i++)
			buffer[i] = newComposedSymbols[random.nextInt(newComposedSymbolsSize)];
		return new String(buffer);
	}

	public static CodeGenerator getInstance() {
		return (codeGeneratorSingleton != null) ? (codeGeneratorSingleton) : (new CodeGenerator());
	}
}
