package com.corrodinggames.rts.game.units;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;

public class e extends com.corrodinggames.rts.game.units.d.d {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   float b;
   static PorterDuffColorFilter c = new PorterDuffColorFilter(Color.a(200, 200, 200), Mode.MULTIPLY);


   public ar b() {
      return ar.H;
   }

   public static void a_() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.crystal);
   }

   public com.corrodinggames.rts.gameFramework.m.e d() {
      return a;
   }

   public boolean e() {
      return true;
   }

   public void a(int var1) {}

   public e(boolean var1) {
      super(var1);
      this.M = a;
      this.b(a);
      this.cj = 11.0F;
      this.ck = this.cj + 1.0F;
      this.cv = 600.0F;
      this.cu = this.cv;
      this.S(1);
      this.n.a(0, -1, 0, 0);
      this.o.a(this.n);
   }

   public Paint f() {
      Paint var1 = super.f();
      return var1;
   }

   public void a(float var1) {
      super.a(var1);
      this.b += 0.01F * var1;
      if(this.b > 1.0F) {
         --this.b;
         if(this.b > 1.0F) {
            this.b = 0.0F;
         }
      }

   }

   public float g() {
      return 0.02F;
   }

   public ao h() {
      return ao.a;
   }

   public boolean i() {
      return false;
   }

   public boolean s_() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      du.a(this.cE());
      return RectF.a(var1.cM, du);
   }

   public com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public boolean l() {
      return false;
   }

   public float m() {
      return 0.0F;
   }

   public float b(int var1) {
      return 0.0F;
   }

   public float c(int var1) {
      return 0.0F;
   }

   public void a(am var1, int var2) {}

   public void n() {
      super.n();
      this.b = (this.ep * 5.0F + this.eo * 3.0F) % 1.0F;
   }

   public boolean o() {
      return true;
   }

   public boolean p() {
      return true;
   }

   public boolean q() {
      return true;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
