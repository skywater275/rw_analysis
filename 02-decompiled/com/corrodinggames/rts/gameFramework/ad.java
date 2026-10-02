/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.ac;
import com.corrodinggames.rts.gameFramework.af;
import com.corrodinggames.rts.gameFramework.ag;
import com.corrodinggames.rts.gameFramework.ah;
import com.corrodinggames.rts.gameFramework.ak;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$MissingKey;
import java.util.ArrayList;
import java.util.Locale;

public class ad {
    public String a;
    public boolean b = false;
    public ArrayList c = new ArrayList();
    public ArrayList d = new ArrayList();

    public boolean a() {
        for (af af2 : this.c) {
            if (af2.a != -1 || !af2.a()) continue;
            return true;
        }
        return false;
    }

    public boolean b() {
        for (af af2 : this.c) {
            if (af2 == null || af2.a != -1 || !af2.b()) continue;
            return true;
        }
        return false;
    }

    public String c() {
        for (af af2 : this.c) {
            if (af2 == null) continue;
            return af2.c().toUpperCase();
        }
        return "";
    }

    public af a(int n) {
        if (this.c.size() > n) {
            af af2 = (af)this.c.get(n);
            return af2;
        }
        return null;
    }

    public String b(int n) {
        if (this.c.size() > n) {
            af af2 = (af)this.c.get(n);
            if (af2 == null) {
                return "<null>";
            }
            return af2.c().toUpperCase();
        }
        return "";
    }

    public ad c(int n) {
        int n2 = 0;
        return this.a(n, 0, n2, false);
    }

    public ad a(int n, int n2, int n3, boolean bl) {
        ag ag2 = new ag();
        ag2.e = n;
        ag2.a = -1;
        ag2.b = n3;
        if (bl) {
            ag2.d = true;
        }
        if (this.c.size() <= n2) {
            this.c.add(new ak());
        }
        if (this.c.size() <= n2) {
            this.c.add(new ak());
        }
        this.c.set(n2, ag2);
        return this;
    }

    public ad a(String string) {
        return this.a(string, -1);
    }

    public ad a(String string, int n) {
        if (string == null) {
            throw new RuntimeException("key==null");
        }
        return this.a(-1, string, n);
    }

    public ad a(int n2, String string, int n3) {
        block10: {
            if (string == null) {
                throw new RuntimeException("key==null");
            }
            ag ag2 = new ag();
            ag2.a = n2;
            ag2.b = 0;
            if ((string = string.toLowerCase(Locale.ENGLISH)).contains("alt+")) {
                string = string.replace("alt+", "");
                ag2.b += 4;
            }
            if (string.contains("ctrl+")) {
                string = string.replace("ctrl+", "");
                ++ag2.b;
            }
            if (string.contains("shift+")) {
                string = string.replace("shift+", "");
                ag2.b += 2;
            }
            try {
                ag2.e = ac.d(string);
                if (n3 == -1) {
                    this.c.add(ag2);
                } else {
                    if (this.c.size() <= n3) {
                        this.c.add(new ak());
                    }
                    if (this.c.size() <= n3) {
                        this.c.add(new ak());
                    }
                    this.c.set(n3, ag2);
                }
            }
            catch (SlickToAndroidKeycodes$MissingKey slickToAndroidKeycodes$MissingKey) {
                slickToAndroidKeycodes$MissingKey.printStackTrace();
                l l2 = l.B();
                if (l2 == null) break block10;
                l2.a(slickToAndroidKeycodes$MissingKey.getMessage(), 1);
            }
        }
        return this;
    }

    public ad b(int n2, int n3, int n4, boolean bl) {
        ah ah2 = new ah();
        ah2.a = n2;
        ah2.e = n3;
        ah2.f = n4;
        ah2.g = bl;
        try {
            ah2.i = ah2.a(true);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            l.b("Failed to bind Axis:" + n4 + " on joystick:" + n3);
            return this;
        }
        this.c.add(ah2);
        return this;
    }

    public boolean d() {
        return false;
    }

    public String e() {
        return this.a.replace("-", "").replace("  ", " ").replace("  ", " ").replace(" ", "_").toLowerCase(Locale.ENGLISH);
    }
}
