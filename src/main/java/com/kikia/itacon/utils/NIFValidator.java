package com.kikia.itacon.utils;

public class NIFValidator {

	public boolean isNIF(Long NIF) {
		
		boolean isValidNIF = false;
		int counter = 2;
		Long dividend = NIF/10;
		int controlDigit = (int)(NIF%10);
		
		int eightDigitsCompute = 0;
		
		for (int i = 1; i < 9; i++) {
			
			eightDigitsCompute += (dividend%10)*counter;
			dividend /= 10;
			counter++;
		}
		
		int modEleven  = eightDigitsCompute%11;
		
		if ((controlDigit == 0) && ((modEleven == 0) || (modEleven == 1))) {
			isValidNIF = true;
		}else if((11 - modEleven) == controlDigit) {
			isValidNIF = true;
		}
        
		return isValidNIF;
	}
}
