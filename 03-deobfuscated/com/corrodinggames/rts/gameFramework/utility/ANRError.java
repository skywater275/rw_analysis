/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import android.os.Looper;
import com.corrodinggames.rts.gameFramework.utility.ANRError$1;
import com.corrodinggames.rts.gameFramework.utility.StackTraceEntry;
import com.corrodinggames.rts.gameFramework.utility.ANRThrowable;
import java.util.Map;
import java.util.TreeMap;

public class ANRError
extends Error {
    private ANRError(ANRThrowable c2) {
        super("Application Not Responding", c2);
    }

    @Override
    public Throwable fillInStackTrace() {
        this.setStackTrace(new StackTraceElement[0]);
        return this;
    }

    static ANRError a(String string, boolean bl) {
        Thread thread = Looper.b().e();
        TreeMap<Thread, Object> treeMap = new TreeMap<Thread, Object>(new ANRError$1(thread));
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            if (entry.getKey() != thread && (!entry.getKey().getName().startsWith(string) || !bl && ((StackTraceElement[])entry.getValue()).length <= 0)) continue;
            treeMap.put(entry.getKey(), entry.getValue());
        }
        if (!treeMap.containsKey(thread)) {
            treeMap.put(thread, thread.getStackTrace());
        }
        Object object = null;
        for (Map.Entry entry : treeMap.entrySet()) {
            StackTraceEntry b2 = new StackTraceEntry(ANRError.a((Thread)entry.getKey()), (StackTraceElement[])entry.getValue(), (ANRError$1) null);
            b2.getClass();
            object = new ANRThrowable(b2, (ANRThrowable) object, (ANRError$1) null);
        }
        return new ANRError((ANRThrowable) object);
    }

    static ANRError a() {
        Thread thread = Looper.b().e();
        StackTraceElement[] stackTraceElementArray = thread.getStackTrace();
        StackTraceEntry b2 = new StackTraceEntry(ANRError.a(thread), stackTraceElementArray, (ANRError$1) null);
        b2.getClass();
        return new ANRError(new ANRThrowable(b2, (ANRThrowable) null, (ANRError$1) null));
    }

    private static String a(Thread thread) {
        return thread.getName() + " (state = " + (Object)((Object)thread.getState()) + ")";
    }
}
