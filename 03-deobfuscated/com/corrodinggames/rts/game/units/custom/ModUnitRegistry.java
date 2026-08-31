/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.TagFilter;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.GameMode;
import com.corrodinggames.rts.game.units.custom.effects.EffectRenderer;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.UnitList;
import com.corrodinggames.rts.game.units.PathResult;
import com.corrodinggames.rts.game.units.custom.actions.g;
import com.corrodinggames.rts.game.units.custom.aa;
import com.corrodinggames.rts.game.units.custom.ab;
import com.corrodinggames.rts.game.units.custom.ac;
import com.corrodinggames.rts.game.units.custom.ad;
import com.corrodinggames.rts.game.units.custom.ModLoader;
import com.corrodinggames.rts.game.units.custom.ay;
import com.corrodinggames.rts.game.units.custom.az;
import com.corrodinggames.rts.game.units.custom.TraitValueBuilder;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.bd;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bl;
import com.corrodinggames.rts.game.units.custom.ModUnitLoader;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;  // 02 铁证: custom.l.eW:Lcustom/logicBooleans/LogicBoolean;
import com.corrodinggames.rts.game.units.custom.effects.EffectConfig;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableMapping;
import com.corrodinggames.rts.game.units.custom.DirectionConfig;
import com.corrodinggames.rts.game.units.custom.DirectionType;
import com.corrodinggames.rts.game.units.custom.AnimationReference;
import com.corrodinggames.rts.game.units.custom.Modifier;
import com.corrodinggames.rts.game.units.custom.ModifierType;
import com.corrodinggames.rts.game.units.custom.ModifierApplier;
import com.corrodinggames.rts.game.units.custom.UnitActionDef;
import com.corrodinggames.rts.game.units.custom.ActionBinding;
import com.corrodinggames.rts.game.units.custom.CustomPhysics;
import com.corrodinggames.rts.game.units.custom.CustomVisuals;
import com.corrodinggames.rts.game.units.projectiles.SpecialBuilding;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.io.IOException;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public strictfp final class ModUnitRegistry
implements UnitTypeHandle {
    public static final Rect a = new Rect();
    public static ModUnitRegistry b;
    public static final ArrayList c;
    public static ArrayList d;
    public static ArrayList e;
    public static final HashMap f;
    public static ArrayList g;
    public final com.corrodinggames.rts.gameFramework.utility.CustomArrayList h = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public final com.corrodinggames.rts.gameFramework.utility.CustomArrayList i = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public final com.corrodinggames.rts.gameFramework.utility.CustomArrayList j = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    final com.corrodinggames.rts.gameFramework.utility.CustomArrayList k = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public final com.corrodinggames.rts.gameFramework.utility.CustomArrayList l = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public final com.corrodinggames.rts.gameFramework.utility.CustomArrayList m = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public final com.corrodinggames.rts.gameFramework.utility.CustomArrayList n = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public final com.corrodinggames.rts.gameFramework.utility.CustomArrayList o = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    final com.corrodinggames.rts.gameFramework.utility.CustomArrayList p = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public final com.corrodinggames.rts.gameFramework.utility.CustomArrayList q = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public final VariableScope$VariableMapping r = new VariableScope$VariableMapping();
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;
    public boolean A = true;
    public boolean B;
    public Rect C;
    public String D;
    public String E;
    public String F;
    public boolean G = true;
    public int H;
    public String I;
    public com.corrodinggames.rts.gameFramework.mods.ModInfo J;
    public String K;
    public String L;
    public String M;
    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList N = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public UnitConfig O;  // 02 铁证: custom.l.O:Lcustom/h;
    public UnitConfig P;  // 02 铁证: custom.l.P:Lcustom/h;
    public String Q;
    public int R;
    public int S;
    public ad T;
    public int U = 1;
    public int V = Integer.MAX_VALUE;
    public int W = -1;
    public int X = -1;
    public int Y;
    public LogicBoolean Z;
    public boolean aa;
    public boolean ab;
    public GameMode ac;
    public Texture ad = null;
    public boolean ae = true;
    public int af;
    public int ag;
    public int ah;
    public int ai;
    public float aj;
    public boolean ak;
    public Texture al = null;
    public boolean am;
    public Texture an = null;
    public Texture ao = null;
    public Texture ap = null;
    public boolean aq;
    public Texture[] ar = new Texture[10];
    public Texture[] as;
    public Texture[] at = null;
    public Texture au = null;
    public boolean av = false;
    public Texture aw;
    public TraitValueBuilder[] ax = null;
    public boolean ay = false;
    public boolean az = false;
    public final com.corrodinggames.rts.gameFramework.utility.CustomArrayList aA = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public boolean aB;
    public LocalizedString aC;
    public com.corrodinggames.rts.game.units.custom.bl do_;  // 02b l.do (L270): 单位音效解析器
    public LocalizedString aD;
    public String aE;
    public boolean aF;
    public float aG = 1.0f;
    public boolean aH;
    public boolean aI;
    public boolean aJ;
    public boolean aK;
    public float aL;
    public boolean aM;
    public boolean aN;
    public boolean aO;
    public boolean aP;
    public boolean aQ;
    public boolean aR;
    public UnitConfig aS;  // 02 铁证: custom.l.aS:Lcustom/h;
    public boolean aT;
    public boolean aU;
    public boolean aV;
    public boolean aW;
    public int aX = -1;
    public boolean aY;
    public int aZ = -1;
    public boolean ba;
    public float bb;
    public float bc;
    public float bd = 1.0f;
    public float be;
    public float bf;
    public boolean bg;
    public float bh;
    public boolean bi;
    public boolean bj;
    public boolean bk;
    public boolean bl;
    public int bm;
    public boolean bn;
    public float bo;
    public float bp;
    public int bq;
    public boolean br;
    public boolean bs;
    public com.corrodinggames.rts.game.units.UnitState bt;
    public boolean bu;
    public boolean bv;
    public CustomVisuals bw;
    public CustomVisuals bx;
    public CustomVisuals by;
    public bl bz;
    public int bA = -1;
    public int bB = -1;
    public bp bC;
    public boolean bD;
    public boolean bE;
    public boolean bF;
    public boolean bG;
    public float bH = 1.0f;
    public float bI = 1.0f;
    boolean bJ;
    boolean bK;
    boolean bL;
    boolean bM;
    boolean bN;
    CustomVisuals bO;
    CustomVisuals bP;
    boolean bQ;
    float bR;
    boolean bS;
    float bT;
    CustomVisuals bU;
    CustomVisuals bV;
    boolean bW;
    float bX;
    CustomVisuals bY;
    CustomVisuals bZ;
    public float ca = 60.0f;
    public ModifierType cb = ModifierType.a;
    public boolean cc;
    public boolean cd;
    public boolean ce;
    public boolean cf;
    public boolean cg;
    public CustomActionBase ch;
    public CustomActionBase ci;
    public CustomActionBase cj;
    public float ck = 0.001f;
    public int cl;
    public boolean cm;
    public boolean cn;
    public CustomActionBase co = CustomActionBase.a;
    public com.corrodinggames.rts.game.units.custom.effects.EffectManager cp = com.corrodinggames.rts.game.units.custom.effects.EffectManager.a;  // 02b l.cp:e.f (L219)
    public com.corrodinggames.rts.game.units.custom.effects.EffectManager cq = com.corrodinggames.rts.game.units.custom.effects.EffectManager.a;  // 02b l.cq:e.f (L220)
    public int cr;
    public float cs;
    public com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter ct = null;
    public float cu = 1.0f;
    public CustomActionBase cv = CustomActionBase.a;
    public CustomActionBase cw = CustomActionBase.a;
    public LogicBoolean cx;
    public boolean cy;
    public boolean cz;
    public boolean cA;
    public boolean cB;
    public boolean cC;
    public boolean cD;
    public boolean cE;
    public float cF;
    public int cG;
    public UnitConfig cH;  // 02 铁证: custom.l.cH:Lcustom/h;
    public int cI = -2;
    public float cJ;
    public float cK;
    public com.corrodinggames.rts.game.units.custom.WeaponConfig cL = new com.corrodinggames.rts.game.units.custom.WeaponConfig(true);
    public boolean cM;
    public float cN;
    public boolean cO;
    public float cP;
    public float cQ;
    public boolean cR;
    public float cS;
    public LocalizedString cT;
    public boolean cU;
    public float cV;
    public int cW;
    public Rect cX = new Rect();
    public Rect cY = new Rect();
    public Rect cZ = new Rect();
    public float da;
    public float db;
    public boolean dc;
    public int dd;
    public float de;
    public int df;
    public int dg;
    public int dh;
    public float di;
    public float dj;
    public Float dk;
    public float dl;
    public boolean dm;
    public Float dn;
    public bl soundOnAttackOrder;
    public bl dp;
    public bl dq;
    com.corrodinggames.rts.gameFramework.utility.CustomArrayList dr = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    UnitParameter ds = new UnitParameter("moving");
    UnitParameter dt = new UnitParameter("idle");
    UnitParameter du = new UnitParameter("attack");
    UnitParameter dv;
    UnitParameter dw;
    UnitParameter dx;
    UnitParameter dy;
    UnitParameter dz;
    UnitParameter dA;
    public boolean dB;
    public boolean dC;
    public boolean dD;
    public boolean dE;
    public ModUnitLoader dF;
    public int dG;
    public float dH;
    DirectionConfig dI;
    public float dJ;
    public Boolean dK;
    public boolean dL;
    public float dM;
    public float dN;
    public float dO;
    public boolean dP;
    public boolean dQ;
    public boolean dR;
    public float dS = 0.0f;
    public float dT;
    public float dU = -1.0f;
    public float dV = 0.03f;
    public float dW = 0.06f;
    public boolean dX;
    public boolean dY;
    public int dZ;
    public float ea;
    public float eb;
    public com.corrodinggames.rts.game.units.PathResult ec;  // 02b l.ec:units.b (L310)
    public float ed;
    public float ee;
    public boolean ef;
    public boolean eg;
    public boolean eh;
    public boolean ei;
    public float ej;
    public float ek;
    public float el;
    public int em = -1;
    public int en = -1;
    public float eo;
    public boolean ep;
    public LogicBoolean eq;
    public LogicBoolean er;
    public LogicBoolean es;
    public LogicBoolean et;
    public boolean eu;
    public UnitConfig ev;  // 02 铁证: custom.l.ev:Lcustom/h;
    public UnitConfig ew;  // 02 铁证: custom.l.ew:Lcustom/h;
    public boolean ex;
    public boolean ey;
    public float ez;
    public boolean eA;
    public int eB;
    public boolean eC;
    public boolean eD;
    public boolean eE;
    public boolean eF;
    public float eG;
    public boolean eH;
    public boolean eI;
    public boolean eJ = false;
    public boolean eK = false;
    public boolean eL = false;
    public int eM = 0;
    public float eN;
    public boolean eO;
    public UnitConfig eP;  // 02 铁证: custom.l.eP:Lcustom/h;
    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList eQ = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public boolean eR;
    public boolean eS;
    public boolean eT;
    public LogicBoolean eU;
    public LogicBoolean eV;
    public LogicBoolean eW;
    public boolean eX;
    public float eY;
    public int eZ = 1;
    public static LogicBoolean defaultNotOverLiquidCheck;
    public static LogicBoolean defaultNotOverLiquidCheckMoving;
    public LogicBoolean fc;
    public LogicBoolean fd;
    public boolean canUnloadUnitsDefault = true;
    public be ff;
    public MovementTypeEnum fg;
    public MovementTypeEnum fh;
    public boolean fi;
    public boolean fj;
    public boolean fk;
    public UnitConfig fl;  // 02 铁证: custom.l.fl:Lcustom/h;
    public int fm;
    public UnitConfig fn;  // 02 铁证: custom.l.fn:Lcustom/h;
    public UnitConfig fo;  // 02 铁证: custom.l.fo:Lcustom/h;
    public boolean fp;
    public boolean fq;
    public boolean fr;
    public boolean fs;
    public boolean ft;
    public boolean fu;
    public UnitConfig fv;  // 02b l.fv:h (L381)
    public boolean fw;
    public int fx;
    public int fy;
    public float fz;
    public int fA;
    public float fB;
    public float fC;
    public float fD;
    public int fE;
    public int fF;
    public boolean fG;
    public UnitConfig fH;
    public String fI;
    public boolean fJ;
    public float fK = -1.0f;
    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList fL = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public boolean fM;
    public boolean fN;
    public UnitConfig fO;  // 02b l.fO:h (L400)
    public boolean fP;
    public ModUnitLoader[] fQ = null;
    public bh[] fR;
    ArrayList fS = new ArrayList();

    public ModUnitLoader e(String string) {
        for (ModUnitLoader bn2 : (java.util.Collection<ModUnitLoader>) (java.util.Collection) this.fS) {
            if (!bn2.a.equalsIgnoreCase(string)) continue;
            return bn2;
        }
        return null;
    }
    ArrayList fT = new ArrayList();
    boolean fU = false;
    ModUnitLoader fV = null;
    com.corrodinggames.rts.gameFramework.utility.CustomArrayList fW = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    boolean fX;
    Modifier[] fY;
    Modifier[] fZ;
    Modifier[] ga;
    com.corrodinggames.rts.gameFramework.utility.CustomArrayList gb = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    ArrayList gc = new ArrayList();
    ArrayList gd = new ArrayList();
    static final ay[] emptyEffectArray;
    static final ay[] noneEffectArray;
    ArrayList gg = new ArrayList();
    ArrayList gh = new ArrayList();
    public boolean gi;
    int cachedLocaleVersion = -1;
    String cachedLocalizedName;
    String cachedLocalizedDescription;
    HashMap actionCache;
    com.corrodinggames.rts.game.units.at[] specialActionsByTier;  // 02b l.gn:units.at[] (L425)
    com.corrodinggames.rts.game.units.actions.SellAction actionHandler = new com.corrodinggames.rts.game.units.actions.SellAction(this);
    com.corrodinggames.rts.gameFramework.utility.CustomArrayList gp = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    com.corrodinggames.rts.gameFramework.utility.CustomArrayList gq = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    public boolean usesCreditResources;
    public boolean gs;
    com.corrodinggames.rts.gameFramework.utility.CustomArrayList gt = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();

    public String e() {
        // 02b custom/l.java L723-736: 本地化单位显示名 (简化去缓存)
        String string = this.aC != null ? this.aC.getLocalizedText() : this.M;
        String string2 = this.M;
        if (this.aE != null) {
            string2 = this.aE;
        }
        return com.corrodinggames.rts.gameFramework.steam.Localization.a("units." + string2 + ".name", string, new Object[0]);
    }
    public String f() {
        // 02b custom/l.java L738-751: 本地化单位描述 (去掉缓存)
        String string = this.aD != null ? this.aD.getLocalizedText() : this.M;
        String string2 = this.M;
        if (this.aE != null) {
            string2 = this.aE;
        }
        return com.corrodinggames.rts.gameFramework.steam.Localization.a("units." + string2 + ".description", string, new Object[0]);
    }

    public String getUnitNameWithoutModPrefix() {
        String string = this.D;
        if (this.J != null) {
            String string2 = this.J.q;
            if (string.startsWith(string2)) {
                if ((string = string.substring(string2.length())).startsWith("/")) {
                    string = string.substring(1);
                }
                if (string.startsWith("\\")) {
                    string = string.substring(1);
                }
            }
            string = string + " (in mod " + this.J.a() + ")";
        }
        return string;
    }

    public AnimationReference a(String string, AnimationReference o2) {
        if (string != null) {
            AnimationReference o3 = new AnimationReference(this);
            o3.a = string;
            o3.a();
            return o3;
        }
        if (o2 != null) {
            AnimationReference o4 = new AnimationReference(this);
            o4.a = o2.a;
            o4.a();
            return o4;
        }
        return null;
    }

    UnitParameter a(DirectionType n2, UnitParameter f2, boolean bl2) throws bo {
        UnitParameter f3 = this.a(n2);
        if (f3 != null) {
            if (bl2 && f2 != null && f2.a()) {
                throw new bo("Cannot define animation " + n2.name() + " on graphics and with onAction at same time");
            }
            return f3;
        }
        return f2;
    }

    public static ArrayList s() {
        // 02b custom/l.java L936-946: 自定义单位编号列表
        ArrayList arrayList = new ArrayList();
        int n2 = 100;
        for (Iterator iterator = g.iterator(); iterator.hasNext(); ++n2) {
            ModUnitRegistry l2 = (ModUnitRegistry) iterator.next();
            arrayList.add(Integer.valueOf(n2));
        }
        return arrayList;
    }
    public static String a(String string) {
        string = string.toLowerCase(Locale.ROOT);
        if ((string = string.trim()).startsWith("arm_")) {
            string = "arm" + string.substring("arm_".length());
        }
        if (string.startsWith("leg_")) {
            string = "leg" + string.substring("leg_".length());
        }
        return string;
    }

    public int getUnitNameWithoutModPrefix(String string) {
        string = ModUnitRegistry.a(string);
        com.corrodinggames.rts.gameFramework.GlobalState.e("name:" + string);
        for (int i = 0; i < this.ax.length; ++i) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("checking:" + this.ax[i].b);
            if (!string.equals(this.ax[i].b)) continue;
            com.corrodinggames.rts.gameFramework.GlobalState.e("got");
            return i;
        }
        return -1;
    }

    UnitParameter a(DirectionType n2) {
        for (UnitParameter f2 : (java.util.Collection<UnitParameter>) (java.util.Collection) this.dr) {
            if (!f2.a(n2)) continue;
            return f2;
        }
        return null;
    }

    public void a(ModifierApplier t2) {
        this.gb.add(t2);
    }

    public CustomVisuals a(String string, CustomVisuals z2) throws bo {
        if (string == null && z2 != null) {
            return z2;
        }
        CustomVisuals z3 = new CustomVisuals(this, string, (ModUnitRegistry$1)null);
        z3.c();
        return z3;
    }

    public CustomVisuals getCreditCost(String string) {
        CustomVisuals z2 = new CustomVisuals(this, string, (ModUnitRegistry$1)null);
        return z2;
    }

    public ay getCostForTechLevel(String string) throws bo {
        boolean bl2 = false;
        boolean bl3 = false;
        String string2 = string.toUpperCase();
        if (string2.startsWith("CUSTOM:")) {
            string = string.substring("CUSTOM:".length());
            string = string.trim();
            bl2 = true;
        }
        if (string2.startsWith("CUSTOM|")) {
            string = string.substring("CUSTOM|".length());
            string = string.trim();
            bl2 = true;
        }
        if (string2.startsWith("BUILTIN:") || string2.startsWith("BUILTIN|")) {
            string = string.substring("BUILTIN:".length());
            string = string.trim();
            bl3 = true;
        }
        if (bl2) {
            for (ay ay2 : (java.util.Collection<ay>) (java.util.Collection) this.gd) {
                if (!string.equalsIgnoreCase(ay2.name)) continue;
                return ay2;
            }
            throw new bo("Failed to find custom effect with the name:" + string);
        }
        if (string.contains(":")) {
            throw new bo("Unknown effect format:" + string + " expected built-in effect or CUSTOM:");
        }
        if (string.contains("|")) {
            throw new bo("Unknown effect format:" + string + " expected built-in effect or CUSTOM|");
        }
        if (!bl3) {
            for (ay ay3 : (java.util.Collection<ay>) (java.util.Collection) this.gd) {
                if (!string.equalsIgnoreCase(ay3.name)) continue;
                return ay3;
            }
        }
        if ("small".equalsIgnoreCase(string)) {
            return new ay(com.corrodinggames.rts.game.units.custom.az.a);
        }
        if ("medium".equalsIgnoreCase(string)) {
            return new ay(com.corrodinggames.rts.game.units.custom.az.b);
        }
        if ("large".equalsIgnoreCase(string)) {
            return new ay(com.corrodinggames.rts.game.units.custom.az.c);
        }
        if ("smoke".equalsIgnoreCase(string)) {
            return new ay(com.corrodinggames.rts.game.units.custom.az.d);
        }
        if ("shockwave".equalsIgnoreCase(string)) {
            return new ay(com.corrodinggames.rts.game.units.custom.az.e);
        }
        if ("largeExplosion".equalsIgnoreCase(string)) {
            return new ay(com.corrodinggames.rts.game.units.custom.az.f);
        }
        if ("smallExplosion".equalsIgnoreCase(string)) {
            return new ay(com.corrodinggames.rts.game.units.custom.az.g);
        }
        if ("resourcePoolSmoke".equalsIgnoreCase(string)) {
            return new ay(com.corrodinggames.rts.game.units.custom.az.h);
        }
        if ("none".equalsIgnoreCase(string)) {
            return new ay(com.corrodinggames.rts.game.units.custom.az.i);
        }
        throw new bo("Failed to find built-in or custom effect with the name:" + string);
    }


    public boolean C() {
        return this.ce;
    }


    public boolean isLocked() {
        GlobalState l2;
        if (this.cg && (l2 = com.corrodinggames.rts.gameFramework.GlobalState.B()).O() && l2.bX.ay.i) {
            return true;
        }
        return this.cf;
    }


    public int getCreditCost() {
        return this.ch.a();
    }


    public CustomActionBase getBaseCost() {
        return this.ch;
    }


    public CustomActionBase B() {
        return this.cj;
    }


    public CustomActionBase getCostForTechLevel(int n2) {
        return this.ch;
    }


    public float D() {
        return this.ck;
    }


    public int g() {
        return this.cl;
    }
    public String v() {
        return this.M;
    }
    @Override
    public boolean w() {
        return this.isLocked();
    }

    @Override
    public int c() {
        return this.getCreditCost();
    }

    @Override
    public int b(int n2) {
        return this.getCreditCost();
    }

    public int b(String string) {  // 02b l.java L551: 按名称查找单位定义索引 (v19.133f6 补缺)
        string = ModUnitRegistry.a(string);
        com.corrodinggames.rts.gameFramework.GlobalState.e("name:" + string);
        for (int i = 0; i < this.ax.length; ++i) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("checking:" + this.ax[i].b);
            if (string.equals(this.ax[i].b)) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("got");
                return i;
            }
        }
        return -1;
    }

    @Override
    public com.corrodinggames.rts.game.units.actions.SellAction d() {
        return this.actionHandler;
    }

    @Override
    public CustomActionBase u() {
        return this.getBaseCost();
    }

    @Override
    public CustomActionBase d(int n2) {
        return this.getCostForTechLevel(n2);
    }

    @Override
    public com.corrodinggames.rts.gameFramework.rendering.Texture z() {
        return this.aw;
    }

    @Override
    public boolean y() {
        return this.usesCreditResources;
    }

    @Override
    public boolean j() {
        return this.aH;
    }

    @Override
    public boolean k() {
        return this.aI;
    }

    @Override
    public boolean m() {
        return this.fq;
    }

    @Override
    public boolean n() {
        return this.fr;
    }

    @Override
    public com.corrodinggames.rts.game.units.MovementTypeEnum o() {
        return this.fg;
    }

    @Override
    public boolean p() {
        return this.aJ;
    }

    @Override
    public be q() {
        return this.ff;
    }

    @Override
    public void h() {
        this.actionCache = null;
        this.specialActionsByTier = new com.corrodinggames.rts.game.units.at[3];
        for (int i = 1; i <= 3; ++i) {
            com.corrodinggames.rts.game.units.at at2 = new com.corrodinggames.rts.game.units.at();
            this.a(at2.a, i);
            this.specialActionsByTier[i - 1] = at2;
        }
    }




    public UnitInstance a() {
        return ModUnitRegistry.a(false, this);
    }

    public UnitInstance a(boolean bl2) {
        return ModUnitRegistry.a(bl2, this);
    }

    public void a(ArrayList arrayList, int n2) {
        if (this.eM != 0 && this.eT) {
            arrayList.add(com.corrodinggames.rts.game.units.projectiles.SpecialBuilding.i);
            arrayList.add(com.corrodinggames.rts.game.units.projectiles.SpecialBuilding.j);
        }
    }


    public ArrayList a(int n2) {
        if (this.specialActionsByTier == null) {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            boolean bl2 = d.contains(this);
            throw new RuntimeException("specialActionLists==null for:" + this.M + " t:" + n2 + " networked:" + l2.N() + " replay:" + l2.cb.j() + " sandbox:" + l2.bv + " active: " + bl2);
        }
        return this.specialActionsByTier[n2 - 1].a;
    }

    public void r() {
        ArrayList arrayList = this.a(this.cl);
        if (arrayList.size() > 4) {
            this.actionCache = new HashMap();
            int n2 = arrayList.size();
            for (int i2 = 0; i2 < n2; ++i2) {
                com.corrodinggames.rts.game.units.actions.GameAction s2 = (com.corrodinggames.rts.game.units.actions.GameAction)arrayList.get(i2);
                if (this.actionCache.get(s2.N()) != null) continue;
                this.actionCache.put(s2.N(), s2);
            }
        }
    }

    public com.corrodinggames.rts.game.units.actions.GameAction a(com.corrodinggames.rts.game.units.actions.ActionId c2) {  // 02 铁证: a(a.c)→ActionId
        if (this.actionCache != null) {
            return (com.corrodinggames.rts.game.units.actions.GameAction)this.actionCache.get(c2);
        }
        ArrayList arrayList = this.a(this.cl);
        int n2 = arrayList.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.actions.GameAction s2 = (com.corrodinggames.rts.game.units.actions.GameAction)arrayList.get(i2);
            if (!s2.d(c2)) continue;
            return s2;
        }
        return null;
    }


    public int getUnitNameWithoutModPrefix(int n2) {
        int n3 = this.getCreditCost();
        return n3;
    }


    public com.corrodinggames.rts.game.units.actions.SellAction getCostForTechLevel() {
        return this.actionHandler;
    }

    public ActionBinding a(String string, String string2, String string3) {
        if (string == null) {
            return null;
        }
        ActionBinding v2 = new ActionBinding();
        v2.a = string2;
        v2.b = string3;
        v2.c = string;
        this.p.add(v2);
        return v2;
    }

    public CustomPhysics getUnitNameWithoutModPrefix(String string, String string2, String string3) {
        CustomPhysics x2 = new CustomPhysics();
        x2.a = string2;
        x2.b = string3;
        x2.c = "(known unit:)" + this.i();
        x2.d = this;
        x2.e = true;
        x2.g = string;
        this.p.add(x2);
        return x2;
    }

    public UnitActionDef getCreditCost(String string, String string2, String string3) {
        if (string == null || string.trim().equals("")) {
            return null;
        }
        UnitActionDef u2 = new UnitActionDef();
        u2.c = string2;
        u2.d = string3;
        for (String string4 : GameUtils.c(string, ',')) {
            string4 = string4.trim();
            u2.a.add(string4);
        }
        this.gp.add(u2);
        return u2;
    }

    public static ActionBinding a(UnitTypeHandle as2) {
        if (as2 == null) {
            return null;
        }
        ActionBinding v2 = new ActionBinding();
        v2.a = "known";
        v2.d = as2;
        v2.e = true;
        return v2;
    }

    public static ModUnitRegistry getCreditCost(int n2) {
        int n3;
        if (n2 >= 100 && (n3 = n2 - 100) < g.size()) {
            ModUnitRegistry l2 = (ModUnitRegistry) g.get(n3);
            return l2;
        }
        return null;
    }

    public com.corrodinggames.rts.game.units.custom.actions.d g(String string) {
        for (com.corrodinggames.rts.game.units.custom.actions.d d2 : (java.util.Collection<com.corrodinggames.rts.game.units.custom.actions.d>) (java.util.Collection) this.gh) {
            if (d2.actionDescription == null || !d2.actionDescription.equalsIgnoreCase(string)) continue;
            return d2;
        }
        return null;
    }

    public void a(com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase a2) {
        if (!this.h.contains(a2)) {
            this.h.add(a2);
        }
    }

    public void getUnitNameWithoutModPrefix(com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase a2) {
        if (!this.i.contains(a2)) {
            this.i.add(a2);
        }
    }

    public void a(CustomActionBase b2) {
        if (b2 != null && b2.b != 0) {
            if (this.gs) {
                com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("usesCreditResources:" + b2);
            }
            this.usesCreditResources = true;
        }
    }

    public EffectConfig a(com.corrodinggames.rts.game.units.custom.effects.LogicBoolean a2) {  // 02b custom/l.a(e.a) → e/d (EffectConfig); e.a = effects.LogicBoolean
        for (EffectConfig d2 : (java.util.Collection<EffectConfig>) (java.util.Collection) this.j) {
            if (d2.b != a2) continue;
            return d2;
        }
        return null;
    }

    public com.corrodinggames.rts.game.units.custom.conditions.a a(com.corrodinggames.rts.game.units.custom.TeamTag g2) {
        for (com.corrodinggames.rts.game.units.custom.conditions.a a2 : (java.util.Collection<com.corrodinggames.rts.game.units.custom.conditions.a>) (java.util.Collection) this.l) {
            if (a2.g != g2) continue;
            return a2;
        }
        return null;
    }


    public boolean usesCreditResources() {
        return this.usesCreditResources;
    }

    /* 02b l.java L1127: as2.e 抛 IOException (R8 移除 throws) */
    public static void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.e("customUnits");
        as2.a(1);
        as2.a(d.size());
        for (ModUnitRegistry l2 : (java.util.Collection<ModUnitRegistry>) (java.util.Collection) d) {
            as2.c(l2.M);
            as2.a(l2.H);
            as2.a(true);
            as2.b(l2.t());
            long l3 = 0L;
            if (l2.J != null && l2.J.k != 0L) {
                l3 = l2.J.k;
            }
            as2.a(l3);
            long l4 = 0L;
            as2.a(l4);
        }
        as2.a("customUnits");
    }

    public static void a(ab ab2, HashMap hashMap) throws bd {
        Object object;
        Object object222;
        boolean bl2;
        ArrayList<Object> arrayList = new ArrayList<Object>();
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        boolean bl3 = bl2 = ab2.a == null;
        if (!bl2) {
            Object object3;
            for (Object object222_867 : hashMap.values()) {
                if (((ac)object222_867).b == 0 && ((ac)object222_867).d == 0 && ((ac)object222_867).c > 0) {
                    arrayList.add(object222_867);
                    continue;
                }
                if (((ac)object222_867).c <= 0 && ((ac)object222_867).d <= 0) continue;
                arrayList2.add(object222_867);
            }
            object = null;
            object222 = "";
            if (arrayList.size() > 0) {
                object3 = "";
                boolean bl4 = true;
                for (ac ac2 : (java.util.Collection<ac>) (java.util.Collection) arrayList) {
                    if (bl4) {
                        bl4 = false;
                    } else {
                        object3 = (String)object3 + ", \n";
                    }
                    object3 = (String)object3 + "'" + ac2.a + "'";
                }
                object3 = GameUtils.b((String)object3, 200);
                if (arrayList.size() == 1) {
                    object = "Missing 1 mod.";
                    object222 = "Missing mod: '" + ((ac)arrayList.get((int)0)).a + "'";
                } else {
                    object = "Missing " + arrayList.size() + " mods";
                    object222 = "missing mods: " + (String)object3;
                }
                object222 = (String)object222 + "\n Required by this server.";
                if (arrayList2.size() > 0) {
                    object222 = (String)object222 + "\n and " + arrayList2.size() + " mods are different.";
                }
            } else if (arrayList2.size() > 0) {
                object3 = "";
                boolean bl5 = true;
                for (ac ac3 : (java.util.Collection<ac>) (java.util.Collection) arrayList2) {
                    if (bl5) {
                        bl5 = false;
                    } else {
                        object3 = (String)object3 + ", \n";
                    }
                    object3 = (String)object3 + "'" + ac3.a + "'";
                }
                object3 = GameUtils.b((String)object3, 200);
                object = "Different mod data.";
                object222 = "Different mod data for: " + (String)object3 + " \n Check these mods are the same version as the server you are connecting to.";
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping nice message: completelyMissedMods:" + arrayList.size() + " differentMods:" + arrayList2.size());
            }
            if (object != null) {
                object3 = new bd((String)object222, "");
                ((bd)object3).a = (String)object;
                throw (bd)object3;
            }
        }
        object = "from internal units";
        if (ab2.a != null) {
            object = "from mod:'" + ab2.a + "'";
        }
        if ((object222 = com.corrodinggames.rts.gameFramework.GlobalState.B().bZ.f(ab2.a)) != null) {
            object = !((com.corrodinggames.rts.gameFramework.mods.ModInfo)object222).m() ? (String)object + " (You seem to have this mod but it is not enabled)" : (String)object + " (You seem to have this mod but it might be a different version)";
        }
        if (ab2.d == -1) {
            throw new bd("The server requires the unit:" + ab2.b + " that was not found " + (String)object, "");
        }
        throw new bd("Found unit:" + ab2.b + " but it does not match the server's copy " + (String)object, "checksum c:" + ab2.d + " s:" + ab2.c);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void a(InputNetStream k2) throws bd {
        k2.b("customUnits");
        try {
            ArrayList<ModUnitRegistry> arrayList = new ArrayList<ModUnitRegistry>();
            ArrayList<Object> arrayList2 = new ArrayList<Object>();
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            int n2 = k2.f();
            boolean bl2 = false;
            if (n2 >= 2) {
                bl2 = k2.e();
                k2.e();
            }
            int n3 = k2.f();
            for (int i2 = 0; i2 < n3; ++i2) {
                String object = k2.readString();
                int n4 = k2.f();
                boolean bl3 = k2.e();
                String string = k2.j();
                long l2 = k2.i();
                long l3 = k2.i();
                String string2 = null;
                if (bl2) {
                    string2 = k2.j();
                }
                ModUnitRegistry l4 = null;
                int n5 = -1;
                ModUnitRegistry l5 = null;
                Object object2 = c;
                synchronized (object2) {
                    for (ModUnitRegistry l6 : (java.util.Collection<ModUnitRegistry>) (java.util.Collection) c) {
                        if (!((String)object).equals(l6.M)) continue;
                        if (n4 == l6.H) {
                            l4 = l6;
                            continue;
                        }
                        l5 = l6;
                        n5 = l6.H;
                    }
                }
                object2 = (ac)hashMap.get(string);
                if (object2 == null) {
                    object2 = new ac(string);
                    hashMap.put(string, object2);
                }
                if (l4 == null) {
                    if (l5 != null) {
                        ++((ac)object2).d;
                    } else {
                        ++((ac)object2).c;
                    }
                    ab ab2 = new ab();
                    ab2.a = string;
                    ab2.b = object;
                    ab2.d = n5;
                    ab2.c = n4;
                    ab2.f = l5;
                    ab2.e = string2;
                    arrayList2.add(ab2);
                    com.corrodinggames.rts.gameFramework.GlobalState.b(ab2.a());
                    continue;
                }
                ++((ac)object2).b;
                arrayList.add(l4);
            }
            if (arrayList2.size() > 0) {
                for (Object object : arrayList2) {
                    if (((UnitState)object).a != null) continue;
                    ModUnitRegistry.a((ab)object, hashMap);
                }
                ModUnitRegistry.a((ab)arrayList2.get(0), hashMap);
            }
            e = arrayList;
        }
        finally {
            k2.d("customUnits");
        }
    }

    public void getUnitNameWithoutModPrefix(UnitTypeHandle as2) {
        if (!this.fL.contains(as2) && as2 != this) {
            this.fL.add(as2);
        }
        if (as2 instanceof ModUnitRegistry) {
            for (UnitTypeHandle as3 : (java.util.Collection<UnitTypeHandle>) (java.util.Collection) ((ModUnitRegistry) as2).fL) {
                if (this.fL.contains(as3) || as2 == this) continue;
                this.fL.add(as3);
            }
        }
    }

    public static ModUnitRegistry a(ModUnitRegistry l2) {
        for (ModUnitRegistry l3 : (java.util.Collection<ModUnitRegistry>) (java.util.Collection) d) {
            if (!l2.D.equals(l3.D)) continue;
            return l3;
        }
        for (ModUnitRegistry l3 : (java.util.Collection<ModUnitRegistry>) (java.util.Collection) d) {
            if (!l2.M.equals(l3.M)) continue;
            return l3;
        }
        return null;
    }

    public static void do_A() {
        for (Iterator iterator = com.corrodinggames.rts.game.units.UnitInstance.getUnitPool().iterator(); iterator.hasNext();) {
            UnitInstance am2 = (UnitInstance) iterator.next();
            if (!(am2 instanceof CustomUnitType)) continue;
            CustomUnitType j2 = (CustomUnitType) am2;
            ModUnitRegistry l2 = j2.x;
            if (d.contains(l2)) continue;
            ModUnitRegistry l3 = ModUnitRegistry.a(l2);
            if (l3 == null) {
                l3 = b;
            }
            if (l3 == null) continue;
            j2.a(l3, false, true);
        }
    }

    public Texture[] a(Texture e2, GameMode o2) {
        boolean bl2 = false;
        if ((this.J != null || this.eE) && !com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.disableModLazyLoad) {
            bl2 = true;
        }
        if (this.cy && this.cz || this.cE) {
            bl2 = true;
        }
        Texture[] eArray = PlayerState.a(e2, o2, bl2);
        for (int i2 = 0; i2 < eArray.length; ++i2) {
            if (!bl2 || this.J == null || !this.eE || i2 != 1) continue;
            eArray[i2].w();
        }
        ModLoader.a(eArray);
        return eArray;
    }

    public Texture a(com.corrodinggames.rts.gameFramework.utility.ab ab2, String string, String string2) {
        return this.a(ab2, string, string2, this.ab);
    }

    public Texture a(com.corrodinggames.rts.gameFramework.utility.ab ab2, String string, String string2, boolean bl2) {
        String string3 = ab2.b(string, string2, (String)null);
        return this.a(this.F, string3, bl2, string, string2);
    }

    public Texture a(String string, String string2, boolean bl2, String string3, String string4) {
        Texture e2 = ModLoader.a(string, string2, bl2, this, string3, string4);
        return e2;
    }

    public static UnitTypeHandle getCreditCost(UnitTypeHandle as2) {
        return (UnitTypeHandle) f.get(as2);
    }

    public String getUnitTypeName() {  // 02b l.i() L849: return this.M
        return this.M;
    }

    public static String E() {  // 02b l.E() L1491-1509: 拼接全部注册单位名
        String string = "";
        for (Iterator iterator = d.iterator(); iterator.hasNext(); string = string + ((ModUnitRegistry) iterator.next()).M + ", ") {
        }
        for (Iterator iterator = d.iterator(); iterator.hasNext(); ) {
            ModUnitRegistry modUnitRegistry = (ModUnitRegistry) iterator.next();
            for (Iterator iterator2 = modUnitRegistry.N.iterator(); iterator2.hasNext(); string = string + (String) iterator2.next() + ", ") {
            }
        }
        return string;
    }

    public static CustomUnitType a(boolean bl2, ModUnitRegistry l2) {
        CustomUnitType j2 = new CustomUnitType(bl2, l2);
        return j2;
    }

    public void r(String string) {
        String string2 = "Warning (on " + this.getUnitNameWithoutModPrefix() + "): " + string;
        com.corrodinggames.rts.gameFramework.GlobalState.b(string2);
        this.gt.add(string);
        if (this.J == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.B().a(string2, 1);
            if (com.corrodinggames.rts.gameFramework.GlobalState.aT) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Crashing on allowed unit warning because automated testing is active");
                throw new RuntimeException(string2);
            }
        } else {
            this.J.b(string2);
        }
    }


    public Texture getImageTexture() {
        return this.aw;
    }

    public static void do_F() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        for (ModUnitRegistry l3 : (java.util.Collection<ModUnitRegistry>) (java.util.Collection) d) {
            UnitInstance am2;
            Object object;
            ad ad2 = l3.T;
            if (ad2 == null) continue;
            if (ad2 == com.corrodinggames.rts.game.units.custom.ad.a || ad2 == com.corrodinggames.rts.game.units.custom.ad.b) {
                for (Object object2 : l2.bL.A) {
                    object = l3.a();
                    ((UnitInstance) object).b(PlayerState.i);
                    l2.bL.a(((Point)object2).a, ((Point)object2).b);
                    ((UnitInstance) object).eo = l2.bL.scrollPixelX;
                    ((UnitInstance) object).ep = l2.bL.scrollPixelY;
                    ((UnitInstance) object).eo += ((UnitInstance) object).getMapOriginX();
                    ((UnitInstance) object).ep += ((UnitInstance) object).getMapOriginY();
                    if (ad2 == com.corrodinggames.rts.game.units.custom.ad.a && object instanceof UnitType && ((UnitType) (am2 = (UnitType) object)).a((UnitInstance) null, (PlayerState)null)) {
                        ((UnitInstance) object).canBuild();
                        continue;
                    }
                    PlayerState.c((UnitInstance) object);
                }
                continue;
            }
            if (ad2 == com.corrodinggames.rts.game.units.custom.ad.c || ad2 == com.corrodinggames.rts.game.units.custom.ad.d) {
                if (ad2 == com.corrodinggames.rts.game.units.custom.ad.c) {
                    UnitInstance iterator = l3.a();
                    iterator.b(PlayerState.i);
                    l2.bL.b(l2.bL.i() / 2.0f, l2.bL.j() / 2.0f);
                    iterator.eo = l2.bL.scrollPixelX;
                    iterator.ep = l2.bL.scrollPixelY;
                    iterator.eo += iterator.getMapOriginX();
                    iterator.ep += iterator.getMapOriginY();
                    PlayerState.c(iterator);
                }
                if (ad2 != com.corrodinggames.rts.game.units.custom.ad.d) continue;
                for (Object object2 : PlayerState.c()) {
                    if (((PlayerState)object2).a(true, false) <= 0) continue;
                    object = l3.a();
                    ((UnitInstance) object).b((PlayerState)object2);
                    l2.bL.b(l2.bL.i() / 2.0f, l2.bL.j() / 2.0f);
                    ((UnitInstance) object).eo = l2.bL.scrollPixelX;
                    ((UnitInstance) object).ep = l2.bL.scrollPixelY;
                    ((UnitInstance) object).eo += ((UnitInstance) object).getMapOriginX();
                    ((UnitInstance) object).ep += ((UnitInstance) object).getMapOriginY();
                    PlayerState.c((UnitInstance) object);
                }
                continue;
            }
            if (ad2 == com.corrodinggames.rts.game.units.custom.ad.e) {
                for (Object object2 : PlayerState.c()) {
                    if (((PlayerState)object2).a(true, false) <= 0) continue;
                    object = new PointF();
                    PathfindingUtils.a((PlayerState)object2, (PointF)object);
                    am2 = l3.a();
                    am2.b((PlayerState)object2);
                    l2.bL.b(((PointF)object).a, ((PointF)object).b);
                    am2.eo = l2.bL.scrollPixelX;
                    am2.ep = l2.bL.scrollPixelY;
                    am2.eo += am2.getMapOriginX();
                    am2.ep += am2.getMapOriginY();
                    PlayerState.c(am2);
                }
                continue;
            }
            com.corrodinggames.rts.gameFramework.GlobalState.b("onNewMapSpawn unhandled: " + (Object)((Object)l3.T));
        }
    }


    public int a(UnitInstance am2) {
        int n2 = 0;
        if (this.aL > 0.0f) {
            return (int)this.aL;
        }
        if (this.aJ && this.aH && am2.isDamaged() < 20) {
            n2 += 17;
        }
        return n2;
    }

    public void a(String string, int n2, String string2, String string3) throws bo {
        if (this.J == null) {
            return;
        }
        if (this.J.checkedVersion >= n2) {
            return;
        }
        if (!this.J.modInfoParsed) {
            return;
        }
        if (this.J.minVersion == null) {
            throw new bo("[" + string2 + "] minVersion of " + string + " is required to be set in mod-info.txt at the root of this mod to use " + string3);
        }
        try {
            com.corrodinggames.rts.gameFramework.mods.VersionChecker.a(string, this.J.minVersion);
        }
        catch (bo bo2) {
            throw new bo("[" + string2 + "]" + string3 + " " + bo2.getMessage() + " to be set PacketBuilder minVersion in mod-info.txt");
        }
        this.J.checkedVersion = n2;
    }

    public static UnitTypeHandle a(String string, boolean bl2) {
        UnitTypeHandle as2 = com.corrodinggames.rts.game.units.UnitRegistry.a(string, bl2);
        if (as2 == com.corrodinggames.rts.game.units.UnitRegistry.Y) {
            return com.corrodinggames.rts.game.units.UnitRegistry.h;
        }
        return as2;
    }

    static {
        c = new ArrayList();
        d = new ArrayList();
        e = null;
        f = new HashMap();
        g = new ArrayList();
        defaultNotOverLiquidCheck = LogicBoolean.create(null, "if not self.isOverLiquid() and not self.isMoving()");
        defaultNotOverLiquidCheckMoving = LogicBoolean.create(null, "if not self.isOverLiquid()");
        emptyEffectArray = new ay[0];
        noneEffectArray = new ay[0];
    }


   // 02b custom.l.n(String) L1466-1495 直译: 按名称查 ModUnitRegistry (d 静态列表 + M/N 字段)
   public static ModUnitRegistry n(String var0) {
      java.util.Iterator var1 = d.iterator();
      ModUnitRegistry var2;
      do {
         if(!var1.hasNext()) {
            var1 = d.iterator();
            do {
               if(!var1.hasNext()) {
                  return null;
               }
               var2 = (ModUnitRegistry)var1.next();
            } while(!var2.N.contains(var0));
            return var2;
         }
         var2 = (ModUnitRegistry)var1.next();
      } while(!var2.M.contains(var0));
      return var2;
   }

    // ===== v19.115p 批5 补缺: 02b 字节码铁证 l.class =====
    // javap: public final utility.m aA; public String i(); public b.n i(String)
    public String i() {  // 02b l.i() L849-851: return this.M (单位显示名)
        return this.M;
    }
    public com.corrodinggames.rts.game.units.custom.animation.UnitTrait i(String string) {
        // 02b l.i(String)→custom.b.n: 按名查附挂槽位 (e.java 调用) — 简化 TODO
        return null;
    }

    public CustomVisuals c(String string) {
        // v19.115q ay战役补缺: 02b l.java L595 c(String)→z (new z(this,var1,null))
        // ay.java alsoEmitEffects/alsoEmitEffectsOnDeath/trailEffect/ifSpawnFailsEmitEffects 调用点 — 简化 TODO
        return null;
    }

    public com.corrodinggames.rts.game.units.custom.effects.LogicBoolean j(String string) {
        // v19.115r logicBooleans 批2 补缺: javap l.j(String)→e.a 铁证 (IsResourceLargerThan 等 meta.j 调用点) — 简化 TODO
        return null;
    }

    public Object l(String string) {
        // v19.115r logicBooleans 批6 补缺: javap l.l(String)→custom.c.a 铁证 (NumberOfConnectionsBoolean) — 简化 TODO
        return null;
    }
    public static UnitTypeHandle m(String string) {  // 02b l.m(String): 遍历 f 键集按 i() 匹配 (L1447-1460)
        Iterator iterator = f.keySet().iterator();
        UnitTypeHandle unitTypeHandle;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
            unitTypeHandle = (UnitTypeHandle)iterator.next();
        } while (!unitTypeHandle.i().equals(string));
        return (UnitTypeHandle)f.get(unitTypeHandle);
    }

    public String t() {  // 02b l.t() L1123: 返回 ModInfo 名 (J.a())
        return this.J != null ? this.J.a() : null;
    }

    public void o(String string) {  // 02b l.o(String) L1519: 注册 tag 监视 (aa=ModFileWatcher)
        if (!com.corrodinggames.rts.gameFramework.utility.ag.i(string)) {
            aa aa2 = new aa(string);
            this.k.add(aa2);
        }
    }

    public void p(String string) {  // 02b l.p(String) L1526: 报告单位加载错误
        ModLoader.a(this.i(), (Exception)(new bo(string)), (com.corrodinggames.rts.game.units.UnitTypeHandle)this);
    }

    public static UnitTypeHandle s(String string) {  // 02b l.s(String) L1689
        return a(string, true);
    }


    public com.corrodinggames.rts.game.units.custom.effects.LogicBoolean k(String string) {  // 02b l.k(String) L1059-1072: 按名查找 EffectConfig 效果
        Iterator iterator = this.j.iterator();
        com.corrodinggames.rts.game.units.custom.effects.EffectConfig effectConfig;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
            effectConfig = (com.corrodinggames.rts.game.units.custom.effects.EffectConfig)iterator.next();
        } while (!effectConfig.unitConfigId.equalsIgnoreCase(string));
        return effectConfig.b;
    }


    public com.corrodinggames.rts.game.units.custom.bh f(String string) {  // 02b l.f(String) L967: 查找项目符号模板
        for (com.corrodinggames.rts.game.units.custom.bh bh2 : (java.util.Collection<com.corrodinggames.rts.game.units.custom.bh>) (java.util.Collection) this.fS) {
            if (bh2.a.equals(string)) {
                return bh2;
            }
        }
        return null;
    }



    public boolean l() {  // 02b l.l() L769-771: return this.fp
        return this.fp;
    }


    @Override
    public UnitConfig x() {  // 02b l.x() L948-950: return this.O
        return this.O;
    }

}
