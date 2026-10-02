package com.corrodinggames.rts.a.a;

import com.corrodinggames.rts.a.a.l;
import com.corrodinggames.rts.a.a.n;

public class k extends l {

   public com.corrodinggames.rts.gameFramework.k.n a(int var1) {
      com.corrodinggames.rts.gameFramework.k.n var2 = new com.corrodinggames.rts.gameFramework.k.n();
      var2.a((short)var1, (short)0);
      var2.a(0, 0, 0);
      return var2;
   }

   public void a() {
      com.corrodinggames.rts.gameFramework.l.e("== Testing FastNodeQueue ==");
      com.corrodinggames.rts.gameFramework.k.d var1 = new com.corrodinggames.rts.gameFramework.k.d();
      this.a(var1);
      com.corrodinggames.rts.gameFramework.l.e("== Testing FastNodeQueue2 ==");
      com.corrodinggames.rts.gameFramework.k.e var2 = new com.corrodinggames.rts.gameFramework.k.e();
      this.a(var2);
   }

   public void a(com.corrodinggames.rts.gameFramework.k.j var1) {
      com.corrodinggames.rts.gameFramework.k.n var2 = this.a(1);
      com.corrodinggames.rts.gameFramework.k.n var3 = this.a(2);
      com.corrodinggames.rts.gameFramework.k.n var4 = this.a(3);
      com.corrodinggames.rts.gameFramework.k.n var5 = this.a(4);
      com.corrodinggames.rts.gameFramework.l.e("sequential");
      var1.b();
      var1.a(var2);
      var1.a(var3);
      var1.a(var4);
      var1.a(var5);
      n.a((Object)var1.a(), (Object)var2);
      n.a((Object)var1.a(), (Object)var3);
      n.a((Object)var1.a(), (Object)var4);
      n.a((Object)var1.a(), (Object)var5);
      com.corrodinggames.rts.gameFramework.l.e("reverse sequence");
      var1.b();
      var1.a(var5);
      var1.a(var4);
      var1.a(var3);
      var1.a(var2);
      n.a((Object)var1.a(), (Object)var2);
      n.a((Object)var1.a(), (Object)var3);
      n.a((Object)var1.a(), (Object)var4);
      n.a((Object)var1.a(), (Object)var5);
      com.corrodinggames.rts.gameFramework.l.e("sequential with noise");
      var1.b();
      var1.a(var2);

      int var6;
      for(var6 = 0; var6 < 1000; ++var6) {
         var1.a(this.a(100 + var6));
      }

      n.a((Object)var1.a(), (Object)var2);
      var1.a(var3);
      var1.a(var4);

      for(var6 = 0; var6 < 1000; ++var6) {
         var1.a(this.a(100 + var6));
      }

      n.a((Object)var1.a(), (Object)var3);
      var1.a(var5);
      n.a((Object)var1.a(), (Object)var4);
      n.a((Object)var1.a(), (Object)var5);
      com.corrodinggames.rts.gameFramework.l.e("reverse sequence with noise");
      var1.b();
      var1.a(var5);
      var1.a(var4);

      for(var6 = 0; var6 < 1000; ++var6) {
         var1.a(this.a(100 + var6));
      }

      var1.a(var3);

      for(var6 = 0; var6 < 1000; ++var6) {
         var1.a(this.a(100 + var6));
      }

      var1.a(var2);
      n.a((Object)var1.a(), (Object)var2);
      n.a((Object)var1.a(), (Object)var3);
      n.a((Object)var1.a(), (Object)var4);
      n.a((Object)var1.a(), (Object)var5);
   }
}
