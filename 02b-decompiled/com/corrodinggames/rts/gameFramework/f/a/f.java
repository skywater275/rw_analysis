package com.corrodinggames.rts.gameFramework.f.a;

import android.graphics.Color;
import com.corrodinggames.rts.gameFramework.f.a.b;
import com.corrodinggames.rts.gameFramework.f.a.f$1;
import com.corrodinggames.rts.gameFramework.f.a.g;
import com.corrodinggames.rts.gameFramework.f.a.h;
import com.corrodinggames.rts.gameFramework.f.a.j;
import com.corrodinggames.rts.gameFramework.f.a.k;
import com.corrodinggames.rts.gameFramework.f.a.m;
import com.corrodinggames.rts.gameFramework.f.a.n;
import com.corrodinggames.rts.gameFramework.m.y;

public class f extends n {

   g a;


   public static f a(String var0, boolean var1) {
      f var2 = new f();
      var2.b = h.n;
      var2.i = 200.0F;
      var2.j = 200.0F;
      j var3 = new j();
      var3.a(var0);
      var3.e(5.0F);
      var3.f(5.0F);
      var3.a(-1);
      var2.a(var3);
      var2.a = new g(m.c);
      var2.a(var2.a);
      if(var1) {
         b var4 = var2.b(com.corrodinggames.rts.gameFramework.h.a.a("menus.common.cancel", new Object[0]));
         var4.a(new f$1(var2));
      }

      return var2;
   }

   public b a(String var1) {
      b var2 = new b();
      var2.a(var1);
      var2.e(5.0F);
      var2.f(5.0F);
      var2.a(Color.a(255, 30, 240, 30));
      return var2;
   }

   public b b(String var1) {
      return this.a(var1, (k)null);
   }

   public b a(String var1, k var2) {
      b var3 = this.a(var1);
      var3.a(var2);
      this.a.a(var3);
      return var3;
   }

   public void u_() {
      if(this.s) {
         this.b();
      }
   }

   public void b() {
      super.b();
      y var1 = this.d();
      this.i = this.z;
      this.j = this.y;
      this.i += this.m + this.n;
      this.j += this.k + this.l;
   }
}
