package com.corrodinggames.rts.game.units.custom;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.custom.aa;
import com.corrodinggames.rts.game.units.custom.ab;
import com.corrodinggames.rts.game.units.custom.ac;
import com.corrodinggames.rts.game.units.custom.ad;
import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.ay;
import com.corrodinggames.rts.game.units.custom.az;
import com.corrodinggames.rts.game.units.custom.ba;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.bd;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bl;
import com.corrodinggames.rts.game.units.custom.bn;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.f;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l$1;
import com.corrodinggames.rts.game.units.custom.m;
import com.corrodinggames.rts.game.units.custom.n;
import com.corrodinggames.rts.game.units.custom.o;
import com.corrodinggames.rts.game.units.custom.r;
import com.corrodinggames.rts.game.units.custom.s;
import com.corrodinggames.rts.game.units.custom.t;
import com.corrodinggames.rts.game.units.custom.u;
import com.corrodinggames.rts.game.units.custom.v;
import com.corrodinggames.rts.game.units.custom.x;
import com.corrodinggames.rts.game.units.custom.z;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableMapping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public final class l implements com.corrodinggames.rts.game.units.as {

   public static final Rect a = new Rect();
   public static l b;
   public static final ArrayList c = new ArrayList();
   public static ArrayList d = new ArrayList();
   public static ArrayList e = null;
   public static final HashMap f = new HashMap();
   public static ArrayList g = new ArrayList();
   public final com.corrodinggames.rts.gameFramework.utility.m h = new com.corrodinggames.rts.gameFramework.utility.m();
   public final com.corrodinggames.rts.gameFramework.utility.m i = new com.corrodinggames.rts.gameFramework.utility.m();
   public final com.corrodinggames.rts.gameFramework.utility.m j = new com.corrodinggames.rts.gameFramework.utility.m();
   final com.corrodinggames.rts.gameFramework.utility.m k = new com.corrodinggames.rts.gameFramework.utility.m();
   public final com.corrodinggames.rts.gameFramework.utility.m l = new com.corrodinggames.rts.gameFramework.utility.m();
   public final com.corrodinggames.rts.gameFramework.utility.m m = new com.corrodinggames.rts.gameFramework.utility.m();
   public final com.corrodinggames.rts.gameFramework.utility.m n = new com.corrodinggames.rts.gameFramework.utility.m();
   public final com.corrodinggames.rts.gameFramework.utility.m o = new com.corrodinggames.rts.gameFramework.utility.m();
   final com.corrodinggames.rts.gameFramework.utility.m p = new com.corrodinggames.rts.gameFramework.utility.m();
   public final com.corrodinggames.rts.gameFramework.utility.m q = new com.corrodinggames.rts.gameFramework.utility.m();
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
   public com.corrodinggames.rts.gameFramework.i.b J;
   public String K;
   public String L;
   public String M;
   public com.corrodinggames.rts.gameFramework.utility.m N = new com.corrodinggames.rts.gameFramework.utility.m();
   public h O;
   public h P;
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
   public com.corrodinggames.rts.game.o ac;
   public com.corrodinggames.rts.gameFramework.m.e ad = null;
   public boolean ae = true;
   public int af;
   public int ag;
   public int ah;
   public int ai;
   public float aj;
   public boolean ak;
   public com.corrodinggames.rts.gameFramework.m.e al = null;
   public boolean am;
   public com.corrodinggames.rts.gameFramework.m.e an = null;
   public com.corrodinggames.rts.gameFramework.m.e ao = null;
   public com.corrodinggames.rts.gameFramework.m.e ap = null;
   public boolean aq;
   public com.corrodinggames.rts.gameFramework.m.e[] ar = new com.corrodinggames.rts.gameFramework.m.e[10];
   public com.corrodinggames.rts.gameFramework.m.e[] as;
   public com.corrodinggames.rts.gameFramework.m.e[] at = null;
   public com.corrodinggames.rts.gameFramework.m.e au = null;
   public boolean av = false;
   public com.corrodinggames.rts.gameFramework.m.e aw;
   public ba[] ax = null;
   public boolean ay = false;
   public boolean az = false;
   public final com.corrodinggames.rts.gameFramework.utility.m aA = new com.corrodinggames.rts.gameFramework.utility.m();
   public boolean aB;
   public bb aC;
   public bb aD;
   public String aE;
   public boolean aF;
   public float aG = 1.0F;
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
   public h aS;
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
   public float bd = 1.0F;
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
   public com.corrodinggames.rts.game.units.ab bt;
   public boolean bu;
   public boolean bv;
   public z bw;
   public z bx;
   public z by;
   public bl bz;
   public int bA = -1;
   public int bB = -1;
   public bp bC;
   public boolean bD;
   public boolean bE;
   public boolean bF;
   public boolean bG;
   public float bH = 1.0F;
   public float bI = 1.0F;
   boolean bJ;
   boolean bK;
   boolean bL;
   boolean bM;
   boolean bN;
   z bO;
   z bP;
   boolean bQ;
   float bR;
   boolean bS;
   float bT;
   z bU;
   z bV;
   boolean bW;
   float bX;
   z bY;
   z bZ;
   public float ca = 60.0F;
   public s cb;
   public boolean cc;
   public boolean cd;
   public boolean ce;
   public boolean cf;
   public boolean cg;
   public com.corrodinggames.rts.game.units.custom.d.b ch;
   public com.corrodinggames.rts.game.units.custom.d.b ci;
   public com.corrodinggames.rts.game.units.custom.d.b cj;
   public float ck;
   public int cl;
   public boolean cm;
   public boolean cn;
   public com.corrodinggames.rts.game.units.custom.d.b co;
   public com.corrodinggames.rts.game.units.custom.e.f cp;
   public com.corrodinggames.rts.game.units.custom.e.f cq;
   public int cr;
   public float cs;
   public VariableScope$CachedWriter ct;
   public float cu;
   public com.corrodinggames.rts.game.units.custom.d.b cv;
   public com.corrodinggames.rts.game.units.custom.d.b cw;
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
   public h cH;
   public int cI;
   public float cJ;
   public float cK;
   public as cL;
   public boolean cM;
   public float cN;
   public boolean cO;
   public float cP;
   public float cQ;
   public boolean cR;
   public float cS;
   public bb cT;
   public boolean cU;
   public float cV;
   public int cW;
   public Rect cX;
   public Rect cY;
   public Rect cZ;
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
   public bl do;
   public bl dp;
   public bl dq;
   com.corrodinggames.rts.gameFramework.utility.m dr;
   f ds;
   f dt;
   f du;
   f dv;
   f dw;
   f dx;
   f dy;
   f dz;
   f dA;
   public boolean dB;
   public boolean dC;
   public boolean dD;
   public boolean dE;
   public bn dF;
   public int dG;
   public float dH;
   m dI;
   public float dJ;
   public Boolean dK;
   public boolean dL;
   public float dM;
   public float dN;
   public float dO;
   public boolean dP;
   public boolean dQ;
   public boolean dR;
   public float dS;
   public float dT;
   public float dU;
   public float dV;
   public float dW;
   public boolean dX;
   public boolean dY;
   public int dZ;
   public float ea;
   public float eb;
   public com.corrodinggames.rts.game.units.b ec;
   public float ed;
   public float ee;
   public boolean ef;
   public boolean eg;
   public boolean eh;
   public boolean ei;
   public float ej;
   public float ek;
   public float el;
   public int em;
   public int en;
   public float eo;
   public boolean ep;
   public LogicBoolean eq;
   public LogicBoolean er;
   public LogicBoolean es;
   public LogicBoolean et;
   public boolean eu;
   public h ev;
   public h ew;
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
   public boolean eJ;
   public boolean eK;
   public boolean eL;
   public int eM;
   public float eN;
   public boolean eO;
   public h eP;
   public com.corrodinggames.rts.gameFramework.utility.m eQ;
   public boolean eR;
   public boolean eS;
   public boolean eT;
   public LogicBoolean eU;
   public LogicBoolean eV;
   public LogicBoolean eW;
   public boolean eX;
   public float eY;
   public int eZ;
   public static LogicBoolean fa = LogicBoolean.create((l)null, "if not self.isOverLiquid() and not self.isMoving()");
   public static LogicBoolean fb = LogicBoolean.create((l)null, "if not self.isOverLiquid()");
   public LogicBoolean fc;
   public LogicBoolean fd;
   public boolean fe;
   public be ff;
   public com.corrodinggames.rts.game.units.ao fg;
   public com.corrodinggames.rts.game.units.ao fh;
   public boolean fi;
   public boolean fj;
   public boolean fk;
   public h fl;
   public int fm;
   public h fn;
   public h fo;
   public boolean fp;
   public boolean fq;
   public boolean fr;
   public boolean fs;
   public boolean ft;
   public boolean fu;
   public h fv;
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
   public h fH;
   public String fI;
   public boolean fJ;
   public float fK;
   public com.corrodinggames.rts.gameFramework.utility.m fL;
   public boolean fM;
   public boolean fN;
   public h fO;
   public boolean fP;
   public bn[] fQ;
   public bh[] fR;
   ArrayList fS;
   ArrayList fT;
   boolean fU;
   bn fV;
   com.corrodinggames.rts.gameFramework.utility.m fW;
   boolean fX;
   r[] fY;
   r[] fZ;
   r[] ga;
   com.corrodinggames.rts.gameFramework.utility.m gb;
   ArrayList gc;
   ArrayList gd;
   static final ay[] ge = new ay[0];
   static final ay[] gf = new ay[0];
   ArrayList gg;
   ArrayList gh;
   public boolean gi;
   int gj;
   String gk;
   String gl;
   HashMap gm;
   com.corrodinggames.rts.game.units.at[] gn;
   com.corrodinggames.rts.game.units.a.z go;
   com.corrodinggames.rts.gameFramework.utility.m gp;
   com.corrodinggames.rts.gameFramework.utility.m gq;
   public boolean gr;
   public boolean gs;
   com.corrodinggames.rts.gameFramework.utility.m gt;


   public strictfp l() {
      this.cb = s.a;
      this.ck = 0.001F;
      this.co = com.corrodinggames.rts.game.units.custom.d.b.a;
      this.cp = com.corrodinggames.rts.game.units.custom.e.f.a;
      this.cq = com.corrodinggames.rts.game.units.custom.e.f.a;
      this.ct = null;
      this.cu = 1.0F;
      this.cv = com.corrodinggames.rts.game.units.custom.d.b.a;
      this.cw = com.corrodinggames.rts.game.units.custom.d.b.a;
      this.cI = -2;
      this.cL = new as(true);
      this.cX = new Rect();
      this.cY = new Rect();
      this.cZ = new Rect();
      this.dr = new com.corrodinggames.rts.gameFramework.utility.m();
      this.ds = new f("moving");
      this.dt = new f("idle");
      this.du = new f("attack");
      this.dS = 0.0F;
      this.dU = -1.0F;
      this.dV = 0.03F;
      this.dW = 0.06F;
      this.em = -1;
      this.en = -1;
      this.eJ = false;
      this.eK = false;
      this.eL = false;
      this.eM = 0;
      this.eQ = new com.corrodinggames.rts.gameFramework.utility.m();
      this.eZ = 1;
      this.fe = true;
      this.fK = -1.0F;
      this.fL = new com.corrodinggames.rts.gameFramework.utility.m();
      this.fQ = null;
      this.fS = new ArrayList();
      this.fT = new ArrayList();
      this.fU = false;
      this.fV = null;
      this.fW = new com.corrodinggames.rts.gameFramework.utility.m();
      this.gb = new com.corrodinggames.rts.gameFramework.utility.m();
      this.gc = new ArrayList();
      this.gd = new ArrayList();
      this.gg = new ArrayList();
      this.gh = new ArrayList();
      this.gj = -1;
      this.go = new com.corrodinggames.rts.game.units.a.z(this);
      this.gp = new com.corrodinggames.rts.gameFramework.utility.m();
      this.gq = new com.corrodinggames.rts.gameFramework.utility.m();
      this.gt = new com.corrodinggames.rts.gameFramework.utility.m();
   }

   public strictfp String b() {
      String var1 = this.D;
      if(this.J != null) {
         String var2 = this.J.q;
         if(var1.startsWith(var2)) {
            var1 = var1.substring(var2.length());
            if(var1.startsWith("/")) {
               var1 = var1.substring(1);
            }

            if(var1.startsWith("\\")) {
               var1 = var1.substring(1);
            }
         }

         var1 = var1 + " (in mod " + this.J.a() + ")";
      }

      return var1;
   }

   public strictfp o a(String var1, o var2) {
      o var3;
      if(var1 != null) {
         var3 = new o(this);
         var3.a = var1;
         var3.a();
         return var3;
      } else if(var2 != null) {
         var3 = new o(this);
         var3.a = var2.a;
         var3.a();
         return var3;
      } else {
         return null;
      }
   }

   strictfp f a(n var1, f var2, boolean var3) {
      f var4 = this.a(var1);
      if(var4 != null) {
         if(var3 && var2 != null && var2.a()) {
            throw new bo("Cannot define animation " + var1.name() + " on graphics and with onAction at same time");
         } else {
            return var4;
         }
      } else {
         return var2;
      }
   }

   public static strictfp String a(String var0) {
      var0 = var0.toLowerCase(Locale.ROOT);
      var0 = var0.trim();
      if(var0.startsWith("arm_")) {
         var0 = "arm" + var0.substring("arm_".length());
      }

      if(var0.startsWith("leg_")) {
         var0 = "leg" + var0.substring("leg_".length());
      }

      return var0;
   }

   public strictfp int b(String var1) {
      var1 = a(var1);
      com.corrodinggames.rts.gameFramework.l.e("name:" + var1);

      for(int var2 = 0; var2 < this.ax.length; ++var2) {
         com.corrodinggames.rts.gameFramework.l.e("checking:" + this.ax[var2].b);
         if(var1.equals(this.ax[var2].b)) {
            com.corrodinggames.rts.gameFramework.l.e("got");
            return var2;
         }
      }

      return -1;
   }

   strictfp f a(n var1) {
      Iterator var2 = this.dr.iterator();

      f var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (f)var2.next();
      } while(!var3.a(var1));

      return var3;
   }

   public strictfp void a(t var1) {
      this.gb.add(var1);
   }

   public strictfp z a(String var1, z var2) {
      if(var1 == null && var2 != null) {
         return var2;
      } else {
         z var3 = new z(this, var1, (l$1)null);
         var3.c();
         return var3;
      }
   }

   public strictfp z c(String var1) {
      z var2 = new z(this, var1, (l$1)null);
      return var2;
   }

   public strictfp ay d(String var1) {
      boolean var2 = false;
      boolean var3 = false;
      String var4 = var1.toUpperCase();
      if(var4.startsWith("CUSTOM:")) {
         var1 = var1.substring("CUSTOM:".length());
         var1 = var1.trim();
         var2 = true;
      }

      if(var4.startsWith("CUSTOM|")) {
         var1 = var1.substring("CUSTOM|".length());
         var1 = var1.trim();
         var2 = true;
      }

      if(var4.startsWith("BUILTIN:") || var4.startsWith("BUILTIN|")) {
         var1 = var1.substring("BUILTIN:".length());
         var1 = var1.trim();
         var3 = true;
      }

      Iterator var5;
      ay var6;
      if(var2) {
         var5 = this.gd.iterator();

         do {
            if(!var5.hasNext()) {
               throw new bo("Failed to find custom effect with the name:" + var1);
            }

            var6 = (ay)var5.next();
         } while(!var1.equalsIgnoreCase(var6.name));

         return var6;
      } else if(var1.contains(":")) {
         throw new bo("Unknown effect format:" + var1 + " expected built-in effect or CUSTOM:");
      } else if(var1.contains("|")) {
         throw new bo("Unknown effect format:" + var1 + " expected built-in effect or CUSTOM|");
      } else {
         if(!var3) {
            var5 = this.gd.iterator();

            while(var5.hasNext()) {
               var6 = (ay)var5.next();
               if(var1.equalsIgnoreCase(var6.name)) {
                  return var6;
               }
            }
         }

         if("small".equalsIgnoreCase(var1)) {
            return new ay(az.a);
         } else if("medium".equalsIgnoreCase(var1)) {
            return new ay(az.b);
         } else if("large".equalsIgnoreCase(var1)) {
            return new ay(az.c);
         } else if("smoke".equalsIgnoreCase(var1)) {
            return new ay(az.d);
         } else if("shockwave".equalsIgnoreCase(var1)) {
            return new ay(az.e);
         } else if("largeExplosion".equalsIgnoreCase(var1)) {
            return new ay(az.f);
         } else if("smallExplosion".equalsIgnoreCase(var1)) {
            return new ay(az.g);
         } else if("resourcePoolSmoke".equalsIgnoreCase(var1)) {
            return new ay(az.h);
         } else if("none".equalsIgnoreCase(var1)) {
            return new ay(az.i);
         } else {
            throw new bo("Failed to find built-in or custom effect with the name:" + var1);
         }
      }
   }

   public strictfp boolean C() {
      return this.ce;
   }

   public strictfp boolean w() {
      if(this.cg) {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         if(var1.O() && var1.bX.ay.i) {
            return true;
         }
      }

      return this.cf;
   }

   public strictfp int c() {
      return this.ch.a();
   }

   public strictfp com.corrodinggames.rts.game.units.custom.d.b u() {
      return this.ch;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.d.b B() {
      return this.cj;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.d.b d(int var1) {
      return this.ch;
   }

   public strictfp float D() {
      return this.ck;
   }

   public strictfp int g() {
      return this.cl;
   }

   public strictfp com.corrodinggames.rts.game.units.am a() {
      return a(false, this);
   }

   public strictfp com.corrodinggames.rts.game.units.am a(boolean var1) {
      return a(var1, this);
   }

   public strictfp String e() {
      if(this.gj != com.corrodinggames.rts.gameFramework.h.a.c || this.gk == null) {
         this.gj = com.corrodinggames.rts.gameFramework.h.a.c;
         String var1 = this.aC != null?this.aC.b():this.M;
         String var2 = this.M;
         if(this.aE != null) {
            var2 = this.aE;
         }

         this.gk = com.corrodinggames.rts.gameFramework.h.a.a("units." + var2 + ".name", var1, new Object[0]);
      }

      return this.gk;
   }

   public strictfp String f() {
      if(this.gj != com.corrodinggames.rts.gameFramework.h.a.c || this.gl == null) {
         this.gj = com.corrodinggames.rts.gameFramework.h.a.c;
         String var1 = this.aD != null?this.aD.b():this.M;
         String var2 = this.M;
         if(this.aE != null) {
            var2 = this.aE;
         }

         this.gl = com.corrodinggames.rts.gameFramework.h.a.a("units." + var2 + ".description", var1, new Object[0]);
      }

      return this.gl;
   }

   public strictfp boolean j() {
      return this.aH;
   }

   public strictfp boolean k() {
      return this.aI;
   }

   public strictfp boolean m() {
      return this.fq;
   }

   public strictfp boolean n() {
      return this.fr;
   }

   public strictfp boolean l() {
      return this.fp;
   }

   public strictfp com.corrodinggames.rts.game.units.ao o() {
      return this.fg;
   }

   public strictfp boolean p() {
      return this.aJ;
   }

   public strictfp be q() {
      return this.ff;
   }

   public strictfp void a(ArrayList var1, int var2) {
      if(this.eM != 0 && this.eT) {
         var1.add(com.corrodinggames.rts.game.units.e.i.i);
         var1.add(com.corrodinggames.rts.game.units.e.i.j);
      }

   }

   public strictfp void h() {
      this.gm = null;
      this.gn = new com.corrodinggames.rts.game.units.at[3];

      for(int var1 = 1; var1 <= 3; ++var1) {
         com.corrodinggames.rts.game.units.at var2 = new com.corrodinggames.rts.game.units.at();
         this.a(var2.a, var1);
         this.gn[var1 - 1] = var2;
      }

   }

   public strictfp ArrayList a(int var1) {
      if(this.gn == null) {
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         boolean var3 = d.contains(this);
         throw new RuntimeException("specialActionLists==null for:" + this.M + " t:" + var1 + " networked:" + var2.N() + " replay:" + var2.cb.j() + " sandbox:" + var2.bv + " active: " + var3);
      } else {
         return this.gn[var1 - 1].a;
      }
   }

   public strictfp void r() {
      ArrayList var1 = this.a(this.cl);
      if(var1.size() > 4) {
         this.gm = new HashMap();
         int var2 = 0;

         for(int var3 = var1.size(); var2 < var3; ++var2) {
            com.corrodinggames.rts.game.units.a.s var4 = (com.corrodinggames.rts.game.units.a.s)var1.get(var2);
            if(this.gm.get(var4.N()) == null) {
               this.gm.put(var4.N(), var4);
            }
         }
      }

   }

   public strictfp com.corrodinggames.rts.game.units.a.s a(com.corrodinggames.rts.game.units.a.c var1) {
      if(this.gm != null) {
         return (com.corrodinggames.rts.game.units.a.s)this.gm.get(var1);
      } else {
         ArrayList var2 = this.a(this.cl);
         int var3 = 0;

         for(int var4 = var2.size(); var3 < var4; ++var3) {
            com.corrodinggames.rts.game.units.a.s var5 = (com.corrodinggames.rts.game.units.a.s)var2.get(var3);
            if(var5.d(var1)) {
               return var5;
            }
         }

         return null;
      }
   }

   public strictfp String i() {
      return this.M;
   }

   public strictfp String v() {
      return this.M;
   }

   public strictfp int b(int var1) {
      int var2 = this.c();
      return var2;
   }

   public strictfp com.corrodinggames.rts.game.units.a.z d() {
      return this.go;
   }

   public strictfp v a(String var1, String var2, String var3) {
      if(var1 == null) {
         return null;
      } else {
         v var4 = new v();
         var4.a = var2;
         var4.b = var3;
         var4.c = var1;
         this.p.add(var4);
         return var4;
      }
   }

   public strictfp x b(String var1, String var2, String var3) {
      x var4 = new x();
      var4.a = var2;
      var4.b = var3;
      var4.c = "(known unit:)" + this.i();
      var4.d = this;
      var4.e = true;
      var4.g = var1;
      this.p.add(var4);
      return var4;
   }

   public strictfp u c(String var1, String var2, String var3) {
      if(var1 != null && !var1.trim().equals("")) {
         u var4 = new u();
         var4.c = var2;
         var4.d = var3;
         String[] var5 = com.corrodinggames.rts.gameFramework.f.c(var1, ',');
         int var6 = var5.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            String var8 = var5[var7];
            var8 = var8.trim();
            var4.a.add(var8);
         }

         this.gp.add(var4);
         return var4;
      } else {
         return null;
      }
   }

   public static strictfp v a(com.corrodinggames.rts.game.units.as var0) {
      if(var0 == null) {
         return null;
      } else {
         v var1 = new v();
         var1.a = "known";
         var1.d = var0;
         var1.e = true;
         return var1;
      }
   }

   public static strictfp l c(int var0) {
      if(var0 >= 100) {
         int var1 = var0 - 100;
         if(var1 < g.size()) {
            l var2 = (l)g.get(var1);
            return var2;
         }
      }

      return null;
   }

   public static strictfp ArrayList s() {
      ArrayList var0 = new ArrayList();
      int var1 = 100;

      for(Iterator var2 = g.iterator(); var2.hasNext(); ++var1) {
         l var3 = (l)var2.next();
         var0.add(Integer.valueOf(var1));
      }

      return var0;
   }

   public strictfp h x() {
      return this.O;
   }

   public strictfp bn e(String var1) {
      Iterator var2 = this.fS.iterator();

      bn var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (bn)var2.next();
      } while(!var3.a.equalsIgnoreCase(var1));

      return var3;
   }

   public strictfp bh f(String var1) {
      Iterator var2 = this.fT.iterator();

      bh var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (bh)var2.next();
      } while(!var3.bh.equalsIgnoreCase(var1));

      return var3;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.a.d g(String var1) {
      Iterator var2 = this.gh.iterator();

      com.corrodinggames.rts.game.units.custom.a.d var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (com.corrodinggames.rts.game.units.custom.a.d)var2.next();
      } while(var3.c == null || !var3.c.equalsIgnoreCase(var1));

      return var3;
   }

   public strictfp com.corrodinggames.rts.game.units.a.s h(String var1) {
      ArrayList var2 = this.a(this.cl);
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         com.corrodinggames.rts.game.units.a.s var4 = (com.corrodinggames.rts.game.units.a.s)var3.next();
         if(var4 instanceof com.corrodinggames.rts.game.units.custom.a.g) {
            com.corrodinggames.rts.game.units.custom.a.g var5 = (com.corrodinggames.rts.game.units.custom.a.g)var4;
            if(var5.a.c.equalsIgnoreCase(var1)) {
               return var5;
            }
         }
      }

      return null;
   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.b.a var1) {
      if(!this.h.contains(var1)) {
         this.h.add(var1);
      }

   }

   public strictfp void b(com.corrodinggames.rts.game.units.custom.b.a var1) {
      if(!this.i.contains(var1)) {
         this.i.add(var1);
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.d.b var1) {
      if(var1 != null && var1.b != 0) {
         if(this.gs) {
            com.corrodinggames.rts.gameFramework.l.g("usesCreditResources:" + var1);
         }

         this.gr = true;
      }

   }

   public strictfp com.corrodinggames.rts.game.units.custom.b.n i(String var1) {
      Iterator var2 = this.aA.iterator();

      com.corrodinggames.rts.game.units.custom.b.n var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (com.corrodinggames.rts.game.units.custom.b.n)var2.next();
      } while(!var3.b().equalsIgnoreCase(var1));

      return var3;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.e.a j(String var1) {
      com.corrodinggames.rts.game.units.custom.e.a var2 = com.corrodinggames.rts.game.units.custom.e.a.a(var1);
      return var2 != null?var2:this.k(var1);
   }

   public strictfp com.corrodinggames.rts.game.units.custom.e.a k(String var1) {
      Iterator var2 = this.j.iterator();

      com.corrodinggames.rts.game.units.custom.e.d var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (com.corrodinggames.rts.game.units.custom.e.d)var2.next();
      } while(!var3.a.equalsIgnoreCase(var1));

      return var3.b;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.e.d a(com.corrodinggames.rts.game.units.custom.e.a var1) {
      Iterator var2 = this.j.iterator();

      com.corrodinggames.rts.game.units.custom.e.d var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (com.corrodinggames.rts.game.units.custom.e.d)var2.next();
      } while(var3.b != var1);

      return var3;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.c.a a(g var1) {
      Iterator var2 = this.l.iterator();

      com.corrodinggames.rts.game.units.custom.c.a var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (com.corrodinggames.rts.game.units.custom.c.a)var2.next();
      } while(var3.g != var1);

      return var3;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.c.a l(String var1) {
      Iterator var2 = this.l.iterator();

      com.corrodinggames.rts.game.units.custom.c.a var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (com.corrodinggames.rts.game.units.custom.c.a)var2.next();
      } while(!var3.g.a.equals(var1));

      return var3;
   }

   public strictfp boolean y() {
      return this.gr;
   }

   public strictfp String t() {
      return this.J != null?this.J.a():null;
   }

   public static strictfp void a(com.corrodinggames.rts.gameFramework.j.as var0) {
      var0.e("customUnits");
      var0.a((int)1);
      var0.a(d.size());
      Iterator var1 = d.iterator();

      while(var1.hasNext()) {
         l var2 = (l)var1.next();
         var0.c(var2.M);
         var0.a(var2.H);
         var0.a(true);
         var0.b(var2.t());
         long var3 = 0L;
         if(var2.J != null && var2.J.k != 0L) {
            var3 = var2.J.k;
         }

         var0.a(var3);
         long var5 = 0L;
         var0.a(var5);
      }

      var0.a("customUnits");
   }

   public static strictfp void a(ab var0, HashMap var1) {
      ArrayList var2 = new ArrayList();
      ArrayList var3 = new ArrayList();
      boolean var4 = var0.a == null;
      String var11;
      if(!var4) {
         Iterator var5 = var1.values().iterator();

         while(var5.hasNext()) {
            ac var6 = (ac)var5.next();
            if(var6.b == 0 && var6.d == 0 && var6.c > 0) {
               var2.add(var6);
            } else if(var6.c > 0 || var6.d > 0) {
               var3.add(var6);
            }
         }

         var11 = null;
         String var12 = "";
         String var7;
         boolean var8;
         Iterator var9;
         ac var10;
         if(var2.size() > 0) {
            var7 = "";
            var8 = true;

            for(var9 = var2.iterator(); var9.hasNext(); var7 = var7 + "\'" + var10.a + "\'") {
               var10 = (ac)var9.next();
               if(var8) {
                  var8 = false;
               } else {
                  var7 = var7 + ", \n";
               }
            }

            var7 = com.corrodinggames.rts.gameFramework.f.b(var7, (int)200);
            if(var2.size() == 1) {
               var11 = "Missing 1 mod.";
               var12 = "Missing mod: \'" + ((ac)var2.get(0)).a + "\'";
            } else {
               var11 = "Missing " + var2.size() + " mods";
               var12 = "missing mods: " + var7;
            }

            var12 = var12 + "\n Required by this server.";
            if(var3.size() > 0) {
               var12 = var12 + "\n and " + var3.size() + " mods are different.";
            }
         } else if(var3.size() > 0) {
            var7 = "";
            var8 = true;

            for(var9 = var3.iterator(); var9.hasNext(); var7 = var7 + "\'" + var10.a + "\'") {
               var10 = (ac)var9.next();
               if(var8) {
                  var8 = false;
               } else {
                  var7 = var7 + ", \n";
               }
            }

            var7 = com.corrodinggames.rts.gameFramework.f.b(var7, (int)200);
            var11 = "Different mod data.";
            var12 = "Different mod data for: " + var7 + " \n Check these mods are the same version as the server you are connecting to.";
         } else {
            com.corrodinggames.rts.gameFramework.l.e("Skipping nice message: completelyMissedMods:" + var2.size() + " differentMods:" + var3.size());
         }

         if(var11 != null) {
            bd var14 = new bd(var12, "");
            var14.a = var11;
            throw var14;
         }
      }

      var11 = "from internal units";
      if(var0.a != null) {
         var11 = "from mod:\'" + var0.a + "\'";
      }

      com.corrodinggames.rts.gameFramework.i.b var13 = com.corrodinggames.rts.gameFramework.l.B().bZ.f(var0.a);
      if(var13 != null) {
         if(!var13.m()) {
            var11 = var11 + " (You seem to have this mod but it is not enabled)";
         } else {
            var11 = var11 + " (You seem to have this mod but it might be a different version)";
         }
      }

      if(var0.d == -1) {
         throw new bd("The server requires the unit:" + var0.b + " that was not found " + var11, "");
      } else {
         throw new bd("Found unit:" + var0.b + " but it does not match the server\'s copy " + var11, "checksum c:" + var0.d + " s:" + var0.c);
      }
   }

   public static strictfp void a(com.corrodinggames.rts.gameFramework.j.k var0) {
      var0.b("customUnits");

      try {
         ArrayList var1 = new ArrayList();
         ArrayList var2 = new ArrayList();
         HashMap var3 = new HashMap();
         int var4 = var0.f();
         boolean var5 = false;
         if(var4 >= 2) {
            var5 = var0.e();
            var0.e();
         }

         int var6 = var0.f();

         for(int var7 = 0; var7 < var6; ++var7) {
            String var8 = var0.l();
            int var9 = var0.f();
            boolean var10 = var0.e();
            String var11 = var0.j();
            long var12 = var0.i();
            long var14 = var0.i();
            String var16 = null;
            if(var5) {
               var16 = var0.j();
            }

            l var17 = null;
            int var18 = -1;
            l var19 = null;
            ArrayList var20 = c;
            synchronized(c) {
               Iterator var21 = c.iterator();

               while(var21.hasNext()) {
                  l var22 = (l)var21.next();
                  if(var8.equals(var22.M)) {
                     if(var9 == var22.H) {
                        var17 = var22;
                     } else {
                        var19 = var22;
                        var18 = var22.H;
                     }
                  }
               }
            }

            ac var32 = (ac)var3.get(var11);
            if(var32 == null) {
               var32 = new ac(var11);
               var3.put(var11, var32);
            }

            if(var17 == null) {
               if(var19 != null) {
                  ++var32.d;
               } else {
                  ++var32.c;
               }

               ab var29 = new ab();
               var29.a = var11;
               var29.b = var8;
               var29.d = var18;
               var29.c = var9;
               var29.f = var19;
               var29.e = var16;
               var2.add(var29);
               com.corrodinggames.rts.gameFramework.l.b(var29.a());
            } else {
               ++var32.b;
               var1.add(var17);
            }
         }

         if(var2.size() > 0) {
            Iterator var30 = var2.iterator();

            while(var30.hasNext()) {
               ab var31 = (ab)var30.next();
               if(var31.a == null) {
                  a(var31, var3);
               }
            }

            a((ab)var2.get(0), var3);
         }

         e = var1;
      } finally {
         var0.d("customUnits");
      }

   }

   public strictfp void b(com.corrodinggames.rts.game.units.as var1) {
      if(!this.fL.contains(var1) && var1 != this) {
         this.fL.add(var1);
      }

      if(var1 instanceof l) {
         Iterator var2 = ((l)var1).fL.iterator();

         while(var2.hasNext()) {
            com.corrodinggames.rts.game.units.as var3 = (com.corrodinggames.rts.game.units.as)var2.next();
            if(!this.fL.contains(var3) && var1 != this) {
               this.fL.add(var3);
            }
         }
      }

   }

   public static strictfp l a(l var0) {
      Iterator var1 = d.iterator();

      l var2;
      do {
         if(!var1.hasNext()) {
            var1 = d.iterator();

            do {
               if(!var1.hasNext()) {
                  return null;
               }

               var2 = (l)var1.next();
            } while(!var0.M.equals(var2.M));

            return var2;
         }

         var2 = (l)var1.next();
      } while(!var0.D.equals(var2.D));

      return var2;
   }

   public static strictfp void A() {
      Iterator var0 = com.corrodinggames.rts.game.units.am.bF().iterator();

      while(var0.hasNext()) {
         com.corrodinggames.rts.game.units.am var1 = (com.corrodinggames.rts.game.units.am)var0.next();
         if(var1 instanceof j) {
            j var2 = (j)var1;
            l var3 = var2.x;
            if(!d.contains(var3)) {
               l var4 = a(var3);
               if(var4 == null) {
                  var4 = b;
               }

               if(var4 != null) {
                  var2.a(var4, false, true);
               }
            }
         }
      }

   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e[] a(com.corrodinggames.rts.gameFramework.m.e var1, com.corrodinggames.rts.game.o var2) {
      boolean var3 = false;
      if((this.J != null || this.eE) && !com.corrodinggames.rts.gameFramework.l.B().bQ.disableModLazyLoad) {
         var3 = true;
      }

      if(this.cy && this.cz || this.cE) {
         var3 = true;
      }

      com.corrodinggames.rts.gameFramework.m.e[] var4 = com.corrodinggames.rts.game.n.a(var1, var2, var3);

      for(int var5 = 0; var5 < var4.length; ++var5) {
         if(var3 && this.J != null && this.eE && var5 == 1) {
            var4[var5].w();
         }
      }

      ag.a(var4);
      return var4;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e a(com.corrodinggames.rts.gameFramework.utility.ab var1, String var2, String var3) {
      return this.a(var1, var2, var3, this.ab);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e a(com.corrodinggames.rts.gameFramework.utility.ab var1, String var2, String var3, boolean var4) {
      String var5 = var1.b(var2, var3, (String)null);
      return this.a(this.F, var5, var4, var2, var3);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e a(String var1, String var2, boolean var3, String var4, String var5) {
      com.corrodinggames.rts.gameFramework.m.e var6 = ag.a(var1, var2, var3, this, var4, var5);
      return var6;
   }

   public static strictfp com.corrodinggames.rts.game.units.as m(String var0) {
      Iterator var1 = f.keySet().iterator();

      com.corrodinggames.rts.game.units.as var2;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         var2 = (com.corrodinggames.rts.game.units.as)var1.next();
      } while(!var2.i().equals(var0));

      return (com.corrodinggames.rts.game.units.as)f.get(var2);
   }

   public static strictfp com.corrodinggames.rts.game.units.as c(com.corrodinggames.rts.game.units.as var0) {
      return (com.corrodinggames.rts.game.units.as)f.get(var0);
   }

   public static strictfp l n(String var0) {
      Iterator var1 = d.iterator();

      l var2;
      do {
         if(!var1.hasNext()) {
            var1 = d.iterator();

            do {
               if(!var1.hasNext()) {
                  return null;
               }

               var2 = (l)var1.next();
            } while(!var2.N.contains(var0));

            return var2;
         }

         var2 = (l)var1.next();
      } while(!var0.equals(var2.M));

      return var2;
   }

   public static strictfp String E() {
      String var0 = "";

      Iterator var1;
      l var2;
      for(var1 = d.iterator(); var1.hasNext(); var0 = var0 + var2.M + ", ") {
         var2 = (l)var1.next();
      }

      var1 = d.iterator();

      while(var1.hasNext()) {
         var2 = (l)var1.next();

         String var4;
         for(Iterator var3 = var2.N.iterator(); var3.hasNext(); var0 = var0 + var4 + ", ") {
            var4 = (String)var3.next();
         }
      }

      return var0;
   }

   public static strictfp j a(boolean var0, l var1) {
      j var2 = new j(var0, var1);
      return var2;
   }

   public strictfp void o(String var1) {
      if(!com.corrodinggames.rts.gameFramework.utility.ag.i(var1)) {
         aa var2 = new aa(var1);
         this.k.add(var2);
      }
   }

   public strictfp void p(String var1) {
      ag.a(this.i(), (Exception)(new bo(var1)), (com.corrodinggames.rts.game.units.as)this);
   }

   public strictfp void q(String var1) {
      String var2 = "Warning (on " + this.b() + "): " + var1;
      com.corrodinggames.rts.gameFramework.l.b(var2);
      this.gt.add(var1);
      if(this.J == null) {
         com.corrodinggames.rts.gameFramework.l.B().a(var2, 1);
         if(com.corrodinggames.rts.gameFramework.l.aT) {
            com.corrodinggames.rts.gameFramework.l.e("Crashing on allowed unit warning because automated testing is active");
            throw new RuntimeException(var2);
         }
      }

   }

   public strictfp void r(String var1) {
      String var2 = "Warning (on " + this.b() + "): " + var1;
      com.corrodinggames.rts.gameFramework.l.b(var2);
      this.gt.add(var1);
      if(this.J == null) {
         com.corrodinggames.rts.gameFramework.l.B().a(var2, 1);
         if(com.corrodinggames.rts.gameFramework.l.aT) {
            com.corrodinggames.rts.gameFramework.l.e("Crashing on allowed unit warning because automated testing is active");
            throw new RuntimeException(var2);
         }
      } else {
         this.J.b(var2);
      }

   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e z() {
      return this.aw;
   }

   public static strictfp void F() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      Iterator var1 = d.iterator();

      while(var1.hasNext()) {
         l var2 = (l)var1.next();
         ad var3 = var2.T;
         if(var3 != null) {
            Iterator var4;
            com.corrodinggames.rts.game.units.am var6;
            if(var3 != ad.a && var3 != ad.b) {
               com.corrodinggames.rts.game.n var9;
               if(var3 != ad.c && var3 != ad.d) {
                  if(var3 == ad.e) {
                     var4 = com.corrodinggames.rts.game.n.c().iterator();

                     while(var4.hasNext()) {
                        var9 = (com.corrodinggames.rts.game.n)var4.next();
                        if(var9.a(true, false) > 0) {
                           PointF var10 = new PointF();
                           com.corrodinggames.rts.gameFramework.utility.y.a(var9, var10);
                           com.corrodinggames.rts.game.units.am var11 = var2.a();
                           var11.b(var9);
                           var0.bL.b(var10.a, var10.b);
                           var11.eo = (float)var0.bL.T;
                           var11.ep = (float)var0.bL.U;
                           var11.eo += var11.cZ();
                           var11.ep += var11.da();
                           com.corrodinggames.rts.game.n.c(var11);
                        }
                     }
                  } else {
                     com.corrodinggames.rts.gameFramework.l.b("onNewMapSpawn unhandled: " + var2.T);
                  }
               } else {
                  if(var3 == ad.c) {
                     com.corrodinggames.rts.game.units.am var8 = var2.a();
                     var8.b(com.corrodinggames.rts.game.n.i);
                     var0.bL.b(var0.bL.i() / 2.0F, var0.bL.j() / 2.0F);
                     var8.eo = (float)var0.bL.T;
                     var8.ep = (float)var0.bL.U;
                     var8.eo += var8.cZ();
                     var8.ep += var8.da();
                     com.corrodinggames.rts.game.n.c(var8);
                  }

                  if(var3 == ad.d) {
                     var4 = com.corrodinggames.rts.game.n.c().iterator();

                     while(var4.hasNext()) {
                        var9 = (com.corrodinggames.rts.game.n)var4.next();
                        if(var9.a(true, false) > 0) {
                           var6 = var2.a();
                           var6.b(var9);
                           var0.bL.b(var0.bL.i() / 2.0F, var0.bL.j() / 2.0F);
                           var6.eo = (float)var0.bL.T;
                           var6.ep = (float)var0.bL.U;
                           var6.eo += var6.cZ();
                           var6.ep += var6.da();
                           com.corrodinggames.rts.game.n.c(var6);
                        }
                     }
                  }
               }
            } else {
               var4 = var0.bL.A.iterator();

               while(var4.hasNext()) {
                  Point var5 = (Point)var4.next();
                  var6 = var2.a();
                  var6.b(com.corrodinggames.rts.game.n.i);
                  var0.bL.a(var5.a, var5.b);
                  var6.eo = (float)var0.bL.T;
                  var6.ep = (float)var0.bL.U;
                  var6.eo += var6.cZ();
                  var6.ep += var6.da();
                  if(var3 == ad.a && var6 instanceof com.corrodinggames.rts.game.units.y) {
                     com.corrodinggames.rts.game.units.y var7 = (com.corrodinggames.rts.game.units.y)var6;
                     if(var7.a((com.corrodinggames.rts.game.units.am)null, (com.corrodinggames.rts.game.n)null)) {
                        var6.ci();
                        continue;
                     }
                  }

                  com.corrodinggames.rts.game.n.c(var6);
               }
            }
         }
      }

   }

   public strictfp int a(com.corrodinggames.rts.game.units.am var1) {
      int var2 = 0;
      if(this.aL > 0.0F) {
         return (int)this.aL;
      } else {
         if(this.aJ && this.aH && var1.y() < 20) {
            var2 += 17;
         }

         return var2;
      }
   }

   public strictfp void a(String var1, int var2, String var3, String var4) {
      if(this.J != null) {
         if(this.J.w < var2) {
            if(this.J.r) {
               if(this.J.v == null) {
                  throw new bo("[" + var3 + "] minVersion of " + var1 + " is required to be set in mod-info.txt at the root of this mod to use " + var4);
               } else {
                  try {
                     com.corrodinggames.rts.gameFramework.i.a.a(var1, this.J.v);
                  } catch (bo var6) {
                     throw new bo("[" + var3 + "]" + var4 + " " + var6.getMessage() + " to be set as minVersion in mod-info.txt");
                  }

                  this.J.w = var2;
               }
            }
         }
      }
   }

   public static strictfp com.corrodinggames.rts.game.units.as s(String var0) {
      return a(var0, true);
   }

   public static strictfp com.corrodinggames.rts.game.units.as a(String var0, boolean var1) {
      com.corrodinggames.rts.game.units.as var2 = com.corrodinggames.rts.game.units.ar.a(var0, var1);
      return (com.corrodinggames.rts.game.units.as)(var2 == com.corrodinggames.rts.game.units.ar.Y?com.corrodinggames.rts.game.units.ar.h:var2);
   }

}
