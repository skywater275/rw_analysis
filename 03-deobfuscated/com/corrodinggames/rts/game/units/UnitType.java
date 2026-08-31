/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.game.units;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.animation.UnitTrait;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.TagFilter;
import com.corrodinggames.rts.game.ResourceType;
import com.corrodinggames.rts.R;
import com.corrodinggames.rts.gameFramework.ProjectileManager;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.units.actions.ActionFilter;
import com.corrodinggames.rts.game.units.actions.UnitActionBase;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.effects.DrawEffect;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit;
import com.corrodinggames.rts.game.units.projectiles.AbstractSubBuilding;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.Projectile;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitFlag;
import com.corrodinggames.rts.game.units.actions.ActionWrapper;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.ResourceRate;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.DecorType1;
import com.corrodinggames.rts.game.units.UnitAttachment;
import com.corrodinggames.rts.game.units.DecorType2;
import com.corrodinggames.rts.game.units.UnitTransform;
import com.corrodinggames.rts.game.units.PathState;
import com.corrodinggames.rts.game.units.DecorType3;
import com.corrodinggames.rts.game.units.TreeDecoration;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitTurret;
import com.corrodinggames.rts.game.units.PathfindingHelper;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.WeaponTypeEnum;
import com.corrodinggames.rts.game.units.PathResult;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.game.units.commands.ExperimentalUnit;
import com.corrodinggames.rts.game.units.commands.RepairBayUnit;
import com.corrodinggames.rts.game.units.pathfinding.SpatialCallback;
import com.corrodinggames.rts.game.units.weapons.ComponentUpdater;
import com.corrodinggames.rts.game.units.CustomUnitBase;
import com.corrodinggames.rts.game.units.WaypointTarget;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.GamePhase;
import com.corrodinggames.rts.gameFramework.GameObject;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.pathfinding.PathNode;
import com.corrodinggames.rts.gameFramework.utility.ai;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import com.corrodinggames.rts.gameFramework.utility.DequeList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public strictfp abstract class UnitType
extends AbstractUnitBase {
    public boolean aO;
    public static DecorType2 aR;  // 02b y.aR static (javap 闂備礁褰炲ù鍥儊?
    public boolean ax;
    public float eo;
    public float ep;
    public float eq;
    public long eh;
    // v19.112d 闁诲孩绋掗〃鍡涱敊瀹€鈧禒锕傚磼濠箑顦靛畷?(javap 闂備礁褰炲ù鍥儊? y.bR/cN:am, y.P:units.a, y.cc:F, y.ci:Z)
    public com.corrodinggames.rts.game.units.UnitInstance bR;
    public com.corrodinggames.rts.game.units.UnitInstance cN;
    public com.corrodinggames.rts.game.units.UnitFlag P;
    public float cc;
    public boolean ci;
        public float cg;
    public boolean cs;
    public float l;
    public WeaponAction[] g;
    public float aA;
    public com.corrodinggames.rts.game.ai.CombatMain aC;
    public boolean aD;
    public float[] aK;
    public com.corrodinggames.rts.gameFramework.utility.u aM;
    public UnitTransform aW;
    protected static android.graphics.PorterDuffColorFilter aX;  // 02b y.java L139
    protected static android.graphics.PorterDuffColorFilter aY;  // 02b y.java L140
    public int aa;
    public UnitType ad;
    public boolean ae;
    public int ag;
    public short ah;
    public float ai;
    public float heightOffset;  // v19.113n: 02b y.java:90 al 闂?闂佽　鍋撻柛鎾楀懏鐎?濠电偞鎸稿鍫曟偂?y 闂佺顑呯换鎺嶇昂 (婵炴垶鎸搁ˇ顖炈?y+al 闂備礁褰炲ù鍥儊?
    public boolean as;
    public static final UnitTransform[] at = new UnitTransform[0];  // v19.113n: javap static final af[] at (PathNode缂備礁鐬奸崕銈夊汲閻旇櫣纾?
    public com.corrodinggames.rts.gameFramework.pathfinding.PathCostLocator au;  // 02b y.au = k/c (PathCostLocator) 闂備礁褰炲ù鍥儊?
    public android.graphics.Paint bb;
    public android.graphics.Paint bc;
    public android.graphics.Paint bd;
    public android.graphics.PointF be;
    public static boolean L = false;
    protected Texture M;
    protected Texture m;  // 02b 闂佸搫鍠氬﹢纰紃 y.m (CommandCenter 缂備焦绋戦ˇ顔界箾閸ヮ剚鍋? 婵?M 闂佸憡鐗曢幖顐﹀垂?
    protected Texture N;
    private int a;
    private float b;
    private float c;
    private float d;
    private float e;
    private int f = 0;
    public static final WeaponAction[] O = new WeaponAction[0];
    int Q = -9999;
    public UnitInstance R;
    public float S;
    public float T;
    public float U;
    private boolean h;
    private int i = -9999;
    public float V;
    public float W;
    public float X;
    public float Y;
    private boolean j;
    private boolean isPathingActive;  // v19.113i 缂備礁顦抽褎鎱ㄩ埡渚囧晠妞ゆ梻鍘ч悗濠氭⒑閸欍儲绁伴柣? 闂傚倸鐗婇悷锕傤敆?false / moveAllUnitsOnTeam 闂?true
    private float pathTargetY = 3.0f;;  // v19.113i 缂備礁顦抽褎鎱ㄩ埡渚囧晠妞ゆ梻鍘ч悗濠氭⒑閸欍儲绁伴柣? 缂備礁顦抽褎鎱ㄩ埡鍛Е?= 3000.0 (闂佺儵鏅╅崰妤呮偉椤? 濠电偛顦崝宥夊礈?public l 闂?pathTargetX 闂佸搫鐗滄禍婵嬪极?(闂佸憡鑹鹃懟顖炲箖閺囥垺鐒兼い鏃€瀵ч弶绋款渻鐎ｎ亪鍙勬繛?
    private int n;
    private float o;
    private float p;
    private byte q;
    private int r;
    private float s;
    private boolean t;
    public UnitInstance Z;
    public int globalUnitIndex;
    public float ab;
    public int ac;
    public UnitType upgradeTarget;
    public boolean hasWeapons;
    public boolean af;
    public int shieldCapacity;
    public short techLevel;
    public float weaponRangeMax;
    public boolean aj = false;
    public float ak = 0.0f;
    public float metalCost = 0.0f;
    public float am = 0.0f;
    public int an = 0;
    public float ao = 0.0f;
    public boolean ap;
    public float aq = -999.0f;
    public boolean ar = false;
    public boolean factorySearchPending = false;
    public com.corrodinggames.rts.gameFramework.pathfinding.h pathCost;  // 02b y.au = k.c
    public com.corrodinggames.rts.game.units.custom.resources.CustomActionBase dJ;  // 02b am.dJ = custom.d.b
    protected UnitTransform[] av = at;
    protected int aw = 0;
    private boolean u;
    private int v = 0;
    private int w;
    public boolean ay;
    public float az;
    public float field_aA;
    public com.corrodinggames.rts.game.ai.AIUnitGroupBase aB;
    public com.corrodinggames.rts.game.ai.CombatMain linkedBaseZone;
    public boolean pathToZone;
    public static final com.corrodinggames.rts.gameFramework.rendering.UniquePaint aE = new com.corrodinggames.rts.gameFramework.rendering.UniquePaint();
    public static final com.corrodinggames.rts.gameFramework.rendering.UniquePaint aF;
    public static final PointF aG;
    private com.corrodinggames.rts.gameFramework.rendering.UniquePaint x = null;
    private int y;
    private com.corrodinggames.rts.gameFramework.rendering.UniquePaint z = null;
    private int A;
    private static final Paint B;
    private static int C;
    private static final com.corrodinggames.rts.gameFramework.rendering.UniquePaint D;
    private static final com.corrodinggames.rts.gameFramework.rendering.UniquePaint E;
    public static SpatialCallback aH;  // 02b y.aH = f.j (UnitType$1 闂佹悶鍎抽崑鐘绘儍?
    public byte aI = 0;
    public UnitInstance[] aJ;
    public float[] targetDistanceArray;
    public int aL = -9999;
    public static final com.corrodinggames.rts.gameFramework.utility.UnitInstanceList unitTypeRegistry;  // 02b y.aM = utility.u
    public boolean aN;
    static UnitAttachment aP;
    public static DecorType2 aQ;
    public static DecorType3 aS;
    public static DecorType3 aT;
    com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator aU = null;
    static com.corrodinggames.rts.gameFramework.utility.DequeList aV;  // 02b y.aV = utility.m (闂傚倸鐗忛崑鐐垫兜妤ｅ啫鍨?  // 02b y.aV = utility.m (k.k=PathCostCalculator 闂佸憡甯楅〃澶愬Υ?
    public static final UnitTransform reusablePathNode;
    protected static PorterDuffColorFilter buildProgressFilter;
    protected static PorterDuffColorFilter validSelectionFilter;
    protected static PorterDuffColorFilter invalidSelectionFilter;
    protected static PorterDuffColorFilter waypointFilter;
    protected static PorterDuffColorFilter aZ;
    protected static PorterDuffColorFilter ba;
    protected static Paint paintWithValidFilter;
    protected static Paint paintWithInvalidFilter;
    protected static Paint paintWithWaypointFilter;
    static final PointF reusableMuzzleOffset;
    protected static final com.corrodinggames.rts.gameFramework.utility.ai bf;  // 02b y.bf = utility.ai
    protected static final PointF bg;
    protected static final PointF bh;
    protected static final com.corrodinggames.rts.gameFramework.utility.ai bi;  // 02b y.bi = utility.ai
    protected static final PointF bj;
    static final Point bk;
    static final Point bl;
    static final PointF bm;
    static WaypointTarget bn;
    public static final DecorType1 bo;
    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList bp;  // 02b y.bp = utility.m (v19.115m: 闂?SubmarineUnit 闂備焦瀵ч悷銈囩礊?
    static com.corrodinggames.rts.gameFramework.utility.DequeList bq;  // 02b y.bq = utility.m

    public void b(float f2) {
        if (this.az < f2) {
            this.az = f2;
        }
    }

    public Paint getDefaultPaint() {
        boolean bl = this.shouldAntiAlias();
        if (bl) {
            return aF;
        }
        return aE;
    }

    public static void a(UnitType y2, UnitType y3) {
        try {
            com.corrodinggames.rts.gameFramework.network.OutputNetStream as2 = new com.corrodinggames.rts.gameFramework.network.OutputNetStream();
            int n2 = y2.f;
            for (int i2 = 0; i2 < n2; ++i2) {
                y2.g[i2].a(as2);
            }
            InputNetStream k2 = new InputNetStream(as2.d());  // 02b y.L182: new j.k(var2.d())
            y3.f = n2;
            for (int i3 = 0; i3 < n2; ++i3) {
                int n3 = i3;
                y3.m(n3);
                if (n3 >= y3.g.length) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("Too many waypoints:" + i3);
                    n3 = y3.g.length - 1;
                }
                if (y3.g[n3] == null) {
                    y3.g[n3] = new WeaponAction();
                }
                y3.g[n3].a(k2);
                y3.g[n3].c();
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }


    public void serializeToStream(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {  // v19.113k: 02 y.a(j.as) 闂佸憡鍔栭悷銈夊疾?
        as2.a(this.b);
        as2.a(this.c);
        as2.a(this.cL[0].shootCooldown);
        as2.a(this.f);
        int n2 = this.f;
        as2.a(n2);
        for (int i2 = 0; i2 < n2; ++i2) {
            this.g[i2].a(as2);
        }
        as2.a(this.P);
        UnitInstance am2 = this.R;
        if (am2 != null && am2.isDead) {
            am2 = null;
        }
        as2.a(am2);
        as2.a(this.S);
        as2.a(this.U);
        as2.a(this.V);
        as2.d("pathing_active:");
        as2.a(this.isPathingActive);
        as2.a(this.l);
        as2.a(this.pathTargetY);
        as2.a(this.s);
        as2.a(this.upgradeTarget);
        as2.a(this.hasWeapons);
        as2.a(this.af);
        as2.a(this.aj);
        as2.a(this.ak);
        as2.a(this.heightOffset);
        as2.a(this.am);
        as2.a(this.an);
        as2.a(this.ac);
        as2.d("activePathCount:");
        as2.a(this.aw);
        for (int i3 = 0; i3 < this.aw; ++i3) {
            this.av[i3].a(as2);
        }
        as2.a(this.aw);
        as2.a(this.v);
        if (as2.f()) {
            // empty if block
        }
        as2.c(12);
        as2.a(this.o);
        as2.a(this.p);
        as2.a(this.d);
        as2.a(this.e);
        as2.a(this.u);
        as2.a(this.weaponRangeMax);
        as2.a(this.n);
        as2.a(this.W);
        as2.a(this.aq);
        as2.a(this.ar);
        as2.a(this.factorySearchPending);
        as2.a(this.techLevel);
        as2.a(this.ab);
        as2.a(this.w);
        as2.a(this.X);
        as2.a(this.az);
        as2.a(this.field_aA);
        ComponentUpdater.a(this, as2);
        super.a(as2);
    }


    public void deserializeFromStream(InputNetStream k2) throws IOException {  // v19.113k: 02 y.a(j.k) 反序列化 (覆写 UnitInstance.deserializeFromStream 已 throws)
        int n2;
        int n3;
        this.b = k2.readFloat();
        this.c = k2.readFloat();
        this.cL[0].shootCooldown = k2.readFloat();
        this.f = k2.readInt();
        if (this.f > 0) {
            this.checkWaypointIndex(GameUtils.c(this.f - 1, 29));
        }
        int n4 = 30;
        if (k2.b() >= 42) {
            n4 = k2.readInt();
        }
        for (int i2 = 0; i2 < n4; ++i2) {
            int n5 = i2;
            this.checkWaypointIndex(n5);
            if (n5 >= this.g.length) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("Too many waypoints:" + i2);
                n5 = this.g.length - 1;
            }
            if (this.g[n5] == null) {
                this.g[n5] = new WeaponAction();
            }
            this.g[n5].a(k2);
        }
        this.P = (UnitFlag) k2.b(UnitFlag.class);
        if (this.P == UnitFlag.a) {
            if (!this.I()) {
                this.P = UnitFlag.b;
            }
            if (k2.b() < 74) {
                this.P = UnitFlag.b;
            }
        }
        long l2 = k2.n();
        this.S = k2.readFloat();
        this.U = k2.readFloat();
        this.V = k2.readFloat();
        this.isPathingActive = k2.readBoolean();
        this.l = k2.readFloat();
        this.pathTargetY = k2.readFloat();
        this.s = k2.readFloat();
        this.a(k2.p());
        this.hasWeapons = k2.readBoolean();
        this.af = k2.readBoolean();
        this.aj = k2.readBoolean();
        this.ak = k2.readFloat();
        this.heightOffset = k2.readFloat();
        this.am = k2.readFloat();
        this.an = k2.readInt();
        if (k2.b() >= 18) {
            this.ac = k2.readInt();
        }
        if (k2.b() >= 21) {
            n3 = k2.readInt();
            for (n2 = 0; n2 < n3; ++n2) {
                this.ensurePathNodeCapacity(n2);
                if (this.av[n2] == null) {
                    this.av[n2] = new UnitTransform();
                }
                this.av[n2].a(k2);
            }
        } else {
            n3 = 60;
            for (n2 = 0; n2 < 60; ++n2) {
                this.ensurePathNodeCapacity(n2);
                if (this.av[n2] == null) {
                    this.av[n2] = new UnitTransform();
                }
                this.av[n2].a(k2);
            }
        }
        this.aw = k2.readInt();
        this.v = k2.readInt();
        n3 = k2.d();
        if (n3 >= 1) {
            this.o = k2.readFloat();
            this.p = k2.readFloat();
        }
        if (n3 >= 2) {
            this.d = k2.readFloat();
            this.e = k2.readFloat();
        }
        if (n3 >= 3) {
            this.u = k2.readBoolean();
        }
        if (n3 >= 4) {
            this.weaponRangeMax = k2.readFloat();
            this.n = k2.readInt();
        }
        if (n3 >= 5) {
            this.W = k2.readFloat();
        }
        if (n3 >= 6) {
            this.aq = k2.readFloat();
            this.ar = k2.readBoolean();
            this.factorySearchPending = k2.readBoolean();
        }
        if (n3 >= 7) {
            this.techLevel = k2.v();
        }
        if (n3 >= 8) {
            this.ab = k2.readFloat();
        }
        if (n3 >= 9) {
            this.w = k2.readInt();
        }
        if (n3 >= 10) {
            this.X = k2.readFloat();
        }
        if (n3 >= 11) {
            this.az = k2.readFloat();
            this.field_aA = k2.readFloat();
        }
        if (n3 >= 12) {
            ComponentUpdater.a(this, k2);
        }
        super.a(k2);
        if (!this.isDead) {
            this.R = com.corrodinggames.rts.gameFramework.GameObject.a(l2, false);  // 02b y.java L421: w.a(var7,false) (EffectConfig 为幻觉)
            for (n2 = 0; n2 < this.f; ++n2) {
                if (this.g[n2] == null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("readIn: convertUnitIds is null: " + n2 + " waypointsCount:" + this.f);
                    continue;
                }
                this.g[n2].c();
            }
        }
        this.S();
        if (this.isDead) {
            this.ew = true;
        }
    }


    public void b(PlayerState n2) {
        super.b(n2);
        this.S();
    }

    public void loadTextures() {
        this.M = this.d();
        this.N = this.k();
    }

    public boolean aV() {
        return false;
    }

    public boolean bj() {
        return false;
    }

    public UnitInstance ab() {
        if (this.R != null && !this.R.isDead) {
            return this.R;
        }
        WeaponAction au2 = this.ar();
        if (au2 != null && au2.h != null && !au2.h.isDead) {  // 02b au.h = am
            return au2.h;
        }
        return null;
    }

    public abstract Texture d();

    public abstract Texture k();

    public abstract Texture d(int var1);

    public abstract float m();

    public abstract com.corrodinggames.rts.game.units.MovementTypeEnum h();


    public float getSensorRange(int n2) {
        return 0.0f;
    }

    public strictfp float g(int n2) {  // 02b y.g(int)
        return 0.0f;
    }
    public CustomActionBase g(UnitInstance var1) {  // 02b y.java L2169-2171: custom.d.b g(am)
        return var1.by != null ? var1.by : var1.r().B();
    }

    public float getAttackRange(int n2) {
        return 0.0f;
    }

    public Texture getOptionalTexture() {
        return null;
    }

    public Paint a(int n2, ColorFilter colorFilter, boolean bl) {
        int n3;
        Paint paint;
        if (n2 == -1 && colorFilter == null) {
            if (bl) {
                return E;
            }
            return D;
        }
        if (this.cp) {
            if (colorFilter == null) {
                paint = B;
                n3 = C;
                C = n2;
            } else {
                paint = B;
                n3 = -1;
                if (colorFilter == aZ) {
                    paint = paintWithInvalidFilter;
                }
                if (colorFilter == validSelectionFilter) {
                    paint = paintWithValidFilter;
                }
                if (colorFilter == ba) {
                    paint = paintWithWaypointFilter;
                }
            }
        } else if (bl) {
            if (this.z == null) {
                this.z = a(true);
            }
            paint = this.z;
            n3 = this.A;
            this.A = n2;
        } else {
            if (this.x == null) {
                this.x = a(false);
            }
            paint = this.x;
            n3 = this.y;
            this.y = n2;
        }
        if (n3 != n2) {
            paint.b(n2);
        }
        if (paint.h() != colorFilter) {
            paint.a(colorFilter);
        }
        return paint;
    }

    public static com.corrodinggames.rts.gameFramework.rendering.UniquePaint a(boolean bl) {
        com.corrodinggames.rts.gameFramework.rendering.UniquePaint ag2 = new com.corrodinggames.rts.gameFramework.rendering.UniquePaint();
        if (bl) {
            ag2.a(true);
            ag2.d(true);
            ag2.b(true);
        } else {
            ag2.a(false);
            ag2.d(false);
            ag2.b(false);
        }
        return ag2;
    }

    public UnitType(boolean bl) {
        super(bl);
    }

    public final void damageAllTurrets(int n2) {
        int n3 = this.bl();
        for (int i2 = 0; i2 < n3; ++i2) {
            this.cL[i2].a(n2);
        }
    }

    public void a(String string) {
        String string2 = this.r() != null ? this.r().i() : "<NO UNIT TYPE>";
        com.corrodinggames.rts.gameFramework.GlobalState.e("(Unit log:" + string2 + " id:" + this.eh + "): " + string);
    }

    public void logUnitTypeDebug() {
        String string = this.r() != null ? this.r().i() : "<NO UNIT TYPE>";
        com.corrodinggames.rts.gameFramework.GlobalState.e("---- Debug for:" + string + " id:" + this.eh + "---");
    }


    public void a(float f2) {
        super.isVisibleTo(f2);
        if (this.ay) {
            this.ay = false;
        }
        if (this.cl != 0.0f) {
            this.cl = GameUtils.a(this.cl, f2);
        }
        if (!this.isDead && this.bT()) {
            float f3;
            float f4;
            UnitTurret ap2;
            int n2;
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            if (this.az > 0.0f) {
                this.az = GameUtils.a(this.az, f2);
            }
            if (this.field_aA > 0.0f) {
                this.field_aA = GameUtils.a(this.field_aA, f2);
            }
            if (this.bp != null) {
                ComponentUpdater.a(this, f2);
            }
            float f5 = this.eo;
            float f6 = this.ep;
            int n3 = this.bl();
            for (n2 = 0; n2 < n3; ++n2) {
                ap2 = this.cL[n2];
                if (ap2.lockDelay == 0.0f) {
                    f4 = this.getTurretFacingAngle(n2);
                    if (!this.b(n2, f2) || ap2.turretAngle == f4) continue;
                    f3 = GameUtils.c(ap2.turretAngle, f4, 360.0f);
                    if (GameUtils.c(f3) < 0.5f) {
                        ap2.lockDelay = 20.0f;
                        ap2.turretPivotX = 0.0f;
                        continue;
                    }
                    this.a(f2, f4, n2);
                    continue;
                }
                ap2.lockDelay = GameUtils.a(ap2.lockDelay, f2);
            }
            if (!this.bk()) {
                this.k(f2);  // 02b y.k(float)
            }
            for (n2 = 0; n2 < n3; ++n2) {
                ap2 = this.cL[n2];
                if (ap2.shootCooldown == 0.0f) continue;
                ap2.shootCooldown = GameUtils.a(ap2.shootCooldown, f2);
            }
            n2 = this.bi() ? 1 : 0;
            boolean bl = false;
            boolean bl2 = bl = this.cc != 0.0f || this.cd != 0.0f;
            if ((this.cf != 0.0f || bl) && this.I()) {
                f4 = this.cg;
                f3 = this.z();
                if (this.bj()) {
                    f4 = this.ch;
                }
                if (n2 == 0) {
                    float f7 = f3 * this.cf * f2;
                    f5 += GameUtils.cosFast(f4) * f7;
                    f6 += GameUtils.sinFast(f4) * f7 * this.aZ();
                    if (bl) {
                        f5 += this.cc * f2;
                        f6 += this.cd * f2 * this.aZ();
                        float f8 = GameUtils.a(0.0f, 0.0f, this.cc, this.cd);
                        if (f8 > f3 * f3) {
                            this.cc = (float)((double)this.cc - (double)this.cc * 0.05 * (double)f2);
                            this.cd = (float)((double)this.cd - (double)this.cd * 0.05 * (double)f2);
                        }
                        this.cc = GameUtils.a(this.cc, 0.0f, 0.5f * f3 * f2);
                        this.cd = GameUtils.a(this.cd, 0.0f, 0.5f * f3 * f2);
                    }
                } else {
                    float f9;
                    float f10;
                    float f11;
                    float f12;
                    if (this.cf != 0.0f) {
                        f12 = this.getTurretFacingAngle() * 1.41f;
                        f11 = GameUtils.cosFast(f4) * f3 * this.cf;
                        f10 = GameUtils.sinFast(f4) * f3 * this.cf;
                    } else {
                        f12 = this.getBarrelEndPoint() * 1.41f;
                        f11 = 0.0f;
                        f10 = 0.0f;
                    }
                    float f13 = GameUtils.a(this.cc, this.cd, f11, f10);
                    if (f13 > f3 * f3) {
                        this.cc = (float)((double)this.cc - (double)this.cc * 0.05 * (double)f2);
                        this.cd = (float)((double)this.cd - (double)this.cd * 0.05 * (double)f2);
                    }
                    if (f13 < (f9 = f12 * f2) * f9) {
                        this.cc = f11;
                        this.cd = f10;
                    } else {
                        float f14 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.cc, this.cd, f11, f10);
                        this.cc += GameUtils.cosFast(f14) * f9;
                        this.cd += GameUtils.sinFast(f14) * f9;
                    }
                    f5 += this.cc * f2;
                    f6 += this.cd * f2 * this.aZ();
                }
                this.ay = true;
            }
            if (this.bZ != 0.0f || this.ca != 0.0f) {
                this.bZ = GameUtils.b(this.bZ, -9.0f, 9.0f);
                this.ca = GameUtils.b(this.ca, -9.0f, 9.0f);
                f5 += this.bZ;
                f6 += this.ca;
                this.ca = 0.0f;
                this.bZ = 0.0f;
                this.ay = true;
            }
            if (this.ay && this.I() && this.cO == null) {
                this.a(f2, l2, f5, f6);
            }
            if (this.ax) {
                this.ax = false;
                this.c(false);
                this.ay = true;
            }
        }
    }

    private void a(float f2, GlobalState l2, float f3, float f4) {
        int n2;
        int n3;
        boolean bl;
        com.corrodinggames.rts.game.map.MapEngine b2 = l2.bL;
        float f5 = b2.float1;
        float f6 = b2.float2;
        float f7 = this.eo * f5;
        float f8 = this.ep * f6;
        float f9 = f3 * f5;
        float f10 = f4 * f6;
        PointF pointF = null;
        boolean bl2 = false;
        int n4 = GameUtils.f(f7);
        int n5 = GameUtils.f(f8);
        int n6 = GameUtils.f(f9);
        int n7 = GameUtils.f(f10);
        if ((n4 != n6 || n5 != n7) && this.cl == 0.0f && l2.bU.a(this.h(), n6, n7)) {
            if (n4 != n6 && n5 != n7) {
                bl = l2.bU.a(this.h(), n4, n7);
                n3 = l2.bU.a(this.h(), n6, n5) ? 1 : 0;
                if (bl && n3 != 0) {
                    bl2 = true;
                    aG.a(f7, f8);
                    pointF = aG;
                }
                if (pointF == null && bl) {
                    pointF = PathfindingHelper.a(this.h(), f7, f8, f9, f10, n4, n7, false);
                }
                if (pointF == null && n3 != 0) {
                    pointF = PathfindingHelper.a(this.h(), f7, f8, f9, f10, n6, n5, false);
                }
            }
            if (pointF == null) {
                pointF = PathfindingHelper.a(this.h(), f7, f8, f9, f10, n6, n7, false);
            }
            if (pointF == null) {
                bl2 = true;
                aG.a(f7, f8);
                pointF = aG;
            }
        }
        bl = false;
        if (pointF != null) {
            n3 = 0;
            boolean bl8 = l2.bU.a(this.h(), n4, n5);  // 02b PathFinder.a 闁哄鏅滈弻銊ッ?boolean
            if (bl8 && !l2.bU.b(this.h(), n6, n7)) {
                n3 = 1;
            }
            if (n3 == 0) {
                f3 = pointF.a * (float)b2.tilePixelWidth;
                f4 = pointF.b * (float)b2.tilePixelHeight;
                bl = true;
            } else {
                bl2 = false;
            }
        }
        if (bl) {
            this.b += f2;
            this.a = 0;
        } else if (this.b != 0.0f && f2 > 0.0f) {
            ++this.a;
            if (this.a >= 3) {
                this.b = 0.0f;
            }
        }
        if (!bl2) {
            n3 = GameUtils.f(f3 * f5);
            n2 = GameUtils.f(f4 * f6);
            this.eo = f3;
            this.ep = f4;
            if (n4 != n3 || n5 != n2) {
                this.c(true);
            }
        }
    }

    public void b(float f2, float f3) {
        com.corrodinggames.rts.game.map.MapEngine b2 = com.corrodinggames.rts.gameFramework.GlobalState.B().bL;
        float f4 = b2.float1;
        float f5 = b2.float2;
        int n2 = GameUtils.f(this.eo * f4);
        int n3 = GameUtils.f(this.ep * f5);
        int n4 = GameUtils.f(f2 * f4);
        int n5 = GameUtils.f(f3 * f5);
        this.eo = f2;
        this.ep = f3;
        if (n2 != n4 || n3 != n5) {
            this.c(true);
        }
    }

    public static void getBarrelLength(float f2) {
        UnitType y2;
        int n2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.cd.a(com.corrodinggames.rts.gameFramework.GamePhase.j);
        int n3 = l2.by;
        com.corrodinggames.rts.gameFramework.utility.UnitInstanceList u2 = unitTypeRegistry;  // 02b L849: utility/u var3 (UnitRegistry 婵炴垶鎸搁幖顐ｅ緞閻旂儤鍠嗗璺猴攻閸?
        UnitInstance[] amArray = UnitInstance.bE.a();  // 02b u.a();  // 02b am.bE.a()
        int n4 = UnitInstance.bE.size();
        for (n2 = 0; n2 < n4; ++n2) {
            amArray[n2].updateUnitAgeState();
        }
        for (n2 = 0; n2 < n4; ++n2) {
            float f3;
            if (!(amArray[n2] instanceof UnitInstance)) continue;
            y2 = (UnitType) amArray[n2];
            if (!y2.ay && !y2.cb || !y2.I() || y2.aL > n3) continue;
            y2.cb = false;
            y2.ay = true;
            if (y2.cK) {
                f3 = y2.cj + 7.0f;
                y2.aL = y2.aI > 9 ? n3 + 200 + n2 % 50 : n3 + 50 + n2 % 50;
            } else {
                f3 = y2.cj + 5.0f;
                y2.aL = n3 + 250 + n2 % 50;
            }
            y2.aI = 0;
            u2.clear();
            l2.cc.b(y2.eo, y2.ep, f3, u2);
            UnitInstance[] amArray2 = u2.a();  // 02b u.a();  // 02b u.a() 闂佽桨鐒︽竟鍡欏垝?
            int n5 = u2.size();  // 02b u.b 闁荤姳璁查崜婵嬪汲?
            for (int i2 = 0; i2 < n5; ++i2) {
                UnitInstance am2 = amArray2[i2];
                y2.a(am2, f2, true);
            }
            if (y2.aI <= 9 || y2.aL <= n3 - 400) continue;  // 02b g(float) 闁诲孩绋掗〃澶嬩繆椤撱垺鍎?getfield aL
            y2.aL = l2.by + 5 + n2 % 5;
            y2.cb = true;
        }
        l2.cd.b(com.corrodinggames.rts.gameFramework.GamePhase.j);
        l2.cd.a(com.corrodinggames.rts.gameFramework.GamePhase.k);
        for (n2 = 0; n2 < n4; ++n2) {
            int n6;
            if (!(amArray[n2] instanceof UnitInstance)) continue;
            y2 = (UnitType) amArray[n2];
            if (!y2.ay || (n6 = y2.aI) <= 0 || !y2.I()) continue;
            if (!y2.cb) {
                y2.cb = true;
            }
            for (int i3 = 0; i3 < n6; ++i3) {
                UnitInstance am3 = y2.aJ[i3];
                y2.a(am3, f2, false);
            }
        }
        l2.cd.b(com.corrodinggames.rts.gameFramework.GamePhase.k);
    }

    private final void a(UnitInstance am2, float f2, boolean bl) {
        UnitInstance am3 = am2;
        if (am3 == this) {
            return;
        }
        int n2 = this.bU;
        if (n2 == -1 || n2 != am3.bU) {
            return;
        }
        if (this.bQ == am3 || am3.bQ == this) {
            return;
        }
        float f3 = this.eo + this.bZ;
        float f4 = this.ep + this.ca;
        float f5 = am3.eo + am3.bZ;
        float f6 = am3.ep + am3.ca;
        float f7 = GameUtils.a(f3, f4, f5, f6);
        float f8 = this.cj + am3.cj;
        if (bl) {
            int n3;
            int n4;
            UnitInstance[] amArray;
            float f9 = f7;
            if (f7 < f8 * f8) {
                f9 = 0.0f;
            }
            if (am2 instanceof UnitType) {  // 02b L942: var1 instanceof y
                UnitType y2 = (UnitType) am2;
                int n5 = y2.aI;
                for (n4 = 0; n4 < n5; ++n4) {
                    if (y2.aJ[n4] == this) {
                        return;
                    }
                }
            }
            if (this.aJ == null) {
                this.aJ = new UnitInstance[10];
                this.targetDistanceArray = new float[10];
            }
            amArray = this.aJ;
            float[] fArray = this.targetDistanceArray;
            n4 = -1;
            for (n3 = 0; n3 < this.aI; ++n3) {
                if (!(f9 < fArray[n3])) continue;
                n4 = n3;
                break;
            }
            if (n4 == -1) {
                if (this.aI < amArray.length) {
                    n4 = this.aI;
                } else {
                    return;
                }
            }
            if (this.aI < amArray.length) {
                this.aI = (byte)(this.aI + 1);
            }
            for (n3 = this.aI - 1; n3 > n4; --n3) {
                amArray[n3] = amArray[n3 - 1];
            }
            amArray[n4] = am3;
            fArray[n4] = f9;
            return;
        }
        if (f7 < f8 * f8 && !am3.isVisibleTo(this, f2) && !this.a(am3, f2)) {
            float f10;
            float f11;
            float f12;
            int n6;
            int n7;
            float f13 = com.corrodinggames.rts.gameFramework.GameUtils.a(f3, f4, f5, f6);
            float f14 = (float)Math.sqrt(f7);
            float f15 = f8 - f14 + 0.001f;
            if (f15 <= 0.0f) {
                return;
            }
            int n8 = this.s(am3);  // 02b y.s(am)
            int n9 = n7 = n8 > (n6 = am3.s(this)) ? n8 : n6;
            if (n7 != 0) {
                f12 = f15 / (float)n7 * f2;
                if (f12 > f15) {
                    f12 = f15;
                }
                f15 = f12;
            }
            if ((f15 *= 0.95f) > 1.0f) {
                f15 *= 0.7f;
            }
            if (f15 > 3.0f) {
                f15 = 3.0f + (f15 - 3.0f) * 0.7f;
            }
            if (f15 > 6.0f) {
                f15 = 6.0f + (f15 - 6.0f) * 0.7f;
            }
            if (f15 > 10.0f) {
                f15 = 10.0f + (f15 - 10.0f) * 0.7f;
            }
            f12 = 0.0f;
            float f16 = this.bN();
            float f17 = am3.getBuildDuration();
            UnitType y2 = null;
            if (am3 instanceof UnitInstance) {
                y2 = (UnitType) am3;  // 02b (y)var
            }
            if (this.player == am3.player) {
                boolean bl2 = false;
                f11 = 1.7f;
                if (y2 != null) {
                    UnitType y3 = y2;
                    if (this.W > 200.0f || y3.W > 200.0f) {
                        f11 = 5.0f;
                    }
                    if (this.upgradeTarget == y3) {
                        f17 *= f11;
                        bl2 = true;
                    }
                    if (y3.upgradeTarget == this) {
                        f16 *= f11;
                        bl2 = true;
                    }
                    if (!bl2) {
                        if (this.hasWeapons && y3.upgradeTarget != null) {
                            f16 *= f11;
                        } else if (y3.ae && this.upgradeTarget != null) {
                            f17 *= f11;
                        } else if (this.c == 0.0f && y3.c != 0.0f) {
                            f16 *= f11;
                        } else if (y3.c == 0.0f && this.c != 0.0f) {
                            f17 *= f11;
                        }
                    }
                }
            }
            if (am3 instanceof CustomUnitBase) {
                f12 = f16 / (f16 + f17);
            }
            float f18 = 1.0f - f12;
            f11 = GameUtils.cosFast(f13);
            float f19 = GameUtils.sinFast(f13);
            if (am3 instanceof CustomUnitBase) {
                f10 = f15 * f12;
                am3.bZ += f11 * f10;
                am3.ca += f19 * f10;
            }
            f10 = f15 * f18;
            this.bZ -= f11 * f10;
            this.ca -= f19 * f10;
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            int n10 = l2.bx;
            this.Z = am3;
            this.globalUnitIndex = n10;
            if (y2 != null) {
                UnitType y4 = y2;
                y4.Z = this;
                y4.aa = n10;
                if (this.ac != 0 && this.ac == y4.ac) {
                    WeaponAction au2;
                    if (this.getFirstWaypoint() == null && (au2 = y4.getFirstWaypoint()) != null && (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.a || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.h)) {
                        y4.completeWaypoint();
                    }
                    if (y4.getFirstWaypoint() == null && (au2 = this.getFirstWaypoint()) != null && (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.a || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.h)) {
                        this.completeWaypoint();
                    }
                }
            }
        }
    }


    public int V() {
        return 1;
    }

    public void a(int n2) {
    }

    protected void removeFromEngine() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.player == l2.bs) {
            l2.bS.i.b(this);
        }
    }

    public float b(float f2, float f3, float f4) {
        if (this.getMuzzlePoint()) {
            if (this.isFactoryBuilding()) {
                return 0.0f;
            }
            float f5 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eo, this.ep, f3, f4);
            return this.c(f2, f5);
        }
        if (this.bl() < 1) {
            return 0.0f;
        }
        int n2 = this.aT();
        if (n2 == -1) {
            n2 = 0;
        }
        PointF pointF = this.getTurretWorldPos(n2);
        float f6 = com.corrodinggames.rts.gameFramework.GameUtils.a(pointF.a, pointF.b, f3, f4);
        UnitTurret ap2 = this.cL[n2];
        ap2.a(70);
        return this.a(f2, f6, n2);
    }

    public float c(float f2, float f3) {
        boolean bl = false;
        boolean bl2 = false;
        if (this.ci && this.bb()) {
            bl = true;
            bl2 = true;
        }
        return this.a(f2, f3, bl, bl2);
    }


    public void h(float f2) {
        float f3 = GameUtils.c(this.cg, f2, 360.0f);
        if ((double)GameUtils.c(f3) > 0.01) {
            this.getAttackRange(f3);
        }
    }

    public float a(float f2, float f3, boolean bl, boolean bl2) {
        this.ch = f3;
        if (GameUtils.c(this.cg - f3) < 0.01f) {
            if (bl && this.ci) {
                this.damageAllTurrets(25);
                this.ci = false;
            }
            return 0.0f;
        }
        float f4 = GameUtils.c(this.cg, f3, 360.0f);
        if (bl) {
            if (bl2 && GameUtils.c(f4) > 100.0f) {
                f4 = GameUtils.c(this.cg, f3 + 180.0f, 360.0f);
                if (!this.ci) {
                    this.damageAllTurrets(25);
                    this.ci = true;
                }
            } else if (this.ci) {
                this.damageAllTurrets(25);
                this.ci = false;
            }
        }
        if (GameUtils.c(f4) < 0.01f) {
            return 0.0f;
        }
        if (this.az <= 0.0f) {
            float f5 = this.getTurretBaseAngle();
            if (f5 <= 0.0f) {
                float f6 = f4 > 0.0f ? 1.0f : -1.0f;
                float f7 = f6 * this.A() * f2;
                if (GameUtils.c(f7) > GameUtils.c(f4)) {
                    f7 = f4;
                }
                this.getAttackRange(f7);
            } else {
                float f8 = f4 > 0.0f ? 1.0f : -1.0f;
                float f9 = GameUtils.c(this.ce) / f5;
                this.ce = GameUtils.c(f4) < f9 ? GameUtils.a(this.ce, f8 * f5, f5 * f2) : GameUtils.a(this.ce, f8 * this.A(), f5 * f2);
                float f10 = this.ce * f2;
                if (GameUtils.c(f10) > GameUtils.c(f4)) {
                    this.ce = 0.0f;
                    f10 = f4;
                }
                this.getAttackRange(f10);
            }
        }
        return f4;
    }

    public void getAttackRange(float f2) {
        this.cg += f2;
        if (this.cg > 180.0f) {
            this.cg -= 360.0f;
        }
        if (this.cg < -180.0f) {
            this.cg += 360.0f;
        }
        if (this.bm()) {
            int n2 = this.bl();
            for (int i2 = 0; i2 < n2; ++i2) {
                UnitTurret ap2 = this.cL[i2];
                ap2.turretAngle += f2;
                if (ap2.turretAngle > 180.0f) {
                    ap2.turretAngle -= 360.0f;
                }
                if (!(ap2.turretAngle < -180.0f)) continue;
                ap2.turretAngle += 360.0f;
            }
        }
    }

    public void damageAllTurrets(float f2) {
        int n2 = this.bl();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitTurret ap2 = this.cL[i2];
            ap2.turretAngle = f2 + this.getTurretBaseAngle(i2);
        }
    }

    public void a(int n2, float f2) {
        UnitTurret ap2 = this.cL[n2];
        ap2.turretAngle += f2;
    }

    public float a(float f2, float f3, int n2) {
        UnitTurret ap2 = this.cL[n2];
        float f4 = ap2.turretAngle;
        float f5 = GameUtils.c(f4, f3, 360.0f);
        if (f5 == 0.0f) {
            return f5;
        }
        float f6 = this.getTurretAngularAccel(n2);
        if (f6 <= 0.0f) {
            float f7 = GameUtils.c(ap2.turretAngle, f3, this.c(n2) * f2);
            this.a(n2, f7);
            f5 -= f7;
        } else {
            float f8 = this.getTurretAngularDecel(n2);
            float f9 = f5 > 0.0f ? 1.0f : -1.0f;
            float f10 = GameUtils.c(ap2.turretPivotX) / f8;
            boolean bl = f5 > 0.0f == ap2.turretPivotX > 0.0f;
            ap2.turretPivotX = GameUtils.c(f5) < f10 && bl ? GameUtils.a(ap2.turretPivotX, f9 * f8, f8 * f2) : GameUtils.a(ap2.turretPivotX, f9 * this.c(n2), f6 * f2);
            float f11 = ap2.turretPivotX * f2;
            if (GameUtils.c(f11) > GameUtils.c(f5)) {
                ap2.turretPivotX = 0.0f;
                f11 = f5;
            }
            this.a(n2, f11);
            f5 -= f11;
        }
        return f5;
    }

    public UnitInstance getBuilderTarget() {
        WeaponAction au2;
        if (this.h && (au2 = this.getFirstWaypoint()) != null && (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.d || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.g) && au2.h != null && !au2.h.isDead) {
            return au2.h;
        }
        return null;
    }

    public boolean isReclaimWaypoint() {
        WeaponAction au2 = this.getFirstWaypoint();
        return au2 != null && au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.g;
    }

    private void a(float f2, WeaponAction au2, UnitAttachment ad2) {
        if (au2.c == null) {
            this.completeWaypoint();
            au2 = null;
        }
        if (au2 != null) {
            boolean bl = true;
            if (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.p) {
                // empty if block
            }
            if (bl) {
                GameAction s2 = this.a(au2.c);
                this.U();
                if (s2 == null) {
                    this.a("Failed to find action:" + au2.c.a());
                } else {
                    PointF pointF = new PointF(au2.e, au2.f);
                    this.a(s2, false, pointF, au2.h);
                }
                this.completeWaypoint();
                au2 = null;
            }
        }
    }

    private void b(float f2, WeaponAction au2, UnitAttachment ad2) {
        UnitInstance am2 = au2.i();
        if (am2 != null) {
            this.R = am2;
            if (this.T > 5.0f) {
                this.T = 5.0f;
            }
        }
        this.completeWaypoint();
        au2 = null;
    }

    private void c(float f2, WeaponAction au2, UnitAttachment ad2) {
        float f3 = au2.g();
        float f4 = au2.h();
        float f5 = GameUtils.a(this.eo, this.ep, f3, f4);
        boolean bl = au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.k || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.n;
        boolean bl2 = au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.k;
        UnitInstance am2 = au2.h;
        if (bl) {
            if (am2 == null || am2.isDead) {
                this.completeWaypoint();
                au2 = null;
            }
            if (au2 != null && am2 != null && !am2.isTargetableForAI() && this.player.c(am2.player)) {
                this.completeWaypoint();
                au2 = null;
            }
        }
        if (au2 != null) {
            WeaponAction au3;
            UnitType y2;
            UnitInstance am3;
            WeaponAction au4;
            boolean bl3 = false;
            float f6 = this.cj;
            if (bl) {
                f6 += am2.cj;
            }
            f6 = au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.n ? (this.cK ? (f6 += 30.0f) : (f6 += 50.0f)) : (this.cK ? (f6 += 80.0f) : (f6 += 100.0f));
            if (f5 > f6 * f6) {
                this.isPathingActive = true;
                this.l = f3;
                this.pathTargetY = f4;
                this.n = 2;
                if (this.s > 90.0f) {
                    this.s = 90.0f;
                }
                this.r = 18;
                if (this.upgradeTarget != null && !this.upgradeTarget.bT()) {
                    ad2.rotationAngle = false;
                }
            } else {
                this.w = 0;
            }
            ad2.rotationAngle = false;
            if (!bl3 && this.R != null && !this.R.isDead) {
                boolean bl4 = false;
                if (this.b(this.R, false)) {
                    bl4 = true;
                }
                if (bl4) {
                    float f7 = GameUtils.a(this.eo, this.ep, this.R.eo, this.R.ep);
                    float f8 = this.getAttackRangeIncludingTarget(this.R);
                    boolean bl5 = false;
                    boolean bl6 = false;
                    if (f7 < f8 * f8) {
                        bl6 = true;
                    }
                    if (bl6 && !this.aa()) {  // 02b y.L1446: !this.aa()
                        bl6 = false;
                    }
                    if (f5 < 22500.0f) {
                        this.w = 0;
                    }
                    if (!bl6 && (this.w == 1 || f5 > 122500.0f)) {
                        bl5 = true;
                        this.w = 1;
                    }
                    if (f5 > 302500.0f || this.w == 1 && f5 > 202500.0f) {
                        bl5 = true;
                        this.w = 1;
                    }
                    if (!bl5) {
                        bl3 = true;
                        this.w = 0;
                        if (bl6) {
                            this.isPathingActive = false;
                        } else {
                            if (this.s > 90.0f) {
                                this.s = 90.0f;
                            }
                            this.isPathingActive = true;
                            this.l = this.R.eo;
                            this.pathTargetY = this.R.ep;
                            this.n = 0;
                            this.j = true;
                        }
                    }
                }
            }
            if (bl2 && !bl3) {
                UnitInstance am4 = am2.q(2.0f);
                if (am4 != null && !this.b(am4, true)) {
                    am4 = null;
                }
                if (am4 == null && this.w != 1 && (am4 = this.q(2.0f)) != null && !this.b(am4, true)) {  // 02b y.q(float)
                    am4 = null;
                }
                if (am4 != null) {
                    bl3 = true;
                    if (this.s > 90.0f) {
                        this.s = 90.0f;
                    }
                    this.isPathingActive = true;
                    this.l = am4.eo;
                    this.pathTargetY = am4.ep;
                    this.n = 0;
                    this.j = true;
                }
            }
            if (bl2 && !bl3 && this.a(am2) && (am2.hp < am2.maxHp || am2.cm < 1.0f) && this.a(am2) && (au4 = this.insertWaypointFront()) != null) {
                au4.b(am2);
                au4.m = true;
                bl3 = true;
                if (this.s > 20.0f) {
                    this.s = 20.0f;
                }
            }
            if (bl2 && !bl3 && this.ak() && am2 instanceof UnitInstance && (am3 = (y2 = (UnitType) am2).X()) != null && this.a(am3) && (au3 = this.ar()) != null) {  // 02b (y)var
                au3.b(am3);
                au3.m = true;
                bl3 = true;
                if (this.s > 20.0f) {
                    this.s = 20.0f;
                }
            }
        }
    }

    private void d(float f2, WeaponAction au2, UnitAttachment ad2) {
        float f3 = au2.g();
        float f4 = au2.h();
        float f5 = GameUtils.a(this.eo, this.ep, f3, f4);
        if (au2.h == null || au2.h.isDead) {
            this.completeWaypoint();
            au2 = null;
        }
        if (au2 != null) {
            float f6;
            boolean bl = false;
            if (au2.h.isFactoryBuilding()) {
                if (f5 < 961.0f) {
                    this.Y += f2;
                }
                if (this.Y > 240.0f) {
                    bl = true;
                }
                f6 = 21.0f;
                if (au2.h.getRenderBounds().a()) {
                    f6 = 11.0f;
                }
                if (this.b > 0.0f) {
                    f6 = au2.h.cj + this.cj + 31.0f;
                }
                if (f5 < f6 * f6) {
                    bl = true;
                }
            } else {
                f6 = au2.h.cj + this.cj + 5.0f;
                if (f5 < f6 * f6) {
                    bl = true;
                }
            }
            if (!bl) {
                this.isPathingActive = true;
                this.l = f3;
                this.pathTargetY = f4;
                this.n = 0;
                if (au2.h.isFactoryBuilding()) {
                    Rect rect = au2.h.getRenderBounds();
                    int n2 = GameUtils.c(rect.c() / 2, rect.b() / 2);
                    this.n = n2 + 1;
                }
                if (this.s > 90.0f) {
                    this.s = 90.0f;
                }
                this.r = 18;
                if (f5 < 48400.0f) {
                    ad2.rotationAngle = false;
                    if (this.s > 0.0f && this.aE() == null) {
                        this.j = true;
                    }
                }
                if (this.upgradeTarget != null && !this.upgradeTarget.bT()) {
                    ad2.rotationAngle = false;
                }
            }
            if (bl) {
                UnitInstance am2 = au2.h;
                this.a(com.corrodinggames.rts.game.units.custom.af.i, am2);
                this.completeWaypoint();
            }
        }
    }

    private void getTurretRange(float f2, WeaponAction au2, UnitAttachment ad2) {
        float f3 = au2.g();
        float f4 = au2.h();
        float f5 = GameUtils.a(this.eo, this.ep, f3, f4);
        if (au2.h == null || au2.h.isDead || !au2.h.isAlive()) {
            this.completeWaypoint();
            au2 = null;
        }
        if (au2 != null && !this.d(au2.h, false)) {
            this.completeWaypoint();
        }
        if (au2 != null) {
            this.bQ = au2.h;
            float f6 = this.cj;  // 02b y.L1396: float var11 = this.cj
            if (f5 > f6 * f6) {
                this.isPathingActive = true;
                this.l = f3;
                this.pathTargetY = f4;
                if (this.s > 90.0f) {
                    this.s = 90.0f;
                }
                this.r = 18;
                if (f5 < 72900.0f) {
                    ad2.rotationAngle = false;
                    if (this.s > 0.0f && this.aU == null) {
                        this.j = true;
                    }
                }
                if (this.upgradeTarget != null && !this.upgradeTarget.bT()) {
                    ad2.rotationAngle = false;
                }
            } else {
                this.e(au2.h, false);  // 02b am.e(am,boolean)
                this.completeWaypoint();
            }
        }
    }

    private void a(float f2, WeaponAction au2, UnitAttachment ad2, boolean bl) {
        Object object;
        float f3 = au2.g();
        float f4 = au2.h();
        float f5 = GameUtils.a(this.eo, this.ep, f3, f4);
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!this.isUsable()) {
            boolean bl2 = false;
            object = this.dn();
            if (object != null && ((UnitTrait)object).H) {
                this.bx();
                bl2 = true;
            }
            if (!bl2) {
                this.clearLinkedZoneAndWaypoint();
                au2 = null;
            }
        }
        float f6 = 7.0f;
        if (f5 < 1681.0f) {
            this.Y += f2;
        }
        if (this.Y > 240.0f) {
            f6 = 16.0f;
        }
        if (this.Y > 340.0f) {
            f6 = 36.0f;
        }
        if (au2 != null && au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.j) {
            if (this.av() != 1) {
                f6 = 20.0f;
                float f7 = 30.0f;
                if (!bl || this.globalUnitIndex == l2.bx || this.globalUnitIndex == l2.bx - 1) {
                    f7 = 70.0f;
                }
                if (f5 < f7 * f7) {
                    this.d(au2);
                    this.clearLinkedZoneAndWaypoint();
                    au2 = null;
                }
            } else {
                f6 = 30.0f;
                if (!bl || this.globalUnitIndex == l2.bx || this.globalUnitIndex == l2.bx - 1) {
                    f6 = 80.0f;
                }
            }
        }
        if (au2 != null) {
            if (f5 < f6 * f6) {
                if (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.j) {
                    if (this.av() == 1) {
                        // empty if block
                    }
                } else if (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.h) {
                    boolean bl3 = false;
                    if (this.R != null && !this.R.isDead && this.a(this.R, false)) {
                        bl3 = true;
                    }
                    if (!bl3) {
                        this.clearLinkedZoneAndWaypoint();
                        au2 = null;
                    }
                } else {
                    this.clearLinkedZoneAndWaypoint();
                    au2 = null;
                }
            } else {
                this.isPathingActive = true;
                this.l = f3;
                this.pathTargetY = f4;
                this.n = 0;
                if (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.j) {
                    this.t = true;
                    this.aB();
                }
            }
        }
        if (au2 != null) {
            if (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.h || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.j) {
                if (this.R != null && !this.R.isDead && this.a(this.R, false)) {
                    this.a(f2, this.R, ad2, true);
                }
                if (this.upgradeTarget != null && this.upgradeTarget.R != null) {
                    ad2.rotationAngle = false;
                }
            }
            if (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.j) {
                if (this.R == null && (object = this.q(3.0f)) != null && this.b((UnitInstance) object, true)) {  // 02b y.q(float)
                    if (this.s > 90.0f) {
                        this.s = 90.0f;
                    }
                    this.isPathingActive = true;
                    this.l = ((UnitInstance) object).eo;
                    this.pathTargetY = ((UnitInstance) object).ep;
                    this.n = 0;
                    this.j = true;
                }
                if (this.ak() && (long)(l2.bx % 10) == this.eh % 10L && (object = com.corrodinggames.rts.game.units.commands.CommandQueue.a(this, f2, 150.0f, true)) != null) {
                    ((WeaponAction) object).m = false;
                    ((WeaponAction) object).k = 200.0f;
                    this.isPathingActive = false;
                    this.resetPathData();
                }
            }
        }
    }

    private void getReloadProgressRate(float f2, WeaponAction au2, UnitAttachment ad2) {
        float f3 = au2.g();
        float f4 = au2.h();
        float f5 = GameUtils.a(this.eo, this.ep, f3, f4);
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitTypeHandle as2 = au2.b;  // 02b au.b = as
        if (as2 == null) {
            this.a("activeBuildingType==null, removing waypoint");
            this.completeWaypoint();
            au2 = null;
        }
        if (au2 != null) {
            boolean bl;
            float f6 = this.f(as2);  // 02b am.f(as)
            int n2 = 30;
            boolean bl2 = false;
            if (f6 <= 30.0f) {
                n2 = 9;
            }
            if (f6 <= 25.0f && this.eq > 4.0f) {
                bl2 = true;
            }
            if (this.upgradeTarget != null) {
                WeaponAction au3 = this.upgradeTarget.getFirstWaypoint();
                if (au3 == null || au3.a != com.corrodinggames.rts.game.units.WeaponTypeEnum.c) {
                    ad2.rotationAngle = false;
                }
                if (au3 != null && !au2.b(au3)) {
                    ad2.rotationAngle = false;
                }
            }
            boolean bl3 = false;
            if (!PathfindingUtils.a(this.Q, 200)) {
                bl3 = true;
            }
            if (f6 > 800000.0f) {
                bl = true;
            } else {
                boolean bl4 = bl = f5 <= f6 * f6;
            }
            if (!bl || bl2) {
                if (!this.isUsable()) {
                    this.completeWaypoint();
                    au2 = null;
                } else {
                    this.isPathingActive = true;
                    this.l = f3;
                    this.pathTargetY = f4;
                    if (f6 > 58.0f) {
                        this.n = (int)((f6 - 41.0f) / ((float)l2.bL.tilePixelWidth * 1.414f));
                    }
                    if (this.s > 90.0f) {
                        this.s = 90.0f;
                    }
                    if (this.q > 3) {
                        this.completeWaypoint();
                        au2 = null;
                        return;
                    }
                }
            } else if (!(bl3 || this.canFaceTarget() && GameUtils.c(this.b(f2, f3, f4)) > 30.0f)) {
                WaypointTarget z2 = this.a(au2, au2.b, au2.d, au2.e, au2.f);
                UnitInstance am2 = null;
                if (z2.a != null) {
                    am2 = z2.a;
                } else if (z2.b != null) {
                    am2 = z2.b;
                }
                if (am2 != null) {
                    z2.d.a((UnitInstance) this, am2);
                    if (this.a(am2)) {
                        if (this.b(am2) > 10000.0f) {
                            am2.r(1.0f);
                            this.clearLinkedZoneAndWaypoint();
                        } else {
                            au2.e();
                            au2.a = com.corrodinggames.rts.game.units.WeaponTypeEnum.d;
                            au2.h = am2;
                            this.resetPathData();
                        }
                    } else {
                        this.completeWaypoint();
                    }
                    this.Q = -9999;
                } else {
                    if (au2.b == null) {
                        com.corrodinggames.rts.gameFramework.GlobalState.e("active.build==null");
                    }
                    if (!z2.c) {
                        this.completeWaypoint();
                    }
                }
            }
        }
    }

    private void a(float f2, UnitInstance am2, UnitAttachment ad2, boolean bl) {
        float f3;
        PathResult b2 = this.be();
        float f4 = am2.eo;
        float f5 = am2.ep;
        float f6 = GameUtils.a(this.eo, this.ep, f4, f5);
        if (this.upgradeTarget != null) {
            if (f6 < 490000.0f) {
                if (f6 < 48400.0f) {
                    ad2.rotationAngle = false;
                }
                if ((f3 = GameUtils.a(this.upgradeTarget.eo, this.upgradeTarget.ep, f4, f5)) < 48400.0f) {
                    ad2.rotationAngle = false;
                }
                if (f3 < 270400.0f && this.aV()) {
                    ad2.rotationAngle = false;
                }
            }
            if (this.upgradeTarget.R == am2) {
                ad2.rotationAngle = false;
            }
            this.weaponRangeMax = ad2.rotationAngle ? 0.0f : (this.weaponRangeMax += f2);
        } else {
            this.weaponRangeMax = 500.0f;
        }
        f3 = this.getAttackRangeIncludingTarget(am2);
        boolean bl2 = true;
        if (f6 < f3 * f3) {
            if (this.R != am2) {
                if (PathfindingHelper.a(this, am2)) {
                    this.R = am2;
                    this.S = 10.0f;
                    this.M(-1);
                }
            } else {
                this.S = 10.0f;
            }
            float f7 = f3;
            if (!this.getMuzzlePoint()) {
                f7 -= 1.0f;
                if (this.aV()) {
                    f7 -= 2.0f;
                }
                if (this.getTurretRange(0) > 5.0f) {
                    f7 -= 3.0f;
                }
            }
            if (f6 < f7 * f7 && this.be() != PathResult.d) {
                if (am2 == null) {
                    bl2 = false;
                } else if (this.getAttackRange(am2)) {
                    bl2 = false;
                    if (bl) {
                        this.isPathingActive = false;
                    }
                } else if (!this.damageAllTurrets(am2)) {
                    bl2 = false;
                }
            }
        }
        if (bl2) {
            this.isPathingActive = true;
            this.l = f4;
            this.pathTargetY = f5;
            this.n = 0;
            if (b2 == PathResult.d) {
                this.a(f6, f4, f5);
            }
            this.n = this.getApproachGridCells(am2);
            if (this.s > 90.0f) {
                this.s = 90.0f;
            }
            if (f6 < 810000.0f) {
                if (this.checkIsLargeUnit() || this.aV()) {
                    this.j = true;
                }
                if (!ad2.rotationAngle && this.weaponRangeMax < 120.0f) {
                    this.s = 0.1f;
                    this.j = true;
                }
            }
        }
    }

    private void getBarrelLength(float f2, WeaponAction au2, UnitAttachment ad2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        PathResult b2 = this.be();
        if (b2 == PathResult.d) {
            if (au2 != null && (au2.h == null || au2.h.isDead || au2.h.player == this.player) && !this.factorySearchPending) {
                if (this.R != null && this.R.isDead) {
                    this.R = null;
                }
                float f3 = this.b(true) + 200.0f;
                this.a(l2, f2, f3);
                if (this.R != null) {
                    au2.h = this.R;
                    this.aB();
                    this.resetPathData();
                } else {
                    this.factorySearchPending = true;
                    this.ar = true;
                }
            }
            if (au2 != null && (au2.h == null || au2.h.isDead || au2.h.player == this.player)) {
                if (au2.h == null) {
                    this.completeWaypoint();
                    au2 = null;
                } else if (!this.ar) {
                    this.completeWaypoint();
                    au2 = null;
                }
            }
        } else if (au2.h == null || au2.h.isDead || au2.h.player == this.player) {
            boolean bl = true;
            if (this.av() > 1) {
                bl = false;
            }
            au2.h = null;
            if (bl) {
                if (this.R != null && this.R.isDead) {
                    this.R = null;
                }
                float f4 = this.b(true);
                this.a(l2, f2, f4);
                if (this.R != null) {
                    au2.h = this.R;
                    this.aB();
                    this.resetPathData();
                }
            }
            if (au2.h == null) {
                this.completeWaypoint();
                au2 = null;
            }
        }
        if (au2 != null && au2.h != null && !au2.h.isDead && !au2.h.isTargetableForAI() && this.player.c(au2.h.player) && !PathfindingHelper.b(this, au2.h)) {
            this.completeWaypoint();
            au2 = null;
            return;
        }
        if (au2 != null && !this.isUsable() && !this.l()) {  // 02b am.l() 闂佺娉涢埀顒傚枙閺?
            this.completeWaypoint();
            au2 = null;
        }
        if (au2 != null) {
            this.a(f2, au2.h, ad2, false);
        }
    }

    private void getSensorRange(float f2, WeaponAction au2, UnitAttachment ad2) {
        float f3 = au2.g();
        float f4 = au2.h();
        float f5 = GameUtils.a(this.eo, this.ep, f3, f4);
        if (au2.h == null || au2.h.isDead) {
            this.completeWaypoint();
            au2 = null;
        }
        if (au2 != null && !au2.h.d(this, false)) {
            this.completeWaypoint();
        }
        if (au2 != null) {
            float f6;
            UnitInstance am2;
            this.bQ = am2 = au2.h;
            boolean bl = false;
            if (am2.isFactoryBuilding()) {
                f6 = am2.isIdle2();
                float f7 = f6 + 10.0f;
                if (f5 < f7 * f7) {
                    this.Y += f2;
                }
                if (this.Y > 240.0f) {
                    bl = true;
                }
                float f8 = 21.0f;
                if (am2.getRenderBounds().a()) {
                    f8 = 11.0f;
                }
                if (this.b > 0.0f) {
                    f8 = am2.cj + 31.0f;
                }
                if (f5 < f8 * f8) {
                    bl = true;
                }
            } else {
                f6 = am2.isIdle2();
                if (f5 < f6 * f6) {
                    bl = true;
                }
            }
            if (!bl) {
                this.isPathingActive = true;
                this.l = f3;
                this.pathTargetY = f4;
                if (this.s > 90.0f) {
                    this.s = 90.0f;
                }
                this.r = 18;
                if (f5 < 48400.0f) {
                    ad2.rotationAngle = false;
                    if (this.s > 0.0f && this.aU == null) {
                        this.j = true;
                    }
                }
                if (this.upgradeTarget != null && !this.upgradeTarget.bT()) {
                    ad2.rotationAngle = false;
                }
            }
            if (bl) {
                UnitInstance am3 = au2.h;
                am3.e(this, false);
                this.completeWaypoint();
            }
        }
    }

    public float a_(UnitInstance am2) {
        float f2 = am2.r().D();
        if (am2.V() == 2) {
            f2 *= 0.5f;
        }
        if (am2.V() == 3) {
            f2 *= 0.25f;
        }
        return f2 *= this.b(am2);
    }

    public float getReloadProgressRate(UnitInstance am2) {
        float f2 = 5.1f;
        return 0.001f * f2;
    }

    public CustomActionBase getBarrelLength(UnitInstance am2) {
        if (am2.by != null) {
            return am2.by;
        }
        return am2.r().B();
    }

    private void getAttackRange(float f2, WeaponAction au2, UnitAttachment ad2) {
        int n2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        boolean bl = false;
        boolean bl2 = false;
        if (au2 == null) {
            return;
        }
        float f3 = au2.g();
        float f4 = au2.h();
        float f5 = GameUtils.a(this.eo, this.ep, f3, f4);
        if (au2 != null && au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.g && au2.h != null && au2.h.bd() > 0.0f) {
            bl2 = true;
        }
        if (au2 != null && (au2.h == null || au2.h.isDead || au2.h.cN != null)) {
            if (bl2) {
                bl = true;
            } else {
                this.clearLinkedZoneAndWaypoint();
                au2 = null;
            }
        }
        if (au2 != null && !bl && bl2 && au2.h != null) {
            boolean bl3 = true;
            if (this.i < l2.by - 100) {
                bl3 = false;
            }
            if (!this.getBarrelLength(au2.h, bl3)) {
                bl = true;
            }
            if (!bl) {
                this.i = l2.by;
            }
        }
        if (au2 != null && bl) {
            UnitInstance am2;
            UnitConfig h2 = null;
            if (au2.h != null) {
                h2 = au2.h.getCustomEffectHandler();
            }
            if ((am2 = UnitType.a(this, au2.h.eo, au2.h.ep, (float) (n2 = this.cS()), h2)) != null) {  // 02b L2217: (float)var11
                au2.h = am2;
                f3 = au2.g();
                f4 = au2.h();
                f5 = GameUtils.a(this.eo, this.ep, f3, f4);
                this.aB();
            } else {
                this.clearLinkedZoneAndWaypoint();
                au2 = null;
            }
        }
        if (au2 != null) {
            if (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.d) {
                if (!this.a(au2.h)) {
                    this.completeWaypoint();
                    au2 = null;
                }
            } else if (!bl2 && !this.l(au2.h)) {  // 02b y.l(am)
                this.completeWaypoint();
                au2 = null;
            }
        }
        if (au2 != null && au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.d && au2.h != null && au2.h.hp >= au2.h.maxHp && au2.h.cm >= 1.0f) {
            this.clearLinkedZoneAndWaypoint();
            au2 = null;
        }
        if (au2 != null && au2.h == this) {
            this.completeWaypoint();
            au2 = null;
        }
        if (au2 != null && au2 != null && au2.h != null && au2.h.bd() != 0.0f) {
            boolean bl4 = false;
            if (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.d) {
                bl4 = true;
            }
            if (bl4) {
                this.completeWaypoint();
                au2 = null;
            }
        }
        if (au2 != null && au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.g && au2.h.player != this.player && au2.h.bd() == 0.0f) {
            boolean bl5 = true;
            if (l2.P() && this.player.d(au2.h.player)) {
                bl5 = false;
            }
            if (bl5) {
                this.completeWaypoint();
                au2 = null;
            }
        }
        if (au2 != null) {
            int n3;
            int n4;
            if (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.g) {
                n4 = this.v(au2.h);
                n2 = this.w(au2.h) ? 1 : 0;  // 02b am.w(am)
            } else {
                n4 = this.u(au2.h);  // 02b am.u(am)
                n2 = this.x(au2.h) ? 1 : 0;  // 02b am.x(am)
            }
            if (this.upgradeTarget != null) {
                WeaponAction au3;
                float f6 = GameUtils.a(this.upgradeTarget.eo, this.upgradeTarget.ep, f3, f4);
                if (f6 < (float)((n3 = n4 + 80) * n3)) {
                    ad2.rotationAngle = false;
                }
                if ((au3 = this.upgradeTarget.getFirstWaypoint()) == null) {
                    ad2.rotationAngle = false;
                }
                if (au3 != null && !au2.b(au3)) {
                    ad2.rotationAngle = false;
                }
            }
            float f7 = n4;
            if (this.h) {
                f7 += 5.0f;
            }
            n3 = 30;
            if (n4 <= 30) {
                n3 = 9;
            }
            if (f5 > f7 * f7) {
                if (!this.isUsable() || au2.k == 0.0f) {
                    this.completeWaypoint();
                } else {
                    float f8;
                    boolean bl6 = false;
                    if (au2.k >= 0.0f && au2.k < (f8 = (float)GameUtils.a((int)f5) - f7)) {
                        bl6 = true;
                    }
                    if (bl6) {
                        this.completeWaypoint();
                    } else {
                        this.isPathingActive = true;
                        this.l = f3;
                        this.pathTargetY = f4;
                        this.n = n4 > 58 ? (int)(((float)n4 - 41.0f) / ((float)l2.bL.tilePixelWidth * 1.414f)) : 0;
                        if (n4 < 30 || n2 != 0) {
                            if (f5 < 841.0f) {
                                this.j = true;
                            }
                            if (f5 < (f8 = (float)(n4 + 14)) * f8 && this.s > 0.0f && this.aU == null) {
                                this.j = true;
                            }
                        }
                        this.r = this.n;
                        if (this.s > 90.0f) {
                            this.s = 90.0f;
                        }
                    }
                }
            } else {
                Object object;
                int n5 = this.aT();
                if (n5 == -1) {
                    n5 = 0;
                }
                float f9 = 0.0f;
                if (this.canFaceTarget()) {
                    f9 = this.b(f2, f3, f4);
                }
                boolean bl7 = false;
                if (GameUtils.c(f9) < 30.0f || !this.canFaceTarget()) {
                    this.h = true;
                    ad2.attachType = true;
                    object = this.cL[n5];
                    if (((UnitTurret) object).maxRotationAngle < this.getTurretRange(n5)) {
                        ((UnitTurret) object).maxRotationAngle += f2;
                    } else {
                        ((UnitTurret) object).maxRotationAngle = this.getTurretRange(n5);
                        bl7 = true;
                    }
                }
                if (bl7) {
                    object = au2.h;
                    if (au2.a != com.corrodinggames.rts.game.units.WeaponTypeEnum.g) {
                        if (((UnitInstance) object).cm < 1.0f) {
                            this.bC();
                            float f10 = this.a_((UnitInstance) object);
                            float f11 = f10 * f2;
                            boolean bl8 = false;
                            boolean bl9 = false;
                            CustomActionBase b2 = this.getBarrelLength((UnitInstance) object);
                            if (b2 != null) {
                                if (((UnitInstance) object).cm + f11 > 1.0f) {
                                    f11 = 1.0f - ((UnitInstance) object).cm;
                                    bl8 = true;
                                }
                                double d2 = ((UnitInstance) object).cm + f11 - ((UnitInstance) object).cn;
                                double d3 = 0.0;
                                if (bl8) {
                                    d3 = 1.0f - ((UnitInstance) object).cn;
                                } else {
                                    double d4 = 0.001f;
                                    if (d2 >= d4) {
                                        int n6 = (int)(d2 / d4);
                                        d3 = (double)n6 * d4;
                                    }
                                }
                                boolean bl10 = false;
                                if (d3 > 0.0 && this.player.am.a(b2)) {
                                    bl10 = true;
                                }
                                if (!bl10 && (d3 <= 0.0 || b2.c((UnitInstance) this, d3))) {
                                    ((UnitInstance) object).cn = (float)((double)((UnitInstance) object).cn + d3);
                                } else {
                                    if (!bl10) {
                                        this.player.am.a(b2, (UnitInstance) this, d3);
                                    }
                                    f11 = 0.0f;
                                    bl8 = false;
                                    bl9 = true;
                                }
                            }
                            if (!bl9) {
                                this.a((UnitInstance) object, f2, n5);
                                float f12 = ((UnitInstance) object).cm + f11;
                                if (bl8) {
                                    f12 = 1.0f;
                                }
                                ((UnitInstance) object).r(f12);
                                if (f12 >= 1.0f && (double)f10 < 0.3 && ((UnitInstance) object).player == l2.bs) {
                                    l2.bS.i.a((UnitInstance) object);
                                }
                                this.aO = false;
                            } else {
                                this.aO = true;
                            }
                        } else {
                            this.a((UnitInstance) object, f2, n5);
                            ((UnitInstance) object).hp += this.b((UnitInstance) object) * f2;  // 02b b(am) 缂備緡鍨崇划顖炲汲?
                            if (((UnitInstance) object).hp > ((UnitInstance) object).maxHp) {
                                ((UnitInstance) object).hp = ((UnitInstance) object).maxHp;
                                this.completeWaypoint();
                            }
                            this.aO = false;
                        }
                    } else {
                        this.b((UnitInstance) object, f2, n5);
                        this.aO = false;
                        this.bC();
                        boolean bl11 = false;
                        boolean bl12 = this.getTurretAngularDecel((UnitInstance) object);
                        float f13 = this.z((UnitInstance) object);
                        boolean bl13 = au2.h.bd() > 0.0f;
                        CustomActionBase b3 = this.getBarrelLength((UnitInstance) object);
                        if (bl13 || b3 != null) {
                            // empty if block
                        }
                        boolean bl14 = false;
                        if (!bl13 && this.V < 100.0f && !bl13) {
                            if ((double)((UnitInstance) object).cm < 0.5) {
                                if (b3 == null) {
                                    bl14 = true;
                                }
                            } else if ((double)(((UnitInstance) object).hp / ((UnitInstance) object).maxHp) < 0.5) {
                                bl14 = true;
                            }
                        }
                        if (!bl14) {
                            float f14;
                            if (((UnitInstance) object).cm < 1.0f) {
                                f14 = this.getReloadProgressRate((UnitInstance) object) * f2;
                                if (f14 >= ((UnitInstance) object).cm) {
                                    f14 = ((UnitInstance) object).cm;
                                    ((UnitInstance) object).cm = 0.0f;
                                } else {
                                    ((UnitInstance) object).cm -= f14;
                                }
                                ((UnitInstance) object).cn = ((UnitInstance) object).cm;
                                if (b3 != null) {
                                    b3.a((UnitInstance) this, f14, true);
                                }
                                if (((UnitInstance) object).cm <= 0.0f) {
                                    bl11 = true;
                                }
                            } else {
                                f14 = f13 * f2;
                                if (f14 >= ((UnitInstance) object).hp) {
                                    f14 = ((UnitInstance) object).hp;
                                    ((UnitInstance) object).hp = -1.0f;
                                } else {
                                    ((UnitInstance) object).hp -= f14;
                                }
                                ((UnitInstance) object).dp = 1000.0f;
                                if (bl12) {
                                    float f15 = f14 / ((UnitInstance) object).maxHp;
                                    CustomActionBase b4 = ((UnitInstance) object).getResourceProduction();
                                    CustomActionBase b5 = ((UnitInstance) object).getCustomResourceOverride();
                                    if (b5 != null) {
                                        b4 = b5;
                                    }
                                    if (bl13 || b3 != null) {
                                        // empty if block
                                    }
                                    if (b4.a() > 0) {
                                        this.ab += f15 * (float)b4.a();
                                        if (this.ab > 1.0f) {
                                            this.player.o += (double)((int)this.ab);
                                            this.ab -= (float)((int)this.ab);
                                        }
                                        b4.a((UnitInstance) this, f15, false);
                                    } else {
                                        b4.a((UnitInstance) this, f15, true);
                                    }
                                }
                                if (((UnitInstance) object).hp <= 0.0f) {
                                    bl11 = true;
                                }
                            }
                        }
                        if (bl11 && !((UnitInstance) object).isDead) {
                            if (!bl12) {
                                CustomActionBase b6 = ((UnitInstance) object).getCustomResourceOverride();
                                if (b6 != null) {
                                    com.corrodinggames.rts.gameFramework.GlobalState.e("refund: " + b6.a(false, true, 10, true));
                                    b6.a((UnitInstance) this, 1.0, true);
                                } else {
                                    b6 = ((UnitInstance) object).getResourceProduction();
                                    if (((UnitInstance) object).bx != null) {
                                        b6 = ((UnitInstance) object).bx;
                                        com.corrodinggames.rts.gameFramework.GlobalState.e("refund==null overridePriceBuildCost: " + b6.a(false, true, 10, true));
                                    }
                                    b6.a((UnitInstance) this, 0.8f, true);
                                    if (((UnitInstance) object).cm >= 1.0f && b3 != null) {
                                        b3.a((UnitInstance) this, 0.8f, true);
                                    }
                                }
                            }
                            ((UnitInstance) object).isDead = true;
                            ((UnitInstance) object).bW = l2.by;
                            ((UnitInstance) object).canBuild();
                            if (object instanceof UnitInstance && ((UnitInstance) object).isFactoryBuilding()) {
                                l2.bU.a((UnitType) object);  // 02b k/l.a(y)
                            }
                        }
                    }
                }
            }
        }
    }

    public void k(float f2) {
        Object object;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.bQ != null) {
            this.bQ = null;
        }
        if (this.bR != null) {
            this.bS = GameUtils.a(this.bS, f2);
            this.bQ = this.bR;
            if (this.bS == 0.0f) {
                this.bR = null;
            }
        }
        if (this.s != 0.0f) {
            this.s = GameUtils.a(this.s, f2);
        }
        if (this.cf != 0.0f) {
            this.c = GameUtils.a(this.c, f2);
        }
        WeaponAction au2 = this.getFirstWaypoint();
        this.j = false;
        boolean bl = this.isPathingActive;
        this.isPathingActive = false;
        this.t = false;
        this.r = 150;
        if (au2 != null && au2.l > 0.0f && au2.l < this.V) {
            this.clearLinkedZoneAndWaypoint();
            au2 = null;
        }
        UnitAttachment ad2 = aP;
        ad2.a();  // 02b ad.a() 闂備焦褰冪粔鍫曟偪?
        if (au2 != null) {
            this.V += f2;
            object = au2.a;
            if (object == com.corrodinggames.rts.game.units.WeaponTypeEnum.a || object == com.corrodinggames.rts.game.units.WeaponTypeEnum.h || object == com.corrodinggames.rts.game.units.WeaponTypeEnum.j) {
                this.a(f2, au2, ad2, bl);
            } else if (object == com.corrodinggames.rts.game.units.WeaponTypeEnum.b) {
                this.getBarrelLength(f2, au2, ad2);
            } else if (object == com.corrodinggames.rts.game.units.WeaponTypeEnum.c) {
                this.getReloadProgressRate(f2, au2, ad2);
            } else if (object == com.corrodinggames.rts.game.units.WeaponTypeEnum.d || object == com.corrodinggames.rts.game.units.WeaponTypeEnum.g) {
                this.getAttackRange(f2, au2, ad2);
            } else if (object == com.corrodinggames.rts.game.units.WeaponTypeEnum.e) {
                this.getSensorRange(f2, au2, ad2);
            } else if (object == com.corrodinggames.rts.game.units.WeaponTypeEnum.i) {
                this.getTurretRange(f2, au2, ad2);
            } else if (object == com.corrodinggames.rts.game.units.WeaponTypeEnum.m) {
                this.d(f2, au2, ad2);
            } else if (object == com.corrodinggames.rts.game.units.WeaponTypeEnum.k || object == com.corrodinggames.rts.game.units.WeaponTypeEnum.l || object == com.corrodinggames.rts.game.units.WeaponTypeEnum.n) {
                this.c(f2, au2, ad2);
            } else if (object == com.corrodinggames.rts.game.units.WeaponTypeEnum.o || object == com.corrodinggames.rts.game.units.WeaponTypeEnum.p) {
                this.a(f2, au2, ad2);
            } else if (object == com.corrodinggames.rts.game.units.WeaponTypeEnum.q) {
                this.b(f2, au2, ad2);
            }
            if (au2 != this.getFirstWaypoint()) {
                au2 = null;
            }
        }
        this.h = ad2.attachType;
        if (au2 != null && au2.m && this.f > 1) {
            boolean bl2 = true;
            WeaponAction au3 = this.k(1);  // 02b y.k(int)
            if (au3 != null && (au3.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.k || au3.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.j)) {
                bl2 = false;
            }
            if (bl2) {
                this.completeWaypoint();
                au2 = null;
            }
        }
        if (au2 == null) {
            this.isPathingActive = false;
        }
        if (this.isPathingActive) {
            object = this.dn();
            if (object != null && ((UnitTrait)object).H) {
                this.bx();
            }
        } else if (this.q != 0) {
            this.q = 0;
        }
        this.b(l2, f2);
        this.a(l2, f2, au2, ad2);
    }

    private void a(float f2, float f3, float f4) {
        if (this.aq < -900.0f) {
            float f5;
            this.aq = f5 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eo, this.ep, f3, f4);
        }
        if (f2 < 10000.0f && this.bX()) {  // 02b am.bX()
            this.ar = true;
        }
        if (this.ar) {
            if ((double)this.cB < (double)this.bd() * 0.6 || f2 < 40000.0f && this.cB < this.bd()) {
                this.l += GameUtils.cosFast(this.aq + 180.0f) * 600.0f;
                this.pathTargetY += GameUtils.sinFast(this.aq + 180.0f) * 600.0f;
            } else {
                this.ar = false;
                this.aq = -999.0f;
                this.resetPathData();
            }
        }
    }

    private void a(float f2, UnitTransform af2, UnitAttachment ad2, WeaponAction au2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitType y2 = this.upgradeTarget;
        float f3 = y2.eo + this.ak;
        float f4 = y2.ep + this.heightOffset;
        boolean bl = false;
        int n2 = l2.by - y2.an;
        float f5 = GameUtils.a(this.eo, this.ep, f3, f4);
        if (n2 > 300 || this.b > 1.0f) {
            this.d += f2;
        }
        boolean bl2 = false;
        if (this.d > 300.0f) {
            bl2 = true;
        }
        if (n2 > 300 && f5 > 250000.0f) {
            bl2 = true;
        }
        if (this.b > 1.0f) {
            if (this.c != 0.0f) {
                bl2 = true;
            }
            if (this.d > 10.0f) {
                bl2 = true;
            }
        }
        if (bl2) {
            this.c = 90.0f;
        }
        if (this.c == 0.0f) {
            float f6;
            float f7;
            float f8;
            UnitTransform af3;
            this.resetPathData();
ad2.scaleValue = f3;  // 02b ad.e (float)
            ad2.f = f4;
            UnitTransform af4 = null;
            if (n2 < 3000 && af4 == null && y2.v > 2 && y2.v - y2.aw <= 2) {
                af4 = y2.o(2);
            }
            if (n2 < 1500 && af4 == null && y2.v > 0 && y2.aw + 0 >= y2.v) {
                af3 = y2.o(0);
                af4 = reusablePathNode;
                float f9 = com.corrodinggames.rts.gameFramework.GameUtils.a(y2.eo, y2.ep, af3.a, af3.b);
                f8 = 80.0f;
                if (n2 > 300) {
                    f7 = 0.06666667f;
                    f8 -= (float)(n2 - 300) * 0.06666667f;
                }
                af4.a = y2.eo + GameUtils.cosFast(f9) * f8;
                af4.b = y2.ep + GameUtils.sinFast(f9) * f8;
            }
            if (af4 != null) {
                ad2.offsetY = true;
ad2.scaleValue = af4.a + this.ak;  // 02b ad.e (float)
                ad2.f = af4.b + this.heightOffset;
            } else if (y2.v >= 2 && y2.aw >= 1) {
                UnitTransform af5;
                if (y2.aw >= 2) {
                    af3 = y2.o(0);
                    af5 = y2.o(1);
                } else {
                    af3 = y2.o(0);
                    af5 = y2.o(0);
                }
                if (af3 != null && af5 != null) {
                    float f10;
                    float f11;
                    f8 = GameUtils.c(y2.eo, y2.ep, af3.a, af3.b);
                    f7 = 1.0f - (f8 - 15.0f) * 0.05f;
                    if (f7 > 2.0f) {
                        f7 = 2.0f;
                    }
                    if (f7 < 0.0f) {
                        f7 = 0.0f;
                    }
                    if (f7 > 1.0f) {
                        if (y2.aw >= 3) {
                            UnitTransform af6 = y2.o(2);
                            f11 = af5.a - af3.a;
                            f6 = af5.b - af3.b;
                            f10 = af6.a - af5.a;
                            float f12 = af6.b - af5.b;
                            f11 += f10 * (f7 - 1.0f);
                            f6 += f12 * (f7 - 1.0f);
                        } else {
                            f11 = af5.a - af3.a;
                            f6 = af5.b - af3.b;
                        }
                    } else {
                        float f13 = af5.a - af3.a;
                        f10 = af5.b - af3.b;
                        f11 = f13 * f7;
                        f6 = f10 * f7;
                    }
                    f3 = af3.a + this.ak + f11;
                    f4 = af3.b + this.heightOffset + f6;
ad2.scaleValue = f3;  // 02b ad.e (float)
                    ad2.f = f4;
                }
            }
            float f14 = 45.0f;
            if (this.b <= 1.0f) {
                f14 = 60.0f;
            } else if (n2 < 500 && this.b <= 1.0f) {
                f14 = 110.0f;
            }
            if (f5 < f14 * f14) {
                this.d = 0.0f;
            }
            boolean bl3 = false;
            WeaponAction au3 = y2.getFirstWaypoint();
            boolean bl4 = false;
            if (au3 == null || au2 != null) {
                // empty if block
            }
            if (au3 == null || bl4) {
                this.e += f2;
                boolean bl5 = false;
                if (au2 != null && (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.a || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.h || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.j)) {
                    bl5 = true;
                }
                if (f5 < (f6 = bl5 && this.e > 600.0f ? 260.0f : (bl5 && this.e > 360.0f ? 140.0f : (bl5 && this.e > 180.0f ? 70.0f : (bl5 && this.e > 120.0f ? 50.0f : 16.0f)))) * f6) {
                    bl3 = true;
                }
                if (bl) {
                    bl3 = true;
                }
            }
            if (bl3) {
                boolean bl6 = false;
                if (au3 == null) {
                    bl6 = true;
                }
                if (bl4) {
                    bl6 = true;
                }
                if (bl6 && GameUtils.c(f6 = this.c(f2, this.am)) < 3.0f && au2 != null && (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.a || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.h)) {
                    this.completeWaypoint();
                    if (y2 != null) {
                        boolean bl7 = false;
                        WeaponAction au4 = this.getFirstWaypoint();
                        WeaponAction au5 = y2.getFirstWaypoint();
                        if (au4 != null && au5 != null && au4.b(au5)) {
                            bl7 = true;
                        }
                        if (!bl7) {
                            this.a((UnitInstance) null);
                        }
                    }
                }
            } else if (!bl) {
                ad2.offsetX = true;
            }
        } else {
            UnitTransform af7 = null;
            int n3 = 8;
            if (af7 == null && y2.v > 2 && n3 < y2.aw) {
                int n4 = n3;
                af7 = y2.o(n4);
            }
            if (af7 == null) {
                af7 = reusablePathNode;
                af7.a = y2.eo;
                af7.b = y2.ep;
            }
            float f15 = GameUtils.a(this.eo, this.ep, af7.a, af7.b);
            float f16 = this.cj + y2.cj + 15.0f;
            float f17 = this.cj + y2.cj + 100.0f;
            if (f15 < f16 * f16) {
                this.d = 0.0f;
                this.c = 0.0f;
            } else if (f15 < f17 * f17) {
                // empty if block
            }
            float f18 = 300.0f;
            boolean bl8 = true;
            if (this.aU == null && af2 != null && (GameUtils.c(this.o - af7.a) > 300.0f || GameUtils.c(this.p - af7.b) > 300.0f) && this.s > 30.0f) {
                this.s = 30.0f;
            }
            if (this.s == 0.0f && this.aU == null) {
                this.s = 700.0f;
                boolean bl9 = false;
                this.a(af7.a, af7.b, 0, false, bl9);
            }
            if (af2 != null) {
ad2.scaleValue = af2.a;  // 02b ad.e (float)
                ad2.f = af2.b;
                if (!bl) {
                    ad2.offsetX = true;
                }
            }
        }
    }

    private void a(GlobalState l2, float f2, WeaponAction au2, UnitAttachment ad2) {
        boolean bl = this.I();
        if (this.aU != null) {
            this.b(l2);
        }
        if (this.upgradeTarget != null && (this.upgradeTarget.isDead || !this.upgradeTarget.bT())) {
            this.a((UnitInstance) null);
        }
        if (this.isPathingActive) {
            WeaponAction au3;
            UnitTransform af2 = this.aE();
            WeaponAction au4 = this.getFirstWaypoint();
            if (au4 == null) {
                ad2.rotationAngle = false;
            }
            if (L) {
                ad2.rotationAngle = false;
            }
            if (this.hasWeapons && this.shieldCapacity > 0 && this.aG()) {
                this.an = l2.by;
            }
            if (au4 != null && this.upgradeTarget != null && ad2.rotationAngle && (au3 = this.upgradeTarget.getFirstWaypoint()) != null && !au3.b(au4)) {
                ad2.rotationAngle = false;
            }
            if (this.upgradeTarget != null && ad2.rotationAngle) {
                this.a(f2, af2, ad2, au2);
            } else if (this.cl != 0.0f) {
ad2.scaleValue = this.l;  // 02b ad.e (float)
                ad2.f = this.pathTargetY;
                ad2.offsetX = true;
            } else {
                boolean bl2 = false;
                if (this.aU == null) {
                    float f3;
                    if (af2 == null) {
                        if (this.u && this.s < 450.0f && this.aU == null) {
                            bl2 = true;
                        }
                        if (this.s == 0.0f) {
                            bl2 = true;
                        }
                    }
                    if (this.s == 0.0f && (this.checkIsLargeUnit() || this.aV())) {
                        f3 = this.m() - 1.0f;
                        if (GameUtils.c(this.o - this.l) > f3 || GameUtils.c(this.p - this.pathTargetY) > f3) {
                            bl2 = true;
                        }
                    }
                    if (au2 != null && this.s == 0.0f && (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.e || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.i)) {
                        f3 = 12.0f;
                        if (GameUtils.c(this.o - this.l) > f3 || GameUtils.c(this.p - this.pathTargetY) > f3) {
                            bl2 = true;
                        }
                    }
                    if (au2 != null) {
                        f3 = this.r;
                        if (GameUtils.c(this.o - this.l) > f3 || GameUtils.c(this.p - this.pathTargetY) > f3) {
                            if (this.s > 30.0f) {
                                this.s = 30.0f;
                            }
                            if (this.s == 0.0f) {
                                bl2 = true;
                            }
                        }
                    }
                }
                if (bl2) {
                    this.s = 500.0f;
                    boolean bl3 = this.hasWeapons && this.techLevel > 1;
                    this.a(this.l, this.pathTargetY, this.n, bl3, this.t);
                }
                if (af2 != null && this.pathCost == null && this.aw >= 2 && this.z() > 5.0f) {
                    UnitTransform af3 = this.av[1];
                    float f4 = GameUtils.a(this.eo, this.ep, af2.a, af2.b);
                    float f5 = GameUtils.a(this.eo, this.ep, af3.a, af3.b);
                    if (f4 < 36.0f) {
                        this.aJ();
                        af2 = this.aE();
                    } else if (f5 < 361.0f) {
                        this.aJ();
                        af2 = this.aE();
                    }
                }
                if (af2 != null) {
ad2.scaleValue = af2.a;  // 02b ad.e (float)
                    ad2.f = af2.b;
                    ad2.offsetX = true;
                } else if (this.j) {
ad2.scaleValue = this.l;  // 02b ad.e (float)
                    ad2.f = this.pathTargetY;
                    ad2.offsetX = true;
                }
            }
        }
        this.a(f2, ad2, au2, bl);
    }

    private void a(float f2, UnitAttachment ad2, WeaponAction au2, boolean bl) {
        float f3 = 0.0f;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.isPathingActive && ad2.offsetX && bl) {
            boolean bl2;
            float f4;
            float f5 = ad2.scaleValue;  // 02b ad.e (float)
            float f6 = ad2.f;
            float f7 = this.z();
            float f8 = GameUtils.a(this.eo, this.ep, f5, f6);
            float f9 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eo, this.ep, f5, (f6 - this.ep) * this.ba() + this.ep);
            boolean bl3 = false;
            float f10 = this.bc();
            if (f10 > 0.95f) {
                bl3 = true;
            } else if ((double)f10 > 0.87) {
                if (this.techLevel <= 1 && this.aw > 0 && this.aw <= 9 && this.hasWeapons && f8 < 250000.0f) {
                    bl3 = true;
                }
            } else if ((double)f10 > 0.7) {
                if (this.techLevel <= 1 && this.aw > 0 && this.aw <= 4 && this.hasWeapons && f8 < 40000.0f) {
                    bl3 = true;
                }
            } else if ((double)f10 > 0.4 && this.techLevel <= 1 && this.aw > 0 && this.aw <= 2 && this.hasWeapons && f8 < 10000.0f) {
                bl3 = true;
            }
            boolean bl4 = true;
            float f11 = 179.0f;
            if (this.R != null && this.getMuzzlePoint() && this.bj() && !this.aV()) {
                this.ch = f9;
            } else if (this.az <= 0.0f) {
                f11 = this.a(f2, f9, bl4, bl3);
            }
            float f12 = 20.0f;
            if (f8 > 361.0f) {
                f12 = 46.0f;
            }
            if (f8 > 3600.0f) {
                f12 = 89.0f;
            }
            if ((double)(f4 = this.A()) < 1.4) {
                f12 = f8 > 6400.0f ? (f12 *= 0.5f) : 17.0f;
            }
            if (f7 > 5.0f && (double)this.cf < 0.01 && (double)this.cf > -0.01) {
                f12 = 1.0f;
            }
            if ((double)f4 < 1.1) {
                f12 *= 0.7f;
            }
            if ((double)this.cf > 0.4 && f8 > 16900.0f) {
                f12 = 180.0f;
            }
            if (this.aY() && this.v == this.aw) {
                f12 = 1.0f;
            }
            if (this.bj()) {
                f12 = 181.0f;
            }
            float f13 = 4.0f;
            boolean bl5 = bl2 = this.aw == 1;
            if ((!bl2 || f8 >= f13 * f13) && GameUtils.c(f11) <= f12) {
                f3 = 1.0f;
                if (ad2.offsetY) {
                    if (f8 < 2500.0f) {
                        f3 -= 0.15f;
                    }
                    if (f8 < 900.0f) {
                        f3 -= 0.15f;
                    }
                    if (f8 < 225.0f) {
                        f3 -= 0.3f;
                    }
                } else if (this.upgradeTarget != null) {
                    if (f8 > 400.0f) {
                        f3 += 0.2f;
                    }
                    if (f8 < 49.0f) {
                        f3 -= 0.15f;
                    }
                    if (f8 < 9.0f) {
                        f3 -= 0.15f;
                    }
                }
                if (f8 < 9.0f) {
                    f3 = 0.0f;
                }
            }
            if (bl2 && f3 != 0.0f) {
                if (f8 < 324.0f && this.getBarrelEndPoint() < 0.13f && this.z() > 1.0f) {
                    f3 = 0.5f * f3;
                }
                if (f8 < 169.0f && this.getBarrelEndPoint() < 0.15f && this.z() > 0.9f) {
                    f3 = 0.5f * f3;
                }
                if (f7 > 5.0f) {
                    if (f8 < 324.0f && f3 > 0.5f) {
                        f3 = 0.5f;
                    }
                    if (f8 < 81.0f && f3 > 0.25f) {
                        f3 = 0.25f;
                    }
                }
            }
            boolean bl6 = false;
            if (!bl2 && f8 < 256.0f) {
                bl6 = true;
            }
            if (bl2 && f8 < f13 * f13) {
                bl6 = true;
            }
            if ((this.globalUnitIndex == l2.bx || this.globalUnitIndex == l2.bx - 1) && this.Z != null && this.Z.c(f5, f6, 2.0f)) {
                bl6 = true;
            }
            if (f3 > 0.0f) {
                this.W += f2;
                if (this.W > 200.0f && f8 < 3600.0f && this.aw >= 2) {
                    float f14 = this.W;
                    this.aJ();
                    this.W = f14;
                }
                if (this.W > 600.0f && this.aw >= 2 && this.pathCost == null) {
                    this.resetPathData();
                }
                if (this.W > 80.0f && this.b > 30.0f) {
                    this.resetPathData();
                }
                if (this.W > 40.0f && this.aw >= 2 && this.pathCost == null) {
                    UnitTransform af2 = this.av[1];
                    float f15 = GameUtils.a(this.eo, this.ep, af2.a, af2.b);
                    if (f15 < f8) {
                        float f16 = this.W;
                        this.aJ();
                        this.W = f16;
                    }
                }
            }
            if (bl6) {
                this.aJ();
                if (bl2) {
                    this.d = 0.0f;
                    this.c = 0.0f;
                    if (!this.u && this.upgradeTarget == null && au2 != null && au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.a) {
                        this.clearLinkedZoneAndWaypoint();
                    }
                }
            }
        }
        if (this.ci && !this.bj()) {
            f3 = -f3 * this.bc();
        }
        if (this.az > 0.0f) {
            f3 = 0.0f;
        }
        if (!this.bi()) {
            if (this.cf < f3) {
                this.cf = GameUtils.a(this.cf, f3, this.getTurretFacingAngle() * f2);
            }
            if (this.cf > f3) {
                this.cf = GameUtils.a(this.cf, f3, this.getBarrelEndPoint() * f2);
            }
        } else {
            this.cf = f3;
        }
        this.cK = ad2.offsetX && bl;
    }

    @Deprecated
    public boolean hasTargetUnit() {
        return this.R != null;
    }

    public boolean hasActiveTurretAttachment() {
        if (this.R != null && !this.R.isDead) {
            int n2 = this.bl();
            for (int i2 = 0; i2 < n2; ++i2) {
                if (this.cL[i2].targetUnit == null || !this.isTurretMountAvailable(i2)) continue;
                return true;
            }
        }
        return false;
    }

    public UnitInstance getCurrentTargetUnit() {
        if (this.R != null && !this.R.isDead) {
            return this.R;
        }
        WeaponAction au2 = this.getFirstWaypoint();
        if (au2 != null && au2.h != null && !au2.h.isDead) {
            return au2.h;
        }
        return null;
    }

    private void a(GlobalState l2, float f2, float f3) {
        aQ.a(f3);
        l2.cc.a(this.eo, this.ep, f3, this, f2, aQ);
        if (!(aQ.animType == 0 || this.R != null && this.getSensorRange(this.R))) {  // 02b ae.a(int) = animType
            aR.a(f3);
            l2.cc.a(this.eo, this.ep, f3, this, f2, aR);
        }
    }

    public boolean hasMultipleTurrets() {
        return this.bl() > 1;
    }

    private void a(GlobalState l2, float f2) {
        int n2;
        int n3 = this.bl();
        if (!this.ac()) {
            for (int i2 = 0; i2 < n3; ++i2) {
                this.cL[i2].targetUnit = this.R;
            }
            return;
        }
        boolean bl = false;
        for (n2 = 0; n2 < n3; ++n2) {
            UnitTurret ap2 = this.cL[n2];
            if (this.v(n2) != -1) continue;
            boolean bl2 = false;
            boolean bl3 = false;
            if (this.a(n2, this.R, false, false)) {
                ap2.targetUnit = this.R;
                continue;
            }
            bl = true;
            if (ap2.targetUnit != this.R) continue;
            ap2.targetUnit = null;
        }
        if (bl) {
            float f3 = this.b(false);
            aT.a(this);
            l2.cc.a(this.eo, this.ep, f3, this, f2, aT);
        }
        for (n2 = 0; n2 < n3; ++n2) {
            int n4 = this.v(n2);
            if (n4 == -1) continue;
            this.cL[n2].targetUnit = this.cL[n4].targetUnit;
        }
    }

    public boolean canBeAutoTargeted() {
        if (!this.l()) {  // 02b am.l() 闂佺娉涢埀顒傚枙閺?
            return false;
        }
        UnitTrait n2 = this.dn();
        return n2 == null || n2.M;
    }

    private void b(GlobalState l2, float f2) {
        int n2;
        int n3 = this.bl();
        boolean bl = false;
        if (this.ad()) {  // 02b y.ad()
            n2 = 0;
            boolean bl2 = false;
            if (this.R != null) {
                boolean bl3;
                UnitTrait n4 = this.dn();
                if (n4 != null && this.cO != null && n4.L && this.cO.R == this.R) {
                    n2 = 1;
                }
                if (!this.a(this.R, false) && n2 == 0 && (bl3 = true)) {
                    this.R = null;
                }
            }
            if (this.R != null && n2 == 0) {
                bl2 = !this.getSensorRange(this.R);
            }
            this.S = GameUtils.a(this.S, f2);
            this.T = GameUtils.a(this.T, f2);
            if ((this.R == null || bl2) && this.S == 0.0f && this.bf()) {
                this.S = 20.0f + this.eo % 5.0f + this.ep % 5.0f;
                float f3 = this.b(false);
                this.a(l2, f2, f3);
                if (this.R != null) {
                    this.T = 0.0f;
                }
            }
            if (this.R != null && this.T == 0.0f) {
                this.T = 20.0f + this.eo % 5.0f + this.ep % 5.0f;
                this.a(l2, f2);
            }
            for (int i2 = 0; i2 < n3; ++i2) {
                this.cL[i2].hasLimitedArc = false;
            }
            if (this.R != null) {
                float f4;
                float f5 = GameUtils.a(this.eo, this.ep, this.R.eo, this.R.ep);
                if (f5 < (f4 = this.getAttackRangeIncludingTarget(this.R)) * f4 || n2 != 0) {
                    UnitInstance am2;
                    UnitTurret ap2;
                    int n5;
                    int n6 = this.aT();
                    for (n5 = 0; n5 < n3; ++n5) {
                        boolean bl4;
                        boolean bl5;
                        ap2 = this.cL[n5];
                        am2 = ap2.targetUnit;
                        if (am2 == null) continue;
                        boolean bl6 = bl5 = am2 == this.R;
                        if (!bl5 && !this.b(am2, true)) {
                            ap2.targetUnit = null;
                            continue;
                        }
                        boolean bl7 = false;
                        boolean bl8 = bl4 = !bl5;
                        if (!this.a(n5, am2, false, bl4)) {
                            ap2.targetUnit = null;
                            continue;
                        }
                        PointF pointF = this.getTurretWorldPos(n5);
                        PointF pointF2 = this.K(n5);
                        pointF2.a += am2.eo;
                        pointF2.b += am2.ep;
                        float f6 = com.corrodinggames.rts.gameFramework.GameUtils.a(pointF.a, pointF.b, pointF2.a, pointF2.b);
                        if (this.v(n5) != -1 || n5 == n6) continue;
                        if (!this.getMuzzlePoint()) {
                            ap2.a(70);
                            ap2.turretOffsetY = ap2.turretAngle;
                            float f7 = 179.0f;
                            if (!ap2.b()) {
                                f7 = this.a(f2, f6, n5);
                            }
                            if (!(GameUtils.c(f7) < this.getTurretTurnArc(n5))) continue;
                            ap2.hasLimitedArc = true;
                            continue;
                        }
                        boolean bl9 = false;
                        WeaponAction au2 = this.getFirstWaypoint();
                        if (au2 != null && (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.c || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.d || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.g)) {
                            bl9 = true;
                        }
                        if (bl9 || this.isPathingActive && !this.bj()) continue;
                        float f8 = this.c(f2, f6);
                        ap2.turretOffsetY = ap2.turretAngle;
                        if (!(GameUtils.c(f8) < this.getTurretTurnArc(n5))) continue;
                        ap2.hasLimitedArc = true;
                    }
                    for (n5 = 0; n5 < n3; ++n5) {
                        ap2 = this.cL[n5];
                        am2 = ap2.targetUnit;
                        if (am2 == null) continue;
                        if (this.isTurretAimedAtTarget(n5) && ap2.shootCooldown == 0.0f) {
                            bl = true;
                        }
                        if (!this.isTurretAimedAtTarget(n5)) continue;
                        this.a(f2, am2, n5);
                    }
                } else if (!this.isPathingActive && this.an()) {
                    this.j = true;
                    this.isPathingActive = true;
                    this.l = this.R.eo;
                    this.pathTargetY = this.R.ep;
                    this.n = 0;
                }
            }
        }
        if (this.aN && this.X() != null) {
            bl = true;
        }
        for (n2 = 0; n2 < n3; ++n2) {
            UnitTurret ap3 = this.cL[n2];
            if (bl || ap3.maxRotationAngle == 0.0f) continue;
            ap3.maxRotationAngle = GameUtils.a(ap3.maxRotationAngle, this.getReloadProgressRate(n2) * f2);
        }
    }

    public void b(UnitInstance am2, int n2) {
    }

    public boolean a(float f2, UnitInstance am2, int n2) {
        UnitTurret ap2 = this.cL[n2];
        int n3 = this.v(n2);
        if (n3 != -1) {
            ap2.turretAngle = this.cL[n3].turretAngle;
        }
        boolean bl = this.isTurretChargeable(n2);
        boolean bl2 = false;
        if (bl) {
            if (ap2.maxRotationAngle < this.getTurretRange(n2)) {
                if (ap2.maxRotationAngle == 0.0f) {
                    this.b(am2, n2);
                }
                ap2.maxRotationAngle += f2;
            } else {
                ap2.maxRotationAngle = this.getTurretRange(n2);
            }
            bl2 = true;
        }
        if (ap2.shootCooldown == 0.0f && this.isTurretMountAvailable(n2)) {
            boolean bl3 = false;
            boolean bl4 = false;
            if (!this.a(n2, am2, false, false)) {
                ap2.shootCooldown = -10.0f;
            } else {
                if (!bl) {
                    if (ap2.maxRotationAngle < this.getTurretRange(n2)) {
                        if (ap2.maxRotationAngle == 0.0f) {
                            this.b(am2, n2);
                        }
                        ap2.maxRotationAngle += f2;
                    } else {
                        bl2 = true;
                    }
                }
                if (bl2) {
                    ap2.shootCooldown = this.b(n2) + this.getReloadRandomDelay(n2);
                    if (!bl) {
                        ap2.maxRotationAngle = 0.0f;
                    }
                    this.a(am2, n2);
                    this.M(n2);
                    ap2.isTurning = !ap2.isTurning;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getSensorRange(UnitInstance am2) {
        float f2;
        float f3 = GameUtils.a(this.eo, this.ep, am2.eo, am2.ep);
        return f3 < (f2 = this.getAttackRangeIncludingTarget(am2)) * f2;
    }

    public boolean isAlwaysTargetable() {
        return false;
    }

    public boolean isAlwaysSelectable() {
        return true;
    }

    public boolean isAlwaysRenderable() {
        return true;
    }

    public boolean isAlwaysAttackable() {
        return true;
    }

    public boolean getAttackRange(UnitInstance am2) {
        int n2 = this.bl();
        for (int i2 = 0; i2 < n2; ++i2) {
            int n3;
            boolean bl = false;
            boolean bl2 = false;
            if (!this.isTurretMountAvailable(i2) || !this.a(i2, am2, false, false) || (n3 = this.v(i2)) != -1 && !this.a(n3, am2, false, false)) continue;
            return true;
        }
        return false;
    }

    public boolean damageAllTurrets(UnitInstance am2) {
        int n2 = this.bl();
        for (int i2 = 0; i2 < n2; ++i2) {
            int n3;
            boolean bl = true;
            boolean bl2 = false;
            if (!this.isTurretMountAvailable(i2) || !this.a(i2, am2, true, false) || (n3 = this.v(i2)) != -1 && !this.a(n3, am2, true, false)) continue;
            return true;
        }
        return false;
    }

    public boolean a(int n2, UnitInstance am2, boolean bl, boolean bl2) {
        return bl || !bl2 || this.getSensorRange(am2);
    }

    public boolean k(UnitInstance am2) {
        if (am2.i()) {
            return this.af;  // 02b y.af 闁诲孩绋掗〃鍡涱敊?
        }
        if (am2.Q()) {
            return this.hasWeapons;  // 02b y.ae 闁诲孩绋掗〃鍡涱敊?
        }
        if (!this.ah() && !am2.cH()) {  // 02b y.k(am) 闁诲孩绋掗〃澶嬩繆椤撱垺鍎? !ah() || cH()
            return false;
        }
        return this.ag();  // 02b y.ag()
    }

    public boolean a(UnitInstance am2) {
        return false;
    }

    public GameAction a(UnitTypeHandle as2, boolean bl) {
        return this.a(as2, -1, bl);
    }

    public boolean ai() {
        for (GameAction s2 : (java.util.Collection<GameAction>) (java.util.Collection) this.N()) {
            if (!s2.g()) continue;
            return true;
        }
        return false;
    }

    public GameAction a(UnitTypeHandle as2, int n2, boolean bl) {
        ArrayList arrayList = this.N();
        GameAction s2 = null;
        if (arrayList.size() > 0) {
            for (GameAction s3 : (java.util.Collection<GameAction>) (java.util.Collection) arrayList) {
                UnitTypeHandle as3;
                UnitTypeHandle as4 = s3.y();  // 03 GameAction.y() 闁哄鏅滈弻銊ッ?UnitTypeHandle
                if (bl && (as3 = s3.E()) != null) {
                    as4 = as3;
                }
                if (as4 != as2 || n2 != -1 && n2 != s3.t()) continue;
                s2 = s3;
                if (!s3.getLabel(this) || !s3.a((UnitInstance) this, false)) continue;
                return s3;
            }
        }
        return s2;
    }

    public boolean b(UnitTypeHandle as2, boolean bl) {
        GameAction s2 = this.a(as2, bl);
        if (s2 != null) {
            if (s2.g(this)) {
                return false;
            }
            return s2.getLabel(this);
        }
        return false;
    }


    public boolean aj() {
        return this.r().m();
    }


    public boolean ak() {
        return this.r().l();
    }

    public void onUnitCommandTarget(UnitInstance am2) {
    }

    public boolean hasExtraAction() {
        return false;
    }

    public final boolean a(UnitInstance am2, boolean bl) {
        if (this.player == am2.player || am2.isDead || !this.player.c(am2.player)) {
            return false;
        }
        if (this.P == UnitFlag.d) {
            return false;
        }
        if (this.P == UnitFlag.c) {
            return false;
        }
        if (am2.cN != null) {
            return false;
        }
        if (!this.k(am2)) {  // 02b y.k(am)
            return false;
        }
        if (!am2.d((UnitInstance) this)) {
            return false;
        }
        if (!bl) {
            float f2;
            float f3;
            float f4 = GameUtils.a(this.eo, this.ep, am2.eo, am2.ep);
            return f4 < (f3 = (f2 = this.b(false)) * f2);
        }
        return true;
    }

    public final boolean b(UnitInstance am2, boolean bl) {
        if (am2.isRepairable()) {
            return false;
        }
        return this.a(am2, bl);
    }

    public float getBuildRangeExtension() {
        return 0.0f;
    }

    public boolean isRusherBehavior() {
        return this.P == UnitFlag.a || this.P == UnitFlag.e || this.P == UnitFlag.f;
    }

    public float b(boolean bl) {
        float f2 = this.m();
        WeaponAction au2 = this.getFirstWaypoint();
        if (au2 != null && (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.h || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.j || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.k)) {
            f2 = au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.j ? (f2 += 110.0f) : (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.k ? (f2 += 90.0f) : (f2 += 20.0f));
            if (f2 < 190.0f) {
                f2 = 190.0f;
            }
        }
        if (this.P == UnitFlag.a) {
            f2 += 250.0f;
        } else if (this.P == UnitFlag.e) {
            f2 += 150.0f;
        } else if (this.P == UnitFlag.f) {
            f2 += 180.0f;
        } else {
            f2 += this.am();
            if (bl) {
                f2 += 110.0f;
            }
        }
        return f2;
    }

    public WeaponAction insertWaypointFront() {
        this.checkWaypointIndex(29);
        if (this.f > 0) {
            this.b(this.g[0]);
        }
        WeaponAction au2 = this.g[29];
        for (int i2 = 29; i2 >= 1; --i2) {
            this.g[i2] = this.g[i2 - 1];
        }
        this.g[0] = au2;
        if (this.f < 29) {
            ++this.f;
        }
        if (this.g[0] == null) {
            this.g[0] = new WeaponAction();
        }
        WeaponAction au3 = this.g[0];
        au3.e();
        this.V = 0.0f;
        this.Y = 0.0f;
        this.W = 0.0f;
        this.c(au3);
        this.resetPathData();
        return au3;
    }

    public void a(WeaponAction au2) {
    }

    public final void b(WeaponAction au2) {
        this.h = false;
    }

    public void c(WeaponAction au2) {
        this.bC();
        this.i = -9999;
        if (this.R != null && this.R.isRepairable()) {
            this.R = null;
        }
    }

    public WeaponAction addWaypoint() {
        this.checkWaypointIndex(this.f);
        if (this.g[this.f] == null) {
            this.g[this.f] = new WeaponAction();
        }
        WeaponAction au2 = this.g[this.f];
        au2.e();
        if (this.f < 29) {
            ++this.f;
        }
        if (this.f > 0) {
            this.c(this.g[0]);
        }
        return au2;
    }

    public WeaponAction d(float f2, float f3) {
        WeaponAction au2 = this.addWaypoint();
        au2.a(f2, f3);
        return au2;
    }

    public WeaponAction removeWaypointAt(UnitInstance am2) {
        WeaponAction au2 = this.addWaypoint();
        au2.a(am2);
        return au2;
    }

    public WeaponAction getTurretRange(float f2, float f3) {
        WeaponAction au2 = this.addWaypoint();
        au2.b(f2, f3);
        return au2;
    }

    public boolean a(WeaponAction au2, boolean bl) {
        if (au2 == null) {
            if (bl) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("isValidNewWaypoint: Skipping null waypoint");
            }
            return false;
        }
        if (au2.d() == com.corrodinggames.rts.game.units.WeaponTypeEnum.c) {
            if (au2.b == null) {
                if (bl) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("isValidNewWaypoint: Skipping build waypoint with no buildType");
                }
                return false;
            }
            GameAction s2 = this.a(au2.b, au2.d, false);
            if (s2 == null) {
                if (bl) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("Unit '" + this.r().i() + "' can not queue build:" + au2.b.i());
                }
                return false;
            }
            if (!au2.n) {
                if (s2.g(this)) {
                    if (bl) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("Builder '" + this.r().i() + "' tried to queue UnitFlag locked building:" + s2.getDisplayString());
                    }
                    return false;
                }
                if (!s2.getLabel(this)) {
                    if (bl) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("Builder '" + this.r().i() + "' tried to queue UnitFlag unavailable building:" + s2.getDisplayString());
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public WeaponAction d(WeaponAction au2) {
        WeaponAction au3 = this.addWaypoint();
        au3.c(au2);
        return au3;
    }

    public boolean hasNoWaypoints() {
        return this.getFirstWaypoint() == null;
    }

    public WeaponAction getFirstWaypoint() {
        if (this.f == 0) {
            return null;
        }
        return this.g[0];
    }

    public WeaponAction getSecondWaypoint() {
        if (this.f <= 1) {
            return null;
        }
        return this.g[1];
    }

    public WeaponAction getLastWaypoint() {
        if (this.f == 0) {
            return null;
        }
        return this.g[this.f - 1];
    }

    public void removeFirstWaypoint() {
        if (this.f == 0) {
            return;
        }
        if (this.f == 1) {
            this.completeWaypoint();
        } else {
            --this.f;
        }
    }

    public WeaponAction k(int n2) {
        return this.g[n2];
    }

    public int getWaypointCount() {
        return this.f;
    }

    public boolean isMoveWaypoint() {
        WeaponAction au2 = this.getFirstWaypoint();
        return au2 != null && au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.b;
    }

    public boolean a(UnitTypeHandle as2, float f2, float f3) {
        for (int i2 = 0; i2 < this.f; ++i2) {
            WeaponAction au2 = this.g[i2];
            if (au2.a != com.corrodinggames.rts.game.units.WeaponTypeEnum.c || au2.b != as2 || !(GameUtils.c(au2.e - f2) < 10.0f) || !(GameUtils.c(au2.f - f3) < 10.0f)) continue;
            return true;
        }
        return false;
    }

    public void ensurePathNodeCapacity(int n2) {  // v19.113n: 02b y.java:3953 l(int) PathNode 鐎瑰綊鍣?
        if (n2 >= 120) {  // 02b y.l(int) 娑撳﹪妾哄Λ鈧弻?
            throw new RuntimeException("PathNode index:" + n2 + " too large");
        }
        if (this.av == at) {
            this.av = new UnitTransform[120];
        }
    }

    public boolean getBarrelLength(UnitInstance am2, boolean bl) {  // 02b am.g(am,boolean): return false
        return false;
    }

    public strictfp void n(int n2) {  // 02b y.n(int): 闁荤姳璀﹂崹鎶藉磻閿濆洦顫曢柕蹇曞Х缁屽潡鎮洪幒鎴姛闁哄拋鍋勮灋闁逞屽墴瀵?(缂備胶濮崑鎾绘煕?
        if (this.f <= n2) {
            throw new IndexOutOfBoundsException("completeWaypoint: waypointsCount:" + this.f);
        }
    }

    public int ensurePathNodeCapacity(float f2) {  // 02b y.l(float): 閺囧瓨鏌婇崝銊ф暰鐢?
        if (f2 < -0.3f) {
            int n2 = (int)((1.0f - -f2 / 10.0f) * 130.0f + 45.0f);
            if (n2 < 45) {
                n2 = 45;
            }
            return n2;
        }
        return 255;
    }

    public void checkWaypointIndex(int n2) {
        if (n2 >= 30) {
            throw new RuntimeException("Waypoint index:" + n2 + " too large");
        }
        if (this.g == O) {
            this.g = new WeaponAction[30];
        }
    }

    public void removeWaypointAt(int n2) {
        if (this.f <= n2) {
            throw new IndexOutOfBoundsException("completeWaypoint: waypointsCount:" + this.f + ", waypointIndex:" + n2);
        }
        if (n2 == 0) {
            this.completeWaypoint();
            return;
        }
        if (this.g.length > 0) {
            WeaponAction au2 = this.g[n2];
            for (int i2 = n2; i2 < this.f - 1; ++i2) {
                this.g[i2] = this.g[i2 + 1];
            }
            this.g[this.f - 1] = au2;
        }
        --this.f;
    }

    public void clearLinkedZoneAndWaypoint() {
        this.linkedBaseZone = null;  // 濠电偞鎸搁幊妯衡枍鎼淬劍鐓ｉ柣鎰靛墮婢跺秹鏌涢弽褎鎯堥柣?
        this.completeWaypoint();
    }

    public void completeWaypoint() {
        this.V = 0.0f;
        this.Y = 0.0f;
        this.W = 0.0f;
        this.ar = false;
        this.aq = -999.0f;
        this.factorySearchPending = false;
        this.w = 0;
        if (this.f == 0) {
            this.resetPathData();
            this.e = 0.0f;
            this.d = 0.0f;
            this.c = 0.0f;
            return;
        }
        if (this.f == 1) {
            this.b(this.g[0]);
            this.f = 0;
            this.resetPathData();
            this.e = 0.0f;
            this.d = 0.0f;
            this.c = 0.0f;
            this.c((WeaponAction) null);
            return;
        }
        if (this.g.length > 0) {
            WeaponAction au2 = this.g[0];
            this.b(au2);
            for (int i2 = 0; i2 < this.f - 1; ++i2) {
                this.g[i2] = this.g[i2 + 1];
            }
            this.g[this.f - 1] = au2;
        }
        --this.f;
        if (this.f > 0) {
            this.c(this.g[0]);
        } else {
            this.c((WeaponAction) null);
        }
        this.resetPathData();
    }

    public void resetAllWaypoints() {
        int n2 = this.f;
        if (this.f > 0) {
            this.b(this.g[0]);
        }
        this.V = 0.0f;
        this.Y = 0.0f;
        this.ar = false;
        this.aq = -999.0f;
        this.factorySearchPending = false;
        this.f = 0;
        this.resetPathData();
        this.pathToZone = false;  // 濠电偞鎸搁幊妯衡枍鎼达絾宕夋い鏍ㄦ皑缁愮偤鏌″鍛┛妞?
        this.a((UnitInstance) null);
        this.e = 0.0f;
        this.d = 0.0f;
        this.c = 0.0f;
        this.w = 0;
        if (n2 > 0) {
            this.c((WeaponAction) null);
        }
    }

    public void clearNonBuildWaypoints() {
        for (int i2 = 0; i2 < this.f; ++i2) {
            WeaponAction au2 = this.g[i2];
            if (au2 == null || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.c || au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.d) continue;
            this.removeWaypointAt(i2);
        }
    }


    public void rea() {
        this.a((UnitInstance) null);
        this.hasWeapons = false;
        this.aj = false;
        this.ak = 0.0f;
        this.heightOffset = 0.0f;
        this.ac = 0;
        this.c = 0.0f;
    }

    public void processLinkedFactories() {
        if (this.shieldCapacity == 0) {
            return;
        }
        WeaponAction au2 = this.as();  // 02b y.aC(): var1 = this.as()
        UnitInstance[] amArray = UnitInstance.bE.a();  // 02b u.a();  // 02b am.bE.a()
        int n2 = UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (y2.ad != this) continue;
            float f2 = GameUtils.a(this.eo, this.ep, y2.eo, y2.ep);
            boolean bl = f2 < 108900.0f;
            boolean bl2 = false;
            boolean bl3 = false;
            WeaponAction au3 = y2.as();
            if (au2 != null && au3 != null) {
                if (au2.b(au3)) {
                    bl2 = true;
                }
            } else if (au2 == null && au3 == null) {
                bl3 = true;
            }
            if (bl2 && bl) {
                y2.completeWaypoint();
                continue;
            }
            if (bl3) continue;
            y2.a((UnitType) null);  // 02b y.a((y)null)
        }
    }

    public void unlinkAllFactories() {
        com.corrodinggames.rts.gameFramework.ProjectileManager ab2;  // 02b gameFramework.ab
        WeaponAction au2;
        UnitType y2 = null;  // 02b aD(): (y)var
        if (this.shieldCapacity == 0) {
            return;
        }
        UnitInstance[] amArray = UnitInstance.bE.a();  // 02b u.a();  // 02b am.bE.a()
        int n2 = UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (!(am2 instanceof UnitInstance)) continue;
            UnitType y3 = (UnitType) am2;  // 02b (y)var9
            if (y3.ad != this) continue;
            y3.a((UnitType) null);  // 02b y.a((y)null)
            y2 = y3;
        }
        if (this.shieldCapacity != 0) {
            this.shieldCapacity = 0;
        }
        if (y2 != null && (au2 = y2.getFirstWaypoint()) != null && (ab2 = au2.i) != null) {
            ab2.c();
        }
    }

    public UnitTransform getFirstPathNode() {
        if (this.aw == 0) {
            return null;
        }
        if (this.pathCost != null) {
            return this.pathCost.a(this);
        }
        return this.av[0];
    }

    public UnitTransform getSecondPathNode() {
        if (this.aw < 2) {
            return null;
        }
        if (this.pathCost != null) {
            return this.pathCost.b(this);
        }
        return this.av[1];
    }

    public void a(int n2, float f2, float f3) {
        this.ensurePathNodeCapacity(n2);
        if (this.av[n2] == null) {
            this.av[n2] = new UnitTransform();
        }
        this.av[n2].a = f2;
        this.av[n2].b = f3;
    }

    public boolean hasAdvancedPathing() {
        if (this.pathCost != null) {
            return false;
        }
        return this.aw >= 2 && ((double)this.z() > 0.5 ? this.W > 150.0f || this.X > 150.0f : this.W > 300.0f || this.X > 300.0f);
    }

    public void resetPathData() {
        this.aw = 0;
        this.u = false;
        this.v = 0;
        this.s = 0.0f;
        this.W = 0.0f;
        this.X = 0.0f;
        this.q = 0;
    }

    public void resetPathDataFull() {
        this.resetPathData();
        this.av = at;
        this.aI = 0;
        this.aJ = null;
        this.targetDistanceArray = null;
    }

    public void advancePathNode() {
        this.X = this.W;
        this.W = 0.0f;
        if (this.pathCost != null) {
            this.pathCost.c(this);
            return;
        }
        if (this.aw == 0) {
            return;
        }
        if (this.aw == 1) {
            this.aw = 0;
            return;
        }
        UnitTransform af2 = this.av[0];
        for (int i2 = 0; i2 < this.aw - 1; ++i2) {
            this.av[i2] = this.av[i2 + 1];
        }
        this.av[this.aw - 1] = af2;
        --this.aw;
    }

    public boolean isOnWalkableTile() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        boolean bl = false;
        boolean bl2 = false;
        if (this.checkIsLargeUnit()) {
            bl = true;
        }
        l2.bL.a(this.eo, this.ep);
        int n2 = l2.bL.scrollPixelX;
        int n3 = l2.bL.scrollPixelY;
        if (l2.bU.a(this.h(), n2, n3) && !l2.bU.b(this.h(), n2, n3)) {
            bl = true;
            bl2 = true;
        }
        return bl;
    }

    public void a(float f2, float f3, int n2, boolean bl, boolean bl2) {
        boolean bl3;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.pathfinding.PathFinder l3 = l2.bU;
        com.corrodinggames.rts.game.map.MapEngine b2 = l2.bL;
        this.cK = true;
        boolean bl4 = false;
        boolean bl5 = false;
        if (this.checkIsLargeUnit()) {
            bl4 = true;
        }
        b2.a(this.eo, this.ep);
        int n3 = b2.scrollPixelX;
        int n4 = b2.scrollPixelY;
        if (l3.a(this.h(), n3, n4) && !l3.b(this.h(), n3, n4)) {
            bl4 = true;
            bl5 = true;
        }
        if (f2 != this.o || this.p != f3) {
            this.q = 0;
        }
        this.o = f2;
        this.p = f3;
        if (bl4) {
            this.u = false;
            this.aw = 0;
            this.pathCost = null;
            float f4 = b2.a(f2);
            float f5 = b2.b(f3);
            if (bl5) {
                float f6 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eo, this.ep, f4, f5);
                float f7 = GameUtils.b(this.eo, this.ep, f4, f5);
                if (f7 > 60.0f) {
                    f7 = 60.0f;
                    this.u = true;
                    if (this.s > 10.0f) {
                        this.s = 10.0f;
                    }
                }
                f4 = this.eo + GameUtils.cosFast(f6) * f7;
                f5 = this.ep + GameUtils.sinFast(f6) * f7;
            }
            this.a(this.aw, f4, f5);
            ++this.aw;
            this.v = this.aw;
            return;
        }
        int n5 = 1;
        int n6 = 80;
        int n7 = 0;
        if (bl) {
            n7 = 3;
        }
        if (bl3 = PathfindingHelper.a(this.h(), this.eo, this.ep, f2, f3, n6, n7, n5)) {
            this.u = false;
            this.aw = 0;
            this.pathCost = null;
            float f8 = b2.a(f2);
            float f9 = b2.b(f3);
            float f10 = this.eo;
            float f11 = this.ep;
            float f12 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eo, this.ep, f8, f9);
            float f13 = GameUtils.b(this.eo, this.ep, f8, f9);
            float f14 = GameUtils.cosFast(f12);
            float f15 = GameUtils.sinFast(f12);
            float f16 = 20.0f;
            float f17 = 0.05f;
            int n8 = (int)(f13 * 0.05f - 1.0f);
            int n9 = 1;
            if (n8 < 4) {
                n9 = 0;
            }
            for (int i2 = 0; i2 < n8; ++i2) {
                f10 += f14 * 20.0f;
                f11 += f15 * 20.0f;
                if (n9 > 0) {
                    --n9;
                    continue;
                }
                this.a(this.aw, f10, f11);
                ++this.aw;
                if (this.aw < 119) continue;
                this.u = true;
                break;
            }
            if (!this.u) {
                if (this.aw < 119) {
                    this.a(this.aw, f8, f9);
                    ++this.aw;
                } else {
                    this.u = true;
                }
            }
            this.v = this.aw;
            return;
        }
        com.corrodinggames.rts.gameFramework.ProjectileManager ab2 = null;  // 02b gameFramework.ab
        boolean bl6 = false;
        WeaponAction au2 = this.getFirstWaypoint();
        if (au2 == null || (ab2 = au2.i) == null) {
            // empty if block
        }
        if (ab2 != null && ab2.g != null) {
            com.corrodinggames.rts.gameFramework.CommandPathPart d2 = null;
            float f18 = 3600.0f;
            for (Object d3o : ab2.g) {
                com.corrodinggames.rts.gameFramework.CommandPathPart d3 = (com.corrodinggames.rts.gameFramework.CommandPathPart) d3o;  // 02b ab.g = utility.m 閻庢鍣ｇ紓姘?
                float f19;
                bl6 = true;
                if (d3.a == null || d3.a.a() == null || GameUtils.c(d3.e - f2) > 10.0f || GameUtils.c(d3.f - f3) > 10.0f || d3.g + 180 < l2.bx || (Object) d3.h != (Object) this.h() || !((f19 = GameUtils.a(this.eo, this.ep, d3.c, d3.d)) < f18)) continue;
                f19 = f18;
                d2 = d3;
            }
            if (d2 != null) {
                this.aU = d2.a;
                return;
            }
        }
        if (L && n2 > 2) {
            n2 = 2;
        }
        boolean bl7 = true;
        this.aU = this.a(f2, f3, n2, bl, bl7, bl2);
    }

    public com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator a(float f2, float f3, int n2, boolean bl, boolean bl2, boolean bl3) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.pathfinding.PathFinder l3 = l2.bU;
        com.corrodinggames.rts.game.map.MapEngine b2 = l2.bL;
        com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator k2 = l3.a(bl2);
        b2.a(this.eo, this.ep);
        boolean bl4 = false;
        if (this.bb() || this.ci) {
            bl4 = true;
        }
        k2.a(this.h(), (short)b2.scrollPixelX, (short)b2.scrollPixelY, Float.valueOf(this.cg), bl4);
        b2.a(f2, f3);
        k2.a((short)b2.scrollPixelX, (short)b2.scrollPixelY, (short)n2);
        k2.p = bl;
        k2.q = this.bh();
        k2.r = bl3;
        boolean bl5 = this.cK;
        this.cK = true;
        if (bl2 && k2.b()) {
            Iterator iterator = aV.iterator();
            while (iterator.hasNext()) {
                com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator k3 = (com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator)iterator.next();
                if (k3.g + 60 < l2.bx) {
                    iterator.remove();
                    continue;
                }
                if (!k3.a(k2)) continue;
                return k3;
            }
        }
        l3.a(k2, bl2);
        this.cK = bl5;
        if (bl2 && k2.b()) {
            aV.add(k2);
        }
        return k2;
    }

    void b(GlobalState l2) {
        if (this.aU != null) {
            com.corrodinggames.rts.game.map.MapEngine b2 = l2.bL;
            LinkedList linkedList = this.aU.a();
            if (linkedList != null) {
                this.pathCost = this.aU.a(this);
                com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator k2 = this.aU;
                this.aw = 0;
                this.u = false;
                for (com.corrodinggames.rts.gameFramework.pathfinding.PathNode p2 : (java.util.Collection<com.corrodinggames.rts.gameFramework.pathfinding.PathNode>) (java.util.Collection) linkedList) {
                    b2.a(p2.a, p2.b);
                    float f2 = b2.scrollPixelX + b2.selectedTileX;
                    float f3 = b2.scrollPixelY + b2.selectedTileY;
                    this.a(this.aw, f2, f3);
                    ++this.aw;
                    if (this.aw < 120) continue;
                    this.u = true;
                    break;
                }
                if (this.aw == 1) {
                    this.q = (byte)(this.q + 1);
                }
                boolean bl = true;
                boolean bl2 = false;
                if (linkedList.size() != 0) {
                    b2.a(this.o, this.p);
                    if (!this.u && ((com.corrodinggames.rts.gameFramework.pathfinding.PathNode) linkedList.getLast()).a == b2.scrollPixelX && ((com.corrodinggames.rts.gameFramework.pathfinding.PathNode) linkedList.getLast()).b == b2.scrollPixelY) {
                        bl2 = true;
                    }
                }
                if (bl2) {
                    if (!bl) {
                        if (this.aw < 120) {
                            this.a(this.aw, this.o, this.p);
                            ++this.aw;
                        }
                    } else {
                        if (this.aw == 0) {
                            ++this.aw;
                        }
                        this.a(this.aw - 1, this.o, this.p);
                    }
                }
                this.aU = null;
                if (this.aw > 120) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("activePathCount>maxPathNodes: activePathCount:" + this.aw);
                    this.aw = 120;
                }
                this.v = this.aw;
            }
        }
    }

    public long computePathHash() {
        long l2 = 0L;
        for (int i2 = 0; i2 < this.aw; ++i2) {
            UnitTransform af2 = this.av[i2];
            if (af2 == null) continue;
            l2 += (long)Float.floatToRawIntBits(af2.a);
            l2 += (long)Float.floatToRawIntBits(af2.b);
        }
        return l2;
    }

    UnitTransform o(int n2) {
        if (this.pathCost != null) {
            if (n2 == 0) {
                return this.aE();
            }
            return this.aF();
        }
        if (n2 >= this.aw) {
            return null;
        }
        return this.av[n2];
    }


    public void d(float f2) {
        super.d(f2);
    }

    public float getBuildProgressCap() {
        return 1.0f;
    }

    public Paint composeRenderPaint() {
        PorterDuffColorFilter porterDuffColorFilter = null;
        int n2 = -1;
        if (this.eq < -0.3f) {
            int bl = this.ensurePathNodeCapacity(this.eq);
            n2 = Color.a(bl, 255, 255, 255);
        } else {
            n2 = -1;
        }
        if (this.cm < 1.0f && this.cm < this.aM()) {
            float f2 = this.cm / this.aM() * 220.0f;
            n2 = Color.a((int)(20.0f + f2), 140, 255, 140);
            porterDuffColorFilter = buildProgressFilter;
        }
        if (this.cp) {
            if (this.cs) {
                n2 = Color.a(200, 20, 255, 20);
                porterDuffColorFilter = validSelectionFilter;
            }
            if (this.ct) {
                n2 = Color.a(200, 255, 20, 20);
                porterDuffColorFilter = invalidSelectionFilter;
            }
            if (this.cq) {
                n2 = Color.a(50, 70, 70, 245);
                porterDuffColorFilter = waypointFilter;
                if (this.ct) {
                    n2 = Color.a(50, 255, 20, 20);
                    porterDuffColorFilter = invalidSelectionFilter;
                }
            }
            if (this.cr) {
                n2 = Color.a(150, 100, 100, 100);
            }
        }
        boolean bl = this.shouldAntiAlias();
        return this.a(n2, porterDuffColorFilter, bl);
    }

    public boolean shouldAntiAlias() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        boolean bl = l2.bQ.renderAntiAlias;
        if (!this.dk()) {
            bl = false;
            float f2 = l2.cX;
            if (f2 < 1.0f) {
                bl = true;
            }
        }
        if (this.co) {
            bl = UnitRegistry.ag;
        }
        return bl;
    }


    public boolean c(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        TextureManagerInterface y2 = l2.bO;
        Paint paint = this.aN();
        float f3 = this.cD();
        if (this.ew) {
            PointF pointF = this.cY();
            float f4 = this.eo + pointF.a - l2.cw;
            float f5 = this.ep + pointF.b - l2.cx - this.eq;
            this.aQ();
            if (f3 != 1.0f) {
                y2.k();
                y2.a(f3, f3, f4, f5);
            }
            y2.a(this.M,  f4,  f5,  this.getSensorRange(false) - 90.0f,  paint);  // 02b m/y.a(e,float,float,float,Paint) (D 婵炴垶鎹囩紓姘额敋閵堝瑙?v19.133f8)  // 02b m.y.a(m.e,ff,f,f,Paint) = 03 D
            if (f3 != 1.0f) {
                y2.l();
            }
        } else {
            PointF pointF = this.cY();
            RectF rectF = this.cF();
            float f6 = pointF.a;
            float f7 = pointF.b - this.eq;
            rectF.a += f6;
            rectF.b += f7;
            rectF.c += f6;
            rectF.d += f7;
            Rect rect = this.a_(false);
            float f8 = (rectF.a + rectF.c) * 0.5f;
            float f9 = (rectF.b + rectF.d) * 0.5f;
            y2.k();
            this.aQ();
            if (f3 != 1.0f) {
                y2.a(f3, f3, f8, f9);
            }
            y2.a(this.getSensorRange(false), f8, f9);
            y2.loadImageFromResource(this.M, rect, rectF, paint);  // 02b m.y.a(m.e,Rect,RectF,Paint)
            y2.l();
        }
        return true;
    }

    public boolean isAboveGround() {
        return this.eq > 0.0f && this.cm >= 1.0f && !this.cq;
    }

    public PointF getMuzzleOffset() {
        be.a(this.getTurretWorldPos(), this.getRecoilDistance());
        return reusableMuzzleOffset;
    }

    public float getTurretWorldPos() {
        return 0.0f;
    }

    public float getRecoilDistance() {
        return 0.0f;
    }

    public boolean renderAlternateIcon() {
        if (this.N != null && this.isAboveGround()) {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            if (!l2.de && this.cj < 18.0f && (double)this.eq < 0.5) {
                return true;
            }
            if (!l2.df && this.cj < 28.0f && this.eq < 5.0f) {
                return true;
            }
            PointF pointF = this.getMuzzleOffset();
            float f2 = this.eo + pointF.a - l2.cw;
            float f3 = this.ep + pointF.b - l2.cx;
            float f4 = this.cD();
            TextureManagerInterface y2 = l2.bO;
            if (f4 != 1.0f) {
                y2.k();
                y2.a(f4, f4, f2, f3);
            }
            if (this.cG()) {
                Rect rect = this.a_(true);
                RectF rectF = dB;
                rectF.a(f2 - this.eu, f3 - this.ev, f2 + this.eu, f3 + this.ev);
                y2.k();
                y2.a(this.getSensorRange(true), f2, f3);
                y2.loadImageFromResource(this.N, rect, rectF, this.R());  // 02b m.y.a(m.e,Rect,RectF,Paint)
                y2.l();
            } else {
                y2.a(this.N,  f2,  f3,  this.getSensorRange(true) - 90.0f,  this.R());  // 02b m/y.a(e,float,float,float,Paint) (D 婵炴垶鎹囩紓姘额敋閵堝瑙?v19.133f8)  // 02b m.y.a(m.e,ff,f,f,Paint) = 03 D
            }
            if (f4 != 1.0f) {
                y2.l();
            }
            return true;
        }
        return false;
    }


    public boolean s_() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return RectF.a(l2.cM, this.cE());
    }

    public abstract boolean I();

    public boolean isUsable() {
        UnitTrait n2 = this.dn();
        if (n2 != null && !n2.O) {
            return false;
        }
        return this.I();
    }

    public boolean aS() {
        return this.isUsable();
    }

    public boolean canFaceTarget() {
        return true;
    }

    public int getGroupSizeLimit() {
        return -1;
    }

    public float getAttackRangeIncludingTarget(UnitInstance am2) {
        if (this.aV() && am2 != null) {
            return this.m() + this.cj + am2.cj;
        }
        return this.m();
    }

    public float getBuildRange() {
        return this.m();
    }

    public float getBuildRangeIncludingTarget(UnitInstance am2) {  // 02b y.p(am)
        if (this.aV() && am2 != null) {
            return this.aU() + this.cj + am2.cj;
        }
        return this.aU();
    }

    public int getApproachGridCells(UnitInstance am2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        int n2 = 0;
        float f2 = this.getBuildRangeIncludingTarget(am2);
        if (f2 > 58.0f) {
            n2 = (int)((f2 - 41.0f) / ((float)l2.bL.tilePixelWidth * 1.414f));
        }
        return n2;
    }

    // v19.133f8: 闂佸憡甯炴繛鈧繛鍛叄閻涱噣寮拌箛锝嗙秿 getMaxMoveDistance() 闂?02b y 闂佸搫鍟版慨鐢割敆濠靛绠柛濠勫枙閺?(5 闂佺娉涢埀顒傚枙閺? m/b(int)/A/c(int)/z); 闁荤姴顑呴崯浼村极閵堝鍊烽柣鐔告緲閸ゆ帡鏌￠埀?m()

    public boolean isBuildingPlacer() {
        return false;
    }

    public abstract float b(int var1);

    public float getApproachGridCells(int n2) {
        return 0.0f;
    }

    public void clampTurretAngles() {
        int n2 = this.bl();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (i2 >= this.cL.length) continue;
            UnitTurret ap2 = this.cL[i2];
            if (!(ap2.shootCooldown > this.b(i2))) continue;
            ap2.shootCooldown = this.b(i2);
        }
    }

    public ArrayList getTurretMountData() {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        if (this.l()) {  // 02b y.aX(): this.l()
            int n2 = this.bl();
            for (int i2 = 0; i2 < n2; ++i2) {
                float f2 = this.getApproachGridCells(i2);
                if (f2 == 0.0f) continue;
                float f3 = this.b(i2);
                if (f3 == 9000.0f) {
                    f3 = 0.0f;
                }
                boolean bl = false;
                for (ResourceRate aa2 : (java.util.Collection<ResourceRate>) (java.util.Collection) arrayList) {
                    if (aa2.productionRate != f2 || aa2.consumptionRate != f3 && f3 != 0.0f && aa2.consumptionRate != 0.0f) continue;
                    ++aa2.resourceRef;
                    if (aa2.consumptionRate == 0.0f) {
                        aa2.consumptionRate = f3;
                    }
                    bl = true;
                    break;
                }
                if (bl) continue;
                ResourceRate aa3 = new ResourceRate();
                aa3.productionRate = f2;
                aa3.consumptionRate = f3;
                aa3.storageCapacity = this.getTurretRange(i2);
                arrayList.add(aa3);
            }
        }
        return arrayList;
    }

    public boolean isTurretMountAvailable(int n2) {
        return true;
    }

    public float getTurretRange(int n2) {
        return 0.0f;
    }

    public boolean isTurretChargeable(int n2) {
        return false;
    }

    public float getReloadRandomDelay(int n2) {
        return 0.0f;
    }

    public float getReloadProgressRate(int n2) {
        return 4.0f;
    }

    public boolean isTurretAimedAtTarget(int n2) {
        int n3 = this.v(n2);
        if (n3 == -1) {
            return this.cL[n2].hasLimitedArc;
        }
        return this.cL[n3].hasLimitedArc;
    }

    public int v(int n2) {
        return -1;
    }

    public abstract float A();

    public float getTurretBaseAngle() {
        return -1.0f;
    }

    public abstract float c(int var1);

    public float getTurretAngularAccel(int n2) {
        return -1.0f;
    }

    public float getTurretTurnArc(int n2) {
        return 5.0f;
    }

    public boolean getTurretAngularDecel(UnitInstance am2) {  // 02b am.y(am): 闂佽　鍋撻悹鍥ㄥ絻濮ｅ﹪鏌￠崟闈涚仩闁诡垯绶氬畷锝夘敍濠垫劖鏂€
        boolean bl = false;
        boolean bl2 = am2.g() > 0.0f;
        if (bl2) {
            bl = true;
        }
        return bl;
    }

    public float getTurretAngularDecel(int n2) {
        return this.getTurretAngularAccel(n2);
    }

    public boolean getMuzzlePoint() {
        return false;
    }

    public boolean isAtFinalPathNode() {
        return false;
    }

    public abstract float z();

    public float getSlopeFactor() {
        return 1.0f;
    }

    public float getPathSlopeFactor() {
        return 1.0f;
    }

    public boolean isNearMaxSpeed() {
        return this.bc() > 0.95f;
    }

    public float getSpeedFraction() {
        return 0.6f;
    }


    public float bd() {
        return 0.0f;
    }

    public PathResult be() {
        return PathResult.a;  // v19.113n: 02b y:4925 return b.a
    }

    public boolean canAutoRepair() {
        return true;
    }

    public boolean canShowHealthBar() {
        return true;
    }

    public int getPathingDirectionFlags() {
        return 0;
    }

    public float getTurretFacingAngle() {
        return 99.0f;
    }

    public float getBarrelEndPoint() {
        return 99.0f;
    }

    public boolean isHoverVehicle() {
        return false;
    }

    public boolean hasReverseMovement() {
        return false;
    }

    public boolean b(int n2, float f2) {
        return true;
    }

    public abstract void a(UnitInstance var1, int var2);

    public boolean hasCustomMovement() {
        return false;
    }


    // v19.112 闁荤偞绋忛崕閬嶅矗?(02b y.j(float): 闁荤姳绀佹晶浠嬫偪閸℃稑绀傞柕濞炬櫅閸斻儵鏌ｉ幇闈涙灈妞ゃ垺鐟ч幉鎾箳閹存繍鍞?= var1 + B(i))
    public void j(float var1) {
        int n2 = this.bl();
        for (int n3 = 0; n3 < n2; ++n3) {
            this.cL[n3].turretAngle = var1 + this.B(n3);  // 02b ap.a = turretAngle
        }
    }

    public int bl() {
        return 1;
    }

    public boolean shouldSyncTurretRotation() {
        return true;
    }

    public float getBarrelLength(int n2) {
        return 0.0f;
    }

    public float z(int n2) {
        return 99999.0f;
    }

    public float A(int n2) {
        return -1.0f;
    }

    public float getTurretBaseAngle(int n2) {
        return 0.0f;
    }

    public float getTurretFacingAngle(int n2) {
        if (this.ci && this.bb()) {
            return this.cg + 180.0f;
        }
        return this.cg;
    }

    public com.corrodinggames.rts.gameFramework.utility.ai getCurrentBarrelEnd() {  // 02b y.bn() 闁哄鏅滈弻銊ッ?utility.ai
        int n2 = this.aT();
        if (n2 == -1) {
            return this.getBarrelEndPoint(0);
        }
        return this.getBarrelEndPoint(n2);
    }

    public com.corrodinggames.rts.gameFramework.utility.ai getBarrelEndPoint(int n2) {  // 02b y.D(int) 闁哄鏅滈弻銊ッ?utility.ai
        bf.a(this.getMuzzlePoint(n2));
        return bf;
    }

    public PointF getMuzzlePoint(int n2) {
        UnitTurret ap2 = this.cL[n2];
        float f2 = this.getBarrelLength(n2);
        float f3 = this.getMuzzlePoint() ? this.cg : ap2.turretAngle;
        PointF pointF = this.getTurretWorldPos(n2);
        float f4 = pointF.a + GameUtils.cosFast(f3) * f2;
        float f5 = pointF.b + GameUtils.sinFast(f3) * f2;
        bg.a(f4, f5);
        return bg;
    }

    public com.corrodinggames.rts.gameFramework.utility.ai isAboveGround(int n2) {  // 02b y.F(int) 闁哄鏅滈弻銊ッ?utility.ai (闂佺粯鍔﹂崰姘额敇閸濄儰鐒婇柛鎾楀奔绱撻梺绋款儏缁绘帊绨?
        bi.a(this.getTurretWorldPos(n2));
        bi.c = 0.0f;  // 02b ai.c  // 02b y.L5018: bi.c (闂佸搫鐗滈崜娑氭偖椤愶附顥堟繛鍡樺姀閸嬫挻鎷呴崫銉︽喕濠?
        return bi;
    }

    public PointF getTurretWorldPos(int n2) {
        UnitTurret ap2 = this.cL[n2];
        float f2 = this.eo;
        float f3 = this.ep;
        float f4 = this.getRecoilDistance(n2);
        if (ap2.shootCooldown != 0.0f && f4 != 0.0f) {
            float f5 = this.I(n2);
            float f6 = this.J(n2);
            float f7 = 0.0f;
            float f8 = this.b(n2) - ap2.shootCooldown;
            if (f8 < f5) {
                f7 = f8 / f5 * f4;
            } else if (f8 < f6 + f5) {
                f7 = f4 - (f8 - f5) / f6 * f4;
            }
            if (f7 != 0.0f) {
                f2 += GameUtils.cosFast(ap2.turretAngle) * f7;
                f3 += GameUtils.sinFast(ap2.turretAngle) * f7;
            }
        }
        bh.a(f2, f3);
        return bh;
    }

    public float getRecoilDistance(int n2) {
        return 0.0f;
    }

    public float I(int n2) {
        return 4.0f;
    }

    public float J(int n2) {
        return 6.0f;
    }

    public PointF K(int n2) {
        PointF pointF = bj;
        pointF.a(0.0f, 0.0f);
        UnitTurret ap2 = this.cL[n2];
        pointF.a += ap2.minAngleLimit;
        pointF.b += ap2.maxAngleLimit;
        return pointF;
    }

    public float getTurretSpread(int n2) {
        return 0.6f;
    }

    public void resetMountOffsets(int n2) {
        if (n2 == -1) {
            int n3 = this.bl();
            for (int i2 = 0; i2 < n3; ++i2) {
                this.M(i2);
            }
            return;
        }
        UnitTurret ap2 = this.cL[n2];
        ap2.minAngleLimit = 0.0f;
        ap2.maxAngleLimit = 0.0f;
        if (this.R != null && this.L(n2) != 0.0f) {
            float f2 = this.R.cj * this.L(n2);
            ap2.minAngleLimit += (float)GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, (int)(-f2), (int)f2, 1 + n2);  // 02b L5080: f.a(this,...) cast 消歧  // 02b L5080: f.a(this,...)
            ap2.maxAngleLimit += (float)GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, (int)(-f2), (int)f2, 2 + n2);  // 02b L5081  // 02b L5081
        }
    }

    public void a(UnitState ab2) {
        this.a(ab2, true);
    }

    public void a(UnitState ab2, boolean bl) {
        HUDElement e2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (ab2 == UnitState.h) {
            l2.bM.a(SoundRegistry.p, 0.8f, this.eo, this.ep);
            l2.bR.a(this.eo, this.ep, this.eq);
            l2.bR.b(DrawLayer.e);
            e2 = l2.bR.c(this.eo, this.ep, this.eq, -1127220);
            if (e2 != null) {
                e2.G = 0.2f;
                e2.F = 2.0f;
                e2.ar = (short)2;
                e2.W = e2.V = 45.0f;
                e2.U = 0.0f;
            }
        } else if (ab2 == UnitState.d || ab2 == UnitState.f || ab2 == UnitState.g) {
            l2.bM.a(SoundRegistry.p, 0.8f, this.eo, this.ep);
            l2.bR.a(this.eo, this.ep, this.eq);
        } else if (ab2 == UnitState.a) {
            float f2 = 1.0f + GameUtils.c(-0.07f, 0.07f);
            l2.bM.a(SoundRegistry.o, 0.4f, f2, this.eo, this.ep);
            l2.bR.b(this.eo, this.ep, this.eq);
        } else if (ab2 == UnitState.e) {
            float f3 = 1.0f + GameUtils.c(-0.07f, 0.07f);
            l2.bM.a(SoundRegistry.o, 0.8f, f3, this.eo, this.ep);
            l2.bR.b(this.eo, this.ep, this.eq);
            l2.bR.b(DrawLayer.e);
            HUDElement e3 = l2.bR.c(this.eo, this.ep, this.eq, -1127220);
            if (e3 != null) {
                e3.G = 0.2f;
                e3.F = 2.0f;
                e3.ar = (short)2;
                e3.W = e3.V = 45.0f;
                e3.U = 0.0f;
            }
        } else {
            float f4 = 1.0f + GameUtils.c(-0.07f, 0.07f);
            l2.bM.a(SoundRegistry.o, 0.8f, f4, this.eo, this.ep);
            l2.bR.b(this.eo, this.ep, this.eq);
        }
        if (ab2 != UnitState.a) {
            if (ab2 != UnitState.g && (e2 = l2.bR.d(this.eo, this.ep, this.eq, 0)) != null) {
                e2.E = 0.9f;
            }
            if (bl) {
                if (!this.bO()) {
                    this.bo();
                }
                if (ab2 != UnitState.g && !this.cK()) {
                    DrawEffect.a(this.eo, this.ep);
                    DrawEffect.b(this.eo, this.ep);
                    this.bq();
                }
            }
        }
    }

    public void spawnDeathParticles() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f2 = 1.0f;
        float f3 = 1.0f;
        int n2 = this.bp();
        if (n2 >= 10) {
            f2 = 1.2f;
            f3 = 1.4f;
        }
        if (n2 >= 20) {
            f2 = 1.5f;
            f3 = 1.7f;
        }
        if (this.eq > -1.0f) {
            for (int i2 = 0; i2 < n2; ++i2) {
                l2.bR.b(this.eo, this.ep, this.eq, f2, f3);
            }
        }
    }

    public int getDeathParticleCount() {
        if (this.dd()) {
            return 8;
        }
        if (this.isFactoryBuilding()) {
            return 7;
        }
        return 4;
    }

    public void spawnGroundCrater() {
        if (!this.cK()) {
            Projectile.a(this.eo, this.ep);
        }
    }

    public int isTurretChargeable() {
        return 15;
    }


    public void c(boolean bl) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.cN != null || this.cO != null) {
            return;
        }
        int n2 = this.isTurretChargeable();
        if (n2 > 0) {
            l2.bL.a(this.eo, this.ep, n2, this.player, bl);
        }
    }

    public void cleanupNearbyUnits() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        RectF rectF = new RectF();
        rectF.a(this.getRenderBounds());
        rectF.b *= (float)l2.bL.tilePixelHeight;
        rectF.d *= (float)l2.bL.tilePixelHeight;
        rectF.a *= (float)l2.bL.tilePixelWidth;
        rectF.c *= (float)l2.bL.tilePixelWidth;
        rectF.a(this.eo, this.ep);
        rectF.a(-this.cZ(), -this.da());
        float f2 = 10.0f;
        rectF.b -= f2;
        rectF.d += f2;
        rectF.a -= f2;
        rectF.c += f2;
        DequeList o2 = UnitInstance.getUnitPool();
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) o2) {
            UnitInstance am3;
            if (!(am2 instanceof UnitInstance) || (am3 = am2) == this || !am3.isVisibleTo(rectF)) continue;
            if (am3 instanceof UnitInstance && am3.isDead) {
                am3.a();
            }
            if (!(am3 instanceof TreeDecoration)) continue;
            ((TreeDecoration) am3).k();
        }
    }

    public boolean c(PlayerState n2) {  // 02b y.c(n)
        return this.b(false, n2) == null;
    }

    public boolean a(boolean bl, PlayerState n2) {  // 02b y.a(boolean,n)
        return this.b(bl, n2) == null;
    }

    public String b(boolean bl, PlayerState n2) {  // 02b y.b(boolean,n)
        Object object;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        be be2 = this.r().q();
        if (be2 != null) {
            String string = be2.a(this, this.eo, this.ep);
            if (string != null) {
                return string;
            }
        }
        if (this.r().p()) {
            l2.bL.a(this.eo, this.ep);
            MapLayer mapLayer = l2.bL.e(l2.bL.scrollPixelX, l2.bL.scrollPixelY);
            if (mapLayer == null || !mapLayer.isTileLayer) {
                return "{2}";
            }
        }
        if (!bl && this.a(null, n2)) {
            return "{0}";
        }
        if (!this.r().p()) {
            object = this.getRenderBounds();
            Point point = this.a(l2.bL, bk);
            int n3 = point.a;
            int n4 = point.b;
            UnitTypeHandle as2 = this.r();
            MovementTypeEnum ao2 = as2.o();
            for (int i2 = n3 + ((Rect)object).a; i2 <= n3 + ((Rect)object).c; ++i2) {
                for (int i3 = n4 + ((Rect)object).b; i3 <= n4 + ((Rect)object).d; ++i3) {
                    String string = com.corrodinggames.rts.game.units.commands.BuildSlot.a(this, as2, ao2, i2, i3, false, n2);
                    if (string == null) continue;
                    return string;
                }
            }
        }
        return null;
    }

    public void drawPlacementGrid(int n2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!this.r().p()) {
            Rect rect = this.getRenderBounds();
            Point point = this.a(l2.bL, bl);
            int n3 = point.a;
            int n4 = point.b;
            UnitTypeHandle as2 = this.r();  // 02b as var7 = this.r()
            int n5 = n3 + rect.a;
            int n6 = n4 + rect.b;
            int n7 = n3 + rect.c;
            int n8 = n4 + rect.d;
            if (n2 != -2) {
                l2.bL.a(this, n5, n6, n7, n8, (int)l2.cw, (int)l2.cx, l2.bO, true, n2);
            }
        }
    }

    public boolean isTurretMountAvailable(UnitInstance am2) {
        float f2 = GameUtils.a(this.eo, this.ep, am2.eo, am2.ep);
        float f3 = 9.0f;
        if (!am2.isFactoryBuilding() && (f3 = this.cj + am2.cj) < 11.0f) {
            f3 = 11.0f;
        }
        return f2 < f3 * f3;
    }

    public boolean a(UnitInstance am2, PlayerState n2) {  // 02b y.a(am,n)
        boolean bl = false;
        if (!this.isFactoryBuilding()) {
            bl = true;
        }
        float f2 = this.cj + com.corrodinggames.rts.game.units.custom.ag.p + 10.0f;
        float f3 = this.eo - f2;
        float f4 = this.eo + f2;
        float f5 = this.ep - f2;
        float f6 = this.ep + f2;
        UnitInstance[] amArray = UnitInstance.bE.a();  // 02b u.a();  // 02b am.bE.a()
        int n3 = UnitInstance.bE.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            UnitInstance am3 = amArray[i2];
            float f7 = am3.eo;
            float f8 = am3.ep;
            if (!(f3 <= f7) || !(f7 <= f4) || !(f5 <= f8) || !(f8 <= f6) || am3 == this || !bl && !am3.isFactoryBuilding() || am3.isDead || !this.isTurretMountAvailable(am3) || am3 == am2 || n2 != null && !am3.d(n2)) continue;
            return true;
        }
        return false;
    }

    public strictfp int bp() {  // 02b y.bp()
        return this.dd() ? 8 : (this.bI() ? 7 : 4);
    }

    public strictfp float o(UnitInstance am2) {  // 02b y.java L4754: o(am)
        return this.aV() && am2 != null ? this.m() + this.cj + am2.cj : this.m();
    }

    public strictfp boolean h(UnitInstance am2) {  // 02b y.h(am): 闁荤姷鍎ょ换鍕€栭崶顒€纭€濠电姴鍊荤粣鐐烘煕閹哄鈧牠寮?
        float f2 = GameUtils.a(this.eo, this.ep, am2.eo, am2.ep);
        float f3 = this.o(am2);
        return f2 < f3 * f3;
    }

    public strictfp boolean r(UnitInstance am2) {  // 02b y.r(am): 閻楀牊婀伴崚銈嗘焽
        float f2 = GameUtils.a(this.eo, this.ep, am2.eo, am2.ep);
        float f3 = 9.0f;
        if (!am2.bI()) {
            f3 = this.cj + am2.cj;
            if (f3 < 11.0f) {
                f3 = 11.0f;
            }
        }
        return f2 < f3 * f3;
    }

    public UnitInstance bs() {
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) UnitInstance.bE) {
            if (am2 == this || !(am2 instanceof UnitInstance)) continue;
            UnitType y2 = (UnitType) am2;  // 02b (y)var
            if (y2.isDead || y2.player != this.player || y2.r() != this.r() || !this.t(y2)) continue;  // 02b y.t(am)
            return y2;
        }
        return null;
    }


    public void a() {
        if (this.cO != null) {
            this.bx();
        }
        this.az();
        this.aI();
        super.a();
    }


    public void bt() {
        this.a((UnitInstance) null);
        this.R = null;
        this.az();
        this.aI();
    }


    public void bu() {
        if (this.cO != null) {
            this.bx();
        }
        super.bu();  // 02b y.L5396: super.bu()
    }


    public void bv() {
        super.bv();  // 02b y.L5400: super.bv()
    }


    public int bw() {
        int n2 = 0;
        n2 = n2 * 31 + super.computeUnitHash();
        n2 = n2 * 31 + (int)(this.z() * 100.0f);
        n2 = n2 * 31 + (int)(this.A() * 100.0f);
        n2 = n2 * 31 + (int)(this.m() * 100.0f);
        n2 = n2 * 31 + (int)this.b(0);
        n2 = n2 * 31 + (int)(this.getTurretFacingAngle() * 100.0f);
        return n2;
    }


    PointF m(float f2) {
        PointF pointF = this.removeWaypointAt(f2);
        dE.a(this.eo + pointF.a, this.ep + pointF.b);
        return dE;
    }

    public PointF removeWaypointAt(float f2) {
        float f3 = 0.0f;
        float f4 = 0.0f;
        if (this.I() && this.b == 0.0f) {
            if (this.bi()) {
                f3 = this.cc * f2;
                f4 = this.cd * f2;
            } else if (this.cf != 0.0f) {
                float f5 = this.cg;
                if (this.bj()) {
                    f5 = this.ch;
                }
                float f6 = this.z() * this.cf * f2;
                f3 = GameUtils.cosFast(f5) * f6;
                f4 = GameUtils.sinFast(f5) * f6;
            }
        }
        bm.a(f3, f4);
        return bm;
    }

    public boolean a(PathState ag2) {
        return false;
    }

    public boolean isOnScreen() {
        GlobalState l2 = GlobalState.B();
        return RectF.a(l2.cM, this.getAnimatedScreenBounds());
    }

    public void a(GameAction s2, boolean bl, float f2, float f3) {
    }

    public boolean a(GameAction s2, float f2, float f3) {
        return true;
    }

    public void a(UnitInstance am2, float f2, int n2) {
        this.U = GameUtils.a(this.U, f2);
        if (this.U == 0.0f) {
            this.U = 5.0f;
            if (this.isOnScreen()) {
                com.corrodinggames.rts.gameFramework.utility.ai ai2 = this.bn();  // 02b y.bn() 闁哄鏅滈弻銊ッ?utility.ai
                GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                HUDElement e2 = l2.bR.b(ai2.a, ai2.b, this.eq + ai2.c, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, DrawLayer.b);  // 02b ai.b/c
                if (e2 != null) {
                    float f3 = (float)((double)am2.eo + (-8.0 + Math.random() * 16.0));
                    float f4 = (float)((double)am2.ep + (-8.0 + Math.random() * 16.0));
                    float f5 = com.corrodinggames.rts.gameFramework.GameUtils.a(ai2.a, ai2.b, f3, f4);
                    e2.P = GameUtils.cosFast(f5) * GameUtils.c(2.0f, 4.0f);
                    e2.Q = GameUtils.sinFast(f5) * GameUtils.c(2.0f, 4.0f);
                    e2.ap = 6;
                    e2.W = e2.V = 20.0f;
                    e2.r = true;
                    e2.E = 0.8f;
                    e2.G = 0.2f;
                    e2.F = 1.0f;
                }
            }
        }
    }

    public void b(UnitInstance am2, float f2, int n2) {
        this.U = GameUtils.a(this.U, f2);
        if (this.U == 0.0f) {
            this.U = 5.0f;
            if (this.isOnScreen()) {
                PointF pointF = this.getMuzzlePoint(0);
                GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                HUDElement e2 = l2.bR.b(am2.eo, am2.ep, am2.eq, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, DrawLayer.b);
                if (e2 != null) {
                    float f3 = (float)((double)pointF.a + (-8.0 + Math.random() * 16.0));
                    float f4 = (float)((double)pointF.b + (-8.0 + Math.random() * 16.0));
                    float f5 = com.corrodinggames.rts.gameFramework.GameUtils.a(am2.eo, am2.ep - am2.eq, f3, f4);
                    e2.P = GameUtils.cosFast(f5) * GameUtils.c(2.0f, 4.0f);
                    e2.Q = GameUtils.sinFast(f5) * GameUtils.c(2.0f, 4.0f);
                    e2.ap = 5;
                    e2.W = e2.V = 20.0f;
                    e2.r = true;
                    e2.E = 0.8f;
                    e2.G = 0.2f;
                    e2.F = 1.0f;
                }
            }
        }
    }

    public WaypointTarget a(WeaponAction au2, UnitTypeHandle as2, int n2, float f2, float f3) {
        Object object;
        Object object2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        GameAction s2 = this.a(as2, n2, false);
        if (s2 == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Unit '" + this.r().i() + "' can not build:" + as2.i());
            return bn.a();
        }
        if (!au2.n) {
            if (s2.g(this)) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("Builder '" + this.r().i() + "' tried to build UnitFlag locked building:" + s2.getDisplayString());
                return bn.a();
            }
            if (!s2.getLabel(this) && !s2.u(this)) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("Builder '" + this.r().i() + "' tried to build UnitFlag unavailable building:" + s2.getDisplayString() + " (add isLocked:false to fix)");
                return bn.a();
            }
        }
        if (!as2.k() && !s2.x() && this.player.w() >= this.player.x()) {  // 02b y.L5526: var7.x() / bX.w() / bX.x()
            if (this.player == l2.bs) {
                l2.bS.b(l2.bS.g.al);
            }
            return bn.a();
        }
        UnitInstance am2 = UnitInstance.a(as2);
        if (am2 == null) {
            String string = "{build is null}";
            if (au2.b != null) {
                string = au2.b.i();
            }
            com.corrodinggames.rts.gameFramework.GlobalState.e("Build unit type missing: " + string);
            return bn.a();
        }
        UnitInstance am3 = com.corrodinggames.rts.game.units.commands.BuildSlot.g(as2);
        if (!com.corrodinggames.rts.game.units.custom.resources.CustomActionBase.b(as2.u(), s2.B()) || !com.corrodinggames.rts.game.units.custom.resources.CustomActionBase.b(as2.B(), s2.r_())) {
            am3.bx = s2.B();
            am3.by = s2.r_();
        }
        am3.cm = 0.0f;
        am3.cn = 0.0f;
        l2.bL.b(f2 - am3.getMapOriginX() + 1.0f, f3 - am3.getMapOriginY() + 1.0f);
        am3.eo = (float)l2.bL.scrollPixelX + am3.getMapOriginX();
        am3.ep = (float)l2.bL.scrollPixelY + am3.getMapOriginY();
        am3.f(this.player);
        am3.accept_B(this);
        if (n2 != 1 && am3 instanceof UnitInstance) {
            ((UnitInstance) am3).a(n2);
        }
        am3.cP();
        if (am3 instanceof UnitInstance) {
            object2 = (UnitInstance) am3;
            boolean bl = false;
            object = null;
            if (this.isDieOnConstruct()) {
                object = this;
            } else if (!this.bT && !this.isFactoryBuilding()) {
                object = this;
            }
            if (((UnitType) object2).a((UnitInstance) object, (PlayerState) null)) {  // 02b y var10.a((am)var12, (n)null)
                bl = true;
            }
            if (!bl && !((UnitType) object2).a(true, (PlayerState) null)) {  // 02b y.a(boolean,n)
                bl = true;
            }
            if (bl) {
                am3.a();
                WaypointTarget z2 = bn.a();
                UnitInstance y2 = ((UnitType) am3).bs();  // 02b ((y)var9).bs()
                z2.b = y2;
                z2.d = s2;
                if (y2 == null) {
                    // empty if block
                }
                return z2;
            }
        }
        object2 = s2.B();
        if (au2.n) {
            object2 = CustomActionBase.a;
        }
        if (!((CustomActionBase) object2).c(this)) {  // 02b custom.d.b.c((am)this)
            am3.a();
            WaypointTarget z3 = bn.a();
            this.Q = l2.by;
            if (this.V < 1000.0f) {
                z3.c = true;
                object = com.corrodinggames.rts.gameFramework.effects.GameHUD.a(this.player, am3.eo, am3.ep);
                if (object != null) {
                    ((com.corrodinggames.rts.gameFramework.effects.GameHUD) object).i = true;  // 02b d.a.i 闁诲孩绋掗〃鍡涱敊?
                }
            }
            return z3;
        }
        this.onUnitCommandTarget(am3);
        if (am3 instanceof UnitInstance) {
            UnitType y3 = (UnitType) am3;  // 02b y var17 = (y)var9
            y3.cleanupNearbyUnits();  // 02b y.br()
            if (am3.isFactoryBuilding()) {
                l2.bU.a(y3);
            }
        }
        PlayerState.c(am3);
        WaypointTarget z4 = bn.a();
        z4.a = am3;
        z4.d = s2;
        return z4;
    }

    public strictfp boolean ad() {  // 02b y.ad()
        if (!this.l()) {
            return false;
        }
        UnitTrait unitTrait = this.dn();
        return unitTrait == null || unitTrait.M;
    }

    public strictfp void a(UnitType y2) {  // 02b y.a(y): 闁荤姳绀佹晶浠嬫偪閸℃鍟呴柕澶堝劚閼煎啴姊洪崜褍浜鹃悽?(javap -c 闂備礁褰炲ù鍥儊?
        if (this.ad != null) {
            this.ad.shieldCapacity--;
        }
        this.ad = y2;
        if (y2 != null) {
            this.ad.shieldCapacity++;
        }
    }

    public strictfp boolean b(UnitType y2) {  // 02b y.b(y): 閺佸本鍨滈崚銈嗘焽
        return false;
    }

    public boolean a(UnitType y2, UnitTrait n2) {
        return false;
    }

    public void bx() {
        if (this.cO == null) {
            return;
        }
        if (this.cO.isDead) {
            // empty if block
        }
        if (!this.cO.b(this)) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Deattach failed, forcing deattach. Child:" + this.cB() + " Parent:" + this.cO.cB());
            this.cO = null;
            this.cP = null;
        }
    }

    public UnitTrait setFactoryLink(short s2) {
        return null;
    }

    public static UnitInstance a(UnitType y2, float f2, float f3, float f4, com.corrodinggames.rts.game.units.custom.UnitConfig h2) {  // 02b y.a(y,float,float,float,custom.h)
        if (f4 <= 0.0f) {
            return null;
        }
        UnitType.bo.colorTint = true;
        UnitType.bo.groundOffset = false;
        UnitType.bo.alphaValue = null;  // 02b ac.e = am
        UnitType.bo.rotationAngle = f4 * f4;
        UnitType.bo.scaleValue = h2;  // 02b ac.c = custom.h
        UnitType.bo.animTimer = f2;
        UnitType.bo.decorType = f3;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.cc.a(f2, f3, f4, y2, 0.0f, bo);
        return UnitType.bo.alphaValue;  // 02b ac.e = am
    }

    public CustomActionBase by() {
        return CustomActionBase.a;
    }

    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList bz() {  // v19.115r: javap y.bz()闂佹剚鍋呮慨纾榠lity.m 闂備礁褰炲ù鍥儊? // 02b y.bz() 闁哄鏅滈弻銊ッ?utility.m
        return null;  // 02b: 闁哄鏅滈崝妯兼椤撱垹绀嗘俊銈呭閳?(bq 闁诲孩绋掗〃鍡涱敊瀹€鈧悮鍓ф嫚閼碱兘鍋撻悜鑺ョ叆婵炲棗娴风粔? 缂備胶濮崑鎾绘煕?TODO)
    }

    public boolean bA() {
        return false;
    }

    public int bB() {
        return 0;
    }


    public void bC() {
        CustomActionBase b2 = this.bE();
        CustomActionBase b3 = this.getCustomResourceOverride();
        CustomActionBase b4 = b2 == null ? b3 : (b3 == null ? b2 : CustomActionBase.a(b2, b3));
        if (this.dJ == null && b4 == null) {
            return;
        }
        if (this.dJ != null && b4 != null && this.dJ.b(b4)) {
            return;
        }
        PlayerState.b((UnitInstance) this);
        this.dJ = b4;
        PlayerState.c(this);
    }

    public CustomActionBase getCustomResourceOverride() {  // 闁荤喐娲栧Λ娑樏?UnitInstance (v19.133d)
        return null;
    }

    public CustomActionBase bE() {
        WeaponAction au2;
        UnitInstance am2 = this.X();
        if (am2 != null && (au2 = this.getFirstWaypoint()) != null) {
            float f2;
            CustomActionBase b2;
            if (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.d && am2.cm < 1.0f) {
                b2 = this.getBarrelLength(am2);
                f2 = this.a_(am2) * 60.0f;
                if (b2 != null) {
                    return CustomActionBase.a(b2, -f2);
                }
            }
            if (au2.a == com.corrodinggames.rts.game.units.WeaponTypeEnum.g) {
                if (am2.cm < 1.0f) {
                    b2 = this.getBarrelLength(am2);
                    f2 = this.getReloadProgressRate(am2) * 60.0f;
                    if (b2 != null) {
                        return CustomActionBase.a(b2, f2);
                    }
                } else {
                    boolean bl = this.getTurretAngularDecel(am2);
                    if (bl) {
                        f2 = this.z(am2);
                        CustomActionBase b3 = am2.getResourceProduction();
                        CustomActionBase b4 = am2.getCustomResourceOverride();
                        if (b4 != null) {
                            b3 = b4;
                        }
                        float f3 = f2 * 60.0f;
                        float f4 = f3 / am2.maxHp;
                        return CustomActionBase.a(b3, f4);
                    }
                }
            }
        }
        return null;
    }

    static /* synthetic */ void a(UnitType y2, UnitInstance am2, float f2, boolean bl) {  // 02b y 闂傚倸鐗婇悷锕傚焵椤戣法顦﹂柟顔奸叄楠? a(y,am,float,boolean)
        y2.a(am2, f2, bl);
    }
        static PointF reusableTurretTarget;  // v19.113o auto_align R3a: 闂傚倸鐗婇悷锕傚焵椤戣法顦︽繛纭风磿閹秆呪偓锝庝簴閸嬫捇宕楅崗纭烽獜婵犮垹婀辨晶妤€危閹寸姵鍋橀柕澶涚畱缁?
    static PointF reusablePosition1;  // v19.113o auto_align R3a: 闂傚倸鐗婇悷锕傚焵椤戣法顦︽繛纭风磿閹秆呪偓锝庝簴閸嬫捇宕楅崗纭烽獜婵犮垹婀辨晶妤€危閹寸姵鍋橀柕澶涚畱缁?
    static Point reusableTilePoint;  // v19.113o auto_align R3a: 闂傚倸鐗婇悷锕傚焵椤戣法顦︽繛纭风磿閹秆呪偓锝庝簴閸嬫捇宕楅崗纭烽獜婵犮垹婀辨晶妤€危閹寸姵鍋橀柕澶涚畱缁?
    static PointF reusablePoint4;  // v19.113o auto_align R3a: 闂傚倸鐗婇悷锕傚焵椤戣法顦︽繛纭风磿閹秆呪偓锝庝簴閸嬫捇宕楅崗纭烽獜婵犮垹婀辨晶妤€危閹寸姵鍋橀柕澶涚畱缁?
    static WaypointTarget reusableBuildResult;  // v19.113o auto_align R3a: 闂傚倸鐗婇悷锕傚焵椤戣法顦︽繛纭风磿閹秆呪偓锝庝簴閸嬫捇宕楅崗纭烽獜婵犮垹婀辨晶妤€危閹寸姵鍋橀柕澶涚畱缁?
    static DecorType1 reusableAcConstants;  // v19.113o auto_align R3a: 闂傚倸鐗婇悷锕傚焵椤戣法顦︽繛纭风磿閹秆呪偓锝庝簴閸嬫捇宕楅崗纭烽獜婵犮垹婀辨晶妤€危閹寸姵鍋橀柕澶涚畱缁?
    
    static {
        aE.a(128, 255, 255, 255);
        aE.o();
        aF = new com.corrodinggames.rts.gameFramework.rendering.UniquePaint();  // 02b y.aF = new m.ag()
        aF.a(aE);
        aF.a(true);
        aF.d(true);
        aF.b(true);
        aF.o();
        aG = new PointF();
        B = new Paint();
        D = a(false);  // 02b y.D = a(false)
        E = a(true);  // 02b y.E = a(true)
        aH = new UnitType$1();  // 02b y.aH = new y$1()
        unitTypeRegistry = new com.corrodinggames.rts.gameFramework.utility.UnitInstanceList();  // 02b y.aM = new utility.u()
        aP = new UnitAttachment();
        aQ = new DecorType2(true);  // 02b y.aQ = new ae(true)
        aR = new DecorType2(false);  // 02b y.aR = new ae(false)
        aS = new DecorType3(true);
        aT = new DecorType3(false);  // 02b y.aT = new ah(false)
        aV = new com.corrodinggames.rts.gameFramework.utility.DequeList();  // 02b y.aV = new utility.m()  // 02b y.aV = new utility.m()
                reusablePathNode = new UnitTransform();
        buildProgressFilter = new PorterDuffColorFilter(Color.a(200, 255, 200), PorterDuff.Mode.MULTIPLY);
        validSelectionFilter = new PorterDuffColorFilter(Color.a(70, 255, 70), PorterDuff.Mode.MULTIPLY);
        invalidSelectionFilter = new PorterDuffColorFilter(Color.a(255, 40, 40), PorterDuff.Mode.MULTIPLY);
        waypointFilter = new PorterDuffColorFilter(Color.a(120, 120, 255), PorterDuff.Mode.MULTIPLY);
        paintWithValidFilter = PathfindingUtils.b();
        paintWithInvalidFilter = PathfindingUtils.b();
        paintWithWaypointFilter = PathfindingUtils.b();
        reusableMuzzleOffset = new PointF();
        bf = new com.corrodinggames.rts.gameFramework.utility.ai();  // 02b y.bf = new ai()
        bg = new PointF();  // 02b y.java L5803
        reusableTurretTarget = new PointF();
        bh = new PointF();  // 02b y.java L5804
        reusablePosition1 = new PointF();
        bi = new com.corrodinggames.rts.gameFramework.utility.ai();  // 02b y.bi = new ai()
        bj = new PointF();
        bk = new Point();  // 02b y.java L5807
        reusableTilePoint = new Point();
        bl = new Point();
        bm = new PointF();  // 02b y.java L5809
        reusablePoint4 = new PointF();
        reusableBuildResult = new WaypointTarget();
        bo = new DecorType1();  // 02b y.java L5811: bo = new ac()
        reusableAcConstants = new DecorType1();
        bq = new com.corrodinggames.rts.gameFramework.utility.DequeList();  // 02b y.bq = new utility.m()
    }


    // v19.111 闂佸憡甯掑Λ娆愮珶閹烘梹鍋橀柕澶堝劚瀵?(缂備焦顨愮粻鎴ｃ亹鐠恒劉鍋撳☉娅亜锕㈤鍫濈畱鐟滄垹鎹㈤崘顭戠叆? 闂?)


    public strictfp void S() {
    this.M = this.d();
    this.N = this.k();
    }



    public strictfp boolean i(UnitInstance am2) {  // 02b y.java L3578: i(am) 单位类型碰撞检查 (补建, PathfindingHelper.b L182 链)
        int n2 = this.bl();
        for (int n3 = 0; n3 < n2; ++n3) {
            boolean bl = false;
            boolean bl2 = false;
            if (this.r(n3) && this.a(n3, am2, false, false)) {
                int n4 = this.v(n3);
                if (n4 == -1 || this.a(n4, am2, false, false)) {
                    return true;
                }
            }
        }
        return false;
    }

    public strictfp float h(int var1) {  // 02b y.java L455
        return 0.0f;
    }

    public strictfp float i(int var1) {
    return 0.0F;
    }



    public strictfp com.corrodinggames.rts.gameFramework.rendering.Texture T() {  // 02b y.T(): m.e
    return null;
    }






    public strictfp float f(am var1) {
    float var2 = 5.1F;
    return 0.001F * var2;
    }


    public strictfp boolean Z() {
    return this.R != null;
    }



    public strictfp boolean ac() {
    return this.bl() > 1;
    }



    public strictfp boolean ae() {
    return false;
    }



    public strictfp boolean ag() {
    return true;
    }



    public strictfp boolean ah() {
    return true;
    }






    public strictfp boolean isDieOnConstruct() {
    return false;
    }



    public strictfp float am() {
    return 0.0F;
    }



    public strictfp boolean an() {
    return this.P == UnitFlag.a || this.P == UnitFlag.e || this.P == UnitFlag.f;  // 02b a.a/a.e/a.f
    }



    public com.corrodinggames.rts.game.units.custom.animation.UnitTrait a(short n2) {
        return null;
    }

    public strictfp WeaponAction ar() {
    return this.f == 0?null:this.g[0];
    }



    public strictfp WeaponAction as() {  // 02b au = WeaponAction
    return this.f <= 1?null:this.g[1];
    }



    public strictfp WeaponAction at() {  // 02b au = WeaponAction
    return this.f == 0?null:this.g[this.f - 1];
    }



    public strictfp int av() {
    return this.f;
    }



    public strictfp void aH() {
    this.aw = 0;
    this.u = false;
    this.v = 0;
    this.s = 0.0F;
    this.W = 0.0F;
    this.X = 0.0F;
    this.q = 0;
    }



    public strictfp float aM() {
    return 1.0F;
    }



    public strictfp float p(int var1) {
    return 1.0F;
    }



    public strictfp float G() {
    return 0.0F;
    }



    public strictfp float H() {
    return 0.0F;
    }



    public strictfp boolean b_() {
    return true;
    }



    public strictfp int aT() {
    return -1;
    }






    public strictfp float aU() {
    return this.m();
    }



    public strictfp boolean r(int var1) {
    return true;
    }



    public strictfp float e(int var1) {
    return 0.0F;
    }



    public strictfp boolean s(int var1) {
    return false;
    }



    public strictfp float t(int var1) {
    return 0.0F;
    }






    public strictfp float B() {
    return -1.0F;
    }



    public strictfp float w(int var1) {
    return -1.0F;
    }



    public strictfp float x(int var1) {
    return 5.0F;
    }



    public strictfp boolean E() {
    return false;
    }



    public strictfp boolean aY() {
    return false;
    }



    public strictfp float aZ() {
    return 1.0F;
    }



    public strictfp float ba() {
    return 1.0F;
    }



    public strictfp float bc() {
    return 0.6F;
    }



    public strictfp boolean bf() {
    return true;
    }



    public strictfp boolean bg() {
    return true;
    }



    public strictfp int bh() {
    return 0;
    }



    public strictfp float C() {
    return 99.0F;
    }



    public strictfp float D() {
    return 99.0F;
    }



    public strictfp boolean bi() {
    return false;
    }



    public strictfp boolean bk() {
    return false;
    }



    public strictfp boolean bm() {
    return true;
    }



    public strictfp float B(int var1) {
    return 0.0F;
    }



    public strictfp float H(int var1) {
    return 0.0F;
    }



    public strictfp float L(int var1) {
    return 0.6F;
    }



    public strictfp int s() {
    return 15;
    }






    public strictfp com.corrodinggames.rts.game.units.custom.resources.CustomActionBase bD() {  // 02b y.java L5719: d.b = CustomActionBase (custom.d.b 婵炴垶鎹佸銊х箔婢跺备鍋撳☉娅亜锕㈤鍫熷剭闁告洦鍋勯拑鐔兼煕閹垮啫鐏℃繝鈧幍顔藉珰妞ゆ牗绋撻崹?
    return null;
    }


    // v19.111 闂佸憡甯掑Λ娆愮珶閹烘梹鍋橀柕澶堝劚瀵?(缂備焦顨愮粻鎴ｃ亹鐠恒劉鍋撳☉娅亜锕㈤鍫濈畱鐟滄垹鎹㈤崘顭戠叆? 闂?)


    // v19.111 闂佸憡甯掑Λ娆愮珶閹烘梹鍋橀柕澶堝劚瀵?(缂備焦顨愮粻鎴ｃ亹鐠恒劉鍋撳☉娅亜锕㈤鍫濈畱鐟滄垹鎹㈤崘顭戠叆? 闂?)


    public strictfp UnitInstance X() {
    if(this.h) {
    WeaponAction var1 = this.ar();
    if(var1 != null && (var1.a == WeaponTypeEnum.d || var1.a == WeaponTypeEnum.g) && var1.h != null && !var1.h.isDead) {
    return var1.h;
    }
    }

    return null;
    }



    public strictfp boolean Y() {
    WeaponAction var1 = this.ar();
    return var1 != null && var1.a == WeaponTypeEnum.g;
    }



    public strictfp boolean aq() {
    return this.ar() == null;
    }



    public strictfp boolean aw() {
    WeaponAction var1 = this.ar();
    return var1 != null && var1.a == WeaponTypeEnum.b;
    }



    public strictfp void ay() {
    this.V = 0.0F;
    this.Y = 0.0F;
    this.W = 0.0F;
    this.ar = false;
    this.aq = -999.0F;
    this.as = false;  // 02b y.as 闁诲孩绋掗〃鍡涱敊?(闁汇埄鍨伴崯顐︽儍?
    this.w = 0;
    if(this.f == 0) {
    this.aH();
    this.e = 0.0F;
    this.d = 0.0F;
    this.c = 0.0F;
    } else if(this.f == 1) {
    this.b(this.g[0]);
    this.f = 0;
    this.aH();
    this.e = 0.0F;
    this.d = 0.0F;
    this.c = 0.0F;
    this.c((WeaponAction)null);
    } else {
    if(this.g.length > 0) {
    WeaponAction var1 = this.g[0];
    this.b(var1);

    for(int i = 0; i < this.f - 1; ++i) {
    this.g[i] = this.g[i + 1];
    }

    this.g[this.f - 1] = var1;
    }

    --this.f;
    if(this.f > 0) {
    this.c(this.g[0]);
    } else {
    this.c((WeaponAction)null);
    }

    this.aH();
    }
    }



    public strictfp void aB() {
    this.a((UnitType)null);
    this.ae = false;
    this.aj = false;
    this.ak = 0.0F;
    this.heightOffset = 0.0F;
    this.ac = 0;
    this.c = 0.0F;
    }



    public strictfp void aD() {
    UnitType var1 = null;
    if(this.ag != 0) {
    UnitInstance[] var2 = UnitInstance.bE.a();
    int var3 = 0;

    for(int var4 = UnitInstance.bE.size(); var3 < var4; ++var3) {
    UnitInstance var5 = var2[var3];
    if(var5 instanceof UnitType) {
    UnitType var6 = (UnitType)var5;
    if (var6.ad == this) {  // 02b y.ad 闁诲孩绋掗〃鍡涱敊?
    var6.a((UnitType)null);
    var1 = var6;
    }
    }
    }

    if(this.ag != 0) {
    this.ag = 0;
    }

    if(var1 != null) {
    WeaponAction var7 = var1.ar();
    if(var7 != null) {
    com.corrodinggames.rts.gameFramework.ProjectileManager var8 = var7.i;
    if(var8 != null) {
    var8.c();
    }
    }
    }

    }
    }



    public strictfp UnitTransform aE() {  // 02b UnitType.aE(): 闁荤姳璀﹂崹鎵閻愮儤鍤嶉柛灞剧矊娴狀垶寮堕悜鍡楀鐎?
    return this.aw == 0?null:(this.au != null ? this.au.a(this) : this.av[0]);  // 02b y.au 闁诲孩绋掗〃鍡涱敊?
    }



    public boolean af() {  // 02b y.java L3566
        return true;
    }

    public strictfp UnitTransform aF() {  // 02b UnitType.aF(): 闁荤姳璀﹂崹鎵閻愮儤鍤嶉柛灞剧矊娴狀垶寮堕悜鍡楀鐎?
    return this.aw < 2?null:(this.au != null ? this.au.b(this) : this.av[1]);  // 02b y.au 闁诲孩绋掗〃鍡涱敊?
    }



    public strictfp boolean aG() {
    if (this.au != null) {  // 02b y.au 闁诲孩绋掗〃鍡涱敊?
    return false;
    } else {
    if(this.aw >= 2) {
    if ((double)this.z() > 0.5) {  // 02b y.z() 闂佸搫鍊介～澶屾兜?
    if(this.W > 150.0F || this.X > 150.0F) {
    return true;
    }
    } else if(this.W > 300.0F || this.X > 300.0F) {
    return true;
    }
    }

    return false;
    }
    }



    public strictfp void aI() {
    this.aH();
    this.av = at;
    this.aI = 0;
    this.aJ = null;
    this.aK = null;
    }



    public strictfp void aJ() {
    this.X = this.W;
    this.W = 0.0F;
    if (this.au != null) {  // 02b y.au 闁诲孩绋掗〃鍡涱敊?
    this.au.c(this);  // 02b y.au 闁诲孩绋掗〃鍡涱敊?(k.c)
    } else if(this.aw != 0) {
    if(this.aw == 1) {
    this.aw = 0;
    } else {
    UnitTransform var1 = this.av[0];

    for(int i = 0; i < this.aw - 1; ++i) {
    this.av[i] = this.av[i + 1];
    }

    this.av[this.aw - 1] = var1;
    --this.aw;
    }
    }
    }



    public strictfp boolean aQ() {  // 02b UnitType.aQ(): 婵炵鍋愭慨鐢稿礉閸涙潙闂柕濞垮劜缁犳垶绻涢幘鍐茬骇闁?
        if (this.N != null && this.F()) {
            GlobalState l2 = GlobalState.B();
            if (!l2.de && this.cj < 18.0f && (double)this.eq < 0.5) {
                return true;
            }
            if (!l2.df && this.cj < 28.0f && this.eq < 5.0f) {
                return true;
            }
            PointF pointF = this.aP();
            float f2 = this.eo + pointF.a - l2.cw;
            float f3 = this.ep + pointF.b - l2.cx;
            float f4 = this.cD();
            TextureManagerInterface y2 = l2.bO;
            if (f4 != 1.0f) {
                y2.k();
                y2.a(f4, f4, f2, f3);
            }
            if (this.cG()) {
                Rect rect = this.a_(true);
                RectF rectF = dB;
                rectF.a(f2 - this.eu, f3 - this.ev, f2 + this.eu, f3 + this.ev);  // 02b y: RectF.a(FFFF) (濠电儑缍侀弨杈╃矚椤栫偞鍋?RectF)
                y2.k();
                y2.a(this.getSensorRange(true), f2, f3);
                y2.loadImageFromResource(this.N, rect, rectF, this.R());  // 02b m.UnitType.a(m.e,Rect,RectF,Paint)
                y2.l();
            } else {
                y2.a(this.N,  f2,  f3,  this.getSensorRange(true) - 90.0f,  this.R());  // 02b m/y.a(e,float,float,float,Paint) (D 婵炴垶鎹囩紓姘额敋閵堝瑙?v19.133f8)  // 02b m.y.a(m.e,ff,f,f,Paint) = 03 D
            }
            if (f4 != 1.0f) {
                y2.l();
            }
            return true;
        }
        return false;
    }

    public strictfp PointF aP() {
    be.a(this.G(), this.H());
    return be;
    }



    public strictfp int q(UnitInstance var1) {
    com.corrodinggames.rts.gameFramework.GlobalState var2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    int var3 = 0;
    float var4 = this.getBuildRangeIncludingTarget(var1);  // 02b y.p(am)
    if(var4 > 58.0F) {
    var3 = (int)((var4 - 41.0F) / ((float)var2.bL.tilePixelWidth * 1.414F));  // 02b b.b.n 闁诲孩绋掗〃鍡涱敊?
    }

    return var3;
    }









    public strictfp boolean bb() {
    return this.bc() > 0.95F;
    }



    public strictfp com.corrodinggames.rts.gameFramework.utility.ai bn() {
    int var1 = this.aT();
    return var1 == -1?this.D(0):this.D(var1);
    }



    public strictfp com.corrodinggames.rts.gameFramework.utility.ai D(int var1) {
    bf.a(this.E(var1));
    return bf;
    }



    public strictfp com.corrodinggames.rts.gameFramework.utility.ai F(int var1) {
    bi.a(this.G(var1));
    bi.c = 0.0F;
    return bi;
    }


    // v19.111 闂佸憡甯掑Λ娆愮珶閹烘梹鍋橀柕澶堝劚瀵?(缂備焦顨愮粻鎴ｃ亹鐠恒劉鍋撳☉娅亜锕㈤鍫濈畱鐟滄垹鎹㈤崘顭戠叆? 闂?)


    public strictfp void az() {
    int var1 = this.f;
    if(this.f > 0) {
    this.b(this.g[0]);
    }

    this.V = 0.0F;
    this.Y = 0.0F;
    this.ar = false;
    this.aq = -999.0F;
    this.as = false;  // 02b y.as 闁诲孩绋掗〃鍡涱敊?(闁汇埄鍨伴崯顐︽儍?
    this.f = 0;
    this.aH();
    this.aD();
    this.a((UnitType)null);
    this.e = 0.0F;
    this.d = 0.0F;
    this.c = 0.0F;
    this.w = 0;
    if(var1 > 0) {
    this.c((WeaponAction)null);
    }

    }





    // v19.111 闂佸憡甯掑Λ娆愮珶閹烘梹鍋橀柕澶堝劚瀵?(缂備焦顨愮粻鎴ｃ亹鐠恒劉鍋撳☉娅亜锕㈤鍫濈畱鐟滄垹鎹㈤崘顭戠叆? 闂?)


    // v19.111 闂佸憡甯掑Λ娆愮珶閹烘梹鍋橀柕澶堝劚瀵?(缂備焦顨愮粻鎴ｃ亹鐠恒劉鍋撳☉娅亜锕㈤鍫濈畱鐟滄垹鎹㈤崘顭戠叆? 闂?)


    // v19.111 闂佸憡甯掑Λ娆愮珶閹烘梹鍋橀柕澶堝劚瀵?(缂備焦顨愮粻鎴ｃ亹鐠恒劉鍋撳☉娅亜锕㈤鍫濈畱鐟滄垹鎹㈤崘顭戠叆? 闂?)


    // v19.111 闂佸憡甯掑Λ娆愮珶閹烘梹鍋橀柕澶堝劚瀵?(缂備焦顨愮粻鎴ｃ亹鐠恒劉鍋撳☉娅亜锕㈤鍫濈畱鐟滄垹鎹㈤崘顭戠叆? 闂?)


    // v19.111 闂佸憡甯掑Λ娆愮珶閹烘梹鍋橀柕澶堝劚瀵?(缂備焦顨愮粻鎴ｃ亹鐠恒劉鍋撳☉娅亜锕㈤鍫濈畱鐟滄垹鎹㈤崘顭戠叆? 闂?)


    public strictfp Paint R() {
    boolean var1 = this.shouldAntiAlias();
    return var1?aF:aE;
    }



    public strictfp void U() {
    String var1;
    if(this.r() != null) {
    var1 = this.r().i();
    } else {
    var1 = "<NO UNIT TYPE>";
    }

    com.corrodinggames.rts.gameFramework.GlobalState.e("---- Debug for:" + var1 + " id:" + this.eh + "---");
    }



    protected strictfp void W() {
    com.corrodinggames.rts.gameFramework.GlobalState var1 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    if(this.player == var1.bs) {
    var1.bS.i.b(this);
    }

    }



    private strictfp void f(float var1, WeaponAction var2, UnitAttachment var3) {
    float var4 = var2.g();
    float var5 = var2.h();
    float var6 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eo, this.ep, var4, var5);
    com.corrodinggames.rts.gameFramework.GlobalState var7 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    UnitTypeHandle var8 = var2.b;
    if(var8 == null) {
    this.a("activeBuildingType==null, removing waypoint");
    this.ay();
    var2 = null;
    }

    if(var2 != null) {
    float var9 = this.f(var8);
    boolean var10 = true;
    boolean var11 = false;
    if(var9 <= 30.0F) {
    var10 = true;
    }

    if(var9 <= 25.0F && this.eq > 4.0F) {
    var11 = true;
    }

    if (this.ad != null) {  // 02b y.ad 闁诲孩绋掗〃鍡涱敊?(UnitType)
    WeaponAction var12 = this.ad.ar();  // 02b y.ad
    if(var12 == null || var12.a != WeaponTypeEnum.c) {
    var3.rotationAngle = false;  // 02b ad.d (boolean)
    }

    if(var12 != null && !var2.b(var12)) {
    var3.rotationAngle = false;  // 02b ad.d (boolean)
    }
    }

    boolean var16 = false;
    if (!com.corrodinggames.rts.gameFramework.utility.y.a(this.Q, 200)) {  // 02b utility.y = PathfindingUtils
    var16 = true;
    }

    boolean var13;
    if(var9 > 800000.0F) {
    var13 = true;
    } else {
    var13 = var6 <= var9 * var9;
    }

    if(var13 && !var11) {
    if(!var16 && (!this.b_() || com.corrodinggames.rts.gameFramework.GameUtils.c(this.b(var1, var4, var5)) <= 30.0F)) {
    WaypointTarget var14 = this.a(var2, var2.b, var2.d, var2.e, var2.f);
    UnitInstance var15 = null;
    if(var14.a != null) {
    var15 = var14.a;
    } else if(var14.b != null) {
    var15 = var14.b;
    }

    if(var15 != null) {
    var14.d.a((UnitInstance)this, var15);
    if(this.a(var15)) {
    if(this.b(var15) > 10000.0F) {
    var15.r(1.0F);
    this.ax();
    } else {
    var2.e();
    var2.a = WeaponTypeEnum.d;
    var2.h = var15;
    this.aH();
    }
    } else {
    this.ay();
    }

    this.Q = -9999;
    } else {
    if(var2.b == null) {
    com.corrodinggames.rts.gameFramework.GlobalState.e("active.build==null");
    }

    if(!var14.c) {
    this.ay();
    }
    }
    }
    } else if(!this.isUsable()) {
    this.ay();
    var2 = null;
    } else {
    this.isPathingActive = true;
    this.l = var4;
    this.pathTargetY = var5;
    if(var9 > 58.0F) {
    this.n = (int)((var9 - 41.0F) / ((float)var7.bL.tilePixelWidth * 1.414F));  // 02b y.n 闁诲孩绋掗〃鍡涱敊? b.b.n
    }

    if(this.s > 90.0F) {
    this.s = 90.0F;
    }

    if(this.q > 3) {
    this.ay();
    var2 = null;
    return;
    }
    }
    }

    }



    public strictfp boolean aa() {
    if(this.R != null && !this.R.isDead) {
    int var1 = this.bl();

    for(int i = 0; i < var1; ++i) {
    if (this.cL[i].targetUnit != null && this.r(i)) {  // 02b ap.j = am
    return true;
    }
    }
    }

    return false;
    }



    public strictfp boolean l(UnitInstance var1) {
    return var1.g() != 0.0F && this.h(var1, true)?true:this.a(var1);
    }



    public strictfp WeaponAction PlayerState(UnitInstance var1) {
    WeaponAction var2 = this.addWaypoint();
    var2.a(var1);
    return var2;
    }



    public strictfp WeaponAction e(float var1, float var2) {
    WeaponAction var3 = this.addWaypoint();
    var3.b(var1, var2);
    return var3;
    }









    public strictfp void ax() {
    this.aC();
    this.ay();
    }



    public strictfp void aA() {
    for(int i = 0; i < this.f; ++i) {
    WeaponAction var2 = this.g[i];
    if(var2 != null && var2.a != WeaponTypeEnum.c && var2.a != WeaponTypeEnum.d) {
    this.n(i);  // 02b y.n(int)
    }
    }

    }



    public strictfp void aC() {
    if(this.ag != 0) {
    WeaponAction var1 = this.as();  // 02b y.as()
    UnitInstance[] var2 = UnitInstance.bE.a();
    int var3 = 0;

    for(int var4 = UnitInstance.bE.size(); var3 < var4; ++var3) {
    UnitInstance var5 = var2[var3];
    if(var5 instanceof UnitType) {
    UnitType var6 = (UnitType)var5;
    if (var6.ad == this) {  // 02b y.ad 闁诲孩绋掗〃鍡涱敊?
    float var7 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eo, this.ep, var6.eo, var6.ep);
    boolean var8 = var7 < 108900.0F;
    boolean var9 = false;
    boolean var10 = false;
    WeaponAction var11 = var6.as();  // 02b y.as() 闂佸搫鍊介～澶屾兜?
    if(var1 != null && var11 != null) {
    if(var1.b(var11)) {
    var9 = true;
    }
    } else if(var1 == null && var11 == null) {
    var10 = true;
    }

    if(var9 && var8) {
    var6.ay();
    } else if(!var10) {
    var6.a((UnitType)null);
    }
    }
    }
    }

    }
    }






    public strictfp Paint aN() {
    PorterDuffColorFilter var1 = null;
    boolean var2 = true;
    int var4;
    if(this.eq < -0.3F) {
    int var3 = this.ensurePathNodeCapacity(this.eq);  // 02b y.l(float)
    var4 = Color.a(var3, 255, 255, 255);
    } else {
    var4 = -1;
    }

    if(this.cm < 1.0F && this.cm < this.aM()) {
    float var5 = this.cm / this.aM() * 220.0F;
    var4 = Color.a((int)(20.0F + var5), 140, 255, 140);
    var1 = aX;
    }

    if(this.cp) {
    if(this.cs) {
    var4 = Color.a(200, 20, 255, 20);
    var1 = buildProgressFilter;  // 02b y.aX (闂傚倸鐗婇悷锕傚焵椤戣法顦︽繛?200,255,200)
    }

    if(this.ct) {
    var4 = Color.a(200, 255, 20, 20);
    var1 = aZ;
    }

    if(this.cq) {
    var4 = Color.a(50, 70, 70, 245);
    var1 = ba;
    if(this.ct) {
    var4 = Color.a(50, 255, 20, 20);
    var1 = aZ;
    }
    }

    if(this.cr) {
    var4 = Color.a(150, 100, 100, 100);
    }
    }

    boolean var6 = this.shouldAntiAlias();
    return this.a(var4, var1, var6);
    }



    public strictfp boolean F() {
    return this.eq > 0.0F && this.cm >= 1.0F && !this.cq;
    }



    public strictfp void aW() {
    int var1 = this.bl();

    for(int i = 0; i < var1; ++i) {
    if(i < this.cL.length) {
    UnitTurret var3 = this.cL[i];
    if(var3.e > this.b(i)) {
    var3.e = this.b(i);
    }
    }
    }

    }



    public strictfp boolean u(int var1) {
    int var2 = this.v(var1);
    return var2 == -1 ? this.cL[var1].hasLimitedArc : this.cL[var2].hasLimitedArc;  // 02b ap.g 婵烇絽娲︾换鍌滆姳?
    }






    public strictfp float C(int var1) {
    return this.ci && this.bb()?this.cg + 180.0F:this.cg;
    }



    public strictfp PointF E(int var1) {
    UnitTurret var2 = this.cL[var1];
    float var3 = this.g(var1);
    float var4 = this.E() ? this.cg : var2.turretAngle;  // 02b ap.a = turretAngle
    PointF var5 = this.G(var1);
    float var6 = var5.a + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(var4) * var3;
    float var7 = var5.b + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(var4) * var3;
    bg.a(var6, var7);
    return bg;
    }



    public strictfp PointF G(int var1) {
    UnitTurret var2 = this.cL[var1];
    float var3 = this.eo;
    float var4 = this.ep;
    float var5 = this.H(var1);
    if(var2.e != 0.0F && var5 != 0.0F) {
    float var6 = this.I(var1);
    float var7 = this.J(var1);
    float var8 = 0.0F;
    float var9 = this.b(var1) - var2.e;
    if(var9 < var6) {
    var8 = var9 / var6 * var5;
    } else if(var9 < var7 + var6) {
    var8 = var5 - (var9 - var6) / var7 * var5;
    }

    if(var8 != 0.0F) {
    var3 += com.corrodinggames.rts.gameFramework.GameUtils.cosFast(var2.turretAngle) * var8;  // 02b ap.a
    var4 += com.corrodinggames.rts.gameFramework.GameUtils.sinFast(var2.turretAngle) * var8;  // 02b ap.a
    }
    }

    bh.a(var3, var4);
    return bh;
    }



    public strictfp void M(int var1) {
    if(var1 != -1) {
    UnitTurret var4 = this.cL[var1];
    var4.h = 0.0F;
    var4.i = 0.0F;
    if(this.R != null && this.L(var1) != 0.0F) {
    float var5 = this.R.cj * this.L(var1);
    var4.h += (float)com.corrodinggames.rts.gameFramework.GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, (int)(-var5), (int)var5, 1 + var1);  // 02b L5080  // 02b L5080
    var4.i += (float)com.corrodinggames.rts.gameFramework.GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, (int)(-var5), (int)var5, 2 + var1);  // 02b L5081  // 02b L5081
    }

    } else {
    int var2 = this.bl();

    for(int i = 0; i < var2; ++i) {
    this.M(i);
    }

    }
    }



    public strictfp void bo() {
    com.corrodinggames.rts.gameFramework.GlobalState var1 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    float var2 = 1.0F;
    float var3 = 1.0F;
    int var4 = this.bp();
    if(var4 >= 10) {
    var2 = 1.2F;
    var3 = 1.4F;
    }

    if(var4 >= 20) {
    var2 = 1.5F;
    var3 = 1.7F;
    }

    if(this.eq > -1.0F) {
    for(int i = 0; i < var4; ++i) {
    var1.bR.b(this.eo, this.ep, this.eq, var2, var3);
    }
    }

    }



    public strictfp void bq() {
    if(!this.cK()) {
    com.corrodinggames.rts.game.l.a(this.eo, this.ep);
    }

    }





    // v19.111 闂佸憡甯掑Λ娆愮珶閹烘梹鍋橀柕澶堝劚瀵?(缂備焦顨愮粻鎴ｃ亹鐠恒劉鍋撳☉娅亜锕㈤鍫濈畱鐟滄垹鎹㈤崘顭戠叆? 闂?)




   // 02b y.al (javap: public float al; 02b ProjectileWeapon.a(m,y,m,f,int) 婵炶揪缍€濞夋洟寮?
   public float al = 0.0f;

   // 02b y.br() L5216: 闂佸憡鐗曢幊搴∶洪崸妤佸剮闁圭儤鍨甸悡鏇㈡煛?(婵炴挻纰嶇换鍡欑矉?GlobalState.bL=MapEngine.o/n + cd()/cZ()/da() + am.bF() DequeList,
   // 03 婵?MapEngine 闁诲孩绋掗〃鍡涱敊瀹€鍕珘妞ゅ繐鎳忚〖闁? 闂佸搫鐗冮崑鎾绘倶韫囨挾绠抽柍瑙勭墱閳ь剙婀遍崑鐔肩嵁? 閻?MapEngine/UnitType 闂佺懓鐡ㄩ敋闁绘帪濡囬埀顒傛嚀閺堫剟寮抽敐澶婄?
   public void br() {
   }


   // 02b y.m(int) L3964-3973: 鑸偣绱㈠紩涓婇檺妫€鏌?
   public void m(int var1) {
      if(var1 >= 30) {
         throw new RuntimeException("Waypoint index:" + var1 + " too large");
      } else {
         if(this.g == O) {
            this.g = new WeaponAction[30];
         }
      }
   }

   // 02b y.ao() L3764-3793 闂佺儵鏅濋ˉ鎰版儊? 闂佸吋鍎抽崲鑼躲亹閸パ屾桨闁挎洍鍋撴繛鍛笒鍗遍柟瀛樺笧缁?(闁哄鍎愰崰妤€鐣烽弻銉ュ偍闁糕剝顨呴拺?
   public WeaponAction ao() {
      this.m(29);
      if(this.f > 0) {
         this.b(this.g[0]);
      }
      WeaponAction var1 = this.g[29];
      for(int var2 = 29; var2 >= 1; --var2) {
         this.g[var2] = this.g[var2 - 1];
      }
      this.g[0] = var1;
      if(this.f < 29) {
         ++this.f;
      }
      if(this.g[0] == null) {
         this.g[0] = new WeaponAction();
      }
      WeaponAction var3 = this.g[0];
      var3.e();
      this.V = 0.0F;
      this.Y = 0.0F;
      this.W = 0.0F;
      this.c(var3);
      this.aH();
      return var3;
   }

   // 02b y.ap() L3810-3827 闂佺儵鏅濋ˉ鎰版儊? 闂佸吋鍎抽崲鑼躲亹閸ヮ亗浜归柟鎯у暱椤ゅ懏鎱ㄥ┑鎾剁暠婵炲懏甯掑嵄闁瑰瓨甯炵粔?
   public WeaponAction ap() {
      this.m(this.f);
      if(this.g[this.f] == null) {
         this.g[this.f] = new WeaponAction();
      }
      WeaponAction var1 = this.g[this.f];
      var1.e();
      if(this.f < 29) {
         ++this.f;
      }
      if(this.f > 0) {
         this.c(this.g[0]);
      }
      return var1;
   }


   // 02b y.o(float) 缂備胶濮崑鎾绘煕? 闁荤姳绀佹晶浠嬫偪閸℃稑瀚夐柍褜鍓氬鍕槾闁轰焦鎹囧畷?(闁诲海鎳撻張顒勫汲閿濆棙濯撮柟鎯х－缁?UnitType 闂佺懓鐡ㄩ敋闁?
   public void o(float var1) {
   }

   // 02b y.f(float,float) 缂備胶濮崑鎾绘煕? 婵炵鍋愭繛鈧柍?(闁诲海鎳撻張顒勫汲閿濆棙濯撮柟鎯х－缁?UnitType 闂佺懓鐡ㄩ敋闁?
   public void f(float var1, float var2) {
   }

   // 02b y.r(float) 缂備胶濮崑鎾绘煕? 闁荤姳绀佹晶浠嬫偪閸℃鍤堟繛宸簴閸嬫捇鎮╅搹鍦啋闁?(闁诲海鎳撻張顒勫汲閿濆棙濯撮柟鎯х－缁?UnitType 闂佺懓鐡ㄩ敋闁?
   public void r(float var1) {
   }

   // 02b y.ci() 缂備胶濮崑鎾绘煕? 闂佸憡甯炴繛鈧繛鍛叄瀹曪繝寮村杈┬?(闁诲海鎳撻張顒勫汲閿濆棙濯撮柟鎯х－缁?UnitType 闂佺懓鐡ㄩ敋闁?
   public void ci() {
   }

    public void i(float f2) {
        // v19.115p 闂? 闁荤偞绋忛崕鍐差啅? 02b y.i(float) 闁诲孩绋掗〃澶嬩繆椤撱垺鍎樺〒姘ｅ亾闁逛匠鍛珰?(bp.java 闂佹眹鍨婚崰鎰板垂濮樿泛纭€闁哄洦姘ㄧ粔鎾煕閹烘挾绠撴い顐ｅ姍瀵剟鎮烽悧鍫濃偓? 闂?缂備胶濮崑鎾绘煕?TODO
    }

    public boolean player() {
        // v19.115r logicBooleans 闂? 闁荤偞绋忛崕鍐差啅? javap y.bX:()Z 闂備礁褰炲ù鍥儊?(isEnergyRechargingBoolean 闁荤姴顑呴崯浼村极閵堝鍊? 闂?缂備胶濮崑鎾绘煕?TODO
        return false;
    }

    public boolean cI() {
        // v19.115r logicBooleans 闂? 闁荤偞绋忛崕鍐差啅? 02b y.cI() (OverCliftBoolean 闂佽鍣崜娆戠矆閳ь剟鏌涢幒瀣偓鏍蓟? 闂?缂備胶濮崑鎾绘煕?TODO
        return false;
    }
    // v19.133f8: 闂佸憡甯炴繛鈧繛鍛叄閻涱噣寮拌箛锝嗙秿 cL() (float) 闂?02b y 闂?cL(); 闂佹椿浜為崰鎰姳椤旂晫鈻?02b am.cL() (int) 閻庣懓鎲¤ぐ鍐耿?UnitInstance 闁?(PriceCreditsBoolean 濠电偛鐭佸▔鏇㈠箣閻戣棄绠ラ柛褎顨嗛幊鐘绘偡濞嗘瑧绋婚柣?
    // v19.133f8: 闂佸憡甯炴繛鈧繛鍛叄閻涱噣寮拌箛锝嗙秿 a(TeamTag) (float) 闂?02b y 闂?a(custom.g); 闂佹椿浜為崰鎰姳椤旂晫鈻?02b units.d.i.a(custom.g)闂佹剚鍋呮慨鏄籺 (MobileBuilderBase 閻庣懓鎲¤ぐ鍐偪閸曨垱鍋?
    public int bz;
    // v19.115r logicBooleans 闂? 闁荤偞绋忛崕鍐差啅? 02b y.bz 闁诲孩绋掗〃鍡涱敊?(TimeAliveBoolean 闂佸憡鍨煎▍锝夊极閹捐绫嶉柛顐ｆ礃閿? 闂?缂備胶濮崑鎾绘煕?TODO

    public PointF n(float f2) {  // 02b y.n(float) L5420-5441: 婵☆偅婢樼€氼厾绮婄€涙ɑ濯寸€广儱娲ㄩ弸?
        float f3 = 0.0f;
        float f4 = 0.0f;
        if (this.I() && this.b == 0.0f) {
            if (this.bi()) {
                f3 = this.cc * f2;
                f4 = this.cd * f2;
            } else if (this.cf != 0.0f) {
                float f5 = this.cg;
                if (this.bj()) {
                    f5 = this.ch;
                }
                float f6 = this.z() * this.cf * f2;
                f3 = com.corrodinggames.rts.gameFramework.GameUtils.k(f5) * f6;
                f4 = com.corrodinggames.rts.gameFramework.GameUtils.j(f5) * f6;
            }
        }
        bm.a(f3, f4);
        return bm;
    }


    public com.corrodinggames.rts.game.units.WeaponAction n(UnitInstance am2) {  // 02b y.n(am) L3835-3839
        com.corrodinggames.rts.game.units.WeaponAction au2 = this.ap();
        au2.a(am2);
        return au2;
    }

}

