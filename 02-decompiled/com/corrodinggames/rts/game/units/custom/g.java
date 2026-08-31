/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import java.util.ArrayList;
import java.util.Locale;

public final class g {
    final String a;
    public static ArrayList b = new ArrayList();
    public static final g[] c = new g[0];
    public static final h d = new h(c);

    private g(String string) {
        this.a = string;
    }

    public String toString() {
        return this.a;
    }

    public static h a(String string) {
        return g.a(string, null);
    }

    public static h a(String string, h h2) {
        if (string == null) {
            return h2;
        }
        if (string.trim().equals("")) {
            return h2;
        }
        ArrayList<g> arrayList = new ArrayList<g>();
        for (String string2 : string.split(",")) {
            g g2;
            if ((string2 = string2.trim()).equals("") || arrayList.contains(g2 = g.c(string2))) continue;
            arrayList.add(g2);
        }
        if (arrayList.size() == 0) {
            return h2;
        }
        h h3 = new h(arrayList.toArray(new g[0]));
        return h3;
    }

    public static g b(String string) {
        if ((string = string.trim()).contains(",")) {
            throw new bo("Expected single tag, got:" + string);
        }
        return g.c(string);
    }

    public static g c(String string) {
        string = string.trim();
        string = string.toLowerCase(Locale.ROOT);
        for (g g2 : b) {
            if (!g2.a.equals(string)) continue;
            return g2;
        }
        g g3 = new g(string);
        b.add(g3);
        return g3;
    }

    public static void a(h h2, as as2) {
        if (h2 == null) {
            as2.b((String)null);
        } else if (h2.a.length == 0) {
            as2.b("");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            boolean bl = true;
            for (g g2 : h2.a) {
                if (!bl) {
                    stringBuilder.append(",");
                }
                bl = false;
                stringBuilder.append(g2.a);
            }
            as2.b(stringBuilder.toString());
        }
    }

    public static h a(k k2) {
        String string = k2.j();
        if (string == null) {
            return null;
        }
        h h2 = g.a(string, d);
        return h2;
    }

    public static boolean a(h h2, h h3) {
        if (h3 == null) {
            return false;
        }
        g[] gArray = h2.a;
        int n2 = gArray.length;
        g[] gArray2 = h3.a;
        int n3 = gArray2.length;
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n3; ++j) {
                if (gArray[i] != gArray2[j]) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean a(g g2, h h2) {
        if (h2 == null) {
            return false;
        }
        g[] gArray = h2.a;
        int n2 = gArray.length;
        for (int i = 0; i < n2; ++i) {
            if (gArray[i] != g2) continue;
            return true;
        }
        return false;
    }

    public static boolean b(h h2, h h3) {
        if (h3 == null) {
            return h2 == null || h2.b() == 0;
        }
        g[] gArray = h2.a;
        int n2 = gArray.length;
        g[] gArray2 = h3.a;
        int n3 = gArray2.length;
        for (int i = 0; i < n2; ++i) {
            boolean bl = false;
            for (int j = 0; j < n3; ++j) {
                if (gArray[i] != gArray2[j]) continue;
                bl = true;
                break;
            }
            if (bl) continue;
            return false;
        }
        return true;
    }
}
