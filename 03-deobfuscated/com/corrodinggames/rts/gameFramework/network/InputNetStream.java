/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.gameFramework.pathfinding.PathFinder;
import com.corrodinggames.rts.gameFramework.network.ServerResult;
import com.corrodinggames.rts.gameFramework.KeyBinding;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.WorldGenerator;
import com.corrodinggames.rts.gameFramework.core.PlatformBackend;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.gameFramework.GameTimer;

import android.graphics.PointF;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.NetworkPacket;
import com.corrodinggames.rts.gameFramework.network.PacketType;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public strictfp class InputNetStream {
    ByteArrayInputStream a;
    private DataInputStream e;
    private DataInputStream f;
    private LinkedList g = new LinkedList();
    int b = 999999;
    int c = 999999;
    int d = 0;

    void a() {
        this.f = this.e;
    }

    public InputNetStream(NetworkPacket au2) {
        this.a = new ByteArrayInputStream(au2.packetData);
        this.e = new DataInputStream(this.a);
        this.a();
    }

    public InputNetStream(DataInputStream dataInputStream) {
        this.e = dataInputStream;
        this.a();
    }

    public InputNetStream(String string) {
        this.a = new ByteArrayInputStream(string.getBytes());
        this.e = new DataInputStream(this.a);
        this.a();
    }

    public InputNetStream(byte[] byArray) {
        this.a = new ByteArrayInputStream(byArray);
        this.e = new DataInputStream(this.a);
        this.a();
    }

    public void a(int n2) {
        this.b = n2;
    }

    public int b() {
        return this.b;
    }

    public void b(int n2) {
        this.c = n2;
    }

    public int c() {
        return this.c;
    }

    public byte d() {
        try {
                return this.f.readByte();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public boolean e() {  // 02b j/k.e(): readBoolean
        try {
                return this.f.readBoolean();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public int f() {  // 02b j/k.f(): readInt
        try {
                return this.f.readInt();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public boolean readBoolean() {
        try {
                return this.f.readBoolean();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public int readInt() {
        try {
                return this.f.readInt();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public float readFloat() {
        try {
                return this.f.readFloat();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public double h() {
        try {
                return this.f.readDouble();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public long i() {
        try {
                return this.f.readLong();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public String j() {
        if (!this.readBoolean()) {
            return null;
        }
        return this.readString();
    }

    public Integer k() {
        if (!this.readBoolean()) {
            return null;
        }
        return this.readInt();
    }

    public String readString() {
        try {
                String string = this.f.readUTF();
                return string;
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public com.corrodinggames.rts.game.units.custom.TeamTag m() {  // 02b L105: m() custom.g
        try {
                String string = this.f.readUTF();
                if (string.equals("")) {
                    return null;
                }
                return com.corrodinggames.rts.game.units.custom.TeamTag.intern(string);  // 02b: custom.g.c(var1)=03 intern
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public long n() {
        try {
                long l = this.f.readLong();
                return l;
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }


    public com.corrodinggames.rts.gameFramework.GameObject a(Class clazz) {  // javap 铁证: a(Class)→w (GameObject 为幻觉名)
        try {
                long l2 = this.f.readLong();
                return com.corrodinggames.rts.gameFramework.GameObject.a(l2, clazz, false);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2, Class clazz) {  // javap: a(m,Class) m=utility.m (TypedObjectList 为幻觉名)
        int n2 = this.readInt();
        for (int i = 0; i < n2; ++i) {
            com.corrodinggames.rts.gameFramework.GameObject w2 = this.a(clazz);
            if (w2 == null) continue;
            m2.add(w2);
        }
    }

    public UnitInstance o() {
        return this.a(PacketType.b);  // 02b: m.b = j.m 枚举常量
    }

    public com.corrodinggames.rts.game.units.UnitInstance a(PacketType m2) {  // javap: a(j.m)→am
        try {
                long l2 = this.f.readLong();
                boolean bl = m2 == PacketType.a;  // 02b: m.a
                return com.corrodinggames.rts.gameFramework.GameObject.a(l2, bl);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public com.corrodinggames.rts.game.units.UnitType p() {  // javap: p()→y
        try {
                long l2 = this.f.readLong();
                return com.corrodinggames.rts.gameFramework.GameObject.b(l2, false);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public Enum b(Class clazz) {
        try {
                int n2 = this.f.readInt();
                if (n2 == -1) {
                    return null;
                }
                Object[] TArray = clazz.getEnumConstants();
                if (n2 < 0 || n2 >= TArray.length) {
                    com.corrodinggames.rts.gameFramework.network.NetEngine.registerRelayServer("readEnum:" + n2 + " is out of range for " + clazz.toString(), false);
                    return null;
                }
                return (Enum)TArray[n2];
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public com.corrodinggames.rts.game.units.UnitTypeHandle q() {  // 02 铁证: j.k q():Lunits/as;
        try {
                int n2 = this.f.readInt();
                if (n2 == -1) {
                    return null;
                }
                if (n2 == -2) {
                    com.corrodinggames.rts.game.units.UnitTypeHandle as2;  // 02b: as var4
                    String string = this.readString();
                    com.corrodinggames.rts.game.units.custom.ModUnitRegistry l2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.n(string);
                    if (l2 == null) {
                        com.corrodinggames.rts.gameFramework.network.NetEngine.registerRelayServer("readUnitType: Could not find customUnitMetadata:" + string, false);
                    }
                    if ((as2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.getCreditCost(l2)) != null) {
                        if (as2 instanceof com.corrodinggames.rts.game.units.custom.ModUnitRegistry) {
                            l2 = (com.corrodinggames.rts.game.units.custom.ModUnitRegistry)as2;
                        } else {
                            com.corrodinggames.rts.gameFramework.GlobalState.b("replacement not a custom unit:" + as2.i());
                        }
                    }
                    return l2;
                }
                Object[] TArray = com.corrodinggames.rts.game.units.UnitRegistry.class.getEnumConstants();  // 02b: units.ar
                if (n2 < 0 || n2 >= TArray.length) {
                    com.corrodinggames.rts.gameFramework.network.NetEngine.registerRelayServer("readUnitType:" + n2 + " is out of range for UnitType", false);
                    return null;
                }
                return (com.corrodinggames.rts.game.units.UnitTypeHandle) TArray[n2];  // 02: (as)var2[var1]
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public PlayerState r() {
        try {
                byte by = this.f.readByte();
                com.corrodinggames.rts.game.PlayerState n2 = com.corrodinggames.rts.game.PlayerState.k(by);
                if (n2 == null) {
                    throw new IOException("Error loading save data, could not find referenced team:" + by + "");
                }
                return n2;
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public com.corrodinggames.rts.game.PlayerState s() {  // 02b L204: s() game.n
        try {
                byte by = this.f.readByte();
                return com.corrodinggames.rts.game.PlayerState.k(by);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public byte[] t() {
        try {
                int n2;
                int n3 = this.readInt();
                byte[] byArray = new byte[n3];
                for (int i = 0; i < n3 && (n2 = this.f.read(byArray, i, n3 - i)) != -1; i += n2) {
                }
                return byArray;
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public InputNetStream u() {
        byte[] byArray = this.t();
        return new InputNetStream(byArray);
    }

    public short v() {
        try {
                return this.f.readShort();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(String string) {
        short s2 = this.v();
        if (s2 != 12345) {
            com.corrodinggames.rts.gameFramework.network.NetEngine.registerRelayServer("Mark wasn't read for:" + string, false);
            if (GlobalState.B().aa()) {
                throw new RuntimeException("Mark wasn't read for:" + string);
            }
        }
    }

    public InputStream w() {
        return this.f;
    }

    public void b(String string) {
        this.a(string, false);
    }

    public String x() {
        return this.a(false, false);
    }

    public void a(String string, boolean bl) {
        this.a(string, bl, false);
    }

    public void a(String string, boolean bl, boolean bl2) {
        if (this.b < 11) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping start block:" + string);
            return;
        }
        String string2 = this.a(bl, bl2);
        if (!string2.equals(string)) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("InputNetStream:endBlock", "Name does not match: expected:" + string + " , got:" + string2);
        }
    }

    public byte[] c(String string) {
        try {
                String string2 = this.f.readUTF();
                if (!string2.equals(string)) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("getBlockRaw", "Name does not match: expected:" + string + " , got:" + string2);
                }
                byte[] byArray = this.t();
                return byArray;
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public String a(boolean bl, boolean bl2) {
        try {
                if (this.b < 11) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping start block: x()");
                    return "<skipped>";
                }
                String string = this.f.readUTF();
                byte[] byArray = this.t();
                com.corrodinggames.rts.gameFramework.network.l l2 = new com.corrodinggames.rts.gameFramework.network.l(byArray, bl, bl2);
                l2.a = string;
                this.g.add(l2);
                this.f = ((com.corrodinggames.rts.gameFramework.network.l)this.g.getLast()).c;
                return string;
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public PointF y() {
        if (!this.readBoolean()) {
            return null;
        }
        PointF pointF = new PointF();
        pointF.a = this.readFloat();
        pointF.b = this.readFloat();
        return pointF;
    }


   // 02b j/k.d(String) L298-314: 结束块校验 (与 a(String,boolean,boolean) 对称; network.l 块类 03 已有)
   public void d(String string) {
      if (this.b < 11) {
         com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping end block:" + string);
      } else {
         com.corrodinggames.rts.gameFramework.network.l l2 = (com.corrodinggames.rts.gameFramework.network.l)this.g.removeLast();
         if (!l2.a.equals(string)) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("InputNetStream:endBlock", "Name does not match: expected" + string + " ," + l2.a);
         }
         if (this.g.isEmpty()) {
            this.f = this.e;
         } else {
            this.f = ((com.corrodinggames.rts.gameFramework.network.l)this.g.getLast()).c;
         }
      }
   }
}
