package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.h;

public class i extends ae {

   int a = -99;
   boolean b;


   public i(String var1, boolean var2) {
      super(var1);
   }

   public boolean a() {
      return this.b;
   }

   public boolean b() {
      boolean var1 = false;
      int var2 = -16711936;
      if(var2 != this.a) {
         this.a("teamColor", var2);
         var1 = true;
         this.a = var2;
      }

      return var1;
   }

   public boolean a(Paint var1, e var2) {
      boolean var3 = false;
      if(var2 instanceof h) {
         h var4 = (h)var2;
         if(var4.D != this.a) {
            this.a("teamColor", var4.D);
            var3 = true;
            this.a = var4.D;
         }
      }

      super.a(var1, var2);
      return var3;
   }

   public void c() {
      super.c();
   }
}
