/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.R;
import com.corrodinggames.rts.game.LobbyPlayer;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.units.actions.ActionFilter;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.game.units.commands.CustomGroundUnit;
import com.corrodinggames.rts.game.units.projectiles.AbstractSubBuilding;
import com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit;
import com.corrodinggames.rts.game.units.projectiles.SpecialBuilding;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.gameFramework.ReplayRecorder;
import com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit;
import com.corrodinggames.rts.game.units.projectiles.WallBuilding;

import android.graphics.Paint;
import android.graphics.Paint$Cap;
import android.graphics.PointF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.actions.BuildQueueAction;
import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.TreeDecoration;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.ModLoader;
import com.corrodinggames.rts.game.units.MovementPath;
import com.corrodinggames.rts.game.units.commands.UnitActionHelper;
import com.corrodinggames.rts.game.units.projectiles.FactoryBuilding;
import com.corrodinggames.rts.game.units.ActionAddCredits;
import com.corrodinggames.rts.game.units.LandUnit;
import com.corrodinggames.rts.game.units.AirUnit;
import com.corrodinggames.rts.game.units.HoverUnit;
import com.corrodinggames.rts.game.units.SubmarineUnit;
import com.corrodinggames.rts.game.units.UnitCategory;
import com.corrodinggames.rts.game.units.UnitBehaviorEnum;
import com.corrodinggames.rts.game.units.UnitQueryFilter;
import com.corrodinggames.rts.game.units.UnitActionEnum;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.io.IOException;

