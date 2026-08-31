package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.u;
import com.corrodinggames.rts.game.units.custom.a.a.n;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.Iterator;

public class m extends com.corrodinggames.rts.game.units.custom.a.a {

   public com.corrodinggames.rts.game.units.custom.d.b a;
   public boolean b;
   public boolean c;
   public float d = -1.0F;
   public q e;
   public LogicBoolean f;
   public com.corrodinggames.rts.game.units.custom.h g;
   public boolean h = true;
   public com.corrodinggames.rts.game.units.custom.d.b i;
   public UnitReference j;
   public int k = 1;
   public u l;
   public u m;
   public u n;
   public boolean o;
   public boolean p;
   public boolean q;
   public boolean r;
   public boolean s = false;
   public static final com.corrodinggames.rts.gameFramework.utility.m t = new com.corrodinggames.rts.gameFramework.utility.m();
   public static final n u = new n();


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      boolean var7 = var1.a(var2, var3 + "takeResources_includeUnitsInTransport", Boolean.valueOf(false)).booleanValue();
      boolean var8 = var1.a(var2, var3 + "takeResources_includeParent", Boolean.valueOf(false)).booleanValue();
      LogicBoolean var9 = var1.b(var0, var2, var3 + "takeResources_includeReference", (LogicBoolean)null);
      float var10 = var1.a(var2, var3 + "takeResources_includeUnitsWithinRange", Float.valueOf(-1.0F)).floatValue();
      boolean var11 = var1.a(var2, var3 + "takeResources_searchOnly", Boolean.valueOf(false)).booleanValue();
      com.corrodinggames.rts.game.units.custom.d.b var12 = com.corrodinggames.rts.game.units.custom.d.b.a(var0, var1, var2, var3 + "takeResources", true);
      if(var11 && var12 != null && !var12.c()) {
         throw new bo("[" + var2 + "]takeResources not supported with takeResources_searchOnly");
      } else {
         boolean var13 = var1.a(var2, var3 + "takeResources_saveFirstUnitToCustomTarget1", Boolean.valueOf(false)).booleanValue();
         boolean var14 = var1.a(var2, var3 + "takeResources_saveFirstUnitToCustomTarget2", Boolean.valueOf(false)).booleanValue();
         if(!var7 && !var8 && var10 < 0.0F && var9 == null) {
            if(var12 != null && !var12.c()) {
               throw new bo("[" + var2 + "]takeResources requires an include (eg: takeResources_includeUnitsWithinRange, takeResources_includeUnitsInTransport) otherwise no results would be found");
            } else if(var11) {
               throw new bo("[" + var2 + "]takeResources_searchOnly requires an include (eg: takeResources_includeUnitsWithinRange) otherwise no results would be found");
            }
         } else {
            m var15 = new m();
            var4.ac.add(var15);
            var15.b = var7;
            var15.d = var10;
            var15.c = var8;
            var15.f = var9;
            var15.a = var12;
            var15.s = var1.a(var2, var3 + "takeResources_directTransferStoppingAtZero", Boolean.valueOf(var15.s)).booleanValue();
            var15.e = (q)var1.a(var2, "takeResources_includeUnitsWithinRange_team", (Enum)q.a, q.class);
            var15.g = var1.a(var0, var2, var3 + "takeResources_excludeUnitsWithoutTags", (com.corrodinggames.rts.game.units.custom.h)null);
            var15.j = UnitReference.parseUnitReferenceFromConf(var0, var1, var2, var3 + "takeResources_excludeUnitsWithoutCustomTarget1EqualTo", (UnitReference)null);
            if(var15.s) {
               var15.h = false;
            }

            var15.h = var1.a(var2, var3 + "takeResources_excludeUnitsWithoutAllResources", Boolean.valueOf(var15.h)).booleanValue();
            if(var11) {
               var15.k = 200;
               var15.q = true;
               var15.r = true;
            }

            var15.k = var1.b(var2, var3 + "takeResources_maxUnits", Integer.valueOf(var15.k)).intValue();
            var15.l = var1.a(var0, var2, var3 + "takeResources_triggerActionIfAnyCollected", (u)null);
            var15.m = var1.a(var0, var2, var3 + "takeResources_triggerActionIfNoneCollected", (u)null);
            var15.n = var1.a(var0, var2, var3 + "takeResources_triggerActionForEach", (u)null);
            var15.o = var13;
            var15.p = var14;
            var15.q = var1.a(var2, var3 + "takeResources_discardCollected", Boolean.valueOf(var15.q)).booleanValue();
            var15.r = var1.a(var2, var3 + "takeResources_keepResourcesOnTarget", Boolean.valueOf(var15.r)).booleanValue();
            if(var11 && (!var15.q || !var15.r)) {
               throw new bo("[" + var2 + "]takeResources_searchOnly shortcut expects takeResources_discardCollected and takeResources_keepResourcesOnTarget to be true");
            } else {
               var15.i = com.corrodinggames.rts.game.units.custom.d.b.a(var0, var1, var2, var3 + "takeResources_excludeUnitsWithTheseResources", true);
               if(var15.i != null && var15.i.c()) {
                  var15.i = null;
               }

               if(var15.s) {
                  if(var15.a.e()) {
                     throw new bo("[" + var2 + "]takeResources_directTransferStoppingAtZero:true only supports custom resources right now");
                  }

                  if(var15.h) {
                     throw new bo("[" + var2 + "]takeResources_directTransferStoppingAtZero:true is not supported at the same time as takeResources_excludeUnitsWithoutAllResources:true");
                  }

                  if(var15.r) {
                     throw new bo("[" + var2 + "]takeResources_directTransferStoppingAtZero:true is not supported at the same time as takeResources_keepResourcesOnTarget:true");
                  }

                  if(var15.q) {
                     throw new bo("[" + var2 + "]takeResources_directTransferStoppingAtZero:true is not supported at the same time as takeResources_discardCollected:true");
                  }
               }

            }
         }
      }
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      com.corrodinggames.rts.gameFramework.utility.m var6 = t;
      var6.clear();
      am var8;
      if(this.b) {
         Iterator var7 = var1.B.iterator();

         while(var7.hasNext()) {
            var8 = (am)var7.next();
            if(var8 != null && !var8.bV) {
               var6.add(var8);
            }
         }
      }

