package com.corrodinggames.rts.game.units.a;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.b;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.gameFramework.f.ae;
import java.util.ArrayList;

public class g extends s {

   public s a;
   public com.corrodinggames.rts.game.units.y b;
   public b c;
   static com.corrodinggames.rts.gameFramework.utility.u d;
   static final com.corrodinggames.rts.gameFramework.utility.u e = new com.corrodinggames.rts.gameFramework.utility.u();


   private void K() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(d != null) {
         throw new RuntimeException("savedSelectedUnitsCache!=null");
      } else {
         d = var1.bS.bZ;
         e.clear();
         e.a((am)this.b);
         var1.bS.bZ = e;
      }
   }

   private void L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(d == null) {
         throw new RuntimeException("savedSelectedUnitsCache==null");
      } else {
         var1.bS.bZ = d;
         d = null;
         e.clear();
      }
   }

   public float m_() {
      return super.m_();
   }

   public int a(s var1) {
      return super.a(var1);
   }

   public String b() {
      return this.a.b();
   }

   public String d(am var1) {
      return this.a.d((am)this.b);
   }

   public String a() {
      String var1 = this.a.a();
      return var1;
   }

   public String e(am var1) {
      return this.a.e((am)this.b);
   }

   public int c() {
      return this.a.c();
   }

   public int b(am var1, boolean var2) {
      return this.a.b(this.b, var2);
   }

   public boolean n_() {
      return this.a.n_();
   }

   public boolean a(am var1, boolean var2) {
      return this.a.a(this.b, var2);
   }

   public int t() {
      return this.a.t();
   }

   public void f(am var1) {
      this.a.f(this.b);
   }

   public boolean equals(Object var1) {
      return super.equals(var1);
   }

   public boolean g(am var1) {
      return this.a.g(this.b);
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
      this.K();
      String var1 = this.a.d();
      this.L();
      return var1;
   }

   public boolean h_() {
      return this.a.h_();
   }

   public void a(am var1, ae var2, Paint var3, Paint var4) {
      this.K();
      this.a.a(this.b, var2, var3, var4);
      this.L();
   }

   public void a(am var1, ae var2) {
      this.K();
      this.a.a((am)this.b, var2);
      this.L();
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
      return this.a.i(this.b);
   }

   public int hashCode() {
      return this.a.hashCode();
   }

   public String toString() {
      return this.a.toString();
   }

   public g(s var1, com.corrodinggames.rts.game.units.y var2, c var3) {
      super(var3);
      this.c = b.emptyActionFilter;
      this.a = var1;
      this.b = var2;
      this.g = this.a.g;
   }

   public s p_() {
      return this.a;
   }

   public boolean x() {
      return this.a.x();
   }

   public boolean s() {
      return this.a.s();
   }

   public as y() {
      return this.a.y();
   }

   public c z() {
      return this.a.N();
   }

   public void a(am var1, am var2) {
      super.a(var1, var2);
   }

   public boolean a(am var1, com.corrodinggames.rts.game.n var2) {
      return this.a.a((am)this.b, var2);
   }

   public boolean A() {
      return this.a.A();
   }

   public boolean a(am var1) {
      return this.a.a((am)this.b);
   }

   public com.corrodinggames.rts.game.units.custom.d.b B() {
      return this.a.B();
   }

   public String j(am var1) {
      return this.a.j(this.b);
   }

   public boolean d(am var1, boolean var2) {
      return this.a.d(this.b, var2);
   }

   public boolean k(am var1) {
      return this.a.k(this.b);
   }

   public boolean l(am var1) {
      return this.a.l(this.b);
   }

   public boolean C() {
      return this.a.C();
   }

   public boolean D() {
      return this.a.D();
   }

   public as E() {
      return this.a.E();
   }

   public boolean F() {
      return this.a.F();
   }

   public boolean m(am var1) {
      return this.a.m(this.b);
   }

   public boolean n(am var1) {
      return this.a.n(this.b);
   }

   public boolean c(am var1, boolean var2) {
      return this.a.c(this.b, var2);
   }

   public boolean o(am var1) {
      return this.a.o(this.b);
   }

   public boolean G() {
      return this.a.G();
   }

   public void c(am var1) {
      this.a.c((am)this.b);
   }

   public float l() {
      return this.a.l();
   }

   public int m() {
      return this.a.m();
   }

   public boolean H() {
      return this.a.H();
   }

   public boolean I() {
      return this.a.I();
   }

   public float p(am var1) {
      return this.a.p(this.b);
   }

   public ArrayList q(am var1) {
      return this.a.q(this.b);
   }

   public boolean r(am var1) {
      return !this.c.isAvailable(this, var1)?false:this.a.r(this.b);
   }

   public boolean b(am var1) {
      return !this.c.isAvailable(this, var1)?false:this.a.b((am)this.b);
   }

   public int J() {
      return this.a.J();
   }

   public boolean s(am var1) {
      return this.a.s(this.b);
   }

   public boolean t(am var1) {
      return this.a.t(this.b);
   }

   public boolean a(g var1) {
      return this.a == var1.a && this.b == var1.b && this.N() == var1.N() && this.c == var1.c;
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.a((s)var1);
   }

}
