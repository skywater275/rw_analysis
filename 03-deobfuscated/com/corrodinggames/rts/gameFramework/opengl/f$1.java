/*
 * v19.133f87 补建: 02b b/f$1.java 直译 (BlendCallback 实现)
 */
package com.corrodinggames.rts.gameFramework.opengl;

class f$1 implements BlendCallback {

    final f a;

    f$1(f var1) {
        this.a = var1;
    }

    public void a(GLObject b2, BlendMode af2, boolean bl) {
        this.a.a.a(b2, 0, 0, b2.b(), b2.c(), af2, (TransformCallback)null);
    }
}
