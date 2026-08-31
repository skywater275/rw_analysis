/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.units.TimedBomb;
import java.io.IOException;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.gameFramework.UnitGroup;
import com.corrodinggames.rts.gameFramework.SaveFileHandler;
import com.corrodinggames.rts.gameFramework.ReplayFrame;
import com.corrodinggames.rts.gameFramework.StatsTimeline;
import com.corrodinggames.rts.gameFramework.GameEvent;
import com.corrodinggames.rts.game.units.UnitShield;
import com.corrodinggames.rts.game.units.TreeDecoration;
import com.corrodinggames.rts.game.units.PlayerUnitIndicator;
import com.corrodinggames.rts.gameFramework.GamePhase;
import com.corrodinggames.rts.game.units.ExperimentalLandUnit;
import com.corrodinggames.rts.game.units.FireDecoration;
import com.corrodinggames.rts.game.units.DecorType4;
import com.corrodinggames.rts.game.units.LandUnit;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.AirUnit;
import com.corrodinggames.rts.game.units.actions.SellAction;
import com.corrodinggames.rts.game.units.actions.StopAction;
import com.corrodinggames.rts.game.units.PathfindingHelper;
import com.corrodinggames.rts.gameFramework.StatsCallback;
import com.corrodinggames.rts.game.units.CustomUnitBase;
import com.corrodinggames.rts.game.units.PathState;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.af;
import com.corrodinggames.rts.game.units.custom.ay;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.CustomVisuals;
import com.corrodinggames.rts.game.units.commands.ExperimentalUnit;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.effects.SoundEffect;
import com.corrodinggames.rts.gameFramework.SpriteBase;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.utility.ai;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import com.corrodinggames.rts.gameFramework.GameObject;

