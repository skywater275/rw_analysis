/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.appFramework.AppFramework;
import com.corrodinggames.rts.appFramework.InGameActivity;
import com.corrodinggames.rts.appFramework.TouchState;
import com.corrodinggames.rts.gameFramework.rendering.OpenGLRenderer;
import com.corrodinggames.rts.gameFramework.rendering.Renderer;
import com.corrodinggames.rts.gameFramework.rendering.NullRenderer;
import com.corrodinggames.rts.java.DesktopWindow;

public class DesktopAppFramework
implements AppFramework {  // 02b java/d implements appFramework/f (v19.133f2 修正, 原 extends FontKey 错标)
    public int a;
    public int b;
    DesktopWindow c;
    TouchState d;  // 02b java/d d = appFramework/m (TouchState) (v19.133f2 修正)
    public Object e = new Object();
    public Object f = new Object();
    NullRenderer g = new NullRenderer();  // 02b java/d g = gameFramework/m/n (NullRenderer) (v19.133f2 修正)

    public DesktopAppFramework() {
        this.c = new DesktopWindow();
    }


    public void j() {
    }


    public void a() {
    }


    public void l() {  // 02b java/d l() void (v19.133f2 修正)
    }


    public boolean c() {
        return false;
    }


    public com.corrodinggames.rts.gameFramework.rendering.OpenGLRenderer d() {  // 02b java/d d() 返回 m/a (v19.133f2 修正)
        return null;
    }


    public boolean e() {
        return false;
    }


    public boolean f() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2 != null && l2.bQ.slick2dFullScreen;
    }


    public Object g() {
        return null;
    }

    public int o() {
        return this.a;
    }

    public int p() {
        return this.b;
    }


    public void h() {
    }


    public DesktopWindow i() {  // 02b java/d L77: i() 返回 appFramework/g (DesktopWindow) (v19.133f2 修正)
        return this.c;
    }


    public TouchState k() {  // 02b java/d k() 返回 appFramework/m (v19.133f2 修正)
        return this.d;
    }

    public Renderer b(boolean bl) {  // 02b java/d L87: b(boolean) 返回 m/l (Renderer) (v19.133f2 补缺)
        return this.g;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public void a(float f2, int n2) {
        Object object = this.f;
        synchronized (object) {
            this.f.notifyAll();
        }
    }


    public void b(float f2, int n2) {  // 02b java/d b(float,int) (v19.133f2 修正)
    }


    public void a(com.corrodinggames.rts.gameFramework.rendering.Renderer l2, boolean bl) {  // 02b java/d a(m/l,boolean) (v19.133f2 修正)
    }


    public void m() {
    }


    public boolean n() {  // 02b java/d n() (v19.133f2 补缺)
        return true;
    }


    public boolean b() {
        return true;
    }


    public void a(boolean bl) {
    }
}
