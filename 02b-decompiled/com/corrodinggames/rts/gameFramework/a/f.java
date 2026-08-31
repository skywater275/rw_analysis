package com.corrodinggames.rts.gameFramework.a;

import android.content.Context;
import com.corrodinggames.rts.R$raw;
import com.corrodinggames.rts.gameFramework.a.g;
import com.corrodinggames.rts.gameFramework.a.h;
import com.corrodinggames.rts.gameFramework.a.i;
import com.corrodinggames.rts.gameFramework.utility.j;

public class f extends h {

   public void a(Context var1) {}

   public i a(int var1) {
      String var2 = com.corrodinggames.rts.gameFramework.f.a(R$raw.class, var1);
      g var3 = new g(var2, this);
      return var3;
   }

   public i a(String var1, j var2, boolean var3) {
      g var4 = new g(var1, this);
      return var4;
   }

   public static i b() {
      g var0 = new g("Null (from out of memory)", (h)null);
      return var0;
   }

   public static i a(String var0) {
      g var1 = new g("Null sound - " + var0, (h)null);
      return var1;
   }
}
