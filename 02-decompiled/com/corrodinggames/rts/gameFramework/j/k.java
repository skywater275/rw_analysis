/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import android.graphics.PointF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.m;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public strictfp class k {
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

    public k(au au2) {
        this.a = new ByteArrayInputStream(au2.c);
        this.e = new DataInputStream(this.a);
        this.a();
    }

    public k(DataInputStream dataInputStream) {
        this.e = dataInputStream;
        this.a();
    }

    public k(String string) {
        this.a = new ByteArrayInputStream(string.getBytes());
        this.e = new DataInputStream(this.a);
        this.a();
    }

    public k(byte[] byArray) {
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
        return this.f.readByte();
    }

    public boolean e() {
        return this.f.readBoolean();
    }

    public int f() {
        return this.f.readInt();
    }

    public float g() {
        return this.f.readFloat();
    }

    public double h() {
        return this.f.readDouble();
    }

    public long i() {
        return this.f.readLong();
    }

    public String j() {
        if (!this.e()) {
            return null;
        }
        return this.l();
    }

    public Integer k() {
        if (!this.e()) {
            return null;
        }
        return this.f();
    }

    public String l() {
        String string = this.f.readUTF();
        return string;
    }

    public g m() {
        String string = this.f.readUTF();
        if (string.equals("")) {
            return null;
        }
        return com.corrodinggames.rts.game.units.custom.g.c(string);
    }

    public long n() {
        long l = this.f.readLong();
        return l;
    }

    public w a(Class clazz) {
        long l2 = this.f.readLong();
        return w.a(l2, clazz, false);
    }

    public void a(com.corrodinggames.rts.gameFramework.utility.m m2, Class clazz) {
        int n2 = this.f();
        for (int i = 0; i < n2; ++i) {
            w w2 = this.a(clazz);
            if (w2 == null) continue;
            m2.add(w2);
        }
    }

    public am o() {
        return this.a(m.b);
    }

    public am a(m m2) {
        long l2 = this.f.readLong();
        boolean bl = m2 == m.a;
        return w.a(l2, bl);
    }

    public y p() {
        long l2 = this.f.readLong();
        return w.b(l2, false);
    }

    public Enum b(Class clazz) {
        int n2 = this.f.readInt();
        if (n2 == -1) {
            return null;
        }
        T[] TArray = clazz.getEnumConstants();
        if (n2 < 0 || n2 >= TArray.length) {
            ad.g("readEnum:" + n2 + " is out of range for " + clazz.toString());
            return null;
        }
        return (Enum)TArray[n2];
    }

    public as q() {
        int n2 = this.f.readInt();
        if (n2 == -1) {
            return null;
        }
        if (n2 == -2) {
            as as2;
            String string = this.l();
            com.corrodinggames.rts.game.units.custom.l l2 = com.corrodinggames.rts.game.units.custom.l.n(string);
            if (l2 == null) {
                ad.g("readUnitType: Could not find customUnitMetadata:" + string);
            }
            if ((as2 = com.corrodinggames.rts.game.units.custom.l.c(l2)) != null) {
                if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
                    l2 = (com.corrodinggames.rts.game.units.custom.l)as2;
                } else {
                    l.b("replacement not a custom unit:" + as2.i());
                }
            }
            return l2;
        }
        T[] TArray = ar.class.getEnumConstants();
        if (n2 < 0 || n2 >= TArray.length) {
            ad.g("readUnitType:" + n2 + " is out of range for UnitType");
            return null;
        }
        return (ar)TArray[n2];
    }

    public n r() {
        byte by = this.f.readByte();
        n n2 = n.k(by);
        if (n2 == null) {
            throw new IOException("Error loading save data, could not find referenced team:" + by + "");
        }
        return n2;
    }

    public n s() {
        byte by = this.f.readByte();
        return n.k(by);
    }

    public byte[] t() {
        int n2;
        int n3 = this.f();
        byte[] byArray = new byte[n3];
        for (int i = 0; i < n3 && (n2 = this.f.read(byArray, i, n3 - i)) != -1; i += n2) {
        }
        return byArray;
    }

    public k u() {
        byte[] byArray = this.t();
        return new k(byArray);
    }

    public short v() {
        return this.f.readShort();
    }

    public void a(String string) {
        short s2 = this.v();
        if (s2 != 12345) {
            ad.g("Mark wasn't read for:" + string);
            if (l.B().aa()) {
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
            l.e("Skipping start block:" + string);
            return;
        }
        String string2 = this.a(bl, bl2);
        if (!string2.equals(string)) {
            l.b("InputNetStream:endBlock", "Name does not match: expected:" + string + " , got:" + string2);
        }
    }

    public byte[] c(String string) {
        String string2 = this.f.readUTF();
        if (!string2.equals(string)) {
            l.b("getBlockRaw", "Name does not match: expected:" + string + " , got:" + string2);
        }
        byte[] byArray = this.t();
        return byArray;
    }

    public String a(boolean bl, boolean bl2) {
        if (this.b < 11) {
            l.e("Skipping start block: startBlockAndGetName()");
            return "<skipped>";
        }
        String string = this.f.readUTF();
        byte[] byArray = this.t();
        com.corrodinggames.rts.gameFramework.j.l l2 = new com.corrodinggames.rts.gameFramework.j.l(byArray, bl, bl2);
        l2.a = string;
        this.g.add(l2);
        this.f = ((com.corrodinggames.rts.gameFramework.j.l)this.g.getLast()).c;
        return string;
    }

    public void d(String string) {
        if (this.b < 11) {
            l.e("Skipping end block:" + string);
            return;
        }
        com.corrodinggames.rts.gameFramework.j.l l2 = (com.corrodinggames.rts.gameFramework.j.l)this.g.removeLast();
        if (!l2.a.equals(string)) {
            l.b("InputNetStream:endBlock", "Name does not match: expected" + string + " ," + l2.a);
        }
        this.f = this.g.isEmpty() ? this.e : ((com.corrodinggames.rts.gameFramework.j.l)this.g.getLast()).c;
    }

    public PointF y() {
        if (!this.e()) {
            return null;
        }
        PointF pointF = new PointF();
        pointF.a = this.g();
        pointF.b = this.g();
        return pointF;
    }
}
