/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.b;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.e;
import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.b.j;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h.d;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.y;
import com.corrodinggames.rts.gameFramework.w;
import java.util.Properties;

public strictfp final class g {
    public j a;
    public int b;
    public int c = -2;
    public short d = (short)-1;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public byte j;
    public boolean k;
    public boolean l;
    public g[] m;
    static final Rect n = new Rect();

    public static boolean a(g g2, g g3) {
        if (g2 == g3) {
            return true;
        }
        if (g2 == null) {
            return false;
        }
        if (g3 == null) {
            return false;
        }
        if (g2.a != g3.a) {
            return false;
        }
        return g2.b == g3.b;
    }

    public g a() {
        g g2 = new g();
        g2.a = this.a;
        g2.b = this.b;
        g2.e = this.e;
        g2.f = this.f;
        g2.g = this.g;
        g2.h = this.h;
        g2.i = this.i;
        g2.j = this.j;
        g2.k = this.k;
        g2.l = this.l;
        return g2;
    }

    public static void a(String string) {
        com.corrodinggames.rts.gameFramework.l.b(string);
        com.corrodinggames.rts.gameFramework.l.B().a("Missing unit data while loading map: " + string, 1);
        try {
            Thread.sleep(2L);
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    public static g a(b b2, e e2, j j2, int n2, short s2, short s3, boolean bl) {
        g[] gArray;
        Object object;
        Properties properties = j2.a(j2.l + n2);
        if (properties != null) {
            object = properties.getProperty("showFog");
            if (object != null) {
                int n3 = Integer.parseInt((String)object);
                l l2 = com.corrodinggames.rts.gameFramework.l.B();
                b2.a(s2, s3);
                float f2 = b2.T + b2.p;
                float f3 = b2.U + b2.q;
                l2.bL.a(f2, f3, n3, l2.bs, false);
                return null;
            }
            String string = properties.getProperty("unit");
            String string2 = properties.getProperty("customUnit");
            if (string != null || string2 != null) {
                String string3 = properties.getProperty("team");
                n n4 = null;
                if ("none".equalsIgnoreCase(string3)) {
                    n4 = com.corrodinggames.rts.game.n.k(-1);
                } else {
                    if (string3 == null) {
                        com.corrodinggames.rts.gameFramework.l.b("map", "warning: unit has no team property:" + string + " at: " + s2 + "," + s3);
                        return null;
                    }
                    n4 = com.corrodinggames.rts.game.n.k(Integer.valueOf(string3));
                    if (n4 == null) {
                        com.corrodinggames.rts.gameFramework.l.b("map", "skipping unit without player:" + string + " at: " + s2 + "," + s3 + " team:" + string3);
                        return null;
                    }
                    if (n4.b()) {
                        com.corrodinggames.rts.gameFramework.l.b("map", "Unit team is marked as spectator:" + string + " (skipping unit)");
                        return null;
                    }
                }
                am am2 = null;
                if (string2 != null) {
                    com.corrodinggames.rts.game.units.custom.l l3 = com.corrodinggames.rts.game.units.custom.l.n(string2);
                    if (l3 == null) {
                        String string4 = "Could not find custom unit of:" + string2 + " at x:" + s2 + ", y:" + s3;
                        com.corrodinggames.rts.game.b.g.a(string4);
                        throw new f(string4);
                    }
                    as as2 = com.corrodinggames.rts.game.units.custom.l.c(l3);
                    if (as2 != null) {
                        if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
                            l3 = (com.corrodinggames.rts.game.units.custom.l)as2;
                        } else {
                            com.corrodinggames.rts.gameFramework.l.b("replacement not a custom unit:" + as2.i());
                        }
                    }
                    if ((am2 = com.corrodinggames.rts.game.units.custom.l.a(false, l3)) == null) {
                        String string5 = "Metadata unit is null for:" + string2;
                        com.corrodinggames.rts.game.b.g.a(string5);
                        throw new f(string5);
                    }
                } else {
                    as as3 = ar.a(string);
                    if (as3 != null) {
                        am2 = as3.a();
                    }
                    if (am2 == null && "scoutShip".equalsIgnoreCase(string)) {
                        am2 = new d(false);
                    }
                    if (am2 == null) {
                        String string6 = "Could not find unit:" + string + " at: " + s2 + "," + s3;
                        com.corrodinggames.rts.game.b.g.a(string6);
                        throw new f(string6);
                    }
                }
                b2.a(s2, s3);
                am2.eo = (float)b2.T + am2.cZ();
                am2.ep = (float)b2.U + am2.da();
                if (n4 == null) {
                    throw new f("team has not been set for:" + string);
                }
                am2.b(n4);
                if (properties.getProperty("type") != null) {
                    am2.a_(properties.getProperty("type"));
                }
                if (properties.getProperty("randomRotate") != null) {
                    am2.cg = com.corrodinggames.rts.gameFramework.f.a(am2, -180, 180);
                }
                am2.bO = "builder".equalsIgnoreCase(string) || "builder".equalsIgnoreCase(string2);
                am2.bP = "commandCenter".equalsIgnoreCase(string) || "commandCenter".equalsIgnoreCase(string2);
                am2.bM = true;
                am2.n();
                com.corrodinggames.rts.game.n.c(am2);
                w.dL();
                return null;
            }
            if (e2 != null && e2.l.equals("units")) {
                Log.d("RustedWarfare", "non unit on units layer at:" + s2 + "," + s3);
                return null;
            }
        }
        object = new g();
        ((g)object).a = j2;
        j2.p = true;
        if (e2 != null && !e2.r) {
            j2.r = true;
        }
        if (bl) {
            j2.q = true;
        }
        ((g)object).b = n2;
        if (properties != null) {
            if (properties.getProperty("water") != null) {
                ((g)object).e = true;
            }
            if (properties.getProperty("water-bridge") != null) {
                ((g)object).f = true;
            }
            if (properties.getProperty("lava") != null || properties.getProperty("lava-cliff") != null) {
                ((g)object).g = true;
                if (properties.getProperty("lava-cliff") != null) {
                    ((g)object).h = true;
                }
            }
            if (properties.getProperty("cliff-soft") != null) {
                ((g)object).h = true;
            }
            if (properties.getProperty("cliff") != null) {
                ((g)object).h = true;
            }
            if (properties.getProperty("large-cliff") != null) {
                ((g)object).k = true;
            }
            if (properties.getProperty("trees") != null) {
                ((g)object).k = true;
            }
            if (properties.getProperty("res_pool") != null) {
                ((g)object).i = true;
            }
            if (properties.getProperty("tree") != null) {
                // empty if block
            }
            if (properties.getProperty("small-rock") != null) {
                ((g)object).j = (byte)40;
            }
            if (properties.getProperty("large-rock") != null) {
                ((g)object).j = (byte)-1;
            }
            if (properties.getProperty("block-land") != null) {
                ((g)object).j = (byte)-1;
            }
            if (properties.getProperty("block-buildings") != null) {
                ((g)object).l = true;
            }
        }
        int n5 = 0;
        int n6 = 0;
        if (((g)object).a != null && (gArray = ((g)object).a.c) != null) {
            if (((g)object).b == 0 && gArray.equals("shallowwater.png")) {
                n5 = 5;
            }
            if (((g)object).b == 0 && gArray.equals("deepwater.png")) {
                n5 = 2;
            }
            if (((g)object).b == 0 && gArray.equals("water.png")) {
                n5 = 2;
            }
            if (((g)object).b == 0 && gArray.equals("longgrass.png")) {
                n5 = 3;
            }
            if (((g)object).b == 0 && gArray.equals("mountain.png")) {
                n5 = 3;
            }
            if (((g)object).b == 7 && gArray.equals("stone.png")) {
                n5 = 4;
                n6 = 23;
            }
            if (((g)object).b != 0 || gArray.equals("lava.png")) {
                // empty if block
            }
            if (((g)object).b == 0 && gArray.equals("snow.png")) {
                n5 = 2;
            }
        }
        if (properties != null && properties.getProperty("randomTileBy") != null) {
            try {
                n5 = Integer.parseInt(properties.getProperty("randomTileBy"));
            }
            catch (NumberFormatException numberFormatException) {
                throw new f("(x:" + s2 + "y:" + s3 + ") - randomTileBy: Unexpected integer value:'" + properties.getProperty("randomTileBy") + "'");
            }
            if (properties.getProperty("randomTileFixedOffset") != null) {
                try {
                    n6 = Integer.parseInt(properties.getProperty("randomTileFixedOffset"));
                }
                catch (NumberFormatException numberFormatException) {
                    throw new f("(x:" + s2 + "y:" + s3 + ") - randomTileFixedOffset: Unexpected integer value:'" + properties.getProperty("randomTileBy") + "'");
                }
            }
        }
        if (n5 > 0) {
            gArray = new g[n5];
            for (int i2 = 0; i2 < n5; ++i2) {
                gArray[i2] = ((g)object).a();
                gArray[i2].b += i2 + 1 + n6;
            }
            ((g)object).m = gArray;
        }
        return object;
    }

    g() {
    }

    public void a(y y2, RectF rectF, float f2, Paint paint) {
        j j2 = this.a;
        Rect rect = j2.b(this.b);
        y2.a(j2.b, rect, rectF, paint);
    }

    public /* synthetic */ Object clone() {
        return this.a();
    }
}
