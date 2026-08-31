/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import android.os.Looper;
import com.corrodinggames.rts.gameFramework.utility.a$1;
import com.corrodinggames.rts.gameFramework.utility.b;
import com.corrodinggames.rts.gameFramework.utility.c;
import java.util.Map;
import java.util.TreeMap;

public class a
extends Error {
    private a(c c2) {
        super("Application Not Responding", c2);
    }

    @Override
    public Throwable fillInStackTrace() {
        this.setStackTrace(new StackTraceElement[0]);
        return this;
    }

    static a a(String string, boolean bl) {
        Thread thread = Looper.b().e();
        TreeMap<Thread, Object> treeMap = new TreeMap<Thread, Object>(new a$1(thread));
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            if (entry.getKey() != thread && (!entry.getKey().getName().startsWith(string) || !bl && ((StackTraceElement[])entry.getValue()).length <= 0)) continue;
            treeMap.put(entry.getKey(), entry.getValue());
        }
        if (!treeMap.containsKey(thread)) {
            treeMap.put(thread, thread.getStackTrace());
        }
        Object object = null;
        for (Map.Entry entry : treeMap.entrySet()) {
            b b2 = new b(a.a((Thread)entry.getKey()), (StackTraceElement[])entry.getValue(), null);
            b2.getClass();
            object = new c(b2, (c)object, null);
        }
        return new a((c)object);
    }

    static a a() {
        Thread thread = Looper.b().e();
        StackTraceElement[] stackTraceElementArray = thread.getStackTrace();
        b b2 = new b(a.a(thread), stackTraceElementArray, null);
        b2.getClass();
        return new a(new c(b2, null, null));
    }

    private static String a(Thread thread) {
        return thread.getName() + " (state = " + (Object)((Object)thread.getState()) + ")";
    }
}
