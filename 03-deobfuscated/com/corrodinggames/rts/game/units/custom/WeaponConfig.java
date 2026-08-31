/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.custom.WeaponConfig$1;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$10;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$11;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$12;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$13;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$14;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$15;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$16;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$17;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$18;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$19;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$2;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$3;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$4;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$5;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$6;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$7;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$8;
import com.corrodinggames.rts.game.units.custom.WeaponConfig$9;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.au;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;

public class WeaponConfig
implements Cloneable {
    public boolean a;
    public float b;
    public int targetFilter;
    public float minAttackRange;
    public float damageMultiplier = 1.0f;
    public float reloadMultiplier = 1.0f;
    public int bulletCount;
    public float aimingTime;
    public float cooldownTime;
    public float muzzleVelocity;
    public float recoilForce;
    public float turretTraverseSpeed;
    public boolean requiresManualFire;
    public int ammoPerBurst;
    public int maxAmmoCount;
    public float accuracySpread;
    public float projectileGravity;
    public float targetLeadTime;
    static LinkedHashMap s = new LinkedHashMap();
    static LinkedHashMap t;

    public WeaponConfig(boolean bl) {
        this.a = bl;
    }

    public static VariableScope$CachedWriter a(String string, ModUnitRegistry l2, String string2, String string3) {
        try {
            return VariableScope$CachedWriter.create(string, new au(l2));
        }
        catch (bo bo2) {
            throw new RuntimeException("[" + string2 + "]" + string3 + ": " + bo2.getMessage(), bo2);
        }
    }

    public WeaponConfig a() {  // 02b as.a() L70-78: clone
        try {
            WeaponConfig as2 = (WeaponConfig)super.clone();
            as2.a = false;
            return as2;
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new RuntimeException(cloneNotSupportedException);
        }
    }

    static void a(LinkedHashMap linkedHashMap, at at2) {  // 02b as.a(LinkedHashMap,at) L80-82
        linkedHashMap.put(at2.b, at2);
    }

    public WeaponConfig b() {  // 02b as.b() L84-88
        WeaponConfig as2 = this.a();
        as2.a = false;
        return as2;
    }

    public static at a(int n) {  // 02b as.a(int) L90-103
        Iterator iterator2 = s.values().iterator();
        while (iterator2.hasNext()) {
            at at2 = (at) iterator2.next();
            if (n == at2.a) {
                return at2;
            }
        }
        return null;
    }

    public static void a(CustomUnitType j2, WeaponConfig as2, at[] atArray) {  // 02b as.a(j,as,at[]) L105-119
        for (at at2 : atArray) {
            double d2;
            double d3 = at2.a(j2, j2.y);
            if (d3 == (d2 = at2.a(j2, as2))) continue;
            j2.dJ();
            at2.a(j2, d2);
        }
    }

    public static void a(CustomUnitType j2, WeaponConfig as2, ModUnitRegistry l2) {  // 02b as.a(j,as,l) L121-139
        boolean bl2;
        boolean bl3 = true;
        boolean bl4 = bl2 = as2 != l2.cL;
        if (!bl2) {
            return;
        }
        Iterator iterator3 = t.keySet().iterator();
        while (iterator3.hasNext()) {
            String string = (String) iterator3.next();
            double d2;
            at at2 = (at)t.get(string);
            double d3 = at2.a(j2, l2.cL);
            if (d3 == (d2 = at2.a(j2, as2))) continue;
            j2.dJ();
            at2.a(j2, d2);
        }
    }

    public static void a(WeaponConfig as2, CustomUnitType j2, com.corrodinggames.rts.gameFramework.network.OutputNetStream as3) throws IOException {  // 02b as.a(as,j,j.as) L141-183
        boolean bl2;
        ModUnitRegistry l2 = j2.x;
        boolean bl3 = bl2 = as2 != l2.cL;
        if (!bl2) {
            as3.a(true);
        } else {
            as3.a(false);
            short s2 = 0;
            for (Object object : t.keySet()) {
                double d2;
                Object object2 = (at)t.get(object);
                double d3 = ((at)object2).a(j2, l2.cL);
                if (d3 == (d2 = ((at)object2).a(j2, as2))) continue;
                s2 = (short)(s2 + 1);
            }
            as3.a(s2);
            int n2 = 0;
            for (Object object2 : t.keySet()) {
                double d4;
                at at2 = (at)t.get(object2);
                double d5 = at2.a(j2, l2.cL);
                if (d5 == (d4 = at2.a(j2, as2))) continue;
                if (s2 < ++n2) {
                    throw new IOException("numberOfChangedFields>fieldsWritten: " + s2 + ">" + n2);
                }
                as3.a((short)at2.a);
                as3.a(d4);
                as3.a(d5);
            }
        }
    }

    public static void a(CustomUnitType j2, com.corrodinggames.rts.gameFramework.network.InputNetStream k2, int n2) throws IOException {  // 02b as.a(j,j.k,int) L185-205
        ModUnitRegistry l2 = j2.x;
        boolean bl2 = k2.e();
        if (bl2) {
            return;
        }
        int n3 = k2.v();
        for (int i2 = 0; i2 < n3; ++i2) {
            short s2 = k2.v();
            double d2 = k2.h();
            double d3 = k2.h();
            at at2 = a(s2);
            if (at2 == null) {
                throw new IOException("Field " + s2 + " doesn't exist");
            }
            j2.dJ();
            at2.a(j2, d2);
        }
    }

    public static at[] a(ab ab2, String string, String string2, at[] atArray) {  // 02b as.a(ab,String,String,at[]) L207
        String string3 = ab2.b(string, string2, (String)null);
        try {
            return a(string3, atArray);
        }
        catch (RuntimeException runtimeException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": " + runtimeException.getMessage(), runtimeException);
        }
    }

    public static at[] a(String string, at[] atArray) {  // 02b as.a(String,at[]) L217
        if (string == null) {
            return atArray;
        }
        ArrayList<at> arrayList = new ArrayList<at>();
        for (String string2 : GameUtils.c(string, ',')) {
            string2 = string2.trim();
            at at2 = (at)t.get(string2 = string2.toLowerCase(Locale.ROOT));
            if (arrayList.contains(at2)) {
                throw new RuntimeException("Value: " + string2 + " is repeated");
            }
            if (at2 == null) {
                String string3 = "";
                Iterator iterator4 = t.keySet().iterator();
                while (iterator4.hasNext()) {
                    String string4 = (String) iterator4.next();
                    if (!string3.equals("")) {
                        string3 = string3 + ", ";
                    }
                    string3 = string3 + string4;
                }
                throw new RuntimeException("Unknown value: " + string2 + " (Expected: " + GameUtils.b(string3, 100) + ")");
            }
            arrayList.add(at2);
        }
        return arrayList.toArray(new at[0]);
    }

    public /* synthetic */ Object clone() {
        return this.a();
    }

    static {
        a(s, new WeaponConfig$1(s.size(), "mass"));
        a(s, new WeaponConfig$12(s.size(), "maxenergy"));
        a(s, new WeaponConfig$13(s.size(), "energy"));
        a(s, new WeaponConfig$14(s.size(), "maxhp"));
        a(s, new WeaponConfig$15(s.size(), "hp"));
        a(s, new WeaponConfig$16(s.size(), "maxshield"));
        a(s, new WeaponConfig$17(s.size(), "shield"));
        a(s, new WeaponConfig$18(s.size(), "shieldregen"));
        a(s, new WeaponConfig$19(s.size(), "armour"));
        a(s, new WeaponConfig$2(s.size(), "maxattackrange"));
        a(s, new WeaponConfig$3(s.size(), "shootdelaymultiplier"));
        a(s, new WeaponConfig$4(s.size(), "shootdamagemultiplier"));
        a(s, new WeaponConfig$5(s.size(), "movespeed"));
        a(s, new WeaponConfig$6(s.size(), "maxturnspeed"));
        a(s, new WeaponConfig$7(s.size(), "fogofwarsightrange"));
        a(s, new WeaponConfig$8(s.size(), "nanorange"));
        a(s, new WeaponConfig$9(s.size(), "selfregenrate"));
        a(s, new WeaponConfig$10(s.size(), "targetHeight"));
        a(s, new WeaponConfig$11(s.size(), "nanoFactorySpeed"));
        t = new LinkedHashMap();
        Iterator iterator5 = s.keySet().iterator();
        while (iterator5.hasNext()) {
            String string = (String) iterator5.next();
            if (!string.equals(string.toLowerCase(Locale.ROOT))) {
                throw new RuntimeException(string);
            }
            at at2 = (at)s.get(string);
            if (at2.b()) continue;
            t.put(string, at2);
        }
    }


   // 02b custom.as.c/g = int (javap); CustomTransportAction 重置统计使用
   public int c;
   public int g;
}
