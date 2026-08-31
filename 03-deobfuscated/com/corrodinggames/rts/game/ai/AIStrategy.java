/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;
import com.corrodinggames.rts.game.NeutralPlayer;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.game.units.actions.StopAction;
import com.corrodinggames.rts.game.units.projectiles.SpecialBuilding;
import com.corrodinggames.rts.game.GameFlagImpl;
import com.corrodinggames.rts.game.NetworkPlayer;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.AirUnit;
import com.corrodinggames.rts.game.GameFlag;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.PointF;
import com.corrodinggames.rts.game.ai.AIStrategy$1;
import com.corrodinggames.rts.game.ai.AIStrategy$10;
import com.corrodinggames.rts.game.ai.AIStrategy$11;
import com.corrodinggames.rts.game.ai.AIStrategy$12;
import com.corrodinggames.rts.game.ai.AIStrategy$13;
import com.corrodinggames.rts.game.ai.AIStrategy$2;
import com.corrodinggames.rts.game.ai.AIStrategy$3;
import com.corrodinggames.rts.game.ai.AIStrategy$4;
import com.corrodinggames.rts.game.ai.AIStrategy$5;
import com.corrodinggames.rts.game.ai.AIStrategy$6;
import com.corrodinggames.rts.game.ai.AIStrategy$7;
import com.corrodinggames.rts.game.ai.AIStrategy$8;
import com.corrodinggames.rts.game.ai.AIStrategy$9;
import com.corrodinggames.rts.game.ai.IncludeExcludeMode;
import com.corrodinggames.rts.game.ai.BuildPreferenceCache;
import com.corrodinggames.rts.game.ai.UnitBuildStrategy;
import com.corrodinggames.rts.game.ai.CombatAction;
import com.corrodinggames.rts.game.ai.AIUnitActionUtils;
import com.corrodinggames.rts.game.ai.CombatMain;
import com.corrodinggames.rts.game.ai.AIUnitGroupBase;

