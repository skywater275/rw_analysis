package com.corrodinggames.rts.game.a.a;

import com.corrodinggames.rts.game.a.a.a;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.u;
import java.util.Iterator;

public abstract class c extends a {

   u a = new u();


   public void a(k var1) {
      super.a(var1);
      int var2 = var1.f();

      for(int var3 = 0; var3 < var2; ++var3) {
         y var4 = var1.p();
         if(var4 != null) {
            this.a.a((am)var4);
         }
      }

   }

   public void a(as var1) {
      super.a(var1);
      int var2 = this.a.size();
      var1.a(var2);
      Iterator var3 = this.a.iterator();

      while(var3.hasNext()) {
         y var4 = (y)var3.next();
         var1.a(var4);
      }

   }

   public abstract boolean c(com.corrodinggames.rts.game.a.a var1, y var2);

   public void a(com.corrodinggames.rts.game.a.a var1, y var2) {
      if(this.c(var1, var2) && !this.a.contains(var2)) {
         this.a.a((am)var2);
      }

   }

   public void b(com.corrodinggames.rts.game.a.a var1, y var2) {}
}
