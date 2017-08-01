package com.kikia.itacon.utils;

/*TODO replace it by dependence injection*/
public class CodeGenerator {

	private static CodeGenerator codeGenerator = null;
	private static final String CODE = "CD";

	private CodeGenerator() {
	}

	public static CodeGenerator getInstance() {

		if (codeGenerator == null)
			codeGenerator = new CodeGenerator();

		return codeGenerator;
	}

	public String getPublicServiceCode(String name) {
		String codeGenerated = CODE;
		int index;
		for (int i = 1; i <= 3; i++) {
			index = (int) Math.random() * (name.length() - 1);
			codeGenerated += name.charAt(index);
		}
		return codeGenerated;
	}
}
