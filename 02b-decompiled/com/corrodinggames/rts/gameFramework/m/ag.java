package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.corrodinggames.rts.gameFramework.m.ae;

public class ag extends Paint {

   public static final ag r = new ag();
   boolean s = false;
   ae t;
   boolean u = false;


   public void o() {
      this.u = true;
   }

   public void c(float var1) {
      super.b(var1);
   }

   public void b(float var1) {
      if(this.u) {
         com.corrodinggames.rts.gameFramework.l.b("UniquePaint changed when locked down:");
         com.corrodinggames.rts.gameFramework.l.b("from:" + this.k() + " to: " + var1);
         com.corrodinggames.rts.gameFramework.l.T();
      }

      super.b(var1);
   }

   public Typeface a(Typeface var1) {
      if(this.u) {
         com.corrodinggames.rts.gameFramework.l.b("UniquePaint changed when locked down:");
         com.corrodinggames.rts.gameFramework.l.T();
      }

      return super.a(var1);
   }

   public static void b(Paint var0) {
      ((ag)var0).o();
   }

   public boolean p() {
      return this.s;
   }

   public void a(boolean var1) {
      this.s = var1;
      super.a(var1);
   }

   public ae q() {
      return this.t;
   }

   public void a(ae var1) {
      this.t = var1;
   }

   static {
      r.b(-1);
      r.o();
   }
}
