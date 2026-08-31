package com.corrodinggames.rts.gameFramework.a;

import com.corrodinggames.rts.gameFramework.a.h;

public abstract class i {

   public float d = 1.0F;
   public String e;
   public boolean f = false;
   public boolean g;


   public i(String var1, h var2) {
      this.e = com.corrodinggames.rts.gameFramework.f.g(var1);
      if(var2 != null) {
         var2.h.put(this.e, this);
      }

   }

   public abstract void a(float var1, float var2, int var3, int var4, float var5);

   public abstract int a();
}
