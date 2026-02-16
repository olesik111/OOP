package ru.nsu.kataeva;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})

/**
 * To be ignored by coverage because of not only game logic.
 */
public @interface Generated {
}