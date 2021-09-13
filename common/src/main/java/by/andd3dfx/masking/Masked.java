package by.andd3dfx.masking;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marks class to mask its fields marked by {@link by.andd3dfx.masking.MaskedProperty}
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Masked {
}
