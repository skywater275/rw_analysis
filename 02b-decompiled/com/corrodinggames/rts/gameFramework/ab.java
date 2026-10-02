package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.aa;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import java.util.Iterator;

public class ab {

   com.corrodinggames.rts.gameFramework.utility.m a;
   boolean b;
   float c;
   float d;
   int e;
   boolean f;
   public com.corrodinggames.rts.gameFramework.utility.m g;
   // $FF: synthetic field
   final aa h;


   public ab(aa var1) {
      this.h = var1;
      this.a = new com.corrodinggames.rts.gameFramework.utility.m();
   }

   public void a(com.corrodinggames.rts.game.units.y var1, com.corrodinggames.rts.game.units.au var2) {
      var2.i = this;
      this.f = var2.j;
   }

   public void a(com.corrodinggames.rts.game.units.au var1) {
      Iterator var2 = this.a.iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var2.next();
         if(!var3.bV) {
            com.corrodinggames.rts.game.units.au var4 = var3.ar();
            if(var4 != null && var4.b(var1)) {
               var3.ay();
            }
         }
      }

   }

   public void a() {
      this.a.clear();
      com.corrodinggames.rts.game.units.am[] var1 = com.corrodinggames.rts.game.units.am.bE.a();
      int var2 = 0;

      for(int var3 = com.corrodinggames.rts.game.units.am.bE.size(); var2 < var3; ++var2) {
         com.corrodinggames.rts.game.units.am var4 = var1[var2];
         if(var4 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var5 = (com.corrodinggames.rts.game.units.y)var4;
            if(var5.I()) {
               com.corrodinggames.rts.game.units.au var6 = var5.ar();
               if(var6 != null && var6.i == this && var5.bg()) {
                  this.a.add(var5);
                  this.c = var6.g();
                  this.d = var6.h();
               }
            }
         }
      }

   }

   public void a(com.corrodinggames.rts.game.units.y var1) {
      var1.ac = this.e;
      com.corrodinggames.rts.game.units.au var2 = var1.ar();
      if(var2 != null) {
         var2.i = this;
      }

   }

   public void b() {
      long var1 = br.a();
      this.c();
   }

   public com.corrodinggames.rts.game.units.y a(com.corrodinggames.rts.gameFramework.utility.m var1, float var2, float var3, boolean var4) {
      float var5 = -1.0F;
      com.corrodinggames.rts.game.units.y var6 = null;
      Iterator var7 = var1.iterator();

      while(var7.hasNext()) {
         com.corrodinggames.rts.game.units.y var8 = (com.corrodinggames.rts.game.units.y)var7.next();
         if(var4 || var8.ad == null && !var8.ae) {
            float var9 = f.b(var2, var3, var8.eo, var8.ep);
            if(var8.af) {
               var9 -= 160.0F;
            }

            if(var5 == -1.0F || var9 < var5) {
               var5 = var9;
               var6 = var8;
            }
         }
      }

      return var6;
   }

   public com.corrodinggames.rts.gameFramework.utility.m a(float var1, float var2, boolean var3) {
      com.corrodinggames.rts.gameFramework.utility.m var4 = new com.corrodinggames.rts.gameFramework.utility.m(1);
      com.corrodinggames.rts.gameFramework.utility.m var5 = new com.corrodinggames.rts.gameFramework.utility.m();
      var5.clear();
      var5.addAll(this.a);

      while(true) {
         com.corrodinggames.rts.game.units.y var6 = this.a(var5, var1, var2, true);
         if(var6 == null) {
            return var4;
         }

         var4.add(var6);
         var5.remove(var6);
         com.corrodinggames.rts.gameFramework.utility.m var7 = this.a(var5, var6, true, var3);
         var5.removeAll(var7);
      }
   }

   public com.corrodinggames.rts.gameFramework.utility.m a(com.corrodinggames.rts.gameFramework.utility.m var1, com.corrodinggames.rts.game.units.y var2, boolean var3, boolean var4) {
      com.corrodinggames.rts.gameFramework.utility.m var5 = new com.corrodinggames.rts.gameFramework.utility.m(1);
      var5.clear();
      int var6 = 0;
      boolean var7 = false;
      boolean var8 = true;
      Object[] var9 = var1.a();
      int var10 = 0;

      int var11;
      for(var11 = var1.size(); var10 < var11; ++var10) {
         com.corrodinggames.rts.game.units.y var12 = (com.corrodinggames.rts.game.units.y)var9[var10];
         var12.ap = false;
      }

      for(var10 = 0; var10 <= 2; ++var10) {
         var11 = 0;

         for(int var15 = var1.size(); var11 < var15; ++var11) {
            com.corrodinggames.rts.game.units.y var13 = (com.corrodinggames.rts.game.units.y)var9[var11];
            if(!var13.ap && var13 != var2 && (var3 || var13.ad == null && !var13.ae) && var13.h() == var2.h()) {
               float var14 = f.a(var13.eo, var13.ep, var2.eo, var2.ep);
               if((var10 != 0 || var14 <= 3600.0F) && (var10 != 1 || var14 <= 14400.0F) && (var4 && var14 < 160000.0F || var14 < 40000.0F && var6 < 25) && (var4 || f.c(var13.z() - var2.z()) < 0.4F)) {
                  var13.ap = true;
                  var5.add(var13);
                  ++var6;
               }
            }
         }
      }

      return var5;
   }

   public void c() {
      l var1 = l.B();
      float var2 = 0.0F;
      float var3 = 0.0F;
      float var4 = 0.0F;
      float var5 = 0.0F;
      long var6 = br.a();
      this.a();
      this.h.b.a(0.0F, 0.0F);
      Iterator var8 = this.a.iterator();

      while(var8.hasNext()) {
         com.corrodinggames.rts.game.units.y var9 = (com.corrodinggames.rts.game.units.y)var8.next();
         this.h.b.b(var9.eo, var9.ep);
      }

      this.h.b.a(this.h.b.a / (float)this.a.size(), this.h.b.b / (float)this.a.size());
      float var24 = f.d(this.h.b.a, this.h.b.b, this.c, this.d);

      com.corrodinggames.rts.game.units.y var10;
      for(Iterator var25 = this.a.iterator(); var25.hasNext(); var10.ac = this.e) {
         var10 = (com.corrodinggames.rts.game.units.y)var25.next();
         if(var10.ah > 1) {
            var10.af = var10.ae;
         } else {
            var10.af = false;
         }

         if(var10.af && var10.ah > 7) {
            float var11 = f.c(var10.am, var24, 360.0F);
            if(f.c(var11) > 80.0F) {
               var10.af = false;
            }
         }

         var10.aB();
         var10.ah = 0;
         var10.an = var1.by;
      }

      int var26 = 0;

      while(true) {
         var10 = null;
         long var27 = br.a();
         var10 = this.a(this.a, this.c, this.d, false);
         if(var10 == null) {
            return;
         }

         var10.ae = true;
         ab var13 = null;
         if(var26 > 0) {
            var13 = this.h.b();
         }

         if(var13 != null) {
            var13.g = this.g;
            var13.a(var10);
         }

         com.corrodinggames.rts.gameFramework.utility.m var14 = this.a(this.a, var10, false, this.f);
         int var15 = 0;
         float var16 = 0.0F;

         for(Iterator var17 = var14.iterator(); var17.hasNext(); ++var15) {
            com.corrodinggames.rts.game.units.y var18 = (com.corrodinggames.rts.game.units.y)var17.next();
            if(var18.cj > var16) {
               var16 = var18.cj;
            }

            var18.a(var10);
            if(var13 != null) {
               var13.a(var18);
            }
         }

         if(var10 != null) {
            var10.ah = (short)(var15 + 1);
         }

         com.corrodinggames.rts.gameFramework.utility.m var28 = new com.corrodinggames.rts.gameFramework.utility.m();
         Object[] var29 = this.a.a();
         int var19 = 0;

         for(int var20 = this.a.size(); var19 < var20; ++var19) {
            com.corrodinggames.rts.game.units.y var21 = (com.corrodinggames.rts.game.units.y)var29[var19];
            if(var21.ad == var10) {
               var28.add(var21);
            }
         }

         com.corrodinggames.rts.gameFramework.utility.m var30 = this.h.a(var15, var16, var24);
         long var31 = br.a();
         this.h.a(var28, var10, var30, var24, var15);
         long var22 = br.a();
         this.h.a(var28, var10);
         ++var26;
      }
   }
}
