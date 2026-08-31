/*
 * Decompiled with CFR 0.152.
 */
package android.util;

import java.text.SimpleDateFormat;

final class Log$1
extends ThreadLocal {
    Log$1() {
    }

    protected SimpleDateFormat a() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }

    protected /* synthetic */ Object initialValue() {
        return this.a();
    }
}
