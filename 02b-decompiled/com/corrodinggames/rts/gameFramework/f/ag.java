package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.f.ai;

public class ag extends ai {

   public Paint a;
   public int b;
   // $FF: synthetic field
   final ae c;


   ag(ae var1, String var2, Paint var3) {
      super(var1, var2);
      this.c = var1;
      this.b = 0;
      this.a = var3;
   }

   ag(ae var1, String var2, Paint var3, int var4) {
      super(var1, var2);
      this.c = var1;
      this.b = 0;
      this.a = var3;
      this.b = var4;
   }

   public Paint b(Paint var1) {
      if(this.a == null) {
         if(this.b != 0) {
            ae.f.a(var1);
            ae.f.b(this.b);
            return ae.f;
         } else {
            return var1;
         }
      } else if(this.b != 0) {
         ae.f.a(this.a);
         ae.f.b(this.b);
         return ae.f;
      } else {
         return this.a;
      }
   }

   public ag a(String var1) {
      ag var2 = new ag(this.c, var1, this.a, this.b);
      return var2;
   }

   // $FF: synthetic method
   public ai b(String var1) {
      return this.a(var1);
   }
}
