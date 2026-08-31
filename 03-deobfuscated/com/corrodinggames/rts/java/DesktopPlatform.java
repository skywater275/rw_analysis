/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.Sys
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.platform.a;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameState;
import com.corrodinggames.rts.java.GameLauncher;
import com.corrodinggames.rts.java.ActionAddCredits$1;
import com.corrodinggames.rts.java.ActionAddCredits$2;
import java.awt.Toolkit;
import org.lwjgl.Sys;

public class DesktopPlatform
extends com.corrodinggames.rts.gameFramework.GameState {
    GameLauncher a;

    public DesktopPlatform(GameLauncher main) {
        this.a = main;
    }


    public void a(String string, int n2) {
        GlobalState.e("slick queuing-alert:" + string);
        ScriptEngine.getInstance().addRunnableToQueue(new DesktopPlatform$1(this, string));
    }


    public void a(String string, String string2) {
        GlobalState.e("slick queuing-messageBox:" + string2);
        ScriptEngine.getInstance().addRunnableToQueue(new DesktopPlatform$2(this, string2, string));
    }


    public void a(String string, boolean bl) {
        GlobalState l2 = GlobalState.B();
        if (l2 != null && l2.bj) {
            return;
        }
        if (this.a.j != null) {
            this.a.j.a(string, bl);
        }
    }

    @Override
    public void d() {
        GlobalState.e("refreshModDisplay");
        ScriptEngine.getInstance().addScriptToQueue("mods.refreshModList()");
    }


    public void a(Throwable throwable) {
        this.a(throwable, true);
    }

    public void a(Throwable throwable, boolean bl) {
        try {
            GlobalState.e("----------- onGameCrash ----------");
            Toolkit.getDefaultToolkit();
            String string = GlobalState.b(throwable);
            String string2 = string + "\nCheck logs for more details";
            GlobalState.e("Error message: " + string2);
            if (com.corrodinggames.rts.platform.a.a()) {  // 02b a/a.a() (v19.133f)
                GlobalState.e("onGameCrash: Not showing popup message due to active debugSocket");
                System.exit(1);
                return;
            }
            if (throwable != null && throwable instanceof OutOfMemoryError) {  // 02b 32位检测 TODO (v19.133f)
                string2 = string2 + " (You are also using the 32 bit version, switching to the 64 bit version might help with out of memory)";
            }
            Sys.alert((String)"Crash", (String)string2);
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            GlobalState.e("onGameCrash: end");
        }
        catch (Throwable throwable2) {
            GlobalState.e("exception showing message");
            throwable2.printStackTrace();
        }
    }


    public boolean b() {
        return !GlobalState.B().I() && !this.a.p.b();
    }

    @Override
    public boolean c() {
        return com.corrodinggames.rts.platform.a.a();  // 02b a/a.a() (v19.133f)
    }

    public void k() {  // 02b game/i.k() 简化 TODO (v19.133f)
    }

    public void a(float var1, int var2) {  // 02b java/d L63 简化 TODO (v19.133f)
    }

    public void b(int var1, int var2) {  // 02b game/i.b(int,int) 简化 TODO (v19.133f)
    }

    public void a(ScreenshotLayer var1) {  // 02b game/i.a(j) 简化 TODO (v19.133f)
    }

    public void b(ScreenshotLayer var1) {  // 02b game/i.b(j) 简化 TODO (v19.133f)
    }

    public com.corrodinggames.rts.gameFramework.rendering.Renderer b(boolean var1) {  // 02b java/d L74 简化 TODO (v19.133f)
        return null;
    }

}
