package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.ArrayList;
import java.util.Iterator;

public class e extends com.corrodinggames.rts.game.units.custom.a.a {

   public bp a;
   public ArrayList b;
   public int c;
   public boolean d;
   public boolean e;
   public boolean f;


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      bp var7 = bp.a(var0, var1, var2, var3 + "attachments_addNewUnits");
      int var8 = var1.b(var2, var3 + "attachments_deleteNumUnits", Integer.valueOf(0)).intValue();
      boolean var9 = var1.a(var2, var3 + "attachments_disconnect", Boolean.valueOf(false)).booleanValue();
      boolean var10 = var1.a(var2, var3 + "attachments_unload", Boolean.valueOf(false)).booleanValue();
      boolean var11 = var1.a(var2, var3 + "disconnectFromParent", Boolean.valueOf(false)).booleanValue();
      if(!var7.b() || var8 != 0 || var11 || var9 || var10) {
         e var12 = new e();
         var12.a = var7;
         String var13 = var1.b(var2, "attachments_onlyOnSlots", (String)null);
         if(var13 != null) {
            String[] var14 = var13.split(",");
            String[] var15 = var14;
            int var16 = var14.length;

            for(int var17 = 0; var17 < var16; ++var17) {
               String var18 = var15[var17];
               var18 = var18.trim();
               if(!var18.equals("")) {
                  com.corrodinggames.rts.game.units.custom.b.n var19 = var0.i(var18);
                  if(var12.b == null) {
                     var12.b = new ArrayList();
                  }

                  if(var19 == null) {
                     throw new bo("[" + var2 + "]attachments_onlyOnSlots: Could not find attachment slot with name: " + var18);
                  }

                  var12.b.add(var19);
               }
            }
         }

         var12.c = var8;
         var12.f = var11;
         var12.d = var9;
         var12.e = var10;
         var4.ac.add(var12);
      }

   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      int var6;
      boolean var18;
      if((this.d || this.e) && var1.C != null && var1.C.size() > 0) {
         for(var6 = var1.C.size() - 1; var6 >= 0; --var6) {
            am var7 = (am)var1.C.get(var6);
            if(var7 != null) {
               if(this.b != null) {
                  boolean var8 = false;
                  Iterator var9 = this.b.iterator();

                  while(var9.hasNext()) {
                     com.corrodinggames.rts.game.units.custom.b.n var10 = (com.corrodinggames.rts.game.units.custom.b.n)var9.next();
                     if(var10.a() == var6) {
                        var8 = true;
                        break;
                     }
                  }

                  if(!var8) {
                     continue;
                  }
               }

               if(var7 instanceof y) {
                  y var16 = (y)var7;
                  if(this.e) {
                     var18 = var1.B.size() % 2 == 0;
                     var1.a((am)var16, true, var18);
                  } else {
                     var16.bx();
                  }
                  break;
               }

               com.corrodinggames.rts.gameFramework.l.e("Failed to deattach unit:" + var7.r().i() + " is not an OrderableUnit");
            }
         }
      }

      am var17;
      if(this.c != 0) {
         for(var6 = 0; var6 < this.c; ++var6) {
            if(var1.C != null && var1.C.size() > 0) {
               for(int var14 = var1.C.size() - 1; var14 >= 0; --var14) {
                  var17 = (am)var1.C.get(var14);
                  if(var17 != null) {
                     if(this.b != null) {
                        var18 = false;
                        Iterator var19 = this.b.iterator();

                        while(var19.hasNext()) {
                           com.corrodinggames.rts.game.units.custom.b.n var11 = (com.corrodinggames.rts.game.units.custom.b.n)var19.next();
                           if(var11.a() == var14) {
                              var18 = true;
                              break;
                           }
                        }

                        if(!var18) {
                           continue;
                        }
                     }

                     var17.ci();
                     break;
                  }
               }
            }
         }
      }

      if(this.a != null) {
         com.corrodinggames.rts.gameFramework.utility.m var13 = new com.corrodinggames.rts.gameFramework.utility.m();
         this.a.a(var13, var1.bX, var1, true);
         Iterator var15 = var13.iterator();

         while(var15.hasNext()) {
            var17 = (am)var15.next();
            var18 = false;
            if(!(var17 instanceof y)) {
               com.corrodinggames.rts.gameFramework.l.e("Failed to attach unit:" + var17.r().i() + " is not an OrderableUnit");
            } else {
               y var20 = (y)var17;
               com.corrodinggames.rts.game.units.custom.b.n var12;
               Iterator var21;
               if(this.b != null) {
                  var21 = this.b.iterator();

                  while(var21.hasNext()) {
                     var12 = (com.corrodinggames.rts.game.units.custom.b.n)var21.next();
                     if(var1.a(var12) == null && var1.a(var20, var12)) {
                        var20.cQ = -9999;
                        var18 = true;
                        break;
                     }
                  }
               } else {
                  var21 = var1.x.aA.iterator();

                  while(var21.hasNext()) {
                     var12 = (com.corrodinggames.rts.game.units.custom.b.n)var21.next();
                     if(var1.a(var12) == null && var1.a(var20, var12)) {
                        var20.cQ = -9999;
                        var18 = true;
                        break;
                     }
                  }
               }

               if(!var18) {
                  var20.a();
               }
            }
         }
      }

      if(this.f) {
         if(var1.cO != null) {
            var1.bx();
         }

         if(var1.cN != null) {
            if(var1.cN instanceof ak) {
               ((ak)var1.cN).e(var1);
            } else {
               com.corrodinggames.rts.gameFramework.l.g("transportedBy is not a TransportInterface");
               var1.cN = null;
            }
         }
      }

      return true;
   }
}
