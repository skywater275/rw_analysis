/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;
import com.corrodinggames.rts.gameFramework.ui.panels.SettingsPanel;
import com.corrodinggames.rts.gameFramework.GameSaver;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Paint$Cap;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.StatsSample;
import com.corrodinggames.rts.gameFramework.StatsTimeline;
import com.corrodinggames.rts.gameFramework.StatsCategory;
import com.corrodinggames.rts.gameFramework.GameTimerTask;
import com.corrodinggames.rts.gameFramework.StatsHistory;
import com.corrodinggames.rts.gameFramework.StatsRecord;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.LineGraphStyle;
import com.corrodinggames.rts.gameFramework.ui.StatsGraph;
import com.corrodinggames.rts.gameFramework.ui.ac;
import com.corrodinggames.rts.gameFramework.ui.IntArray;
import com.corrodinggames.rts.gameFramework.ui.GameResult;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.ui.CameraMode;
import com.corrodinggames.rts.gameFramework.ui.z;
import com.corrodinggames.rts.gameFramework.steam.Localization;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import java.util.ArrayList;

public class StatsPanel {
    private ArrayList e;
    private ac f = ac.a;
    private z g = z.a;
    private ArrayList h = new ArrayList();
    private StatsGraph[] i = new StatsGraph[StatsCategory.values().length];
    private ArrayList j = new ArrayList();
    private StatsGraph[] k = new StatsGraph[StatsCategory.values().length];
    private ArrayList l;
    private StatsGraph[] m;
    LineGraphStyle a;  // 02b f/y.java: aa a
    private long n;
    private com.corrodinggames.rts.gameFramework.rendering.Texture o;
    private com.corrodinggames.rts.gameFramework.rendering.Texture[] textureArray;
    private Rect q;
    private Rect r;
    private ArrayList s = new ArrayList();
    private ArrayList t = new ArrayList();
    private int u = -1;
    private int v = -1;
    private int maxDisplayIndex = -1;
    Rect b = new Rect();
    Paint c;
    Paint d;

