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
 * Created by simon on 15. 6. 30.
 *
 * Explanation for @Size(min = 6, max = 20)
 *  Password length should be between 6 and 20.
 *
 * Explanation for @Pattern(regexp = ".*(?=.*[a-zA-Z])(?=.*[0-9])(?=\\S+$).{1,}")
 *  (?=.*[a-zA-Z]): Alphabets must occur at least once
 *  (?=.*[0-9]): A digit must occur at least once
 *  (?=\\S+$): Whitespace is not allowed.
 *  .{1,}: At least 1 character.
 *
 *  Explanation for @Pattern(regexp = "^[a-zA-Z0-9\\Q~∙!@#$%^&*()_-+={}[]|\\;:'\"<>,.?/\\E]+$")
 *   a-zA-Z0-9: Alphanumerics are allowed.
 *   * ~ ∙ ! @ # $ % ^ & * ( ) _ - + = { } [ ] | \ ; :‘ “ < > , . ? / `: These special characters are allowed.
 *   * Other characters are now allowed
 */
@NotBlank
@Size(min = 6, max = 20)
@Pattern.List({
        @Pattern(regexp = ".*(?=.*[a-zA-Z])(?=.*[0-9])(?=\\S+$).{1,}"),
        @Pattern(regexp = "^[a-zA-Z0-9\\Q~∙!@#$%^&*()_-+={}[]|\\;:'\"<>,.?/`\\E]+$")
})
@Documented
@Constraint(validatedBy = {})
@Retention(RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
public @interface Password {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
