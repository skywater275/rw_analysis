/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import android.graphics.PointF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.ax;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.j;
import com.corrodinggames.rts.gameFramework.w;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.ListIterator;

public strictfp class aw
extends as {
    ByteArrayOutputStream a;
    PrintStream e;
    private PrintStream f;
    private LinkedList g = new LinkedList();

    @Override
    public void a() {
        ListIterator listIterator = this.g.listIterator(this.g.size());
        while (listIterator.hasPrevious()) {
            ax ax2 = (ax)listIterator.previous();
            ax2.a();
        }
        this.e.flush();
        if (this.a != null) {
            this.a.flush();
        }
    }

    @Override
    void b() {
        this.f = this.e;
    }

    public aw() {
        this.a = new ByteArrayOutputStream();
        this.e = new PrintStream(this.a);
        this.b();
    }

    public aw(PrintStream printStream) {
        this.e = printStream;
        this.b();
    }

    @Override
    public au b(int n2) {
        return this.a(n2, -1);
    }

    @Override
    public au a(int n2, int n3) {
        try {
            this.a();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        au au2 = new au(n2);
        au2.c = this.a.toByteArray();
        au2.d = n3;
        return au2;
    }

    @Override
    public String c() {
        try {
            this.a();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        return this.a.toString();
    }

    @Override
    public byte[] d() {
        try {
            this.a();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        return this.a.toByteArray();
    }

    @Override
    public void c(int n2) {
        this.f.println(n2);
    }

    @Override
    public void a(boolean bl) {
        this.f.println(bl);
    }

    @Override
    public void a(int n2) {
        this.f.println("#int:");
        this.f.println(n2);
    }

    @Override
    public void a(float f) {
        this.f.println("#writeFloat");
        this.f.println(f);
    }

    @Override
    public void a(long l) {
        this.f.println("#writeLong");
        this.f.println(l);
    }

    @Override
    public void b(String string) {
        this.a(string != null);
        if (string != null) {
            this.c(string);
        }
    }

    @Override
    public void c(String string) {
        this.f.println(string);
    }

    @Override
    public void a(w w2) {
        this.f.println("#writeGameObject:");
        if (w2 == null) {
            this.f.println(-1);
        } else {
            this.f.println(w2.eh);
        }
    }

    @Override
    public void b(w w2) {
        this.f.println("#writeExistingGameObject:");
        if (w2 != null && !w2.ej) {
            this.f.println(w2.eh);
            return;
        }
        this.f.println(-1);
    }

    @Override
    public void b(am am2) {
        if (am2 != null && !am2.ej && !am2.bV) {
            this.a((w)am2);
            return;
        }
        this.a((w)null);
    }

    @Override
    public void a(am am2) {
        if (am2 != null && !am2.ej) {
            this.a((w)am2);
            return;
        }
        this.a((w)null);
    }

    @Override
    public void a(y y2) {
        if (y2 != null && !y2.ej) {
            this.a((w)y2);
            return;
        }
        this.a((w)null);
    }

    @Override
    public void a(PointF pointF) {
        this.f.println("#PointF:");
        this.a(pointF != null);
        if (pointF != null) {
            this.a(pointF.a);
            this.a(pointF.b);
        }
    }

    @Override
    public void a(Enum enum_) {
        if (enum_ == null) {
            this.f.println("#Enum: null");
            this.f.println(-1);
        } else {
            this.f.println("#Enum:" + enum_.getClass().getSimpleName() + " : " + enum_.toString());
            this.f.println(enum_.ordinal());
        }
    }

    @Override
    public void a(com.corrodinggames.rts.game.units.as as2) {
        this.f.println("#unitType:");
        if (as2 == null) {
            this.f.println(-1);
        } else if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
            this.f.println(-2);
            this.c(((com.corrodinggames.rts.game.units.custom.l)as2).M);
        } else {
            this.f.println(((ar)as2).ordinal());
        }
    }

    @Override
    public void a(c c2) {
        if (c2 == null) {
            this.f.println(0);
        } else {
            this.f.println(c2.c);
        }
    }

    @Override
    public void a(n n2) {
        this.f.println("#team:");
        this.f.println(n2.k);
    }

    @Override
    public void a(File file) {
        try (j j2 = com.corrodinggames.rts.gameFramework.e.a.a(file);){
            this.a(j2, (int)file.length());
        }
    }

    @Override
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
                this.f.write(byArray, 0, n5);
                return;
            }
            this.f.write(byArray, 0, n3);
            n4 += n3;
        }
    }

    @Override
    public void a(ByteArrayOutputStream byteArrayOutputStream) {
        this.a(byteArrayOutputStream.size());
        byteArrayOutputStream.writeTo(this.f);
    }

    @Override
    public void a(byte[] byArray) {
        this.a(byArray.length);
        this.f.write(byArray);
    }

    @Override
    public void a(short s2) {
        this.f.println("#writeShort");
        this.f.println(s2);
    }

    @Override
    public void e() {
        this.f.println("#writeMark:");
        this.a((short)12345);
    }

    @Override
    public void d(String string) {
        this.f.println("#writeIfDebugOnly: " + string);
    }

    @Override
    public boolean f() {
        return true;
    }

    @Override
    public void e(String string) {
        this.a(string, false);
    }

    @Override
    public void a(String string, boolean bl2) {
        ax ax2 = new ax(bl2);
        ax2.b = string;
        this.g.add(ax2);
        this.f = ((ax)this.g.getLast()).d;
    }

    @Override
    public void a(String string) {
        ax ax2 = (ax)this.g.removeLast();
        if (!ax2.b.equals(string)) {
            l.b("OutputNetStream:endBlock", "Name does not match: expected" + string + " , got:" + ax2.b);
        }
        ax2.a();
        this.f = this.g.isEmpty() ? this.e : ((ax)this.g.getLast()).d;
        String string2 = "";
        String string3 = "";
        for (int i2 = 0; i2 < this.g.size(); ++i2) {
            string2 = string2 + ">";
            string3 = string3 + "<";
        }
        this.f.println(string2 + ">>>> Start of block: " + ax2.b);
        this.a(ax2.c);
        this.f.println(string3 + "<<<< End of block: " + ax2.b);
        ax2.b();
    }
}
