package com.corrodinggames.rts.game;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Debug;
import android.util.DisplayMetrics;
import android.util.Log;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.a;
import com.corrodinggames.rts.game.b;
import com.corrodinggames.rts.game.e;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.i$a;
import com.corrodinggames.rts.game.j;
import com.corrodinggames.rts.game.k;
import com.corrodinggames.rts.game.l;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.aa;
import com.corrodinggames.rts.gameFramework.ac;
import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.gameFramework.ba;
import com.corrodinggames.rts.gameFramework.be;
import com.corrodinggames.rts.gameFramework.bf;
import com.corrodinggames.rts.gameFramework.bg;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.bs;
import com.corrodinggames.rts.gameFramework.w;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.m.x;
import com.corrodinggames.rts.gameFramework.m.y;
import com.corrodinggames.rts.gameFramework.m.z;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class i extends com.corrodinggames.rts.gameFramework.l {

   public static String a;
   public static boolean b;
   public static boolean c;
   int d;
   public float e = 1.0F;
   public static String f = null;
   k[] g = new k[6];
   String h;
   public boolean i = false;
   public int j = 0;
   public ConcurrentLinkedQueue k = new ConcurrentLinkedQueue();
   Paint l;
   Paint m;
   Paint n;
   Paint o;
   Paint p;
   int q = 0;
   int r = 0;
   int s = 0;
   float t = 16.0F;
   public String u = "0fps";
   Rect v = new Rect();
   public ArrayList w = new ArrayList();
   Paint x;
   Paint y;
   Paint z;
   public Paint A = new Paint();
   public bf B;
   public be C;
   public com.corrodinggames.rts.gameFramework.d.b D = new com.corrodinggames.rts.gameFramework.d.b();
   a E;
   boolean F;
   float G = 0.0F;
   public float H = 1.0F;
   public float I;
   public float J;
   j K;
   j L;
   boolean M;
   y N;
   com.corrodinggames.rts.gameFramework.m.e O;
   com.corrodinggames.rts.gameFramework.m.e P;
   com.corrodinggames.rts.gameFramework.m.e Q;
   float R = 0.0F;
   Rect S = new Rect();
   RectF T = new RectF();
   public com.corrodinggames.rts.gameFramework.m.e U = null;
   public com.corrodinggames.rts.gameFramework.m.e V = null;
   com.corrodinggames.rts.gameFramework.utility.s W = new com.corrodinggames.rts.gameFramework.utility.s("allOnScreenObjects");
   com.corrodinggames.rts.gameFramework.utility.s X = new com.corrodinggames.rts.gameFramework.utility.s("allOnScreenObjectsDirty");
   Matrix Y = new Matrix();
   public ArrayList Z = new ArrayList();
   public ArrayList aa = new ArrayList();
   Timer ab;
   boolean ac;
   Object ad = new Object();
   int ae = 0;
   com.corrodinggames.rts.game.units.am af;
   com.corrodinggames.rts.game.units.am ag;
   float ah;
   boolean ai;


   public strictfp i(Context var1) {
      super(var1);
   }

   public strictfp boolean a() {
      return this.bS.u?true:this.dH != null && this.dH.b();
   }

   public strictfp boolean a(boolean var1) {
      if(!var1 || this.cb.j()) {
         if(this.bS.u) {
            return true;
         }

         if(this.bp) {
            return true;
         }

         if(this.aq && !this.bH) {
            return true;
         }

         if(this.bF && this.dH != null && this.dH.b()) {
            return true;
         }
      }

      return var1 && !this.bX.aW?true:this.bX.I();
   }

   public strictfp int b() {
      return this.s;
   }

   public strictfp boolean c() {
      return this.eh;
   }

   public strictfp boolean d() {
      return this.ei;
   }

   public synchronized strictfp void a(Context var1) {
      Log.d("RustedWarfare", "--- ----------------- ----");
      Log.d("RustedWarfare", "--- GameEngine:init() ----");
      Log.d("RustedWarfare", "--- ----------------- ----");
      if(this.bi) {
         Log.d("RustedWarfare", "GameEngine init has already been called");
      } else {
         com.corrodinggames.rts.gameFramework.l.e("Version:" + this.r());
         if(C() && this.getClass().equals(i.class)) {
            throw new RuntimeException("inSpace but class is:" + this.getClass());
         } else {
            System.gc();
            this.h("Asset Index");
            this.bK = new com.corrodinggames.rts.gameFramework.utility.i(var1);
            long var2 = br.a();
            this.cd = new br(this);
            this.cd.a(bs.C);
            if(aU) {
               this.ci = 1.0F;
            } else {
               DisplayMetrics var4 = var1.e().getDisplayMetrics();
               this.ci = var1.e().getDisplayMetrics().density;
               com.corrodinggames.rts.gameFramework.l.e("densityScaleRaw: " + this.ci);
               this.a(var4.widthPixels, var4.heightPixels);
            }

            this.ci *= this.e;
            com.corrodinggames.rts.gameFramework.l.e("densityScaleRaw*densityScaleMultiplier: " + this.ci);
            if(com.corrodinggames.rts.gameFramework.l.b(var1)) {
               this.ar = true;
            }

            this.E = new b();
            this.bo = false;
            this.h("InputController");
            this.bT = new ac();
            this.bT.a();
            this.h("SettingsEngine");
            this.bQ = SettingsEngine.getInstance(var1);
            this.bQ.loadMainExternalFolder(true);
            com.corrodinggames.rts.gameFramework.e.a.b();
            byte var20 = 3;
            if(aZ) {
               var20 = 1;
            }

            if(this.bQ.numIncompleteLoadAttempts > 1 || this.bQ.numLoadsSinceRunningGameOrNormalExit > var20) {
               this.ee = true;
               if(this.bQ.numIncompleteLoadAttempts > 2 || this.bQ.numLoadsSinceRunningGameOrNormalExit > 4) {
                  this.bQ.forceEnglish = true;
                  this.ef = true;
               }

               if(this.bQ.numIncompleteLoadAttempts > 3) {
                  this.bQ.newRender = false;
               }

               if(this.bQ.numIncompleteLoadAttempts > 4 || this.bQ.numLoadsSinceRunningGameOrNormalExit > 5) {
                  com.corrodinggames.rts.gameFramework.l.e("Extra safe mode");
                  this.eh = true;
               }

               if(this.bQ.numIncompleteLoadAttempts > 5) {
                  com.corrodinggames.rts.gameFramework.l.e("Extra safe mode x2");
                  this.ei = true;
               }

               if(this.bQ.numIncompleteLoadAttempts > 6) {
                  com.corrodinggames.rts.gameFramework.l.e("Extra safe mode x3");
                  this.bQ.newRender = false;
                  this.bQ.shaderEffects = false;
                  this.bQ.teamShaders = false;
               }

               if(this.bQ.newRender && this.bQ.numLoadsSinceRunningGameOrNormalExit > 15) {
                  com.corrodinggames.rts.gameFramework.l.e("Disabling opengl mode");
                  this.bQ.newRender = false;
               }

               com.corrodinggames.rts.gameFramework.l.e("starting game in safe mode, numIncompleteLoadAttempts:" + this.bQ.numIncompleteLoadAttempts + " numLoadsSinceRunningGameOrNormalExit:" + this.bQ.numLoadsSinceRunningGameOrNormalExit);
            }

            if(aO) {
               this.ee = true;
               this.eg = "<forced by command line>";
            }

            if(aP) {
               this.ee = true;
               this.eh = true;
               this.ei = true;
               this.eg = "<forced by command line>";
            }

            ++this.bQ.numLoadsSinceRunningGameOrNormalExit;
            ++this.bQ.numIncompleteLoadAttempts;
            boolean var5 = this.bQ.save();
            if(!var5 && aZ) {
               com.corrodinggames.rts.gameFramework.l.e("starting game in safe mode, failed to save settings");
               this.eg = "failing to write preferences data";
               this.ee = true;
            }

            com.corrodinggames.rts.gameFramework.c.a.a();
            this.cj = this.W();
            com.corrodinggames.rts.gameFramework.l.e("densityScale(): " + this.cj);
            long var6 = br.a();
            com.corrodinggames.rts.gameFramework.h.a.a();
            br.a("Locale.init took:", var6);
            n.L();
            this.l = new Paint();
            this.m = new Paint();
            this.m.a(255, 255, 255, 255);
            this.m.a(true);
            this.a(this.m, 16.0F);
            this.n = new Paint();
            this.n.a(255, 255, 255, 255);
            this.n.a(true);
            this.a(this.n, 16.0F);
            this.o = new Paint();
            this.o.a(100, 255, 0, 0);
            this.a(this.o, 16.0F);
            this.p = new Paint();
            this.p.a(100, 0, 255, 0);
            this.a(this.p, 16.0F);
            this.dn = new Paint();
            this.do = new Paint();
            this.do.a(Paint$Align.b);
            this.do.a(true);
            this.do.a(Typeface.a(Typeface.c, 0));
            this.a(this.do, 16.0F);
            this.dp = new Paint();
            this.dp.a(255, 230, 255, 230);
            this.dp.a(true);
            this.dp.a(Paint$Align.b);
            this.a(this.dp, 18.0F);
            this.x = new Paint();
            this.x.b(-1);
            this.x.c(100);
            this.y = new Paint();
            this.y.b(-7829368);
            this.y.c(240);
            this.y.a(Paint$Style.b);
            this.y.a(1.0F);
            long var8 = br.a();
            this.h("AudioEngine");
            com.corrodinggames.rts.gameFramework.a.e.b();
            this.bM = new com.corrodinggames.rts.gameFramework.a.e();
            this.bM.a(var1);
            br.a("AudioEngine took:", var8);
            this.h("MusicController");
            this.bN = new am();
            this.bN.a(var1);
            if(bh != null) {
               e("init(): using Graphics instance");
               this.bO = bh;
            } else if(bg != null) {
               e("init(): using GraphicsSlick2d");

               try {
                  this.bO = (y)bg.newInstance();
               } catch (InstantiationException var18) {
                  throw new RuntimeException(var18);
               } catch (IllegalAccessException var19) {
                  throw new RuntimeException(var19);
               }
            } else if(aU) {
               this.bO = new z();
            } else {
               this.bO = new x();
            }

            this.h("graphics.init");
            this.bO.a(var1);
            this.bO.b();
            com.corrodinggames.rts.gameFramework.j.a();
            this.h("Fonts");
            this.Y();
            this.h("effects.init");
            this.bR = new com.corrodinggames.rts.gameFramework.d.c();
            this.bR.a(var1);
            this.h("minimapHandler");
            this.bW = new com.corrodinggames.rts.gameFramework.f.o();
            this.bW.a(var1);
            if(ck != null) {
               com.corrodinggames.rts.gameFramework.l.e("We have an initial screen size, can do early setup of image buffers");
               this.h("Map Buffers");
               this.b(ck.a, ck.b);
               this.k();
               com.corrodinggames.rts.game.b.b.d();
               com.corrodinggames.rts.game.b.b.f();
               this.bW.e();
               boolean var10 = com.corrodinggames.rts.gameFramework.l.aA();
               if(var10) {
                  this.h("Setting up postprocessing");
                  boolean var11 = this.i();
                  if(!var11) {
                     com.corrodinggames.rts.gameFramework.l.e("Failed to setup postprocessing");
                  }
               }
            }

            this.h("PathEngine");
            this.bU = new com.corrodinggames.rts.gameFramework.k.l();
            this.h("GroupController");
            this.bV = new aa();
            this.h("CollisionEngine");
            this.bP = new com.corrodinggames.rts.gameFramework.a();
            this.h("InterfaceEngine");
            this.bS = new com.corrodinggames.rts.gameFramework.f.g();
            this.bS.a(var1);
            this.C = be.c(var1);
            this.h("NetworkEngine");
            this.bX = new ad();
            this.bX.F();
            this.h("StatsHandler");
            this.bY = new bg();
            this.h("ModEngine");
            this.bZ = new com.corrodinggames.rts.gameFramework.i.a();
            this.bZ.a();
            if(this.ee) {
               this.bZ.g();
            }

            this.h("CommandController");
            this.cf = new com.corrodinggames.rts.gameFramework.c();
            this.h("GameSaver");
            this.ca = new com.corrodinggames.rts.gameFramework.y();
            this.h("ReplayEngine");
            this.cb = new ba();
            this.cb.a(var1);
            this.h("UnitGeoIndex");
            this.cc = new com.corrodinggames.rts.game.units.f.c();
            this.h("Precalculating map fog");
            com.corrodinggames.rts.game.b.b.c();
            this.h("ScorchMark.load");
            l.b();
            this.h("Projectile.load");
            f.c();
            this.h("Emitter.load");
            com.corrodinggames.rts.gameFramework.d.f.b();
            this.h("Unit.loadAllUnits");
            long var21 = br.a();
            com.corrodinggames.rts.game.units.am.bH();
            br.a("loadAllUnits took:", var21);
            this.h("Loading custom unit data");
            long var12 = br.a();
            ag.h();
            this.h("getAllUnitsChecksum");
            br.a("CustomUnits took:", var12);
            long var14 = br.a();
            this.d = com.corrodinggames.rts.game.units.am.bM();
            br.a("allUnitsChecksum took:", var14);
            this.z = new Paint();
            this.z.a(50, 255, 255, 255);
            this.F();
            System.gc();
            this.bi = true;
            com.corrodinggames.rts.gameFramework.l.e("Init completed");
            br.a("Loading took:", var2);
            this.cd.b(bs.C);
            this.cd.a(true, true);
            long var16 = br.a();
            this.h("Loading map data");
            if(!com.corrodinggames.rts.gameFramework.l.ay) {
               this.x();
            }

            br.a("loadAMenuMap took:", var16);
            this.h("Last setup");
            ap();
            this.bX.m();
            this.h("init complete");
            if(aE) {
               ar.s();
               System.exit(0);
            }

            if(aF) {
               ar.r();
               System.exit(0);
            }

            this.bj = true;
         }
      }
   }

   public strictfp void a(int var1, int var2) {
      float var3 = 1.0F;
      float var4 = com.corrodinggames.rts.gameFramework.f.b(0.0F, 0.0F, (float)var1, (float)var2);
      float var5 = 1131.0F;
      var3 = var4 / var5;
      com.corrodinggames.rts.gameFramework.l.e("defaultViewpointZoomDensity: " + var3);
      if(var3 < 0.5F) {
         var3 = 0.5F;
      }

      if(var3 > 3.0F) {
         var3 = 3.0F;
      }

      com.corrodinggames.rts.gameFramework.l.e("defaultViewpointZoomDensity after limit: " + var3);
      this.cY = 1.0F;
      if((double)com.corrodinggames.rts.gameFramework.f.c(var3 - 1.0F) > 0.1D) {
         this.cY = var3;
         if(this.cY > 2.0F) {
            this.cY = 2.0F;
         }

         if(this.cY < 0.5F) {
            this.cY = 0.5F;
         }

         this.cX = this.cV * this.cY;
      }

   }

   public strictfp void e() {
      this.K();
      this.f();
   }

   public strictfp void f() {
      this.b(false);
      this.bG = false;
      this.bH = false;
      this.bF = false;
      this.bp = false;
      this.bS.u = false;
   }

   public synchronized strictfp void a(boolean var1, com.corrodinggames.rts.gameFramework.s var2) {
      this.K();
      this.a(var1, false, var2);
   }

   public strictfp void a(boolean var1, boolean var2, com.corrodinggames.rts.gameFramework.s var3) {
      this.bC = this.bQ.teamUnitCapSinglePlayer;
      if(this.bC < 1) {
         this.bC = 1;
      }

      this.bB = this.bC;
      this.b(var2);
      n.X();
      this.bo = false;
      System.gc();
      this.bI = true;
      this.bG = false;
      this.bp = false;
      this.bF = false;
      this.by = 0;
      this.ch = false;
      this.bX.a(1L);
      this.bx = 0;
      this.bJ = 0;
      com.corrodinggames.rts.gameFramework.f.a();
      this.bX.t();
      if(!var2) {
         this.dq = false;
         this.dr = false;
         this.ds = 0.0F;
         this.du = false;
         this.dt = false;
      }

      this.j = 0;
      if(!var2) {
         this.cV = 1.0F;
      }

      this.dx = 0.0F;
      if(!this.cb.j()) {
         if(!this.bX.B) {
            ag.b(true);
         } else {
            ag.d();
         }
      }

      int var4;
      if(!this.bX.B) {
         if(!this.cb.j() && var1) {
            this.bs = new e(0);
            this.bs.v = "Player";

            for(var4 = 1; var4 < 8; ++var4) {
               new com.corrodinggames.rts.game.a.a(var4);
            }

            this.bX.aq();
         }
      } else {
         this.bs = this.bX.z;
         if(this.bs == null) {
            throw new RuntimeException("cannot find player\'s team");
         }

         if(this.bs != n.k(this.bs.k)) {
            com.corrodinggames.rts.gameFramework.l.g("Stale playerTeam");
         }
      }

      this.ce = null;
      this.bL = new com.corrodinggames.rts.game.b.b();

      try {
         if(this.dm != null) {
            InputStream var24 = this.dm.w();

            try {
               var24.reset();
            } catch (IOException var22) {
               var22.printStackTrace();
            }

            this.bL.a(var24, var2);
         } else {
            this.bL.a(this.ak(), var2);
         }
      } catch (com.corrodinggames.rts.game.b.f var23) {
         var23.printStackTrace();
         this.a("Error loading map: " + var23.getMessage(), 1);
         if(aT) {
            com.corrodinggames.rts.gameFramework.l.e("Crashing on allowed map error because automated testing is active");
            throw new RuntimeException(var23);
         }

         if(!this.bX.B && this.ao != null) {
            com.corrodinggames.rts.appFramework.g var5 = this.ao.i();
            if(var5 != null) {
               var5.m();
            }
         }

         String var25 = a((Throwable)var23);
         e("Map Load Warning", var25);
         this.bI = false;
         return;
      }

      if(!this.bL.W) {
         e("map did not load, returning");
         this.bI = false;
      } else {
         this.bL.G = false;
         n.e();

         for(var4 = 0; var4 < n.c; ++var4) {
            n var26 = n.k(var4);
            if(var26 != null) {
               var26.J();
            }
         }

         if(!var2) {
            com.corrodinggames.rts.game.units.custom.l.F();
         }

         if(!this.bX.B && !this.cb.j()) {
            this.bX.ay.h = 1.0F;
            this.bX.ay.q = com.corrodinggames.rts.gameFramework.f.a(1, 1000000000);
         }

         this.bJ = this.bX.ay.q;
         e("global Seed: " + this.bJ);
         int var28;
         if(this.bX.B || this.cb.j()) {
            if(!this.bX.F) {
               this.bB = this.bX.aw;
               this.bC = this.bX.ax;
            }

            com.corrodinggames.rts.gameFramework.l.e("Unit cap is now: " + this.bC);
            if(this.bX.ay.d == 0) {
               this.bL.E = false;
               this.bL.F = false;
            } else if(this.bX.ay.d == 1) {
               this.bL.E = true;
               this.bL.F = false;
            } else if(this.bX.ay.d == 2) {
               this.bL.E = true;
               this.bL.F = true;
            }

            this.bL.G = this.bX.ay.e;
            byte var27 = 10;
            if(this.bX.ay.e) {
               var27 = 10;
            }

            for(var28 = 0; var28 < n.c; ++var28) {
               n var6 = n.k(var28);
               if(var6 != null) {
                  if(var6.N == null) {
                     com.corrodinggames.rts.gameFramework.l.e("Fog null for team: " + var6.k);
                  } else {
                     for(int var7 = 0; var7 < this.bL.C; ++var7) {
                        for(int var8 = 0; var8 < this.bL.D; ++var8) {
                           var6.N[var7][var8] = var27;
                        }
                     }
                  }
               }
            }

            var28 = this.bX.k();

            for(int var29 = 0; var29 < n.c; ++var29) {
               n var33 = n.k(var29);
               if(var33 != null) {
                  var33.o = (double)var28;
                  if(var33.w) {
                     if(!var33.y) {
                        if(var33.z != null) {
                           var33.x = var33.z.intValue();
                        } else {
                           var33.x = this.bX.ay.f;
                        }
                     } else {
                        var33.c("aiDifficulty is locked");
                     }
                  }

                  var33.I = this.bX.ay.l;
                  boolean var36 = false;
                  boolean var9 = false;
                  int var10 = this.bX.ay.g;
                  if(var33.A != null) {
                     var10 = var33.A.intValue();
                  }

                  if(var10 != 1) {
                     boolean var11 = true;
                     boolean var12 = true;
                     Float var13 = null;
                     Float var14 = null;
                     Float var15 = null;
                     Float var16 = null;
                     if(var10 == 5 || var10 == 4 || var10 > 10) {
                        var12 = false;
                     }

                     if(var10 == 5 || var10 == 4 || var10 == 3 || var10 > 10) {
                        var11 = false;
                     }

                     if(var10 == 9) {
                        var12 = false;
                        var11 = false;
                     }

                     Iterator var17 = com.corrodinggames.rts.game.units.am.bF().iterator();

                     while(var17.hasNext()) {
                        com.corrodinggames.rts.game.units.am var18 = (com.corrodinggames.rts.game.units.am)var17.next();
                        if(var18 instanceof com.corrodinggames.rts.game.units.am && !var18.bV && var18.bX == var33) {
                           if(var18.bO && !var36) {
                              var36 = true;
                              var13 = Float.valueOf(var18.eo);
                              var14 = Float.valueOf(var18.ep);
                              if(!var11) {
                                 var18.ci();
                                 continue;
                              }
                           }

                           if(var18.bP && !var9) {
                              var9 = true;
                              var15 = Float.valueOf(var18.eo);
                              var16 = Float.valueOf(var18.ep);
                              if(!var12) {
                                 var18.ci();
                              }
                           }
                        }
                     }

                     if(var13 == null) {
                        var13 = var15;
                        var14 = var16;
                     }

                     if(var13 == null) {
                        com.corrodinggames.rts.gameFramework.l.e("placementLocation==null for team:" + var33.k);
                     } else {
                        float var38 = var13.floatValue();
                        float var39 = var14.floatValue();
                        int var19;
                        com.corrodinggames.rts.game.units.am var20;
                        if(var10 == 2) {
                           for(var19 = 0; var19 <= 2; ++var19) {
                              if(var19 != 1) {
                                 var20 = ar.h.a();
                                 var20.b(var33);
                                 var20.eo = var38 - 50.0F + (float)(var19 * 50);
                                 var20.ep = var39;
                                 n.c(var20);
                              }
                           }

                           for(var19 = 0; var19 <= 2; ++var19) {
                              var20 = ar.w.a();
                              var20.b(var33);
                              var20.eo = var38 - 50.0F + (float)(var19 * 50);
                              var20.ep = var39 + 50.0F;
                              n.c(var20);
                           }
                        } else if(var10 != 3 && var10 != 4) {
                           if(var10 == 5) {
                              as var41 = ar.a("experimentalSpider");
                              if(var41 == null) {
                                 ad.g("Could not find: experimentalSpider on network.setup.startingUnits==5");
                              } else {
                                 var20 = var41.a();
                                 var20.b(var33);
                                 var20.eo = var38;
                                 var20.ep = var39;
                                 var20.cg = 90.0F;
                                 var20.eq = 2.0F;
                                 var20.dc();
                                 n.c(var20);
                              }
                           } else if(var10 != 9 && var10 > 10) {
                              com.corrodinggames.rts.game.units.custom.l var42 = com.corrodinggames.rts.game.units.custom.l.c(var10);
                              if(var42 == null) {
                                 ad.g("Could not find starting unit on startingUnits==" + var10);
                              } else {
                                 var20 = var42.a();
                                 var20.b(var33);
                                 var20.eo = var38;
                                 var20.ep = var39;
                                 if(!var20.bI()) {
                                    var20.cg = 90.0F;
                                 }

                                 if(var42.eI) {
                                    var20.dc();
                                    if(var20 instanceof com.corrodinggames.rts.game.units.custom.j) {
                                       ((com.corrodinggames.rts.game.units.custom.j)var20).dB();
                                    }
                                 }

                                 n.c(var20);
                              }
                           }
                        } else {
                           for(var19 = 0; var19 <= 2; ++var19) {
                              as var40 = ar.a("combatEngineer");
                              if(var40 == null) {
                                 ad.g("Could not find: combatEngineer on network.setup.startingUnits==3");
                              } else {
                                 com.corrodinggames.rts.game.units.am var21 = var40.a();
                                 var21.b(var33);
                                 var21.eo = var38 - 50.0F + (float)(var19 * 50);
                                 var21.ep = var39 + 50.0F;
                                 n.c(var21);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if(!var2 && (this.ce == null || !this.ce.q)) {
            this.a(0.0F, 0.0F);
            var4 = 0;
            var28 = 0;
            boolean var30 = false;
            Iterator var35 = com.corrodinggames.rts.game.units.am.bE.iterator();

            com.corrodinggames.rts.game.units.am var37;
            while(var35.hasNext()) {
               var37 = (com.corrodinggames.rts.game.units.am)var35.next();
               if(var37 instanceof al) {
                  ++var28;
               } else {
                  ++var4;
               }

               if(var37.bX == this.bs && var37.bP) {
                  this.b(var37.eo, var37.ep);
                  var30 = true;
               }
            }

            if(!var30) {
               var35 = com.corrodinggames.rts.game.units.am.bE.iterator();

               while(var35.hasNext()) {
                  var37 = (com.corrodinggames.rts.game.units.am)var35.next();
                  if(var37.bX == this.bs && !var37.t() && !var37.u()) {
                     this.b(var37.eo, var37.ep);
                  }
               }
            }

            e("there are " + var4 + " units on this map and " + var28 + " trees");
         }

         this.B = be.c(this.am).b(this.ak());
         this.bU.a(this.bL, var2);
         this.bW.a(this.bL, var2);
         this.cf.a();
         this.bV.a();
         if(!var2) {
            com.corrodinggames.rts.gameFramework.d.a.a();
         }

         this.ca.a(var2);
         this.bS.a(var2);
         if(!var2) {
            this.bS.y();
            this.aG();
            if(this.bv) {
               this.bS.y();
            }
         } else {
            this.bS.y();
         }

         this.cc.a(this.bL);
         if(!var2) {
            this.bN.c();
         }

         this.bY.a();
         Iterator var31 = com.corrodinggames.rts.game.units.am.bE.iterator();

         while(var31.hasNext()) {
            com.corrodinggames.rts.game.units.am var32 = (com.corrodinggames.rts.game.units.am)var31.next();
            if(var32 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var34 = (com.corrodinggames.rts.game.units.y)var32;
               var34.c(false);
            }
         }

         this.B.e = true;
         this.C.a(this.am);
         this.bG = true;
         this.bH = false;
         this.bI = false;
         if(var3 != com.corrodinggames.rts.gameFramework.s.a && !this.bQ.hasPlayedGameOrSeenHelp) {
            this.bQ.hasPlayedGameOrSeenHelp = true;
            this.bQ.save();
         }

         for(var4 = 0; var4 < 5; ++var4) {
            System.gc();
         }

         if(!com.corrodinggames.rts.gameFramework.l.aU) {
            Log.a("RustedWarfare", "getNativeHeapSize" + String.valueOf(Debug.getNativeHeapSize()));
            Log.a("RustedWarfare", "getNativeHeapAllocatedSize" + String.valueOf(Debug.getNativeHeapAllocatedSize()));
            Log.a("RustedWarfare", "getNativeHeapFreeSize" + String.valueOf(Debug.getNativeHeapFreeSize()));
            Log.a("RustedWarfare", "Runtime.getRuntime().maxMemory()" + String.valueOf(Runtime.getRuntime().maxMemory()));
         }

         if(this.dk != null) {
            this.dk.a();
         }

         this.G = 0.0F;
         if(this.bX.F && this.bX.B) {
            com.corrodinggames.rts.gameFramework.l.e("Disabling network for singleplayer");
            this.bX.B = false;
         }

         if(!ax()) {
            if(var3 == com.corrodinggames.rts.gameFramework.s.c) {
               com.corrodinggames.rts.gameFramework.l.e("Not starting replay recording as we are loading a save");
            } else {
               this.cb.a(var2);
            }
         }

         if(com.corrodinggames.rts.gameFramework.k.l.m) {
            ;
         }

      }
   }

   private strictfp void aG() {
      this.bS.y();
      Iterator var1 = com.corrodinggames.rts.game.units.am.bE.iterator();

      com.corrodinggames.rts.game.units.am var2;
      do {
         if(!var1.hasNext()) {
            com.corrodinggames.rts.gameFramework.l.e("selectAnyOnScreenBuilder: no builder found");
            return;
         }

         var2 = (com.corrodinggames.rts.game.units.am)var1.next();
      } while(var2.bX != this.bs || !(var2 instanceof com.corrodinggames.rts.game.units.y) || !var2.ak() || !var2.s_() || !var2.bT() || var2.u() || var2.t());

      com.corrodinggames.rts.gameFramework.l.e("selectAnyOnScreenBuilder: found builder");
      this.bS.j(var2);
   }

   public strictfp void g() {
      com.corrodinggames.rts.gameFramework.utility.o var1 = w.dK();
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         w var3 = (w)var2.next();
         var3.a();
      }

      com.corrodinggames.rts.game.units.am.bF();
      w.dK();
      int var6 = var1.size();
      if(var6 != 0) {
         com.corrodinggames.rts.gameFramework.l.a("SHOULD_NOT_HAPPEN: we still had " + var6 + " objects in gameObjectListForLogic after removeAll");

         String var5;
         for(Iterator var7 = var1.iterator(); var7.hasNext(); com.corrodinggames.rts.gameFramework.l.a("Remaining object: " + var5)) {
            w var4 = (w)var7.next();
            var5 = "Object: " + var4.eh;
            if(var4 instanceof com.corrodinggames.rts.game.units.am) {
               var5 = ((com.corrodinggames.rts.game.units.am)var4).c();
            }
         }

         if(com.corrodinggames.rts.gameFramework.l.B().aa()) {
            throw new RuntimeException("We still had " + var6 + " objects in gameObjectListForLogic after removeAll");
         }
      }

      com.corrodinggames.rts.game.units.am.bF().clear();
      w.dK().clear();
      com.corrodinggames.rts.game.units.custom.j.dD();
      this.W.clear();
   }

   public strictfp void b(boolean var1) {
      Object var2 = this.aj;
      synchronized(this.aj) {
         if(this.ao != null) {
            this.ao.l();
         }

         this.bq = false;
         if(!var1) {
            this.cb.g();
         }

         this.bU.c();
         this.g();
         if(!av()) {
            this.bN.f();
         }

         this.bR.a(var1);
         if(this.bL != null) {
            this.bL.h();
            this.bL = null;
         }

         if(this.ce != null) {
            this.ce = null;
         }

         if(this.cc != null) {
            this.cc.b();
         }

         this.af = null;
         this.ag = null;
         this.j = 0;
         n.Y();
         this.a(com.corrodinggames.rts.gameFramework.g.f.a, com.corrodinggames.rts.gameFramework.g.c.a);
      }
   }

   public strictfp void a(float var1, int var2) {
      Object var3 = this.aj;
      synchronized(this.aj) {
         this.b(var1, var2);
      }
   }

   public strictfp void b(float var1, int var2) {
      if(this.bx == 2) {
         this.aF();
      } else if(this.bx % 10000 == 0 && this.bx != 0) {
         this.aF();
      }

      if(aL && !this.aS && at() && Debug.getNativeHeapAllocatedSize() > 209715200L) {
         com.corrodinggames.rts.gameFramework.l.e("getNativeHeapAllocatedSize: " + com.corrodinggames.rts.gameFramework.f.g((int)Debug.getNativeHeapAllocatedSize()));
         this.aS = true;
      }

      this.aE();
      this.eb.a();
      this.ec.b();
      this.cd.a(bs.a);
      this.bX.b(var1);
      this.ao = this.ap;
      if(this.ao.b()) {
         this.cd.a(bs.b);

         while(this.k.peek() != null) {
            Runnable var3 = (Runnable)this.k.poll();
            var3.run();
         }

         if(!this.bG) {
            if(!this.aq) {
               Log.d("RustedWarfare", "game running without a loaded level!!!");
               this.h();

               try {
                  Thread.sleep(10L);
               } catch (InterruptedException var21) {
                  var21.printStackTrace();
               }

            }
         } else {
            this.bq = true;
            if(!this.F && this.bx > 5) {
               this.F = true;
               boolean var25 = false;
               if(this.bQ.numIncompleteLoadAttempts > 1) {
                  var25 = true;
               }

               this.bQ.numIncompleteLoadAttempts = 0;
               if(this.ee) {
                  this.bQ.numLoadsSinceRunningGameOrNormalExit = 0;
               }

               this.bQ.save();
               if(this.ee && (this.ef || this.bZ.c() > 0)) {
                  if(this.eg != null) {
                     this.c("Safe mode", "Started game in safe mode due to " + this.eg + ". Mods have been disabled.");
                  } else if(var25) {
                     this.c("Safe mode", "Started game in safe mode due to failed loading attempts. Mods have been disabled.");
                  } else {
                     this.c("Safe mode", "Started game in safe mode due to multiple loads without starting a game or exiting. Mods have been disabled.");
                  }
               }
            }

            if(!this.bH && this.bG && this.bQ.numLoadsSinceRunningGameOrNormalExit != 0) {
               this.bQ.numLoadsSinceRunningGameOrNormalExit = 0;
               this.bQ.save();
            }

            this.ca.b();
            float var26 = this.cV * this.cY;
            float var4;
            float var5;
            float var6;
            if(var26 != this.cX) {
               var4 = this.da / this.cX + this.cy;
               var5 = this.db / this.cX + this.cz;
               this.cX = var26;
               this.k();
               if(this.cZ) {
                  var6 = this.da / this.cX + this.cy;
                  float var7 = this.db / this.cX + this.cz;
                  this.a(this.cy - (var6 - var4), this.cz - (var7 - var5));
                  this.cZ = false;
               }
            }

            if(this.cr != 0.0F || this.cs != 0.0F) {
               var4 = 3.0F * var1;
               var5 = 0.0F;
               if(this.cr > 0.0F) {
                  var5 = com.corrodinggames.rts.gameFramework.f.g(this.cr, var4);
               }

               if(this.cr < 0.0F) {
                  var5 = com.corrodinggames.rts.gameFramework.f.f(this.cr, -var4);
               }

               var5 += 0.15F * this.cr;
               var6 = 0.0F;
               if(this.cs > 0.0F) {
                  var6 = com.corrodinggames.rts.gameFramework.f.g(this.cs, var4);
               }

               if(this.cs < 0.0F) {
                  var6 = com.corrodinggames.rts.gameFramework.f.f(this.cs, -var4);
               }

               var6 += 0.15F * this.cs;
               if(com.corrodinggames.rts.gameFramework.f.c(this.cr) <= var4) {
                  var5 = this.cr;
                  this.cr = 0.0F;
               } else {
                  this.cr -= var5;
               }

               if(com.corrodinggames.rts.gameFramework.f.c(this.cs) <= var4) {
                  var6 = this.cs;
                  this.cs = 0.0F;
               } else {
                  this.cs -= var6;
               }

               this.cy += var5;
               this.cz += var6;
               this.a(this.cy, this.cz);
               this.Q();
            }

            if(this.cR != this.cS) {
               this.k();
            }

            if(var1 > 3.0F) {
               var1 = 3.0F;
            }

            if(var1 < 0.0F) {
               var1 = 0.0F;
            }

            if(this.bu >= 0.0F) {
               var1 = this.bu;
            }

            this.bA = (int)((float)this.bA + var1 * 16.666666F);
            this.d(var1);
            this.q += var2;
            ++this.r;
            if(this.r >= 40) {
               if(this.q == 0) {
                  this.q = 1;
               }

               this.s = (int)((float)(this.r * 1000 / this.q) + 0.5F);
               this.t = (float)this.q / (float)this.r;
               this.q = 0;
               this.r = 0;
               if(this.bQ.showFps) {
                  this.u = this.s + "fps";
               }
            }

            this.aj();

            for(int var27 = 0; var27 < this.dM.length; ++var27) {
               this.dM[var27] = true;
            }

            this.dh = com.corrodinggames.rts.gameFramework.f.a(this.dh, 0.1F * var1);
            this.di = com.corrodinggames.rts.gameFramework.f.a(this.di, 0.1F * var1);
            this.dh = com.corrodinggames.rts.gameFramework.f.b(this.dh, 5.0F);
            this.di = com.corrodinggames.rts.gameFramework.f.b(this.di, 5.0F);
            this.bS.a(var1);
            this.Q();
            com.corrodinggames.rts.game.b.b.f();
            if(this.bX.B) {
               var4 = var1;
               if(this.cb.v != 1) {
                  var4 = var1 * (float)this.cb.v;
               }

               this.bX.a(var4);
               if(!this.a(true) && !this.bX.Y) {
                  this.G += var4;

                  while(this.G > this.bX.c()) {
                     if(this.bX.I()) {
                        this.bX.Y = true;
                        break;
                     }

                     this.G -= this.bX.c();
                     this.bX.a(this.bX.c(), false);
                     if(this.bX.Y) {
                        break;
                     }

                     this.a(this.bX.c());
                  }

                  if(!this.bX.C) {
                     if(this.bX.af || this.bX.ad) {
                        if(this.bX.af && this.bX.ad && this.bx < this.bX.X - this.bX.Q - 5) {
                           this.bX.d("nearly within frame range");
                           this.bX.af = false;
                        }

                        if(this.bx > this.bX.X - 6) {
                           this.bX.d("we have back within frame range");
                           this.bX.af = false;
                           this.bX.ad = false;
                        }
                     }

                     if(!this.bX.ad && this.bx < this.bX.X - this.bX.Q - 10) {
                        this.bX.d("we are slightly out of frame range, speeding up");
                        this.bX.ad = true;
                     }

                     if(!this.bX.af && this.bx < this.bX.X - this.bX.Q - 30) {
                        this.bX.d("we are out of frame range, fast forwarding (" + this.bx + "->" + this.bX.X + ")");
                        this.bX.af = true;
                     }

                     if(!this.bX.af && this.bX.ad) {
                        this.bX.ae += var1;
                        if(this.bX.ae > this.bX.c() * 3.0F) {
                           this.bX.ae = 0.0F;
                           this.bX.a(this.bX.c(), true);
                           if(!this.bX.Y) {
                              this.a(this.bX.c());
                           }
                        }
                     }

                     if(this.bX.af) {
                        this.bX.a(this.bX.c(), true);
                        if(!this.bX.Y) {
                           this.a(this.bX.c());
                        }
                     }

                     if(this.bx < this.bX.X - 90) {
                        this.bX.a(this.bX.c(), true);
                        if(!this.bX.Y) {
                           this.a(this.bX.c());
                        }
                     }

                     if(this.bx < this.bX.X - 120) {
                        this.bX.a(this.bX.c(), true);
                        if(!this.bX.Y) {
                           this.a(this.bX.c());
                        }
                     }

                     if(this.bx < this.bX.X - 600) {
                        this.bX.a(this.bX.c(), true);
                        if(!this.bX.Y) {
                           this.a(this.bX.c());
                        }
                     }
                  }
               }
            } else if(this.cb.i()) {
               var4 = var1;
               if(this.cb.v != 1) {
                  var4 = var1 * (float)this.cb.v;
               }

               if(this.bt != 1.0F) {
                  var4 *= this.bt;
               }

               if(!this.a(false)) {
                  this.G += var4;

                  while(this.G > this.bX.c()) {
                     this.G -= this.bX.c();
                     if(this.bX.I()) {
                        break;
                     }

                     this.a(this.bX.c());
                  }
               }

               if(this.G > 100.0F) {
                  this.G = 100.0F;
               }

               if(this.G < 0.0F) {
                  this.G = 0.0F;
               }
            } else if(!this.a(false)) {
               this.a(var1);
            }

            if(this.a(false)) {
               try {
                  Thread.sleep(2L);
               } catch (Exception var22) {
                  ;
               }
            }

            this.bU.a(var1);
            this.bM.b(var1);
            this.bN.a(var1);
            this.bT.b();
            com.corrodinggames.rts.gameFramework.o.a.a().a(var1);
            this.cd.b(bs.b);
            this.cd.a(bs.c);
            if(!this.dv) {
               if(this.bO.a()) {
                  this.a((com.corrodinggames.rts.gameFramework.m.l)null, var1);
               } else if(this.ao.n()) {
                  com.corrodinggames.rts.gameFramework.m.l var29 = this.ao.b(true);
                  this.a(var29, var1);
               } else {
                  com.corrodinggames.rts.appFramework.f var30 = this.ao;
                  this.ao.a(var1, var2);
                  if(var30.c() && !var30.e()) {
                     synchronized(var30.g()) {
                        if(var30.c() && !var30.e()) {
                           this.cd.a(bs.w);
                           com.corrodinggames.rts.gameFramework.m.l var32 = var30.b(true);
                           this.cd.b(bs.w);

                           try {
                              if(!var30.e()) {
                                 if(var32 != null) {
                                    if(var32.c()) {
                                       com.corrodinggames.rts.gameFramework.l.e("gameengine draw: bufferedCanvas drawn on");
                                    }

                                    var32.a(true);
                                 }

                                 if(var32 == null) {
                                    com.corrodinggames.rts.gameFramework.l.f("GameEngine gameViewCanvas is null after lockCanvas - " + var30.hashCode());
                                 }

                                 this.a(var32, var1);
                                 this.bO.a((com.corrodinggames.rts.gameFramework.m.l)null);
                              }
                           } finally {
                              if(var32 != null) {
                                 try {
                                    var30.a(var32, true);
                                 } catch (IllegalArgumentException var19) {
                                    var19.printStackTrace();
                                    com.corrodinggames.rts.gameFramework.l.f("GameEngine catch currentGameView - " + var30.hashCode());
                                    com.corrodinggames.rts.gameFramework.l.f("GameEngine catch currentGameView.gameThreadSync - " + var30.g().hashCode());
                                    var30.h();
                                 } catch (IllegalStateException var20) {
                                    var20.printStackTrace();
                                    com.corrodinggames.rts.gameFramework.l.f("GameEngine catch currentGameView - " + var30.hashCode());
                                    com.corrodinggames.rts.gameFramework.l.f("GameEngine catch currentGameView.gameThreadSync - " + var30.g().hashCode());
                                 }
                              }

                           }
                        }
                     }
                  }

                  this.ao.b(var1, var2);
               }
            }

            this.dv = false;
            this.Z();
            this.cd.b(bs.c);
            if(this.du) {
               this.du = false;
               Integer var31 = l(this.dl);
               String var28 = null;
               if(var31 != null) {
                  var28 = m(this.dl);
               }

               if(this.bX.B) {
                  var28 = null;
                  (new i$a(this)).start();
               }

               if(var28 != null) {
                  com.corrodinggames.rts.gameFramework.l.e("gotoNextLevel: Loading next level: " + var28);
                  this.dl = var28;
                  this.bS.h.b();
                  this.a(true, false, com.corrodinggames.rts.gameFramework.s.b);
               } else {
                  com.corrodinggames.rts.gameFramework.l.e("gotoNextLevel: No next level, finishing");
                  this.bG = false;
                  com.corrodinggames.rts.appFramework.g var33 = this.ao.i();
                  if(var33 != null) {
                     var33.b();
                     var33.m();
                  } else {
                     com.corrodinggames.rts.gameFramework.l.e("gotoNextLevel: Error getInGameActivity==null");
                  }
               }
            }

            if(!this.aq && this.bE && !this.i) {
               e("starting method trace");
               Debug.startMethodTracing("lukeTrace", 110000000);
               this.i = true;
            }

            this.bF = true;
            this.ed.a();
            this.cd.b(bs.a);
            this.cd.b();
         }
      }
   }

   public strictfp void h() {
      com.corrodinggames.rts.appFramework.g var1 = this.ao.i();
      if(var1 != null) {
         if(!var1.c()) {
            var1.b();
         } else {
            com.corrodinggames.rts.gameFramework.l.b("stopAndClose: inGameActivity is isFinishing");
         }
      } else {
         com.corrodinggames.rts.gameFramework.l.b("stopAndClose: Error getInGameActivity==null");
      }

   }

   public strictfp void a(float var1) {
      if(this.ay() && var1 < 0.1F) {
         ad.g("updateAllGame1: deltaSpeed:" + var1 + " frame:" + this.bx + " network.currentStepRate:" + this.bX.c());
      }

      if(this.bt != 1.0F && !this.bX.B && !this.cb.i()) {
         var1 *= this.bt;
      }

      var1 *= this.H;
      this.I = var1 + 2.0F;
      this.J = var1;
      this.bX.c(var1);
      this.by = (int)((float)this.by + var1 * 16.666666F);
      this.cf.c();
      this.cb.a(var1);
      ++this.bx;
      n.g(var1);
      if(this.bL != null) {
         this.bL.e(var1);
      }

      if(this.ay() && var1 < 0.1F) {
         ad.g("updateAllGame2: deltaSpeed:" + var1 + " frame:" + this.bx);
      }

      com.corrodinggames.rts.game.units.am.bF();
      com.corrodinggames.rts.gameFramework.utility.o var2 = w.dK();
      Object[] var3 = var2.b();
      int var4 = var2.size();
      boolean var5 = this.ay();

      int var6;
      for(var6 = 0; var6 < var4; ++var6) {
         w var7 = (w)var3[var6];
         if(var5 && var1 != this.J) {
            ad.h("JIT bug detected, attempting to correct. before object:" + var7.eh + " frame:" + this.bx + " deltaSpeed:" + var1);
            var1 = this.J;
         }

         var7.a(var1);
      }

      if(this.ay() && var1 < 0.1F) {
         ad.g("updateAllGame3: deltaSpeed:" + var1 + " frame:" + this.bx);
      }

      var6 = var2.a.size();

      int var13;
      for(var13 = 0; var13 < var6; ++var13) {
         com.corrodinggames.rts.gameFramework.utility.r var8 = (com.corrodinggames.rts.gameFramework.utility.r)var2.a.get(var13);
         if(var8.a == com.corrodinggames.rts.gameFramework.utility.q.a) {
            w var9 = (w)var8.b;
            if(!var9.ej) {
               var9.a(var1);
            }
         }
      }

      this.cd.a(bs.m);
      this.cc.a();
      this.cd.b(bs.m);
      com.corrodinggames.rts.game.units.y.g(var1);
      com.corrodinggames.rts.game.units.custom.j.s(var1);
      com.corrodinggames.rts.game.units.custom.j.a(var1, 0);
      ++this.j;
      if(this.j >= 1000) {
         this.j = 0;
         var13 = 0;
         Iterator var14 = com.corrodinggames.rts.game.units.am.bF().iterator();

         while(var14.hasNext()) {
            com.corrodinggames.rts.game.units.am var16 = (com.corrodinggames.rts.game.units.am)var14.next();
            if(var16.bV && !(var16 instanceof al)) {
               ++var13;
            }
         }

         boolean var15 = true;
         if(var13 > 70) {
            com.corrodinggames.rts.gameFramework.utility.o var17 = com.corrodinggames.rts.game.units.am.bF();
            Iterator var10 = var17.iterator();

            while(var10.hasNext()) {
               com.corrodinggames.rts.game.units.am var11 = (com.corrodinggames.rts.game.units.am)var10.next();
               if(var11 instanceof com.corrodinggames.rts.game.units.am && var11.bV && !(var11 instanceof al) && var11.bW < (long)(this.by - 30000) && var13 > 70) {
                  var11.a();
                  --var13;
               }
            }
         }
      }

      this.cd.a(bs.l);
      n.f(var1);
      this.cd.b(bs.l);
      com.corrodinggames.rts.gameFramework.d.a.a(var1);
      this.bR.a(var1);
      this.D.a(var1);
      com.corrodinggames.rts.gameFramework.utility.y.a(var1);
      if(this.ce != null) {
         this.ce.c(var1);
      }

      this.cd.a(bs.o);
      this.bV.a(var1);
      this.cd.b(bs.o);
      this.cd.a(bs.n);
      this.bW.a(var1);
      this.cd.b(bs.n);
      this.bU.b(var1);
      if(this.cg != null) {
         this.cg.b();
      }

      this.bY.b();
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.m.l var1, float var2) {
      Object var3 = this.ak;
      synchronized(this.ak) {
         this.b(var1, var2);
      }
   }

   public strictfp boolean i() {
      if(this.K == null) {
         this.K = new j("assets/shaders/post_base.frag");
      }

      if(this.L == null) {
         this.L = new j("assets/shaders/post_displacement.frag");
      }

      this.K.a(this.bO);
      this.L.a(this.bO);
      if(!this.K.g && !this.L.g) {
         return true;
      } else {
         if(!this.M) {
            this.M = true;
            com.corrodinggames.rts.gameFramework.l.e("setupPostprocessing: failed");
         }

         return false;
      }
   }

   public strictfp void a(j var1) {
      if(this.N != null) {
         throw new RuntimeException("Layer already enabled");
      } else {
         this.N = this.bO;
         this.bO = var1.b;
         this.bO.i();
         this.bO.a(new Rect(0, 0, this.bO.m(), this.bO.n()));
         this.bO.b(var1.f, var1.e);
      }
   }

   public strictfp void b(j var1) {
      if(this.N == null) {
         throw new RuntimeException("Layer not enabled");
      } else {
         this.bO.j();
         this.bO.p();
         this.bO = this.N;
         this.N = null;
         this.bO.b(var1.f, var1.e);
      }
   }

   public strictfp void b(com.corrodinggames.rts.gameFramework.m.l var1, float var2) {
      if(var1 == null) {
         b("drawAll", "canvas is null, not may not be available yet");
      } else if(!aB) {
         this.bO.a(var1);
         this.bO.a(this.ao.d());
         this.bO.g();
         ++this.bz;
         com.corrodinggames.rts.gameFramework.m.h.G = 0.0F;
         if(this.du) {
            this.bO.b(Color.a(0, 0, 0));
            this.bO.a("Loading..", this.co, this.cp, this.dp);
         } else {
            float var3 = this.cn;
            if(var3 != 1.0F) {
               this.bO.i();
               this.bO.a(var3, var3);
            }

            boolean var4 = com.corrodinggames.rts.gameFramework.l.aA();
            if(var4 && this.h(113) && this.h(44)) {
               var4 = false;
            }

            if(var4) {
               boolean var5 = this.i();
               if(!var5) {
                  var4 = false;
               }
            }

            if(var4) {
               this.a(this.K);

               try {
                  this.bO.b(Color.a(0, 0, 0));
                  this.cd.a(bs.d);
                  this.c((com.corrodinggames.rts.gameFramework.m.l)null, var2);
                  this.cd.b(bs.d);
               } finally {
                  this.b(this.K);
               }

               this.K.b();
               if(!this.L.a()) {
                  this.a(this.L);

                  int var14;
                  try {
                     this.bO.b(Color.a(128, 128, 255));
                     this.R();
                     var14 = this.bR.a(var2, 3);
                     this.bR.l = null;
                  } finally {
                     this.b(this.L);
                  }

                  if(var14 > 0) {
                     float var6 = this.bO.s();
                     this.L.d.a("screenBase", this.K.a);
                     this.L.d.b("screenBaseSize", this.K.a);
                     this.L.d.a("u_resolution", this.cl, this.cm);
                     this.L.d.a("u_offsetBy", 0.2F * this.cX);
                     this.L.d.a("u_uiScaling", var6);
                     this.L.b();
                  }
               }
            } else {
               this.cd.a(bs.d);
               this.c(var1, var2);
               this.cd.b(bs.d);
            }

            if(!this.A()) {
               this.cd.a(bs.f);
               this.d(var1, var2);
               this.cd.b(bs.f);
            }

            if(this.bQ.showFps && this.cT == 0.0F && !this.cU && !this.cS) {
               this.bO.a(this.u, 100.0F, 35.0F, this.m);
            }

            if(f != null) {
               this.bO.a(f, 100.0F, 85.0F, this.m);
            }

            if(!this.aq && (this.bO.d() != null || com.corrodinggames.rts.gameFramework.l.aW)) {
               this.bS.c(var2);
            }

            if(!this.A()) {
               this.bR.a(var2, 4);
            }

            com.corrodinggames.rts.game.units.custom.j.dE();
            this.bO.h();
            if(var3 != 1.0F) {
               var1.a();
            }

         }
      }
   }

   public strictfp boolean j() {
      return !this.bQ.showUnitIcons?false:((double)this.cX < 0.7D && this.cE >= this.bL.i() - 5.0F && this.cB >= this.bL.j() - 5.0F?true:(C()?(double)this.cX < 0.1D:(av()?(double)this.cX < 0.27D:(double)this.cX < 0.4D)));
   }

   public strictfp void b(float var1) {
      boolean var2 = false;
      if(this.cQ.a < 0 || this.cQ.b < 0 || (float)this.cQ.c > this.bL.i() || (float)this.cQ.d > this.bL.j()) {
         var2 = true;
      }

      if(var2) {
         this.bO.b(Color.a(0, 0, 0));
      }

   }

   public strictfp void c(float var1) {}

   public strictfp void c(com.corrodinggames.rts.gameFramework.m.l var1, float var2) {
      if(this.bG) {
         this.cd.a(bs.h);
         this.X.b();
         this.dw = 0;
         boolean var3 = false;
         w[] var4 = com.corrodinggames.rts.game.units.am.er.a();
         int var5 = w.er.size();

         for(int var6 = 0; var6 < var5; ++var6) {
            w var7 = var4[var6];
            boolean var8 = var7.el;
            boolean var9 = var7.a((com.corrodinggames.rts.gameFramework.l)this);
            var7.el = var9;
            if(var8 != var9) {
               var3 = true;
            }

            if(var9) {
               this.X.a(var7);
            }
         }

         if(this.W.size() != this.X.size()) {
            var3 = true;
         }

         this.cd.b(bs.h);
         this.cd.a(bs.i);
         if(var3) {
            com.corrodinggames.rts.gameFramework.utility.s var12 = this.W;
            this.W = this.X;
            this.X = var12;
         }

         if(!this.j()) {
            Collections.sort(this.W, w.ei);
         }

         this.cd.b(bs.i);
         this.cd.a(bs.q);
         this.cd.a(bs.s);
         this.bO.i();
         this.bO.a(this.cK);
         this.cd.b(bs.s);
         this.cd.a(bs.r);
         this.b(var2);
         this.cd.b(bs.r);
         if(this.bQ.renderFancyWater) {
            if(this.O == null) {
               this.O = this.bO.a(R$drawable.water_cloud);
            }

            if(this.P == null) {
               this.P = this.bO.a(R$drawable.water_layer1);
            }

            if(this.Q == null) {
               this.Q = this.bO.a(R$drawable.water_layer2);
            }

            this.S.a(this.cK);
            this.R += 0.05F * var2;
            if(this.R > 100.0F) {
               this.R -= 100.0F;
            }

            this.bO.a(this.O, this.S, (Paint)null, this.cu / 6, this.cv / 6, 1, 1);
            this.S.a(this.cL);
            this.T.a(this.cL);
            this.bO.i();
            this.R();
            this.bO.a(this.Q, this.T, (Paint)null, (float)this.cu + this.R, (float)this.cv + this.R, 0, 0);
            this.bO.a(this.P, this.T, (Paint)null, (float)this.cu, (float)this.cv, 0, 0);
            this.bO.j();
         }

         this.cd.a(bs.t);
         if(this.bL != null && this.ar()) {
            this.bL.d(var2);
         }

         this.cd.b(bs.t);
         this.R();
         this.bO.a(this.cL);
         boolean var13 = this.j();
         this.bU.c(var2);
         this.cd.b(bs.q);
         w[] var14 = this.W.a();
         int var15 = this.W.size();
         this.dc = true;
         this.dd = true;
         this.de = true;
         this.df = true;
         this.dg = true;
         if((double)this.cX < 0.45D) {
            this.de = false;
            this.dc = false;
            this.dg = false;
         }

         if((double)this.cX < 0.3D) {
            this.df = false;
            this.dd = false;
         }

         w var10;
         int var16;
         if(!var13) {
            for(var16 = 0; var16 < var15; ++var16) {
               var10 = var14[var16];
               if(var10.em == 0) {
                  var10.c(var2);
               }
            }
         }

         com.corrodinggames.rts.gameFramework.d.a.b(var2);
         this.cd.a(bs.g);
         this.bR.b(var2);
         this.bR.a(var2, 1);
         this.cd.b(bs.g);
         this.cd.a(bs.p);
         if(var13) {
            if(this.bS.q() == 0) {
               com.corrodinggames.rts.game.units.am.bI.a(255, 195, 195, 195);
               com.corrodinggames.rts.game.units.am.bJ.a(255, 255, 255, 255);
            } else {
               com.corrodinggames.rts.game.units.am.bI.a(175, 175, 175, 175);
               com.corrodinggames.rts.game.units.am.bJ.a(255, 255, 255, 255);
            }

            for(var16 = 0; var16 < var15; ++var16) {
               var10 = var14[var16];
               if(!var10.f(var2)) {
                  var10.c(var2);
               }
            }

            for(var16 = 0; var16 < var15; ++var16) {
               var10 = var14[var16];
               var10.a(var2, true);
               var10.p(var2);
            }
         } else {
            for(var16 = 0; var16 < var15; ++var16) {
               var10 = var14[var16];
               var10.d(var2);
            }

            for(var16 = 0; var16 < var5; ++var16) {
               var10 = var4[var16];
               if(!var10.el) {
                  if(!(var10 instanceof com.corrodinggames.rts.game.units.am)) {
                     continue;
                  }

                  com.corrodinggames.rts.game.units.am var11 = (com.corrodinggames.rts.game.units.am)var10;
                  if(!var11.cG || var11.bX != this.bs && !var11.cf()) {
                     continue;
                  }
               }

               var10.e(var2);
               if(!var10.el) {
                  var10.p(var2);
               }
            }

            for(var16 = 0; var16 < var15; ++var16) {
               var10 = var14[var16];
               if(var10.em != 0 && var10.em != 10) {
                  var10.c(var2);
               }
            }

            for(var16 = 0; var16 < var15; ++var16) {
               var10 = var14[var16];
               var10.a(var2, false);
               var10.p(var2);
            }

            n.h(var2);
         }

         this.de = true;
         this.df = true;
         this.cd.b(bs.p);
         this.cd.a(bs.g);
         this.bR.a(var2, 2);
         this.cd.b(bs.g);

         for(var16 = 0; var16 < var15; ++var16) {
            var10 = var14[var16];
            if(var10.em == 10) {
               var10.c(var2);
            }
         }

         this.D.b(var2);
         if(this.ce != null) {
            this.ce.a(var2);
         }

         this.c(var2);
         com.corrodinggames.rts.gameFramework.utility.y.b(var2);
         this.cc.c(var2);
         this.cd.a(bs.e);
         this.bO.j();
         this.cd.b(bs.e);
      }
   }

   public strictfp void d(com.corrodinggames.rts.gameFramework.m.l var1, float var2) {
      this.bS.b(var2);
      if(this.ce != null) {
         this.ce.b(var2);
      }

      this.bW.e(var2);
      if(this.bQ.showFps && this.cT == 0.0F) {
         this.cd.c();
      }

      if(this.ch) {
         this.bO.a("Look Mode", this.co, this.cp, this.dp);
      }

      if(this.bm) {
         int var3 = 20;

         for(int var4 = 0; var4 < n.c; ++var4) {
            n var5 = n.k(var4);
            if(var5 != null && var5 instanceof com.corrodinggames.rts.game.a.a) {
               com.corrodinggames.rts.game.a.a var6 = (com.corrodinggames.rts.game.a.a)var5;
               this.bO.a(var6.k + "| c:" + var6.o, 20.0F, (float)var3, this.dn);
               var3 += 20;
            }
         }
      }

   }

   public strictfp void k() {
      this.cj = this.W();
      this.X();
      this.co = this.cl / 2.0F;
      this.cp = this.cm / 2.0F;
      this.cq = (float)((int)(this.cm / 3.0F));
      if(av()) {
         this.cq = (float)((int)(this.cm / 2.5F));
      }

      float var1 = (float)((int)(this.cl / 3.0F));
      if(this.cq > var1) {
         this.cq = var1;
      }

      int var2 = (int)(250.0F * this.cj);
      this.cq = com.corrodinggames.rts.gameFramework.f.b(this.cq, 60.0F, (float)var2);
      float var3 = this.cy + this.cI;
      float var4 = this.cz + this.cJ;
      if(this.cS) {
         this.cF = this.cl;
         this.cG = this.cl;
      } else {
         this.cG = this.cl - this.cq + 1.0F;
         if(com.corrodinggames.rts.gameFramework.f.g.bO) {
            this.cF = this.cl;
         } else {
            this.cF = this.cG;
         }
      }

      if(this.cF < 1.0F) {
         this.cF = 1.0F;
      }

      if(this.cG < 1.0F) {
         this.cG = 1.0F;
      }

      if(this.cR != this.cS) {
         if(!this.cS) {
            var3 -= this.cq / 2.0F / this.cX;
         } else {
            var3 += this.cq / 2.0F / this.cX;
         }
      }

      this.cR = this.cS;
      this.cH = this.cm;
      this.cA = this.cF / this.cX;
      this.cB = this.cH / this.cX;
      this.cE = this.cG / this.cX;
      this.cI = this.cA / 2.0F;
      this.cJ = this.cB / 2.0F;
      this.cK.a(0, 0, (int)this.cF, (int)this.cH);
      this.cL.a(0, 0, (int)this.cA + 1, (int)this.cB + 1);
      this.cM.a(0.0F, 0.0F, this.cA + 1.0F, this.cB + 1.0F);
      this.a(var3 - this.cI, var4 - this.cJ);
   }

   public strictfp void b(int var1, int var2) {
      this.a(var1, var2, 1.0F);
   }

   public strictfp void a(int var1, int var2, float var3) {
      this.cl = (float)var1;
      this.cm = (float)var2;
      this.cn = var3;
      this.k();
   }

   public strictfp String l() {
      return com.corrodinggames.rts.gameFramework.l.aX?"com.corrodinggames.rts.java":(com.corrodinggames.rts.gameFramework.l.aY?"com.corrodinggames.rts.gdx":(aU?"com.corrodinggames.rts.server":(this.am == null?"<null context>":this.am.h())));
   }

   public strictfp String m() {
      if(com.corrodinggames.rts.gameFramework.l.aX) {
         return "java";
      } else if(com.corrodinggames.rts.gameFramework.l.aY) {
         return "java-gdx";
      } else if(aU) {
         return "dedicatedServer";
      } else if(this.am == null) {
         return "<null context>";
      } else {
         try {
            PackageManager var1 = this.am.f();
            String var2 = var1.getInstallerPackageName(this.l());
            return var2;
         } catch (IllegalArgumentException var3) {
            return "IllegalArgumentException: " + var3.getMessage();
         }
      }
   }

   public strictfp boolean n() {
      return this.v().contains("p");
   }

   public strictfp int c(boolean var1) {
      if(!aU && !var1) {
         try {
            PackageInfo var2 = this.am.f().getPackageInfo(this.am.h(), 0);
            int var3 = var2.versionCode;
            return var3;
         } catch (NameNotFoundException var4) {
            throw new RuntimeException(var4);
         }
      } else {
         return 176;
      }
   }

   public strictfp String o() {
      if(!at()) {
         return null;
      } else {
         try {
            PackageInfo var1 = this.am.f().getPackageInfo(this.am.h(), 64);
            Signature[] var2 = var1.signatures;
            int var3 = var2.length;
            byte var4 = 0;
            if(var4 < var3) {
               Signature var5 = var2[var4];
               String var6 = com.corrodinggames.rts.gameFramework.f.b(var5.toByteArray());
               return var6;
            } else {
               return null;
            }
         } catch (NameNotFoundException var7) {
            throw new RuntimeException(var7);
         }
      }
   }

   public strictfp boolean p() {
      if(!com.corrodinggames.rts.gameFramework.l.aZ) {
         if(this.q()) {
            return true;
         }

         if(aV) {
            return true;
         }
      }

      return false;
   }

   public strictfp boolean q() {
      return com.corrodinggames.rts.game.units.y.class.getSimpleName().equals("OrderableUnit");
   }

   public strictfp String r() {
      String var1 = this.t();
      if("" != null && !"".equals("")) {
         var1 = var1 + "-";
      }

      return var1;
   }

   public strictfp void s() {
      a = null;
      this.t();
   }

   public strictfp String t() {
      if(a != null) {
         return a;
      } else {
         String var1 = "v" + this.u();
         if(com.corrodinggames.rts.gameFramework.l.as && !aV) {
            if(com.corrodinggames.rts.gameFramework.l.at) {
               var1 = "TESTING BUILD - " + var1;
            } else if(var1.contains("p")) {
               var1 = "BETA VERSION - " + var1;
            }
         } else {
            var1 = "DEBUG BUILD - " + var1;
         }

         if(!com.corrodinggames.rts.gameFramework.l.aZ && this.q()) {
            var1 = "RAW - " + var1;
         }

         a = var1;
         return a;
      }
   }

   public strictfp String u() {
      return "1.15";
   }

   public strictfp String v() {
      return "1.15";
   }

   public synchronized strictfp void w() {
      this.ac = false;
      if(this.ab != null) {
         this.ab.cancel();
         this.ab = null;
      }

   }

   public synchronized strictfp void a(Activity var1, com.corrodinggames.rts.appFramework.f var2, boolean var3) {
      Object var4 = this.ad;
      synchronized(this.ad) {
         if(!aU) {
            var2.a();
         }

         this.an = var1;
         this.aq = var3;
         this.cS = this.aq;
         if(var3 && !this.bG && !this.bI && !com.corrodinggames.rts.gameFramework.l.ay && !this.bX.B) {
            this.x();
         }

         com.corrodinggames.rts.appFramework.f var5 = this.ap;
         if(this.ao == null) {
            this.ao = var2;
         }

         this.ap = var2;
         if(var5 != null && var5 != var2) {
            var5.j();
         }

         if(var2 != null) {
            var2.m();
         }

         if(this.bS != null) {
            this.bS.e();
         }

         this.w();
         this.J();
      }
   }

   public synchronized strictfp void x() {
      if(this.ae <= 20) {
         boolean var1 = true;
         int var2 = this.bQ.nextBackgroundMap++;
         if(this.bQ.nextBackgroundMap > 3) {
            this.bQ.nextBackgroundMap = 1;
         }

         this.bQ.save();
         var2 = com.corrodinggames.rts.gameFramework.f.b(var2, 1, 3);
         this.dm = null;
         this.dl = "maps/menu_background/menu" + var2 + ".tmx";

         try {
            n.b(10, true);
         } catch (IOException var5) {
            throw new RuntimeException(var5);
         }

         for(int var3 = 0; var3 < n.c; ++var3) {
            com.corrodinggames.rts.game.a.a var4 = new com.corrodinggames.rts.game.a.a(var3);
            if(var3 == 0) {
               this.bs = var4;
            }
         }

         this.a(false, com.corrodinggames.rts.gameFramework.s.a);
         this.bH = true;
         this.bS.y();
         if(!this.bG) {
            com.corrodinggames.rts.gameFramework.l.g("Menu load failed");
            ++this.ae;
         }

      }
   }

   strictfp void d(float var1) {
      if(this.aq && !this.bH) {
         if(this.ag == null) {
            this.ag = this.y();
            if(this.af == this.ag) {
               this.ag = null;
            }
         }

         if(this.af == null) {
            this.af = this.ag;
            this.ag = null;
         }

         if(this.ah != 0.0F && this.ag != null) {
            this.a(var1, this.ag.eo, this.ag.ep, this.ah * 0.5F);
         }

         if(this.af != null) {
            boolean var2 = this.a(var1, this.af.eo, this.af.ep, (1.0F - this.ah) * 0.5F);
            float var3 = com.corrodinggames.rts.gameFramework.f.a(this.cy + this.cI, this.cz + this.cJ, this.af.eo, this.af.ep);
            if(var3 < 6400.0F) {
               var2 = true;
            }

            if(var2) {
               this.ai = true;
            }
         }

         if(this.ai) {
            this.ah += 0.01F * var1;
            if(this.ah >= 1.0F) {
               this.ah = 0.0F;
               this.af = null;
               this.ai = false;
            }
         }
      }

   }

   strictfp com.corrodinggames.rts.game.units.am a(n var1) {
      int var2 = 0;
      Iterator var3 = com.corrodinggames.rts.game.units.am.bE.iterator();

      while(var3.hasNext()) {
         com.corrodinggames.rts.game.units.am var4 = (com.corrodinggames.rts.game.units.am)var3.next();
         if(!var4.u() && (var4.bX == var1 || var1 == null)) {
            ++var2;
         }
      }

      if(var2 > 0) {
         int var7 = com.corrodinggames.rts.gameFramework.f.a(0, var2 - 1);
         int var8 = 0;
         Iterator var5 = com.corrodinggames.rts.game.units.am.bE.iterator();

         while(var5.hasNext()) {
            com.corrodinggames.rts.game.units.am var6 = (com.corrodinggames.rts.game.units.am)var5.next();
            if(!var6.u() && (var6.bX == var1 || var1 == null)) {
               if(var8 == var7) {
                  return var6;
               }

               ++var8;
            }
         }
      }

      return null;
   }

   strictfp com.corrodinggames.rts.game.units.am y() {
      com.corrodinggames.rts.game.units.am var1 = this.a(this.bs);
      return var1 != null?var1:this.a((n)null);
   }

   public strictfp boolean a(float var1, float var2, float var3, float var4) {
      float var5 = com.corrodinggames.rts.gameFramework.f.d(this.cy + this.cI, this.cz + this.cJ, var2, var3);
      float var6 = com.corrodinggames.rts.gameFramework.f.a(this.cy + this.cI, this.cz + this.cJ, var2, var3);
      float var7 = var4 * var1;
      float var8 = 15.0F;
      if(var8 < var7 + 1.0F) {
         var8 = var7 + 1.0F;
      }

      if(var6 >= var8 * var8 && !this.ct) {
         this.cC += com.corrodinggames.rts.gameFramework.f.k(var5) * var7;
         this.cD += com.corrodinggames.rts.gameFramework.f.j(var5) * var7;
         if(com.corrodinggames.rts.gameFramework.f.c(this.cC) >= 1.0F || com.corrodinggames.rts.gameFramework.f.c(this.cD) >= 1.0F) {
            this.cy += this.cC;
            this.cz += this.cD;
            this.cC = 0.0F;
            this.cD = 0.0F;
            this.a(this.cy, this.cz);
         }

         return false;
      } else {
         return true;
      }
   }

   public strictfp int z() {
      return this.d;
   }

}
