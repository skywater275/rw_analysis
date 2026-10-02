package com.corrodinggames.rts.gameFramework.g;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.g.a$1;
import com.corrodinggames.rts.gameFramework.g.b;
import com.corrodinggames.rts.gameFramework.g.c;
import com.corrodinggames.rts.gameFramework.g.d;
import com.corrodinggames.rts.gameFramework.g.e;
import com.corrodinggames.rts.gameFramework.g.f;
import java.util.ArrayList;
import java.util.Iterator;

public class a {

   private final f a;
   private final c b;
   private final ArrayList c;


   public a() {
      this(f.a, c.a);
   }

   public a(f var1, c var2) {
      this.c = new ArrayList();
      this.a = var1;
      this.b = var2;
   }

   public void a() {
      if(this.a != f.a) {
         ArrayList var1 = n.b(false);
         if(this.b == c.a) {
            Iterator var2 = var1.iterator();

            while(var2.hasNext()) {
               n var3 = (n)var2.next();
               this.c.add(new e(var3));
            }
         } else if(this.b == c.b) {
            ArrayList var9 = n.f();
            Iterator var11 = var9.iterator();

            while(var11.hasNext()) {
               Integer var4 = (Integer)var11.next();
               ArrayList var5 = new ArrayList();
               Iterator var6 = var1.iterator();

               while(var6.hasNext()) {
                  n var7 = (n)var6.next();
                  if(var7.r == var4.intValue()) {
                     var5.add(var7);
                  }
               }

               this.c.add(new b(var4.intValue(), var5));
            }
         } else if(this.b == c.c) {
            int var10 = 0;
            ArrayList var12 = n.f();
            Iterator var13 = var12.iterator();

            n var8;
            Integer var14;
            ArrayList var16;
            Iterator var17;
            while(var13.hasNext()) {
               var14 = (Integer)var13.next();
               var16 = new ArrayList();
               var17 = var1.iterator();

               while(var17.hasNext()) {
                  var8 = (n)var17.next();
                  if(var8.r == var14.intValue()) {
                     var16.add(var8);
                  }
               }

               if(var10 < var16.size()) {
                  var10 = var16.size();
               }
            }

            if(var10 <= 1) {
               var13 = var1.iterator();

               while(var13.hasNext()) {
                  n var15 = (n)var13.next();
                  this.c.add(new e(var15));
               }
            } else {
               var13 = var12.iterator();

               while(var13.hasNext()) {
                  var14 = (Integer)var13.next();
                  var16 = new ArrayList();
                  var17 = var1.iterator();

                  while(var17.hasNext()) {
                     var8 = (n)var17.next();
                     if(var8.r == var14.intValue()) {
                        var16.add(var8);
                     }
                  }

                  this.c.add(new b(var14.intValue(), var16));
                  var17 = var16.iterator();

                  while(var17.hasNext()) {
                     var8 = (n)var17.next();
                     this.c.add(new e(var8));
                  }
               }
            }
         }

         this.b();
      }
   }

   public void b() {
      Iterator var1 = this.c.iterator();

      while(var1.hasNext()) {
         d var2 = (d)var1.next();
         var2.b(this.a);
      }

   }

   public void c() {
      int var1 = this.a.ordinal() + 1;
      if(var1 >= f.values().length) {
         var1 = 0;
      }

      f var2 = f.values()[var1];
      c var3 = c.c;
      l var4 = l.B();
      var4.a(var2, var3);
   }

   public String a(d var1) {
      return this.b == c.c && var1 instanceof e?"   " + a(this.a, d.b(var1)):a(this.a, d.b(var1));
   }

   public static String a(f var0, int var1) {
      switch(a$1.a[var0.ordinal()]) {
      case 1:
         return "" + var1;
      case 2:
         return "+" + com.corrodinggames.rts.game.units.custom.e.a.c.D.a((double)var1, true);
      default:
         return com.corrodinggames.rts.game.units.custom.e.a.c.D.a((double)var1, true);
      }
   }

   public ArrayList d() {
      return this.c;
   }

   public f e() {
      return this.a;
   }

   public c f() {
      return this.b;
   }
}
