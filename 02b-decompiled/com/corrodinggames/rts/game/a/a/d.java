package com.corrodinggames.rts.game.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.a.f;
import com.corrodinggames.rts.game.a.a.b;
import com.corrodinggames.rts.game.a.a.c;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.a.e;
import com.corrodinggames.rts.game.units.d.l;

public class d extends c {

   public final boolean b = true;
   static final g c = g.c("nukeLauncher");


   public b a() {
      return b.b;
   }

   public boolean c(com.corrodinggames.rts.game.a.a var1, y var2) {
      return this.a(var2);
   }

   public PointF d(com.corrodinggames.rts.game.a.a var1, y var2) {
      return var1.at();
   }

   public void e(com.corrodinggames.rts.game.a.a var1, y var2) {
      s var3 = f.a(var1, var2, e.f);
      if(var3 != null) {
         if(var3.b((am)var2) && var3.a(var2, false)) {
            PointF var4 = this.d(var1, var2);
            if(var4 != null) {
               var1.c("nuke: launching at:" + var4.a + ", " + var4.b);
               var1.a(var2, var3, var4, (am)null);
            } else {
               var1.c("nuke: no target");
            }
         } else {
            var1.c("nuke: not ready");
         }
      }

   }

   public void f(com.corrodinggames.rts.game.a.a var1, y var2) {
      if(var2 instanceof l && ((l)var2).dy()) {
         s var3 = f.a(var1, var2, e.g);
         if(var3 != null && var1.a(var3.B(), (am)var2)) {
            var1.c("ai nuke building");
            var1.a(var2, var3);
         }
      }

   }

   public boolean a(y var1) {
      return f.a(var1, c);
   }

   public void b(float var1, com.corrodinggames.rts.game.a.a var2) {
      am[] var3 = this.a.a();
      int var4 = 0;

      for(int var5 = this.a.size(); var4 < var5; ++var4) {
         y var6 = (y)var3[var4];
         this.f(var2, var6);
         this.e(var2, var6);
      }

   }

}
