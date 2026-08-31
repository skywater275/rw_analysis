/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import com.corrodinggames.rts.gameFramework.opengl.BlendMode;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.f;
import com.corrodinggames.rts.gameFramework.opengl.BlendCallback;

class ActionType$1
implements BlendCallback {
    final /* synthetic */ f a;

    ActionType$1(f f2) {
        this.a = f2;
    }

    @Override
    public void a(GLObject b2, BlendMode af2, boolean bl) {
        this.a.a.a(b2, 0, 0, b2.b(), b2.c(), af2, null);
    }
}
