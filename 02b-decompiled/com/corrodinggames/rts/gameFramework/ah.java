package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.ac;
import com.corrodinggames.rts.gameFramework.af;

public class ah extends af {

   int e = -1;
   int f = -1;
   boolean g;
   int h = -1;
   float i;
   boolean j = false;


   public boolean a() {
      if(this.b()) {
         if(!this.c) {
            this.c = true;
            return true;
         } else {
            return false;
         }
      } else {
         if(this.c) {
            this.c = false;
         }

         return false;
      }
   }

   public boolean b() {
      return this.e() > 0.5F;
   }

   public float e() {
      return this.a(false);
   }

   public float a(boolean var1) {
      float var2;
      if(this.h != -1) {
         var2 = ac.b.a(this.h, this.e)?0.0F:1.0F;
      } else {
         var2 = ac.b.b(this.e, this.f);
         var2 = this.g?-var2:var2;
      }

      if(var1) {
         return var2;
      } else {
         if(!this.j && Math.abs(var2 - this.i) > 0.001F) {
            this.j = true;
         }

         return !this.j?0.0F:var2;
      }
   }

   public String c() {
      return "controller";
   }

   public boolean d() {
      return false;
   }
}
