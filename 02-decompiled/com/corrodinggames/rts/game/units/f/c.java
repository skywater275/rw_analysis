/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.f;

import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.f.a;
import com.corrodinggames.rts.game.units.f.b;
import com.corrodinggames.rts.game.units.f.d;
import com.corrodinggames.rts.game.units.f.e;
import com.corrodinggames.rts.game.units.f.f;
import com.corrodinggames.rts.game.units.f.g;
import com.corrodinggames.rts.game.units.f.h;
import com.corrodinggames.rts.game.units.f.i;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.u;

public final class c {
    int a;
    int b;
    float c;
    float d;
    public a[][] e = null;
    d f = new d();
    g g = new g();
    h h = new h();
    final u i = new u();
    final f j = new f();
    final Rect k = new Rect();
    final int l = 32;
    int m;

    public void a(float f2, float f3, float f4, y y2, float f5, i i2) {
        float f6 = f2 - f4;
        float f7 = f3 - f4;
        float f8 = f2 + f4;
        float f9 = f3 + f4;
        this.g.a(f6, f7, f8, f9);
        this.a(this.g.a, this.g, y2, f5, i2);
    }

    public final f a(float f2, float f3, float f4) {
        u u2 = this.i;
        u2.clear();
        this.a(f2, f3, f4, u2);
        this.j.a(u2);
        return this.j;
    }

    public final void a(float f2, float f3, float f4, u u2) {
        a[][] aArray = this.e;
        float f5 = f2 - f4;
        float f6 = f2 + f4;
        float f7 = f3 - f4;
        float f8 = f3 + f4;
        int n2 = this.a(f5);
        int n3 = this.a(f6);
        int n4 = this.b(f7);
        int n5 = this.b(f8);
        for (int j = n2; j <= n3; ++j) {
            for (int i2 = n4; i2 <= n5; ++i2) {
                b b2 = aArray[j][i2].a;
                am[] amArray = b2.a();
                int n6 = b2.b;
                for (int i3 = 0; i3 < n6; ++i3) {
                    am am2 = amArray[i3];
                    float f9 = am2.eo;
                    float f10 = am2.ep;
                    if (!(f5 <= f9) || !(f9 <= f6) || !(f7 <= f10) || !(f10 <= f8)) continue;
                    u2.a(am2);
                }
            }
        }
    }

    public final f b(float f2, float f3, float f4) {
        u u2 = this.i;
        u2.clear();
        this.b(f2, f3, f4, u2);
        this.j.a(u2);
        return this.j;
    }

    public final void b(float f2, float f3, float f4, u u2) {
        a[][] aArray = this.e;
        float f5 = f2 - f4;
        float f6 = f2 + f4;
        float f7 = f3 - f4;
        float f8 = f3 + f4;
        float f9 = 50.0f;
        int n2 = this.a(f5 - 50.0f);
        int n3 = this.a(f6 + 50.0f);
        int n4 = this.b(f7 - 50.0f);
        int n5 = this.b(f8 + 50.0f);
        for (int j = n2; j <= n3; ++j) {
            for (int i2 = n4; i2 <= n5; ++i2) {
                b b2 = aArray[j][i2].a;
                am[] amArray = b2.a();
                int n6 = b2.b;
                for (int i3 = 0; i3 < n6; ++i3) {
                    am am2 = amArray[i3];
                    float f10 = am2.eo;
                    float f11 = am2.ep;
                    float f12 = am2.cj;
                    if (!(f5 - f12 <= f10) || !(f10 <= f6 + f12) || !(f7 - f12 <= f11) || !(f11 <= f8 + f12)) continue;
                    u2.b(am2);
                }
            }
        }
    }

    public final void a(n n2, float f2, float f3, float f4, u u2) {
        a[][] aArray = this.e;
        float f5 = f2 - f4;
        float f6 = f2 + f4;
        float f7 = f3 - f4;
        float f8 = f3 + f4;
        float f9 = 50.0f;
        int n3 = this.a(f5 - 50.0f);
        int n4 = this.a(f6 + 50.0f);
        int n5 = this.b(f7 - 50.0f);
        int n6 = this.b(f8 + 50.0f);
        int n7 = n2.k;
        for (int j = n3; j <= n4; ++j) {
            for (int i2 = n5; i2 <= n6; ++i2) {
                b b2 = aArray[j][i2].b[n7];
                am[] amArray = b2.a();
                int n8 = b2.b;
                for (int i3 = 0; i3 < n8; ++i3) {
                    am am2 = amArray[i3];
                    float f10 = am2.eo;
                    float f11 = am2.ep;
                    float f12 = am2.cj;
                    if (!(f5 - f12 <= f10) || !(f10 <= f6 + f12) || !(f7 - f12 <= f11) || !(f11 <= f8 + f12)) continue;
                    u2.b(am2);
                }
            }
        }
    }

