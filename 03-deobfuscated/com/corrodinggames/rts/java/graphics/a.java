/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.graphics;
import com.corrodinggames.librocket.LibRocketContext;

import com.corrodinggames.rts.java.GameLauncher;
import com.corrodinggames.rts.java.DesktopAppFramework;

public class a
extends com.corrodinggames.librocket.LibRocketContext {
    public GameLauncher f;
    boolean g = false;

    public static synchronized a p() {  // 02b b.a.p() 返回自身; LibRocketContext 返回类型错标修正
        if (a != null) {
            throw new RuntimeException("CommonGuiEngine already exists");
        }
        a a2 = new a();  // new LibRocketContext() 错标 (抽象类不可实例化)
        a = a2;
        return a2;
    }

    @Override
    public void g() {
        this.f.i();
    }

    @Override
    public void h() {
        this.f.u = true;
    }

    @Override
    public int i() {
        return this.f.j.e();
    }

    @Override
    public void d(boolean bl) {
        this.f.a(bl);
    }

    public void b() {  // 02b b.a.b() (Main L529: this.i.b()) — GameEngine 初始化后通知
        super.b();
    }
}
