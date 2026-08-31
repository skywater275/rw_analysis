package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface LogicBoolean$Parameter {

   LogicBoolean$ReturnType type() default LogicBoolean$ReturnType.undefined;

   boolean required() default false;

   int positional() default -1;

   String key() default "";
}
