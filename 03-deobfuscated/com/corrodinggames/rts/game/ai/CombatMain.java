/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;
import com.corrodinggames.rts.game.GameFlagImpl;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.commands.CarrierUnit;
import com.corrodinggames.rts.game.units.AirUnit;
import com.corrodinggames.rts.game.LobbyPlayer;
import com.corrodinggames.rts.game.units.LandUnit;
import com.corrodinggames.rts.game.ProjectileType;
import com.corrodinggames.rts.game.units.WaterUnit;
import com.corrodinggames.rts.game.units.projectiles.SpecialBuilding;

import android.graphics.PointF;
import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.ai.IncludeExcludeMode;
import com.corrodinggames.rts.game.ai.UnitBuildStrategy;
import com.corrodinggames.rts.game.ai.UnitBuildStrategyEntry;
import com.corrodinggames.rts.game.ai.AIUnitActionUtils;
import com.corrodinggames.rts.game.ai.BaseZoneType;
import com.corrodinggames.rts.game.ai.BaseZoneStage;
import com.corrodinggames.rts.game.ai.RallyGroup;
import com.corrodinggames.rts.game.ai.AIStrategyNode;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitFlag;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.WeaponTypeEnum;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;

public class CombatMain
extends AIStrategyNode {
    float a;
    BaseZoneType b;
    BaseZoneStage c;
    float d = -1.0f;
    float e;
    float f;
    float g = 100.0f;
    int h;
    float i = 50.0f;
    float j = 50.0f;
    float k;
    float l;
    float m;
    boolean n;
    boolean o;
    CustomArrayList p = new CustomArrayList();
    com.corrodinggames.rts.gameFramework.utility.UnitInstanceList q = new com.corrodinggames.rts.gameFramework.utility.UnitInstanceList();
    com.corrodinggames.rts.gameFramework.utility.UnitInstanceList r = new com.corrodinggames.rts.gameFramework.utility.UnitInstanceList();
    boolean s;
    boolean t;
    float u;
    float v = 0.0f;
    PointF cachedBuildPoint = new PointF();
    PointF x = new PointF();
    int y;
    com.corrodinggames.rts.game.units.UnitTypeHandle z;
    com.corrodinggames.rts.game.units.custom.resources.CustomActionBase lastCantAffordBuilding;
    com.corrodinggames.rts.game.units.custom.resources.CustomActionBase lastFailedBuilding;
    int lastAttemptedBuildingCount;
    int lastAttemptedBuildingFailed;
    String lastUnitAttemptName;
    int lastUnitAttemptCost;
    int lastUnitAttemptBuildTime;
    boolean retryBuildRequested = false;
    int numberOfFactories;
    int numberOfCombatUnits;
    int numberOfIdleBuilders;
    int numberOfExtractors;
    boolean hasFactories;
    ArrayList buildOrderList = new ArrayList();
    com.corrodinggames.rts.game.units.UnitTypeHandle lastRequestedUnitType;
    com.corrodinggames.rts.game.units.custom.resources.CustomActionBase lastBuildOrderResource;

    @Override
    public void serializeToStream(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) {
        as2.a(this.b);
        as2.a(this.c);
        as2.a(this.d);
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.i);
        as2.a(this.j);
        as2.a(this.k);
        as2.a(this.l);
        as2.c(4);
        as2.a(this.v);
        as2.a(this.m);
        as2.a(this.n);
        as2.a(this.o);
        as2.a(this.h);
        super.serializeToStream(as2);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {
        this.b = (BaseZoneType) k2.b(BaseZoneType.class);
        this.c = (BaseZoneStage) k2.b(BaseZoneStage.class);
        this.d = k2.readFloat();
        this.e = k2.readFloat();
        this.f = k2.readFloat();
        this.g = k2.readFloat();
        this.i = k2.readFloat();
        this.j = k2.readFloat();
        this.k = k2.readFloat();
        this.l = k2.readFloat();
        byte by = k2.d();
        if (by >= 1) {
            this.v = k2.readFloat();
        }
        if (by >= 2) {
            this.m = k2.readFloat();
        }
        if (by >= 3) {
            this.n = k2.e();
            this.o = k2.e();
        }
        if (by >= 4) {
            this.h = k2.f();
        }
        super.a(k2);
    }

    public CombatMain(AIStrategy a2, float f2, float f3) {
        super(a2, f2, f3);
    }

    public PointF a() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        PointF pointF = null;
        int n2 = (int)(this.U * l2.bL.float1);
        l2.bL.a(this.S, this.T);
        int n3 = l2.bL.scrollPixelX;
        int n4 = l2.bL.scrollPixelY;
        for (int i2 = n3 - n2; i2 <= n3 + n2; ++i2) {
            for (int i3 = n4 - n2; i3 <= n4 + n2; ++i3) {
                UnitType y2;
                MapLayer g2;
                if (!l2.bL.c(i2, i3) || (g2 = l2.bL.e(i2, i3)) == null || !g2.isTileLayer) continue;
                UnitInstance am2 = com.corrodinggames.rts.game.units.commands.BuildSlot.b(i2, i3);
                boolean bl = false;
                if (am2 == null) {
                    bl = true;
                }
                if (am2 != null && am2 instanceof UnitType && !(y2 = (UnitType)am2).r().p()) {
                    bl = true;
                }
                if (!bl) continue;
                l2.bL.a(i2, i3);
                if (pointF != null && GameUtils.a(0, 100) >= 50) continue;
                this.cachedBuildPoint.a(l2.bL.scrollPixelX + l2.bL.selectedTileX, l2.bL.scrollPixelY + l2.bL.selectedTileY);
                pointF = this.cachedBuildPoint;
            }
        }
        return pointF;
    }

    public void a(UnitType y2) {
        this.q.remove(y2);
    }

    public void b() {
        this.p.clear();
        this.q.clear();
        UnitInstance[] amArray = UnitInstance.bE.a();
        int n2 = UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (am2.player != this.R || am2.isDead || am2.u() || !this.a(am2)) continue;
            this.q.a(am2);
            UnitTypeHandle as2 = am2.dz;
            if (this.p.contains(as2)) continue;
            this.p.add(as2);
        }
    }

    public boolean a(UnitTypeHandle as2) {
        return this.a(as2, false, true) != null;
    }

    public boolean b(UnitTypeHandle as2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.ar && !as2.C() || as2.w()) {
            return false;
        }
        Object[] objectArray = this.p.a();
        int n2 = this.p.size();
        block0: for (int i2 = 0; i2 < n2; ++i2) {
            UnitTypeHandle as3 = (UnitTypeHandle) objectArray[i2];
            UnitInstance[] amArray = this.q.a();
            int n3 = this.q.size();
            for (int i3 = 0; i3 < n3; ++i3) {
                UnitInstance am2 = amArray[i3];
                if (am2.r() != as3 || !(am2 instanceof UnitType)) continue;
                UnitType y2 = (UnitType)am2;
                if (!y2.b(as2, true)) continue block0;
                return true;
            }
        }
        return false;
    }

    public UnitTypeHandle c() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.R.allowExpansion) {
            return null;
        }
        int n2 = this.a(this.R.buildingFactories);
        UnitTypeHandle as2 = null;
        float f2 = -1.0f;
        for (UnitTypeHandle as3 : UnitRegistry.ae) {
            if (!as3.j() || !this.b(as3)) continue;
            int n3 = this.R.a(as3, com.corrodinggames.rts.game.ai.IncludeExcludeMode.a);
            int n4 = this.c(as3);
            boolean bl2 = false;
            if (as3 instanceof ModUnitRegistry) {
                bl2 = true;
                ModUnitRegistry l3 = (ModUnitRegistry)as3;
                if (l3.fL.size() != 0) {
                    for (UnitTypeHandle as4 : (java.util.Collection<UnitTypeHandle>) (java.util.Collection) l3.fL) {
                        n3 += this.R.a(as4, com.corrodinggames.rts.game.ai.IncludeExcludeMode.a);
                        n4 += this.c(as4);
                    }
                }
            }
            float f3 = -2.0f;
            if (as3.p() && !bl2) {
                int n5 = n4;
                if (this.a() != null && GameUtils.a(0, 100) < 90) {
                    if (n5 == 0) {
                        f3 = this.R.o < 5000.0 ? 0.98f : 0.58f;
                    }
                    if (n5 == 1) {
                        f3 = 0.55f;
                    }
                    if (n5 == 2) {
                        f3 = 0.4f;
                    }
                    if (n5 >= 3) {
                        f3 = 0.25f / (float)n5;
                    }
                    if (n3 >= 3) {
                        f3 = (float)((double)f3 * 0.6);
                    }
                }
            }
            if (as3 == UnitRegistry.b && (n3 < 5 || n2 == 0)) {
                if (n3 == 0) {
                    f3 = 0.8f;
                } else if (n4 < 2) {
                    f3 = 0.46f / (float)(n3 + n4 * 2);
                }
            }
            if (as3 == UnitRegistry.d && this.R.isUnitLimitReached() && (n3 < 5 || n2 == 0)) {
                if (n3 == 0) {
                    f3 = 0.3f;
                } else if (n4 < 1) {
                    f3 = 0.1f / (float)(n3 + n4 * 2);
                }
            }
            if (as3 == UnitRegistry.c && (n3 < 5 || n2 == 0)) {
                if (n3 == 0) {
                    f3 = 0.48f;
                } else if (n4 < 2) {
                    f3 = 0.29f / (float)(n3 + n4);
                }
            }
            if (as3 == UnitRegistry.f) {
                if (n4 == 0) {
                    f3 = 0.47f;
                } else if (n4 < 3) {
                    f3 = 0.35f / (float)n4;
                } else if (n4 < 4) {
                    f3 = 0.025f / (float)n4;
                }
            }
            if (as3 == UnitRegistry.y && n4 == 0) {
                f3 = 0.018f;
            }
            if (as3 == UnitRegistry.B && n4 == 0) {
                f3 = 0.02f;
            }
            if (as3 == UnitRegistry.g) {
                if (n4 == 0) {
                    f3 = 0.42f;
                } else if (this.R.shouldRushExpansion()) {
                    if (n4 < 4) {
                        f3 = 0.3f / (float)n4;
                    }
                } else if (n4 < 3) {
                    f3 = 0.3f / (float)n4;
                } else if (n4 < 4) {
                    f3 = 0.02f / (float)n4;
                }
            }
            if (as3 == UnitRegistry.J && this.R.o > 2000.0 && n4 < 5) {
                f3 = n3 == 0 ? 0.11f : 0.07f / (0.2f * (float)n3 + (float)n4);
            }
            if (!(as3 != UnitRegistry.D || l2.O() && l2.bX.ay.i || !(this.R.o > 2200.0) || n3 >= 4)) {
                if (n3 == 0) {
                    f3 = 0.06f;
                } else if (n4 < 1) {
                    f3 = 0.05f / (float)(n3 + n4 * 2);
                }
            }
            if (bl2) {
                ModUnitRegistry l4 = (ModUnitRegistry)as3;
                if (!(l4.fw || n3 >= l4.fx && l4.fx != -1 || n4 >= l4.fy && l4.fy != -1)) {
                    f3 = l4.fz;
                    if (n4 < l4.fA) {
                        f3 = l4.fB;
                    }
                    if (n4 == 0) {
                        f3 += l4.fC;
                    }
                    if (n3 == 0) {
                        f3 += l4.fD;
                    }
                    if (as3.p() && this.a() == null) {
                        f3 = -2.0f;
                    }
                }
            }
            if (this.R.isHardDifficulty() && as3 == UnitRegistry.G && this.R.o > 15000.0) {
                if (n3 == 0) {
                    f3 = 0.04f;
                }
                if (this.R.o > 55000.0 && n3 == 1) {
                    f3 = 0.03f;
                }
            }
            if (!(f3 >= 0.0f) || !(f3 > f2) && !((double)GameUtils.c(0.0f, 1.0f) < 0.01)) continue;
            f2 = f3;
            as2 = as3;
        }
        this.f = f2;
        return as2;
    }

    public int a(UnitBuildStrategy d2) {
        int n2 = 0;
        for (UnitBuildStrategyEntry e2 : (java.util.Collection<UnitBuildStrategyEntry>) (java.util.Collection) d2.c) {
            n2 += this.c(e2.a);
        }
        return n2;
    }

    public int c(UnitTypeHandle as2) {
        int n2 = 0;
        com.corrodinggames.rts.gameFramework.utility.UnitInstanceList u2 = this.q;
        UnitInstance[] amArray = u2.a();
        int n3 = u2.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (am2.player != this.R || am2.dz != as2 || !this.a(am2)) continue;
            ++n2;
        }
        return n2;
    }

    public int d() {
        int n2 = 0;
        com.corrodinggames.rts.gameFramework.utility.UnitInstanceList u2 = this.q;
        UnitInstance[] amArray = u2.a();
        int n3 = u2.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            UnitType y2;
            UnitInstance am2 = amArray[i2];
            if (am2.player != this.R || !(am2 instanceof UnitType) || !this.a(y2 = (UnitType)am2, false) || y2.bM || y2.aB != null || !this.R.h(y2) || !this.R.i(y2)) continue;
            ++n2;
        }
        return n2;
    }

    public int e() {
        return this.numberOfIdleBuilders;
    }

    public boolean f() {
        int n2 = this.h();
        return n2 != 0;
    }

    public UnitInstance g() {
        float f2 = this.U + 120.0f;
        UnitInstance[] amArray = UnitInstance.bE.a();
        int n2 = UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (!(am2.eo + f2 > this.S) || !(am2.eo - f2 < this.S) || !(am2.ep + f2 > this.T) || !(am2.ep - f2 < this.T) || am2.player == this.R || !this.a(am2, 120.0f) || !am2.player.c(this.R) || !this.R.j(am2)) continue;
            return am2;
        }
        return null;
    }

    public int h() {
        return this.a(60.0f);
    }

    public int a(float f2) {
        int n2 = 0;
        float f3 = this.U + f2;
        UnitInstance[] amArray = UnitInstance.bE.a();
        int n3 = UnitInstance.bE.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (!(am2.eo + f3 > this.S) || !(am2.eo - f3 < this.S) || !(am2.ep + f3 > this.T) || !(am2.ep - f3 < this.T) || am2.player == this.R || !this.a(am2, f2) || !am2.player.c(this.R) || !am2.l() || !this.R.j(am2)) continue;
            ++n2;
        }
        return n2;
    }

    public void i() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitInstance am2 = this.g();
        if (am2 != null) {
            com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.a(this.R);
            UnitInstance[] amArray = UnitInstance.bE.a();
            int n2 = UnitInstance.bE.size();
            for (int i2 = 0; i2 < n2; ++i2) {
                UnitInstance am3 = amArray[i2];
                if (!(am3 instanceof UnitType)) continue;
                UnitType y2 = (UnitType)am3;
                if (am3.player != this.R || !this.R.b(am3, am2) || !this.R.i(y2) || !y2.aq()) continue;
                if (!am3.bM) {
                    if (!AIStrategy.a(am3, this.S, this.T, 800.0f)) continue;
                    e2.a(y2);
                    continue;
                }
                if (!AIStrategy.a(am3, this.S, this.T, 540.0f)) continue;
                e2.a(y2);
            }
            e2.a(am2);
        }
    }

    public boolean a(UnitInstance am2) {
        return this.a(am2, false);
    }

    public boolean a(UnitInstance am2, boolean bl2) {
        return am2 instanceof UnitType && ((UnitType)am2).aC == this && (!bl2 || this.b(am2));
    }

    public boolean a(UnitType y2, boolean bl2) {
        return y2.aC == this && (!bl2 || this.b(y2));
    }

    public int j() {
        int n2 = 0;
        for (Object obj2 : this.getNearbyUnits()) {
            UnitInstance am2 = (UnitInstance)obj2;
            if (this.R == am2.player || !am2.player.c(this.R) || !(am2 instanceof UnitType) || !this.b(am2)) continue;
            ++n2;
        }
        return n2;
    }

    public com.corrodinggames.rts.game.units.pathfinding.QueryResult getNearbyUnits() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.cc.b(this.S, this.T, this.U);
    }

    private UnitType x() {
        return this.a((UnitTypeHandle) null, (PointF)null, true);
    }

    private UnitType y() {
        return this.f((UnitTypeHandle)null);
    }

    private UnitType f(UnitTypeHandle as2) {
        return this.a(as2, null, false);
    }

    private UnitType a(UnitTypeHandle as2, PointF pointF, boolean bl2) {
        if (this.numberOfIdleBuilders == 0) {
            return null;
        }
        this.y = 0;
        float f2 = Float.MAX_VALUE;
        UnitType y2 = null;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (as2 != null && (l2.ar && !as2.C() || as2.w())) {
            return null;
        }
        UnitInstance[] amArray = UnitInstance.bE.a();
        int n2 = UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitType y3;
            boolean bl3;
            UnitInstance am2 = amArray[i2];
            if (am2.player != this.R || !this.a(am2) || am2.cN != null || !am2.aj() || !(am2 instanceof UnitType) || !this.R.i(am2) || !(bl3 = com.corrodinggames.rts.game.ai.AIUnitActionUtils.getRandomElement(y3 = (UnitType)am2)) || bl2 && !y3.I()) continue;
            ++this.y;
            if (as2 != null && !y3.b(as2, true)) continue;
            boolean bl4 = false;
            float f3 = -1.0f;
            if (pointF != null) {
                f3 = GameUtils.a(pointF.a, pointF.b, am2.eo, am2.ep);
            }
            if (y2 == null) {
                bl4 = true;
            } else {
                if (pointF != null && f3 < f2) {
                    bl4 = true;
                }
                if ((double)GameUtils.c(0.0f, 1.0f) < 0.2) {
                    bl4 = true;
                }
            }
            if (!bl4) continue;
            y2 = (UnitType)am2;
            if (pointF == null) continue;
            f2 = f3;
        }
        return y2;
    }

    private UnitType a(UnitInstance am2, PointF pointF, boolean bl2) {
        if (this.numberOfExtractors == 0) {
            return null;
        }
        float f2 = Float.MAX_VALUE;
        UnitType y2 = null;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitInstance[] amArray = this.q.a();
        int n2 = this.q.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitType y3;
            boolean bl3;
            UnitTypeHandle as2;
            UnitInstance am3 = amArray[i2];
            if (am3.player != this.R || !this.a(am3) || am3.cN != null || !(as2 = am3.r()).n() || !(am3 instanceof UnitType) || !this.R.i(am3) || !(bl3 = com.corrodinggames.rts.game.ai.AIUnitActionUtils.b(y3 = (UnitType)am3)) || bl2 && !y3.I() || am3 != null && !y3.h(am3, true)) continue;
            boolean bl4 = false;
            if (as2 instanceof ModUnitRegistry) {
                ModUnitRegistry l3 = (ModUnitRegistry)as2;
                if (l3.fH != null && !this.a(l3.fH)) continue;
            }
            float f3 = -1.0f;
            if (pointF != null) {
                f3 = GameUtils.a(pointF.a, pointF.b, am3.eo, am3.ep);
            }
            if (y2 == null) {
                bl4 = true;
            } else {
                if (pointF != null && f3 < f2) {
                    bl4 = true;
                }
                if ((double)GameUtils.c(0.0f, 1.0f) < 0.2) {
                    bl4 = true;
                }
            }
            if (!bl4) continue;
            y2 = (UnitType)am3;
            if (pointF == null) continue;
            f2 = f3;
        }
        return y2;
    }

    private boolean g(UnitTypeHandle as2) {
        this.z = as2;
        this.lastCantAffordBuilding = null;
        this.lastFailedBuilding = null;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        PointF pointF = as2.p() ? this.a() : this.e(as2);
        if (pointF != null) {
            UnitType y2 = this.a(as2, pointF, false);
            if (y2 == null) {
                return false;
            }
            if (as2 == UnitRegistry.d && PathfindingUtils.c(pointF.a, pointF.b, MovementTypeEnum.e) < this.R.maxUnits) {
                return false;
            }
            int n2 = 1;
            GameAction s2 = y2.a(as2, true);
            if (s2 != null) {
                n2 = s2.t();
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.b("buildBuilding: could not find getBuildUnitAction for builder this shouldn't happen:" + as2.i());
            }
            if (!s2.b(y2) || !s2.a((UnitInstance) y2, false)) {
                if (!this.R.a(s2.B(), (UnitInstance) y2)) {
                    this.lastCantAffordBuilding = s2.B();
                    this.lastFailedBuilding = this.lastCantAffordBuilding.i(y2);
                }
            } else if (s2.A()) {
                com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.a(this.R);
                e2.a(y2);
                e2.a(s2.N(), pointF, null);
            } else {
                com.corrodinggames.rts.gameFramework.Command e3 = l2.cf.a(this.R);
                e3.a(y2);
                e3.a(pointF.a, pointF.b, as2, n2);
            }
            return true;
        }
        return false;
    }

    private boolean z() {
        UnitInstance[] amArray = UnitInstance.bE.a();
        int n2 = UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitType y2;
            UnitInstance am2 = amArray[i2];
            if (am2.player != this.R || !this.a(am2) || !am2.isAlive() || am2.u() || !(am2 instanceof UnitType) || !(y2 = (UnitType)am2).ai()) continue;
            return true;
        }
        return false;
    }

    public boolean a(UnitConfig h2) {
        UnitInstance[] amArray = this.q.a();
        int n2 = this.q.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitConfig h3;
            UnitInstance am2 = amArray[i2];
            if (am2.player != this.R || !am2.isAlive() || (h3 = am2.getStatusEffects()) == null || !com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(h2, h3)) continue;
            return true;
        }
        return false;
    }

    private UnitType a(UnitTypeHandle as2, boolean bl2, boolean bl3) {
        UnitInstance[] amArray = this.q.a();
        int n2 = this.q.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (am2.player != this.R || !am2.isAlive() || !this.R.i(am2) || !(am2 instanceof CarrierUnit) || !(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType)am2;
            CarrierUnit l2 = (CarrierUnit)((Object)am2);
            GameAction s2 = am2.e(as2);
            if (s2 == null || !l2.dy() && bl2 || s2.m(am2) || !s2.b(y2) || !s2.a((UnitInstance) y2, false) || am2 instanceof com.corrodinggames.rts.game.units.commands.CommandCenter && !as2.m() && this.u() > 2 && !this.s && bl2 || bl3 && !y2.aD) continue;
            return y2;
        }
        return null;
    }

    private boolean a(UnitBuildStrategy d2, boolean bl2) {
        ArrayList arrayList = d2.isUnitTypeAllowed();
        for (UnitBuildStrategyEntry e2 : (java.util.Collection<UnitBuildStrategyEntry>) (java.util.Collection) arrayList) {
            if (!this.a(e2.a, bl2)) continue;
            return true;
        }
        return false;
    }

    private boolean a(UnitTypeHandle as2, boolean bl2) {
        int n2 = 1;
        return this.a(as2, bl2, n2);
    }

    private boolean a(UnitTypeHandle as2, boolean bl2, int n2) {
        if (n2 < 1) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("AI", "buildUnit: quantity cannot be < 1");
            return false;
        }
        UnitType y2 = this.a(as2, true, bl2);
        if (y2 == null) {
            // empty if block
        }
        if (y2 == null) {
            return false;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        GameAction s2 = y2.e(as2);
        if (s2 == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("AI", "buildUnit: action is null!");
            return false;
        }
        if (!s2.b(y2)) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("AI", "buildUnit: isAvailable==false");
            return false;
        }
        if (!s2.a((UnitInstance) y2, false)) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("AI", "buildUnit: isActive==false");
            return false;
        }
        if (s2.g(y2) || s2.n_() && l2.ar) {
            return false;
        }
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.a(this.R);
            e2.a(y2);
            e2.a(s2.z());
        }
        return true;
    }

    CombatMain l() {
        float f2 = -1.0f;
        CombatMain i2 = null;
        for (Object obj2 : this.R.zoneSnapshot) {
            AIStrategyNode o2 = (AIStrategyNode)obj2;
            CombatMain i3;
            if (!(o2 instanceof CombatMain) || (i3 = (CombatMain) o2) == this || i3.e() <= 1) continue;
            float f3 = i3.a(this);
            if (i2 != null && !(f3 < f2)) continue;
            f2 = f3;
            i2 = i3;
        }
        return i2;
    }

    public void m() {
        UnitType y2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        CombatMain i2 = this.l();
        if (i2 != null && i2.e() > 1 && (y2 = i2.x()) != null) {
            boolean bl2;
            PointF pointF = this.w();
            if (PathfindingUtils.a((UnitInstance) y2, pointF.a, pointF.b) && ((bl2 = this.R.a((UnitInstance) y2, pointF.a, pointF.b)) || this.R.scoutCount != 0)) {
                com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.a(this.R);
                e2.a(y2);
                e2.a(pointF.a, pointF.b);
                ++this.h;
                this.g = GameUtils.a(1800, 2500);
                if (this.h >= 2) {
                    this.g += 11000.0f;
                }
                --i2.numberOfIdleBuilders;
                if (!bl2) {
                    boolean bl3 = true;
                    if (y2.aB != null) {
                        if (!y2.aB.addUnitHook()) {
                            y2.aB.b(y2);
                        } else {
                            bl3 = false;
                            if (!y2.aB.G.contains(y2)) {
                                y2.aB.G.add(y2);
                            }
                        }
                    }
                    if (bl3) {
                        RallyGroup l3 = new RallyGroup(this.R);
                        l3.c(y2);
                        l3.S = pointF.a;
                        l3.T = pointF.b;
                    }
                    this.g = GameUtils.a(12000, 14000);
                }
            }
        }
    }

    private UnitInstance A() {
        UnitInstance[] amArray = UnitInstance.bE.a();
        int n2 = UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (am2.player != this.R || !this.a(am2, true) || !am2.bI() || !(am2.hp < am2.maxHp - 1.0f) && !(am2.cm < 1.0f)) continue;
            return am2;
        }
        return null;
    }

    public void n() {
        boolean bl2;
        UnitTypeHandle as2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.r.size() == 0) {
            return;
        }
        if (this.s) {
            return;
        }
        for (int i2 = 0; !(i2 >= 8 || (as2 = this.R.harvesterUnits.getBuildWeight()) != null && this.a(as2) && (bl2 = this.d(as2))); ++i2) {
        }
    }

    public boolean d(UnitTypeHandle as2) {
        int n2;
        if (!(as2 instanceof ModUnitRegistry)) {
            return false;
        }
        ModUnitRegistry l2 = (ModUnitRegistry)as2;
        if (l2.fE == -1 && l2.fF == -1) {
            return false;
        }
        int n3 = 0;
        int n4 = 0;
        boolean bl2 = l2.fG;
        UnitInstance[] amArray = UnitInstance.bE.a();
        int n5 = UnitInstance.bE.size();
        for (n2 = 0; n2 < n5; n2 += 1) {
            UnitInstance am2 = amArray[n2];
            if (am2.player != this.R || am2.cN != null || !(am2 instanceof UnitType) || !this.R.i(am2)) continue;
            UnitType y2 = (UnitType)am2;
            UnitTypeHandle as3 = am2.r();
            if (bl2 ? !as3.n() : as3 != l2 && !l2.fL.contains(as3)) continue;
            ++n4;
            if (!this.a(am2)) continue;
            ++n3;
        }
        if (l2.fE != -1 && n3 >= l2.fE) {
            return false;
        }
        if (l2.fF != -1 && n4 >= l2.fF) {
            return false;
        }
        n2 = this.a(l2, true) ? 1 : 0;
        return n2 != 0;
    }

    public void o() {
        if (this.r.size() == 0) {
            return;
        }
        UnitInstance am2 = this.r();
        if (am2 != null) {
            this.x.a(am2.eo, am2.ep);
            UnitType y2 = this.a(am2, this.x, true);
            if (y2 != null) {
                this.a(y2, am2);
            }
        }
    }

    public void q() {
        if (this.r.size() == 0) {
            return;
        }
        if (this.lastFailedBuilding != null) {
            UnitInstance[] amArray = this.q.a();
            int n2 = this.q.size();
            for (int i2 = 0; i2 < n2; ++i2) {
                // L840 重复声明删除 (02b q() 无此声明)
                UnitType y2;
                WeaponAction au2;
                UnitTypeHandle as2;
                UnitInstance am2 = amArray[i2];
                if (am2.player != this.R || !this.a(am2) || am2.cN != null || !(as2 = am2.r()).n() || !(am2 instanceof UnitType) || !this.R.i(am2) || (double)GameUtils.c(0.0f, 1.0f) > 0.3 || (au2 = (y2 = (UnitType)am2).ar()) == null || au2.d() != WeaponTypeEnum.g || (am2 = au2.i()) == null || !(am2.g() > 0.0f) || this.lastFailedBuilding.c(am2.getResourceProduction())) continue;
                UnitInstance am3 = this.r();
                this.a(y2, am3);
                break;
            }
        }
    }

    public UnitInstance r() {
        UnitInstance am2 = null;
        for (int i2 = 0; i2 < 20 && (am2 = this.r.a(GameUtils.a(0, this.r.size() - 1))) != null && this.lastFailedBuilding != null && !this.lastFailedBuilding.c(am2.getResourceProduction()); ++i2) {
        }
        return am2;
    }

    public void a(UnitType y2, UnitInstance am2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (y2.g(am2, true)) {
            com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.a(this.R);
            e2.a(y2);
            e2.d(am2);
        }
    }

    public void s() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitInstance am2 = this.A();
        if (am2 != null) {
            this.x.a(am2.eo, am2.ep);
            UnitType y2 = this.a((UnitTypeHandle) null, this.x, true);
            if (y2 != null && y2.a(am2) && am2.e(y2) < 2) {
                com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.a(this.R);
                e2.a(y2);
                e2.b(am2);
            }
        }
    }

    public void b(float f2) {
        int n2;
        int n3;
        this.c(f2);
        int n4 = this.numberOfCombatUnits;
        int n5 = this.numberOfFactories;
        this.b();
        this.n = this.z();
        if (this.n) {
            this.o = true;
        }
        if (n4 >= 1) {
            this.s();
        }
        if (this.hasFactories && this.numberOfFactories > 0) {
            this.m();
            this.o();
            this.n();
        }
        if (n4 < (n3 = 2) && this.i == 0.0f) {
            this.i = 300.0f;
            n2 = this.R.a(this.R.builderUnits, com.corrodinggames.rts.game.ai.IncludeExcludeMode.a);
            if (!this.s || n2 <= 2) {
                boolean bl2;
                boolean bl3 = true;
                boolean bl4 = bl2 = GameUtils.a(0, 100) < 5;
                if (!bl2 && this.a(this.R.builderUnits, bl3)) {
                    this.retryBuildRequested = false;
                    this.i = 900.0f;
                } else {
                    if (!bl2) {
                        this.retryBuildRequested = true;
                    }
                    if (!this.s && this.v == 0.0f && n4 < 1 && this.g == 0.0f) {
                        this.l();
                    }
                }
            }
        }
        n2 = this.j();
        if (n4 == 0 && n5 == 0) {
            this.k += f2;
            if (n2 > 2) {
                this.k += 2.0f * f2;
            }
            if (n2 > 5) {
                this.k += 4.0f * f2;
            }
        } else {
            this.k = GameUtils.a(this.k, f2);
        }
        if (this.k > 11000.0f) {
            this.p();
        }
        if (this.b == com.corrodinggames.rts.game.ai.BaseZoneType.a && (n4 != 0 && n5 != 0 || n5 > 5 && n2 == 0)) {
            this.l += f2;
            if (this.l > 2000.0f) {
                this.b = com.corrodinggames.rts.game.ai.BaseZoneType.c;
            }
        }
        this.t();
    }

    public void t() {
        if (this.b == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.a("fixOverlaps: this.state==null");
            com.corrodinggames.rts.gameFramework.GlobalState.a("id:" + this.Q);
            com.corrodinggames.rts.gameFramework.GlobalState.a("x:" + this.S);
            com.corrodinggames.rts.gameFramework.GlobalState.a("y:" + this.T);
            com.corrodinggames.rts.gameFramework.GlobalState.a("radius:" + this.U);
            if (this.R != null) {
                com.corrodinggames.rts.gameFramework.GlobalState.a("team:" + this.R.k);
            }
            return;
        }
        for (Object obj2 : this.R.zoneQueue) {
            AIStrategyNode o2 = (AIStrategyNode)obj2;
            if (!(o2 instanceof CombatMain) || o2 == this) continue;
            CombatMain i2 = (CombatMain) o2;
            float f2 = GameUtils.a(this.S, this.T, i2.S, i2.T);
            if (!(f2 < 400.0f)) continue;
            if (i2.b == null) {
                com.corrodinggames.rts.gameFramework.GlobalState.a("fixOverlaps: targetBase.state==null");
                continue;
            }
            if (i2.b.getOrdinal() < this.b.getOrdinal()) {
                i2.p();
                continue;
            }
            this.p();
        }
    }

    public int u() {
        return this.numberOfFactories;
    }

    public void c(float f2) {
        this.numberOfFactories = 0;
        this.numberOfCombatUnits = 0;
        this.numberOfExtractors = 0;
        this.numberOfIdleBuilders = 0;
        this.hasFactories = false;
        this.r.clear();
        boolean bl2 = true;
        if (bl2) {
            for (Object obj2 : this.getNearbyUnits()) {
                UnitInstance am2 = (UnitInstance)obj2;
                if (!(am2.bd() > 0.0f) || !this.b(am2)) continue;
                this.hasFactories = true;
                this.r.a(am2);
            }
        }
        UnitInstance[] amArray = UnitInstance.bE.a();
        int n2 = UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitType y2;
            Object object = amArray[i2];
            if (((UnitInstance) object).player != this.R || !(object instanceof UnitType) || !this.a(y2 = (UnitType)object, false) || !((UnitInstance) object).isAlive() || !this.R.i((UnitInstance) object) || ((UnitInstance) object).u()) continue;
            UnitTypeHandle as2 = ((UnitInstance) object).r();
            if (as2.j()) {
                ++this.numberOfFactories;
            }
            if (as2.m()) {
                ++this.numberOfCombatUnits;
                boolean bl3 = com.corrodinggames.rts.game.ai.AIUnitActionUtils.getRandomElement(y2);
                if (bl3) {
                    ++this.numberOfIdleBuilders;
                }
            }
            if (as2.n()) {
                ++this.numberOfExtractors;
            }
            if (!(object instanceof CarrierUnit)) continue;
            CarrierUnit l2 = (CarrierUnit)object;
            this.numberOfCombatUnits += l2.h(UnitRegistry.h);
        }
    }

    public void d(float f2) {
        this.t = this.s = this.f();
        if (this.s) {
            this.v += f2;
            this.u = 100.0f;
        } else {
            this.v = 0.0f;
        }
        if (this.v > 6000.0f) {
            this.s = false;
        }
        this.m = GameUtils.a(this.m, f2);
        this.e = GameUtils.a(this.e, f2);
        this.g = GameUtils.a(this.g, f2);
        this.i = GameUtils.a(this.i, f2);
        this.j = GameUtils.a(this.j, f2);
        if (this.s && this.j == 0.0f) {
            this.j = 100 + this.Q % 15;
            if (!this.R.allowExpansion) {
                this.i();
            }
        }
        if (this.e <= 0.0f) {
            boolean bl2;
            this.e = 270 + this.Q % 15;
            if (this.R.shouldRushExpansion()) {
                this.e = 190 + this.Q % 15;
            }
            if ((double)this.f < 0.2) {
                this.e += 180.0f;
            }
            if ((double)this.f < 0.08) {
                this.e += 180.0f;
            }
            boolean bl3 = bl2 = this.y() != null;
            if (bl2) {
                UnitTypeHandle as2 = null;
                as2 = this.c();
                if (as2 != null && ((double)this.f > 0.8 || this.R.a(1300.0)) && ((double)this.f > 0.4 || this.R.a(1700.0)) && ((double)this.f > 0.2 || this.R.a(2100.0)) && ((double)this.f > 0.1 || this.R.a(2800.0)) && ((double)this.f > 0.05 || this.R.a(3100.0)) && ((double)this.f > 0.01 || this.R.a(4800.0))) {
                    ++this.lastAttemptedBuildingCount;
                    if (!this.g(as2)) {
                        this.e -= 120.0f;
                        ++this.lastAttemptedBuildingFailed;
                    }
                }
            }
        }
        float f3 = this.u();
        if ((f3 /= 3.0f) < 1.0f) {
            f3 = 1.0f;
        }
        if (this.s) {
            this.d = (float)((double)this.d + (double)f2 * 0.015);
        }
        if ((double)this.f < 0.6) {
            if (this.R.defendingCount < 2) {
                this.d = (float)((double)this.d + (double)f2 * 7.0E-4 * (double)f3);
            } else if (this.R.a(1200.0)) {
                this.d = (float)((double)this.d + (double)f2 * 1.0E-4 * (double)f3);
            }
            if (this.R.a(1600.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.001);
            }
            if (this.R.a(2200.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.001);
            }
            if (this.R.a(2600.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.001);
            }
            if (this.R.a(8000.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.005);
            }
            if (this.R.a(9000.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.01);
            }
            if (this.R.a(10100.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.01);
            }
            if (this.R.a(30000.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.05);
            }
        }
        if (this.R.a(5000.0)) {
            this.d = (float)((double)this.d + (double)f2 * 0.001);
        }
        if (!this.R.a(800.0) && !this.s && this.d > 1.2f) {
            this.d = 1.2f;
        }
        if (this.d > 3.5f) {
            this.d = 3.5f;
        }
        for (int i2 = 0; i2 < 12; ++i2) {
            this.v();
            if (!(this.d >= 3.0f)) break;
        }
    }

    public void a(ArrayList arrayList, UnitBuildStrategy d2, MovementTypeEnum ao2, int n2) {
        this.buildOrderList.clear();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitTypeHandle as2 = d2.isUnitTypeAllowed(ao2);
            if (as2 == null || this.buildOrderList.contains(as2)) continue;
            this.buildOrderList.add(as2);
        }
        arrayList.addAll(this.buildOrderList);
    }

    public void a(UnitType y2, com.corrodinggames.rts.game.units.custom.resources.CustomActionBase b2, boolean bl2) {  // 02b i.a(y,d.b,boolean)
        this.lastRequestedUnitType = y2.r();
        if (bl2) {
            this.lastBuildOrderResource = null;
            this.lastFailedBuilding = null;
        } else {
            this.lastBuildOrderResource = b2;
            this.lastFailedBuilding = b2.i(y2);
        }
    }

    public void v() {
        int n2;
        int n3;
        float f2;
        int n4 = this.d();
        int n5 = 12;
        int n6 = 50;
        if (this.R.shouldRushExpansion()) {
            n6 = 65;
            n5 = 16;
        }
        boolean bl2 = this.R.a(25000.0);
        boolean bl3 = false;
        ArrayList arrayList = new ArrayList();
        boolean bl4 = this.R.useTransportsOnThisMap();
        boolean bl5 = this.R.useHoverTransportsOnThisMap();
        float f3 = 0.4f;
        float f4 = 0.3f;
        float f5 = 0.2f;
        if (!this.R.landPathExists) {
            f3 = 0.1f;
            f4 = 0.5f;
            f5 = 0.4f;
        }
        if (!this.R.waterPathExists) {
            f3 = 0.2f;
            f4 = 0.1f;
            f5 = 0.7f;
        }
        MovementTypeEnum ao2 = (f2 = GameUtils.c(0.0f, 1.0f)) < f3 ? MovementTypeEnum.b : (f2 < f3 + f4 ? MovementTypeEnum.f : MovementTypeEnum.d);
        if (this.R.a(1300.0) && this.d >= 1.0f || this.R.a(300.0) && this.d >= 3.0f) {
            int n7;
            if (this.R.isUnitLimitReached() && this.R.amphibiousCount < n5 && (n7 = GameUtils.c(100)) < 35) {
                this.a(arrayList, this.R.attackingUnitsWater, null, 2);
                if (bl2) {
                    bl3 = true;
                }
            }
            if (n4 < 3 && this.R.attackingCount < n6) {
                if (ao2 == MovementTypeEnum.b) {
                    this.a(arrayList, this.R.attackingUnitsLand, null, 4);
                    if (bl2) {
                        bl3 = true;
                    }
                } else if (ao2 == MovementTypeEnum.f) {
                    this.a(arrayList, this.R.attackingUnitsHover, null, 4);
                    if (bl2) {
                        bl3 = true;
                    }
                } else {
                    this.a(arrayList, this.R.attackingUnitsAir, null, 4);
                    if (bl2) {
                        bl3 = true;
                    }
                }
            }
            if (this.d >= 1.0f && bl4 && this.m == 0.0f) {
                n7 = this.R.a(this.R.transportUnitsFlying, com.corrodinggames.rts.game.ai.IncludeExcludeMode.a);
                int n8 = this.R.a(this.R.transportUnitsNonFlying, com.corrodinggames.rts.game.ai.IncludeExcludeMode.a);
                n3 = n7 + n8;
                n2 = this.R.ao();
                if ((this.R.a(1700.0) || n2 > 10 || this.R.transportCount == 0 && n2 >= 1 && n7 == 0) && (n3 < 3 || n2 > 20 && n3 < 5)) {
                    if (bl5 && n3 < 2) {
                        this.a(arrayList, this.R.transportUnits, null, 2);
                    } else {
                        this.a(arrayList, this.R.transportUnits, MovementTypeEnum.d, 2);
                    }
                }
            }
        }
        if (arrayList.size() == 0) {
            ++this.lastUnitAttemptCost;
        }
        while (arrayList.size() != 0) {
            UnitInstance am2;
            UnitTypeHandle as2 = (UnitTypeHandle) arrayList.remove(arrayList.size() - 1);
            UnitInstance am3 = UnitInstance.b(as2);
            n3 = 1;
            if (this.s && GameUtils.c(100) < 10 && (am2 = this.g()) != null && !this.R.b(am3, am2)) {
                ++this.lastUnitAttemptCost;
                n3 = 0;
            }
            if (n3 == 0) continue;
            n2 = 0;
            if (this.a(as2, n2 != 0)) {
                ++this.lastUnitAttemptCost;
                this.R.combatManager.clearAllCounts(as2);
                this.d -= 1.0f;
                if (!this.R.g(am3)) break;
                this.m = 1000.0f;
                ++this.R.transportCount;
                break;
            }
            ++this.lastUnitAttemptBuildTime;
        }
    }

    public void writeToStream(UnitType y2, com.corrodinggames.rts.game.units.custom.resources.CustomActionBase b2, boolean bl2) {
        this.lastRequestedUnitType = y2.r();
        if (bl2) {
            this.lastBuildOrderResource = null;
            this.lastFailedBuilding = null;
        } else {
            this.lastBuildOrderResource = b2;
            this.lastFailedBuilding = b2.i(y2);
        }
    }
}