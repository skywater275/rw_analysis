/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.TagFilter;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.custom.animation.UnitTrait;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.game.units.projectiles.SpecialBuilding;
import com.corrodinggames.rts.game.units.projectiles.WallBuilding;
import com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit;
import com.corrodinggames.rts.game.units.commands.CustomGroundUnit;
import com.corrodinggames.rts.game.units.projectiles.AbstractSubBuilding;
import com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.TreeDecoration;
import com.corrodinggames.rts.game.units.PlayerUnitIndicator;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitTurret;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.WeaponTypeEnum;
import com.corrodinggames.rts.game.units.custom.af;
import com.corrodinggames.rts.game.units.custom.effects.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.commands.ExperimentalUnit;
import com.corrodinggames.rts.game.units.commands.ExperimentalSubUnit;
import com.corrodinggames.rts.game.units.commands.RepairBayUnit;
import com.corrodinggames.rts.game.units.projectiles.FactoryBuilding;
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.Sprite;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import com.corrodinggames.rts.gameFramework.utility.DequeList;
import com.corrodinggames.rts.game.map.MapEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public strictfp abstract class UnitInstance
extends com.corrodinggames.rts.gameFramework.Sprite {
    public float cv;
    public float cu;
    public UnitInstance bR;
    public float cg;  // v19.113j: 鏈濆悜瑙?(涓庣偖濉旇鍚屾 132.49/45.03 鈥?姝讳骸宸垎)
    public boolean cs;
    public long eh;
    public float eo;
    public float ep;
    public float eq;
    public boolean bD;
    public UnitInstance bu;
    public boolean cb;
    public android.graphics.RectF dB;
    public int dl;
    public float do_;
    public static java.util.ArrayList dy;  // 02b am.dy 闈欐€?(闈欐€佸潡璧嬪€?
    public float br;
    public int bs = -9999;  // v19.113g: 瀛樻。榛樿 -9999 (璺緞鐩稿叧鐘舵€?
    public UnitInstance bt = null;
    public UnitInstance bv = null;
    public VariableScope bw;
    public CustomActionBase bx;  // 02b am.bx (d/b) (custom.d.b=CustomActionBase, 02b b.java L17 m 閾佽瘉)
    public CustomActionBase by;  // 02b am.by (d/b) (custom.d.b=CustomActionBase)
    public int creationSequence = -9999;  // v19.113h 閾佽瘉: 02b am:627 = GlobalState.by 蹇収 + 瀛樻。閫掑 33350鈫?6454鈫?9550
    public int bA = -9999;
    public int bB = -9999;
    public int bC;
    public boolean isSelected;
    private static final DequeList a = new DequeList();
    public void b(PlayerState n2) {
        if (n2 == null) {
            throw new RuntimeException("Could not set team to null");
        }
        this.player = n2;
    }

    public static UnitInstance a(UnitTypeHandle as2) {  // 02b am.a(as): 绫诲瀷鈫掑疄渚嬬紦瀛?(bF HashMap)
        return (UnitInstance) bF.get(as2);
    }

    public static strictfp void bG() {  // 02b am.java L486-488
        a.a();
    }

    public static DequeList bF() {
        // 02 am.java:465 鈥?鍗曚綅杩唬鍣?(a.a() 閲嶇疆鍚庤繑鍥?
        a.a();
        return a;
    }
    public static HashMap bF = new HashMap();
    public static HashMap bG = new HashMap();
    public static HashMap bH = new HashMap();
    public static final Paint bI = new UniquePaint();
    public static final Paint bJ;
    static final LightingColorFilter bK;
    public boolean bL;
    public boolean bM = false;
    public boolean bN = false;
    public boolean bO = false;
    public boolean bP = false;
    public UnitInstance bQ = null;
    public float bS;
    public boolean bT = true;
    public boolean bV = false;  // 02b am.bV (L64)
    public int bU = 1;
    public boolean isDead = false;  // v19.113i 閾佽瘉: 02b鏃ュ織", dead:"+bV + 瀛樻。 false(娲? + 宕╂簝鏍堝兊灏稿崟浣嶉摼
    public long bW = 0L;
    public PlayerState player;  // v19.113i 閾佽瘉: 宕╂簝鏍?bX=鎵€灞炵帺瀹?+ 瀛樻。鍐?team id + isEnemy(player) 璋冪敤閾?
    public boolean bY;
    public float bZ = 0.0f;
    public float ca = 0.0f;
    public boolean s_ = false;
    public float cc = 0.0f;
    public float cd = 0.0f;
    public float ce = 0.0f;  // v19.113i: 绉诲姩鏂瑰悜瑙?(闈欐 0 / 绉诲姩 2.0135 rad)
    public float cf = 0.0f;  // v19.113h: 閫熷害鍊欓€?(tank 11 / builder 0)
    public float ch;  // v19.113j: 鏈濆悜瑙?(瀛樻。 xy 娈靛墠 鈥?涓?cg 鍚屼箟鍊欓€?
    public boolean ci;
    public float cj;  // v19.113j: 姝讳骸鍔ㄧ敾鍙傛暟1 (娲?0 / 姝?10.0)
    public float ck;  // v19.113j: 姝讳骸鍔ㄧ敾鍙傛暟2 (娲?0 / 姝?12.0)
    public float cl;
    public float cm = 1.0f;
    public float cn = 1.0f;
    public boolean co = false;
    public boolean cp = false;
    public boolean cq = false;
    public boolean cr = false;
    public boolean ct = false;
    public float hp;  // v19.113i 鍥涢噸閾佽瘉: 褰撳墠琛€閲?(agent 鐩村啓 210鈫?0 瀛樻。楠岃瘉)
    public float maxHp;  // v19.113i 閾佽瘉: 鏈€澶ц閲?(鍙椾激鍚庝粛 210)
    public float cw;
    public float cx;
    public float cy;
    public float cz;
    public float cA;
    public float cB;
    public float cC;
    public float cD;
    public int cE;
    public int cF;
    public boolean cG;
    public int cH = -9999;
    public boolean cI;
    public float cJ = 0.0f;
    public boolean cK = true;  // v19.113g: 璋冭瘯鏀剧疆鏍囪 (createUnit 璁?true; 瀛樻。 false)
    public UnitTurret[] cL;
    public boolean cM;
    public UnitInstance cN = null;
    public UnitType cO = null;
    public UnitTrait cP = null;
    public int cQ = -9999;
    public boolean cR;
    public int cS;
    public int cT;
    public int cU;
    public float cV;
    public static final Paint cW;
    public static final Paint cX;
    public static final Paint cY;
    public static final Paint cZ;
    public static final Paint da;
    public static final Paint db;
    public static final Paint dc;
    public static final Paint dd;
    public static final Paint de;
    public static final Paint df;
    public static final Paint dg;
    public static final Paint dh;
    public static final Paint di;
    public static final Paint dj;
    public static final Paint dk;
    public int dm = -1;
    public int factorySlotIndex2 = -1;
    public int dn = -99;
    public float smokeParticleTimer;
    public float dp;
    public float dq = 70.0f;
    static final RectF dr;
    static Paint ds;
    static Paint dt;
    public static final RectF du;
    public static final Rect dv;
    static final Rect dw;
    static final ArrayList dx;
    static ArrayList arrayList17;
    public UnitTypeHandle dz;
    static final RectF dA;
    static final RectF rectF15;
    static final Rect dC;
    static final PointF dD;
    static final PointF dE;
    PlayerUnitIndicator[] playerIndicators;
    static final PointF dG;
    EffectManager dH = new EffectManager();
    public com.corrodinggames.rts.game.units.custom.conditions.c dI = new com.corrodinggames.rts.game.units.custom.conditions.c();
        CustomActionBase dJ = null;  // 02b custom.d.b (ResourceComponent 为误建副本)


    public void serializeToStream(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {  // v19.113k: 02 am.a(j.as) 鍐欎晶 (鏃ц璇?isVisibleTo; 涓?v19.112 鎵? serializeToStream 鎯緥瀵归綈)
        Object object;
        int n2;
        as2.a(this.bM);
        as2.a(this.bQ);
        as2.a(this.bR);
        as2.a(this.bS);
        as2.a(this.bT);
        as2.a(this.isDead);
        as2.a(this.bW);
        as2.a(this.player);
        as2.a(this.bZ);
        as2.a(this.ca);
        as2.a(this.cc);
        as2.a(this.cd);
        as2.a(this.cf);
        as2.a(this.cg);
        as2.a(this.cj);
        as2.a(this.ck);
        as2.a(this.cl);
        as2.a(this.cm);
        as2.a(this.cp);
        as2.a(this.cs);
        as2.a(this.hp);
        as2.a(this.maxHp);
        as2.a(this.cK);
        as2.a(this.cL[0].turretAngle);
        as2.a(this.cL[0].lockDelay);
        as2.a(this.cN);
        as2.c(26);
        as2.a(this.cU);
        as2.a(this.cV);
        as2.a(this.ce);
        as2.a(this.ch);
        int n3 = this.getWeaponMountCount();
        as2.a(n3);
        for (n2 = 0; n2 < n3; ++n2) {
            object = this.cL[n2];
            as2.a(((UnitTurret) object).turretAngle);
            as2.a(((UnitTurret) object).turretPivotX);
            as2.a(((UnitTurret) object).lockDelay);
            as2.a(((UnitTurret) object).shootCooldown);
            as2.a(((UnitTurret) object).maxRotationAngle);
            as2.a(((UnitTurret) object).minAngleLimit);
            as2.a(((UnitTurret) object).maxAngleLimit);
            UnitInstance am2 = ((UnitTurret) object).targetUnit;
            if (am2 != null && am2.isDead) {
                am2 = null;
            }
            as2.a(am2);
            as2.a(this.cM);
        }
        as2.a(this.bs);
        as2.a(this.cx);
        as2.a(this.cy);
        as2.a(this.cz);
        as2.a(this.cA);
        as2.a(this.cq);
        as2.a(this.cr);
        as2.a(this.ct);
        as2.a(this.bN);
        as2.a(this.cB);
        as2.a(this.ci);
        as2.a(this.playerIndicators != null);
        if (this.playerIndicators != null) {
            as2.a(this.playerIndicators.length);
            for (n2 = 0; n2 < this.playerIndicators.length; ++n2) {
                object = this.playerIndicators[n2];
                as2.a(((PlayerUnitIndicator) object).discovered);
                as2.a(((PlayerUnitIndicator) object).typeValue);
            }
        }
        as2.a(this.cw);
        as2.b(this.bt);
        as2.a(this.cE);
        as2.a(this.cF);
        as2.a(this.creationSequence);
        as2.a(this.bA);
        as2.a(this.bB);
        as2.a(this.bC);
        as2.a(this.bO);
        as2.a(this.bP);
        this.dH.a(as2);
        this.dI.a(as2);
        as2.b(this.cO);
        n2 = -1;
        if (this.cO != null && this.cP != null) {
            n2 = this.cP.a();
        }
        as2.a((short)n2);
        as2.a(this.cQ);
        VariableScope.writeOutUnitOrPlaceholder(as2, this.cN);
        VariableScope.writeOutUnitOrPlaceholder(as2, this.bv);
        VariableScope.writeOut(as2, this.bw);
        CustomActionBase.a(as2, this.bx);  // 02b am.bx (d/b) 搴忓垪鍖?
        CustomActionBase.a(as2, this.by);  // 02b am.by (d/b) 搴忓垪鍖?
        as2.a(this.cn);
    }


    public void deserializeFromStream(InputNetStream k2) throws IOException {  // v19.113k: 02 am.a(j.k) 鍙嶅簭鍒楀寲 (鏃ц璇?isVisibleTo 鈥?閭ｆ槸 EffectConfig 鍙鎬ф娊璞?
        Object object;
        int n2;
        int n3;
        this.bM = (boolean) k2.readBoolean();
        this.bQ = k2.o();
        this.bR = k2.o();
        this.bS = k2.readFloat();
        this.bT = (boolean) k2.readBoolean();
        this.isDead = (boolean) k2.readBoolean();
        this.bW = k2.i();
        this.player = k2.r();
        this.bZ = k2.readFloat();
        this.ca = k2.readFloat();
        this.cc = k2.readFloat();
        this.cd = k2.readFloat();
        this.cf = k2.readFloat();
        this.cg = k2.readFloat();
        float f2 = k2.readFloat();
        float f3 = k2.readFloat();
        this.cl = k2.readFloat();
        this.cm = k2.readFloat();
        this.cp = (boolean) k2.readBoolean();
        this.cs = (boolean) k2.readBoolean();
        this.o(k2.readFloat());
        this.maxHp = k2.readFloat();
        this.cK = (boolean) k2.readBoolean();
        this.cL[0].turretAngle = k2.readFloat();
        this.cL[0].lockDelay = k2.readFloat();
        this.cN = k2.o();
        byte by = k2.d();
        if (by >= 1) {
            this.cU = (int) k2.readInt();
            this.cV = k2.readFloat();
        }
        if (by >= 2) {
            this.ce = k2.readFloat();
            this.ch = k2.readFloat();
            n3 = (int) k2.readInt();
            this.drawWaypointOverlays(n3);
            for (n2 = 0; n2 < n3; ++n2) {
                object = this.cL[n2];
                ((UnitTurret) object).turretAngle = k2.readFloat();
                ((UnitTurret) object).turretPivotX = k2.readFloat();
                ((UnitTurret) object).lockDelay = k2.readFloat();
                ((UnitTurret) object).shootCooldown = k2.readFloat();
                ((UnitTurret) object).maxRotationAngle = k2.readFloat();
                if (by >= 8) {
                    ((UnitTurret) object).minAngleLimit = k2.readFloat();
                    ((UnitTurret) object).maxAngleLimit = k2.readFloat();
                    ((UnitTurret) object).targetUnit = k2.o();
                }
                if (by < 12) continue;
                this.cM = (boolean) k2.readBoolean();
            }
        }
        if (by >= 3) {
            this.bs = (int) k2.readInt();
        }
        if (by >= 4) {
            this.cx = k2.readFloat();
            this.cy = k2.readFloat();
            this.cz = k2.readFloat();
            this.cA = k2.readFloat();
        }
        if (by >= 5) {
            this.cq = (boolean) k2.readBoolean();
            this.cr = (boolean) k2.readBoolean();
        }
        if (by >= 6) {
            this.ct = (boolean) k2.readBoolean();
        }
        if (by >= 7) {
            this.bN = (boolean) k2.readBoolean();
        }
        if (by >= 9) {
            this.cB = k2.readFloat();
        }
        if (by >= 10) {
            this.ci = (boolean) k2.readBoolean();
        }
        if (by >= 11 && (n3 = (int)((boolean) k2.readBoolean() ? 1 : 0)) != 0) {
            this.playerIndicators = new PlayerUnitIndicator[(int) k2.readInt()];
            for (n2 = 0; n2 < this.playerIndicators.length; ++n2) {
                this.playerIndicators[n2] = new PlayerUnitIndicator();
                object = this.playerIndicators[n2];
                ((PlayerUnitIndicator) object).discovered = (boolean) k2.readBoolean();
                ((PlayerUnitIndicator) object).typeValue = (int) k2.readInt();
            }
        }
        if (by >= 13) {
            this.cw = k2.readFloat();
        }
        if (by >= 14) {
            this.bt = k2.o();
        }
        if (by >= 15) {
            this.cE = (int) k2.readInt();
            this.cF = (int) k2.readInt();
        }
        if (by >= 16) {
            this.creationSequence = (int) k2.readInt();
            this.bA = (int) k2.readInt();
            this.bB = (int) k2.readInt();
        }
        if (by >= 17) {
            this.bC = (int) k2.readInt();
        }
        if (by >= 18) {
            this.bO = (boolean) k2.readBoolean();
            this.bP = (boolean) k2.readBoolean();
        }
        if (by >= 19) {
            this.dH.a(k2);
            this.dI.a(this, k2);
        }
        if (by >= 20) {
            UnitType y2 = k2.p();
            n2 = k2.v();
            if (n2 != -1) {
                UnitTrait n4;
                boolean bl = false;
                if (y2 != null && this instanceof UnitType && (n4 = y2.a((short)n2)) != null && y2.a((UnitType) this, n4)) {
                    bl = true;
                }
                if (!bl) {
                    this.getAttackRange();
                }
            }
        }
        if (by >= 21) {
            this.cQ = (int) k2.readInt();
        }
        if (by >= 22) {
            if (by < 24) {
                throw new IOException("extension >=22 but <24");
            }
            this.cN = VariableScope.readInUnitOrPlaceholder(k2);
            this.bv = VariableScope.readInUnitOrPlaceholder(k2);
        }
        if (by >= 23) {
            this.bw = VariableScope.readIn(k2);
        }
        if (by >= 25) {
            this.bx = CustomActionBase.a(k2);  // 02b b.java L661: a(k)
            this.by = CustomActionBase.a(k2);  // 02b b.java L661: a(k)
        }
        if (by >= 26) {
            this.cn = k2.readFloat();
        }
        if (this.isDead) {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            bE.remove(this);
            l2.cc.a(this);
        }
    }

    public static Texture isVisibleTo(Texture e2) {
        return UnitInstance.isVisibleTo(e2, e2.m(), e2.l());
    }

    public static Texture isVisibleTo(Texture e2, int n2, int n3) {
        Texture e3 = e2.setRenderTarget(n2, n3, false);
        e2.x();
        e3.j();
        int n4 = e3.m();
        int n5 = e3.l();
        for (int j = 0; j < n4; ++j) {
            for (int i2 = 0; i2 < n5; ++i2) {
                int n6 = e2.setRenderTarget(j, i2);
                e3.a(j, i2, Color.a(Color.a(n6), 0, 0, 0));
            }
        }
        e3.p();
        e3.s();
        e2.y();
        e3.setRenderTarget("shadow:" + e2.setRenderTarget());
        e3.n = true;
        return e3;
    }

    public static DequeList getUnitPool() {
        a.a();
        return a;
    }

    public static void clearUnitPool() {
    }

    public static void clearAllStaticRegistries() {  // 02b am.bH()
        com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase.dt();  // 02b e/j.dt()
        com.corrodinggames.rts.game.units.commands.BuildSlot.dt();  // 02b d/d.dt()
        com.corrodinggames.rts.game.units.projectiles.AbstractSubBuilding.K();  // 02b e/h.K()
        com.corrodinggames.rts.game.units.debug.FactoryAction6.M();  // 02b h/f.M()
        com.corrodinggames.rts.game.units.buildings.AbstractUnitBehavior.K();  // 02b b/b.K()
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.as()) {
            for (UnitRegistry ar2 : (java.util.Collection<UnitRegistry>) (java.util.Collection) EnumSet.allOf(UnitRegistry.class)) {  // 02b ar
                ar2.b();
            }
        } else {
            com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.K();  // 02b e/b.K()
            com.corrodinggames.rts.game.units.commands.ExperimentalSubUnit.b();  // 02b d/p.b()
            com.corrodinggames.rts.game.units.commands.RepairBayUnit.M();  // 02b d/r.M()
            TreeDecoration.b();  // 02b al.b(): 鍔犺浇 palm_tree 绾圭悊
        }
        UnitRegistry.t();  // 02b UnitRegistry.t()
    }

    public boolean isFactoryBuilding() {
        return false;
    }

    public boolean isNeutralTeam() {
        return false;
    }

    public static HashMap buildTypeInstanceCache() {  // 02b am.bK()
        UnitInstance am2;
        HashMap<UnitTypeHandle, UnitInstance> hashMap = new HashMap<UnitTypeHandle, UnitInstance>();
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.as()) {
            for (UnitRegistry as2 : (java.util.Collection<UnitRegistry>) (java.util.Collection) EnumSet.allOf(UnitRegistry.class)) {  // 02b ar
                am2 = as2.a(true);
                am2.a();
                am2.b(PlayerState.i);
                am2.cp = true;
                hashMap.put(as2, am2);
            }
        }
        for (com.corrodinggames.rts.game.units.custom.ModUnitRegistry l3 : (java.util.Collection<com.corrodinggames.rts.game.units.custom.ModUnitRegistry>) (java.util.Collection) com.corrodinggames.rts.game.units.custom.ModUnitRegistry.d) {  // 02b custom/l.d
            am2 = l3.a(true);
            am2.a();
            am2.b(PlayerState.i);
            am2.cp = true;
            hashMap.put(l3, am2);
        }
        return hashMap;
    }

    public static void initAllCaches() {  // 02b am.bL(): bG=bK(); bH=bK(); bF=bK()
        bG = UnitInstance.buildTypeInstanceCache();
        bH = UnitInstance.buildTypeInstanceCache();
        bF = UnitInstance.buildTypeInstanceCache();
    }

    public static UnitInstance isVisibleTo(UnitTypeHandle as2) {
        UnitInstance am2 = (UnitInstance) bF.get(as2);
        return am2;
    }

    public static UnitInstance setTeamInternal(UnitTypeHandle as2) {  // 02b am.b(as): 濮旀墭 c(as)
        return UnitInstance.isRenderable(as2);  // 02b c(as): bG 缂撳瓨鏌ユ壘 (鍚己鐪佸崰浣?
    }

    public static UnitInstance isRenderable(UnitTypeHandle as2) {
        UnitInstance am2 = (UnitInstance) bG.get(as2);
        if (am2 == null) {
            if (ModUnitRegistry.b == null) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Could not find:" + as2.i() + " and missing place holder is null");
                return null;
            }
            am2 = (UnitInstance) bG.get(ModUnitRegistry.b);
            if (am2 == null) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("name: " + ModUnitRegistry.b.M);
                com.corrodinggames.rts.gameFramework.GlobalState.e("contains:" + bG.containsKey(ModUnitRegistry.b));
                for (UnitTypeHandle as3 : (java.util.Collection<UnitTypeHandle>) (java.util.Collection) bG.keySet()) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("has:" + as3.i());
                }
                com.corrodinggames.rts.gameFramework.GlobalState.e("Could not find:" + as2.i() + " and missing place holder could not be found");
            }
        }
        return am2;
    }

    public static UnitInstance isVisibleToTeam(UnitTypeHandle as2) {
        UnitInstance am2 = (UnitInstance) bH.get(as2);
        if (am2 == null) {
            am2 = (UnitInstance) bH.get(ModUnitRegistry.b);
        }
        return am2;
    }

    public static int computeTypesHash() {  // 02b am.bM()
        int n2 = 0;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.as()) {
            for (UnitRegistry ar2 : (java.util.Collection<UnitRegistry>) (java.util.Collection) EnumSet.allOf(UnitRegistry.class)) {  // 02b ar
                UnitInstance am2 = UnitInstance.a(ar2);
                n2 = n2 * 31 + am2.computeUnitHash();
            }
        }
        return n2;
    }

    public strictfp void bS() {  // 02b am.bS(): 璁剧疆鐐鏁?(O(1) = drawWaypointOverlays(1))
        this.drawWaypointOverlays(1);
    }

    public strictfp int bY() {  // 02b am.bY()
        return -1;
    }

    public strictfp int bZ() {  // 02b am.bZ()
        return -1;
    }

    public strictfp boolean bU() {  // 02b am.bU()
        return true;
    }

    protected UnitInstance(boolean bl2) {
        super(bl2);
        this.bS();
        if (!bl2) {
            this.bL = true;
            bE.a(this);
            a.a(this);  // 02b utility/o.a: 鍔犲叆 DequeList a
        }
        this.creationSequence = com.corrodinggames.rts.gameFramework.GlobalState.B().by;
        this.dz = this.r();
    }


    public static void a(Paint paint) {
        a(paint, false);
    }

    public static void a(Paint paint, boolean bl) {
        if (com.corrodinggames.rts.gameFramework.GlobalState.av() || bl) {
            paint.a(0.0f);
        }
    }

    public void isVisibleTo() {  // 02b am.a(): 鍙嶆敞鍐?
        PlayerState.a(this);
        if (this.bL) {
            bE.remove(this);
            a.b(this);  // 02b utility/o.b: 浠?DequeList a 绉婚櫎
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bS.l(this);
        super.a();  // 02b ay.a()
    }

    public float getBuildDuration() {
        return 3000.0f;
    }

    public int s(UnitInstance am2) {
        return 0;
    }

    public boolean hasSpawnedDeathEffect() {
        return false;
    }

    public boolean canAttackAir() {
        return false;
    }

    public float getXpKillReward() {
        return -1.0f;
    }

    final void updateUnitAgeState() {
        int n2 = 1;
        if (this.i()) {
            n2 = 3;
        } else if (this.Q()) {  // 02b am L667: else-if this.Q()
            n2 = 2;
        }
        if (this.cN != null) {
            n2 = -1;
        }
        if (!this.bT) {
            n2 = -1;
        }
        this.bU = n2;
    }

    public void o(float f2) {
        this.hp = f2;
    }

    public void initTurretTargets() {
        this.drawWaypointOverlays(1);
    }

    public void drawWaypointOverlays(int n2) {
        int n3;
        int n4 = this.getWeaponMountCount();
        if (n4 < n2) {
            n4 = n2;
        }
        if (this.cL == null) {
            n3 = 0;
            this.cL = new UnitTurret[n4];
        } else if (this.cL.length < n4) {
            n3 = this.cL.length;
            this.cL = Arrays.copyOf(this.cL, n4);
        } else {
            return;
        }
        for (int i2 = n3; i2 < this.cL.length; ++i2) {
            this.cL[i2] = new UnitTurret();
        }
    }

    public static void isVisibleTo(Paint paint) {
        UnitInstance.a(paint, false);
    }

    public static void isVisibleTo(Paint paint, boolean bl2) {
        if (!com.corrodinggames.rts.gameFramework.GlobalState.av() && bl2) {
            paint.a(0.0f);
        }
    }

    public float isVisibleToTeam(boolean bl2) {
        return this.cg + 90.0f;
    }

    public final boolean isAlive() {
        if (this.cN != null) {
            return false;
        }
        return this.cm >= 1.0f;
    }

    public float getHealthPercent() {
        if (this.hp < this.maxHp) {
            return this.hp / this.maxHp;
        }
        return -1.0f;
    }

    public boolean shouldDrawHpBar() {
        return true;
    }

    public float getBuildProgressForDisplay() {
        if (this.cm < 1.0f && (this.cO == null || this.cO.cm >= 1.0f)) {
            return this.cm;
        }
        return -1.0f;
    }

    public float getRepairProgressForDisplay() {
        return -1.0f;
    }

    public boolean canBeManuallyRepaired() {
        return false;
    }

    public int getActionCategoryId() {
        return -1;
    }

    public int getActionSubCategoryId() {
        return -1;
    }


    public strictfp boolean bO() {
        return false;
    }

    public strictfp boolean bI() {
        return false;
    }

    public strictfp boolean cr() {  // 02b am.cr(): 榛樿 false
        return false;
    }

    public strictfp boolean c_() {  // 02b am.c_()
        return true;
    }

    public strictfp void a(java.util.ArrayList arrayList) {  // 02b am.a(ArrayList): 娓呯┖
        arrayList.clear();
    }

    public strictfp void cX() {  // 02b am.cX(): 杩烽浘鍗曚綅鎸囩ず鍣?(dF 鏁扮粍 + GameHUD 钃濆浘鏍囪)
        GlobalState l2 = GlobalState.B();
        if (l2.bs != null && this.player != l2.bs && l2.bs.k >= 0 && l2.bs.k < com.corrodinggames.rts.game.PlayerState.c) {
            PlayerUnitIndicator an2 = this.playerIndicators[l2.bs.k];  // 02b am.dF[]
            if (an2.indicator != null && an2.indicator.c) {
                an2.indicator = null;
            }
            if (an2.indicator == null && an2.discovered) {
                boolean bl = this.d(l2.bs);
                if (!bl) {
                    com.corrodinggames.rts.gameFramework.effects.GameHUD gameHUD = new com.corrodinggames.rts.gameFramework.effects.GameHUD();
                    an2.indicator = gameHUD;
                    gameHUD.d = this.r();
                    gameHUD.g = this.eo;
                    gameHUD.h = this.ep;
                    gameHUD.n = false;
                    gameHUD.e = this.player;
                    gameHUD.f = an2.typeValue;
                    gameHUD.j = l2.bs;
                    gameHUD.u = this.c_();
                    gameHUD.v = this;
                }
            }
        }
    }

    public strictfp boolean cG() {  // 02b am.cG(): 鏄惁缁樺埗浼犳劅鍣ㄥ湀
        return false;
    }

    public strictfp Rect a_(boolean bl2) {  // 02b am.a_(boolean): 娓叉煋鐭╁舰 (闈欐€?dC)
        byte by = 0;
        byte by2 = 0;
        dC.a = by;  // 02b 瀛楄妭鐮? Rect 娣锋穯瀛楁 a/b/c/d = left/top/right/bottom (game-lib 娣锋穯 SDK)
        dC.b = by2;
        dC.c = by + this.es;
        dC.d = by2 + this.et;
        return dC;
    }

    public strictfp Rect a(boolean bl2, int n2) {  // 02b am.java L1797: a(Z,I) 帧矩形(补建, TurretBuilding super.a(bl,e) 链)
        int n3 = 0;
        int n4 = 0;
        int n5 = n3 + n2 * this.es;
        UnitInstance.dC.a = n5;
        UnitInstance.dC.b = n4;
        UnitInstance.dC.c = n5 + this.es;
        UnitInstance.dC.d = n4 + this.et;
        return dC;
    }

    public strictfp Rect a(boolean bl2, int n2, int n3) {  // 02b am.java L1805: a(Z,II) 甯х煩褰?(es/et=甯у楂? 闈欐€?dC)
        int n4 = this.es;
        int n5 = this.et;
        int n6 = n2 * n4;
        int n7 = n3 * n5;
        UnitInstance.dC.a = n6;
        UnitInstance.dC.b = n7;
        UnitInstance.dC.c = n6 + n4;
        UnitInstance.dC.d = n7 + n5;
        return dC;
    }

    public strictfp float cD() {  // 02b am.cD(): 娓叉煋缂╂斁绯绘暟
        return 1.0f;
    }

    public strictfp RectF cE() {  // 02b am.cE(): 缂╂斁鍚庤竟鐣?(闈欐€?dA)
        GlobalState l2 = GlobalState.B();
        float f2 = this.cD();
        dA.a(this.eo - this.eu * f2 - l2.cw, this.ep - this.ev * f2 - l2.cx, this.eo + this.eu * f2 - l2.cw, this.ep + this.ev * f2 - l2.cx);  // 02b: RectF.a(FFFF) = set
        return dA;
    }

    public strictfp RectF cF() {  // 02b am.cF(): 闈欐€佽竟鐣?(闈欐€?dA)
        GlobalState l2 = GlobalState.B();
        RectF rectF = dA;
        float f2 = l2.cw;
        float f3 = l2.cx;
        float f4 = this.eu;
        float f5 = this.ev;
        rectF.a = this.eo - f4 - f2;  // 02b: RectF 娣锋穯瀛楁 a/c/b/d
        rectF.c = this.eo + f4 - f2;
        rectF.b = this.ep - f5 - f3;
        rectF.d = this.ep + f5 - f3;
        return rectF;
    }

    public strictfp PointF cY() {  // 02b am.cY(): 鐗规晥娓叉煋鍋忕Щ (闈欐€?dG)
        dG.a(0.0f, 0.0f);  // 02b: PointF.a(FF) = set
        return dG;
    }

    public strictfp float getSensorRange(boolean bl2) {  // 02b am.d(boolean): 浼犳劅鍣ㄨ寖鍥?
        return this.cg + 90.0f;
    }

    public strictfp float g() {  // 02b am.g()
        return 0.0f;
    }

    public strictfp float b(UnitInstance am2) {  // 02b am.b(am): 浼ゅ绯绘暟
        return 1.0f;
    }

    public strictfp boolean y(UnitInstance am2) {  // 02b am.y(am)
        boolean bl = false;
        boolean bl2 = am2.g() > 0.0f;
        if (bl2) {
            bl = true;
        }
        return bl;
    }

    public strictfp int v(UnitInstance am2) {  // 02b am.v(am)
        return this.y() + am2.r().a(this);
    }

    public strictfp boolean w(UnitInstance am2) {  // 02b am.w(am)
        return false;
    }

    public strictfp int u(UnitInstance am2) {  // 02b am.u(am)
        return this.y() + am2.r().a(this);
    }

    public strictfp boolean x(UnitInstance am2) {  // 02b am.x(am)
        return false;
    }

    public strictfp boolean bX() {  // 02b am.bX()
        return false;
    }

    public strictfp int cS() {  // 02b am.cS(): 寤洪€犳牸鏁?
        return 500;
    }

    public strictfp float bN() {  // 02b am.bN(): 瑙嗛噹璺濈
        return 3000.0f;
    }

    public strictfp int y() {  // 02b am.y()
        return 85;
    }

    public strictfp float f(UnitTypeHandle as2) {  // 02b am.f(as)
        int n2 = as2.a(this) + this.y();
        return (float) n2;
    }

    public strictfp boolean d(UnitInstance am2, boolean bl) {  // 02b am.d(am,boolean)
        return false;
    }

    public strictfp boolean e(UnitInstance am2, boolean bl) {  // 02b am.e(am,boolean)
        return false;
    }

    public strictfp boolean a(UnitInstance am2, float f2) {  // 02b am.a(am,float)
        return false;
    }

    public void a(GameAction s2) {  // 02b am.a(a.s): 绌?
    }

    public void a(GameAction s2, boolean bl) {  // 02b am.a(a.s,boolean): 绌?
    }

    public void a(GameAction s2, boolean bl, PointF pointF, UnitInstance am2) {  // 02b am.a(a.s,boolean,PointF,am)
        this.a(s2, bl);
    }

    public final strictfp boolean c(float f2, float f3, float f4) {  // 02b am.c(fff): 鍗婂緞鍐呮鏌?
        float f5 = GameUtils.a(this.eo, this.ep, f2, f3);
        float f6 = this.cj + f4;
        return f5 < f6 * f6;
    }

    public strictfp void cP() {  // 02b am.cP()
    }

    public strictfp void f(PlayerState n2) {  // 02b am.f(n): 璁剧疆鎵€灞炵帺瀹?(淇濈暀涓珛鏂?
        if (this.p()) {
            this.b(PlayerState.i);
        } else {
            this.b(n2);
        }
    }

    public strictfp boolean dd() {  // 02b am.dd()
        return false;
    }

    public abstract boolean Q();  // 02b am.Q()

    public strictfp boolean d(UnitInstance am2) {  // 02b am.d(am)
        return true;
    }

    public abstract boolean l();  // 02b am.l(): 鏄惁鍦ㄥ湴鍥惧唴/鍙椿鍔?

    public strictfp boolean dk() {  // 02b am.dk()
        return false;
    }

    public strictfp boolean dl() {
        return this.bO();
    }

    public strictfp boolean dm() {
        return this.bO();
    }

    public strictfp int cQ() {  // 02b am.java L1927: cQ() 榛樿 MAX_VALUE (CustomUnitType override 涓哄脊鑽暟)
        return Integer.MAX_VALUE;
    }

    public strictfp int d(UnitType y2) {  // 02b am.d(y) javap 瀛楄妭鐮佺洿璇? 缁熻寮曠敤鏈鍣ㄧ殑鍚岀帺瀹跺崟浣嶆暟
        PlayerState n2 = y2.player;
        UnitInstance[] amArray = bE.a();
        int n3 = 0;
        for (int i2 = 0; i2 < bE.size(); ++i2) {
            UnitInstance am2 = amArray[i2];
            if (am2.player != n2 || !(am2 instanceof UnitType)) continue;
            WeaponAction au2 = ((UnitType)am2).ar();
            if (au2 == null || au2.d() != com.corrodinggames.rts.game.units.WeaponTypeEnum.g || au2.h != this || am2 == y2) continue;
            ++n3;
        }
        return n3;
    }

    public strictfp boolean c(UnitType y2) {  // 02b am.c(y) javap 瀛楄妭鐮佺洿璇? 姝﹀櫒瀹归噺妫€鏌?
        int n2 = this.cQ();
        return n2 < Integer.MAX_VALUE && this.d(y2) >= n2;
    }

    public strictfp void di() {  // 02b am.java L2156 绌哄疄鐜?
    }

    public strictfp void dj() {  // 02b am.java L2158 绌哄疄鐜?
    }

    public strictfp com.corrodinggames.rts.game.units.actions.ActionId cp() {  // 02b am.cp() javap: return a/s.i (GameAction.i)
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }

    public strictfp float bV() {  // 02b am.java L742: bV()
        return this.cm < 1.0f && (this.cO == null || this.cO.cm >= 1.0f) ? this.cm : -1.0f;
    }

    public strictfp float x() {  // 02b am.java L734: x() 琛€閲忕櫨鍒嗘瘮 (getHealthPercent 涓鸿涔夊悕鍓湰)
        return this.hp < this.maxHp ? this.hp / this.maxHp : -1.0f;
    }

    public strictfp com.corrodinggames.rts.game.units.custom.resources.CustomActionBase cN() {  // 02b am.java L1854: d.b cN() { return null; } (void 涓哄够瑙?
        return null;
    }

    public strictfp void f_() {  // 02b am.java L1194: f_() 姝讳骸鐘舵€佸垏鎹?
        if (this.isDead) {
            this.bT = false;
        } else {
            this.bT = true;
        }
    }

    public strictfp float bW() {
        return -1.0f;
    }

    public strictfp Point a(MapEngine b2, Point point) {  // 02b am.java L2105-2109
        point.a = (int)((this.eo - this.cZ() + 1.0f) * b2.float1);
        point.b = (int)((this.ep - this.da() + 1.0f) * b2.float2);
        return point;
    }

    public strictfp float cZ() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bL.selectedTileX;
    }

    public strictfp float db() {  // 02b am.java L2100: db()
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return (float)(l2.bL.selectedTileX + 2) + l2.bS.r(this);
    }

    public strictfp float da() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bL.selectedTileY;
    }

    public strictfp void ch() {
    }

    @Override
    public void a(float f2) {  // 02b am.java L1202-1220: 受伤回复 (简化 TODO: 低血烟雾省略)
        if (!this.isDead) {
            if (this.cw > 0.0f) {
                if (this.cw > this.cv * 2.0f) {
                    this.cw = this.cv * 2.0f;
                }
                this.cw = com.corrodinggames.rts.gameFramework.GameUtils.a(this.cw, f2);
            }
        }
    }

    public void a(float f2, boolean bl2) {  // 02b am.java L762: a(float,boolean) 琛€鏉?閫夋嫨缁樺埗 (鏃ц鍚?isVisibleTo)
        int n2;
        if (this.isDead || this.cN != null) {
            return;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f3 = this.cj;
        if (bl2) {
            return;
        }
        boolean bl3 = false;
        int n3 = this.bY();
        if (this.bW() >= 0.0f) {
            bl3 = true;
        }
        if (this.bW() >= 0.0f) {
            bl3 = true;
        }
        if (this.cG || l2.bQ.showHp) {
            if (this.getHealthPercent() >= 0.0f) {
                bl3 = true;
            }
            if (n3 >= 0) {
                bl3 = true;
            }
        }
        if (!bl3) {
            return;
        }
        float f4 = this.eo - l2.cw;
        float f5 = this.ep - l2.cx - this.eq;
        float f6 = f3 + 4.0f;
        int n4 = 120;
        int n5 = 200;
        int n6 = 4;
        float f7 = 2.0f * f3;
        float f8 = this.co ? 1.0f : l2.cX;
        if (f8 < 1.0f) {
            l2.bO.k();
            l2.S();
            f4 *= l2.cX;
            f5 *= l2.cX;
            f6 *= l2.cX;
        }
        float f9 = 3.0f;
        if (this.cG || l2.bQ.showHp) {
            if (this.getHealthPercent() >= 0.0f) {
                n2 = 0;
                boolean bl4 = false;
                UnitTrait n7 = this.dn();
                if (n7 != null) {
                    n2 = n7.p ? 1 : 0;
                    bl4 = n7.q;
                }
                if (!bl4) {
                    int n8;
                    int n9;
                    if (l2.bs.c(this.player)) {
                        n9 = GameUtils.b(200, 183, 44, 44);
                        n8 = GameUtils.b(120, 255, 60, 60);
                    } else {
                        n9 = GameUtils.b(200, 0, 150, 0);
                        n8 = GameUtils.b(120, 0, 230, 0);
                    }
                    Paint paint = PathfindingUtils.a(n9, Paint$Style.a);
                    Paint paint2 = PathfindingUtils.a(n8, Paint$Style.b);
                    int n10 = n6;
                    if (n2 != 0) {
                        n10 = 1;
                    }
                    dr.a(f4 - f3, f5 + f6, f4 - f3 + f7 * this.getHealthPercent(), f5 + f6 + (float)n10);
                    l2.bO.a(dr, paint);
                    dr.a(f4 - f3, f5 + f6, f4 - f3 + f7, f5 + f6 + (float)n10);
                    l2.bO.a(dr, paint2);
                    if (this.cC != 0.0f && this.bU() && l2.bQ.showHpChanges) {
                        float f10 = this.getHealthPercent();
                        float f11 = f10 + -this.cC / this.maxHp;
                        if (f11 < 0.0f) {
                            f11 = 0.0f;
                        }
                        if (f11 >= 1.0f) {
                            f11 = 1.0f;
                        }
                        int n11 = GameUtils.b(100, 232, 208, 26);
                        Paint paint3 = PathfindingUtils.a(n11, Paint$Style.a);
                        dr.a(f4 - f3 + f7 * f10, f5 + f6, f4 - f3 + f7 * f11, f5 + f6 + (float)n10);
                        l2.bO.a(dr, paint3);
                    }
                }
            }
            if (n3 >= 0) {
                n2 = this.bZ();
                float f12 = f7;
                if (n2 > 10) {
                    f12 += 20.0f;
                }
                float f13 = f4 - f12 / 2.0f;
                float f14 = (int)(f12 / (float)n2 + 0.5f);
                float f15 = f14 - 2.0f;
                float f16 = 3.0f;
                for (int i2 = 1; i2 <= n2; ++i2) {
                    float f17 = f13 + (float)(i2 - 1) * f14;
                    dr.a(f17, f5 + f6 + f9, f17 + f15, f5 + f6 + f9 + 3.0f);
                    if (n3 >= i2) {
                        l2.bO.a(dr, PathfindingUtils.a(240, 0, 0, 255, Paint$Style.a));
                    }
                    l2.bO.a(dr, PathfindingUtils.a(110, 0, 0, 210, Paint$Style.b));
                }
                f9 += 5.0f;
            }
        }
        if (this.bW() >= 0.0f) {
            n2 = n6;
            n6 = 2;
            int n12 = n6 + 1;
            boolean bl5 = this.bX();
            dr.a(f4 - f3, f5 + f6 + (float)n12 + f9, f4 - f3 + f7 * this.bW(), f5 + f6 + (float)n12 + (float)n6 + f9);
            int n13 = bl5 ? GameUtils.b(185, 103, 117, 119) : GameUtils.b(200, 23, 179, 207);
            l2.bO.a(dr, PathfindingUtils.a(n13, Paint$Style.a));
            dr.a(f4 - f3, f5 + f6 + (float)n12 + f9, f4 - f3 + f7, f5 + f6 + (float)n12 + (float)n6 + f9);
            n13 = bl5 ? GameUtils.b(105, 123, 182, 193) : GameUtils.b(120, 45, 211, 241);
            l2.bO.a(dr, PathfindingUtils.a(n13, Paint$Style.b));
            f9 += (float)n6;
            n6 = n2;
        }
        if (this.bW() >= 0.0f) {
            n2 = n6 + 1;
            dr.a(f4 - f3, f5 + f6 + (float)n2 + f9, f4 - f3 + f7 * this.bW(), f5 + f6 + (float)n2 + (float)n6 + f9);
            l2.bO.a(dr, PathfindingUtils.a(200, 0, 0, 150, Paint$Style.a));
            dr.a(f4 - f3, f5 + f6 + (float)n2 + f9, f4 - f3 + f7, f5 + f6 + (float)n2 + (float)n6 + f9);
            l2.bO.a(dr, PathfindingUtils.a(120, 0, 0, 230, Paint$Style.b));
            f9 += (float)n6;
        }
        if (f8 < 1.0f) {
            l2.bO.l();
        }
    }


    public void isVisibleToTeam(float f2) {
    }


    public void p(float f2) {
        if (this.isDead || this.cN != null) {
            return;
        }
        if (this.cG) {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            if (this.player == l2.bs || l2.bS.m(this)) {
                if (l2.bQ.showUnitWaypoints && l2.dw <= 40) {
                    ++l2.dw;
                    this.drawWaypointOverlays();
                }
                this.onProjectileImpact();
            }
            if (PathfindingUtils.a(this)) {
                this.isOnScreen();
            }
        }
    }

    public void onProjectileImpact() {
    }

    public void drawWaypointOverlays() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        WeaponAction au2 = null;
        WeaponAction au3 = null;
        if (this instanceof UnitType) {
            UnitType y2 = (UnitType) this;
            int n2 = y2.av();
            float f2 = this.eo;
            float f3 = this.ep;
            for (int i2 = 0; i2 < n2; ++i2) {
                float f4;
                WeaponAction au4 = y2.k(i2);
                if (au4 == null) continue;
                if (com.corrodinggames.rts.gameFramework.GlobalState.av()) {
                    ds.a(2.0f);
                } else {
                    ds.a(0.0f);
                }
                int n3 = 160;
                if (au4.d() == WeaponTypeEnum.b) {
                    ds.b(Color.a(n3, 180, 0, 0));
                } else if (au4.d() == WeaponTypeEnum.h) {
                    ds.b(Color.a(n3, 180, 180, 0));
                } else if (au4.d() == WeaponTypeEnum.c) {
                    ds.b(Color.a(n3, 0, 0, 180));
                } else if (au4.d() == WeaponTypeEnum.d) {
                    ds.b(Color.a(n3, 0, 0, 180));
                } else if (au4.d() == WeaponTypeEnum.e || au4.d() == WeaponTypeEnum.i) {
                    ds.b(Color.a(n3, 0, 180, 180));
                } else if (au4.d() == WeaponTypeEnum.g) {
                    ds.b(Color.a(n3, 180, 0, 42));
                } else if (au4.d() == WeaponTypeEnum.k || au4.d() == WeaponTypeEnum.l) {
                    ds.b(Color.a(n3, 97, 20, 229));
                } else if (au4.d() == WeaponTypeEnum.j) {
                    ds.b(Color.a(n3, 0, 210, 210));
                    if (au2 == null) {
                        au2 = au4;
                    } else {
                        au3 = au4;
                    }
                } else {
                    ds.b(Color.a(n3, 0, 180, 0));
                }
                float f5 = au4.g();
                float f6 = au4.h();
                UnitInstance am2 = au4.i();
                if (am2 != null && au4.f() && !am2.isFactoryBuilding() && !am2.d(l2.bs)) {
                    float f7 = 400.0f;
                    f4 = com.corrodinggames.rts.gameFramework.GameUtils.d(f2, f3, f5, f6);
                    f5 = f2 + GameUtils.cosFast(f4) * f7;
                    f6 = f3 + GameUtils.sinFast(f4) * f7;
                }
                l2.bO.a(f2 - l2.cw, f3 - l2.cx, f5 - l2.cw, f6 - l2.cx, ds);
                boolean bl2 = false;
                if (bl2) {
                    f4 = GameUtils.b(f2, f3, f5, f6);
                    float f8 = com.corrodinggames.rts.gameFramework.GameUtils.d(f2, f3, f5, f6);
                    float f9 = l2.bS.aT * f4;
                    float f10 = f2 + GameUtils.cosFast(f8) * f9;
                    float f11 = f3 + GameUtils.sinFast(f8) * f9;
                    dr.a(f10 - 1.0f, f11 - 1.0f, f10 + 1.0f, f11 + 1.0f);
                    dr.a(-l2.cw, -l2.cx);
                    l2.bO.a(dr, ds);
                }
                f2 = f5;
                f3 = f6;
            }
        }
        if (au2 != null && au3 != null && au2 != au3) {
            ds.b(Color.a(50, 0, 210, 210));
            float f12 = au3.g();
            float f13 = au3.h();
            WeaponAction au5 = au2;
            l2.bO.a(f12 - l2.cw, f13 - l2.cx, au5.g() - l2.cw, au5.h() - l2.cx, ds);
        }
    }

    public void onBuildingComplete() {
    }


    public void e(float f2) {  // 02b am.java L1047: e(float) 闃熶紞鑹茬幆缁樺埗 (setTeam 涓哄够瑙夊悕)
        boolean bl2 = false;
        if (this.cJ != 0.0f) {
            this.cJ = GameUtils.a(this.cJ, f2);
            if (this.cJ % 15.0f < 7.0f) {
                bl2 = true;
            }
        }
        if (this.cG || bl2) {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            if (this.shouldDrawSelectionRing()) {
                Paint paint;
                float f3 = this.eo - l2.cw;
                float f4 = this.ep - l2.cx - this.eq;
                PlayerState n2 = l2.bs;
                if (n2 == this.player) {
                    paint = this.ck < 8.0f ? cX : shieldBarPaint;
                } else if (this.player.isEnemy(n2)) {  // 02b n.c(n): 闃熶紞涓嶅悓妫€鏌?
                    paint = selectionPaint;
                } else if (this.player != null && l2.cb.j()) {
                    cW.b(PlayerState.i(this.player.r));
                    paint = hpBarPaint;
                } else {
                    paint = rangePaint;
                }
                if (bl2) {
                    paint = waypointPaint;
                }
                if (this.bI()) {
                    Rect rect;
                    if (paint == cY) {
                        paint = buildBarPaint;
                    }
                    if ((rect = this.getTargetBounds()) != null) {
                        dr.a(rect);
                        UnitInstance.dr.b *= (float)l2.bL.tilePixelHeight;
                        UnitInstance.dr.d *= (float)l2.bL.tilePixelHeight;
                        UnitInstance.dr.a *= (float)l2.bL.tilePixelWidth;
                        UnitInstance.dr.c *= (float)l2.bL.tilePixelWidth;
                        float f5 = this.getMinimapDrawRadius();
                        dr.a(-(this.cZ() - (float)l2.bL.selectedTileX), -(this.da() - (float)l2.bL.selectedTileY));
                        GameUtils.a(dr, f5);
                        dr.a(f3, f4);
                        float f6 = 11.0f;
                        l2.bO.a(UnitInstance.dr.a - f6, UnitInstance.dr.b, UnitInstance.dr.c + f6, UnitInstance.dr.b, paint);
                        l2.bO.a(UnitInstance.dr.a - f6, UnitInstance.dr.d, UnitInstance.dr.c + f6, UnitInstance.dr.d, paint);
                        l2.bO.a(UnitInstance.dr.a, UnitInstance.dr.b - f6, UnitInstance.dr.a, UnitInstance.dr.d + f6, paint);
                        l2.bO.a(UnitInstance.dr.c, UnitInstance.dr.b - f6, UnitInstance.dr.c, UnitInstance.dr.d + f6, paint);
                    }
                } else {
                    float f7 = this.ck + l2.bS.r(this);
                    if (l2.a(f3, f4, f7)) {
                        l2.bO.a(f3, f4, f7, paint);
                    }
                }
            }
        }
    }


    public boolean isRenderable(float f2) {
        return true;
    }

    public Rect getRenderBounds() {
        return dw;
    }

    public Rect getHitboxRect() {
        return dw;
    }

    public Rect getTargetBounds() {
        return this.getRenderBounds();
    }

    public Texture v() {
        return null;
    }


    public boolean f(float f2) {  // 02b am.java L1135: f(float) 鍗曚綅绾圭悊缁樺埗 (setTeamRespectNeutral 涓哄够瑙夊悕)
        Texture e2 = this.v();
        if (e2 == null) {
            return false;
        }
        if (this.isDead) {
            return true;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bO.l();
        float f3 = (int)(this.eo - l2.cw);
        float f4 = (int)(this.ep - l2.cx);
        Paint paint = null;
        paint = this.cG ? bJ : bI;
        l2.bO.clearScreen(e2, f3 *= l2.cX, f4 *= l2.cX, paint);
        l2.bO.k();
        l2.R();
        return true;
    }


    public boolean isVisibleTo(GlobalState l2) {
        if (!l2.cO.b(this.eo, this.ep)) {
            return false;
        }
        if (this.cN != null) {
            return false;
        }
        if (this.cP != null && (this.cP.I || this.cP.C)) {
            return false;
        }
        return this.isVisibleTo(l2.bs);
    }

    public boolean isOnRadar() {
        return true;
    }

    public final boolean canMove() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return this.isVisibleTo(l2.bs);
    }

    public boolean isVisibleTo(PlayerState n2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.game.map.MapEngine b2 = l2.bL;
        if ((this.player != n2 || this.cO != null) && b2.tileHeight && n2.N != null) {
            b2.a(this.eo, this.ep);
            int n3 = b2.scrollPixelX;
            int n4 = b2.scrollPixelY;
            if (b2.c(n3, n4) && n2.N[n3][n4] >= 5) {  // 02b b.b.c(II) = isInsideMap
                return false;
            }
        }
        return true;
    }

    public boolean isTargetableForAI() {
        return true;
    }

    public void syncAliveFromRemoved() {
        if (this.isDead) {
            this.bT = false;
            return;
        }
        this.bT = true;
    }


    public void isVisibleTo(float f2) {
        if (!this.isDead) {
            if (this.cw > 0.0f) {
                if (this.cw > this.maxHp * 2.0f) {
                    this.cw = this.maxHp * 2.0f;
                }
                this.cw = GameUtils.a(this.cw, f2);
            }
            if (this.hp < this.maxHp * 0.33f && this.eq > -1.0f) {
                HUDElement e2;
                GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                this.smokeParticleTimer += f2;
                this.dp += f2;
                this.dq += f2;
                if (this.smokeParticleTimer > 10.0f && this.dp < 300.0f && !this.dm()) {
                    this.smokeParticleTimer = 0.0f;
                    if (this.el && l2.dd && (e2 = l2.bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.a)) != null) {
                        com.corrodinggames.rts.gameFramework.effects.DrawEffect.b(e2, true);
                        e2.I = this.eo;
                        e2.J = this.ep;
                        e2.K = this.eq;
                        e2.P += GameUtils.c(-0.1f, 0.1f) + this.cc;
                        e2.Q += GameUtils.c(-0.1f, 0.1f) + this.cd;
                        e2.I += GameUtils.c(-4.0f, 4.0f);
                        e2.J += GameUtils.c(-4.0f, 4.0f);
                    }
                }
                if (this.dq > 30.0f && this.dp < 600.0f && !this.dm()) {
                    this.dq = 0.0f;
                    l2.bR.a();
                    e2 = l2.bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.a);
                    if (e2 != null) {
                        com.corrodinggames.rts.gameFramework.effects.DrawEffect.a(e2, true);
                        e2.I = this.eo;
                        e2.J = this.ep;
                        e2.K = this.eq;
                        e2.P += GameUtils.c(-0.1f, 0.1f);
                        e2.Q += GameUtils.c(-0.1f, 0.1f);
                        e2.I += GameUtils.c(-4.0f, 4.0f);
                        e2.J += GameUtils.c(-4.0f, 4.0f);
                    }
                }
            } else if (this.dp != 0.0f) {
                this.dp = 0.0f;
            }
            if (this.cC != 0.0f) {
                this.cC = GameUtils.a(this.cC, this.maxHp * this.cD * 0.005f * f2);
                this.cD += f2 + 0.2f * this.cD * f2;
                if (this.cC == 0.0f) {
                    this.cD = 0.0f;
                }
            }
            if (this.hp <= 0.0f) {
                this.ch();
            }
        }
    }

    public float setTeamInternal(UnitInstance am2, float f2, MovementController f3) {
        float f4;
        float f5;
        float f6 = f2;
        float f7 = 1.0f;
        float f8 = 1.0f;
        float f9 = 1.0f;
        if (f3 != null) {
            f7 = f3.ak;
            f8 = f3.al;
            f9 = f3.am;
        }
        if (this.cx < this.cA) {
            f5 = this.cA - this.cx;
            f4 = f6 * f7;
            if (f5 > f4) {
                this.cx += f4;
                f6 -= f4 * f8;
            } else {
                this.cx = this.cA;
                f6 -= f4 * f8;
            }
        }
        if (f6 > 0.0f && this.hp < this.maxHp) {
            f4 = this.maxHp - this.hp;
            f5 = f6 * f9;
            if (f4 > f5) {
                this.o(this.hp + f5);
                f6 = 0.0f;
            } else {
                this.o(this.maxHp);
                f6 -= f4;
            }
        }
        return 0.0f;
    }

    public boolean J() {
        return false;
    }

    public float a(UnitInstance am2, float f2, MovementController f3) {  // 02b am.java L1313: a(am,float,f) 鈥?isVisibleTo 涓哄够瑙夊悕 (javap 瀛楄妭鐮侀搧璇?
        float f4;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.cm < 1.0f) {
            f2 *= 1.75f;
        }
        float f5 = 1.0f;
        float f6 = 1.0f;
        float f7 = 1.0f;
        if (f3 != null) {
            f5 = f3.ak;
            f6 = f3.al;
            f7 = f3.am;
        }
        float f8 = f2;
        float f9 = 0.0f;
        if (this.cz == 0.0f && this.cx > 0.0f) {
            f4 = f8 * f5;
            if (this.cx < f4) {
                f8 -= this.cx * f6;
                f9 += this.cx;
                this.cy += this.cx;
                this.cx = 0.0f;
            } else {
                this.cx -= f4;
                this.cy += f4;
                f9 += f4;
                f8 -= f8 * f6;
            }
        }
        if (f8 > 0.0f) {
            f4 = f8 * f7;
            if (this.hp < f4) {
                f8 -= this.hp;
                f9 += this.hp;
                this.o(0.0f);
                this.cC += this.hp;
            } else {
                this.o(this.hp - f4);
                f9 += f4;
                f8 -= f4;
                this.cC -= f4;
            }
        }
        this.bs = l2.by;
        this.bt = am2 != null ? am2 : null;
        this.ch();
        return f8;
    }

    public UnitInstance q(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if ((float)l2.by - f2 * 1000.0f < (float)this.bs) {
            return this.bt;
        }
        return null;
    }

    public void checkAndRemoveIfDead() {
        if (!this.isDead && this.hp <= 0.0f) {
            this.destroyUnit();
        }
    }

    public void n() {
    }

    public boolean setTeam() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        return false;
    }

    public void onUnitKilled() {
    }

    public void deathChain() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bS.l(this);
        PlayerState.a(this);
        if (bE.remove(this)) {
            // empty if block
        }
        this.isDead = true;
        this.bW = l2.by;
        if (this.hp > 0.0f) {
            this.hp = 0.0f;
        }
        if (this.cL != null) {
            int n2 = this.getWeaponMountCount();
            for (int i2 = 0; i2 < n2; ++i2) {
                this.cL[i2].targetUnit = null;
            }
        }
        l2.cc.a(this);
    }

    public void canBuild() {
        this.cN();
        this.isVisibleTo();
        this.bt();
    }

    public void getAttackRange() {
        this.hp = -1.0f;
    }

    public void destroyUnit() {
        this.cN();
        if (!this.setTeam()) {
            this.isVisibleTo();
        }
        this.bt();
    }

    public boolean isVisibleTo(RectF rectF) {
        return this.eo + this.cj > rectF.a && this.eo - this.cj < rectF.c && this.ep + this.cj > rectF.b && this.ep - this.cj < rectF.d;
    }

    public final boolean isRenderable(float f2, float f3, float f4) {
        float f5;
        float f6 = GameUtils.a(this.eo, this.ep, f2, f3);
        return f6 < (f5 = this.cj + f4) * f5;
    }

    public boolean t(UnitInstance am2) {
        float f2;
        float f3 = GameUtils.a(this.eo, this.ep, am2.eo, am2.ep);
        return f3 < (f2 = this.cj + am2.cj) * f2;
    }

    public final void setTeamById(int n2) throws com.corrodinggames.rts.game.map.MapException {
        PlayerState n3 = PlayerState.k(n2);
        if (n3 == null) {
            throw new com.corrodinggames.rts.game.map.MapException("Could not find team with id: " + n2);
        }
        this.setTeam(n3);
    }

    public void setTeam(PlayerState n2) {
        if (this.player == n2) {
            return;
        }
        if (n2 == null) {
            throw new RuntimeException("Could not set team to null");
        }
        if (this.player != null) {
            PlayerState.b(this);
            this.player.d(this);
        }
        this.player = n2;
        PlayerState.c(this);
        if (n2 != PlayerState.i) {
            this.isRenderable(false);
        }
    }

    public void setTeamInternal(PlayerState n2) {
        if (n2 == null) {
            throw new RuntimeException("Could not set team to null");
        }
        this.player = n2;
    }

    public final void setTeamInternalById(int n2) throws com.corrodinggames.rts.game.map.MapException {
        this.player = PlayerState.k(n2);
        if (this.player == null) {
            throw new com.corrodinggames.rts.game.map.MapException("Could not find team with id: " + n2);
        }
    }

    public ArrayList N() {
        return dx;
    }

    public int V() {
        return 1;
    }

    public void isVisibleTo(Position s2, boolean bl2) {
    }

    public void isVisibleTo(Position s2, boolean bl2, PointF pointF, UnitInstance am2) {
        this.isVisibleTo(s2, bl2);
    }

    public void setTeamInternal(Position s2, boolean bl2) {
    }

    public void isVisibleTo(Position s2) {
    }

    public ActionId cm() {
        // 02b am.java L1541-1543: cm() 返回默认动作 ID (a.s.i = GameAction.i)
        return GameAction.i;
    }

    public GameAction a(ActionId c2) {
        ArrayList arrayList = this.N();
        int n2 = arrayList.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            GameAction s2 = (GameAction) arrayList.get(i2);
            if (!s2.getDisplayString(c2)) continue;
            return s2;
        }
        return null;
    }

    public boolean getMoveSpeed() {  // 02b am.ck(): bI() && GameAction.getResourceCost(cm())
        if (this.bI()) {
            return com.corrodinggames.rts.game.units.actions.GameAction.getResourceCost(this.getDefaultActionType());
        }
        return false;
    }

    public boolean isIdle() {
        return false;
    }

    public ActionId getDefaultActionType() {
        return ActionId.a;
    }

    public float getActionCooldownTime() {
        return -1.0f;
    }

    public boolean isActionAvailable() {
        return false;
    }

    public void isVisibleTo(ArrayList arrayList) {
        arrayList.clear();
    }

    public ActionId applyDamage() {
        return ActionId.a;
    }

    public Position setTeam(UnitTypeHandle as2) {
        return null;
    }

    public final int countUsableActions() {  // 02b am.cq(): 閬嶅巻 N() GameAction 鍒楄〃
        int n2 = 0;
        for (com.corrodinggames.rts.game.units.actions.GameAction s2 : (java.util.Collection<com.corrodinggames.rts.game.units.actions.GameAction>) (java.util.Collection) this.N()) {
            if (!s2.b(this) && !s2.s()) continue;  // 02b a/s.b(am)/a/s.s()
            ++n2;
        }
        return n2;
    }

    public boolean isRenderable(UnitInstance am2, boolean bl2) {
        UnitInstance am3 = am2.cN;
        UnitType y2 = am2.cO;
        am2.cN = null;
        am2.cO = null;
        boolean bl3 = this.isVisibleToTeam(am2, bl2);
        am2.cN = am3;
        am2.cO = y2;
        return bl3;
    }

    public boolean isVisibleToTeam(UnitInstance am2, boolean bl2) {
        return false;
    }

    public boolean setTeam(UnitInstance am2, boolean bl2) {
        return false;
    }

    public boolean canFireAtAirTargets() {
        return false;
    }

    public abstract MovementTypeEnum h();

    public abstract boolean i();

    public boolean checkIsLargeUnit() {
        return this.i();
    }


    public abstract boolean aj();

    public abstract boolean ak();

    public boolean isRepairable() {
        return false;
    }

    public boolean isReclaimable() {
        return false;
    }

    public boolean setTeamById() {
        return false;
    }

    public int getMaxUnitGroupSize() {
        return 1;
    }

    public abstract boolean isOnScreen();

    public int isDamaged() {
        return 85;
    }

    public float setTeamRespectNeutral(UnitTypeHandle as2) {
        int n2 = as2.a(this) + this.isDamaged();
        return n2;
    }

    public boolean getHealthPercent(UnitInstance am2) {
        return false;
    }

    public float setTeamInternal(UnitInstance am2) {
        return 1.0f;
    }

    public float isRenderable(UnitInstance am2) {
        return 0.2f;
    }

    public boolean isDamaged(UnitInstance am2) {
        boolean bl2;
        boolean bl3 = false;
        boolean bl4 = bl2 = am2.bd() > 0.0f;
        if (bl2) {
            bl3 = true;
        }
        return bl3;
    }

    public float z(UnitInstance am2) {
        boolean bl2;
        float f2 = 5.1f;
        float f3 = this.isRenderable(am2) * f2;
        boolean bl3 = bl2 = am2.bd() > 0.0f;
        if (bl2) {
            f3 = am2.bd();
        }
        return f3;
    }

    public float isIdle2() {
        return 1.0f;
    }

    public float getSpeedMultiplier() {
        return 0.0f;
    }

    public EffectManager getShieldStatModifiers() {
        float f2 = this.getSpeedMultiplier();
        if (f2 == 0.0f) {
            return EffectManager.a;
        }
        EffectManager f3 = new EffectManager();
        f3.do_b(com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.D, f2);  // 02b e/f.b(e.a,D)
        return f3;
    }

    public EffectManager getDefaultStatModifiers() {
        return EffectManager.a;  // 02b e/f.a 闈欐€?
    }

    public abstract UnitTypeHandle r();

    public String toShortDebugString() {
        return this.r().i() + "(id:" + this.eh + ")";
    }

    public static String setTeamRespectNeutral(UnitInstance am2, boolean bl2) {
        if (am2 != null) {
            return am2.r().e();
        }
        return "No unit";
    }

    public static strictfp String f(UnitInstance am2, boolean bl) {  // 02b am.java L1721-1723
        return am2 != null ? am2.r().e() : "No unit";
    }

    public static String A(UnitInstance am2) {
        if (am2 != null) {
            return am2.c();
        }
        return "<null unit>";
    }

    public String isRenderable() {
        String string = this.r().i() + "(pos:" + (int)this.eo + "," + (int)this.ep + " id:" + this.eh + "";
        if (this.player != null) {
            string = string + " t:" + this.player.k;
        }
        if (this.isDead) {
            string = string + " [dead]";
        }
        if (this.ej) {
            string = string + " [deleted]";
        }
        string = string + ")";
        return string;
    }

    public String toFullDebugString() {
        String string = this.r().i() + "(pos:" + (int)this.eo + "," + (int)this.ep + " id:" + this.eh + "";
        string = string + ", hp:" + this.hp + ", dead:" + this.isDead + ", deleted:" + this.ej + " tags:" + this.getStatusEffects();
        if (this.player != null) {
            string = string + " t:" + this.player.k;
        }
        string = string + ")";
        return string;
    }

    public float getFireCooldownMultiplier() {
        return 1.0f;
    }

    public RectF getAnimatedScreenBounds() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f2 = this.getFireCooldownMultiplier();
        dA.a(this.eo - this.eu * f2 - l2.cw, this.ep - this.ev * f2 - l2.cx, this.eo + this.eu * f2 - l2.cw, this.ep + this.ev * f2 - l2.cx);
        return dA;
    }

    public RectF getStaticScreenBounds() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        RectF rectF = animatedScreenBounds;
        float f2 = l2.cw;
        float f3 = l2.cx;
        float f4 = this.eu;
        float f5 = this.ev;
        rectF.a = this.eo - f4 - f2;
        rectF.c = this.eo + f4 - f2;
        rectF.b = this.ep - f5 - f3;
        rectF.d = this.ep + f5 - f3;
        return rectF;
    }

    public boolean isDamageImmune() {
        return false;
    }

    public Rect getAnimationFrameRectFull(boolean bl2) {
        int n2 = 0;
        int n3 = 0;
        UnitInstance.dC.a = n2;
        UnitInstance.dC.b = n3;
        UnitInstance.dC.c = n2 + this.es;
        UnitInstance.dC.d = n3 + this.et;
        return dC;
    }

    public Rect isVisibleTo(boolean bl2, int n2) {
        int n3 = 0;
        int n4 = 0;
        dC.a(n3 += n2 * this.es, n4, n3 + this.es, n4 + this.et);
        return dC;
    }

    public Rect isVisibleTo(boolean bl2, int n2, int n3) {
        int n4 = this.es;
        int n5 = this.et;
        int n6 = n2 * n4;
        int n7 = n3 * n5;
        UnitInstance.dC.a = n6;
        UnitInstance.dC.b = n7;
        UnitInstance.dC.c = n6 + n4;
        UnitInstance.dC.d = n7 + n5;
        return dC;
    }

    public boolean isVisibleTo(UnitInstance am2, float f2) {
        return false;
    }

    public void getAnimationFrameRectFull(String string) {
    }

    public boolean isLeftOfMap() {
        return PathfindingUtils.b(this.eo, this.ep);
    }

    public boolean isAboveMap() {
        return PathfindingUtils.c(this.eo, this.ep);
    }

    public boolean isOffMapCorner() {
        return PathfindingUtils.d(this.eo, this.ep);
    }

    public int computeUnitHash() {
        int n2 = 0;
        n2 = n2 * 31 + (int)this.bN();
        n2 = n2 * 31 + (int)this.maxHp;
        return n2;
    }

    public int getResourceCostValue() {
        return this.r().b(this.V());
    }

    public CustomActionBase getResourceProduction() {  // 02b am.cM() 杩斿洖 d/b (璇箟寰呭畾, v19.133d 鍥炴粴)
        return this.r().d(this.V());
    }

    public CustomActionBase getCustomResourceOverride() {  // 02b 璇箟: 璧勬簮瑕嗙洊 (v19.133d 鍥炴粴)
        return null;
    }

    public PointF isVisibleTo(float f2, float f3, float f4, float f5, float f6) {
        float f7 = 0.0f;
        if ((double)f4 > 0.1 && this.cK) {
            float f8 = 1.0f / f4;
            for (int i2 = 0; i2 < 3; ++i2) {
                PointF pointF = this.getPredictedPosition(f7);
                float f9 = GameUtils.b(f2, f3, pointF.a, pointF.b);
                f7 = f9 * f8;
            }
        }
        if (f7 > f5) {
            f7 = f5;
        }
        PointF pointF = this.getPredictedPosition(f7);
        float f10 = GameUtils.a(f2, f3, pointF.a, pointF.b);
        if (f6 >= 0.0f && f6 * f6 < f10) {
            float f11 = com.corrodinggames.rts.gameFramework.GameUtils.d(f2, f3, pointF.a, pointF.b);
            pointF.a = f2 + GameUtils.cosFast(f11) * f6;
            pointF.b = f3 + GameUtils.sinFast(f11) * f6;
        }
        dD.a(pointF);
        return dD;
    }

    public PointF a(float f2, float f3, float f4, float f5, float f6) {  // 02b am.java L1858: 棰勬祴浣嶇疆+鎷︽埅淇
        float f7 = 0.0f;
        if ((double) f4 > 0.1 && this.cK) {
            float f8 = 1.0f / f4;
            for (int i = 0; i < 3; ++i) {
                PointF pointF = this.getPredictedPosition(f7);
                float f9 = GameUtils.b(f2, f3, pointF.a, pointF.b);
                f7 = f9 * f8;
            }
        }
        if (f7 > f5) {
            f7 = f5;
        }
        PointF pointF2 = this.getPredictedPosition(f7);
        float f10 = GameUtils.a(f2, f3, pointF2.a, pointF2.b);
        if (f6 >= 0.0f && f6 * f6 < f10) {
            float f11 = GameUtils.d(f2, f3, pointF2.a, pointF2.b);
            pointF2.a = f2 + GameUtils.k(f11) * f6;
            pointF2.b = f3 + GameUtils.j(f11) * f6;
        }
        dD.a(pointF2);
        return dD;
    }

    PointF getPredictedPosition(float f2) {
        dE.a(this.eo + this.cc * f2, this.ep + this.cd * f2);
        return dE;
    }


    public boolean o() {
        return false;
    }

    public boolean p() {
        return false;
    }

    public boolean isAirUnit() {
        return false;
    }

    public void setTeamRespectNeutral(PlayerState n2) {
        if (this.p()) {
            this.setTeamInternal(PlayerState.i);
            return;
        }
        this.setTeamInternal(n2);
    }

    public void accept_B(UnitInstance am2) {
        if (am2 instanceof Factory) {
            am2 = null;
        }
        this.cN = am2;
    }

    public boolean onCloakStateChanged() {  // 02b am.cH() L1823-1825
        return this.cJ() && this.eq <= 2.0f;
    }

    public float getResourceProductionRate() {
        return 0.0f;
    }

    public int getMaxFactoryQueueLength() {
        return Integer.MAX_VALUE;
    }

    public UnitConfig getCustomEffectHandler() {
        return null;
    }

    public boolean setDiscoveredBy(UnitInstance am2, boolean bl2) {
        return false;
    }

    public boolean h(UnitInstance am2, boolean bl2) {
        return this.setDiscoveredBy(am2, bl2);
    }

    public int getSightRadius() {
        return 500;
    }

    public boolean isRenderable(UnitType y2) {
        int n2;
        int n3 = this.getMaxFactoryQueueLength();
        return n3 < Integer.MAX_VALUE && (n2 = this.isVisibleToTeam(y2)) >= n3;
    }

    public int isVisibleToTeam(UnitType y2) {
        int n2 = 0;
        PlayerState n3 = y2.player;
        UnitInstance[] amArray = bE.a();
        int n4 = bE.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            UnitType y3;
            WeaponAction au2;
            UnitInstance am2 = amArray[i2];
            if (am2.player != n3 || !(am2 instanceof UnitType) || (au2 = (y3 = (UnitType) am2).ar()) == null || au2.d() != WeaponTypeEnum.g || au2.h != this || am2 == y2) continue;
            ++n2;
        }
        return n2;
    }

    public int setTeam(UnitType y2) {
        int n2 = 0;
        PlayerState n3 = y2.player;
        UnitInstance[] amArray = bE.a();
        int n4 = bE.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            UnitType y3;
            WeaponAction au2;
            UnitInstance am2 = amArray[i2];
            if (am2.player != n3 || !(am2 instanceof UnitType) || (au2 = (y3 = (UnitType) am2).ar()) == null || au2.d() != WeaponTypeEnum.d || au2.h != this || am2 == y2) continue;
            ++n2;
        }
        return n2;
    }

    public int getWeaponMountCount() {
        return 1;
    }

    public boolean u() {
        return false;
    }

    public boolean isCapturable() {
        return this.u() || this.cm < 1.0f || this.player == PlayerState.h;
    }

    public boolean bJ() {  // 02b am.bJ(): 鏄惁鐗规畩鏀诲嚮鍗曚綅
        return false;
    }

    public void cj() {  // 02b am.cj(): 绉婚櫎鍓嶆爣璁?
        this.cu = -1.0f;
    }

    public boolean isNotCapturable() {
        return !this.u();
    }

    public boolean t() {
        return false;
    }

    public boolean isAutoRepairActive() {
        return this.t();
    }

    public boolean isAutoReclaimActive() {
        return false;
    }

    public boolean isVisibleToTeam(UnitInstance am2) {
        return true;
    }

    public void setDiscoveredBy(PlayerState n2) {
        if (this.playerIndicators == null || this.playerIndicators.length != PlayerState.c) {
            this.playerIndicators = new PlayerUnitIndicator[PlayerState.c];
            for (int i2 = 0; i2 < this.playerIndicators.length; ++i2) {
                this.playerIndicators[i2] = new PlayerUnitIndicator();
            }
        }
        PlayerUnitIndicator an2 = this.playerIndicators[n2.k];
        if (this.isDead) {
            boolean bl2;
            if (an2.discovered && (bl2 = this.isVisibleTo(n2))) {
                an2.discovered = false;
            }
        } else {
            boolean bl3 = this.isVisibleTo(n2);
            if (bl3) {
                an2.discovered = true;
                an2.typeValue = this.V();
            }
        }
    }

    public void updateMinimapIndicator() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bs != null && this.player != l2.bs && l2.bs.k >= 0 && l2.bs.k < PlayerState.c) {
            boolean bl2;
            PlayerUnitIndicator an2 = this.playerIndicators[l2.bs.k];
            if (an2.indicator != null && an2.indicator.c) {
                an2.indicator = null;
            }
            if (an2.indicator == null && an2.discovered && !(bl2 = this.isVisibleTo(l2.bs))) {
            }
        }
    }

    public PointF getEffectsRenderOffset() {
        dG.a(0.0f, 0.0f);
        return dG;
    }

    public float getMapOriginX() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bL.selectedTileX;
    }

    public float getMapOriginY() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bL.selectedTileY;
    }

    public float getMinimapDrawRadius() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return (float)(l2.bL.selectedTileX + 2) + l2.bS.r(this);
    }

    public Point isVisibleTo(com.corrodinggames.rts.game.map.MapEngine b2, Point point) {
        point.a = (int)((this.eo - this.cZ() + 1.0f) * b2.float1);
        point.b = (int)((this.ep - this.da() + 1.0f) * b2.float2);
        return point;
    }

    public RectF isVisibleTo(com.corrodinggames.rts.game.map.MapEngine b2, RectF rectF) {
        int n2 = (int)((this.eo - this.cZ() + 1.0f) * b2.float1);
        int n3 = (int)((this.ep - this.da() + 1.0f) * b2.float2);
        b2.a(n2, n3);
        float f2 = b2.scrollPixelX;
        float f3 = b2.scrollPixelY;
        Rect rect = this.getHitboxRect();
        rectF.a(f2 + (float)(rect.a * b2.tilePixelWidth), f3 + (float)(rect.b * b2.tilePixelHeight), f2 + (float)((rect.c + 1) * b2.tilePixelWidth), f3 + (float)((rect.d + 1) * b2.tilePixelHeight));
        return rectF;
    }

    public void onUnitDeployed() {
    }

    public boolean updateMapVisibility() {
        return false;
    }

    public boolean q() {
        return false;
    }

    public com.corrodinggames.rts.game.units.custom.UnitConfig getStatusEffects() {  // 02 閾佽瘉: am.de():custom.h
        return null;
    }

    // v19.112 琛ュ叏 (javap 閾佽瘉: am.a(af)/a(af,am) final 濮旀墭 + a(af,am,h,VariableScope) 绌哄疄鐜?
    public final void a(af var1) {
        this.a(var1, (UnitInstance)null, (UnitConfig)null, (VariableScope)null);
    }

    public final void a(af var1, UnitInstance var2) {
        this.a(var1, var2, (UnitConfig)null, (VariableScope)null);
    }

    public void a(af var1, UnitInstance var2, UnitConfig var3, VariableScope var4) {
    }

    // v19.112c 琛ュ叏 (02b 鏂规硶浣撻搧璇? am.c() 鎻忚堪瀛楃涓?/ am.c(float)=true / am.d(float)=绌?
    public String c() {
        String string = this.r().i() + "(pos:" + (int)this.eo + "," + (int)this.ep + " id:" + this.eh + "";
        if (this.player != null) {
            string = string + " t:" + this.player.k;
        }
        if (this.isDead) {
            string = string + " [dead]";
        }
        if (this.ej) {
            string = string + " [deleted]";
        }
        return string + ")";
    }

    public boolean c(float var1) {
        return true;
    }

    // v19.112c 琛ュ叏 (02b 閾佽瘉: am.f(float,float)=鍧愭爣璁剧疆+c(true); am.c(boolean)=绌?
    public void f(float var1, float var2) {
        this.eo = var1;
        this.ep = var2;
        this.c(true);
    }

    public void c(boolean var1) {
    }

    public void d(float var1) {
    }

    // v19.112 琛ュ叏 (02b 鏂规硶浣撻搧璇? am.cJ/cB/cK 杩斿洖 PathfindingUtils c/d 鏌ヨ)
    public final strictfp boolean cH() {  // 02b am.cH()
        return this.cJ() && this.eq <= 2.0f;
    }

    public boolean cJ() {
        return com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.c(this.eo, this.ep);
    }

    public String cB() {
        return this.r().i() + "(id:" + this.eh + ")";
    }

    public boolean cK() {
        return com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.d(this.eo, this.ep);
    }

    public strictfp boolean d(PlayerState n2) {  // 02b am.d(n): 闆炬皵鍙鎬ф鏌?
        GlobalState l2 = GlobalState.B();
        MapEngine mapEngine = l2.bL;
        if ((this.player != n2 || this.cO != null) && mapEngine.fogGrid && n2.N != null) {
            mapEngine.a(this.eo, this.ep);
            int n3 = mapEngine.selectedTileX;
            int n4 = mapEngine.selectedTileY;
            if (mapEngine.c(n3, n4) && n2.N[n3][n4] >= 5) {
                return false;
            }
        }
        return true;
    }

    public strictfp boolean e() {  // 02b am.e(): 娓叉煋瓒宠抗鍦?
        GlobalState l2 = GlobalState.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        return false;
    }

    public strictfp int bl() {  // 02b am.bl(): 鐐鎸傝浇鏁?(UnitType 瑕嗙洊涓?cL.length)
        return 1;
    }

    public strictfp void bu() {  // 02b am.bu(): 鍗曚綅绉婚櫎娓呯悊
        GlobalState l2 = GlobalState.B();
        l2.bS.l(this);
        PlayerState.a(this);
        bE.remove(this);
        this.isDead = true;
        this.bW = (long)l2.by;
        if (this.cu > 0.0f) {
            this.cu = 0.0f;
        }
        if (this.cL != null) {
            int n2 = this.bl();
            for (int i2 = 0; i2 < n2; ++i2) {
                this.cL[i2].targetUnit = null;  // 02b ap.j = am
            }
        }
        l2.cc.a(this);
    }

    public strictfp void bv() {  // 02b am.bv(): 褰诲簳绉婚櫎
        this.bu();
        if (!this.e()) {
            this.isVisibleTo();
        }
        this.bt();
    }

    public void bt() {
    }

    public EffectManager getStatsCollection() {
        return this.dH;
    }

    public EffectManager df() {
        return this.dH;
    }

    public double isVisibleTo(LogicBoolean a2) {  // 02b am: dH.a(e.a) = EffectManager.a(LogicBoolean)
        return this.dH.a(a2);
    }

    public RectF a(MapEngine mapEngine, RectF rectF) {  // 02b am.a(b,RectF): 钃濆浘鐭╁舰瀵归綈 (鍦板浘鍧愭爣鈫掑睆骞曞潗鏍?
        int n2 = (int)((this.eo - this.getMapOriginX() + 1.0f) * mapEngine.float1);
        int n3 = (int)((this.ep - this.getMapOriginY() + 1.0f) * mapEngine.float2);
        mapEngine.a(n2, n3);
        float f2 = (float)mapEngine.scrollPixelX;
        float f3 = (float)mapEngine.scrollPixelY;
        Rect rect = this.getHitboxRect();
        rectF.a(f2 + (float)(rect.a * mapEngine.tilePixelWidth), f3 + (float)(rect.b * mapEngine.tilePixelHeight), f2 + (float)((rect.c + 1) * mapEngine.tilePixelWidth), f3 + (float)((rect.d + 1) * mapEngine.tilePixelHeight));
        return rectF;
    }

    public com.corrodinggames.rts.game.units.custom.conditions.c getCustomUnitData() {
        return this.dI;
    }

    public com.corrodinggames.rts.game.units.custom.UnitConfig getCustomStatusHandler() {  // 02b units/am dh() 杩斿洖 h (v19.133f5 TagFilter 骞昏淇)
        return null;
    }

    public float bd() {
        return 0.0f;
    }

    public void onStatusEffectApplied() {
    }

    public void onStatusEffectRemoved() {
    }

    public boolean hasActiveStatusEffect() {
        return false;
    }

    public boolean isSpawnPointA() {
        return this.bO();
    }

    public boolean isSpawnPointA_alias() {
        return this.bO();
    }

    public final UnitTrait dn() {
        return this.cP;
    }

    public String toString() {
        return "unit(id=" + this.eh + ",type=" + this.r().i() + ")";
    }

    public void r(float f2) {
        if (f2 >= 1.0f) {
            boolean bl2;
            boolean bl3 = bl2 = this.cm >= 1.0f;
            if (!bl2) {
                PlayerState.b(this);
                this.cm = 1.0f;
                PlayerState.c(this);
            }
        } else {
            boolean bl4;
            boolean bl5 = bl4 = this.cm >= 1.0f;
            if (bl4) {
                PlayerState.b(this);
                this.cm = f2;
                PlayerState.c(this);
            } else {
                this.cm = f2;
            }
        }
    }

    public final void isVisibleTo(UnitTransform af2) {
        this.isVisibleTo(af2, null, null, null);
    }

    public final void isVisibleTo(UnitTransform af2, UnitInstance am2) {
        this.isVisibleTo(af2, am2, null, null);
    }

    public void isVisibleTo(UnitTransform af2, UnitInstance am2, TagFilter h2, VariableScope variableScope) {
    }

    public void h(float f2) {
        this.cg = f2;
    }

    public int isVisibleTo(ExperimentalLandUnit g2) {
        return 0;
    }

    public SubmarineUnit setTeam(boolean bl2) {
        return null;
    }

    public CustomArrayList e(boolean bl2) {
        return null;
    }

    public boolean isVisibleTo(int n2, int n3) {
        return false;
    }

    public void isRenderable(boolean bl2) {
    }

    public float getCollisionRadius() {
        return this.cj;
    }

    public boolean shouldDrawSelectionRing() {
        return true;
    }

    public void onFactoryDeploy() {
    }

    public final CustomActionBase getCustomResourceHolder() {
        return this.dJ;
    }

    public final UnitInstance getActiveBuildOrTarget() {
        UnitInstance am2 = this.cO;
        if (am2 == null && this.cN != null) {
            am2 = this.cN;
        }
        return am2;
    }

    public void setTeamRespectNeutral(float f2, float f3) {
        this.eo = f2;
        this.ep = f3;
        this.isRenderable(true);
    }
    static UniquePaint minimapWeaponIconPaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static Paint hpBarPaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static UniquePaint hpBgPaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static UniquePaint shieldBarPaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static UniquePaint buildBarPaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static UniquePaint selectionPaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static UniquePaint waypointPaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static UniquePaint rangePaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static Paint repairRangePaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static Paint resourcePaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static Paint energyBarPaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static UniquePaint boundsGrayPaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static UniquePaint boundsGrayThickPaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static UniquePaint boundsRedPaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static UniquePaint boundsStrokePaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static Paint boundsStrokePaint2;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static RectF hpBarRect;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static Paint waypointLinePaint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static Paint paint16;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static RectF renderSourceRect;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static Rect renderDestRect;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static Rect renderBoundsCache;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static RectF animatedScreenBounds;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static Rect textureSrcRect;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static PointF movementTargetPoint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static PointF interpolationTargetPoint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃
    static PointF effectsOffsetPoint;  // v19.113o auto_align R3a: 闈欐€佸潡璧嬪€兼棤澹版槑琛ユ彃

    static {
        bI.a(true);
        bI.a(255, 195, 195, 195);
        minimapWeaponIconPaint = new UniquePaint();
        bJ = new UniquePaint();  // 02b am.java L2262
        bJ.a(true);
        bK = new LightingColorFilter(Color.a(255, 255, 255), Color.a(100, 100, 100));
        bJ.a(255, 255, 255, 255);
        bJ.a(bK);
        hpBarPaint = new Paint();
        hpBgPaint = new UniquePaint();
        shieldBarPaint = new UniquePaint();
        buildBarPaint = new UniquePaint();
        selectionPaint = new UniquePaint();
        waypointPaint = new UniquePaint();
        rangePaint = new UniquePaint();
        repairRangePaint = new Paint();
        resourcePaint = new Paint();
        energyBarPaint = new Paint();
        boundsGrayPaint = new UniquePaint();
        boundsGrayThickPaint = new UniquePaint();
        boundsRedPaint = new UniquePaint();
        boundsStrokePaint = new UniquePaint();
        boundsStrokePaint2 = new Paint();
        cW = new Paint();  // 02b am.java L2267
        cX = new UniquePaint();  // 02b am.java L2268
        cY = new UniquePaint();  // 02b am.java L2269
        cZ = new UniquePaint();  // 02b am.java L2270
        da = new UniquePaint();  // 02b am.java L2271
        db = new UniquePaint();  // 02b am.java L2272
        dc = new UniquePaint();  // 02b am.java L2273
        dd = new Paint();  // 02b am.java L2274
        de = new Paint();  // 02b am.java L2275
        df = new Paint();  // 02b am.java L2276
        dg = new UniquePaint();  // 02b am.java L2277
        dh = new UniquePaint();  // 02b am.java L2278
        di = new UniquePaint();  // 02b am.java L2279
        dj = new UniquePaint();  // 02b am.java L2280
        dk = new Paint();  // 02b am.java L2281
        cW.a(Paint$Style.b);
        cW.a(2.0f);
        UnitInstance.a(cW);
        cX.a(180, 0, 255, 0);
        cX.a(Paint$Style.b);
        cX.a(2.0f);
        UnitInstance.a(cX, true);
        cY.a(180, 0, 255, 0);
        cY.a(Paint$Style.b);
        cY.a(2.0f);
        UnitInstance.a(cY);
        cZ.a(130, 0, 255, 0);
        cZ.a(Paint$Style.b);
        cZ.a(2.0f);
        UnitInstance.a(cZ);
        dd.a(70, 0, 255, 0);
        dd.a(Paint$Style.b);
        dd.a(1.0f);
        UnitInstance.a(dd);
        da.a(180, 255, 0, 0);
        da.a(Paint$Style.b);
        da.a(2.0f);
        UnitInstance.a(da);
        de.a(70, 255, 0, 0);
        de.a(Paint$Style.b);
        de.a(1.0f);
        UnitInstance.a(de);
        dc.a(180, 255, 255, 0);
        dc.a(Paint$Style.b);
        dc.a(2.0f);
        UnitInstance.a(dc);
        df.a(70, 255, 255, 0);
        df.a(Paint$Style.b);
        df.a(1.0f);
        UnitInstance.a(df);
        db.a(180, 255, 255, 255);
        db.a(Paint$Style.b);
        db.a(2.0f);
        UnitInstance.a(db);
        dg.a(90, 235, 235, 235);
        dg.a(Paint$Style.b);
        dg.a(1.0f);
        UnitInstance.a(dg);
        dh.a(100, 235, 235, 235);
        dh.a(Paint$Style.b);
        dh.a(2.0f);
        UnitInstance.a(dh);
        di.a(90, 235, 0, 0);
        di.a(Paint$Style.b);
        di.a(1.0f);
        UnitInstance.a(di);
        dj.a(Paint$Style.b);
        dk.a(Paint$Style.b);
        dr = new RectF();  // 02b am.java L2335 (hpBarRect 为语义名, dr 引用残留)
        hpBarRect = new RectF();
        waypointLinePaint = new Paint();
        paint16 = new Paint();
        du = new RectF();  // 02b am.java L2338 (renderSourceRect 为语义名, du 残留)
        renderSourceRect = new RectF();
        dv = new Rect();  // 02b am.java L2339
        renderDestRect = new Rect();
        dw = new Rect();  // 02b am.java L2340
        renderBoundsCache = new Rect();
        dx = new ArrayList();  // 02b dx
        dy = new ArrayList();  // 02b dy
        dA = new RectF();  // 02b am.java L2343
        animatedScreenBounds = new RectF();
        rectF15 = new RectF();
        dC = new Rect();  // 02b am.java L2345
        textureSrcRect = new Rect();
        dD = new PointF();  // 02b am.java L2346
        movementTargetPoint = new PointF();
        dE = new PointF();  // 02b am.java L2347
        interpolationTargetPoint = new PointF();
        dG = new PointF();  // 02b am.java L2348
        effectsOffsetPoint = new PointF();
    }


   // 02b am.cW() L2023: return false; (Command L346/598 UnitType.cW 璋冪敤, 02b 鏂规硶鍦?am 鐖剁被)
   public boolean cW() {
      return false;
   }

   // 02b am.b(s,Z) javap 瀛楄妭鐮? return; 绌哄疄鐜?(Command L468 var2.b(a(k), g) 璋冪敤)
   public void b(com.corrodinggames.rts.game.units.actions.GameAction var1, boolean var2) {
   }

   // 02b am.B(am) javap 瀛楄妭鐮? instanceof units.h 鍒嗘敮 + this.bu=var1 (03 bu 瀛楁 L85; h 鍒嗘敮绠€鍖?
   public void B(com.corrodinggames.rts.game.units.UnitInstance var1) {
      this.bu = var1;
   }


   // 02b am.de() L2132-2134: 杩斿洖鍗曚綅鏍囩闆?(03 杩斿洖 null, 02b 绌哄疄鐜?
   public com.corrodinggames.rts.game.units.custom.UnitConfig de() {
      return null;
   }



   // 02b am.e(n) L1464-1479 绠€鍖栫洿璇? 璁剧疆闃熶紞 (渚濊禆 n.b/n.c 03 寰呮垬褰? 淇濈暀鏍稿績)
   public void e(com.corrodinggames.rts.game.PlayerState var1) {
      if(this.player != var1) {
         if(var1 == null) {
            throw new RuntimeException("Could not set team to null");
         } else {
            if(this.player != null) {
               this.player.d(this);
            }
            this.b(var1);
            if(var1 != com.corrodinggames.rts.game.PlayerState.i) {
               this.c(false);
            }
         }
      }
   }

   // 02b am.i(boolean) 绠€鍖? 娓呯┖闃熷垪 (瀹屾暣浣撳緟 UnitInstance 鎴樺焦)
   public void i(boolean var1) {
   }

    public boolean c(UnitInstance am2, boolean bl2) {
        // v19.115p 鎵? 琛ョ己: 02b am.c(am,Z) 瀛楄妭鐮侀搧璇?(bp.java 杩愯緭杞Щ鍏煎妫€鏌? 鈥?绠€鍖?TODO
        return false;
    }

    // ===== v19.115r logicBooleans 鎵? 琛ョ己: javap am.class 閾佽瘉 =====
    public static final com.corrodinggames.rts.gameFramework.utility.UnitInstanceList bE = new com.corrodinggames.rts.gameFramework.utility.UnitInstanceList();  // 02b: public static final utility.u bE 鍗曚綅娉ㄥ唽琛?
    public UnitInstance dr() {
        // 02b am.dr() (javap: public final strictfp am dr()) 鈥?鐖跺崟浣嶅紩鐢?鈥?绠€鍖?TODO
        return null;
    }
    public static UnitInstance c(UnitTypeHandle as2) {
        // 02b am.c(as) 闈欐€?(UnitReferenceOrUnitType 璋冪敤鐐? 鈥?绠€鍖?TODO
        return null;
    }

    public int cL() {  // 02b am.java L1846-1848: 鍗曚綅浠锋牸/淇＄敤鐐?(PriceCreditsBoolean 娌跨户鎵块摼璋冪敤; BuildActionSlot.cL() 鍚岀鍚嶈鐩?
        return this.r().b(this.V());
    }

    public float cx() {  // 02b am.java L1692-1694: 寤洪€犲€嶇巼
        return 1.0f;
    }


    public final void Q(int n2) throws com.corrodinggames.rts.game.map.MapException {  // 02b am.java L1492-1499: 璁剧疆闃熶紞 (ExperimentalLandUnit.w 璋冪敤)
        this.player = PlayerState.k(n2);
        if (this.player == null) {
            throw new com.corrodinggames.rts.game.map.MapException("Could not find team with id: " + n2);
        } else {
            this.b(this.player);
        }
    }

    public int a(TeamTag g2) {  // 02b am.java L2216-2218: 闃熷垪澶у皬 (QueueSize 璋冪敤; MobileBuilderBase 瑕嗙洊)
        return 0;
    }

    // v19.115t 鎵? 琛ョ己: 02b am.e(as) javap 閾佽瘉 (public strictfp units.a.s e(units.as) L1561) 鈥?绠€鍖栬繑鍥?null
    public GameAction e(UnitTypeHandle as2) {
        return null;
    }
    // v19.115t 鎵? 琛ョ己: javap am.b(as) 闈欐€侀搧璇?(public static strictfp am b(as)) 鈥?绠€鍖栬繑鍥?null
    public static UnitInstance b(UnitTypeHandle as2) {
        return null;
    }

    // v19.115t 鎵? 琛ョ己: javap am.g(am,boolean) 閾佽瘉 鈥?绠€鍖栬繑鍥?false
    public boolean g(UnitInstance am2, boolean bl2) {
        return false;
    }

    // v19.115t 鎵? 琛ョ己: javap am.e(y) int 閾佽瘉 鈥?绠€鍖栬繑鍥?0
    public int e(UnitType y2) {
        return 0;
    }

    public void a_(String string) {  // 02b am.a_(String) L1821: 绌哄疄鐜?(鐐绫诲瀷鏍囪)
    }


    public static Texture a(Texture texture) {  // 02b am.java L455-457: a(m.e) 1参 = a(m(),l()) 阴影纹理 (ExtractorBuilding 链)
        return a(texture, texture.m(), texture.l());
    }

    public static com.corrodinggames.rts.gameFramework.rendering.Texture a(com.corrodinggames.rts.gameFramework.rendering.Texture texture, int n2, int n3) {  // 02b am.a(m.e,int,int) L459-479: 闃村奖绾圭悊缂╂斁
        com.corrodinggames.rts.gameFramework.rendering.Texture texture2 = texture.a(n2, n3, false);
        texture.x();
        texture2.j();
        int n4 = texture2.m();
        int n5 = texture2.l();
        for (int i2 = 0; i2 < n4; ++i2) {
            for (int i3 = 0; i3 < n5; ++i3) {
                int n6 = texture.a(i2, i3);
                texture2.a(i2, i3, android.graphics.Color.a(android.graphics.Color.a(n6), 0, 0, 0));
            }
        }
        texture2.p();
        texture2.s();
        texture.y();
        texture2.a("shadow:" + texture.a());
        texture2.n = true;
        return texture2;
    }


    public static void bH() {  // 02b am.bH() L490-500+: 鍔犺浇鍗曚綅闈欐€佽祫婧?
        com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase.dt();
        com.corrodinggames.rts.game.units.commands.BuildSlot.dt();
        com.corrodinggames.rts.game.units.projectiles.AbstractSubBuilding.K();
        com.corrodinggames.rts.game.units.debug.FactoryAction6.M();
        com.corrodinggames.rts.game.units.buildings.AbstractUnitBehavior.K();
    }


    public static int bM() {  // 02b am.bM() L604-616: 鍗曚綅绫诲瀷鏍￠獙鍜?
        int n2 = 0;
        com.corrodinggames.rts.gameFramework.GlobalState globalState = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitInstance unitInstance;
        if (globalState.as()) {
            for (java.util.Iterator iterator = java.util.EnumSet.allOf(com.corrodinggames.rts.game.units.UnitRegistry.class).iterator(); iterator.hasNext(); n2 = n2 * 31 + unitInstance.bw()) {
                com.corrodinggames.rts.game.units.UnitRegistry unitRegistry = (com.corrodinggames.rts.game.units.UnitRegistry)iterator.next();
                unitInstance = a((com.corrodinggames.rts.game.units.UnitTypeHandle)unitRegistry);
            }
        }
        return n2;
    }


    public void ci() {  // 02b am.ci() L1420-1424
        this.bu();
        this.a();
        this.bt();
    }

    public void dc() {  // 02b am.dc() L2122: 绌哄疄鐜?
    }


    public boolean s_() {  // 02b am.s_() L1636 鎶借薄 鈫?03 榛樿瀹炵幇
        return false;
    }


    public final boolean bT() {  // 02b am.bT() L730-732
        return this.cN != null ? false : this.cm >= 1.0f;
    }


    public com.corrodinggames.rts.game.PlayerState bX;  // 02b am.java L66


    public final boolean cf() {  // 02b am.cf() L1170-1173 (绠€鍖? 绔欎綅妫€鏌?
        return true;
    }

    public int cw() {  // 02b am.cw() L1632-1634: 杩愯緭鍗犵敤
        return 1;
    }


    public strictfp int bw() {  // 02b am.java L1839-1844
        byte by = 0;
        int n2 = by * 31 + (int)this.bN();
        n2 = n2 * 31 + (int)this.cv;
        return n2;
    }

    public float d(boolean bl2) {  // 02b am.d(boolean) L726-728
        return this.cg + 90.0f;
    }


    public strictfp boolean a(int n2, int n3) {  // 02b am.java L2224: a(int,int) 绌哄疄鐜?(Minimap 缁樺埗璋冪敤鐐?
        return false;
    }

}
