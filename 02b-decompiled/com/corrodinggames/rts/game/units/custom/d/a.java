package com.corrodinggames.rts.game.units.custom.d;

import com.corrodinggames.rts.game.units.am;

public abstract class a {

   public abstract void a(am var1);

   public abstract boolean b(am var1);

   public abstract void a(am var1, double var2);

   public abstract boolean b(am var1, double var2);

   public boolean c(am var1) {
      if(this.b(var1)) {
         this.a(var1);
         return true;
      } else {
         return false;
      }
   }

   public boolean c(am var1, double var2) {
      if(this.b(var1, var2)) {
         this.a(var1, var2);
         return true;
      } else {
         return false;
      }
   }
}
