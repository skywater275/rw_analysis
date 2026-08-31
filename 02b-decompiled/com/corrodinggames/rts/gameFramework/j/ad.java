package com.corrodinggames.rts.gameFramework.j;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Notification.Builder;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.util.Log;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.custom.bd;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.j.a;
import com.corrodinggames.rts.gameFramework.j.ac;
import com.corrodinggames.rts.gameFramework.j.ad$1;
import com.corrodinggames.rts.gameFramework.j.ad$2;
import com.corrodinggames.rts.gameFramework.j.ad$3;
import com.corrodinggames.rts.gameFramework.j.ad$4;
import com.corrodinggames.rts.gameFramework.j.ad$5;
import com.corrodinggames.rts.gameFramework.j.ad$6;
import com.corrodinggames.rts.gameFramework.j.ad$7;
import com.corrodinggames.rts.gameFramework.j.ad$8;
import com.corrodinggames.rts.gameFramework.j.ae;
import com.corrodinggames.rts.gameFramework.j.af;
import com.corrodinggames.rts.gameFramework.j.ag;
import com.corrodinggames.rts.gameFramework.j.ah;
import com.corrodinggames.rts.gameFramework.j.ai;
import com.corrodinggames.rts.gameFramework.j.aj;
import com.corrodinggames.rts.gameFramework.j.ak;
import com.corrodinggames.rts.gameFramework.j.al;
import com.corrodinggames.rts.gameFramework.j.am;
import com.corrodinggames.rts.gameFramework.j.an;
import com.corrodinggames.rts.gameFramework.j.ao;
import com.corrodinggames.rts.gameFramework.j.aq;
import com.corrodinggames.rts.gameFramework.j.ar;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.av;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.g;
import com.corrodinggames.rts.gameFramework.j.h;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.j.n;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Timer;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class ad {

   public static final boolean a = false;
   public static boolean b = true;
   public static boolean c = false;
   public ac d = new ac();
   public int e;
   ArrayList f;
   public boolean g;
   public int h = 25;
   public boolean i;
   public float j;
   public float k;
   public boolean l = false;
   public int m;
   public String n;
   public boolean o;
   public boolean p;
   public boolean q;
   public static boolean r = true;
   public boolean s;
   public int t = 5005;
   public String u;
   public boolean v = false;
   public long w = 1L;
   public boolean x = false;
   public String y;
   private boolean bG;
   public com.corrodinggames.rts.game.n z;
   public boolean A;
   private boolean bH = false;
   public volatile boolean B = false;
   public boolean C;
   public boolean D;
   public String E;
   public boolean F = false;
   public boolean G;
   public boolean H;
   public int I = 0;
   private volatile float bI = 1.0F;
   public volatile float J = 1.0F;
   public Float K;
   public String L;
   public ArrayList M = new ArrayList();
   public boolean N;
   public int O;
   public int P;
   public int Q;
   public int R;
   public String S;
   public int T = -1;
   public int U = -1;
   public int V = -1;
   public int W = com.corrodinggames.rts.gameFramework.f.a(1, 9000000);
   public int X = 0;
   public boolean Y;
   public float Z;
   boolean aa;
   public float ab;
   public float ac;
   public boolean ad;
   public float ae;
   public boolean af;
   public boolean ag;
   public int ah = -1;
   public int ai = 300;
   public boolean aj;
   public boolean ak;
   public boolean al;
   public ak am = new ak();
   public boolean an;
   public boolean ao = true;
   public int ap;
   public int aq;
   public int ar;
   public static boolean as;
   float at = 0.0F;
   long au;
   public boolean av;
   public int aw = 5;
   public int ax = 5;
   public ah ay = new ah();
   public String az = null;
   public k aA;
   public k aB;
   public a aC = new a();
   Thread aD;
   ao aE;
   Thread aF;
   ao aG;
   Timer aH;
   av aI;
   Thread aJ;
   af aK;
   c aL;
   public ConcurrentLinkedQueue aM = new ConcurrentLinkedQueue();
   ConcurrentLinkedQueue aN = new ConcurrentLinkedQueue();
   boolean aO;
   volatile int aP = 1;
   Object aQ = new Object();
   String aR;
   String aS;
   public String aT;
   public Boolean aU;
   public Boolean aV;
   public boolean aW;
   public boolean aX = false;
   boolean aY = false;
   boolean aZ = false;
   public float ba;
   public boolean bb;
   public boolean bc;
   public boolean bd;
   public boolean be;
   public boolean bf;
   public String bg;
   public String bh = null;
   public ConcurrentLinkedQueue bi = new ConcurrentLinkedQueue();
   public com.corrodinggames.rts.game.e bj;
   public com.corrodinggames.rts.game.e bk;
   public final Object bl = new Object();
   public boolean bm = false;
   float bn;
   float bo;
   int bp;
   int bq;
   boolean br = false;
   public long bs;
   public long bt;
   boolean bu = false;
   public Socket bv = null;
   public String bw = null;
   public boolean bx;
   boolean by = false;
   boolean bz = false;
   static ArrayList bA;
   boolean bB = false;
   final Object bC = new Object();
   Timer bD;
   public static ae bE = new ae();
   an bF;


   public strictfp aj a(c var1) {
      String var2 = var1.f();
      long var3 = System.currentTimeMillis();
      if(var2 == null) {
         var1.b("Is banned: No target");
         return null;
      } else {
         ArrayList var5 = this.M;
         synchronized(this.M) {
            Iterator var6 = this.M.iterator();

            aj var7;
            do {
               if(!var6.hasNext()) {
                  return null;
               }

               var7 = (aj)var6.next();
            } while(!var2.equals(var7.a) || var7.b <= var3);

            return var7;
         }
      }
   }

   public strictfp boolean a(c var1, String var2, int var3) {
      if(var1 == null) {
         com.corrodinggames.rts.gameFramework.l.b("Ban failed: No connection");
         return false;
      } else {
         String var4 = var1.f();
         if(var4 == null) {
            var1.b("Ban failed: No target");
            return false;
         } else {
            aj var5 = new aj();
            var5.a = var1.f();
            var5.b = System.currentTimeMillis() + (long)(var3 * 1000);
            var5.c = var2;
            ArrayList var6 = this.M;
            synchronized(this.M) {
               this.b();
               this.M.add(var5);
            }

            var1.c("Banned " + var4 + " for " + var3 + "s");
            return true;
         }
      }
   }

   public strictfp void a() {
      ArrayList var1 = this.M;
      synchronized(this.M) {
         this.M.clear();
      }
   }

   public strictfp void b() {
      ArrayList var1 = this.M;
      synchronized(this.M) {
         int var2 = 0;
         long var3 = System.currentTimeMillis();
         Iterator var5 = this.M.iterator();

         while(var5.hasNext()) {
            ++var2;
            aj var6 = (aj)var5.next();
            boolean var7 = false;
            if(var6.b < var3) {
               var7 = true;
            }

            if(var2 > 1000) {
               var7 = true;
            }

            if(var7) {
               var5.remove();
            }
         }

      }
   }

   public strictfp String a(String var1) {
      var1 = var1.trim();
      var1 = var1.replace(" ", "_");
      this.y = var1;
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.y != null && !this.y.equals(var2.bQ.lastNetworkPlayerName)) {
         var2.bQ.lastNetworkPlayerName = this.y;
         var2.bQ.save();
      }

      return var1;
   }

   public strictfp void a(float var1, String var2) {
      if((double)var1 < 0.1D) {
         a("setCurrentStepRate:" + var1 + " is too small, source:" + var2, true);
      } else {
         this.bI = var1;
      }
   }

   public strictfp float c() {
      return this.bI;
   }

   public strictfp void d() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.ah = var1.bx;
      this.am.b();
      this.an = false;
   }

   public strictfp void a(as var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      var1.c(0);
      this.ay.a(var1);
      var1.a(var2.bB);
      var1.a(var2.bC);
   }

   public strictfp void a(k var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      var1.d();
      this.ay.a(var1);
      var2.bB = var1.f();
      var2.bC = var1.f();
   }

   public strictfp ah e() {
      ah var1;
      if(this.C) {
         var1 = this.ay;
      } else if(this.H) {
         var1 = this.ay.c();
      } else {
         var1 = null;
         com.corrodinggames.rts.gameFramework.l.b("getChangeableSetup", "Clicked but not server or proxy controller");
      }

      return var1;
   }

   public strictfp void f() {
      if(this.F) {
         com.corrodinggames.rts.gameFramework.l.B().bQ.aiDifficulty = this.ay.f;
      }

      if(this.C || this.F) {
         if(this.aW) {
            com.corrodinggames.rts.gameFramework.l.g("updateAIDifficulty with gameHasBeenStarted=true");
         } else {
            for(int var1 = 0; var1 < com.corrodinggames.rts.game.n.c; ++var1) {
               com.corrodinggames.rts.game.n var2 = com.corrodinggames.rts.game.n.k(var1);
               if(var2 != null) {
                  this.a(var2);
               }
            }
         }

         this.aq();
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.n var1) {
      if(var1.w) {
         var1.c("aiDifficultyOverride=" + var1.z);
         if(var1.z != null) {
            var1.x = var1.z.intValue();
         } else {
            var1.x = this.ay.f;
         }
      }

   }

   public strictfp boolean b(com.corrodinggames.rts.game.n var1) {
      boolean var2 = false;
      if(var1.w) {
         String var3 = "AI - " + this.b(var1.C());
         if(!var3.equals(var1.v)) {
            var1.v = var3;
            var2 = true;
         }
      }

      return var2;
   }

   public strictfp void a(ah var1) {
      if(this.C) {
         this.f();
         this.P();
         this.L();
         com.corrodinggames.rts.appFramework.n.o();
      } else if(this.H) {
         this.b(var1);
      } else {
         com.corrodinggames.rts.gameFramework.l.e("applyChangedSetup but not server or proxy controller");
      }

   }

   private strictfp void b(ah var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.e("applyProxyControl");
      ah var3 = this.ay;
      String var4;
      if(!var3.b.equals(var1.b)) {
         var4 = com.corrodinggames.rts.appFramework.i.e(var1.b);
         var4 = com.corrodinggames.rts.gameFramework.e.a.o(var4);
         var2.bX.k("-map \'" + var4 + "\'");
      }

      if(var3.e != var1.e) {
         var4 = !var1.e?"true":"false";
         var2.bX.k("-revealedmap " + var4);
      }

      if(var3.d != var1.d) {
         var4 = var2.bX.a(var1.d);
         var2.bX.k("-fog " + var4);
      }

      if(var3.c != var1.c) {
         int var5 = var2.bX.e(var1.c);
         var2.bX.k("-credits " + var5);
      }

      if(!com.corrodinggames.rts.gameFramework.f.k(var3.h, var1.h)) {
         var2.bX.k("-income " + com.corrodinggames.rts.gameFramework.f.a(var1.h, 1));
      }

      if(var3.i != var1.i) {
         var4 = !var1.i?"true":"false";
         var2.bX.k("-nukes " + var4);
      }

      if(var3.f != var1.f) {
         var2.bX.k("-ai " + var1.f);
      }

      if(var3.g != var1.g) {
         var2.bX.k("-startingunits " + var1.g);
      }

      if(var3.l != var1.l) {
         var4 = var1.l?"true":"false";
         var2.bX.k("-sharedControl " + var4);
      }

   }

   public strictfp String g() {
      return this.ay.d == 0?"No fog":(this.ay.d == 1?"Basic fog":(this.ay.d == 2?"Line of Sight":"Unknown"));
   }

   public strictfp String a(int var1) {
      return var1 == 0?"off":(var1 == 1?"basic":(var1 == 2?"los":"Unknown"));
   }

   public strictfp String b(int var1) {
      return this.c(var1);
   }

   public strictfp String c(int var1) {
      return var1 == -2?"Very Easy":(var1 == -1?"Easy":(var1 == 0?"Medium":(var1 == 1?"Hard":(var1 == 2?"Very Hard":(var1 == 3?"Impossible":"Unknown")))));
   }

   public strictfp String h() {
      return this.d(this.ay.g);
   }

   public strictfp ArrayList i() {
      ArrayList var1 = new ArrayList();

      for(int var2 = 1; var2 <= 4; ++var2) {
         var1.add(Integer.valueOf(var2));
      }

      var1.addAll(com.corrodinggames.rts.game.units.custom.l.s());
      return var1;
   }

   public strictfp String d(int var1) {
      if(var1 == 1) {
         return "Normal (1 builder)";
      } else if(var1 == 2) {
         return "Small Army";
      } else if(var1 == 3) {
         return "3 Engineers";
      } else if(var1 == 4) {
         return "3 Engineers (No Command Center)";
      } else if(var1 == 5) {
         return "Experimental Spider";
      } else if(var1 == 9) {
         return "Custom";
      } else {
         com.corrodinggames.rts.game.units.custom.l var2 = com.corrodinggames.rts.game.units.custom.l.c(var1);
         return var2 != null?var2.e():"Unknown";
      }
   }

   public strictfp String j() {
      return this.ay.c == 0?"Default ($" + this.k() + ")":"$" + this.k();
   }

   public final strictfp int k() {
      return this.e(this.ay.c);
   }

   public strictfp int e(int var1) {
      return var1 == 0?4000:(var1 == 1?0:(var1 == 2?1000:(var1 == 3?2000:(var1 == 4?5000:(var1 == 5?10000:(var1 == 6?'\uc350':(var1 == 7?100000:(var1 == 8?200000:999))))))));
   }

   public strictfp String l() {
      return com.corrodinggames.rts.gameFramework.e.a.o(this.az);
   }

   public strictfp void m() {
      new com.corrodinggames.rts.gameFramework.utility.m();
      com.corrodinggames.rts.gameFramework.f.b(256);
      aq.a(5.0F, 6.0F, 7.0F);
      ar.a(5);
      this.bg = ar.a();
      this.bf = true;
   }

   public strictfp boolean n() {
      return this.aW;
   }

   public strictfp boolean o() {
      return this.d.e();
   }

   public synchronized strictfp void a(boolean var1, String var2, Boolean var3) {
      this.aV = Boolean.valueOf(var1);
      this.aT = var2;
      this.aU = var3;
      com.corrodinggames.rts.appFramework.n.o();
   }

   strictfp void a(g var1) {
      Iterator var2 = this.bi.iterator();

      while(var2.hasNext()) {
         g var3 = (g)var2.next();
         if(var3.a && var3.c.equals(var1.c) && var3.g == var1.g) {
            var3.o = this.p();
         }
      }

      var1.o = this.p();
      this.bi.add(var1);
      com.corrodinggames.rts.appFramework.p.l();
   }

   public strictfp long p() {
      return System.currentTimeMillis();
   }

   public strictfp ad() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.e = var1.c(true);
      this.aR = com.corrodinggames.rts.gameFramework.f.e(40);
      this.aL = new c(this, (Socket)null);
      this.aL.p = true;
      this.bj = new com.corrodinggames.rts.game.e(-3, false);
      this.bj.v = "SPECTATOR";
      this.bk = new com.corrodinggames.rts.game.e(-1, false);
      this.bk.v = "ADMIN";
   }

   public strictfp void q() {
      this.a(false);
   }

   public strictfp void r() {
      this.a(true);
   }

   public strictfp void s() {
      this.bH = false;
      this.bG = false;
      this.z = null;
      this.p = false;
      this.bs = System.currentTimeMillis();
      this.X = 0;
      this.I = 0;
      this.w = 1L;
      this.a(1.0F, "new");
      this.Z = 10.0F;
      this.N = false;
      this.Q = 10;
      this.R = 0;
      this.Y = false;
      this.aa = false;
      this.al = false;
      this.ak = false;
      this.ab = 0.0F;
      this.ac = 0.0F;
      this.ad = false;
      this.af = false;
      this.aW = false;
      this.aY = false;
      this.aZ = false;
      this.ba = 0.0F;
      this.aX = false;
      this.bb = false;
      this.bc = false;
      this.bd = false;
      this.be = false;
      this.ag = false;
      this.ah = -1;
      this.am.a = 0L;
      this.br = false;
      this.am.a();
      this.an = false;
      this.ao = true;
      this.ap = 0;
      this.aq = 0;
      this.ar = 0;
      this.at = 0.0F;
      this.bn = 0.0F;
      this.bo = 0.0F;
      this.bp = 0;
      this.bq = -1000;
      aq.i = 55;
      aq.j = 66;
   }

   public strictfp void a(boolean var1) {
      this.B = false;
      this.C = false;
      this.f = null;
      this.F = false;
      this.D = false;
      this.E = null;
      this.x = false;
      this.H = false;
      this.G = false;
      this.av = false;
      this.A = false;
      this.s();
      this.S = null;
      this.m = 0;
      this.i = false;
      this.j = 0.0F;
      this.k = 0.0F;
      this.bz = false;
      this.aB = null;
      this.ax = com.corrodinggames.rts.gameFramework.l.B().bQ.teamUnitCapHostedGame;
      if(this.ax < 1) {
         this.ax = 1;
      }

      this.aw = this.ax;
      this.ay.g = 1;
      this.ay.h = 1.0F;
      this.ay.i = false;
      this.ay.j = false;
      this.ay.l = false;
      this.ay.c = 0;
      this.ay.m = false;
      this.ay.n = false;
      this.ay.o = true;
      this.ay.p = false;
      this.ay.q = 0;
      this.a();
      this.aC.c();
      com.corrodinggames.rts.gameFramework.l.B().bS.g();
      if("<CHAT ONLY>".equals(this.ay.b)) {
         com.corrodinggames.rts.gameFramework.l.e("Chat only map selection - restarting");
         this.ay.a();
      }

      if(!var1) {
         com.corrodinggames.rts.game.n.F();
      }

      String var2 = com.corrodinggames.rts.game.units.custom.ag.b(this.o);
   }

   public strictfp void t() {}

   public synchronized strictfp void b(String var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.e("Disconnect: " + var1);
      if(this.C) {
         this.ar();
         n.d();
         if(this.aE != null) {
            this.aE.b();

            try {
               if(this.aD != null) {
                  this.aD.join();
               }
            } catch (InterruptedException var9) {
               ;
            }

            this.aE = null;
            this.aD = null;
         }

         if(this.aG != null) {
            this.aG.b();

            try {
               if(this.aF != null) {
                  this.aF.join();
               }
            } catch (InterruptedException var8) {
               ;
            }

            this.aG = null;
            this.aF = null;
         }

         if(this.aH != null) {
            this.aH.cancel();
            this.aH = null;
            this.aI = null;
         }

         if(this.aK != null) {
            this.aK.b();
            this.aK = null;
            this.aJ = null;
         }
      }

      this.q(var1);
      com.corrodinggames.rts.gameFramework.o.a.a().j();
      Object var3 = this.bl;
      synchronized(this.bl) {
         this.B = false;
         this.C = false;
         this.F = false;
         this.f = null;

         try {
            this.wait(50L);
         } catch (InterruptedException var6) {
            var6.printStackTrace();
         }

         this.aW = false;
         var2.cb.e();
         var2.e();
         this.am();
         this.bm = false;
         this.bl.notifyAll();
      }
   }

   public strictfp void u() {
      Object var1 = this.bl;
      synchronized(this.bl) {
         if(this.B) {
            this.bm = true;

            try {
               this.bl.wait();
            } catch (InterruptedException var4) {
               var4.printStackTrace();
            }

         }
      }
   }

   public strictfp void b(c var1) {
      this.aM.remove(var1);
   }

   private synchronized strictfp void ay() {
      Iterator var1 = this.aM.iterator();

      while(var1.hasNext()) {
         c var2 = (c)var1.next();
         if(var2.a) {
            var1.remove();
         }
      }

   }

   strictfp void a(byte[] var1, c var2) {
      if(!com.corrodinggames.rts.gameFramework.l.ax()) {
         Log.d("RustedWarfare", "Ignoring incoming resync tagged as debug only");
      } else {
         if(var2.u) {
            Log.d("RustedWarfare", "Ignoring desync client save, as past desync was already saved");
            return;
         }

         var2.u = true;
         Log.d("RustedWarfare", "Saving client save for debugging");
         String var3 = "desyncs/";
         String var4 = "desync_" + com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy HH.mm.ss") + "_" + var2.c;
         File var5 = new File(var3 + var4);
         var5.getParentFile().mkdirs();

         try {
            FileOutputStream var6 = new FileOutputStream(var5);
            var6.write(var1);
            var6.close();
         } catch (IOException var8) {
            var8.printStackTrace();
         }
      }

   }

   public strictfp void v() {
      if(!this.br) {
         com.corrodinggames.rts.gameFramework.l.e("Adding quick resync command");
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         com.corrodinggames.rts.gameFramework.e var2 = var1.cf.b();
         var2.i = com.corrodinggames.rts.game.n.i;
         var2.r = true;
         var2.u = 200;
         var1.bX.a(var2);
         this.br = true;
      }
   }

   public strictfp void w() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      as var2 = new as();

      try {
         var1.ca.a(var2);
      } catch (IOException var10) {
         throw new RuntimeException(var10);
      }

      try {
         var2.a();
      } catch (IOException var9) {
         var9.printStackTrace();
      }

      byte[] var3 = var2.d();
      var2.h();
      if(this.C) {
         Iterator var4 = this.aM.iterator();

         while(var4.hasNext()) {
            c var5 = (c)var4.next();
            if(var5.w) {
               var5.w = false;
               var5.v = false;
               this.a(var5, var3, this.l, false);
            }
         }
      }

      com.corrodinggames.rts.gameFramework.l.e("Loading quick resync save data (bytes:" + var3.length + ")");
      k var11 = new k(var3);
      var1.a("Game resync (quick)...", true);
      int var12 = var1.bx;
      int var6 = var1.by;
      var1.ca.a(var11, true, true, true);
      var1.bx = var12;
      var1.by = var6;
      this.X = var1.bx + 1;
      this.ag = false;
      this.ah = this.X + 1;
      this.am.a = 0L;

      c var8;
      for(Iterator var7 = this.aM.iterator(); var7.hasNext(); var8.v = false) {
         var8 = (c)var7.next();
      }

      this.br = false;
      ++this.ar;
      this.bn = 0.0F;
      this.bo = 0.0F;
      if(this.bp < 1) {
         ++this.bp;
      }

      this.bq = var1.bx;
   }

   public synchronized strictfp void x() {
      Iterator var1 = this.aM.iterator();

      c var2;
      do {
         if(!var1.hasNext()) {
            return;
         }

         var2 = (c)var1.next();
         if(var2.w) {
            throw new RuntimeException("Player: " + var2.e() + " has complete desync");
         }

         if(var2.v) {
            throw new RuntimeException("Player: " + var2.e() + " has minor desync");
         }
      } while(var2.x != 0);

      throw new RuntimeException("Player: " + var2.e() + " has no sync matches");
   }

   private synchronized strictfp void e(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var3 = false;
      boolean var4 = false;
      boolean var5 = false;
      this.bn += var1;
      Iterator var6 = this.aM.iterator();

      while(var6.hasNext()) {
         c var7 = (c)var6.next();
         if(var7.w) {
            var3 = true;
         }

         if(var7.v) {
            if(this.g) {
               com.corrodinggames.rts.gameFramework.l.e("desync_count:" + var7.y + " lastResyncTimer:" + this.bn);
            }

            if(var7.y < 4 || this.bn > 3600.0F) {
               var5 = true;
            }
         }
      }

      if(var5) {
         this.bo += var1;
         if(c && this.bo > 5.0F) {
            var4 = true;
         }

         if(this.bp == 0) {
            if(this.bo > 60.0F) {
               var4 = true;
            }
         } else if(this.bp == 1) {
            if(this.bo > 420.0F) {
               var4 = true;
            }
         } else if(this.bp == 2) {
            if(this.bo > 3600.0F) {
               var4 = true;
            }
         } else if(this.bp == 3 && this.bo > 14400.0F) {
            var4 = true;
         }
      }

      if(as && var4) {
         com.corrodinggames.rts.gameFramework.l.e("disableDesyncFixing==true, running quick resync instead");
         var4 = false;
         var3 = true;
      }

      if(!var4 && var3) {
         if(b) {
            this.v();
         } else {
            var4 = true;
         }
      }

      if(var4) {
         String var9 = "";
         Iterator var10 = this.aM.iterator();

         while(var10.hasNext()) {
            c var8 = (c)var10.next();
            if(var8.w || var8.v) {
               if(!var9.equals("")) {
                  var9 = var9 + ", ";
               }

               var9 = var9 + var8.e();
            }
         }

         this.j("Resyncing game for " + var9 + "...");
         this.az();
         this.a(this.l, false, true);
      }

   }

   private strictfp void az() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.bn = 0.0F;
      this.bo = 0.0F;
      ++this.bp;
      this.bq = var1.bx;

      c var3;
      for(Iterator var2 = this.aM.iterator(); var2.hasNext(); var3.x = 0) {
         var3 = (c)var2.next();
         var3.w = false;
         var3.v = false;
      }

   }

   public strictfp void c(String var1) {
      this.q(var1);
   }

   private strictfp void q(String var1) {
      Iterator var2 = this.aM.iterator();

      while(var2.hasNext()) {
         c var3 = (c)var2.next();
         var3.a(var1);
      }

      this.aM.clear();
      this.aN.clear();
      this.aP = 1;
      this.aO = false;
   }

   public strictfp long y() {
      boolean var1 = false;
      if(var1) {
         com.corrodinggames.rts.gameFramework.l.e("New id set:" + this.w + 1);
         com.corrodinggames.rts.gameFramework.l.T();
      }

      long var2 = (long)(this.w++);
      if(var2 == 0L) {
         com.corrodinggames.rts.gameFramework.l.e("getNextUnitId: id==0");
         com.corrodinggames.rts.gameFramework.l.T();
      }

      return var2;
   }

   public strictfp long z() {
      return this.w;
   }

   public strictfp void a(long var1) {
      this.w = var1;
   }

   public strictfp boolean a(boolean var1, int var2) {
      Iterator var3 = this.aM.iterator();

      c var4;
      do {
         if(!var3.hasNext()) {
            return true;
         }

         var4 = (c)var3.next();
      } while(!var4.p || !var4.h() || var4.s || var4.D);

      if(var1) {
         this.j("Still waiting on: " + var4.e());
      }

      return false;
   }

   public strictfp void A() {
      c var2;
      for(Iterator var1 = this.aM.iterator(); var1.hasNext(); var2.D = false) {
         var2 = (c)var1.next();
         var2.C = false;
      }

   }

   public strictfp int B() {
      int var1 = 0;
      Iterator var2 = this.aM.iterator();

      while(var2.hasNext()) {
         c var3 = (c)var2.next();
         if(var3.p && var3.h() && !var3.s) {
            ++var1;
         }
      }

      return var1;
   }

   public strictfp int C() {
      ArrayList var1 = new ArrayList();
      int var2 = 0;
      Iterator var3 = this.aM.iterator();

      while(var3.hasNext()) {
         c var4 = (c)var3.next();
         if(var4.p && var4.h() && !var4.s) {
            com.corrodinggames.rts.game.e var5 = var4.z;
            if(var5 != null) {
               if(var1.contains(var5)) {
                  continue;
               }

               var1.add(var5);
            }

            ++var2;
         }
      }

      return var2;
   }

   public strictfp int D() {
      int var1 = 0;
      Iterator var2 = this.aM.iterator();

      while(var2.hasNext()) {
         c var3 = (c)var2.next();
         if(var3.p && !var3.s) {
            ++var1;
         }
      }

      return var1;
   }

   public strictfp int E() {
      byte var1 = 0;
      int var2 = var1 + this.C();
      if(!com.corrodinggames.rts.gameFramework.l.ax()) {
         ++var2;
      }

      return var2;
   }

   public strictfp void d(String var1) {
      Log.b("RustedWarfare", "network:" + var1);
   }

   public static strictfp void e(String var0) {
      com.corrodinggames.rts.gameFramework.l.e("network debug: " + var0);
   }

   public strictfp void f(String var1) {
      Log.d("RustedWarfare", "reportProblem:" + var1);
      if(this.aW) {
         this.b((c)null, -1, (String)null, var1);
      } else {
         this.b((c)null, -1, (String)null, var1);
      }

   }

   public static strictfp void g(String var0) {
      a(var0, false);
   }

   public static strictfp void h(String var0) {
      a(var0, true);
   }

   public static strictfp void a(String var0, boolean var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      ad var3 = var2.bX;
      String var4 = "desync:" + var0;
      com.corrodinggames.rts.gameFramework.l.b(var4);
      com.corrodinggames.rts.gameFramework.l.T();
      ++var3.ap;
      if(var3.ao) {
         if(var3.ap > 2 || as) {
            var1 = true;
         }

         String var5;
         if(var3.ap > 10) {
            var5 = "<suppressing desync errors>";
            var3.ao = false;
            var1 = true;
         } else {
            var5 = var4;
         }

         if(var1) {
            var5 = "-i " + var5;
         }

         var3.m(var5);
      }

   }

   public static strictfp void a(String var0, String var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      var2.cb.a(-1, var0, var1, var2.bx);
      if(var2.bS != null && var2.bS.h != null) {
         var2.bS.h.a(var0, var1);
      } else {
         com.corrodinggames.rts.gameFramework.l.g("interfaceEngine/messageInterface==null");
      }

   }

   public strictfp void F() {}

   public strictfp void a(com.corrodinggames.rts.gameFramework.e var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      var1.c = this.X;
      var1.g();
      var2.cf.b.add(var1);
   }

   public strictfp void G() {
      Iterator var1 = this.aM.iterator();

      while(var1.hasNext()) {
         c var2 = (c)var1.next();
         if(var2.p && var2.b() != -2 && var2.b() <= 500 && var2.b() < 0) {
            ;
         }
      }

   }

   public strictfp void H() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      String var2 = "";
      ArrayList var3 = com.corrodinggames.rts.game.n.a(true);
      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
         com.corrodinggames.rts.game.n var5 = (com.corrodinggames.rts.game.n)var4.next();
         if(var5 != null) {
            String var6 = "unnamed";
            if(var5.v != null) {
               var6 = var5.v;
            }

            String var7 = " " + var5.y();
            String var8 = "•";
            var2 = var2 + var8 + var5.N().toLowerCase() + " [Team " + var5.h() + "] - " + var6 + var7 + "\n";
         }
      }

      com.corrodinggames.rts.gameFramework.l.e("showPlayerListPopup(): Showing playlist messagebox.");
      var1.c("Players", var2);
   }

   public strictfp void a(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      this.at += var1;
      if(this.aZ) {
         if(this.ba > 0.0F) {
            this.ba -= var1 / 60.0F;
            com.corrodinggames.rts.gameFramework.l.B().bS.a("Returning to battleroom in " + (int)this.ba + "...", 3500);
         } else {
            com.corrodinggames.rts.gameFramework.l.e("Sending returnToBattleroomEvent...");
            this.aZ = false;
            this.i((c)null);
         }
      }

      if(this.aY) {
         this.aD();
      }

      if(this.at > 60.0F) {
         this.G();
         this.at = 0.0F;
      }

      int var4;
      if(this.aW && !this.aX) {
         this.aX = true;
         ArrayList var3 = com.corrodinggames.rts.game.n.f();
         var4 = 0;
         int var5 = 0;

         for(Iterator var6 = var3.iterator(); var6.hasNext(); ++var4) {
            Integer var7 = (Integer)var6.next();
            int var8 = com.corrodinggames.rts.game.n.a(var7.intValue(), false);
            if(var8 > var5) {
               var5 = var8;
            }
         }

         if(var4 > 2 && var5 <= 1) {
            this.bb = true;
         }
      }

      if(!this.C && !this.bH) {
         this.ad();
         this.bH = true;
      }

      boolean var17;
      if(this.C) {
         if(!this.aa && this.aW) {
            if(this.a(false, 0)) {
               this.Z = com.corrodinggames.rts.gameFramework.f.a(this.Z, var1);
               if(this.Z == 0.0F) {
                  this.aa = true;
                  a("", "<All players ready>");
                  this.d.a();
               }
            } else {
               this.ab += var1;
               this.ac += var1;
               float var13 = 900.0F;
               if(this.ab > var13) {
                  this.aa = true;
                  a("", "Starting game without all players ready!");
               } else if(this.ac > 180.0F) {
                  this.ac = 0.0F;
                  this.a(true, (int)((var13 - this.ab) / 60.0F));
               }
            }
         }

         if(this.aa) {
            boolean var14 = false;
            if(this.ak) {
               var14 = true;
            }

            if(this.al) {
               var14 = true;
            }

            if(var2.bx >= this.X - this.R && !var14) {
               var4 = this.X + this.Q;
               ++this.O;
               var17 = false;

               for(int var19 = 0; var19 < com.corrodinggames.rts.game.n.c; ++var19) {
                  com.corrodinggames.rts.game.n var23 = com.corrodinggames.rts.game.n.k(var19);
                  if(var23 != null && var23.V != 0 && !var23.B() && var23.V < 40) {
                     var17 = true;
                  }
               }

               if(var2.b() != 0 && var2.b() < 40 && !com.corrodinggames.rts.gameFramework.l.ax()) {
                  var17 = true;
               }

               if(var17) {
                  ++this.P;
               }

               if(this.O > 8) {
                  float var21 = 1.0F;
                  if(this.P > 4) {
                     var21 = 2.0F;
                  }

                  if(this.K != null) {
                     var21 = this.K.floatValue();
                  }

                  if(var21 != this.c()) {
                     com.corrodinggames.rts.gameFramework.l.e("Changing step rate to " + var21);
                     com.corrodinggames.rts.gameFramework.e var25 = var2.cf.b();
                     var25.i = com.corrodinggames.rts.game.n.i;
                     var25.r = true;
                     var25.s = var21;
                     this.a(var25);
                  }

                  this.O = 0;
                  this.P = 0;
               }

               as var27 = new as();

               try {
                  var27.a(var4);
                  int var26 = 0;
                  Iterator var28 = var2.cf.b.iterator();

                  label234:
                  while(true) {
                     com.corrodinggames.rts.gameFramework.e var9;
                     if(!var28.hasNext()) {
                        var27.a(var26);
                        var28 = var2.cf.b.iterator();

                        while(true) {
                           if(!var28.hasNext()) {
                              break label234;
                           }

                           var9 = (com.corrodinggames.rts.gameFramework.e)var28.next();
                           if(var9.c == this.X) {
                              var9.a(var27);
                           }
                        }
                     }

                     var9 = (com.corrodinggames.rts.gameFramework.e)var28.next();
                     if(var9.c == this.X) {
                        ++var26;
                     }
                  }
               } catch (IOException var12) {
                  throw new RuntimeException(var12);
               }

               au var30 = var27.b(10);
               var30.e = true;
               this.d(var30);
               this.X = var4;
            }
         }
      }

      Iterator var15;
      com.corrodinggames.rts.gameFramework.e var16;
      if(!var2.cf.d.isEmpty()) {
         var15 = var2.cf.d.iterator();

         while(var15.hasNext()) {
            var16 = (com.corrodinggames.rts.gameFramework.e)var15.next();
            var17 = false;
            if(var17) {
               var2.cf.c.add(var16);
               var15.remove();
            } else {
               if(!var16.x) {
                  var16.b();
               }

               if(var16.a()) {
                  var2.cf.c.add(var16);
                  var15.remove();
               }
            }
         }
      }

      if(!this.C) {
         if(!var2.cf.c.isEmpty()) {
            var15 = var2.cf.c.iterator();

            while(var15.hasNext()) {
               var16 = (com.corrodinggames.rts.gameFramework.e)var15.next();
               if(!var16.e()) {
                  var16.j();
                  as var22 = new as();

                  try {
                     var16.a(var22);
                  } catch (IOException var10) {
                     throw new RuntimeException(var10);
                  }

                  this.d(var22.b(20));
               }
            }

            var2.cf.c.clear();
         }
      } else if(!var2.cf.c.isEmpty()) {
         var15 = var2.cf.c.iterator();

         while(var15.hasNext()) {
            var16 = (com.corrodinggames.rts.gameFramework.e)var15.next();
            if(!var16.e()) {
               if(!var16.l()) {
                  g("Skipped command issued from server");
               } else {
                  var16.j();
                  this.a(var16);
               }
            }
         }

         var2.cf.c.clear();
      }

      while(!this.aN.isEmpty()) {
         au var18 = (au)this.aN.remove();

         try {
            this.a(var18);
         } catch (IOException var11) {
            String var24 = "None";
            c var29 = var18.a;
            if(var29 != null) {
               var24 = var29.g();
               String var31 = var11.getMessage();
               if(var31 == null) {
                  var31 = "IO error";
               }

               var29.a(var31);
               g("IO error on processGamePacket for " + var29.e());
            }

            com.corrodinggames.rts.gameFramework.l.a("Error on processGamePacket ip:" + var24, (Throwable)var11);
         }
      }

      if(this.C) {
         if(!this.B) {
            com.corrodinggames.rts.gameFramework.l.e("Skipping server updates, not networked");
         } else {
            this.ay();
            if(!this.aj) {
               this.e(var1);
            }
         }
      }

      if(this.B) {
         String var20 = "Game paused.";
         if(this.al) {
            var2.bS.b("Game paused.", 100);
         } else {
            var2.bS.a("Game paused.");
         }
      }

      if(var2.bx < this.X) {
         this.Y = false;
      }

      if(this.bm) {
         this.b("queDisconnect");
      }

   }

   public strictfp void b(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(var2 != null) {
         if(!this.C && this.B) {
            boolean var3 = false;
            Iterator var4 = this.aM.iterator();

            while(var4.hasNext()) {
               c var5 = (c)var4.next();
               if(var5.p && !var5.a) {
                  var3 = true;
               }
            }

            if(this.be && this.n()) {
               var2.bS.b("Game ended by server.");
               com.corrodinggames.rts.appFramework.n.o();
            } else if(!var3 && this.n()) {
               var2.bS.b("Server Disconnected.");
               com.corrodinggames.rts.appFramework.n.o();
            }

            if(var3 && (this.Y || this.bs + 1000L < System.currentTimeMillis()) && !this.C) {
               c var6 = this.W();
               if(var6 != null && var6.U > 20000) {
                  String var7 = "Receiving network data: " + var6.V + "/" + var6.U;
                  com.corrodinggames.rts.gameFramework.l.e(var7);
                  var2.bS.d(var7);
                  if(!this.aW && this.bt + 4000L < System.currentTimeMillis()) {
                     this.bt = System.currentTimeMillis();
                     this.o(var7);
                  }

                  this.a(var6, var6.V, var6.U);
               }
            }
         }

      }
   }

   public strictfp void c(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      this.bs = System.currentTimeMillis();
      if(this.B && (this.ah + this.ai < var2.bx || this.ah == -1)) {
         this.d();
         var2.cb.a(this.am);
      }

      if((this.B || var2.cb.j()) && this.N) {
         this.N = false;
         this.w();
      }

      if(this.B) {
         if(this.C && !this.an && this.ah + this.ai / 2 < var2.bx && this.ah != -1) {
            try {
               as var3 = new as();
               var3.a(this.ah);
               var3.a(this.am.a);
               var3.a(this.am.b.size());
               Iterator var4 = this.am.b.iterator();

               while(var4.hasNext()) {
                  al var5 = (al)var4.next();
                  var3.a(var5.b);
               }

               au var7 = var3.b(30);
               this.h(var7);
               if(this.g) {
                  com.corrodinggames.rts.gameFramework.l.e("Sent checksum to client [" + this.ah + "]");
               }

               this.an = true;
            } catch (IOException var6) {
               throw new RuntimeException(var6);
            }
         }

      }
   }

   public strictfp boolean I() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1.bU.e()) {
         if(!this.bu) {
            com.corrodinggames.rts.gameFramework.l.e("shouldGameBePaused: isGoingToBlockThisFrame()==true: " + var1.bU.f());
         }

         this.bu = true;
         return true;
      } else {
         if(this.bu) {
            com.corrodinggames.rts.gameFramework.l.e("shouldGameBePaused: isGoingToBlockThisFrame()==false");
         }

         this.bu = false;
         return false;
      }
   }

   public strictfp void a(float var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var3.bx >= this.X) {
         if(var3.bx > this.X) {
            throw new RuntimeException("game frame:" + var3.bx + " is greater then nest step:" + this.X);
         }

         this.Y = true;
      }

      if(var2 && this.I()) {
         this.Y = true;
      }

   }

   public strictfp void a(au var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.b(var1)) {
         this.d("filtered packet (type:" + var1.b + ")");
      } else {
         k var3;
         int var4;
         int var5;
         boolean var9;
         c var19;
         k var20;
         int var23;
         switch(var1.b) {
         case 10:
            if(this.C) {
               this.d("we are a server! we don\'t follow orders");
            } else if(var1.a.t) {
               this.d("ignoring command");
            } else {
               var3 = new k(var1);
               var4 = var3.f();
               var5 = var3.f();

               for(var23 = 0; var23 < var5; ++var23) {
                  com.corrodinggames.rts.gameFramework.e var27 = var2.cf.b();
                  var27.c = this.X;
                  var27.a(var3);
                  this.a(var27);
               }

               if(var4 < this.X) {
                  String var28 = "New nextBlockingFrame:" + var4 + " is smaller than current step:" + this.X;
                  g(var28);
               }

               this.X = var4;
            }
            break;
         case 20:
            if(!this.C) {
               this.d("we are not a server! skipping");
            } else {
               var3 = new k(var1);
               c var21 = var1.a;
               if(!var21.a()) {
                  com.corrodinggames.rts.game.e var22 = var21.z;
                  if(var22 == null) {
                     this.d("Player is null for message ADDCLIENTCOMMAND, skipping");
                  } else {
                     com.corrodinggames.rts.gameFramework.e var25 = var2.cf.b();
                     var25.a(var3);
                     var25.p = var22;
                     if(var25.r) {
                        this.d("Got system action from client, ignoring (" + var21.c + ")");
                        var25.r = false;
                     }

                     if(var25.c() == null) {
                        g("Invalid command from \'" + var22.v + "\', no team found");
                     } else if(!var25.l()) {
                        g("Ignored command from \'" + var22.v + "\', check failed");
                     } else {
                        this.a(var25);
                     }
                  }
               }
            }
            break;
         case 30:
            var19 = var1.a;
            var20 = new k(var1);
            var5 = var20.f();
            long var24 = var20.i();
            if(this.ag) {
               this.d("PACKET_SYNCCHECKSUM: skipping frame:" + var5 + ", we were told to wait for resync");
            } else {
               as var30 = new as();
               var30.c(0);
               var30.a(var5);
               var30.a(this.ah);
               if(this.ah == var5 && this.am.a != 0L) {
                  var30.a(true);
                  Log.d("RustedWarfare", "Running checksum");
                  var30.a(var24);
                  var30.a(this.am.a);
                  var9 = false;
                  if(var24 != this.am.a) {
                     g("Checksum doesn\'t match. Got:" + var24 + " expected:" + this.am.a);
                     var9 = true;
                     com.corrodinggames.rts.gameFramework.l.e("--- Desync for frame: " + var5 + " ---");
                     Iterator var32 = com.corrodinggames.rts.game.n.c().iterator();

                     while(var32.hasNext()) {
                        com.corrodinggames.rts.game.n var35 = (com.corrodinggames.rts.game.n)var32.next();
                        var35.t();
                     }
                  } else {
                     ++this.aq;
                  }

                  int var34 = var20.f();
                  if(var34 != this.am.b.size()) {
                     Log.d("RustedWarfare", "checkSumSize!=syncCheckList.size()");
                  }

                  var30.e("checkList");
                  var30.a(var34);
                  var30.a(this.am.b.size());
                  Iterator var36 = this.am.b.iterator();

                  while(var36.hasNext()) {
                     al var38 = (al)var36.next();
                     long var40 = var20.i();
                     var30.a(var40);
                     var30.a(var38.b);
                     if(var40 != var38.b && var38.c) {
                        g("[" + var5 + "] check(" + var38.a + "): " + var40 + "!=" + var38.b);
                        var9 = true;
                     }
                  }

                  var30.a("checkList");
                  var30.a(var9);
               } else {
                  var30.a(false);
                  Log.d("RustedWarfare", "got remoteSyncFrame for:" + var5 + " needed:" + this.ah + " lastSyncCheckSum:" + this.am.a);
               }

               if(!this.C) {
                  au var33 = var30.b(31);
                  this.a(var19, var33);
               }
            }
            break;
         case 31:
            if(!this.C) {
               this.d("we are not a server, but got PACKET_SYNCCHECKSUM_STATUS");
            } else {
               var19 = var1.a;
               var20 = new k(var1);
               var20.d();
               var5 = var20.f();
               var23 = var20.f();
               boolean var26 = var20.e();
               if(var26) {
                  long var29 = var20.i();
                  long var31 = var20.i();
                  var20.b("checkList");
                  var20.f();
                  int var37 = var20.f();
                  if(var37 != this.am.b.size()) {
                     Log.d("RustedWarfare", "checkSumSize!=syncCheckList.size()");
                  }

                  Iterator var13 = this.am.b.iterator();

                  while(var13.hasNext()) {
                     al var14 = (al)var13.next();
                     long var15 = var20.i();
                     long var17 = var20.i();
                     if(var15 != var17) {
                        com.corrodinggames.rts.gameFramework.l.b(var14.a + " Checksum [" + var5 + "]. server:" + var15 + " client:" + var17);
                     }
                  }

                  var20.d("checkList");
                  boolean var39 = var20.e();
                  if(this.bq >= var5) {
                     this.d("Not marking desync, already resynced before frame: " + this.bq + "<=" + var5);
                  } else {
                     if(!var19.v && var39) {
                        ++var19.y;
                     }

                     var19.v = var39;
                     if(!var39) {
                        if(this.g) {
                           com.corrodinggames.rts.gameFramework.l.e("checksum: client checksum match [" + var5 + "]");
                        }

                        ++var19.x;
                     } else {
                        com.corrodinggames.rts.gameFramework.l.e("client:" + var19.e() + " desync [" + var5 + "]");
                        if(this.aj && !this.ak) {
                           g("pauseOnDesync is active, pausing");
                           this.ak = true;
                        }
                     }
                  }
               } else if(this.g) {
                  com.corrodinggames.rts.gameFramework.l.e("checksum for:" + var19.e() + " frameMatch==false client:" + var23 + " server:[" + var5 + "]");
               }
            }
            break;
         case 35:
            var3 = new k(var1);
            var3.d();
            var4 = var3.f();
            var5 = var3.f();
            float var6 = var3.g();
            float var7 = var3.g();
            if(!this.C && (double)var6 < 0.1D) {
               a("resync packet with setCurrentStepRate:" + var6 + " is too small", true);
            }

            c var8 = var1.a;
            if(var8.t) {
               this.d("ignoring resync command");
            } else {
               var9 = var3.e();
               boolean var10 = var3.e();
               byte[] var11;
               if(var10) {
                  if(!this.C) {
                     this.d("we are not a server, but got a debug game save! skipping");
                  } else {
                     var11 = var3.c("gameSave");
                     this.a(var11, var8);
                  }
               } else {
                  com.corrodinggames.rts.gameFramework.l.e("Reloading from network save");
                  if(var9 && !this.C) {
                     this.a(false, true, false);
                  }

                  var11 = var3.c("gameSave");
                  com.corrodinggames.rts.gameFramework.l.e("Save size: " + var11.length);
                  if(this.l) {
                     this.a(var11, var8);
                  }

                  var2.cb.a(var11, var2.bx, var4, var5, var6, var7);
                  k var12 = new k(var11);
                  var2.a("Resyncing game from server...", true);
                  var2.ca.a(var12, true, true, true);
                  var2.Z();
                  ++this.ar;
                  var2.bx = var4;
                  var2.by = var5;
                  this.X = var4 + 1;
                  this.ag = false;
                  this.ah = this.X + 1;
                  this.am.a = 0L;
                  if((double)var6 < 0.1D) {
                     a("resync setCurrentStepRate:" + var6 + " is too small", true);
                  }

                  this.a(var6, "rsync");
                  this.J = var7;
               }
            }
            break;
         default:
            this.d("we did not handle packet:" + var1.b);
         }

      }
   }

   public synchronized strictfp boolean b(au var1) {
      if(this.C) {
         c var2 = var1.a;
         if(var2 == null) {
            return false;
         }

         if(!var2.p && var1.b != 105 && var1.b != 110 && var1.b != 111 && var1.b != 108 && var1.b != 160) {
            return true;
         }
      }

      return false;
   }

   public synchronized strictfp void c(au var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.b(var1)) {
         this.d("filtered packet (type:" + var1.b + ")");
      } else {
         c var3;
         k var4;
         int var5;
         boolean var6;
         int var7;
         int var9;
         String var10;
         k var34;
         byte var36;
         c var37;
         int var40;
         String var41;
         String var42;
         c var43;
         String var44;
         k var49;
         String var51;
         String var53;
         int var54;
         boolean var55;
         boolean var59;
         boolean var60;
         String var62;
         int var69;
         String var71;
         int var73;
         byte var76;
         switch(var1.b) {
         case 4:
            var3 = var1.a;
            var4 = new k(var1);
            var36 = var4.d();
            var4.f();
            var4.f();
            break;
         case 105:
            this.d("got PACKET_GET_SERVER_INFO");
            if(!this.C) {
               this.d("we are not a server! skipping");
            }
            break;
         case 106:
            if(this.C) {
               this.d("we are a server! we don\'t follow orders");
            } else {
               var34 = new k(var1);
               var37 = var1.a;
               var34.l();
               var34.f();
               this.ay.a = (ai)var34.b(ai.class);
               this.ay.b = var34.l();
               this.ay.c = var34.f();
               this.ay.d = var34.f();
               this.ay.e = var34.e();
               this.ay.f = var34.f();
               var36 = var34.d();
               this.G = var34.e();
               this.H = var34.e();
               this.av = true;
               if(var36 >= 1) {
                  this.aw = var34.f();
                  this.ax = var34.f();
               }

               if(var36 >= 2) {
                  this.ay.g = var34.f();
                  this.ay.h = var34.g();
                  this.ay.i = var34.e();
                  this.ay.j = var34.e();
               }

               if(var36 >= 3) {
                  var6 = var34.e();
                  if(var6) {
                     try {
                        com.corrodinggames.rts.game.units.custom.l.a(var34);
                        this.x = true;
                     } catch (bd var31) {
                        this.b("Missing unit:" + var31.getMessage() + " d:" + var31.b);
                        this.b("Server sync mismatch", var31.getMessage());
                        if(!com.corrodinggames.rts.gameFramework.l.av()) {
                           var2.i(var31.getMessage());
                        }

                        var51 = "Server sync mismatch";
                        if(var31.a != null) {
                           var51 = var31.a;
                        }

                        var2.d(var51, var31.getMessage());
                        break;
                     }
                  }
               }

               if(var36 >= 4) {
                  this.ay.l = var34.e();
               }

               if(var36 >= 5) {
                  this.ay.m = var34.e();
               }

               if(var36 >= 6) {
                  this.ay.n = var34.e();
               }

               if(var36 >= 7) {
                  this.ay.o = var34.e();
                  this.ay.p = var34.e();
               }

               if(var36 >= 8) {
                  this.ay.q = var34.f();
               }

               com.corrodinggames.rts.appFramework.n.o();
            }
            break;
         case 108:
            var3 = var1.a;
            var4 = new k(var1);
            long var63 = var4.i();
            var4.d();
            as var68 = new as();
            var68.a(var63);
            var68.c(1);
            var54 = var2.b();
            if(var54 > 130) {
               var54 = 130;
            }

            var68.c(var54);
            au var77 = var68.b(109);
            this.a(var3, var77);
            break;
         case 109:
            if(!this.C) {
               this.d("we are not a server! skipping");
            } else {
               long var52 = System.currentTimeMillis();
               c var61 = var1.a;
               var49 = new k(var1);
               long var65 = var49.i();
               var76 = var49.d();
               byte var75 = 0;
               if(var76 >= 1) {
                  var75 = var49.d();
               }

               var69 = (int)(var52 - var65);
               var61.A = var69;
               var61.B = var52;
               if(var61.z != null) {
                  var61.z.W = var69;
                  var61.z.X = var52;
                  var61.z.V = var75;
               }

               if(var61.q && this.C && this.D && this.z != null) {
                  this.z.W = var69;
                  this.z.X = var52;
               }

               if(!this.aW) {
                  com.corrodinggames.rts.appFramework.n.o();
               }
            }
            break;
         case 110:
            this.d("got REGISTER_CONNECTION");
            if(!this.C) {
               this.d("we are not a server! skipping");
            } else {
               var34 = new k(var1);
               var37 = var1.a;
               var42 = var34.l();
               var40 = var34.f();
               var7 = var34.f();
               var54 = var34.f();
               var53 = var34.l();
               var10 = var34.j();
               var62 = null;
               var37.E = var7;
               if(var40 >= 1) {
                  var37.L = var34.l();
               }

               if(var40 >= 2) {
                  var62 = var34.l();
               }

               var73 = -1;
               if(var40 >= 3) {
                  var73 = var34.f();
               }

               var71 = "MISSING";
               if(var40 >= 4) {
                  var71 = var34.l();
               }

               String var14 = "";
               if(var40 >= 5) {
                  var14 = var34.l();
               }

               if(var53.length() > 20) {
                  this.a(var37, "Your username is too long");
                  var37.a("kicked");
               } else {
                  var53 = p(var53);
                  if(var53.length() < 2) {
                     this.a(var37, "Your username is too short");
                     var37.a("kicked");
                  } else {
                     com.corrodinggames.rts.game.e var15 = null;
                     if(var62 != null) {
                        var15 = com.corrodinggames.rts.game.n.a(var62);
                        if(var15 != null) {
                           this.d("Existing player: " + var15.k + " - " + var15.v);
                        }
                     }

                     aj var16 = this.a(var37);
                     String var17;
                     if(var16 != null) {
                        com.corrodinggames.rts.gameFramework.l.e("Connection banned for " + var16.b() + " more seconds");
                        var17 = var16.a();
                        this.a(var37, var17);
                        var37.a("kicked");
                     } else {
                        var17 = this.d.a(var37, var53, var7, var54, var37.L, var15);
                        if(var17 != null) {
                           this.a(var37, var17);
                           var37.a("kicked");
                        } else if(var7 < this.e && !this.v) {
                           this.a(var37, "Game is out of date, please update to v" + var2.u());
                           var37.a("kicked");
                        } else if(var7 > this.e && !this.v) {
                           this.a(var37, "Your client is newer then the server. Server is on: v" + var2.u());
                           var37.a("kicked");
                        } else if(!this.v && var73 != var2.z()) {
                           com.corrodinggames.rts.gameFramework.l.e("New Player kicked: Unit checksum mismatch: clientUnitsChecksum=" + var73 + " game.getAllUnitsChecksum():" + var2.z());
                           this.a(var37, "Your core units are different to the server\'s core units. Game can not be synchronized");
                           var37.a("kicked");
                        } else {
                           String var18;
                           if(!this.v) {
                              var18 = this.g(var37.M);
                              if(!var18.equals(var71)) {
                                 com.corrodinggames.rts.gameFramework.l.e("New Player kicked: Integrity Check Failed: expectedResponse=" + var18 + " clientResponse=" + var71);
                                 this.a(var37, "Your \'Rusted Warfare\' client is different to the server. Game can not be synchronized.");
                                 var37.a("kicked");
                                 break;
                              }
                           }

                           if(!this.aW && this.ay.p) {
                              this.a(var37, "Room is locked. New players cannot join this server.");
                              var37.a("kicked");
                           } else if(this.aW && var15 == null && !this.s) {
                              this.a(var37, "A game has already been started on this server");
                              var37.a("kicked");
                           } else {
                              if(this.n != null && var15 == null) {
                                 var18 = com.corrodinggames.rts.gameFramework.f.e(this.n);
                                 if(!var18.equals(var10)) {
                                    if(var10 == null) {
                                       com.corrodinggames.rts.gameFramework.l.b("processSystemPacket", "Player tried to join but needs a password");
                                    } else {
                                       com.corrodinggames.rts.gameFramework.l.b("processSystemPacket", "Player tried to join but had an incorrect password");
                                    }

                                    this.d(var37);
                                    break;
                                 }
                              }

                              var18 = this.h(this.W);
                              if(!var18.equals(var14)) {
                                 var37.c("no extra");
                                 var37.N = true;
                              }

                              if(var37.z == null) {
                                 Object var19 = this.bC;
                                 synchronized(this.bC) {
                                    int var20;
                                    if(var15 == null) {
                                       var20 = com.corrodinggames.rts.game.n.G();
                                    } else {
                                       var20 = var15.k;
                                    }

                                    if(var20 == -1 && !this.v) {
                                       this.a(var37, "No free slots on server");
                                       var37.a("no free slots");
                                    } else {
                                       String var21 = this.d.a(var37, var53);
                                       if(var21 != null) {
                                          this.a(var37, var21);
                                          var37.a("kicked");
                                       } else {
                                          aq.a(var37);
                                          if(!this.v && var37.O) {
                                             this.a(var37, "");
                                             var37.a("kicked");
                                          } else {
                                             String var22 = null;
                                             if(var15 != null) {
                                                var37.z = var15;
                                                String var23 = "";
                                                if(this.aW) {
                                                   if(var15.b()) {
                                                      var23 = " (Spectator)";
                                                   } else {
                                                      var23 = " (Team " + var15.h() + ")";
                                                   }
                                                }

                                                this.j("\'" + var37.z.v + "\' reconnected. " + var23);
                                                var37.w = true;
                                                var22 = var15.v;
                                                var15.P = var37.m;
                                             } else {
                                                if(this.v && var20 == -1) {
                                                   var37.z = new com.corrodinggames.rts.game.e(-3);
                                                } else {
                                                   var37.z = new com.corrodinggames.rts.game.e(var20);
                                                   var37.z.r = var20 % 2;
                                                }

                                                if(this.aW && this.s) {
                                                   var37.w = true;
                                                }
                                             }

                                             if(var15 == null && var53 != null) {
                                                ArrayList var78 = this.ax();

                                                for(int var24 = 0; var24 < 10; ++var24) {
                                                   boolean var25 = false;
                                                   String var26 = var53;
                                                   if(var24 > 0) {
                                                      var26 = var53 + "(" + var24 + ")";
                                                   }

                                                   Iterator var27 = var78.iterator();

                                                   while(var27.hasNext()) {
                                                      com.corrodinggames.rts.game.n var28 = (com.corrodinggames.rts.game.n)var27.next();
                                                      if(var26.equalsIgnoreCase(var28.v)) {
                                                         var25 = true;
                                                      }
                                                   }

                                                   if(!var25) {
                                                      var53 = var26;
                                                      break;
                                                   }
                                                }
                                             }

                                             var37.z.v = var53;
                                             var37.z.O = var62;
                                             var37.z.P = var37.m;
                                             var37.E = var7;
                                             com.corrodinggames.rts.gameFramework.l.b("processSystemPacket", "New player: " + var53 + ", networkVersion:" + var37.E + " existing:" + (var15 != null));
                                             var37.p = true;
                                             if(var15 == null) {
                                                this.d.a((com.corrodinggames.rts.game.n)var37.z);
                                             }

                                             com.corrodinggames.rts.appFramework.n.o();
                                             this.e(var37);
                                             this.c(var37);
                                             this.d.c(var37, var53, var22);
                                             if((var15 != null || this.s) && this.aW) {
                                                boolean var79 = true;
                                                this.a(var37, var79);
                                             }
                                          }
                                       }
                                    }
                                 }
                              } else {
                                 com.corrodinggames.rts.gameFramework.l.b("processSystemPacket", "This connection already has a player");
                              }
                           }
                        }
                     }
                  }
               }
            }
            break;
         case 111:
            var34 = new k(var1);
            var37 = var1.a;
            var42 = null;

            try {
               var42 = var34.l();
            } catch (IOException var30) {
               com.corrodinggames.rts.gameFramework.l.a("Error reading disconnect reason", (Throwable)var30);
            }

            this.d("Got a disconnect packet:" + var42);
            if(var37 != null) {
               var37.a(false, false, var42);
            }

            if(!this.C) {
               ;
            }
            break;
         case 112:
            if(!this.C) {
               this.d("we are not a server! skipping");
            } else {
               var3 = var1.a;
               var4 = new k(var1);
               var3.C = var4.e();
               var3.D = var4.e();
            }
            break;
         case 113:
            if(this.C) {
               this.d("we are a server! skipping: " + var1.b);
            } else {
               a(bE);
            }
            break;
         case 115:
            if(this.C) {
               this.d("we are a server! we don\'t follow orders");
            } else {
               var34 = new k(var1);
               var34.b(var1.a.E);
               var37 = var1.a;
               var5 = var34.f();
               Object var57 = null;
               var7 = 8;
               var55 = false;
               if(var34.c() >= 90) {
                  var59 = false;
                  if(var34.c() >= 141) {
                     var59 = true;
                     var55 = var34.e();
                  }

                  var7 = var34.f();
                  com.corrodinggames.rts.game.n.b(var7, false);
                  var34.a("teams", var59);
                  if(var7 > com.corrodinggames.rts.game.n.c) {
                     throw new IOException("Cannot load:" + var7 + " teams");
                  }
               } else if(this.aW) {
                  g("Warning old team system used in started game, stream version:" + var34.c());
               }

               for(var9 = 0; var9 < var7; ++var9) {
                  Object var67 = com.corrodinggames.rts.game.n.k(var9);
                  boolean var72 = var34.e();
                  if(!var72) {
                     if(var67 != null) {
                        if(this.aW) {
                           g("Warning team:" + var9 + " removed while game is running");
                        }

                        ((com.corrodinggames.rts.game.n)var67).I();
                     }
                  } else {
                     var73 = var34.f();
                     if(var67 == null) {
                        if(this.aW) {
                           g("Warning team:" + var9 + " added while game is running");
                        }

                        if(!this.C && var67 instanceof com.corrodinggames.rts.game.a.a) {
                           g("Warning we are a client with an AI team");
                        }

                        var67 = new com.corrodinggames.rts.game.e(var9);
                     }

                     if(var55) {
                        ((com.corrodinggames.rts.game.n)var67).a(var34);
                     } else {
                        ((com.corrodinggames.rts.game.n)var67).a(var34, this.aW);
                     }
                  }

                  if(var67 != null && ((com.corrodinggames.rts.game.n)var67).k == var5) {
                     var57 = var67;
                  }
               }

               if(var34.c() >= 90) {
                  var34.d("teams");
               }

               this.z = (com.corrodinggames.rts.game.n)var57;
               this.ay.d = var34.f();
               this.ay.c = var34.f();
               this.ay.e = var34.e();
               this.ay.f = var34.f();
               var76 = var34.d();
               this.aw = var34.f();
               this.ax = var34.f();
               if(var76 >= 2) {
                  this.ay.g = var34.f();
                  this.ay.h = var34.g();
                  this.ay.i = var34.e();
                  this.ay.j = var34.e();
               }

               if(var76 >= 3) {
                  var60 = var34.e();
                  if(var60) {
                     try {
                        com.corrodinggames.rts.game.units.custom.l.a(var34);
                        this.x = true;
                     } catch (bd var33) {
                        this.b("Missing unit:" + var33.getMessage() + " d:" + var33.b);
                        this.b("Connection Failed", var33.getMessage());
                        if(!com.corrodinggames.rts.gameFramework.l.av()) {
                           var2.i(var33.getMessage());
                        }

                        var2.d("Connection Failed", var33.getMessage());
                        break;
                     }
                  }
               }

               if(var76 >= 4) {
                  this.ay.l = var34.e();
               }

               if(var76 >= 5) {
                  this.al = var34.e();
               }

               com.corrodinggames.rts.appFramework.n.o();
            }
            break;
         case 116:
            if(this.C) {
               this.d("we are a server! we don\'t follow orders");
            } else {
               var34 = new k(var1);
               var37 = var1.a;
               var5 = var34.f();
               var6 = var34.e();
               if(var6 && !this.be) {
                  this.be = var6;
               }
            }
            break;
         case 117:
            var3 = var1.a;
            if(this.C && !var3.q) {
               this.d("we are a server! skipping: " + var1.b);
            } else {
               var4 = new k(var1);
               var4.d();
               var5 = var4.f();
               var44 = var4.l();
               ae var58 = new ae();
               var58.d = true;
               var58.c = var5;
               var58.b = var44;
               a(var58);
            }
         case 118:
            break;
         case 120:
            if(this.C) {
               this.d("error, we are a server but got: PACKET_START_GAME");
            } else {
               var34 = new k(var1);
               var34.d();
               this.ay.a = (ai)var34.b(ai.class);
               if(this.ay.a == ai.c) {
                  this.aA = var34.u();
               } else if(this.ay.a == ai.b) {
                  this.aB = var34.u();
               }

               this.az = var34.l();
               this.aB();
            }
            break;
         case 122:
            if(this.C) {
               this.d("error, we are a server but got: PACKET_RETURN_TO_BATTLEROOM");
            } else {
               this.aC();
            }
            break;
         case 140:
            if(!this.C) {
               this.d("we are not a server! skipping");
            } else {
               var3 = var1.a;
               var4 = new k(var1);
               com.corrodinggames.rts.game.e var45 = var3.z;
               if(var45 == null) {
                  if(!var3.q) {
                     this.d("player is null for message, skipping");
                     break;
                  }

                  this.d("Allowing message from non player on forwarding connection");
                  var45 = this.bk;
               }

               var44 = var4.l();
               var4.d();
               var44 = i(var44);
               if(this.d.a(var3, var45.v, var44)) {
                  if(this.aC.a(var3, '\uea60') > this.h) {
                     if(com.corrodinggames.rts.gameFramework.f.a(var3.g, System.nanoTime()) > 60000L) {
                        var3.g = System.nanoTime();
                        this.j("Anti-spam: Too many messages from \'" + var3.e() + "\'");
                     }

                     if(this.g) {
                        com.corrodinggames.rts.gameFramework.l.e("extraDebug:" + var44);
                     }
                  } else {
                     this.a(var3, var45, var45.v, var44);
                     this.d.b(var3, var45.v, var44);
                     this.b(var3, var45, var45.v, var44);
                  }
               }
            }
            break;
         case 141:
            if(this.C) {
               var3 = var1.a;
               if(!var3.q) {
                  this.d("error, we are a server but got: PACKET_RECEIVE_CHAT_FROM_SERVER");
                  break;
               }
            }

            var34 = new k(var1);
            var41 = var34.l();
            var36 = var34.d();
            var44 = var34.j();
            var34.f();
            var7 = -1;
            if(var36 >= 3) {
               var7 = var34.f();
            }

            this.b((c)null, var7, var44, var41);
            break;
         case 150:
            if(this.C) {
               this.d("error, we are a server but got: PACKET_SEND_KICK");
            } else {
               var34 = new k(var1);
               var41 = var34.l();
               var41 = com.corrodinggames.rts.gameFramework.h.a.c(var41);
               this.d("we got kicked, reason:" + var41);
               this.b("I was kicked");
               this.b("Kicked", "Kicked: " + var41);
               var2.d("Kicked", "Kicked: " + var41);
               var2.i("Kicked: " + var41);
            }
            break;
         case 151:
            var3 = var1.a;
            if(this.C && !var3.q) {
               this.d("error, we are a server but got: 151");
            } else {
               long var39 = br.a();
               var49 = new k(var1);
               var7 = var49.f();
               var54 = var49.f();
               if(var49.e()) {
                  aq.i = var49.f();
               }

               if(var49.e()) {
                  aq.j = var49.f();
               }

               var53 = "";
               if(var54 == 0) {
                  var53 = "" + aq.i;
               }

               if(var54 == 1) {
                  var53 = "" + aq.j;
               }

               if(var54 == 2) {
                  var53 = this.g(aq.i);
               }

               if(var54 == 3) {
                  var53 = com.corrodinggames.rts.gameFramework.f.c(aq.i + "|" + aq.j);
               }

               if(var54 == 4) {
                  var53 = com.corrodinggames.rts.gameFramework.f.c(aq.i + "|" + aq.j);
               }

               if(var54 == 5 || var54 == 6) {
                  var10 = var49.l();
                  var62 = var49.l();
                  var73 = var49.f();
                  if(var54 == 6) {
                     var62 = var62 + aq.i;
                  }

                  if(var73 > 10000000) {
                     var53 = "max";
                  } else {
                     var53 = "-1";

                     for(int var74 = 0; var74 <= var73; ++var74) {
                        if(com.corrodinggames.rts.gameFramework.f.c(var62 + var74).equals(var10)) {
                           var53 = "" + var74;
                           break;
                        }
                     }
                  }
               }

               if(var54 == 7) {
                  var10 = var49.l();
                  var69 = var49.f();
                  if(var69 > 10000) {
                     var53 = "max";
                  } else {
                     var53 = "";

                     for(var73 = 0; var73 < var69; ++var73) {
                        var53 = var53 + var10;
                     }
                  }
               }

               float var64 = br.a(var39);
               as var70 = new as();
               var70.a(var7);
               var70.a(var54);
               var70.c(var53);
               var70.a(var64);
               this.a(var3, var70.b(152));
            }
            break;
         case 160:
            var34 = new k(var1);
            var37 = var1.a;
            var42 = var34.l();
            var40 = var34.f();
            var7 = var34.f();
            var55 = true;
            if(var40 >= 1) {
               var54 = var34.f();
            }

            if(var37.i) {
               com.corrodinggames.rts.gameFramework.l.e("steam: request info packet");
            }

            if(var40 >= 2) {
               var53 = var34.j();
               if(var53 != null) {
                  var37.c("Using query string: " + var53);
                  var37.o = var53;
               }
            }

            if(var40 >= 3) {
               var34.l();
            }

            if(var40 >= 4) {
               var53 = var34.l();
               var10 = var34.l();
               if(com.corrodinggames.rts.gameFramework.l.ax()) {
                  var37.c("Misc: " + var10);
               }
            }

            this.g(var37);
            break;
         case 161:
            if(this.C) {
               this.d("we are a server! we don\'t PREREGISTER_INFO");
            } else {
               var34 = new k(var1);
               var37 = var1.a;
               if(var37.i) {
                  com.corrodinggames.rts.gameFramework.l.e("steam: got info packet");
               }

               var42 = var34.l();
               var40 = var34.f();
               var7 = var34.f();
               var54 = var34.f();
               var53 = var34.l();
               this.S = var34.l();
               var37.E = var7;
               if(var40 >= 1) {
                  this.T = var34.f();
               }

               if(var40 >= 2) {
                  this.U = var34.f();
                  this.V = var34.f();
               }

               if(this.bz) {
                  this.d("PACKET_SEND_PREREGISTER_INFO: Register connection has already been sent (resending)");
               }

               this.h(var37);
            }
            break;
         case 163:
            if(this.C) {
               this.d("we are already a server");
            } else {
               var34 = new k(var1);
               var34.d();
               int var35 = var34.f();
               var5 = var34.f();
               var44 = var34.j();
               this.d("Relay version: " + var35);
            }
            break;
         case 170:
            this.d("Got \'become server\' packet");
            if(this.C) {
               this.d("we are already a server");
            } else {
               var3 = var1.a;
               var4 = new k(var1);
               var36 = var4.d();
               var6 = var4.e();
               boolean var47 = var4.e();
               var51 = var4.j();
               var59 = var4.e();
               var60 = var4.e();
               var62 = var4.j();
               boolean var66 = false;
               if(var36 >= 1) {
                  var66 = var4.e();
               }

               var71 = null;
               if(var36 >= 2) {
                  var71 = var4.j();
               }

               this.d("Multicast:" + var66);
               var3.r = var66;
               if(var6) {
                  var3.q = true;
               }

               if(var47) {
                  var3.s = true;
               }

               this.D = true;
               this.E = var62;
               var2.bX.n = null;
               var2.bX.o = var59;
               var2.bX.q = var60;
               this.c(false);
               if(var71 != null) {
                  if(this.z != null) {
                     this.z.P = var71;
                  } else {
                     com.corrodinggames.rts.gameFramework.l.e("Become server: No local team");
                  }
               }

               if(var2.bX.q) {
                  ;
               }

               if(var51 != null) {
                  var2.bQ.networkServerId = var51;
               }

               if(var2.bx > 60) {
                  this.aa = true;
               }

               if(!this.x && !this.aW) {
                  com.corrodinggames.rts.gameFramework.l.e("enableAllCustomUnitsPossible mods:" + this.o);
                  com.corrodinggames.rts.game.units.custom.ag.b(this.o);
                  this.x = true;
               }
            }
            break;
         case 172:
            var3 = var1.a;
            if(!var3.q) {
               this.d("forwarding not allowed on this connection");
            } else {
               this.d("got FORWARD_CLIENT_ADD");
               var4 = new k(var1);
               var36 = var4.d();
               var40 = var4.f();
               String var46 = var4.l();
               var51 = var4.j();
               var53 = null;
               if(var36 >= 1) {
                  var53 = var4.j();
               }

               if(this.a(var3, var40) != null) {
                  this.d("Not adding client:" + var40 + " already exists");
               } else {
                  c var56 = this.a(var3, var40, var46, var53);
                  if(var56 != null && var51 != null) {
                     com.corrodinggames.rts.game.e var11 = com.corrodinggames.rts.game.n.b(var46);
                     if(var11 == null) {
                        this.d("PACKET_FORWARD_CLIENT_ADD: Failed to find existing player with id:" + var46);
                        Iterator var12 = com.corrodinggames.rts.game.n.c().iterator();

                        while(var12.hasNext()) {
                           com.corrodinggames.rts.game.n var13 = (com.corrodinggames.rts.game.n)var12.next();
                           if(var13 != null) {
                              this.d("option: " + var13.v + " - " + var13.P + " - localPlayer:" + (this.z == var13));
                           }
                        }
                     } else {
                        var11.O = var51;
                     }
                  }
               }
            }
            break;
         case 173:
            var3 = var1.a;
            if(!var3.q) {
               this.d("forwarding not allowed on this connection");
            } else {
               this.d("got FORWARD_CLIENT_REMOVE");
               var4 = new k(var1);
               var36 = var4.d();
               var40 = var4.f();
               var43 = null;
               c var50 = this.a(var3, var40);
               if(var50 != null) {
                  this.b(var50, var43);
               }
            }
            break;
         case 174:
            var3 = var1.a;
            if(!var3.q) {
               this.d("forwarding not allowed on this connection");
            } else {
               var4 = new k(var1);
               var5 = var4.f();
               byte[] var38 = var4.t();
               var43 = this.a(var3, var5);
               if(var43 == null) {
                  this.d("PACKET_FORWARD_CLIENT_FROM failed, cannot find client");
               } else if(!(var43.d instanceof h)) {
                  this.d("PACKET_FORWARD_CLIENT_FROM failed, socket is wrong type");
               } else {
                  h var48 = (h)var43.d;
                  var48.d.a(var38);
               }
            }
            break;
         case 175:
            this.d("got PACKET_FORWARD_CLIENT_TO");
            break;
         case 176:
            this.d("got PACKET_FORWARD_CLIENT_TO_REPEATED");
            break;
         case 178:
            this.d("got PACKET_RECONNECT_TO");
            var3 = var1.a;
            if(this.C && !var3.q) {
               this.d("we are a server, ");
            } else {
               var4 = new k(var1);
               var4.d();
               var5 = var4.f();
               var6 = var4.e();
               var7 = var4.f();
               ArrayList var8 = new ArrayList();

               for(var9 = 0; var9 < var7; ++var9) {
                  var10 = var4.l();
                  var8.add(var10);
               }

               this.a(var8, var6);
            }
            break;
         default:
            this.d("we did not handle packet:" + var1.b);
         }

      }
   }

   public static strictfp String i(String var0) {
      if(var0 == null) {
         return null;
      } else {
         if(var0.length() > 250) {
            var0 = var0.substring(0, 250);
         }

         if(var0.contains("\n")) {
            var0 = var0.replace("\n", "?");
         }

         var0 = var0.replace(" ", ".");
         boolean var1 = false;
         char[] var2 = var0.toCharArray();
         int var3 = var2.length;

         int var4;
         for(var4 = 0; var4 < var3; ++var4) {
            char var5 = var2[var4];
            if(Character.isISOControl(var5)) {
               var1 = true;
               break;
            }
         }

         if(var1) {
            StringBuilder var7 = new StringBuilder();
            char[] var8 = var0.toCharArray();
            var4 = var8.length;

            for(int var9 = 0; var9 < var4; ++var9) {
               char var6 = var8[var9];
               if(!Character.isISOControl(var6)) {
                  var7.append(var6);
               }
            }

            var0 = var7.toString();
         }

         return var0;
      }
   }

   public strictfp void J() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bS.g.m();
   }

   public strictfp void K() {
      this.b((String)null, (String)null);
   }

   public strictfp void b(String var1, String var2) {
      com.corrodinggames.rts.gameFramework.l.e("closeBattleroom..");
      com.corrodinggames.rts.appFramework.n.a(var1, var2);
      this.d.d();
   }

   public synchronized strictfp void L() {
      Iterator var1 = this.aM.iterator();

      while(var1.hasNext()) {
         c var2 = (c)var1.next();
         if(var2.p) {
            this.c(var2);
         }
      }

   }

   public synchronized strictfp void c(c var1) {
      if(!this.C) {
         this.d("sendServerInfo: we are not a server!");
      } else {
         as var2 = new as();

         try {
            var2.c("com.corrodinggames.rts");
            var2.a(this.e);
            var2.a((Enum)this.ay.a);
            if(this.v) {
               var2.c("<CHAT ONLY>");
            } else {
               var2.c(this.ay.b == null?"<NULL>":com.corrodinggames.rts.gameFramework.e.a.o(this.ay.b));
            }

            var2.a(this.ay.c);
            var2.a(this.ay.d);
            var2.a(this.ay.e);
            var2.a(this.ay.f);
            var2.c(8);
            var2.a(this.d.a(var1));
            boolean var3 = this.d.b(var1);
            var2.a(var3);
            var2.a(this.aw);
            var2.a(this.ax);
            var2.a(this.ay.g);
            var2.a(this.ay.h);
            var2.a(this.ay.i);
            var2.a(this.ay.j);
            if(this.v) {
               var2.a(false);
            } else {
               var2.a(true);
               com.corrodinggames.rts.game.units.custom.l.a(var2);
            }

            var2.a(this.ay.l);
            var2.a(this.ay.m);
            var2.a(this.ay.n);
            var2.a(this.ay.o);
            var2.a(this.ay.p);
            var2.a(this.ay.q);
         } catch (IOException var4) {
            throw new RuntimeException(var4);
         }

         this.a(var1, var2.b(106));
      }
   }

   public synchronized strictfp void a(c var1, String var2) {
      if(!this.C) {
         this.d("sendKick: we are not a server!");
      } else {
         this.d("kicking client reason:" + var2);
         as var3 = new as();

         try {
            var3.c(var2);
         } catch (IOException var5) {
            throw new RuntimeException(var5);
         }

         this.a(var1, var3.b(150));
      }
   }

   public synchronized strictfp void d(c var1) {
      if(!this.C) {
         this.d("sendIncorrectPassword: we are not a server!");
      } else {
         this.d("sendIncorrectPassword");
         as var2 = new as();

         try {
            var2.a((int)0);
         } catch (IOException var4) {
            throw new RuntimeException(var4);
         }

         this.a(var1, var2.b(113));
      }
   }

   public strictfp void M() {
      if(this.C) {
         int var1;
         com.corrodinggames.rts.game.n var2;
         for(var1 = 0; var1 < com.corrodinggames.rts.game.n.f; ++var1) {
            var2 = com.corrodinggames.rts.game.n.k(var1);
            if(var2 != null) {
               if(this.v) {
                  var2.ac = 0;
               } else if(var2.b()) {
                  var2.ac = 100;
               } else {
                  var2.ac = var2.r;
               }

               if(var2.b()) {
                  var2.D = -1;
               } else {
                  int var3 = var2.S();
                  if(var2.C != null) {
                     var3 = var2.C.intValue();
                  } else if(this.a(var3, (com.corrodinggames.rts.game.n)null)) {
                     var3 = -1;
                  }

                  var2.D = var3;
               }
            }
         }

         for(var1 = 0; var1 < com.corrodinggames.rts.game.n.f; ++var1) {
            var2 = com.corrodinggames.rts.game.n.k(var1);
            if(var2 != null && var2.D == -1 && !var2.b()) {
               var2.D = this.N();
            }
         }
      }

   }

   public strictfp int N() {
      for(int var1 = 0; var1 < 10; ++var1) {
         if(!this.f(var1)) {
            return var1;
         }
      }

      return -1;
   }

   public strictfp boolean f(int var1) {
      for(int var2 = 0; var2 < com.corrodinggames.rts.game.n.f; ++var2) {
         com.corrodinggames.rts.game.n var3 = com.corrodinggames.rts.game.n.k(var2);
         if(var3 != null && var3.D == var1 && !var3.b()) {
            return true;
         }
      }

      return false;
   }

   public strictfp boolean a(int var1, com.corrodinggames.rts.game.n var2) {
      for(int var3 = 0; var3 < com.corrodinggames.rts.game.n.f; ++var3) {
         com.corrodinggames.rts.game.n var4 = com.corrodinggames.rts.game.n.k(var3);
         if(var4 != null && var4 != var2 && var4.C != null && var4.C.intValue() == var1 && !var4.b()) {
            return true;
         }
      }

      return false;
   }

   public strictfp void O() {
      if(this.C) {
         long var1 = System.currentTimeMillis();
         int var3 = com.corrodinggames.rts.gameFramework.l.B().by;
         if(this.z != null && !this.D) {
            this.z.W = -99;
            this.z.X = var1;
         }

         this.M();

         for(int var4 = 0; var4 < com.corrodinggames.rts.game.n.c; ++var4) {
            com.corrodinggames.rts.game.n var5 = com.corrodinggames.rts.game.n.k(var4);
            if(var5 != null) {
               boolean var6 = this.z == var5;
               var5.c(var6);
               if(!this.aW) {
                  ;
               }

               if(this.aW && !this.F && !var5.w) {
                  boolean var7 = false;
                  if(var5.B()) {
                     var7 = true;
                  }

                  long var8 = 60000L;
                  if(var5.Z > 180000) {
                     var8 = 160000L;
                  }

                  boolean var10 = false;
                  if(this.aa) {
                     if(var5.Y == -1L) {
                        var5.Y = var1;
                        var5.Z = var3;
                     }

                     if((this.ak || this.al) && !var5.ab) {
                        var5.Y = var1;
                        var5.Z = var3;
                     }

                     if(var5.Y + var8 < var1) {
                        var10 = true;
                     }
                  }

                  if(var5.ab != var10) {
                     var5.ab = var10;
                  }

                  if(var10) {
                     var7 = true;
                     if(!var5.aa) {
                        boolean var11 = var5.G || var5.F || var5.J || var5.b();
                        if(!var11) {
                           var5.aa = true;
                        }
                     }
                  }

                  if(var5.J != var7) {
                     if(var7 && !var5.G && !var5.F && !var5.I && !var5.b()) {
                        String var13 = "-t [Sharing control due to disconnect]";
                        if(var10) {
                           var13 = "-t [Sharing control due to afk]";
                        }

                        com.corrodinggames.rts.gameFramework.l.e(var5.v + " - " + var13);
                        int var12 = com.corrodinggames.rts.game.n.a(var5.r, true);
                        if(var12 > 1) {
                           this.a((c)null, var5, var5.v, var13);
                        }
                     }

                     var5.J = var7;
                  }
               }
            }
         }
      }

   }

   public strictfp void P() {
      if(this.au == 0L) {
         this.au = System.currentTimeMillis();
      }

   }

   public strictfp void Q() {
      this.au = 0L;
      this.e((c)null);
   }

   public strictfp void e(c var1) {
      if(!this.C) {
         this.d("sendUpdatePlayer: we are not a server!");
      } else {
         this.O();
         Iterator var2 = this.aM.iterator();

         while(var2.hasNext()) {
            c var3 = (c)var2.next();
            if(var3.p) {
               as var4 = new as(var3.E);

               try {
                  var4.a(var3.c());
                  int var5 = com.corrodinggames.rts.game.n.c;
                  boolean var6 = false;
                  if(var4.g() >= 90) {
                     boolean var7 = false;
                     if(var4.g() >= 141) {
                        var7 = true;
                        if(this.aW && var3.Q) {
                           var6 = true;
                        }

                        var4.a(var6);
                     }

                     var4.a(var5);
                     var4.a("teams", var7);
                  } else {
                     var5 = 8;
                     if(!this.v) {
                        this.d("sendUpdatePlayer: warning saving with lower team count");
                     }
                  }

                  int var12 = 0;

                  while(true) {
                     if(var12 >= var5) {
                        if(var4.g() >= 90) {
                           var4.a("teams");
                        }

                        var4.a(this.ay.d);
                        var4.a(this.ay.c);
                        var4.a(this.ay.e);
                        var4.a(this.ay.f);
                        var4.c(5);
                        var4.a(this.aw);
                        var4.a(this.ax);
                        var4.a(this.ay.g);
                        var4.a(this.ay.h);
                        var4.a(this.ay.i);
                        var4.a(this.ay.j);
                        var4.a(false);
                        var4.a(this.ay.l);
                        var4.a(this.al);
                        break;
                     }

                     com.corrodinggames.rts.game.n var8 = com.corrodinggames.rts.game.n.k(var12);
                     var4.a(var8 != null);
                     if(var8 != null) {
                        byte var9 = 0;
                        if(var8 instanceof com.corrodinggames.rts.game.a.a) {
                           var9 = 1;
                        }

                        var4.a((int)var9);
                        if(var6) {
                           var8.c(var4);
                        } else {
                           var8.b(var4);
                        }
                     }

                     ++var12;
                  }
               } catch (IOException var10) {
                  throw new RuntimeException(var10);
               }

               short var11 = -1;
               if(var1 == var3 && var3.E <= 26) {
                  var11 = 1000;
               }

               var3.Q = true;
               this.a(var3, var4.a(115, var11));
            }
         }

      }
   }

   public strictfp void a(c var1, int var2, int var3) {
      as var4 = new as();

      try {
         var4.c(0);
         var4.a(var2);
         var4.a(var3);
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      }

      this.a(var1, var4.b(4));
   }

   public synchronized strictfp boolean R() {
      if(this.S()) {
         this.p = true;
         this.ay.d = 0;
         return true;
      } else {
         return false;
      }
   }

   public synchronized strictfp boolean S() {
      if(this.B) {
         this.b("Started singleplayer");
      }

      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.r();
      this.B = true;
      this.C = true;
      this.F = true;
      this.ay.a = var1.an();
      this.ay.b = var1.am();
      this.aa();
      this.z = var1.bs;
      com.corrodinggames.rts.appFramework.n.o();
      this.m = var1.bQ.networkPort;
      this.d("singleplayer server started");
      return true;
   }

   private strictfp void aA() {
      this.ay.q = com.corrodinggames.rts.gameFramework.f.a(1, 1000000000);
   }

   public synchronized strictfp boolean b(boolean var1) {
      if(this.B) {
         throw new RuntimeException("networking already started");
      } else {
         this.q();
         this.B = true;
         this.C = true;
         this.aa();
         this.aA();
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         this.c(var1);
         com.corrodinggames.rts.appFramework.n.o();
         this.m = var2.bQ.networkPort;
         com.corrodinggames.rts.gameFramework.o.a.a().i();
         this.aE = new ao(this);

         try {
            this.aE.a(false);
         } catch (IOException var6) {
            var6.printStackTrace();
            var2.a("Could not open tcp port:" + this.m + ", check this port is not in use or change the port in the game settings", 1);
            this.b("Could not open tcp port");
            return false;
         }

         this.aD = new Thread(this.aE);
         this.aD.setDaemon(true);
         this.aD.start();
         boolean var3 = true;
         if(var3) {
            this.aG = new ao(this);

            try {
               this.aG.a(true);
            } catch (IOException var5) {
               var5.printStackTrace();
               var2.a("Could not open udp port:" + this.m + ", check this port is not in use or change the port in the game settings", 1);
               this.b("Could not open udp port");
               return false;
            }

            this.aF = new Thread(this.aG);
            this.aF.start();
         }

         this.am();
         if(this.q) {
            n.b();
         }

         this.aV = null;
         if(r) {
            n.a();
         }

         this.d("server started");
         return true;
      }
   }

   public strictfp void c(boolean var1) {
      this.C = true;
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.z == null) {
         com.corrodinggames.rts.game.e var4 = null;
         int var3;
         if(!var1) {
            var3 = com.corrodinggames.rts.game.n.G();
            if(var3 == -1) {
               throw new RuntimeException("playerId is -1 for server player");
            }
         } else {
            var4 = this.bk;
            var3 = this.bk.k;
         }

         if(var4 == null) {
            var4 = new com.corrodinggames.rts.game.e(var3);
            var4.v = this.y;
            var2.bs = var4;
         }

         this.z = var4;
      }

      if(this.aI == null) {
         com.corrodinggames.rts.gameFramework.l.e("pingerTask starting");
         this.aI = new av(this);
         this.aH = new Timer();
         this.aH.schedule(this.aI, 100L, 100L);
      } else {
         com.corrodinggames.rts.gameFramework.l.e("pingerTask already active");
      }

      com.corrodinggames.rts.appFramework.n.o();
   }

   public strictfp boolean T() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return var1.bQ.udpInMultiplayer;
   }

   public strictfp an a(String var1, boolean var2, Runnable var3) {
      an var4 = new an(var1, var2, var3);
      var4.b();
      return var4;
   }

   public static strictfp Socket b(String var0, boolean var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.e("Connect to server: " + var0 + " (force tcp:" + var1 + ")");
      boolean var3 = false;
      String var4 = var0.trim();
      String var6;
      String var7;
      String var12;
      int var31;
      if(var4.startsWith("get|")) {
         String[] var30 = var4.split("\\|");

         boolean var33;
         try {
            var6 = var30[0];
            var7 = var30[1];
            var31 = Integer.parseInt(var30[2]);
            var33 = Boolean.parseBoolean(var30[3]);
            int var35 = Integer.parseInt(var30[4]);
         } catch (NumberFormatException var21) {
            var21.printStackTrace();
            var12 = "Bad server connect string";
            throw new IOException(var12);
         }

         if(var33) {
            var2.bX.n = null;
            Object var11 = new Object();
            ad$1 var37 = new ad$1(var11);
            com.corrodinggames.rts.gameFramework.l.e("Asking for password..");
            synchronized(var11) {
               a((ae)var37);

               try {
                  var11.wait();
               } catch (InterruptedException var19) {
                  var19.printStackTrace();
               }
            }

            if(var2.bX.n == null) {
               com.corrodinggames.rts.gameFramework.l.b("No password entered");
               throw new ag();
            }

            com.corrodinggames.rts.gameFramework.l.e("Password has been entered");
         }

         String var36 = null;
         if(var33) {
            var36 = var2.bX.n;
            if(var36 == null) {
               throw new IOException("This server requires a password but no password was provided");
            }
         }

         Object var38 = new Object();
         ad$2 var13 = new ad$2(var38);
         synchronized(var38) {
            n.a(var13, var7, var31, var36);

            try {
               var38.wait(15000L);
            } catch (InterruptedException var17) {
               ;
            }
         }

         if(var13.b != null) {
            throw new IOException(var13.b);
         } else if(var13.a == null) {
            throw new IOException("Failed to get game server info.");
         } else {
            Socket var14 = b(var13.a, var1);
            return var14;
         }
      } else {
         if(var4.toLowerCase(Locale.ENGLISH).endsWith(".relay")) {
            var4 = var4 + ".corrodinggames.com";
         }

         if(var4.startsWith("[TCP]")) {
            var4 = var4.substring("[TCP]".length());
            var1 = true;
         }

         String var5;
         if(var4.length() > 4 && !var4.contains(":") && !var4.contains(".") && !var4.equals("localhost") && !var4.contains("/") && !var4.contains("\\")) {
            var5 = ".relay.corrodinggames.com";
            var6 = "" + var4.charAt(0);
            var7 = var6 + var5 + "/" + var4;
            com.corrodinggames.rts.gameFramework.l.e("Converting connect string to: " + var7);
            var4 = var7;
         }

         var2.bX.L = null;
         String var8;
         int var27;
         if(var4.contains("/") || var4.contains("\\")) {
            int var26 = var4.indexOf("/");
            var27 = var4.indexOf("\\");
            if(var26 == -1) {
               var26 = var4.length();
            }

            if(var27 == -1) {
               var27 = var4.length();
            }

            int var28 = com.corrodinggames.rts.gameFramework.f.c(var26, var27);
            var8 = var4.substring(var28 + 1);
            var8 = var8.trim();
            if(!var8.equals("")) {
               var2.bX.L = var8;
            }

            var4 = var4.substring(0, var28);
         }

         var5 = var4;
         var27 = 5123;
         String[] var29 = var4.split(":");
         if(var29.length > 1) {
            var5 = null;

            for(var31 = 0; var31 < var29.length - 1; ++var31) {
               if(var5 == null) {
                  var5 = "";
               } else {
                  var5 = var5 + ":";
               }

               var5 = var5 + var29[var31];
            }

            var8 = var29[var29.length - 1];

            try {
               var27 = Integer.parseInt(var8);
            } catch (NumberFormatException var23) {
               String var10 = "Bad port number:" + var8;
               var23.printStackTrace();
               throw new IOException(var10);
            }
         }

         if(!var1 && var2.bX.T()) {
            var3 = true;
         }

         short var9 = 7000;
         com.corrodinggames.rts.gameFramework.l.e("");
         com.corrodinggames.rts.gameFramework.l.e("===============================");
         com.corrodinggames.rts.gameFramework.l.e("Connect to: " + var4);
         Object var32;
         if(!var3) {
            var32 = new Socket();
            com.corrodinggames.rts.gameFramework.l.e("connecting to Server.. (tcp)");
         } else {
            var32 = new a.a.h();
            com.corrodinggames.rts.gameFramework.l.e("connecting to Server.. (udp)");
            var9 = 5000;
         }

         ((Socket)var32).setTcpNoDelay(true);

         InetSocketAddress var34;
         try {
            var34 = new InetSocketAddress(InetAddress.getByName(var5), var27);
         } catch (IllegalArgumentException var22) {
            var12 = "Incorrect server format";
            com.corrodinggames.rts.gameFramework.l.b("IllegalArgumentException.." + var12);
            var22.printStackTrace();
            throw new IOException(var12, var22);
         }

         try {
            ((Socket)var32).connect(var34, var9);
            return (Socket)var32;
         } catch (UnknownHostException var24) {
            var12 = "Failed to connect to host";
            if(var3) {
               var12 = var12 + " (udp)";
            }

            com.corrodinggames.rts.gameFramework.l.e("UnknownHostException.." + var12);
            var24.printStackTrace();
            throw new IOException(var12, var24);
         } catch (IOException var25) {
            var12 = "Failed to connect to host";
            if(var3) {
               var12 = var12 + " (udp)";
            }

            var12 = var12 + " - " + var25.getMessage();
            com.corrodinggames.rts.gameFramework.l.e("IOException.." + var12);
            var25.printStackTrace();
            throw new IOException(var12, var25);
         }
      }
   }

   public strictfp void U() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.f.a.f var2 = com.corrodinggames.rts.gameFramework.f.a.f.a(com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerReconnect.message", new Object[0]), false);
      var2.a(com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.resume", new Object[0]), new ad$3(this, var2));
      var2.a(com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.reconnect", new Object[0]), new ad$4(this, var2));
      var2.a(com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.disconnect", new Object[0]), new ad$5(this, var2, var1));
      var1.bS.a(var2);
      this.bx = true;
   }

   public synchronized strictfp boolean V() {
      Socket var1 = this.bv;
      if(var1 == null) {
         com.corrodinggames.rts.gameFramework.l.e("reconnectToServer: lastConnectedTo==null");
         return false;
      } else {
         com.corrodinggames.rts.gameFramework.l.e("reconnectToServer attempted");
         if(this.B) {
            com.corrodinggames.rts.gameFramework.l.e("reconnectToServer: disconnecting");
            this.b("reconnecting");
         }

         if(var1.getInetAddress() == null) {
            com.corrodinggames.rts.gameFramework.l.e("reconnectToServer: lastConnectedTo.getInetAddress()==null");
            return false;
         } else {
            String var2 = var1.getInetAddress().getHostAddress();
            int var3 = var1.getPort();
            String var4 = var2 + ":" + var3;
            com.corrodinggames.rts.gameFramework.l.e("reconnectToServer: connecting to: " + var4);

            try {
               boolean var6 = false;
               Socket var5 = b(var4, var6);
               boolean var7 = this.a(var5);
               return var7;
            } catch (IOException var8) {
               var8.printStackTrace();
               return false;
            } catch (ag var9) {
               var9.printStackTrace();
               return false;
            }
         }
      }
   }

   public synchronized strictfp boolean a(Socket var1) {
      if(this.B) {
         this.b("starting new");
      }

      if(var1 == null) {
         throw new RuntimeException("connectedSocket==null");
      } else {
         this.q();
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         this.m = var1.getPort();
         this.B = true;
         this.C = false;
         this.d("connected to Server..");
         c var3 = new c(this, var1);
         var3.p = true;
         var3.d();
         this.aM.add(var3);
         this.f(var3);
         this.am();
         this.bv = var1;
         return true;
      }
   }

   public strictfp c c(com.corrodinggames.rts.game.n var1) {
      Iterator var2 = this.aM.iterator();

      c var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (c)var2.next();
      } while(var3.z != var1);

      return var3;
   }

   public strictfp c d(com.corrodinggames.rts.game.n var1) {
      Iterator var2 = this.aM.iterator();

      c var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (c)var2.next();
      } while(var3.a || var3.z != var1);

      return var3;
   }

   public strictfp c W() {
      if(this.C) {
         return null;
      } else {
         Iterator var1 = this.aM.iterator();

         c var2;
         do {
            if(!var1.hasNext()) {
               return null;
            }

            var2 = (c)var1.next();
         } while(var2.a);

         return var2;
      }
   }

   public strictfp void d(au var1) {
      if(!this.B) {
         com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketToAll, not networked");
      } else {
         this.i(var1);
      }
   }

   private strictfp void i(au var1) {
      Iterator var2 = this.aM.iterator();

      while(var2.hasNext()) {
         c var3 = (c)var2.next();
         if(var3.p && !var3.a && !var3.s) {
            var3.a(var1);
         }
      }

   }

   public strictfp void e(au var1) {
      if(!this.B) {
         com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketToAllIncludingRelay, not networked");
      } else {
         Iterator var2 = this.aM.iterator();

         while(var2.hasNext()) {
            c var3 = (c)var2.next();
            if(var3.p && !var3.a) {
               var3.a(var1);
            }
         }

      }
   }

   public strictfp void f(au var1) {
      if(!this.B) {
         com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketToServer, not networked");
      } else if(this.C) {
         throw new RuntimeException("We are a server");
      } else {
         this.d(var1);
      }
   }

   public strictfp void g(au var1) {
      if(!this.B) {
         com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketToClients, not networked");
      } else if(!this.C) {
         throw new RuntimeException("We are not a server");
      } else {
         this.e(var1);
      }
   }

   public strictfp void h(au var1) {
      if(!this.B) {
         com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketToClients, not networked");
      } else if(!this.C) {
         throw new RuntimeException("We are not a server");
      } else {
         this.d(var1);
      }
   }

   public strictfp void a(c var1, au var2) {
      if(!this.B) {
         com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketOnConnection, not networked");
      } else {
         var1.a(var2);
      }
   }

   public strictfp void X() {
      if(this.C) {
         this.d("registerConnection: We are a server");
      }

      Iterator var1 = this.aM.iterator();

      while(var1.hasNext()) {
         c var2 = (c)var1.next();
         this.h(var2);
      }

   }

   public strictfp void Y() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bQ.networkClientId = null;
      if(this.S == null) {
         com.corrodinggames.rts.gameFramework.l.e("generateNewClientId: serverUUID==null");
         this.S = "x";
      }

      this.Z();
      var1.bQ.save();
   }

   public strictfp String Z() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var2 = false;
      if(var1.bQ.networkClientId == null) {
         var2 = true;
      }

      String var3;
      if(!this.by) {
         this.by = true;
         if(com.corrodinggames.rts.gameFramework.l.av()) {
            var3 = this.ak();
            if(!var3.equals(var1.bQ.networkClientIdMachineKey)) {
               if(var1.bQ.networkClientIdMachineKey != null) {
                  com.corrodinggames.rts.gameFramework.l.e("Machine appears to have changed: " + var1.bQ.networkClientIdMachineKey + " vs " + var3);
               }

               var1.bQ.networkClientIdMachineKey = var3;
               var2 = true;
            }
         }
      }

      if(var2) {
         com.corrodinggames.rts.gameFramework.l.e("new networkClientId needed");
         var1.bQ.networkClientId = UUID.randomUUID().toString();
         var1.bQ.save();
      }

      var3 = var1.bQ.networkClientId;
      if(this.S == null) {
         throw new RuntimeException("getOwnClientIdHashed: serverUUID==null");
      } else {
         String var4 = com.corrodinggames.rts.gameFramework.f.e(var3 + this.S);
         return var4;
      }
   }

   public strictfp void aa() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bQ.networkServerId = UUID.randomUUID().toString();
      var1.bQ.save();
   }

   public strictfp String ab() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1.bQ.networkServerId == null) {
         this.aa();
      }

      return var1.bQ.networkServerId;
   }

   public strictfp String ac() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return this.C?var1.bQ.networkServerId:this.S;
   }

   public strictfp void f(c var1) {
      as var2 = new as();

      try {
         byte var3 = 4;
         byte var4 = 1;
         if(com.corrodinggames.rts.gameFramework.l.av()) {
            var4 = 2;
         }

         if(com.corrodinggames.rts.gameFramework.l.aZ) {
            var4 = 3;
         }

         var2.c("com.corrodinggames.rts");
         var2.a((int)var3);
         var2.a(this.e);
         var2.a((int)var4);
         var2.b(this.L);
         var2.c(this.y);
         var2.c(com.corrodinggames.rts.gameFramework.h.a.c());
         String var5 = "";
         if(com.corrodinggames.rts.gameFramework.l.aT) {
            var5 = var5 + "d";
         }

         var2.c(var5);
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      }

      this.a(var1, var2.b(160));
   }

   public strictfp void g(c var1) {
      as var2 = new as();

      try {
         com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
         var2.c("com.corrodinggames.rts");
         var2.a((int)2);
         var2.a(this.e);
         var2.a(var3.c(true));
         var2.c(var3.l());
         var2.c(this.ab());
         var2.a(var1.M);
         var2.a(this.W);
         var2.a((int)0);
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }

      this.a(var1, var2.b(161));
   }

   public strictfp void h(c var1) {
      com.corrodinggames.rts.gameFramework.l.e("sendRegisterConnection...");
      as var2 = new as();

      try {
         var2.c("com.corrodinggames.rts");
         var2.a((int)5);
         var2.a(this.e);
         com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
         var2.a(var3.c(true));
         var2.c(this.y);
         String var4 = null;
         if(this.n != null) {
            var4 = com.corrodinggames.rts.gameFramework.f.e(this.n);
         }

         var2.b(var4);
         var2.c(var3.l());
         var2.c(this.Z());
         var2.a(var3.z());
         var2.c(this.g(this.T));
         var2.c(this.h(this.U));
      } catch (IOException var5) {
         throw new RuntimeException(var5);
      }

      this.a(var1, var2.b(110));
      this.bz = true;
   }

   public strictfp String g(int var1) {
      String var2 = "";
      var2 = var2 + "c:" + var1;
      var2 = var2 + "m:" + (var1 * 87 + 24);
      var2 = var2 + "0:" + this.e(0) * 11 * var1;
      var2 = var2 + "1:" + (this.e(1) * 12 + var1);
      var2 = var2 + "2:" + this.e(2) * 13 * var1;
      var2 = var2 + "3:" + (this.e(3) * 14 + var1);
      var2 = var2 + "4:" + this.e(4) * 15 * var1;
      var2 = var2 + "5:" + (this.e(5) * 16 + var1);
      var2 = var2 + "6:" + this.e(6) * 17 * var1;
      var2 = var2 + "7:" + this.e(7) * 18 * var1;
      var2 = var2 + "8:" + this.e(8) * 19 * var1;
      var2 = var2 + "t1:" + com.corrodinggames.rts.game.n.j.o * 11.0D * (double)var1;
      int var3 = 5 * var1;
      if(this.k() != this.e(this.ay.c)) {
         var3 = 7 * var1;
      }

      var2 = var2 + "d:" + var3;
      return var2;
   }

   public strictfp String h(int var1) {
      return com.corrodinggames.rts.gameFramework.f.h(var1);
   }

   public strictfp void ad() {
      if(this.C) {
         throw new RuntimeException("We are a server");
      } else {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         as var2 = new as();

         try {
            var2.a(this.bG);
            var2.a(var1.bq);
         } catch (IOException var4) {
            throw new RuntimeException(var4);
         }

         this.f(var2.b(112));
      }
   }

   public strictfp void j(String var1) {
      if(!this.C) {
         this.d("cannot send sendSystemMessage:" + var1 + ", we are not a server");
      } else if(this.B && !this.F) {
         com.corrodinggames.rts.gameFramework.l.e("sendSystemMessage:" + var1);
         this.a((c)null, (com.corrodinggames.rts.game.n)null, (String)null, var1);
      } else {
         this.d("cannot send sendSystemMessage:" + var1 + ", not networked");
      }
   }

   public strictfp void k(String var1) {
      this.m("-qc " + var1);
   }

   public strictfp void l(String var1) {
      boolean var2 = true;
      String var3 = null;
      if(var1 != null) {
         String var4 = var1.trim();
         if((var4.startsWith("-") || var4.startsWith(".") || var4.startsWith("_")) && var4.length() >= 2) {
            String var5 = var4.substring(1).trim();
            int var6 = var5.indexOf(" ");
            if(var6 == -1) {
               var6 = var5.length();
            }

            var3 = var5.substring(0, var6).toLowerCase(Locale.ENGLISH);
         }
      }

      if("share".equals(var3)) {
         var2 = false;
      }

      if("t".equals(var3)) {
         var2 = false;
      }

      if(var2) {
         var1 = "-t " + var1;
      }

      this.m(var1);
   }

   public strictfp void m(String var1) {
      if(!this.B) {
         com.corrodinggames.rts.gameFramework.l.e("sendChatMessage: not networked:" + var1);
         this.b((c)null, -1, (String)null, var1);
      } else if(this.C) {
         this.a((c)null, this.z, this.y, var1);
         this.b((c)null, this.z, this.y, var1);
      } else {
         try {
            as var2 = new as();
            var2.c(var1);
            var2.c(0);
            this.f(var2.b(140));
         } catch (IOException var3) {
            throw new RuntimeException(var3);
         }
      }
   }

   public strictfp void a(c var1, com.corrodinggames.rts.game.n var2, String var3, String var4) {
      this.a(var1, var2, var3, var4, (c)null);
   }

   public strictfp void a(c var1, com.corrodinggames.rts.game.n var2, String var3, String var4, c var5) {
      try {
         boolean var6 = false;
         boolean var7 = false;
         String var8 = n(var4);
         if("t".equalsIgnoreCase(var8)) {
            if(var2 != null) {
               var6 = true;
               var4 = var4.substring("-t".length());
               var4 = "[TEAM] " + var4;
            } else {
               com.corrodinggames.rts.gameFramework.l.b("toOnlyTeams failed team==null");
            }
         }

         if(var2 != null && "surrender".equalsIgnoreCase(var8)) {
            var6 = true;
            var4 = "[TEAM] " + var4;
         }

         if(var2 != null && "i".equalsIgnoreCase(var8)) {
            var7 = true;
            var4 = var4.substring("-i".length());
            var4 = "[INFO] " + var4;
         }

         if(var2 != null && "qc".equalsIgnoreCase(var8)) {
            var7 = true;
            var4 = var4.substring("-qc".length());
            var4 = "[COMMAND] " + var4;
         }

         if(!var7 && var2 != null && var2 != this.bj && var2 != this.bk && !this.d.a(var1, var2, var4, var6)) {
            var7 = true;
         }

         as var9 = new as();
         var9.c(var4);
         var9.c(3);
         var9.b(var3);
         var9.a(var1);
         int var10 = -1;
         if(var2 != null) {
            var10 = var2.k;
         }

         var9.a(var10);
         au var11 = var9.b(141);
         if(var6) {
            Iterator var12 = this.aM.iterator();

            while(var12.hasNext()) {
               c var13 = (c)var12.next();
               if(var13.p && !var13.a) {
                  com.corrodinggames.rts.game.e var14 = var13.z;
                  if(var14 != null && var14.d(var2)) {
                     var13.a(var11);
                  }
               }
            }

            com.corrodinggames.rts.game.n var16 = this.z;
            if(var16 != null && var16.d(var2)) {
               this.b(var1, var10, var3, var4);
            }
         } else if(var7) {
            com.corrodinggames.rts.gameFramework.l.b("info message:" + c(var3, var4));
         } else {
            if(var5 != null) {
               this.a(var5, var11);
            } else {
               this.g(var11);
            }

            this.b(var1, var10, var3, var4);
         }

      } catch (IOException var15) {
         throw new RuntimeException(var15);
      }
   }

   public static strictfp String n(String var0) {
      if(var0 == null) {
         return null;
      } else {
         String var1 = var0.trim();
         if((var1.startsWith("-") || var1.startsWith(".") || var1.startsWith("_")) && var1.length() >= 2) {
            String var2 = var1.substring(1).trim();
            int var3 = var2.indexOf(" ");
            if(var3 == -1) {
               var3 = var2.length();
            }

            return var2.substring(0, var3).toLowerCase(Locale.ENGLISH);
         } else {
            return null;
         }
      }
   }

   public static strictfp String c(String var0, String var1) {
      return var0 != null?var0 + ": " + var1:var1;
   }

   public strictfp void o(String var1) {
      var1 = com.corrodinggames.rts.gameFramework.h.a.c(var1);
      byte var2 = -1;
      Object var3 = null;
      Object var4 = null;
      this.aC.a(var2, (String)var3, var1, (c)var4);
      this.d.a(var2, (String)var3, var1, (c)var4);
      boolean var5 = false;
      if(this.aW) {
         var5 = true;
      }

      if(!this.B) {
         var5 = true;
      }

      if(var5) {
         a((String)var3, var1);
      } else {
         String var6 = c((String)var3, var1);
         if(!com.corrodinggames.rts.gameFramework.l.aU) {
            com.corrodinggames.rts.appFramework.n.d(var6);
         }
      }

   }

   private strictfp void b(c var1, int var2, String var3, String var4) {
      if(this.B || !var4.startsWith("-i ")) {
         if(this.B || !var4.startsWith("-qc ")) {
            var4 = com.corrodinggames.rts.gameFramework.h.a.c(var4);
            if(var3 != null) {
               boolean var5 = true;
               if(var4 != null) {
                  if(var4.equals("-surrender")) {
                     ;
                  }

                  if(this.z != null && var2 >= 0 && this.z.k == var2) {
                     ;
                  }
               }

               if(var5) {
                  this.d("New Message", var3 + ": " + var4);
               }
            }

            c var8 = null;
            if(this.C) {
               var8 = var1;
            }

            this.aC.a(var2, var3, var4, var8);
            this.d.a(var2, var3, var4, var1);
            boolean var6 = false;
            if(this.aW) {
               var6 = true;
            }

            if(!this.B) {
               var6 = true;
            }

            if(var6) {
               a(var3, var4);
            } else {
               String var7 = c(var3, var4);
               if(!com.corrodinggames.rts.gameFramework.l.aU) {
                  com.corrodinggames.rts.appFramework.n.d(var7);
               }
            }

         }
      }
   }

   public strictfp void a(c var1, byte[] var2, boolean var3, boolean var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();

      try {
         as var6 = new as();
         var6.c(0);
         var6.a(var5.bx);
         var6.a(var5.by);
         var6.a(this.c());
         var6.a(1.0F);
         var6.a(var3);
         var6.a(var4);
         var6.e("gameSave");
         var6.b(var2);
         var6.a("gameSave");
         au var7 = var6.b(35);
         this.a(var1, var7);
      } catch (IOException var8) {
         throw new RuntimeException(var8);
      }
   }

   public strictfp void a(boolean var1, boolean var2, boolean var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();

      try {
         as var5 = new as();
         var5.c(0);
         var5.a(var4.bx);
         var5.a(var4.by);
         var5.a(this.c());
         var5.a(1.0F);
         var5.a(var1);
         var5.a(var2);
         var5.e("gameSave");
         var4.ca.a(var5);
         var5.a("gameSave");
         if(var1) {
            ;
         }

         au var6 = var5.b(35);
         this.d(var6);
         if(var3) {
            if(!this.C) {
               throw new RuntimeException("sendResyncSave: reloadCreatedSave: We are not a server");
            }

            var6.a = this.aL;
            this.a(var6);
         }

      } catch (IOException var7) {
         throw new RuntimeException(var7);
      }
   }

   public strictfp boolean ae() {
      this.Q();
      this.L();
      return this.a((c)null, false);
   }

   public strictfp boolean a(c var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l.e("Sending start game....");
      if(!this.C) {
         throw new RuntimeException("We are not a server");
      } else {
         com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
         as var4 = new as();

         try {
            var4.c(0);
            var4.a((Enum)this.ay.a);
            if(this.ay.a == ai.c) {
               try {
                  var3.ca.a(this.ay.b, var4);
               } catch (IOException var7) {
                  var7.printStackTrace();
                  var3.c("Map error starting game", "Map error: " + var7.getMessage());
                  return false;
               }

               var4.c("SAVE:" + this.ay.b);
            } else if(this.ay.a == ai.b) {
               com.corrodinggames.rts.gameFramework.l.e("Starting with custom map: " + this.l());

               try {
                  com.corrodinggames.rts.game.b.b.a(this.az, var4);
               } catch (IOException var6) {
                  var6.printStackTrace();
                  var3.c("Map error starting game", "Map error: " + var6.getMessage());
                  return false;
               }

               var4.c("STEAM:" + this.l());
            } else {
               var4.c(this.l());
            }

            var4.a(var2);
         } catch (IOException var8) {
            throw new RuntimeException(var8);
         }

         au var5 = var4.b(120);
         if(var1 == null) {
            this.g(var5);
         } else {
            this.a(var1, var5);
         }

         if(!this.aW) {
            this.aB();
         }

         return true;
      }
   }

   public strictfp void af() {
      this.bc = true;
      com.corrodinggames.rts.gameFramework.l.e("onStartGameFailed");
      if(this.C) {
         this.aW = false;
         this.j("Map load failed.");
      } else {
         this.b("Map load failed");
      }

   }

   private strictfp void aB() {
      this.aY = false;
      this.aW = true;
      this.bc = false;
      this.bd = false;
      com.corrodinggames.rts.gameFramework.l.e("Starting new network game (" + this.ac() + ")");
      if(this.q && this.C) {
         n.c();
      }

      if(!com.corrodinggames.rts.gameFramework.l.aU) {
         com.corrodinggames.rts.appFramework.n.p();
      }

      this.d.b();
   }

   public strictfp void ag() {
      this.d(5.0F);
   }

   public strictfp void d(float var1) {
      if(!this.C) {
         throw new RuntimeException("We are not a server");
      } else if(!this.aZ) {
         com.corrodinggames.rts.gameFramework.l.e("Setting up return to battleroom timer...");
         this.ba = var1;
         this.aZ = true;
         this.j("Game ended by host. Returning to battleroom in " + (int)var1 + " seconds...");
      }
   }

   public strictfp void i(c var1) {
      if(!this.C) {
         throw new RuntimeException("We are not a server");
      } else {
         try {
            as var2 = new as();
            var2.c(0);
            au var3 = var2.b(122);
            if(var1 == null) {
               this.h(var3);
            } else {
               this.a(var1, var3);
            }
         } catch (IOException var4) {
            throw new RuntimeException(var4);
         }

         this.aC();
      }
   }

   private strictfp void aC() {
      this.aY = true;
   }

   private strictfp void aD() {
      com.corrodinggames.rts.gameFramework.l.e("----- returnToBattleroom -----");
      this.aY = false;
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.cb.e();
      com.corrodinggames.rts.game.n var2 = this.z;
      var1.g();
      this.s();
      this.z = var2;
      var1.bx = 0;
      var1.by = 0;
      this.A();
      com.corrodinggames.rts.game.n.n();
      if(this.C) {
         this.aA();
      }

      this.J();
      if(this.q && this.C) {
         n.c();
      }

      if(!com.corrodinggames.rts.gameFramework.l.aU) {
         ;
      }

   }

   public strictfp String ah() {
      ArrayList var1 = this.aj();
      return var1 != null && var1.size() != 0?(String)var1.get(0):null;
   }

   public strictfp String ai() {
      ArrayList var1 = this.aj();
      if(var1 != null && var1.size() != 0) {
         String var2 = "";
         boolean var3 = true;

         String var5;
         for(Iterator var4 = var1.iterator(); var4.hasNext(); var2 = var2 + var5) {
            var5 = (String)var4.next();
            if(var3) {
               var3 = false;
            } else {
               var2 = var2 + ", ";
            }
         }

         return var2;
      } else {
         return null;
      }
   }

   public strictfp ArrayList aj() {
      if(bA != null) {
         return new ArrayList(bA);
      } else {
         long var1 = br.a();
         ArrayList var3 = null;
         ArrayList var4 = this.d(true);
         if(var4 != null && var4.size() > 0) {
            var3 = var4;
         } else {
            var3 = this.d(false);
         }

         double var5 = (double)br.a(var1);
         if(var5 > 2.0D) {
            com.corrodinggames.rts.gameFramework.l.b("getLocalIpAddressList was slow, taking:" + br.a(var5));
         }

         if(var5 > 10.0D && var3 != null && var3.size() > 0) {
            com.corrodinggames.rts.gameFramework.l.e("getLocalIpAddressList: creating cache");
            bA = new ArrayList(var3);
         }

         return var3;
      }
   }

   public strictfp String ak() {
      String var1 = null;

      try {
         Enumeration var2 = NetworkInterface.getNetworkInterfaces();

         while(var2.hasMoreElements()) {
            NetworkInterface var3 = (NetworkInterface)var2.nextElement();
            byte[] var4 = var3.getHardwareAddress();
            if(var4 != null) {
               String var5 = new String(var4);
               var5 = var5.trim();
               if(var5.length() > 2) {
                  var1 = var5;
                  break;
               }
            }
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      return var1 != null?com.corrodinggames.rts.gameFramework.f.c(var1):"[blank]";
   }

   public strictfp ArrayList d(boolean var1) {
      ArrayList var2 = new ArrayList();

      try {
         Enumeration var3 = NetworkInterface.getNetworkInterfaces();

         while(var3.hasMoreElements()) {
            NetworkInterface var4 = (NetworkInterface)var3.nextElement();
            Enumeration var5 = var4.getInetAddresses();

            while(var5.hasMoreElements()) {
               InetAddress var6 = (InetAddress)var5.nextElement();
               if(!var6.isLoopbackAddress()) {
                  String var7 = var6.getHostAddress().toString();
                  if(!var7.contains("%")) {
                     if(!var1) {
                        var2.add(var7);
                     } else if(var7.contains(".")) {
                        var2.add(var7);
                     }
                  }
               }
            }
         }
      } catch (SocketException var8) {
         Log.d("RustedWarfare", var8.toString());
      }

      return var2;
   }

   strictfp InetAddress al() {
      try {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         WifiManager var2 = (WifiManager)var1.am.c("wifi");
         DhcpInfo var3 = var2.getDhcpInfo();
         int var4 = var3.ipAddress & var3.netmask | ~var3.netmask;
         byte[] var5 = new byte[4];

         for(int var6 = 0; var6 < 4; ++var6) {
            var5[var6] = (byte)(var4 >> var6 * 8 & 255);
         }

         return InetAddress.getByAddress(var5);
      } catch (UnknownHostException var7) {
         var7.printStackTrace();
         return null;
      }
   }

   public strictfp void d(String var1, String var2) {
      if(!com.corrodinggames.rts.gameFramework.l.aU) {
         com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
         if(!this.F && !var3.cb.j()) {
            boolean var4 = com.corrodinggames.rts.appFramework.n.l();
            com.corrodinggames.rts.appFramework.f var5 = var3.ao;
            if(var5 != null && !var5.e()) {
               var4 = true;
            }

            if(var4) {
               if(this.bB) {
                  this.i(2);
               }

            } else {
               NotificationManager var6 = (NotificationManager)var3.am.c("notification");
               Intent var7 = new Intent(var3.am, com.corrodinggames.rts.appFramework.a.class);
               PendingIntent var8 = PendingIntent.getActivity(var3.am, 0, var7, 2);
               if(VERSION.SDK_INT >= 11) {
                  Builder var9 = new Builder(var3.am);
                  var9.setContentTitle("Rusted Warfare Multiplayer");
                  var9.setContentText(var1 + ": " + var2);
                  var9.setSmallIcon(R$drawable.icon);
                  var9.setContentIntent(var8);
                  var9.setOngoing(false);
                  var9.setAutoCancel(true);
                  this.a(var6);
                  this.a(var9, "multiplayerChatId");
                  Notification var10 = var9.getNotification();
                  var6.notify(2, var10);
                  this.bB = true;
               }

            }
         }
      }
   }

   public strictfp void am() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.B && var1 != null && var1.M()) {
         this.aE();
      } else {
         this.i(1);
         this.i(2);
      }

   }

   private strictfp void a(Builder var1, String var2) {
      if(VERSION.SDK_INT >= 26) {
         try {
            Method var3 = var1.getClass().getDeclaredMethod("setChannelId", new Class[]{String.class});
            var3.invoke(var1, new Object[]{var2});
         } catch (Exception var4) {
            com.corrodinggames.rts.gameFramework.l.a("setChannelId failed", (Throwable)var4);
         }
      }

   }

   private strictfp void a(NotificationManager var1) {
      this.a(var1, "multiplayerChatId", "Multiplayer Chat");
      this.a(var1, "multiplayerStatusId", "Multiplayer Status");
   }

   private strictfp void a(NotificationManager var1, String var2, String var3) {
      if(VERSION.SDK_INT >= 26) {
         byte var4 = 3;

         try {
            Class var5 = Class.forName("android.app.NotificationChannel");
            Constructor var6 = var5.getDeclaredConstructor(new Class[]{String.class, CharSequence.class, Integer.TYPE});
            Object var7 = var6.newInstance(new Object[]{var2, var3, Integer.valueOf(var4)});
            Method var8 = var1.getClass().getDeclaredMethod("createNotificationChannel", new Class[]{var5});
            var8.invoke(var1, new Object[]{var7});
         } catch (Exception var9) {
            com.corrodinggames.rts.gameFramework.l.a("Creating notification channel failed", (Throwable)var9);
         }
      }

   }

   private strictfp void aE() {
      if(!com.corrodinggames.rts.gameFramework.l.aU) {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         Intent var2 = new Intent(var1.am, com.corrodinggames.rts.appFramework.a.class);
         PendingIntent var3 = PendingIntent.getActivity(var1.am, 0, var2, 2);
         NotificationManager var4 = (NotificationManager)var1.am.c("notification");
         if(VERSION.SDK_INT >= 11) {
            if(VERSION.SDK_INT >= 26) {
               ;
            }

            Builder var5 = new Builder(var1.am);
            var5.setContentTitle("Rusted Warfare Multiplayer");
            var5.setContentText("A multiplayer game is in progress");
            var5.setSmallIcon(R$drawable.icon);
            var5.setContentIntent(var3);
            var5.setOngoing(true);
            this.a(var4);
            this.a(var5, "multiplayerStatusId");
            if(VERSION.SDK_INT >= 16) {
               var5.build();
            }

            Notification var6 = var5.getNotification();
            var4.notify(1, var6);
         }

      }
   }

   private strictfp void i(int var1) {
      if(!com.corrodinggames.rts.gameFramework.l.aU) {
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         NotificationManager var3 = (NotificationManager)var2.am.c("notification");
         var3.cancel(var1);
      }
   }

   public strictfp int an() {
      int var1 = 0;

      for(int var2 = 0; var2 < com.corrodinggames.rts.game.n.c; ++var2) {
         com.corrodinggames.rts.game.n var3 = com.corrodinggames.rts.game.n.k(var2);
         if(var3 != null && !var3.w) {
            ++var1;
         }
      }

      return var1;
   }

   public strictfp int ao() {
      int var1 = 0;

      for(int var2 = 0; var2 < com.corrodinggames.rts.game.n.c; ++var2) {
         com.corrodinggames.rts.game.n var3 = com.corrodinggames.rts.game.n.k(var2);
         if(var3 != null) {
            ++var1;
         }
      }

      return var1;
   }

   public strictfp void e(com.corrodinggames.rts.game.n var1) {
      if(this.C) {
         this.f(var1);
      } else if(this.H) {
         this.k("-kick " + (var1.k + 1));
      } else {
         com.corrodinggames.rts.gameFramework.l.b("kickTeamAndAttachedPlayer: but not server or proxy controller");
      }

   }

   public strictfp void f(com.corrodinggames.rts.game.n var1) {
      if(var1 instanceof com.corrodinggames.rts.game.a.a) {
         var1.I();
      } else {
         if(this.z == var1) {
            com.corrodinggames.rts.gameFramework.l.b("kickTeamAndAttachedPlayer", "Cannot kick self");
            return;
         }

         c var2 = this.c(var1);
         if(var2 == null) {
            g("Kick player: cannot find connection for team");
         } else {
            int var3 = com.corrodinggames.rts.gameFramework.l.B().bQ.banTimeInSecondsAfterKick;
            if(var3 > 0) {
               this.a(var2, "Temporarily banned due to recent kick", var3);
            }

            this.a(var2, "Kicked by host");
            var2.a("Kicked by host");
         }

         var1.I();
      }

      this.P();
      com.corrodinggames.rts.appFramework.n.o();
   }

   public strictfp void ap() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(!this.C) {
         com.corrodinggames.rts.gameFramework.l.b("addAIToGame", "We are not a server");
      } else {
         int var2 = com.corrodinggames.rts.game.n.G();
         if(var2 == -1) {
            var1.a("No free slots for AI", 1);
         }

         com.corrodinggames.rts.game.a.a var3 = new com.corrodinggames.rts.game.a.a(var2);
         var3.v = "AI";
         var3.r = var2 % 2;
         var3.x = this.ay.f;
         this.aq();
         var1.bX.d.a((com.corrodinggames.rts.game.n)var3);
         var1.bX.e((c)null);
         com.corrodinggames.rts.appFramework.n.o();
      }
   }

   public strictfp boolean aq() {
      if(!this.C && this.B) {
         com.corrodinggames.rts.gameFramework.l.b("updateNamesOfAI", "We are not a server");
         return false;
      } else {
         boolean var1 = false;

         for(int var2 = 0; var2 < com.corrodinggames.rts.game.n.c; ++var2) {
            com.corrodinggames.rts.game.n var3 = com.corrodinggames.rts.game.n.k(var2);
            if(var3 != null && this.b(var3)) {
               var1 = true;
            }
         }

         return var1;
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.n var1, int var2) {
      Object var3 = this.bC;
      synchronized(this.bC) {
         this.c(var1, var2);
      }
   }

   private strictfp void c(com.corrodinggames.rts.game.n var1, int var2) {
      if(var1.k != var2) {
         int var3 = var1.k;
         int var4 = var1.r;
         boolean var5 = false;
         if(var2 == -3) {
            var5 = true;
            var2 = com.corrodinggames.rts.game.n.H();
            if(var2 == -1) {
               e("No free spectator slots");
               return;
            }
         }

         com.corrodinggames.rts.game.n var6 = com.corrodinggames.rts.game.n.k(var2);
         var1.f(var2);
         var1.r = var4;
         if(var5) {
            var1.r = -3;
         }

         if(var6 != null) {
            int var7 = var6.r;
            var6.f(var3);
            if(var7 == -3) {
               var6.r = -3;
            } else {
               var6.r = var4;
            }
         }

         this.M();
         this.P();
      }

   }

   public strictfp void a(am var1) {
      Object var2 = this.bC;
      synchronized(this.bC) {
         this.b(var1);
      }
   }

   private synchronized strictfp void b(am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(!var2.bX.C) {
         com.corrodinggames.rts.gameFramework.l.e("Not server");
      } else {
         ArrayList var3;
         int var4;
         com.corrodinggames.rts.game.n var5;
         int var6;
         int var7;
         byte var11;
         int var12;
         if(var1 == am.a) {
            var3 = new ArrayList();

            for(var4 = 0; var4 < com.corrodinggames.rts.game.n.c; ++var4) {
               var5 = com.corrodinggames.rts.game.n.k(var4);
               if(var5 != null) {
                  var3.add(var5);
               }
            }

            Collections.shuffle(var3);
            var4 = var3.size() / 2;
            if(var3.size() % 2 != 0) {
               var4 += com.corrodinggames.rts.gameFramework.f.a(0, 1);
            }

            if(var4 >= var3.size()) {
               var4 = var3.size();
            }

            var11 = 0;
            var6 = 0;

            for(var7 = var11; var7 < var4; ++var7) {
               ((com.corrodinggames.rts.game.n)var3.get(var7)).f(var6);
               var6 += 2;
               ((com.corrodinggames.rts.game.n)var3.get(var7)).r = 0;
            }

            var12 = var11 + var4;
            var6 = 1;

            for(var7 = var12; var7 < var3.size(); ++var7) {
               ((com.corrodinggames.rts.game.n)var3.get(var7)).f(var6);
               var6 += 2;
               ((com.corrodinggames.rts.game.n)var3.get(var7)).r = 1;
            }
         } else if(var1 == am.b) {
            var3 = new ArrayList();

            for(var4 = 0; var4 < com.corrodinggames.rts.game.n.c; ++var4) {
               var5 = com.corrodinggames.rts.game.n.k(var4);
               if(var5 != null) {
                  var3.add(var5);
               }
            }

            Collections.shuffle(var3);
            var4 = var3.size() / 3;
            if(var4 >= var3.size()) {
               var4 = var3.size();
            }

            var11 = 0;
            var6 = 0;

            for(var7 = var11; var7 < var4; ++var7) {
               com.corrodinggames.rts.game.n var8 = (com.corrodinggames.rts.game.n)var3.get(var7);
               var8.f(var6);
               var8.r = 0;
               var6 += 3;
               var3.set(var7, (Object)null);
            }

            var12 = var11 + var4;
            var7 = var12 + var3.size() / 3;
            if(var7 >= var3.size()) {
               var7 = var3.size();
            }

            if(var12 >= var3.size()) {
               var12 = var3.size();
            }

            var6 = 1;

            com.corrodinggames.rts.game.n var9;
            int var13;
            for(var13 = var12; var13 < var7; ++var13) {
               var9 = (com.corrodinggames.rts.game.n)var3.get(var13);
               var9.f(var6);
               var9.r = 1;
               var6 += 3;
               var3.set(var13, (Object)null);
            }

            var12 += var4;
            if(var12 >= var3.size()) {
               var12 = var3.size();
            }

            var6 = 2;

            for(var13 = var12; var13 < var3.size(); ++var13) {
               var9 = (com.corrodinggames.rts.game.n)var3.get(var13);
               if(var6 >= com.corrodinggames.rts.game.n.c) {
                  var9.f(var6);
                  var9.r = 2;
                  var6 += 3;
                  var3.set(var13, (Object)null);
               }
            }

            for(var13 = 0; var13 < var3.size(); ++var13) {
               var9 = (com.corrodinggames.rts.game.n)var3.get(var13);
               if(var9 != null) {
                  for(int var10 = 0; var10 < com.corrodinggames.rts.game.n.c; ++var10) {
                     if(com.corrodinggames.rts.game.n.k(var10) == null) {
                        var9.f(var10);
                        var9.r = 2;
                        var3.set(var13, (Object)null);
                     }
                  }
               }
            }
         } else if(var1 == am.c) {
            var3 = new ArrayList();

            for(var4 = 0; var4 < com.corrodinggames.rts.game.n.c; ++var4) {
               var5 = com.corrodinggames.rts.game.n.k(var4);
               if(var5 != null) {
                  var3.add(var5);
               }
            }

            Collections.shuffle(var3);
            var4 = 0;

            for(var12 = 0; var12 < var3.size(); ++var12) {
               ((com.corrodinggames.rts.game.n)var3.get(var12)).f(var4);
               ((com.corrodinggames.rts.game.n)var3.get(var12)).r = var4++;
            }
         } else {
            if(var1 != am.d) {
               throw new RuntimeException("overrideTeamLayout: unhandled layout: " + var1);
            }

            var3 = new ArrayList();

            for(var4 = 0; var4 < com.corrodinggames.rts.game.n.c; ++var4) {
               var5 = com.corrodinggames.rts.game.n.k(var4);
               if(var5 != null) {
                  var3.add(var5);
               }
            }

            Collections.shuffle(var3);
            var4 = 0;

            for(var12 = 0; var12 < var3.size(); ++var12) {
               var6 = com.corrodinggames.rts.game.n.H();
               if(var6 != -1) {
                  ((com.corrodinggames.rts.game.n)var3.get(var12)).f(var6);
               }

               ((com.corrodinggames.rts.game.n)var3.get(var12)).r = -3;
               ++var4;
            }
         }

         this.M();
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.n var1, int var2, Integer var3) {
      String var4 = "";
      if(var3 != null) {
         var4 = " " + var3;
      }

      if(!this.H && this.z == var1) {
         this.k("-self_move " + (var2 + 1) + var4);
      } else {
         this.k("-move " + (var1.k + 1) + " " + (var2 + 1) + var4);
      }
   }

   public strictfp void b(com.corrodinggames.rts.game.n var1, int var2) {
      if(var2 != -1) {
         ++var2;
      }

      if(!this.H && this.z == var1) {
         this.k("-self_team " + var2);
      } else {
         this.k("-team " + (var1.k + 1) + " " + var2);
      }
   }

   public strictfp void g(com.corrodinggames.rts.game.n var1) {
      if(!var1.H) {
         var1.H = true;
         String var2 = var1.v;
         if(var2 == null) {
            var2 = "Player - " + (var1.k + 1) + "";
         }

         String var3 = var2 + " is victorious!";
         this.j(var3);
      }

   }

   public strictfp void h(com.corrodinggames.rts.game.n var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var3 = false;
      String var4 = var1.v;
      if(var4 == null) {
         var4 = "Player - " + (var1.k + 1) + "";
      }

      String var5 = var4 + " was defeated";
      if(!this.bb) {
         var5 = var5 + " (Team: " + var1.h() + ")";
      } else {
         int var6 = com.corrodinggames.rts.game.n.g();
         var5 = var5 + " (" + var6 + " players remaining)";
         if(var6 == 1) {
            var3 = true;
         }
      }

      if(!var2.N() && var2.bx < 60) {
         com.corrodinggames.rts.gameFramework.l.e("Not showing defeated message: " + var5);
         var5 = null;
      }

      if(var1.E) {
         var5 = null;
      }

      if(var5 != null) {
         this.j(var5);
      }

      if(var3) {
         com.corrodinggames.rts.game.n.Q();
      }

   }

   public strictfp void i(com.corrodinggames.rts.game.n var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      String var3 = var1.v;
      if(var3 == null) {
         var3 = "Player - " + (var1.k + 1) + "";
      }

      boolean var4 = false;
      String var5;
      if(var2.bx < 10) {
         var5 = var3 + " had no starting units";
      } else {
         var5 = var3 + " has been wiped out";
      }

      if(!this.bb) {
         var5 = var5 + " (Team: " + var1.h() + ")";
      } else {
         int var6 = com.corrodinggames.rts.game.n.g();
         var5 = var5 + " (" + var6 + " players remaining)";
         if(var6 == 1) {
            var4 = true;
         }
      }

      if(!var2.N() && var2.bx < 60) {
         com.corrodinggames.rts.gameFramework.l.e("Not showing defeated message: " + var5);
         var5 = null;
      }

      if(var1.E) {
         var5 = null;
      }

      if(var1.b()) {
         var5 = null;
      }

      if(var5 != null) {
         this.j(var5);
      }

      if(var4) {
         com.corrodinggames.rts.game.n.Q();
      }

   }

   public synchronized strictfp void ar() {
      if(this.bD != null) {
         this.bD.cancel();
         this.bD = null;
      }

   }

   public synchronized strictfp void as() {
      if(this.q && this.C && this.bD == null) {
         this.bD = new Timer();
         ad$6 var1 = new ad$6(this);
         this.bD.schedule(var1, 60000L, 60000L);
      }

   }

   public strictfp String at() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      String var2 = "";
      if(var1.bX.C && !var1.bX.F) {
         String var3 = var1.bX.ai();
         String var4;
         if(this.D) {
            if(this.E != null) {
               var4 = this.E;
               var2 = var2 + var4;
            }
         } else if(var3 != null) {
            var4 = "Local IP address: " + var3 + " port: " + var1.bX.m;
            if(var1.bX.aV != null) {
               if(!var1.bX.aV.booleanValue()) {
                  var4 = var4 + "\nUnable to get a public IP address, check your internet connection";
               } else if(var1.bX.aT != null && var1.bX.aU != null) {
                  var4 = var4 + "\nYour public address is " + (var1.bX.aU.booleanValue()?"<Open>":"<CLOSED>") + " to the internet";
               }
            } else {
               var4 = var4 + "\nRetrieving your public IP...";
            }

            var2 = var2 + var4;
         } else {
            var2 = var2 + "You do not have a network connection";
         }
      }

      if(var1.P()) {
         if(this.p) {
            var2 = var2 + "SandBox Mode!\nPlace any unit, Control all teams, Special powers";
         } else {
            var2 = var2 + "Local skirmish";
         }
      }

      boolean var9 = true;
      if(com.corrodinggames.rts.gameFramework.l.at() && var1.bX.C) {
         var9 = false;
      }

      if(var2.length() != 0) {
         var2 = var2 + "\n";
         if(com.corrodinggames.rts.gameFramework.l.av()) {
            var2 = var2 + "\n";
         }
      }

      if(var1.bX.av || var1.bX.C) {
         if(var9) {
            if(var1.bX.ay.a != null) {
               var2 = var2 + "Game Mode: " + var1.bX.ay.a.a();
            }

            if(var1.bX.ay.b != null) {
               var2 = var2 + "\nMap: " + com.corrodinggames.rts.appFramework.i.e(var1.bX.ay.b);
            }
         }

         var2 = var2 + "\nStarting Credits: " + var1.bX.j();
         var2 = var2 + "\nFog: " + var1.bX.g();
         if(var1.bX.ay.g != 1) {
            var2 = var2 + "\nStarting Units: " + var1.bX.h();
         }

         if(var1.bX.ay.h != 1.0F) {
            var2 = var2 + "\n" + com.corrodinggames.rts.gameFramework.f.a(var1.bX.ay.h, 1) + "X income";
         }

         if(var1.bX.ay.i) {
            var2 = var2 + "\nNo nukes";
         }

         if(var1.bX.ay.l) {
            var2 = var2 + "\nShared control: On";
         }

         if(this.C) {
            if(var1.bX.n != null) {
               var2 = var2 + "\nPassword Protection: On";
            }

            if(!var1.bX.q && !var1.bX.F) {
               var2 = var2 + "\nServer Visibility: Hidden";
            }

            if(var1.bX.o && !var1.bX.F) {
               ArrayList var10 = var1.bZ.j();
               var2 = var2 + "\n-- Required Mods: --\n";
               int var5 = 0;

               String var8;
               for(Iterator var6 = var10.iterator(); var6.hasNext(); var2 = var2 + " mod: \"" + var8 + "\"\n") {
                  com.corrodinggames.rts.gameFramework.i.b var7 = (com.corrodinggames.rts.gameFramework.i.b)var6.next();
                  if(var5 > 2 && var5 < var10.size() - 1) {
                     var2 = var2 + "" + (var10.size() - var5) + " more mods...";
                     break;
                  }

                  ++var5;
                  var8 = var7.b();
                  var8.replace("\"", "\'");
                  var8.replace(";", ".");
               }
            }
         }
      }

      return var2;
   }

   public strictfp String au() {
      if(!this.o) {
         return null;
      } else {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         ArrayList var2 = var1.bZ.j();
         String var3 = "";
         int var4 = 0;

         String var7;
         for(Iterator var5 = var2.iterator(); var5.hasNext(); var3 = var3 + var7) {
            com.corrodinggames.rts.gameFramework.i.b var6 = (com.corrodinggames.rts.gameFramework.i.b)var5.next();
            if(var4 != 0) {
               var3 = var3 + "; ";
            }

            if(var4 > 1 && var4 < var2.size() - 1) {
               var3 = var3 + "" + (var2.size() - var4) + " more...";
               break;
            }

            ++var4;
            var7 = var6.b();
            var7.replace(";", ".");
         }

         return var3;
      }
   }

   public strictfp String av() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1.bX.ay.b == null) {
         return null;
      } else if(var1.bX.ay.a == null) {
         return null;
      } else if(var1.bX.ay.a == ai.a) {
         return "maps/skirmish/" + var1.bX.ay.b;
      } else if(var1.bX.ay.a == ai.b) {
         return "/SD/rusted_warfare_maps/" + var1.bX.ay.b;
      } else {
         com.corrodinggames.rts.gameFramework.l.e("getNetworkMapPath: unhandled type:" + var1.bX.ay.a);
         return null;
      }
   }

   public strictfp boolean aw() {
      return this.C || this.H;
   }

   public strictfp void a(String var1, c var2) {
      com.corrodinggames.rts.gameFramework.l.e("sendCommandError: " + var1);
      if(var2 == null) {
         this.b((c)null, -1, (String)null, var1);
      } else {
         this.a((c)null, (com.corrodinggames.rts.game.n)null, (String)null, var1, var2);
      }

   }

   public strictfp boolean b(c var1, com.corrodinggames.rts.game.n var2, String var3, String var4) {
      String var5 = null;
      String var6 = "";
      String[] var7 = new String[0];
      String var8 = var4.trim();
      boolean var9 = false;
      if(var8.startsWith("-qc ")) {
         var8 = var8.substring("-qc ".length());
         var8 = var8.trim();
         var9 = true;
      }

      String var10;
      int var11;
      if((var8.startsWith("-") || var8.startsWith(".") || var8.startsWith("_")) && var8.length() >= 2) {
         var10 = var8.substring(1).trim();
         var11 = var10.indexOf(" ");
         if(var11 == -1) {
            var11 = var10.length();
         }

         var5 = var10.substring(0, var11).toLowerCase(Locale.ENGLISH);
         if(var11 != -1 && var10.length() >= var11 + 1) {
            var6 = var10.substring(var11 + 1).trim();
            var7 = var6.split(" ");
         }
      }

      if(var5 == null) {
         return false;
      } else if(var9 && !"self_move".equals(var5) && !"self_team".equals(var5)) {
         return false;
      } else if(!"pause".equals(var5) && !"unpause".equals(var5)) {
         if("endgame".equals(var5)) {
            if(var2 == null) {
               this.a("[Could not find player]", var1);
               return true;
            } else if(this.C && var2 == this.z) {
               if(!this.aW) {
                  this.a("[Game not yet started]", var1);
                  return true;
               } else {
                  this.ag();
                  return true;
               }
            } else {
               this.a("[Only the host can end game]", var1);
               return true;
            }
         } else if("teamlock".equals(var5)) {
            if(var2 == null) {
               this.a("[Could not find player]", var1);
               return true;
            } else if((!this.C || var2 != this.z) && !this.d.b(var1)) {
               this.a("[Only the host can change teamlock]", var1);
               return true;
            } else if(!"true".equalsIgnoreCase(var6) && !"on".equalsIgnoreCase(var6)) {
               if(!"false".equalsIgnoreCase(var6) && !"off".equalsIgnoreCase(var6)) {
                  this.a("[Expected true or false]", var1);
                  return true;
               } else {
                  this.ay.m = false;
                  this.a("[teams are unlocked]", var1);
                  return true;
               }
            } else {
               this.ay.m = true;
               this.a("[teams are locked]", var1);
               return true;
            }
         } else if("roomlock".equals(var5)) {
            if(var2 == null) {
               this.a("[Could not find player]", var1);
               return true;
            } else if(this.C && var2 == this.z) {
               if(!"true".equalsIgnoreCase(var6) && !"on".equalsIgnoreCase(var6)) {
                  if(!"false".equalsIgnoreCase(var6) && !"off".equalsIgnoreCase(var6)) {
                     this.a("[Expected true or false]", var1);
                     return true;
                  } else {
                     this.ay.p = false;
                     this.a("[room is unlocked]", var1);
                     return true;
                  }
               } else {
                  this.ay.p = true;
                  this.a("[room is locked]", var1);
                  return true;
               }
            } else {
               this.a("[Only the host can change roomlock]", var1);
               return true;
            }
         } else if("share".equals(var5)) {
            if(var2 == null) {
               this.a("[Could not find player]", var1);
               return true;
            } else if(!this.ay.l) {
               this.a("[Shared control is not enabled in this game]", var1);
               return true;
            } else if(!"true".equalsIgnoreCase(var6) && !"on".equalsIgnoreCase(var6)) {
               if(!"false".equalsIgnoreCase(var6) && !"off".equalsIgnoreCase(var6)) {
                  this.a("[Expected true or false]", var1);
                  return true;
               } else {
                  if(var2.I) {
                     var2.I = false;
                     this.j("[shared control now off for " + var3 + "]");
                  } else {
                     this.j("[shared control already off for " + var3 + "]");
                  }

                  return true;
               }
            } else {
               if(!var2.I) {
                  var2.I = true;
                  this.j("[shared control now on for " + var3 + "]");
               } else {
                  this.j("[shared control already on for " + var3 + "]");
               }

               return true;
            }
         } else {
            int var24;
            if("self_move".equals(var5)) {
               if(var2 == null) {
                  this.a("[Cannot Move - Player not found]", var1);
                  return true;
               } else if(this.aW) {
                  this.a("[Cannot Move \'" + var2.v + "\' - Game has been started]", var1);
                  return true;
               } else if(this.o()) {
                  this.a("[Cannot Move \'" + var2.v + "\' - Game is starting]", var1);
                  return true;
               } else if(this.ay.m) {
                  this.a("[Cannot Move \'" + var2.v + "\' - Teams locked]", var1);
                  return true;
               } else if(var7.length > 0) {
                  try {
                     var24 = Integer.valueOf(var7[0]).intValue();
                  } catch (NumberFormatException var20) {
                     this.a("[Cannot Move \'" + var2.v + "\' - team \'" + var7[0] + "\' is not a number]", var1);
                     return true;
                  }

                  Integer var29 = null;
                  if(var7.length > 1) {
                     try {
                        var29 = Integer.valueOf(var7[1]);
                     } catch (NumberFormatException var19) {
                        this.a("[Cannot Move \'" + var2.v + "\' - ally group \'" + var7[1] + "\' is not a number]", var1);
                        return true;
                     }

                     if(var29.intValue() != -1 && (var29.intValue() < 1 || var29.intValue() > 99)) {
                        this.a("[Cannot Move Team - Ally group - Out of range]", var1);
                        return true;
                     }
                  }

                  boolean var25 = false;
                  if(var24 - 1 == -3) {
                     if(!this.ay.o) {
                        this.a("[Spectators are disabled on this server]", var1);
                        return true;
                     }

                     Object var13 = this.bC;
                     synchronized(this.bC) {
                        var24 = com.corrodinggames.rts.game.n.H();
                        if(var24 != -1) {
                           this.a(var2, -3);
                        }
                     }

                     var25 = true;
                  }

                  int var28 = var2.r;
                  boolean var14 = var28 == -3;
                  if(!var25) {
                     if(var24 < 1 || var24 > com.corrodinggames.rts.game.n.c) {
                        this.a("[Cannot Move \'" + var2.v + "\' - target slotId must between 1-" + com.corrodinggames.rts.game.n.c + "]", var1);
                        return true;
                     }

                     Object var15 = this.bC;
                     synchronized(this.bC) {
                        if(this.z != var2) {
                           com.corrodinggames.rts.game.n var16 = com.corrodinggames.rts.game.n.k(var24 - 1);
                           if(var16 != null && !var16.w && !var16.b()) {
                              this.a("[Cannot move \'" + var2.v + "\' to slot: " + var24 + " - Player: " + var16.v + " is in that slot.]", var1);
                              return true;
                           }
                        }

                        this.a(var2, var24 - 1);
                     }
                  }

                  var2.r = var28;
                  if(var29 != null) {
                     if(var29.intValue() == -1) {
                        var2.r = var2.k % 2;
                     } else {
                        var2.r = var29.intValue();
                     }
                  }

                  if(this.ay.n) {
                     var2.r = var2.k % 2;
                  }

                  if(var25) {
                     var2.r = -3;
                  }

                  if(var25) {
                     if(!var14) {
                        this.j("Player \'" + var2.v + "\' is now a spectator");
                     }
                  } else {
                     this.j("Player \'" + var2.v + "\' moved themselves to: " + var24);
                  }

                  this.P();
                  com.corrodinggames.rts.appFramework.n.o();
                  return true;
               } else {
                  this.a("[Cannot Move \'" + var2.v + "\' - No target]", var1);
                  return true;
               }
            } else if(!"self_team".equals(var5)) {
               if("surrender".equals(var5)) {
                  if(!this.aW) {
                     this.a("[Cannot Surrender - Game has not started]", var1);
                     return true;
                  } else if(var2 == null) {
                     this.a("[Could not find player]", var1);
                     return true;
                  } else {
                     var10 = "";
                     if(!var2.k()) {
                        var2.l();
                        boolean var26 = var2.m();
                        com.corrodinggames.rts.gameFramework.l.e(var3 + ": Is voting to surrender (can surrender:" + var26 + ", afk:" + var2.ab + ", defeated:" + var2.G + ", disconnected:" + var2.B() + ")");
                        if(var26) {
                           var10 = "";
                        } else {
                           var10 = "(Cannot vote) ";
                        }
                     } else {
                        com.corrodinggames.rts.gameFramework.l.e(var3 + ": Is already voting to surrender but updating timestamp");
                        var2.l();
                        var10 = "(Already voted) ";
                     }

                     String var27 = com.corrodinggames.rts.game.n.b(var2.r) + "/" + com.corrodinggames.rts.game.n.c(var2.r);
                     String var12 = "-t " + var10 + "[Votes to surrender " + var27 + "]";
                     this.a(var1, var2, var3, var12);
                     return true;
                  }
               } else {
                  return false;
               }
            } else if(var2 == null) {
               this.a("[Cannot Set Team - Player not found]", var1);
               return true;
            } else if(this.aW) {
               this.a("[" + var2.v + ": Cannot Set Team - Game has been started]", var1);
               return true;
            } else if(this.o()) {
               this.a("[" + var2.v + ": Cannot Set Team - Game is starting]", var1);
               return true;
            } else if(this.ay.m) {
               this.a("[" + var2.v + ": Cannot Set Team - Teams locked]", var1);
               return true;
            } else if(this.ay.n) {
               return true;
            } else {
               try {
                  var24 = Integer.valueOf(var6).intValue();
               } catch (NumberFormatException var21) {
                  this.m("\'" + var6 + "\' is not a number");
                  return true;
               }

               if(var24 == -1) {
                  var11 = var2.k % 2;
               } else {
                  if(var24 < 1 || var24 > 99) {
                     this.a("[Cannot Set Team - Out of range]", var1);
                     return true;
                  }

                  var11 = var24 - 1;
               }

               if(var2.r != var11) {
                  var2.r = var11;
                  this.a("Player \'" + var2.v + "\' team changed to: " + var24, var1);
               }

               this.P();
               com.corrodinggames.rts.appFramework.n.o();
               return true;
            }
         }
      } else if(var2 == null) {
         this.a("[Could not find player]", var1);
         return true;
      } else if((!this.C || var2 != this.z) && !this.d.b(var1)) {
         this.a("[Only the host can change pause state]", var1);
         return true;
      } else if(!this.aW) {
         this.a("[Game not yet started]", var1);
         return true;
      } else {
         boolean var23 = !this.al;
         if("unpause".equals(var5)) {
            var23 = false;
         }

         this.e(var23);
         return true;
      }
   }

   public static strictfp void a(ae var0) {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1.bX != null) {
         var1.bX.d.a(var0);
      }

      if(!com.corrodinggames.rts.gameFramework.l.aU) {
         ad$7 var2 = new ad$7(var0);
         com.corrodinggames.rts.appFramework.c.a((Runnable)var2);
      }
   }

   public strictfp ArrayList ax() {
      Object var1 = this.bC;
      synchronized(this.bC) {
         return com.corrodinggames.rts.game.n.c();
      }
   }

   public strictfp void e(boolean var1) {
      this.al = var1;
      if(this.al) {
         this.j("Game Paused");
      } else {
         this.j("Game unpaused");
      }

   }

   public strictfp void b(c var1, String var2) {
      var1.a(false, false, var2);
   }

   public strictfp void c(c var1, String var2) {
      Iterator var3 = this.aM.iterator();

      while(var3.hasNext()) {
         c var4 = (c)var3.next();
         if(var4.j == var1) {
            this.b(var4, var2);
         }
      }

   }

   public strictfp c a(c var1, int var2, String var3, String var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      h var6 = new h(var1, var2);
      c var7 = new c(this, var6);
      var7.k = var2;
      var7.j = var1;
      var7.m = var3;
      var7.n = var4;

      try {
         var7.d();
         var5.bX.aM.add(var7);
         var5.bX.Q();
         return var7;
      } catch (IOException var9) {
         var9.printStackTrace();
         var7.a("crash");
         return null;
      }
   }

   public strictfp c a(c var1, int var2) {
      Iterator var3 = this.aM.iterator();

      c var4;
      do {
         if(!var3.hasNext()) {
            return null;
         }

         var4 = (c)var3.next();
      } while(var4.k != var2 || var4.j != var1);

      return var4;
   }

   public static strictfp String p(String var0) {
      var0 = var0.trim();
      var0 = var0.replace("\n", ".");
      var0 = var0.replace("\r", ".");
      var0 = var0.replace("\t", ".");
      var0 = var0.replace(" ", ".");

      for(var0 = var0.replace(" ", "_"); var0.startsWith(".") || var0.startsWith("-") || var0.startsWith(" "); var0 = var0.substring(1)) {
         ;
      }

      StringBuilder var1 = new StringBuilder();
      char[] var2 = var0.toCharArray();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         char var5 = var2[var4];
         if(!Character.isISOControl(var5)) {
            var1.append(var5);
         }
      }

      var0 = var1.toString();
      return var0;
   }

   public strictfp void a(ArrayList var1, boolean var2) {
      if(this.bF != null) {
         com.corrodinggames.rts.gameFramework.l.e("startJoinServerInternalThread: Already joining");
      } else if(var1.size() == 0) {
         com.corrodinggames.rts.gameFramework.l.e("startJoinServerInternalThread: no servers");
      } else {
         String var3 = (String)var1.get(0);
         boolean var4 = false;
         ad$8 var5 = new ad$8(this, var2);
         this.bF = this.a(var3, var4, var5);
      }
   }

}
