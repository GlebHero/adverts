package com.gleb.zemskoi.adverts.aop.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface LogJournal {
}
