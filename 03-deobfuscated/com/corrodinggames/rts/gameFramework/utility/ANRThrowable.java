/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.StackTraceEntry;

class ANRThrowable
extends Throwable {
    final /* synthetic */ StackTraceEntry a;

    private ANRThrowable(StackTraceEntry b2, ANRThrowable c2) {
        super(StackTraceEntry.a(b2), c2);
        this.a = b2;
    }

    // $FF: synthetic method (02b c.java L23: c(b,c,a$1))
    ANRThrowable(StackTraceEntry b2, ANRThrowable c2, ANRError$1 anrError$1) {
        this(b2, c2);
    }

    @Override
    public Throwable fillInStackTrace() {
        this.setStackTrace(StackTraceEntry.b(this.a));
        return this;
    }
}
