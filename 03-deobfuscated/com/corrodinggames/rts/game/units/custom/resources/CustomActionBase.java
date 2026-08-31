/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.resources;

import android.graphics.Color;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.resources.CommandSlotBase;
import com.corrodinggames.rts.game.units.custom.anim.e;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.ui.ThemeColors;
import com.corrodinggames.rts.gameFramework.ui.UnitStateTracker;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class CustomActionBase
extends CommandSlotBase
implements Comparable {
    private static final com.corrodinggames.rts.game.units.custom.effects.EffectManager m = new com.corrodinggames.rts.game.units.custom.effects.EffectManager().a();  // 02b d/b.java L18: private static final f m — import custom/e/f 铁证 (anim.f 为误标)
    public static final CustomActionBase a = a(0);
    public int b;
    public float c;
    public float d;
    public float e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public com.corrodinggames.rts.game.units.custom.effects.EffectManager k = com.corrodinggames.rts.game.units.custom.effects.EffectManager.a;  // 02b javap: public e.f k
    static final int l = Color.a(255, 0, 100, 0);

    public CustomActionBase() {  // 02b d/b.java L33: public b() { this.k = m; }
        this.k = m;
    }


    public int a() {
        return this.b;
    }

    public int b() {
        if (this.k == m) {
            return this.b;
        }
        int n = this.b;
        int n2 = this.k.b.a;
        Object[] objectArray = this.k.b.a();
        for (int i = 0; i < n2; ++i) {
            float f2;
            e e2 = (e)objectArray[i];
            if (!(e2.b > 0.0) || (f2 = e2.a.b()) == 0.0f) continue;
            n += (int)((double)f2 * e2.b);
        }
        return n;
    }

    public static CustomActionBase a(CustomActionBase b2, CustomActionBase b3) {
        CustomActionBase b4 = new CustomActionBase();
        b4.b = b2.b + b3.b;
        b4.c = b2.c + b3.c;
        b4.d = b2.d + b3.d;
        b4.e = b2.e + b3.e;
        b4.f = b2.f + b3.f;
        if (!b2.k.c() || !b3.k.c()) {
            b4.k = com.corrodinggames.rts.game.units.custom.effects.EffectManager.a(b2.k, b3.k);
        }
        return b4;
    }

    public static CustomActionBase a(CustomActionBase b2, float f2) {
        CustomActionBase b3 = new CustomActionBase();
        b3.b = (int)((float)b2.b * f2);
        b3.c = b2.c * f2;
        b3.d = b2.d * f2;
        b3.e = b2.e * f2;
        b3.f = (int)((float)b2.f * f2);
        if (!b2.k.c()) {
            b3.k = com.corrodinggames.rts.game.units.custom.effects.EffectManager.b(b2.k, f2);
        }
        return b3;
    }

    public static CustomActionBase a(int n) {
        CustomActionBase b2 = new CustomActionBase();
        b2.b = n;
        return b2;
    }

    public static CustomActionBase a(ModUnitRegistry l2, ab ab2, String string, String string2, boolean bl) throws bo {
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null && !bl) {
            throw new RuntimeException("Could not find " + string2 + " in configuration file under:" + string);
        }
        try {
            CustomActionBase b2 = a(l2, string3);
            return b2;
        }
        catch (bo bo2) {
            throw new bo("[" + string + "]" + string2 + ": " + bo2.getMessage());
        }
    }

    public static CustomActionBase a(ModUnitRegistry l2, ab ab2, String string, String string2, CustomActionBase b2) throws bo {
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null) {
            return b2;
        }
        try {
            CustomActionBase b3 = a(l2, string3);
            return b3;
        }
        catch (bo bo2) {
            throw new bo("[" + string + "]" + string2 + ": " + bo2.getMessage());
        }
    }


    public static int a(int n, String string) throws bo {  // 02b d/b.java L145: a(int,String) flag id 解析
        if (string.contains("-")) {
            String[] stringArray = com.corrodinggames.rts.gameFramework.GameUtils.c(string, '-');
            if (stringArray.length != 2) {
                throw new bo("Unexpected flag id: " + string);
            }
            int n2 = Integer.parseInt(stringArray[0]);
            int n3 = Integer.parseInt(stringArray[1]);
            b(n2);
            b(n3);
            if (n3 < n2) {
                throw new bo("end<start in flag id: " + string);
            }
            for (int i = n2; i <= n3; ++i) {
                n |= 1 << i;
            }
            return n;
        }
        int n4 = Integer.parseInt(string);
        b(n4);
        return n |= 1 << n4;
    }

    public static void b(int n) throws bo {  // 02b d/b.java L139: void b(int) flag id 校验
        if (n < 0 || n > 31) {
            throw new bo("Flag id must be between 0-31 (is:" + n + ")");
        }
    }

    public static CustomActionBase b(ModUnitRegistry l2, String string) throws bo {
        CustomActionBase b2 = a(l2, string);
        if (b2 != null && b2.f != 0) {
            throw new bo("Ammo not supported on streaming price:" + string);
        }
        return b2;
    }

    public static CustomActionBase a(ModUnitRegistry l2, String string) throws bo {
        if (string == null) {
            return a;
        }
        CustomActionBase b2 = new CustomActionBase();
        for (String string2 : string.split(",|\\|")) {
            String string3;
            String string4;
            if ((string2 = string2.trim()).equals("")) continue;
            String[] stringArray = string2.split("=|:");
            if (stringArray.length == 1) {
                string4 = "credits";
                string3 = stringArray[0];
            } else if (stringArray.length == 2) {
                string4 = stringArray[0].trim();
                string3 = stringArray[1].trim();
            } else {
                throw new bo("Unknown price format:" + string);
            }
            boolean bl = false;
            try {
                if (string4.equals("credits")) {
                    int n2;
                    bl = true;
                    b2.b = n2 = Integer.parseInt(string3);
                    continue;
                }
                if (string4.equals("energy")) {
                    float f2;
                    b2.c = f2 = Float.parseFloat(string3);
                    continue;
                }
                if (string4.equals("hp")) {
                    float f3;
                    b2.d = f3 = Float.parseFloat(string3);
                    continue;
                }
                if (string4.equals("shield")) {
                    float f4;
                    b2.e = f4 = Float.parseFloat(string3);
                    continue;
                }
                if (string4.equals("ammo")) {
                    int n3;
                    bl = true;
                    b2.f = n3 = Integer.parseInt(string3);
                    continue;
                }
                if (string4.equals("hasFlag")) {
                    bl = true;
                    b2.i = a(b2.i, string3);
                    continue;
                }
                if (string4.equals("hasMissingFlag")) {
                    bl = true;
                    b2.j = a(b2.j, string3);
                    continue;
                }
                if (string4.equals("setFlag")) {
                    bl = true;
                    b2.g = a(b2.g, string3);
                    continue;
                }
                if (string4.equals("unsetFlag")) {
                    bl = true;
                    b2.h = a(b2.h, string3);
                    continue;
                }
                com.corrodinggames.rts.game.units.custom.effects.LogicBoolean a2 = l2.k(string4);  // 02b d/b.java L245: var0.k(var7) — e/a=LogicBoolean (anim.a 为幻觉)
                if (a2 != null) {
                    float f5 = Float.parseFloat(string3);
                    if (b2.k == m) {
                        b2.k = new com.corrodinggames.rts.game.units.custom.effects.EffectManager();
                    }
                    b2.k.a(a2, (double)f5);
                    continue;
                }
                throw new bo("Unknown price type:" + string4);
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
                String string5 = "Bad price number:" + string3 + " in " + string;
                if (bl) {
                    string5 = string5 + " (Hint: A whole number was expected)";
                }
                throw new bo(string5);
            }
        }
        if (b2.k != m) {
            b2.k.a();
        }
        if (!b2.d()) {
            return a;
        }
        return b2;
    }

    public int a(UnitInstance am2, boolean bl) {
        int n2;
        int n3 = 9999;
        if (!bl && this.b > 0) {
            n2 = (int)(am2.player.o / (double)this.b);
            n3 = com.corrodinggames.rts.gameFramework.GameUtils.c(n3, n2);
        }
        if (this.c > 0.0f) {
            n2 = (int)(am2.cB / this.c);
            n3 = com.corrodinggames.rts.gameFramework.GameUtils.c(n3, n2);
        }
        if (this.d > 0.0f) {
            n2 = (int)(am2.cu / this.d);
            n3 = com.corrodinggames.rts.gameFramework.GameUtils.c(n3, n2);
        }
        if (this.e > 0.0f) {
            n2 = (int)(am2.cx / this.e);
            n3 = com.corrodinggames.rts.gameFramework.GameUtils.c(n3, n2);
        }
        if (this.f > 0) {
            n2 = am2.cE / this.f;
            n3 = com.corrodinggames.rts.gameFramework.GameUtils.c(n3, n2);
        }
        if (!this.k.c()) {
            n2 = com.corrodinggames.rts.game.units.custom.effects.EffectManager.a(this.k, am2);
            n3 = com.corrodinggames.rts.gameFramework.GameUtils.c(n3, n2);
        }
        if (!this.f(am2)) {
            n3 = 0;
        }
        return n3;
    }


    public boolean b(UnitInstance am2, double d2) {
        if (this.b > 0 && !am2.player.a((double)this.b * d2)) {
            return false;
        }
        if (this.c > 0.0f && (double)am2.cB < (double)this.c * d2) {
            return false;
        }
        if (this.d > 0.0f && (double)am2.cu < (double)this.d * d2) {
            return false;
        }
        if (this.e > 0.0f && (double)am2.cx < (double)this.e * d2) {
            return false;
        }
        if (this.f > 0 && (double)am2.cE < (double)this.f * d2) {
            return false;
        }
        if (!this.f(am2)) {
            return false;
        }
        return this.k.c() || com.corrodinggames.rts.game.units.custom.effects.EffectManager.a(this.k, am2, d2);
    }


    public boolean b(UnitInstance am2) {
        if (this.b > 0 && !am2.player.a((double)this.b)) {
            return false;
        }
        if (this.c > 0.0f && am2.cB < this.c) {
            return false;
        }
        if (this.d > 0.0f && am2.cu < this.d) {
            return false;
        }
        if (this.e > 0.0f && am2.cx < this.e) {
            return false;
        }
        if (this.f > 0 && am2.cE < this.f) {
            return false;
        }
        if (!this.f(am2)) {
            return false;
        }
        return this.k.c() || com.corrodinggames.rts.game.units.custom.effects.EffectManager.b(this.k, am2);
    }

    public boolean a(UnitInstance am2, UnitInstance am3) {
        boolean bl = false;
        if (!this.k.c() && com.corrodinggames.rts.game.units.custom.effects.EffectManager.a(this.k, am2, am3)) {
            bl = true;
        }
        return bl;
    }

    public static void d(UnitInstance am2) {
        if (am2.cB < 0.0f) {
            am2.cB = 0.0f;
        }
        if (am2.cB > am2.bd()) {
            am2.cB = am2.bd();
        }
        if (am2.cx < 0.0f) {
            am2.cx = 0.0f;
        }
        if (am2.cx > am2.cA) {
            am2.cx = am2.cA;
        }
        if (am2.cu > am2.cv) {
            am2.cu = am2.cv;
        }
        if (am2.cE < 0) {
            am2.cE = 0;
        }
    }

    public void e(UnitInstance am2) {
        if (this.h != 0) {
            am2.cF &= ~this.h;
        }
        if (this.g != 0) {
            am2.cF |= this.g;
        }
    }

    public int c(int n2) {
        if (this.h != 0) {
            n2 &= ~this.h;
        }
        if (this.g != 0) {
            n2 |= this.g;
        }
        return n2;
    }

    public static boolean a(int n2, int n3) {
        int n4 = 1 << n3;
        return (n2 & n4) != 0;
    }

    public boolean f(UnitInstance am2) {
        if (this.i != 0 && !a(am2.cF, this.i)) {
            return false;
        }
        return this.j == 0 || !c(am2.cF, this.j);
    }

    public static boolean b(int n2, int n3) {
        return (n3 & n2) == n3;
    }

    public static boolean c(int n2, int n3) {
        return (n3 & n2) != 0;
    }

    @Override
    public void a(UnitInstance am2) {
        am2.player.o -= (double)this.b;
        am2.cB -= this.c;
        am2.cu -= this.d;
        am2.cx -= this.e;
        am2.cE -= this.f;
        this.e(am2);
        if (!this.k.c()) {
            com.corrodinggames.rts.game.units.custom.effects.EffectManager.c(this.k, am2);
        }
        d(am2);
    }

    @Override
    public void a(UnitInstance am2, double d2) {
        am2.player.o -= (double)this.b * d2;
        am2.cB = (float)((double)am2.cB - (double)this.c * d2);
        am2.cu = (float)((double)am2.cu - (double)this.d * d2);
        am2.cx = (float)((double)am2.cx - (double)this.e * d2);
        am2.cE = (int)((double)am2.cE - (double)this.f * d2);
        this.e(am2);
        if (!this.k.c()) {
            com.corrodinggames.rts.game.units.custom.effects.EffectManager.a(this.k, am2, d2);
        }
        d(am2);
    }

    public void g(UnitInstance am2) {
        if (this.b > 0) {
            am2.player.b((float)this.b);
        } else {
            am2.player.o += (double)this.b;
        }
        am2.cB += this.c;
        am2.cu += this.d;
        am2.cx += this.e;
        am2.cE += this.f;
        this.e(am2);
        if (!this.k.c()) {
            com.corrodinggames.rts.game.units.custom.effects.EffectManager.d(this.k, am2);
        }
        d(am2);
    }

    public void h(UnitInstance am2) {
        am2.player.o += (double)this.b;
        am2.cB += this.c;
        am2.cu += this.d;
        am2.cx += this.e;
        am2.cE += this.f;
        this.e(am2);
        if (!this.k.c()) {
            com.corrodinggames.rts.game.units.custom.effects.EffectManager.d(this.k, am2);
        }
        d(am2);
    }

    public void a(UnitInstance am2, double d2, boolean bl) {
        if (bl) {
            am2.player.o += (double)this.b * d2;
        }
        am2.cB = (float)((double)am2.cB + (double)this.c * d2);
        am2.cu = (float)((double)am2.cu + (double)this.d * d2);
        am2.cx = (float)((double)am2.cx + (double)this.e * d2);
        am2.cE = (int)((double)am2.cE + (double)this.f * d2);
        this.e(am2);
        if (!this.k.c()) {
            com.corrodinggames.rts.game.units.custom.effects.EffectManager.c(this.k, am2, d2);
        }
        d(am2);
    }

    public boolean c() {
        if (this == a) {
            return true;
        }
        if (this.b != 0 || this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.f != 0) {
            return false;
        }
        return this.k.c();
    }

    public boolean d() {
        if (this == a) {
            return false;
        }
        if (this.b != 0 || this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.f != 0) {
            return true;
        }
        if (this.g != 0 || this.h != 0 || this.i != 0 || this.j != 0) {
            return true;
        }
        return !this.k.c();
    }

    public boolean e() {
        if (this == a) {
            return false;
        }
        if (this.b != 0 || this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.f != 0) {
            return true;
        }
        return this.g != 0 || this.h != 0;
    }

    public String a(boolean bl, boolean bl2, int n2, boolean bl3) {
        ThemeColors ae2 = new ThemeColors();
        this.a(ae2, bl, bl2, n2, bl3);
        return ae2.a();
    }

    public void a(ThemeColors ae2, boolean bl, boolean bl2, int n2, boolean bl3, UnitInstance am2, int n3) {
        this.b(ae2, bl, bl2, n2, bl3, am2, n3);
    }

    private void a(ThemeColors ae2, boolean bl, boolean bl2, int n2, boolean bl3) {
        this.b(ae2, bl, bl2, n2, bl3, null, 0);
    }

    private void b(ThemeColors ae2, boolean bl, boolean bl2, int n2, boolean bl3, UnitInstance am2, int n3) {
        String string = bl ? "\n" : " | ";
        int n4 = 0;
        if (this.b > 0 && n4 < n2) {
            double d2;
            int n5 = l;
            if (am2 != null && (d2 = am2.player.o) < (double)this.b) {
                n5 = n3;
            }
            ae2.a("$" + this.b + string, n5);
            ++n4;
        }
        if (bl2) {
            if (this.c > 0.0f && n4 < n2) {
                ae2.b(com.corrodinggames.rts.gameFramework.GameUtils.g(this.c) + " energy" + string);
                ++n4;
            }
            if (this.d > 0.0f && n4 < n2) {
                ae2.b(com.corrodinggames.rts.gameFramework.GameUtils.g(this.d) + " hp" + string);
                ++n4;
            }
            if (this.e > 0.0f && n4 < n2) {
                ae2.b(com.corrodinggames.rts.gameFramework.GameUtils.g(this.e) + " shield" + string);
                ++n4;
            }
            if (this.f > 0 && n4 < n2) {
                ae2.b(com.corrodinggames.rts.gameFramework.GameUtils.g((float)this.f) + " ammo" + string);
                ++n4;
            }
        }
        if (!this.k.c()) {
            this.k.a(ae2, bl, bl2, n2 - n4, bl3, false, am2, n3);
        }
        ae2.a(string);
    }

    public CustomActionBase i(UnitInstance am2) {
        CustomActionBase b2 = new CustomActionBase();
        if (this.b > 0 && am2.player.o < (double)this.b) {
            b2.b = this.b - (int)am2.player.o;
        }
        if (!this.k.c()) {
            b2.k = this.k.a(am2);
        }
        return b2;
    }

    public String a(UnitInstance am2, int n2, boolean bl) {
        String string;
        String string2 = null;
        String string3 = ", ";
        int n3 = 0;
        if (this.b > 0 && n3 < n2 && am2.player.o < (double)this.b) {
            if (string2 == null) {
                string2 = "";
            }
            string2 = string2 + "credits" + string3;
            ++n3;
        }
        if (!this.k.c() && (string = this.k.a(am2, string3, n2, bl)) != null) {
            if (string2 == null) {
                string2 = "";
            }
            string2 = string2 + string;
        }
        if (string2 == null) {
            return null;
        }
        string2 = com.corrodinggames.rts.gameFramework.GameUtils.a(string2, string3);
        return string2;
    }

    public int a(CustomActionBase b2) {
        return this.b - b2.b;
    }

    public static void a(OutputNetStream as2, CustomActionBase b2) {
        as2.a(b2 != null);
        if (b2 != null) {
            b2.a(as2);
        }
    }

    public void a(OutputNetStream as2) {
        boolean bl = false;
        boolean bl2 = false;
        if (this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.f != 0) {
            bl = true;
        }
        if (this.g != 0 || this.h != 0 || this.i != 0 || this.j != 0) {
            bl = true;
        }
        if (!this.k.c()) {
            bl2 = true;
        }
        byte by = 0;
        if (bl) {
            by = (byte)(by | 1);
        }
        if (bl2) {
            by = (byte)(by | 2);
        }
        as2.c(by);
        as2.a(this.b);
        if (bl) {
            as2.a(this.c);
            as2.a(this.d);
            as2.a(this.e);
            as2.a(this.f);
            as2.a(this.g);
            as2.a(this.h);
            as2.a(this.i);
            as2.a(this.j);
        }
        if (bl2) {
            this.k.a(as2);
        }
    }

    public static CustomActionBase b(InputNetStream k2) {  // 02b d/b.java L666: b(k) 包装 (读 boolean)
        boolean bl = k2.readBoolean();
        if (bl) {
            return a(k2);
        }
        return null;
    }

    public static CustomActionBase a(InputNetStream k2) {
        CustomActionBase b2 = new CustomActionBase();
        byte by = k2.d();
        boolean bl = b(by, 1);
        boolean bl2 = b(by, 2);
        b2.b = k2.readInt();
        if (bl) {
            b2.c = k2.readFloat();
            b2.d = k2.readFloat();
            b2.e = k2.readFloat();
            b2.f = k2.readInt();
            b2.g = k2.readInt();
            b2.h = k2.readInt();
            b2.i = k2.readInt();
            b2.j = k2.readInt();
        }
        if (bl2) {
            b2.k = new com.corrodinggames.rts.game.units.custom.effects.EffectManager();
            b2.k.a(k2);
        }
        return b2;
    }

    public boolean b(UnitInstance am2, boolean bl) {
        if (this.c(am2, bl)) {
            this.d(am2, bl);
            return true;
        }
        return false;
    }

    public boolean c(UnitInstance am2, boolean bl) {
        if (this.b > 0 && !am2.player.g(this.b)) {
            return false;
        }
        if (bl) {
            return an_c(am2, this);
        }
        return this.b(am2);
    }

    public void d(UnitInstance am2, boolean bl) {
        am2.player.p -= (double)this.b;
        am2.player.q = 0;
        if (bl) {
            an_a(am2, this);
        }
    }

    public void e(UnitInstance am2, boolean bl) {
        am2.player.p += (double)this.b;
        am2.player.q = 0;
        if (bl) {
            an_b(am2, this);
        }
    }

    // 02b gameFramework/f/an.java (建造需求缓存 ao) 03 缺失 — 简化: 缓存跳过 (02b L81 无缓存路径语义)
    private static boolean an_c(UnitInstance am2, CustomActionBase b2) {  // 02b f/an.c
        return b2.b(am2);
    }

    private static void an_a(UnitInstance am2, CustomActionBase b2) {  // 02b f/an.a (缓存累加, 简化跳过)
    }

    private static void an_b(UnitInstance am2, CustomActionBase b2) {  // 02b f/an.b (缓存递减, 简化跳过)
    }


    public boolean c(CustomActionBase b2) {  // 02b d/b.java L730: c(b) 重叠需求检查
        if (this.b > 0 && b2.b > 0) {
            return true;
        }
        if (this.d > 0.0f && b2.d > 0.0f) {
            return true;
        }
        if (this.e > 0.0f && b2.e > 0.0f) {
            return true;
        }
        if (this.f > 0 && b2.f > 0) {
            return true;
        }
        return !this.k.c() && !b2.k.c() && this.k.f(b2.k);
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((CustomActionBase)object);
    }


    public boolean b(CustomActionBase b2) {  // 02b d/b.java L726: b(b) 实例字段比较
        if (this.b != b2.b) {
            return false;
        }
        if (this.d != b2.d) {
            return false;
        }
        if (this.e != b2.e) {
            return false;
        }
        if (this.f != b2.f) {
            return false;
        }
        if (this.k.c() != b2.k.c()) {
            return false;
        }
        return this.k.c() || b2.k.c() || this.k.e(b2.k);
    }

    public static boolean b(CustomActionBase b2, CustomActionBase b3) {  // 02b d/b.java L722: b(b,b) 静态
        if (b3 == b2) {
            return true;
        }
        if (b3 == null || b2 == null) {
            return false;
        }
        return b3.b(b2);
    }


    public static CustomActionBase b(com.corrodinggames.rts.game.units.custom.ModUnitRegistry modUnitRegistry, com.corrodinggames.rts.gameFramework.utility.ab ab2, String string, String string2, CustomActionBase customActionBase) throws bo {  // 02b d/b.java L125-137
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null) {
            return customActionBase;
        }
        try {
            return a(modUnitRegistry, string3);
        }
        catch (com.corrodinggames.rts.game.units.custom.bo bo2) {
            throw new com.corrodinggames.rts.game.units.custom.bo("[" + string + "]" + string2 + ": " + bo2.getMessage());
        }
    }

}