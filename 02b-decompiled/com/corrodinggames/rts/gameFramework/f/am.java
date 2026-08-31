package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.f.a;
import java.util.ArrayList;
import java.util.Iterator;

public class am extends bq {

   private final a i;
   public ArrayList a = new ArrayList();
   public float b;
   public long c;
   public float d;
   public float e;
   public float f;
   public boolean g;
   public boolean h;


   public am(a var1, boolean var2) {
      this.i = var1;
      this.g = var2;
   }

   public void a() {
      com.corrodinggames.rts.game.units.am var1 = null;
      Iterator var2 = this.a.iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.game.units.am var3 = (com.corrodinggames.rts.game.units.am)var2.next();
         if(!var3.bV && var3.cN == null) {
            boolean var4 = this.i.a.j(var3);
            if(var4 && var3.cf()) {
               var1 = var3;
            }
         }
      }

      if(this.c > com.corrodinggames.rts.gameFramework.l.V() - 700L && var1 != null) {
         this.i.b.b(var1.eo, var1.ep);
      }

      this.c = com.corrodinggames.rts.gameFramework.l.V();
   }

   public void b() {
      this.a.clear();
   }

   public void c() {
      Iterator var1 = com.corrodinggames.rts.gameFramework.w.er.iterator();

      while(var1.hasNext()) {
         com.corrodinggames.rts.gameFramework.w var2 = (com.corrodinggames.rts.gameFramework.w)var1.next();
         if(var2 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var2;
            if(var3.cG && !this.a.contains(var3)) {
               this.a.add(var3);
            }
         }
      }

   }

   public void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      this.d();
      var1.a(this.b);
      var1.a(this.c);
      int var2 = this.a.size();
      var1.a(var2);
      Iterator var3 = this.a.iterator();

      while(var3.hasNext()) {
         com.corrodinggames.rts.game.units.am var4 = (com.corrodinggames.rts.game.units.am)var3.next();
         var1.a(var4);
      }

      var1.c(0);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.b = var1.g();
      this.c = var1.i();
      this.a.clear();
      int var2 = var1.f();

      for(int var3 = 0; var3 < var2; ++var3) {
         com.corrodinggames.rts.game.units.am var4 = var1.o();
         if(var4 != null) {
            this.a.add(var4);
         }
      }

      var1.d();
   }

   public void d() {
      if(this.a.size() != 0) {
         Iterator var1 = this.a.iterator();

         while(var1.hasNext()) {
            com.corrodinggames.rts.game.units.am var2 = (com.corrodinggames.rts.game.units.am)var1.next();
            if(var2.bV) {
               var1.remove();
            }
         }

      }
   }

   public void e() {
      if(this.a.size() != 0) {
         ArrayList var1 = new ArrayList();
         Iterator var2 = this.a.iterator();

         while(var2.hasNext()) {
            com.corrodinggames.rts.game.units.am var3 = (com.corrodinggames.rts.game.units.am)var2.next();
            com.corrodinggames.rts.game.units.am var4 = com.corrodinggames.rts.gameFramework.w.a(var3.eh, true);
            if(var4 != null && !var4.bV) {
               var1.add(var4);
            }
         }

         this.a = var1;
      }
   }
}
