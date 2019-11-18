package be.vdab.personeel.constraints;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import javax.validation.ConstraintValidatorContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RijksregisterValidatorTest {
    @Mock
    RijksregisterValidator rijksregisterValidator;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @Before
    public void setUp() {
        when(rijksregisterValidator.isValid(any(), any())).thenCallRealMethod();
    }

//    @Test
//    public void testIsValidWithValidValues() {
//        assertTrue(rijksregisterValidator.isValid(60010100172L, constraintValidatorContext));
//    }
//
//    @Test
//    public void testIsInvalidWithInvalidValues() {
//        assertFalse(rijksregisterValidator.isValid(60010100171L, constraintValidatorContext));
//    }
}
