/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.gameFramework.aq;
import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.gameFramework.as;
import com.corrodinggames.rts.gameFramework.aw;
import com.corrodinggames.rts.gameFramework.ax;
import com.corrodinggames.rts.gameFramework.l;

public class av
extends aq {
    boolean a = false;

    @Override
    public ar a(String string) {
        return new aw(string, this);
    }

    @Override
    public as a() {
        ax ax2 = new ax(this);
        return ax2;
    }

    @Override
    public void a(am am2) {
        l.e("Null musicFactory - load");
        this.e = am2;
    }

    @Override
    public void b() {
    }
}
