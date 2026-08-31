package com.corrodinggames.rts.game.units.a;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.a;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.o;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.gameFramework.ad;
import com.corrodinggames.rts.gameFramework.f.ae;
import java.util.ArrayList;

public abstract class s implements Comparable {

   public float g = -999.0F;
   public a h;
   public static final c i = c.a;
   private c a;
   private com.corrodinggames.rts.game.units.custom.d.b b;


   public float m_() {
      if(this instanceof o) {
         return -100.0F;
      } else if(this.g != -999.0F) {
         return this.g;
      } else {
         as var1 = this.i();
         return var1 != null && this.g()?(float)var1.g():1.0F;
      }
   }

   public int a(s var1) {
      if(var1 == null) {
         return 0;
      } else {
         float var2 = this.m_() - var1.m_();
         return var2 < 0.0F?-1:(var2 > 0.0F?1:0);
      }
   }

   public boolean equals(Object var1) {
      if(this == var1) {
         return true;
      } else if(var1 != null && var1.getClass() == this.getClass()) {
         s var2 = (s)var1;
         return this.a.equals(var2.a);
      } else {
         return false;
      }
   }

   public static final boolean b(c var0) {
      return var0 == null || var0 == i;
   }

   public static final boolean c(c var0) {
      return !b(var0);
   }

   public static boolean a(s var0, s var1) {
      return var0 == var1;
   }

   public final boolean d(c var1) {
      return this.a == var1;
   }

   public s(int var1) {
      this.h = a.a;
      this.a(String.valueOf(var1));
   }

   public s(String var1) {
      this.h = a.a;
      this.a(var1);
   }

   public s(c var1) {
      this.h = a.a;
      this.e(var1);
   }

   public final void a(String var1) {
      this.a = c.a(var1);
   }

   public final void e(c var1) {
      this.a = var1;
   }

   public final c N() {
      return this.a;
   }

   public c z() {
      return this.N();
   }

   public final String O() {
      return this.a == null?"<null index>":this.a.a();
   }

   public abstract String b();

   public abstract String a();

   public com.corrodinggames.rts.game.units.custom.h P() {
      return null;
   }

   public String d(am var1) {
      return this.b();
   }

   public String e(am var1) {
      return this.a();
   }

   public abstract int c();

   public com.corrodinggames.rts.game.units.custom.d.b B() {
      com.corrodinggames.rts.game.units.custom.d.b var1 = this.h.a();
      if(var1 != null) {
         return var1;
      } else {
         int var2 = this.c();
         if(var2 == 0) {
            return com.corrodinggames.rts.game.units.custom.d.b.a;
         } else {
            if(this.b == null || this.b.a() != var2) {
               this.b = com.corrodinggames.rts.game.units.custom.d.b.a(var2);
            }

            return this.b;
         }
      }
   }

   public com.corrodinggames.rts.game.units.custom.d.b r_() {
      return this.h.b() != null?this.h.b():null;
   }

   public abstract int b(am var1, boolean var2);

   public boolean n_() {
      return false;
   }

   public boolean g(am var1) {
      return this.h.b(var1);
   }

   public String j(am var1) {
      return this.h.c(var1);
   }

   public void a(am var1, am var2) {
      this.h.a(var1, var2);
   }

   public boolean d(am var1, boolean var2) {
      return true;
   }

   public boolean k(am var1) {
      return false;
   }

   public boolean l(am var1) {
      return false;
   }

   public boolean a(am var1, boolean var2) {
      return this.g(var1)?false:(com.corrodinggames.rts.game.units.g.e.a(var1, this.N()) > 0?false:(var2?this.B().c(var1, this.Q()):this.B().b(var1)));
   }

   public boolean r(am var1) {
      return this.b(var1);
   }

   public boolean u(am var1) {
      return this.h.a(var1);
   }

   public boolean b(am var1) {
      return this.h.a(var1, false);
   }

   public boolean a(am var1, com.corrodinggames.rts.game.n var2) {
      return false;
   }

   public boolean u() {
      return false;
   }

   public boolean h() {
      return false;
   }

   public boolean C() {
      return false;
   }

   public boolean D() {
      return true;
   }

   public boolean A() {
      return false;
   }

   public abstract as i();

   public as y() {
      return null;
   }

   public as E() {
      return null;
   }

   public boolean F() {
      return false;
   }

