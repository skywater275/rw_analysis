/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui.panels;
import com.corrodinggames.rts.gameFramework.pathfinding.PathFinder;

import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.ui.panels.ChatPanel;
import com.corrodinggames.rts.gameFramework.ui.TextFormatter;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import java.util.ArrayList;

public class LeaderboardPanel
extends UIElementBase {  // 02b f/a/j extends l; v19.133f2: 02b ui/a/l 修正
    String a;
    Paint b = new UniquePaint();
    ChatPanel c = ChatPanel.l;  // 02b f/a/h L16 (v19.133f2: OSEnum 幻觉名修正)
    ArrayList d;

    public LeaderboardPanel() {
        this.b.a(Paint$Align.b);
        this.b.b(-16777216);
        this.a(18.0f);
    }

    public void a(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();  // 02b: l.B() (v19.133f2 修正)
        l2.b(this.b, f2);
        this.e();
    }

    public void a(int n2) {
        this.b.b(n2);
    }


    public String a() {
        return super.a() + " (text:" + this.a + ")";
    }


    public void a(float f2, float f3) {
        super.a(f2, f3);
        TextureManagerInterface y2 = this.d();
        RectF rectF = this.a(new RectF(), f2, f3);
        this.c.a(y2, rectF);
        if (this.a == null) {
            return;
        }
        if (this.d == null) {
            y2.a(this.a, rectF.d(), rectF.d - this.l, this.b);
        } else {
            int n2 = 0;
            for (String string : (java.util.Collection<String>) (java.util.Collection) this.d) {
                Paint paint = this.b;
                int n3 = TextFormatter.a(paint);  // 02b f/d (v19.133f2: UILabel 旧名修正)
                y2.a(string, rectF.d(), rectF.b + this.k + (float)n3 + (float)(n2 * n3), paint);
                ++n2;
            }
        }
    }

    public void a(String string) {
        this.a = string;
        this.e();
    }

    public Rect c() {
        RectF rectF = this.a(new RectF(), 0.0f, 0.0f);
        Rect rect = new Rect();
        rect.d = (int)rectF.d;
        rect.b = (int)rectF.b;
        rect.a = (int)rectF.a;
        rect.c = (int)rectF.c;
        rect.c = 10000;
        return rect;
    }


    public void b() {
        super.b();
        TextureManagerInterface y2 = this.d();
        Rect rect = this.c();
        this.d = new ArrayList(TextFormatter.a(this.a, rect, this.b, this.b, true));  // 02b f/d (v19.133f2: UILabel 旧名修正)
        this.i = rect.b();
        this.j = rect.c();
        this.i += this.m + this.n;
        this.j += this.k + this.l;
    }
}
