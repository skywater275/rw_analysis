/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui.panels;

public class BuildMenuPanel {
    public int a;
    public int b;
    public d c;
    public int d = -1;

    public static BuildMenuPanel a(int n, int n2) {
        BuildMenuPanel c2 = new BuildMenuPanel();
        c2.a = n;
        c2.b = n2;
        c2.c = com.corrodinggames.rts.gameFramework.ui.panels.d.b;
        c2.d = 1;
        return c2;
    }

    public static BuildMenuPanel b(int n, int n2) {
        BuildMenuPanel c2 = new BuildMenuPanel();
        c2.a = n;
        c2.b = n2;
        c2.c = com.corrodinggames.rts.gameFramework.ui.panels.d.a;
        c2.d = 1;
        return c2;
    }

    public boolean a() {
        return this.c == com.corrodinggames.rts.gameFramework.ui.panels.d.b;
    }

    public boolean b() {
        return this.c == com.corrodinggames.rts.gameFramework.ui.panels.d.a;
    }
}
