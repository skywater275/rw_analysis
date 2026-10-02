/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ParameterMapping$FieldOrMethod;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class LogicBooleanLoader$ParameterMapping {
    public HashMap parameters = new HashMap();
    int numberOfPositionalParameters = 0;
    public Class type;
    public String allParametersString;

    public LogicBooleanLoader$ParameterMapping(Class clazz) {
        this.type = clazz;
        ArrayList arrayList = new ArrayList();
        LogicBooleanLoader.getAllFieldsInherited(arrayList, clazz);
        this.allParametersString = "";
        Method[] methodArray = arrayList.iterator();
        while (methodArray.hasNext()) {
            Method[] methodArray2 = (Method[])methodArray.next();
            if (!methodArray2.isAnnotationPresent(LogicBoolean$Parameter.class)) continue;
            LogicBoolean$Parameter parameter = methodArray2.getAnnotation(LogicBoolean$Parameter.class);
            String string = methodArray2.getName().toLowerCase(Locale.ROOT);
            this.addParameter(string, new LogicBooleanLoader$ParameterMapping$FieldOrMethod((Field)methodArray2), parameter);
        }
        for (Method method : methodArray = clazz.getMethods()) {
            if (!method.isAnnotationPresent(LogicBoolean$Parameter.class)) continue;
            LogicBoolean$Parameter parameter = method.getAnnotation(LogicBoolean$Parameter.class);
            String string = method.getName().toLowerCase(Locale.ROOT);
            this.addParameter(string, new LogicBooleanLoader$ParameterMapping$FieldOrMethod(method), parameter);
        }
    }

    public void addParameter(String string, LogicBooleanLoader$ParameterMapping$FieldOrMethod logicBooleanLoader$ParameterMapping$FieldOrMethod, LogicBoolean$Parameter logicBoolean$Parameter) {
        if (logicBoolean$Parameter.type() != LogicBoolean$ReturnType.undefined) {
            logicBooleanLoader$ParameterMapping$FieldOrMethod.returnType = logicBoolean$Parameter.type();
        }
        if (logicBoolean$Parameter.positional() != -1) {
            logicBooleanLoader$ParameterMapping$FieldOrMethod.positionalOffset = logicBoolean$Parameter.positional();
            ++this.numberOfPositionalParameters;
        }
        if (logicBoolean$Parameter.required()) {
            logicBooleanLoader$ParameterMapping$FieldOrMethod.required = true;
        }
        if (logicBoolean$Parameter.key() != null && !logicBoolean$Parameter.key().equals("")) {
            string = logicBoolean$Parameter.key();
        }
        this.parameters.put(string, logicBooleanLoader$ParameterMapping$FieldOrMethod);
        if (!this.allParametersString.equals("")) {
            this.allParametersString = this.allParametersString + ", ";
        }
        this.allParametersString = this.allParametersString + string;
    }
}
