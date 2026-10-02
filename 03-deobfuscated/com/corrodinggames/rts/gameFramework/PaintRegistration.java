/*
 * 02b m.java 直译 (Paint 尺寸注册项, CustomArrayList 为幻觉名)
 */
package com.corrodinggames.rts.gameFramework;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;

class PaintRegistration {

    float a;
    Paint b;
    final GlobalState c;

    PaintRegistration(GlobalState globalState) {
        this.c = globalState;
    }

    void a() {
        float f2 = (float)this.c.e(this.a);
        if (this.b.k() != f2) {
            if (this.b instanceof UniquePaint) {
                ((UniquePaint)this.b).c(f2);
            } else {
                this.b.b(f2);
            }
        }
    }
}
