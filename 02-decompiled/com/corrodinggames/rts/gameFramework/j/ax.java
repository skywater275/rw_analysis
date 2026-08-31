/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public strictfp class ax {
    public BufferedOutputStream a;
    public String b;
    public ByteArrayOutputStream c = new ByteArrayOutputStream();
    public PrintStream d;
    public boolean e = false;

    public void a() {
        this.d.flush();
        if (this.a != null) {
            this.a.flush();
        }
    }

    public void b() {
        if (!this.e) {
            this.d.close();
        } else {
            l.g("TODO: Cannot yet close wrapped stream");
        }
    }

    public ax(boolean bl) {
        OutputStream outputStream;
        if (bl) {
            this.a = new BufferedOutputStream(this.c);
            outputStream = this.a;
        } else {
            outputStream = this.c;
        }
        this.d = new PrintStream(outputStream);
    }
}
