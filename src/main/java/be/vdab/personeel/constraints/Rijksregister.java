package be.vdab.personeel.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, METHOD, ANNOTATION_TYPE, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = RijksregisterValidator.class)
public @interface Rijksregister {
    String message() default "{be.vdab.personeel.constraints.Rijksregister.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
