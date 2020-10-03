package com.github.kai9026.dni.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DniValidator implements ConstraintValidator<Dni, String> {

	private static final Character[] letters = new Character[] {
		'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 
		'H', 'L', 'C', 'K', 'E'
	};

	@Override
	public boolean isValid(String dniValue, ConstraintValidatorContext context) {
		boolean isValidDni = true;

		if (dniValue.length() != 9 || !Character.isLetter(dniValue.charAt(8))) {
			isValidDni = false;
		} 

		if (isValidDni && !this.isDniValidFormat(dniValue)) {
			isValidDni = false;
		}

		return isValidDni;
	}

	private boolean isDniValidFormat(String dniValue) {
		String numDniStr = dniValue.substring(0, dniValue.length() - 1);
		try {
			Integer numDni = Integer.valueOf(numDniStr);
			Integer letterIndex = numDni % 23;
			Character letter = letters[letterIndex];
			return (dniValue.toUpperCase().charAt(8) == letter);
		} catch (NumberFormatException ex) {
			return false;
		}
	}
    
}