   public int t() {
      return 1;
   }

   public abstract boolean g();

   public abstract u e();

   public boolean o() {
      return false;
   }

   public abstract t f();

   public boolean m(am var1) {
      return false;
   }

   public boolean n(am var1) {
      return false;
   }

   public com.corrodinggames.rts.game.units.custom.a.e v(am var1) {
      return null;
   }

   public String d() {
      String var1 = null;
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      int var3 = 0;
      am[] var4 = var2.bS.bZ.a();
      int var5 = var2.bS.bZ.size();

      for(int var6 = 0; var6 < var5; ++var6) {
         am var7 = var4[var6];
         if(var7 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var8 = (com.corrodinggames.rts.game.units.y)var7;
            if(var1 == null) {
               var1 = this.d((am)var8);
            }

            int var9 = this.b(var8, true);
            if(var9 != -1 && var9 != 0) {
               var3 += var9;
            }
         }
      }

      if(var1 == null) {
         var1 = this.b();
      }

      if(var3 != -1 && var3 != 0) {
         var1 = var1 + " (" + var3 + ")";
      }

      return var1;
   }

   public boolean h_() {
      return true;
   }

   public String w(am var1) {
      return this.d(var1);
   }

   public void a(am var1, ae var2, Paint var3, Paint var4) {
      Paint var5 = var2.g;
      if(var3 != null) {
         var2.a(var3);
      }

      if(this.h_()) {
         String var6 = this.w(var1);
         if(var6 != null && !var6.equals("")) {
            var2.b(var6);
         }
      }

      if(var3 != null) {
         var2.a(var5);
      }

      t var11 = this.f();
      com.corrodinggames.rts.game.units.custom.d.b var7 = this.B();
      if(!var7.c() && var11 != t.i) {
         boolean var8 = true;
         var2.b(" (");
         am var9 = null;
         int var10 = 0;
         if(var4 != null) {
            var9 = var1;
            var10 = var4.e();
         }

         var7.a(var2, false, true, 5, var8, var9, var10);
         var2.b(")");
      }

      com.corrodinggames.rts.game.units.custom.d.b var12 = this.r_();
      if(var12 != null && !var12.c() && var11 != t.i) {
         boolean var13 = true;
         var2.b(" (");
         byte var14 = 0;
         var12.a(var2, false, true, 5, var13, (am)null, var14);
         var2.b(")");
      }

   }

   public void a(am var1, ae var2) {
      String var3 = com.corrodinggames.rts.gameFramework.f.a.a(this, false);
      if(var3 != null && !"".equals(var3)) {
         var3 = var3.trim();
         var2.b("\n" + var3);
      }

      String var4 = this.e(var1);
      if(var4 != null && !"".equals(var4)) {
         var4 = var4.trim();
         var2.b("\n" + var4);
      }

   }

   public boolean c(am var1, boolean var2) {
      return false;
   }

   public void f(am var1) {}

   public com.corrodinggames.rts.gameFramework.m.e j() {
      return this.f() == t.c?com.corrodinggames.rts.gameFramework.l.B().bS.bk:null;
   }

   public com.corrodinggames.rts.gameFramework.m.e h(am var1) {
      return null;
   }

   public int J() {
      return Color.a(100, 255, 255, 255);
   }

   public Rect v() {
      return null;
   }

   public am i(am var1) {
      return null;
   }

   public boolean s(am var1) {
      return true;
   }

   public boolean t(am var1) {
      return true;
   }

   public boolean a(am var1) {
      return this.h.d(var1);
   }

   public boolean s() {
      return false;
   }

   public boolean o(am var1) {
      return true;
   }

   public boolean G() {
      return false;
   }

   public void c(am var1) {}

   public float l() {
      return 1.0F;
   }

   public int m() {
      return -1;
   }

   public boolean H() {
      return false;
   }

   public boolean I() {
      return false;
   }

   public boolean x() {
      return false;
   }

   public float p(am var1) {
      return -1.0F;
   }

   public ArrayList q(am var1) {
      return null;
   }

   public ad M() {
      return null;
   }

   public boolean o_() {
      return false;
   }

   public boolean Q() {
      return false;
   }

   public void a(com.corrodinggames.rts.game.units.y var1) {}

   public boolean a(float var1, float var2) {
      return false;
   }

   public boolean p() {
      return false;
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.a((s)var1);
   }

}
