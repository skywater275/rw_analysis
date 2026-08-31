package com.corrodinggames.rts.game.units.custom.b;

import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.ba;
import com.corrodinggames.rts.game.units.custom.bn;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.t;
import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.custom.b.d;
import com.corrodinggames.rts.game.units.custom.b.e;
import com.corrodinggames.rts.game.units.custom.b.f;
import com.corrodinggames.rts.game.units.custom.b.g;
import com.corrodinggames.rts.game.units.custom.b.i;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.gameFramework.m.aa;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.y;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.ai;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class c extends a {

   static Paint a = new Paint();
   static ag b = new ag();
   com.corrodinggames.rts.gameFramework.utility.m c = new com.corrodinggames.rts.gameFramework.utility.m();
   com.corrodinggames.rts.gameFramework.utility.m d = new com.corrodinggames.rts.gameFramework.utility.m();
   com.corrodinggames.rts.gameFramework.utility.m e = new com.corrodinggames.rts.gameFramework.utility.m();
   com.corrodinggames.rts.gameFramework.utility.m f = new com.corrodinggames.rts.gameFramework.utility.m();
   com.corrodinggames.rts.gameFramework.utility.m g = new com.corrodinggames.rts.gameFramework.utility.m();
   boolean h;
   static final PointF i = new PointF();
   static final g j = new g("");
   static final Rect k = new Rect();
   static final RectF l = new RectF();


   private static strictfp d c(com.corrodinggames.rts.game.units.custom.l var0, String var1) {
      Iterator var2 = var0.q.iterator();

      d var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (d)var2.next();
      } while(!var1.equalsIgnoreCase(var3.a));

      return var3;
   }

   public static strictfp g a(com.corrodinggames.rts.game.units.custom.l var0, String var1) {
      if(var1 != null && !var1.equals("")) {
         if(var1.equalsIgnoreCase("NONE")) {
            return null;
         } else {
            g var2 = new g(var1);
            var0.a((t)var2);
            return var2;
         }
      } else {
         return null;
      }
   }

   public static strictfp void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1) {
      c var2 = null;
      String var3 = "decal_";
      com.corrodinggames.rts.gameFramework.utility.m var4 = var1.e(var3);
      Iterator var5 = var4.iterator();

      while(var5.hasNext()) {
         String var6 = (String)var5.next();
         var0.a("1.15p9", 115009, var6, "decals");
         String var7 = var6.substring(var3.length());
         d var8 = new d();
         var8.a = var7;
         if(var7.contains(",")) {
            throw new bo("[" + var6 + "]Decal name: \'" + var7 + "\' cannot have \',\'");
         }

         if(var7.contains(" ")) {
            throw new bo("[" + var6 + "]Decal name: \'" + var7 + "\' cannot have spaces");
         }

         if(var7.contains("(")) {
            throw new bo("[" + var6 + "]Decal name: \'" + var7 + "\' cannot have \'(\'");
         }

         var8.G = (f)var1.a(var6, "layer", (Enum)f.d, f.class);
         var8.H = var1.a(var6, "order", Float.valueOf(0.0F)).floatValue();
         var8.c = var1.a(var6, "onlyWhenSelectedByOwnPlayer", Boolean.valueOf(false)).booleanValue();
         var8.d = var1.a(var6, "onlyWhenSelectedByEnemyPlayer", Boolean.valueOf(false)).booleanValue();
         var8.e = var1.a(var6, "onlyWhenSelectedByAllyNotOwnPlayer", Boolean.valueOf(false)).booleanValue();
         var8.f = var1.a(var6, "onlyWhenSelectedByAnyPlayer", Boolean.valueOf(false)).booleanValue();
         int var9 = 0;
         if(var8.c) {
            ++var9;
         }

         if(var8.d) {
            ++var9;
         }

         if(var8.e) {
            ++var9;
         }

         if(var8.f) {
            ++var9;
         }

         if(var9 >= 2) {
            throw new bo("[" + var6 + "]Doesn\'t make sense to set more than one onlyWhenSelectedBy* to true at a time.");
         }

         if(var9 > 0) {
            var8.b = true;
         } else {
            var8.b = false;
         }

         var8.g = var1.a(var6, "includeParentsSelection", Boolean.valueOf(false)).booleanValue();
         var8.h = (q)var1.a(var6, "onlyTeam", (Enum)q.f, q.class);
         var8.i = var1.a(var6, "onlyPlayersWithUnitControl", Boolean.valueOf(false)).booleanValue();
         var8.j = var1.a(var6, "onlyWithZoomLevelOrMore", Float.valueOf(0.0F)).floatValue();
         boolean var10 = false;
         if(var8.G == f.e) {
            var10 = true;
         }

         var8.k = var1.a(var6, "onlyWhileActive", Boolean.valueOf(false)).booleanValue();
         var8.l = var1.a(var6, "onlyWhileAlive", Boolean.valueOf(var10)).booleanValue();
         var8.m = var1.a(var6, "onlyInPreview", Boolean.valueOf(false)).booleanValue();
         var8.n = var1.a(var6, "onlyOnNonPreview", Boolean.valueOf(false)).booleanValue();
         if(var8.m && var8.n) {
            throw new bo("[" + var6 + "]decal with both onlyInPreview and onlyOnNonPreview will never be visible");
         }

         if(var8.j > 5.0F) {
            throw new bo("[" + var6 + "]decal with onlyWithZoomLevelOrMore:" + var8.j + " will never be visible");
         }

         if(var8.j < 0.0F) {
            throw new bo("[" + var6 + "]onlyWithZoomLevelOrMore:" + var8.j + " cannot be less than zero");
         }

         Integer var11 = var1.b(var6, "onlyOnBodyFrameOf", (Integer)null);
         if(var11 != null) {
            var8.o = var11.intValue();
            if(var8.o < 0) {
               throw new bo("[" + var6 + "]onlyOnBodyFrameOf cannot be: " + var8.o);
            }
         }

         LogicBoolean var12 = var1.c(var0, var6, "imageScale", (LogicBoolean)null);
         if(var12 != null) {
            if(LogicBoolean.isStaticNumber(var12)) {
               var8.p = LogicBoolean.getKnownStaticNumber(var12);
            } else {
               var8.q = true;
               var8.r = var12;
            }
         }

         LogicBoolean var13 = var1.c(var0, var6, "imageScaleX", (LogicBoolean)null);
         LogicBoolean var14 = var1.c(var0, var6, "imageScaleY", (LogicBoolean)null);
         if(var13 != null || var14 != null) {
            var8.q = true;
            var8.s = var13;
            var8.t = var14;
         }

         Integer var15 = var1.b(var6, "total_frames", (Integer)null);
         if(var15 != null) {
            var8.J = var15.intValue();
            if(var8.J < 1) {
               throw new bo("[" + var6 + "] TOTAL_FRAMES cannot be: " + var8.J + " (must be 1 or more)");
            }
         }

         var8.M = var1.a(var6, "frame_verticalOrdering", Boolean.valueOf(false)).booleanValue();
         var8.K = var1.b(var6, "frame_width", Integer.valueOf(-1)).intValue();
         var8.L = var1.b(var6, "frame_height", Integer.valueOf(-1)).intValue();
         if(var8.K != -1 && var8.J != -1) {
            throw new bo("[" + var6 + "]TOTAL_FRAMES and frame_width cannot be both set");
         }

         if(var8.L != -1 && var8.L <= 0) {
            throw new bo("[" + var6 + "]frame_height cannot be: " + var8.L);
         }

         if(var8.K != -1 && var8.K <= 0) {
            throw new bo("[" + var6 + "]frame_width cannot be: " + var8.K);
         }

         if(var8.J != -1 && var8.J <= 0) {
            throw new bo("[" + var6 + "]TOTAL_FRAMES cannot be: " + var8.J);
         }

         if(var8.L != -1 || var8.K != -1 || var8.J != -1) {
            var8.I = true;
         }

         boolean var16 = var1.a(var6, "teamColors", Boolean.valueOf(false)).booleanValue();
         com.corrodinggames.rts.gameFramework.m.e var17 = var0.a(var1, var6, "image");
         if(var17 != null) {
            e var18 = new e();
            var18.a = var17;
            if(var18.a != null && var16) {
               var18.b = var0.a(var17, var0.ac);
            }

            var18.a(var8);
            var8.v = var18;
            var8.u = true;
         }

         String var36 = var1.b(var6, "imageStack", (String)null);
         String[] var21;
         boolean var25;
         if(var36 != null && !var36.equals("")) {
            var8.u = true;
            ArrayList var19 = new ArrayList();
            String[] var20 = var36.split(",");
            var21 = var20;
            int var22 = var20.length;

            for(int var23 = 0; var23 < var22; ++var23) {
               String var24 = var21[var23];
               var24 = var24.trim();
               var25 = var16;
               String var26 = null;
               String[] var27;
               if(var24.contains("(") && var24.contains(")")) {
                  var27 = al.b(var24, "(");
                  if(var27 == null) {
                     throw new bo("[" + var6 + "]imageStack: Unexpected format for: " + var36);
                  }

                  var24 = var27[0];
                  var26 = var27[1].trim();
               }

               var27 = var24.split("\\*");
               String var28 = var27[0];
               int var29 = 1;
               if(var27.length >= 2) {
                  var29 = Integer.parseInt(var27[1]);
               }

               if(var26 != null) {
                  if(!var26.endsWith(")")) {
                     throw new bo("[" + var6 + "]imageStack: Missing \')\' in: " + var36);
                  }

                  var26 = var26.substring(0, var26.length() - 1);
                  ArrayList var30 = al.a(var26, ",", false, false);
                  Iterator var31 = var30.iterator();

                  while(var31.hasNext()) {
                     String var32 = (String)var31.next();
                     if(!var32.trim().equals("")) {
                        String[] var33 = al.b(var32, "=");
                        if(var33 == null) {
                           throw new RuntimeException("[" + var6 + "]imageStack: Unexpected key format for: " + var36);
                        }

                        String var34 = var33[0].trim();
                        String var35 = var33[1].trim();
                        if(var34.equalsIgnoreCase("teamColors")) {
                           var25 = ab.g(var6, "imageStack", var35);
                        } else {
                           if(!var34.equalsIgnoreCase("teamColor")) {
                              throw new RuntimeException("[" + var6 + "]imageStack: Unknown parameter: " + var34);
                           }

                           var25 = ab.g(var6, "imageStack", var35);
                        }
                     }
                  }
               }

               e var46 = new e();
               var46.a = var0.a(var0.F, var28, var0.ab, var6, "imageStack");
               if(var46.a == null) {
                  throw new bo("[" + var6 + "]failed to load image " + var28);
               }

               if(var25) {
                  var46.b = var0.a(var46.a, var0.ac);
               }

               var46.a(var8);

               for(int var47 = 0; var47 < var29; ++var47) {
                  var19.add(var46);
               }
            }

            if(var19.size() > 0) {
               var8.w = (e[])var19.toArray(new e[0]);
            }
         }

         var8.x = var1.a(var6, "stack_hOffset", Float.valueOf(1.0F)).floatValue();
         var8.y = var1.b(var6, "stack_frameOffset", Integer.valueOf(0)).intValue();
         var8.A = var1.c(var0, var6, "stack_indexStart", (LogicBoolean)null);
         var8.B = var1.c(var0, var6, "stack_indexCount", (LogicBoolean)null);
         Boolean var37 = var1.a(var6, "stack_drawInReverseOrder", (Boolean)null);
         if(var37 != null && var37.booleanValue() || var37 == null && var8.x < 0.0F) {
            var8.z = true;
         }

         var8.N = var1.a(var0, var6, "frame", (LogicBoolean)null, LogicBoolean$ReturnType.number);
         var8.O = var1.b(var6, "addBodyFrameMultipliedBy", Integer.valueOf(0)).intValue();
         var8.F = var1.a(var0, var6, "isVisible", (LogicBoolean)null);
         var8.R = var1.a(var6, "xOffsetRelative", Float.valueOf(0.0F)).floatValue();
         var8.S = var1.a(var6, "yOffsetRelative", Float.valueOf(0.0F)).floatValue();
         var8.W = var1.c(var0, var6, "xOffsetAbsolute", (LogicBoolean)null);
         var8.X = var1.c(var0, var6, "yOffsetAbsolute", (LogicBoolean)null);
         if(LogicBoolean.isStaticNumber(var8.W)) {
            var8.T = LogicBoolean.getKnownStaticNumber(var8.W);
            var8.W = null;
         }

         if(LogicBoolean.isStaticNumber(var8.X)) {
            var8.U = LogicBoolean.getKnownStaticNumber(var8.X);
            var8.X = null;
         }

         var8.V = var1.a(var6, "hOffset", Float.valueOf(0.0F)).floatValue();
         var8.aa = var1.a(var6, "dirOffset", Float.valueOf(0.0F)).floatValue();
         var8.ab = var1.a(var6, "pivotOffset", Float.valueOf(0.0F)).floatValue();
         var8.Y = var1.a(var6, "alwaysStartDirAtZero", "alwayStartDirAtZero", Boolean.valueOf(false)).booleanValue();
         var8.Z = var1.a(var6, "alwaysStartHeightAtZero", Boolean.valueOf(false)).booleanValue();
         if(var8.R != 0.0F) {
            ;
         }

         var8.ac = var1.b(var0, var6, "basePosition", (LogicBoolean)null);
         var8.ad = var1.b(var0, var6, "drawLineTo", (LogicBoolean)null);
         String var38 = var1.b(var6, "basePositionFromLegEnd", (String)null);
         var21 = null;
         String var39;
         if(var38 != null || var21 != null) {
            if(var38 != null && var21 != null) {
               throw new bo("[" + var6 + "]basePositionFromLegEnd and basePositionFromLegMiddle cannot be used at the same time");
            }

            if(var21 != null) {
               var39 = var21;
               var8.af = true;
            } else {
               var39 = var38;
            }

            var8.ae = var0.b(var39);
            if(var8.ae == -1) {
               throw new bo("[" + var6 + "]basePositionFromLeg* failed to find: " + var39);
            }
         }

         var39 = var1.b(var6, "basePositionFromTurret", (String)null);
         if(var39 != null) {
            bn var40 = var0.e(var39);
            if(var40 == null) {
               throw new bo("[" + var6 + "]basePositionFromTurret failed to find: " + var39);
            }

            var8.ag = var40.e;
         }

         if(var8.ae != -1 && var8.ag != -1) {
            throw new bo("[" + var6 + "]basePositionFromTurret and basePositionFromLeg cannot be used at the same time");
         }

         if((var8.ae != -1 || var8.ag != -1) && var8.ac != null) {
            throw new bo("[" + var6 + "]basePositionFromTurret/basePositionFromLeg cannot be used at the same time as basePosition");
         }

         var8.C = var0.a(var1, var6, "image_shadow");
         var8.D = var1.a(var6, "shadowOffsetX", Float.valueOf(1.0F)).floatValue();
         var8.E = var1.a(var6, "shadowOffsetY", Float.valueOf(1.0F)).floatValue();
         var8.P = var1.a(var6, "color", Integer.valueOf(-1)).intValue();
         var8.Q = var1.a(var6, "lineWidth", Float.valueOf(1.0F)).floatValue();
         float var41 = 1.0F;
         LogicBoolean var42 = var1.c(var0, var6, "alpha", (LogicBoolean)null);
         if(var42 != null) {
            if(LogicBoolean.isStaticNumber(var42)) {
               float var43 = LogicBoolean.getKnownStaticNumber(var42);
               if(var43 < 0.0F || var43 > 1.0F) {
                  throw new bo("[" + var6 + "]alpha should be between 0-1");
               }

               var41 = var43;
            } else {
               var8.ai = var42;
            }
         }

         if(var8.P != -1 || var8.Q != 1.0F || var41 != 1.0F) {
            var8.ah = new ag();
            var8.ah.b(var8.P);
            if(var8.P != -1) {
               aa.a((Paint)var8.ah);
            }

            int var44 = (int)((float)var8.ah.f() * var41);
            if(var44 < 0) {
               var44 = 0;
            }

            if(var44 > 255) {
               var44 = 255;
            }

            var8.ah.c(var44);
            var8.ah.a(var8.Q);
         }

         var25 = true;
         if(LogicBoolean.isStaticFalse(var8.F)) {
            var25 = false;
         }

         if(var8.ad == null && !var8.u) {
            var25 = false;
         }

         if(var41 == 0.0F) {
            var25 = false;
         }

         var0.q.add(var8);
         if(var25) {
            if(var2 == null) {
               var2 = new c();
               var0.b((a)var2);
            }

            if(!var8.b) {
               var2.h = true;
            }

            if(var8.C != null && var8.G != f.a) {
               var2.c.add(var8);
            }

            com.corrodinggames.rts.gameFramework.utility.m var45;
            if(var8.G == f.c) {
               var45 = var2.f;
            } else if(var8.G == f.b) {
               var45 = var2.e;
            } else if(var8.G == f.e) {
               var45 = var2.g;
            } else if(var8.G == f.a) {
               var45 = var2.c;
            } else if(var8.G == f.f) {
               var45 = null;
            } else {
               var45 = var2.d;
            }

            if(var45 != null) {
               var45.add(var8);
               Collections.sort(var45);
            }
         }
      }

   }

   public strictfp void b(com.corrodinggames.rts.game.units.custom.j var1, float var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.j var1) {}

   public strictfp void d(com.corrodinggames.rts.game.units.custom.j var1, float var2) {
      this.a(var1, var2, f.a, this.c);
      this.a(var1, var2, f.b, this.e);
   }

   public strictfp void e(com.corrodinggames.rts.game.units.custom.j var1, float var2) {
      this.a(var1, var2, f.c, this.f);
   }

   public strictfp void c(com.corrodinggames.rts.game.units.custom.j var1, float var2) {
      this.a(var1, var2, f.d, this.d);
   }

   public strictfp void f(com.corrodinggames.rts.game.units.custom.j var1, float var2) {
      this.a(var1, var2, f.e, this.g);
   }

   public static strictfp Rect a(d var0, e var1, com.corrodinggames.rts.gameFramework.m.e var2, int var3) {
      int var4 = var1.c;
      int var5 = var1.d;
      int var6 = 0;
      int var7 = 0;
      if(var3 > 0) {
         int var8;
         int var9;
         int var10;
         if(!var0.M) {
            var8 = 0;
            var9 = var3;
            var10 = var1.f;
            if(var3 >= var10) {
               var8 += var3 / var10;
               var9 = var3 % var10;
            }

            var6 = var9 * var4;
            var7 = var8 * var5;
         } else {
            var8 = var3;
            var9 = 0;
            var10 = var1.e;
            if(var3 >= var10) {
               var9 += var3 / var10;
               var8 = var3 % var10;
            }

            var6 = var9 * var4;
            var7 = var8 * var5;
         }
      }

      Rect var11 = k;
      var11.a = var6;
      var11.b = var7;
      var11.c = var6 + var4;
      var11.d = var7 + var5;
      return var11;
   }

   public static strictfp RectF a(d var0, e var1, com.corrodinggames.rts.gameFramework.m.e var2, float var3, float var4) {
      int var5 = var1.c;
      int var6 = var1.d;
      var3 -= (float)(var5 / 2);
      var4 -= (float)(var6 / 2);
      RectF var7 = l;
      var7.a = var3;
      var7.c = var3 + (float)var5;
      var7.b = var4;
      var7.d = var4 + (float)var6;
      return var7;
   }

   public final strictfp void a(com.corrodinggames.rts.game.units.custom.j var1, float var2, f var3, com.corrodinggames.rts.gameFramework.utility.m var4) {
      a(var1, var2, var3, var4, (PointF)null);
   }

   public static final strictfp void a(com.corrodinggames.rts.game.units.custom.j var0, float var1, f var2, com.corrodinggames.rts.gameFramework.utility.m var3, PointF var4) {
      int var5 = var3.a;
      if(var5 != 0) {
         boolean var6 = var0.cG;
         boolean var7 = false;
         am var8 = var0.dr();
         if(var8 != null) {
            if(var8.cG) {
               var7 = true;
            }

            am var9 = var8.dr();
            if(var9 != null && var9.cG) {
               var7 = true;
            }
         }

         com.corrodinggames.rts.gameFramework.l var39 = com.corrodinggames.rts.gameFramework.l.B();
         float var10 = var39.cX;
         boolean var11 = var2 == f.a;
         Object[] var12 = var3.a();

         for(int var13 = 0; var13 < var5; ++var13) {
            d var14 = (d)var12[var13];
            if((!var14.b || var6 || var7) && var14.j <= var10 && (!var14.k || var0.bT()) && (!var14.l || !var0.bV)) {
               if(var14.b) {
                  boolean var15 = var6;
                  if(var14.g && var7) {
                     var15 = true;
                  }

                  if(!var15) {
                     continue;
                  }

                  com.corrodinggames.rts.game.n var16 = var39.bs;
                  com.corrodinggames.rts.game.n var17 = var0.bX;
                  if(var16 != null && (var14.c && var17 != var16 || var14.d && !var17.c(var39.bs) || var14.e && (!var17.d(var39.bs) || var17 == var16))) {
                     continue;
                  }
               }

               if((var14.F == null || var14.F.read(var0)) && (var14.o < 0 || var0.a == var14.o) && (!var14.i || var39.bS.m(var0)) && (var14.h == q.f || var14.h == null || var39.bs == null || var39.bs.a(var14.h, var0.bX)) && (!var14.m || var0.cp) && (!var14.n || !var0.cp)) {
                  float var18;
                  float var19;
                  float var24;
                  float var25;
                  float var26;
                  float var27;
                  float var28;
                  float var29;
                  float var40;
                  float var41;
                  float var42;
                  float var46;
                  if(var14.ae != -1) {
                     i[] var20 = var0.dT;
                     ba[] var21 = var0.x.ax;
                     if(var20 == null || var20.length <= var14.ae || var21 == null || var21.length <= var14.ae) {
                        continue;
                     }

                     i var22 = var20[var14.ae];
                     ba var23 = var21[var14.ae];
                     var40 = var0.eo + var22.b;
                     var41 = var0.ep + var22.c;
                     var42 = var0.eq + var22.d;
                     var18 = var22.i + var22.r + var23.R + 90.0F;
                     var19 = var22.i + var22.r + var23.R;
                     if(var14.af) {
                        var24 = var0.cg;
                        if(var0.x.dE) {
                           var24 = var0.cL[var0.x.dG].a;
                        }

                        var25 = com.corrodinggames.rts.gameFramework.f.k(var24);
                        var26 = com.corrodinggames.rts.gameFramework.f.j(var24);
                        var27 = var25 * var23.k - var26 * var23.j;
                        var28 = var26 * var23.k + var25 * var23.j;
                        var29 = com.corrodinggames.rts.gameFramework.f.d(var22.b, var22.c, var27, var28);
                        com.corrodinggames.rts.gameFramework.f.a(var22.b, var22.c, var27, var28);
                        var18 = var29 + 90.0F;
                        var19 = var29 + 90.0F;
                     }
                  } else if(var14.ag != -1) {
                     if(var14.ag >= var0.cL.length) {
                        continue;
                     }

                     int var43 = var14.ag;
                     ai var45 = var0.F(var43);
                     var40 = var45.a;
                     var41 = var45.b;
                     var42 = var0.eq + var45.c;
                     var18 = var0.cL[var43].a + 90.0F;
                     var19 = var0.cL[var43].a;
                  } else {
                     Object var44;
                     if(var14.ac == null) {
                        var44 = var0;
                     } else {
                        var44 = var14.ac.readUnit(var0);
                        if(var44 == null) {
                           continue;
                        }
                     }

                     var40 = ((am)var44).eo;
                     var41 = ((am)var44).ep;
                     var42 = ((am)var44).eq;
                     if(var4 != null && var14.ac == null) {
                        var40 = var4.a;
                        var41 = var4.b;
                        var18 = 0.0F;
                     }

                     var18 = ((am)var44).cg + 90.0F;
                     var19 = ((am)var44).cg;
                     if(var14.ac == null && var0.x.dC) {
                        var46 = var0.cL[var0.x.dG].a;
                        var18 = var46 + 90.0F;
                        var19 = var46;
                     }
                  }

                  if(var14.Y) {
                     var18 = 0.0F;
                  }

                  if(var14.Z) {
                     var42 = 0.0F;
                  }

                  var19 += var14.ab;
                  var18 += var14.aa;
                  var40 += var14.T;
                  var41 += var14.U;
                  if(var14.W != null) {
                     var40 += var14.W.readNumber(var0);
                  }

                  if(var14.X != null) {
                     var41 += var14.X.readNumber(var0);
                  }

                  float var49;
                  float var51;
                  if(var14.R != 0.0F || var14.S != 0.0F) {
                     float var47 = com.corrodinggames.rts.gameFramework.f.k(var19);
                     var46 = com.corrodinggames.rts.gameFramework.f.j(var19);
                     var49 = var14.R;
                     var51 = var14.S;
                     var40 += var47 * var51 - var46 * var49;
                     var41 += var46 * var51 + var47 * var49;
                  }

                  var42 += var14.V;
                  y var48;
                  if(var11 && var14.C != null) {
                     var48 = var39.bO;
                     var46 = var40 - var39.cw + var14.D;
                     var49 = var41 - var39.cx + var14.E;
                     Paint var58 = var0.R();
                     com.corrodinggames.rts.gameFramework.m.e var59 = var14.C;
                     var48.k();
                     var48.a(var18, var46, var49);
                     var48.a(var59, var46, var49, var58);
                     var48.l();
                     return;
                  }

                  if(var14.u) {
                     var48 = var39.bO;
                     var46 = var40 - var39.cw;
                     var49 = var41 - var39.cx - var42;
                     var51 = var18;
                     Object var52 = var14.ah;
                     if(var52 == null) {
                        var52 = var0.aN();
                     }

                     if(var14.ai != null) {
                        var25 = var14.ai.readNumber(var0);
                        if(var25 != 1.0F) {
                           Paint var54 = a;
                           var54.b(((Paint)var52).e());
                           var54.a(((Paint)var52).c());
                           int var57 = (int)((float)((Paint)var52).f() * var25);
                           if(var57 < 0) {
                              var57 = 0;
                           }

                           if(var57 > 255) {
                              var57 = 255;
                           }

                           var54.c(var57);
                           var52 = var54;
                        }
                     }

                     int var53;
                     if(var14.N != null) {
                        var53 = (int)var14.N.readNumber(var0);
                     } else {
                        var53 = 0;
                     }

                     var53 += var0.a * var14.O;
                     if(var14.v != null) {
                        e var55 = var14.v;
                        com.corrodinggames.rts.gameFramework.m.e var60;
                        if(var55.b != null) {
                           var60 = var55.b[var0.bX.R()];
                        } else {
                           var60 = var55.a;
                        }

                        var48.k();
                        var48.a(var18, var46, var49);
                        var28 = var14.p;
                        var29 = var14.p;
                        if(var14.q) {
                           if(var14.r != null) {
                              float var30 = var14.r.readNumber(var0);
                              var28 = var30;
                              var29 = var30;
                           }

                           if(var14.s != null) {
                              var28 *= var14.s.readNumber(var0);
                           }

                           if(var14.t != null) {
                              var29 *= var14.t.readNumber(var0);
                           }
                        }

                        if(var28 != 1.0F || var29 != 1.0F) {
                           var48.a(var28, var29, var46, var49);
                        }

                        if(!var14.I) {
                           var48.a(var60, var46, var49, (Paint)var52);
                        } else {
                           Rect var62 = a(var14, var55, var60, var53);
                           RectF var31 = a(var14, var55, var60, var46, var49);
                           var48.a(var60, var62, var31, (Paint)var52);
                        }

                        var48.l();
                     }

                     if(var14.w != null) {
                        var26 = var14.p;
                        var27 = var14.p;
                        if(var14.q) {
                           if(var14.r != null) {
                              var28 = var14.r.readNumber(var0);
                              var26 = var28;
                              var27 = var28;
                           }

                           if(var14.s != null) {
                              var26 *= var14.s.readNumber(var0);
                           }

                           if(var14.t != null) {
                              var27 *= var14.t.readNumber(var0);
                           }
                        }

                        e[] var61 = var14.w;
                        int var65 = 0;
                        int var64 = var61.length;
                        if(var14.A != null) {
                           var65 = (int)var14.A.readNumber(var0);
                           if(var65 < 0) {
                              var65 = 0;
                           }

                           if(var65 >= var61.length) {
                              var65 = var61.length;
                           }
                        }

                        int var66;
                        if(var14.B != null) {
                           var66 = (int)var14.B.readNumber(var0);
                           var64 = var65 + var66;
                           if(var64 < 0) {
                              var64 = 0;
                           }

                           if(var64 >= var61.length) {
                              var64 = var61.length;
                           }
                        }

                        for(var66 = var65; var66 < var64; ++var66) {
                           int var32 = var66;
                           if(var14.z) {
                              var32 = var14.w.length - 1 - var66;
                           }

                           e var33 = var61[var32];
                           com.corrodinggames.rts.gameFramework.m.e var34;
                           if(var33.b != null) {
                              var34 = var33.b[var0.bX.R()];
                           } else {
                              var34 = var33.a;
                           }

                           float var35 = (float)var32 * var14.x;
                           var48.k();
                           var48.a(var51, var46, var49 - var35);
                           if(var26 != 1.0F || var27 != 1.0F) {
                              var48.a(var26, var27, var46, var49 - var35);
                           }

                           int var36 = var53 + var32 * var14.y;
                           Rect var37 = a(var14, var33, var34, var36);
                           RectF var38 = a(var14, var33, var34, var46, var49 - var35);
                           var48.a(var34, var37, var38, (Paint)var52);
                           var48.l();
                        }
                     }
                  }

                  am var50 = null;
                  if(var14.ad != null) {
                     var50 = var14.ad.readUnit(var0);
                  }

                  if(var50 != null) {
                     var46 = var40 - var39.cw;
                     var49 = var41 - var39.cx - var42;
                     var51 = var50.eo - var39.cw;
                     var24 = var50.ep - var39.cx - var50.eq;
                     Object var56 = var14.ah;
                     if(var56 == null) {
                        var56 = b;
                     }

                     if(var14.ai != null) {
                        var26 = var14.ai.readNumber(var0);
                        if(var26 != 1.0F) {
                           Paint var67 = a;
                           var67.b(((Paint)var56).e());
                           int var63 = (int)((float)((Paint)var56).f() * var26);
                           if(var63 < 0) {
                              var63 = 0;
                           }

                           if(var63 > 255) {
                              var63 = 255;
                           }

                           var67.c(var63);
                           var56 = var67;
                        }
                     }

                     var39.bO.a(var46, var49, var51, var24, (Paint)var56);
                  }
               }
            }
         }

      }
   }

   // $FF: synthetic method
   static d b(com.corrodinggames.rts.game.units.custom.l var0, String var1) {
      return c(var0, var1);
   }

}
