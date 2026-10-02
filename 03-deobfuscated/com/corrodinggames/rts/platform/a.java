/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.platform;
import com.corrodinggames.librocket.scripts.ScriptEngine$Action;

import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.librocket.scripts.ScriptEngine$Action;
import com.corrodinggames.rts.platform.b;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;

public strictfp class a
implements Runnable {
    public ServerSocket a;
    public boolean b = true;
    public static boolean c = false;
    public static boolean d = false;
    static ArrayList e = new ArrayList();

    public static boolean a() {
        return c;
    }

    public static void a(String string) {  // 02b a/a.java L28: 静态方法 (03 误写为构造器)
        c = true;
        e.add(string);
    }

    public static void b() {
        if (e.size() == 0) {
            return;
        }
        a$1 a$1 = new a$1();
        Thread thread = new Thread(a$1);
        thread.start();
    }

    public static void a(int n2, String string) {  // 02b a/a.java L41: 静态方法
        try {
            c = true;
            GlobalState.aT = true;
            a a2 = new a();  // 02b a/a.java L45: a=platform.a 自身
            if (n2 != -1) {
                a2.a = new ServerSocket(n2);
                Thread thread = new Thread(a2);
                thread.start();
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    private a() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void run() {
        try {
            while (this.b) {
                Socket socket = this.a.accept();
                try {
                    socket.setTcpNoDelay(true);
                    b b2 = new b(this, socket);  // 02b a/a.java L64: b=platform.b
                    Thread thread = new Thread(b2);
                    thread.run();
                }
                catch (IOException iOException) {
                    GlobalState.e("Got IOException on debugSocket connection");
                    iOException.printStackTrace();
                    throw new RuntimeException(iOException);
                }
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public static String b(String string) {
        String string2 = null;
        String[] stringArray = null;
        int n2 = string.indexOf(" ");
        if (n2 == -1) {
            n2 = string.length();
        }
        String string3 = string.substring(0, n2).toLowerCase(Locale.ENGLISH);
        if (n2 != -1 && string.length() >= n2 + 1) {
            string2 = string.substring(n2 + 1);
            stringArray = string2.split(" ");
        }
        if (ScriptEngine.getInstance() == null) {
            GlobalState.b("DebugSocketConnection: waiting for ScriptEngine to start....");
            for (int j = 0; j < 100; ++j) {
                if (ScriptEngine.getInstance() != null) {
                    GlobalState.b("started");
                    break;
                }
                try {
                    Thread.sleep(100L);
                    continue;
                }
                catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
        if (string3.equalsIgnoreCase("ping")) {
            return "pong";
        }
        if (string3.equalsIgnoreCase("crash")) {
            throw new RuntimeException("test crash");
        }
        if (string3.equalsIgnoreCase("script")) {
            boolean bl;
            if (string2 == null) {
                return "argString==null";
            }
            ScriptEngine scriptEngine = ScriptEngine.getInstance();
            ScriptEngine$Action scriptEngine$Action = scriptEngine.addScriptToQueue(string2);
            String string4 = scriptEngine$Action.waitForCompletionOrCrash(bl = false);
            if (string4 == null) {
                return "done";
            }
            return string4;
        }
        if (string3.equalsIgnoreCase("function") || string3.equalsIgnoreCase("functionNoTimeout")) {
            String string5;
            if (string2 == null) {
                return "argString==null";
            }
            ScriptEngine scriptEngine = ScriptEngine.getInstance();
            String string6 = string2;
            a$2 a$2 = new a$2(scriptEngine, string6);
            ScriptEngine$Action scriptEngine$Action = scriptEngine.addRunnableToQueue(a$2);
            scriptEngine$Action.tryToCatchCrash = true;
            boolean bl = false;
            if (string3.equalsIgnoreCase("functionNoTimeout")) {
                bl = true;
            }
            if ((string5 = scriptEngine$Action.waitForCompletionOrCrash(bl)) == null) {
                String string7 = "ok\n";
                string7 = a$2.c == null ? string7 + "<NULL>" : string7 + "" + a$2.c;
                string7 = string7 + "\u0000";
                return string7;
            }
            String string8 = "crash\n";
            string8 = string8 + string5;
            string8 = string8 + "\u0000";
            return string8;
        }
        return "unknown command";
    }
}