public strictfp class MovementController
extends SpriteBase {
    public com.corrodinggames.rts.gameFramework.effects.HUDElement aP;  // 02b f.aP L124: d.e
    public float aw;
    public static CustomArrayList a = new CustomArrayList();
    // v19.112c 补全 (02b 铁证: f.e(float) 空实现)
    public float e() {
        float f2 = 1.0f;
        if (this.J < this.F) {
            f2 = this.J / this.F;
        }
        return f2;
    }

    public void e(float var1) {
    }

    private static MovementController bm = new MovementController(true);
    static Texture b = null;
    static Texture c = null;
    static Texture d = null;
    static final Rect e = new Rect();
    static final RectF f = new RectF();
    public com.corrodinggames.rts.game.GameSettings g;  // 02b f.g=game.g (bh extends GameSettings)
    public float h;
    public float i;
    public UnitInstance j;
    public short k = (short)-1;
    public UnitInstance l;
    public boolean m;
    public float n;
    public float o;
    public float p;  // 02b f.p L46
    public float pathIndex;
    public MovementController q;
    public float r = -1.0f;
    public float s = 0.1f;
    public float t;
    public float u;
    public float v;
    public float w;
    public float x = 2.0f;
    public float y = -1.0f;
    public boolean z = true;
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public float F;
    public boolean G;
    public float H = 1.0f;
    public float I;
    public float J;
    public float K;
    public float L;
    public boolean M;
    public float N;
    public float[] nodePositions;
    public short P = (short)-1;
    public short Q = (short)-1;
    public short R = 0;
    public boolean S = true;
    public boolean T;
    public float U;
    public boolean V = false;
    public float W = 0.0f;
    public float X = 0.0f;
    public float Y;
    public float Z;
    public boolean aa;
    public boolean isPathfinding = false;
    public boolean ac = false;
    public boolean ad = false;
    public boolean ae = true;
    public boolean af;
    public float minMoveSpeed;
    public float maxMoveSpeedOverride;
    public float ai = 1.0f;
    public float aj = 1.0f;
    public float ak = 1.0f;
    public float al = 1.0f;
    public float am = 1.0f;
    public float an;
    public boolean ao;
    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList ap;  // 02b f.ap L98: utility.m (T0 javap)
    static final int aq = Color.a(255, 255, 255, 255);
    public int ar;  // 02b f.ar (defaultPathColor 为幻觉初始化)
    public boolean drawPathLine;
    public boolean boolean21;
    public CustomUnitBase attachedGameObject;
    public int av = -1;
    public float collisionRadius;
    public float ax;
    public float ay;
    public float az;
    public float ag;  // 02b f.ag L89
    public float ah;  // 02b f.ah L90
    public com.corrodinggames.rts.gameFramework.GameObject au;  // 02b f.au = w (w=GameObject, az extends w 铁证)

    public boolean ab;  // 02b f.ab L84

    public float heightLevel;
    public boolean aB;
    public boolean aC;
    public int aD;
    public UnitConfig aE;  // 02b game/f.java: public custom.h aE — h=UnitConfig (TagFilter 为幻觉)
    public float aF;
    public boolean aG;
    public boolean aH;
    public float aI = 40.0f;
    public float aJ = 60.0f;
    public boolean avoidFriendlies = false;
    public float aL = 2.0f;
    public boolean aM;
    public float aN;
    public float aO;
    public HUDElement selectionCircleUI;
    public boolean aQ;
    public boolean aR = true;
    private boolean bn;
    public boolean aS;
    public float aT = 0.0f;
    public boolean movementAnimActive;
    float aV;
    float impactPointY;
    float impactNormalX;
    public boolean aY;
    public boolean aZ;
    public static final UniquePaint ba = new UniquePaint();  // 02b f.java L136: ag ba = new ag() — m/ag=UniquePaint (PathState 为幻觉)
    public static final Paint bb = new Paint();
    public static final Paint bc = new UniquePaint();  // 02b f.java L138: Paint bc = new ag()
    public static final Paint bd = new Paint();  // 02b L139
    public static final Paint be = new Paint();  // 02b L140
    public static final Paint bf = new Paint();  // 02b L141
    public static final Paint bg = new Paint();  // 02b L142
    public static final Paint bh = new Paint();  // 02b L143
    public static final com.corrodinggames.rts.gameFramework.utility.UnitInstanceList bi = new com.corrodinggames.rts.gameFramework.utility.UnitInstanceList();  // 02b f.java L1991: bi = new utility/u() — u=UnitInstanceList (LobbyPlayer 为幻觉)
    public UniquePaint bj;
    public static UniquePaint bk;
    public static int bl;

    public MovementController(boolean bl) {
        super(bl);
        if (!bl) {
            a.add(this);  // 02b f.java L185: a.add(this) (GameFlag 为幻觉名)
        }
    }


    public void a() {  // 02b f.java L190: a()
        a.remove(this);  // 02b f.java L191: a.remove(this)
        super.a();  // 02b L192: super.a()
    }

    public void b() {  // 02b f.java L222: b() (updateSplashDamage 为幻觉名)
        if (this.D) {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            HUDElement e2 = l2.bR.d(this.eo, this.ep, this.eq, 0);
            if (e2 != null) {
                e2.G = 0.7f;
                e2.F = 2.1f;
                e2.ar = (short)2;
                e2.W = e2.V = 90.0f;
            }
            l2.bM.a(SoundRegistry.p, 0.8f, this.eo, this.ep);
        }
        this.a();  // 02b L237: this.a()
    }


    public void serializeToStream(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {  // 02b f.java L240: a(as) — 03 serializeToStream 惯例 (v19.112d 抽象对齐)
        as2.a(this.h);
        as2.a(this.j);
        as2.a(this.l);
        as2.a(this.t);
        as2.a(99);
        as2.a(this.A);
        as2.a(this.B);
        as2.a(this.S);
        as2.a(this.T);
        as2.a(this.U);
        as2.a(this.Y);
        as2.a(this.Z);
        as2.a(this.ar);
        as2.a(this.aH);
        as2.a(this.aI);
        as2.a(this.aJ);
        as2.a(this.avoidFriendlies);
        as2.a(this.aL);
        as2.a(this.aM);
        as2.a(this.aN);
        as2.a(this.aQ);
        as2.a(this.aR);
        as2.a(this.bn);
        as2.a(this.aS);
        as2.a(this.M);
        as2.a(this.P);
        as2.a(this.r);
        as2.a(this.s);
        as2.a(this.drawPathLine);
        as2.a(this.boolean21);
        as2.a(this.az);
        as2.a(this.heightLevel);
        as2.a(this.aB);
        as2.a(this.aC);
        as2.a(false);
        as2.a(0.0f);
        as2.a(0.0f);
        as2.a(this.E);
        as2.a(this.F);
        as2.a(this.J);
        as2.a(this.K);
        as2.a(this.L);
        as2.a(this.m);
        as2.a(this.n);
        as2.a(this.o);
        as2.a(this.C);
        as2.a(this.D);
        as2.a(this.q);
        as2.a(this.aV);
        as2.a(this.impactPointY);
        as2.a(this.impactNormalX);
        as2.a(this.V);
        as2.a(this.W);
        as2.a(this.X);
        as2.a(this.movementAnimActive);
        as2.a(this.R);
        as2.a(this.ao);
        as2.a(this.ap);
        as2.a(this.Q);
        as2.a(this.x);
        as2.a(this.aa);
        as2.a(this.ad);
        as2.a(this.G);
        as2.a(this.H);
        as2.a(this.ae);
        as2.a(this.aG);
        as2.a(this.z);
        as2.a(this.y);
        as2.a(this.aO);
        as2.a(this.i);
        as2.a(this.aY);
        as2.a(this.af);
        as2.a(this.minMoveSpeed);
        as2.a(this.maxMoveSpeedOverride);
        as2.a(this.ai);
        as2.a(this.aj);
        as2.a(0);
        as2.a(0.0f);
        as2.a(0.0f);
        as2.a((UnitTypeHandle) null);
        as2.a(0);
        as2.a(false);
        com.corrodinggames.rts.game.units.custom.TeamTag.a(this.aE, as2);  // 02b f.java L323: g.a(this.aE, var1) 写侧
        as2.a(this.ak);
        as2.a(this.al);
        as2.a(this.isPathfinding);
        as2.a(this.ac);
        as2.a(this.an);
        com.corrodinggames.rts.game.GameSettings.a(this.g, as2);  // 02b f.java L330: g.a(this.g, var1) 写侧
        as2.a(false);
        boolean bl = this.attachedGameObject != null && !this.attachedGameObject.ej;
        as2.a(bl);
        if (bl) {
            as2.a(this.attachedGameObject);
            as2.a(this.collisionRadius);
            as2.a(this.ax);
            as2.a(this.ay);
        }
        as2.a(this.k);
        as2.a(this.aD);
        as2.a(this.am);
        as2.a(this.pathIndex);
        as2.a(this.av);
        super.a(as2);  // 02b f.java L345: super.a(var1) (isVisibleOnScreen 为幻觉名)
    }


    public void a(InputNetStream k2) {
        boolean bl;
        this.h = k2.readFloat();
        this.j = k2.o();
        this.l = k2.a(com.corrodinggames.rts.gameFramework.network.PacketType.a);  // 02b L351: j.m=PacketType (network.m 为幻觉)
        this.t = k2.readFloat();
        this.x = k2.readInt();
        this.A = k2.readBoolean();
        this.B = k2.readBoolean();
        this.S = k2.readBoolean();
        this.T = k2.readBoolean();
        this.U = k2.readFloat();
        this.Y = k2.readFloat();
        this.Z = k2.readFloat();
        this.ar = k2.readInt();
        this.aH = k2.readBoolean();
        this.aI = k2.readFloat();
        this.aJ = k2.readFloat();
        this.avoidFriendlies = k2.readBoolean();
        this.aL = k2.readFloat();
        this.aM = k2.readBoolean();
        this.aN = k2.readFloat();
        this.aQ = k2.readBoolean();
        this.aR = k2.readBoolean();
        this.bn = k2.readBoolean();
        if (k2.b() >= 7) {
            this.aS = k2.readBoolean();
        }
        if (k2.b() >= 13) {
            this.M = k2.readBoolean();
            this.P = k2.v();
        }
        if (k2.b() >= 16) {
            this.r = k2.readFloat();
            this.s = k2.readFloat();
        }
        if (k2.b() >= 17) {
            this.drawPathLine = k2.readBoolean();
            this.boolean21 = k2.readBoolean();
            this.az = k2.readFloat();
            this.heightLevel = k2.readFloat();
            this.aB = k2.readBoolean();
            this.aC = k2.readBoolean();
        }
        if (k2.b() >= 18) {
            k2.readBoolean();
            k2.readFloat();
            k2.readFloat();
        }
        if (k2.b() >= 28) {
            this.E = k2.readBoolean();
            this.F = k2.readFloat();
            this.J = k2.readFloat();
            this.K = k2.readFloat();
            this.L = k2.readFloat();
        }
        if (k2.b() >= 29) {
            this.m = k2.readBoolean();
            this.n = k2.readFloat();
            this.o = k2.readFloat();
            this.C = k2.readBoolean();
            this.D = k2.readBoolean();
            this.q = (MovementController) k2.a(MovementController.class);  // 02b: var1.a(f.class) f=本类
            this.aV = k2.readFloat();
            this.impactPointY = k2.readFloat();
            this.impactNormalX = k2.readFloat();
            this.V = k2.readBoolean();
            this.W = k2.readFloat();
            this.X = k2.readFloat();
            this.movementAnimActive = k2.readBoolean();
            this.R = k2.v();
            this.ao = k2.readBoolean();
            com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
            k2.a(m2, com.corrodinggames.rts.game.units.UnitInstance.class);  // 02b: am.class (am=UnitInstance)
            if (m2.size() > 0) {
                this.ap = m2;
            }
            this.Q = k2.v();
        }
        if (k2.b() >= 35) {
            this.x = k2.readFloat();
            this.aa = k2.readBoolean();
            this.ad = k2.readBoolean();
            this.G = k2.readBoolean();
        }
        if (k2.b() >= 38) {
            this.H = k2.readFloat();
        }
        if (k2.b() >= 39) {
            this.ae = k2.readBoolean();
        }
        if (k2.b() >= 41) {
            this.aG = k2.readBoolean();
        }
        if (k2.b() >= 43) {
            this.z = k2.readBoolean();
            this.y = k2.readFloat();
        }
        if (k2.b() >= 44) {
            this.aO = k2.readFloat();
        }
        if (k2.b() >= 47) {
            this.i = k2.readFloat();
        }
        if (k2.b() >= 48) {
            this.aY = k2.readBoolean();
        }
        if (k2.b() >= 59) {
            this.af = k2.readBoolean();
            this.minMoveSpeed = k2.readFloat();
            this.maxMoveSpeedOverride = k2.readFloat();
            this.ai = k2.readFloat();
        }
        if (k2.b() >= 60) {
            this.aj = k2.readFloat();
            k2.readInt();
            k2.readFloat();
            k2.readFloat();
        }
        if (k2.b() >= 62) {
            k2.q();
            k2.readInt();
            k2.readBoolean();
        }
        if (k2.b() >= 63) {
            this.aE = com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(k2);
        }
        if (k2.b() >= 64) {
            this.ak = k2.readFloat();
            this.al = k2.readFloat();
        }
        if (k2.b() >= 66) {
            this.isPathfinding = k2.readBoolean();
            this.ac = k2.readBoolean();
        }
        if (k2.b() >= 67 && k2.b() < 78) {
            bp.a(k2, true);
        }
        if (k2.b() >= 68) {
            this.an = k2.readFloat();
        }
        if (k2.b() >= 77) {
            k2.readBoolean();
        }
        if (k2.b() >= 78) {
            try {
                this.g = com.corrodinggames.rts.game.GameSettings.readFromStream(k2);  // 02b: this.g = g.a(var1) 读侧
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
        }
        if (k2.b() >= 81 && (bl = k2.readBoolean())) {
            this.attachedGameObject = (CustomUnitBase) k2.a(com.corrodinggames.rts.gameFramework.GameObject.class);  // 02b L523: var1.a(w.class) w=GameObject
            this.collisionRadius = k2.readFloat();
            this.ax = k2.readFloat();
            this.ay = k2.readFloat();
        }
        if (k2.b() >= 83) {
            this.k = k2.v();
            this.aD = k2.readInt();
        }
        if (k2.b() >= 88) {
            this.am = k2.readFloat();
        }
        if (k2.b() >= 89) {
            this.pathIndex = k2.readFloat();
            this.av = k2.readInt();
        }
        super.a(k2);  // 02b 读方法尾部 super.a(var1)
    }

    public void a(UnitInstance am2) {  // 02b f.java L578: a(am) (isVisibleOnScreen 为幻觉名)
        if (this.minMoveSpeed != 0.0f || this.maxMoveSpeedOverride != 0.0f) {
            if (am2.isFactoryBuilding()) {
                return;
            }
            float f2 = GameUtils.a(this.aV, this.impactPointY, am2.eo, am2.ep);
            float f3 = f2 > 100.0f ? GameUtils.d(this.aV, this.impactPointY, am2.eo, am2.ep) : this.az;
            float f4 = this.maxMoveSpeedOverride;
            am2.cc += GameUtils.cosFast(f3) * (f4 += this.minMoveSpeed / am2.getBuildDuration());
            am2.cd += GameUtils.sinFast(f3) * f4;
        }
    }

    public static void a(UnitInstance am2, UnitInstance am3, float f2, MovementController f3, boolean bl) {  // 02b f.java L600: a(am,am,float,f,boolean)
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bw && f2 > 0.0f) {
            f2 = 0.0f;
        }
        if (am3 != null && !am3.isDead) {
            float f4;
            float f5;
            float f6;
            if (f3 != null && f3.g.bc && am2 != null) {
                am3.e(am2.player);
            }
            if (f3 != null) {
                if (f3.ai != 1.0f && am3.isFactoryBuilding()) {
                    f2 *= f3.ai;
                }
                if (f3.aj != 1.0f && am3.i()) {
                    f2 *= f3.aj;
                }
            }
            if (f2 < 0.0f) {
                f6 = am3.setTeamInternal(am2, -f2, f3);
            } else {
                boolean bl2 = !am3.isDead && am3.hp > 0.0f;
                f5 = am3.a(am2, f2, f3);
                f4 = f2;
                if (am3.J()) {
                    f4 = 0.0f;
                }
                if (f4 > 0.0f) {
                    l2.bY.a(am2, am3, f4);
                }
                if (am2 != null) {
                    am2.cV += f4;
                    if (bl2 && (am3.isDead || am3.hp < 0.0f)) {
                        ++am2.cU;
                        am2.a(com.corrodinggames.rts.game.units.custom.af.d, am3);
                    }
                }
            }
            if (f3 != null && !am3.isDead && (f6 = am3.getXpKillReward()) != -1.0f) {
                f5 = 100.0f;
                f4 = GameUtils.d(f3.eo, f3.ep, am3.eo, am3.ep);
                am3.cc += GameUtils.cosFast(f4) * (f5 /= f6);
                am3.cd += GameUtils.sinFast(f4) * f5;
            }
        }
    }

    public float onDraw() {
        float f2 = 1.0f;
        if (this.J < this.F) {
            f2 = this.J / this.F;
        }
        return f2;
    }


    public void a(float f2) {
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        HUDElement e2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.aS) {
            this.a();  // 02b f.a(float) 内 this.a() 铁证 (L671/675/1215/1512/1527)
        }
        if (this.l == null && !this.aC) {
            this.a();  // 02b f.a(float) 内 this.a() 铁证 (L671/675/1215/1512/1527)
            return;
        }
        if (this.i > 0.0f) {
            this.i = GameUtils.a(this.i, f2);
            if (this.i > 0.0f) {
                return;
            }
        }
        GameSettings g2 = this.g;
        if (this.i == 0.0f) {
            this.i = -1.0f;
            if (g2.al != null) {
                com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = null;  // 02b L689: (utility.m)var4
                boolean bl2 = false;
                UnitInstance l = this.j;
                MovementController f15 = this;
                g2.al.a(this.eo, this.ep, this.eq, this.az, l, m2, bl2, this.aD + 1, f15, null);  // 02b 字节码 L693: 第10参 slot8=null (am)
            }
        }
        this.h = GameUtils.a(this.h, f2);
        boolean bl3 = false;
        if (this.aG) {
            if (this.l == null) {
                bl3 = true;
            } else if (this.l.isDead) {
                bl3 = true;
            }
        }
        if (bl3) {
            this.a(g2.ax, g2.ay, null);  // 02b L708: this.a(var3.ax, var3.ay, (custom.h)null)
        }
        if (g2.az) {
            this.aF = GameUtils.a(this.aF, f2);
            if (this.aF == 0.0f) {
                this.aF = g2.aA;
                bl3 = true;
                this.a(g2.aB, g2.aC, g2.aD);  // 02b L716: this.a(var3.aB, var3.aC, var3.aD)
            }
        }
        if (g2.R != 0.0f || g2.S != 0.0f) {
            float f16 = g2.R;
            if (this.l != null) {
                f16 += this.l.cj * g2.S;
            }
            this.K = GameUtils.sinFast((360.0f * this.I + this.J * 1.0f) % 360.0f) * f16;
            this.L = GameUtils.sinFast((360.0f * this.I + this.J * 1.5f) % 360.0f) * f16;
        }
        if (this.E && this.l != null) {
            this.K = GameUtils.sinFast(this.J * 1.0f % 360.0f) * this.l.cj * 0.4f;
            this.L = GameUtils.sinFast(this.J * 1.5f % 360.0f) * this.l.cj * 0.4f;
            float f17 = this.l.eo + this.K;
            float f18 = this.l.ep + this.L;
            if (this.el) {
                this.aN += f2;
                this.aO += f2;
                if (this.aN > 11.0f) {
                    this.aN = GameUtils.c(1.0f, 4.0f);
                    boolean bl4 = false;
                    e2 = l2.bR.b(f17, f18, this.l.eq, SoundEffect.a, bl4, DrawLayer.b);
                    if (e2 != null) {
                        e2.aq = 0;
                        e2.ap = 0;
                        e2.ar = (short)2;
                        e2.r = true;
                        e2.E = 0.5f;
                        e2.W = 60.0f;
                        e2.V = 60.0f;
                        e2.G = 0.7f;
                        e2.F = 0.3f;
                        e2.as = false;
                        e2.P = GameUtils.c(-0.3f, 0.3f);
                        e2.Q = -0.9f + GameUtils.c(-0.3f, 0.3f);
                    }
                }
                if (this.aO > 75.0f) {
                    this.aO = GameUtils.c(1.0f, 20.0f);
                    l2.bR.b(f17, f18, this.l.eq);
                }
            }
        }
        float f19 = 5.0f;
        boolean bl5 = false;
        boolean bl6 = false;
        if (!this.aC) {
            f14 = this.l.eo + this.K;
            f13 = this.l.ep + this.L;
            f12 = this.l.eq;
            f11 = GameUtils.d(this.eo, this.ep, f14, f13);
            f10 = GameUtils.a(this.eo, this.ep, f14, f13);
            f9 = f12;
            f8 = f9 - this.eq;
            f19 = this.l.cj;
            bl5 = this.l instanceof com.corrodinggames.rts.game.units.commands.BuildSlot;  // 02b L784: units.d.d=BuildSlot
            bl6 = this.l.cx > 10.0f + this.U;
        } else {
            f10 = 999.0f;
            f8 = 0.0f;
            f11 = this.az;
            f9 = 0.0f;
            if (this.q != null) {
                f14 = this.q.eo + this.K;
                f13 = this.q.ep + this.L;
                f12 = this.q.eq;
                f11 = GameUtils.d(this.eo, this.ep, f14, f13);
                f10 = GameUtils.a(this.eo, this.ep, f14, f13);
                f9 = f12;
                f8 = f9 - this.eq;
            } else if (this.l != null) {
                f14 = this.l.eo + this.K;
                f13 = this.l.ep + this.L;
                f12 = this.l.eq;
                f11 = GameUtils.d(this.eo, this.ep, f14, f13);
                f10 = GameUtils.a(this.eo, this.ep, f14, f13);
                f9 = f12;
                f8 = f9 - this.eq;
                f19 = this.l.cj;
                bl5 = this.l instanceof com.corrodinggames.rts.game.units.commands.BuildSlot;  // 02b L808: units.d.d=BuildSlot
                bl6 = this.l.cx > 10.0f + this.U;
            } else if (this.m) {
                f14 = this.n + this.K;
                f13 = this.o + this.L;
                f12 = this.pathIndex;
                f11 = GameUtils.d(this.eo, this.ep, f14, f13);
                f10 = GameUtils.a(this.eo, this.ep, f14, f13);
                f9 = f12;
                f8 = f9 - this.eq;
            } else {
                f14 = this.n + this.K;
                f13 = this.o + this.L;
                f12 = 0.0f;
                f11 = GameUtils.d(this.eo, this.ep, f14, f13);
                f10 = GameUtils.a(this.eo, this.ep, f14, f13);
                f9 = f12;
                f8 = f9 - this.eq;
            }
        }
        f14 = g2.O;
        if (f10 < 225.0f) {
            f14 = g2.P;
        }
        if (f14 >= 0.0f) {
            f13 = GameUtils.c(this.az, f11, f14 * f2);
            this.az += f13;
            f11 = this.az;
        } else {
            this.az = f11;
        }
        boolean bl7 = false;
        boolean bl8 = false;
        float f20 = f11;
        if (this.attachedGameObject != null && !this.attachedGameObject.ej) {
            float f21;
            if (this.av >= 0) {
                com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)this.attachedGameObject;
                if (this.av >= y2.bl()) {
                    this.av = 0;
                }
                com.corrodinggames.rts.gameFramework.utility.ai ai2 = y2.D(this.av);  // 02b L859: ai var20 (utility.ai, FireDecoration 为幻觉)
                f7 = ai2.a;
                f6 = ai2.b;
                f21 = this.j.eq + ai2.c;
            } else {
                f7 = this.attachedGameObject.eo;
                f6 = this.attachedGameObject.ep;
                f21 = this.attachedGameObject.eq;
            }
            f5 = f7 - this.collisionRadius;
            f4 = f6 - this.ax;
            f3 = f21 - this.ay;
            this.eo += f5;
            this.ep += f4;
            this.eq += f3;
            this.collisionRadius = f7;
            this.ax = f6;
            this.ay = f21;
        }
        if (!this.A) {
            this.eo += this.u * f2;
            this.ep += this.v * f2;
            if (this.w != 0.0f) {
                f7 = this.w * f2;
                this.eq += f7;
                f8 = f9 - this.eq;
            }
            if (this.eq > 0.0f) {
                if (g2.G != 0.0f) {
                    this.eq -= g2.G * f2;
                    f8 = f9 - this.eq;
                }
                if (g2.H != 0.0f) {
                    this.w -= g2.H * f2;
                }
            }
            if (!this.aH || this.aI < this.eq || this.avoidFriendlies) {
                f7 = this.t * f2;
                bl7 = true;
                if (f10 < f7 * f7) {
                    f7 = GameUtils.a(f10);
                    f10 = 0.0f;
                }
                this.eo += GameUtils.cosFast(f11) * f7;
                this.ep += GameUtils.sinFast(f11) * f7;
            }
            if (this.aH) {
                if (this.aL < 0.0f) {
                    f7 = this.t * f2;
                    bl7 = true;
                } else {
                    f7 = this.aL * f2;
                }
                if (!this.avoidFriendlies) {
                    this.eq = GameUtils.a(this.eq, this.aJ, f7);
                    if (this.eq < this.aI) {
                        f20 = -90.0f;
                    }
                    if (this.eq >= this.aJ) {
                        this.avoidFriendlies = true;
                    }
                } else if (f10 < 400.0f) {
                    this.eq = GameUtils.a(this.eq, f9, f7);
                    if (GameUtils.c(this.eq - f9) > 0.5f) {
                        f20 = 90.0f;
                        bl8 = true;
                    }
                }
            } else {
                f7 = f8;
                f6 = this.t * f2;
                bl7 = true;
                if (f7 != 0.0f) {
                    if ((double)f10 > 0.1) {
                        f6 = GameUtils.c(f7) / GameUtils.a(f10) * this.t * f2;
                        f6 = GameUtils.b(f6, this.t * f2);
                    }
                    this.eq += GameUtils.b(f8, f6);
                    f8 = f9 - this.eq;
                }
            }
        }
        if (bl7 && this.r > 0.0f) {
            this.t = GameUtils.a(this.t, this.r, this.s * f2);
        }
        if (g2.am != 0.0f) {
            f7 = GameUtils.sinFast((this.J * 360.0f / g2.an + 360.0f * this.I) % 360.0f);
            f7 = f7 * g2.am * f2;
            this.eo += GameUtils.cosFast(f11 + 90.0f) * f7;
            this.ep += GameUtils.sinFast(f11 + 90.0f) * f7;
        }
        if (this.el && (this.aM || g2.ah != null) && !this.bn) {
            this.aN += f2;
            if (this.aN > g2.ag) {
                HUDElement e3;
                this.aN = 0.0f;
                boolean bl9 = false;
                if (this.D) {
                    bl9 = true;
                }
                if (g2.ah != null) {
                    g2.ah.a(this.eo, this.ep, this.eq, this.aT, this);
                }
                if (this.aM && (e3 = l2.bR.b(this.eo, this.ep, this.eq, SoundEffect.a, bl9, DrawLayer.b)) != null) {
                    if (this.eq >= 0.0f) {
                        e3.aq = 0;
                        e3.ap = 0;
                        e3.ar = (short)2;
                        e3.r = true;
                        e3.E = 0.5f;
                        e3.W = e3.V = 70.0f;
                        e3.as = true;
                        if (bl8) {
                            e3.as = false;
                        }
                        e3.Q = 0.1f;
                        e3.s = true;
                        e3.t = 5.0f;
                        e3.G = 0.5f;
                        e3.F = 1.2f;
                        e3.Y = GameUtils.c(-180.0f, 180.0f);
                        if (this.D) {
                            e3.G = 0.5f;
                            e3.F = 2.1f;
                        }
                    } else {
                        e3.aq = 9;
                        e3.ap = 1;
                        e3.ar = 1;
                        e3.r = true;
                        e3.E = 0.5f;
                        e3.W = 60.0f;
                        e3.V = 60.0f;
                        e3.Q = 0.1f;
                    }
                }
            }
        }
        if (!this.bn) {
            Object object;
            int n2;
            int n3;
            Object object2;
            boolean bl10 = false;
            UnitInstance am3 = null;
            boolean bl11 = false;
            f5 = 6.0f;
            if (bl5 && (f5 = f19 * 0.8f) < 6.0f) {
                f5 = 6.0f;
            }
            if (bl6) {
                f5 = f19 * 1.1f;
            }
            f4 = 3.0f;
            if (this.w != 0.0f || g2.G != 0.0f) {
                f4 += GameUtils.c(this.w * f2) + GameUtils.c(g2.G * f2);
            }
            if (f10 < f5 * f5 && GameUtils.c(f8) < f4) {
                bl10 = true;
                am3 = this.l;
            }
            if (this.A) {
                bl10 = true;
                am3 = this.l;
            }
            if (this.af && this.h == 0.0f) {
                bl10 = true;
            }
            if (this.drawPathLine) {
                f3 = this.heightLevel + 50.0f;
                UnitInstance[] objectArray5 = com.corrodinggames.rts.game.units.UnitInstance.bE.a();  // 02b L1056: am[] var22 (Object[] 为误标)
                n3 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
                for (n2 = 0; n2 < n3; n2 += 1) {
                    float f22;
                    float f23;
                    object = objectArray5[n2];
                    if (!(((UnitInstance) object).eo + f3 > this.eo) || !(((UnitInstance) object).eo - f3 < this.eo) || !(((UnitInstance) object).ep + f3 > this.ep) || !(((UnitInstance) object).ep - f3 < this.ep) || !((UnitInstance) object).bT || ((UnitInstance) object).i() || ((UnitInstance) object).cN != null || !((f23 = GameUtils.a(this.eo, this.ep, ((UnitInstance) object).eo, ((UnitInstance) object).ep)) < (f22 = this.heightLevel + ((UnitInstance) object).cj) * f22)) continue;
                    bl10 = true;
                    am3 = (UnitInstance) object;  // 02b L1066: var38 = var25
                }
            }
            if (this.boolean21) {
                l2.bL.a(this.eo, this.ep);
                int n4 = l2.bL.scrollPixelX;
                int n5 = l2.bL.scrollPixelY;
                if (l2.bU.a(com.corrodinggames.rts.game.units.MovementTypeEnum.f, n4, n5)) {
                    bl10 = true;
                    bl11 = true;
                }
            }
            if (this.aC) {
                // empty if block
            }
            if (this.aY && (this.aH && bl8 && this.eq < 30.0f || bl10) && this.j != null) {
                this.aY = false;
                TimedBomb u2 = new TimedBomb(false);
                u2.eo = this.eo;
                u2.ep = this.ep;
                u2.b(this.j.player);
                u2.a = 15;
                u2.b = 360.0f;
                PlayerState.c(u2);
            }
            if (bl10) {
                this.bn = true;  // 02b L1097-1101: bl10 块 (SellAction z2 为幻觉声明)
                this.aV = this.eo;
                this.impactPointY = this.ep;
                this.impactNormalX = this.eq;
                if (this.A) {
                    if (this.aC) {
                        this.aV = this.n;
                        this.impactPointY = this.o;
                        this.impactNormalX = 0.0f;
                    }
                    if (this.l != null) {
                        this.aV = this.l.eo + this.K;
                        this.impactPointY = this.l.ep + this.L;
                        this.impactNormalX = this.l.eq;
                    }
                }
                if (!(this.B || this.M || g2.X)) {
                    this.S = false;
                }
                boolean bl12 = false;
                if (this.l != null) {
                    bl12 = this.l.cx > 10.0f;
                }
                CustomVisuals z2 = g2.aX;  // 02b L1125: z var47 = var3.aX
                if (bl12) {
                    z2 = g2.aY;
                }
                if (this.l != null && (z2 = g2.getVisualOverride(this.l)) != null) {
                }
                if (z2 != null) {  // 02b L1138: if(var47 != null) var47.a(...) (object2 为幻觉)
                    z2.a(this.aV, this.impactPointY, this.impactNormalX, this.aT, this.l);
                }
                if (g2.aj != null) {
                    com.corrodinggames.rts.gameFramework.utility.CustomArrayList m3 = null;  // 02b 字节码 L1143: slot23=null (utility.m; CustomVisuals 为 FF 误标)
                    n3 = 0;
                    object = this.j;
                    MovementController f24 = this;
                    UnitInstance am4 = this.l;
                    g2.aj.a(this.eo, this.ep, this.eq, this.az, (UnitInstance) object, m3, n3 != 0, this.aD + 1, f24, am4);
                }
                if (g2.aZ != null && this.j != null) {
                    g2.aZ.a(this.aV, this.impactPointY, 0.0f, this.az, this.j.player, false, this.j);
                }
                if (g2.ba > 0 && this.j != null && this.j instanceof com.corrodinggames.rts.game.units.custom.CustomUnitType) {  // 02b L1154: custom.j
                    com.corrodinggames.rts.game.units.custom.CustomUnitType j2 = (com.corrodinggames.rts.game.units.custom.CustomUnitType) this.j;  // 02b L1155 (ResourceLoader 为幻觉)
                    for (n3 = 0; n3 < g2.ba; n3 += 1) {
                        if (j2.B == null || j2.B.size() <= 0) continue;
                        object = (UnitInstance) j2.B.remove(j2.B.size() - 1);
                        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) object, j2);
                        ((UnitInstance) object).eo = this.aV;
                        ((UnitInstance) object).ep = this.impactPointY;
                        ((UnitInstance) object).cg = this.az;
                        ((UnitInstance) object).cd = 0.0f;
                        ((UnitInstance) object).cc = 0.0f;
                        ((UnitInstance) object).bZ = 0.0f;
                        ((UnitInstance) object).ca = 0.0f;
                        if (object instanceof com.corrodinggames.rts.game.units.UnitType) {  // 02b L1168: instanceof y
                            com.corrodinggames.rts.game.units.UnitType y3 = (com.corrodinggames.rts.game.units.UnitType)object;
                            y3.az();
                            y3.j(((UnitInstance) object).cg);
                            if (object instanceof com.corrodinggames.rts.game.units.custom.CustomUnitType) {  // 02b L1172
                                ((com.corrodinggames.rts.game.units.custom.CustomUnitType) object).dF();
                            }
                        }
                        j2.D((UnitInstance) object);  // 02b L1177: var51.D(var25)
                    }
                }
                if (g2.bb && this.j != null) {
                    this.j.f(this.aV, this.impactPointY);
                }
                if (!bl11 && am3 != null) {
                    if (this.E) {
                        this.bn = false;
                        float f25 = this.U / 60.0f * f2 * this.onDraw();
                        if (this.Z == 0.0f) {
                            this.a(am3);  // 02b: this.a(var9)
                        }
                        f25 = g2.applyMultipliers(am3, f25, true);  // 02b: applyMultipliers (GameSettings.a(am,f,Z) 语义名)
                        com.corrodinggames.rts.game.MovementController.a(this.j, am3, f25, this, false);  // 02b L600: 本类静态 a(am,am,float,f,boolean) (projectiles 为幻觉包)
                    } else {
                        if (this.Z == 0.0f) {
                            this.a(am3);
                        }
                        float f26 = this.U;
                        f26 = g2.applyMultipliers(am3, f26, false);
                        com.corrodinggames.rts.game.MovementController.a(this.j, am3, f26, this, false);
                    }
                }
                if (this.q != null) {
                    if (g2.d) {
                        this.q.h = 0.0f;
                    } else {
                        this.q.b();
                    }
                    this.a();  // 02b f.a(float) 内 this.a() 铁证 (L671/675/1215/1512/1527)
                }
                if (!this.E) {
                    n2 = 1;
                    if (this.l != null && this.l.cx > 10.0f) {
                        HUDElement e4;
                        n2 = 0;
                        if (g2.aY == null && (e4 = l2.bR.d(this.aV, this.impactPointY, this.impactNormalX, -1127220)) != null) {
                            e4.V = 10.0f;
                            e4.F = 0.5f;
                            if (this.aQ) {
                                e4.V = 25.0f;
                                e4.F = 1.0f;
                            }
                            e4.ar = (short)2;
                            e4.W = e4.V;
                        }
                    }
                    if (this.G) {
                        n2 = 0;
                        com.corrodinggames.rts.gameFramework.effects.DrawEffect f27 = com.corrodinggames.rts.gameFramework.effects.DrawEffect.b(this.eo, this.ep);  // 02b L1241: d.f.b
                        f27.a = 21.0f;
                    }
                    if (n2 != 0) {  // 02b L1245: if(var55) — n2 为 0/1 布尔模拟
                        if (!this.aQ) {
                            if (g2.aX == null) {
                                l2.bR.c(this.aV, this.impactPointY, this.impactNormalX);
                            }
                        } else if (g2.aX == null) {
                            HUDElement e5;
                            if (this.Z > 10.0f && (e5 = l2.bR.d(this.aV, this.impactPointY, this.impactNormalX, 0)) != null) {
                                e5.F = this.Z / 25.0f;
                                e5.E = 0.7f;
                                if (this.impactNormalX > 5.0f) {
                                    e5.ar = (short)2;
                                }
                            }
                            l2.bR.b(this.aV, this.impactPointY, this.impactNormalX);
                            if (this.aR && !this.D) {
                                float f28 = 1.0f + GameUtils.c(-0.06f, 0.06f);
                                l2.bM.a(SoundRegistry.n, 0.5f, f28, this.aV, this.impactPointY);
                            }
                        }
                        if (this.D && g2.aX == null) {
                            int n6;
                            float f29 = 0.7f;
                            l2.bM.a(SoundRegistry.C, 1.6f, f29, this.aV, this.impactPointY);
                            l2.bR.b(DrawLayer.e);
                            object = l2.bR.a(this.aV, this.impactPointY, this.eq, Color.a(255, 255, 255, 255));
                            if (object != null) {
                                ((HUDElement)object).G = 14.0f;
                                ((HUDElement)object).F = 8.0f;
                                ((HUDElement)object).E = 0.9f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 35.0f;
                                ((HUDElement)object).r = true;
                            }
                            l2.bR.b(DrawLayer.e);
                            object = l2.bR.c(this.aV, this.impactPointY, this.impactNormalX, -1127220);
                            if (object != null) {
                                ((HUDElement)object).G = 1.5f;
                                ((HUDElement)object).F = 3.0f;
                                ((HUDElement)object).ar = (short)2;
                                ((HUDElement)object).W = ((HUDElement)object).V = 20.0f;
                                ((HUDElement)object).U = 0.0f;
                            }
                            l2.bR.b(DrawLayer.e);
                            object = l2.bR.c(this.aV, this.impactPointY, this.impactNormalX, -1127220);
                            if (object != null) {
                                ((HUDElement)object).G = 0.2f;
                                ((HUDElement)object).F = 5.0f;
                                ((HUDElement)object).ar = (short)2;
                                ((HUDElement)object).W = ((HUDElement)object).V = 65.0f;
                                ((HUDElement)object).U = 0.0f;
                            }
                            l2.bR.b(DrawLayer.e);
                            object = l2.bR.a(this.aV, this.impactPointY, this.eq, Color.a(255, 255, 255, 255));
                            if (object != null) {
                                ((HUDElement)object).G = 3.0f;
                                ((HUDElement)object).F = 6.0f;
                                ((HUDElement)object).E = 0.9f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 290.0f;
                            }
                            l2.bR.b(DrawLayer.e);
                            object = l2.bR.a(this.aV, this.impactPointY, this.eq, Color.a(255, 255, 244, 230));
                            if (object != null) {
                                ((HUDElement)object).G = 2.0f;
                                ((HUDElement)object).F = 6.0f;
                                ((HUDElement)object).E = 0.5f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 370.0f;
                                ((HUDElement)object).U = 10.0f;
                            }
                            for (n6 = 0; n6 < 1; ++n6) {
                                l2.bR.b(DrawLayer.e);
                                object = l2.bR.a(this.aV, this.impactPointY, this.eq, Color.a(255, 255, 244, 230));
                                if (object == null) continue;
                                ((HUDElement)object).G = 0.2f;
                                ((HUDElement)object).F = 9.0f;
                                ((HUDElement)object).E = 0.7f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 210.0f;
                                ((HUDElement)object).U = 20 + n6 * 110;
                            }
                            l2.bR.b(DrawLayer.e);
                            object = l2.bR.a(this.aV, this.impactPointY, this.eq, Color.a(255, 255, 255, 255));
                            if (object != null) {
                                ((HUDElement)object).G = 3.0f;
                                ((HUDElement)object).F = 4.0f;
                                ((HUDElement)object).E = 0.2f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 870.0f;
                                ((HUDElement)object).r = true;
                                ((HUDElement)object).U = 70.0f;
                            }
                            l2.bR.b(DrawLayer.e);
                            object = l2.bR.a(this.aV, this.impactPointY, this.eq, Color.a(255, 206, 255, 239));
                            if (object != null) {
                                ((HUDElement)object).G = 4.0f;
                                ((HUDElement)object).F = 1.0f;
                                ((HUDElement)object).E = 0.9f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 320.0f;
                            }
                            l2.bR.b(DrawLayer.e);
                            object = l2.bR.a(this.aV, this.impactPointY, this.eq, Color.a(255, 255, 242, 129));
                            if (object != null) {
                                ((HUDElement)object).G = 2.0f;
                                ((HUDElement)object).F = 1.0f;
                                ((HUDElement)object).E = 1.0f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 340.0f;
                                ((HUDElement)object).s = true;
                                ((HUDElement)object).t = 20.0f;
                            }
                            l2.bR.b(DrawLayer.e);
                            object = l2.bR.a(this.aV, this.impactPointY, this.eq, Color.a(245, 255, 182, 110));
                            if (object != null) {
                                ((HUDElement)object).G = 1.5f;
                                ((HUDElement)object).F = 1.5f;
                                ((HUDElement)object).E = 0.3f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 1340.0f;
                                ((HUDElement)object).s = true;
                                ((HUDElement)object).t = 40.0f;
                                ((HUDElement)object).U = 140.0f;
                            }
                            for (n6 = 0; n6 < 4; ++n6) {
                                l2.bR.b(DrawLayer.e);
                                object = l2.bR.a(this.aV, this.impactPointY, this.eq, Color.a(225, 255, 242, 129));
                                if (object == null) continue;
                                ((HUDElement)object).G = 1.5f;
                                ((HUDElement)object).F = 1.4f;
                                ((HUDElement)object).E = 1.3f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 340.0f;
                                ((HUDElement)object).Q = -0.29f;
                                ((HUDElement)object).s = true;
                                ((HUDElement)object).t = 50.0f;
                                ((HUDElement)object).U = 30 + n6 * 40;
                            }
                            for (n6 = 0; n6 < 2; ++n6) {
                                l2.bR.b(DrawLayer.e);
                                object = l2.bR.a(this.aV, this.impactPointY, this.eq, Color.a(185, 255, 242, 129));
                                if (object == null) continue;
                                ((HUDElement)object).G = 1.3f;
                                ((HUDElement)object).F = 1.0f;
                                ((HUDElement)object).E = 1.0f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 340.0f;
                                ((HUDElement)object).Q = -0.14f;
                                ((HUDElement)object).s = true;
                                ((HUDElement)object).t = 50.0f;
                                ((HUDElement)object).U = 70 + n6 * 70;
                            }
                            for (n6 = 0; n6 < 4; ++n6) {
                                l2.bR.b(DrawLayer.e);
                                object = l2.bR.a(this.aV, this.impactPointY - 30.0f, this.eq, -16711936);
                                if (object == null) continue;
                                ((HUDElement)object).G = 1.5f;
                                ((HUDElement)object).F = 2.6f;
                                ((HUDElement)object).E = 1.3f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 510.0f;
                                ((HUDElement)object).Q = -0.2f;
                                ((HUDElement)object).s = true;
                                ((HUDElement)object).t = 50.0f;
                                ((HUDElement)object).B = null;
                                ((HUDElement)object).x = Color.a(175, 235, 235, 235);
                                ((HUDElement)object).U = 20 + n6 * 40;
                            }
                            for (n6 = 0; n6 < 2; ++n6) {
                                l2.bR.b(DrawLayer.e);
                                object = l2.bR.a(this.aV, this.impactPointY - 30.0f, this.eq, -16711936);
                                if (object == null) continue;
                                ((HUDElement)object).G = 1.5f;
                                ((HUDElement)object).F = 3.8f;
                                ((HUDElement)object).E = 0.8f;
                                ((HUDElement)object).W = ((HUDElement)object).V = 590.0f;
                                ((HUDElement)object).Q = -0.2f;
                                ((HUDElement)object).s = true;
                                ((HUDElement)object).t = 50.0f;
                                ((HUDElement)object).B = null;
                                ((HUDElement)object).x = Color.a(105, 115, 115, 115);
                                ((HUDElement)object).U = 20 + n6 * 40;
                            }
                            for (n6 = 0; n6 < 1; ++n6) {
                                com.corrodinggames.rts.gameFramework.effects.DrawEffect f30 = com.corrodinggames.rts.gameFramework.effects.DrawEffect.a(this.aV + GameUtils.a(-10.0f, 10.0f, (int)this.eh), this.impactPointY + GameUtils.a(-10.0f, 10.0f, (int)this.eh + n6));  // 02b: d.f.a
                                if (f30 == null) continue;
                                f30.t = 200 + n6 * 70;
                                f30.a = 980 + n6 * 800;
                            }
                            if (com.corrodinggames.rts.gameFramework.GlobalState.aB()) {
                                if (l2.bR.m == null) {
                                    l2.bR.m = l2.bO.a(R$drawable.shockwave_normal_256, true);
                                }
                                l2.bR.b(DrawLayer.e);
                                object = l2.bR.a(this.aV, this.impactPointY, this.eq, -1);
                                if (object != null && l2.bR.m != null) {
                                    ((HUDElement)object).a = new ay(null);
                                    ((HUDElement)object).a.imageStrip = new com.corrodinggames.rts.gameFramework.effects.HUDElementRenderer();
                                    ((HUDElement)object).a.imageStrip.k = true;
                                    ((HUDElement)object).a.imageStrip.i = l2.bR.m;
                                    ((HUDElement)object).a.imageStrip.b = ((HUDElement)object).a.imageStrip.i.m();
                                    ((HUDElement)object).a.imageStrip.c = ((HUDElement)object).a.imageStrip.i.l();  // 02b L1481: i.l() (readString 为幻觉名)
                                    ((HUDElement)object).ar = (short)3;
                                    ((HUDElement)object).G = 0.5f;
                                    ((HUDElement)object).F = 3.5f;
                                    ((HUDElement)object).E = 0.5f;
                                    ((HUDElement)object).W = ((HUDElement)object).V = 60.0f;
                                    ((HUDElement)object).Q = -0.2f;
                                    ((HUDElement)object).s = true;
                                    ((HUDElement)object).t = 1.0f;
                                    ((HUDElement)object).B = null;
                                    ((HUDElement)object).U = 0.0f;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.bn && !this.V) {
            this.W = GameUtils.a(this.W, f2);
            if (this.ao) {
                float f31 = 1.0f - this.W / this.X;
                this.b(f31);  // 02b L1505: this.b(var16) (updateSplashDamage 为幻觉名)
            }
            if (this.W == 0.0f) {
                this.V = true;
                this.b(1.0f);  // 02b L1510: this.b(1.0F)
                if (!(this.B || this.M || g2.X)) {
                    this.a();  // 02b f.a(float) 内 this.a() 铁证 (L671/675/1215/1512/1527)
                }
            }
        }
        this.J += f2;
        if (this.h == 0.0f && (!this.bn || this.V)) {
            if (g2.ak != null) {
                com.corrodinggames.rts.gameFramework.utility.CustomArrayList m4 = null;  // 02b L1524: (utility.m)var49
                boolean bl13 = false;
                UnitInstance am5 = this.j;
                MovementController f32 = this;
                UnitInstance am6 = null;
                g2.ak.a(this.eo, this.ep, this.eq, this.az, am5, m4, bl13, this.aD + 1, f32, am6);
            }
            this.a();  // 02b f.a(float) 内 this.a() 铁证 (L671/675/1215/1512/1527)
        }
        if (!this.movementAnimActive) {
            this.aT = f20;
            this.movementAnimActive = true;
        }
        float f33 = GameUtils.c(this.aT, f20, 12.0f * f2);
        this.aT += f33;
    }

    public void b(float f2) {  // 02b f.java L1540: b(float) (updateSplashDamage 为幻觉名)
        float f3;
        boolean bl2 = false;
        if (this.g.f) {
            return;
        }
        if (this.g.e) {
            bl2 = true;
        }
        if (!bl2) {
            if (this.Y != 0.0f && this.Z > 0.0f) {
                bl2 = true;
            }
            if ((this.minMoveSpeed != 0.0f || this.maxMoveSpeedOverride != 0.0f) && this.Z > 0.0f) {
                bl2 = true;
            }
        }
        if (!bl2) {
            return;
        }
        float f4 = f3 = this.Z * f2;
        if (this.g.h) {
            f4 += 150.0f;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        bi.clear();  // 02b L1565: bi.clear() (StatsTimeline 为幻觉名)
        l2.cc.b(this.aV, this.impactPointY, f4, bi);
        UnitInstance[] amArray = bi.a();  // 02b L1567: bi.a()
        int n2 = bi.size();  // 02b L1568: bi.size()
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance l = amArray[i2];
            this.b(l, f2, f3);  // 02b L1572: this.b(var9, var1, var3)
        }
        bi.clear();  // 02b L1575: bi.clear()
    }

    public void b(UnitInstance am2, float f2, float f3) {  // 02b f.java L1580: b(am,float,float) (updateSplashDamage 为幻觉名)
        float f4;
        if (am2.cN != null) {
            return;
        }
        if (this.ap != null && this.ap.contains(am2)) {
            return;
        }
        if (this.j != null) {
            PlayerState n2 = am2.player;
            PlayerState n3 = this.j.player;
            if (n2 != n3 && n3.d(n2)) {
                return;
            }
            if (this.aa && !n3.c(n2)) {
                return;
            }
            if (this.isPathfinding && n3.c(n2)) {
                return;
            }
        }
        if (am2.eq < -5.0f && this.impactNormalX >= -2.0f && !this.ac) {
            return;
        }
        if (this.ae) {
            boolean bl2;
            boolean bl3 = bl2 = this.impactNormalX >= 5.0f;
            if (am2.i() != bl2) {
                return;
            }
        } else if (!this.ad && am2.i()) {
            return;
        }
        if ((f4 = GameUtils.a(this.aV, this.impactPointY, am2.eo, am2.ep)) > f3 * f3 && !this.g.h) {
            return;
        }
        float f5 = (float)StrictMath.sqrt(f4);
        if (this.g.h && (f5 -= am2.cj) < 0.0f) {
            f5 = 0.0f;
        }
        if (f5 > f3) {
            return;
        }
        if (f5 < this.g.j) {
            return;
        }
        this.a(f2, am2, f5);  // 02b L1621: this.a(var2, var1, var8)
    }

    public void a(float f2, UnitInstance am2, float f3) {  // 02b f.java L1630: a(float,am,float) (isVisibleOnScreen 为幻觉名)
        float f4 = 1.0f - f3 / this.Z;
        if ((f4 = (float)((double)f4 + 0.1)) > 1.0f) {
            f4 = 1.0f;
        }
        if (this.g.g) {
            f4 = 1.0f;
        }
        float f5 = f4 * this.Y;
        this.a(am2);  // 02b L1642: this.a(var2)
        f5 = this.g.applyMultipliers(am2, f5, true);  // 02b g.java L145: a(am,float,boolean)
        com.corrodinggames.rts.game.MovementController.a(this.j, am2, f5, this, true);  // 02b L1644: 本类静态 a(am,am,float,f,boolean) (projectiles 为幻觉包名)
        if (this.ao) {
            if (this.ap == null) {
                this.ap = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
            }
            this.ap.add(am2);
        }
    }


    public boolean a(com.corrodinggames.rts.gameFramework.GlobalState l2) {  // 02b f.java L1655: a(l) (Projectile 为幻觉名)
        if (l2.cO.b(this.eo, this.ep)) {
            return true;
        }
        return (this.B || this.E || this.g.X) && this.l != null && l2.cO.b(this.l.eo, this.l.ep);
    }


    public boolean c(float f2) {
        int n2;
        float f3;
        float f4;
        float f5;
        if (!this.S) {
            return false;
        }
        if (this.i > 0.0f) {
            return false;
        }
        GameSettings g2 = this.g;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface y2 = l2.bO;
        float f6 = this.eo - l2.cw;
        float f7 = this.ep - l2.cx;
        if (this.l != null) {
            f5 = this.l.eo;
            f4 = this.l.ep;
            f3 = this.l.eq;
        } else {
            f5 = this.n;
            f4 = this.o;
            f3 = this.pathIndex;
        }
        if (!this.aZ && !this.D) {
            n2 = 0;
            if (this.A) {
                if (this.l != null) {
                    if (!l2.bL.a(this.l.eo, this.l.ep, l2.bs)) {
                        n2 = 1;
                    }
                } else if (this.m && !l2.bL.a(this.n, this.o, l2.bs)) {
                    n2 = 1;
                }
            }
            if (!l2.bL.a(this.eo, this.ep, l2.bs) && n2 == 0) {
                return false;
            }
            this.aZ = true;
        }
        if (this.E || g2.X) {
            if (g2.Y != null) {
                Paint paint = this.f();  // 02b L1817: this.f() (isTickComplete 为幻觉名)
                float f8 = 0.0f;
                float f9 = 0.0f;
                if (g2.ad != 0.0f) {
                    f9 += g2.ad * this.J;
                }
                float f10 = this.eo - l2.cw;
                float f11 = this.ep - l2.cx - this.eq;
                float f12 = f5 - l2.cw + this.K;
                float f13 = f4 - f3 - l2.cx + this.L;
                float f14 = (f12 + f10) * 0.5f;
                float f15 = (f13 + f11) * 0.5f;
                float f16 = GameUtils.b(f14, f15, f12, f13);
                float f17 = GameUtils.d(f14, f15, f12, f13);
                y2.k();  // 02b: var4.k()
                f.a(f14 - (float)g2.Y.r, f15 - f16, f14 + (float)g2.Y.r, f15 + f16);  // 02b 字节码 L1833: 静态 RectF f.a(FFFF) (MovementController 类调用为幻觉)
                y2.a(f17 + 90.0f, f14, f15);
                y2.a(g2.Y, f, paint, f8, f9, 0, 0);
                y2.l();  // 02b L1836: var4.l() (readString 为幻觉名)
                if (g2.Z != null) {
                    if (g2.aa) {
                        y2.k();  // 02b: var4.k()
                        y2.a(f17 + 90.0f, f10, f11);
                        y2.clearScreen(g2.Z, f10, f11, paint);
                        y2.l();  // 02b L1842: var4.l()
                    } else {
                        y2.clearScreen(g2.Z, f10, f11, paint);
                    }
                }
                if (g2.ab != null) {
                    if (g2.ac) {
                        y2.k();  // 02b: var4.k()
                        y2.a(f17 + 90.0f, f12, f13);
                        y2.clearScreen(g2.ab, f12, f13, paint);
                        y2.l();  // 02b L1853: var4.l()
                    } else {
                        y2.clearScreen(g2.ab, f12, f13, paint);
                    }
                }
            } else {
                bf.c((int)(60.0f + this.onDraw() * 60.0f));  // 02b L1859: bf.c(int) (UnitGroup 为幻觉名)
                float f18 = f5 - l2.cw + this.K;
                float f19 = f4 - f3 - l2.cx + this.L;
                bf.a(6.0f);  // 02b L1862
                y2.a(this.eo - l2.cw, this.ep - l2.cx - this.eq, f18, f19, bf);
                bf.a(3.0f);  // 02b L1864
                y2.a(this.eo - l2.cw, this.ep - l2.cx - this.eq, f18, f19, bf);
                y2.a(f18, f19, 8.0f, bf);
                y2.a(f18, f19, 5.0f, bf);
            }
        } else if (this.B) {
            float f20 = f5 - l2.cw + this.K;
            float f21 = f4 - f3 - l2.cx + this.L;
            bd.b(this.ar);  // 02b L1716: bd.b (ReplayFrame 为幻觉名)
            be.b(this.ar);  // 02b L1717: be.b (SaveFileHandler 为幻觉名)
            be.c((int)((float)be.f() * 0.5f));  // 02b L1718: be.c/be.f
            y2.a(this.eo - l2.cw, this.ep - l2.cx - this.eq, f20, f21, be);
            y2.a(this.eo - l2.cw, this.ep - l2.cx - this.eq, f20, f21, bd);
            y2.a(f20, f21, 5.0f, bd);
        } else if (this.M) {
            this.N = GameUtils.a(this.N, f2);
            if (this.nodePositions == null) {
                this.nodePositions = new float[20];
                this.N = 0.0f;
            }
            if (this.N == 0.0f) {
                this.N = 4.0f;
                for (n2 = 0; n2 < this.nodePositions.length; ++n2) {
                    this.nodePositions[n2] = GameUtils.c(-10.0f, 10.0f);
                }
            }
            float f22 = this.eo - l2.cw;
            float f23 = this.ep - l2.cx - this.eq;
            float f24 = f5 - l2.cw;
            float f25 = f4 - f3 - l2.cx;
            float f26 = GameUtils.c(f22, f23, f24, f25);
            int n3 = this.nodePositions.length;
            if (f26 < 200.0f) {
                n3 = GameUtils.b(0, n3 - 5);
            } else if (f26 < 100.0f) {
                n3 = GameUtils.b(0, n3 - 10);
            }
            float f27 = f26 / (float)(n3 - 1);
            float f28 = GameUtils.d(f22, f23, f24, f25);
            float f29 = f22;
            float f30 = f23;
            float f31 = GameUtils.cosFast(f28);
            float f32 = GameUtils.sinFast(f28);
            for (int i2 = 0; i2 < n3; ++i2) {
                float f33 = this.nodePositions[i2];
                float f34 = f22 + f31 * (float)i2 * f27;
                float f35 = f23 + f32 * (float)i2 * f27;
                if (i2 != n3 - 1) {
                    f34 -= f32 * f33;
                    f35 += f31 * f33;
                }
                y2.a(f29, f30, f34, f35, bg);
                f29 = f34;
                f30 = f35;
            }
        } else if (this.P != -1) {
            Object object;
            Texture e2 = b;
            int n4 = 20;
            int n5 = 20;
            if (this.R == 1) {
                e2 = d;
                n4 = 60;
                n5 = 60;
            } else if (this.R == 2) {
                e2 = c;
                n4 = 20;
                n5 = 20;
            }
            if (g2.C != null) {
                object = g2.C;
                int n6 = g2.C.p;
                int n7 = g2.C.q;
                int n8 = 0;
                com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((Texture)object, f6, f7, 0.0f, this.aT, this.x, bc, n6, n7, n8);
            } else if (this.Q != -1 && this.z) {
                object = e2;
                int n9 = n4;
                int n10 = n5;
                com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((Texture)object, f6, f7, 0.0f, this.aT, this.x, bc, n9, n10, this.Q);
            }
            if (g2.B != null) {
                e2 = g2.B;
                n4 = g2.B.p;
                n5 = g2.B.q;
            }
            object = this.f();  // 02b L1801: Paint var33 = this.f() (isTickComplete 为幻觉名)
            com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(e2, f6, f7, this.eq, this.aT, this.x, (Paint)object, n4, n5, this.P);
        } else {
            bb.b(this.ar);  // 02b L1804: bb.b (ReplayWriter 为幻觉名)
            if (this.eq > 0.0f && this.z) {
                y2.a(f6, f7, this.x, bc);
            }
            y2.a(f6, f7 - this.eq, this.x, bb);
            if (this.y > 0.0f) {
                bb.c(bb.f() / 3);  // 02b L1811: bb.c(bb.f()/3)
                y2.a(f6, f7 - this.eq, this.y, bb);
            }
        }
        return true;
    }


    public void a(float f2, boolean bl2) {
    }

    public void d(float f2) {  // 02b f.java L1876 空实现
    }

    public UniquePaint a(int n2) {  // 02b f.java L1900: ag a(int) — m/ag=UniquePaint (isVisibleOnScreen 为幻觉名)
        if (this.bj != null) {
            return this.bj;
        }
        if (bk != null && bl == n2) {
            this.bj = bk;
            return this.bj;
        }
        UniquePaint uniquePaint = new UniquePaint();
        uniquePaint.a((android.graphics.ColorFilter)(new LightingColorFilter(n2, 0)));
        uniquePaint.b(n2);
        bk = uniquePaint;
        bl = n2;
        this.bj = uniquePaint;
        return this.bj;
    }

    public boolean f(float f2) {
        return false;
    }

    public Paint f() {  // 02b f.java L1884: Paint f() (isTickComplete 为幻觉名)
        Paint paint;
        if (this.ar != aq) {
            if (com.corrodinggames.rts.gameFramework.GlobalState.at()) {
                paint = this.a(this.ar);  // 02b L1888: this.a(this.ar)
            } else {
                paint = bb;  // 02b L1890: bb (pathPaint 为幻觉名)
                paint.b(this.ar);
            }
        } else {
            paint = ba;  // 02b L1894: ba (defaultPaint 为幻觉名)
        }
        return paint;
    }

    public void a(float f2, float f3, UnitConfig h2) {  // 02b f.java L1917: a(float,float,custom.h) (isVisibleOnScreen 为幻觉名)
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.j == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Projectile: cannot Retarget: source==null");
        } else {
            float f4 = this.eo + GameUtils.cosFast(this.az) * f3;
            float f5 = this.ep + GameUtils.sinFast(this.az) * f3;
            float f6 = f2;
            float f7 = f6 * f6;
            float f8 = -1.0f;
            com.corrodinggames.rts.game.units.UnitType y2 = null;
            UnitInstance l = null;
            if (this.j instanceof com.corrodinggames.rts.game.units.UnitType) {
                y2 = (com.corrodinggames.rts.game.units.UnitType)this.j;
                l = y2.ab();  // 02b L1930: var11 = var10.ab()
            }
            for (UnitInstance am3 : (java.util.Collection<UnitInstance>) (java.util.Collection) l2.cc.a(f4, f5, f6)) {
                if (this.j.player == am3.player) continue;
                boolean bl2 = true;
                if (y2 != null) {
                    bl2 = y2.b(am3, true);
                }
                if (bl2 && this.k >= 0 && y2 != null && this.k < y2.bl() && !y2.a(this.k, am3, true, false)) {
                    bl2 = false;
                }
                if (h2 != null && !com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(h2, am3.de())) {  // 02b L1947: custom.g.a(var3, var13.de())
                    bl2 = false;
                }
                if (!bl2) continue;
                float f9 = GameUtils.a(f4, f5, am3.eo, am3.ep);
                boolean bl3 = false;
                if (f8 == -1.0f || f9 < f8) {
                    bl3 = true;
                }
                if (l == am3) {  // 02b L1958: var11 == var13
                    bl3 = true;
                }
                if (!bl3 || !(f9 < f7)) continue;
                f8 = f9;
                this.l = am3;
            }
        }
    }

    static {
        // 02b f.java L1973-1994 静态块直译 (GameEvent/ReplayFrame/SaveFileHandler/UnitGroup 为幻觉类名)
        bc.b(-16777216);
        bc.c(108);
        bd.a(80, 255, 0, 0);
        bd.a(true);
        bd.a(5.0f);
        be.a(30, 255, 0, 0);
        be.a(true);
        be.a(8.0f);
        bf.a(80, 128, 166, 255);
        bf.a(true);
        bf.a(5.0f);
        bg.a(150, 224, 239, 255);
        bg.a(true);
        bg.a(3.0f);
        bh.a(110, 224, 239, 255);
        bh.a(true);
        bh.a(8.0f);
        bk = null;  // 02b L1992 (sharedPaint 为幻觉名)
        bl = 0;  // 02b L1993 (globalPathIdCounter 为幻觉名)
    }

    public static void c() {  // 02b f.c() L547-552: 加载投射物纹理
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        b = l2.bO.a(com.corrodinggames.rts.R$drawable.projectiles);
        c = l2.bO.a(com.corrodinggames.rts.R$drawable.projectiles2);
        d = l2.bO.a(com.corrodinggames.rts.R$drawable.projectiles_large);
    }


    public static MovementController a(UnitInstance am2, float f2, float f3) {  // 02b f.a(am,float,float) L558-567
        MovementController movementController = new MovementController(false);
        movementController.j = am2;
        movementController.eo = f2;
        movementController.ep = f3;
        movementController.ar = android.graphics.Color.a(255, 100, 30, 30);
        movementController.en = am2.en + 1;
        movementController.em = 4;
        return movementController;
    }

    public void d() {  // 02b f.java L554-556
        this.aS = true;
    }

    public static MovementController a(MovementController f2) {  // 02b f.java L195: a(f) 静态缓存拷贝
        MovementController f3 = bm;
        f3.aD = -1;
        if (f2 == null) {
            f3.am = 1.0f;
            f3.ak = 1.0f;
            f3.al = 1.0f;
            f3.an = 0.0f;
        } else {
            f3.am = f2.am;
            f3.ak = f2.ak;
            f3.al = f2.al;
            f3.an = f2.an;
        }
        return f3;
    }

    public static MovementController a(UnitInstance am2, float f2, float f3, float f4, int n2) {  // 02b f.a(am,float,float,float,int) L569-576
        MovementController movementController = a(am2, f2, f3);
        movementController.eq = f4;
        movementController.k = (short)n2;
        movementController.I = com.corrodinggames.rts.gameFramework.GameUtils.b(am2, 0.0f, 1.0f, am2.bC);
        ++am2.bC;
        return movementController;
    }


    public void a(UnitInstance am2, float f2, float f3, float f4) {  // 02b f.a(am,3f) L213-220
        this.j = am2;
        this.eo = f2;
        this.ep = f3;
        this.eq = f4;
        this.bn = false;
        this.V = false;
    }

}
