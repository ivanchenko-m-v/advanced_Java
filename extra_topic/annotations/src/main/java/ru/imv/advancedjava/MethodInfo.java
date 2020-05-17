package ru.imv.advancedjava;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MethodInfo {
    String author() default "Michael";
    int dateOfCreation() default 19000101;
    String purpose() default "Demo purpose";

}