import com.corrodinggames.rts.game.ai.BaseZoneType;
import com.corrodinggames.rts.game.ai.BaseZoneStage;
import com.corrodinggames.rts.game.ai.RallyGroup;
import com.corrodinggames.rts.game.ai.PlainZone;
import com.corrodinggames.rts.game.ai.AIStrategyNode;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.PathfindingHelper;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.WeaponTypeEnum;
import com.corrodinggames.rts.game.units.commands.Structures;
import com.corrodinggames.rts.gameFramework.utility.UnitInstanceList;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.utility.UnitRegistry;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class AIStrategy
extends com.corrodinggames.rts.game.PlayerState {  // 02b a.a extends n (PlayerState) 铁证
    public static boolean globalAIEnabled;
    public static boolean as;  // 02b game/a/a.java L53: AI Debug 开关
    public float bG;  // 02b game/a/a.java L119: 冻结计时
    final int maxUnits = 3000;
    int totalUnitCount;
    int enemyUnitCount;
    int forwardZoneCount;
    int landUnitCount;
    int waterUnitCount;
    int airUnitCount;
    int buildingCount;
    int extractorCount;
    int factoryCount;
    int builderCount;
    int combatUnitCount;
    int harvesterCount;
    int scoutCount;
    int experimentalCount;
    public int zoneIdCounter;
    int turtleFlag = 0;
    boolean isHumanPlayer;
    float tier2Accum;
    float tier2Param;
    float tier3Accum;
    float tier3Param;
    float tier1Accum;
    float creditAccumulator;
    float groupUpdateTimer = 0.0f;
    float groupUpdateAccum = 0.0f;
    float forwardZoneTimer;
    float resourceZoneTimer;
    int recycleTimer;
    float repairTimer;
    public boolean aY;  // 02b game/a/a L85: basicAI 地图标记 (v19.133f6 补缺)
    public boolean aiDisabled;
    public boolean y;  // 02b game/a/a: lockAiDifficulty 锁定 (v19.133f6 补缺)
    public boolean allowExpansion;
    public boolean aiSleeping;
    int attackingCount;
    int defendingCount;
    int amphibiousCount;
    boolean firstRun = true;
    boolean firstRunDelayed = true;
    boolean mapChecked = false;
    com.corrodinggames.rts.game.units.f damagingBorder;
    boolean landPathExists;
    boolean waterPathExists;
    boolean harborPathExists;
    boolean airPathExists;
    int transportCount;
    ConcurrentLinkedQueue zoneQueue = new ConcurrentLinkedQueue();
    ArrayList zoneSnapshot = new ArrayList();
    PointF centerPosition = new PointF();
    Paint debugPaint;
    ArrayList aiBehaviorList = new ArrayList();
    UnitBuildStrategy attackingUnitsLand = new AIStrategy$1(this, "attackingUnitsLand");
    UnitBuildStrategy attackingUnitsHover = new AIStrategy$6(this, "attackingUnitsHover");
    UnitBuildStrategy attackingUnitsAir = new AIStrategy$7(this, "attackingUnitsAir");
    UnitBuildStrategy attackingUnitsWater = new AIStrategy$8(this, "attackingUnitsWater");
    UnitBuildStrategy buildingUnits = new AIStrategy$9(this, "buildingUnits");
    UnitBuildStrategy transportUnits = new AIStrategy$10(this, "transportUnits");
    UnitBuildStrategy transportUnitsFlying = new AIStrategy$11(this, "transportUnitsFlying");
    UnitBuildStrategy transportUnitsNonFlying = new AIStrategy$12(this, "transportUnitsNonFlying");
    UnitBuildStrategy builderUnits = new AIStrategy$13(this, "builderUnits");
    UnitBuildStrategy harvesterUnits = new AIStrategy$2(this, "harvesterUnits");
    UnitBuildStrategy extractorUnits = new AIStrategy$3(this, "extractorUnits");
    UnitBuildStrategy buildingFactories = new AIStrategy$4(this, "buildingFactories");
    UnitBuildStrategy buildingFactoriesForBuilders = new AIStrategy$5(this, "buildingFactoriesForBuilders");
    public BuildPreferenceCache combatManager = new BuildPreferenceCache();
    public int difficultyIndex;  // 02b game/a/a x (v19.133f6 跨包访问修正)
    public float updateCooldown = 0.0f;
    ArrayList sortedZones = new ArrayList();
    private static ArrayList zoneTypeList;
    public static final UnitInstanceList nearbyUnitsCache;
    public final com.corrodinggames.rts.gameFramework.utility.m aiBehaviors = new com.corrodinggames.rts.gameFramework.utility.m();

    public boolean shouldRushExpansion() {
        int n2 = this.getDifficultyIndex();
        return this.getDifficultyIndex() == 3 || n2 > 300;
    }

    public boolean isHardDifficulty() {
        return this.getDifficultyIndex() >= 2;
    }

    public boolean isTurtleEnabled() {
        return (1 & this.turtleFlag) == 1;
    }

    public boolean isTurtleMode() {
        return this.isTurtleEnabled();
    }

    public int getDifficultyIndex() {
        return this.difficultyIndex;
    }

    public boolean isUnitLimitReached() {
        com.corrodinggames.rts.gameFramework.pathfinding.PathFinder l2 = com.corrodinggames.rts.gameFramework.GlobalState.B().bU;
        return l2.A.costDiagonal > 3000;
    }

    public boolean useTransportsOnThisMap() {
        if (this.isUnitLimitReached()) {
            return true;
        }
        if (!this.landPathExists || !this.waterPathExists) {
            return true;
        }
        if (!this.harborPathExists) {
            return true;
        }
        return !this.airPathExists;
    }

    public boolean useHoverTransportsOnThisMap() {
        if (!this.airPathExists) {
            return false;
        }
        return this.useTransportsOnThisMap() && this.waterPathExists;
    }

    public boolean a(float f2, float f3, AIStrategyNode o2, MovementTypeEnum ao2) {
        if (this.a(f2, f3, o2.S, o2.T, ao2)) {
            return true;
        }
        for (float f4 = -180.0f; f4 < 180.0f; f4 += 90.0f) {
            float f5;
            float f6 = o2.S + GameUtils.cosFast(f4) * o2.U * 0.4f;
            if (!this.a(f2, f3, f6, f5 = o2.T + GameUtils.sinFast(f4) * o2.U * 0.4f, ao2)) continue;
            return true;
        }
        return false;
    }

    public boolean a(float f2, float f3, float f4, float f5, MovementTypeEnum ao2) {
        if (ao2 == com.corrodinggames.rts.game.units.MovementTypeEnum.d || ao2 == com.corrodinggames.rts.game.units.MovementTypeEnum.a) {
            return true;
        }
        short s2 = com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.b(f2, f3, ao2);
        short s3 = com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.b(f4, f5, ao2);
        if (s2 == -3 || s3 == -3) {
            String string = "null";
            if (ao2 != null) {
                string = ao2.name();
            }
            this.d("pathPossible: no isolatedGroups found! (" + string + ")");
            com.corrodinggames.rts.gameFramework.GlobalState.T();
        }
        if (s2 == -1 || s3 == -1) {
            return false;
        }
        if (s2 == -2) {
            return false;
        }
        if (s3 == -2) {
            return false;
        }
        return s2 == s3;
    }

    public boolean a(UnitInstance am2, float f2, float f3) {
        return this.a(am2.eo, am2.ep, f2, f3, am2.h());
    }

    public boolean b(UnitInstance am2, float f2, float f3) {
        float f4 = 60.0f;
        MovementTypeEnum ao2 = am2.h();
        if (this.a(am2.eo, am2.ep, f2, f3, ao2)) {
            return true;
        }
        if (this.a(am2.eo, am2.ep, f2 + f4, f3, ao2)) {
            return true;
        }
        if (this.a(am2.eo, am2.ep, f2 - f4, f3, ao2)) {
            return true;
        }
        if (this.a(am2.eo, am2.ep, f2, f3 + f4, ao2)) {
            return true;
        }
        return this.a(am2.eo, am2.ep, f2, f3 - f4, ao2);
    }

    public boolean a(UnitInstance am2, UnitInstance am3) {
        return this.b(am2, am3.eo, am3.ep);
    }

    @Override
    public void serializeToStream(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) {
        as2.a(this.isHumanPlayer);
        as2.a(this.tier2Accum);
        as2.a(this.tier2Param);
        as2.a(this.tier3Accum);
        as2.a(this.tier3Param);
        as2.a(this.forwardZoneTimer);
        as2.a(this.recycleTimer);
        as2.a(this.repairTimer);
        as2.a(this.aiDisabled);
        as2.a(this.attackingCount);
        as2.a(this.zoneQueue.size());
        for (Object object : this.zoneQueue) {
            int n2 = -1;
            if (object instanceof CombatMain) {
                n2 = 1;
            } else if (object instanceof CombatAction) {
                n2 = 2;
            } else if (object instanceof com.corrodinggames.rts.game.ai.TransporterGroup) {
                n2 = 3;
            } else if (object instanceof PlainZone) {
                n2 = 4;
            } else if (object instanceof RallyGroup) {
                n2 = 5;
            } else {
                throw new RuntimeException("zone not instance not supported:" + object.getClass().getName());
            }
            as2.c(n2);
            as2.a(((AIStrategyNode) object).Q);
        }
        for (Object object : this.zoneQueue) {
            as2.a(((AIStrategyNode) object).Q);
            ((AIStrategyNode) object).serializeToStream(as2);
        }
        as2.c(9);
        as2.a(this.zoneIdCounter);
        as2.a(this.firstRun);
        as2.a(this.landPathExists);
        as2.a(this.waterPathExists);
        as2.a(this.harborPathExists);
        as2.a(this.airPathExists);
        as2.a(this.resourceZoneTimer);
        as2.a(this.transportCount);
        as2.a(this.totalUnitCount);
        as2.a(this.enemyUnitCount);
        as2.a(this.forwardZoneCount);
        as2.a(this.allowExpansion);
        as2.a(this.turtleFlag);
        as2.e();
        as2.a(this.aiBehaviors.a);
        for (int j = 0; j < this.aiBehaviors.a; ++j) {
            Object object;
            object = (com.corrodinggames.rts.game.ai.strategies.AIStrategyResult)this.aiBehaviors.get(j);
            as2.a((Enum)((com.corrodinggames.rts.game.ai.strategies.AIStrategyResult)object).a());  // 02b L276: var1.a((Enum)var6.a())
            ((com.corrodinggames.rts.game.ai.strategies.AIStrategyResult)object).a(as2);
        }
        as2.e();
        super.serializeToStream(as2);
    }

    public AIStrategyNode l(int n2) {
        if (n2 == 1) {
            return new CombatMain(this, -1.0f, -1.0f);
        }
        if (n2 == 2) {
            return new CombatAction(this);
        }
        if (n2 == 3) {
            return new com.corrodinggames.rts.game.ai.TransporterGroup(this);
        }
        if (n2 == 4) {
            return new PlainZone(this);
        }
        if (n2 == 5) {
            return new RallyGroup(this);
        }
        if (n2 == 0) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Found zone type 0, loading PlainZone instead");
            return new PlainZone(this);
        }
        throw new RuntimeException("Unknown zone type:" + n2);
    }


    public void c(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {
        int n2;
        this.isHumanPlayer = k2.e();
        this.tier2Accum = k2.readFloat();
        this.tier2Param = k2.readFloat();
        this.tier3Accum = k2.readFloat();
        this.tier3Param = k2.readFloat();
        this.forwardZoneTimer = k2.readFloat();
        this.recycleTimer = k2.f();
        this.repairTimer = k2.readFloat();
        this.aiDisabled = k2.e();
        this.attackingCount = k2.f();
        int n3 = k2.f();
        this.zoneQueue.clear();
        boolean transportCount = false;
        if (k2.b() >= 20) {
            transportCount = true;
            for (n2 = 0; n2 < n3; ++n2) {
                byte transportUnitsNonFlying = k2.d();
                AIStrategyNode o2 = this.l(transportUnitsNonFlying);
                o2.Q = k2.f();
            }
        }
        for (n2 = 0; n2 < n3; ++n2) {
            AIStrategyNode o3;
            if (!transportCount) {
                byte transportUnitsNonFlying = k2.d();
                o3 = this.l(transportUnitsNonFlying);
            } else {
                o3 = this.m(k2.f());
            }
            o3.a(k2);
        }
        n2 = k2.d();
        if (n2 >= 1) {
            this.zoneIdCounter = k2.f();
        }
        this.zoneSnapshot.clear();
        this.zoneSnapshot.addAll(this.zoneQueue);
        if (n2 >= 2) {
            this.firstRun = k2.e();
            this.landPathExists = k2.e();
            this.waterPathExists = k2.e();
        }
        if (n2 >= 3) {
            this.harborPathExists = k2.e();
            this.airPathExists = k2.e();
        }
        if (n2 >= 4) {
            this.resourceZoneTimer = k2.readFloat();
        }
        if (n2 >= 5) {
            this.transportCount = k2.f();
        }
        if (n2 >= 6) {
            this.totalUnitCount = k2.f();
            this.enemyUnitCount = k2.f();
            this.forwardZoneCount = k2.f();
        }
        if (n2 >= 7) {
            this.allowExpansion = k2.e();
        }
        if (n2 >= 8) {
            this.turtleFlag = k2.f();
        }
        if (n2 >= 9) {
            k2.a("ai-NetworkPlayer s");
            this.aiBehaviors.clear();
            int n4 = k2.f();
            for (int j = 0; j < n4; ++j) {
                com.corrodinggames.rts.game.ai.strategies.AIStrategy a2 = (com.corrodinggames.rts.game.ai.strategies.AIStrategy)k2.b(com.corrodinggames.rts.game.ai.strategies.AIStrategy.class);  // 02b L386: 读 b 枚举
                com.corrodinggames.rts.game.ai.strategies.AIStrategyResult b2 = a2.a();  // 02b L387: b.a() → a/a/a
                b2.a(k2);  // 02b L388: a/a/a.a(k)
                this.a(b2);  // 02b L389
            }
            k2.a("ai-NetworkPlayer e");
        }
        super.c(k2);
        this.ak();
    }

    public AIStrategyNode m(int n2) {
        for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneQueue) {
            if (o2.Q != n2) continue;
            return o2;
        }
        return null;
    }

    public int a(AIStrategyNode o2) {
        if (o2 == null) {
            return -1;
        }
        return o2.Q;
    }

    void ak() {
        this.airUnitCount = 0;
        this.factoryCount = 0;
        this.builderCount = 0;
        this.combatUnitCount = 0;
        this.buildingCount = 0;
        this.extractorCount = 0;
        this.harvesterCount = 0;
        this.scoutCount = 0;
        this.landUnitCount = 0;
        this.waterUnitCount = 0;
        this.experimentalCount = 0;
        for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneSnapshot) {
            AIStrategyNode o3;
            if (o2 instanceof CombatMain) {
                o3 = (CombatMain) o2;
                ++this.landUnitCount;
                if (((CombatMain) o3).u() >= 2) {
                    ++this.waterUnitCount;
                }
                if (((CombatMain) o3).n) {
                    ++this.experimentalCount;
                }
            }
            if (o2 instanceof CombatAction) {
                o3 = (CombatAction) o2;
                if (((CombatAction) o3).a) continue;
                if (((CombatAction) o3).isAttacker) {
                    ++this.airUnitCount;
                    if (!((CombatAction) o3).isRetreating && !((CombatAction) o3).d()) {
                        if (!((CombatAction) o3).field_B) {
                            ++this.buildingCount;
                        } else {
                            ++this.extractorCount;
                        }
                    }
                } else {
                    ++this.factoryCount;
                    if (((CombatAction) o3).d()) {
                        ++this.builderCount;
                    }
                    this.combatUnitCount += ((AIUnitGroupBase) o3).l();
                }
            }
            if (!(o2 instanceof com.corrodinggames.rts.game.ai.TransporterGroup)) continue;
            o3 = (AIUnitGroupBase) o2;
            ++this.harvesterCount;
            if (((AIUnitGroupBase) o3).l() <= 0) continue;
            ++this.scoutCount;
        }
    }

    private boolean a(UnitTypeHandle as2) {
        UnitInstance am2 = com.corrodinggames.rts.game.units.UnitInstance.setTeamInternal(as2);
        if (!am2.isFactoryBuilding() && am2 instanceof UnitType && !this.g(am2) && !am2.aj() && ((UnitType)am2).l()) {
            if (as2 instanceof com.corrodinggames.rts.game.units.custom.ModUnitRegistry) {
                com.corrodinggames.rts.game.units.custom.ModUnitRegistry l2 = (com.corrodinggames.rts.game.units.custom.ModUnitRegistry)as2;
                if (l2.fw || !l2.fs) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public AIStrategy(int n2) {
        this(n2, true);
    }

    public AIStrategy(int n2, boolean bl2) {
        super(n2, bl2);
    }

    private void av() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.tier2Accum = 100 + this.k * 9;
        this.tier3Accum = 202 + this.k * 19;
        this.tier1Accum = 50 + this.k * 2;
        this.repairTimer = 4200 + this.k * 5;
        this.forwardZoneTimer = 3500 + this.k * 5;
        this.resourceZoneTimer = 7500 + this.k * 5;
        this.debugPaint = new Paint();
        this.debugPaint.b(Color.a(0, 255, 0));
        this.debugPaint.a(Paint$Style.b);
        this.debugPaint.a(true);
        l2.b(this.debugPaint, 14.0f);
        this.al();
    }

    public void al() {
        for (UnitBuildStrategy d2 : (java.util.Collection<UnitBuildStrategy>) (java.util.Collection) this.aiBehaviorList) {
            d2.containsUnitType();
        }
    }

    public void d(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.b("ai_debug(" + this.k + ")", string);
    }

    public PointF am() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bL.a(GameUtils.a(0, l2.bL.mapHeight), GameUtils.a(0, l2.bL.tileWidth));
        this.centerPosition.a(l2.bL.scrollPixelX, l2.bL.scrollPixelY);
        return this.centerPosition;
    }

    public PointF an() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bL.A.size() == 0) {
            return null;
        }
        int n2 = GameUtils.c(l2.bL.A.size());
        Point point = (Point)l2.bL.A.get(n2);
        l2.bL.a(point.a, point.b);
        this.centerPosition.a(l2.bL.scrollPixelX, l2.bL.scrollPixelY);
        return this.centerPosition;
    }

    public PointF a(float f2, float f3) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f4 = -1.0f;
        PointF pointF = new PointF();
        for (int i2 = 0; i2 < l2.bL.A.size(); ++i2) {
            Point point = (Point)l2.bL.A.get(i2);
            l2.bL.a(point.a, point.b);
            this.centerPosition.a(l2.bL.scrollPixelX, l2.bL.scrollPixelY);
            PointF pointF2 = this.centerPosition;
            float f5 = GameUtils.a(pointF2.a, pointF2.b, f2, f3);
            if (!(f5 < f4) && f4 != -1.0f) continue;
            f4 = f5;
            pointF.a(pointF2);
        }
        if (f4 == -1.0f) {
            return null;
        }
        return pointF;
    }

    CombatMain e(UnitInstance am2) {
        for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneSnapshot) {
            CombatMain i2;
            if (!(o2 instanceof CombatMain) || !(i2 = (CombatMain) o2).b(am2)) continue;
            return i2;
        }
        return null;
    }

    CombatMain b(float f2, float f3) {
        for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneSnapshot) {
            CombatMain i2;
            if (!(o2 instanceof CombatMain) || !(i2 = (CombatMain) o2).c(f2, f3)) continue;
            return i2;
        }
        return null;
    }

    CombatMain f(UnitInstance am2) {
        return this.c(am2.eo, am2.ep);
    }

    CombatMain c(float f2, float f3) {
        float f4 = -1.0f;
        CombatMain i2 = null;
        for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneSnapshot) {
            if (!(o2 instanceof CombatMain)) continue;
            CombatMain i3 = (CombatMain) o2;
            float f5 = i3.d(f2, f3);
            if (i2 != null && !(f5 < f4)) continue;
            f4 = f5;
            i2 = i3;
        }
        return i2;
    }

    CombatMain a(MovementTypeEnum ao2, float f2, float f3, boolean bl2) {
        float f4 = -1.0f;
        CombatMain i2 = null;
        for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneSnapshot) {
            if (!(o2 instanceof CombatMain)) continue;
            CombatMain i3 = (CombatMain) o2;
            float f5 = i3.d(f2, f3);
            if (!this.a(f2, f3, i3, ao2) || bl2 && i3.t || i2 != null && !(f5 < f4)) continue;
            f4 = f5;
            i2 = i3;
        }
        return i2;
    }

    public static boolean a(UnitInstance am2, float f2, float f3, float f4) {
        float f5;
        float f6 = GameUtils.a(am2.eo, am2.ep, f2, f3);
        return f6 < (f5 = f4) * f5;
    }

    private boolean a(PointF pointF) {
        float f2;
        if (AIStrategy.a(this, pointF.a, pointF.b, 290.0f) != null) {
            return false;
        }
        CombatMain i2 = this.c(pointF.a, pointF.b);
        if (i2 != null && i2.d(pointF.a, pointF.b) < 490000.0f) {
            return false;
        }
        PointF pointF2 = this.a(pointF.a, pointF.b);
        if (pointF2 != null && (f2 = GameUtils.a(pointF.a, pointF.b, pointF2.a, pointF2.b)) < 160000.0f) {
            return false;
        }
        f2 = 60.0f;
        return !com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.d(pointF.a, pointF.b) && !com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.d(pointF.a + f2, pointF.b) && !com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.d(pointF.a, pointF.b + f2) && !com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.d(pointF.a - f2, pointF.b) && !com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.d(pointF.a, pointF.b + f2);
    }

    private boolean b(PointF pointF) {
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) com.corrodinggames.rts.game.units.UnitInstance.bE) {
            if (am2.player == this || !(am2 instanceof com.corrodinggames.rts.game.units.commands.CommandCenter)) continue;
            if (am2.player.c(this) && AIStrategy.a(am2, pointF.a, pointF.b, 300.0f)) {
                return false;
            }
            if (!am2.player.d(this) || !AIStrategy.a(am2, pointF.a, pointF.b, 320.0f)) continue;
            return false;
        }
        if (AIStrategy.b(this, pointF.a, pointF.b, 360.0f) >= 4) {
            return false;
        }
        boolean bl2 = true;
        return AIStrategy.a(this, pointF.a, pointF.b, 360.0f, bl2) < 2;
    }

    public int a(UnitBuildStrategy d2, IncludeExcludeMode b2) {
        int n2 = 0;
        for (UnitBuildStrategyEntry e2 : (java.util.Collection<UnitBuildStrategyEntry>) (java.util.Collection) d2.c) {
            n2 += this.a(e2.a, b2);
        }
        return n2;
    }

    public int a(UnitTypeHandle as2, IncludeExcludeMode b2) {
        return this.a(as2, true, b2);
    }

    public int a(UnitTypeHandle as2, boolean bl2, IncludeExcludeMode b2) {
        boolean bl3 = as2.j();
        Integer n2 = this.combatManager.clearAllCounts(bl3, as2, bl2);
        if (n2 != null) {
            return n2;
        }
        int n3 = 0;
        if (bl3) {
            bl2 = false;
        }
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n4 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (am2.player != this || b2 != IncludeExcludeMode.a && am2.bM) continue;
            if (am2.dz == as2) {
                ++n3;
            }
            if (!bl2 || !(am2 instanceof com.corrodinggames.rts.game.units.commands.CarrierUnit)) continue;
            com.corrodinggames.rts.game.units.commands.CarrierUnit l2 = (com.corrodinggames.rts.game.units.commands.CarrierUnit)((Object)am2);
            n3 += l2.h(as2);
        }
        this.combatManager.clearAllCounts(bl3, as2, bl2, n3);
        return n3;
    }

    public int ao() {
        int n2 = 0;
        for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneSnapshot) {
            if (!(o2 instanceof CombatAction)) continue;
            CombatAction g2 = (CombatAction) o2;
            n2 += g2.G.size();
        }
        return n2;
    }

    public boolean g(UnitInstance am2) {
        UnitType y2;
        if (am2 instanceof UnitType && (y2 = (UnitType)am2).cr()) {
            com.corrodinggames.rts.game.units.UnitTypeHandle as2 = y2.r();
            if (as2 instanceof com.corrodinggames.rts.game.units.custom.ModUnitRegistry) {
                com.corrodinggames.rts.game.units.custom.ModUnitRegistry l2 = (com.corrodinggames.rts.game.units.custom.ModUnitRegistry)as2;
                if (!l2.ft) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean h(UnitInstance am2) {
        UnitType y2;
        if (am2 instanceof UnitType && !(y2 = (UnitType)am2).bI() && y2.l() && !this.g(y2) && !y2.aj()) {
            com.corrodinggames.rts.game.units.UnitTypeHandle as2 = y2.r();
            if (as2 instanceof com.corrodinggames.rts.game.units.custom.ModUnitRegistry) {
                com.corrodinggames.rts.game.units.custom.ModUnitRegistry l2 = (com.corrodinggames.rts.game.units.custom.ModUnitRegistry)as2;
                if (!l2.fs) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean b(UnitInstance am2, UnitInstance am3) {
        UnitType y2;
        if (this.U) {
            if (am2 instanceof UnitType) {
                y2 = (UnitType)am2;
                if (y2.aq() && PathfindingHelper.a(y2, am3)) {
                    return true;
                }
            }
            return false;
        }
        return this.h(am2) && am2 instanceof UnitType && PathfindingHelper.a(y2 = (UnitType)am2, am3);
    }

    public void i(float f2) {
        float f3;
        float f4;
        float f5;
        float f6;
        Object object;
        if (!globalAIEnabled || !com.corrodinggames.rts.gameFramework.GlobalState.B().bl) {
            return;
        }
        if (this.aiSleeping || this.aiDisabled) {
            return;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n2 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            float f7;
            UnitInstance am2 = amArray[i2];
            if (am2.player != this || !l2.cN.b((int)(am2.eo - (f7 = 200.0f)), (int)(am2.ep - f7), (int)(am2.eo + f7), (int)(am2.ep + f7))) continue;
            if (am2 instanceof UnitType) {
                object = (UnitType)am2;
            }
            object = "";
            f6 = am2.ep - l2.cx - 60.0f;
            this.debugPaint.b(Color.a(0, 255, 0));
            if (am2 instanceof com.corrodinggames.rts.game.units.commands.CommandCenter) {
                f6 -= 80.0f;
                object = (String)object + "Base ( Team:" + this.k + " )";
                object = (String)object + "\nuseTransportsOnThisMap: " + this.useTransportsOnThisMap();
                object = (String)object + "\nuseHoverTransportsOnThisMap: " + this.useHoverTransportsOnThisMap();
                object = (String)object + "\nattackingCount: " + this.attackingCount;
                object = (String)object + "\ndefendingCount: " + this.defendingCount;
                object = (String)object + "\nnumOfUnitsNeedingTransport: " + this.ao();
                object = (String)object + "\ntransport: " + this.scoutCount;
                if (this.isTurtleEnabled()) {
                    object = (String)object + "\nTurtling: true";
                }
                this.debugPaint.b(Color.a(255, 255, 255));
            }
            if (((String)object).length() == 0) continue;
            for (String object2 : ((String)object).split("\n")) {
                f5 = am2.eo - l2.cw;
                f4 = f6;
                f3 = -this.debugPaint.l() + this.debugPaint.m();
                l2.bO.k();
                if (l2.cX > 1.0f) {
                    l2.S();
                    f5 *= l2.cX;
                    f4 *= l2.cX;
                    f3 /= l2.cX;
                }
                l2.bO.a(object2, f5, f4, this.debugPaint);
                l2.bO.l();
                f6 += f3;
            }
        }
        for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneQueue) {
            Object object3;
            if (!l2.cN.b((int)(o2.S - o2.U), (int)(o2.T - o2.U), (int)(o2.S + o2.U), (int)(o2.T + o2.U))) continue;
            this.debugPaint.b(this.K());
            l2.bO.a(o2.S - l2.cw, o2.T - l2.cx, o2.U + 2.0f, this.debugPaint);
            int n3 = Color.a(0, 255, 0);
            String string = "";
            object = o2.getClass().getSimpleName();
            string = string + "\n" + (String)object + " ( Team:" + this.k + " )";
            f6 = o2.T - l2.cx;
            if (o2 instanceof CombatMain) {
                f6 -= 50.0f;
                object3 = (CombatMain) o2;
                string = string + "\nState: " + ((CombatMain) object3).b.name() + "(id:" + ((CombatMain) object3).Q + ")";
                string = string + "\nunsafe: " + ((CombatMain) object3).f() + " (" + ((CombatMain) object3).s + ")";
                string = string + "\nunsafeBaseTimer: " + ((CombatMain) object3).v;
                string = string + "\nallowedUnits: " + ((CombatMain) object3).d;
                if (((CombatMain) object3).z != null) {
                    string = string + "\nlastAttemptedBuilding: " + ((CombatMain) object3).z.i();
                }
                if (((CombatMain) object3).lastCantAffordBuilding != null) {
                    string = string + "\nlastAttemptedBuilding-cannotAffordPrice: " + ((CombatMain) object3).lastCantAffordBuilding.a(false, true, 4, true);
                }
                if (((CombatMain) object3).lastFailedBuilding != null) {
                    string = string + "\nlastAttemptedBuilding-cannotAffordBy: " + ((CombatMain) object3).lastFailedBuilding.a(false, true, 4, true);
                }
                string = string + "\nlastAttemptedBuildingCount: " + ((CombatMain) object3).lastAttemptedBuildingCount;
                string = string + "\nlastAttemptedBuildingFailed: " + ((CombatMain) object3).lastAttemptedBuildingFailed;
                string = string + "\nlastUnitAttempt: " + ((CombatMain) object3).lastUnitAttemptName + " (" + ((CombatMain) object3).lastUnitAttemptCost + " - " + ((CombatMain) object3).lastUnitAttemptBuildTime + ")";
                string = string + "\nbuildBuildingDelay: " + ((CombatMain) object3).e;
                string = string + "\ncredits: " + GameUtils.c(this.o) + " (x" + GameUtils.formatBytes(this.getExpenseRate()) + ")";
                if (((CombatMain) object3).b == com.corrodinggames.rts.game.ai.BaseZoneType.a) {
                    string = string + "\nclaimedBaseTimer: " + ((CombatMain) object3).l;
                }
                if (((CombatMain) object3).k > 100.0f) {
                    string = string + "\nabandonedTimer: " + ((CombatMain) object3).k;
                }
                if (((CombatMain) object3).g > 0.0f) {
                    string = string + "\nrequestedBuildersDelay: " + ((CombatMain) object3).g + " (" + ((CombatMain) object3).h + ")";
                }
                string = string + "\nBuilders: " + ((CombatMain) object3).numberOfCombatUnits;
                string = string + "\nIdle Builders: " + ((CombatMain) object3).numberOfIdleBuilders;
            }
            if (o2 instanceof CombatAction) {
                object3 = (CombatAction) o2;
                if (((CombatAction) object3).recruitingEnabled) {
                    string = string + "\nVIP Mode";
                }
                string = string + "\n" + (((CombatAction) object3).b() ? "Defensive Type" : "Attack Type");
                string = string + "\nUnits: " + ((CombatAction) object3).F.size() + " / " + ((CombatAction) object3).field_A;
                string = string + "\nStagingForAttack: " + ((CombatAction) object3).isStaging;
                string = string + "\nAttackDelay: " + ((CombatAction) object3).idleTimer;
                if (((CombatAction) object3).stagingWaitTimer != 0.0f) {
                    string = string + "\nStagingTimer: " + ((CombatAction) object3).stagingWaitTimer;
                }
                string = string + "\nStagingTargetFound: " + ((CombatAction) object3).hasAttackTarget;
                if (((CombatAction) object3).missionTimer != 0.0f) {
                    string = string + "\nattackingFor: " + ((CombatAction) object3).missionTimer;
                }
                string = string + "\ncommonMovement: " + ((CombatAction) object3).i().name();
                if (((CombatAction) object3).field_B) {
                    string = string + " (seaGroup)";
                }
                if (((CombatAction) object3).G.size() > 0) {
                    string = string + "\nunitsNeedingTransport:" + ((CombatAction) object3).G.size();
                }
                if (((CombatAction) object3).lastActionName != null) {
                    string = string + "\nlast action:" + ((CombatAction) object3).lastActionName;
                }
                if (!((CombatAction) object3).isRetreating && !((CombatAction) object3).isStaging) {
                    string = string + "\nnext move:" + (int)this.k(((CombatAction) object3).randomMoveTimer) + "s";
                }
            }
            if (o2 instanceof com.corrodinggames.rts.game.ai.TransporterGroup) {
                object3 = (com.corrodinggames.rts.game.ai.TransporterGroup)o2;
                string = string + "\nUnitsWanted: " + ((com.corrodinggames.rts.game.ai.TransporterGroup)object3).l;
                string = string + "\nunits: " + ((com.corrodinggames.rts.game.ai.TransporterGroup)object3).F.size();
                string = string + "\nreadyToMoveOut: " + ((com.corrodinggames.rts.game.ai.TransporterGroup)object3).q;
                if (((com.corrodinggames.rts.game.ai.TransporterGroup)object3).m != null) {
                    string = string + "\nCurrentlyHelping: " + ((com.corrodinggames.rts.game.ai.TransporterGroup)object3).m.Q;
                }
            }
            if (o2 instanceof RallyGroup) {
                object3 = (RallyGroup) o2;
                string = string + "\nneedsTransportGroup: " + ((RallyGroup) object3).a;
            }
            this.debugPaint.b(this.K());
            for (String string2 : string.split("\n")) {
                if (string2.trim().equals("")) continue;
                f5 = o2.S - l2.cw;
                f4 = f6;
                f3 = -this.debugPaint.l() + this.debugPaint.m();
                l2.bO.k();
                if (l2.cX > 1.0f) {
                    l2.S();
                    f5 *= l2.cX;
                    f4 *= l2.cX;
                    f3 /= l2.cX;
                }
                l2.bO.a(string2, f5, f4, this.debugPaint);
                l2.bO.l();
                f6 += f3;
                this.debugPaint.b(n3);
            }
        }
    }

    public UnitInstance e(com.corrodinggames.rts.game.PlayerState n2) {
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) com.corrodinggames.rts.game.units.UnitInstance.bE) {
            if (am2.player != n2 || !(am2 instanceof com.corrodinggames.rts.game.units.commands.CommandCenter) && !am2.bP) continue;
            return am2;
        }
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) com.corrodinggames.rts.game.units.UnitInstance.bE) {
            if (am2.player != n2 || !am2.bO) continue;
            return am2;
        }
        return null;
    }


    public void a(float f2) {
        Object object4;
        int n2;
        Object object2;
        super.a(f2);
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.aiDisabled || this.aiSleeping) {
            return;
        }
        if (l2.bX.B) {
            if (!l2.bX.C) {
                return;
            }
            if (l2.cb.j()) {
                return;
            }
        }
        if (this.updateCooldown > 0.0f) {
            this.updateCooldown -= f2;
            return;
        }
        this.difficultyIndex = this.C();
        if (this.firstRunDelayed && l2.by > 3000) {
            this.firstRunDelayed = false;
            object2 = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
            int n3 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
            for (n2 = 0; n2 < n3; ++n2) {
                object4 = ((UnitInstance[]) object2)[n2];
                if (!(object4 instanceof com.corrodinggames.rts.game.units.f)) continue;
                this.d("firstRunDelayed: Found damagingBorder");
                this.damagingBorder = (com.corrodinggames.rts.game.units.f)object4;
                break;
            }
        }
        if (this.firstRun) {
            this.firstRun = false;
            this.landPathExists = true;
            this.waterPathExists = true;
            this.harborPathExists = true;
            this.airPathExists = true;
            object2 = this.e(this);
            if (object2 == null) {
                this.d("firstRun: no command center found");
            }
            if (object2 != null) {
                for (n2 = 0; n2 < com.corrodinggames.rts.game.PlayerState.c; ++n2) {
                    com.corrodinggames.rts.game.PlayerState n4 = com.corrodinggames.rts.game.PlayerState.k(n2);
                    if (n4 == null || n4 == this || (object4 = this.e(n4)) == null) continue;
                    if (!this.a(((UnitInstance) object2).eo, ((UnitInstance) object2).ep, ((UnitInstance) object4).eo, ((UnitInstance) object4).ep, com.corrodinggames.rts.game.units.MovementTypeEnum.b)) {
                        this.landPathExists = false;
                    }
                    if (this.a(((UnitInstance) object2).eo, ((UnitInstance) object2).ep, ((UnitInstance) object4).eo, ((UnitInstance) object4).ep, com.corrodinggames.rts.game.units.MovementTypeEnum.f)) continue;
                    this.waterPathExists = false;
                }
                for (Iterator<Point> pointIterator = l2.bL.A.iterator(); pointIterator.hasNext();) {
                    Point point = pointIterator.next();
                    object4 = l2.bL.a(point);
                    if (!this.a(((UnitInstance) object2).eo, ((UnitInstance) object2).ep, ((PointF)object4).a, ((PointF)object4).b + (float)l2.bL.tilePixelHeight, com.corrodinggames.rts.game.units.MovementTypeEnum.b)) {
                        this.harborPathExists = false;
                    }
                    if (this.a(((UnitInstance) object2).eo, ((UnitInstance) object2).ep, ((PointF)object4).a, ((PointF)object4).b + (float)l2.bL.tilePixelHeight, com.corrodinggames.rts.game.units.MovementTypeEnum.f)) continue;
                    this.airPathExists = false;
                }
            }
        }
        this.tier1Accum += f2;
        this.creditAccumulator += f2;
        if (this.tier1Accum > 25.0f) {
            Object object3;
            this.tier1Accum -= 25.0f;
            if (this.tier1Accum > 25.0f) {
                this.tier1Accum = 25.0f;
            }
            if (this.tier1Accum < -1.0f) {
                this.tier1Accum = -1.0f;
            }
            boolean bl2 = false;
            n2 = 0;
            for (Object object4_998 : this.zoneQueue) {
                if (!(object4_998 instanceof CombatMain)) continue;
                object3 = (CombatMain) object4_998;
                ((CombatMain) object3).a += this.creditAccumulator;
            }
            for (int i2 = 0; i2 < 2; ++i2) {
                object4 = null;
                for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneQueue) {
                    if (!(o2 instanceof CombatMain)) continue;
                    CombatMain i3 = (CombatMain) o2;
                    if (object4 != null && !(((CombatMain) object4).a < i3.a)) continue;
                    object4 = i3;
                }
                if (object4 == null || ((CombatMain) object4).a < 50.0f) break;
                object3 = object4;
                ((CombatMain) object3).b(((CombatMain) object3).a);
                ((CombatMain) object3).d(((CombatMain) object3).a);
                ((CombatMain) object3).a = 0.0f;
            }
            this.creditAccumulator = 0.0f;
        }
        this.tier2Accum += f2;
        this.tier2Param += f2;
        if (this.tier2Accum > 80.0f) {
            this.n(this.tier2Param);
            this.tier2Accum -= 80.0f;
            if (this.tier2Accum > 80.0f) {
                this.tier2Accum = 80.0f;
            }
            if (this.tier2Accum < -1.0f) {
                this.tier2Accum = -1.0f;
            }
            this.tier2Param = 0.0f;
        }
        this.tier3Accum += f2;
        this.tier3Param += f2;
        if (this.tier3Accum > 250.0f) {
            this.m(this.tier3Param);
            this.tier3Accum -= 250.0f;
            if (this.tier3Accum > 250.0f) {
                this.tier3Accum = 250.0f;
            }
            if (this.tier3Accum < -1.0f) {
                this.tier3Accum = -1.0f;
            }
            this.tier3Param = 0.0f;
        }
    }

    public float j(float f2) {
        return f2 / 60.0f * 1000.0f;
    }

    public float k(float f2) {
        return f2 / 60.0f;
    }

    public void a(UnitType y2, com.corrodinggames.rts.game.units.actions.ActionId c2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.gete4(this);
        e2.a(y2);
        e2.a(c2);
    }

    public void l(float f2) {
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) com.corrodinggames.rts.game.units.UnitInstance.bE) {
            boolean bl2;
            Object object;
            boolean bl3;
            if (am2.player != this || !(am2 instanceof UnitType) || !this.i(am2)) continue;
            UnitType y2 = (UnitType)am2;
            if (y2 instanceof com.corrodinggames.rts.game.units.debug.FactoryAction5) {
                bl3 = false;
                object = y2.ab();
                if (object != null && y2.h((UnitInstance) object)) {
                    bl3 = !((UnitInstance) object).cH();
                }
                boolean bl4 = bl2 = !y2.Q();
                if (bl3 && bl3 != bl2) {
                    this.a(y2, com.corrodinggames.rts.game.units.debug.FactoryAction5.j.N());
                }
                if (!bl3 && bl3 != bl2) {
                    this.a(y2, com.corrodinggames.rts.game.units.debug.FactoryAction5.k.N());
                }
            }
            if (y2 instanceof com.corrodinggames.rts.game.units.buildings.AttackBehavior) {
                bl3 = true;
                object = y2.ab();
                if (object != null && y2.h((UnitInstance) object)) {
                    bl3 = !((UnitInstance) object).Q();
                }
                boolean bl5 = bl2 = !y2.Q();
                if (bl3 && bl3 != bl2) {
                    this.a(y2, com.corrodinggames.rts.game.units.buildings.AttackBehavior.y.N());
                }
                if (!bl3 && bl3 != bl2) {
                    this.a(y2, com.corrodinggames.rts.game.units.buildings.AttackBehavior.z.N());
                }
            }
            if (y2.be() != com.corrodinggames.rts.game.units.PathResult.d || !y2.aq() || y2.ab() == null) continue;
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            object = l2.cf.gete4(this);
            ((com.corrodinggames.rts.gameFramework.Command)object).a(y2);
            ((com.corrodinggames.rts.gameFramework.Command)object).a(y2.ab());
        }
    }

    public com.corrodinggames.rts.game.units.UnitFlag c(UnitType y2) {
        if (y2.aS()) {
            boolean bl2 = true;
            if (y2.aj()) {
                bl2 = false;
            }
            if (this.g(y2)) {
                bl2 = false;
            }
            if (bl2) {
                if (this.allowExpansion) {
                    return com.corrodinggames.rts.game.units.UnitFlag.f;
                }
                return com.corrodinggames.rts.game.units.UnitFlag.a;
            }
        }
        return com.corrodinggames.rts.game.units.UnitFlag.b;
    }

    public ArrayList ap() {
        zoneTypeList.clear();
        return zoneTypeList;
    }

    public void d(UnitType y2) {
        java.util.Iterator iterator = this.aiBehaviors.iterator();
        while (iterator.hasNext()) {
            com.corrodinggames.rts.game.ai.strategies.AIStrategyResult a2 = (com.corrodinggames.rts.game.ai.strategies.AIStrategyResult) iterator.next();
            a2.a(this, y2);
        }
    }

    public void e(UnitType y2) {
        java.util.Iterator iterator = this.aiBehaviors.iterator();
        while (iterator.hasNext()) {
            com.corrodinggames.rts.game.ai.strategies.AIStrategyResult a2 = (com.corrodinggames.rts.game.ai.strategies.AIStrategyResult) iterator.next();
            a2.b(this, y2);
        }
    }

    public void m(float f2) {
        int n2;
        Object object;
        Object object2;
        int n3;
        Object object32;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.combatManager.clearAllCounts();
        for (Object object32_1149 : this.aiBehaviors) {
            ((com.corrodinggames.rts.game.ai.strategies.AIStrategyResult)object32_1149).b(this.j(f2), this);
        }
        int n4 = 0;
        object32 = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n5 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (n3 = 0; n3 < n5; ++n3) {
            object2 = ((UnitInstance[]) object32)[n3];
            if (((UnitInstance) object2).player != this || ((UnitInstance) object2).u()) continue;
            ++n4;
            if (!(object2 instanceof UnitType)) continue;
            object = (UnitType)object2;
            if (!((UnitType)object).bD) {
                ((UnitType)object).bD = true;
                this.d((UnitType)object);
            }
            if (((UnitInstance) object2).cN != null) continue;
            Object object4 = ((UnitType)object).aC;
            ((UnitType)object).aC = this.f((UnitInstance) object);
            if (((UnitType)object).aC == null || object4 == ((UnitType)object).aC) continue;
            if (((UnitInstance) object).isFactoryBuilding()) {
                ((UnitType)object).aD = this.a(((UnitInstance) object2).eo, ((UnitInstance) object2).ep, ((UnitType)object).aC.S, ((UnitType)object).aC.T, com.corrodinggames.rts.game.units.MovementTypeEnum.b);
                if (((UnitType)object).aD || !((UnitInstance) object).r().p()) continue;
                ((UnitType)object).aD = this.a(((UnitInstance) object2).eo, ((UnitInstance) object2).ep + 15.0f, ((UnitType)object).aC.S, ((UnitType)object).aC.T, com.corrodinggames.rts.game.units.MovementTypeEnum.b);
                continue;
            }
            ((UnitType)object).aD = this.a(((UnitInstance) object2).eo, ((UnitInstance) object2).ep, ((UnitType)object).aC.S, ((UnitType)object).aC.T, com.corrodinggames.rts.game.units.MovementTypeEnum.b);
        }
        this.l(f2);
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) com.corrodinggames.rts.game.units.UnitInstance.bE) {
            if (am2.player != this || !(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType)am2;
            object2 = this.c(y2);
            if (y2.P != object2 && this.i(y2)) {
                object = l2.cf.gete4(this);
                ((com.corrodinggames.rts.gameFramework.Command)object).a(y2);
                ((com.corrodinggames.rts.gameFramework.Command)object).a((com.corrodinggames.rts.game.units.UnitFlag)((Object)object2));
            }
            if (!y2.aj() || !y2.dd() || y2.aB != null || !this.i(y2)) continue;
            com.corrodinggames.rts.game.ai.CombatAction.a(this, y2);
        }
        if (n4 == 0 && !this.U) {
            this.aiSleeping = true;
        }
        this.resourceZoneTimer = GameUtils.a(this.resourceZoneTimer, f2);
        this.forwardZoneTimer = GameUtils.a(this.forwardZoneTimer, f2);
        if (this.shouldRushExpansion()) {
            this.forwardZoneTimer = GameUtils.a(this.forwardZoneTimer, 4.0f * f2);
        }
        if (this.forwardZoneTimer == 0.0f) {
            int n6 = 0;
            for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneSnapshot) {
                if (!(o2 instanceof CombatMain)) continue;
                object2 = (CombatMain) o2;
                if (((CombatMain) object2).b != com.corrodinggames.rts.game.ai.BaseZoneType.a) continue;
                ++n6;
            }
            n3 = 0;
            if (n6 > 2) {
                n3 = 1;
            }
            if (n3 != 0) {
                this.forwardZoneTimer = 300.0f;
            } else {
                PointF pointF = this.an();
                if (pointF != null) {
                    pointF.b += (float)l2.bL.tilePixelHeight;
                    if (this.b(pointF.a, pointF.b) == null && this.b(pointF)) {
                        this.forwardZoneTimer = 2000.0f;
                        object2 = new CombatMain(this, pointF.a, pointF.b);
                        ((CombatMain) object2).U = 360.0f;
                        ((CombatMain) object2).b = com.corrodinggames.rts.game.ai.BaseZoneType.a;
                        ((CombatMain) object2).c = com.corrodinggames.rts.game.ai.BaseZoneStage.b;
                        ++this.forwardZoneCount;
                    }
                }
            }
        }
        if (this.resourceZoneTimer == 0.0f) {
            this.resourceZoneTimer = 100.0f;
            int n7 = 0;
            Object object5 = this.zoneSnapshot.iterator();
            while (((Iterator) object5).hasNext()) {
                AIStrategyNode o3 = (AIStrategyNode) ((Iterator) object5).next();
                if (!(o3 instanceof CombatMain)) continue;
                object2 = (CombatMain) o3;
                if (((CombatMain) object2).c != com.corrodinggames.rts.game.ai.BaseZoneStage.c) continue;
                ++n7;
            }
            if (n7 < 3 && (object5 = this.ar()) != null) {
                PointF pointF = new PointF();
                pointF.a = ((UnitInstance) object5).eo;
                pointF.b = ((UnitInstance) object5).ep;
                if (pointF != null && this.b(pointF.a, pointF.b) == null && this.a(pointF)) {
                    this.resourceZoneTimer = 5000.0f;
                    object2 = new CombatMain(this, pointF.a, pointF.b);
                    ((CombatMain) object2).U = 310.0f;
                    ((CombatMain) object2).b = com.corrodinggames.rts.game.ai.BaseZoneType.a;
                    ((CombatMain) object2).c = com.corrodinggames.rts.game.ai.BaseZoneStage.c;
                    ++this.forwardZoneCount;
                }
            }
        }
        this.amphibiousCount = 0;
        this.attackingCount = 0;
        this.defendingCount = 0;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        n5 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (n2 = 0; n2 < n5; ++n2) {
            object2 = amArray[n2];
            if (((UnitInstance) object2).player != this || !(object2 instanceof UnitType)) continue;
            object = (UnitType)object2;
            if (((UnitInstance) object2).bI()) continue;
            if (((UnitType)object).aB != null && ((UnitType)object).aB.b()) {
                ++this.defendingCount;
                continue;
            }
            if (!this.h((UnitInstance) object) || ((UnitType)object).bM) continue;
            if (((UnitInstance) object).h() == com.corrodinggames.rts.game.units.MovementTypeEnum.e) {
                ++this.amphibiousCount;
                continue;
            }
            ++this.attackingCount;
        }
        this.groupUpdateTimer = GameUtils.a(this.groupUpdateTimer, f2);
        this.groupUpdateAccum += f2;
        if (this.groupUpdateTimer == 0.0f) {
            Object object62;
            int n8 = 0;
            n2 = 0;
            n5 = 0;
            int n9 = 0;
            for (Object object4 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
                if (((UnitInstance) object4).player != this || !((UnitInstance) object4).isAlive()) continue;
                if ((object4 instanceof com.corrodinggames.rts.game.units.commands.ExperimentalBuilding || object4 instanceof com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit || object4 instanceof Structures) && object4 instanceof com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit) {
                    ++n2;
                    object62 = (com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit)object4;
                    if (((com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit)object62).V() > 1) {
                        ++n8;
                    }
                }
                if (!((UnitInstance) object4).r().p()) continue;
                ++n5;
                object62 = ((UnitInstance) object4).getDefaultActionType();
                if (!com.corrodinggames.rts.game.units.actions.GameAction.getResourceCost((com.corrodinggames.rts.game.units.actions.ActionId)object62)) continue;
                ++n9;
            }
            if (this.a(4100.0) || this.groupUpdateAccum > 2400.0f || this.experimentalCount == 0) {
                Object object7;
                Object object8;
                for (Object object4 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
                    if (((UnitInstance) object4).player != this || !(object4 instanceof UnitType) || !((UnitInstance) (object62 = (UnitType)object4)).isIdle()) continue;
                    object8 = ((UnitInstance) object62).N();
                    object7 = this.ap();
                    Iterator iterator = ((ArrayList)object8).iterator();
                    while (iterator.hasNext()) {
                        GameAction s2 = (GameAction) iterator.next();
                        if (!s2.n((UnitInstance) object62)) continue;
                        ((ArrayList)object7).add(s2);
                    }
                    if (((ArrayList)object7).size() <= 0) continue;
                    this.a((UnitType)object62, (GameAction) com.corrodinggames.rts.game.ai.AIUnitActionUtils.getRandomElement((AbstractList)object7));
                }
                boolean bl2 = false;
                if (this.a(30000.0)) {
                    bl2 = true;
                }
                for (Object object62_1316 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
                    boolean bl3;
                    if (((UnitInstance) object62_1316).player != this || !(object62_1316 instanceof UnitType) || !com.corrodinggames.rts.game.units.actions.GameAction.getResourceCost((com.corrodinggames.rts.game.units.actions.ActionId)(object7 = ((UnitInstance) (object8 = (UnitType)object62_1316)).getDefaultActionType()))) continue;
                    float f3 = ((UnitInstance) object8).getActionCooldownTime();
                    if (f3 < 0.0f) {
                        f3 = 6.0f;
                        bl3 = false;
                    } else {
                        bl3 = true;
                    }
                    if (f3 == 0.0f) continue;
                    boolean bl4 = false;
                    int n10 = GameUtils.c(100);
                    float f4 = 100.0f - f3;
                    if (bl2) {
                        f4 -= 4.0f;
                    }
                    if (!bl3) {
                        if (((UnitInstance) object62_1316).r().p() && n9 > 0) {
                            f4 = 50.0f;
                        }
                        if (n2 > 0 && n8 == 0) {
                            f4 = 99.0f;
                            if (object62_1316 instanceof com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit) {
                                f4 = 40.0f;
                            }
                        }
                    }
                    if (f4 < 10.0f) {
                        f4 = 10.0f;
                    }
                    if (!(bl4 = (float)n10 > f4)) continue;
                    boolean bl5 = ((UnitInstance) object8).isActionAvailable();
                    if (bl5) {
                        // empty if block
                    }
                    if (GameUtils.c(100) > 50) {
                        ((UnitInstance) object8).a(this.sortedZones);
                        if (this.sortedZones.size() != 0) {
                            object7 = (com.corrodinggames.rts.game.units.actions.ActionId)this.sortedZones.get(new Random().nextInt(this.sortedZones.size()));
                        }
                    }
                    boolean bl6 = false;
                    GameAction s3 = ((UnitInstance) object8).a((com.corrodinggames.rts.game.units.actions.ActionId)object7);
                    if (s3 != null) {
                        if (s3.m((UnitInstance) object8)) {
                            bl6 = true;
                        }
                        if (s3.e() == com.corrodinggames.rts.game.units.actions.ActionTargetType.g) {
                            bl6 = true;
                        }
                        if (!s3.b((UnitInstance) object8)) {
                            bl6 = true;
                        }
                        if (!s3.a((UnitInstance) object8, false)) {
                            bl6 = true;
                        }
                    } else {
                        bl6 = true;
                    }
                    if (bl6) continue;
                    this.a((UnitType)object8, (com.corrodinggames.rts.game.units.actions.ActionId)object7);
                    com.corrodinggames.rts.game.units.custom.resources.CustomActionBase b2 = s3.B();
                    boolean bl7 = true;
                    this.a((UnitType)object8, b2, bl7);
                    this.groupUpdateTimer = 900.0f;
                    this.groupUpdateAccum = 0.0f;
                    if (bl2 && !(this.a(40000.0) ? GameUtils.c(100) > 95 : GameUtils.c(100) > 80)) continue;
                    break;
                }
            }
        }
        for (AIStrategyNode o4 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneQueue) {
            if (!(o4 instanceof AIUnitGroupBase)) continue;
            AIUnitGroupBase h2 = (AIUnitGroupBase) o4;
            h2.b(f2);
        }
    }

    public boolean a(UnitType y2, GameAction s2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (s2.b(y2) && s2.a((UnitInstance) y2, false)) {
            com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.gete4(this);
            e2.a(y2);
            e2.a(s2.z());
            return true;
        }
        return false;
    }

    public boolean a(UnitType y2, GameAction s2, PointF pointF, UnitInstance am2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (s2.b(y2) && s2.a((UnitInstance) y2, false)) {
            com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.gete4(this);
            e2.a(y2);
            e2.a(s2.z(), pointF, am2);
            return true;
        }
        return false;
    }

    public void aq() {
        for (Object object : this.zoneQueue) {
            if (!(object instanceof CombatMain)) continue;
            ((CombatMain) object).t();
        }
        for (Object object : this.zoneQueue) {
            for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneQueue) {
                if (object == o2 || ((AIStrategyNode) object).Q != o2.Q) continue;
                com.corrodinggames.rts.gameFramework.GlobalState.a("Id overlap on:" + ((AIStrategyNode) object).Q);
                com.corrodinggames.rts.gameFramework.GlobalState.a("zone x:" + ((AIStrategyNode) object).S);
                com.corrodinggames.rts.gameFramework.GlobalState.a("zone y:" + ((AIStrategyNode) object).T);
                com.corrodinggames.rts.gameFramework.GlobalState.a("zone radius:" + ((AIStrategyNode) object).U);
                com.corrodinggames.rts.gameFramework.GlobalState.a("zone type:" + object.getClass().getName());
            }
        }
        int n2 = 0;
        for (Object object : this.zoneQueue) {
            if (!(object instanceof CombatMain)) continue;
            ++n2;
        }
        int n3 = 0;
        for (AIStrategyNode o2 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneQueue) {
            if (!(o2 instanceof CombatMain)) continue;
            for (AIStrategyNode o3 : (java.util.Collection<AIStrategyNode>) (java.util.Collection) this.zoneQueue) {
                float f2;
                if (!(o3 instanceof CombatMain) || o2 == o3 || !((f2 = GameUtils.a(o2.S, o2.T, o3.S, o3.T)) < 400.0f)) continue;
                ++n3;
            }
        }
        if (n3 > 0) {
            this.d("baseOverlapCount:" + n3);
        }
    }


    public void a(UnitType y2) {
        if (y2.player == this) {
            this.combatManager.clearAllCounts(y2);
        }
    }

    public void n(float f2) {
        Object object;
        Object object2;
        Object object3;
        UnitType bq2;
        int n2;
        Object object42;
        Object object5;
        Object object62;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.combatManager.clearAllCounts();
        for (Object object7 : this.aiBehaviors) {
            ((com.corrodinggames.rts.game.ai.strategies.AIStrategyResult)object7).a(this.j(f2), this);
        }
        for (Object object7 : this.zoneQueue) {
            if (!(object7 instanceof AIUnitGroupBase)) continue;
            object62 = (AIUnitGroupBase) object7;
            ((AIUnitGroupBase) object62).c(f2);
        }
        if (this.damagingBorder != null) {
            for (Object object7 : this.zoneQueue) {
                if (!this.damagingBorder.a(((AIStrategyNode) object7).S, ((AIStrategyNode) object7).T)) continue;
                if (object7 instanceof CombatMain) {
                    ((AIStrategyNode) object7).p();
                    break;
                }
                if (!(object7 instanceof CombatAction)) continue;
                object62 = this.damagingBorder.a(((AIStrategyNode) object7).S, ((AIStrategyNode) object7).T, ((AIStrategyNode) object7).U + 20.0f);
                ((AIStrategyNode) object7).S = ((PointF)object62).a;
                ((AIStrategyNode) object7).T = ((PointF)object62).b;
            }
        }
        this.repairTimer = GameUtils.a(this.repairTimer, f2);
        int n3 = 0;
        for (Object object62_1492 : this.zoneSnapshot) {
            if (!(object62_1492 instanceof CombatMain)) continue;
            ++n3;
        }
        if (n3 < 1) {
            Object object8;
            for (Object object62_1498 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
                if (((UnitInstance) object62_1498).player != this || !(object62_1498 instanceof com.corrodinggames.rts.game.units.commands.CommandCenter)) continue;
                object5 = new CombatMain(this, ((UnitInstance) object62_1498).eo, ((UnitInstance) object62_1498).ep);
                ((CombatMain) object5).U = 420.0f;
                ((CombatMain) object5).b = com.corrodinggames.rts.game.ai.BaseZoneType.c;
                ((CombatMain) object5).c = com.corrodinggames.rts.game.ai.BaseZoneStage.a;
                ++n3;
                break;
            }
            if (n3 < 1) {
                for (Object object62_1508 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
                    if (((UnitInstance) object62_1508).player != this || !this.builderUnits.containsUnitType(((UnitInstance) object62_1508).r())) continue;
                    object5 = new CombatMain(this, ((UnitInstance) object62_1508).eo, ((UnitInstance) object62_1508).ep);
                    ((CombatMain) object5).U = 420.0f;
                    ((CombatMain) object5).b = com.corrodinggames.rts.game.ai.BaseZoneType.c;
                    ((CombatMain) object5).c = com.corrodinggames.rts.game.ai.BaseZoneStage.a;
                    ++n3;
                    break;
                }
            }
            if (n3 < 1) {
                for (Object object62_1519 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
                    if (((UnitInstance) object62_1519).player != this || !(object62_1519 instanceof UnitType)) continue;
                    object5 = (UnitType)object62_1519;
                    boolean bl2 = false;
                    for (Object object42_1523 : this.builderUnits.c) {
                        if (!((UnitType)object5).b(((UnitBuildStrategyEntry) object42_1523).a, true)) continue;
                        bl2 = true;
                        break;
                    }
                    if (!bl2) continue;
                    object8 = new CombatMain(this, ((UnitInstance) object62_1519).eo, ((UnitInstance) object62_1519).ep);
                    ((CombatMain) object8).U = 420.0f;
                    ((CombatMain) object8).b = com.corrodinggames.rts.game.ai.BaseZoneType.c;
                    ((CombatMain) object8).c = com.corrodinggames.rts.game.ai.BaseZoneStage.a;
                    ++n3;
                    break;
                }
            }
            if (n3 < 1) {
                for (Object object62_1538 : com.corrodinggames.rts.game.units.UnitInstance.bE) {
                    if (((UnitInstance) object62_1538).player != this || !(object62_1538 instanceof UnitType) || !((UnitType)(object5 = (UnitType)object62_1538)).ai()) continue;
                    CombatMain i2 = new CombatMain(this, ((UnitInstance) object62_1538).eo, ((UnitInstance) object62_1538).ep);
                    i2.U = 420.0f;
                    i2.b = com.corrodinggames.rts.game.ai.BaseZoneType.c;
                    i2.c = com.corrodinggames.rts.game.ai.BaseZoneStage.a;
                    ++n3;
                    break;
                }
            }
            if (!this.mapChecked) {
                this.mapChecked = true;
                int n4 = this.a(this.extractorUnits, IncludeExcludeMode.a);
                if (n4 >= 1) {
                    for (int i3 = 0; i3 < l2.bL.A.size(); ++i3) {
                        object5 = (Point)l2.bL.A.get(i3);
                        l2.bL.a(((Point)object5).a, ((Point)object5).b);
                        this.centerPosition.a(l2.bL.scrollPixelX, l2.bL.scrollPixelY);
                        PointF pointF = this.centerPosition;
                        pointF.b += (float)l2.bL.tilePixelHeight;
                        if (this.b(pointF.a, pointF.b) != null || this.a(this.extractorUnits, pointF.a, pointF.b, 200) < 1 || !this.b(pointF)) continue;
                        object8 = new CombatMain(this, pointF.a, pointF.b);
                        ((CombatMain) object8).U = 360.0f;
                        ((CombatMain) object8).b = com.corrodinggames.rts.game.ai.BaseZoneType.a;
                        ((CombatMain) object8).c = com.corrodinggames.rts.game.ai.BaseZoneStage.b;
                    }
                }
            }
        }
        UnitType y2 = null;
        CombatMain i4 = null;
        object5 = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n5 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (n2 = 0; n2 < n5; ++n2) {
            object42 = ((UnitInstance[]) object5)[n2];
            if (((UnitInstance) object42).player != this || ((UnitInstance) object42).cN != null || !(object42 instanceof UnitType) || !((UnitInstance) object42).aj() || !this.i((UnitInstance) object42)) continue;
            bq2 = (UnitType)object42;
            object3 = this.e((UnitInstance) bq2);
            if (object3 != null) {
                if (!((UnitType)bq2).aq()) continue;
                y2 = bq2;
                i4 = (CombatMain) object3;
                continue;
            }
            if (!((UnitType)bq2).aq() || (object2 = this.f((UnitInstance) bq2)) == null) continue;
            object = ((AIStrategyNode) object2).w();
            com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.gete4(this);
            e2.a((UnitType)bq2);
            e2.a(((PointF)object).a, ((PointF)object).b);
        }
        n5 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (n2 = 0; n2 < n5; ++n2) {
            object42 = ((UnitInstance[]) object5)[n2];
            if (((UnitInstance) object42).player != this || !(object42 instanceof UnitType)) continue;
            bq2 = (UnitType)object42;
            if (((UnitType)bq2).V > 2400.0f && this.i((UnitInstance) bq2)) {
                if (((UnitType)bq2).aN && ((UnitType)bq2).V < 24000.0f) continue;
                object3 = l2.cf.gete4(this);
                ((com.corrodinggames.rts.gameFramework.Command)object3).a((UnitType)bq2);
                ((com.corrodinggames.rts.gameFramework.Command)object3).h();
            }
            if (!((UnitType)bq2).aj() || !this.i((UnitInstance) bq2) || (object3 = ((UnitType)bq2).ar()) == null || ((WeaponAction) object3).d() != com.corrodinggames.rts.game.units.WeaponTypeEnum.c || !(((UnitType)bq2).V > 700.0f)) continue;
            object2 = l2.cf.gete4(this);
            ((com.corrodinggames.rts.gameFramework.Command)object2).a((UnitType)bq2);
            ((com.corrodinggames.rts.gameFramework.Command)object2).h();
        }
        if (!this.U) {
            this.ak();
            n2 = 1;
            n5 = this.isTurtleMode() ? 1 : 0;
            boolean bl3 = true;
            if (n5 != 0) {
                ++n2;
                bl3 = false;
            }
            if (this.waterUnitCount > 6) {
                n2 = 2;
            }
            if (this.waterUnitCount > 11) {
                n2 = 3;
            }
            if (this.factoryCount < n2) {
                CombatAction ca2 = new CombatAction(this, false);
                ((CombatAction) ca2).field_A = 8;
                if (this.shouldRushExpansion()) {
                    ((CombatAction) ca2).field_A = 10;
                }
                ((CombatAction) ca2).k();
                ++this.enemyUnitCount;
            }
            if ((this.builderCount >= n2 || this.combatUnitCount > 6) && this.buildingCount < 1 && bl3) {
                CombatAction ca2 = new CombatAction(this, true);
                if (this.totalUnitCount < 2) {
                    ((CombatAction) ca2).field_A = 3;
                } else if (this.totalUnitCount < 5) {
                    ((CombatAction) ca2).field_A = 5;
                } else {
                    ((CombatAction) ca2).field_A = 7;
                    if (this.shouldRushExpansion()) {
                        ((CombatAction) ca2).field_A = this.totalUnitCount < 25 ? 14 : 18;
                    }
                }
                ((CombatAction) ca2).k();
                ++this.totalUnitCount;
            }
            if (this.isUnitLimitReached() && this.extractorCount < 1 && bl3) {
                CombatAction ca2 = new CombatAction(this, true);
                ((CombatAction) ca2).field_B = true;
                ((CombatAction) ca2).field_A = 5;
                if (this.shouldRushExpansion()) {
                    ((CombatAction) ca2).field_A = 10;
                }
                ((CombatAction) ca2).k();
            }
            if (this.useTransportsOnThisMap() && this.harvesterCount < 3) {
                com.corrodinggames.rts.game.ai.TransporterGroup tg2 = new com.corrodinggames.rts.game.ai.TransporterGroup(this);
                ((com.corrodinggames.rts.game.ai.TransporterGroup)tg2).l = 1;
                ((com.corrodinggames.rts.game.ai.TransporterGroup)tg2).f();  // v19.115v: 02b n.f() 铁证 (pickDropLocation 语义名废除)
            }
        }
        if (this.U) {
            if (this.repairTimer > 30.0f) {
                this.repairTimer = 30.0f;
            }
            if (this.repairTimer == 0.0f) {
                ++this.recycleTimer;
                if (this.recycleTimer == 1) {
                    this.repairTimer = 1000.0f;
                } else if (this.recycleTimer == 2) {
                    this.repairTimer = 3000.0f;
                    UnitInstance am2 = this.as();
                    if (am2 != null) {
                        if (this.U) {
                            n5 = 0;
                        } else {
                            n5 = 2;
                            if (this.attackingCount < 4) {
                                n5 = 5;
                            }
                        }
                        object42 = l2.cf.gete4(this);
                        int n6 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
                        for (int i5 = 0; i5 < n6; ++i5) {
                            object2 = ((UnitInstance[]) object5)[i5];
                            if (((UnitInstance) object2).player != this || !(object2 instanceof UnitType)) continue;
                            object = (UnitType)object2;
                            if (((UnitType)object).bM || !this.b((UnitInstance) object, am2)) continue;
                            if (n5 <= 0) {
                                ((com.corrodinggames.rts.gameFramework.Command)object42).a((UnitType)object);
                                continue;
                            }
                            --n5;
                        }
                        ((com.corrodinggames.rts.gameFramework.e)object42).b(am2.eo, am2.ep);
                    }
                } else {
                    this.recycleTimer = 0;
                }
            }
        }
    }

    public boolean i(UnitInstance am2) {
        if (am2.u() || am2.t()) {
            return false;
        }
        if (am2.isAutoReclaimActive()) {
            return false;
        }
        return !am2.bN;
    }

    public UnitInstance ar() {
        int n2;
        UnitInstance am2 = null;
        int n3 = 0;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n4 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (n2 = 0; n2 < n4; ++n2) {
            UnitInstance am3 = amArray[n2];
            if (am3.isDead || am3.cN != null || this != am3.player || !this.h(am3)) continue;
            ++n3;
        }
        n2 = (int)(Math.random() * (double)n3);
        n4 = 0;
        for (UnitInstance am4 : (java.util.Collection<UnitInstance>) (java.util.Collection) com.corrodinggames.rts.game.units.UnitInstance.bE) {
            if (am4.isDead || am4.cN != null || this != am4.player || !this.h(am4)) continue;
            if (n4 == n2) {
                am2 = am4;
                break;
            }
            ++n4;
        }
        return am2;
    }

    public UnitInstance as() {
        int n2;
        UnitInstance am2 = null;
        int n3 = 0;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n4 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (n2 = 0; n2 < n4; ++n2) {
            UnitInstance am3 = amArray[n2];
            if (am3.isDead || am3.cN != null || am3.u() || !this.c(am3.player) || !this.j(am3)) continue;
            ++n3;
        }
        n2 = (int)(Math.random() * (double)n3);
        n4 = 0;
        for (UnitInstance am4 : (java.util.Collection<UnitInstance>) (java.util.Collection) com.corrodinggames.rts.game.units.UnitInstance.bE) {
            if (am4.isDead || am4.cN != null || am4.u() || !this.c(am4.player) || !this.j(am4)) continue;
            if (n4 == n2) {
                am2 = am4;
                break;
            }
            ++n4;
        }
        return am2;
    }

    public PointF at() {
        int n2;
        UnitInstance am2 = null;
        int n3 = 0;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n4 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (n2 = 0; n2 < n4; ++n2) {
            UnitInstance am3 = amArray[n2];
            if (am3.isDead || am3.cN != null || am3.u() || !this.c(am3.player) || !this.j(am3)) continue;
            ++n3;
        }
        n2 = (int)(Math.random() * (double)n3);
        n4 = 0;
        for (UnitInstance am4 : (java.util.Collection<UnitInstance>) (java.util.Collection) com.corrodinggames.rts.game.units.UnitInstance.bE) {
            if (am4.isDead || am4.cN != null || am4.u() || !this.c(am4.player) || !this.j(am4)) continue;
            if (n4 == n2) {
                am2 = am4;
                break;
            }
            ++n4;
        }
        if (am2 != null) {
            return new PointF(am2.eo, am2.ep);
        }
        return null;
    }

    public static UnitInstance a(com.corrodinggames.rts.game.PlayerState n2, float f2, float f3, float f4) {
        float f5 = f4;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n3 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (!(am2.eo + f5 > f2) || !(am2.eo - f5 < f2) || !(am2.ep + f5 > f3) || !(am2.ep - f5 < f3) || am2.player == n2 || !AIStrategy.a(am2, f2, f3, f4) || !am2.player.c(n2)) continue;
            return am2;
        }
        return null;
    }

    public static int a(com.corrodinggames.rts.game.PlayerState n2, float f2, float f3, float f4, boolean bl2) {
        int n3 = 0;
        float f5 = f4;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n4 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (!(am2.eo + f5 > f2) || !(am2.eo - f5 < f2) || !(am2.ep + f5 > f3) || !(am2.ep - f5 < f3) || am2.player == n2 || !AIStrategy.a(am2, f2, f3, f4) || !am2.player.d(n2) || bl2 && !am2.isFactoryBuilding()) continue;
            ++n3;
        }
        return n3;
    }

    public static int b(com.corrodinggames.rts.game.PlayerState n2, float f2, float f3, float f4) {
        int n3 = 0;
        float f5 = f4;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n4 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (!(am2.eo + f5 > f2) || !(am2.eo - f5 < f2) || !(am2.ep + f5 > f3) || !(am2.ep - f5 < f3) || am2.player == n2 || !AIStrategy.a(am2, f2, f3, f4) || !am2.player.c(n2)) continue;
            ++n3;
        }
        return n3;
    }

    public int a(UnitBuildStrategy d2, float f2, float f3, int n2) {
        int n3 = 0;
        for (UnitBuildStrategyEntry e2 : (java.util.Collection<UnitBuildStrategyEntry>) (java.util.Collection) d2.c) {
            n3 += this.a(e2.a, f2, f3, n2);
        }
        return n3;
    }

    public int a(UnitTypeHandle as2, float f2, float f3, int n2) {
        int n3 = 0;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        nearbyUnitsCache.clear();
        l2.cc.a(this, f2, f3, (float)n2, nearbyUnitsCache);
        UnitInstance[] amArray = nearbyUnitsCache.a();
        int n4 = nearbyUnitsCache.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (am2.player != this || am2.dz != as2 || !AIStrategy.a(am2, f2, f3, (float)n2)) continue;
            ++n3;
        }
        return n3;
    }

    public int au() {
        int n2 = 0;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n3 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            UnitInstance am2 = amArray[i2];
            ++n2;
        }
        return n2;
    }


    public void T() {
        if (this.aiSleeping && this.au() != 0) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("waking up AI");
            this.aiSleeping = false;
        }
    }


    public void d(UnitInstance am2) {
        if (!(am2 instanceof UnitType)) {
            return;
        }
        UnitType y2 = (UnitType)am2;
        y2.bD = false;
        if (y2.linkedBaseZone != null) {
            y2.linkedBaseZone.a(y2);
            y2.linkedBaseZone = null;
        }
        if (y2.aB != null) {
            y2.aB.b(y2);
            y2.aB = null;
        }
        this.e(y2);
    }

    public void a(UnitType y2, com.corrodinggames.rts.game.units.custom.resources.CustomActionBase b2, boolean bl2) {
        if (y2.linkedBaseZone != null) {
            y2.linkedBaseZone.a(y2, b2, bl2);
        }
    }

    public boolean j(UnitInstance am2) {
        return am2.isTargetableForAI() || !this.c(am2.player);
    }

    public boolean a(com.corrodinggames.rts.game.units.custom.resources.CustomActionBase b2, UnitInstance am2) {
        return this.a(b2, am2, false);
    }

    public boolean a(com.corrodinggames.rts.game.units.custom.resources.CustomActionBase b2, UnitInstance am2, boolean bl2) {
        return b2.b(am2);
    }

    public void a(com.corrodinggames.rts.game.ai.strategies.AIStrategyResult a2) {
        if (!this.aiBehaviors.contains(a2)) {
            this.aiBehaviors.add(a2);
        } else {
            this.d("Skipping add of component: " + a2.a().name());
        }
    }

    static /* synthetic */ boolean a(AIStrategy a2, UnitTypeHandle as2) {
        return a2.a(as2);
    }

    static {
        zoneTypeList = new ArrayList();
        nearbyUnitsCache = new UnitInstanceList();
    }
}
