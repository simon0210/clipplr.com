package com.clipplr.platform.common.core.conversion.annotation;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by simon on 7/6/15.
 */
@NotBlank
@Size(min = 4, max = 20)
@Pattern.List({
        @Pattern(regexp = ".*(?=.*[a-z])(?=\\S+$).{1,}"),
        @Pattern(regexp = "^[a-z0-9]+$")
})
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
public @interface Username {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
