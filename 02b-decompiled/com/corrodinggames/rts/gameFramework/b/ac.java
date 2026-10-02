package com.corrodinggames.rts.gameFramework.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import com.corrodinggames.rts.gameFramework.b.ad;
import com.corrodinggames.rts.gameFramework.b.ae;
import com.corrodinggames.rts.gameFramework.b.b;
import com.corrodinggames.rts.gameFramework.b.k;
import java.util.ArrayList;
import java.util.HashMap;

public class ac {

   public k a;
   public ad b;
   public static Bitmap c = Bitmap.a(64, 64, Bitmap$Config.d);
   HashMap d = new HashMap();
   ArrayList e = new ArrayList();
   int f = 0;
   boolean g;
   boolean h = false;
   int i = 0;
   int j = 0;
   int k = 0;
   int l = 1;


   public ac(k var1, int var2, int var3) {
      this.a = var1;
      this.b = new ad(var1, var2, var3);
   }

   public b a(Bitmap var1) {
      ae var2 = (ae)this.d.get(var1);
      if(var2 != null) {
         if(this.h) {
            this.e.add(var1);
         }

         return var2;
      } else {
         int var3 = var1.b();
         int var4 = var1.c();
         int var5 = this.b.b();
         int var6 = this.b.c();
         if(this.i + var3 > var5) {
            this.i = 0;
            this.j += this.k + this.l;
            this.k = 0;
         }

         if(this.j + var4 > var6) {
            if(!this.g) {
               this.g = true;
            }

            return null;
         } else {
            var2 = new ae();
            var2.a = this.b.a;
            var2.l = this.b;
            int var7 = this.i;
            int var8 = this.j;
            this.i += var3 + this.l;
            if(this.k < var4) {
               this.k = var4;
            }

            this.b.a(this.a, var1, var7, var8);
            var2.o = var7;
            var2.p = var8;
            var2.m = (float)var7 / (float)this.b.e;
            var2.n = (float)var8 / (float)this.b.f;
            var2.e = this.b.e;
            var2.f = this.b.f;
            var2.g = this.b.g;
            var2.h = this.b.h;
            var2.c = var3;
            var2.d = var4;
            ++this.f;
            this.d.put(var1, var2);
            return var2;
         }
      }
   }

   public void b(Bitmap var1) {
      ae var2 = (ae)this.d.get(var1);
      if(var2 != null) {
         this.d.remove(var1);
      }

   }

}
