/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.b.i;
import com.corrodinggames.rts.game.units.custom.ba;
import com.corrodinggames.rts.game.units.custom.c;
import com.corrodinggames.rts.game.units.custom.d;
import com.corrodinggames.rts.game.units.custom.f;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.m;

public strictfp final class e {
    public f a;
    float b;
    float c;
    float d = 1.0f;
    boolean e = false;
    boolean f = false;
    boolean g;
    boolean h;
    boolean i;
    int j;
    float k = 0.0f;
    float l = 0.05f;
    j m;
    float[] n;

    public e(j j2) {
        this.m = j2;
    }

    public void a(f f2, int n) {
        this.a(f2, n, false);
    }

    public void a(f f2, int n2, boolean bl) {
        if (f2 == null || !f2.a()) {
            return;
        }
        if ((this.i || this.f && this.e) && n2 <= this.j && (!bl || f2 != this.a)) {
            return;
        }
        this.i = true;
        if (f2 != this.a || bl || this.g) {
            float f3 = 0.0f;
            if (this.a != null && this.e) {
                f3 = this.a.i;
            }
            this.a = f2;
            this.j = n2;
            this.c();
            this.f = bl;
            this.h = !bl;
            this.b = -1.0f;
            this.c = -1.0f;
            this.d = 1.0f;
            this.g = false;
            float f4 = f2.h;
            if (f3 > f4) {
                f4 = f3;
            }
            if (f4 > 0.0f) {
                this.k = 1.0f;
                this.l = f4;
            } else {
                this.k = 0.0f;
            }
        }
        this.e = true;
    }

    public void a() {
        if (this.a != null) {
            boolean bl = true;
            this.b(bl);
        }
        this.e = false;
        this.a = null;
        this.j = -1;
    }

    public void b() {
        if (this.a != null) {
            float f2;
            if (!this.g && (f2 = this.a.i) > 0.0f) {
                this.g = true;
                this.c();
                this.h = false;
                this.j = -1;
                this.k = 1.0f;
                this.l = f2;
                return;
            }
            boolean bl = true;
            this.b(bl);
        }
        this.e = false;
        this.a = null;
        this.j = -1;
    }

    public void a(float f2) {
        if (!this.e) {
            return;
        }
        this.c = this.b;
        if (this.b < 0.0f) {
            this.b = 0.0f;
        }
        float f3 = this.d;
        if (this.a != null && this.a.j != null) {
            f3 *= this.a.j.readNumber(this.m);
        }
        this.b += f3 * f2;
        if (this.h && !this.i) {
            this.b();
        }
        this.i = false;
        if (this.e) {
            if (this.k > 0.0f) {
                this.k -= this.l * f2;
            } else if (this.g) {
                this.b();
                return;
            }
            if (!this.g && this.a != null) {
                if (this.a.g) {
                    if (this.b > this.a.n) {
                        this.a(false);
                        this.b = this.a.n;
                        this.d = -1.0f;
                    } else if (this.b < 0.0f) {
                        this.b = 0.0f;
                        this.d = 1.0f;
                        if (this.f) {
                            this.b();
                            if (!this.g) {
                                return;
                            }
                        }
                    }
                } else {
                    if (this.b > this.a.n) {
                        if (this.f) {
                            this.a(false);
                            this.b();
                            if (!this.g) {
                                return;
                            }
                        } else {
                            this.a(false);
                            this.b = 0.0f;
                            this.d = 1.0f;
                        }
                    }
                    if (this.b < 0.0f && !this.f && f3 < 0.0f) {
                        this.b = this.a.n;
                    }
                }
            }
            boolean bl = false;
            if (this.g) {
                bl = true;
            }
            this.b(bl);
        }
    }

    void c() {
        m m2 = this.a.l;
        if (this.n == null || this.n.length < m2.size()) {
            this.n = new float[m2.size()];
        }
        for (int i2 = 0; i2 < m2.size(); ++i2) {
            i i3;
            c c2 = (c)m2.get(i2);
            d d2 = c2.a;
            if (d2 == com.corrodinggames.rts.game.units.custom.d.b) {
                this.n[i2] = this.m.c;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.a) {
                this.n[i2] = -99.0f;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.c) {
                if (this.m.dT != null && c2.b < this.m.dT.length) {
                    i3 = this.m.dT[c2.b];
                    this.n[i2] = i3.p;
                    continue;
                }
                this.n[i2] = 0.0f;
                com.corrodinggames.rts.gameFramework.l.b("setBaseBlendValues: Target leg out of range for: " + this.m.dt().i());
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.d) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                i3 = this.m.dT[c2.b];
                this.n[i2] = i3.q;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.e) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                this.n[i2] = this.m.dT[c2.b].r = com.corrodinggames.rts.gameFramework.f.a(this.m.dT[c2.b].r, false);
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.f) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                this.n[i2] = this.m.dT[c2.b].d;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.j) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                this.n[i2] = this.m.dT[c2.b].s;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.i) continue;
            this.n[i2] = 0.0f;
            com.corrodinggames.rts.gameFramework.l.b("Unsupported blend type:" + (Object)((Object)d2));
        }
    }

    void a(boolean bl) {
        m m2 = this.a.l;
        for (int i2 = 0; i2 < m2.size(); ++i2) {
            c c2 = (c)m2.get(i2);
            d d2 = c2.a;
            if (d2 != com.corrodinggames.rts.game.units.custom.d.i) continue;
            c2.a(this.m, this.c, this.b, bl);
        }
    }

    void b(boolean bl2) {
        m m2 = this.a.l;
        for (int i2 = 0; i2 < m2.size(); ++i2) {
            Object object;
            float f2;
            c c2 = (c)m2.get(i2);
            d d2 = c2.a;
            if (d2 == com.corrodinggames.rts.game.units.custom.d.a && !this.m.el && !bl2) continue;
            if (bl2) {
                f2 = 0.0f;
                if (d2 == com.corrodinggames.rts.game.units.custom.d.b) {
                    f2 = 1.0f;
                } else if (d2 == com.corrodinggames.rts.game.units.custom.d.a) {
                    f2 = this.m.x.Y;
                } else if (d2 == com.corrodinggames.rts.game.units.custom.d.j) {
                    f2 = 1.0f;
                    object = this.m.x.ax;
                    if (object != null && c2.b < ((ba[])object).length) {
                        f2 = object[c2.b].r;
                    }
                }
            } else {
                f2 = c2.b(this.b);
            }
            if (this.k > 0.0f && d2 != com.corrodinggames.rts.game.units.custom.d.a) {
                f2 = f2 * (1.0f - this.k) + this.n[i2] * this.k;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.a) {
                this.m.a = (int)f2;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.b) {
                this.m.c = f2;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.c) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                object = this.m.dT[c2.b];
                object.p = f2;
                object.k = true;
                object.o = true;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.d) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                object = this.m.dT[c2.b];
                object.q = f2;
                object.k = true;
                object.o = true;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.e) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                this.m.dT[c2.b].r = f2;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.f) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                this.m.dT[c2.b].d = f2;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.j) {
                object = this.m.dT;
                if (object == null || c2.b >= ((Object[])object).length) continue;
                ((i)object[c2.b]).s = f2;
                continue;
            }
            if (d2 == com.corrodinggames.rts.game.units.custom.d.g || d2 != com.corrodinggames.rts.game.units.custom.d.i) continue;
            c2.a(this.m, this.c, this.b, bl2);
        }
    }

    public boolean a(f f2) {
        return this.e && this.a == f2;
    }
}
