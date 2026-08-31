/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.a.a;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.a.a.g;
import com.corrodinggames.rts.a.a.h;
import com.corrodinggames.rts.a.a.i;
import com.corrodinggames.rts.a.a.j;
import com.corrodinggames.rts.a.a.l;
import com.corrodinggames.rts.a.a.n;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.aq;
import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.e.b;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.Random;

public class f
extends l {
    int a = 1;
    int b;
    final Rect c = new Rect();
    final PointF d = new PointF();
    static final Point e = new Point();

    public void a() {
        double d;
        int n2;
        int n3;
        int n4;
        int n5;
        com.corrodinggames.rts.gameFramework.l.e("Misc Performance test");
        int n6 = 5;
        int n7 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== applyDigitGroupingStyle tests (runs:" + n6 + ")");
        Long l2 = br.a();
        for (int i2 = 0; i2 < n6; ++i2) {
            for (int i3 = 0; i3 < 100; ++i3) {
                if (com.corrodinggames.rts.game.units.custom.e.a.a(i3 + "9870000001.67", com.corrodinggames.rts.game.units.custom.e.b.c).equals("")) continue;
                ++n7;
            }
        }
        Long l3 = br.a();
        double d2 = br.a(l2, (long)l3);
        this.a += n7;
        com.corrodinggames.rts.gameFramework.l.e("Took: " + d2);
        n6 = 5;
        n7 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== applyDigitGroupingStyle_systemLibraryVersion tests (runs:" + n6 + ")");
        l2 = br.a();
        for (int i4 = 0; i4 < n6; ++i4) {
            for (int i5 = 0; i5 < 100; ++i5) {
                if (com.corrodinggames.rts.game.units.custom.e.a.a((long)i5 + 9870000001L, com.corrodinggames.rts.game.units.custom.e.b.c).equals("")) continue;
                ++n7;
            }
        }
        Long l4 = br.a();
        double d3 = br.a(l2, (long)l4);
        this.a += n7;
        com.corrodinggames.rts.gameFramework.l.e("Took: " + d3);
        n6 = 3;
        n7 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== isLineClear tests (runs:" + n6 + ")");
        l2 = br.a();
        for (int i6 = 0; i6 < n6; ++i6) {
            for (int i7 = 0; i7 < 100; ++i7) {
                n5 = 1000 - i7;
                int n8 = 50;
                int n9 = 50;
                int n10 = 1000;
                int n11 = 1;
                int n12 = 1;
                if (!aq.b(ao.b, i7, n5, n8, n9, n10, n11, n12)) continue;
                ++n7;
            }
        }
        Long l5 = br.a();
        double d4 = br.a(l2, (long)l5);
        this.a += n7;
        com.corrodinggames.rts.gameFramework.l.e("Took: " + d4);
        n6 = 3;
        n7 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== maths tests == (runs:" + n6 + ")");
        l2 = br.a();
        int n13 = 0;
        for (int i8 = 0; i8 < n6; ++i8) {
            for (n5 = 0; n5 < 1000; ++n5) {
                Point point = e;
                point.a += n5;
                point.a += n5;
                point.a += n5;
                point.a += n5;
                point.a += n5;
                point.a += n5;
                point.a += n5;
                point.a += n5;
                point.a += n5;
                ++this.b;
                n7 += n13;
            }
        }
        Long l6 = br.a();
        double d5 = br.a(l2, (long)l6);
        this.a += n7;
        com.corrodinggames.rts.gameFramework.l.e("Took: " + d5);
        n6 = 14;
        n7 = 5;
        int n14 = 0;
        m m2 = new m();
        int n15 = 0;
        for (int i9 = 0; i9 < 20000; ++i9) {
            com.corrodinggames.rts.game.units.custom.i i10 = new com.corrodinggames.rts.game.units.custom.i();
            if (i9 % 10 != 0) {
                i10.a(com.corrodinggames.rts.game.units.custom.g.c("test"));
                i10.a(com.corrodinggames.rts.game.units.custom.g.c("test1"));
            }
            if (i9 % 2 == 0) {
                i10.a(com.corrodinggames.rts.game.units.custom.g.c("test2"));
                ++n15;
            }
            if (i9 % 3 == 0) {
                i10.a(com.corrodinggames.rts.game.units.custom.g.c("test3"));
            }
            if (i9 % 4 == 0) {
                i10.a(com.corrodinggames.rts.game.units.custom.g.c("test4"));
            }
            if (i9 % 5 == 0) {
                m2.add((Object)null);
            }
            m2.add(i10.a());
        }
        com.corrodinggames.rts.game.units.custom.h h2 = com.corrodinggames.rts.game.units.custom.g.a("test2");
        com.corrodinggames.rts.gameFramework.l.e("=== CustomTagList tests == (runs:" + n7 + ")");
        for (n4 = 0; n4 < n6; ++n4) {
            Long l7 = br.a();
            for (int i11 = 0; i11 < n7; ++i11) {
                int n16 = 0;
                for (com.corrodinggames.rts.game.units.custom.h h3 : m2) {
                    if (!com.corrodinggames.rts.game.units.custom.g.a(h2, h3)) continue;
                    ++n16;
                }
                n.a(n15, n16);
            }
            com.corrodinggames.rts.gameFramework.l.e("test2Expected:" + n15);
            Long l8 = br.a();
            double d6 = br.a(l7, (long)l8);
            this.a += n14;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + d6);
        }
        n6 = 5000000;
        float f2 = 0.5f;
        for (n14 = 0; n14 < 2; ++n14) {
            h h4;
            h[] hArray;
            Random random;
            int n17 = 5;
            n15 = 5;
            int n18 = 0;
            com.corrodinggames.rts.gameFramework.l.e("=== [Write]/comparison tests == (runs:" + n15 + ")");
            for (n4 = 0; n4 < n17; ++n4) {
                random = new Random();
                hArray = new h[n6];
                for (int i12 = 0; i12 < hArray.length; ++i12) {
                    hArray[i12] = new h();
                    hArray[i12].d = random.nextFloat() < f2;
                }
                Long l9 = br.a();
                for (int i13 = 0; i13 < n15; ++i13) {
                    for (int i14 = 0; i14 < hArray.length; ++i14) {
                        h4 = hArray[i14];
                        h4.d = false;
                    }
                }
                Long l10 = br.a();
                double d7 = br.a(l9, (long)l10);
                this.a += n18;
                com.corrodinggames.rts.gameFramework.l.e("Took: " + d7);
            }
            n17 = 5;
            n15 = 5;
            n18 = 0;
            com.corrodinggames.rts.gameFramework.l.e("=== Write/[comparison] tests == (runs:" + n15 + ")");
            for (n4 = 0; n4 < n17; ++n4) {
                random = new Random();
                hArray = new h[n6];
                for (int i15 = 0; i15 < hArray.length; ++i15) {
                    hArray[i15] = new h();
                    hArray[i15].d = random.nextFloat() < f2;
                }
                Long l11 = br.a();
                for (int i16 = 0; i16 < n15; ++i16) {
                    for (int i17 = 0; i17 < hArray.length; ++i17) {
                        h4 = hArray[i17];
                        if (!h4.d) continue;
                        h4.d = false;
                    }
                }
                Long l12 = br.a();
                double d8 = br.a(l11, (long)l12);
                this.a += n18;
                com.corrodinggames.rts.gameFramework.l.e("Took: " + d8);
            }
        }
        float f3 = 0.3f;
        int n19 = 7;
        n15 = 5;
        int n20 = 0;
        n4 = 1000;
        com.corrodinggames.rts.gameFramework.l.e("=== [Virtual method]/if tests == (runs:" + n15 + ")");
        for (n3 = 0; n3 < n19; ++n3) {
            Random random = new Random();
            i[] iArray = new i[n4];
            for (int i18 = 0; i18 < iArray.length; ++i18) {
                boolean bl;
                boolean bl2 = bl = random.nextFloat() < f3;
                if (bl) {
                    j j2 = new j(this);
                    j2.c = random.nextInt(1000);
                    iArray[i18] = j2;
                    continue;
                }
                iArray[i18] = new i(this);
                iArray[i18].a = random.nextInt(1000);
            }
            Long l13 = br.a();
            for (int i19 = 0; i19 < n15; ++i19) {
                for (int i20 = 0; i20 < iArray.length; ++i20) {
                    if (iArray[i20].a() != 0) continue;
                    ++n20;
                }
            }
            Long l14 = br.a();
            double d9 = br.a(l13, (long)l14);
            this.a += n20;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + d9);
        }
        n19 = 7;
        n15 = 5;
        n20 = 0;
        n4 = 1000;
        com.corrodinggames.rts.gameFramework.l.e("=== Virtual method/[if tests] == (runs:" + n15 + ")");
        for (n3 = 0; n3 < n19; ++n3) {
            Random random = new Random();
            g[] gArray = new g[n4];
            for (int i21 = 0; i21 < gArray.length; ++i21) {
                boolean bl = random.nextFloat() < f3;
                g g2 = new g(this);
                g2.b = random.nextInt(1000);
                g2.a = random.nextInt(1000);
                g2.c = bl;
                gArray[i21] = g2;
            }
            Long l15 = br.a();
            for (int i22 = 0; i22 < n15; ++i22) {
                for (int i23 = 0; i23 < gArray.length; ++i23) {
                    if (gArray[i23].a() != 0) continue;
                    ++n20;
                }
            }
            Long l16 = br.a();
            double d10 = br.a(l15, (long)l16);
            this.a += n20;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + d10);
        }
        n19 = 14;
        n15 = 10;
        n20 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== comparison tests 1 == (runs:" + n15 + ")");
        for (n4 = 0; n4 < n19; ++n4) {
            Random random = new Random();
            int n21 = 600;
            int n22 = 600;
            float[] fArray = new float[n21 * n21];
            for (int i24 = 0; i24 < n21; ++i24) {
                for (int i25 = 0; i25 < n22; ++i25) {
                    fArray[i24 * n22 + i25] = random.nextFloat();
                }
            }
            Long l17 = br.a();
            for (int i26 = 0; i26 < n15; ++i26) {
                for (int i27 = 0; i27 < n21; ++i27) {
                    for (n2 = 0; n2 < n22; ++n2) {
                        int n23 = i27 * n22 + n2;
                        n20 = (int)((float)n20 + fArray[n23]);
                    }
                }
            }
            Long l18 = br.a();
            d = br.a(l17, (long)l18);
            this.a += n20;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + d);
        }
        n19 = 14;
        n15 = 10;
        n20 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== comparison tests 2 == (runs:" + n15 + ")");
        for (n4 = 0; n4 < n19; ++n4) {
            Random random = new Random();
            int n24 = 600;
            int n25 = 600;
            float[][] fArray = new float[n24][n24];
            for (int i28 = 0; i28 < n24; ++i28) {
                for (int i29 = 0; i29 < n25; ++i29) {
                    fArray[i28][i29] = random.nextFloat();
                }
            }
            Long l19 = br.a();
            for (int i30 = 0; i30 < n15; ++i30) {
                for (int i31 = 0; i31 < n24; ++i31) {
                    for (n2 = 0; n2 < n25; ++n2) {
                        n20 = (int)((float)n20 + fArray[i31][n2]);
                    }
                }
            }
            Long l20 = br.a();
            d = br.a(l19, (long)l20);
            this.a += n20;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + d);
        }
        n19 = 5;
        n15 = 5;
        n20 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== [divide]/multiply float tests == (runs:" + n15 + ")");
        for (n4 = 0; n4 < n19; ++n4) {
            Random random = new Random();
            float[] fArray = new float[n6];
            float[] fArray2 = new float[n6];
            for (int i32 = 0; i32 < fArray.length; ++i32) {
                fArray[i32] = random.nextFloat();
                fArray2[i32] = random.nextFloat();
            }
            Long l21 = br.a();
            for (int i33 = 0; i33 < n15; ++i33) {
                for (int i34 = 0; i34 < fArray.length; ++i34) {
                    if (fArray[i34] / fArray2[i34] != 0.0f) continue;
                    ++n20;
                }
            }
            Long l22 = br.a();
            double d11 = br.a(l21, (long)l22);
            this.a += n20;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + d11);
        }
        n19 = 5;
        n15 = 5;
        n20 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== divide/[multiply] float tests == (runs:" + n15 + ")");
        for (n4 = 0; n4 < n19; ++n4) {
            Random random = new Random();
            float[] fArray = new float[n6];
            float[] fArray3 = new float[n6];
            for (int i35 = 0; i35 < fArray.length; ++i35) {
                fArray[i35] = random.nextFloat();
                fArray3[i35] = random.nextFloat();
            }
            Long l23 = br.a();
            for (int i36 = 0; i36 < n15; ++i36) {
                for (int i37 = 0; i37 < fArray.length; ++i37) {
                    if (fArray[i37] * fArray3[i37] != 0.0f) continue;
                    ++n20;
                }
            }
            Long l24 = br.a();
            double d12 = br.a(l23, (long)l24);
            this.a += n20;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + d12);
        }
        n19 = 5;
        n15 = 5;
        n20 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== [divide]/multiply int tests == (runs:" + n15 + ")");
        for (n4 = 0; n4 < n19; ++n4) {
            Random random = new Random();
            int[] nArray = new int[n6];
            int[] nArray2 = new int[n6];
            for (int i38 = 0; i38 < nArray.length; ++i38) {
                nArray[i38] = random.nextInt();
                nArray2[i38] = random.nextInt();
            }
            Long l25 = br.a();
            for (int i39 = 0; i39 < n15; ++i39) {
                for (int i40 = 0; i40 < nArray.length; ++i40) {
                    if (nArray[i40] / nArray2[i40] != 0) continue;
                    ++n20;
                }
            }
            Long l26 = br.a();
            double d13 = br.a(l25, (long)l26);
            this.a += n20;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + d13);
        }
        n19 = 5;
        n15 = 5;
        n20 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== [float cast and divide]/multiply int tests == (runs:" + n15 + ")");
        for (n4 = 0; n4 < n19; ++n4) {
            Random random = new Random();
            int[] nArray = new int[n6];
            int[] nArray3 = new int[n6];
            for (int i41 = 0; i41 < nArray.length; ++i41) {
                nArray[i41] = random.nextInt();
                nArray3[i41] = random.nextInt();
            }
            Long l27 = br.a();
            for (int i42 = 0; i42 < n15; ++i42) {
                for (int i43 = 0; i43 < nArray.length; ++i43) {
                    if ((float)nArray[i43] / (float)nArray3[i43] != 0.0f) continue;
                    ++n20;
                }
            }
            Long l28 = br.a();
            double d14 = br.a(l27, (long)l28);
            this.a += n20;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + d14);
        }
        n19 = 5;
        n15 = 5;
        n20 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== [mixed float and divide]/multiply int tests == (runs:" + n15 + ")");
        for (n4 = 0; n4 < n19; ++n4) {
            Random random = new Random();
            int[] nArray = new int[n6];
            float[] fArray = new float[n6];
            for (int i44 = 0; i44 < nArray.length; ++i44) {
                nArray[i44] = random.nextInt();
                fArray[i44] = random.nextFloat();
            }
            Long l29 = br.a();
            for (int i45 = 0; i45 < n15; ++i45) {
                for (int i46 = 0; i46 < nArray.length; ++i46) {
                    if ((float)nArray[i46] / fArray[i46] != 0.0f) continue;
                    ++n20;
                }
            }
            Long l30 = br.a();
            double d15 = br.a(l29, (long)l30);
            this.a += n20;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + d15);
        }
        n19 = 5;
        n15 = 5;
        n20 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== divide/[multiply] int tests == (runs:" + n15 + ")");
        for (n4 = 0; n4 < n19; ++n4) {
            Random random = new Random();
            int[] nArray = new int[n6];
            int[] nArray4 = new int[n6];
            for (int i47 = 0; i47 < nArray.length; ++i47) {
                nArray[i47] = random.nextInt();
                nArray4[i47] = random.nextInt();
            }
            Long l31 = br.a();
            for (int i48 = 0; i48 < n15; ++i48) {
                for (int i49 = 0; i49 < nArray.length; ++i49) {
                    if (nArray[i49] * nArray4[i49] != 0) continue;
                    ++n20;
                }
            }
            Long l32 = br.a();
            double d16 = br.a(l31, (long)l32);
            this.a += n20;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + d16);
        }
    }
}
