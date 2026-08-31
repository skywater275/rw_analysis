package com.corrodinggames.rts.gameFramework.n;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f.n;
import com.corrodinggames.rts.gameFramework.f.r;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.n.a;
import com.corrodinggames.rts.gameFramework.n.e;
import com.corrodinggames.rts.gameFramework.n.f;
import java.util.Iterator;

public class d {

   public static void a(f var0, a var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var3 = false;
      if(!var1.j) {
         var3 = true;
      }

      var0.a(var1);
      var1.i = true;
      var1.j = true;
      var1.k = var2.by;
      boolean var4 = false;
      String var5;
      String var7;
      String var8;
      if(var1.A != null) {
         var5 = var1.A.b();
         n var6 = var2.bS.h.a((String)null, var5);
         if(var6 != null) {
            var7 = "globalMessage_delayPerChar";
            var8 = var1.b(var7);
            int var9;
            if(var8 != null) {
               if(var8.equals("slow")) {
                  var6.e = 18;
               } else {
                  var9 = var1.b(var7, -1);
                  if(var9 != -1) {
                     var6.e = var9;
                  }
               }
            }

            var9 = var1.c("globalMessage_textColor", -1);
            if(var9 != -1) {
               var6.f = var9;
            }
         }

         var4 = true;
      }

      var5 = var1.b("debugMessage");
      if(var5 != null) {
         var1.h("Debug: " + var5);
         if(var2.bv && var2.bl) {
            String var16 = "Debug: " + var5;
            ad.a((String)null, var16);
         }

         var4 = true;
      }

      boolean var17 = var1.a("showOnMap", false);
      if(var17) {
         var2.bW.a(var1.b(), var1.c(), r.d);
         var4 = true;
      }

      if(var1.f.a > 0) {
         Iterator var18 = var1.f.iterator();

         while(var18.hasNext()) {
            com.corrodinggames.rts.gameFramework.n.a.a var20 = (com.corrodinggames.rts.gameFramework.n.a.a)var18.next();
            if(var20.c(var1)) {
               var4 = true;
            }
         }
      }

      if(var1.g == e.a) {
         if(var3) {
            var1.h("objective met");
         }

         var4 = true;
      }

      if(var1.g == e.k) {
         var4 = true;
      }

      if(var1.g == e.i) {
         var4 = true;
      }

      if(var1.g == e.j) {
         var4 = true;
      }

      if(var1.g == e.g) {
         var4 = true;
      }

      float var19;
      float var22;
      if(var1.g == e.h) {
         var4 = true;
         var19 = (float)var1.b();
         var22 = (float)var1.c();
         var2.b(var19, var22);
      }

      Iterator var12;
      y var14;
      if(var1.g == e.e) {
         var19 = (float)var1.b();
         var22 = (float)var1.c();
         float var25 = 0.0F;
         float var10 = 0.0F;
         com.corrodinggames.rts.game.n var11 = var1.a();
         var12 = null;
         boolean var13 = false;
         var14 = null;
         boolean var15 = false;
         if(var11 == null) {
            var1.g("No team set, cannot spawn");
         } else if(var1.v != null) {
            var1.v.a(var19, var22, var25, var10, var11, var13, var12, var14, var15);
         } else {
            var1.g("No valid unit list to spawn");
         }

         var4 = true;
      }

      com.corrodinggames.rts.game.n var23;
      if(var1.g == e.c) {
         var23 = var1.a();
         if(var23 == null) {
            var1.g("Team not set for changeCredits");
         } else {
            Integer var29 = var1.d("set");
            if(var29 != null) {
               var23.o = (double)var29.intValue();
            }

            Integer var36 = var1.d("add");
            if(var36 != null) {
               var23.d((float)var36.intValue());
            }

            var4 = true;
         }
      } else if(var1.g == e.d) {
         var23 = var1.a();
         if(var23 == null) {
            var1.g("Team not set for event_teamTags");
         } else {
            var8 = var1.a("addTeamTags", (String)null);
            if(var8 != null) {
               com.corrodinggames.rts.game.units.custom.h var31 = com.corrodinggames.rts.game.units.custom.g.a(var8);
               var23.b(var31);
            }

            String var33 = var1.a("removeTeamTags", (String)null);
            if(var33 != null) {
               com.corrodinggames.rts.game.units.custom.h var34 = com.corrodinggames.rts.game.units.custom.g.a(var33);
               var23.c(var34);
            }

            var4 = true;
         }
      } else if(var1.g == e.b) {
         var7 = var1.b("target");
         if(var7 == null) {
            f.i("Move trigger has no target id:" + var1.a);
         } else {
            PointF var26 = var0.f(var7);
            if(var26 == null) {
               f.i("Move trigger: Cannot find target for:" + var1.a + " target:" + var7);
            } else {
               com.corrodinggames.rts.game.n var30 = var1.a();
               if(var30 == null) {
                  f.i("Team not set map trigger:" + var1.a);
               } else {
                  int var28 = 0;
                  com.corrodinggames.rts.gameFramework.e var35 = var2.cf.b(var30);
                  var12 = am.bE.iterator();

                  while(var12.hasNext()) {
                     am var39 = (am)var12.next();
                     if(var39.bX == var30 && var39 instanceof y && var1.a(var39) && var1.b(var39)) {
                        var14 = (y)var39;
                        var35.a(var14);
                        ++var28;
                     }
                  }

                  var35.a(var26.a, var26.b);
                  if(var3) {
                     var0.b("firstActivation: move at:" + var2.by + " for teamId:" + var30.k + " to targetId:" + var7 + " (#units:" + var28 + ")");
                  }

                  if(var1.b("unload") != null) {
                     Iterator var32 = am.bE.iterator();

                     while(var32.hasNext()) {
                        am var37 = (am)var32.next();
                        if(var37.bX == var30 && var37 instanceof y && var1.a(var37) && var1.b(var37) && var37.cr()) {
                           y var38 = (y)var37;
                           com.corrodinggames.rts.gameFramework.e var40 = var2.cf.b(var30);
                           var40.e = true;
                           var40.a(var38);
                           com.corrodinggames.rts.game.units.a.c var41 = var38.cp();
                           var40.a(var41);
                        }
                     }
                  }

                  var4 = true;
               }
            }
         }
      } else {
         if(var1.g == e.f) {
            com.corrodinggames.rts.gameFramework.utility.m var21 = new com.corrodinggames.rts.gameFramework.utility.m();
            Iterator var24 = am.bE.iterator();

            am var27;
            while(var24.hasNext()) {
               var27 = (am)var24.next();
               if(var27 instanceof y && var1.a(var27) && var1.b(var27)) {
                  var21.add(var27);
               }
            }

            if(var21.size() > 0) {
               var24 = var21.iterator();

               while(var24.hasNext()) {
                  var27 = (am)var24.next();
                  var27.ci();
                  if(var27 instanceof y && var27.bI()) {
                     var2.bU.a((y)var27);
                  }
               }
            }

            var4 = true;
         }

         if(!var4) {
            var1.h("Trigger activated with no effect");
         }

      }
   }
}
