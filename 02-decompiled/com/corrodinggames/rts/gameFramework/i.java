/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.l;

public class i
implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler a;

    i(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.a = uncaughtExceptionHandler;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized void uncaughtException(Thread thread, Throwable throwable) {
        boolean bl = false;
        boolean bl2 = false;
        l l2 = null;
        try {
            l.dQ = null;
            l.dR = null;
            l.dP = null;
            System.gc();
            try {
                l.e("uncaughtException start");
                l2 = l.B();
                if (l2 != null && throwable instanceof OutOfMemoryError) {
                    l.e("Freeing memory");
                    try {
                        b.al = null;
                        if (l2.bL != null) {
                            l2.bL = null;
                        }
                        if (l2.bN != null) {
                            l2.bN.i();
                            l2.bN = null;
                        }
                        System.gc();
                        l.e("uncaughtException: Memory freed");
                    }
                    catch (Throwable throwable2) {
                        l.e("exception freeing memory");
                        throwable2.printStackTrace();
                    }
                }
                l.a("gameEngine:uncaughtExceptionHandler", throwable);
                String string = l.a(throwable);
                boolean bl3 = false;
                boolean bl4 = false;
                if (l2 != null) {
                    SettingsEngine settingsEngine = l2.bQ;
                    if (settingsEngine != null) {
                        bl3 = settingsEngine.sendReports;
                    } else {
                        l.e("CustomExceptionHandler: no settings");
                    }
                } else {
                    l.e("CustomExceptionHandler: no game");
                }
                if (l.dO) {
                    l.e("CustomExceptionHandler: a crash was already sent");
                    bl3 = false;
                    bl4 = true;
                }
                l.dO = true;
                if (bl3) {
                    try {
                        l.e("Starting errorReport");
                        n.a("uncaughtException", string);
                        l.e("waiting");
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
                l.e("fatal", string);
            }
            catch (Exception exception) {
                l.e("exception sending crash");
                exception.printStackTrace();
            }
            if (l2 != null) {
                if (l2.dH != null && l2.dH.a()) {
                    l.e("gameCrashesDontExit=true");
                    bl = true;
                    return;
                }
                if (l2.bX != null && l2.bX.B) {
                    l.e("Sending disconnect");
                    l2.bX.c("Game crash");
                }
            }
            if (!l.az) {
                if (this.a != null) {
                    l.e("CustomExceptionHandler: sending to: defaultUEH.uncaughtException");
                    this.a.uncaughtException(thread, throwable);
                    l.e("CustomExceptionHandler: back from: defaultUEH.uncaughtException");
                } else {
                    l.e("CustomExceptionHandler: defaultUEH==null");
                    System.exit(2);
                }
            }
            l.av = throwable;
            bl = true;
        }
        catch (Throwable throwable3) {
            l.e("Exception in uncaughtException");
            throwable3.printStackTrace();
        }
        finally {
            if (!bl) {
                l.e("Crash was not handled, exiting");
                Runtime.getRuntime().halt(1);
            }
        }
    }
}
