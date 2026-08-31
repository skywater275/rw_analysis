/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.commands;

import com.corrodinggames.rts.gameFramework.commands.DebugSession;
import com.corrodinggames.rts.gameFramework.commands.DebugMonitorTask;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;

public class DebugServer
implements Runnable {
    public static boolean a = false;
    public static boolean debugRenderEnabled = false;
    public static boolean c = false;
    public static boolean d = false;
    public static float e;
    boolean f = true;
    public static boolean g;
    public ServerSocket h;
    public boolean i = true;
    static ArrayList j;

    public static void a() {
        if (!a) {
            return;
        }
        GlobalState.e("-----");
        GlobalState.e("-----");
        GlobalState.e("----- Debug Active ----");
        GlobalState.e("-----");
        GlobalState.e("-----");
        GlobalState.aV = true;
        GlobalState.B().s();
        DebugServer a2 = new DebugServer();
        a2.b2();  // 实例 b 改名避与静态 b 冲突
    }

    public void b2() {  // 实例版 (02b 仅静态 b); 改名避重复
        DebugServer.a(5677, "");  // DebugSession 绫诲悕璇敤 boolean 淇 (鐩存帴寮€绔彛)
        GlobalState.B().eb.a(new DebugMonitorTask(this));
    }

    public static void b() {  // 02b a/a.java L33-38: e 闃熷垪闈炵┖鏃跺惎鍔ㄥ鐞嗙嚎绋?(GameLauncher 闈欐€佽皟鐢?
        if (j.size() != 0) {
            // 02b a$1 绾跨▼: 澶勭悊闃熷垪鍛戒护 (瀹屾暣浣撳緟 DebugServer 鎴樺焦)
            j.clear();
        }
    }

    public static void a(int n2, String string) {  // 02b a/a.java L41: 闈欐€佹柟娉?(GameLauncher 闈欐€佽皟鐢?
        try {
            c = true;
            GlobalState.aT = true;
            DebugServer a2 = new DebugServer();
            if (n2 != -1) {
                a2.h = new ServerSocket(n2);
                Thread thread = new Thread(a2);
                thread.start();
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    private DebugServer() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void run() {
        try {
            while (this.i) {
                Socket socket = this.h.accept();
                try {
                    socket.setTcpNoDelay(true);
                    DebugSession b2 = new DebugSession(this, socket);
                    Thread thread = new Thread(b2);
                    thread.run();
                }
                catch (IOException iOException) {
                    GlobalState.e("Got IOException on debug connection");
                    iOException.printStackTrace();
                    throw new RuntimeException(iOException);
                }
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public static String a(String string) {
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
        if (string3.equalsIgnoreCase("ping")) {
            return "pong";
        }
        if (string3.equalsIgnoreCase("script")) {
            return "todo";
        }
        if (string3.equalsIgnoreCase("function") || string3.equalsIgnoreCase("functionNoTimeout")) {
            return "todo";
        }
        return "unknown command";
    }

    static {
        // isRunning 幻觉名删除 (02b 无此字段)
        j = new ArrayList();
    }
}
