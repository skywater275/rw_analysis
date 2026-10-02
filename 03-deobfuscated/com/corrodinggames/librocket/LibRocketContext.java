/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket;

import com.corrodinggames.librocket.LibRocketContext$1;
import com.corrodinggames.librocket.LibRocketContext$2;
import com.corrodinggames.librocket.LibRocketBridge;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.appFramework.AppFramework;
import com.corrodinggames.rts.appFramework.ContextMenuActivity;
import com.corrodinggames.rts.gameFramework.network.PasswordManager;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

public abstract class LibRocketContext {
    protected static LibRocketContext a;
    public LibRocketBridge b;
    public AppFramework c;
    boolean d = true;
    boolean e = true;

    public static LibRocketContext a() {
        return a;
    }

    public void a(LibRocketBridge b2, AppFramework f2) {
        this.b = b2;
        this.c = f2;
    }

    public void b() {
        GlobalState l2 = GlobalState.B();
        if (l2 != null) {
            l2.a(null, this.c, true);
        } else {
            GlobalState.e("showMainMenu: game is null");
        }
        this.b.setDocument("mainMenu.rml");
    }

    public void c() {
    }

    public void d() {
        this.b.setDocument("settings.rml");
    }

    public void e() {
        this.b.setDocument("leaderboard.rml");
    }

    public synchronized void a(boolean bl) {
        this.e = bl;
        this.f();
    }

    public synchronized void f() {
        GlobalState l2 = GlobalState.B();
        if (l2 == null) {
            GlobalState.b("Main::resumeGame(): game==null");
            return;
        }
        l2.bD = true;
        l2.a(null, this.c, this.e);
    }

    public synchronized void b(boolean bl) {
        GlobalState l2 = GlobalState.B();
        if (!bl) {
            GlobalState.e("endGame: queDisconnectAndWait");
            l2.bX.waitForSyncComplete();
        } else {
            GlobalState.e("endGame: network disconnect");
            l2.bX.m("shutdownServer");
        }
    }

    public synchronized void a(String string) {
        this.b(true);
        GlobalState l2 = GlobalState.B();
        this.e = false;
        String string2 = string;
        boolean bl = false;
        int n2 = 8;
        int n3 = 0;
        boolean bl2 = true;
        boolean bl3 = false;
        com.corrodinggames.rts.appFramework.ContextMenuActivity.a(string2, bl, n2, n3, bl2, bl3);
        this.f();
    }

    public void c(boolean bl) {
        this.e = bl;
    }

    public abstract void g();

    public abstract void h();

    public abstract int i();

    public abstract void d(boolean var1);

    public boolean j() {
        return this.b != null && !this.b.b();
    }

    public void a(int n2, char c2) {
        int n3 = SlickToAndroidKeycodes.b(n2);
        if (this.j()) {
            Integer n4 = SlickToAndroidKeycodes.c(n2);
            Object object = this.b.b("event_onkeydown");
            if (object != null) {
                ScriptEngine.getInstance().addScriptToQueue(object + "(" + n3 + ");");
                if (this.b.c() != null) {
                    return;
                }
            }
            if (c2 > '\u00ff') {
                ScriptEngine.getInstance().addRunnableToQueue(new LibRocketContext$1(this));
            }
            if (n2 == 30 && this.i() == 1) {
                this.b.processKeyDown(93, 0);
                this.b.processKeyUp(93, 0);
                this.b.processKeyDown(91, 2);
                this.b.processKeyUp(91, 2);
                return;
            }
            if (n2 == 46 && this.i() == 1) {
                this.b.processKeyDown(14, 1);
                return;
            }
            if (n2 == 47 && this.i() == 1) {
                this.b.processKeyDown(33, 1);
                return;
            }
            if (n4 != null) {
                this.b.processKeyDown(n4, this.i());
            } else if (c2 != '\u0000') {
                if (Character.isISOControl(c2)) {
                    if (c2 == '\b') {
                        GlobalState.e("backspace char pressed");
                        this.b.processKeyDown(69, 0);
                        this.b.processKeyUp(69, 0);
                    } else {
                        GlobalState.e("keyPressed skipping isISOControl:" + n2 + " c:" + c2 + " c_print:" + c2);
                    }
                } else {
                    this.b.processTextInputChar(c2);
                }
            }
            if (n2 == 28 || n2 == 156) {
                ScriptEngine.getInstance().addScriptToQueue("onEnter();");
            } else if (c2 == '\r') {
                GlobalState.e("keyPressed: new line entered");
                ScriptEngine.getInstance().addScriptToQueue("onEnter();");
            }
            if (n2 == 1) {
                ScriptEngine.getInstance().addScriptToQueue("onEscape();");
            }
            return;
        }
        GlobalState l2 = GlobalState.B();
        if (l2 != null) {
            l2.b(n3, true);
            if (n2 == 1) {
                // empty if block
            }
            return;
        }
    }

    public LinkedList k() {
        return null;
    }

    public boolean b(String string) {
        block12: {
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        GlobalState.e("Opening link from desktop api");
                        Desktop.getDesktop().browse(new URI(string));
                        return true;
                    }
                    catch (IOException iOException) {
                        iOException.printStackTrace();
                        break block12;
                    }
                    catch (URISyntaxException uRISyntaxException) {
                        uRISyntaxException.printStackTrace();
                        break block12;
                    }
                }
                String string2 = System.getProperty("os.name").toLowerCase();
                String[] stringArray = null;
                if (string2.contains("win")) {
                    stringArray = new String[]{"rundll32", "url.dll,FileProtocolHandler", string};
                }
                if (string2.contains("mac")) {
                    stringArray = new String[]{"open", string};
                }
                if (string2.contains("nix") || string2.contains("nux")) {
                    stringArray = new String[]{"xdg-open", string};
                }
                if (stringArray != null) {
                    Runtime runtime = Runtime.getRuntime();
                    try {
                        runtime.exec(stringArray);
                        return true;
                    }
                    catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                }
            }
            catch (RuntimeException runtimeException) {
                runtimeException.printStackTrace();
            }
        }
        return false;
    }

    public void l() {
    }

    public void m() {
    }

    public void n() {
    }

    public void o() {
        ScriptEngine scriptEngine;
        GlobalState l2 = GlobalState.B();
        if (!l2.bX.aW && (scriptEngine = ScriptEngine.getInstance()) != null) {
            scriptEngine.addScriptToQueue("mp.closeBattleroomIfOpen()");
        }
    }

    public void a(PasswordManager ae2) {
        GlobalState l2 = GlobalState.B();
        ScriptEngine scriptEngine = ScriptEngine.getInstance();
        if (scriptEngine != null) {
            LibRocketContext$2 a$2 = new LibRocketContext$2(this, scriptEngine, ae2);
            scriptEngine.addRunnableToQueue(a$2);
        }
    }
}
