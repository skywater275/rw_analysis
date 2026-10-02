/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.TeamColorTexture;
import java.io.IOException;

public class FileShader
extends Shader {
    int a = -99;
    boolean b;

    public FileShader(String string, boolean bl) throws IOException {
        super(string);
    }


    public boolean isEnabled() {
        return this.b;
    }


    public boolean isEnabled2() {
        boolean bl = false;
        int n = -16711936;
        if (n != this.a) {
            this.a("teamColor", n);
            bl = true;
            this.a = n;
        }
        return bl;
    }


    @Override
    public boolean a(Paint paint, Texture e2) {
        boolean bl = false;
        if (e2 instanceof TeamColorTexture) {
            TeamColorTexture h2 = (TeamColorTexture) e2;
            if (h2.D != this.a) {
                this.a("teamColor", h2.D);
                bl = true;
                this.a = h2.D;
            }
        }
        super.a(paint, e2);
        return bl;
    }

    @Override
    public void c() {
        super.c();
    }
}
