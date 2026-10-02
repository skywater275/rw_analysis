package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.bo;
import java.util.ArrayList;

public class e {

   public String a;
   public String b;
   public float c;
   public float d;


   public e(String var1, String var2) {
      this.a = var1;
      this.b = var2;
   }

   public e(String var1, float var2) {
      this.a = var1;
      this.c = var2;
      this.b = null;
   }

   public static ArrayList a() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      ArrayList var1 = new ArrayList();
      bo var2 = null;
      if(var0.bs != null) {
         var2 = var0.bY.a(var0.bs);
      }

      if(var2 != null) {
         e var3;
         if(var0.ce != null && var0.ce.k) {
            var3 = new e("Lasted till wave: " + var0.ce.r, "");
            var1.add(var3);
            if(!var0.ce.l) {
               var3 = new e("Wave difficulty: " + var0.bX.c(var0.ce.y), "");
               var1.add(var3);
            }
         }

         var3 = new e("Game Time", com.corrodinggames.rts.gameFramework.f.a((long)(var0.by / 1000)));
         var1.add(var3);
         var3 = new e("=============================", "");
         var1.add(var3);
         var3 = new e("Units Killed", (float)var2.c);
         var1.add(var3);
         var3 = new e("Buildings Killed", (float)var2.d);
         var1.add(var3);
         var3 = new e("Experimentals Killed", (float)var2.e);
         var1.add(var3);
         var3 = new e("=============================", "");
         var1.add(var3);
         var3 = new e("Units Lost", (float)var2.f);
         var1.add(var3);
         var3 = new e("Buildings Lost", (float)var2.g);
         var1.add(var3);
         var3 = new e("Experimentals Lost", (float)var2.h);
         var1.add(var3);
         var3 = new e("=============================", "");
         var1.add(var3);
      }

      return var1;
   }
}
