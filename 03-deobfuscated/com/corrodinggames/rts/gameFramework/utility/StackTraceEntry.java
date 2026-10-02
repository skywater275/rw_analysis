/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import java.io.Serializable;

class StackTraceEntry
implements Serializable {
    private final String a;
    private final StackTraceElement[] b;

    private StackTraceEntry(String string, StackTraceElement[] stackTraceElementArray) {
        this.a = string;
        this.b = stackTraceElementArray;
    }

    // $FF: synthetic method (02b b.java L22: b(String,StackTraceElement[],a$1))
    StackTraceEntry(String string, StackTraceElement[] stackTraceElementArray, ANRError$1 anrError$1) {
        this(string, stackTraceElementArray);
    }

    static /* synthetic */ String a(StackTraceEntry b2) {
        return b2.a;
    }

    static /* synthetic */ StackTraceElement[] b(StackTraceEntry b2) {
        return b2.b;
    }
}
