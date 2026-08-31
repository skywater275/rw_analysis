/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import java.io.IOException;
import com.corrodinggames.rts.game.units.custom.animation.UnitTrait;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.R;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.Projectile;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.units.custom.effects.EffectRenderer;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionTrigger;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionParticle;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.PathState;
import com.corrodinggames.rts.game.units.FireDecoration;
import com.corrodinggames.rts.game.units.UnitShield;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitTurret;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.WeaponTypeEnum;
import com.corrodinggames.rts.game.units.PathResult;
import com.corrodinggames.rts.game.units.custom.actions.a;
import com.corrodinggames.rts.game.units.custom.actions.d;
import com.corrodinggames.rts.game.units.custom.actions.e;
import com.corrodinggames.rts.game.units.custom.actions.g;
import com.corrodinggames.rts.game.units.custom.ae;
import com.corrodinggames.rts.game.units.custom.af;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.animation.AnimationMovementCurve;
import com.corrodinggames.rts.game.units.custom.TraitValueBuilder;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.ModUnitLoader;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import com.corrodinggames.rts.game.units.custom.CollisionShape;
import com.corrodinggames.rts.game.units.custom.WeaponMount;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.DirectionConfig;
import com.corrodinggames.rts.game.units.custom.Modifier;
import com.corrodinggames.rts.game.units.commands.UnitActionHelper;
import com.corrodinggames.rts.game.units.CustomUnitBase;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public strictfp class CustomUnitType
extends com.corrodinggames.rts.game.units.CustomUnitBase
implements UnitShield,
com.corrodinggames.rts.game.units.MovementPath,  // 02b j.java L30: implements units.d 闂?d=MovementPath (b()/e_() 闂?PointF[] 闂備浇顫夋禍浠嬪磿鏉堫偁浜规繛鎴欏灪閻擄絽霉閿濆洦娅曟俊?
com.corrodinggames.rts.game.units.commands.CarrierUnit {
    public float do_;
    public int a;
    public final ParameterAnimator b = new ParameterAnimator(this);
    float c = 1.0f;
    float d;
    float maxSpeed;
    public float maxHealth;  // 02 闂傚倷绀佽ぐ鐐裁归崶顒佸剨? custom.j.o闂備焦鍓氶崑鍛叏椤╁櫄Health (supplement 闂佽瀛╃粙鎺椼€冮崱娑辨晩鐎光偓閸曨偄寮烽梺缁橆焾鐏忔瑥鈻?
    // v19.112 闂傚倷绶￠崑鍛暜閹烘梻绀婂┑鐘插€堕埀顒佸浮瀹曪絾寰勭仦钘夎劘闂備胶绮…鍫ュ春閺嶎厼鐒?(javap 闂傚倷绀佽ぐ鐐裁归崶顒佸剨? custom.j dM/dN/dO/dV/dW static)
    public static PointF sharedRenderPoint;  // = 02 dM
    public static UnitInstance selectedUnitRef;  // = 02 dN
    public static int selectionIndex;  // = 02 dO
    static com.corrodinggames.rts.gameFramework.utility.CustomArrayList sharedRenderList1;  // = 02 dV
    static com.corrodinggames.rts.gameFramework.utility.CustomArrayList sharedRenderList2;  // = 02 dW
    public UnitTurret[] cL;  // 02 闂傚倷绀佽ぐ鐐裁归崶顒佸剨? custom.j.cL:[Lunits/ap;
    public float f;
    boolean isInitialized;
    public boolean h = true;
    public boolean i = true;
    float turretScale = 1.0f;
    boolean hasTurret = false;
    boolean l;
    float m;
    boolean n;
    public boolean p = true;
    float sightRange;
    boolean isWaterUnit;
    float s;
    float deceleration;
    public float u;
    public boolean v;
    float shieldRegenRate;
    public ModUnitRegistry x;
    public com.corrodinggames.rts.game.units.custom.WeaponConfig y;
    public ModUnitRegistry z;
    public at[] A;
    public final com.corrodinggames.rts.gameFramework.utility.CustomArrayList B = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList C = null;
    public float D;
    PointF[] E = null;
    PointF[] F = null;
    MovementController[] G;
    static final PointF H = new PointF();
    static final PointF I = new PointF();
    Paint sharedPaint;
    protected static final PointF K = new PointF();
    protected static final com.corrodinggames.rts.gameFramework.utility.ai dK = new com.corrodinggames.rts.gameFramework.utility.ai();
    final com.corrodinggames.rts.game.units.commands.UnitFactoryHelper dL = new com.corrodinggames.rts.game.units.commands.UnitFactoryHelper(this);
    public static PointF dM;
    public float dP;
    public float dQ;
    public float dR;
    public float dS;
    public com.corrodinggames.rts.game.units.custom.animation.i[] dT = null;
    static ArrayList dU;
    public static com.corrodinggames.rts.gameFramework.utility.CustomArrayList dV;
    public static com.corrodinggames.rts.gameFramework.utility.CustomArrayList dW;
    public static com.corrodinggames.rts.gameFramework.utility.CustomArrayList dX;
    static boolean renderStateFlag;
    static final HashMap unitTypeCache;
    static int nextCacheId;
    static String eb;
    static final PointF ec;
    UnitConfig ed;  // 02 闂傚倷绀佽ぐ鐐裁归崶顒佸剨? custom.j.ed:Lcustom/h;
    protected static final com.corrodinggames.rts.gameFramework.utility.ai ee;
    protected static final PointF ef;
    com.corrodinggames.rts.gameFramework.utility.CustomArrayList eg = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();

    public void getMuzzlePointWithRecoil() {
        this.E = new PointF[6];
        this.F = new PointF[this.E.length];
        for (int i2 = 0; i2 < this.E.length; ++i2) {
            this.E[i2] = new PointF();
            this.F[i2] = new PointF();
        }
    }

    @Override
    public PointF[] b() {
        if (this.E == null) {
            this.getMuzzlePointWithRecoil();
        }
        return this.E;
    }

    @Override
    public PointF[] e_() {
        if (this.E == null) {
            this.getMuzzlePointWithRecoil();
        }
        return this.F;
    }


    public void serializeToStream(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {  // v19.113k: 02 j.a(j.as) 闂備礁鎲￠崝鏍偡閵堝鐤?
        as2.a(11);
        as2.a(this.maxSpeed);
        as2.a(this.m);
        as2.a(this.n);
        as2.a(this.B.size());
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.B) {
            as2.a(am2);
        }
        as2.a(this.isWaterUnit);
        as2.a(this.maxHealth);
        as2.a(this.f);
        as2.a(this.s);
        as2.a(this.v);
        int n2 = 0;
        if (this.dT != null) {
            n2 = (byte)this.dT.length;
        }
        as2.c(n2);
        if (this.dT != null) {
            for (int i2 = 0; i2 < n2; ++i2) {
                com.corrodinggames.rts.game.units.custom.animation.i i3 = this.dT[i2];
                as2.a(i3.b);
                as2.a(i3.frictionValue);
                as2.a(i3.accelerationValue);
                as2.a(i3.airSpeedMultiplier);
                as2.a(i3.ignoreTerrain);
                as2.a(i3.collisionEnabled);
                as2.a(i3.pathfindingEnabled);
                as2.a(i3.avoidanceEnabled);
            }
        }
        this.dL.a(as2);
        as2.a(this.z);
        as2.a(this.i);
        as2.a(this.h);
        boolean bl2 = this.ed != this.x.O;
        as2.a(bl2);
        if (bl2) {
            com.corrodinggames.rts.game.units.custom.TeamTag.serializeTags(this.ed, as2);  // 02b g.a(h,j.as) 闂備礁鎲￠崝鏍偡閵堝鐤?
        }
        try {
            com.corrodinggames.rts.game.units.custom.WeaponConfig.a(this.y, this, as2);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        as2.a(this.sightRange);
        super.serializeToStream(as2);
    }

    @Override
    public void deserializeFromStream(InputNetStream k2) throws IOException {  // 覆写 UnitType.deserializeFromStream (已 throws IOException)  // v19.113k: 02 j.a(j.k) 闂佽崵濮村ú鈺咁敊婵犲洤鐤?
        if (k2.b() >= 32) {
            boolean bl2;
            com.corrodinggames.rts.game.units.UnitTypeHandle as2;  // 02b L244: units.as var8 = var1.q()
            int n2;
            int n3 = k2.readInt();
            this.maxSpeed = k2.readFloat();
            this.m = k2.readFloat();
            this.n = k2.readBoolean();
            this.B.clear();
            int n4 = k2.readInt();
            for (n2 = 0; n2 < n4; ++n2) {
                UnitInstance am2 = k2.o();
                if (am2 == null) continue;
                this.B.add(am2);
            }
            if (n3 >= 1) {
                this.isWaterUnit = k2.readBoolean();
            }
            if (n3 >= 2) {
                this.maxHealth = k2.readFloat();
            }
            if (n3 >= 3) {
                this.f = k2.readFloat();
                this.s = k2.readFloat();
            }
            if (n3 >= 4) {
                this.v = k2.readBoolean();
            }
            if (n3 >= 5 && (n2 = (int)k2.d()) != 0) {
                this.du();
                if (this.dT == null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("readIn: legs==null but leg data found in save, this might be due to missing or changed mods");
                }
                for (int i2 = 0; i2 < n2; ++i2) {
                    com.corrodinggames.rts.game.units.custom.animation.i i3;
                    if (this.dT == null) {
                        i3 = new com.corrodinggames.rts.game.units.custom.animation.i();
                    } else if (i2 >= this.dT.length) {
                        com.corrodinggames.rts.gameFramework.GlobalState.e("readIn: legs " + i2 + ">=" + this.dT.length);
                        i3 = new com.corrodinggames.rts.game.units.custom.animation.i();
                    } else {
                        i3 = this.dT[i2];
                    }
                    i3.b = k2.readFloat();
                    i3.frictionValue = k2.readFloat();
                    i3.accelerationValue = k2.readFloat();
                    i3.airSpeedMultiplier = k2.readFloat();
                    i3.ignoreTerrain = k2.readBoolean();
                    i3.pushingEnabled = true;
                    i3.collisionEnabled = k2.readBoolean();
                    i3.pathfindingEnabled = k2.readBoolean();
                    i3.avoidanceEnabled = k2.readBoolean();
                }
            }
            if (n3 >= 6) {
                this.dL.a(k2);
            }
            if (n3 >= 7 && (as2 = k2.q()) != null) {
                if (as2 instanceof ModUnitRegistry) {
                    this.z = (ModUnitRegistry) as2;
                } else {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("Got non CustomUnitMetadata object of:" + as2.i() + " loading real_meta");
                    this.z = ModUnitRegistry.b;
                }
            }
            if (n3 >= 8) {
                this.i = k2.readBoolean();
                this.h = k2.readBoolean();
            }
            if (n3 >= 9 && (bl2 = k2.readBoolean())) {
                this.a(com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(k2), true);
            }
            if (n3 >= 10) {
                try {
                    com.corrodinggames.rts.game.units.custom.WeaponConfig.a(this, k2, n3);
                }
                catch (IOException iOException) {
                    throw new RuntimeException(iOException);
                }
            }
            if (n3 >= 11) {
                this.sightRange = k2.readFloat();
            }
        }
        super.deserializeFromStream(k2);
        if (this.dT != null) {
            this.dP = this.eo;
            this.dQ = this.ep;
            this.dR = this.eq;
            this.dS = this.cg;
        }
    }


    public boolean cr() {
        return this.x.eM > 0;
    }


    public int bB() {
        return this.B.size();
    }


    public boolean bA() {
        return this.n;
    }


    public boolean d(UnitInstance am2, boolean bl2) {
        boolean bl3;
        if (this.x.eM == 0) {
            return false;
        }
        if (this.n) {
            return false;
        }
        if (this.cm < 1.0f) {
            return false;
        }
        if (!this.G(am2)) {  // 02b L305: !this.G(var1)
            return false;
        }
        if (am2 == this) {
            return false;
        }
        if (!(this.player == am2.player || bl2 || this.x.cB && this.player == PlayerState.i)) {
            return false;
        }
        if (this.x.eP != null && !this.x.eP.a() && !com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(this.x.eP, am2.getStatusEffects())) {
            return false;
        }
        if (this.x.eQ.size() > 0) {
            bl3 = false;
            for (MovementTypeEnum ao2 : (java.util.Collection<MovementTypeEnum>) (java.util.Collection) this.x.eQ) {
                if (ao2 != am2.h()) continue;
                bl3 = true;
                break;
            }
            if (!bl3) {
                return false;
            }
        }
        bl3 = this.x.eR;
        return PathfindingUtils.a(am2, bl3, this.x.eS);
    }


    public boolean e(UnitInstance am2, boolean bl2) {
        if (!this.d(am2, bl2)) {
            return false;
        }
        this.C(am2);  // 02b j.java L341: this.C(var1)
        return true;
    }

    public void C(UnitInstance am2) {  // 02b j.java L346: C(am) 闂佽崵鍠嶇粈渚€骞婇幘瓒佸綊宕堕鈧涵鈧梺鍝勬处濮樸劎绮旈幘顔界厱闁圭儤鎸搁婊呯磼鐠囪尙校婵?(getTurretAimAngle 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
        am2.cN = this;
        this.B.add(am2);
        if (this.x.cC && this.player == PlayerState.i) {
            this.e(am2.player);  // 02b L350: this.e(var1.bX)
        }
        this.a(com.corrodinggames.rts.game.units.custom.af.l, am2);
        am2.a(com.corrodinggames.rts.game.units.custom.af.o, this);  // 02b L354: var1.a(af.o,(am)this)
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bS.l(am2);
    }

    public void getMuzzleWorldPosition(UnitInstance am2) {
        this.a(com.corrodinggames.rts.game.units.custom.af.m, am2);
        am2.a(com.corrodinggames.rts.game.units.custom.af.p, this);  // 02b L361: var1.a(af.p,(am)this)
        if (this.x.cD && this.B.size() == 0) {
            this.e(PlayerState.i);  // 02b L363: this.e(n.i)
        }
    }


    public void e(UnitInstance am2) {
        if (am2.cN == this) {
            this.B.remove(am2);
            am2.cN = null;
            this.getMuzzleWorldPosition(am2);
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.g("Unit is not being transported");  // 02b L374: l.g
        }
    }

    public void getTurretRangeExtension() {
        if (this.x.eM == 0) {
            return;
        }
        this.n = true;
        this.m = 30.0f;
    }

    public void M() {
        if (this.x.eM == 0) {
            return;
        }
        this.n = false;
    }

    public boolean g(boolean bl2) {
        if (this.B.size() == 0) {
            return false;
        }
        boolean bl3 = this.B.size() % 2 == 0;
        UnitInstance am2 = (UnitInstance) this.B.remove(this.B.size() - 1);
        return this.detachUnit(am2, bl2, bl3);
    }

    public boolean a(UnitInstance am2, boolean bl2, boolean bl3) {
        this.B.remove(am2);
        return this.detachUnit(am2, bl2, bl3);
    }

    private boolean detachUnit(UnitInstance am2, boolean bl2, boolean bl3) {
        boolean bl4;
        float f2 = 180.0f;
        if (this.x.dk != null) {
            f2 = this.x.dk.floatValue();
        }
        float f3 = 70.0f;
        if (this.x.dn != null) {
            f3 = this.x.dn.floatValue();
        }
        float f4 = 7.0f;
        float f5 = this.x.di;
        float f6 = this.x.dj;
        float f7 = this.eo + GameUtils.cosFast(this.cg + f2) * f6 - GameUtils.sinFast(this.cg + f2) * f5;
        float f8 = this.ep + GameUtils.sinFast(this.cg + f2) * f6 + GameUtils.cosFast(this.cg + f2) * f5;
        f7 += GameUtils.cosFast(this.cg + 90.0f) * (bl3 ? -f4 : f4);
        f8 += GameUtils.sinFast(this.cg + 90.0f) * (bl3 ? -f4 : f4);
        if (!bl2 && !this.isSeaUnitByConfig()) {
            if (!PathfindingUtils.a(am2, f7, f8)) {
                f7 += 10.0f;
            }
            if (!PathfindingUtils.a(am2, f7, f8)) {
                f7 -= 20.0f;
            }
            if (!PathfindingUtils.a(am2, f7, f8)) {
                f7 -= 10.0f;
                f8 += 10.0f;
            }
            if (!PathfindingUtils.a(am2, f7, f8)) {
                f8 -= 20.0f;
            }
        }
        if (!(bl2 || PathfindingUtils.a(am2, f7, f8) || this.isSeaUnitByConfig())) {
            bl4 = false;
        } else {
            bl4 = true;
            boolean bl5 = false;
            UnitTrait n2 = null;
            if (am2 instanceof UnitType) {
                UnitType y2 = (UnitType) am2;
                if (y2.cO == this) {
                    n2 = y2.dn();
                    if (n2 == null) {
                        com.corrodinggames.rts.gameFramework.GlobalState.e("Unload, attachment data is null");
                    }
                    if (n2 != null) {
                        bl5 = n2.E;
                    }
                }
            }
            PathfindingUtils.a(am2, this);
            float f9 = this.cg + f2;
            if (!bl5) {
                am2.eo = f7;
                am2.ep = f8;
                am2.cg = f9;
                am2.cd = 0.0f;
                am2.cc = 0.0f;
                am2.bZ = 0.0f;
                am2.ca = 0.0f;
                am2.bZ += 0.1f;
            }
            am2.bR = this;
            am2.bS = 45.0f;
            if (bl5) {
                am2.bS = 85.0f;
            }
            if (am2 instanceof UnitType) {
                UnitType y3 = (UnitType) am2;
                if (!bl5) {
                    y3.j(am2.cg);
                }
                if (!this.x.eW.read(this)) {
                    y3.az();
                    if (f3 != 0.0f) {
                        y3.d(am2.eo + GameUtils.cosFast(f9 + (bl3 ? -f4 : f4)) * f3, am2.ep + GameUtils.sinFast(f9 + (bl3 ? -f4 : f4)) * f3);
                    }
                    y3.ac = 0;
                } else {
                    y3.aH();
                }
            }
            if (!bl5) {
                if (!this.x.dm) {
                    am2.eq = this.eq;
                }
                am2.eq += this.x.dl;
            }
            if (am2 instanceof CustomUnitType) {
                ((CustomUnitType) am2).dF();
            }
        }
        if (!bl4) {
            this.B.add(am2);
        } else {
            this.getMuzzleWorldPosition(am2);
        }
        return bl4;
    }


    public int dI() {
        if (this.x.eM == 0 || !this.x.x) {
            return -1;
        }
        return this.dI();
    }


    public int bZ() {
        if (this.x.eM == 0 || !this.x.x) {
            return -1;
        }
        return this.x.eM;
    }

    public void ds() {
        if (this.B.a > 0) {
            boolean bl2 = this.x.eV.read(this);
            this.ejectAllTransported(bl2);
        }
    }

    public void ejectAllTransported(boolean bl2) {
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.B) {
            am2.cN = null;
            am2.eo = this.eo + GameUtils.cosFast(this.cg) * -9.0f;
            am2.ep = this.ep + GameUtils.sinFast(this.cg) * -9.0f;
            if (!bl2) continue;
            am2.getAttackRange();
        }
        this.B.clear();
    }


    public void bu() {
        if (!this.isDead) {
            this.a(com.corrodinggames.rts.game.units.custom.af.c);
        }
        Object[] objectArray = this.x.h.a();
        for (int i2 = this.x.h.a - 1; i2 >= 0; --i2) {
            com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase a2 = (com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)objectArray[i2];
            a2.b(this);
        }
        super.bu();  // 02b L568: super.bu()
    }

    @Override
    public void a() {
        this.ds();
        Object[] objectArray = this.x.h.a();
        for (int i2 = this.x.h.a - 1; i2 >= 0; --i2) {
            com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase a2 = (com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)objectArray[i2];
            a2.c(this);
        }
        PlayerState.a((UnitInstance) this);  // 02b L580: n.a((am)this)
        this.dL.a(true);
        super.a();
    }

    public ModUnitRegistry dt() {
        return this.x;
    }


    public boolean I() {
        if (this.x.aH) {
            return false;
        }
        if (this.isInitialized) {
            return false;
        }
        return this.cO == null;
    }


    public boolean aR() {
        if (this.x.dP) {
            return false;
        }
        UnitTrait n2 = this.dn();
        return n2 == null || n2.O;
    }


    public boolean aS() {
        if (this.x.aH) {
            return false;
        }
        UnitTrait n2 = this.dn();
        return n2 == null || n2.H;
    }


    public MovementTypeEnum h() {
        return this.x.fh;
    }


    public boolean i() {
        if (this.x.fh == com.corrodinggames.rts.game.units.MovementTypeEnum.d) {
            return this.eq >= 4.0f;
        }
        return false;
    }


    public boolean Q() {
        return this.eq <= -1.0f;
    }


    public boolean cv() {
        return this.ejectAllTransported() == com.corrodinggames.rts.game.units.MovementTypeEnum.e;
    }


    public boolean ct() {
        return this.ejectAllTransported() == com.corrodinggames.rts.game.units.MovementTypeEnum.d;
    }


    public Texture v() {  // 02b j.v() L631: m.e
        if (this.player.k == -1 || this.x.as == null) {
            return null;
        }
        return this.x.as[this.player.getTeamIndex()];
    }


    public Texture d() {
        if (this.isDead && this.x.an != null) {
            return this.x.an;
        }
        return this.x.ar[this.player.getTeamIndex()];
    }


    public Texture k() {
        return this.x.ap;
    }


    public boolean F() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.renderExtraShadows && (!this.isDead || this.eq >= 1.0f) && this.eq >= -1.0f;
    }


    public Texture d(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        if (bn2.C != null && bn2.C.read(this)) {
            return null;
        }
        if (bn2.aE != null) {
            return bn2.aE[this.player.getTeamIndex()];
        }
        if (bn2.aD != null) {
            return bn2.aD;
        }
        if (this.x.at != null) {
            return this.x.at[this.player.getTeamIndex()];
        }
        return this.x.ao;
    }


    public float h(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.aG;
    }


    public float i(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.aH;
    }

    public void b(ModUnitLoader bn2) {  // 02b j.java L1884: b(bn) 闂?闂備礁鎲￠懝楣冨箟閿熺姴鏋佺憸鐗堝笚閸ゅ嫬霉閻樺樊鍎忔繛?闂傚倸鍊哥€氼剛鈧凹鍓氶幈銊╂倷閸濆嫮顦梺闈涱焾閸婃绮?(v19.133f6 闂佽崵鍋炵粙蹇涘磿閸愬樊鍟?
        if (bn2.u > 0.0f) {
            this.cB -= bn2.u;
            if (this.cB < bn2.u && this.x.cR) {
                this.v = true;
            }
        }
        if (bn2.v != null) {
            bn2.v.a(this);
        }
    }


    public boolean e() {
        Object object;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.isSeaUnitByConfig()) {
            l2.bU.a(this);
        }
        if (this.cm < 1.0f) {
            if (this.x.bx != null && this.x.bx.b()) {
                this.x.bx.a(this.eo, this.ep, this.eq, this.cg, null);
                return false;
            }
            if (this.x.by != null && this.x.by.b()) {
                this.x.by.a(this.eo, this.ep, this.eq, this.cg, null);
                return false;
            }
            this.a(com.corrodinggames.rts.game.units.UnitState.a);
            return false;
        }
        if (!this.x.fi) {
            this.S(0);
        }
        if (this.x.by != null && this.x.by.b()) {
            this.x.by.a(this.eo, this.ep, this.eq, this.cg, null);
        }
        if (this.x.bB != -1) {
            this.a(null, this.eo, this.ep, this.x.bB, null, 0);
        }
        if (this.x.bC != null) {
            com.corrodinggames.rts.game.PlayerState n2;
            if (this.x.bD && this.bt != null && this.bt.bX != null) {  // 02b L694-698
                n2 = this.bt.bX;
            } else {
                n2 = this.bX;
            }
            if (!n2.E) {
                this.x.bC.a(this.eo, this.ep, this.eq, this.cg, n2, this.cG, this);
            }
        }
        this.bT = false;
        if (!this.x.fi) {
            this.ds();
        }
        if (this.x.bn) {
            boolean bl2 = false;
            if (this.x.br && l2.O() && l2.bX.ay.i) {
                bl2 = true;
            }
            if (!bl2) {
                object = com.corrodinggames.rts.game.units.commands.UnitActionHelper.a((UnitInstance) this, this.eo, this.ep, this.eo, this.ep);
                ((MovementController)object).aH = false;
                ((MovementController)object).Z = this.x.bo;
                ((MovementController)object).Y = this.x.bp;
            }
        }
        if (this.x.bm != 0) {
            FireDecoration ai2 = new FireDecoration(false);
            ai2.eo = this.eo;
            ai2.ep = this.ep;
            ai2.b(PlayerState.h);
            PlayerState.c(ai2);
        }
        if (this.x.bz != null) {
            this.x.bz.a(this.eo, this.ep, 1.0f);
        }
        if (this.x.eD) {
            if (this.x.bz == null) {
                l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.A, 0.8f, this.eo, this.ep);  // 02b L739: gameFramework.a.e.A
            }
            if (this.x.bu) {
                if (!this.setQueuePaused() && !this.x.eJ) {
                    Projectile.a(this, 1);
                }
                if (this.x.bt != null) {
                    this.a(this.x.bt, true);
                } else if (this.x.bj) {
                    this.a(com.corrodinggames.rts.game.units.UnitState.d);
                }
            }
            if (this.x.eE) {
                for (int i2 = 0; i2 < this.getExplosionParticleCount(); ++i2) {
                    l2.bR.d(this.eo, this.ep, this.eq);
                }
            }
        } else {
            if (this.dT != null) {
                this.dv();
                for (int i3 = 0; i3 < this.dT.length; ++i3) {
                    object = this.dT[i3];
                    TraitValueBuilder ba2 = this.x.ax[i3];
                    float f2 = this.eo + ((com.corrodinggames.rts.game.units.custom.animation.i)object).b;
                    float f3 = this.ep + ((com.corrodinggames.rts.game.units.custom.animation.i)object).frictionValue;  // 02b L228: var6.c
                    if (!ba2.J || ba2.p || ba2.q != null && ba2.q.read(this)) continue;
                    if (!PathfindingUtils.d(f2, f3) && !this.x.eJ) {
                        Projectile.a(f2, f3);
                    }
                    l2.bR.b(f2, f3, 0.0f);
                }
            }
            if (!this.setQueuePaused()) {
                if (this.x.bu) {
                    if (this.x.bt != null) {
                        this.a(this.x.bt, true);
                    } else {
                        this.a(com.corrodinggames.rts.game.units.UnitState.b);
                    }
                }
            } else {
                if (this.x.bu) {
                    if (this.x.bt != null) {
                        this.a(this.x.bt, false);
                    } else {
                        l2.bR.b(this.eo, this.ep, this.eq);
                    }
                }
                for (int i4 = 0; i4 < this.getExplosionParticleCount(); ++i4) {
                    l2.bR.e(this.eo, this.ep, this.eq);
                }
            }
        }
        if (this.x.fi) {
            return true;
        }
        if (this.x.an != null) {
            this.M = this.x.an;
            this.a = 0;
            this.ew = true;
            return true;
        }
        return false;
    }


    public void bq() {
        if (!this.x.eJ) {
            super.bq();  // 02b L816: super.bq()
        }
    }


    public void U() {
        super.U();
        for (int i2 = 0; i2 < this.x.fQ.length; ++i2) {
            UnitTurret ap2 = this.cL[i2];
            com.corrodinggames.rts.gameFramework.GlobalState.e("Dir was:" + ap2.turretAngle + " for name:" + this.x.fQ[i2].a);
            com.corrodinggames.rts.gameFramework.GlobalState.e("lockDelay:" + ap2.lockDelay + " shootCooldown:" + ap2.shootCooldown);
            com.corrodinggames.rts.gameFramework.GlobalState.e("updateAndShouldResetTurret:" + this.b(i2, 0.0f));  // 02b L828: this.b(var1,0.0F)
            float f2 = this.C(i2);  // 02b L829: this.C(var1)
            com.corrodinggames.rts.gameFramework.GlobalState.e("idleDir:" + f2);
            float f3 = GameUtils.c(ap2.turretAngle, f2, 360.0f);
            com.corrodinggames.rts.gameFramework.GlobalState.e("diffDir:" + f3);
        }
    }

    public void a(ModUnitRegistry l2, boolean bl2, boolean bl3) {
        this.a(l2, bl2, bl3, null);
    }

    public void a(ModUnitRegistry l2, boolean bl2, boolean bl3, at[] atArray) {
        Object object;
        Object[] objectArray;
        com.corrodinggames.rts.game.units.custom.WeaponConfig as2;
        ModUnitRegistry l3;
        GlobalState l4;
        block52: {
            int n2;
            block51: {
                int n3;
                ModUnitLoader bn2;
                l4 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                l3 = this.x;
                as2 = this.y;
                this.x = l2;
                this.dz = this.x;
                this.y = l2.cL;
                if (bl3) {
                    com.corrodinggames.rts.game.units.custom.WeaponConfig.a(this, as2, l3);
                } else if (atArray != null) {
                    com.corrodinggames.rts.game.units.custom.WeaponConfig.a(this, as2, atArray);
                }
                this.bS();
                if (!bl3) {
                    this.j(true);  // 02b L856: this.j(true)
                }
                if (bl2) break block51;
                if (l2.fQ.length <= 1) break block52;
                n2 = 1;
                if (l2.fQ.length != l3.fQ.length) {
                    n2 = 0;
                } else {
                    for (int i2 = 0; i2 < l2.fQ.length; ++i2) {
                        com.corrodinggames.rts.game.units.custom.ModUnitLoader bn3 = l2.fQ[i2];  // 02b var10
                        bn2 = l3.fQ[i2];
                        if (bn3.a.equalsIgnoreCase(bn2.a)) continue;
                        n2 = 0;
                        break;
                    }
                }
                if (n2 != 0) break block52;
                UnitTurret[] apArray = new UnitTurret[l2.fQ.length];
                for (n3 = 0; n3 < l2.fQ.length; ++n3) {
                    bn2 = l2.fQ[n3];
                    object = l3.e(bn2.a);
                    if (object == null) continue;
                    apArray[bn2.e] = this.cL[((ModUnitLoader) object).e];
                    this.cL[((ModUnitLoader) object).e] = null;
                }
                for (n3 = 0; n3 < l2.fQ.length; ++n3) {
                    if (apArray[n3] != null) continue;
                    for (int i3 = 0; i3 < l2.fQ.length; ++i3) {
                        if (this.cL[i3] == null) continue;
                        apArray[n3] = this.cL[i3];
                        this.cL[i3] = null;
                        break;
                    }
                    if (apArray[n3] == null) {
                        apArray[n3] = new UnitTurret();
                    }
                    apArray[n3].a(this.cg);
                }
                this.cL = apArray;
                break block52;
            }
            for (n2 = 0; n2 < l2.fQ.length; ++n2) {
                float f2 = this.cg + this.B(n2);  // 02b L914: this.B(var14)
                this.cL[n2].a(f2);
            }
        }
        if (this.x.ae) {
            this.V(this.x.af);  // 02b L920: this.V(x.af)
            this.W(this.x.ag);
        } else {
            this.T(this.x.af);  // 02b L923: this.T(x.af)
            this.U(this.x.ag);
        }
        this.ew = false;
        this.cj = this.x.cW;
        this.ck = this.x.dd;
        this.a = this.isDead ? 0 : this.x.Y;
        this.isInitialized = false;
        if (bl2) {
            this.eq += this.x.dS;
        }
        float f3 = this.maxHp;
        this.maxHp = this.y.targetFilter;
        if (bl2) {
            this.maxHealth = this.maxHp;
        } else if (f3 == 0.0f) {
            this.maxHealth = this.maxHp;
        } else {
            this.o(this.hp / f3 * this.maxHp);  // 02b L948: this.o(...)
        }
        float f4 = this.cA;
        this.cA = this.y.bulletCount;
        if (this.x.cM) {
            if (this.cx > this.cA) {
                this.cx = this.cA;
            }
        } else {
            this.cx = bl2 ? this.cA : (f4 == 0.0f ? this.cA : this.cx / f4 * this.cA);
        }
        if (this.x.cO) {
            if (this.cB > this.y.minAttackRange) {
                this.cB = this.y.minAttackRange;
            }
        } else {
            this.cB = bl2 ? this.y.minAttackRange * this.x.cS : (as2.minAttackRange == 0.0f ? this.y.minAttackRange : this.cB / as2.minAttackRange * this.y.minAttackRange);
        }
        if (this.player == null) {
            this.M = this.x.ad;
        } else {
            this.S();
        }
        if (this.x.aH && bl2) {
            this.cg = -90.0f;
        }
        this.f_();  // 02b L987: this.f_()
        if (!(bl2 || this.bx == null && this.by == null || com.corrodinggames.rts.game.units.custom.resources.CustomActionBase.b(this.x.ch, l3.ch) && com.corrodinggames.rts.game.units.custom.resources.CustomActionBase.b(this.x.cj, l3.cj))) {
            this.bx = null;
            this.by = null;
        }
        if (!bl2) {
            boolean bl4;
            boolean bl5 = bl4 = this.x.j() != l3.j();  // 02b L994: this.x.j() != var6.j()
            if (this.x.j()) {
                this.cd = 0.0f;
                this.cc = 0.0f;
                if (l3.j() && !this.x.cX.equals(l3.cX)) {
                    bl4 = true;
                }
            }
            if (bl4) {
                l4.bU.a(this);
            }
        }
        this.turretScale = 1.0f;
        if (this.x.cI != -2) {
            // empty if block
        }
        if (!this.isDead) {
            this.updateAnimationState();
        }
        if (this.x.eF) {
            // empty if block
        }
        this.du();
        this.getCustomUnitData().a(this.x);  // 02b L1022: this.dg().a(this.x) (custom.c.c)
        if (!bl2) {
            int n4 = this.bl();
            for (int i4 = 0; i4 < n4; ++i4) {
                object = this.cL[i4];
                ModUnitLoader bn3 = this.x.fQ[i4];
                if (bn3 == null) continue;
                if (((UnitTurret) object).shootCooldown > bn3.m) {
                    ((UnitTurret) object).shootCooldown = bn3.m;
                }
                if (!(((UnitTurret) object).maxRotationAngle > bn3.n)) continue;
                ((UnitTurret) object).maxRotationAngle = bn3.n;
            }
        }
        if (!bl2) {
            if (!this.x.dc) {
                this.dL.b = null;  // 02b L1043: this.dL.b = null (PointF)
            }
            if (this.x.fg != l3.fg) {
                this.aH();
            }
        }
        if (this.x.cD && this.B.size() == 0) {
            this.b(PlayerState.i);  // 02b L1052: this.b(n.i)
        }
        if (this.cG && !l4.bS.i(this)) {
            l4.bS.l(this);
        }
        if (!bl2) {
            int n5;
            objectArray = this.x.h.a();
            for (n5 = this.x.h.a - 1; n5 >= 0; --n5) {
                object = (com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)objectArray[n5];
                ((com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)object).a(this, l3);
            }
            if (this.cm >= 1.0f) {
                if (this.y.ammoPerBurst > as2.ammoPerBurst) {
                    this.c(false);  // 02b L1069: this.c(false)
                }
            } else {
                int n6;
                n5 = l3.dh != -1 ? l3.dh : as2.ammoPerBurst;
                int n7 = n6 = this.x.dh != -1 ? this.x.dh : this.y.ammoPerBurst;
                if (n6 > n5) {
                    this.c(false);  // 02b L1077
                }
            }
        }
        if (this.i && this.x.dv != null) {
            this.b.a(this.x.dv, 7, true);
        }
    }


    public void f_() {
        if (this.x.aH) {
            this.bT = false;
        } else if (!this.isDead) {
            this.bT = true;
            if (this.x.eC) {
                this.bT = false;
            }
        } else {
            this.bT = false;
        }
        if (this.cO != null) {
            this.bT = false;
        }
    }

    public CustomUnitType(boolean bl2, ModUnitRegistry l2) {
        super(bl2);
        this.a(l2, true, false);
    }


    public void a(float f2) {
        float f3;
        Object object;
        float f4;
        float f5;
        int n2;
        Object object2;
        ModUnitRegistry object3;
        boolean bl2 = this.i;
        if (bl2) {
            this.i = false;
            Object[] object3Arr = this.x.h.a();
            for (int i2 = this.x.h.a - 1; i2 >= 0; --i2) {
                object2 = (com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)object3Arr[i2];
                ((com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)object2).a(this);
            }
            this.detachUnit(com.corrodinggames.rts.game.units.custom.af.a);
        }
        object3 = this.x;
        super.a(f2);
        if (this.isDead && !this.l) {
            if (this.eq > 0.0f) {
                if (object3.fi && (this.cf != 0.0f || this.cc != 0.0f || this.cd != 0.0f)) {
                    this.f += 0.017f * f2;
                    this.eq -= this.f * f2;
                    PointF pointF = this.n(f2);
                    this.eo += pointF.a;
                    this.ep += pointF.b;
                    if (object3.fg == com.corrodinggames.rts.game.units.MovementTypeEnum.d) {
                        HUDElement e2;
                        object2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                        this.dq += f2;
                        this.do_ += f2;
                        if (object3.fj && this.dq > 9.0f) {
                            this.dq = GameUtils.c(1.0f, 3.0f);
                            e2 = ((GlobalState)object2).bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, DrawLayer.b);  // 02b L1141: d.d.a
                            if (e2 != null) {
                                e2.aq = 0;
                                e2.ap = 0;
                                e2.ar = (short)2;
                                e2.r = true;
                                e2.E = 0.5f;
                                e2.W = 60.0f;
                                e2.V = 60.0f;
                                e2.G = 0.9f;
                                e2.F = 1.2f;
                                e2.as = false;
                                e2.P = 0.0f;
                                e2.Q = 0.0f;
                            }
                        }
                        if (this.do_ > 7.0f) {
                            this.do_ = 0.0f;
                            e2 = ((GlobalState)object2).bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, DrawLayer.a);  // 02b L1160: d.d.a
                            if (e2 != null) {
                                com.corrodinggames.rts.gameFramework.effects.DrawEffect.b(e2, true);  // 02b L1162: d.f.b(var6,true)
                                e2.I = this.eo;
                                e2.J = this.ep;
                                e2.K = this.eq;
                                e2.P += GameUtils.c(-0.1f, 0.1f) + this.cc;
                                e2.Q += GameUtils.c(-0.1f, 0.1f) + this.cd;
                                e2.I += GameUtils.c(-4.0f, 4.0f);
                                e2.J += GameUtils.c(-4.0f, 4.0f);
                            }
                        }
                    }
                } else {
                    this.f += object3.dW * f2;
                    this.eq -= this.f * f2;
                }
            } else if (!this.hasTurret) {
                this.hasTurret = true;
                if (object3.fi) {
                    this.ds();
                    this.S(0);
                    if (object3.an != null) {
                        this.M = object3.an;
                        this.a = 0;
                        this.ew = true;
                    } else {
                        this.ci();
                    }
                }
                if ((double)this.f > 0.5) {
                    if (object3.bw != null && object3.bw.a()) {
                        object3.bw.a(this.eo, this.ep, this.eq, this.cg, null);
                    }
                    if (this.cK()) {
                        if (object3.bv) {
                            this.a(com.corrodinggames.rts.game.units.UnitState.a);
                        }
                        if (this.cJ()) {
                            com.corrodinggames.rts.gameFramework.GlobalState.B().bR.a(this.eo, this.ep, 0.0f, 0, 0.0f, 0.0f, this.cg);
                        }
                    } else if (object3.bv) {
                        this.a(com.corrodinggames.rts.game.units.UnitState.b);
                    }
                }
                this.f = 0.0f;
            } else if (this.cK()) {
                if (this.eq > -10.0f) {
                    this.f += 8.0E-4f * f2;
                    this.eq -= this.f * f2;
                    if (this.cJ()) {
                        this.deceleration += f2;
                        if (this.deceleration > 30.0f) {
                            this.deceleration = 0.0f;
                            if (this.s_()) {
                                GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                                object2 = l2.bR.b(this.eo, this.ep, this.eq, this.cg);
                                if (object2 != null) {
                                    ((HUDElement)object2).P = 0.0f;
                                    ((HUDElement)object2).Q = -0.1f;
                                }
                            }
                        }
                    }
                } else {
                    this.l = true;
                }
            } else {
                this.eq = 0.0f;
                this.l = true;
            }
        }
        if (this.isDead) {
            return;
        }
        GlobalState l3 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (object3.bh != 0.0f && this.cm < 1.0f) {
            float f6 = this.cm + object3.bh * f2;
            if (f6 >= 1.0f) {
                PlayerState.b((UnitInstance) this);
                this.cm = 1.0f;
                this.cn = 1.0f;
                PlayerState.c(this);
            } else {
                this.cm = f6;
                this.cn = f6;
            }
        }
        if (!this.bT()) {
            int n3;
            if (this.cm < 1.0f) {
                if (object3.dw != null) {
                    this.b.a(object3.dw, 8);
                    this.b.a(f2);
                } else if (object3.dx != null) {
                    this.b.a(object3.dx, 99);
                    this.b.b = this.cm;
                    this.b.d = 0.0f;
                    this.b.a(f2);
                }
            }
            Object[] objectArray = object3.h.a();
            for (n3 = object3.h.a - 1; n3 >= 0; --n3) {
                com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase a2 = (com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)objectArray[n3];
                a2.a(this, f2);
            }
            n3 = 1;
            if (this.cm < 1.0f && !object3.cd || this.cN != null && !object3.cc) {
                n3 = 0;
            }
            if (n3 != 0) {
                this.detachUnit(f2, bl2);
                object3 = this.x;
            }
            return;
        }
        Object[] objectArray = object3.h.a();
        for (n2 = object3.h.a - 1; n2 >= 0; --n2) {
            com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase a3 = (com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)objectArray[n2];
            a3.b(this, f2);
        }
        n2 = this.h ? 1 : 0;
        if (n2 != 0) {
            this.h = false;
            this.a(com.corrodinggames.rts.game.units.custom.af.b);
        }
        if (this.y.accuracySpread != 0.0f && (this.hp < this.maxHp || this.y.accuracySpread < 0.0f)) {
            this.hp += this.y.accuracySpread * f2;
            if (this.hp > this.maxHp) {
                this.hp = this.maxHp;
            }
        }
        if (this.y.minAttackRange != 0.0f) {
            float f7 = !this.v ? object3.cP : object3.cQ;
            if (this.cB < this.y.minAttackRange || f7 < 0.0f) {
                this.cB += f7 * f2;
            }
            if (this.cB >= this.y.minAttackRange) {
                this.cB = this.y.minAttackRange;
                this.v = false;
            }
            if (this.cB <= 0.0f) {
                if (object3.bk) {
                    this.bv();  // 02b L1326: this.bv()
                    return;
                }
                this.cB = 0.0f;
            }
        }
        if (this.cA != 0.0f) {
            if (this.y.aimingTime != 0.0f) {
                this.cx += this.y.aimingTime * f2;
                if (this.cx > this.cA) {
                    this.cx = this.cA;
                }
            }
            if (this.cx < 0.0f) {
                this.cx = 0.0f;
            }
            if (this.cy != 0.0f) {
                this.cy -= this.cy * 0.02f * f2;
                this.cy = GameUtils.a(this.cy, 0.0f, object3.cV * f2);
                if (this.cy > 50.0f) {
                    this.cy = 50.0f;
                }
            }
        }
        this.dL.a(f2);
        object3 = this.x;
        if (object3.fp) {
            com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.a(f2, this);
        }
        if (object3.cn) {
            this.maxHealth += f2;
            if (this.maxHealth > (float)object3.cr - 0.1f) {
                this.maxHealth -= (float)object3.cr;
                boolean bl3 = object3.cx.read(this);
                if (this.p != bl3) {
                    PlayerState.a((UnitInstance) this);  // 02b L580: n.a((am)this)
                    this.p = bl3;
                    PlayerState.c(this);
                }
                if (this.p) {
                    object3.co.g(this);
                }
            }
        }
        if (object3.ct != null) {
            this.sightRange += f2;
            if (this.sightRange >= object3.cu) {
                this.sightRange = 0.0f;
                try {
                    object3.ct.writeToUnit(this);
                }
                catch (bo bo2) {
                    throw new RuntimeException(bo2);
                }
            }
        }
        if (this.cK) {
            this.b.a(object3.ds, 3);
        } else if (!this.b.e || this.b.a == object3.dt) {
            this.b.a(object3.dt, 2);
        }
        if (object3.bL) {
            float f8;
            if (object3.bJ && l3.dd) {
                if (this.cf > 0.0f || this.cf < 0.0f && object3.bK) {
                    this.deceleration += f2;
                }
                if (this.deceleration > 10.0f) {
                    this.deceleration = 0.0f;
                    if (this.el && this.cJ()) {
                        float f9 = this.cg + 180.0f;
                        if (this.cf < 0.0f) {
                            f9 += 180.0f;
                        }
                        if ((f8 = this.cj - 6.0f) < 4.0f) {
                            f8 = 4.0f;
                        }
                        f5 = this.eo + GameUtils.cosFast(f9) * f8;
                        f4 = this.ep + GameUtils.sinFast(f9) * f8;
                        l3.bR.b(f5, f4, 0.0f, f9);
                    }
                }
            }
            if (l3.dc && (this.cf > 0.0f || this.cf < 0.0f) && this.el) {
                this.d += f2;
                if (this.d > object3.bR) {
                    this.d = 0.0f;
                    if (this.cf > 0.0f) {
                        if (object3.bO != null) {
                            object3.bO.a(this.eo, this.ep, this.eq, this.cg, this);
                        }
                    } else if (object3.bP != null) {
                        float f10 = this.cg;
                        if (object3.bQ) {
                            f10 += 180.0f;
                        }
                        object3.bP.a(this.eo, this.ep, this.eq, f10, this);
                    }
                    if (object3.bM && (this.cf > 0.0f || object3.bN) && !this.cJ()) {
                        for (int i3 = 0; i3 <= 1; ++i3) {
                            f8 = i3 == 0 ? -20 : 20;
                            f5 = this.cg + 180.0f;
                            if (this.cf < 0.0f) {
                                f8 += 180.0f;
                                f5 += 180.0f;
                            }
                            if ((object = l3.bR.c(f4 = this.eo + GameUtils.cosFast(this.cg + 180.0f + f8) * (this.cj - 1.0f), f3 = this.ep + GameUtils.sinFast(this.cg + 180.0f + f8) * (this.cj - 1.0f), this.eq, f5 += GameUtils.c(-7.0f, 7.0f), 0)) == null) continue;
                            ((HUDElement)object).P += GameUtils.c(-0.15f, 0.15f);
                            ((HUDElement)object).Q += GameUtils.c(-0.15f, 0.15f);
                        }
                    }
                }
            }
        }
        if (object3.eM > 0) {
            if (object3.eY != 0.0f && this.B.a > 0) {
                Object[] objectArray2 = this.B.a();
                for (int i4 = 0; i4 < this.B.a; ++i4) {
                    UnitInstance am2 = (UnitInstance) objectArray2[i4];
                    if (!(am2.cu < am2.cv) && !(object3.eY < 0.0f)) continue;
                    am2.cu += object3.eY * f2;
                    if (!(am2.cu > am2.cv)) continue;
                    am2.cu = am2.cv;
                }
            }
            if (this.n && object3.fc.read(this)) {
                this.m = GameUtils.a(this.m, f2);
                if (this.m == 0.0f) {
                    this.m = object3.eN;
                    if (this.B.size() == 0) {
                        this.n = false;
                    } else {
                        this.g(false);  // 02b L1486: this.g(false)
                        if (this.B.size() == 0) {
                            this.n = false;
                        }
                    }
                }
            }
        }
        this.s = !this.cK ? (this.s += f2) : 0.0f;
        if (object3.fg != com.corrodinggames.rts.game.units.MovementTypeEnum.d && this.cO == null) {
            this.dF();  // 02b L1502: this.dF()
        }
        if ((object3.fg == com.corrodinggames.rts.game.units.MovementTypeEnum.g || object3.fg == com.corrodinggames.rts.game.units.MovementTypeEnum.h) && this.cK) {
            this.turretScale = this.cI() ? 0.7f : 1.0f;
        }
        if (object3.fg == com.corrodinggames.rts.game.units.MovementTypeEnum.d) {
            this.maxSpeed += 2.0f * f2;
            if (this.maxSpeed > 360.0f) {
                this.maxSpeed -= 360.0f;
            }
            boolean bl4 = this.setQueuePaused();
            boolean bl5 = false;
            if (object3.dQ) {
                boolean bl6 = this.cK();
                if (!(this.cK || bl6 || !(this.s > 3.0f) || object3.dR && !this.aq())) {
                    bl5 = true;
                }
            }
            if (this.cO == null) {
                if (bl5) {
                    f5 = object3.dU < 0.0f ? GameUtils.c(this.eq - 2.0f) * 0.05f * 0.4f + 0.2f : object3.dU;
                    this.eq = GameUtils.a(this.eq, 2.0f, f5 * f2);
                } else {
                    f5 = this.y.projectileGravity + GameUtils.sinFast(this.maxSpeed) * object3.dT;
                    f4 = object3.dU < 0.0f ? GameUtils.c(this.eq - 2.0f) * 0.05f * 0.4f + 0.2f : object3.dU;
                    f3 = GameUtils.c(this.eq - f5) * 0.05f * 0.3f + 0.1f;
                    f4 = GameUtils.b(f4, f3);
                    this.eq = GameUtils.a(this.eq, f5, f4 * f2);
                }
                if (bl4 != this.setQueuePaused()) {
                    this.ay = true;
                    this.updateAnimationState();
                }
            }
        } else {
            float f11 = this.y.projectileGravity - object3.dT;
            if (this.eq < f11) {
                this.eq += 0.2f * f2;
                if (this.eq >= f11) {
                    this.eq = f11;
                }
            }
            if ((this.y.projectileGravity != 0.0f || object3.dT != 0.0f || this.eq > 0.0f) && this.cO == null) {
                float f12 = this.y.projectileGravity;
                if (object3.dT != 0.0f) {
                    this.maxSpeed += 2.0f * f2;
                    if (this.maxSpeed > 360.0f) {
                        this.maxSpeed -= 360.0f;
                    }
                    f12 += GameUtils.sinFast(this.maxSpeed) * object3.dT;
                }
                f5 = object3.dU < 0.0f ? GameUtils.c(this.eq - 2.0f) * 0.05f * 0.4f + 0.2f : object3.dU;
                f4 = GameUtils.c(this.eq - f12) * 0.05f * 0.3f + 0.1f;
                f5 = GameUtils.b(f5, f4);
                this.eq = GameUtils.a(this.eq, f12, f5 * f2);
                boolean bl7 = false;
                if (this.eq > this.y.projectileGravity + object3.dT + 1.0f) {
                    this.f += object3.dV * f2;
                    if (this.eq < 0.0f) {
                        this.f = GameUtils.b(this.f, 0.2f);
                    }
                    this.eq -= this.f * f2;
                    if ((double)this.f > 1.5) {
                        this.dq += f2;
                        if ((double)this.dq > 0.5) {
                            this.dq = 0.0f;
                            object = l3.bR.b(this.eo + GameUtils.c(-this.cj, this.cj), this.ep + GameUtils.c(-this.cj, this.cj), this.eq, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, DrawLayer.c);
                            if (object != null) {
                                ((HUDElement)object).aq = 0;
                                ((HUDElement)object).ap = 0;
                                ((HUDElement)object).ar = (short)2;
                                ((HUDElement)object).r = true;
                                ((HUDElement)object).s = true;
                                ((HUDElement)object).t = 40.0f;
                                ((HUDElement)object).an = true;
                                ((HUDElement)object).P = 0.1f;
                                ((HUDElement)object).R = 0.0f;
                                ((HUDElement)object).u = true;
                                ((HUDElement)object).E = 0.4f;
                                ((HUDElement)object).V = ((HUDElement)object).W = 380.0f;
                                ((HUDElement)object).G = 0.8f;
                                ((HUDElement)object).F = 1.7f;
                                ((HUDElement)object).as = false;
                                ((HUDElement)object).P += GameUtils.c(-0.04f, 0.04f);
                                ((HUDElement)object).Q += GameUtils.c(-0.04f, 0.04f);
                            }
                        }
                    }
                    if (this.eq <= this.y.projectileGravity + object3.dT + 1.0f) {
                        if (this.f > 2.0f) {
                            bl7 = true;
                        }
                        if (this.eq < this.y.projectileGravity + object3.dT) {
                            this.eq = this.y.projectileGravity + object3.dT;
                        }
                        this.f = 0.0f;
                    }
                } else {
                    if (this.f > 2.0f) {
                        bl7 = true;
                    }
                    this.f = 0.0f;
                }
                if (bl7 && (object = l3.bR.c(this.eo, this.ep, this.eq, 0)) != null) {
                    ((HUDElement)object).G = 0.8f;
                    ((HUDElement)object).F = 1.4f;
                    ((HUDElement)object).W = ((HUDElement)object).V = 60.0f;
                }
            }
        }
        boolean bl8 = false;
        boolean bl9 = false;
        if (object3.bg) {
            bl9 = true;
        }
        if (this.z != null && this.z.bg) {
            bl9 = true;
        }
        if (bl9) {
            GameAction s2 = this.dL.d();
            boolean bl10 = false;
            if (s2 != null) {
                if (s2 instanceof com.corrodinggames.rts.game.units.custom.actions.g) {  // 02b L1666: custom.a.g
                    com.corrodinggames.rts.game.units.UnitTypeHandle as2;
                    com.corrodinggames.rts.game.units.custom.actions.g g2 = (com.corrodinggames.rts.game.units.custom.actions.g) s2;
                    object = g2.a;
                    boolean bl11 = false;
                    bl10 = g2.L();
                    if (((com.corrodinggames.rts.game.units.custom.actions.d) object).costResourceType != null && (as2 = ((com.corrodinggames.rts.game.units.custom.actions.d) object).costResourceType.c()) != null && as2 instanceof ModUnitRegistry) {  // 02b L1671-1673: var41.l.c() instanceof l
                        bl8 = true;
                        if (as2 != object3) {
                            if (this.z != null) {
                                PlayerState.b((UnitInstance) this);
                                this.a(this.z, false, false, this.A);
                                this.z = null;
                                this.A = null;
                                object3 = this.x;
                                PlayerState.c(this);
                            }
                            PlayerState.b((UnitInstance) this);
                            this.z = object3;
                            this.A = ((com.corrodinggames.rts.game.units.custom.actions.d) object).actionScript;
                            this.a((ModUnitRegistry) as2, false, false, ((com.corrodinggames.rts.game.units.custom.actions.d) object).actionScript);
                            object3 = this.x;
                            PlayerState.c(this);
                        }
                    }
                    if (((com.corrodinggames.rts.game.units.custom.actions.d) object).W != null) {
                        Object object4;
                        float f13;
                        float f14 = ((com.corrodinggames.rts.game.units.custom.actions.d) object).W.floatValue();
                        if (((com.corrodinggames.rts.game.units.custom.actions.d) object).Z) {
                            f13 = this.eo;
                            float f15 = this.ep;
                            object4 = this.dL.b();
                            if (object4 != null) {
                                float f16 = Float.MIN_VALUE;
                                float f17 = Float.MIN_VALUE;
                                if (((com.corrodinggames.rts.game.units.commands.BuilderUnit)object4).targetUnit != null) {
                                    f16 = ((com.corrodinggames.rts.game.units.commands.BuilderUnit)object4).targetUnit.eo;  // 02b L1706: var17.i.eo
                                    f17 = ((com.corrodinggames.rts.game.units.commands.BuilderUnit)object4).targetUnit.ep;
                                } else if (((com.corrodinggames.rts.game.units.commands.BuilderUnit)object4).buildPosition != null) {
                                    f16 = ((com.corrodinggames.rts.game.units.commands.BuilderUnit)object4).buildPosition.a;  // 02b L1709: var17.h.a
                                    f17 = ((com.corrodinggames.rts.game.units.commands.BuilderUnit)object4).buildPosition.b;
                                }
                                if (f16 > Float.MIN_VALUE) {
                                    float f18 = GameUtils.d(f13, f15, f16, f17);
                                    f14 += f18;
                                }
                            }
                        }
                        if (((com.corrodinggames.rts.game.units.custom.actions.d) object).aa == null) {
                            f13 = this.a(f2, f14, true, ((com.corrodinggames.rts.game.units.custom.actions.d) object).X);
                        } else {
                            int n4 = ((com.corrodinggames.rts.game.units.custom.actions.d) object).aa.e;
                            f13 = this.a(f2, f14, n4);
                            object4 = this.cL[n4];
                            ((UnitTurret) object4).b(5);
                            ((UnitTurret) object4).turretOffsetY = ((UnitTurret) object4).turretAngle;
                        }
                        if (((com.corrodinggames.rts.game.units.custom.actions.d) object).Y && GameUtils.c(f13) > 5.0f) {
                            bl11 = true;
                        }
                    }
                    if (((com.corrodinggames.rts.game.units.custom.actions.d) object).V != null && !bl11) {
                        this.b.a(((com.corrodinggames.rts.game.units.custom.actions.d) object).V.b(), 10);
                    }
                    if (bl11) {
                        this.dL.e = 0.0f;
                    }
                }
                if (object3.dy != null && s2.i() != null && this.dL.e >= object3.dy.q) {
                    this.b.a(object3.dy, 5);
                }
            }
            this.isInitialized = bl10;
            if (this.isInitialized) {
                this.cc = 0.0f;
                this.cd = 0.0f;
                this.cf = 0.0f;
            }
        }
        if (this.z != null && !bl8) {
            PlayerState.b((UnitInstance) this);
            this.a(this.z, false, false, this.A);
            this.z = null;
            this.A = null;
            object3 = this.x;
            PlayerState.c(this);
        }
        this.b.a(f2);
        this.detachUnit(f2, bl2);
        object3 = this.x;
    }


    public float cy() {
        int n2 = this.x.co.b;
        if (!this.p) {
            return 0.0f;
        }
        return (float)n2 * this.x.cs;
    }


    public com.corrodinggames.rts.game.units.custom.effects.EffectManager cz() {  // 02b j.cz() L1778: custom.e.f
        if (!this.p) {
            return com.corrodinggames.rts.game.units.custom.effects.EffectManager.a;
        }
        return this.x.cp;
    }


    public com.corrodinggames.rts.game.units.custom.effects.EffectManager cA() {  // 02b j.cA() L1782
        if (!this.p) {
            return com.corrodinggames.rts.game.units.custom.effects.EffectManager.a;
        }
        return this.x.cq;
    }


    public boolean a(int n2, UnitInstance am2, boolean bl2, boolean bl3) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return this.a(bn2, n2, am2, bl2, bl3);
    }

    public final boolean a(ModUnitLoader bn2, int n2, float f2, float f3, boolean bl2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f4 = GameUtils.a(this.eo, this.ep, f2, f3);
        if (f4 > bn2.ae) {
            if (this.player == l2.bs) {
                l2.bS.b("Location too far");
            }
            return false;
        }
        if (f4 < bn2.ah) {
            if (this.player == l2.bs) {
                l2.bS.b("Location too close");
            }
            return false;
        }
        return true;
    }

    public final boolean a(ModUnitLoader bn2, int n2, UnitInstance am2, boolean bl2, boolean bl3) {
        float f2;
        float f3;
        float f4;
        if (!bl2 && (bn2.I || bl3)) {
            f4 = GameUtils.a(this.eo, this.ep, am2.eo, am2.ep);
            if (f4 > bn2.ae) {
                return false;
            }
            if (f4 < bn2.ah) {
                return false;
            }
        }
        if (!bn2.H) {
            return true;
        }
        if (bn2.ai != -1.0f && GameUtils.c(f3 = GameUtils.c(f4 = bn2.w != -1 ? this.cL[bn2.w].turretAngle + bn2.j : this.cg + bn2.j, f2 = GameUtils.d(this.eo, this.ep, am2.eo, am2.ep), 360.0f)) > bn2.ai) {
            return false;
        }
        if (bn2.N != null && !bn2.N.read(this)) {
            return false;
        }
        if (bn2.O != null && !com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(bn2.O, am2.getStatusEffects())) {
            return false;
        }
        if (bn2.P != null && com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(bn2.P, am2.getStatusEffects())) {
            return false;
        }
        if (am2.i()) {
            if (bn2.J != null) {
                return bn2.J.read(this);
            }
            return true;
        }
        if (am2.Q()) {
            if (bn2.L != null) {
                return bn2.L.read(this);
            }
            return true;
        }
        if (bn2.M != null && !bn2.M.read(this) && !am2.onCloakStateChanged()) {
            return false;
        }
        if (bn2.K != null) {
            return bn2.K.read(this);
        }
        return true;
    }


    public void a(UnitInstance am2, int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        if (!bn2.B) {
            return;
        }
        if (!this.a(bn2, n2, am2, false, false)) {
            return;
        }
        for (int i2 = 0; i2 < this.x.fQ.length; ++i2) {
            ModUnitLoader bn3 = this.x.fQ[i2];
            if (bn3 == null || i2 == n2 || bn3.ao == null || bn3.ao != bn2 || !(this.cL[i2].shootCooldown < 9000.0f - (this.b(n2) * 0.5f - bn3.n))) continue;  // 02b L1851: this.b(var2)
            this.cL[i2].shootCooldown = 0.0f;
        }
        if (bn2.w != -1) {
            ModUnitLoader bn4 = this.x.fQ[bn2.w];
            if (!bn4.B && bn4.p != 0.0f) {
                this.cL[bn2.w].shootCooldown = this.b(bn2.w) + this.t(bn2.w);  // 02b L1859: this.b(var3.w) + this.t(var3.w)
            }
        }
        this.b.a(this.x.du, 11, true);
        this.detachUnit(bn2);
        this.a(am2, -1.0f, -1.0f, n2, null, 0);
    }

    public boolean a(ModUnitLoader bn2) {
        if (bn2.u > 0.0f) {
            if (bn2.u > this.cB) {
                return false;
            }
            if (this.v) {
                return false;
            }
        }
        return bn2.v == null || bn2.v.b(this);
    }

    public void detachUnit(ModUnitLoader bn2) {
        if (bn2.u > 0.0f) {
            this.cB -= bn2.u;
            if (this.cB < bn2.u && this.x.cR) {
                this.v = true;
            }
        }
        if (bn2.v != null) {
            bn2.v.a(this);
        }
    }

    public static MovementController a(UnitInstance am2, int n2, bh bh2, float f2, float f3, float f4, float f5) {  // 02b bh=ProjectileTemplate (03 濠电儑绲藉ú锔炬崲閸愵亖鍋撻崹顐ｇ叆閼挎劙姊洪崹顕呭剳闁崇懓顑夐弻?
        MovementController f6 = com.corrodinggames.rts.game.MovementController.a(am2, f2, f3, f4, n2);  // 02b L1899: game.f.a
        CustomUnitType.a(f6, am2, n2, bh2, f2, f3, f4, f5);
        return f6;
    }

    public static void a(MovementController f2, UnitInstance am2, int n2, bh bh2, float f3, float f4, float f5, float f6) {
        Object object;
        UnitType y2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        f2.az = f6;
        f2.aT = f6;
        if (am2 == null) {
            throw new RuntimeException("Source cannot be null");
        }
        f2.g = bh2;
        f2.G = bh2.aI;
        f2.aR = bh2.aJ;
        f2.U = bh2.b;
        f2.Y = bh2.c;
        if (!bh2.aN && am2 instanceof CustomUnitType) {
            y2 = (CustomUnitType) am2;
            f2.U *= ((CustomUnitType) y2).y.reloadMultiplier;
            f2.Y *= ((CustomUnitType) y2).y.reloadMultiplier;
        }
        f2.Z = bh2.i;
        if (bh2.l) {
            f2.aa = false;
            f2.ab = true;
        } else {
            f2.aa = !bh2.k;
        }
        f2.ac = bh2.n;
        if (bh2.m) {
            f2.ad = true;
            f2.ae = false;
        }
        f2.D = bh2.p;
        f2.aY = bh2.q;
        f2.aZ = bh2.r;
        if ((double)bh2.o < 0.5) {
            f2.C = true;
        } else {
            f2.H = bh2.o;
        }
        f2.h = bh2.v;
        f2.i = bh2.u;
        f2.t = bh2.w;
        if (bh2.aM != 0.0f) {
            f2.t += (float)GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) am2, (int)(-bh2.aM * 100.0f), (int)(bh2.aM * 100.0f), 1) / 100.0f;  // 02b L1949: f.a(var1,int,int,int) cast 濠电偞鍨甸悧濠囶敆?
        }
        if (bh2.T && n2 != -1) {
            f2.au = am2;
            if (am2 instanceof UnitType) {
                y2 = (UnitType) am2;
                f2.av = n2;
                object = y2.D(n2);
                f2.aw = ((com.corrodinggames.rts.gameFramework.utility.ai)object).a;
                f2.ax = ((com.corrodinggames.rts.gameFramework.utility.ai)object).b;  // 02b ai.b
                f2.ay = am2.eq + ((com.corrodinggames.rts.gameFramework.utility.ai)object).c;  // 02b ai.c
            } else {
                f2.aw = am2.eo;
                f2.ax = am2.ep;
                f2.ay = am2.eq;
            }
        }
        f2.w = bh2.D;
        f2.u = bh2.E;
        f2.v = bh2.F;
        f2.af = bh2.aO;
        f2.ag = bh2.aP;
        f2.ah = bh2.aQ;
        f2.ai = bh2.aR;
        f2.ak = bh2.aS;
        f2.al = bh2.aT;
        f2.am = bh2.aU;
        f2.an = bh2.aV;
        if (bh2.aW > 0.0f) {
            f2.ao = true;
            f2.X = f2.W = bh2.aW;
        }
        f2.ar = bh2.aE;
        if (bh2.aG != 0.0f) {
            float f7 = bh2.aH;
            int n3 = Color.a(f2.ar);
            int n4 = (int)((float)Color.b(f2.ar) * f7);
            int n5 = (int)((float)Color.c(f2.ar) * f7);
            int n6 = (int)((float)Color.d(f2.ar) * f7);
            int n7 = am2.player.getMaxUnitCapacity();
            n4 = (int)((float)n4 + (float)Color.b(n7) * bh2.aG);
            n5 = (int)((float)n5 + (float)Color.c(n7) * bh2.aG);
            n6 = (int)((float)n6 + (float)Color.d(n7) * bh2.aG);
            n4 = GameUtils.b(n4, 0, 255);
            n5 = GameUtils.b(n5, 0, 255);
            n6 = GameUtils.b(n6, 0, 255);
            f2.ar = Color.a(n3, n4, n5, n6);
        }
        f2.P = bh2.x;
        f2.R = bh2.y;
        f2.S = !bh2.A;
        f2.Q = bh2.z;
        if (bh2.B != null) {
            f2.P = 0;
            f2.R = 0;
        }
        f2.x = bh2.aF;
        f2.m = bh2.s;
        f2.A = bh2.I;
        f2.M = bh2.V;
        f2.B = bh2.W;
        f2.aH = bh2.ae;
        f2.aG = bh2.aw;
        f2.aM = bh2.af;
        if (bh2.ai != null) {
            bh2.ai.a(f2.eo, f2.ep, f2.eq, f2.az, f2);
        }
        if (bh2.ao != -1) {
            HUDElement e2;
            boolean bl2 = false;
            object = f2.aP;
            if (object != null && ((HUDElement)object).b == f2 && ((HUDElement)object).d && object != null) {
                if (((HUDElement)object).V < 150.0f) {
                    ((HUDElement)object).V = 200.0f;
                }
                bl2 = true;
            }
            if (!bl2 && (e2 = l2.bR.a(f2, bh2.ao, bh2.ap)) != null) {
                if (bh2.aq) {
                    e2.c = true;
                }
                if (bh2.L) {
                    f2.aP = e2;
                }
            }
        }
        f2.aQ = bh2.ar;
        if (bh2.as != -1.0f) {
            f2.aI = bh2.as;
        }
        if (bh2.at != -1.0f) {
            f2.aJ = bh2.at;
        }
        f2.aL = -1.0f;
        if (bh2.au != -1.0f) {
            f2.r = bh2.au;
        }
        f2.s = bh2.av;
        if (bh2.aZ != null) {
            // empty if block
        }
        f2.aE = bh2.bd;
        f2.em = am2.em;
        if (f2.em < 4 && f5 >= -1.0f) {
            f2.em = 4;
        }
        if (bh2.U) {
            f2.em = 1;
        }
    }

    public MovementController a(UnitInstance am2, float f2, float f3, int n2, bh bh2, int n3) {
        Object object;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        ModUnitLoader bn2 = this.x.fQ[n2];
        if (bn2.aA != null) {
            this.b.a(bn2.aA.b(), 6, true);
        }
        if (bn2.aB > 0.0f) {
            this.b(bn2.aB);  // 02b L2088: this.b(var8.aB)
        }
        if (bn2.aC != null) {
            object = new PointF(f2, f3);
            bn2.aC.a(this, (PointF)object, am2, n3 + 1, 0);
        }
        object = bh2 == null ? this.x.fR[bn2.a(this)] : bh2;
        PointF pointF = this.K(n2);  // 02b L2103: this.K(var4)
        com.corrodinggames.rts.gameFramework.utility.ai ai2 = this.D(n2);  // 02b L2104: this.D(var4)
        if (bn2.aI > 0) {
            for (int i2 = 0; i2 < bn2.aI; ++i2) {
                if (this.B == null || this.B.size() <= 0) continue;
                UnitInstance am3 = (UnitInstance) this.B.remove(this.B.size() - 1);
                UnitTurret ap2 = this.cL[n2];
                PathfindingUtils.a(am3, this);
                am3.eo = ai2.a;
                am3.ep = ai2.b;
                am3.cg = ap2.turretAngle;
                if (!(am3 instanceof UnitType)) continue;
                UnitType y2 = (UnitType) am3;
                y2.az();
                if (am2 != null) {
                    y2.n(am2);  // 02b L2118
                    continue;
                }
                y2.e(f2, f3);
            }
        }
        MovementController f4 = null;
        if (((bh)object).L && bh2 == null) {
            if (this.G == null) {
                this.G = new MovementController[31];
            }
            if (this.G[n2] != null && !this.G[n2].ej) {
                f4 = this.G[n2];
                f4.a(this, ai2.a, ai2.b, this.eq + ai2.c);
                if (!((bh)object).N && f4.ap != null) {
                    f4.ap.clear();
                }
            }
        }
        this.bC = (int)((long)this.bC + (1L + this.eh));
        float f5 = this.cL[n2].turretAngle;
        boolean bl2 = false;
        if (f4 == null) {
            f4 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, ai2.a, ai2.b, this.eq + ai2.c, n2);  // 02b L? game.f.a
            if (((bh)object).L && bh2 == null) {
                this.G[n2] = f4;
            }
        } else {
            f4.g = (com.corrodinggames.rts.game.GameSettings) object;  // 02b L? var17.g = var16 (bh闂備焦鍓氶崑鍛叏閸楀攲e.g 濠电姰鍨奸崺鏍枈瀹ュ鐒?
            bl2 = true;
        }
        CustomUnitType.a(f4, this, n2, (bh)object, ai2.a, ai2.b, this.eq + ai2.c, f5);
        ((bh)object).a(this, f4, am2, f2, f3, this.m());
        if (!bl2 && ((bh)object).R == 0.0f && ((bh)object).S == 0.0f) {
            f4.K = pointF.a;
            f4.L = pointF.b;
        }
        if (bn2.G != null) {
            l2.bR.a(ai2.a, ai2.b, this.eq + ai2.c, bn2.G);
        }
        if (bn2.E != null) {
            bn2.E.a(ai2.a, ai2.b, this.eq + ai2.c, this.cL[n2].turretAngle, this);
        }
        if (bn2.D != null) {
            float f6 = 1.0f + GameUtils.c(-0.07f, 0.07f);
            bn2.D.a(ai2.a, ai2.b, f6);
        }
        if (this.x.eg) {
            this.R = null;
        }
        if (bn2.ay) {
            this.cL[n2].targetUnit = null;
        }
        if (this.x.bj && !this.isDead) {
            this.bv();  // 02b L2184: this.bv()
        }
        if (this.x.bl && !this.isDead) {
            this.a();
            this.isDead = true;
        }
        return f4;
    }


    public float getMaxMoveDistance() {
        return this.y.cooldownTime;
    }


    @Override
    public float m() {  // 02b j.m() L2195-2197: return this.y.i (WeaponConfig.cooldownTime)
        return this.y.cooldownTime;
    }

    public int y() {
        if (this.y.maxAmmoCount != -1) {
            return this.y.maxAmmoCount;
        }
        return super.y();  // 02b L2200: super.y()
    }


    public int u(UnitInstance am2) {
        int n2 = am2.r().a(this);
        if (this.x.aX != -1) {
            if (this.x.aY) {
                int n3 = (int)((float)this.x.aX + this.cj);
                if (am2 != null) {
                    n3 = (int)((float)n3 + am2.cj);
                }
                return n3 + n2;
            }
            return this.x.aX + n2;
        }
        return this.y() + n2;  // 02b L2217: this.y() + var2
    }


    public int v(UnitInstance am2) {
        int n2 = am2.r().a(this);
        if (this.x.aZ != -1) {
            if (this.x.ba) {
                int n3 = (int)((float)this.x.aZ + this.cj);
                if (am2 != null) {
                    n3 = (int)((float)n3 + am2.cj);
                }
                return n3 + n2;
            }
            return this.x.aZ + n2;
        }
        return this.y() + n2;  // 02b L2217: this.y() + var2
    }


    public boolean w(UnitInstance am2) {
        return this.x.ba;
    }


    public boolean x(UnitInstance am2) {
        return this.x.aY;
    }


    public float cx() {
        return this.y.targetLeadTime;
    }


    public float c(UnitInstance am2) {
        return this.x.bb;
    }


    public float z(UnitInstance am2) {
        boolean bl2;
        float f2 = this.x.bc;
        boolean bl3 = bl2 = am2.bd() > 0.0f;
        if (bl2) {
            f2 = am2.bd() * this.x.bd;
        }
        return f2;
    }


    public float f(UnitInstance am2) {
        return this.x.be;
    }


    public float b(UnitInstance am2) {
        return this.x.bf;
    }


    public float z() {
        return this.y.muzzleVelocity * this.turretScale;
    }


    public float aZ() {
        return this.x.ej;
    }


    public float ba() {
        return this.x.ek;
    }


    public float A() {
        return this.y.recoilForce;
    }


    public float c(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        if (bn2.U != null) {
            return bn2.U.floatValue();
        }
        return this.x.eb;
    }


    public float x(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.Q;
    }


    public float w(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.V;
    }


    public float y(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.W;
    }


    public float B() {
        return this.x.eo;
    }


    public float cD() {
        return this.x.bH * this.c;
    }


    public float getTurretTurnRate(int n2) {
        return this.x.bI;
    }


    public float d(boolean bl2) {
        if (!this.x.dB) {
            return 0.0f;
        }
        if (bl2 && this.x.dD) {
            return this.cL[this.x.dG].turretAngle + 90.0f;
        }
        if (this.x.dC) {
            return this.cL[this.x.dG].turretAngle + 90.0f;
        }
        return super.d(bl2);
    }


    public PointF cY() {
        PointF pointF = H;
        ModUnitRegistry l2 = this.x;
        if (l2.dC && this.cL[l2.dG].shootCooldown != 0.0f) {
            ModUnitLoader bn2 = l2.dF;
            if (bn2.p != 0.0f) {
                pointF.a(this.G(l2.dG));
                pointF.b(-this.eo, -this.ep);
                return pointF;
            }
        }
        pointF.a = 0.0f;
        pointF.b = 0.0f;
        return pointF;
    }


    public PointF aP() {
        ModUnitRegistry l2 = this.x;
        PointF pointF = this.cY();  // 02b L2361: this.cY()
        CustomUnitType.I.a = pointF.a + l2.cJ;
        CustomUnitType.I.b = pointF.b + l2.cK;
        return I;
    }


    public boolean c(float f2) {
        com.corrodinggames.rts.gameFramework.rendering.Texture e2;  // 02b L2469: m.e var22 = this.T()
        float f3;
        float f4;
        float f5;
        Object object;
        float f6;
        RectF object2;
        ModUnitRegistry l2 = this.x;
        boolean bl2 = this.isDead;
        if (this.dT != null && !bl2) {
            com.corrodinggames.rts.game.units.custom.animation.AnimationMovementCurve.a(this, f2, false, false);
        }
        GlobalState l3 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface y2 = l3.bO;  // 02b L2358: m.y var5 = var4.bO
        Paint paint = this.aN();
        float f7 = this.cD();
        PointF pointF = this.cY();  // 02b L2361
        this.aQ();
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = l2.i;
        int n2 = m2.a;
        if (n2 > 0) {
            Object[] objectArray2 = l2.i.a();  // 02b L2369: var11 = var2.i.a()
            for (int i2 = n2 - 1; i2 >= 0; --i2) {
                com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase a2 = (com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)objectArray2[i2];
                a2.d(this, f2);
            }
        }
        if (this.ew) {
            float f8 = this.eo + pointF.a - l3.cw;
            f6 = this.ep + pointF.b - l3.cx - this.eq;
            if (f7 != 1.0f) {
                y2.k();
                y2.a(f7, f7, f8, f6);
            }
            y2.a(this.M,  f8,  f6,  this.d(false) - 90.0f,  paint);  // 02b m/y.a(e,float,float,float,Paint) (D 濠电偞鍨堕幑鍥╃磽濮橀鏁嬮柕鍫濐槸鐟?v19.133f8)  // 02b L2390: var5.a(m.e,4f,Paint) = 03 D
            if (f7 != 1.0f) {
                y2.l();
            }
        } else {
            object2 = this.cF();  // 02b L2395: RectF var19 = this.cF()
            f6 = pointF.a;
            float f9 = pointF.b - this.eq;
            object2.a += f6;
            object2.b += f9;
            object2.c += f6;
            object2.d += f9;
            object = this.a_(false);  // 02b L2402: Rect var14 = this.a_(false)
            f5 = (object2.a + object2.c) * 0.5f;
            f4 = (object2.b + object2.d) * 0.5f;
            y2.k();
            if (f7 != 1.0f) {
                y2.a(f7, f7, f5, f4);
            }
            y2.a(this.d(false), f5, f4);
            y2.loadImageFromResource(this.M, (Rect)object, (RectF)object2, paint);  // 02b L2411: a(e,Rect,RectF,Paint)
            y2.l();
        }
        if (n2 > 0) {
            Object[] objectArray = l2.i.a();
            for (int i3 = n2 - 1; i3 >= 0; --i3) {
                com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase a3 = (com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)objectArray[i3];
                a3.e(this, f2);
            }
        }
        PathfindingUtils.a(this);
        if (this.dT != null && !bl2 && l2.ay) {
            com.corrodinggames.rts.game.units.custom.animation.AnimationMovementCurve.a(this, f2, true, false);
        }
        if (this.ak() && l2.fV != null && !bl2 && (f3 = this.cL[l2.fV.e].maxRotationAngle / this.e(l2.fV.e)) != 0.0f) {
            boolean bl3 = true;
            boolean bl4 = this.Y();
            if (bl4 && l2.bW) {
                bl3 = false;
            } else if (!bl4 && l2.bS) {
                bl3 = false;
            }
            if (bl3 && l2.fQ[l2.fV.e].aF == null) {
                object = this.bn();
                l3.bO.k();
                l3.bO.b(((com.corrodinggames.rts.gameFramework.utility.ai)object).a - l3.cw, ((com.corrodinggames.rts.gameFramework.utility.ai)object).b - ((com.corrodinggames.rts.gameFramework.utility.ai)object).c - l3.cx - this.eq);  // 02b ai.b/c
                l3.bO.a(f3, f3);
                if (bl4) {
                    l3.bO.a(com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.f, 0.0f, 0.0f, (Paint)null);
                } else {
                    l3.bO.a(com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.e, 0.0f, 0.0f, (Paint)null);
                }
                l3.bO.l();
            }
        }
        if (l2.fP && !bl2) {
            int n3 = this.bl();
            for (int i4 = 0; i4 < n3; ++i4) {
                float f10 = this.cL[i4].maxRotationAngle / this.e(i4);  // 02b L2460: this.e(var12)
                object = l2.fQ[i4];
                if (object == null || f10 == 0.0f || ((ModUnitLoader) object).aF == null) continue;
                PathfindingUtils.a(this, ((ModUnitLoader) object).aF, f10, i4);
            }
        }
        if (!bl2 && this.cx > 0.0f && this.cz == 0.0f && (e2 = this.T()) != null) {  // 02b L2469: this.T()
            float f11 = 0.0f;
            if (!l2.cU) {
                f11 += 0.09f;
                f11 += this.cx / this.cA * 0.4f;
                f11 += GameUtils.b(this.cy, 50.0f) / 50.0f * 0.5f;
            } else {
                f11 += GameUtils.b(this.cy, 50.0f) / 50.0f * 0.5f;
                float f12 = this.cy;
                if (f12 > 5.0f) {
                    f12 = 5.0f;
                }
                f11 += f12 / 5.0f * 0.2f;
            }
            if (f11 > 0.0f) {
                float f13;
                if (f11 > 1.0f) {
                    f11 = 1.0f;
                }
                if (this.sharedPaint == null) {
                    this.sharedPaint = PathfindingUtils.a();
                }
                Paint paint2 = this.sharedPaint;
                paint2.a((int)(f11 * 255.0f), 255, 255, 255);
                float f14 = this.eo - l3.cw;
                f5 = this.ep - l3.cx - this.eq;
                if (!l2.av) {
                    f13 = 87.0f;
                    f4 = (float)(l2.df * 2) / f13 * 1.25f;
                } else {
                    f13 = e2.p;
                    f4 = (float)(l2.df * 2) / f13 * 1.25f;
                }
                l3.bO.k();
                l3.bO.a(f4, f4, f14, f5);
                l3.bO.a(e2,  f14,  f5,  this.d(false) - 90.0f,  paint2);  // 02b m/y.a(e,float,float,float,Paint) (D 濠电偞鍨堕幑鍥╃磽濮橀鏁嬮柕鍫濐槸鐟?v19.133f8)  // 02b j.java L2500: var5.a(e,float,float,float,Paint)
                l3.bO.l();
            }
        }
        if (n2 > 0) {
            Object[] objectArray = l2.i.a();
            for (int i5 = n2 - 1; i5 >= 0; --i5) {
                com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase a4 = (com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)objectArray[i5];
                a4.c(this, f2);
            }
        }
        return true;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture T() {  // 02b j.T() L2528: m.e
        return this.x.au;
    }


    public float C() {
        return this.x.dN;
    }


    public float D() {
        return this.x.dO;
    }


    public boolean bi() {
        return this.x.dX;
    }


    public boolean bj() {
        return this.x.dY;
    }


    public boolean l() {
        return this.x.ep;
    }


    public boolean ag() {
        return this.x.er.read(this);
    }


    public boolean af() {
        return this.x.eq.read(this);
    }


    public boolean ae() {
        return this.x.es.read(this);
    }


    public boolean ah() {
        if (this.x.et == null) {
            return true;
        }
        return this.x.et.read(this);
    }


    public boolean k(UnitInstance am2) {
        ModUnitRegistry l2 = this.x;
        if (l2.eu) {
            if (l2.ev != null && !com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(l2.ev, am2.getStatusEffects())) {
                return false;
            }
            if (l2.ew != null && com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(l2.ew, am2.getStatusEffects())) {
                return false;
            }
            if (l2.ex) {
                boolean bl2 = false;
                for (int i2 = 0; i2 < l2.fQ.length; ++i2) {
                    ModUnitLoader bn2 = l2.fQ[i2];
                    if (bn2.P != null && com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(bn2.P, am2.getStatusEffects()) || bn2.O != null && !com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(bn2.O, am2.getStatusEffects())) continue;
                    bl2 = true;
                    break;
                }
                if (!bl2) {
                    return false;
                }
            }
        }
        if (am2.i()) {
            return this.af();
        }
        if (am2.Q()) {
            return this.ae();
        }
        if (!this.ah() && !am2.onCloakStateChanged()) {  // 02b j.java L2596: !this.ah() && !var1.cH()
            return false;
        }
        return this.ag();  // 02b j.java L2596: this.ag()
    }


    public boolean E() {
        return this.x.ey;
    }


    public float g(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.X;
    }


    public float z(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.ab;
    }


    public float A(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.ah;
    }


    public float B(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.j;
    }


    public float C(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        float f2 = bn2.w != -1 ? this.cL[bn2.w].turretAngle : this.cg;
        f2 = this.ci && (double)this.bc() > 0.95 ? (f2 += bn2.k) : (f2 += bn2.j);
        if (bn2.ar != 0.0f) {
            return 999.0f;
        }
        return f2;
    }


    public boolean bm() {
        return this.x.dL;
    }


    public float q(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        if (!bn2.B) {
            return 0.0f;
        }
        bh bh2 = this.x.fR[bn2.a(this)];
        float f2 = 0.0f;
        if (!bh2.s) {
            f2 += (float)bh2.b;
        }
        f2 += (float)bh2.c;
        if (!bh2.aN) {
            f2 *= this.y.reloadMultiplier;
        }
        return f2;
    }


    public float b(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.m * this.y.damageMultiplier;
    }


    public float e(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.n;
    }


    public float f(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.o;
    }


    public boolean s(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.s;
    }


    public float t(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        if (bn2.t == 0.0f || bn2.n == 0.0f) {
            return 0.0f;
        }
        return -(bn2.t * (this.cL[n2].maxRotationAngle / bn2.n));
    }


    public boolean r(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.B;
    }


    public void b(UnitInstance am2, int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        if (bn2.F != null) {
            PointF pointF = this.E(n2);
            bn2.F.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle, this);
        }
    }


    public boolean u(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        if (!this.a(bn2)) {
            return false;
        }
        return super.u(n2);  // 02b j.java L2706: super.u(var1)
    }


    public int s(UnitInstance am2) {
        return this.x.eB;
    }


    public boolean bO() {
        return this.x.eD;
    }


    public boolean bP() {
        return this.x.eE;
    }


    public float bN() {
        return this.y.b;
    }


    public boolean cG() {
        return this.x.aq;
    }


    public Rect a_(boolean bl2) {
        if (bl2 && !this.x.aq) {
            return super.a_(bl2);  // 02b j.java L2731: super.a_(var1)
        }
        if (this.isDead) {
            return super.a_(bl2);  // 02b j.java L2733: super.a_(var1)
        }
        ModUnitRegistry l2 = this.x;
        int n2 = this.a;
        int n3 = 0;
        DirectionConfig m2 = l2.dI;
        if (this.b.a != null && this.b.a.k != null) {
            m2 = this.b.a.k;
        }
        if (m2 != null) {
            float f2;
            float f3 = m2.b;
            if (f3 < 0.0f) {
                f3 = -f3;
                f2 = -this.cg;
                if (m2.a) {
                    f2 = -this.cL[l2.dG].turretAngle;
                }
            } else {
                f2 = this.cg;
                if (m2.a) {
                    f2 = this.cL[l2.dG].turretAngle;
                }
            }
            int n4 = (int)((f2 - m2.e - f3 * 0.5f) / f3);
            int n5 = (int)(360.0f / f3);
            if ((n4 %= n5) < 0) {
                n4 += n5;
            }
            if (m2.c > 0) {
                n2 += n4 * m2.c;
            }
            if (m2.d > 0) {
                n3 += n4 * m2.d;
            }
        }
        if (n2 >= l2.V) {
            n3 += n2 / l2.V;
            n2 %= l2.V;
        }
        return super.a(bl2, n2, n3);  // 02b j.java L2780: super.a(var1,var3,var4) = UnitInstance.a(Z,II)
    }


    public RectF cF() {
        RectF rectF = super.cF();  // 02b j.java L2785: super.cF()
        if (this.x.ak) {
            rectF.a(this.x.ah, (float)this.x.ai - this.x.aj);
        }
        return rectF;
    }


    public int bl() {
        if (this.x == null) {
            return 1;
        }
        return this.x.fQ.length;
    }


    public int v(int n2) {
        return this.x.fQ[n2].x;
    }


    public com.corrodinggames.rts.gameFramework.utility.ai F(int n2) {
        return this.a(n2, false);
    }


    public PointF G(int n2) {
        com.corrodinggames.rts.gameFramework.utility.ai ai2 = this.a(n2, false);
        CustomUnitType.K.a = ai2.a;
        CustomUnitType.K.b = ai2.b;  // 02b j.java L2808: K.b = var2.b (K=PointF, b 濠?ai 闂佽瀛╃粙鎺椼€冮崱娑辨晩鐎光偓閸曨偉袝?
        return K;
    }

    public com.corrodinggames.rts.gameFramework.utility.ai a(int n2, boolean bl2) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        ModUnitLoader bn2 = this.x.fQ[n2];
        if (bn2.w == -1) {
            f6 = this.eo;
            f5 = this.ep;
            f4 = 0.0f;
            f3 = this.cg;
        } else {
            if (bl2) {
                throw new RuntimeException("Turret can not be attached to turret that is not attached to the body");
            }
            com.corrodinggames.rts.gameFramework.utility.ai ai2 = this.a(bn2.w, true);
            f6 = ai2.a;
            f5 = ai2.b;
            f4 = ai2.c;
            f3 = this.cL[bn2.w].turretAngle;
        }
        if (this.cL[n2].shootCooldown > 0.0f && bn2.p != 0.0f) {
            float f7 = 0.0f;
            f2 = this.b(n2) + this.t(n2) - this.cL[n2].shootCooldown;  // 02b j.java L2839: this.b(var1)+this.t(var1)-this.cL[var1].e
            if (f2 < bn2.q) {
                f7 = f2 / bn2.q * bn2.p;
            } else if (f2 < bn2.q + bn2.r) {
                f7 = bn2.p - (f2 - bn2.q) / bn2.r * bn2.p;
            }
            if (f7 != 0.0f) {
                f6 += GameUtils.cosFast(this.cL[n2].turretAngle) * f7;
                f5 += GameUtils.sinFast(this.cL[n2].turretAngle) * f7;
            }
        }
        float f8 = bn2.f;
        f2 = bn2.g;
        float f9 = bn2.h;
        if (f8 != 0.0f || f2 != 0.0f) {
            float f10 = GameUtils.sinFast(f3);
            float f11 = GameUtils.cosFast(f3);
            f6 += f11 * f2 - f10 * f8;
            f5 += (f10 * f2 + f11 * f8) * bn2.i;
        }
        CustomUnitType.dK.a = f6;
        CustomUnitType.dK.b = f5;
        CustomUnitType.dK.c = f4 += f9;
        return dK;
    }


    public ArrayList N() {
        if (this.z != null) {
            return this.z.a(this.V());  // 02b j.java L2870: this.z.a(this.V())
        }
        return this.x.a(this.V());  // 02b j.java L2870: this.x.a(this.V())
    }


    public GameAction a(com.corrodinggames.rts.game.units.actions.ActionId c2) {  // 02 闂傚倷绀佽ぐ鐐裁归崶顒佸剨? a(a.c), a.c闂備焦鍓氶崑鍛櫠椤わ箹ionId
        ModUnitRegistry l2 = this.z != null ? this.z : this.x;
        return l2.a(c2);
    }


    public int V() {
        return this.x.cl;
    }


    public GameAction e(UnitTypeHandle as2) {
        return this.dL.b(as2);
    }


    public void a(GameAction s2, boolean bl2, PointF pointF, UnitInstance am2) {
        Object object;
        Object object2;
        if (s2 == com.corrodinggames.rts.game.units.projectiles.SpecialBuilding.i) {
            if (!bl2) {
                this.getTurretRangeExtension();
            } else {
                this.M();
            }
            return;
        }
        if (s2 == com.corrodinggames.rts.game.units.projectiles.SpecialBuilding.j) {
            if (!bl2) {
                this.M();
            }
            return;
        }
        if (!bl2) {
            if (pointF != null && !this.a(s2, pointF.a, pointF.b)) {
                return;
            }
            if (s2 instanceof g) {
                object2 = (g) s2;
                if (((g) object2).a.ax != null) {
                    object = com.corrodinggames.rts.gameFramework.GlobalState.B();
                    if (this.player == ((GlobalState)object).bs && !((GlobalState)object).I()) {
                        ((g) object2).a.ax.a();
                    }
                }
                if (((g) object2).a.at != null) {
                    ((g) object2).a.at.a(this.eo, this.ep, this.eq, this.cg, this);
                }
            }
        }
        if (bl2 && s2 instanceof g) {
            object2 = (g) s2;
            if (!((g) object2).a.M) {
                return;
            }
        }
        object2 = this.dL.a(s2, bl2, pointF, am2);
        if (!bl2) {
            if (object2 != null) {
                object = s2.P();
                this.a(com.corrodinggames.rts.game.units.custom.af.f, null, (UnitConfig)object, null);
            }
        } else if (object2 != null) {
            this.a(com.corrodinggames.rts.game.units.custom.af.g, null, s2.P(), null);
        }
    }


    public void a(GameAction s2, boolean bl2) {
        this.a(s2, bl2, null, null);
    }


    public void b(GameAction s2, boolean bl2) {
        this.dL.a(s2, bl2);
    }


    public void a(GameAction s2) {
        this.dL.a(s2);
    }


    public boolean a(GameAction s2, float f2, float f3) {
        if (s2 instanceof g) {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            g g2 = (g) s2;
            if (g2.a.ag != null && g2.a.ah == null) {
                if (g2.a.ag >= this.x.fQ.length) {
                    this.a("checkTargetedActionOrder: " + g2.a.ag + " larger than max turret size");
                    return true;
                }
                ModUnitLoader bn2 = this.x.fQ[g2.a.ag];
                boolean bl2 = true;
                if (!this.a(bn2, (int)g2.a.ag, f2, f3, bl2)) {
                    return false;
                }
                if (g2.a.al != null && PathfindingUtils.a(f2, f3, g2.a.al)) {
                    if (this.player == l2.bs) {
                        l2.bS.b("Invalid map location (Must be passable by " + g2.a.al.name() + ")");
                    }
                    return false;
                }
            }
        }
        return true;
    }


    public void a(GameAction s2, boolean bl2, float f2, float f3) {
        GameAction s3;
        if (s2 instanceof g && (s3 = this.a(s2.N())) != null) {
            g g2 = (g) s3;  // 02b j.java: 缂傚倷绶￠崑澶愵敋瑜旈幃?custom/g (ActionFactory 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
            Integer n2 = g2.a.ag;
            if (n2 != null && g2.a.ah == null && n2 < this.x.fQ.length) {
                ModUnitLoader bn2 = this.x.fQ[n2];
                if (bn2.ag > 0.0f) {
                    PathfindingUtils.b((UnitInstance) this, bn2.ag, true);
                }
                PathfindingUtils.a((UnitInstance) this, bn2.ad, true, true);
            }
            if (bl2 && g2.a.am != null) {
                g2.a.am.a(this, f2, f3);
            }
        }
        super.a(s2, bl2, f2, f3);
    }

    public boolean a(GameAction s2, PointF pointF, UnitInstance am2, int n2, int n3) {
        PointF pointF2 = null;
        UnitInstance am3 = null;
        int n4 = 0;
        if (n2 > 0) {
            pointF2 = sharedRenderPoint;
            am3 = selectedUnitRef;
            n4 = selectionIndex;
        }
        sharedRenderPoint = pointF;
        selectedUnitRef = am2;
        selectionIndex = n3;
        boolean bl2 = this.a(s2, pointF, am2, n2);
        sharedRenderPoint = pointF2;
        selectedUnitRef = am3;
        selectionIndex = n4;
        return bl2;
    }

    public boolean a(GameAction s2, PointF objectArray, UnitInstance am2, int n2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (n2 > 10) {
            return false;
        }
        s2.a((UnitInstance) this, am2);
        if (s2 instanceof g) {
            float f2;
            float f3;
            int n3;
            Object object;
            g g2 = (g) s2;
            d d2 = g2.a;
            if (d2.actionHotkey != null && !d2.actionHotkey.read(this)) {
                return true;
            }
            boolean bl2 = false;
            if (d2.ad != null) {
                this.cB += g2.a.ad.floatValue();
                bl2 = true;
            }
            if (d2.ae != null) {
                d2.ae.h(this);
                bl2 = true;
            }
            if (d2.af != null) {
                d2.af.a((UnitInstance) this, (double)this.player.getExpenseRate(), true);  // 02b j.java L3056: var7.af.a(this, (double)this.bX.E(), true) 闂?n.E()=03 getExpenseRate() 闂傚倷绀佽ぐ鐐裁归崶顒佸剨?
                bl2 = true;
            }
            if (d2.aH != null) {
                if (d2.aH.read(this)) {
                    this.bA = l2.by;
                }
                bl2 = true;
            }
            if (d2.ag != null) {
                object = objectArray;
                for (n3 = 0; n3 < d2.ak; ++n3) {
                    if (d2.ah != null) {
                        object = new PointF();
                        if (d2.ai == null) {
                            ((PointF)object).a(this.eo, this.ep);
                        } else {
                            UnitInstance am3 = d2.ai.readUnit(this);
                            if (am3 != null) {
                                ((PointF)object).a(am3.eo, am3.ep);
                            } else {
                                ((PointF)object).a(this.eo, this.ep);
                            }
                        }
                        float f4 = GameUtils.cosFast(this.cg);
                        float f5 = GameUtils.sinFast(this.cg);
                        f3 = d2.ah.a;
                        f2 = d2.ah.b;
                        float f6 = f4 * f2 - f5 * f3;
                        float f7 = f5 * f2 + f4 * f3;
                        ((PointF)object).b(f6, f7);
                    }
                    if (object == null) {
                        com.corrodinggames.rts.gameFramework.network.NetEngine.sendPacketToClients("completeQueueItem:" + g2.N() + " for fireTurretXAtGround needs points but it is missing");
                        continue;
                    }
                    this.a(null, ((PointF)object).a, ((PointF)object).b, g2.a.ag, g2.a.aj, n2);
                }
                bl2 = true;
            }
            if (d2.as != null) {
                d2.as.a(this.eo, this.ep, this.eq, this.cg, this);
                bl2 = true;
            }
            if (d2.au != null) {
                d2.au.a(this.eo, this.ep, 1.0f);
                bl2 = true;
            }
            if (d2.av != null && !l2.I()) {
                d2.av.a();
                bl2 = true;
            }
            if (d2.aw != null) {
                if (this.player == l2.bs && !l2.I()) {
                    d2.aw.a();
                }
                bl2 = true;
            }
            if (d2.ac.a > 0) {
                Object[] objectArray3 = d2.ac.a();  // 02b j.java L3132: Object[] var17
                for (n3 = 0; n3 < d2.ac.a; ++n3) {
                    a a2 = (a) objectArray3[n3];  // 02b j.java L3135: custom/a/a var20 = 03 actions/a 闂備胶顢婂▔娑㈠焵椤掑倸鏋欓柡澶夌矙閺屾盯鍩￠崟顒変哗闁?(RangeValue 濠电偞鍨堕幐鎼佸箹椤愩儯浜归柣鏂挎憸閳绘棃鏌嶈閸撴盯鎮ィ鍐ㄥ強闊浄绲藉▓鈺呮⒑?
                    if (!a2.a(this, s2, (PointF)objectArray, am2, n2)) continue;
                    bl2 = true;
                }
            }
            object = objectArray;
            UnitInstance am4 = am2;
            if ((d2.ap != null || g2.a.aq != null) && d2.an != null) {
                am4 = d2.an.readUnit(this);
                object = new PointF();
                if (am4 != null) {
                    ((PointF)object).a = am4.eo;
                    ((PointF)object).b = am4.ep;
                } else {
                    ((PointF)object).a = this.eo;
                    ((PointF)object).b = this.ep;
                }
            }
            if (d2.ap != null) {
                if (d2.ao == null || d2.ao.read(this)) {
                    int n4 = 1;
                    if (d2.ar != null && (n4 = (int)d2.ar.readNumber(this)) > 10000) {
                        n4 = 10000;
                    }
                    for (int i2 = 0; i2 < n4; ++i2) {
                        g2.a.ap.a(this, (PointF)object, am4, n2 + 1, i2);
                    }
                }
                bl2 = true;
            }
            if (g2.a.aq != null) {
                if (d2.ao == null || d2.ao.read(this)) {
                    g2.a.aq.a(this, (PointF)object, am4);
                }
                bl2 = true;
            }
            UnitTypeHandle as2 = null;  // 02b j.java L3182: units.as var24 = null (PacketBuilder 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
            if (d2.H != null) {
                as2 = d2.H.c();
            }
            if (as2 != null) {
                if (com.corrodinggames.rts.gameFramework.GlobalState.aw) {
                    String string = this.c() + ": converting unit: " + s2.getActionIdString();  // 02b j.java L3189: this.c() (String 闂佽崵濮撮鍛村疮閹惰姤鍎婃い鏍仜鐟? getTurretFireCooldownOverride 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
                    com.corrodinggames.rts.gameFramework.GlobalState.b(string);
                }
                if (!(as2 instanceof ModUnitRegistry)) {
                    UnitInstance am5 = as2.a();
                    am5.eo = this.eo;
                    am5.ep = this.ep;
                    if (!am5.isFactoryBuilding()) {
                        am5.cg = this.cg;
                    }
                    am5.f(this.player);
                    am5.accept_B(null);
                    f3 = this.maxHp;
                    f2 = am5.cv;
                    if (f3 == 0.0f) {
                        am5.o(am5.cv);
                    } else {
                        am5.o(this.hp / f3 * f2);
                    }
                    if (this.cG) {
                        com.corrodinggames.rts.gameFramework.GlobalState.B().bS.k(am5);
                    }
                    PlayerState.c(am5);
                    this.ci();
                } else {
                    UnitConfig h2 = null;
                    if (d2.Q) {
                        h2 = this.de();
                    }
                    PlayerState.b((UnitInstance) this);
                    this.z = null;
                    this.a((ModUnitRegistry) as2, false, false, d2.R);
                    if (h2 != null) {
                        this.a(h2, true);
                    }
                    this.S();
                    this.dL.e();
                    this.bB = com.corrodinggames.rts.gameFramework.GlobalState.B().by;
                    PlayerState.c(this);
                }
                bl2 = true;
                if (!g2.B().c()) {
                    this.W();
                }
            }
            if (!bl2 && d2.actionFlags) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("completeQueueItem:" + g2.N() + " had no effect (but should have)");
            }
            return true;
        }
        return false;
    }

    @Override
    public void b(com.corrodinggames.rts.game.units.commands.BuilderUnit j2) {
        GameAction s2 = this.a(j2.j);
        if (s2 != null && s2 instanceof g) {
            g g2 = (g) s2;
            d d2 = g2.a;
            if (d2.ab != null) {
                CustomUnitType.ec.a = this.eo;
                CustomUnitType.ec.b = this.ep;
                PointF pointF = CustomUnitType.ec;
                UnitInstance am2 = null;
                d2.ab.a(this, pointF, am2, 0, 0);
            }
        }
    }

    @Override
    public boolean c(com.corrodinggames.rts.game.units.commands.BuilderUnit j2) {
        return true;
    }

    public void setQueuePaused(boolean bl2) {
        this.dL.a(bl2);
    }

    // v19.112 闂佽崵鍋炵粙蹇涘磿闁秴鐭?(javap 闂傚倷绀佽ぐ鐐裁归崶顒佸剨? custom.j.h() 闂佸搫顦弲婊堝蓟閵娿儍?units.ao; 02 CFR 闂備礁鎼崐浠嬶綖婢跺本鍏滈柤绋跨仛婵?return this.x.fh)
    public com.corrodinggames.rts.game.units.MovementTypeEnum ejectAllTransported() {
        return this.x.fh;
    }

    // v19.112 闂佽崵鍋炵粙蹇涘磿闁秴鐭?(javap 闂傚倷绀佽ぐ鐐裁归崶顒佸剨? custom.j.i() boolean; 02 CFR 闂備礁鎼崐浠嬶綖婢跺本鍏滈柤绋跨仛婵?x.fh==ao.d ? eq>=4 : false)
    public boolean setQueuePaused() {
        return this.x.fh == com.corrodinggames.rts.game.units.MovementTypeEnum.d ? this.eq >= 4.0F : false;
    }

    @Override
    public void a(com.corrodinggames.rts.game.units.commands.BuilderUnit j2) {
        float f2;
        UnitInstance am2;
        boolean bl2;
        GameAction s2 = this.a(j2.j);
        if (s2 != null && (bl2 = this.a(s2, j2.buildPosition, j2.targetUnit, 0, 0))) {  // 02b j.java L3281: this.a(var2, var1.h, var1.i, 0, 0) 闂?h=buildPosition, i=targetUnit
            return;
        }
        float f3 = 0.0f;
        if (this.x.aH && this.x.dk != null) {
            f3 = this.cg + this.x.dk.floatValue();
            f3 += 90.0f;
        }
        if ((am2 = this.dL.a(j2, f2 = this.x.dn != null ? this.x.dn.floatValue() : (this.dL.b != null ? this.cj * 3.0f : this.cj * 2.0f), this.isWaterUnit, f3)) != null) {  // 02b L3296: this.dL.b != null (PointF, factoryReference 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
            this.F(am2);
            PlayerState.c(am2);
            this.a(com.corrodinggames.rts.game.units.custom.af.e, am2);
        }
    }

    public void getMuzzlePoint(UnitInstance am2) {
        float f2 = 0.0f;
        if (this.x.aH && this.x.dk != null) {
            f2 = this.cg + this.x.dk.floatValue();
            f2 += 90.0f;
        }
        am2.cg = 90.0f + f2;
        float f3 = 70.0f;
        if (this.x.dn != null) {
            f3 = this.x.dn.floatValue();
        }
        this.dL.a(am2, f3, this.isWaterUnit);
    }

    public void getTurretBasePosition(UnitInstance am2) {
        am2.eo = this.eo + this.x.di;
        am2.ep = this.ep + this.x.dj;
        if (!this.x.aH) {
            float f2 = 180.0f;
            if (this.x.dk != null) {
                f2 = this.x.dk.floatValue();
            }
            float f3 = 70.0f;
            if (this.x.dn != null) {
                f3 = this.x.dn.floatValue();
            }
            float f4 = 7.0f;
            boolean bl2 = com.corrodinggames.rts.game.units.projectiles.SpecialBuilding.m(this, am2, this.isWaterUnit, f4, f2, f3, this.x.di, this.x.dj);  // 02b e/i.java L163: a(am,am,boolean,float闂?) 闂?03 m 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄渚剱闁哄棭鍙冮弻?(闂備礁鎼崐浠嬶綖婢跺本鍏滈柤绋跨仛婵挳鏌熼崜褍鍔﹂柛瀣崌楠炲洭顢涢敐鍡樻澑濠电偞鍨堕幐鎾磻閹剧粯鐓?
            if (!this.x.dm) {
                am2.eq = this.eq;
            }
            am2.eq += this.x.dl;
            if (am2 instanceof CustomUnitType) {
                ((CustomUnitType) am2).dF();
            }
            if ((this.setQueuePaused() || !bl2 || this.x.eU.read(this)) && this.cr()) {
                this.C(am2);  // 02b j.java L3353: this.C(var1)
            }
        }
        this.isWaterUnit = !this.isWaterUnit;
    }


    public CustomActionBase by() {
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = this.dL.g();
        int n2 = m2.size();
        if (n2 == 0) {
            return CustomActionBase.a;
        }
        CustomActionBase b2 = new CustomActionBase();
        Object[] objectArray = m2.a();
        for (int i2 = 0; i2 < n2; ++i2) {
            CustomActionBase b3;
            com.corrodinggames.rts.game.units.commands.BuilderUnit j2 = (com.corrodinggames.rts.game.units.commands.BuilderUnit)objectArray[i2];
            GameAction s2 = this.a(j2.j);
            if (s2 == null || !(s2 instanceof g)) continue;
            g g2 = (g) s2;
            if (g2.a.ad != null) {
                b2.c += g2.a.ad.floatValue();
            }
            if (g2.a.ae != null && !(b3 = g2.a.ae).c()) {
                b2 = CustomActionBase.a(b2, b3);
            }
            if (g2.a.af == null || (b3 = g2.a.af).c()) continue;
            b2 = CustomActionBase.a(b2, b3);
        }
        return b2;
    }

    @Override
    public boolean dA() {
        if (this.x.eM > 0) {
            return this.dI() > this.x.eM;
        }
        return false;
    }

    @Override
    public int h(UnitTypeHandle as2) {
        return this.dL.a(as2);
    }

    @Override
    public int f(boolean bl2) {
        return this.dL.a(com.corrodinggames.rts.game.units.actions.GameAction.i, bl2, true);
    }


    public int a(com.corrodinggames.rts.game.units.actions.ActionId c2, boolean bl2) {  // 02 闂傚倷绀佽ぐ鐐裁归崶顒佸剨? a(a.c,boolean)
        return this.dL.a(c2, bl2);
    }

    @Override
    public com.corrodinggames.rts.game.units.commands.BuilderUnit dw() {
        return this.dL.b();
    }


    public CustomActionBase bD() {
        return this.dL.c();
    }


    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList dx() {
        return this.dL.c;
    }

    @Override
    public void dz() {
        this.dL.e = 1.0f;
    }

    @Override
    public boolean dy() {
        return this.dL.a();
    }


    @Override
    public void a(PointF pointF) {
        if (this.x.dc) {
            this.dL.b = pointF;
        }
    }


    public float x() {
        if (!this.x.t) {
            return -1.0f;
        }
        return super.x();  // 02b j.java L3447: super.x() (getTurretDamage 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
    }


    public boolean bU() {
        return this.x.u;
    }


    public float bV() {
        if (this.bT() && !this.dL.a() && this.x.z) {
            return this.dL.e;
        }
        return super.bV();  // 02b j.java L3455: super.bV() (isDead 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
    }


    public float bW() {
        if (this.y.minAttackRange > 0.0f && this.cB < this.y.minAttackRange && this.x.v) {
            return this.cB / this.y.minAttackRange;
        }
        if (this.cA > 0.0f && this.cx < this.cA && this.x.y) {
            return this.cx / this.cA;
        }
        if (this.y.minAttackRange == 0.0f && this.cA == 0.0f) {
            if (this.x.em != -1 && this.cL[this.x.em].shootCooldown > 0.0f) {
                return 1.0f - this.cL[this.x.em].shootCooldown / this.b(this.x.em);  // 02b j.java L3466: this.b(this.x.em)
            }
            if (this.x.en != -1 && this.cL[this.x.en].maxRotationAngle != 0.0f) {
                return this.cL[this.x.en].maxRotationAngle / this.e(this.x.en);  // 02b j.java L3470: this.e(this.x.en)
            }
        }
        return super.bW();  // 02b j.java L3474: super.bW() (getProgressFraction 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
    }


    public boolean f(float f2) {
        return super.f(f2);  // 02b j.java L3479: super.f(var1)
    }


    public void p(float f2) {  // 02b j.java L3482: p(float) 闂?getTurretTurnRate 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = this.x.i;
        int n2 = m2.a;
        if (n2 > 0) {
            Object[] objectArray = this.x.i.a();
            for (int i2 = n2 - 1; i2 >= 0; --i2) {
                com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase a2 = (com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase)objectArray[i2];
                a2.f(this, f2);
            }
        }
        super.p(f2);  // 02b L3494: super.p(var1)
    }


    public void e(float f2) {
        super.e(f2);  // 02b j.java L3498: super.e(var1)
    }


    public void ca() {
        if (this.dL.b != null) {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            float f2 = (int)(this.eo - l2.cw);
            float f3 = (int)(this.ep - l2.cx);
            float f4 = (int)(this.dL.b.a - l2.cw);
            float f5 = (int)(this.dL.b.b - l2.cx);
            l2.bO.a(f2, f3, f4, f5, com.corrodinggames.rts.game.units.commands.MobileBuilderBase.y);
        }
    }


    public void cb() {
        float f2;
        boolean bl2 = true;
        boolean bl3 = false;
        if (this.y.cooldownTime > 70.0f && this.x.ep && this.x.dK == null || this.x.dK != null && this.x.dK.booleanValue()) {
            f2 = this.m();
            PathfindingUtils.a((UnitInstance) this, f2, bl2);
            bl3 = true;
        } else if (this.x.aH && this.y.maxAmmoCount > 50 && !this.x.ep) {
            f2 = this.y.maxAmmoCount;
            PathfindingUtils.a((UnitInstance) this, f2, bl2);
            bl3 = true;
        }
        if (this.x.o.size() != 0) {
            for (CustomSounds y2 : (java.util.Collection<CustomSounds>) (java.util.Collection) this.x.o) {
                PathfindingUtils.a((UnitInstance) this, y2.a, bl2);
                bl3 = true;
            }
        }
        if (this.x.bF) {
            int n2 = this.bl();
            for (int i2 = 0; i2 < n2; ++i2) {
                ModUnitLoader bn2 = this.x.fQ[i2];
                if (bn2.ak == null || !(bn2.al > 0.0f)) continue;
                int n3 = 90;
                if (bl3) {
                    n3 = 40;
                }
                PathfindingUtils.a(this, bn2.al, Color.a(n3, 35, 235, 35), 1, bl2);
            }
        }
    }


    public void d(float f2) {
        super.d(f2);
        if (this.isDead) {
            return;
        }
        this.dI.a(f2, this);
        if (this.x.al != null) {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            int n2 = 0;
            int n3 = 0;
            float f3 = this.eo - l2.cw;
            float f4 = this.ep - l2.cx - this.eq;
            float f5 = this.cD();
            if (f5 != 1.0f) {
                l2.bO.k();
                l2.bO.a(f5, f5, f3, f4);
            }
            if (this.x.am) {
                int n4 = this.x.al.p;
                int n5 = this.x.al.q;
                int n6 = n4 / 2;
                int n7 = n5 / 2;
                du.a(f3 - (float)n6, f4 - (float)n7, f3 + (float)n6, f4 + (float)n7);
                dv.a(n2, n3, n2 + n4, n3 + n5);
            } else {
                du.a(f3 - this.eu, f4 - this.ev, f3 + this.eu, f4 + this.ev);
                dv.a(n2, n3, n2 + this.es, n3 + this.et);
            }
            l2.bO.a(this.x.al, dv, du, this.aN());
            if (f5 != 1.0f) {
                l2.bO.l();
            }
        }
        if (this.x.az && this.dT != null && !this.isDead) {
            com.corrodinggames.rts.game.units.custom.animation.AnimationMovementCurve.a(this, f2, false, true);
        }
    }


    public float aM() {
        return this.x.dH;
    }


    public Paint aN() {
        Paint paint = super.aN();
        return paint;
    }


    public boolean aV() {
        return this.x.eF;
    }


    public boolean bI() {
        return this.x.aH;
    }


    public boolean q() {
        if (this.cO != null && this.cO.cm < 1.0f) {
            return true;
        }
        return this.x.aK;
    }

    private boolean getTurretBackswingAngle(UnitInstance am2) {
        if (am2.q()) {
            return false;
        }
        if (am2 == this) {
            return false;
        }
        return am2.isFactoryBuilding() ? this.x.aU : this.x.aV;
    }


    public boolean a(UnitInstance am2) {
        if (this.x.fo != null && !com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(this.x.fo, am2.getStatusEffects())) {
            return false;
        }
        return this.getTurretBackswingAngle(am2);
    }


    public boolean l(UnitInstance am2) {
        if (am2.bd() != 0.0f && this.h(am2, true)) {  // 02b j.java L3639: this.h(var1, true)
            return true;
        }
        if (this.x.fn != null && !com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(this.x.fn, am2.getStatusEffects())) {
            return false;
        }
        return this.getTurretBackswingAngle(am2);
    }


    public void m(UnitInstance am2) {
        if (this.x.bi) {
            com.corrodinggames.rts.gameFramework.ProjectileManager ab2;
            WeaponAction au2 = this.ar();
            if (au2 != null && (ab2 = au2.i) != null) {
                ab2.a(au2);
            }
            if (this.cG && am2 != null) {
                com.corrodinggames.rts.gameFramework.GlobalState.B().bS.k(am2);
            }
            this.ci();
        }
    }


    public boolean isDieOnConstruct() {
        return this.x.bi;
    }


    public boolean aj() {
        return this.x.fq;
    }


    public boolean cu() {
        return this.x.fN;
    }


    public boolean ak() {
        return this.x.fp;
    }


    public boolean g(UnitInstance am2, boolean bl2) {  // 02b j.java L3677: g(am,boolean) 闂?setDiscoveredBy 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
        if (!this.h(am2, bl2)) {
            return false;
        }
        return !bl2 || !am2.c((UnitType)this);  // 02b L3678: !var1.c((y)this)
    }


    public boolean h(UnitInstance am2, boolean bl2) {
        if (!this.x.fk) {
            return false;
        }
        return this.x.fl == null || com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(this.x.fl, am2.getStatusEffects());
    }


    public int cS() {
        return this.x.fm;
    }


    public boolean bJ() {
        return this.x.fu;
    }


    public void a(float f2, boolean bl2) {
        super.a(f2, bl2);  // 02b am.java L762: a(float,boolean) (bl 婵?UnitType 闁诲孩绋掗〃鍡涱敊瀹€鈧幏鐘活敍濮樿京鈹? 闂佸憡鐟ラ崐褰掑汲閻旂厧瑙?bl2)
        if (!this.isDead && this.ak()) {
            if (this.Y()) {
                if (!this.x.bW) {
                    com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.b(f2, this);
                }
            } else if (!this.x.bS) {
                com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.b(f2, this);
            }
        }
    }


    public boolean o() {
        return this.x.cy;
    }


    public boolean p() {  // 02b j.java L3711: p() 闂?getTurretTurnRate() 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
        return this.x.cz;
    }


    public boolean cO() {
        return this.x.cA;
    }


    public void f(PlayerState n2) {  // 02b j.java L3719: f(n) DirectionType 判断
        if (this.x.cE) {
            this.b(PlayerState.h);  // 02b L3721: this.b(n.h)
            return;
        }
        if (this.x.cD && this.B.size() == 0) {
            this.b(PlayerState.i);  // 02b L1052: this.b(n.i)
            return;
        }
        super.f(n2);  // 02b L3725: super.f(var1)
    }


    public void B(UnitInstance am2) {
        super.B(am2);  // 02b j.java L3730: super.B(var1) (getTurretAngleOffset 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
    }


    public float g() {
        return this.x.cF;
    }


    public int cQ() {
        return this.x.cG;
    }


    public UnitConfig cR() {  // 02 闂傚倷绀佽ぐ鐐裁归崶顒佸剨? 闂佸搫顦弲婊堝蓟閵娿儍?custom.h
        return this.x.cH;
    }


    public void cP() {
        if (this.x.bh == 0.0f && this.g() > 0.0f) {  // 02b j.java L3746: this.g() (isInitialized 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
            PlayerState.b((UnitInstance) this);
            this.cm = 1.0f;
            PlayerState.c(this);
        }
    }


    public ActionId cp() {  // 02b j.java L3754: a.c cp() (AnimationCurve 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐑樻崳缂佺姵鐓￠弻?
        if (this.x.eM != 0) {
            return com.corrodinggames.rts.game.units.projectiles.SpecialBuilding.i.N();
        }
        return super.cp();
    }


    public float L(int n2) {
        return this.x.fQ[n2].af;
    }


    public PointF K(int n2) {
        PointF pointF = super.K(n2);  // 02b j.java L3763: super.K(var1) (getMuzzlePointWithRecoil 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
        if (this.x.eA) {
            MovementController f2;
            ModUnitLoader bn2 = this.x.fQ[n2];
            bh bh2 = this.x.fR[bn2.a(this)];
            if (bh2.M && this.G != null && (f2 = this.G[n2]) != null && !f2.ej) {
                pointF.a += f2.K;
                pointF.b += f2.L;
            }
        }
        return pointF;
    }


    public float getMaxHealth() {
        return this.y.minAttackRange;
    }


    public PathResult be() {  // 02b j.java L3783: be() 闂佸搫顦弲婊堝蓟閵娿儍?units/b (enum b=PathResult; getb 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
        return this.x.ec;
    }


    public boolean bf() {
        if (this.x.ef) {
            return false;
        }
        if (this.x.ec != com.corrodinggames.rts.game.units.PathResult.d) {  // 02b L3790: this.x.ec != b.d (BuildingSlot 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚村姛闁糕晜顨婂?
            return true;
        }
        WeaponAction au2 = this.ar();  // 02b L3793-3794: au var1 = this.ar(); return var1 != null && (d()==av.h || d()==av.j)
        return au2 != null && (au2.d() == com.corrodinggames.rts.game.units.WeaponTypeEnum.h || au2.d() == com.corrodinggames.rts.game.units.WeaponTypeEnum.j);
    }


    public boolean bX() {
        return this.v;
    }


    public boolean bg() {
        return this.x.ei;
    }


    public float bc() {
        return this.x.el;
    }


    public void f(float f2, float f3) {
        super.f(f2, f3);  // 02b j.java L3811: super.f(var1,var2) (getTurretRecoilTime 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
        this.a(com.corrodinggames.rts.game.units.custom.af.h);
        float f4 = this.cg;
        if (this.x.dE) {
            f4 = this.cL[this.x.dG].turretAngle;
        }
        this.dP = this.eo;
        this.dQ = this.ep;
        this.dR = this.eq;
        this.dS = f4;
    }

    public void du() {
        if (this.x.ax == null && this.dT == null) {
            return;
        }
        if (this.x.ax == null || this.x.ax.length == 0) {
            this.dT = null;
            return;
        }
        if (this.dT != null && this.dT.length == this.x.ax.length) {
            return;
        }
        this.dT = new com.corrodinggames.rts.game.units.custom.animation.i[this.x.ax.length];
        for (int i2 = 0; i2 < this.x.ax.length; ++i2) {
            com.corrodinggames.rts.game.units.custom.animation.i i3;
            this.dT[i2] = i3 = new com.corrodinggames.rts.game.units.custom.animation.i();
            i3.a = i2;
            i3.movementSpeedScale = this.x.ax[i2].r;
        }
        float f2 = this.cg;
        if (this.x.dE) {
            f2 = this.cL[this.x.dG].turretAngle;
        }
        this.dP = this.eo;
        this.dQ = this.ep;
        this.dR = this.eq;
        this.dS = f2;
        this.dv();
        for (int i4 = 0; i4 < this.x.ax.length; ++i4) {
            this.dT[i4].m = true;
        }
    }

    public void dv() {
        com.corrodinggames.rts.game.units.custom.animation.AnimationMovementCurve.a.b(this, 0.0f);
    }

    public void markLegsDirty() {
        if (this.dT != null) {
            for (int i2 = 0; i2 < this.dT.length; ++i2) {
                this.dT[i2].n = true;
                this.dT[i2].m = true;
            }
            this.dv();
        }
    }


    public int aT() {
        if (this.x.fV == null) {
            return -1;
        }
        return this.x.fV.e;
    }


    public int s() {
        return this.y.ammoPerBurst;
    }


    public void c(boolean bl2) {
        ModUnitRegistry l2 = this.x;
        GlobalState l3 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.cN != null || this.cO != null) {
            return;
        }
        int n2 = this.y.ammoPerBurst;
        if (this.cm < 1.0f && l2.dh != -1) {
            n2 = l2.dh;
        }
        if (n2 > 0) {
            l3.bL.a(this.eo, this.ep, n2, this.player, bl2);
        }
    }


    public Rect cc() {
        return this.x.cX;
    }


    public Rect ce() {
        return this.x.cZ;
    }


    public Rect cd() {
        return this.x.cY;
    }


    public boolean b(int n2, float f2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        if (bn2.as != 0.0f) {
            boolean bl2 = true;
            if (bn2.av != null && !bn2.av.read(this)) {
                bl2 = false;
            }
            if (bl2) {
                float f3;
                UnitTurret ap2 = this.cL[n2];
                float f4 = bn2.ar != 0.0f ? ap2.turretAngle : (!bn2.aq ? ap2.turretOffsetY : this.C(n2));  // 02b j.java L3927: this.C(var1)
                ap2.currentAngle += f2;
                float f5 = f2 * bn2.au;
                if (ap2.targetAngle > 0.0f) {
                    if (ap2.targetAngle < Float.POSITIVE_INFINITY && (f3 = this.a(f5, f4 + ap2.targetAngle, n2)) == 0.0f) {
                        ap2.targetAngle = Float.POSITIVE_INFINITY;
                    }
                } else if (ap2.targetAngle > Float.NEGATIVE_INFINITY && (f3 = this.a(f5, f4 + ap2.targetAngle, n2)) == 0.0f) {
                    ap2.targetAngle = Float.NEGATIVE_INFINITY;
                }
                if (ap2.currentAngle > bn2.at) {
                    ap2.currentAngle = -GameUtils.a(this, 0, (int)bn2.aw);  // 02b L3948: f.a(this, 0, (int)var3.aw) (formatDuration 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
                    f3 = bn2.as;
                    if (bn2.ax > 0.0f) {
                        f3 += GameUtils.b(this, 0.0f, bn2.ax, n2);  // 02b L3951: f.b(this, 0.0F, var3.ax, var1) (md5Hex 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
                    }
                    ap2.targetAngle = ap2.targetAngle > 0.0f ? -f3 : f3;
                }
                return false;
            }
        }
        if (bn2.ar != 0.0f) {
            this.cL[n2].turretAngle += bn2.ar * f2;
            if (this.cL[n2].turretAngle > 180.0f) {
                this.cL[n2].turretAngle -= 360.0f;
            }
            if (this.cL[n2].turretAngle < -180.0f) {
                this.cL[n2].turretAngle += 360.0f;
            }
            return false;
        }
        return bn2.aq;
    }


    public int cw() {
        return this.x.eZ;
    }

    public ArrayList getProductionList() {
        dU.clear();
        ArrayList arrayList = this.N();
        if (arrayList.size() != 0) {
            for (GameAction s2 : (java.util.Collection<GameAction>) (java.util.Collection) arrayList) {
                if (!(s2 instanceof g)) continue;
                g g2 = (g) s2;
                if (g2.actionList != com.corrodinggames.rts.game.units.custom.actions.e.c) continue;  // 02b j.java L3991: var4.c == a.e.c
                dU.add(g2);
            }
        }
        return dU;
    }


    public com.corrodinggames.rts.game.units.actions.ActionId cm() {
        ArrayList arrayList = this.getProductionList();
        if (arrayList.size() > 0) {
            return ((GameAction) arrayList.get(0)).N();
        }
        return null;
    }


    public void a(ArrayList arrayList) {
        arrayList.clear();
        ArrayList arrayList2 = this.getProductionList();
        if (arrayList2.size() < 2) {
            return;
        }
        arrayList2.remove(0);
        for (GameAction s2 : (java.util.Collection<GameAction>) (java.util.Collection) arrayList2) {
            arrayList.add(s2.N());
        }
    }


    public float cZ() {
        return this.x.da;
    }


    public float da() {
        return this.x.db;
    }


    public void bv() {
        PlayerState.a((UnitInstance) this);  // 02b L580: n.a((am)this)
        this.dL.a(true);
        super.bv();  // 02b j.java L4032: super.bv() (finalizeUnitRemoval 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
    }


    public void dc() {
        this.eq = 170.0f;
        this.f = 1.5f;
        this.markLegsDirty();
        super.dc();  // 02b j.java L4039: super.dc() (startDeathExplosion 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
    }


    public boolean dd() {
        return this.x.cm;
    }


    public int bp() {
        return this.x.bq;
    }


    public void a(int n2, float f2) {
        this.cL[n2].turretAngle += f2;
        if (this.x.fU) {
            for (int i2 = 0; i2 < this.x.fQ.length; ++i2) {
                ModUnitLoader bn2 = this.x.fQ[i2];
                if (bn2.w != n2) continue;
                this.cL[i2].turretAngle += f2;
                this.cL[i2].a(2);
            }
        }
    }


    public float db() {
        return super.db() + (float)this.x.dg;
    }


    public float H(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.p;
    }


    public float I(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.q;
    }


    public float J(int n2) {
        ModUnitLoader bn2 = this.x.fQ[n2];
        return bn2.r;
    }


    public float a(UnitInstance am2, float f2, MovementController f3) {
        UnitTrait n2 = this.dn();  // 02b j.java L4084: b.n var4 = this.dn() (dn 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
        if (n2 != null && this.cO != null && n2.j) {
            int n3 = 0;
            if (f3 != null) {
                n3 = f3.aD;
            }
            if (n3 >= 0) {
                float f4;
                MovementController f5 = com.corrodinggames.rts.game.MovementController.a(f3);  // 02b L4092: game.f.a(var3) (projectiles.a 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚村伐闁?
                if (n2.k) {
                    f5.am = 0.0f;
                }
                if ((f2 = (f4 = this.cO.a(am2, f2, f5))) < 0.0f) {
                    f2 = 0.0f;
                }
            }
        }
        if (this.J()) {  // 02b L4105: this.J() (sharedPaint 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
            f2 = 0.0f;
        }
        if (this.y.turretTraverseSpeed > 0.0f && f2 > this.x.cN) {
            float f6 = this.y.turretTraverseSpeed;
            if (f3 != null) {
                f6 -= f3.an;
            }
            if (f6 < 0.0f) {
                f6 = 0.0f;
            }
            if ((f2 -= f6) < this.x.cN) {
                f2 = this.x.cN;
            }
        }
        if (f3 != null) {
            this.a(com.corrodinggames.rts.game.units.custom.af.n, am2, f3.aE, (VariableScope)null);  // 02b L4126: (VariableScope)null
        } else {
            this.a(com.corrodinggames.rts.game.units.custom.af.n, am2);
        }
        return super.a(am2, f2, f3);  // 02b L4131: super.a(var1,var2,var3) = UnitInstance.a(am,float,f)
    }


    public float aU() {
        return this.x.dJ;
    }


    public boolean ac() {
        if (!this.x.eh) {
            return false;
        }
        return super.ac();  // 02b j.java L4139: super.ac() (canShieldRegenerate 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
    }


    public boolean a(PathState ag2) {  // 02b j.java L4142: a(units.ag) 闂?ag enum=03 PathState (ModLoader 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄铏圭劮妞ゎ偓绠撻弻?
        if (ag2 == com.corrodinggames.rts.game.units.PathState.a) {
            return this.x.do_.a();
        }
        if (ag2 == com.corrodinggames.rts.game.units.PathState.b) {
            return this.x.dp.a();
        }
        if (ag2 == com.corrodinggames.rts.game.units.PathState.c) {
            return this.x.dq.a();
        }
        return false;
    }

    public void detachUnit(af af2) {
        ModUnitRegistry l2 = this.x;
        if (l2.gq.a == 0) {
            return;
        }
        Object[] objectArray = l2.gq.a();
        for (int i2 = l2.gq.a - 1; i2 >= 0; --i2) {
            ae ae2 = (ae)objectArray[i2];
            if (ae2.a != af2) continue;
            CustomUnitType.ec.a = this.eo;
            CustomUnitType.ec.b = this.ep;
            PointF pointF = CustomUnitType.ec;
            UnitInstance am2 = null;
            this.a(ae2.b, pointF, am2, 0, 0);
        }
    }


    public void a(af af2, UnitInstance am2, UnitConfig h2, VariableScope variableScope) {
        ModUnitRegistry l2 = this.x;
        if (l2.gq.a == 0) {
            return;
        }
        Object[] objectArray = l2.gq.a();
        for (int i2 = l2.gq.a - 1; i2 >= 0; --i2) {
            ae ae2 = (ae)objectArray[i2];
            if (ae2.a != af2 || ae2.d != null && !com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(ae2.d, h2)) continue;
            WeaponMount k2 = dX.size() > 0 ? (WeaponMount) dX.b() : new WeaponMount();
            k2.a = ae2;
            k2.b = this;
            k2.c = am2;
            k2.d = h2;
            k2.e = variableScope;
            dW.add(k2);
        }
    }

    public static void isTurretAutoFire(float f2) {
    }

    public static void clearQueuedEvents() {
        if (CustomUnitType.dW.a == 0) {
            return;
        }
        sharedRenderList2 = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    }

    public static void a(float f2, int n2) {
        Object object;
        int n3;
        if (CustomUnitType.dW.a == 0) {
            return;
        }
        for (int i2 = 0; i2 < 105 && (n3 = CustomUnitType.dW.a) != 0; ++i2) {
            Object object2;
            Object object3;
            Object object4;
            Object object5;
            int n4;
            Object[] objectArray4 = dW.a();  // 02b L4209: Object[] var4 (object 濠电姰鍨煎▔娑氱矓閸洖鏋侀柕鍫濐槹閸?array required)
            for (n4 = n3 - 1; n4 >= 0; --n4) {
                object5 = (WeaponMount) objectArray4[n4];
                object4 = ((WeaponMount) object5).a;
                object3 = ((WeaponMount) object5).b;
                if (((ae)object4).c != ((CustomUnitType) object3).x) continue;
                CustomUnitType.ec.a = ((CustomUnitType) object3).eo;
                CustomUnitType.ec.b = ((CustomUnitType) object3).ep;
                object2 = CustomUnitType.ec;
                UnitInstance am2 = null;
                LogicBoolean.setContextEventSource((WeaponMount) object5);
                ((CustomUnitType) object3).a(((ae)object4).b, (PointF)object2, am2, 0, 0);
                LogicBoolean.clearContext();
            }
            if (i2 < 105) {
                int n5;
                if (n3 == CustomUnitType.dW.a) break;
                objectArray4 = dW.a();  // 02b L4236: var4 = dW.a()
                n4 = 0;
                for (n5 = n3; n5 < CustomUnitType.dW.a; ++n5) {
                    object4 = (WeaponMount) objectArray4[n5];
                    object3 = ((WeaponMount) object4).a;
                    if (i2 >= ((ae)object3).e) continue;
                    ++n4;
                }
                if (n4 <= 0) break;
                dV.clear();
                for (n5 = 0; n5 < CustomUnitType.dW.a; ++n5) {
                    object4 = (WeaponMount) objectArray4[n5];  // 02b L4256: var4[var13]
                    boolean bl2 = true;
                    if (n5 < n3) {
                        bl2 = false;
                    } else {
                        object2 = ((WeaponMount) object4).a;
                        if (i2 >= ((ae)object2).e) {
                            bl2 = false;
                        }
                    }
                    if (!bl2) {
                        ((WeaponMount) object4).a();
                        dX.add(object4);
                        continue;
                    }
                    dV.add(object4);
                }
                dW.clear();
                object5 = sharedRenderList1;
                sharedRenderList1 = sharedRenderList2;
                sharedRenderList2 = (com.corrodinggames.rts.gameFramework.utility.CustomArrayList)object5;  // 02b L4278: dW = var15
                if (n4 == CustomUnitType.dW.a) continue;
                com.corrodinggames.rts.gameFramework.GlobalState.e("processAllQueuedEvents: " + n4 + "!=" + CustomUnitType.dW.a);
                continue;
            }
            com.corrodinggames.rts.gameFramework.GlobalState.e("processAllQueuedEvents: recursion limit reached");
            break;
        }
        Object[] objectArray = dW.a();
        for (n3 = CustomUnitType.dW.a - 1; n3 >= 0; --n3) {
            object = (WeaponMount) objectArray[n3];
            ((WeaponMount) object).a();
            dX.add(object);
        }
        dW.clear();
    }

    public static void dE() {
    }

    public void detachUnit(float f2, boolean bl2) {
        Modifier[] rArray;
        Modifier[] rArray2;
        Modifier[] rArray3;
        ModUnitRegistry l2 = this.x;
        if (!l2.fX) {
            return;
        }
        if (this.shieldRegenRate != 0.0f) {
            this.shieldRegenRate = GameUtils.a(this.shieldRegenRate, f2);
            if (this.shieldRegenRate == 0.0f) {
                bl2 = true;
            } else {
                return;
            }
        }
        if ((rArray3 = l2.fY) != null) {
            this.a(f2, rArray3);
            if (l2 != this.x) {
                return;
            }
        }
        if ((rArray2 = l2.fZ) != null) {
            GlobalState l4 = com.corrodinggames.rts.gameFramework.GlobalState.B();  // 02b L4320: l var6 = l.B() (闂備胶绮悷顖炲礈濞戙垺鍋熸繛鎴欏灩閻鎱ㄥ璇蹭壕闂? 闂備礁鎲″ú妯尖偓绗涘懏瀚婚柍鍝勬噹缁€?r[])
            int n2 = (int)((long)l4.bx + this.eh) % 4;
            if (n2 == 0 || bl2) {
                this.a(f2, rArray2);
                if (l2 != this.x) {
                    return;
                }
            }
        }
        if ((rArray = l2.ga) != null) {
            GlobalState l3 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            int n3 = (int)((long)l3.bx + this.eh) % 8;
            if (n3 == 0 || bl2) {
                this.a(f2, rArray);
                if (l2 != this.x) {
                    return;
                }
            }
        }
    }

    public void a(float f2, Modifier[] rArray) {
        ModUnitRegistry l2 = this.x;
        GlobalState l3 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        boolean bl2 = false;
        for (int i2 = 0; i2 < rArray.length; ++i2) {
            String string;
            Object object;
            Modifier r2 = rArray[i2];
            long l4 = 0L;
            boolean bl3 = r2.a.read(this);
            if (!bl3) continue;
            if (l3.bl && l3.bn && this.cG) {
                object = null;
                if (r2.d != null) {
                    object = "" + r2.d.O();
                }
                string = "autoTrigger fired on: " + this.cB() + " details: " + r2.a.getDebugDetails(this);
                com.corrodinggames.rts.gameFramework.GlobalState.e(string);
                l3.bS.i.a(string, 2000);
            }
            CustomUnitType.ec.a = this.eo;
            CustomUnitType.ec.b = this.ep;
            object = CustomUnitType.ec;
            string = null;
            long l5 = 0L;
            this.a(r2.d, (PointF)object, (UnitInstance) ((Object)string), 0, 0);
            this.shieldRegenRate = this.x.ca;
            if (l2 == this.x) continue;
            return;
        }
    }


    public UnitConfig de() {  // 02 闂傚倷绀佽ぐ鐐裁归崶顒佸剨? 闂佸搫顦弲婊堝蓟閵娿儍?custom.h
        return this.ed;
    }

    public void a(UnitConfig h2, boolean bl2) {
        if (bl2) {
            this.ed = h2;
            return;
        }
        PlayerState.b((UnitInstance) this);
        this.ed = h2;
        PlayerState.c(this);
    }

    public void setInitialDisplayName(boolean bl2) {
        this.a(this.x.O, bl2);
    }

    public void a(UnitConfig h2) {
        UnitConfig h3 = this.de();
        if (h3 == null || h3.b() == 0) {  // 02b j.java L4403: var2.b() (tagValue 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
            this.a(h2, false);
            return;
        }
        if (com.corrodinggames.rts.game.units.custom.TeamTag.b(h3, h2)) {  // 02b L4404: g.b(var2,var1) (teams 闂備礁鎲￠悧鏇㈠箠瀹ュ洦瀚婚柣鏃囧亹瀹撲線鏌￠崟顐ｇ婵?
            return;
        }
        CollisionShape i2 = new CollisionShape(h3);
        if (i2.a(h2)) {
            this.a(i2.a(), false);
            return;
        }
    }

    public void detachUnit(UnitConfig h2) {
        UnitConfig h3 = this.de();
        if (h3 == null || h3.b() == 0) {  // 02b j.java L4417: var2.b()
            return;
        }
        if (!com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(h2, h3)) {
            return;
        }
        CollisionShape i2 = new CollisionShape(h3);
        if (i2.b(h2)) {
            this.a(i2.a(), false);
            return;
        }
    }

    public final void updateAnimationState() {
        if (this.x.fg == com.corrodinggames.rts.game.units.MovementTypeEnum.d) {
            if (this.setQueuePaused()) {
                this.S(5);
            } else if (this.cr() && this.cl == 0.0f) {
                this.S(3);
            } else {
                this.S(2);
            }
        } else if (this.cl == 0.0f) {
            this.S(this.x.cI);
        } else {
            this.S(2);
        }
        this.en = 0;
    }


    public boolean ck() {
        return this.x.gi;
    }


    public boolean f() {
        return this.x.fd.read(this);
    }


    public boolean j() {
        return true;
    }


    public com.corrodinggames.rts.gameFramework.utility.ai D(int n2) {
        UnitTurret ap2 = this.cL[n2];
        ModUnitLoader bn2 = this.x.fQ[n2];
        float f2 = bn2.X;
        float f3 = bn2.Y;
        if (bn2.Z != 0.0f && ap2.isTurning) {  // 02b j.java L4462: var2.m (isRotating 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁? ap.m(boolean)=isTurning)
            f3 += bn2.Z;
        }
        float f4 = this.E() ? this.cg : ap2.turretAngle;
        com.corrodinggames.rts.gameFramework.utility.ai ai2 = this.F(n2);
        float f5 = GameUtils.cosFast(f4);
        float f6 = GameUtils.sinFast(f4);
        float f7 = ai2.a;
        float f8 = ai2.b;
        float f9 = ai2.c;
        CustomUnitType.ee.a = f7 += f5 * f2 - f6 * f3;
        CustomUnitType.ee.b = f8 += f6 * f2 + f5 * f3;
        CustomUnitType.ee.c = f9 + bn2.aa;
        return ee;
    }


    public PointF E(int n2) {
        com.corrodinggames.rts.gameFramework.utility.ai ai2 = this.D(n2);  // 02b L2104: this.D(var4)
        CustomUnitType.ef.a = ai2.a;
        CustomUnitType.ef.b = ai2.b;  // ef=PointF (02b E(int): ef.a/ef.b)
        return ef;
    }


    public boolean cl() {
        return this.x.fJ;
    }


    public float cn() {
        return this.x.fK;
    }


    public void a(UnitInstance am2, float f2, int n2) {
        if (this.x.dz != null) {
            this.b.a(this.x.dz, 5);
        }
        if (this.x.bS) {
            this.U = GameUtils.a(this.U, f2);
            if (this.U == 0.0f) {
                this.U = this.x.bT;
                if (this.x.bU != null) {
                    UnitTurret ap2 = this.cL[n2];
                    PointF pointF = this.E(n2);
                    this.x.bU.a(pointF.a, pointF.b, this.eq, ap2.turretAngle, this);
                }
                if (this.x.bV != null) {  // 02b L4511: this.x.bV (isDead 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
                    this.x.bV.a(am2.eo, am2.ep, am2.eq, am2.cg, am2);
                }
            }
        } else {
            super.a(am2, f2, n2);
        }
    }


    public void b(UnitInstance am2, float f2, int n2) {
        if (this.x.dA != null) {
            this.b.a(this.x.dA, 5);
        }
        if (this.x.bW) {
            this.U = GameUtils.a(this.U, f2);
            if (this.U == 0.0f) {
                this.U = this.x.bX;  // 02b L4529: this.x.bX (player 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
                if (this.x.bY != null) {
                    UnitTurret ap2 = this.cL[n2];
                    PointF pointF = this.E(n2);
                    this.x.bY.a(pointF.a, pointF.b, this.eq, ap2.turretAngle, this);
                }
                if (this.x.bZ != null) {
                    this.x.bZ.a(am2.eo, am2.ep, am2.eq, am2.cg, am2);
                }
            }
        } else {
            super.b(am2, f2, n2);  // 02b L4541: super.b(var1,var2,var3) (detachUnit 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
        }
    }


    public boolean cg() {
        return this.y.requiresManualFire;
    }

    public boolean dG() {
        Object object;
        if (this.x.Z != null && !this.x.Z.read(this)) {
            return false;
        }
        if (!this.y.requiresManualFire) {
            object = com.corrodinggames.rts.gameFramework.GlobalState.B();
            if (((GlobalState)object).bs.c(this.player) && !((GlobalState)object).bs.b()) {
                return false;
            }
        }
        return (object = this.dn()) == null || ((UnitTrait)object).o;
    }


    public boolean t() {
        UnitTrait n2 = this.dn();
        if (n2 != null && n2.m) {
            return true;
        }
        return this.x.aM;
    }


    public boolean cV() {
        UnitTrait n2 = this.dn();
        if (n2 != null && n2.n) {
            return true;
        }
        return this.x.aN;
    }


    public boolean d(UnitInstance am2) {
        ModUnitRegistry l2 = this.x;
        if (l2.aS != null && !com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(l2.aS, am2.getStatusEffects())) {
            return false;
        }
        if (this.dH()) {
            return false;
        }
        return !l2.aO;
    }


    public boolean cW() {
        return this.x.aT;
    }


    public boolean cT() {
        if (this.x.aO) {
            return true;
        }
        return this.u() || this.cm < 1.0f && this.x.bh <= 0.0f;  // 02b L4586: this.u() (canUseTurret 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
    }


    public UnitConfig dh() {  // 02 闂傚倷绀佽ぐ鐐裁归崶顒佸剨? 闂佸搫顦弲婊堝蓟閵娿儍?custom.h
        return this.x.P;
    }


    public float am() {
        return this.x.eG;
    }


    public boolean an() {
        return super.an() || this.x.eF;  // 02b j.java L4598: super.an() (isShieldActive 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
    }


    public boolean a(GlobalState l2) {
        if (!l2.cO.b(this.eo, this.ep)) {
            if (!this.x.B) {
                return false;
            }
            boolean bl2 = false;
            if (this.x.C != null) {
                ModUnitRegistry.a.a(this.x.C);
                ModUnitRegistry.a.a((int)this.eo, (int)this.ep);
                if (l2.cQ.b(ModUnitRegistry.a)) {
                    bl2 = true;
                }
            }
            if (l2.cO.b(this.eo, this.ep - this.eq)) {
                bl2 = true;
            }
            if (!bl2) {
                return false;
            }
        }
        if (this.cN != null) {
            return false;
        }
        if (this.cP != null && this.cP.I) {
            return false;
        }
        if (!this.d(l2.bs)) {
            return false;
        }
        return this.dG();
    }

    public UnitType a(UnitTrait n2) {  // 02b j.java L4628: y a(b.n) 闂?闂佸搫顦弲婊堝蓟閵娿儍?UnitType (UnitTrait 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐑樻崳缂佺姵鐓￠弻?
        UnitType y2 = com.corrodinggames.rts.game.units.custom.animation.AnimationTurretCurve.a(this, n2);
        return y2;
    }


    public UnitTrait a(short s2) {
        UnitTrait n2 = com.corrodinggames.rts.game.units.custom.animation.AnimationTurretCurve.a(this, s2);
        return n2;
    }


    public boolean a(UnitType y2, UnitTrait n2) {
        if (y2 == this) {
            return false;
        }
        if (n2 == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("attachRequest: No attachedSlotData");
            return false;
        }
        UnitType y3 = com.corrodinggames.rts.game.units.custom.animation.AnimationTurretCurve.a(this, n2);
        if (y3 != null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("attachRequest: a unit is already in slot (parent:" + this.cB() + " slot:" + n2.b() + " existing:" + y3.cB() + ")");  // 02b L4647: var3.cB() (cC 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
            return false;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.game.units.custom.animation.AnimationTurretCurve.a(this, n2, y2);
        y2.cO = this;
        y2.cP = n2;
        y2.cQ = l2.by;
        y2.bT = false;
        return true;
    }


    public boolean b(UnitType y2) {
        if (y2.cO != this) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("deattachRequest: unit is not attached");
            return false;
        }
        UnitTrait n2 = y2.cP;
        if (n2 == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("deattachRequest: unit has no attachedSlotData");
            return false;
        }
        UnitType y3 = com.corrodinggames.rts.game.units.custom.animation.AnimationTurretCurve.a(this, n2);
        if (y3 == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("deattachRequest: Failed, no unit in slot");
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("deattachRequest");
            return false;
        }
        if (y2 != y3) {
            String string = "null";
            if (y3 != null) {
                string = y3.cB();
            }
            com.corrodinggames.rts.gameFramework.GlobalState.b("deattachRequest: unit and slot don't match - requested:" + y2.cB() + " current:" + string);
            return false;
        }
        if (this.B.remove(y2)) {
            this.D(y2);  // 02b j.java L4686: this.D(var1) (getMuzzleWorldPosition 濠电偞鍨堕幐鎼佸箹椤愶絽绶為柣鏃傚劋閸犲棗顭跨捄鐚存敾闁?
        }
        com.corrodinggames.rts.game.units.custom.animation.AnimationTurretCurve.a(this, n2, null);
        y2.cO = null;
        y2.cP = null;
        y2.f_();
        this.a(com.corrodinggames.rts.game.units.custom.af.r, this);
        return true;
    }

    public boolean dH() {
        UnitTrait n2 = this.dn();
        return n2 != null && !n2.l;
    }


    public boolean J() {
        if (this.dH()) {
            return true;
        }
        return this.x.aP;
    }


    public void di() {
        ModUnitRegistry l2 = this.x;
        if (!l2.cv.c()) {
            l2.cv.a(this);
        }
        if (!l2.cw.c() && this.cm >= 1.0f) {
            l2.cw.a(this);
        }
        super.di();
    }


    public void dj() {
        ModUnitRegistry l2 = this.x;
        if (!l2.cv.c()) {
            l2.cv.h(this);
        }
        if (!l2.cw.c() && this.cm >= 1.0f) {
            l2.cw.h(this);
        }
        super.dj();
    }


    public void a(WeaponAction au2) {
        WeaponTypeEnum av2;
        this.a(com.corrodinggames.rts.game.units.custom.af.j);
        UnitTrait n2 = this.dn();
        if (n2 != null && n2.H && ((av2 = au2.d()) == com.corrodinggames.rts.game.units.WeaponTypeEnum.h || av2 == com.corrodinggames.rts.game.units.WeaponTypeEnum.a)) {
            this.bx();
        }
    }


    public boolean c_() {
        ModUnitRegistry l2 = this.x;
        if (!l2.aR) {
            GlobalState l3 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            if (l3.bs.c(this.player)) {
                return false;
            }
        }
        return l2.aQ;
    }


    public boolean dk() {
        return this.x.aa;
    }


    public boolean dl() {
        return this.x.eK;
    }


    public boolean dm() {
        return this.x.eL;
    }

    public int getUsedTransportCapacity2() {
        int n2 = 0;
        if (this.x.eO) {
            n2 += this.B.size();
        } else if (this.B.a > 0) {
            for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.B) {
                n2 += am2.getMaxUnitGroupSize();
            }
        }
        return n2;
    }


    public CustomActionBase cN() {
        return this.x.ci;
    }


    public void ch() {
        if (this.x.bs) {
            if (this.hp <= -1.0f) {
                this.hp = -1.0f;
            }
            return;
        }
        super.ch();
    }


    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList bz() {
        return this.B;
    }


    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList e(boolean bl2) {
        this.eg.clear();
        if (this.x.aA.a > 0) {
            com.corrodinggames.rts.game.units.custom.animation.AnimationTurretCurve.a(this, this.eg, bl2);
        }
        if (this.eg.a > 0) {
            return this.eg;
        }
        return null;
    }


    public float do_() {
        return this.x.de;
    }


    public boolean dp() {
        return this.x.A;
    }

    public void dJ() {
        if (this.y.a) {
            this.y = this.y.b();
        }
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.dt();
    }

    static PointF sharedBuildPoint;  // v19.113o auto_align R3a: 闂傚倸鍊搁悧濠囨偡閿曞倸鐒垫い鎴ｆ硶椤︼附绻涚涵椋庣？闁圭鍛亾閿濆簼绨撮柛瀣崌瀹曟宕楃涵鐑界崪濠电姰鍨瑰﹢杈ㄦ櫠濡も偓鍗遍柟瀵稿У閸嬫﹢鏌曟径娑氱暠缂?
    static com.corrodinggames.rts.gameFramework.utility.ai sharedQueueState;  // 闂傚倸鍊搁悧濠囨偡閿曞倸鐒垫い鎴ｆ硶椤︼附绻涚涵椋庣？闁圭鍛亾閿濆簼绨撮柛瀣崌瀹曟宕楃涵鐑界崪濠电姰鍨瑰﹢杈ㄦ櫠濡も偓鍗遍柟瀵稿У閸嬫﹢鏌曟径娑氱暠缂?
    static ArrayList sharedUnitList;  // 闂傚倸鍊搁悧濠囨偡閿曞倸鐒垫い鎴ｆ硶椤︼附绻涚涵椋庣？闁圭鍛亾閿濆簼绨撮柛瀣崌瀹曟宕楃涵鐑界崪濠电姰鍨瑰﹢杈ㄦ櫠濡も偓鍗遍柟瀵稿У閸嬫﹢鏌曟径娑氱暠缂?
    static com.corrodinggames.rts.gameFramework.utility.CustomArrayList sharedRenderList3;  // 闂傚倸鍊搁悧濠囨偡閿曞倸鐒垫い鎴ｆ硶椤︼附绻涚涵椋庣？闁圭鍛亾閿濆簼绨撮柛瀣崌瀹曟宕楃涵鐑界崪濠电姰鍨瑰﹢杈ㄦ櫠濡も偓鍗遍柟瀵稿У閸嬫﹢鏌曟径娑氱暠缂?
    static String cachedDisplayString;  // 闂傚倸鍊搁悧濠囨偡閿曞倸鐒垫い鎴ｆ硶椤︼附绻涚涵椋庣？闁圭鍛亾閿濆簼绨撮柛瀣崌瀹曟宕楃涵鐑界崪濠电姰鍨瑰﹢杈ㄦ櫠濡も偓鍗遍柟瀵稿У閸嬫﹢鏌曟径娑氱暠缂?

    static {
        sharedUnitList = new ArrayList();
        sharedRenderList1 = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
        sharedRenderList2 = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
        sharedRenderList3 = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
        for (int i2 = 0; i2 < 10; ++i2) {
            dX.add(new WeaponMount());
        }
        renderStateFlag = true;
        unitTypeCache = new HashMap();
        nextCacheId = 0;
        cachedDisplayString = "";
        ec = new PointF();  // 02b L4886 缂傚倷鑳舵慨顓㈠磻閹剧粯鐓曟俊銈勭劍缁€鈧梺?(qualified 闂佽崵濮嶉崘鎯у壈闂?final 闂備胶顢婄紙浼村磿閻㈢鏋?
        ee = new com.corrodinggames.rts.gameFramework.utility.ai();  // 02b L4887
        ef = new PointF();  // 02b L4888
        sharedQueueState = new com.corrodinggames.rts.gameFramework.utility.ai();
        sharedBuildPoint = new PointF();
    }






    // v19.112 闂佽崵濮村ù鍌炲储閾忓湱涓嶉柣鏃傚帶濡﹢鎮峰▎蹇擃伀闁靛棗锕幃璺衡槈閺嵮冨闂?(supplement 02 闂備礁鎲￠懝鍓х矓闂堟党锝堫槾濞?+ 02b 闂備礁鎼崐浠嬶綖婢跺本鍏滈柤绋跨仛婵?


    public strictfp boolean isSeaUnitByConfig() {
    return this.x.aH;
    }



    public strictfp int getExplosionParticleCount() {
    return this.x.bq;
    }







    public strictfp float getTurretFireCooldownOverride(int var1) {
    ModUnitLoader var2 = this.x.fQ[var1];
    return var2.U != null?var2.U.floatValue():this.x.eb;
    }



    public final strictfp void dF() {
    if(this.x.fg == com.corrodinggames.rts.game.units.MovementTypeEnum.d) {
    if(this.i()) {
    this.s(5);
    } else if(this.cr() && this.cl == 0.0F) {
    this.s(3);
    } else {
    this.s(2);
    }
    } else if(this.cl == 0.0F) {
    this.s(this.x.cI);
    } else {
    this.s(2);
    }

    this.en = 0;
    }


    // ===== v19.115p 闂? 闂佽崵鍋炵粙蹇涘磿閸愬樊鍟? 02b 闂佽瀛╃粙鎺椼€冩径瀣╃箚妞ゆ挶鍨洪崕妯恒€掑锝呬壕闂侀€涘尃閸涱喖鐝?j.class =====
    // javap: public strictfp void C(am)/D(am)/E(am)/F(am); L()V; j(Z)V; b(h)V; a(b.n)闂備焦鍓氶崑鍛繆缁?    // 闂佽崵濮撮鍛村疮娴兼潙鏋侀柕鍫濐槹閸? custom/a/a/{e,k,l,o}.java 闂備胶鍎甸弲婵嬎夐幇鐗堝剨?(02b 闂傚倷绀佽ぐ鐐裁归崶顒佸剨?, 闂備礁鎼崐浠嬶綖婢跺本鍏滈柤绋跨仛婵挳鏌熼幆褍顣抽柡鍡楁閺?TODO 闂佽崵濮村ù鍌炲储閾忓湱涓嶉柣鏃傚帶鐟欙箓骞栨潏鍓ф偧闁轰線绠栭弻鐔衡偓娑欌棨椤忓牊鍊?
    public void D(UnitInstance am2) {
        // 02b j.D(am): 閸楁瓕娴囬崡鏇氱秴 (v19.115p 缁屽搫鐤勯悳?
    }

    public void E(UnitInstance am2) {
        // 02b j.E(am): 閻㈢喍楠囬崶鐐剁殶 (v19.115p 缁屽搫鐤勯悳?
    }

    public void F(UnitInstance am2) {
        // 02b j.F(am): 閻㈢喍楠囬崶鐐剁殶 (v19.115p 缁屽搫鐤勯悳?
    }

    public void L() {
        // 02b j.L(): 瀵偓婵宓忔潪鍊熺箥鏉?(v19.115p 缁屽搫鐤勯悳?
    }

    public void j(boolean bl2) {
        // 02b j.j(Z): 闁插秶鐤嗘妯款吇閺嶅洨顒?(v19.115p 缁屽搫鐤勯悳?
    }

    public void b(UnitConfig h2) {
        // 02b j.b(h): 缁夊娅庢稉瀛樻闂冪喍绱為弽鍥╊劮 (v19.115p 缁屽搫鐤勯悳?
    }

    public void dB() {  // 02b j.dB() L3863-3872
        if (this.dT != null) {
            for (int i2 = 0; i2 < this.dT.length; ++i2) {
                this.dT[i2].n = true;
                this.dT[i2].m = true;
            }
            this.dv();
        }
    }

    public static void dD() {  // 02b j.dD() L4194
    }


    public static void s(float f2) {}  // 02b custom/j.java L4192 缂傚倷绀侀懟顖炲箹椤愶附鍋柛鏇ㄥ灡閸?

    public boolean G(UnitInstance am2) {  // 02b j.G(am) L4771-4779: 缁撶畻妫€鏌?
        int n2 = this.dI();
        int n3 = am2.cw();
        if (this.x.eO) {
            n3 = 1;
        }
        return n2 + n3 <= this.x.eM;
    }

    public void e(com.corrodinggames.rts.game.PlayerState n2) {  // 02b j.e(n) L4797-4820: setPlayer
        if (this.bX != n2) {
            super.e(n2);
            if (this.B != null && !this.x.eX) {
                java.util.Iterator iterator = this.B.iterator();
                while (iterator.hasNext()) {
                    UnitInstance am2 = (UnitInstance) iterator.next();
                    am2.e(n2);
                }
            }
            if (this.C != null) {
                java.util.Iterator iterator2 = this.C.iterator();
                while (iterator2.hasNext()) {
                    UnitInstance am3 = (UnitInstance) iterator2.next();
                    if (am3 != null) {
                        com.corrodinggames.rts.game.units.custom.animation.UnitTrait unitTrait = am3.dn();
                        if (unitTrait != null && !unitTrait.z) {
                            am3.e(n2);
                        }
                    }
                }
            }
            this.a(com.corrodinggames.rts.game.units.custom.af.k);
        }
    }

}
