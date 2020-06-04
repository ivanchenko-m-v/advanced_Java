package ru.imv.advancedjava;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface TypeInfo {
    String author() default "Michael";
    int dateOfCreation() default 19000101;
    String purpose() default "Demo purpose";

}
