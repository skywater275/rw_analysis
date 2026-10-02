/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import java.io.IOException;

public strictfp class UnitTransform {
    public float a;
    public float b;


    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.a);
        as2.a(this.b);
    }

    public void a(InputNetStream k2) {
        this.a = k2.readFloat();
        this.b = k2.readFloat();
    }
}
