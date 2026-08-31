package com.corrodinggames.rts.game.units.custom;


class aa {

   public long a;
   public String b;


   public strictfp aa(String var1) {
      this.b = var1;
      this.a = this.a(true);
   }

   public strictfp long a(boolean var1) {
      if(com.corrodinggames.rts.gameFramework.l.au()) {
         return 0L;
      } else {
         long var2 = com.corrodinggames.rts.gameFramework.j.a(this.b, var1);
         if(var1 && var2 == 0L) {
            com.corrodinggames.rts.gameFramework.l.e("Failed to watch: " + this.b);
         }

         return var2;
      }
   }
}
