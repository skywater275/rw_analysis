package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.ac;
import com.corrodinggames.rts.gameFramework.af;

public class ag extends af {

   int e;


   public boolean a(af var1) {
      if(!(var1 instanceof ag)) {
         return false;
      } else {
         ag var2 = (ag)var1;
         return this.e != var2.e?false:super.a(var1);
      }
   }

   public boolean a() {
      if(ac.b.a(this.e, this.b, false)) {
         if(!this.c) {
            this.c = true;
            return true;
         } else {
            return false;
         }
      } else {
         if(ac.b.a(this.e, this.b, true)) {
            this.c = true;
         } else if(this.c) {
            this.c = false;
         }

         return false;
      }
   }

   public boolean b() {
      return ac.b.a(this.e, this.b, false);
   }

   public String c() {
      return this.e == 0?"":ac.b.c(this.e, this.b);
   }

   public boolean d() {
      return this.e == 0;
   }
}
