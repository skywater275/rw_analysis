package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;

public class bt {

   boolean a = true;
   int b;
   double c;
   double d;
   long e;
   String f;


   public bt(String var1) {
      this.f = var1;
   }

   public bt(String var1, boolean var2) {
      this.f = var1;
      this.a = var2;
   }

   public void a() {
      if(this.a) {
         if(this.e != 0L) {
            this.e = Long.MIN_VALUE;
         } else {
            this.e = br.a();
         }

      }
   }

   public void b() {
      if(this.a) {
         long var1 = br.a();
         double var3 = br.a(this.e, var1);
         this.c += var3;
         ++this.b;
         if(var3 > this.d) {
            this.d = var3;
         }

         this.e = 0L;
      }
   }

   public String c() {
      if(!this.a) {
         return "{ Not enabled }";
      } else {
         String var1 = "{ ";
         if(this.b > 0) {
            var1 = var1 + "#" + this.b + " = ";
            var1 = var1 + "peak:" + f.a(this.d, 2) + "ms ";
            var1 = var1 + "avg:" + f.a(this.c / (double)this.b, 2) + "ms ";
            var1 = var1 + "total:" + f.a(this.c, 2) + "ms ";
         } else {
            var1 = var1 + "#0 = NA";
         }

         var1 = var1 + "}";
         return var1;
      }
   }

   public void d() {
      if(this.a) {
         this.b();
         this.e();
      }
   }

   public void e() {
      if(this.a) {
         if(this.b > 0) {
            l.e(l.a(this.f + " - " + this.c(), "[36m"));
            this.f();
         }

      }
   }

   public void f() {
      this.b = 0;
      this.c = 0.0D;
      this.d = 0.0D;
   }
}
