/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.gameFramework.pathfinding.PathFinder;
import com.corrodinggames.rts.gameFramework.network.SendWorker;
import com.corrodinggames.rts.gameFramework.RenderThread;
import com.corrodinggames.rts.gameFramework.network.ServerResult;
import com.corrodinggames.rts.gameFramework.ByteIndexedMap;
import com.corrodinggames.rts.gameFramework.KeyBinding;

import android.graphics.PointF;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.CompressedStream;
import com.corrodinggames.rts.gameFramework.network.NetworkPacket;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.zip.DataFormatException;

public strictfp class OutputNetStream {
    ByteArrayOutputStream b;
    DataOutputStream c;
    private DataOutputStream a;
    private LinkedList e = new LinkedList();
    public int d = 999999;

    public void a()  {
        try {
                ListIterator listIterator = this.e.listIterator(this.e.size());
                while (listIterator.hasPrevious()) {
                    CompressedStream at2 = (CompressedStream) listIterator.previous();
                    at2.a();
                }
                this.c.flush();
                if (this.b != null) {
                    this.b.flush();
                }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    void b() {
        this.a = this.c;
    }

    public OutputNetStream(int n2) {
        this();
        this.d = n2;
    }

    public OutputNetStream() {
        this.b = new ByteArrayOutputStream();
        this.c = new DataOutputStream(this.b);
        this.b();
    }

    public OutputNetStream(DataOutputStream dataOutputStream) {
        this.c = dataOutputStream;
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
        NetworkPacket au2 = new NetworkPacket(n2);  // 02b: au var3 = new au(var1), au=Packet
        au2.packetData = this.b.toByteArray();  // 02b as.java L79: au.c=packetData
        au2.packetType = n3;  // 02b as.java L80: au.d=packetType
        return au2;
    }

    public byte[] d() {
        try {
            this.a();
        }
        catch (RuntimeException iOException) {
            throw new RuntimeException(iOException);
        }
        return this.b.toByteArray();
    }

    public void c(int n2)  {
        try {
                this.a.writeByte(n2);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(boolean bl)  {
        try {
                this.a.writeBoolean(bl);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(int n2)  {
        try {
                this.a.writeInt(n2);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(float f)  {
        try {
                this.a.writeFloat(f);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(double d)  {
        try {
                this.a.writeDouble(d);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(long l)  {
        try {
                this.a.writeLong(l);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    // v19.112 补全 (02 铁证: j.as c(String) 纯 writeUTF / a(w) 写 eh long / b(am) null检查委托 a(w))
    public void c(String string) {
        this.writeByte(string);
    }

    public void writeByte(String string)  {
        try {
                this.a.writeUTF(string);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(com.corrodinggames.rts.gameFramework.GameObject w2)  {  // 02b j/as.java L156: a(w) (EffectConfig 为幻觉名)
        try {
                this.a.writeLong(w2 == null ? -1L : w2.eh);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }


    public void b(String string) {
        this.a(string != null);
        if (string != null) {
            this.writeByte(string);
        }
    }

    public void a(Integer n2) {
        this.a(n2 != null);
        if (n2 != null) {
            this.a((int)n2);
        }
    }

    public void a(com.corrodinggames.rts.game.units.custom.TeamTag g2)  {  // 02b j/as.java L148: a(custom.g)
        try {
                if (g2 == null) {
                    this.a.writeUTF("");
                }
                this.a.writeUTF(g2.toString());
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }


    public void b(com.corrodinggames.rts.gameFramework.GameObject w2)  {  // 02b L165: b(w) (EffectConfig 为幻觉名)
        try {
                if (w2 != null && !w2.ej) {
                    this.a.writeLong(w2.eh);
                    return;
                }
                this.a.writeLong(-1L);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2) {  // 02b L173: a(utility.m) (TypedObjectList 为幻觉名)
        if (m2 == null) {
            this.a(0);
            return;
        }
        this.a(m2.size());
        for (Object e : m2) {
            com.corrodinggames.rts.gameFramework.GameObject w2 = (com.corrodinggames.rts.gameFramework.GameObject) e;  // 02b: w var4 = (w)var3
            this.b(w2);
        }
    }

    public void b(com.corrodinggames.rts.game.units.UnitInstance am2) {  // 02b L189: b(am)
        if (am2 != null && !am2.ej && !am2.isDead) {
            this.a((com.corrodinggames.rts.gameFramework.GameObject) am2);  // 02b: (w)var1
            return;
        }
        this.a((com.corrodinggames.rts.gameFramework.GameObject) null);
    }

    public void a(com.corrodinggames.rts.game.units.UnitInstance am2) {  // 02b L197: a(am)
        if (am2 != null && !am2.ej) {
            this.a((com.corrodinggames.rts.gameFramework.GameObject) am2);  // 02b: (w)var1
            return;
        }
        this.a((com.corrodinggames.rts.gameFramework.GameObject) null);
    }

    public void a(com.corrodinggames.rts.game.units.UnitType y2) {  // 02b L205: a(y)
        if (y2 != null && !y2.ej) {
            this.a((com.corrodinggames.rts.gameFramework.GameObject) y2);
            return;
        }
        this.a((com.corrodinggames.rts.gameFramework.GameObject) null);
    }

    public void a(PointF pointF) {
        this.a(pointF != null);
        if (pointF != null) {
            this.a(pointF.a);
            this.a(pointF.b);
        }
    }

    public void a(Enum enum_)  {
        try {
                if (enum_ == null) {
                    this.a.writeInt(-1);
                } else {
                    this.a.writeInt(enum_.ordinal());
                }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(com.corrodinggames.rts.game.units.UnitTypeHandle as2)  {
        try {
                if (as2 == null) {
                    this.a.writeInt(-1);
                } else if (as2 instanceof com.corrodinggames.rts.game.units.custom.ModUnitRegistry) {
                    this.a.writeInt(-2);
                    this.writeByte(((com.corrodinggames.rts.game.units.custom.ModUnitRegistry)as2).M);
                } else {
                    this.a.writeInt(((com.corrodinggames.rts.game.units.UnitRegistry)as2).ordinal());  // javap 铁证: checkcast units.ar
                }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(PacketDecoder c2)  {
        try {
                if (c2 == null) {
                    this.a.writeInt(0);
                } else {
                    this.a.writeInt(c2.c);
                }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(com.corrodinggames.rts.game.PlayerState n2)  {  // 02b L252: a(game.n)
        try {
                this.a.writeByte(n2.k);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(File file) {
        AssetStream j2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(file);  // 02b as.java L257: utility.j=AssetStream
        if (j2 == null) {
            throw new RuntimeException("Failed to read save file data");
        }
        try {
            this.a(j2, (int)file.length());
        }
        finally {
            if (j2 != null) {
                try {
                    j2.close();  // 02b as.java L265
                }
                catch (IOException iOException) {
                    throw new RuntimeException(iOException);
                }
            }
        }
    }

    public void a(InputNetStream k2) {
        InputStream inputStream = k2.w();
        try {
            inputStream.reset();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        try {
            this.a(inputStream, inputStream.available());
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(InputStream inputStream, int n2)  {
        try {
                int n3;
                int n4 = 0;
                this.a(n2);
                byte[] byArray = new byte[16384];
                while ((n3 = inputStream.read(byArray, 0, byArray.length)) != -1) {
                    if (n4 + n3 > n2) {
                        int n5 = n2 - n4;
                        if (n5 < 0) {
                            com.corrodinggames.rts.gameFramework.network.NetEngine.registerRelayServer("writeStream: bytesTillFull is " + n5, false);  // javap: ad.g=a(String,false)
                            return;
                        }
                        this.a.write(byArray, 0, n5);
                        return;
                    }
                    this.a.write(byArray, 0, n3);
                    n4 += n3;
                }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(ByteArrayOutputStream byteArrayOutputStream) {
        this.a(byteArrayOutputStream.size());
        try {
            byteArrayOutputStream.writeTo(this.a);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(byte[] byArray)  {
        try {
                this.a(byArray.length);
                this.a.write(byArray);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void b(byte[] byArray)  {
        try {
                this.a.write(byArray);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    // v19.112d 补缺 (02b j/as.java L84/L325/L335/L332)
    public String c() {
        try {
            this.a();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return this.b.toString();
    }

    public void e() {
        this.a((short)12345);
    }

    /* 02b as.java L335: 调 a(String,boolean) 抛 IOException */
    public void e(String var1) throws IOException {
        this.a(var1, false);
    }

    public void a(short s2)  {
        try {
                this.a.writeShort(s2);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void d(String string) {
    }

    public boolean f() {
        return false;
    }

    /* 02b as.java L339: new CompressedStream 抛 IOException (R8 移除 throws) */
    public void a(String string, boolean bl2) throws IOException {
        CompressedStream at2 = new CompressedStream(bl2);  // 02b: at
        at2.fileName = string;
        this.e.add(at2);
        this.a = ((CompressedStream) this.e.getLast()).dataOutputStream;
    }

    public void a(String string)  {
        try {
                CompressedStream at2 = (CompressedStream) this.e.removeLast();
                if (!at2.fileName.equals(string)) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("OutputNetStream:endBlock", "Name does not match: expected" + string + " , got:" + at2.fileName);
                }
                at2.a();
                this.a = this.e.isEmpty() ? this.c : ((CompressedStream) this.e.getLast()).dataOutputStream;
                this.a.writeUTF(at2.fileName);
                this.a(at2.byteArrayStream);
                try {
                    at2.b();
                }
                catch (Exception exception) {
                    if (exception instanceof DataFormatException) {
                        if (!GlobalState.aZ) {
                            com.corrodinggames.rts.gameFramework.GlobalState.b("DataFormatException error calling streamBlock.close() (this is expected on android 4.4)");
                        }
                    }
                    com.corrodinggames.rts.gameFramework.GlobalState.b("Error calling streamBlock.close() to clean up memory");
                    exception.printStackTrace();
                }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
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
