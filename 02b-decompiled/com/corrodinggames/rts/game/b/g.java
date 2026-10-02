package com.corrodinggames.rts.game.b;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.e;
import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.b.j;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;
import com.corrodinggames.rts.gameFramework.m.y;
import java.util.Properties;

public final class g {

   public j a;
   public int b;
   public int c = -2;
   public short d = -1;
   public boolean e;
   public boolean f;
   public boolean g;
   public boolean h;
   public boolean i;
   public byte j;
   public boolean k;
   public boolean l;
   public g[] m;
   static final Rect n = new Rect();


   public static strictfp boolean a(g var0, g var1) {
      return var0 == var1?true:(var0 == null?false:(var1 == null?false:(var0.a != var1.a?false:var0.b == var1.b)));
   }

   public strictfp g a() {
      g var1 = new g();
      var1.a = this.a;
      var1.b = this.b;
      var1.e = this.e;
      var1.f = this.f;
      var1.g = this.g;
      var1.h = this.h;
      var1.i = this.i;
      var1.j = this.j;
      var1.k = this.k;
      var1.l = this.l;
      return var1;
   }

   public static strictfp void a(String var0) {
      l.b(var0);
      l.B().a("Missing unit data while loading map: " + var0, 1);

      try {
         Thread.sleep(2L);
      } catch (InterruptedException var2) {
         ;
      }

   }

