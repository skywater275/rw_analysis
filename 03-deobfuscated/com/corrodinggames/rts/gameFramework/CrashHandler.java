/*
 * v19.133f2: 02b gameFramework/i.java 全文直译 (崩溃处理器)
 * 混淆引用还原: l→GlobalState / game.b.b→MapEngine / j.n→WebAPIClient
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class CrashHandler
implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler a;

    public CrashHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {  // 02b i.java L12: i(UncaughtExceptionHandler)
        this.a = uncaughtExceptionHandler;
    }

    @Override
    public synchronized void uncaughtException(Thread thread, Throwable throwable) {
        boolean bl = false;
        boolean bl2 = false;
        GlobalState l2 = null;
        try {
            GlobalState.integrityTable2 = null;  // 02b l.dQ (v19.133f2 语义名)
            GlobalState.integrityTable3 = null;  // 02b l.dR
            GlobalState.dP = null;
            System.gc();
            try {
                GlobalState.e("uncaughtException start");
                l2 = GlobalState.B();
                if (l2 != null && throwable instanceof OutOfMemoryError) {
                    GlobalState.e("Freeing memory");
                    try {
                        MapEngine.al = null;  // 02b game/b/b.al (地图网格缓存)
                        if (l2.bL != null) {
                            l2.bL = null;
                        }
                        if (l2.bN != null) {
                            l2.bN.i();
                            l2.bN = null;
                        }
                        System.gc();
                        GlobalState.e("uncaughtException: Memory freed");
                    }
                    catch (Throwable throwable2) {
                        GlobalState.e("exception freeing memory");
                        throwable2.printStackTrace();
                    }
                }
                GlobalState.a("gameEngine:uncaughtExceptionHandler", throwable);
                String string = GlobalState.a(throwable);
                boolean bl3 = false;
                boolean bl4 = false;
                if (l2 != null) {
                    SettingsEngine settingsEngine = l2.bQ;
                    if (settingsEngine != null) {
                        bl3 = settingsEngine.sendReports;
                    } else {
                        GlobalState.e("CustomExceptionHandler: no settings");
                    }
                } else {
                    GlobalState.e("CustomExceptionHandler: no game");
                }
                if (GlobalState.nativeLibraryLoaded) {  // 02b l.dO (崩溃已上报标志)
                    GlobalState.e("CustomExceptionHandler: a crash was already sent");
                    bl3 = false;
                    bl4 = true;
                }
                GlobalState.nativeLibraryLoaded = true;
                if (bl3) {
                    try {
                        GlobalState.e("Starting errorReport");
                        WebAPIClient.a("uncaughtException", string);  // 02b j/n.java L423: ErrorReport 发送
                        GlobalState.e("waiting");
                        Thread.sleep(800L);
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
                if (!bl4 && l2 != null && l2.dH != null) {
                    l2.dH.a(throwable);
                }
                bl2 = true;
                GlobalState.e("fatal", string);
            }
            catch (Exception exception) {
                GlobalState.e("exception sending crash");
                exception.printStackTrace();
            }
            if (l2 != null) {
                if (l2.dH != null && l2.dH.a()) {
                    GlobalState.e("gameCrashesDontExit=true");
                    bl = true;
                    return;
                }
                if (l2.bX != null && l2.bX.B) {
                    GlobalState.e("Sending disconnect");
                    l2.bX.c("Game crash");
                }
            }
            if (!GlobalState.az) {
                if (this.a != null) {
                    GlobalState.e("CustomExceptionHandler: sending to: defaultUEH.uncaughtException");
                    this.a.uncaughtException(thread, throwable);
                    GlobalState.e("CustomExceptionHandler: back from: defaultUEH.uncaughtException");
                } else {
                    GlobalState.e("CustomExceptionHandler: defaultUEH==null");
                    System.exit(2);
                }
            }
            GlobalState.av = throwable;
            bl = true;
        }
        catch (Throwable throwable3) {
            GlobalState.e("Exception in uncaughtException");
            throwable3.printStackTrace();
        }
        finally {
            if (!bl) {
                GlobalState.e("Crash was not handled, exiting");
                Runtime.getRuntime().halt(1);
            }
        }
    }
}
