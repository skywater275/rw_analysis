package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.f.af;

public class ai extends af {

   String d;
   // $FF: synthetic field
   final ae e;


   public int a(Paint var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      Paint var3 = this.b(var1);
      int var4 = var2.bO.b(this.d, var3);
      if(com.corrodinggames.rts.gameFramework.l.at()) {
         ;
      }

      return var4;
   }

   public Paint b(Paint var1) {
      return var1;
   }

   ai(ae var1, String var2) {
      this.e = var1;
      this.d = var2;
   }

   public ai b(String var1) {
      ai var2 = new ai(this.e, var1);
      return var2;
   }
}
