/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import android.graphics.PointF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.at;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.j;
import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.w;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.zip.DataFormatException;

public strictfp class as {
    ByteArrayOutputStream b;
    DataOutputStream c;
    private DataOutputStream a;
    private LinkedList e = new LinkedList();
    public int d = 999999;

    public void a() {
        ListIterator listIterator = this.e.listIterator(this.e.size());
        while (listIterator.hasPrevious()) {
            at at2 = (at)listIterator.previous();
            at2.a();
        }
        this.c.flush();
        if (this.b != null) {
            this.b.flush();
        }
    }

    void b() {
        this.a = this.c;
    }

    public as(int n2) {
        this();
        this.d = n2;
    }

    public as() {
        this.b = new ByteArrayOutputStream();
        this.c = new DataOutputStream(this.b);
        this.b();
    }

    public as(DataOutputStream dataOutputStream) {
        this.c = dataOutputStream;
        this.b();
    }

    public au b(int n2) {
        return this.a(n2, -1);
    }

    public au a(int n2, int n3) {
        try {
            this.a();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        au au2 = new au(n2);
        au2.c = this.b.toByteArray();
        au2.d = n3;
        return au2;
    }

    public String c() {
        try {
            this.a();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        return this.b.toString();
    }

    public byte[] d() {
        try {
            this.a();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        return this.b.toByteArray();
    }

    public void c(int n2) {
        this.a.writeByte(n2);
    }

    public void a(boolean bl) {
        this.a.writeBoolean(bl);
    }

    public void a(int n2) {
        this.a.writeInt(n2);
    }

    public void a(float f) {
        this.a.writeFloat(f);
    }

    public void a(double d) {
        this.a.writeDouble(d);
    }

    public void a(long l) {
        this.a.writeLong(l);
    }

    public void b(String string) {
        this.a(string != null);
        if (string != null) {
            this.c(string);
        }
    }

    public void a(Integer n2) {
        this.a(n2 != null);
        if (n2 != null) {
            this.a((int)n2);
        }
    }

    public void c(String string) {
        this.a.writeUTF(string);
    }

    public void a(g g2) {
        if (g2 == null) {
            this.a.writeUTF("");
        }
        this.a.writeUTF(g2.toString());
    }

    public void a(w w2) {
        if (w2 == null) {
            this.a.writeLong(-1L);
        } else {
            this.a.writeLong(w2.eh);
        }
    }

    public void b(w w2) {
        if (w2 != null && !w2.ej) {
            this.a.writeLong(w2.eh);
            return;
        }
        this.a.writeLong(-1L);
    }

    public void a(m m2) {
        if (m2 == null) {
            this.a(0);
            return;
        }
        this.a(m2.size());
        for (Object e : m2) {
            w w2 = (w)e;
            this.b(w2);
        }
    }

    public void b(am am2) {
        if (am2 != null && !am2.ej && !am2.bV) {
            this.a((w)am2);
            return;
        }
        this.a((w)null);
    }

    public void a(am am2) {
        if (am2 != null && !am2.ej) {
            this.a((w)am2);
            return;
        }
        this.a((w)null);
    }

    public void a(y y2) {
        if (y2 != null && !y2.ej) {
            this.a((w)y2);
            return;
        }
        this.a((w)null);
    }

    public void a(PointF pointF) {
        this.a(pointF != null);
        if (pointF != null) {
            this.a(pointF.a);
            this.a(pointF.b);
        }
    }

    public void a(Enum enum_) {
        if (enum_ == null) {
            this.a.writeInt(-1);
        } else {
            this.a.writeInt(enum_.ordinal());
        }
    }

    public void a(com.corrodinggames.rts.game.units.as as2) {
        if (as2 == null) {
            this.a.writeInt(-1);
        } else if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
            this.a.writeInt(-2);
            this.c(((com.corrodinggames.rts.game.units.custom.l)as2).M);
        } else {
            this.a.writeInt(((ar)as2).ordinal());
        }
    }

    public void a(c c2) {
        if (c2 == null) {
            this.a.writeInt(0);
        } else {
            this.a.writeInt(c2.c);
        }
    }

    public void a(n n2) {
        this.a.writeByte(n2.k);
    }

    public void a(File file) {
        j j2 = com.corrodinggames.rts.gameFramework.e.a.a(file);
        if (j2 == null) {
            throw new IOException("Failed to read save file data");
        }
        try {
            this.a(j2, (int)file.length());
        }
        finally {
            if (j2 != null) {
                ((InputStream)j2).close();
            }
        }
    }

    public void a(k k2) {
        InputStream inputStream = k2.w();
        try {
            inputStream.reset();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        this.a(inputStream, inputStream.available());
    }

    public void a(InputStream inputStream, int n2) {
        int n3;
        int n4 = 0;
        this.a(n2);
        byte[] byArray = new byte[16384];
        while ((n3 = inputStream.read(byArray, 0, byArray.length)) != -1) {
            if (n4 + n3 > n2) {
                int n5 = n2 - n4;
                if (n5 < 0) {
                    ad.g("writeStream: bytesTillFull is " + n5);
                    return;
                }
                this.a.write(byArray, 0, n5);
                return;
            }
            this.a.write(byArray, 0, n3);
            n4 += n3;
        }
    }

    public void a(ByteArrayOutputStream byteArrayOutputStream) {
        this.a(byteArrayOutputStream.size());
        byteArrayOutputStream.writeTo(this.a);
    }

    public void a(byte[] byArray) {
        this.a(byArray.length);
        this.a.write(byArray);
    }

    public void b(byte[] byArray) {
        this.a.write(byArray);
    }

    public void a(short s2) {
        this.a.writeShort(s2);
    }

    public void e() {
        this.a((short)12345);
    }

    public void d(String string) {
    }

    public boolean f() {
        return false;
    }

    public void e(String string) {
        this.a(string, false);
    }

    public void a(String string, boolean bl2) {
        at at2 = new at(bl2);
        at2.c = string;
        this.e.add(at2);
        this.a = ((at)this.e.getLast()).e;
    }

    public void a(String string) {
        at at2 = (at)this.e.removeLast();
        if (!at2.c.equals(string)) {
            l.b("OutputNetStream:endBlock", "Name does not match: expected" + string + " , got:" + at2.c);
        }
        at2.a();
        this.a = this.e.isEmpty() ? this.c : ((at)this.e.getLast()).e;
        this.a.writeUTF(at2.c);
        this.a(at2.d);
        try {
            at2.b();
        }
        catch (Exception exception) {
            if (exception instanceof DataFormatException) {
                if (!l.aZ) {
                    l.b("DataFormatException error calling streamBlock.close() (this is expected on android 4.4)");
                }
            }
            l.b("Error calling streamBlock.close() to clean up memory");
            exception.printStackTrace();
        }
    }

    public int g() {
        return this.d;
    }

    public void h() {
        this.c = null;
        this.a = null;
        try {
            if (this.b != null) {
                this.b.close();
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        this.b = null;
    }
}
