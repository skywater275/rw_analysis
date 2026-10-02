/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class LogicBooleanLoader$ParameterMapping$FieldOrMethod {
    Field field;
    Method method;
    Class type;
    LogicBoolean.ReturnType returnType;
    int positionalOffset = -1;
    boolean required;

    public LogicBooleanLoader$ParameterMapping$FieldOrMethod(Field field) {
        this.field = field;
        this.type = field.getType();
    }

    public LogicBooleanLoader$ParameterMapping$FieldOrMethod(Method method) {
        this.method = method;
        this.type = method.getParameterTypes()[0];
    }
}
