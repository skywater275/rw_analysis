/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.a;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.b.n;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.ArrayList;

public class e
extends a {
    public bp a;
    public ArrayList b;
    public int c;
    public boolean d;
    public boolean e;
    public boolean f;

    public static void a(l l2, ab ab2, String string, String string2, d d2, String string3, boolean bl2) {
        bp bp2 = bp.a(l2, ab2, string, string2 + "attachments_addNewUnits");
        int n2 = ab2.b(string, string2 + "attachments_deleteNumUnits", 0);
        boolean bl3 = ab2.a(string, string2 + "attachments_disconnect", (Boolean)false);
        boolean bl4 = ab2.a(string, string2 + "attachments_unload", (Boolean)false);
        boolean bl5 = ab2.a(string, string2 + "disconnectFromParent", (Boolean)false);
        if (!bp2.b() || n2 != 0 || bl5 || bl3 || bl4) {
            e e2 = new e();
            e2.a = bp2;
            String string4 = ab2.b(string, "attachments_onlyOnSlots", (String)null);
            if (string4 != null) {
                String[] stringArray;
                for (String string5 : stringArray = string4.split(",")) {
                    if ((string5 = string5.trim()).equals("")) continue;
                    n n3 = l2.i(string5);
                    if (e2.b == null) {
                        e2.b = new ArrayList();
                    }
                    if (n3 == null) {
                        throw new bo("[" + string + "]attachments_onlyOnSlots: Could not find attachment slot with name: " + string5);
                    }
                    e2.b.add(n3);
                }
            }
            e2.c = n2;
            e2.f = bl5;
            e2.d = bl3;
            e2.e = bl4;
            d2.ac.add(e2);
        }
    }

    @Override
    public boolean a(j j2, s s2, PointF pointF, am am2, int n2) {
        int n3;
        if ((this.d || this.e) && j2.C != null && j2.C.size() > 0) {
            for (n3 = j2.C.size() - 1; n3 >= 0; --n3) {
                am am3 = (am)j2.C.get(n3);
                if (am3 == null) continue;
                if (this.b != null) {
                    boolean bl2 = false;
                    for (Object object : this.b) {
                        if (((n)object).a() != n3) continue;
                        bl2 = true;
                        break;
                    }
                    if (!bl2) continue;
                }
                if (!(am3 instanceof y)) {
                    com.corrodinggames.rts.gameFramework.l.e("Failed to deattach unit:" + am3.r().i() + " is not an OrderableUnit");
                    continue;
                }
                y y2 = (y)am3;
                if (this.e) {
                    boolean bl3 = j2.B.size() % 2 == 0;
                    j2.a((am)y2, true, bl3);
                    break;
                }
                y2.bx();
                break;
            }
        }
        if (this.c != 0) {
            block2: for (n3 = 0; n3 < this.c; ++n3) {
                if (j2.C == null || j2.C.size() <= 0) continue;
                for (int i2 = j2.C.size() - 1; i2 >= 0; --i2) {
                    am am4 = (am)j2.C.get(i2);
                    if (am4 == null) continue;
                    if (this.b != null) {
                        boolean bl4 = false;
                        for (Object object : this.b) {
                            if (((n)object).a() != i2) continue;
                            bl4 = true;
                            break;
                        }
                        if (!bl4) continue;
                    }
                    am4.ci();
                    continue block2;
                }
            }
        }
        if (this.a != null) {
            m m2 = new m();
            this.a.a(m2, j2.bX, j2, true);
            for (am am5 : m2) {
                Object object;
                boolean bl5 = false;
                if (!(am5 instanceof y)) {
                    com.corrodinggames.rts.gameFramework.l.e("Failed to attach unit:" + am5.r().i() + " is not an OrderableUnit");
                    continue;
                }
                object = (y)am5;
                if (this.b != null) {
                    for (n n4 : this.b) {
                        if (j2.a(n4) != null || !j2.a((y)object, n4)) continue;
                        ((y)object).cQ = -9999;
                        bl5 = true;
                        break;
                    }
                } else {
                    for (n n4 : j2.x.aA) {
                        if (j2.a(n4) != null || !j2.a((y)object, n4)) continue;
                        ((y)object).cQ = -9999;
                        bl5 = true;
                        break;
                    }
                }
                if (bl5) continue;
                ((y)object).a();
            }
        }
        if (this.f) {
            if (j2.cO != null) {
                j2.bx();
            }
            if (j2.cN != null) {
                if (j2.cN instanceof ak) {
                    ((ak)((Object)j2.cN)).e(j2);
                } else {
                    com.corrodinggames.rts.gameFramework.l.g("transportedBy is not a TransportInterface");
                    j2.cN = null;
                }
            }
        }
        return true;
    }
}
