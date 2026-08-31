/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.resources;
import com.corrodinggames.rts.game.units.custom.UnitParameter;

import android.graphics.Color;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.resources.CommandSlotBase;
import com.corrodinggames.rts.game.units.custom.effects.DataValue;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.ThemeColors;
import com.corrodinggames.rts.gameFramework.ui.UnitStateTracker;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class ResourceComponent
extends CommandSlotBase
implements Comparable {
    private static final com.corrodinggames.rts.game.units.custom.effects.EffectManager defaultCustomResources = new com.corrodinggames.rts.game.units.custom.effects.EffectManager().a();
    public static ResourceComponent a = ResourceComponent.a(0);
    public int b;
    public float c;
    public float d;
    public float e;
    public int ammo;
    public int setFlagMask;
    public int unsetFlagMask;
    public int requiredFlagMask;
    public int forbiddenFlagMask;
    public com.corrodinggames.rts.game.units.custom.effects.EffectManager customResources = defaultCustomResources;
    static final int displayColor = Color.a(255, 0, 100, 0);

    public int a() {
        return this.b;
    }

    public int isEnabled() {
        if (this.customResources == defaultCustomResources) {
            return this.b;
        }
        int n = this.b;
        int n2 = this.customResources.b.a;
        Object[] objectArray = this.customResources.b.a();
        for (int i2 = 0; i2 < n2; ++i2) {
            float f2;
            DataValue e2 = (DataValue) objectArray[i2];
            if (!(e2.amountValue > 0.0) || (f2 = e2.resourceTypeRef.b()) == 0.0f) continue;
            n += (int)((double)f2 * e2.amountValue);
        }
        return n;
    }

    public static ResourceComponent a(ResourceComponent b2, ResourceComponent b3) {
        ResourceComponent b4 = new ResourceComponent();
        b4.b = b2.b + b3.b;
        b4.c = b2.c + b3.c;
        b4.d = b2.d + b3.d;
        b4.e = b2.e + b3.e;
        b4.ammo = b2.ammo + b3.ammo;
        if (!b2.customResources.c() || !b3.customResources.c()) {
            b4.customResources = com.corrodinggames.rts.game.units.custom.effects.EffectManager.a(b2.customResources, b3.customResources);
        }
        return b4;
    }

    public static ResourceComponent a(ResourceComponent b2, float f2) {
        ResourceComponent b3 = new ResourceComponent();
        b3.b = (int)((float)b2.b * f2);
        b3.c = b2.c * f2;
        b3.d = b2.d * f2;
        b3.e = b2.e * f2;
        b3.ammo = (int)((float)b2.ammo * f2);
        if (!b2.customResources.c()) {
            b3.customResources = com.corrodinggames.rts.game.units.custom.effects.EffectManager.b(b2.customResources, f2);
        }
        return b3;
    }

    public static ResourceComponent a(int n) {
        ResourceComponent b2 = new ResourceComponent();
        b2.b = n;
        return b2;
    }

    public static ResourceComponent a(ModUnitRegistry l2, ab ab2, String string, String string2, boolean bl) throws bo {
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null && !bl) {
            throw new RuntimeException("Could not find " + string2 + " in configuration file under:" + string);
        }
        try {
            ResourceComponent b2 = isEnabled(l2, string3);
            return b2;
        }
        catch (bo bo2) {
            throw new bo("[" + string + "]" + string2 + ": " + bo2.getMessage());
        }
    }

    public static ResourceComponent a(ModUnitRegistry l2, ab ab2, String string, String string2, ResourceComponent b2) throws bo {
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null) {
            return b2;
        }
        try {
            ResourceComponent b3 = isEnabled(l2, string3);
            return b3;
        }
        catch (bo bo2) {
            throw new bo("[" + string + "]" + string2 + ": " + bo2.getMessage());
        }
    }

    public static ResourceComponent isEnabled(ModUnitRegistry l2, ab ab2, String string, String string2, ResourceComponent b2) throws bo {
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null) {
            return b2;
        }
        try {
            ResourceComponent b3 = isEnabled(l2, string3);
            return b3;
        }
        catch (bo bo2) {
            throw new bo("[" + string + "]" + string2 + ": " + bo2.getMessage());
        }
    }

    public static void isEnabled(int n) throws bo {
        if (n < 0 || n > 31) {
            throw new bo("Flag id must be between 0-31 (is:" + n + ")");
        }
    }

    public static int a(int n, String string) throws bo {
        if (string.contains("-")) {
            String[] stringArray = GameUtils.c(string, '-');
            if (stringArray.length != 2) {
                throw new bo("Unexpected flag id: " + string);
            }
            int n2 = Integer.parseInt(stringArray[0]);
            int n3 = Integer.parseInt(stringArray[1]);
            isEnabled(n2);
            isEnabled(n3);
            if (n3 < n2) {
                throw new bo("end<start in flag id: " + string);
            }
            for (int i2 = n2; i2 <= n3; ++i2) {
                n |= 1 << i2;
            }
            return n;
        }
        int n4 = Integer.parseInt(string);
        isEnabled(n4);
        return n |= 1 << n4;
    }

    public static ResourceComponent a(ModUnitRegistry l2, String string) throws bo {
        ResourceComponent b2 = isEnabled(l2, string);
        if (b2 != null && b2.ammo != 0) {
            throw new bo("Ammo not supported on streaming price:" + string);
        }
        return b2;
    }

    public static ResourceComponent isEnabled(ModUnitRegistry l2, String string) throws bo {
        if (string == null) {
            return a;
        }
        ResourceComponent b2 = new ResourceComponent();
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
                    b2.ammo = n3 = Integer.parseInt(string3);
                    continue;
                }
                if (string4.equals("hasFlag")) {
                    bl = true;
                    b2.requiredFlagMask = a(b2.requiredFlagMask, string3);
                    continue;
                }
                if (string4.equals("hasMissingFlag")) {
                    bl = true;
                    b2.forbiddenFlagMask = a(b2.forbiddenFlagMask, string3);
                    continue;
                }
                if (string4.equals("setFlag")) {
                    bl = true;
                    b2.setFlagMask = a(b2.setFlagMask, string3);
                    continue;
                }
                if (string4.equals("unsetFlag")) {
                    bl = true;
                    b2.unsetFlagMask = a(b2.unsetFlagMask, string3);
                    continue;
                }
                com.corrodinggames.rts.game.units.custom.effects.LogicBoolean a2 = l2.k(string4);
                if (a2 != null) {
                    float f5 = Float.parseFloat(string3);
                    if (b2.customResources == defaultCustomResources) {
                        b2.customResources = new com.corrodinggames.rts.game.units.custom.effects.EffectManager();
                    }
                    b2.customResources.a(a2, (double)f5);
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
        if (b2.customResources != defaultCustomResources) {
            b2.customResources.a();
        }
        if (!b2.isNonEmpty()) {
            return a;
        }
        return b2;
    }

    public int a(UnitInstance am2, boolean bl) {
        int n2;
        int n3 = 9999;
        if (!bl && this.b > 0) {
            n2 = (int)(am2.player.o / (double)this.b);
            n3 = GameUtils.c(n3, n2);
        }
        if (this.c > 0.0f) {
            n2 = (int)(am2.cB / this.c);
            n3 = GameUtils.c(n3, n2);
        }
        if (this.d > 0.0f) {
            n2 = (int)(am2.cu / this.d);
            n3 = GameUtils.c(n3, n2);
        }
        if (this.e > 0.0f) {
            n2 = (int)(am2.cx / this.e);
            n3 = GameUtils.c(n3, n2);
        }
        if (this.ammo > 0) {
            n2 = am2.cE / this.ammo;
            n3 = GameUtils.c(n3, n2);
        }
        if (!this.customResources.c()) {
            n2 = com.corrodinggames.rts.game.units.custom.effects.EffectManager.a(this.customResources, am2);
            n3 = GameUtils.c(n3, n2);
        }
        if (!this.checkFlagRequirements(am2)) {
            n3 = 0;
        }
        return n3;
    }


    public boolean isEnabled(UnitInstance am2, double d2) {
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
        if (this.ammo > 0 && (double)am2.cE < (double)this.ammo * d2) {
            return false;
        }
        if (!this.checkFlagRequirements(am2)) {
            return false;
        }
        return this.customResources.c() || com.corrodinggames.rts.game.units.custom.effects.EffectManager.a(this.customResources, am2, d2);
    }


    @Override
    public boolean b(UnitInstance am2) {
        return this.isEnabled(am2);
    }

    @Override
    public boolean b(UnitInstance am2, double d2) {
        return this.isEnabled(am2, d2);
    }

    public boolean isEnabled(UnitInstance am2) {
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
        if (this.ammo > 0 && am2.cE < this.ammo) {
            return false;
        }
        if (!this.checkFlagRequirements(am2)) {
            return false;
        }
        return this.customResources.c() || com.corrodinggames.rts.game.units.custom.effects.EffectManager.b(this.customResources, am2);
    }

    public boolean a(UnitInstance am2, UnitInstance am3) {
        boolean bl = false;
        if (!this.customResources.c() && com.corrodinggames.rts.game.units.custom.effects.EffectManager.a(this.customResources, am2, am3)) {
            bl = true;
        }
        return bl;
    }

    public static void isNonEmpty(UnitInstance am2) {
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

    public void hasSimpleCostOrFlags(UnitInstance am2) {
        if (this.unsetFlagMask != 0) {
            am2.cF &= ~this.unsetFlagMask;
        }
        if (this.setFlagMask != 0) {
            am2.cF |= this.setFlagMask;
        }
    }

    public int hasZeroCost(int n2) {
        if (this.unsetFlagMask != 0) {
            n2 &= ~this.unsetFlagMask;
        }
        if (this.setFlagMask != 0) {
            n2 |= this.setFlagMask;
        }
        return n2;
    }

    public static boolean a(int n2, int n3) {
        int n4 = 1 << n3;
        return (n2 & n4) != 0;
    }

    public boolean checkFlagRequirements(UnitInstance am2) {
        if (this.requiredFlagMask != 0 && !isEnabled(am2.cF, this.requiredFlagMask)) {
            return false;
        }
        return this.forbiddenFlagMask == 0 || !hasZeroCost(am2.cF, this.forbiddenFlagMask);
    }

    public static boolean isEnabled(int n2, int n3) {
        return (n3 & n2) == n3;
    }

    public static boolean hasZeroCost(int n2, int n3) {
        return (n3 & n2) != 0;
    }

    @Override
    public void a(UnitInstance am2) {
        am2.player.o -= (double)this.b;
        am2.cB -= this.c;
        am2.cu -= this.d;
        am2.cx -= this.e;
        am2.cE -= this.ammo;
        this.hasSimpleCostOrFlags(am2);
        if (!this.customResources.c()) {
            com.corrodinggames.rts.game.units.custom.effects.EffectManager.c(this.customResources, am2);
        }
        isNonEmpty(am2);
    }

    @Override
    public void a(UnitInstance am2, double d2) {
        am2.player.o -= (double)this.b * d2;
        am2.cB = (float)((double)am2.cB - (double)this.c * d2);
        am2.cu = (float)((double)am2.cu - (double)this.d * d2);
        am2.cx = (float)((double)am2.cx - (double)this.e * d2);
        am2.cE = (int)((double)am2.cE - (double)this.ammo * d2);
        this.hasSimpleCostOrFlags(am2);
        if (!this.customResources.c()) {
            com.corrodinggames.rts.game.units.custom.effects.EffectManager.do_b(this.customResources, am2, d2);
        }
        isNonEmpty(am2);
    }

    public void refundToUnit(UnitInstance am2) {
        if (this.b > 0) {
            am2.player.b(this.b);
        } else {
            am2.player.o += (double)this.b;
        }
        am2.cB += this.c;
        am2.cu += this.d;
        am2.cx += this.e;
        am2.cE += this.ammo;
        this.hasSimpleCostOrFlags(am2);
        if (!this.customResources.c()) {
            com.corrodinggames.rts.game.units.custom.effects.EffectManager.d(this.customResources, am2);
        }
        isNonEmpty(am2);
    }

    public void addToUnit(UnitInstance am2) {
        am2.player.o += (double)this.b;
        am2.cB += this.c;
        am2.cu += this.d;
        am2.cx += this.e;
        am2.cE += this.ammo;
        this.hasSimpleCostOrFlags(am2);
        if (!this.customResources.c()) {
            com.corrodinggames.rts.game.units.custom.effects.EffectManager.d(this.customResources, am2);
        }
        isNonEmpty(am2);
    }

    public void a(UnitInstance am2, double d2, boolean bl) {
        if (bl) {
            am2.player.o += (double)this.b * d2;
        }
        am2.cB = (float)((double)am2.cB + (double)this.c * d2);
        am2.cu = (float)((double)am2.cu + (double)this.d * d2);
        am2.cx = (float)((double)am2.cx + (double)this.e * d2);
        am2.cE = (int)((double)am2.cE + (double)this.ammo * d2);
        this.hasSimpleCostOrFlags(am2);
        if (!this.customResources.c()) {
            com.corrodinggames.rts.game.units.custom.effects.EffectManager.c(this.customResources, am2, d2);
        }
        isNonEmpty(am2);
    }

    public boolean hasZeroCost() {
        if (this == a) {
            return true;
        }
        if (this.b != 0 || this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.ammo != 0) {
            return false;
        }
        return this.customResources.c();
    }

    public boolean isNonEmpty() {
        if (this == a) {
            return false;
        }
        if (this.b != 0 || this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.ammo != 0) {
            return true;
        }
        if (this.setFlagMask != 0 || this.unsetFlagMask != 0 || this.requiredFlagMask != 0 || this.forbiddenFlagMask != 0) {
            return true;
        }
        return !this.customResources.c();
    }

    public boolean hasSimpleCostOrFlags() {
        if (this == a) {
            return false;
        }
        if (this.b != 0 || this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.ammo != 0) {
            return true;
        }
        return this.setFlagMask != 0 || this.unsetFlagMask != 0;
    }

    public String a(boolean bl, boolean bl2, int n2, boolean bl3) {
        ThemeColors ae2 = new ThemeColors();
        this.a(ae2, bl, bl2, n2, bl3);
        return ae2.a();
    }

    public void a(ThemeColors ae2, boolean bl, boolean bl2, int n2, boolean bl3, UnitInstance am2, int n3) {
        this.isEnabled(ae2, bl, bl2, n2, bl3, am2, n3);
    }

    private void a(ThemeColors ae2, boolean bl, boolean bl2, int n2, boolean bl3) {
        this.isEnabled(ae2, bl, bl2, n2, bl3, null, 0);
    }

    private void isEnabled(ThemeColors ae2, boolean bl, boolean bl2, int n2, boolean bl3, UnitInstance am2, int n3) {
        String string = bl ? "\n" : " | ";
        int n4 = 0;
        if (this.b > 0 && n4 < n2) {
            double d2;
            int n5 = displayColor;
            if (am2 != null && (d2 = am2.player.o) < (double)this.b) {
                n5 = n3;
            }
            ae2.a("$" + this.b + string, n5);
            ++n4;
        }
        if (bl2) {
            if (this.c > 0.0f && n4 < n2) {
                ae2.b(GameUtils.g(this.c) + " energy" + string);
                ++n4;
            }
            if (this.d > 0.0f && n4 < n2) {
                ae2.b(GameUtils.g(this.d) + " hp" + string);
                ++n4;
            }
            if (this.e > 0.0f && n4 < n2) {
                ae2.b(GameUtils.g(this.e) + " shield" + string);
                ++n4;
            }
            if (this.ammo > 0 && n4 < n2) {
                ae2.b(GameUtils.g((float)this.ammo) + " ammo" + string);
                ++n4;
            }
        }
        if (!this.customResources.c()) {
            this.customResources.a(ae2, bl, bl2, n2 - n4, bl3, false, am2, n3);
        }
        ae2.a(string);
    }

    public ResourceComponent getDeficit(UnitInstance am2) {
        ResourceComponent b2 = new ResourceComponent();
        if (this.b > 0 && am2.player.o < (double)this.b) {
            b2.b = this.b - (int)am2.player.o;
        }
        if (!this.customResources.c()) {
            b2.customResources = this.customResources.a(am2);
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
        if (!this.customResources.c() && (string = this.customResources.a(am2, string3, n2, bl)) != null) {
            if (string2 == null) {
                string2 = "";
            }
            string2 = string2 + string;
        }
        if (string2 == null) {
            return null;
        }
        string2 = GameUtils.a(string2, string3);
        return string2;
    }

    public int a(ResourceComponent b2) {
        return this.b - b2.b;
    }

    public static void a(OutputNetStream as2, ResourceComponent b2) {
        as2.a(b2 != null);
        if (b2 != null) {
            b2.a(as2);
        }
    }

    public void a(OutputNetStream as2) {
        boolean bl = false;
        boolean bl2 = false;
        if (this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.ammo != 0) {
            bl = true;
        }
        if (this.setFlagMask != 0 || this.unsetFlagMask != 0 || this.requiredFlagMask != 0 || this.forbiddenFlagMask != 0) {
            bl = true;
        }
        if (!this.customResources.c()) {
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
            as2.a(this.ammo);
            as2.a(this.setFlagMask);
            as2.a(this.unsetFlagMask);
            as2.a(this.requiredFlagMask);
            as2.a(this.forbiddenFlagMask);
        }
        if (bl2) {
            this.customResources.a(as2);
        }
    }

    public static ResourceComponent a(InputNetStream k2) {
        boolean bl = k2.readBoolean();
        if (bl) {
            return isEnabled(k2);
        }
        return null;
    }

    public static ResourceComponent isEnabled(InputNetStream k2) {
        ResourceComponent b2 = new ResourceComponent();
        byte by = k2.d();
        boolean bl = isEnabled(by, 1);
        boolean bl2 = isEnabled(by, 2);
        b2.b = k2.readInt();
        if (bl) {
            b2.c = k2.readFloat();
            b2.d = k2.readFloat();
            b2.e = k2.readFloat();
            b2.ammo = k2.readInt();
            b2.setFlagMask = k2.readInt();
            b2.unsetFlagMask = k2.readInt();
            b2.requiredFlagMask = k2.readInt();
            b2.forbiddenFlagMask = k2.readInt();
        }
        if (bl2) {
            b2.customResources = new com.corrodinggames.rts.game.units.custom.effects.EffectManager();
            b2.customResources.a(k2);
        }
        return b2;
    }

    public boolean isEnabled(UnitInstance am2, boolean bl) {
        if (this.hasZeroCost(am2, bl)) {
            this.isNonEmpty(am2, bl);
            return true;
        }
        return false;
    }

    public boolean hasZeroCost(UnitInstance am2, boolean bl) {
        if (this.b > 0 && !am2.player.a((double)this.b)) {
            return false;
        }
        if (bl) {
            return com.corrodinggames.rts.gameFramework.ui.UnitStateTracker.c(am2, this);
        }
        return this.isEnabled(am2);
    }

    public void isNonEmpty(UnitInstance am2, boolean bl) {
        am2.player.p -= (double)this.b;
        am2.player.q = 0;
        if (bl) {
            com.corrodinggames.rts.gameFramework.ui.UnitStateTracker.a(am2, this);
        }
    }

    public void hasSimpleCostOrFlags(UnitInstance am2, boolean bl) {
        am2.player.p += (double)this.b;
        am2.player.q = 0;
        if (bl) {
            com.corrodinggames.rts.gameFramework.ui.UnitStateTracker.b(am2, this);
        }
    }

    public static boolean isEnabled(ResourceComponent b2, ResourceComponent b3) {
        if (b3 == b2) {
            return true;
        }
        if (b3 == null || b2 == null) {
            return false;
        }
        return b3.isEnabled(b2);
    }

    public boolean isEnabled(ResourceComponent b2) {
        if (this.b != b2.b) {
            return false;
        }
        if (this.d != b2.d) {
            return false;
        }
        if (this.e != b2.e) {
            return false;
        }
        if (this.ammo != b2.ammo) {
            return false;
        }
        if (this.customResources.c() != b2.customResources.c()) {
            return false;
        }
        return this.customResources.c() || b2.customResources.c() || this.customResources.e(b2.customResources);
    }

    public boolean hasZeroCost(ResourceComponent b2) {
        if (this.b > 0 && b2.b > 0) {
            return true;
        }
        if (this.d > 0.0f && b2.d > 0.0f) {
            return true;
        }
        if (this.e > 0.0f && b2.e > 0.0f) {
            return true;
        }
        if (this.ammo > 0 && b2.ammo > 0) {
            return true;
        }
        return !this.customResources.c() && !b2.customResources.c() && this.customResources.f(b2.customResources);
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((ResourceComponent) object);
    }
}
