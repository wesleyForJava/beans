package com.wesley.bean.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;

@Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
@Repeatable(Hints.class)
public @interface Hint {
  String value();
}
