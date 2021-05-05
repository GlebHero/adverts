package com.gleb.zemskoi.adverts.aop.nullchecker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface NotNullResult {
}
