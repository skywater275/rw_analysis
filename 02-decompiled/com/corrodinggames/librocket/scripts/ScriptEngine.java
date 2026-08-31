/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.a;
import com.corrodinggames.librocket.b;
import com.corrodinggames.librocket.scripts.Debug;
import com.corrodinggames.librocket.scripts.Mods;
import com.corrodinggames.librocket.scripts.Multiplayer;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.ScriptContext;
import com.corrodinggames.librocket.scripts.ScriptEngine$1;
import com.corrodinggames.librocket.scripts.ScriptEngine$Action;
import com.corrodinggames.librocket.scripts.ScriptEngine$RunnableAction;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScriptEngine {
    b slickLibRocket;
    private Root root;
    static ScriptEngine scriptEngine;
    public static boolean inDebugScript;
    static boolean mainScriptThreadMarked;
    static ThreadLocal isMainScriptThread;
    ArrayList queuedScripts = new ArrayList();
    ArrayList runningScripts = new ArrayList();
    static Throwable scriptError;
    static String scriptErrorMessage;
    HashMap globals = new HashMap();

    public static boolean isStrict() {
        return com.corrodinggames.rts.a.a.a();
    }

    public static void checkThreadAccess() {
        if (!((Boolean)isMainScriptThread.get()).booleanValue()) {
            l.b("ScriptEngine: thread is not marked as main script thread!");
            l.T();
        }
    }

    public Root getRoot() {
        ScriptEngine.checkThreadAccess();
        return this.root;
    }

    public Root getRootNoCheck() {
        return this.root;
    }

    public static ScriptEngine getInstance() {
        return scriptEngine;
    }

    public static ScriptEngine createScriptEngine(b b2) {
        if (scriptEngine != null) {
            throw new RuntimeException("scriptEngine already exists");
        }
        scriptEngine = new ScriptEngine(b2);
        return scriptEngine;
    }

    private ScriptEngine(b b2) {
        this.slickLibRocket = b2;
        this.root = new Root();
        this.setupScriptContext(this.root);
        this.setGlobalVariable("root", this.root);
        Multiplayer multiplayer = new Multiplayer(this.root);
        this.setupScriptContext(multiplayer);
        this.setGlobalVariable("multiplayer", multiplayer);
        this.setGlobalVariable("mp", multiplayer);
        this.root.multiplayer = multiplayer;
        Mods mods = new Mods(this.root);
        this.setupScriptContext(mods);
        this.setGlobalVariable("mods", mods);
        this.root.mods = mods;
        if (com.corrodinggames.rts.a.a.a()) {
            Debug debug = new Debug(this.root);
            this.setupScriptContext(debug);
            this.setGlobalVariable("debug", debug);
        }
    }

    public void setupScriptContext(ScriptContext scriptContext) {
        Method[] methodArray;
        scriptContext.libRocket = this.slickLibRocket;
        scriptContext.guiEngine = a.a();
        scriptContext.scriptEngine = this;
        for (Method method : methodArray = scriptContext.getClass().getMethods()) {
            String string = method.getName();
            if (string.equals("wait") || string.equals("getClass")) continue;
            if (scriptContext.methods.get(string) != null) {
                ScriptEngine.logError("method: " + string + " already exists");
            }
            scriptContext.methods.put(string, method);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void update(float f2) {
        if (!mainScriptThreadMarked) {
            mainScriptThreadMarked = true;
            isMainScriptThread.set(true);
        }
        if (this.queuedScripts.size() != 0) {
            ArrayList arrayList = this.queuedScripts;
            synchronized (arrayList) {
                Object object = this.queuedScripts.iterator();
                while (object.hasNext()) {
                    ScriptEngine$Action scriptEngine$Action = (ScriptEngine$Action)object.next();
                    if (scriptEngine$Action.framesDelay > 0) {
                        --scriptEngine$Action.framesDelay;
                        continue;
                    }
                    this.runningScripts.add(scriptEngine$Action);
                    object.remove();
                }
            }
            for (Object object : this.runningScripts) {
                ((ScriptEngine$Action)object).run(this);
            }
            this.runningScripts.clear();
        }
        this.root.onFrameUpdate(f2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ScriptEngine$Action addScriptToQueue(String string, boolean bl) {
        ArrayList arrayList = this.queuedScripts;
        synchronized (arrayList) {
            ScriptEngine$Action scriptEngine$Action = new ScriptEngine$Action();
            scriptEngine$Action.script = string;
            scriptEngine$Action.tryToCatchCrash = bl;
            this.queuedScripts.add(scriptEngine$Action);
            return scriptEngine$Action;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ScriptEngine$Action addScriptToQueueIfNotAlreadyQueued(String string) {
        ArrayList arrayList = this.queuedScripts;
        synchronized (arrayList) {
            for (ScriptEngine$Action scriptEngine$Action : this.queuedScripts) {
                if (!string.equals(scriptEngine$Action.script)) continue;
                return null;
            }
            return this.addScriptToQueue(string, false);
        }
    }

    public ScriptEngine$Action addScriptToQueue(String string) {
        return this.addScriptToQueue(string, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ScriptEngine$Action addRunnableToQueue(Runnable runnable) {
        ArrayList arrayList = this.queuedScripts;
        synchronized (arrayList) {
            ScriptEngine$RunnableAction scriptEngine$RunnableAction = new ScriptEngine$RunnableAction(runnable);
            this.queuedScripts.add(scriptEngine$RunnableAction);
            return scriptEngine$RunnableAction;
        }
    }

    public void processScript(String string) {
        if (!"mp.refreshUI()".equals(string)) {
            System.out.println("ScriptEngine:HandleEvent:" + string);
        }
        try {
            String[] stringArray;
            for (String string2 : stringArray = al.a(string, ';')) {
                this.processArg(string2);
            }
        }
        catch (Exception exception) {
            ScriptEngine.throwDelayedException("Found error running:" + string, exception);
            throw new RuntimeException(exception);
        }
    }

    public static void throwDelayedException(String string, Throwable throwable) {
        l.a("throwDelayedException", throwable);
        if (scriptError == null) {
            scriptError = throwable;
            scriptErrorMessage = string;
        }
    }

    public void checkForErrors() {
        if (scriptError != null) {
            throw new RuntimeException(scriptErrorMessage, scriptError);
        }
    }

    public Matcher match(String string, String string2) {
        Pattern pattern = Pattern.compile(string);
        Matcher matcher = pattern.matcher(string2);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

    public Object processArg(String string) {
        if ((string = string.trim()).length() == 0) {
            return null;
        }
        if (string.equals("null")) {
            return null;
        }
        Matcher matcher = this.match("'(.*)'", string);
        if (matcher != null) {
            return f.o(matcher.group(1));
        }
        matcher = this.match("(-?\\d*)", string);
        if (matcher != null) {
            return Integer.parseInt(matcher.group(1));
        }
        matcher = this.match("(-?\\d*\\.\\d*)", string);
        if (matcher != null) {
            return Float.valueOf(Float.parseFloat(matcher.group(1)));
        }
        matcher = this.match("\\s*([^\\s\"']*)\\s*=(.*)", string);
        if (matcher != null) {
            String string2 = matcher.group(1);
            String string3 = matcher.group(2);
            System.out.println("processArg: setting: " + string2 + "=" + string3);
            Object object = this.processArg(string3);
            this.setLocalVariable(string2, object);
            return object;
        }
        matcher = this.match("\\s*([\\w\\.]+)\\((.*)\\)\\s*", string);
        if (matcher != null) {
            return this.processFunction(string, matcher);
        }
        if ("false".equalsIgnoreCase(string)) {
            return Boolean.FALSE;
        }
        if ("true".equalsIgnoreCase(string)) {
            return Boolean.TRUE;
        }
        Object object = this.getScriptVariable(string, false);
        if (object != null) {
            return object;
        }
        System.out.println("processArg: no variable:" + string);
        this.getScriptVariable(string, true);
        System.out.println("SlickLibRocket:HandleEvent: failed to match:" + string);
        return null;
    }

    public void printMetadata(HashMap hashMap) {
        if (hashMap == null) {
            System.out.println("No metadata");
        } else {
            String string = "";
            for (String string2 : hashMap.keySet()) {
                string = string + string2 + ",";
            }
            System.out.println("metadata:" + string);
        }
    }

    public Object getScriptVariable(String string, boolean bl) {
        Object object;
        if (this.slickLibRocket.d() != null) {
            object = this.slickLibRocket.d().getMetadata(string);
            if (object != null) {
                return object;
            }
            if (bl) {
                System.out.println("getScriptVariable: alert");
                this.printMetadata(this.slickLibRocket.d().metadata);
            }
        }
        if (this.slickLibRocket.c() != null) {
            object = this.slickLibRocket.c().getMetadata(string);
            if (object != null) {
                return object;
            }
            if (bl) {
                System.out.println("getScriptVariable: popup");
                this.printMetadata(this.slickLibRocket.c().metadata);
            }
        }
        if ((object = this.slickLibRocket.b(string)) != null) {
            return object;
        }
        if (bl) {
            System.out.println("getScriptVariable: document");
            this.printMetadata(this.slickLibRocket.getActiveDocumentMetadata());
        }
        if ((object = this.globals.get(string)) != null) {
            return object;
        }
        if (bl) {
            System.out.println("getScriptVariable: globals");
            this.printMetadata(this.globals);
        }
        return null;
    }

    public void setLocalVariable(String string, Object object) {
        HashMap hashMap = this.slickLibRocket.getActiveDocumentMetadata();
        hashMap.put(string, object);
    }

    public void setGlobalVariable(String string, Object object) {
        HashMap hashMap = this.globals;
        hashMap.put(string, object);
    }

    public Object processFunction(String string, Matcher matcher) {
        l l2 = l.B();
        String string2 = matcher.group(1);
        String string3 = matcher.group(2);
        String[] stringArray = string3.equals("") ? new String[]{} : al.a(string3, ',');
        Object[] objectArray = new Object[stringArray.length];
        for (int i = 0; i < objectArray.length; ++i) {
            objectArray[i] = this.processArg(stringArray[i]);
        }
        return this.runFunction(string2, objectArray);
    }

    public Object runFunction(String string, Object[] objectArray) {
        Object object;
        String[] stringArray = string.split("\\.");
        ScriptContext scriptContext = this.root;
        if (stringArray.length > 2) {
            ScriptEngine.logCritical("Unsupported nameParts: " + string);
            return null;
        }
        if (stringArray.length == 2) {
            object = this.getScriptVariable(stringArray[0], false);
            if (!(object instanceof ScriptContext)) {
                ScriptEngine.logCritical("Could not find context for: " + string);
                return null;
            }
            scriptContext = (ScriptContext)object;
            string = stringArray[1];
        }
        if ((object = (Method)scriptContext.methods.get(string)) == null) {
            ScriptEngine.logCritical("Could not find function: " + string);
            return null;
        }
        Class<?>[] classArray = ((Method)object).getParameterTypes();
        ArrayList<Object> arrayList = new ArrayList<Object>();
        if (objectArray.length > classArray.length) {
            ScriptEngine.logCritical("function: " + string + " does not accept " + objectArray.length + " parameters");
        }
        for (int i = 0; i < classArray.length; ++i) {
            Class<?> clazz = classArray[i];
            Object object2 = null;
            if (i < objectArray.length) {
                object2 = objectArray[i];
            }
            if (object2 == null || clazz.isInstance(object2) || clazz.equals(Float.class)) {
                // empty if block
            }
            arrayList.add(object2);
        }
        try {
            Object object3 = ((Method)object).invoke(scriptContext, arrayList.toArray());
            return object3;
        }
        catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
            throw new RuntimeException(illegalAccessException);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            l.b("convertedParameters:");
            for (Object object2 : arrayList) {
                if (object2 == null) {
                    l.b("=null");
                    continue;
                }
                l.b("=" + object2.getClass().getName());
            }
            l.b("-----");
            illegalArgumentException.printStackTrace();
            throw new RuntimeException(illegalArgumentException);
        }
        catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
            throw new RuntimeException(invocationTargetException);
        }
    }

    public static void logError(String string) {
        l.e("ScriptEngine - error: " + string);
    }

    public static void logCritical(String string) {
        l.e("ScriptEngine - critical: " + string);
        if (ScriptEngine.isStrict()) {
            throw new RuntimeException("ScriptEngine - critical:" + string);
        }
    }

    static {
        isMainScriptThread = new ScriptEngine$1();
    }
}
