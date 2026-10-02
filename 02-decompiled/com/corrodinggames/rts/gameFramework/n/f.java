/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.n;

import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.PointF;
import android.graphics.Typeface;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.d.a.b;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.n.a;
import com.corrodinggames.rts.gameFramework.n.c;
import com.corrodinggames.rts.gameFramework.n.d;
import com.corrodinggames.rts.gameFramework.n.e;
import com.corrodinggames.rts.gameFramework.n.g;
import com.corrodinggames.rts.gameFramework.n.h;
import com.corrodinggames.rts.gameFramework.n.i;
import com.corrodinggames.rts.gameFramework.n.k;
import com.corrodinggames.rts.gameFramework.n.m;
import java.util.ArrayList;

public class f
extends bq {
    public static boolean a = false;
    int b;
    int c;
    n d;
    com.corrodinggames.rts.gameFramework.n.l e;
    com.corrodinggames.rts.gameFramework.n.l f = com.corrodinggames.rts.gameFramework.n.l.b;
    public ArrayList g = new ArrayList();
    public bb h;
    boolean i;
    boolean j;
    public boolean k;
    public boolean l;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    public boolean q;
    public int r = 0;
    String s = null;
    String t = null;
    int u = 0;
    int v = 2;
    int w = 1;
    int x = 0;
    public int y = 0;
    float z = 3000.0f;
    float A = 0.0f;
    float B = 0.0f;
    h C = com.corrodinggames.rts.gameFramework.n.h.a;
    ArrayList D = new ArrayList();
    public Paint E;
    public Paint F;
    public Paint G;
    public Paint H;
    final boolean I = true;
    public ArrayList J = new ArrayList();
    PointF K = new PointF();
    int L = 0;
    float M = 0.0f;
    public boolean N;
    public ArrayList O = new ArrayList();
    PointF P = new PointF();
    boolean Q = false;
    boolean R = false;
    ArrayList S = new ArrayList();
    ArrayList T = new ArrayList();

    public void a(String string) {
        com.corrodinggames.rts.gameFramework.l.b("MissionEngine", "Map warning: " + string);
        ad.a(null, "Map error: " + string);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.j);
        as2.a(this.r);
        as2.a(this.u);
        as2.a(this.v);
        as2.a(this.w);
        as2.a(this.x);
        as2.a(this.z);
        as2.a(this.A);
        as2.a(this.B);
        as2.a(this.m);
        as2.a(6);
        as2.a(this.J.size());
        for (a a2 : this.J) {
            as2.c(a2.c);
            as2.a(a2.j);
            as2.a(a2.k);
            as2.a(a2.l);
            as2.a(a2.m);
            as2.a(a2.n);
        }
        as2.a(this.y);
        as2.a(this.l);
    }

    public void a(com.corrodinggames.rts.gameFramework.j.k k2) {
        this.j = k2.e();
        this.r = k2.f();
        this.u = k2.f();
        this.v = k2.f();
        this.w = k2.f();
        this.x = k2.f();
        this.z = k2.g();
        this.A = k2.g();
        this.B = k2.g();
        this.m = k2.e();
        int n2 = k2.f();
        if (n2 >= 1) {
            int n3 = k2.f();
            for (int j = 0; j < n3; ++j) {
                a a2;
                String string = k2.l();
                boolean bl = k2.e();
                int n4 = 0;
                int n5 = 0;
                boolean bl2 = false;
                int n6 = 0;
                if (n2 >= 2) {
                    n4 = k2.f();
                    n5 = k2.f();
                }
                if (n2 >= 3) {
                    bl2 = k2.e();
                }
                if (n2 >= 4) {
                    n6 = k2.f();
                }
                if ((a2 = this.e(string)) == null) {
                    com.corrodinggames.rts.gameFramework.l.b("MissionEngine:readIn: Could not find saved trigger:" + string + " for de/activation");
                    continue;
                }
                a2.j = bl;
                a2.k = n4;
                a2.l = n5;
                a2.m = bl2;
                a2.n = n6;
            }
        }
        if (n2 >= 5) {
            this.y = k2.f();
        }
        this.l = n2 >= 6 ? k2.e() : true;
    }

    public void b(String string) {
        com.corrodinggames.rts.gameFramework.l.b("MissionEngine:triggerLog", string);
    }

    public boolean a() {
        return this.n;
    }

    public boolean b() {
        return this.o;
    }

    /*
     * WARNING - void declaration
     */
    public void a(boolean bl) {
        Object object;
        Object object2;
        Object object3;
        Object object4;
        String object5;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.q = false;
        this.b = l2.by - 1000;
        this.c = l2.by - 1000;
        this.E = new Paint();
        this.E.a(255, 255, 255, 255);
        this.E.a(true);
        this.E.a(Paint$Align.b);
        this.E.a(Typeface.a(Typeface.c, 1));
        l2.a(this.E, 24.0f);
        this.G = new Paint();
        this.G.a(255, 255, 255, 255);
        this.G.a(true);
        this.G.a(Paint$Align.b);
        l2.a(this.G, 18.0f);
        this.H = new Paint();
        this.H.a(255, 255, 255, 255);
        this.H.a(true);
        this.H.a(Paint$Align.b);
        l2.a(this.H, 14.0f);
        this.F = new Paint();
        this.F.a(this.H);
        l2.a(this.F, 18.0f);
        this.j = true;
        boolean bl2 = false;
        Object object622 = null;
        if (l2.bL.Q == null) {
            com.corrodinggames.rts.gameFramework.l.b("MissionEngine", "Error: 'triggers' object layer is missing from this map");
            bl2 = true;
        } else {
            object622 = l2.bL.Q.a("map_info");
        }
        if (object622 == null) {
            com.corrodinggames.rts.gameFramework.l.b("MissionEngine", "Error: map_info is missing from this map");
            bl2 = true;
        }
        if (object622 != null && ((com.corrodinggames.rts.game.b.a)object622).b("type") == null) {
            this.a("type is missing from map_info");
            bl2 = true;
        }
        if (bl2) {
            com.corrodinggames.rts.gameFramework.l.b("MissionEngine", "Defaulting to skirmish");
            this.n = true;
            this.e = com.corrodinggames.rts.gameFramework.n.l.f;
            return;
        }
        this.k = "survival".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).b("type"));
        if (this.k) {
            this.l = "true".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).b("survivalWavesClassic"));
            if (this.l) {
                com.corrodinggames.rts.gameFramework.l.e("Classic survial waves selected");
            }
            this.f();
            this.p = false;
            this.y = l2.bQ.aiDifficulty;
            if (!this.l) {
                this.z = 1200.0f;
                if (this.y < 0) {
                    this.z = 3000.0f;
                }
            } else {
                this.z = 3000.0f;
            }
        }
        if ((object5 = ((com.corrodinggames.rts.game.b.a)object622).b("survivalWaves")) != null) {
            this.g(object5);
        }
        if ((object4 = ((com.corrodinggames.rts.game.b.a)object622).b("startWithMusic")) != null) {
            l2.bN.a((String)object4);
        }
        this.n = "skirmish".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).b("type"));
        if (this.n) {
            this.e = com.corrodinggames.rts.gameFramework.n.l.f;
        }
        this.o = "true".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).b("shareFogWithAllies"));
        Object object6 = ((com.corrodinggames.rts.game.b.a)object622).b("winCondition");
        if (object6 == null && !this.n) {
            throw new com.corrodinggames.rts.game.b.f("win condition not set");
        }
        if (object6 != null) {
            if (((String)object6).equalsIgnoreCase("none")) {
                this.e = com.corrodinggames.rts.gameFramework.n.l.a;
            } else if (((String)object6).equalsIgnoreCase("allUnitsAndBuildings")) {
                this.e = com.corrodinggames.rts.gameFramework.n.l.b;
            } else if (((String)object6).equalsIgnoreCase("allBuildings")) {
                this.e = com.corrodinggames.rts.gameFramework.n.l.c;
            } else if (((String)object6).equalsIgnoreCase("mainBuilings")) {
                this.e = com.corrodinggames.rts.gameFramework.n.l.d;
            } else if (((String)object6).equalsIgnoreCase("mainBuildings")) {
                this.e = com.corrodinggames.rts.gameFramework.n.l.d;
            } else if (((String)object6).equalsIgnoreCase("commandCenter")) {
                this.e = com.corrodinggames.rts.gameFramework.n.l.e;
            } else if (((String)object6).equalsIgnoreCase("requiredObjectives")) {
                this.e = com.corrodinggames.rts.gameFramework.n.l.g;
            } else {
                throw new com.corrodinggames.rts.game.b.f("unknown win condition:" + (String)object6);
            }
        }
        if (this.n) {
            this.f = this.e;
        }
        this.h = ((com.corrodinggames.rts.game.b.a)object622).a("introText", (bb)null);
        if (this.h != null) {
            this.h.a("\\\\n", "\n");
            if (this.h.a()) {
                this.h = null;
            }
        }
        if (!l2.ay() && !this.n) {
            this.d = com.corrodinggames.rts.game.n.k(3);
            if (this.d != null) {
                this.d.r = 0;
            }
        }
        if (l2.ay()) {
            // empty if block
        }
        for (Object object622 : l2.bL.Q.c) {
            if ("team_info".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d)) {
                Object object7;
                Object object8;
                int n2 = Integer.parseInt(((com.corrodinggames.rts.game.b.a)object622).a("team", "-2"));
                if (n2 == -2) {
                    throw new RuntimeException("cannot find team for:" + ((com.corrodinggames.rts.game.b.a)object622).b);
                }
                object4 = com.corrodinggames.rts.game.n.k(n2);
                if (object4 == null) {
                    com.corrodinggames.rts.gameFramework.l.b("No team loaded for:" + n2 + " skipping");
                    continue;
                }
                object6 = ((com.corrodinggames.rts.game.b.a)object622).c("credits");
                if (object6 != null) {
                    ((n)object4).o = ((Integer)object6).intValue();
                }
                if ((object3 = ((com.corrodinggames.rts.game.b.a)object622).b("basicAI")) != null && l2.P() && object4 instanceof com.corrodinggames.rts.game.a.a) {
                    com.corrodinggames.rts.gameFramework.l.b("Using basic AI:" + n2 + " by map request");
                    object8 = (com.corrodinggames.rts.game.a.a)object4;
                    ((com.corrodinggames.rts.game.a.a)object8).aY = true;
                }
                if ((object8 = ((com.corrodinggames.rts.game.b.a)object622).b("lockAiDifficulty")) != null && object4 instanceof com.corrodinggames.rts.game.a.a) {
                    int n3;
                    com.corrodinggames.rts.gameFramework.l.b("Locking lockAiDifficulty:" + n2 + " by map request to: " + (String)object8);
                    object7 = (com.corrodinggames.rts.game.a.a)object4;
                    ((com.corrodinggames.rts.game.a.a)object7).x = n3 = Integer.parseInt((String)object8);
                    ((com.corrodinggames.rts.game.a.a)object7).y = true;
                    l2.bX.aq();
                }
                if ((object7 = ((com.corrodinggames.rts.game.b.a)object622).b("disabledAI")) != null && l2.P() && object4 instanceof com.corrodinggames.rts.game.a.a) {
                    com.corrodinggames.rts.gameFramework.l.b("Disabling AI:" + n2 + " by map request");
                    com.corrodinggames.rts.game.a.a a2 = (com.corrodinggames.rts.game.a.a)object4;
                    a2.aX = true;
                }
                if ((object2 = ((com.corrodinggames.rts.game.b.a)object622).b("allyGroup")) != null && l2.P()) {
                    int n4;
                    ((n)object4).r = n4 = Integer.parseInt((String)object2);
                }
                if ((object = ((com.corrodinggames.rts.game.b.a)object622).b("ai")) != null) {
                    ((n)object4).U = ((String)object).equalsIgnoreCase("survival");
                }
            }
            if ("camera_start".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).b) && !bl) {
                l2.b(((com.corrodinggames.rts.game.b.a)object622).e, ((com.corrodinggames.rts.game.b.a)object622).f);
                this.q = true;
                Integer n5 = ((com.corrodinggames.rts.game.b.a)object622).c("zoomTo");
                if (n5 != null) {
                    l2.cV = n5.intValue();
                }
            }
            if ("attack_point".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).b)) {
                this.D.add(new PointF(((com.corrodinggames.rts.game.b.a)object622).e, ((com.corrodinggames.rts.game.b.a)object622).f));
            }
            if ("rotate".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d)) {
                String string = ((com.corrodinggames.rts.game.b.a)object622).b("dir");
                float f2 = Float.parseFloat(string);
                object6 = am.bE.iterator();
                while (object6.hasNext()) {
                    object3 = (am)object6.next();
                    if (!(object3 instanceof y) || ((am)object3).bI() || !((com.corrodinggames.rts.game.b.a)object622).a((am)object3)) continue;
                    ((am)object3).cg = f2;
                }
            }
            if ("fall".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d)) {
                for (am am2 : am.bE) {
                    if (!(am2 instanceof y) || am2.bI() || !((com.corrodinggames.rts.game.b.a)object622).a(am2)) continue;
                    am2.dc();
                }
            }
            if ("set_team".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d)) {
                String string = ((com.corrodinggames.rts.game.b.a)object622).b("team");
                int n6 = Integer.parseInt(string);
                object6 = am.bE.iterator();
                while (object6.hasNext()) {
                    object3 = (am)object6.next();
                    if (!(object3 instanceof y) || !((com.corrodinggames.rts.game.b.a)object622).a((am)object3)) continue;
                    ((am)object3).P(n6);
                }
            }
            if ("ai_allow_full_use".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d)) {
                for (am am3 : am.bE) {
                    if (!(am3 instanceof y) || !((com.corrodinggames.rts.game.b.a)object622).a(am3)) continue;
                    ((y)am3).bM = false;
                }
            }
            if (!"disable_unit_ai".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d)) continue;
            for (am am4 : am.bE) {
                if (!(am4 instanceof y) || !((com.corrodinggames.rts.game.b.a)object622).a(am4)) continue;
                am4.bN = true;
            }
        }
        for (Object object622 : am.bE) {
            void var5_21;
            if (((am)object622).u() || object622 instanceof al || ((am)object622).bI() || ((am)object622).cN != null || ((am)object622).cO != null) continue;
            Object var5_20 = null;
            float f3 = 4900.0f;
            object6 = am.bE.iterator();
            while (object6.hasNext()) {
                float f4;
                object3 = (am)object6.next();
                if (!((am)object3).cr() || object622 == object3 || ((am)object622).bX != com.corrodinggames.rts.game.n.i && !((am)object3).bX.d(((am)object622).bX) || !((f4 = com.corrodinggames.rts.gameFramework.f.a(((am)object3).eo, ((am)object3).ep, ((am)object622).eo, ((am)object622).ep)) < f3) || !((am)object3).d((am)object622, true)) continue;
                Object object9 = object3;
                f3 = f4;
            }
            if (var5_21 == null) continue;
            var5_21.e((am)object622, true);
        }
        this.J.clear();
        for (Object object622 : l2.bL.Q.c) {
            if ("team_info".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d) || "point".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d) || "camera_pan".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d) || "camera_start".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).b) || "map_info".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).b) || "attack_point".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).b) || "rotate".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d) || "fall".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d) || "set_team".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d) || "ai_allow_full_use".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d) || "disable_unit_ai".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d) || "info".equalsIgnoreCase(((com.corrodinggames.rts.game.b.a)object622).d)) continue;
            if (((com.corrodinggames.rts.game.b.a)object622).n == null) {
                com.corrodinggames.rts.gameFramework.n.f.c("Error: Skipping trigger:" + ((com.corrodinggames.rts.game.b.a)object622).b + " - no properties found");
                continue;
            }
            a a3 = com.corrodinggames.rts.gameFramework.n.c.a(this, (com.corrodinggames.rts.game.b.a)object622);
            if (a3 == null) continue;
            this.J.add(a3);
        }
        for (Object object622 : this.J) {
            int n7;
            void var5_26;
            String string = ((a)object622).b("activateIds");
            if (string == null) {
                String string2 = ((a)object622).b("alsoActivate");
            }
            if (var5_26 != null) {
                object6 = object4 = var5_26.split(",");
                int n8 = ((String[])object6).length;
                for (n7 = 0; n7 < n8; ++n7) {
                    Object object10 = object6[n7];
                    object2 = this.d((String)object10);
                    if (object2 == null) {
                        ((a)object622).g("linkedTo target not found: " + (String)var5_26);
                        com.corrodinggames.rts.gameFramework.l.e("Possible IDs:");
                        for (a a4 : this.J) {
                            if (a4.b == null) continue;
                            com.corrodinggames.rts.gameFramework.l.e(a4.b);
                        }
                        com.corrodinggames.rts.gameFramework.l.e("--------");
                        continue;
                    }
                    ((a)object2).d.a((a)object622);
                }
            }
            if ((object4 = ((a)object622).b("whenActivatedIds")) == null) {
                object4 = ((a)object622).b("activatedBy");
            }
            if (object4 != null) {
                Object object11 = object6 = ((String)object4).split(",");
                n7 = ((Object)object11).length;
                for (int i2 = 0; i2 < n7; ++i2) {
                    object2 = object11[i2];
                    object = this.d((String)object2);
                    if (object == null) {
                        ((a)object622).g("linkedFrom target not found: " + (String)object2);
                        continue;
                    }
                    ((a)object622).d.a((a)object);
                }
            }
            if ((object4 = ((a)object622).b("deactivatedBy")) == null) continue;
            Object object12 = object6 = ((String)object4).split(",");
            n7 = ((Object)object12).length;
            for (int i3 = 0; i3 < n7; ++i3) {
                object2 = object12[i3];
                object = this.d((String)object2);
                if (object == null) {
                    ((a)object622).g("deactivatedBy: target not found: " + (String)object2);
                    continue;
                }
                ((a)object622).e.a((a)object);
            }
        }
        com.corrodinggames.rts.gameFramework.l.e("Found " + this.J.size() + " map triggers");
        for (Object object622 : this.J) {
            for (String string : ((a)object622).t.a()) {
                ((a)object622).g("Key was not used: " + string);
            }
        }
        this.c();
    }

    public void c() {
        for (a a2 : this.J) {
            if (a2.g != com.corrodinggames.rts.gameFramework.n.e.a) continue;
            boolean bl = false;
            for (m m2 : this.g) {
                if (m2.a != a2) continue;
                bl = true;
            }
            if (bl) continue;
            m m3 = new m();
            m3.a = a2;
            this.g.add(m3);
            com.corrodinggames.rts.gameFramework.l.e("Found objective: " + m3.a());
        }
    }

    public static void c(String string) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.l.b("MissionEngine", string);
        ad.g(string);
    }

    public a d(String string) {
        string = string.trim();
        for (a a2 : this.J) {
            if (a2.b == null || !a2.b.equalsIgnoreCase(string)) continue;
            return a2;
        }
        return null;
    }

    public a e(String string) {
        string = string.trim();
        for (a a2 : this.J) {
            if (!a2.c.equalsIgnoreCase(string)) continue;
            return a2;
        }
        return null;
    }

    public PointF f(String string) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.game.b.i i2 = l2.bL.Q;
        if (i2 != null) {
            for (com.corrodinggames.rts.game.b.a a2 : i2.c) {
                if (!"point".equalsIgnoreCase(a2.d) || a2.c == null || !a2.c.equalsIgnoreCase(string)) continue;
                this.K.a(a2.e, a2.f);
                return this.K;
            }
        }
        return null;
    }

    public void a(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
    }

    public void b(float f2) {
        Object object;
        Object object2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.i) {
            object2 = this.J.iterator();
            while (object2.hasNext()) {
                a a2 = (a)object2.next();
                if (a2.g != com.corrodinggames.rts.gameFramework.n.e.g || !a2.j) continue;
                float f3 = (float)a2.b() - l2.cw;
                float f4 = (float)a2.c() - l2.cx;
                f3 *= l2.cX;
                f4 *= l2.cX;
                f3 += a2.w;
                f4 += a2.x;
                if (a2.C) {
                    object = com.corrodinggames.rts.gameFramework.d.c.s[9];
                    ((com.corrodinggames.rts.gameFramework.d.g)object).a(2, f3, f4, a2.B);
                    f4 -= (float)(((com.corrodinggames.rts.gameFramework.d.g)object).c - 2);
                }
                if (a2.z == null || (object = a2.z.b()) == null || ((String)object).equals("")) continue;
                l2.bO.a((String)object, f3, f4, a2.B);
            }
        }
        if (this.k && !this.N) {
            boolean bl = true;
            boolean bl2 = false;
            this.B = com.corrodinggames.rts.gameFramework.f.a(this.B, f2);
            if (this.B == 0.0f && this.A != 0.0f) {
                this.A = com.corrodinggames.rts.gameFramework.f.a(this.A, f2);
                bl2 = true;
            }
            if (bl) {
                if (bl2) {
                    int n2 = (int)(23.0f + this.E.k() / 2.0f);
                    l2.bO.a("- Wave " + this.r + " -", l2.cF / 2.0f, (float)n2, this.E);
                    if (this.s != null) {
                        l2.bO.a(this.s, l2.cF / 2.0f, (float)n2 + this.E.k() + 2.0f, this.F);
                    }
                } else {
                    int n3 = (int)(23.0f + this.G.k() / 2.0f);
                    String string = "Wave " + (this.r + 1) + " in " + com.corrodinggames.rts.gameFramework.f.f(String.valueOf((int)((double)this.z / 60.0)), 3);
                    if (this.m) {
                        string = "Defeat - Wave " + this.r;
                    }
                    l2.bO.a(string, l2.cF / 2.0f, (float)n3, this.G);
                    if (this.t == null) {
                        object = !this.l ? this.b(false) : this.c(false);
                        this.t = ((i)object).toString();
                    }
                    object = this.t;
                    l2.bO.a((String)object, l2.cF / 2.0f, (float)n3 + this.G.k() + 2.0f, this.H);
                }
            }
        }
        if (this.k && this.N && (object2 = this.d()) != null) {
            int n4 = ((g)object2).e - l2.by / 1000;
            int n5 = (int)(23.0f + this.G.k() / 2.0f);
            String string = "Wave " + (this.r + 1) + " in " + com.corrodinggames.rts.gameFramework.f.f(String.valueOf(n4), 3);
            if (this.m) {
                string = "Defeat - Wave " + this.r;
            }
            l2.bO.a(string, l2.cF / 2.0f, (float)n5, this.G);
            object = ((g)object2).f;
            if (object != null) {
                l2.bO.a((String)object, l2.cF / 2.0f, (float)n5 + this.G.k() + 2.0f, this.H);
            }
        }
    }

    public void g(String string) {
        com.corrodinggames.rts.gameFramework.l.e("Loading survival waves");
        this.N = true;
        String[] stringArray = string.split("\n");
        int n2 = 0;
        int n3 = 0;
        boolean bl = false;
        for (String string2 : stringArray) {
            ++n3;
            g g2 = new g(this);
            if (!g2.a(string2)) continue;
            n2 = g2.e = n2 + (int)g2.d;
            com.corrodinggames.rts.gameFramework.l.e("Adding wave " + n3 + " at " + g2.e);
            this.O.add(g2);
        }
    }

    public g d() {
        if (this.r < this.O.size()) {
            return (g)this.O.get(this.r);
        }
        return null;
    }

    public void e() {
        this.R = true;
        int n2 = com.corrodinggames.rts.gameFramework.f.a(0, this.D.size() - 1, this.r);
        PointF pointF = (PointF)this.D.get(n2);
        this.P.a(pointF);
    }

    public void f() {
        this.S.clear();
        this.a(this.S, "scout", 0.7f);
        this.a(this.S, ar.i, 2.1f);
        this.a(this.S, "mechGun", 1.0f);
        this.a(this.S, "lightGunship", 2.8f);
        this.a(this.S, ar.j, 1.9f);
        this.a(this.S, ar.l, 0.8f);
        this.a(this.S, ar.w, 1.0f);
        this.a(this.S, ar.x, 0.8f);
        this.a(this.S, ar.n, 0.7f);
        this.a(this.S, "plasmaTank", 0.6f);
        this.a(this.S, "missileAirship", 0.4f);
        this.T.clear();
        this.a(this.T, ar.F, 1.0f);
        this.a(this.T, ar.O, 0.5f);
    }

    public void a(ArrayList arrayList, String string, float f2) {
        this.a(arrayList, com.corrodinggames.rts.game.units.custom.l.s(string), f2);
    }

    public void a(ArrayList arrayList, as as2, float f2) {
        as as3;
        if (as2 == null) {
            as2 = ar.i;
        }
        if ((as3 = com.corrodinggames.rts.game.units.custom.l.c(as2)) != null) {
            as2 = as3;
        }
        k k2 = new k(this);
        k2.a = as2;
        k2.b = f2;
        arrayList.add(k2);
    }

    public void a(i i2, int n2, float f2) {
        int n3;
        if (n2 < 0) {
            n2 = 0;
        }
        if ((n3 = this.S.size()) == 0) {
            com.corrodinggames.rts.gameFramework.l.b("error maxTypeNum: " + n3);
            return;
        }
        int n4 = n2 % n3;
        k k2 = (k)this.S.get(n4);
        int n5 = (int)((double)(n2 + 3) * 0.5 * (double)k2.b * (double)f2);
        if ((n5 = (int)com.corrodinggames.rts.gameFramework.f.e(n5, 0.8f)) < 1) {
            n5 = 1;
        }
        i2.b(k2.a, n5);
    }

    public i b(boolean bl) {
        int n2;
        i i2 = new i(this);
        boolean bl2 = false;
        if (this.u > 50 && (this.u + 1) % 100 == 0) {
            n2 = this.T.size();
            int n3 = this.u / 100;
            if (n2 == 0) {
                com.corrodinggames.rts.gameFramework.l.b("error maxTypeNum: " + n2);
            } else {
                int n4 = n3 % n2;
                k k2 = (k)this.T.get(n4);
                int n5 = (int)((float)n3 * k2.b);
                if (n5 < 1) {
                    n5 = 1;
                }
                i2.b(k2.a, n5);
            }
            bl2 = true;
        }
        n2 = 0;
        if (this.y > 0) {
            n2 = this.y;
        }
        this.a(i2, this.u + n2, 1.0f);
        if (this.u > 15 && !bl2) {
            this.a(i2, (int)((float)(this.u + n2) * 1.1f) - 11, 0.5f);
        }
        if (bl) {
            ++this.u;
            ++this.v;
        }
        return i2;
    }

    public i c(boolean bl) {
        i i2 = new i(this);
        i2.a = false;
        int n2 = this.v;
        ar ar2 = null;
        if (this.p) {
            ar2 = ar.t;
        } else {
            if (this.u == 0) {
                ++n2;
                ar2 = ar.i;
            }
            if (this.u == 1) {
                ar2 = ar.j;
            }
            if (this.u == 2) {
                ar2 = ar.l;
            }
            if (this.u == 3) {
                n2 = this.w;
                ar2 = ar.w;
            }
            if (this.u == 4) {
                n2 = this.w;
                ar2 = ar.x;
                if (this.w % 2 == 0) {
                    ar2 = ar.n;
                }
            }
            if (this.u == 5) {
                i2.a = true;
                n2 = 1;
                ar2 = ar.F;
            }
            if (bl) {
                ++this.u;
                boolean bl2 = false;
                if (this.w == 1) {
                    if (this.u > 2) {
                        bl2 = true;
                    }
                } else if (this.w < 5) {
                    if (this.u > 4) {
                        bl2 = true;
                    }
                } else {
                    if (this.u > 5) {
                        bl2 = true;
                    }
                    if (this.u > 4 && this.w % 2 == 0) {
                        bl2 = true;
                    }
                }
                if (bl2) {
                    this.u = 0;
                    this.v += 2;
                    ++this.w;
                }
            }
        }
        i2.a(ar2, n2);
        return i2;
    }

    public void c(float f2) {
        Object object;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        int n2 = l2.by;
        this.M = com.corrodinggames.rts.gameFramework.f.a(this.M, f2);
        if (l2.aq && l2.bH) {
            object = null;
            if (l2.bL.Q != null) {
                for (com.corrodinggames.rts.game.b.a a2 : l2.bL.Q.c) {
                    if (!"camera_pan".equalsIgnoreCase(a2.d) || this.L != Integer.parseInt(a2.a("index", "-1"))) continue;
                    object = a2;
                }
            }
            if (object == null) {
                this.L = 0;
            } else {
                float f3 = ((com.corrodinggames.rts.game.b.a)object).e;
                float f4 = ((com.corrodinggames.rts.game.b.a)object).f;
                if (f3 < l2.cI + 2.0f) {
                    f3 = l2.cI + 2.0f;
                }
                if (f4 < l2.cJ + 2.0f) {
                    f4 = l2.cJ + 2.0f;
                }
                if (f3 > l2.bL.i() - l2.cI - 2.0f) {
                    f3 = l2.bL.i() - l2.cI - 2.0f;
                }
                if (f4 > l2.bL.j() - l2.cJ - 2.0f) {
                    f4 = l2.bL.j() - l2.cJ - 2.0f;
                }
                float f5 = com.corrodinggames.rts.gameFramework.f.d(l2.cy + l2.cI, l2.cz + l2.cJ, f3, f4);
                float f6 = com.corrodinggames.rts.gameFramework.f.a(l2.cy + l2.cI, l2.cz + l2.cJ, f3, f4);
                if (this.M == 0.0f && (f6 < 225.0f || l2.ct)) {
                    ++this.L;
                    this.M = 50.0f;
                }
                float f7 = 0.45f * f2;
                l2.cy += com.corrodinggames.rts.gameFramework.f.k(f5) * f7;
                l2.cz += com.corrodinggames.rts.gameFramework.f.j(f5) * f7;
                l2.a(l2.cy, l2.cz);
                l2.Q();
            }
        }
        if (this.k) {
            if (!this.N) {
                if (!this.m) {
                    this.z = com.corrodinggames.rts.gameFramework.f.a(this.z, f2);
                }
                if (this.z == 0.0f && !this.m) {
                    i i2;
                    ++this.r;
                    this.A = 180.0f;
                    int n3 = com.corrodinggames.rts.gameFramework.f.a(0, this.D.size() - 1, this.r);
                    PointF pointF = (PointF)this.D.get(n3);
                    if (!this.l) {
                        this.s = this.b(false).toString();
                        i2 = this.b(true);
                    } else {
                        this.s = this.c(false).toString();
                        i2 = this.c(true);
                    }
                    this.z = 1800.0f;
                    if (!this.l) {
                        this.z = this.y > 0 ? (this.z -= (float)(this.y * 3 * 60)) : (this.z -= (float)(this.y * 9 * 60));
                    }
                    i2.a(pointF.a, pointF.b);
                    this.t = null;
                }
            } else if (!this.m) {
                object = this.d();
                if (object != null) {
                    if (((g)object).e * 1000 < l2.by) {
                        ((g)object).a();
                        ++this.r;
                    }
                } else if (!l2.dq && !l2.cb.j()) {
                    l2.bS.G();
                }
            }
        }
        if (this.j) {
            this.j = false;
            if (this.h != null) {
                l2.a("Briefing", this.h);
            }
        }
        if (n2 > this.b + 250) {
            this.b = n2;
            this.a(n2);
        }
        if (n2 > this.c + 1000) {
            this.c = n2;
            if (this.h()) {
                this.h();
                this.h();
            }
            boolean bl = false;
            boolean bl2 = false;
            if (l2.bs != null) {
                if (l2.bs.j()) {
                    bl = true;
                }
                if (l2.bs.b()) {
                    bl2 = true;
                }
            }
            if (!(l2.dq || l2.dt || l2.cb.j() || bl2)) {
                boolean bl3 = true;
                boolean bl4 = true;
                if (this.e == com.corrodinggames.rts.gameFramework.n.l.a) {
                    bl3 = false;
                } else if (this.e == com.corrodinggames.rts.gameFramework.n.l.g) {
                    for (m m2 : this.g) {
                        if (m2.b()) continue;
                        bl3 = false;
                    }
                } else if (l2.bs != null) {
                    for (am am2 : am.bE) {
                        if (!l2.bs.c(am2.bX) || !this.a(this.e, am2)) continue;
                        bl3 = false;
                        break;
                    }
                }
                if (this.f == com.corrodinggames.rts.gameFramework.n.l.a) {
                    bl4 = false;
                } else if (this.f == com.corrodinggames.rts.gameFramework.n.l.g) {
                    bl4 = false;
                } else if (l2.bs != null) {
                    for (am am3 : am.bE) {
                        if (!l2.bs.d(am3.bX) || !this.a(this.f, am3)) continue;
                        bl4 = false;
                        break;
                    }
                }
                if (bl4 && !bl3) {
                    l2.bS.H();
                }
                if (bl3) {
                    l2.bS.G();
                    if (l2.by > 1500) {
                        ++l2.bQ.numberOfWins;
                        l2.bQ.save();
                    }
                }
            }
            if (this.k && !this.m) {
                boolean bl5 = true;
                for (am am4 : am.bE) {
                    if (!(am4 instanceof com.corrodinggames.rts.game.units.d.e) && !am4.bP || am4.bV || am4.u() || am4.bX != l2.bs) continue;
                    bl5 = false;
                }
                if (bl5) {
                    this.m = true;
                    l2.bS.H();
                }
            }
        }
    }

    public boolean a(com.corrodinggames.rts.gameFramework.n.l l2, am am2) {
        if (!(am2 instanceof y)) {
            return false;
        }
        if (am2.bV || am2.cT()) {
            return false;
        }
        if (l2 == com.corrodinggames.rts.gameFramework.n.l.a) {
            return false;
        }
        if (l2 == com.corrodinggames.rts.gameFramework.n.l.b) {
            return true;
        }
        if (l2 == com.corrodinggames.rts.gameFramework.n.l.c) {
            return am2.bI();
        }
        if (l2 == com.corrodinggames.rts.gameFramework.n.l.e) {
            return am2 instanceof com.corrodinggames.rts.game.units.d.e || am2.bP;
        }
        if (l2 == com.corrodinggames.rts.gameFramework.n.l.d) {
            return am2.bI() && am2.bJ() && !(am2 instanceof b) && !(am2 instanceof com.corrodinggames.rts.game.units.d.g);
        }
        if (l2 == com.corrodinggames.rts.gameFramework.n.l.f) {
            if (am2.bJ()) {
                return true;
            }
            return am2.ak();
        }
        if (l2 == com.corrodinggames.rts.gameFramework.n.l.g) {
            return false;
        }
        return false;
    }

    public void h(String string) {
        com.corrodinggames.rts.gameFramework.l.e("Map Script: " + string);
    }

    public void a(a a2) {
        if (this.g()) {
            this.h("Activiated trigger:" + a2.a + " (id:" + a2.b + ")");
        }
    }

    public boolean g() {
        return a && com.corrodinggames.rts.gameFramework.l.B().bl;
    }

    public static void i(String string) {
        ad.g("Map ScriptError: " + string);
    }

    public void a(int n2) {
        for (a a2 : this.J) {
            if (a2.j && a2.q != -1 && n2 >= a2.k + a2.q) {
                a2.j = false;
                a2.u = false;
            }
            if (!a2.j && !a2.u && a2.d()) {
                a2.u = true;
            }
            if ((a2.j || a2.u) && a2.e.b()) {
                a2.j = false;
                a2.u = false;
                a2.m = true;
            }
            if (a2.j && a2.p > 0 && n2 >= a2.k + a2.p) {
                a2.u = true;
            }
            if (!a2.u) continue;
            a2.u = false;
            try {
                com.corrodinggames.rts.gameFramework.n.d.a(this, a2);
            }
            catch (com.corrodinggames.rts.game.b.f f2) {
                f2.printStackTrace();
                a2.g("Error activating trigger: " + f2.getMessage());
            }
        }
    }

    public boolean h() {
        boolean bl = false;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        block0: for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (am2.bX != com.corrodinggames.rts.game.n.i || !(am2 instanceof y) || !am2.bT() || am2.o()) continue;
            int n3 = am.bE.size();
            for (int i3 = 0; i3 < n3; ++i3) {
                boolean bl2;
                am am3 = amArray[i3];
                if (!l2.ay()) {
                    bl2 = am3.bX == l2.bs;
                } else {
                    boolean bl3 = bl2 = !am3.bX.w;
                    if (am2.cO()) {
                        bl2 = true;
                    }
                }
                if (am3.bX != null && am3.bX.k < 0) {
                    bl2 = false;
                }
                if (!bl2 || am3.bX == am2.bX || !(am3 instanceof y) || am3.i() || !am3.bT() || !(com.corrodinggames.rts.gameFramework.f.a(am3.eo, am3.ep, am2.eo, am2.ep) < 28900.0f)) continue;
                am2.e(am3.bX);
                am2.cJ = 60.0f;
                bl = true;
                continue block0;
            }
        }
        return bl;
    }
}
