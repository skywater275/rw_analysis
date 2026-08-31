package com.corrodinggames.rts.game.units.custom.anim;  // v19.112d 补建 (02b e/ 直译)

import com.corrodinggames.rts.game.units.custom.anim.e;
import com.corrodinggames.rts.game.units.custom.anim.f;
import java.util.Comparator;

class f$1 implements Comparator {

   // $FF: synthetic field
   final f a;


   f$1(f var1) {
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
