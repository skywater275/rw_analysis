/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui.panels;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.ui.panels.ChatPanel;
import com.corrodinggames.rts.gameFramework.rendering.FontRenderer;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;

public class e
extends ChatPanel {
    int a;
    int b;
    float c;
    float d;
    public boolean e = true;
    public boolean f = false;
    public float g = 1.0f;
    static Rect h = new Rect();
    static Rect i = new Rect();

    public e() {
    }

    public e(Texture e2, int n, int n2) {
        this.a(e2);
        this.a(e2, n, n2);
    }

    public void a(Texture e2, int n, int n2) {
        this.a = n;
        this.b = n2;
        this.c = (float)n / (float)e2.p;
        this.d = (float)n2 / (float)e2.q;
    }

    public e a() {  // 02b f/a/e: 返回 e (v19.133f3 SettingsPanel/Texture 幻觉修正)
        e e2 = new e();
        e2.a(this);
        return e2;
    }

    @Override
    public void a(ChatPanel h2) {
        e e2 = (e) h2;  // 02b f/a/e: (e)var1 (v19.133f3 修正)
        this.a = e2.a;
        this.b = e2.b;
        this.c = e2.c;
        this.d = e2.d;
        this.e = e2.e;
        super.a(e2);
    }

    @Override
    public void a(Texture e2) {
        super.a(e2);
    }

    @Override
    public void a(TextureManagerInterface y2, Rect rect) {
        this.b(y2, rect);
        if (this.q != null) {
            // empty if block
        }
    }

    public void b(TextureManagerInterface y2, Rect rect) {
        Texture e2 = this.p;
        Paint paint = this.o;
        this.a(y2, e2, paint, rect);
    }

    private boolean c() {
        return true;
    }

    private void a(TextureManagerInterface y2, Texture e2, Paint paint, Rect rect) {
        int n;
        int n2 = rect.a;
        int n3 = rect.b;
        int n4 = rect.b();
        int n5 = rect.c();
        int n6 = this.a;
        int n7 = this.b;
        if (!this.e) {
            if (n6 > n4 / 2) {
                n6 = n4 / 2;
            }
            if (n7 > n5 / 2) {
                n7 = n5 / 2;
            }
        } else {
            float f = 1.0f;
            n = n4 / 2;
            int n8 = n5 / 2;
            if ((float)n6 * f > (float)n) {
                f = (float)n / (float)n6;
            }
            if ((float)n7 * f > (float)n8) {
                f = (float)n8 / (float)n7;
            }
            n6 = (int)((float)this.a * f);
            n7 = (int)((float)this.b * f);
        }
        int n9 = n4 - 2 * n6;
        n = n5 - 2 * n7;
        float f = this.c;
        float f2 = this.d;
        if (this.c()) {
            this.a(y2, e2, paint, n2 + n6, n3 + 0, n9, n7, f, 0.0f, 1.0f - f, f2, this.f);
            this.a(y2, e2, paint, n2 + 0, n3 + n7, n6, n, 0.0f, f2, f, 1.0f - f2, this.f);
            this.a(y2, e2, paint, n2 + n6, n3 + n5 - n7, n9, n7, f, 1.0f - f2, 1.0f - f, 1.0f, this.f);
            this.a(y2, e2, paint, n2 + n4 - n6, n3 + n7, n6, n, 1.0f - f, f2, 1.0f, 1.0f - f2, this.f);
            this.a(y2, e2, paint, n2 + 0, n3 + 0, n6, n7, 0.0f, 0.0f, this.c, this.d);
            this.a(y2, e2, paint, n2 + n4 - n6, n3 + 0, n6, n7, 1.0f - this.c, 0.0f, 1.0f, this.d);
            this.a(y2, e2, paint, n2 + 0, n3 + n5 - n7, n6, n7, 0.0f, 1.0f - this.d, this.c, 1.0f);
            this.a(y2, e2, paint, n2 + n4 - n6, n3 + n5 - n7, n6, n7, 1.0f - this.c, 1.0f - this.d, 1.0f, 1.0f);
        }
        this.a(y2, e2, paint, n2 + n6, n3 + n7, n9, n, f, f2, 1.0f - f, 1.0f - f2, this.f);
    }

    public void a(TextureManagerInterface y2, Texture e2, Paint paint, int n, int n2, int n3, int n4, float f, float f2, float f3, float f4) {
        this.a(y2, e2, paint, n, n2, n3, n4, f, f2, f3, f4, false);
    }

    public void a(TextureManagerInterface y2, Texture e2, Paint paint, int n, int n2, int n3, int n4, float f, float f2, float f3, float f4, boolean bl) {
        Rect rect = h;
        Rect rect2 = i;
        rect.a((int)(f * (float)e2.p), (int)(f2 * (float)e2.q), (int)(f3 * (float)e2.p), (int)(f4 * (float)e2.q));
        rect2.a(n, n2, n + n3, n2 + n4);
        if (!bl) {
            y2.loadImageFromResource(e2, rect, rect2, paint);
        } else {
            FontRenderer.a(y2, e2, rect, rect2, paint, 0, 0, 0, 0, this.g);
        }
    }

    public /* synthetic */ Object clone() {
        return this.a();
    }
}
