/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.n;

import android.graphics.PointF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.f.r;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.n.a;
import com.corrodinggames.rts.gameFramework.n.f;
import com.corrodinggames.rts.gameFramework.utility.m;

public class d {
    public static void a(f f2, a a2) {
        Object object;
        Object object2;
        Object object32;
        boolean bl;
        Object object42;
        Object object5;
        String string;
        l l2 = l.B();
        boolean bl2 = false;
        if (!a2.j) {
            bl2 = true;
        }
        f2.a(a2);
        a2.i = true;
        a2.j = true;
        a2.k = l2.by;
        boolean bl3 = false;
        if (a2.A != null) {
            string = a2.A.b();
            object5 = l2.bS.h.a(null, string);
            if (object5 != null) {
                int n2;
                String string2 = "globalMessage_delayPerChar";
                object42 = a2.b(string2);
                if (object42 != null) {
                    if (((String)object42).equals("slow")) {
                        ((com.corrodinggames.rts.gameFramework.f.n)object5).e = 18;
                    } else {
                        n2 = a2.b(string2, -1);
                        if (n2 != -1) {
                            ((com.corrodinggames.rts.gameFramework.f.n)object5).e = n2;
                        }
                    }
                }
                if ((n2 = a2.c("globalMessage_textColor", -1)) != -1) {
                    ((com.corrodinggames.rts.gameFramework.f.n)object5).f = n2;
                }
            }
            bl3 = true;
        }
        if ((string = a2.b("debugMessage")) != null) {
            a2.h("Debug: " + string);
            if (l2.bv && l2.bl) {
                object5 = "Debug: " + string;
                ad.a(null, (String)object5);
            }
            bl3 = true;
        }
        if (bl = a2.a("showOnMap", false)) {
            l2.bW.a(a2.b(), a2.c(), r.d);
            bl3 = true;
        }
        if (a2.f.a > 0) {
            for (Object object42 : a2.f) {
                if (!((com.corrodinggames.rts.gameFramework.n.a.a)object42).c(a2)) continue;
                bl3 = true;
            }
        }
        if (a2.g == com.corrodinggames.rts.gameFramework.n.e.a) {
            if (bl2) {
                a2.h("objective met");
            }
            bl3 = true;
        }
        if (a2.g == com.corrodinggames.rts.gameFramework.n.e.k) {
            bl3 = true;
        }
        if (a2.g == com.corrodinggames.rts.gameFramework.n.e.i) {
            bl3 = true;
        }
        if (a2.g == com.corrodinggames.rts.gameFramework.n.e.j) {
            bl3 = true;
        }
        if (a2.g == com.corrodinggames.rts.gameFramework.n.e.g) {
            bl3 = true;
        }
        if (a2.g == com.corrodinggames.rts.gameFramework.n.e.h) {
            bl3 = true;
            float f3 = a2.b();
            float f4 = a2.c();
            l2.b(f3, f4);
        }
        if (a2.g == com.corrodinggames.rts.gameFramework.n.e.e) {
            float f5 = a2.b();
            float f6 = a2.c();
            float f7 = 0.0f;
            float f8 = 0.0f;
            object32 = a2.a();
            object2 = null;
            boolean bl4 = false;
            object = null;
            boolean bl5 = false;
            if (object32 == null) {
                a2.g("No team set, cannot spawn");
            } else if (a2.v != null) {
                a2.v.a(f5, f6, f7, f8, (n)object32, bl4, (am)object2, (m)object, bl5);
            } else {
                a2.g("No valid unit list to spawn");
            }
            bl3 = true;
        }
        if (a2.g == com.corrodinggames.rts.gameFramework.n.e.c) {
            Integer n3;
            n n4 = a2.a();
            if (n4 == null) {
                a2.g("Team not set for changeCredits");
                return;
            }
            object42 = a2.d("set");
            if (object42 != null) {
                n4.o = ((Integer)object42).intValue();
            }
            if ((n3 = a2.d("add")) != null) {
                n4.d(n3.intValue());
            }
            bl3 = true;
            return;
        }
        if (a2.g == com.corrodinggames.rts.gameFramework.n.e.d) {
            String string3;
            n n5 = a2.a();
            if (n5 == null) {
                a2.g("Team not set for event_teamTags");
                return;
            }
            object42 = a2.a("addTeamTags", (String)null);
            if (object42 != null) {
                h h2 = g.a((String)object42);
                n5.b(h2);
            }
            if ((string3 = a2.a("removeTeamTags", (String)null)) != null) {
                h h3 = g.a(string3);
                n5.c(h3);
            }
            bl3 = true;
            return;
        }
        if (a2.g == com.corrodinggames.rts.gameFramework.n.e.b) {
            String string4 = a2.b("target");
            if (string4 == null) {
                f.i("Move trigger has no target id:" + a2.a);
                return;
            }
            object42 = f2.f(string4);
            if (object42 == null) {
                f.i("Move trigger: Cannot find target for:" + a2.a + " target:" + string4);
                return;
            }
            n n6 = a2.a();
            if (n6 == null) {
                f.i("Team not set map trigger:" + a2.a);
                return;
            }
            int n7 = 0;
            object32 = l2.cf.b(n6);
            for (am am2 : am.bE) {
                if (am2.bX != n6 || !(am2 instanceof y) || !a2.a(am2) || !a2.b(am2)) continue;
                object = (y)am2;
                ((e)object32).a((y)object);
                ++n7;
            }
            ((e)object32).a(((PointF)object42).a, ((PointF)object42).b);
            if (bl2) {
                f2.b("firstActivation: move at:" + l2.by + " for teamId:" + n6.k + " to targetId:" + string4 + " (#units:" + n7 + ")");
            }
            if (a2.b("unload") != null) {
                for (Object object32 : am.bE) {
                    if (((am)object32).bX != n6 || !(object32 instanceof y) || !a2.a((am)object32) || !a2.b((am)object32) || !((am)object32).cr()) continue;
                    object2 = (y)object32;
                    e e2 = l2.cf.b(n6);
                    e2.e = true;
                    e2.a((y)object2);
                    object = ((am)object2).cp();
                    e2.a((c)object);
                }
            }
            bl3 = true;
            return;
        }
        if (a2.g == com.corrodinggames.rts.gameFramework.n.e.f) {
            m m2 = new m();
            for (am am3 : am.bE) {
                if (!(am3 instanceof y) || !a2.a(am3) || !a2.b(am3)) continue;
                m2.add(am3);
            }
            if (m2.size() > 0) {
                for (am am4 : m2) {
                    am4.ci();
                    if (!(am4 instanceof y) || !am4.bI()) continue;
                    l2.bU.a((y)am4);
                }
            }
            bl3 = true;
        }
        if (!bl3) {
            a2.h("Trigger activated with no effect");
        }
    }
}
