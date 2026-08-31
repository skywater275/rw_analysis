package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.corrodinggames.rts.gameFramework.f.aq;
import com.corrodinggames.rts.gameFramework.f.ar;
import com.corrodinggames.rts.gameFramework.f.as;
import com.corrodinggames.rts.gameFramework.f.at;
import com.corrodinggames.rts.gameFramework.f.au;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ap {

   private com.corrodinggames.rts.gameFramework.l a;
   private Paint b;
   private ArrayList c = new ArrayList();


   public strictfp ap(com.corrodinggames.rts.gameFramework.l var1) {
      this.a = var1;
      this.a();
   }

   public strictfp void a() {
      this.b = new Paint();
      this.b.a(255, 255, 255, 255);
      this.b.a(true);
      this.b.c(true);
      this.b.a(Typeface.a(Typeface.c, 1));
      this.a.a(this.b, 14.0F);
   }

   public synchronized strictfp void b() {
      this.c.clear();
   }

   public synchronized strictfp void a(com.corrodinggames.rts.game.units.am var1) {
      ar var2 = new ar(var1.eo, var1.ep, var1.r());
      var2.c = com.corrodinggames.rts.gameFramework.l.V();
      this.a((au)var2);
   }

   public synchronized strictfp void b(com.corrodinggames.rts.game.units.am var1) {
      at var2 = new at(var1.eo, var1.ep, var1.r());
      var2.c = com.corrodinggames.rts.gameFramework.l.V();
      this.a((au)var2);
   }

   public synchronized strictfp void c(com.corrodinggames.rts.game.units.am var1) {
      as var2 = new as(var1.eo, var1.ep, var1.bI());
      var2.c = com.corrodinggames.rts.gameFramework.l.V();
      this.a((au)var2);
   }

   public synchronized strictfp void a(String var1) {
      aq var2 = new aq(var1);
      var2.c = com.corrodinggames.rts.gameFramework.l.V();
      this.a((au)var2);
   }

   public synchronized strictfp void a(String var1, int var2) {
      aq var3 = new aq(var1);
      var3.c = com.corrodinggames.rts.gameFramework.l.V();
      var3.d = (long)var2;
      var3.i = true;
      this.a((au)var3);
   }

   private strictfp void a(au var1) {
      boolean var2 = false;
      Iterator var3 = this.c.iterator();

      while(var3.hasNext()) {
         au var4 = (au)var3.next();
         if(var4.a(var1)) {
            var4.b(var1);
            var2 = true;
            break;
         }
      }

      if(var2) {
         Collections.sort(this.c);
      } else {
         this.c.add(0, var1);
      }

   }

   public synchronized strictfp void a(float var1) {
      this.c();
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      int var3 = (int)(var2.cm - 130.0F * var2.cj);
      byte var4 = 20;
      int var5 = (int)(20.0F * var2.cj);
      Iterator var6 = this.c.iterator();

      while(var6.hasNext()) {
         au var7 = (au)var6.next();
         String var8 = var7.a();
         if(var2.bQ.showWarLogOnScreen || var7.i) {
            if(var7.c + var7.d < System.currentTimeMillis()) {
               break;
            }

            if(var7.h) {
               this.b.a(255, 160, 160, 160);
            } else {
               this.b.a(255, 255, 255, 255);
            }

            var2.bO.a(var8, (float)var4, (float)var3, this.b);
            var3 -= var5;
         }
      }

   }

   public synchronized strictfp void c() {
      Iterator var1 = this.c.iterator();

      while(var1.hasNext()) {
         au var2 = (au)var1.next();
         if(var2.c + 20000L < System.currentTimeMillis()) {
            var1.remove();
         }
      }

   }

   public synchronized strictfp void d() {
      if(!this.c.isEmpty()) {
         Iterator var1 = this.c.iterator();

         while(var1.hasNext()) {
            au var2 = (au)var1.next();
            if(!var2.h) {
               var2.h = true;
               this.a.b(var2.e, var2.f);
               break;
            }
         }

      }
   }
}
