/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.ad;
import com.corrodinggames.rts.gameFramework.ae;
import com.corrodinggames.rts.gameFramework.af;
import com.corrodinggames.rts.gameFramework.ai;
import com.corrodinggames.rts.gameFramework.aj;
import com.corrodinggames.rts.gameFramework.al;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

public class ac {
    public static aj a = new al();
    public static ai b = new ai();
    public ad c;
    public ad d;
    public ad e;
    public ad f;
    public ad g;
    public ad h;
    public ad i;
    public ad j;
    public ad k;
    public ad l = this.a("Debug Left");
    public ad m = this.a("Debug Right");
    public ad n = this.b("Camera Up");
    public ad o = this.b("Camera Down");
    public ad p = this.b("Camera Left");
    public ad q = this.b("Camera Right");
    public ad r = this.b("Zoom In");
    public ad s = this.b("Zoom Out");
    public ad t = this.b("Send Chat");
    public ad u = this.b("Send Team Chat");
    public ad v = this.b("Ping Map");
    public ad w = this.b("Show Menu");
    public ad x = this.b("Save Game");
    public ad y = this.b("Deselect units");
    public ad z = this.b("Go to notification");
    public ad A = this.b("Select Whole Army");
    public ad B = this.b("Select Command Center");
    public ad C = this.b("Cycle Builders");
    public ad D = this.b("Cycle Extractors");
    public ad E = this.b("Cycle Upgradable Fabricators");
    public ad F = this.b("Cycle Land Factories");
    public ad G = this.b("Cycle Air Factories");
    public ad H = this.b("Next Music Track");
    public ae I = this.c("Game Speed (Single player)");
    public ad J = this.b("Slower");
    public ad K = this.b("Faster");
    public ad L = this.b("Pause Game");
    public ae M = this.c("Unit Actions");
    public ad N = this.b("Attack Move");
    public ad O = this.b("Stop");
    public ad P = this.b("Guard Unit");
    public ad Q = this.b("Patrol");
    public ad R = this.b("Reclaim");
    public ad S = this.b("Action - Upgrade");
    public ad T = this.b("Action - Set Rally");
    public ad U = this.a("Debug Editor");
    public ad V = this.a("Debug Pause");
    public ad W = this.a("Debug Slow");
    public ad X = this.a("Debug HideInterface");
    public ad Y = this.a("Debug HideInterface Temp");
    public ad Z = this.a("Debug InvincibleUnits");
    public ad aa = this.a("debugPrintSelectedUnit");
    public ad ab = this.a("debugDevModeSwitch");
    public ad ac = this.a("debugAIViewSwitch");
    public ad ad = this.a("debugMapSwitch");
    public ad ae = this.a("Debug Take Screenshot");
    public ad af = this.a("Debug Take Screenshot High");
    public ad[] ag;
    public ae ah;
    public ad[] ai;
    public ad[] aj;
    public ad[] ak;
    public ArrayList al;
    Properties am;
    int an;
    int ao;

    public ac() {
        int n;
        int n2;
        int n3;
        this.n.a("UP").a("NUMPAD8");
        this.o.a("DOWN").a("NUMPAD2");
        this.p.a("LEFT").a("NUMPAD4");
        this.q.a("RIGHT").a("NUMPAD6");
        this.l.a("F5");
        this.m.a("F6");
        this.x.a("CTRL+S");
        this.t.a("ENTER").a("T");
        this.u.a("SHIFT+ENTER").a("Y");
        this.v.a("CTRL+M").a("CTRL+P");
        this.w.a("ESCAPE").a("F10");
        this.y.a("SPACE");
        this.z.a("CTRL+SPACE");
        this.A.a("CTRL+A");
        this.C.a("CTRL+B");
        this.D.a("CTRL+E");
        this.E.a("CTRL+F");
        this.F.a("CTRL+L");
        this.G.a("CTRL+K");
        this.B.a("CTRL+C");
        this.H.a("CTRL+N");
        this.N.a("A");
        this.L.a("BREAK");
        this.O.a("S");
        this.P.a("G");
        this.Q.a("P");
        this.S.a("U");
        this.T.a("R");
        this.U.a("CTRL+SHIFT+E");
        this.V.a("CTRL+SHIFT+P");
        this.W.a("CTRL+SHIFT+S");
        this.X.a("CTRL+SHIFT+H");
        this.Y.a("CTRL+H");
        this.Z.a("CTRL+SHIFT+I");
        this.aa.a("CTRL+SHIFT+L");
        this.ab.a("CTRL+SHIFT+D");
        this.ac.a("SHIFT+F3");
        this.ad.a("SHIFT+F4");
        this.ae.a("CTRL+SHIFT+ALT+S");
        this.af.a("CTRL+SHIFT+ALT+D");
        this.J.a("minus").a("NUMPADSUBTRACT");
        this.K.a("equals").a("NUMPADADD");
        int[] nArray = new int[]{54, 52, 31, 50, 30, 42, 41, 38, 39, 40, 37, 43};
        this.ag = new ad[10];
        for (n3 = 0; n3 < this.ag.length; ++n3) {
            this.ag[n3] = this.b("unit action " + (n3 + 1));
            this.ag[n3].c(nArray[n3]);
        }
        this.ah = this.c("Unit Groups");
        this.ak = new ad[10];
        for (n3 = 0; n3 < this.ak.length; ++n3) {
            this.ak[n3] = this.b("create group " + (n3 + 1));
            n2 = this.a(n3 == 9 ? 0 : n3 + 1);
            n = 1;
            this.ak[n3].a(n2, 0, n, false);
        }
        this.ai = new ad[10];
        for (n3 = 0; n3 < this.ai.length; ++n3) {
            this.ai[n3] = this.b("select group " + (n3 + 1));
            n2 = this.a(n3 == 9 ? 0 : n3 + 1);
            this.ai[n3].c(n2);
        }
        this.aj = new ad[10];
        for (n3 = 0; n3 < this.aj.length; ++n3) {
            this.aj[n3] = this.b("Add group to selection " + (n3 + 1));
            n2 = this.a(n3 == 9 ? 0 : n3 + 1);
            n = 2;
            this.aj[n3].a(n2, 0, n, false);
        }
        this.am = new Properties();
        this.an = 0;
        this.ao = 0;
    }

