/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.InGameActivity;
import com.corrodinggames.rts.appFramework.TouchState;
import com.corrodinggames.rts.gameFramework.rendering.OpenGLRenderer;
import com.corrodinggames.rts.gameFramework.rendering.Renderer;

public interface AppFramework {
    public void a();

    public void a(boolean var1);

    public boolean b();

    public boolean e();

    public com.corrodinggames.rts.gameFramework.rendering.OpenGLRenderer d();  // 02b f.java: m.a d() (v19.133f2 修正, 原 AppContext 错标)

    public boolean f();  // 02b f.java: f() (v19.133f2 修正, 原 isEnabled2 错标)



    public Object g();

    public void h();

    public InGameActivity i();

    public void j();

    public TouchState k();

    public void a(float var1, int var2);

    public void b(float var1, int var2);

    public void l();

    public com.corrodinggames.rts.gameFramework.rendering.Renderer b(boolean var1);  // 02b f.java: m.l b(boolean)

    public void a(com.corrodinggames.rts.gameFramework.rendering.Renderer var1, boolean var2);  // 02b f.java: a(m.l,boolean)

    public void m();

    public boolean n();

    boolean c();  // 02b f.java

}
