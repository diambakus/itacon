package com.kikia.itacon.utils;

import org.springframework.stereotype.Component;

@Component("textUtils")
public class TextUtils {

	private char[] capitalAccentedAscii = { 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ẽ', 'Ì', 'Í', 'Î', 'Ĩ', 'Ò', 'Ó', 'Ô',
			'Õ', 'Ù', 'Ú', 'Û', 'Ũ', 'Ç', 'Ñ' };
	private char[] accentedAscii = { 'à', 'á', 'â', 'ã', 'è', 'é', 'ê', 'ẽ', 'ì', 'í', 'î', 'ĩ', 'ò', 'ó', 'ô', 'õ',
			'ù', 'ú', 'û', 'ũ', 'ç', 'ñ' };

	/**
	 * verifies whether the input name is valid or not
	 * 
	 * @param inputName
	 * @return
	 */

	public boolean isValidName(String inputName) {
		int length = inputName.length();
		boolean isValid = true;

		char[] nameCharacters = inputName.toCharArray();

		// Name must start with capital Roman alphabet letter
		if (((nameCharacters[0] < 'A') || (nameCharacters[0] > 'Z'))
				&& (!isCapitalAccentedRomanAlphabetLetter(nameCharacters[0])))
			isValid = false;

		// Name must end with Roman alphabet letter
		if ((!(((nameCharacters[length - 1] >= 'a') && (nameCharacters[length - 1] <= 'z'))
				|| ((nameCharacters[length - 1] >= 'A') && (nameCharacters[length - 1] <= 'Z'))))
				&& ((!isCapitalAccentedRomanAlphabetLetter(nameCharacters[length - 1]))
						&& (!isAccentedRomanAlphabetLetter(nameCharacters[length - 1]))))
			isValid = false;

		for (int i = 1; (i < length - 1) && (isValid); i++) {
			if ((nameCharacters[i] == ' ') || nameCharacters[i] == '-')
				continue;
			else if (((nameCharacters[i] >= 'a') && (nameCharacters[i] <= 'z'))
					|| ((nameCharacters[i] >= 'A') && (nameCharacters[i] <= 'Z'))
					|| isCapitalAccentedRomanAlphabetLetter(nameCharacters[i])
					|| isAccentedRomanAlphabetLetter(nameCharacters[i]))
				continue;
		}
		return isValid;
	}

	private boolean isCapitalAccentedRomanAlphabetLetter(char charac) {

		boolean found = false;
		for (int i = 0; (i < capitalAccentedAscii.length) && (!found); i++) {
			if (capitalAccentedAscii[i] != charac)
				continue;
			else
				found = true;
		}
		return found;
	}

	private boolean isAccentedRomanAlphabetLetter(char charac) {

		boolean found = false;
		for (int i = 0; (i < accentedAscii.length) && (!found); i++) {
			if (accentedAscii[i] != charac)
				continue;
			else
				found = true;
		}
		return found;
	}

}
