/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.animation;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.game.units.custom.UnitTypeComparator;

import com.corrodinggames.rts.game.ResourceType;
import com.corrodinggames.rts.game.units.custom.animation.e;
import com.corrodinggames.rts.game.units.custom.animation.AnimationActivationCurve;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;

public strictfp class AnimationResourceCurve
implements Comparable {
    String a;
    boolean b = false;
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    UnitTypeComparator h = UnitTypeComparator.any;  // 02b custom/b/e: q.f (q=UnitTypeComparator, v19.133f6 修正)
    boolean i;
    public float j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public int o = -1;
    public float filterRadiusMultiplier = 1.0f;
    public boolean q;
    public LogicBoolean r;
    public LogicBoolean s;
    public LogicBoolean t;
    public boolean u;
    public e v;
    public e[] w;
    public float x;
    public int y;
    public boolean z;
    LogicBoolean A;
    LogicBoolean B;
    public Texture C;
    public float D;
    public float E;
    public LogicBoolean F;
    public AnimationActivationCurve G;
    public float H;
    public boolean I;
    public int J = -1;
    public int K = -1;
    public int L = -1;
    public boolean M;
    public LogicBoolean N;
    public int O;
    public int P;
    public float Q;
    public float R;
    public float S;
    public float T;
    public float U;
    public float V;
    public LogicBoolean W;
    public LogicBoolean X;
    public boolean Y;
    public boolean Z;
    public float aa;
    public float ab;
    public LogicBoolean ac;
    public LogicBoolean ad;
    public int ae = -1;
    public boolean af;
    public int ag = -1;
    public UniquePaint ah;
    public LogicBoolean ai;

    public int a(AnimationResourceCurve d2) {
        if (d2 == null) {
            return 0;
        }
        float f2 = this.H - d2.H;
        if (f2 < 0.0f) {
            return -1;
        }
        if (f2 > 0.0f) {
            return 1;
        }
        return 0;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((AnimationResourceCurve) object);
    }
}
