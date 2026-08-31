/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import android.graphics.PointF;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.NetworkPacket;
import com.corrodinggames.rts.gameFramework.network.TextStream;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.ListIterator;

public strictfp class ByteArrayPacketBuilder
extends OutputNetStream {
    ByteArrayOutputStream a;
    PrintStream e;
    private PrintStream f;
    private LinkedList g = new LinkedList();


    /* 02b aw.java L25: 覆写父类 a(); flush 抛 IOException 内部消化 (与 OutputNetStream.a() 一致) */
    public void a() {
        try {
            ListIterator listIterator = this.g.listIterator(this.g.size());
            while (listIterator.hasPrevious()) {
                TextStream ax2 = (TextStream) listIterator.previous();
                ax2.a();
            }
            this.e.flush();
            if (this.a != null) {
                this.a.flush();
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }


    void b() {
        this.f = this.e;
    }

    public ByteArrayPacketBuilder() {
        this.a = new ByteArrayOutputStream();
        this.e = new PrintStream(this.a);
        this.b();
    }

    public ByteArrayPacketBuilder(PrintStream printStream) {
        this.e = printStream;
        this.b();
    }


    public NetworkPacket b(int n2) {
        return this.a(n2, -1);
    }


    public NetworkPacket a(int n2, int n3) {
        try {
            this.a();
        }
        catch (RuntimeException iOException) {
            throw new RuntimeException(iOException);
        }
        NetworkPacket au2 = new NetworkPacket(n2);  // 02b j/au=NetworkPacket
        au2.packetData = this.a.toByteArray();  // 02b au.c
        au2.packetType = n3;  // 02b au.d
        return au2;
    }


    public String c() {
        try {
            this.a();
        }
        catch (RuntimeException iOException) {
            throw new RuntimeException(iOException);
        }
        return this.a.toString();
    }


    public byte[] d() {
        try {
            this.a();
        }
        catch (RuntimeException iOException) {
            throw new RuntimeException(iOException);
        }
        return this.a.toByteArray();
    }


    public void c(int n2) {
        this.f.println(n2);
    }


    public void a(boolean bl) {
        this.f.println(bl);
    }


    public void a(int n2) {
        this.f.println("#int:");
        this.f.println(n2);
    }


    public void a(float f) {
        this.f.println("#writeFloat");
        this.f.println(f);
    }


    public void a(long l) {
        this.f.println("#writeLong");
        this.f.println(l);
    }


    public void b(String string) {
        this.a(string != null);
        if (string != null) {
            this.c(string);
        }
    }


    public void c(String string) {
        this.f.println(string);
    }


    public void a(GameObject w2) {  // 02b aw L127: a(w) (ServerResult 为幻觉名)
        this.f.println("#writeGameObject:");
        if (w2 == null) {
            this.f.println(-1);
        } else {
            this.f.println(w2.eh);
        }
    }


    public void b(GameObject w2) {  // 02b L137: b(w)
        this.f.println("#writeExistingGameObject:");
        if (w2 != null && !w2.ej) {
            this.f.println(w2.eh);
            return;
        }
        this.f.println(-1);
    }


    public void b(UnitInstance am2) {  // 02b L146: b(am) (ConnectionState 为幻觉名)
        if (am2 != null && !am2.ej && !am2.isDead) {
            this.a((GameObject) am2);
            return;
        }
        this.a((GameObject) null);  // 02b L150/158/166: (w)null
    }


    public void a(UnitInstance am2) {  // 02b L154: a(am)
        if (am2 != null && !am2.ej) {
            this.a((GameObject) am2);
            return;
        }
        this.a((GameObject) null);  // 02b L150/158/166: (w)null
    }


    public void a(UnitType y2) {  // 02b L162: a(y)
        if (y2 != null && !y2.ej) {
            this.a((GameObject) y2);
            return;
        }
        this.a((GameObject) null);  // 02b L150/158/166: (w)null
    }


    public void a(PointF pointF) {
        this.f.println("#PointF:");
        this.a(pointF != null);
        if (pointF != null) {
            this.a(pointF.a);
            this.a(pointF.b);
        }
    }


    public void a(Enum enum_) {
        if (enum_ == null) {
            this.f.println("#Enum: null");
            this.f.println(-1);
        } else {
            this.f.println("#Enum:" + enum_.getClass().getSimpleName() + " : " + enum_.toString());
            this.f.println(enum_.ordinal());
        }
    }


    public void a(com.corrodinggames.rts.game.units.UnitTypeHandle as2) {
        this.f.println("#unitType:");
        if (as2 == null) {
            this.f.println(-1);
        } else if (as2 instanceof com.corrodinggames.rts.game.units.custom.ModUnitRegistry) {
            this.f.println(-2);
            this.c(((com.corrodinggames.rts.game.units.custom.ModUnitRegistry)as2).M);
        } else {
            this.f.println(((UnitRegistry) as2).ordinal());  // 02b L199: (ar)var1 (NetworkUtils 为幻觉名)
        }
    }


    public void a(PacketDecoder c2) {
        if (c2 == null) {
            this.f.println(0);
        } else {
            this.f.println(c2.c);
        }
    }


    public void a(PlayerState n2) {  // 02b L213: a(n)
        this.f.println("#team:");
        this.f.println(n2.k);
    }


    /* 02b aw.java L218: FileLoader.a(File) 抛 IOException → 内部消化 (覆写约束) */
    public void a(File file) {
        try {
            AssetStream j2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(file);  // 02b e/a.a(File)=AssetStream
            if (j2 == null) {
                throw new RuntimeException("Failed to read save file data");
            }
            try {
                this.a(j2, (int)file.length());
            }
            finally {
                try {
                    j2.close();
                }
                catch (IOException iOException) {
                    throw new RuntimeException(iOException);
                }
            }
        }
        catch (RuntimeException iOException) {
            throw new RuntimeException(iOException);
        }
    }


    /* 02b aw.java L229: read/write 抛 IOException → 内部消化 (覆写约束) */
    public void a(InputStream inputStream, int n2) {
        try {
            int n3;
            int n4 = 0;
            this.a(n2);
            byte[] byArray = new byte[16384];
            while ((n3 = inputStream.read(byArray, 0, byArray.length)) != -1) {
                if (n4 + n3 > n2) {
                    int n5 = n2 - n4;
                    if (n5 < 0) {
                        NetEngine.g("writeStream: bytesTillFull is " + n5);  // 02b ad.g (desync 日志)
                        return;
                    }
                    this.f.write(byArray, 0, n5);
                    return;
                }
                this.f.write(byArray, 0, n3);
                n4 += n3;
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }


    /* 02b aw.java 对应: writeTo 抛 IOException → 内部消化 */
    public void a(ByteArrayOutputStream byteArrayOutputStream) {
        this.a(byteArrayOutputStream.size());
        try {
            byteArrayOutputStream.writeTo(this.f);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }


    /* 02b aw.java 对应: write 抛 IOException → 内部消化 */
    public void a(byte[] byArray) {
        this.a(byArray.length);
        try {
            this.f.write(byArray);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }


    public void a(short s2) {
        this.f.println("#writeShort");
        this.f.println(s2);
    }


    public void e() {
        this.f.println("#writeMark:");
        this.a((short)12345);
    }


    public void d(String string) {
        this.f.println("#writeIfDebugOnly: " + string);
    }


    public boolean f() {
        return true;
    }


    public void e(String string) {
        this.a(string, false);
    }


    public void a(String string, boolean bl2) {
        TextStream ax2 = new TextStream(bl2);
        ax2.fileName = string;
        this.g.add(ax2);
        this.f = ((TextStream) this.g.getLast()).printStream;  // 02b ax.d=03 printStream
    }


    public void a(String string) {
        TextStream ax2 = (TextStream) this.g.removeLast();
        if (!ax2.fileName.equals(string)) {
            GlobalState.b("OutputNetStream:endBlock", "Name does not match: expected" + string + " , got:" + ax2.fileName);
        }
        try {
            ax2.a();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.f = this.g.isEmpty() ? this.e : ((TextStream) this.g.getLast()).printStream;
        String string2 = "";
        String string3 = "";
        for (int i2 = 0; i2 < this.g.size(); ++i2) {
            string2 = string2 + ">";
            string3 = string3 + "<";
        }
        this.f.println(string2 + ">>>> Start of block: " + ax2.fileName);
        this.a(ax2.c);
        this.f.println(string3 + "<<<< End of block: " + ax2.fileName);
        try {
            ax2.b();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }
}