public strictfp class Factory
extends com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase
implements MovementPath {
    PointF[] a = new PointF[6];
    PointF[] b = new PointF[this.a.length];
    boolean c;
    static Paint d;
    static Paint e;
    static Paint f;
    static Texture g;
    static GameAction actionReloadUnits;
    static GameAction actionReloadActive;
    static GameAction actionCloneUnit;
    static GameAction k;
    static GameAction l;
    static GameAction m;
    static GameAction actionNukeAt;
    static GameAction actionFreezeAI;
    static GameAction actionChangeAlliance;
    static GameAction rallyPoint;
    String r;
    static GameAction idleTimer;
    static GameAction totalProduced;
    static GameAction u;
    static GameAction v;
    static GameAction w;
    static GameAction actionFastForward;
    static GameAction y;
    static GameAction z;
    static GameAction actionEnableAIDebug;
    static GameAction actionEnableTriggerDebug;
    static GameAction actionClearSaveHistory;
    static ArrayList D;
    com.corrodinggames.rts.gameFramework.mods.ModInfo E;
    UnitBehaviorEnum F;
    UnitCategory G;
    String H;
    boolean I;
    String J;
    static ActionFilter buildFilter;

    public UnitRegistry startBuild() {
        return UnitRegistry.Y;
    }

    public strictfp float A() {
        return 9.8F;
    }

    public strictfp float getMaxMoveDistance() {
        return 30.0f;
    }

    public strictfp float m() {  // 02b h.java L282
        return 30.0f;
    }

    public strictfp boolean l() {  // 02b h.java L306
        return false;
    }

    public Texture k() {
        return null;
    }

    public strictfp boolean I() {
        return false;
    }

    public boolean f(float f2) {
        return false;
    }

    public void e(float f2) {
        // 02b am.e(float) 渲染绘制复杂逻辑, 空实现 (PENDING 语义)
    }

    public strictfp boolean isEnabled2() {
        return false;
    }

    public static boolean w() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.cb.i();
    }

    @Override
    public PointF[] e_() {
        return this.b;
    }

    public PointF[] b() {
        return this.a;
    }


    public PointF[] getPointF() {
        return this.b;
    }


    public Texture gete() {
        if (this.player.k == -1) {
            return null;
        }
        return dN[this.player.getTeamIndex()];
    }

    public static void processUpgradeComplete() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        g = l2.bO.a(R$drawable.icon_search);
    }


    public boolean a(UnitInstance am2) {
        return true;
    }


    public Texture d() {
        if (this.isDead) {
            return com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.defaultBuildUnit;
        }
        return com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.costResources[this.player.getTeamIndex()];
    }


    public Texture isBuildable() {
        return null;
    }


    public Texture d(int n2) {
        return null;
    }


    public boolean processBuildQueue() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.defaultBuildUnit;
        this.S(0);
        this.bT = false;
        l2.bM.a(SoundRegistry.o, 0.8f, this.eo, this.ep);
        this.bq();
        return true;
    }

    public Factory(boolean bl) {
        super(bl);
        d = new Paint();
        d.a(40, 0, 255, 0);
        d.a(true);
        d.a(2.0f);
        d.a(Paint$Cap.b);
        e = new Paint();
        e.a(d);
        e.a(55, 255, 60, 60);
        f = new Paint();
        f.a(60, 255, 255, 255);
        this.E = null;
        this.F = UnitBehaviorEnum.a;
        this.G = UnitCategory.a;
        this.I = true;
        this.T(20);
        this.U(20);
        this.cj = 10.0f;
        this.eo = -1000.0f;
        this.ep = -1000.0f;
        this.ck = this.cj;
        this.hp = this.maxHp = 170000.0f;
        this.M = com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.defaultBuildUnit;
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            this.a[i2] = new PointF();
            this.b[i2] = new PointF();
        }
    }

    public static void a(float f2, MovementPath d2) {
        block4: {
            PointF[] pointFArray;
            PointF[] pointFArray2;
            block3: {
                UnitType y2 = (UnitType) ((Object)d2);
                pointFArray2 = d2.b();
                pointFArray = d2.e_();
                UnitInstance am2 = y2.X();
                boolean bl = y2.aN = am2 != null;
                if (am2 == null) break block3;
                for (int i2 = 0; i2 < pointFArray2.length; ++i2) {
                    PointF pointF = pointFArray2[i2];
                    PointF pointF2 = pointFArray[i2];
                    pointF.a = GameUtils.a(pointF.a, pointF2.a, 0.1f * f2);
                    pointF.b = GameUtils.a(pointF.b, pointF2.b, 0.1f * f2);
                    pointF.a += (pointF2.a - pointF.a) * 0.04f * f2;
                    pointF.b += (pointF2.b - pointF.b) * 0.04f * f2;
                    float f3 = am2.cj * 0.75f;
                    if (GameUtils.c(pointF.a - pointF2.a) < 1.0f) {
                        pointF2.a = com.corrodinggames.rts.gameFramework.GameUtils.d(-f3, f3);
                    }
                    if (!(GameUtils.c(pointF.b - pointF2.b) < 1.0f)) continue;
                    pointF2.b = com.corrodinggames.rts.gameFramework.GameUtils.d(-f3, f3);
                }
                break block4;
            }
            if (pointFArray2[0].a == 0.0f && pointFArray2[0].b == 0.0f) break block4;
            for (int i3 = 0; i3 < pointFArray2.length; ++i3) {
                PointF pointF = pointFArray2[i3];
                PointF pointF3 = pointFArray[i3];
                pointF.a = 0.0f;
                pointF.b = 0.0f;
                pointF3.a = 0.0f;
                pointF3.b = 0.0f;
            }
        }
    }


    public void a(float f2) {
        if (f2 < 0.3f) {
            f2 = 0.3f;
        }
        if (this.ax && this.player.b()) {
            for (int i2 = 0; i2 < PlayerState.c; ++i2) {
                PlayerState n2 = PlayerState.u(i2);
                if (n2 == null || n2.b()) continue;
                this.processBuildQueue(n2);
                break;
            }
        }
        super.a(f2);
        if (!this.isDead) {
            Factory.a(f2, this);
        }
        this.hp = this.maxHp;
    }


    public void a(float f2, boolean bl) {
        if (!this.isDead) {
            // empty if block
        }
    }


    public float processBuildQueue(PlayerState n2) {
        return 0.0f;
    }

    public float processBuildQueue(int n2) {
        return 0.0f;
    }


    public float startBuild(int n2) {
        return 0.0f;
    }


    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return true;
    }


    public void a(UnitInstance am2, int n2) {
    }


    public boolean get_b_() {
        return false;
    }


    public int y() {
        return 850000;
    }


    public float b(UnitInstance am2) {
        return 1.0E7f;
    }


    public float c(UnitInstance am2) {
        return 1.0E7f;
    }


    public float clearBuildQueue() {
        return 30.0f;
    }


    public float b(int n2) {
        return 100.0f;
    }


    public float z() {
        return 0.0f;
    }


    public float getResourceProductionRate() {
        return 9.8f;
    }


    public float getBuildPowerOutput() {
        return 9.35f;
    }


    public float c(int n2) {
        return 99.0f;
    }


    public boolean getQueueLength() {
        return false;
    }


    public float getRepairRate() {
        return 0.04f;
    }


    public float getReclaimRate() {
        return 0.1f;
    }


    public void a(GameAction s2, boolean bl) {
        Object object;
        boolean bl2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (s2 instanceof AirUnit) {
            PlayerState n2;
            int n3;
            AirUnit x2 = (AirUnit) s2;
            bl2 = true;
            if (bl) {
                boolean bl3 = bl2 = !bl2;
            }
            if (x2.a) {
                boolean bl4 = bl2 = !bl2;
            }
            if (bl2) {
                object = null;
                for (n3 = this.player.k + 1; n3 < PlayerState.c; ++n3) {
                    n2 = PlayerState.u(n3);
                    if (n2 == null || n2.b()) continue;
                    object = n2;
                    break;
                }
                if (object == null && this.player.k < 4 && (object = PlayerState.u(this.player.k + 1)) == null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Sandbox adding new team:" + this.player.k);
                    LobbyPlayer a2 = new LobbyPlayer(this.player.k + 1);
                    object = a2;
                    ((PlayerState)object).r = 1;
                    ((PlayerState)object).F = true;
                    ((PlayerState)object).G = true;
                    a2.bG = !this.c ? 0.0f : Float.MAX_VALUE;
                }
                if (object == null) {
                    for (n3 = 0; n3 < PlayerState.c; ++n3) {
                        n2 = PlayerState.u(n3);
                        if (n2 == null || n2.b()) continue;
                        object = n2;
                        break;
                    }
                }
                if (object != null) {
                    this.processBuildQueue((PlayerState)object);
                    if (!l2.cb.j()) {
                        l2.bs = (PlayerState) object;
                    }
                }
            } else {
                object = null;
                for (n3 = this.player.k - 1; n3 >= 0; --n3) {
                    n2 = PlayerState.u(n3);
                    if (n2 == null || n2.b()) continue;
                    object = n2;
                    break;
                }
                if (object == null) {
                    for (n3 = PlayerState.c - 1; n3 >= 0; --n3) {
                        n2 = PlayerState.u(n3);
                        if (n2 == null || n2.b()) continue;
                        object = n2;
                        break;
                    }
                }
                if (object != null) {
                    this.processBuildQueue((PlayerState)object);
                    if (!l2.cb.j()) {
                        l2.bs = (PlayerState) object;
                    }
                }
            }
        }
        if (s2 instanceof LandUnit) {
            LandUnit x2 = (LandUnit) s2;
            bl2 = true;
            if (bl) {
                boolean bl5 = bl2 = !bl2;
            }
            if (x2.a) {
                boolean bl6 = bl2 = !bl2;
            }
            if (((ArrayList)(object = l2.bZ.j())).size() == 0) {
                this.E = null;
            } else if (bl2) {
                if (this.E == null) {
                    this.E = (com.corrodinggames.rts.gameFramework.mods.ModInfo)((ArrayList)object).get(0);
                } else {
                    com.corrodinggames.rts.gameFramework.mods.ModInfo b2 = null;
                    boolean bl7 = false;
                    Iterator iterator = ((ArrayList)object).iterator();
                    while (iterator.hasNext()) {
                        com.corrodinggames.rts.gameFramework.mods.ModInfo b3 = (com.corrodinggames.rts.gameFramework.mods.ModInfo)iterator.next();
                        if (bl7) {
                            b2 = b3;
                            break;
                        }
                        if (b3 != this.E) continue;
                        bl7 = true;
                    }
                    this.E = b2;
                }
            } else if (this.E == null) {
                this.E = (com.corrodinggames.rts.gameFramework.mods.ModInfo)((ArrayList)object).get(((ArrayList)object).size() - 1);
            } else {
                com.corrodinggames.rts.gameFramework.mods.ModInfo b4 = null;
                boolean bl8 = false;
                ArrayList arrayList = new ArrayList();
                arrayList.addAll((ArrayList) object);
                Collections.reverse(arrayList);
                for (Object objectB5 : arrayList) {
                    com.corrodinggames.rts.gameFramework.mods.ModInfo b5 = (com.corrodinggames.rts.gameFramework.mods.ModInfo) objectB5;
                    if (bl8) {
                        b4 = b5;
                        break;
                    }
                    if (b5 != this.E) continue;
                    bl8 = true;
                }
                this.E = b4;
            }
        }
        if (s2 instanceof HoverUnit) {
            HoverUnit x2 = (HoverUnit) s2;
            bl2 = true;
            if (bl) {
                boolean bl9 = bl2 = !bl2;
            }
            if (x2.a) {
                bl2 = !bl2;
            }
            this.F = this.F.a(!bl2);
        }
        if (s2 instanceof ActionAddCredits) {
            this.player.d(10000.0f);
        }
        if (s2 instanceof SubmarineUnit) {
            ((SubmarineUnit) s2).n();
        }
    }

    static Factory L() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.bS.getDebugFactory();
    }


    public void a(GameAction s2, boolean bl, PointF pointF, UnitInstance am2) {
        Object object3;
        PlayerState n2;
        Iterator object2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (s2 instanceof com.corrodinggames.rts.game.units.actions.ActionWrapper) {
            s2 = ((com.corrodinggames.rts.game.units.actions.ActionWrapper)s2).q_();
        }
        if (s2 == actionReloadUnits) {
            if (Factory.w()) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("Not reloading units: Need to keep network sync");
                l2.bS.b("Not reloading units: Need to keep network sync");
                return;
            }
            if (bl) {
                return;
            }
            if (l2.bZ.h() == 0) {
                l2.bS.b("No custom units to reload");
                return;
            }
            com.corrodinggames.rts.gameFramework.GlobalState.e("Reload units requested");
            l2.bZ.a(true, false);
            object2 = PlayerState.c().iterator();
            while (object2.hasNext()) {
                n2 = (PlayerState)object2.next();
                if (!(n2 instanceof LobbyPlayer)) continue;
                ((LobbyPlayer) n2).al();
            }
            l2.bS.b("All custom unit files reloaded");
        }
        if (s2 == actionReloadActive) {
            if (Factory.w()) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("Not reloading units: Need to keep network sync");
                return;
            }
            if (bl) {
                return;
            }
            if (l2.bZ.h() == 0) {
                l2.bS.b("No custom units to reload");
                return;
            }
            com.corrodinggames.rts.gameFramework.GlobalState.e("Reload active only requested");
            l2.bZ.a(true, true);
            object2 = PlayerState.c().iterator();
            while (object2.hasNext()) {
                n2 = (PlayerState)object2.next();
                if (!(n2 instanceof LobbyPlayer)) continue;
                ((LobbyPlayer) n2).al();
            }
            int n3 = com.corrodinggames.rts.game.units.custom.ag.d;
            int n4 = 100;
            object3 = "Quick reloaded changed data for " + n3 + " units active on map.";
            if (l2.bQ.liveReloading && n3 == 0) {
                object3 = (String)object3 + " (Note: Live reloading is currently enabled, so changed units may have already be reloaded)";
                n4 = 170;
            }
            l2.bS.a((String)object3, n4);
        }
        if (s2 == k || s2 == l || s2 == actionCloneUnit) {
            int n5 = 0;
            if (bl) {
                return;
            }
            for (Object object3_576 : UnitInstance.getUnitPool()) {
                if (!(object3_576 instanceof UnitInstance)) continue;
                UnitInstance am3 = (UnitInstance) object3_576;
                if (!(GameUtils.a(((UnitInstance) object3_576).eo, ((UnitInstance) object3_576).ep, pointF.a, pointF.b) < 2500.0f)) continue;
                if (s2 == k) {
                    if (am3.cN != null || am3.cO != null) continue;
                    am3.canBuild();
                    if (!(am3 instanceof UnitType) || !am3.isFactoryBuilding()) continue;
                    l2.bU.a((UnitType) am3);
                    continue;
                }
                if (s2 == l) {
                    if (am3.cN != null || am3.cO != null) continue;
                    am3.cu = -1.0f;
                    continue;
                }
                if (s2 != actionCloneUnit) continue;
                if (n5 > 4) break;
                if (am3.isFactoryBuilding() || am3.isDead || am3.cN != null || am3.cO != null) continue;
                ++n5;
                UnitTypeHandle as2 = am3.r();
                for (int i2 = -25; i2 < 25; ++i2) {
                    UnitInstance am4 = as2.a();
                    am4.eo = am3.eo + (float)i2 * 0.5f + 1.0f;
                    am4.ep = am3.ep + (float)i2 * 0.5f + 1.0f;
                    am4.b(am3.player);
                    PlayerState.c(am4);
                    am4.cg = (float) GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) am3, -180, 180, 25 + i2);
                    if (!(am4 instanceof UnitType)) continue;
                    ((UnitType) am4).ay = true;
                }
            }
            return;
        }
        if (s2 == actionNukeAt) {
            if (bl) {
                return;
            }
            MovementController object4 = com.corrodinggames.rts.game.units.commands.UnitActionHelper.a((UnitInstance) this, pointF.a, pointF.b, pointF.a, pointF.b);
            if (object4 != null) {
                object4.eq = 100.0f;
                object4.j = null;
            }
        }
        if (s2 == m) {
            if (bl) {
                return;
            }
            Iterator iterator2 = UnitInstance.getUnitPool().iterator();
            while (iterator2.hasNext()) {
                com.corrodinggames.rts.gameFramework.GameObject w2 = (com.corrodinggames.rts.gameFramework.GameObject) iterator2.next();
                if (!(w2 instanceof UnitType) || !(w2 instanceof com.corrodinggames.rts.game.units.commands.CarrierUnit) || !(GameUtils.a(w2.eo, w2.ep, pointF.a, pointF.b) < 2500.0f)) continue;
                com.corrodinggames.rts.game.units.commands.CarrierUnit carrierUnit = (com.corrodinggames.rts.game.units.commands.CarrierUnit) w2;
                carrierUnit.dz();
            }
            return;
        }
        if (s2 == actionFreezeAI) {
            PlayerState n7 = this.player;
            if (n7 instanceof LobbyPlayer) {
                LobbyPlayer a2 = (LobbyPlayer) n7;
                a2.bG = a2.bG > 0.0f ? 0.0f : 10800.0f;
            }
        }
        if (s2 == actionChangeAlliance) {
            ++this.player.r;
            if (this.player.r > 4) {
                this.player.r = 0;
            }
        }
        if (s2 == u) {
            boolean bl2;
            boolean bl3 = false;
            boolean bl4 = false;
            java.util.Iterator iterator3 = PlayerState.c().iterator();
            while (iterator3.hasNext()) {
                PlayerState n6 = (PlayerState) iterator3.next();
                if (!(n6 instanceof LobbyPlayer)) continue;
                LobbyPlayer a3 = (LobbyPlayer) n6;
                if (a3.bG > 0.0f) {
                    bl3 = true;
                }
                bl4 = true;
            }
            boolean bl5 = bl2 = !bl3;
            if (!bl4) {
                bl2 = !this.c;
            }
            this.c = bl2;
            this.M();
        }
        if (s2 == v) {
            // empty if block
        }
        if (s2 == w) {
            // empty if block
        }
        if (s2 == actionFastForward) {
            // empty if block
        }
        if (s2 == z) {
            boolean bl6 = l2.bl = !l2.bl;
        }
        if (s2 == actionEnableAIDebug) {
            boolean bl7 = com.corrodinggames.rts.game.ai.AIStrategy.globalAIEnabled = !com.corrodinggames.rts.game.ai.AIStrategy.globalAIEnabled;
        }
        if (s2 == actionEnableTriggerDebug) {
            boolean bl8 = l2.bn = !l2.bn;
        }
        if (s2 == actionClearSaveHistory) {
            l2.bY.a();
        }
        if (s2 instanceof SpecialActionType) {
            SpecialActionType q2 = (SpecialActionType)s2;
            UnitQueryFilter.a(q2.a, pointF);
        }
        super.a(s2, bl, pointF.a, pointF.b);
    }

    public void M() {
        java.util.Iterator iterator4 = PlayerState.c().iterator();
        while (iterator4.hasNext()) {
            PlayerState n2 = (PlayerState) iterator4.next();
            if (!(n2 instanceof LobbyPlayer)) continue;
            LobbyPlayer a2 = (LobbyPlayer) n2;
            if (!this.c) {
                a2.bG = 0.0f;
                continue;
            }
            a2.bG = Float.MAX_VALUE;
        }
    }

    public static boolean a(GameAction s2, UnitInstance am2) {
        if (s2 instanceof com.corrodinggames.rts.game.units.actions.ActionWrapper) {
            s2 = ((com.corrodinggames.rts.game.units.actions.ActionWrapper)s2).q_();
        }
        if (s2 == actionFreezeAI) {
            return true;
        }
        if (s2 == w) {
            return true;
        }
        if (s2 == actionFastForward) {
            return true;
        }
        if (s2 == m) {
            return true;
        }
        if (s2 == k) {
            return true;
        }
        if (s2 == actionCloneUnit) {
            return true;
        }
        if (s2 == z) {
            return true;
        }
        if (s2 == actionChangeAlliance) {
            return true;
        }
        if (s2 == rallyPoint) {
            return true;
        }
        if (s2 == idleTimer) {
            return true;
        }
        if (s2 == totalProduced) {
            return true;
        }
        if (s2 == actionEnableTriggerDebug) {
            return true;
        }
        return s2 == actionClearSaveHistory;
    }

    public static void a(ArrayList arrayList, int n2) {
        Object object;
        if (n2 != 1) {
            return;
        }
        D = new ArrayList();
        D.add(new AirUnit(true, false));
        D.add(new AirUnit(true, true));
        D.add(new AirUnit(false, false));
        D.add(new SubmarineUnit(true, false));
        SubmarineUnit m2 = new SubmarineUnit(true, true);
        D.add(m2);
        D.add(new SubmarineUnit(false, false));
        D.add(new LandUnit(true, false));
        D.add(new LandUnit(true, true));
        D.add(new LandUnit(false, false));
        D.add(new HoverUnit(true, false));
        D.add(new HoverUnit(true, true));
        D.add(new HoverUnit(false, false));
        D.add(new SpecialActionType(UnitActionEnum.a));
        D.add(new SpecialActionType(UnitActionEnum.b));
        D.add(new SpecialActionType(UnitActionEnum.c));
        D.add(new SpecialActionType(UnitActionEnum.d));
        ArrayList<GameAction> arrayList2 = new ArrayList<GameAction>();
        arrayList2.add(new ActionAddCredits());
        arrayList2.add(y);
        arrayList2.add(actionReloadUnits);
        arrayList2.add(actionReloadActive);
        arrayList2.add(k);
        arrayList2.add(actionCloneUnit);
        arrayList2.add(l);
        arrayList2.add(actionNukeAt);
        arrayList2.add(m);
        arrayList2.add(u);
        arrayList2.add(v);
        arrayList2.add(w);
        arrayList2.add(actionFastForward);
        arrayList2.add(z);
        arrayList2.add(actionChangeAlliance);
        arrayList2.add(rallyPoint);
        arrayList2.add(idleTimer);
        arrayList2.add(totalProduced);
        if (com.corrodinggames.rts.gameFramework.GlobalState.at) {
            arrayList2.add(actionEnableAIDebug);
        }
        arrayList2.add(actionEnableTriggerDebug);
        arrayList2.add(actionClearSaveHistory);
        for (GameAction object22 : arrayList2) {
            boolean as2 = true;
            object = new com.corrodinggames.rts.game.units.actions.ActionWrapper(object22, buildFilter, as2);
            D.add(object);
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(UnitRegistry.ae);
        Collections.sort(arrayList3, new Factory$15());
        Iterator iterator = arrayList3.iterator();
        while (iterator.hasNext()) {
            Object object2;
            UnitTypeHandle as2 = (UnitTypeHandle) iterator.next();
            if (as2 == UnitRegistry.I || as2.i().equals("test_tank") || as2.i().equals("missing") || as2 == UnitRegistry.v || as2 == UnitRegistry.q || as2 == UnitRegistry.R || as2 == UnitRegistry.H || as2 == UnitRegistry.W || as2 == UnitRegistry.X || as2 == UnitRegistry.Y || as2 == UnitRegistry.Z || as2 == UnitRegistry.N || !((object = UnitInstance.isRenderable(as2)) instanceof UnitType)) continue;
            if (as2 instanceof ModUnitRegistry) {
                object2 = (ModUnitRegistry)as2;
                if (!((ModUnitRegistry)object2).aF) continue;
            }
            object2 = new DecorUnit(as2, 1, null);
            object2 = new com.corrodinggames.rts.game.units.actions.ActionWrapper((GameAction) object2, buildFilter);
            boolean bl2 = false;
            for (GameAction s2 : (java.util.Collection<GameAction>) (java.util.Collection) D) {
                if (!s2.equals(object2)) continue;
                bl2 = true;
            }
            if (bl2) continue;
            D.add(object2);
        }
    }


    public ArrayList N() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return D;
    }


    public boolean hasActiveProduction() {
        return true;
    }


    public float cancelBuild(int n2) {
        return 10.0f;
    }


    public boolean isQueuePaused() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.renderExtraShadows && !this.isDead;
    }


    public float getQueueProgress() {
        return 1.0f;
    }


    public float getMaxQueueCapacity() {
        return 1.0f;
    }


    public boolean u() {
        return true;
    }


    public boolean canAcceptOrders() {
        return false;
    }


    public boolean d(UnitInstance am2) {
        return false;
    }


    public boolean isUpgrading() {
        return true;
    }


    public float a(UnitInstance am2, float f2, MovementController f3) {
        f2 = 0.0f;
        return 0.0f;
    }


    public void updateProductionQueue() {
    }


    public boolean P() {
        return true;
    }

    public void a(Factory h2) {
        this.r = h2.r;
        this.c = h2.c;
    }


    /* 02b 对应: super.a 抛 IOException (覆写链) */
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.c(1);
        as2.a(this.G);
        as2.b(this.H);
        super.a(as2);
    }


    public void a(InputNetStream k2) {
        byte by = k2.d();
        this.G = (UnitCategory) k2.b(n.class);
        if (this.G == null) {
            this.G = UnitCategory.a;
        }
        if (by >= 1) {
            this.H = k2.j();
        }
        super.a(k2);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.startBuild();
    }

    static {
        actionReloadUnits = new Factory$1("reloadUnits");
        actionReloadActive = new Factory$12("reloadOnlyActiveUnits");
        actionCloneUnit = new Factory$17("unitClone");
        k = new Factory$18("removeUnits");
        l = new Factory$19("killUnits");
        m = new Factory$20("finishQueue");
        actionNukeAt = new Factory$21("nukeAt");
        actionFreezeAI = new Factory$22("freezeAI");
        actionChangeAlliance = new Factory$23("changeAlliance");
        rallyPoint = new Factory$2("startRecording");
        idleTimer = new Factory$3("startReplayPlayback");
        totalProduced = new Factory$4("hideInterface");
        u = new Factory$5("freezeAllAI");
        v = new Factory$6("pauseGame");
        w = new Factory$7("slowGame");
        actionFastForward = new Factory$8("fastForward");
        y = new Factory$9("search");
        z = new Factory$10("enableDebug");
        actionEnableAIDebug = new Factory$11("enableAIDebug");
        actionEnableTriggerDebug = new Factory$13("enableTriggerDebug");
        actionClearSaveHistory = new Factory$14("clearSaveHistory");
        buildFilter = new Factory$16();
    }
}
