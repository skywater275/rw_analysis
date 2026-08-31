/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.SleepThread;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.File;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class FileWatcher {
    static ConcurrentHashMap a = new ConcurrentHashMap();
    static SleepThread b;

    public static long a(String string, boolean bl) {
        Long l = (Long)a.get(string);  // 02b: a.get (ByteIndexedMap 幻觉名修正)
        if (l != null) {
            return l;
        }
        l = FileWatcher.a(string);
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
        FileWatcher.a(GlobalState.B().bQ.liveReloading);
    }

    public static synchronized void a(boolean bl) {
        if (!GlobalState.av()) {
            return;
        }
        if (bl) {
            if (b != null) {
                GlobalState.e("FileChangeEngine: Already running");
                return;
            }
            GlobalState.e("FileChangeEngine: Starting");
            b = new SleepThread();
            b.start();  // 02b: b.start() (k=SleepThread, ByteSlot 幻觉名修正)
        } else if (b != null) {
            b.a = false;  // 02b: b.a = false (j.b.a 幻觉名修正)
            b = null;
        }
    }

    public static void b() {
        int n2 = 0;
        Enumeration enumeration = a.keys();  // 02b: a.keys()
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            long l2 = FileWatcher.a(string);
            Long l3 = (Long)a.get(string);  // 02b: a.get
            if (l3 == null) {
                GlobalState.e("FileChangeEngine: old lastModified null for " + string);
            } else if (l3 != l2) {
                GlobalState.e("FileChangeEngine: Detected change to:" + string + " now " + l2);
            }
            a.put(string, l2);  // 02b: a.put
            if (++n2 <= 50) continue;
            n2 = 0;
            try {
                Thread.sleep(2L);
            }
            catch (InterruptedException interruptedException) {}
        }
    }
}
