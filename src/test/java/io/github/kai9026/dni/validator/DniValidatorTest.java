package io.github.kai9026.dni.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class DniValidatorTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void given_DocumentWithLetterLowercase_thenNoConstraintViolations() {
        // Dummy object with constraint
        CitizenDummy c1 = getCitizenDummy("79344474v");
        // Get validator and evaluate
        Set<ConstraintViolation<CitizenDummy>> constraintViolations = 
                validator.validate(c1);
        // Assertions
        this.assertValidDocument(constraintViolations);
    }

    @Test
    public void given_DocumentWithLetterUppercase_thenNoConstraintViolations() {
        // Dummy object with constraint
        CitizenDummy c1 = getCitizenDummy("79344474V");
        // Get validator and evaluate
        Set<ConstraintViolation<CitizenDummy>> constraintViolations = 
                validator.validate(c1);
        // Assertions
        this.assertValidDocument(constraintViolations);
    }

    @Test
    public void given_DocumentWithInvalidLetter_thenConstraintViolationsExists() {
        // Dummy object with constraint
        CitizenDummy c1 = getCitizenDummy("79344474M");
        // Get validator and evaluate
        Set<ConstraintViolation<CitizenDummy>> constraintViolations = 
                validator.validate(c1);
        // Assertions
        this.assertIncorrectDocument(constraintViolations);
    }

    @Test
    public void given_DocumentWithNoFirst8CharsAreNumber_thenConstraintViolationsExists() {
        // Dummy object with constraint
        CitizenDummy c1 = getCitizenDummy("72AD7327A");
        // Get validator and evaluate
        Set<ConstraintViolation<CitizenDummy>> constraintViolations = 
                validator.validate(c1);
        // Assertions
        this.assertIncorrectDocument(constraintViolations);
    }

    @Test
    public void given_DocumenttWithIncorrectLength_thenConstraintViolationsExists() {
        // Dummy object with constraint
        CitizenDummy c1 = getCitizenDummy("793444V");
        // Get validator and evaluate
        Set<ConstraintViolation<CitizenDummy>> constraintViolations = 
                validator.validate(c1);
        // Assertions
        this.assertIncorrectDocument(constraintViolations);
        
    }

    private class CitizenDummy {
        @Dni
        private String dni;
    }
    
    private CitizenDummy getCitizenDummy(String dni) {
        CitizenDummy citizen = new CitizenDummy();
        citizen.dni = dni;
        return citizen;
    }

    private void assertValidDocument(Set<ConstraintViolation<CitizenDummy>> errors) {
        assertEquals(0, errors.size());
    }

    private void assertIncorrectDocument(Set<ConstraintViolation<CitizenDummy>> errors) {
        assertEquals(1, errors.size());
        assertEquals("DNI is invalid. Please, check it", 
                        errors.stream().findFirst().get().getMessage());
    }


}