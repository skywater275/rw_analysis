package com.corrodinggames.rts.game.units.custom.logicBooleans;

import android.graphics.PointF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class UnitReference$ThisActionTargetReference extends UnitReference {

   public am getSingleRaw(y var1) {
      am var2 = j.dN;
      if(var2 != null) {
         return var2;
      } else {
         PointF var3 = j.dM;
         if(var3 != null) {
            y var4 = n.i.t;
            var4.cg = 0.0F;
            var4.eo = var3.a;
            var4.ep = var3.b;
            var4.eq = 0.0F;
            return var4;
         } else {
            return null;
         }
      }
   }

   public String getClassDebugName() {
      return "ThisActionTarget";
   }
}
