/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.PointF;
import com.corrodinggames.rts.game.ai.AIStrategy;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ExperimentalUnit;
import com.corrodinggames.rts.game.units.commands.BuildSlot;  // 02b units/d/d: 静态位置放置检查
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.util.ArrayList;
import java.io.IOException;

public abstract class AIStrategyNode
extends BaseGameObject {
    public int Q;
    protected final AIStrategy R;
    public float S;
    public float T;
    public float U;
    public boolean V;
    static final ArrayList W = new ArrayList();


    @Override
    public void serializeToStream(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) {  // v19.112d 抽象对齐
        as2.a(this.S);
        as2.a(this.T);
        as2.a(this.U);
    }

    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {  // 02b o.java L25-29: 写 S/T/U
        as2.a(this.S);
        as2.a(this.T);
        as2.a(this.U);
    }


    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {
        this.S = k2.readFloat();
        this.T = k2.readFloat();
        this.U = k2.readFloat();
    }

    public AIStrategyNode(AIStrategy a2) {
        ++a2.zoneIdCounter;  // 02b a.aI: 节点唯一 ID 计数器 (03 zoneIdCounter 语义名)
        this.Q = a2.zoneIdCounter;
        this.R = a2;
        this.R.zoneQueue.add(this);  // 02b a.bm: 活动节点队列
        this.R.zoneSnapshot.add(this);  // 02b a.bn: 节点快照列表
    }

    public AIStrategyNode(AIStrategy a2, float f2, float f3) {
        this(a2);
        this.S = f2;
        this.T = f3;
    }

    public void p() {
        this.R.zoneQueue.remove(this);
        this.R.zoneSnapshot.remove(this);
        this.V = true;
    }

    public boolean c(float f2, float f3) {
        float f4;
        float f5 = GameUtils.a(this.S, this.T, f2, f3);
        return f5 < (f4 = this.U) * f4;
    }

    public boolean b(UnitInstance am2) {
        float f2;
        float f3 = GameUtils.a(this.S, this.T, am2.eo, am2.ep);
        return f3 < (f2 = this.U + am2.cj) * f2;
    }

    public boolean a(UnitInstance am2, float f2) {
        float f3;
        float f4 = GameUtils.a(this.S, this.T, am2.eo, am2.ep);
        return f4 < (f3 = this.U + am2.cj + f2) * f3;
    }

    public float c(UnitInstance am2) {
        return GameUtils.a(this.S, this.T, am2.eo, am2.ep);
    }

    public float a(CombatMain i2) {
        return GameUtils.a(this.S, this.T, i2.S, i2.T);
    }

    public float d(float f2, float f3) {
        return GameUtils.a(this.S, this.T, f2, f3);
    }

    public PointF w() {
        PointF pointF = new PointF();
        float f2 = (float)(Math.random() * 360.0);
        float f3 = (float)(Math.random() * (double)this.U);
        pointF.a(this.S + GameUtils.k(f2) * f3, this.T + GameUtils.j(f2) * f3);
        return pointF;
    }

    public PointF e(UnitTypeHandle as2) {
        GlobalState l2 = GlobalState.B();
        PointF pointF = new PointF();
        float f2 = this.U;
        MovementTypeEnum ao2 = MovementTypeEnum.b;
        UnitInstance am2 = null;
        if (as2 == UnitRegistry.d) {
            f2 = 600.0f;
            ao2 = MovementTypeEnum.e;
        }
        for (int i2 = 0; i2 < 15; ++i2) {
            int n2;
            UnitRegistry ar2 = null;
            boolean bl = false;
            boolean bl2 = false;
            if (this instanceof CombatMain) {
                CombatMain i3 = (CombatMain) this;
                if (i2 < 6 && as2 == UnitRegistry.J) {
                    ar2 = UnitRegistry.J;
                }
                if (ar2 != null) {
                    com.corrodinggames.rts.game.units.UnitType y2 = null;
                    if (am2 == null) {
                        am2 = UnitInstance.c(as2);
                    }
                    if (am2 instanceof com.corrodinggames.rts.game.units.UnitType) {
                        y2 = (com.corrodinggames.rts.game.units.UnitType)am2;
                    }
                    n2 = i3.c(ar2);
                    if (y2 != null && n2 > 1) {
                        int n3 = -1;
                        int n4 = GameUtils.a(0, n2 - 1);
                        UnitInstance[] amArray = UnitInstance.bE.a();
                        int n5 = UnitInstance.bE.size();
                        for (int i4 = 0; i4 < n5; ++i4) {
                            UnitInstance am3 = amArray[i4];
                            if (am3.player != this.R || !i3.a(am3) || !am3.isAlive() || !this.R.i(am3) || am3.r() != ar2 || ++n3 != n4) continue;
                            float f3 = am3.eo;
                            float f4 = am3.ep;
                            boolean bl3 = GameUtils.a(0, 1) == 0;
                            float f5 = f3;
                            float f6 = f4;
                            if (bl3) {
                                f6 += GameUtils.c(-150.0f, 150.0f);
                            } else {
                                f5 += GameUtils.c(-150.0f, 150.0f);
                            }
                            boolean bl4 = false;
                            W.clear();
                            UnitInstance am4 = null;
                            l2.bS.a(y2, f3, f4, f5, f6, bl4, W, am4);
                            if (W.size() > 0) {
                                PointF pointF2 = (PointF)W.get(0);
                                pointF.a(pointF2.a, pointF2.b);
                                bl = true;
                                continue;
                            }
                            bl2 = true;
                        }
                    }
                }
            }
            if (bl2) continue;
            if (!bl) {
                float f7 = (float)(Math.random() * 360.0);
                float f8 = (float)(Math.random() * (double)f2);
                pointF.a(this.S + GameUtils.k(f7) * f8, this.T + GameUtils.j(f7) * f8);
            }
            l2.bL.a(pointF.a, pointF.b);
            int n6 = l2.bL.scrollPixelX;
            int n7 = l2.bL.scrollPixelY;
            if (l2.bL.c(n6, n7) && ((n2 = PathfindingUtils.c(n6, n7, ao2)) > 5 || n2 == 0) && BuildSlot.a(as2, pointF.a, pointF.b, this.R)) {
                return pointF;
            }
            if (as2 != UnitRegistry.d) continue;
            f2 += 100.0f;
        }
        return null;
    }
}
