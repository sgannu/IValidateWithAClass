package com.sgannu.ivalidator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
// import com.aa.web.requestmiles.form.validator.ValidationFacade.VALIDATION_TYPE;

@Target({FIELD, TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = GenericValidator.class)
public @interface Validate {

    @SuppressWarnings("rawtypes")
    Class<? extends ValidationFacade> with();

    String bean() default "";
    // VALIDATION_TYPE[] include() default {VALIDATION_TYPE.EMPTY, VALIDATION_TYPE.INVALID_FORMAT, VALIDATION_TYPE.NOT_FOUND};

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@code @Length} annotations on the same element.
     */
    @Target({ FIELD })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        Validate[] value();
    }

}