      if(this.c) {
         if(var1.cO != null) {
            var6.add(var1.cO);
         } else if(var1.cN != null) {
            var6.add(var1.cO);
         }
      }

      am var15;
      if(this.f != null) {
         var15 = this.f.readUnit(var1);
         if(var15 != null && !var15.bV) {
            var6.add(var15);
         }
      }

      if(this.d > 0.0F) {
         u.b = this.d * this.d;
         u.a = this.g;
         u.c = true;
         u.d = this.e;
         u.e = var6;
         com.corrodinggames.rts.gameFramework.l var16 = com.corrodinggames.rts.gameFramework.l.B();
         var16.cc.a(var1.eo, var1.ep, this.d, var1, 0.0F, u);
      }

      int var17;
      if(this.g != null) {
         for(var17 = var6.size() - 1; var17 >= 0; --var17) {
            var8 = (am)var6.get(var17);
            if(!com.corrodinggames.rts.game.units.custom.g.a(this.g, var8.de())) {
               var6.remove(var17);
            }
         }
      }

      if(this.h) {
         for(var17 = var6.size() - 1; var17 >= 0; --var17) {
            var8 = (am)var6.get(var17);
            if(!this.a.b(var8)) {
               var6.remove(var17);
            }
         }
      }

      if(this.i != null) {
         for(var17 = var6.size() - 1; var17 >= 0; --var17) {
            var8 = (am)var6.get(var17);
            if(this.i.b(var8)) {
               var6.remove(var17);
            }
         }
      }

      am var9;
      if(this.j != null) {
         var15 = this.j.get((y)var1);

         for(int var18 = var6.size() - 1; var18 >= 0; --var18) {
            var9 = (am)var6.get(var18);
            if(var9.bu != var15) {
               var6.remove(var18);
            }
         }
      }

      if(this.n != null) {
         var6 = new com.corrodinggames.rts.gameFramework.utility.m(var6);
      }

      boolean var20 = false;
      boolean var19 = false;
      var9 = null;
      int var10 = 0;
      int var11 = 0;

      for(int var12 = 0; var12 < var6.size(); ++var12) {
         am var13 = (am)var6.get(var12);
         if(var9 == null) {
            var9 = var13;
         }

         if(this.s) {
            boolean var14 = this.a.a(var13, (am)var1);
            if(!var14) {
               continue;
            }
         } else {
            if(!this.r) {
               this.a.a(var13);
            }

            if(!this.q) {
               this.a.h(var1);
            }
         }

         if(this.n != null && var13 != null) {
            PointF var21 = new PointF(var13.eo, var13.ep);
            this.n.a(var1, var21, var13, var5 + 1, var11);
            ++var11;
         }

         var20 = true;
         ++var10;
         if(var10 >= this.k) {
            var19 = true;
            break;
         }
      }

      if(var9 != null) {
         if(this.o) {
            var1.bu = var9;
         }

         if(this.p) {
            var1.bv = var9;
         }
      }

      if(var20) {
         if(this.l != null) {
            this.l.a(var1, var3, var4, var5 + 1, 0);
         }
      } else if(this.m != null) {
         this.m.a(var1, var3, var4, var5 + 1, 0);
      }

      var6.clear();
      return true;
   }

}
