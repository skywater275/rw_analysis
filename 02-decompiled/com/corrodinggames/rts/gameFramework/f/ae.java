/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.f.af;
import com.corrodinggames.rts.gameFramework.f.ag;
import com.corrodinggames.rts.gameFramework.f.ah;
import com.corrodinggames.rts.gameFramework.f.ai;
import com.corrodinggames.rts.gameFramework.f.aj;
import com.corrodinggames.rts.gameFramework.f.ak;
import com.corrodinggames.rts.gameFramework.f.d;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.m;

public class ae {
    static com.corrodinggames.rts.gameFramework.m.ag a = new com.corrodinggames.rts.gameFramework.m.ag();
    static com.corrodinggames.rts.gameFramework.m.ag b = new com.corrodinggames.rts.gameFramework.m.ag();
    static com.corrodinggames.rts.gameFramework.m.ag c = new com.corrodinggames.rts.gameFramework.m.ag();
    public Paint d = a;
    public Paint e = a;
    static Paint f = new Paint();
    public Paint g = this.d;
    static Paint h;
    m i = new m();

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
        for (af af2 : this.i) {
            if (!(af2 instanceof ai)) continue;
            stringBuilder.append(((ai)af2).d);
        }
        return stringBuilder.toString();
    }

    public void a(String string) {
        int n;
        af af2;
        if (this.i.size() > 0 && (af2 = (af)this.i.get(n = this.i.size() - 1)) instanceof ai) {
            ai ai2 = (ai)af2;
            String string2 = com.corrodinggames.rts.gameFramework.f.a(ai2.d, string);
            if (!ai2.d.equals(string2)) {
                this.i.set(n, ai2.b(string2));
            }
        }
    }

    public void b() {
        this.i.clear();
    }

    public void a(af af2) {
        this.i.add(af2);
    }

    public void b(String string) {
        if (this.g != null && this.g != this.d) {
            this.a(string, this.g);
            return;
        }
        this.a(new ai(this, string));
    }

    public void a(String string, Paint paint) {
        this.a(new ag(this, string, paint));
    }

    public void a(String string, int n) {
        if (this.g != null && this.g != this.d) {
            this.a(new ag(this, string, this.g, n));
            return;
        }
        this.a(new ag(this, string, null, n));
    }

    public void a(String string, int n, boolean bl) {
        Paint paint = this.d;
        if (bl) {
            paint = this.e;
        }
        this.a(new ag(this, string, paint, n));
    }

    public void a(e e2, int n, int n2) {
        ah ah2 = new ah(this);
        ah2.a = e2;
        float f2 = com.corrodinggames.rts.gameFramework.f.d.a(e2, n, n2);
        ah2.c = (int)((float)e2.p * f2);
        ah2.d = (int)((float)e2.q * f2);
        ah2.b = f2;
        this.i.add(ah2);
    }

    public int c() {
        l l2 = l.B();
        return l2.bO.a("A", this.g);
    }

    public aj a(int n2, boolean bl) {
        l l2 = l.B();
        Rect rect = new Rect(-n2 / 2, 0, n2 / 2, 10);
        m m2 = new m();
        ak ak2 = new ak();
        Paint paint = this.d;
        int n3 = n2 - 5;
        block0: for (af af2 : this.i) {
            if (ak2.b >= n3 - 5) {
                if (ak2.a.size() > 0) {
                    m2.add(ak2);
                }
                ak2 = new ak();
            }
            if (!(af2 instanceof ai)) {
                ak2.a(af2);
                ak2.b += af2.a(this.d);
                continue;
            }
            ai ai2 = (ai)af2;
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
                if (com.corrodinggames.rts.gameFramework.f.c(string, "\\n")) {
                    string = string.replaceAll("(\\n)", "");
                }
                ai ai3 = ai2.b(string);
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
        rect.d = rect.b + m2.size() * com.corrodinggames.rts.gameFramework.f.d.a(paint);
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
        aj aj2 = new aj();
        aj2.a = m2;
        aj2.b = rect;
        aj2.c = this.d;
        aj2.d = this.e;
        return aj2;
    }

    static {
        c.a(true);
        h = new Paint();
        h.b(-65536);
        h.a(Paint$Style.b);
    }
}
