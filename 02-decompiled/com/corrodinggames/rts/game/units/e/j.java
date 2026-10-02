/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.e;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.e.h;
import com.corrodinggames.rts.game.units.w;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp abstract class j
extends w {
    float dK;
    public static e dL = null;
    public static e dM = null;
    public static e[] dN = new e[10];
    public static e[] dO = new e[10];

    public j(boolean bl) {
        super(bl);
    }

    @Override
    public e v() {
        if (this.bX.k == -1) {
            return null;
        }
        if (this.dd()) {
            return dO[this.bX.R()];
        }
        return dN[this.bX.R()];
    }

    public static void dt() {
        l l2 = l.B();
        dL = l2.bO.a(R$drawable.unit_icon_land);
        if (dL == null) {
            throw new RuntimeException("IMAGE_ICON is null");
        }
        dN = n.a(dL);
        dM = l2.bO.a(R$drawable.unit_icon_land_exp);
        if (dM == null) {
            throw new RuntimeException("IMAGE_ICON_EXP is null");
        }
        dO = n.a(dM);
    }

    @Override
    public void a(float f2) {
        float f3;
        super.a(f2);
        if (this.bV) {
            f3 = 0.0f;
            if (this.cK()) {
                f3 = -10.0f;
            }
            if (this.eq > f3) {
                if (this.eq > 0.0f && this.dK < 0.4f) {
                    this.dK = 0.4f;
                }
                this.dK += 0.002f * f2;
                this.eq -= this.dK * f2;
                if (this.eq <= f3) {
                    this.eq = f3;
                }
            }
        }
        if (!this.bT() || this.bV) {
            return;
        }
        if (!(this instanceof h)) {
            f3 = 0.0f;
            if (this.eq < f3) {
                this.eq += 0.2f * f2;
                if (this.eq >= f3) {
                    this.eq = f3;
                }
            }
            if (this.eq > 0.0f) {
                this.dK += 0.03f * f2;
                if (this.eq < 0.0f) {
                    this.dK = f.b(this.dK, 0.2f);
                }
                this.eq -= this.dK * f2;
                if (this.eq <= 0.0f) {
                    if (this.eq < 0.0f) {
                        this.eq = 0.0f;
                    }
                    this.dK = 0.0f;
                }
            }
        }
    }

    @Override
    public ao h() {
        return com.corrodinggames.rts.game.units.ao.b;
    }
}
