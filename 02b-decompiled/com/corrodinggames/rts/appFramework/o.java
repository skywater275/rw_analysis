package com.corrodinggames.rts.appFramework;


public class o {

   String a;
   int b = 0;
   int c = 0;
   com.corrodinggames.rts.game.n d = null;
   com.corrodinggames.rts.game.n e = null;
   int f;
   int g;


   o(String var1) {
      this.a = var1;
   }

   public void a(com.corrodinggames.rts.game.n var1, int var2) {
      if(var2 >= this.b && this.d != null) {
         if(var2 == this.b) {
            ++this.f;
         }
      } else {
         this.b = var2;
         this.d = var1;
         this.f = 1;
      }

      if(var2 <= this.c && this.e != null) {
         if(var2 == this.c) {
            ++this.g;
         }
      } else {
         this.c = var2;
         this.e = var1;
         this.g = 1;
      }

   }

   public boolean a() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.b == this.c) {
         return false;
      } else if(this.d == null && this.e == null) {
         return false;
      } else {
         String var2;
         if(this.g == 1) {
            var2 = "Warning: Uneven map - Player " + (this.e.k + 1) + " on team " + this.e.h() + ": " + this.a + " is " + this.c + " vs " + this.b;
            var1.bS.h.a((String)null, var2);
            return true;
         } else {
            var2 = "Warning: Uneven map - " + this.g + " players including player " + (this.e.k + 1) + " on team " + (this.e.r + 1) + ": " + this.a + " is " + this.c + " vs " + this.b;
            var1.bS.h.a((String)null, var2);
            return true;
         }
      }
   }
}
