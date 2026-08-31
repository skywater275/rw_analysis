package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.e;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public abstract class d {

   String b;
   public ArrayList c;
   private ArrayList a;
   // $FF: synthetic field
   final a d;


   public d(a var1, String var2) {
      this.d = var1;
      this.c = new ArrayList();
      this.a = new ArrayList();
      this.b = var2;
      var1.bq.add(this);
   }

   public boolean b(as var1) {
      Iterator var2 = this.c.iterator();

      e var3;
      do {
         if(!var2.hasNext()) {
            return false;
         }

         var3 = (e)var2.next();
      } while(var3.a != var1);

      return true;
   }

   public abstract boolean a(as var1);

   public float c(as var1) {
      return 10.0F;
   }

   public ArrayList a() {
      Collections.shuffle(this.a);
      return this.a;
   }

   public void b() {
      this.c = new ArrayList();
      float var1 = 0.0F;
      Iterator var2 = ar.ae.iterator();

      while(var2.hasNext()) {
         as var3 = (as)var2.next();
         if(this.a(var3)) {
            float var4 = this.c(var3);
            var1 += var4;
            this.c.add(new e(this, var3, var4));
         }
      }

      this.a = new ArrayList(this.c);
      if(this.c.size() == 0) {
         com.corrodinggames.rts.gameFramework.l.e("AI: rebuildUnitMix: no units in unitMix:" + this.b);
      }

   }

   public as c() {
      return this.a((ao)null, -1);
   }

   public as a(ao var1) {
      return this.a(var1, -1);
   }

   public boolean a(as var1, ao var2) {
      if(var2 == null) {
         return true;
      } else {
         ao var3 = var1.o();
         if(var3 == ao.g) {
            var3 = ao.b;
         }

         if(var3 == ao.h) {
            var3 = ao.f;
         }

         return var3 == var2;
      }
   }

   public as a(ao var1, int var2) {
      if(this.c.size() == 0) {
         com.corrodinggames.rts.gameFramework.l.e("AI: getRandomUnitType: no units in unitMix:" + this.b);
         return null;
      } else {
         float var3 = 0.0F;
         int var4 = 0;
         Iterator var5 = this.c.iterator();

         while(var5.hasNext()) {
            e var6 = (e)var5.next();
            if(this.a(var6.a, var1) && (var2 == -1 || var6.a.c() <= var2)) {
               var3 += var6.b;
               ++var4;
            }
         }

         if(var4 == 0) {
            return null;
         } else {
            float var9 = com.corrodinggames.rts.gameFramework.f.c(0.0F, var3);
            float var10 = 0.0F;
            Iterator var7 = this.c.iterator();

            while(var7.hasNext()) {
               e var8 = (e)var7.next();
               if(this.a(var8.a, var1) && (var2 == -1 || var8.a.c() <= var2)) {
                  var10 += var8.b;
                  if(var10 > var9) {
                     return var8.a;
                  }
               }
            }

            com.corrodinggames.rts.gameFramework.l.e("Did not find getRandomUnit, this should only happen very rarely, name:" + this.b + " unitMix.size:" + this.c.size() + " minPrice:" + var2 + " movementType:" + var1 + " totalUnits:" + var4);
            return ((e)this.c.get(this.c.size() - 1)).a;
         }
      }
   }
}
