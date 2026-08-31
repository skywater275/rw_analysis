/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public strictfp class at {
    public GZIPOutputStream a;
    public BufferedOutputStream b;
    public String c;
    public ByteArrayOutputStream d = new ByteArrayOutputStream();
    public DataOutputStream e;
    public boolean f = false;

    public void a() {
        this.e.flush();
        if (this.b != null) {
            this.b.flush();
        }
        if (this.a != null) {
            this.a.finish();
        }
    }

    public void b() {
        if (!this.f) {
            this.e.close();
        } else {
            l.g("TODO: Cannot yet close wrapped stream");
        }
    }

    public at(boolean bl) {
        OutputStream outputStream;
        if (bl) {
            this.a = new GZIPOutputStream(this.d);
            this.b = new BufferedOutputStream(this.a);
            outputStream = this.b;
        } else {
            outputStream = this.d;
        }
        this.e = new DataOutputStream(outputStream);
    }
}
