/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.FileWatcher;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.ShaderUniform;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.io.IOException;
import java.util.Arrays;

public class Shader {
    public String c;
    public String d = "";
    public String e;
    public String f;
    public int g;
    public int h;
    String i;
    String j;
    long k;
    long l;
    public boolean m;
    public int n;
    public int o;
    public ShaderUniform[] p = new ShaderUniform[0];
    public Object q;
    public int r;
    int s;

    public void a(String string, float f) {
        this.a(string).a(f);
    }

    public void a(String string, float f, float f2) {
        this.a(string).a(f, f2);
    }

    public void a(String string, int n) {
        float f = (float)(n >> 16 & 0xFF) * 0.003921569f;
        float f2 = (float)(n >> 8 & 0xFF) * 0.003921569f;
        float f3 = (float)(n & 0xFF) * 0.003921569f;
        float f4 = (float)(n >>> 24) * 0.003921569f;
        this.a(string).a(f, f2, f3, f4);
    }

    public void a(String string, Texture e2) {
        ShaderUniform af2 = this.a(string);
        af2.a(e2);
    }

    public void b(String string, Texture e2) {
        ShaderUniform af2 = this.a(string);
        af2.b(e2);
    }

    public ShaderUniform a(String string) {
        for (ShaderUniform af2 : this.p) {
            if (!af2.uniformName.equals(string)) continue;
            return af2;
        }
        ShaderUniform af3 = new ShaderUniform();
        af3.uniformName = string;
        ShaderUniform[] afArray = Arrays.copyOf(this.p, this.p.length + 1);
        afArray[afArray.length - 1] = af3;
        this.p = afArray;
        return af3;
    }

    public Shader(String string) throws IOException {
        String string2 = "assets/shaders/plain.vert";
        if (com.corrodinggames.rts.gameFramework.GlobalState.aY) {
            string2 = "assets/shaders/plainGDX.vert";
        }
        this.a(string2, string);
    }

    public void a(String string, String string2) throws IOException {
        this.c = com.corrodinggames.rts.gameFramework.GameUtils.formatBytes(string2);
        this.i = string;
        this.j = string2;
        this.d();
        this.e();
    }

    public Shader() {
        this.c = "Invalid";
        this.o = 1;
    }

    public void d() throws IOException {
        com.corrodinggames.rts.gameFramework.utility.AssetStream j2 = FileLoader.k(this.i);
        if (j2 == null) {
            throw new IOException("Cannot find: " + this.i);
        }
        this.e = com.corrodinggames.rts.gameFramework.GameUtils.a(j2);
        com.corrodinggames.rts.gameFramework.utility.AssetStream j3 = FileLoader.k(this.j);
        if (j3 == null) {
            throw new IOException("Cannot find: " + this.j);
        }
        this.f = com.corrodinggames.rts.gameFramework.GameUtils.a(j3);
    }

    public void b(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("shader(" + this.c + "): " + string);
    }

    public void c(String string) {
        if (this.r < 3) {
            ++this.r;
            com.corrodinggames.rts.gameFramework.GlobalState.logAndNetworkSend("shader(" + this.c + "): " + string);
        }
        com.corrodinggames.rts.gameFramework.GlobalState.a("shader(" + this.c + "): " + string);
        this.o = 1;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean a(Paint paint, Texture e2) {
        return false;
    }

    public boolean e() {
        long l2 = new java.io.File(this.i).lastModified();
        long l3 = new java.io.File(this.j).lastModified();
        boolean bl = l2 != this.k || l3 != this.l;
        this.k = l2;
        this.l = l3;
        return bl;
    }

    public void f() {
        ++this.s;
        if (this.s < 100) {
            return;
        }
        this.s = 0;
        if (this.e()) {
            this.b("Reloading shader");
            try {
                this.d();
                this.m = true;
                this.o = 0;
                for (ShaderUniform af2 : this.p) {
                    af2.isInteger = true;
                    af2.uniformLocation = -1;
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    public void c() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bO.a(this);
    }
}
