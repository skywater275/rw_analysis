package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;
import java.util.Iterator;

public class c {

   static final boolean a = false;
   public ArrayList b = new ArrayList();
   public ArrayList c = new ArrayList();
   public ArrayList d = new ArrayList();
   static int e;


   public static strictfp void a(String var0) {
      ++e;
      if(e == 5) {
         l.e("(Rate Limiting...)");
      }

      if(e < 5) {
         l.e(var0);
      }
   }

   public strictfp void a() {
      this.b.clear();
      this.c.clear();
      this.d.clear();
   }

   public strictfp e b() {
      e var1 = new e(this);
      if(a) {
         l.e("Tracing source");
         var1.b = l.a((Throwable)(new Exception("Test")));
      }

      return var1;
   }

   public strictfp e a(com.corrodinggames.rts.game.n var1) {
      return this.b(var1);
   }

   public strictfp e b(com.corrodinggames.rts.game.n var1) {
      if(var1 == null) {
         throw new RuntimeException("team==null");
      } else {
         l var2 = l.B();
         e var3 = new e(this);
         var3.i = var1;
         var3.d = var2.by;
         if(a) {
            l.e("Tracing source");
            var3.b = l.a((Throwable)(new Exception("Test")));
         }

         if(!var2.bX.B) {
            if(!var3.l()) {
               l.b("Command failed prepareAndCheckOnServer()");
            }

            this.b.add(var3);
         } else {
            this.d.add(var3);
         }

         return var3;
      }
   }

   public strictfp void c() {
      l var1 = l.B();
      e = 0;
      if(!var1.bX.B) {
         this.d();
      } else {
         this.e();
      }

   }

   public strictfp void d() {
      l var1 = l.B();
      int var2 = var1.bx;
      int var3 = 0;

      for(Iterator var4 = this.b.iterator(); var4.hasNext(); ++var3) {
         e var5 = (e)var4.next();
         var1.cb.a(var5, var2);
         var5.k();
      }

      this.b.clear();
      if(var3 > 0) {
         var1.cb.c();
      }

   }

   public strictfp void e() {
      l var1 = l.B();
      int var2 = var1.bx;
      int var3 = 0;
      Iterator var4 = this.b.iterator();

      while(var4.hasNext()) {
         e var5 = (e)var4.next();
         if(var5.c == var2) {
            var1.cb.a(var5, var2);
            var5.k();
            var4.remove();
            ++var3;
         }
      }

      if(var3 > 0) {
         var1.cb.c();
      }

   }

}