    public int a(int n) {
        if (n >= 10) {
            throw new RuntimeException("number:" + n + " too high");
        }
        if (n == 0) {
            return 7;
        }
        return 8 + (n - 1);
    }

    public ad a(String string) {
        if (this.al == null) {
            this.al = new ArrayList();
        }
        ad ad2 = new ad();
        ad2.a = string;
        ad2.b = false;
        this.al.add(ad2);
        return ad2;
    }

    public ad b(String string) {
        if (this.al == null) {
            this.al = new ArrayList();
        }
        ad ad2 = new ad();
        ad2.a = string;
        ad2.b = true;
        this.al.add(ad2);
        return ad2;
    }

    public ae c(String string) {
        if (this.al == null) {
            this.al = new ArrayList();
        }
        ae ae2 = new ae();
        ae2.a = string;
        ae2.b = true;
        this.al.add(ae2);
        return ae2;
    }

    public void a(String string, String string2) {
        string = string.toLowerCase(Locale.ENGLISH).trim();
        ad ad2 = null;
        for (ad ad3 : this.al) {
            if (ad3.a == null || !ad3.e().equals(string)) continue;
            ad2 = ad3;
        }
        if (ad2 == null) {
            com.corrodinggames.rts.gameFramework.l.b("loadKey: could not find:" + string);
            return;
        }
        String[] stringArray = string2.split(",");
        for (int i = 0; i <= 1 && i < stringArray.length; ++i) {
            String string3 = stringArray[i];
            if (string3.equalsIgnoreCase("DEFAULT")) continue;
            ad2.a(string3, i);
            if (ad2.c.size() > i && ad2.c.get(i) != null) {
                ((af)ad2.c.get((int)i)).d = true;
                continue;
            }
            com.corrodinggames.rts.gameFramework.l.g("out of range");
        }
    }

    public String a(ad ad2) {
        String string = "";
        boolean bl = true;
        for (af af2 : ad2.c) {
            if (bl) {
                bl = false;
            } else {
                string = string + ",";
            }
            if (af2.d) {
                if (af2.d()) {
                    string = string + "CLEARED";
                    continue;
                }
                string = string + af2.c();
                continue;
            }
            string = string + "DEFAULT";
        }
        return string;
    }

    public boolean a(ad ad2, int n2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        af af2 = ad2.a(n2);
        if (af2 == null) {
            return false;
        }
        ArrayList arrayList = l2.bT.al;
        for (int j = 0; j < arrayList.size(); ++j) {
            ad ad3 = (ad)arrayList.get(j);
            if (ad3 == ad2) continue;
            for (af af3 : ad3.c) {
                if (!af2.a(af3)) continue;
                return true;
            }
        }
        return false;
    }

    public void a() {
        this.c = this.a("shoot");
        this.d = this.a("move up");
        this.e = this.a("move down");
        this.f = this.a("move left");
        this.g = this.a("move right");
        this.h = this.a("aim up");
        this.i = this.a("aim down");
        this.j = this.a("aim left");
        this.k = this.a("aim right");
        int n2 = 0;
        this.c.a(n2, "enter", -1);
        this.c.a(n2, "space", -1);
        this.d.a(n2, "w", -1);
        this.e.a(n2, "s", -1);
        this.f.a(n2, "a", -1);
        this.g.a(n2, "d", -1);
        this.h.a(n2, "UP", -1);
        this.i.a(n2, "DOWN", -1);
        this.j.a(n2, "LEFT", -1);
        this.k.a(n2, "RIGHT", -1);
        com.corrodinggames.rts.gameFramework.l.e("getControllerCount:" + b.a());
        int n3 = 1;
        this.d.b(n2, n3, 0, true);
        this.e.b(n2, n3, 0, false);
        this.f.b(n2, n3, 1, true);
        this.g.b(n2, n3, 1, false);
        this.h.b(n2, n3, 2, true);
        this.i.b(n2, n3, 2, false);
        this.j.b(n2, n3, 3, true);
        this.k.b(n2, n3, 3, false);
        this.c.b(n2, n3, 4, true);
    }

    public void b() {
        if (b.a() != this.ao) {
            this.ao = b.a();
            com.corrodinggames.rts.gameFramework.l.e("Number of controllers changed, now:" + this.ao);
        }
    }

    public static int d(String string) {
        if (string.equalsIgnoreCase("CLEARED")) {
            return 0;
        }
        return SlickToAndroidKeycodes.a(string);
    }
}