    public void a(RectF rectF, e e2, y y2, float f2, i i2) {
        a[][] aArray = this.e;
        int n2 = this.a(rectF.a);
        int n3 = this.a(rectF.c);
        int n4 = this.b(rectF.b);
        int n5 = this.b(rectF.d);
        n n6 = null;
        int n7 = i2.excludeTeam(y2);
        if (n7 != -2 && n7 != -3) {
            n6 = n.k(n7);
        }
        n n8 = i2.onlyEnemiesOfTeam(y2);
        n n9 = i2.onlyTeam(y2);
        i2.setup(y2, f2);
        if (n8 == null && n9 == null) {
            for (int i3 = n2; i3 <= n3; ++i3) {
                for (int i4 = n4; i4 <= n5; ++i4) {
                    b b2 = aArray[i3][i4].a;
                    am[] amArray = b2.a();
                    int n10 = b2.b;
                    for (int i5 = 0; i5 < n10; ++i5) {
                        am am2 = amArray[i5];
                        if (n6 != null && am2.bX == n6 || !e2.a(am2)) continue;
                        i2.callback(y2, f2, am2);
                    }
                }
            }
        } else if (n9 != null) {
            int n11 = n9.k;
            if (n11 == -1) {
                for (int i6 = n2; i6 <= n3; ++i6) {
                    for (int i7 = n4; i7 <= n5; ++i7) {
                        b b3 = aArray[i6][i7].d;
                        if (b3.b <= 0) continue;
                        am[] amArray = b3.a();
                        int n12 = b3.b;
                        for (int i8 = 0; i8 < n12; ++i8) {
                            am am3 = amArray[i8];
                            if (!e2.a(am3)) continue;
                            i2.callback(y2, f2, am3);
                        }
                    }
                }
            } else if (n11 == -2) {
                for (int i9 = n2; i9 <= n3; ++i9) {
                    for (int i10 = n4; i10 <= n5; ++i10) {
                        b b4 = aArray[i9][i10].c;
                        if (b4.b <= 0) continue;
                        am[] amArray = b4.a();
                        int n13 = b4.b;
                        for (int i11 = 0; i11 < n13; ++i11) {
                            am am4 = amArray[i11];
                            if (!e2.a(am4)) continue;
                            i2.callback(y2, f2, am4);
                        }
                    }
                }
            } else {
                for (int i12 = n2; i12 <= n3; ++i12) {
                    for (int i13 = n4; i13 <= n5; ++i13) {
                        b b5 = aArray[i12][i13].b[n11];
                        if (b5.b <= 0) continue;
                        am[] amArray = b5.a();
                        int n14 = b5.b;
                        for (int i14 = 0; i14 < n14; ++i14) {
                            am am5 = amArray[i14];
                            if (!e2.a(am5)) continue;
                            i2.callback(y2, f2, am5);
                        }
                    }
                }
            }
        } else {
            int n15;
            Object object;
            int n16;
            int n17;
            if (n8 != n.h) {
                for (n17 = n2; n17 <= n3; ++n17) {
                    for (n16 = n4; n16 <= n5; ++n16) {
                        object = aArray[n17][n16].c;
                        if (((b)object).b <= 0) continue;
                        am[] amArray = ((b)object).a();
                        int n18 = ((b)object).b;
                        for (n15 = 0; n15 < n18; ++n15) {
                            am am6 = amArray[n15];
                            if (!e2.a(am6)) continue;
                            i2.callback(y2, f2, am6);
                        }
                    }
                }
            }
            n17 = this.m;
            for (n16 = 0; n16 <= n17; ++n16) {
                object = n.k(n16);
                if (object == null || n8 == object || !n8.c((n)object)) continue;
                for (int i15 = n2; i15 <= n3; ++i15) {
                    for (n15 = n4; n15 <= n5; ++n15) {
                        b b6 = aArray[i15][n15].b[n16];
                        int n19 = b6.b;
                        if (n19 <= 0) continue;
                        am[] amArray = b6.a();
                        for (int i16 = 0; i16 < n19; ++i16) {
                            am am7 = amArray[i16];
                            if (!e2.a(am7)) continue;
                            i2.callback(y2, f2, am7);
                        }
                    }
                }
            }
        }
    }

    public final int a(float f2) {
        int n2 = (int)(f2 * this.c);
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 >= 32) {
            n2 = 31;
        }
        return n2;
    }

    public final int b(float f2) {
        int n2 = (int)(f2 * this.d);
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 >= 32) {
            n2 = 31;
        }
        return n2;
    }

    public void a() {
        float f2 = this.c;
        float f3 = this.d;
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (!am2.bV && (int)(am2.eo * f2) == am2.dl && (int)(am2.ep * f3) == am2.dm && am2.bX != null && am2.dn == am2.bX.k) continue;
            this.a(am2);
        }
    }

    public void a(am am2) {
        if (this.e == null) {
            if (com.corrodinggames.rts.gameFramework.l.B().bx != 0) {
                com.corrodinggames.rts.gameFramework.l.b("updateUnitGeoIndex: areaList not active");
            }
            am2.dl = -1;
            am2.dm = -1;
            return;
        }
        if (am2.bV) {
            if (am2.dl != -1 && am2.dm != -1) {
                this.e[am2.dl][am2.dm].b(am2);
                am2.dl = -1;
                am2.dm = -1;
            }
            return;
        }
        int n2 = this.a(am2.eo);
        int n3 = this.b(am2.ep);
        int n4 = -2;
        if (am2.bX != null) {
            n4 = am2.bX.k;
        }
        if (am2.dl == n2 && am2.dm == n3 && am2.dn == n4) {
            return;
        }
        if (am2.dl != -1 && am2.dm != -1) {
            this.e[am2.dl][am2.dm].b(am2);
        }
        am2.dl = n2;
        am2.dm = n3;
        am2.dn = n4;
        if (n4 > this.m && this.m < n.c) {
            this.m = n4;
        }
        this.e[am2.dl][am2.dm].a(am2);
    }

    public void a(com.corrodinggames.rts.game.b.b b2) {
        this.e = new a[32][32];
        this.m = 0;
        for (int i2 = 0; i2 < 32; ++i2) {
            for (int i3 = 0; i3 < 32; ++i3) {
                this.e[i2][i3] = new a();
            }
        }
        this.a = b2.C * b2.n / 32;
        this.b = b2.D * b2.o / 32;
        this.c = 1.0f / (float)this.a;
        this.d = 1.0f / (float)this.b;
    }

    public void b() {
        this.e = null;
    }

    public void c(float f2) {
    }
}
