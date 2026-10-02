/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;
import com.corrodinggames.rts.gameFramework.KeyTrigger;
import com.corrodinggames.rts.gameFramework.ui.StatsPanel;

import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.af;
import com.corrodinggames.rts.gameFramework.ui.ThemePaint;
import com.corrodinggames.rts.gameFramework.ui.ThemeColorEntry;
import com.corrodinggames.rts.gameFramework.ui.ThemeFontEntry;
import com.corrodinggames.rts.gameFramework.ui.UnitInfoPanel;
import com.corrodinggames.rts.gameFramework.ui.ak;
import com.corrodinggames.rts.gameFramework.ui.TextFormatter;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class ThemeColors {
    static com.corrodinggames.rts.gameFramework.rendering.UniquePaint a = new com.corrodinggames.rts.gameFramework.rendering.UniquePaint();
    static com.corrodinggames.rts.gameFramework.rendering.UniquePaint secondaryColor = new com.corrodinggames.rts.gameFramework.rendering.UniquePaint();
    static com.corrodinggames.rts.gameFramework.rendering.UniquePaint accentColor = new com.corrodinggames.rts.gameFramework.rendering.UniquePaint();
    public Paint d = a;
    public Paint e = a;
    static Paint f = new Paint();
    public Paint g = this.d;
    static Paint h;
    CustomArrayList i = new CustomArrayList();  // 02b f/ae.java: m i (v19.132 修)

    public void b() {
        this.i.clear();
    }

    public void b(String string) {
        if (this.g != null && this.g != this.d) {
            this.a(string, this.g);  // 02b f/ae L97: a(String,Paint)
            return;
        }
        this.a(new ThemeFontEntry(this, string));  // 02b f/ae L93: a(af)
    }

    public int c() {
        GlobalState l2 = GlobalState.B();
        return l2.bO.a("A", this.g);
    }

    static {
        accentColor.a(true);  // 02b f/ae L255: static c (m/ag) (v19.133e)
        h = new Paint();
        h.b(-65536);
        h.a(Paint$Style.b);
    }

    public void a(Paint paint) {
        if (paint == null) {
            this.g = this.d;
            return;
        }
        this.g = paint;
    }

    public void a(boolean bl) {
        this.g = bl ? this.e : this.d;
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object8 : this.i) {  // 02b: raw 集合显式迭代 (F17)
            af af2 = (af) object8;
            if (!(af2 instanceof ThemeFontEntry)) continue;
            stringBuilder.append(((ThemeFontEntry) af2).d);
        }
        return stringBuilder.toString();
    }

    public void a(String string) {
        int n;
        af af2;
        if (this.i.size() > 0 && (af2 = (af)this.i.get(n = this.i.size() - 1)) instanceof ThemeFontEntry) {  // 02b f/ai (v19.133e)
            ThemeFontEntry ai2 = (ThemeFontEntry) af2;
            String string2 = ai2.d.endsWith(string) ? ai2.d.substring(0, ai2.d.length() - string.length()) : ai2.d;  // 02b f.a(String,String) 去后缀 (v19.133e)
            if (!ai2.d.equals(string2)) {
                this.i.set(n, ai2.b(string2));
            }
        }
    }

    public void a(af af2) {
        this.i.add(af2);
    }

    public void a(String string, Paint paint) {
        this.a(new ThemePaint(this, string, paint));  // 02b f/ag 3参 (v19.133e)
    }

    public void a(String string, int n) {  // 02b f/ae.a(String,int)
        if (this.g != null && this.g != this.d) {
            this.a(new ThemePaint(this, string, this.g, n));  // 02b f/ag 4参 (v19.133e)
            return;
        }
        this.a(new ThemePaint(this, string, null, n));  // 02b f/ag 4参 (v19.133e)
    }

    public void a(String string, int n, boolean bl) {
        Paint paint = this.d;
        if (bl) {
            paint = this.e;
        }
        this.a(new ThemePaint(this, string, paint, n));  // 02b f/ag 4参 (v19.133e)
    }

    public void a(com.corrodinggames.rts.gameFramework.rendering.Texture e2, int n, int n2) {  // 02b f/ae L118: a(m/e,int,int) (v19.133e)
        ThemeColorEntry ah2 = new ThemeColorEntry(this);  // 02b f/ah
        ah2.a = e2;
        float f2 = TextFormatter.a(e2, n, n2);
        ah2.c = (int)((float)e2.p * f2);
        ah2.d = (int)((float)e2.q * f2);
        ah2.b = f2;
        this.i.add(ah2);
    }


    public ThemeLayout a(int n2, boolean bl) {  // 02b f/ae L133: aj (v19.133e)
        GlobalState l2 = GlobalState.B();  // 02b f/ae L134: l (GlobalState) (v19.133e)
        Rect rect = new Rect(-n2 / 2, 0, n2 / 2, 10);
        CustomArrayList m2 = new CustomArrayList();  // 02b: utility.m (v19.133e)
        ak ak2 = new ak();
        Paint paint = this.d;
        int n3 = n2 - 5;
        block0: for (Object object6 : this.i) {  // 02b: raw 集合显式迭代 (F17)
            af af2 = (af) object6;
            if (ak2.b >= n3 - 5) {
                if (ak2.a.size() > 0) {
                    m2.add(ak2);
                }
                ak2 = new ak();
            }
            if (!(af2 instanceof ThemeFontEntry)) {
                ak2.a(af2);
                ak2.b += af2.a(this.d);
                continue;
            }
            ThemeFontEntry ai2 = (ThemeFontEntry) af2;
            Object object = ai2.d;
            int n4 = 0;
            while (n4 < ((String)object).length()) {
                String string;
                if (((String)object).charAt(n4) == '\n') {
                    ++n4;
                    m2.add(ak2);
                    ak2 = new ak();
                    continue;
                }
                int n5 = paint.a((CharSequence)object, n4, ((String)object).length(), true, (float)(n3 - ak2.b), null);
                if (n5 == 0) continue block0;
                boolean bl2 = true;
                int n6 = ((String)object).indexOf("\n", n4 + 1);
                if (n6 != -1 && n6 < n4 + n5) {
                    n5 = n6 - n4;
                } else {
                    int n7;
                    if (n4 + n5 < ((String)object).length() && (n7 = (string = ((String)object).substring(n4, n4 + n5)).lastIndexOf(" ")) != -1 && n7 != 0) {
                        n5 = n7;
                    }
                    if (n4 + n5 == ((String)object).length()) {
                        bl2 = false;
                    }
                }
                string = ((String)object).substring(n4, n4 + n5);
                if (string.contains("\\n")) {  // 02b f.c(String,String) 包含判断 (v19.133e)
                    string = string.replaceAll("(\\n)", "");
                }
                ThemeFontEntry ai3 = ai2.b(string);
                ak2.a(ai3);
                ak2.b += ai3.a(this.d);
                if ((n4 += n5) < ((String)object).length() && ((String)object).charAt(n4) == '\n') {
                    ++n4;
                }
                if (!bl2 && ak2.b < n3 - 5) continue;
                if (ak2.a.size() > 0) {
                    m2.add(ak2);
                }
                ak2 = new ak();
            }
        }
        if (ak2.a.size() > 0) {
            m2.add(ak2);
        }
        ak2 = null;
        if (m2.size() > 0) {
            ak ak3 = (ak)m2.get(m2.size() - 1);
            if (ak3.a.size() == 0) {
                m2.remove(m2.size() - 1);
            }
        }
        rect.d = rect.b + m2.size() * TextFormatter.a(paint);
        if (bl) {
            float f2 = rect.d();
            float f3 = 0.0f;
            for (Object object : m2) {
                if (!((float)((ak)object).b > f3)) continue;
                f3 = ((ak)object).b;
            }
            float f4 = f3;
            if (f4 < (float)rect.b()) {
                rect.a = (int)(f2 - f4 / 2.0f);
                rect.c = (int)(f2 + f4 / 2.0f);
            }
        }
        ThemeLayout aj2 = new ThemeLayout();  // 02b f/aj (v19.133e)
        aj2.a = m2;
        aj2.b = rect;
        aj2.c = this.d;
        aj2.d = this.e;
        return aj2;
    }
}
