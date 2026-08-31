/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.R;
import com.corrodinggames.rts.gameFramework.GameUtils;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.ui.HUDOverlay;
import com.corrodinggames.rts.gameFramework.ui.ActionType$1;
import com.corrodinggames.rts.gameFramework.ui.ActionType$2;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.ui.CameraMode;
import com.corrodinggames.rts.gameFramework.ui.StatsPanel;
import com.corrodinggames.rts.gameFramework.steam.Localization;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;

public class RateGameDialog {
    Paint a;
    boolean b = false;
    float c = 0.0f;
    float d = 0.0f;
    Rect e = new Rect();
    Rect f = new Rect();
    Rect g = new Rect();
    StatsPanel h = null;  // 02b f/f L34: y h (v19.133f4 GameSaver 幻觉修正)
    ArrayList i = new ArrayList();
    int j = 30;
    int k = 140;
    int l = 30;
    final Rect m = new Rect();
    boolean n;
    boolean o;
    static String textPrompt = com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.rategame.text", new Object[0]);
    static String q = com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.rategame.yes", new Object[0]);
    static String r = com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.rategame.no", new Object[0]);
    boolean s = false;
    float t = 0.0f;

    public RateGameDialog() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.updateDialog();
        this.a = new Paint();
        this.a.a(true);
        this.a.a(Paint$Align.b);
        this.a.a(255, 0, 255, 0);
        l2.a(this.a, 34.0f);
    }

    void updateDialog() {
        this.i.clear();
        this.i.add(new RateGameDialog$1(this, "Finish game"));
        this.i.add(new RateGameDialog$2(this, "Keep playing"));
    }

    boolean drawDialog() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return (l2.dq || l2.dt) && !l2.dr;
    }

    public void updateDialog(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        InGameUI g2 = l2.bS;
        boolean bl = this.drawDialog();
        this.m.h();
        this.n = false;
        if (bl && !g2.u) {
            int n2 = l2.a(this.j);
            int n3 = l2.a(this.k);
            int n4 = n2 + l2.a(this.l);
            int n5 = n3 + l2.a(this.l);
            int n6 = this.i.size();
            float f3 = 0.0f;
            if (g2.c) {
                g2.d += 2.0f * f2 / 60.0f;
                f3 = g2.d;
                f3 = GameUtils.b(f3, 0.0f, 1.0f);
                f3 = GameUtils.smoothstep(f3);  // 02b f.i(float) (v19.133f4 CameraMode 幻觉修正)
            }
            int n7 = l2.a(40) + n5 * n6;
            int n8 = l2.a(140);
            if (g2.b) {
                n8 += l2.a(50);
            }
            if (g2.c) {
                n7 = (int)GameUtils.f(n7, l2.cF * 0.9f, f3);  // 02b f.f(float,float,float) (v19.133f4 修正)
                n8 = (int)GameUtils.f(n8, l2.cH * 0.9f, f3);
            }
            float f4 = l2.cp - (float)(n8 / 2);
            if (!g2.c) {
                f4 = GameUtils.f(f4, f4 / 2.0f, 1.0f - f3);  // 02b f.f (v19.133f4 修正)
            }
            if (f4 < 20.0f) {
                f4 = 20.0f;
            }
            this.g.b = (int)f4;
            this.g.d = this.g.b + n8;
            this.g.a = (int)(l2.cF / 2.0f - (float)(n7 / 2));
            this.g.c = (int)(l2.cF / 2.0f + (float)(n7 / 2));
            this.m.a(this.g);
            if (this.m.b((int)g2.z, (int)g2.A)) {
                this.n = g2.U;
                g2.U = false;
                this.o = g2.I;
                g2.I = false;
            }
            g2.a(this.m);
        }
    }

    public void drawDialog(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        InGameUI g2 = l2.bS;
        boolean bl = this.drawDialog();
        if (!l2.dq) {
            this.b = false;
        } else if (!this.b) {
            this.b = true;
            if (!l2.ar && l2.bQ.numberOfWins >= 5 && !l2.bQ.rateGameShown && com.corrodinggames.rts.gameFramework.GlobalState.au) {
                this.s = true;
                l2.bQ.rateGameShown = true;
                l2.bQ.save();
            }
        }
        if (!bl) {
            this.c = 0.0f;
        }
        if (bl && !g2.u) {
            Object object;
            int n2;
            int n3;
            this.c += f2;
            if (l2.bx < 120) {
                this.c = 100000.0f;
            }
            if (this.n) {
                g2.U = true;
            }
            if (this.o) {
                g2.I = true;
            }
            boolean bl2 = this.c > 80.0f;
            float f3 = 95.0f;
            boolean bl3 = this.c > 100.0f;
            boolean bl4 = this.c > 110.0f;
            int n4 = l2.a(this.j);
            int n5 = l2.a(this.k);
            int n6 = n4 + l2.a(this.l);
            int n7 = n5 + l2.a(this.l);
            int n8 = this.i.size();
            int n9 = n5 * n8 + (n8 - 1) * n6;
            int n10 = (int)(l2.cF / 2.0f - (float)(n9 / 2));
            float f4 = 0.0f;
            float f5 = 0.0f;
            if (g2.c) {
                f4 = g2.d;
                f4 = GameUtils.b(f4, 0.0f, 1.0f);
                float f6 = f5 = (f4 = GameUtils.smoothstep(f4)) >= 1.0f ? 1.0f : 0.0f;  // 02b f.i (v19.133f4 修正)
            }
            if (bl2) {
                float f7 = g2.bt.g;
                g2.bt.g = f5;
                g2.bt.c(l2.bO, this.m);
                g2.bt.g = f7;
            }
            int n11 = this.m.b + l2.a(40);
            int n12 = (int)(l2.cF / 2.0f);
            int n13 = n3 = this.m.d - l2.a(45);
            int n14 = Color.a(140, 100, 100, 100);
            Paint paint = this.a;
            String string = "Victory!";
            if (l2.dt) {
                string = "Defeat";
            }
            float f8 = 1.0f;
            if (this.c < f3) {
                f8 = this.c / f3;
            }
            n11 = (int)((float)n11 - GameUtils.cosFast(f8 * 90.0f) * 100.0f);
            paint.a(string, 0, string.length(), this.e);
            l2.bO.a(string, (float)n12, (float)n11 - (paint.l() + paint.m()) / 2.0f, paint);
            if (this.c < 100.0f && !l2.dt) {
                this.d += f2;
                if (this.d > 0.5f) {
                    this.d = 0.0f;
                    l2.bR.b(com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
                    l2.bR.b();
                    n2 = Color.a(255, GameUtils.a(0, 255), GameUtils.a(0, 255), GameUtils.a(0, 255));
                    object = l2.bR.b(0.0f, 0.0f, 0.0f, n2);
                    if (object != null) {
                        ((HUDElement) object).ar = (short)4;
                        ((HUDElement) object).I = (float)n12 + GameUtils.c(-70.0f, 70.0f);
                        ((HUDElement) object).J = (float)n11 + GameUtils.c(-15.0f, 15.0f);
                        ((HUDElement) object).J += l2.cp / 2.0f;
                        ((HUDElement) object).K += l2.cp / 2.0f;
                        ((HUDElement) object).W = ((HUDElement) object).V = GameUtils.c(140.0f, 380.0f);
                        ((HUDElement) object).r = true;
                        ((HUDElement) object).s = true;
                        ((HUDElement) object).t = 5.0f;
                        ((HUDElement) object).E = 2.0f;
                        ((HUDElement) object).Q = GameUtils.c(-2.7f, 2.7f);
                        ((HUDElement) object).P = GameUtils.c(-12.7f, 12.7f);
                        ((HUDElement) object).G = 0.4f;
                        ((HUDElement) object).F = 0.2f;
                        ((HUDElement) object).R = GameUtils.c(2.0f, 4.0f);
                        ((HUDElement) object).w = 2.0f;
                        ((HUDElement) object).v = true;
                        ((HUDElement) object).p = true;
                    }
                }
            }
            n11 += 60;
            if (bl4) {
                boolean bl5;
                Rect rect = this.e;
                object = this.f;
                rect.a(this.m.a + l2.a(10), this.m.b + l2.a(60), this.m.c - l2.a(10), n13 - l2.a(10));
                ((Rect)object).a(rect);
                if (!g2.c) {
                    rect.b = this.m.d + l2.a(15);
                    rect.d = rect.b + l2.a(200);
                }
                boolean bl6 = bl5 = g2.d >= 1.0f;
                if (this.h != null) {
                    this.h.createPanel(rect, (Rect)object, f2, bl5, g2.b);  // 02b f/y L148 (v19.133f4 语义名)
                }
            }
            for (n2 = 0; n2 < this.i.size(); ++n2) {
                if (bl3 && g2.a(n10, n13, n5, n4, ((HUDOverlay) (object = (HUDOverlay) this.i.get(n2))).a(), com.corrodinggames.rts.gameFramework.ui.CameraMode.a, false, n14, g2.aD, g2.br)) {
                    this.s = false;
                    ((HUDOverlay) object).b();
                }
                n10 += n6 + n5;
            }
            if (this.s) {
                this.drawRateNowPopup(f2);
            }
            if (this.m.b((int)g2.z, (int)g2.A)) {
                // empty if block
            }
            g2.a(this.m);
        }
    }

    void drawRateNowPopup(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        InGameUI g2 = l2.bS;
        int n2 = l2.a(180);
        int n3 = (int)(l2.cF / 2.0f - (float)(n2 / 2));
        int n4 = l2.a(120);
        int n5 = (int)(l2.cH - (float)n4);
        this.g.a(n3, n5, n2, n4);
        l2.bO.b(this.g, g2.aP);
        int n6 = n3 + n2 / 2;
        int n7 = n5;
        Paint paint = this.a;
        String string = textPrompt;
        paint.a(string, 0, string.length(), this.e);
        l2.bO.a(string, (float)n6, (float)n7 - (paint.l() + paint.m()) / 2.0f, paint);
        int n8 = n5 + this.e.c();
        int n9 = l2.a(70);
        int n10 = l2.a(30);
        int n11 = n3 + n2 / 2 - l2.a(10) - n9;
        int n12 = Color.a(140, 100, 100, 100);
        String string2 = q;  // 02b f/f L43: q (v19.133f4 textYes 幻觉修正)
        if (g2.a(n11, n8, n9, n10, string2, com.corrodinggames.rts.gameFramework.ui.CameraMode.a, false, n12, g2.aD, null)) {
            this.s = false;
            com.corrodinggames.rts.appFramework.AppFramework f3 = l2.ao;
            if (f3 == null) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("showRateNow: gameView==null");
                return;
            }
            com.corrodinggames.rts.appFramework.InGameActivity g3 = f3.i();
            if (g3 == null) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("showRateNow: inGameActivity==null");
                return;
            }
            g3.l();
        }
        if (g2.a(n11 = n3 + n2 / 2 + l2.a(10), n8, n9, n10, string2 = r, com.corrodinggames.rts.gameFramework.ui.CameraMode.a, false, n12, g2.aD, null)) {
            this.s = false;
        }
    }

    public void drawRateNowPopup() {
        this.h = StatsPanel.createPanel();  // 02b f/f L316: y.a() (v19.133f4 语义名)
    }

    public void c() {  // 02b f/f.java L316: this.h = y.a() — y 类(f/y) 03 未映射, 简化 TODO
    }

}
