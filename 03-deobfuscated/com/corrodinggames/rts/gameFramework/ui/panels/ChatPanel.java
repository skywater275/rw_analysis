/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui.panels;
import com.corrodinggames.rts.gameFramework.OSEnum;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.panels.i;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;

public class ChatPanel {
    public static final ChatPanel j = new ChatPanel();  // 02b f/a/h.java L14: h j
    public static final ChatPanel k = new ChatPanel();  // 02b L15
    public static final ChatPanel l = new ChatPanel();  // 02b L16
    public static final ChatPanel m = new ChatPanel();  // 02b L17 (OSEnum 为幻觉名)
    public static final ChatPanel n = new ChatPanel();  // 02b L18
    Paint o = new UniquePaint();
    com.corrodinggames.rts.gameFramework.rendering.Texture p;  // 02b f/a/h: m/e p (v19.133f2 SettingsPanel 错标修正)
    Paint q = new UniquePaint();
    public ChatPanel r;
    public int s = 3;
    public int t = 3;
    public int u;
    public ChatPanel v;
    static Rect w = new Rect();
    static Rect x = new Rect();
    static Rect y = new Rect();

    public void a(Texture e2) {
        this.p = e2;
    }

    public void a(ChatPanel h2) {
        this.p = h2.p;
        this.o = h2.o != null ? new Paint(h2.o) : null;
        this.q = h2.q != null ? new Paint(h2.q) : null;
    }

    public static void b() {
        ChatPanel h2 = j;
        h2.o.b(Color.a(140, 100, 100, 100));
        h2.q.b(-16777216);
        h2.q.a(Paint$Style.b);
        h2 = k;
        h2.o.b(Color.a(180, 100, 100, 190));
        h2.q.b(-16777216);
        h2.q.a(Paint$Style.b);
        h2 = l;
        h2.o = null;
        h2.q = null;
        h2 = m;
        h2.o = null;
        h2.q.b(-65536);
        h2.q.c(127);
        h2.q.a(Paint$Style.b);
        h2 = n;
        h2.o.c(255);
        h2.p = com.corrodinggames.rts.gameFramework.GlobalState.B().bS.bl;
        h2.q.b(-7829368);
        h2.q.c(255);
        h2.q.a(Paint$Style.b);
    }

    public void a(TextureManagerInterface y2, RectF rectF) {
        x.a = (int)rectF.a;  // 02b f/a/h 静态字段 x (v19.133f3: h 前缀修正)
        x.b = (int)rectF.b;
        x.c = (int)rectF.c;
        x.d = (int)rectF.d;
        this.a(y2, x, i.a);
    }

    public void c(TextureManagerInterface y2, Rect rect) {
        this.a(y2, rect, i.a);
    }

    public void a(TextureManagerInterface y2, Rect rect, i i2) {  // 02b f/a/h: i var3 (v19.133f3: ReplayPanel 幻觉名修正)
        if (this.u > 0) {
            y.a(rect);
            rect = y;
            GameUtils.a(rect, (float)this.u);  // 02b f.java: a(Rect,float) (v19.133f3: SaveLoadPanel 幻觉名修正)
        }
        if (this.r != null) {
            w.a(rect);
            w.a(this.s, this.t);
            this.r.a(y2, w);
        }
        if (i2 == i.b && this.v != null) {
            this.v.a(y2, rect);
            return;
        }
        this.a(y2, rect);
    }

    public void a(TextureManagerInterface y2, Rect rect) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.p != null) {
            l2.bO.a(this.p, rect, this.o, 0, 0, 0, 0);
        } else if (this.o != null) {
            y2.clearScreen(rect, this.o);
        }
        if (this.q != null) {
            y2.clearScreen(rect, this.q);
        }
    }
}
