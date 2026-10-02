/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.k;
import com.corrodinggames.rts.gameFramework.l;
import java.io.File;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class j {
    static ConcurrentHashMap a = new ConcurrentHashMap();
    static k b;

    public static long a(String string, boolean bl) {
        Long l = (Long)a.get(string);
        if (l != null) {
            return l;
        }
        l = j.a(string);
        if (!bl) {
            a.put(string, l);
            if (b == null) {
                // empty if block
            }
        }
        return l;
    }

    private static long a(String string) {
        File file = new File(string);
        return file.lastModified();
    }

    public static synchronized void a() {
        j.a(l.B().bQ.liveReloading);
    }

    public static synchronized void a(boolean bl) {
        if (!l.av()) {
            return;
        }
        if (bl) {
            if (b != null) {
                l.e("FileChangeEngine: Already running");
                return;
            }
            l.e("FileChangeEngine: Starting");
            b = new k();
            b.start();
        } else if (b != null) {
            j.b.a = false;
            b = null;
        }
    }

    public static void b() {
        int n2 = 0;
        Enumeration enumeration = a.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            long l2 = j.a(string);
            Long l3 = (Long)a.get(string);
            if (l3 == null) {
                l.e("FileChangeEngine: old lastModified null for " + string);
            } else if (l3 != l2) {
                l.e("FileChangeEngine: Detected change to:" + string + " now " + l2);
            }
            a.put(string, l2);
            if (++n2 <= 50) continue;
            n2 = 0;
            try {
                Thread.sleep(2L);
            }
            catch (InterruptedException interruptedException) {}
        }
    }
}