   public static strictfp g a(b var0, e var1, j var2, int var3, short var4, short var5, boolean var6) {
      Properties var7 = var2.a(var2.l + var3);
      String var11;
      int var20;
      if(var7 != null) {
         String var8 = var7.getProperty("showFog");
         if(var8 != null) {
            var20 = Integer.parseInt(var8);
            l var23 = l.B();
            var0.a(var4, var5);
            float var24 = (float)(var0.T + var0.p);
            float var26 = (float)(var0.U + var0.q);
            var23.bL.a(var24, var26, var20, var23.bs, false);
            return null;
         }

         String var9 = var7.getProperty("unit");
         String var10 = var7.getProperty("customUnit");
         if(var9 != null || var10 != null) {
            var11 = var7.getProperty("team");
            n var25 = null;
            if("none".equalsIgnoreCase(var11)) {
               var25 = n.k(-1);
            } else {
               if(var11 == null) {
                  l.b("map", "warning: unit has no team property:" + var9 + " at: " + var4 + "," + var5);
                  return null;
               }

               var25 = n.k(Integer.valueOf(var11).intValue());
               if(var25 == null) {
                  l.b("map", "skipping unit without player:" + var9 + " at: " + var4 + "," + var5 + " team:" + var11);
                  return null;
               }

               if(var25.b()) {
                  l.b("map", "Unit team is marked as spectator:" + var9 + " (skipping unit)");
                  return null;
               }
            }

            Object var13 = null;
            String var28;
            if(var10 != null) {
               com.corrodinggames.rts.game.units.custom.l var14 = com.corrodinggames.rts.game.units.custom.l.n(var10);
               if(var14 == null) {
                  var28 = "Could not find custom unit of:" + var10 + " at x:" + var4 + ", y:" + var5;
                  a(var28);
                  throw new f(var28);
               }

               as var15 = com.corrodinggames.rts.game.units.custom.l.c((as)var14);
               if(var15 != null) {
                  if(var15 instanceof com.corrodinggames.rts.game.units.custom.l) {
                     var14 = (com.corrodinggames.rts.game.units.custom.l)var15;
                  } else {
                     l.b("replacement not a custom unit:" + var15.i());
                  }
               }

               var13 = com.corrodinggames.rts.game.units.custom.l.a(false, var14);
               if(var13 == null) {
                  String var16 = "Metadata unit is null for:" + var10;
                  a(var16);
                  throw new f(var16);
               }
            } else {
               as var27 = ar.a(var9);
               if(var27 != null) {
                  var13 = var27.a();
               }

               if(var13 == null && "scoutShip".equalsIgnoreCase(var9)) {
                  var13 = new com.corrodinggames.rts.game.units.h.d(false);
               }

               if(var13 == null) {
                  var28 = "Could not find unit:" + var9 + " at: " + var4 + "," + var5;
                  a(var28);
                  throw new f(var28);
               }
            }

            var0.a(var4, var5);
            ((am)var13).eo = (float)var0.T + ((am)var13).cZ();
            ((am)var13).ep = (float)var0.U + ((am)var13).da();
            if(var25 == null) {
               throw new f("team has not been set for:" + var9);
            }

            ((am)var13).b(var25);
            if(var7.getProperty("type") != null) {
               ((am)var13).a_(var7.getProperty("type"));
            }

            if(var7.getProperty("randomRotate") != null) {
               ((am)var13).cg = (float)com.corrodinggames.rts.gameFramework.f.a((am)var13, -180, 180);
            }

            ((am)var13).bO = "builder".equalsIgnoreCase(var9) || "builder".equalsIgnoreCase(var10);
            ((am)var13).bP = "commandCenter".equalsIgnoreCase(var9) || "commandCenter".equalsIgnoreCase(var10);
            ((am)var13).bM = true;
            ((am)var13).n();
            n.c((am)var13);
            w.dL();
            return null;
         }

         if(var1 != null && var1.l.equals("units")) {
            Log.d("RustedWarfare", "non unit on units layer at:" + var4 + "," + var5);
            return null;
         }
      }

      g var19 = new g();
      var19.a = var2;
      var2.p = true;
      if(var1 != null && !var1.r) {
         var2.r = true;
      }

      if(var6) {
         var2.q = true;
      }

      var19.b = var3;
      if(var7 != null) {
         if(var7.getProperty("water") != null) {
            var19.e = true;
         }

         if(var7.getProperty("water-bridge") != null) {
            var19.f = true;
         }

         if(var7.getProperty("lava") != null || var7.getProperty("lava-cliff") != null) {
            var19.g = true;
            if(var7.getProperty("lava-cliff") != null) {
               var19.h = true;
            }
         }

         if(var7.getProperty("cliff-soft") != null) {
            var19.h = true;
         }

         if(var7.getProperty("cliff") != null) {
            var19.h = true;
         }

         if(var7.getProperty("large-cliff") != null) {
            var19.k = true;
         }

         if(var7.getProperty("trees") != null) {
            var19.k = true;
         }

         if(var7.getProperty("res_pool") != null) {
            var19.i = true;
         }

         if(var7.getProperty("tree") != null) {
            ;
         }

         if(var7.getProperty("small-rock") != null) {
            var19.j = 40;
         }

         if(var7.getProperty("large-rock") != null) {
            var19.j = -1;
         }

         if(var7.getProperty("block-land") != null) {
            var19.j = -1;
         }

         if(var7.getProperty("block-buildings") != null) {
            var19.l = true;
         }
      }

      var20 = 0;
      int var21 = 0;
      if(var19.a != null) {
         var11 = var19.a.c;
         if(var11 != null) {
            if(var19.b == 0 && var11.equals("shallowwater.png")) {
               var20 = 5;
            }

            if(var19.b == 0 && var11.equals("deepwater.png")) {
               var20 = 2;
            }

            if(var19.b == 0 && var11.equals("water.png")) {
               var20 = 2;
            }

            if(var19.b == 0 && var11.equals("longgrass.png")) {
               var20 = 3;
            }

            if(var19.b == 0 && var11.equals("mountain.png")) {
               var20 = 3;
            }

            if(var19.b == 7 && var11.equals("stone.png")) {
               var20 = 4;
               var21 = 23;
            }

            if(var19.b == 0 && var11.equals("lava.png")) {
               ;
            }

            if(var19.b == 0 && var11.equals("snow.png")) {
               var20 = 2;
            }
         }
      }

      if(var7 != null && var7.getProperty("randomTileBy") != null) {
         try {
            var20 = Integer.parseInt(var7.getProperty("randomTileBy"));
         } catch (NumberFormatException var18) {
            throw new f("(x:" + var4 + "y:" + var5 + ") - randomTileBy: Unexpected integer value:\'" + var7.getProperty("randomTileBy") + "\'");
         }

         if(var7.getProperty("randomTileFixedOffset") != null) {
            try {
               var21 = Integer.parseInt(var7.getProperty("randomTileFixedOffset"));
            } catch (NumberFormatException var17) {
               throw new f("(x:" + var4 + "y:" + var5 + ") - randomTileFixedOffset: Unexpected integer value:\'" + var7.getProperty("randomTileBy") + "\'");
            }
         }
      }

      if(var20 > 0) {
         g[] var22 = new g[var20];

         for(int var12 = 0; var12 < var20; ++var12) {
            var22[var12] = var19.a();
            var22[var12].b += var12 + 1 + var21;
         }

         var19.m = var22;
      }

      return var19;
   }

   public strictfp void a(y var1, RectF var2, float var3, Paint var4) {
      j var5 = this.a;
      Rect var6 = var5.b(this.b);
      var1.a(var5.b, var6, var2, var4);
   }

   // $FF: synthetic method
   public Object clone() {
      return this.a();
   }

}
