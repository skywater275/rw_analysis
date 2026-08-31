package com.corrodinggames.rts.game.units.custom.anim;  // v19.112d 补建 (02b e/ 直译)

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.anim.a;
import com.corrodinggames.rts.game.units.custom.anim.e;
import com.corrodinggames.rts.game.units.custom.anim.f;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class c {

   public final CustomArrayList a = new CustomArrayList();


   public void a(a var1) {
      if(!this.a.contains(var1)) {
         this.a.add(var1);
      }

   }

   public void a(EffectManager var1, UnitInstance var2, double var3) {
      int var5 = var1.b.a;
      Object[] var6 = var1.b.a();

      for(int i = 0; i < var5; ++i) {
         e var8 = (e)var6[i];
         double var9 = var8.a.a(var2);
         if(var9 < var8.b * var3) {
            this.a(var8.a);
         }
      }

   }

   public void a(com.corrodinggames.rts.game.units.custom.resources.CustomActionBase var1, UnitInstance var2, double var3) {
      if(!var1.k.c()) {
         this.a(var1.k, var2, var3);
      }

      if(var1.b > 0 && var2.player.credits < (double)var1.b * var3) {
         this.a(com.corrodinggames.rts.game.units.custom.anim.a.D);
      }

   }

   public boolean a(EffectManager var1) {
      int var2 = var1.b.a;
      Object[] var3 = var1.b.a();

      for(int i = 0; i < var2; ++i) {
         e var5 = (e)var3[i];
         if(this.a.contains(var5.a)) {
            return true;
         }
      }

      return false;
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.resources.CustomActionBase var1) {
      return var1.b > 0 && this.a.contains(com.corrodinggames.rts.game.units.custom.anim.a.D)?true:!var1.k.c() && this.a(var1.k);
   }

   public void a() {
      this.a.clear();
   }
}
