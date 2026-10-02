package com.corrodinggames.rts.game.units.custom.conditions;

import com.corrodinggames.rts.game.units.custom.anim.e;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import java.util.Comparator;

class f$1 implements Comparator {

   // $FF: synthetic field
   final EffectManager a;


   f$1(EffectManager var1) {  // v19.133f5: 构造器前 strictfp 不合法
      this.a = var1;
   }

   public strictfp int a(e var1, e var2) {
      return var1.a != null && var2.a != null?Float.compare(var1.a.x, var2.a.x):0;
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((e)var1, (e)var2);
   }
}
