/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;
import com.corrodinggames.rts.gameFramework.KeyTrigger;

import android.graphics.Paint$Cap;
import com.corrodinggames.rts.gameFramework.StatsHistory;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;

public class LineGraphStyle {
    StatsHistory a;  // 02b f/aa.java: bn a (ModUnitLoader 为幻觉名)
    String b;
    int c;
    UniquePaint[] d;
    UniquePaint[] e;

    public UniquePaint a(int n, boolean bl) {  // 02b f/aa.java: 返回 m/ag (ThemePaint 为幻觉名)
        int n2 = n / 25;
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > 10) {
            n2 = 10;
        }
        if (bl) {
            return this.e[n2];
        }
        return this.d[n2];
    }

    public LineGraphStyle(StatsHistory bn2, String string, int n2) {
        this.a = bn2;
        this.b = string;
        this.c = n2;
        this.d = new UniquePaint[11];
        this.e = new UniquePaint[11];
        for (int j = 0; j < 11; ++j) {
            int n3 = j * 25;
            if (j == 10) {
                n3 = 255;
            }
            this.d[j] = new UniquePaint();
            this.d[j].a(2.0f);
            if (GlobalState.aZ) {
                this.d[j].a(3.0f);
            }
            this.d[j].a(Paint$Cap.b);
            this.d[j].b(n2);
            this.d[j].c(n3);
            this.e[j] = new UniquePaint();
            this.e[j].b(-13162713);
            this.e[j].c(n3);
            this.e[j].a(5.0f);
            this.e[j].a(Paint$Cap.b);
        }
    }
}
