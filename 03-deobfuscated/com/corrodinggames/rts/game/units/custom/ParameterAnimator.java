/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.game.units.custom.CollisionShape;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.ResourceType;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionParticle;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionTrigger;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionSequence;
import com.corrodinggames.rts.gameFramework.ui.ActionCooldown;
import com.corrodinggames.rts.gameFramework.ReplayRecorder;

import com.corrodinggames.rts.game.units.custom.animation.i;
import com.corrodinggames.rts.game.units.custom.TraitValueBuilder;
import com.corrodinggames.rts.game.units.custom.AnimationCurve;
import com.corrodinggames.rts.game.units.custom.CurveType;
import com.corrodinggames.rts.game.units.custom.UnitParameter;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public strictfp final class ParameterAnimator {
    public UnitParameter a;
    float b;
    float c;
    float d = 1.0f;
    boolean e = false;
    boolean f = false;
    boolean g;
    boolean h;
    boolean i;
    int j;
    float k = 0.0f;
    float l = 0.05f;
    CustomUnitType m;
    float[] n;

    public ParameterAnimator(CustomUnitType j2) {
        this.m = j2;
    }

    public void a(UnitParameter f2, int n) {
        this.a(f2, n, false);
    }

    public void a(UnitParameter f2, int n2, boolean bl) {
        if (f2 == null || !f2.a()) {
            return;
        }
        if ((this.i || this.f && this.e) && n2 <= this.j && (!bl || f2 != this.a)) {
            return;
        }
        this.i = true;
        if (f2 != this.a || bl || this.g) {
            float f3 = 0.0f;
            if (this.a != null && this.e) {
                f3 = this.a.i;
            }
            this.a = f2;
            this.j = n2;
            this.c();
            this.f = bl;
            this.h = !bl;
            this.b = -1.0f;
            this.c = -1.0f;
            this.d = 1.0f;
            this.g = false;
            float f4 = f2.h;
            if (f3 > f4) {
                f4 = f3;
            }
            if (f4 > 0.0f) {
                this.k = 1.0f;
                this.l = f4;
            } else {
                this.k = 0.0f;
            }
        }
        this.e = true;
    }

    public void a() {
        if (this.a != null) {
            boolean bl = true;
            this.b(bl);
        }
        this.e = false;
        this.a = null;
        this.j = -1;
    }

    public void b() {
        if (this.a != null) {
            float f2;
            if (!this.g && (f2 = this.a.i) > 0.0f) {
                this.g = true;
                this.c();
                this.h = false;
                this.j = -1;
                this.k = 1.0f;
                this.l = f2;
                return;
            }
            boolean bl = true;
            this.b(bl);
        }
        this.e = false;
        this.a = null;
        this.j = -1;
    }

    public void a(float f2) {
        if (!this.e) {
            return;
        }
        this.c = this.b;
        if (this.b < 0.0f) {
            this.b = 0.0f;
        }
        float f3 = this.d;
        if (this.a != null && this.a.j != null) {
            f3 *= this.a.j.readNumber(this.m);
        }
        this.b += f3 * f2;
        if (this.h && !this.i) {
            this.b();
        }
        this.i = false;
        if (this.e) {
            if (this.k > 0.0f) {
                this.k -= this.l * f2;
            } else if (this.g) {
                this.b();
                return;
            }
            if (!this.g && this.a != null) {
                if (this.a.g) {
                    if (this.b > this.a.n) {
                        this.a(false);
                        this.b = this.a.n;
                        this.d = -1.0f;
                    } else if (this.b < 0.0f) {
                        this.b = 0.0f;
                        this.d = 1.0f;
                        if (this.f) {
                            this.b();
                            if (!this.g) {
                                return;
                            }
                        }
                    }
                } else {
                    if (this.b > this.a.n) {
                        if (this.f) {
                            this.a(false);
                            this.b();
                            if (!this.g) {
                                return;
                            }
                        } else {
                            this.a(false);
                            this.b = 0.0f;
                            this.d = 1.0f;
                        }
                    }
                    if (this.b < 0.0f && !this.f && f3 < 0.0f) {
                        this.b = this.a.n;
                    }
                }
            }
            boolean bl = false;
            if (this.g) {
                bl = true;
            }
            this.b(bl);
        }
    }

    void c() {
        CustomArrayList m2 = this.a.l;
        if (this.n == null || this.n.length < m2.size()) {
            this.n = new float[m2.size()];
        }
        for (int i2 = 0; i2 < m2.size(); ++i2) {
            com.corrodinggames.rts.game.units.custom.animation.i i3;
            AnimationCurve c2 = (AnimationCurve) m2.get(i2);
            CurveType d2 = c2.a;
            if (d2 == CurveType.b) {
                this.n[i2] = this.m.c;
                continue;
            }
            if (d2 == CurveType.a) {
                this.n[i2] = -99.0f;
                continue;
            }
            if (d2 == CurveType.c) {
                if (this.m.dT != null && c2.b < this.m.dT.length) {
                    i3 = this.m.dT[c2.b];
                    this.n[i2] = i3.pushStrengthValue;
                    continue;
                }
                this.n[i2] = 0.0f;
                com.corrodinggames.rts.gameFramework.GlobalState.b("setBaseBlendValues: Target leg out of range for: " + this.m.dt().i());
                continue;
            }
            if (d2 == CurveType.d) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                i3 = this.m.dT[c2.b];
                this.n[i2] = i3.formationSpacing;
                continue;
            }
            if (d2 == CurveType.e) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                this.n[i2] = this.m.dT[c2.b].arrivalDistance = GameUtils.a(this.m.dT[c2.b].arrivalDistance, false);
                continue;
            }
            if (d2 == CurveType.f) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                this.n[i2] = this.m.dT[c2.b].accelerationValue;
                continue;
            }
            if (d2 == CurveType.j) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                this.n[i2] = this.m.dT[c2.b].movementSpeedScale;
                continue;
            }
            if (d2 == CurveType.i) continue;
            this.n[i2] = 0.0f;
            com.corrodinggames.rts.gameFramework.GlobalState.b("Unsupported blend type:" + (Object)((Object)d2));
        }
    }

    void a(boolean bl) {
        CustomArrayList m2 = this.a.l;
        for (int i2 = 0; i2 < m2.size(); ++i2) {
            AnimationCurve c2 = (AnimationCurve) m2.get(i2);
            CurveType d2 = c2.a;
            if (d2 != CurveType.i) continue;
            c2.reset(this.m, this.c, this.b, bl);  // 02b custom/e.java: c.a(j,float,float,boolean) = 03 reset
        }
    }

    void b(boolean bl2) {
        CustomArrayList m2 = this.a.l;
        for (int i2 = 0; i2 < m2.size(); ++i2) {
            com.corrodinggames.rts.game.units.custom.animation.i object;
            float f2;
            AnimationCurve c2 = (AnimationCurve) m2.get(i2);
            CurveType d2 = c2.a;
            if (d2 == CurveType.a && !this.m.el && !bl2) continue;
            if (bl2) {
                f2 = 0.0f;
                if (d2 == CurveType.b) {
                    f2 = 1.0f;
                } else if (d2 == CurveType.a) {
                    f2 = this.m.x.Y;
                } else if (d2 == CurveType.j) {
                    f2 = 1.0f;
                    TraitValueBuilder[] traitValueBuilderArray = this.m.x.ax;  // 02b e.java L263: ba[] var7
                    if (traitValueBuilderArray != null && c2.b < traitValueBuilderArray.length) {
                        f2 = traitValueBuilderArray[c2.b].r;
                    }
                }
            } else {
                f2 = c2.b(this.b);
            }
            if (this.k > 0.0f && d2 != CurveType.a) {
                f2 = f2 * (1.0f - this.k) + this.n[i2] * this.k;
            }
            if (d2 == CurveType.a) {
                this.m.a = (int)f2;
                continue;
            }
            if (d2 == CurveType.b) {
                this.m.c = f2;
                continue;
            }
            if (d2 == CurveType.c) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                object = this.m.dT[c2.b];
                object.pushStrengthValue = f2;
                object.ignoreTerrain = true;
                object.pushingEnabled = true;
                continue;
            }
            if (d2 == CurveType.d) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                object = this.m.dT[c2.b];
                object.formationSpacing = f2;
                object.ignoreTerrain = true;
                object.pushingEnabled = true;
                continue;
            }
            if (d2 == CurveType.e) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                this.m.dT[c2.b].arrivalDistance = f2;
                continue;
            }
            if (d2 == CurveType.f) {
                if (this.m.dT == null || c2.b >= this.m.dT.length) continue;
                this.m.dT[c2.b].accelerationValue = f2;
                continue;
            }
            if (d2 == CurveType.j) {
                com.corrodinggames.rts.game.units.custom.animation.i[] collisionShapeArray = this.m.dT;  // 02b e.java L307: b.i[] var9
                if (collisionShapeArray == null || c2.b >= collisionShapeArray.length) continue;
                collisionShapeArray[c2.b].movementSpeedScale = f2;
                continue;
            }
            if (d2 == CurveType.g || d2 != CurveType.i) continue;
            c2.reset(this.m, this.c, this.b, bl2);  // 02b custom/e.java: c.a(j,float,float,boolean) = 03 reset
        }
    }

    public boolean a(UnitParameter f2) {
        return this.e && this.a == f2;
    }
}