    public static StatsPanel createPanel() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        ArrayList arrayList = l2.bY.d();
        ArrayList arrayList2 = com.corrodinggames.rts.gameFramework.ui.GameResult.a();
        return new StatsPanel(arrayList, arrayList2);
    }

    private StatsPanel(ArrayList arrayList, ArrayList arrayList2) {
        this.e = arrayList2;
        for (StatsRecord bo2 : (java.util.Collection<StatsRecord>) (java.util.Collection) arrayList) {
            PlayerState n2 = com.corrodinggames.rts.game.PlayerState.k(bo2.modRegistryRef.b());
            this.h.add(new LineGraphStyle(bo2.modRegistryRef, n2.v, n2.K()));
        }
        ArrayList arrayList3 = com.corrodinggames.rts.game.PlayerState.f();
        for (Integer comparable : (java.util.Collection<Integer>) (java.util.Collection) arrayList3) {
            ArrayList<StatsRecord> arrayList4 = new ArrayList<StatsRecord>();
            for (StatsRecord bo22 : (java.util.Collection<StatsRecord>) (java.util.Collection) arrayList) {
                PlayerState n22 = com.corrodinggames.rts.game.PlayerState.k(bo22.modRegistryRef.b());
                if (n22.r != comparable) continue;
                arrayList4.add(bo22);
            }
            if (arrayList4.isEmpty()) continue;
            GameTimerTask bm2 = new GameTimerTask(arrayList4);
            this.j.add(new LineGraphStyle(bm2.modRegistryRef, "Team " + com.corrodinggames.rts.game.PlayerState.a(comparable), com.corrodinggames.rts.game.PlayerState.i(comparable)));
        }
        for (StatsCategory bj2 : StatsCategory.values()) {
            this.i[bj2.ordinal()] = new StatsGraph(bj2, this.h);
            this.k[bj2.ordinal()] = new StatsGraph(bj2, this.j);
        }
        this.l = this.h;
        this.m = this.i;
        this.initPanel();
    }

    public void initPanel() {
        this.f = ac.a;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.c = new Paint();
        this.c.a(true);
        this.c.a(Paint$Align.a);
        this.c.a(255, 0, 255, 0);
        l2.b(this.c, 16.0f);
        this.d = new Paint();
        this.d.a(true);
        this.d.a(Paint$Align.c);
        this.d.a(255, 0, 255, 0);
        l2.b(this.d, 16.0f);
        this.loadButtonTextures();
    }

    private void loadButtonTextures() {  // 02b f/y.java c() L135-146
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.textureArray = new com.corrodinggames.rts.gameFramework.rendering.Texture[ac.values().length + 2];
        this.textureArray[0] = l2.bO.a(R$drawable.stats_button_info);
        this.textureArray[1] = l2.bO.a(R$drawable.stats_button_income);
        this.textureArray[2] = l2.bO.a(R$drawable.stats_button_armyvalue);
        this.textureArray[3] = l2.bO.a(R$drawable.stats_button_buildingvalue);
        this.textureArray[4] = l2.bO.a(R$drawable.stats_button_totalvalue);
        this.textureArray[5] = l2.bO.a(R$drawable.stats_toggle_relative);
        this.textureArray[6] = l2.bO.a(R$drawable.stats_toggle_teams);
        this.r = new Rect(0, 0, this.textureArray[0].m(), this.textureArray[0].l());
    }

    public void createPanel(Rect rect, Rect rect2, float f2, boolean bl, boolean bl2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        InGameUI g2 = l2.bS;
        boolean bl3 = true;
        if (bl2) {
            Paint paint;
            Object object;
            int n2;
            int n3 = ac.values().length;
            int n4 = l2.a(30);
            int n5 = n4 * 2;
            int n6 = l2.a(20);
            int n7 = rect2.d - n4 - n6;
            int n8 = 2;
            int n9 = n3;
            n9 = g2.c ? (n9 += n8) : --n9;
            int n10 = n5 * n9 + n6 * (n9 - 1);
            int n11 = (int)(l2.cF / 2.0f - (float)(n10 / 2));
            Paint paint2 = new Paint();
            Paint paint3 = new Paint();
            paint3.a(100, 255, 255, 255);
            for (n2 = 0; n2 < n3; ++n2) {
                ac ac2 = ac.values()[n2];
                if (!g2.c && ac2 == ac.a) continue;
                if (g2.a(n11, n7, n5, n4, com.corrodinggames.rts.gameFramework.ui.CameraMode.a, false)) {
                    if (this.f != ac2) {
                        this.f = ac2;
                        this.n = System.currentTimeMillis();
                        this.u = -1;
                        this.v = -1;
                        this.maxDisplayIndex = -1;
                    }
                    if (this.f != ac.a) {
                        g2.c = true;
                    }
                }
                this.b.a(n11, n7, n11 + n5, n7 + n4);
                l2.bO.a(l2.bS.bn, this.r, this.b, paint2);
                paint = paint3;
                if (!g2.c || this.f == ac2) {
                    paint = paint2;
                }
                l2.bO.a(this.textureArray[n2], this.r, this.b, paint);
                n11 += n6 + n5;
            }
            n11 += n6;
            if (g2.c) {
                int n12 = n2 = this.g != z.a ? 1 : 0;
                if (g2.a(n11, n7, n5, n4, com.corrodinggames.rts.gameFramework.ui.CameraMode.a, false)) {
                    this.g = n2 == 0 ? z.b : z.a;
                    this.n = System.currentTimeMillis();
                }
                this.b.a(n11, n7, n11 + n5, n7 + n4);
                object = paint2;
                if (this.f == ac.a) {
                    object = paint3;
                }
                l2.bO.a(l2.bS.bn, this.r, this.b, (Paint)object);
                paint = paint2;
                if (n2 == 0 || this.f == ac.a) {
                    paint = paint3;
                }
                l2.bO.a(this.textureArray[5], this.r, this.b, paint);
                int n13 = n2 = this.l == this.j ? 1 : 0;
                if (g2.a(n11 += n6 + n5, n7, n5, n4, com.corrodinggames.rts.gameFramework.ui.CameraMode.a, false)) {
                    if (n2 == 0) {
                        this.l = this.j;
                        this.m = this.k;
                    } else {
                        this.l = this.h;
                        this.m = this.i;
                    }
                    this.n = System.currentTimeMillis();
                }
                this.b.a(n11, n7, n11 + n5, n7 + n4);
                object = paint2;
                if (this.f == ac.a) {
                    object = paint3;
                }
                l2.bO.a(l2.bS.bn, this.r, this.b, (Paint)object);
                paint = paint2;
                if (n2 == 0 || this.f == ac.a) {
                    paint = paint3;
                }
                l2.bO.a(this.textureArray[6], this.r, this.b, paint);
                n11 += n6 + n5;
            }
            if (this.f == ac.a) {
                bl3 = true;
            } else {
                bl3 = false;
                rect.d = n7 - l2.a(10);
                if (bl) {
                    this.createPanel(this.f.a(), this.g, rect);
                }
            }
        }
        if (bl3) {
            this.createPanel(rect, f2);
        }
    }

    private void createPanel(Rect rect, float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f3 = 1.5f;
        int n2 = rect.b + l2.a(25);
        int n3 = rect.d();
        String string = "123|";
        this.c.a(string, 0, string.length(), this.b);
        float f4 = this.b.c() + 6;
        for (GameResult e2 : (java.util.Collection<GameResult>) (java.util.Collection) this.e) {
            if (e2.survivalWaves != 1.0f && f3 > 0.0f) {
                e2.survivalWaves = GameUtils.a(e2.survivalWaves, 1.0f, 0.01f * f3 * f2);
                f3 -= 1.0f - e2.survivalWaves;
            }
            float f5 = e2.survivalWaves;
            f5 = GameUtils.b(f5, 0.0f, 1.0f);
            String string2 = "";
            if (e2.resultValue != null) {
                string2 = e2.resultValue;
            } else {
                string2 = "" + (int)(e2.scoreValue * f5);
                if (f5 <= 0.0f) {
                    string2 = " ";
                }
            }
            String string3 = e2.resultLabel;
            float f6 = e2.survivalWaves * 2.2f;
            f6 = GameUtils.b(f6, 0.0f, 1.0f);
            int n4 = 0;
            if (f6 > 0.0f) {
                n4 = (int)((float)string3.length() * f6);
            }
            n4 = GameUtils.b(n4, 0, string3.length());
            String string4 = "";
            if (n4 > 0 && n4 < string3.length() - 1) {
                string4 = "_";
            }
            string3 = string3.substring(0, n4) + string4 + GameUtils.d(" ", string3.length() + string4.length() - n4);
            l2.bO.a(string3, (float)n3 - 8.0f * this.c.k(), (float)n2, this.c);
            l2.bO.a(string2, (float)n3 + 8.0f * this.c.k(), (float)n2, this.d);
            n2 = (int)((float)n2 + f4);
        }
    }

    private void createPanel(StatsCategory bj2, z z2, Rect rect) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.createPanel(l2.bO, bj2, z2, rect);
    }

    private void createPanel(com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface y2, StatsCategory bj2, z z2, Rect rect) {
        float f2;
        float f3;
        float f4;
        int n2;
        int n3;
        String string;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        InGameUI g2 = l2.bS;
        StatsGraph ab2 = this.m[bj2.ordinal()];  // 02b f/y.java: ab var7
        float f5 = (float)(System.currentTimeMillis() - this.n) / 250.0f;
        Paint paint = new Paint();
        paint.a(255, 0, 255, 0);
        paint.a(true);
        paint.c(true);
        paint.a(Typeface.a(Typeface.c, 0));
        l2.b(paint, 14.0f);
        Paint paint2 = new Paint(paint);
        paint2.a(Paint$Align.b);
        l2.b(paint2, 14.0f);
        Paint paint3 = new Paint();
        paint3.a(2.0f);
        if (com.corrodinggames.rts.gameFramework.GlobalState.aZ) {
            paint3.a(3.0f);
        }
        paint3.a(Paint$Cap.b);
        Rect rect2 = new Rect();
        Paint paint4 = g2.aD;
        String string2 = com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.leaderboard.type." + bj2.name(), new Object[0]);
        paint4.a(string2, 0, string2.length(), this.b);
        y2.a(string2, (float)rect.d(), (float)(rect.b + this.b.c()), paint4);
        rect2.b = rect.b + this.b.c() + 3;
        rect2.d = rect.d - this.b.c() - 3;
        int n4 = Math.max(1, StatsGraph.a(ab2) - StatsGraph.b(ab2));
        float f6 = (float)rect2.c() / (float)n4;
        String string3 = GameUtils.a(0L);
        int n5 = y2.b(string3, paint2);
        y2.a(string3, (float)(rect.a + n5 / 2), (float)rect.d, paint2);
        rect2.a = rect.a + n5 / 2;
        String string4 = "123|";
        paint.a(string4, 0, string4.length(), this.b);
        int n6 = this.b.c();
        if (z2 == z.a) {
            string = com.corrodinggames.rts.gameFramework.audio.DataFieldCollector.a(StatsGraph.c(ab2).a(), StatsGraph.a(ab2));
            String string5 = com.corrodinggames.rts.gameFramework.audio.DataFieldCollector.a(StatsGraph.c(ab2).a(), StatsGraph.b(ab2));
            n5 = Math.max(y2.b(string, paint), y2.b(string5, paint));
            rect2.c = rect.c - n5 - 2;
            n3 = n6 / 2;
            y2.clearScreen(rect2, g2.aM);
            paint3.b(-13619152);
            for (n2 = 0; n2 <= 4; ++n2) {
                float f7 = (float)StatsGraph.b(ab2) + (float)n4 * (float)n2 / 4.0f;
                float f8 = (float)rect2.d - (f7 - (float)StatsGraph.b(ab2)) * f6;
                String string6 = com.corrodinggames.rts.gameFramework.audio.DataFieldCollector.a(StatsGraph.c(ab2).a(), (int)f7);
                y2.a(string6, (float)(rect2.c + 2), f8 + (float)n3, paint);
                if (n2 <= 0 || n2 >= 4) continue;
                y2.a((float)rect2.a, f8, (float)rect2.c, f8, paint3);
            }
        } else {
            rect2.c = rect.c - l2.a(10);
        }
        string = GameUtils.a((long)(StatsGraph.d(ab2) / 1000));
        n5 = y2.b(string, paint2);
        y2.a(string, (float)rect2.c, (float)rect.d, paint2);
        float f9 = (float)rect2.b() / (float)StatsGraph.d(ab2);
        if (z2 == z.a) {
            for (n3 = 0; n3 <= 2; ++n3) {
                for (LineGraphStyle aa2 : (java.util.Collection<LineGraphStyle>) (java.util.Collection) this.l) {
                    int n7;
                    boolean bl;
                    StatsTimeline bi2 = aa2.a.a(bj2);
                    boolean bl2 = bl = n3 == 0;
                    if (!bl) {
                        n7 = 220;
                        if (this.a != null) {
                            n7 = aa2 == this.a ? 255 : 50;
                        }
                    } else {
                        if (aa2.c != -16777216) continue;
                        n7 = 255;
                        if (this.a != null) {
                            n7 = aa2 == this.a ? 255 : 50;
                        }
                    }
                    if (n3 == 2 ? aa2 != this.a : n3 == 1 && aa2 == this.a) continue;
                    StatsSample bh2 = (StatsSample) bi2.get(0);
                    float f10 = rect2.a;
                    f4 = (float)rect2.d - f6 * (float)(bh2.b - StatsGraph.b(ab2));
                    for (int i2 = 1; i2 < bi2.size(); ++i2) {
                        bh2 = (StatsSample) bi2.get(i2);
                        f3 = (float)rect2.a + f9 * (float)bh2.a;
                        f2 = (float)rect2.d - f6 * (float)(bh2.b - StatsGraph.b(ab2));
                        int n8 = (int)((float)n7 * Math.min(1.0f, Math.max(0.0f, f5 - (float)bh2.a / (float)StatsGraph.d(ab2))));
                        UniquePaint ag2 = aa2.a(n8, bl);
                        y2.a(f10, f4, f3, f4, (Paint)ag2);
                        y2.a(f3, f4, f3, f2, (Paint)ag2);
                        f10 = f3;
                        f4 = f2;
                    }
                }
            }
        } else {
            ArrayList arrayList = StatsGraph.e(ab2);
            IntArray ad2 = (IntArray) arrayList.get(0);
            for (int i3 = 1; i3 < arrayList.size(); ++i3) {
                IntArray ad3 = (IntArray) arrayList.get(i3);
                float f11 = (float)rect2.a + f9 * (float)IntArray.a(ad2);
                float f12 = (float)rect2.a + f9 * (float)IntArray.a(ad3);
                float f13 = rect2.d;
                for (int i4 = 0; i4 < this.l.size(); ++i4) {
                    f4 = ad2.a(i4);
                    float f14 = f13 - (float)rect2.c() * f4;
                    if (f4 > 0.0f) {
                        LineGraphStyle aa3 = (LineGraphStyle) this.l.get(i4);
                        f2 = Math.min(1.0f, Math.max(0.0f, f5 - (float)IntArray.a(ad2) / (float)StatsGraph.d(ab2)));
                        UniquePaint ag3 = aa3.a((int)(f2 * 255.0f), false);
                        this.b.a((int)f11, (int)(f14 + 0.5f), (int)f12, (int)(f13 + 0.5f));
                        if (this.o != null) {
                            y2.a(this.o, this.q, this.b, (Paint)ag3);  // 02b L475: m.y.a(e,Rect,Rect,Paint)
                        } else {
                            y2.clearScreen(this.b, (Paint)ag3);
                        }
                    }
                    f13 = f14;
                }
                ad2 = ad3;
            }
        }
        if (rect2.b((int)g2.x, (int)g2.y)) {
            g2.a((float)rect2.a, (float)rect2.b, (float)rect2.b(), rect2.c());
            paint3.b(-1);
            y2.a(g2.x, (float)rect2.b, g2.x, (float)rect2.d, paint3);
            int n9 = (int)g2.x;
            n2 = (int)g2.y;
            int n10 = (int)((g2.x - (float)rect2.a) / f9);
            if (this.v != n9 || this.maxDisplayIndex != n2) {
                this.v = n9;
                this.maxDisplayIndex = n2;
                this.u = n10;
                this.s.clear();
                this.t.clear();
                this.s.add(GameUtils.a((long)(this.u / 1000)));
                this.t.add(-1);
                LineGraphStyle aa4 = null;  // 02b f/y.java: aa var45
                if (z2 == z.a) {
                    float f15 = 30.0f;
                    for (LineGraphStyle aa5 : (java.util.Collection<LineGraphStyle>) (java.util.Collection) this.l) {
                        StatsHistory bn2 = aa5.a;
                        int n11 = bn2.a(bj2, this.u);
                        float f16 = (float)rect2.d - f6 * (float)(n11 - StatsGraph.b(ab2));
                        f3 = GameUtils.c(f16 - g2.y);
                        if (!(f3 < f15)) continue;
                        f15 = f3;
                        aa4 = aa5;
                    }
                }
                this.a = aa4;
                for (LineGraphStyle aa6 : (java.util.Collection<LineGraphStyle>) (java.util.Collection) this.l) {
                    StatsHistory bn3 = aa6.a;
                    int n12 = bn3.a(bj2, this.u);
                    String string7 = com.corrodinggames.rts.gameFramework.audio.DataFieldCollector.a(StatsGraph.c(ab2).a(), n12) + " " + aa6.b;
                    this.s.add(string7);
                    int n13 = aa6.c;
                    if (this.a != null && this.a != aa6) {
                        int n14 = 60;
                        n13 = Color.a(n14, Color.b(n13), Color.c(n13), Color.d(n13));
                    }
                    this.t.add(n13);
                }
            }
            this.b.a = rect2.a + l2.a(5);
            this.b.b = rect2.b + l2.a(5);
            this.b.d = this.b.b + l2.a(5) + n6 * this.s.size();
            String string8 = "";
            for (String string9 : (java.util.Collection<String>) (java.util.Collection) this.s) {
                if (string8.length() >= string9.length()) continue;
                string8 = string9;
            }
            int n15 = y2.b(string8, paint);
            this.b.c = this.b.a + l2.a(10) + n15;
            y2.clearScreen(this.b, g2.aL);
            int n16 = this.b.b + n6 + 3;
            for (int i5 = 0; i5 < this.s.size(); ++i5) {
                paint.b((Integer)this.t.get(i5));
                y2.a((String)this.s.get(i5), (float)(this.b.a + 3), (float)n16, paint);
                n16 += n6;
            }
        } else {
            this.a = null;
        }
    }
}