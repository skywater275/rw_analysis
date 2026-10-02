package com.corrodinggames.rts.java.b;

import com.corrodinggames.rts.java.Main;

public class a extends com.corrodinggames.librocket.a {

   public Main f;
   boolean g = false;


   public static synchronized a p() {
      if(a != null) {
         throw new RuntimeException("CommonGuiEngine already exists");
      } else {
         a var0 = new a();
         a = var0;
         return var0;
      }
   }

   public void g() {
      this.f.i();
   }

   public void h() {
      this.f.u = true;
   }

   public int i() {
      return this.f.j.e();
   }

   public void d(boolean var1) {
      this.f.a(var1);
   }
}
