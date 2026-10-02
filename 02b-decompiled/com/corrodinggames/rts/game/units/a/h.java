package com.corrodinggames.rts.game.units.a;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.b;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.gameFramework.f.ae;

public class h extends s {

   s a;
   b b;
   boolean c;
   public int d;
   public boolean e;
   public final int f;


   public float m_() {
      return this.a.m_();
   }

   public int a(s var1) {
      return this.a.a(var1);
   }

   public String b() {
      return this.a.b();
   }

   public String d(am var1) {
      return this.a.d(var1);
   }

   public String a() {
      String var1 = this.a.a();
      return var1;
   }

   public String e(am var1) {
      return this.a.e(var1);
   }

   public int c() {
      return 0;
   }

   public int b(am var1, boolean var2) {
      return this.a.b(var1, var2);
   }

   public boolean n_() {
      return this.a.n_();
   }

   public boolean a(am var1, boolean var2) {
      return this.c?this.a.a(var1, var2):true;
   }

   public int t() {
      return this.a.t();
   }

   public void f(am var1) {
      this.a.f(var1);
   }

   public boolean equals(Object var1) {
      return var1 instanceof h?this.a.equals(((h)var1).a):false;
   }

   public boolean g(am var1) {
      return this.a.g(var1);
   }

   public boolean b(am var1) {
      return !this.b.isAvailable(this, var1)?false:this.a.b(var1);
   }

   public boolean u() {
      return this.a.u();
   }

   public boolean h() {
      return this.a.h();
   }

   public as i() {
      return this.a.i();
   }

   public boolean g() {
      return this.a.g();
   }

   public u e() {
      return this.a.e();
   }

   public t f() {
      return this.a.f();
   }

   public String d() {
      return this.a.d();
   }

   public boolean h_() {
      return this.a.h_();
   }

   public void a(am var1, ae var2, Paint var3, Paint var4) {
      this.a.a(var1, var2, var3, var4);
   }

   public void a(am var1, ae var2) {
      this.a.a(var1, var2);
      as var3 = this.a.i();
      if(var3 != null && var3 instanceof com.corrodinggames.rts.game.units.custom.l) {
         com.corrodinggames.rts.game.units.custom.l var4 = (com.corrodinggames.rts.game.units.custom.l)var3;
         if(var4.J != null) {
            String var5 = var4.J.a();
            var5 = com.corrodinggames.rts.gameFramework.f.a(var5, (int)30);
            var2.a("\n(mod: " + var5 + ")", this.f, true);
         }
      }

   }

   public com.corrodinggames.rts.gameFramework.m.e j() {
      return this.a.j();
   }

   public com.corrodinggames.rts.gameFramework.m.e h(am var1) {
      return this.a.h(var1);
   }

   public Rect v() {
      return this.a.v();
   }

   public am i(am var1) {
      return this.a.i(var1);
   }

   public int hashCode() {
      return this.a.hashCode();
   }

   public String toString() {
      return this.a.toString();
   }

   public h(s var1, b var2) {
      this(var1, var2, false);
   }

   public h(s var1, b var2, boolean var3) {
      super(var1.N());
      this.b = b.emptyActionFilter;
      this.d = 0;
      this.f = Color.a(255, 50, 50, 50);
      this.a = var1;
      this.b = var2;
      this.e(this.a.N());
      this.g = this.a.g;
      this.c = var3;
   }

   public s q_() {
      return this.a;
   }

   public boolean x() {
      return true;
   }

   public boolean s() {
      return !this.b.isAvailable(this, (am)null)?false:(this.c?this.a.s():true);
   }

   public as y() {
      return this.a.y();
   }

   public boolean c(am var1, boolean var2) {
      return this.a.c(var1, var2);
   }

   public boolean a(am var1) {
      return this.a.a(var1);
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.a((s)var1);
   }
}
