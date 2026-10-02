/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$JoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$NotBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$DefaultContextReader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ParameterMapping;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ParameterMapping$FieldOrMethod;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$VoidContextReader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StaticString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogicBooleanLoader {
    public static final boolean TRACE = false;
    static Pattern patternSingleQuote = Pattern.compile("'(.*)'");
    static Pattern patternDoubleQuote = Pattern.compile("\"(.*)\"");
    static Pattern patternInteger = Pattern.compile("(-?\\d*)");
    static Pattern patternFloat = Pattern.compile("(-?\\d*\\.\\d*)");
    static final LogicBooleanLoader$LogicBooleanContext defaultContextReader = new LogicBooleanLoader$DefaultContextReader();
    static final LogicBooleanLoader$LogicBooleanContext voidContextReader = new LogicBooleanLoader$VoidContextReader(null);
    static final LogicBooleanLoader$LogicBooleanContext voidNumberContextReader = new LogicBooleanLoader$VoidContextReader("Number");
    static final LogicBooleanLoader$LogicBooleanContext voidBoolContextReader = new LogicBooleanLoader$VoidContextReader("Bool");
    static final LogicBooleanLoader$LogicBooleanContext voidArrayContextReader = new LogicBooleanLoader$VoidContextReader("Array element");
    static final LogicBooleanLoader$LogicBooleanContext numberArrayContextReader = new LogicBooleanLoader$ArrayContextReader(LogicBoolean$ReturnType.numberArray);
    static final LogicBooleanLoader$LogicBooleanContext boolArrayContextReader = new LogicBooleanLoader$ArrayContextReader(LogicBoolean$ReturnType.boolArray);
    static final LogicBooleanLoader$LogicBooleanContext unitArrayContextReader = new LogicBooleanLoader$ArrayContextReader(LogicBoolean$ReturnType.unitArray);

    public static boolean isEmptyIgnoringPlusMinus(String string) {
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (c == '-' || c == '+' || c == ' ') continue;
            return false;
        }
        return true;
    }

    public static LogicBoolean parseNumberBlock(l l2, String string) {
        LogicBoolean logicBoolean = LogicBooleanLoader.parseBooleanBlock(l2, string, false);
        if (logicBoolean != null && logicBoolean.getReturnType() != LogicBoolean$ReturnType.number) {
            throw new RuntimeException("Expected number for: '" + string + "' got a " + (Object)((Object)logicBoolean.getReturnType()) + " type");
        }
        return logicBoolean;
    }

    /*
     * WARNING - void declaration
     */
    public static LogicBoolean parseBooleanBlock(l l2, String string, boolean bl) {
        Object object;
        Object object2;
        int n2;
        Object object3;
        int n3 = al.a(string);
        if (n3 != 0) {
            if (n3 > 0) {
                throw new RuntimeException("Brackets unbalanced for: '" + string + "'. A '(' was not closed.");
            }
            if (n3 < 0) {
                throw new RuntimeException("Brackets unbalanced for: '" + string + "'. Too many ')'.");
            }
        }
        string = string.trim();
        if ((string = LogicBooleanLoader.breakOuterLayerBrackets(string)).length() > 1 && string.charAt(0) == '-') {
            boolean bl2 = false;
            for (int i = 1; i < string.length(); ++i) {
                char c2 = string.charAt(i);
                if (c2 == ' ') continue;
                bl2 = !Character.isDigit(c2);
            }
            if (bl2) {
                string = "0" + string;
            }
        }
        String string2 = string.toLowerCase(Locale.ROOT);
        String[] stringArray = new String[]{"==", "!=", "<=", ">=", "<", ">"};
        String[] stringArray2 = new String[]{"or", "and", "==", "!=", "<=", ">=", "<", ">", "%", "-", "+", "*", "/", "="};
        for (String string3 : stringArray2) {
            Object object4;
            void object42;
            if (!f.c(string2, string3)) continue;
            boolean bl2 = false;
            boolean bl3 = false;
            if (string3.equals("and") || string3.equals("or")) {
                bl2 = true;
                bl3 = true;
            }
            if (string3.equals("<>")) {
                String i;
                int n4 = al.a(string, 0, stringArray);
                object3 = new ArrayList<String>();
                ((ArrayList)object3).add(string.substring(0, n4));
                int logicBoolean = n4 + 2;
                if (logicBoolean > string.length() - 1) {
                    logicBoolean = string.length() - 1;
                }
                if (!(i = string.substring(n4, logicBoolean)).endsWith("=")) {
                    i = i.substring(0, 1);
                }
                ((ArrayList)object3).add(string.substring(n4 + i.length()));
                String string4 = i;
            } else {
                object3 = bl3 ? al.a(string, string3, bl2, true) : al.a(string, string3, bl2, false);
            }
            if (((ArrayList)object3).size() == 1) continue;
            if (object42.equals("=")) {
                throw new RuntimeException("Unexpected assignment operator: '=', use '==' for comparison");
            }
            if (((String)((ArrayList)object3).get(0)).equals("") && ((ArrayList)object3).size() == 2 && (object42.equals("+") || object42.equals("-"))) continue;
            ArrayList<Object> arrayList = new ArrayList<Object>();
            LogicBoolean$JoinerBoolean logicBoolean$JoinerBoolean = LogicBoolean$JoinerBoolean.getNewJoiner((String)object42);
            boolean logicBoolean$ReturnType = logicBoolean$JoinerBoolean.requireBooleanChildren();
            if (logicBoolean$ReturnType && (logicBoolean$JoinerBoolean instanceof CompareJoinerBoolean.CompareNotEqualBoolean || logicBoolean$JoinerBoolean instanceof CompareJoinerBoolean.CompareEqualBoolean)) {
                com.corrodinggames.rts.gameFramework.l.e(logicBoolean$JoinerBoolean.type() + " was set to require boolean. Workaround triggered. requireBooleanChildren:" + logicBoolean$JoinerBoolean.requireBooleanChildren());
                logicBoolean$ReturnType = false;
            }
            n2 = 0;
            int string11 = -1;
            if (object42.equals("+") || object42.equals("-")) {
                boolean string8 = false;
                object2 = ((ArrayList)object3).iterator();
                while (object2.hasNext()) {
                    object4 = (String)object2.next();
                    boolean bl4 = LogicBooleanLoader.isEmptyIgnoringPlusMinus((String)object4);
                    if (!bl4) continue;
                    string8 = true;
                    break;
                }
                if (string8) {
                    object2 = new ArrayList();
                    object4 = "";
                    Iterator iterator = ((ArrayList)object3).iterator();
                    while (iterator.hasNext()) {
                        String string5 = (String)iterator.next();
                        if (LogicBooleanLoader.isEmptyIgnoringPlusMinus(string5)) {
                            object4 = (String)object4 + string5 + (String)object42;
                            continue;
                        }
                        if (!((String)object4).equals("")) {
                            string5 = (String)object4 + string5;
                            object4 = "";
                        }
                        ((ArrayList)object2).add(string5);
                    }
                    if (!((String)object4).equals("")) {
                        throw new RuntimeException("Unexpected empty last element using: " + (String)object42);
                    }
                    object3 = object2;
                }
            }
            if (((ArrayList)object3).size() == 1) continue;
            Iterator string10 = ((ArrayList)object3).iterator();
            while (string10.hasNext()) {
                object2 = (String)string10.next();
                ++string11;
                if (((String)object2).equals("")) {
                    if (string11 == 0) {
                        throw new RuntimeException("Unexpected empty element before: " + (String)object42);
                    }
                    throw new RuntimeException("Unexpected empty element after: " + (String)object42);
                }
                object4 = LogicBooleanLoader.parseBooleanBlock(l2, (String)object2, logicBoolean$ReturnType);
                if (object4 == null) {
                    throw new RuntimeException("null on:'" + string + "'");
                }
                arrayList.add(object4);
            }
            logicBoolean$JoinerBoolean.children = arrayList.toArray(new LogicBoolean[0]);
            return logicBoolean$JoinerBoolean.validateAndOptimize((String)object42, "", string, null, bl);
        }
        if (string2.startsWith("not ")) {
            object = string.substring("not ".length());
            LogicBoolean logicBoolean = LogicBooleanLoader.parseBooleanBlock(l2, (String)object, true);
            LogicBoolean$NotBoolean logicBoolean$NotBoolean = new LogicBoolean$NotBoolean(logicBoolean);
            return logicBoolean$NotBoolean.validateAndOptimize("not", "", (String)object, null, bl);
        }
        if (string.length() > 0) {
            object = string;
            if (((String)object).startsWith("+")) {
                object = ((String)object).substring(1).trim();
            }
            if (f.r((String)(object = al.c((String)object)))) {
                if (bl) {
                    throw new RuntimeException("Expected a boolean type here, not number: " + (String)object);
                }
                return LogicBoolean$StaticValueBoolean.getStaticNumber((String)object);
            }
            String string5 = f.p(string);
            if (string5 != null) {
                if (bl) {
                    throw new RuntimeException("Expected a boolean type here, not string: " + string);
                }
                return new LogicString$StaticString(string5);
            }
        }
        boolean bl8 = false;
        int n7 = 0;
        if (string.startsWith("self.")) {
            string = string.substring("self.".length());
            bl8 = true;
        }
        String[] stringArray3 = al.b(string, ".", false);
        m m2 = new m();
        Object object5 = null;
        String string6 = null;
        object3 = defaultContextReader;
        Object object6 = null;
        for (int i = 0; i < stringArray3.length; ++i) {
            String string7;
            if (object6 != null) {
                string7 = object6;
                object6 = null;
            } else {
                string7 = stringArray3[i];
            }
            if (f.b(string7, '[') && (n2 = al.b(string7, "[", 0)) != -1) {
                if (n2 == 0) {
                    int n5 = al.b(string7, "]", 0);
                    if (n5 == -1 || n2 >= n5) {
                        throw new RuntimeException("Unexpected use of square brankets:'" + string7 + "'");
                    }
                    if (n5 < string7.length() - 1 && n5 > 0) {
                        String string8 = string7.substring(n2, n5 + 1);
                        object6 = object2 = string7.substring(n5 + 1);
                        --i;
                        string7 = string8;
                    }
                    if (n2 != 0 || n5 != string7.length() - 1 || string7.length() < 2) {
                        throw new RuntimeException("Error reading square brankets:'" + string7 + "'");
                    }
                    string7 = string7.substring(1, string7.length() - 1);
                    string7 = "get(" + string7 + ")";
                } else {
                    String string9 = string7.substring(0, n2);
                    String string10 = string7.substring(n2);
                    object6 = string10;
                    --i;
                    string7 = string9;
                }
            }
            if (string7.equalsIgnoreCase("self")) {
                if (n7 != 0) {
                    throw new RuntimeException("No field:'" + string7 + "' globals");
                }
                if (stringArray3.length == 1) {
                    return UnitReference.selfUnitReference;
                }
                bl8 = true;
                continue;
            }
            if (i == 0 && string7.equalsIgnoreCase("game")) {
                n7 = 1;
                continue;
            }
            n2 = i == stringArray3.length - 1 ? 1 : 0;
            String string11 = null;
            if (bl8) {
                string11 = "self.";
            }
            if (n7 != 0) {
                string11 = "game.";
            }
            boolean bl5 = bl;
            if (n2 == 0) {
                bl5 = false;
            }
            if (object5 != null) {
                object3 = ((LogicBoolean)object5).createContext();
            }
            if ((object2 = object3.parseNextElementInChain(string11, l2, string7, bl5, string, string6, (LogicBoolean)object5)) == null) {
                throw new RuntimeException("Null function or field:'" + string7 + "'");
            }
            object5 = object2;
            string6 = string7;
            bl8 = true;
            m2.add(object2);
        }
        if (m2.size() == 0) {
            throw new RuntimeException("Unknown function:'" + string + "'");
        }
        LogicBoolean logicBoolean = null;
        for (int i = m2.a - 1; i >= 0; --i) {
            LogicBoolean logicBoolean2 = (LogicBoolean)m2.get(i);
            if (logicBoolean != null) {
                logicBoolean2 = logicBoolean2.setChild(logicBoolean);
            }
            logicBoolean = logicBoolean2;
        }
        LogicBoolean$ReturnType logicBoolean$ReturnType = logicBoolean.getReturnType();
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.voidReturn) {
            logicBoolean.throwVoidReturnError(string);
            throw new RuntimeException("throwVoidReturnError");
        }
        if (bl && logicBoolean$ReturnType != LogicBoolean$ReturnType.bool) {
            throw new BooleanParseException("Function:'" + string + "' is expected to return a boolean type but it returns type: " + (Object)((Object)logicBoolean$ReturnType));
        }
        return logicBoolean;
    }

    public static String fixArguments(String string) {
        String string2 = string;
        if ((string2 = string2.trim()).equals("")) {
            return "";
        }
        if (!string2.startsWith("(") || !string2.endsWith(")")) {
            throw new RuntimeException("Failed to parse function arguments:'" + string2 + "'");
        }
        string2 = string2.substring(1, string2.length() - 1);
        string2 = string2.trim();
        return string2;
    }

    public static Matcher match(Pattern pattern, String string) {
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

    public static void setArgumentsWithMapping(LogicBooleanLoader$ParameterMapping logicBooleanLoader$ParameterMapping, Object object, String string, l l2, String string2) {
        if (string2 == null) {
            string2 = object.getClass().getSimpleName();
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        if (string != null && !"".equals(string)) {
            ArrayList arrayList2 = al.a(string, ",", false);
            int n2 = 0;
            boolean bl = false;
            Iterator iterator = arrayList2.iterator();
            while (iterator.hasNext()) {
                String string3;
                String string4;
                String string5 = (String)iterator.next();
                int n3 = al.a(string5, "=");
                if (n3 > 0) {
                    string4 = string5.substring(0, n3);
                    string3 = string5.substring(n3 + 1);
                    bl = true;
                } else {
                    if (bl) {
                        throw new BooleanParseException(string2 + "(): SyntaxError: Cannot use non-keyword arg after keyword arg");
                    }
                    if (logicBooleanLoader$ParameterMapping.numberOfPositionalParameters == 0) {
                        throw new BooleanParseException(string2 + "(): Function doesn't accept any non-keyword arguments.");
                    }
                    if (logicBooleanLoader$ParameterMapping.numberOfPositionalParameters <= n2) {
                        throw new BooleanParseException(string2 + "(): Too many non-keyword arguments. Only " + logicBooleanLoader$ParameterMapping.numberOfPositionalParameters + " accepted.");
                    }
                    string4 = null;
                    for (String string6 : logicBooleanLoader$ParameterMapping.parameters.keySet()) {
                        LogicBooleanLoader$ParameterMapping$FieldOrMethod logicBooleanLoader$ParameterMapping$FieldOrMethod = (LogicBooleanLoader$ParameterMapping$FieldOrMethod)logicBooleanLoader$ParameterMapping.parameters.get(string6);
                        if (logicBooleanLoader$ParameterMapping$FieldOrMethod.positionalOffset != n2) continue;
                        string4 = string6;
                        break;
                    }
                    if (string4 == null) {
                        throw new BooleanParseException("Error failed to find non-keyword argument index: " + n2);
                    }
                    string3 = string5;
                }
                String string7 = string4;
                string4 = string4.trim();
                string4 = string4.toLowerCase(Locale.ROOT);
                if (arrayList.contains(string4)) {
                    throw new BooleanParseException("SyntaxError: Argument '" + (String)string7 + "' has been listed more than once");
                }
                arrayList.add(string4);
                LogicBooleanLoader.setArgumentWithMapping(logicBooleanLoader$ParameterMapping, object, string4, string3, l2);
                ++n2;
            }
        }
        for (String string8 : logicBooleanLoader$ParameterMapping.parameters.keySet()) {
            LogicBooleanLoader$ParameterMapping$FieldOrMethod logicBooleanLoader$ParameterMapping$FieldOrMethod = (LogicBooleanLoader$ParameterMapping$FieldOrMethod)logicBooleanLoader$ParameterMapping.parameters.get(string8);
            if (!logicBooleanLoader$ParameterMapping$FieldOrMethod.required || arrayList.contains(string8)) continue;
            throw new BooleanParseException(string2 + "(): SyntaxError: Missing required argument: '" + string8 + "'");
        }
    }

    public static Object getArgumentTextWithMapping(LogicBooleanLoader$ParameterMapping$FieldOrMethod logicBooleanLoader$ParameterMapping$FieldOrMethod, Object object) {
        if (logicBooleanLoader$ParameterMapping$FieldOrMethod != null && logicBooleanLoader$ParameterMapping$FieldOrMethod.field != null) {
            Object object2;
            try {
                object2 = logicBooleanLoader$ParameterMapping$FieldOrMethod.field.get(object);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                illegalArgumentException.printStackTrace();
                return "<error>";
            }
            catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
                return "<error>";
            }
            if (object2 == null) {
                return null;
            }
            return object2;
        }
        if (logicBooleanLoader$ParameterMapping$FieldOrMethod != null && logicBooleanLoader$ParameterMapping$FieldOrMethod.method != null) {
            com.corrodinggames.rts.gameFramework.l.e("getArgumentTextWithMapping: method not supported");
            return "<method>";
        }
        com.corrodinggames.rts.gameFramework.l.e("getArgumentTextWithMapping: No method or field");
        return "<error>";
    }

    public static void setArgumentWithMapping(LogicBooleanLoader$ParameterMapping logicBooleanLoader$ParameterMapping, Object object, String string, String string2, l l2) {
        LogicBooleanLoader$ParameterMapping$FieldOrMethod logicBooleanLoader$ParameterMapping$FieldOrMethod = (LogicBooleanLoader$ParameterMapping$FieldOrMethod)logicBooleanLoader$ParameterMapping.parameters.get(string);
        if (logicBooleanLoader$ParameterMapping$FieldOrMethod != null && logicBooleanLoader$ParameterMapping$FieldOrMethod.field != null) {
            Class clazz = logicBooleanLoader$ParameterMapping$FieldOrMethod.type;
            Object object2 = LogicBooleanLoader.convertParameterData(string2, clazz, l2, logicBooleanLoader$ParameterMapping$FieldOrMethod.returnType, string);
            if (object2 == null && logicBooleanLoader$ParameterMapping$FieldOrMethod.required) {
                throw new BooleanParseException("SyntaxError: Cannot set required argument: '" + string + "' to null");
            }
            try {
                logicBooleanLoader$ParameterMapping$FieldOrMethod.field.set(object, object2);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                illegalArgumentException.printStackTrace();
                String string3 = illegalArgumentException.getMessage();
                if (string3 == null) {
                    throw new BooleanParseException("Error parameter:'" + string + "' on " + object.getClass().getSimpleName(), illegalArgumentException);
                }
                string3 = string3.replace("com.corrodinggames.rts.game.units.custom.logicBooleans.", "");
                string3 = string3.replace("java.lang.", "");
                throw new BooleanParseException("Error parameter:'" + string + "': " + string3);
            }
            catch (IllegalAccessException illegalAccessException) {
                throw new BooleanParseException("Error setting parameter:'" + string + "' on " + object.getClass().getSimpleName(), illegalAccessException);
            }
            return;
        }
        if (logicBooleanLoader$ParameterMapping$FieldOrMethod != null && logicBooleanLoader$ParameterMapping$FieldOrMethod.method != null) {
            Class clazz = logicBooleanLoader$ParameterMapping$FieldOrMethod.type;
            Object object3 = LogicBooleanLoader.convertParameterData(string2, clazz, l2, logicBooleanLoader$ParameterMapping$FieldOrMethod.returnType, string);
            try {
                logicBooleanLoader$ParameterMapping$FieldOrMethod.method.invoke(object, object3);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                illegalArgumentException.printStackTrace();
                String string4 = illegalArgumentException.getMessage();
                if (string4 == null) {
                    throw new BooleanParseException("Error parameter:'" + string + "' on " + object.getClass().getSimpleName(), illegalArgumentException);
                }
                string4 = string4.replace("com.corrodinggames.rts.game.units.custom.logicBooleans.", "");
                string4 = string4.replace("java.lang.", "");
                throw new BooleanParseException("Error setting parameter:'" + string + "': " + string4);
            }
            catch (IllegalAccessException illegalAccessException) {
                throw new BooleanParseException("Error setting parameter:'" + string + "' on " + object.getClass().getSimpleName(), illegalAccessException);
            }
            catch (InvocationTargetException invocationTargetException) {
                Throwable throwable = invocationTargetException.getCause();
                String string5 = "";
                if (throwable != null) {
                    string5 = " - " + throwable.getMessage();
                }
                throw new BooleanParseException("Error setting parameter:'" + string + "' on " + object.getClass().getSimpleName() + string5, invocationTargetException);
            }
            return;
        }
        throw new BooleanParseException("No parameter:'" + string + "' on " + object.getClass().getSimpleName() + " (Possible parameters:" + logicBooleanLoader$ParameterMapping.allParametersString + ")");
    }

    public static List getAllFieldsInherited(List list, Class clazz) {
        list.addAll(Arrays.asList(clazz.getFields()));
        return list;
    }

    public static Object convertParameterData(String string, Class clazz, l l2, LogicBoolean$ReturnType logicBoolean$ReturnType, String string2) {
        if (string == null) {
            return null;
        }
        if ((string = string.trim()).length() == 0) {
            return null;
        }
        if (string.equals("null")) {
            return null;
        }
        if (clazz == LogicBoolean.class) {
            LogicBoolean$ReturnType logicBoolean$ReturnType2;
            LogicBoolean logicBoolean = LogicBooleanLoader.parseBooleanBlock(l2, string, false);
            if (logicBoolean != null && logicBoolean$ReturnType != null && logicBoolean$ReturnType != (logicBoolean$ReturnType2 = logicBoolean.getReturnType())) {
                throw new BooleanParseException("Wrong type. Expected type: '" + (Object)((Object)logicBoolean$ReturnType) + "' for dynamic parameter '" + string2 + "' instead got type:'" + (Object)((Object)logicBoolean$ReturnType2) + "' (parsing: " + string + ")");
            }
            return logicBoolean;
        }
        if (f.s(string)) {
            if (!f.c(string, ".")) {
                if (clazz == String.class) {
                    return string;
                }
                return Integer.parseInt(string);
            }
            if (clazz == String.class) {
                return string;
            }
            return Float.valueOf(Float.parseFloat(string));
        }
        String string3 = string.toLowerCase(Locale.ENGLISH);
        if ("false".equals(string3)) {
            return Boolean.FALSE;
        }
        if ("true".equals(string3)) {
            return Boolean.TRUE;
        }
        Matcher matcher = LogicBooleanLoader.match(patternSingleQuote, string);
        if (matcher != null) {
            return f.q(matcher.group(1));
        }
        matcher = LogicBooleanLoader.match(patternDoubleQuote, string);
        if (matcher != null) {
            return f.q(matcher.group(1));
        }
        matcher = LogicBooleanLoader.match(patternInteger, string);
        if (matcher != null) {
            if (clazz == String.class) {
                return matcher.group(1);
            }
            return Integer.parseInt(matcher.group(1));
        }
        matcher = LogicBooleanLoader.match(patternFloat, string);
        if (matcher != null) {
            if (clazz == String.class) {
                return matcher.group(1);
            }
            return Float.valueOf(Float.parseFloat(matcher.group(1)));
        }
        String string4 = "null";
        if (clazz != null) {
            string4 = "data of " + clazz.getSimpleName();
            if (clazz == String.class) {
                string4 = "string";
            }
            if (clazz == Float.TYPE) {
                string4 = "number";
            }
            if (clazz == Integer.TYPE) {
                string4 = "integer";
            }
            if (clazz == Boolean.TYPE) {
                string4 = "boolean";
            }
        }
        String string5 = "Failed to read parameter '" + string2 + "' expected non-dynamic " + string4 + " got: " + string + "";
        if (clazz == String.class) {
            string5 = string5 + " (A quoted string was expected)";
        }
        throw new BooleanParseException(string5);
    }

    public static String breakOuterLayerBrackets(String string) {
        if (string.startsWith("(") && string.endsWith(")")) {
            int n2 = al.a(string, 0);
            if (n2 == -1) {
                throw new RuntimeException("Brackets unbalanced. Starting '(' in '" + string + "' was not closed.");
            }
            if (n2 == string.length() - 1) {
                string = string.substring(1, string.length() - 1);
                string = string.trim();
                string = LogicBooleanLoader.breakOuterLayerBrackets(string);
            }
        }
        return string;
    }
}
