/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui.panels;

import android.graphics.Color;
import com.corrodinggames.rts.gameFramework.ui.panels.b;
import com.corrodinggames.rts.gameFramework.ui.panels.ActionType$1;
import com.corrodinggames.rts.gameFramework.ui.panels.g;
import com.corrodinggames.rts.gameFramework.ui.panels.ChatPanel;
import com.corrodinggames.rts.gameFramework.ui.panels.LeaderboardPanel;
import com.corrodinggames.rts.gameFramework.ui.panels.ActionPanel;
import com.corrodinggames.rts.gameFramework.ui.panels.m;
import com.corrodinggames.rts.gameFramework.ui.panels.MinimapPanel;
import com.corrodinggames.rts.gameFramework.steam.Localization;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;

public class f
extends MinimapPanel {
    g a;  // 02b f/a/f L16: g a (v19.133f3 MapInfoPanel 幻觉修正)

    public static f a(String string, boolean bl) {
        f f2 = new f();  // v19.133f3: SaveLoadPanel 幻觉名修正
        f2.b = ChatPanel.n;
        f2.i = 200.0f;
        f2.j = 200.0f;
        LeaderboardPanel j2 = new LeaderboardPanel();
        j2.a(string);
        j2.e(5.0f);
        j2.f(5.0f);
        j2.a(-1);
        f2.a(j2);
        f2.a = new g(com.corrodinggames.rts.gameFramework.ui.panels.m.c);
        f2.a(f2.a);
        if (bl) {
            b b2 = f2.b(com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.common.cancel", new Object[0]));
            b2.a(new f$1(f2));
        }
        return f2;
    }

    public b a(String string) {
        b b2 = new b();
        b2.a(string);
        b2.e(5.0f);
        b2.f(5.0f);
        b2.a(Color.a(255, 30, 240, 30));
        return b2;
    }

    public b b(String string) {
        return this.a(string, null);
    }

    public b a(String string, ActionPanel k2) {
        b b2 = this.a(string);
        b2.a(k2);
        this.a.a(b2);
        return b2;
    }

    public void u_() {
        if (!this.s) {
            return;
        }
        this.b();
    }


    public void b() {
        super.b();
        TextureManagerInterface y2 = this.d();
        this.i = this.z;
        this.j = this.y;
        this.i += this.m + this.n;
        this.j += this.k + this.l;
    }
}
