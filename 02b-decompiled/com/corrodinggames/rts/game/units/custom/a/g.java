package com.corrodinggames.rts.game.units.custom.a;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.v;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.a.e;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.f.an;

public class g extends w {

   public d a;
   public v b;
   public e c;


   public g(d var1, v var2) {
      super((String)null);
      this.c = e.b;
      String var3 = "";
      if(var1.k != null) {
         var3 = var3 + var1.k;
      }

      var3 = var3 + "_" + var1.a;
      if(var1.b != null) {
         var3 = var1.b;
      }

      this.a(var3);
      this.a = var1;
      this.b = var2;
      if(var1.J != null) {
         this.b = var1.J;
      }

      this.c = var1.aN;
      if(this.c == e.a) {
         boolean var4 = false;
         boolean var5 = false;
         if(var1.ag != null && var1.ah == null) {
            var5 = true;
         }

         if(var1.q.d()) {
            var4 = true;
            this.c = e.c;
         }

         if(var4 && !var5) {
            this.c = e.c;
         } else {
            this.c = e.d;
         }

         if(var1.I != null) {
            this.c = e.e;
         }
      }

   }

   public h P() {
      return this.a.s;
   }

   public boolean F() {
      return true;
   }

   public boolean d(am var1, boolean var2) {
      return this.a.M;
   }

   public boolean k(am var1) {
      return this.a.O;
   }

   public boolean l(am var1) {
      return this.a.P;
   }

   public boolean u() {
      return super.u();
   }

   public boolean a(am var1, boolean var2) {
      j var3 = (j)var1;
      if(!this.a.N && var3.a(this.N(), var2) > 0) {
         return false;
      } else {
         if(this.a.u != null) {
            if(var2 && this.Q()) {
               if(!an.a(this.a.u, (y)var3)) {
                  return false;
               }
            } else if(!this.a.u.read(var3)) {
               return false;
            }
         }

         return super.a(var1, var2);
      }
   }

   public boolean g(am var1) {
      return this.a(var1, -1)?true:super.g(var1);
   }

   public boolean a(am var1, int var2) {
      if(this.a.z != null && (var2 == -1 || var2 == 1)) {
         if(!(var1 instanceof j)) {
            l.n("CustomActionConfig lockedInGame:" + var1.r().i() + " is not a custom unit");
            return false;
         }

         if(this.a.z.read((j)var1)) {
            return true;
         }
      }

      if(this.a.B != null && (var2 == -1 || var2 == 2)) {
         if(!(var1 instanceof j)) {
            l.n("CustomActionConfig lockedInGame:" + var1.r().i() + " is not a custom unit");
            return false;
         }

         if(this.a.B.read((j)var1)) {
            return true;
         }
      }

      if(this.a.D != null && (var2 == -1 || var2 == 3)) {
         if(!(var1 instanceof j)) {
            l.n("CustomActionConfig lockedInGame:" + var1.r().i() + " is not a custom unit");
            return false;
         }

         if(this.a.D.read((j)var1)) {
            return true;
         }
      }

      return false;
   }

   public String j(am var1) {
      return this.a(var1, 1) && this.a.A != null?this.a.A.b(var1):(this.a(var1, 2) && this.a.C != null?this.a.C.b(var1):(this.a(var1, 3) && this.a.E != null?this.a.E.b(var1):super.j(var1)));
   }

   public boolean r(am var1) {
      j var2 = (j)var1;
      return this.a.v != null?(this.Q()?an.a(this.a.v, (y)var2):this.a.v.read(var2)):super.b(var1);
   }

   public boolean b(am var1) {
      j var2 = (j)var1;
      return this.a.v != null?this.a.v.read(var2):super.b(var1);
   }

   public boolean a(am var1, n var2) {
      return !this.a.w && !this.a.x?false:(var1.bX.d(var2)?this.a.w:this.a.x);
   }

   public com.corrodinggames.rts.game.units.custom.d.b r_() {
      com.corrodinggames.rts.game.units.custom.d.b var1 = this.h.b();
      return var1 != null?var1:this.a.r;
   }

   public int b(am var1, boolean var2) {
      return this.a.aI?this.a.q.a(var1, true):super.b(var1, var2);
   }

   public String d() {
      return super.d();
   }

   public String b() {
      String var1 = null;
      if(this.a.d != null) {
         var1 = this.a.d.b();
      }

      return var1;
   }

   public String d(am var1) {
      String var2 = null;
      if(this.a.d != null) {
         var2 = this.a.d.b(var1);
      }

      if(this.a.e != null) {
         as var3 = this.a.e.getTypeOrNull(var1);
         if(var3 != null) {
            if(var2 == null) {
               var2 = "";
            } else if(!var2.equals("")) {
               var2 = var2 + " ";
            }

            var2 = var2 + var3.e();
         }
      }

      if(this.a.h != null) {
         if(var2 == null) {
            var2 = "";
         } else if(!var2.equals("")) {
            var2 = var2 + " ";
         }

         var2 = var2 + this.a.h.b();
      }

      return var2;
   }

   public String a() {
      String var1 = null;
      if(this.a.i != null) {
         var1 = this.a.i.b();
      }

      return var1;
   }

   public String e(am var1) {
      String var2 = null;
      if(this.a.i != null) {
         var2 = this.a.i.b(var1);
      }

      if(this.a.f != null) {
         as var3 = this.a.f.getTypeOrNull(var1);
         if(var3 != null) {
            if(var2 == null) {
               var2 = "";
            } else if(!var2.equals("")) {
               var2 = var2 + " ";
            }

            var2 = var2 + var3.f();
         }
      }

      if(this.a.g != null) {
         am var7 = this.a.g.getUnitReferenceOrNull(var1);
         if(var7 != null) {
            if(var2 == null) {
               var2 = "";
            } else if(!var2.equals("")) {
               var2 = var2 + "\n\n";
            }

            boolean var4 = false;
            String var5 = com.corrodinggames.rts.gameFramework.f.a.a(var7, false, false, var4);
            var2 = var2 + var5;
         } else {
            am var8 = this.a.g.getUnitOrSharedUnit(var1);
            if(var8 != null) {
               if(var2 == null) {
                  var2 = "";
               } else if(!var2.equals("")) {
                  var2 = var2 + "\n\n";
               }

               boolean var9 = true;
               String var6 = com.corrodinggames.rts.gameFramework.f.a.a(var8, false, false, var9);
               var2 = var2 + var6;
            }
         }
      }

      return var2;
   }

   public boolean L() {
      return this.a.U;
   }

   public float K() {
      return this.a.S >= 1.0F?1000.0F:this.a.S;
   }

   public u e() {
      return this.a.j;
   }

   public com.corrodinggames.rts.game.units.custom.d.b B() {
      com.corrodinggames.rts.game.units.custom.d.b var1 = this.h.a();
      return var1 != null?var1:this.a.q;
   }

   public int c() {
      return this.B().a();
   }

   public as i() {
      return this.b == null?null:this.b.c();
   }

   public as y() {
      return this.a.J != null?this.a.J.c():null;
   }

   public as E() {
      return this.a.I != null?this.a.I.c():null;
   }

   public boolean A() {
      return true;
   }

   public boolean g() {
      return this.a.J != null;
   }

   public t f() {
      return this.a.aG;
   }

   public boolean m(am var1) {
      return this.a.G.read((j)var1);
   }

   public boolean n(am var1) {
      if(this.a.F == null) {
         return false;
      } else if(!(var1 instanceof j)) {
         l.b("ai_isHighPriority non customUnit:" + var1.r().i());
         return false;
      } else {
         return this.a.F.read((j)var1);
      }
   }

   public e v(am var1) {
      return this.c;
   }

   public boolean H() {
      return this.a.K;
   }

   public boolean I() {
      return this.a.L;
   }

   public com.corrodinggames.rts.gameFramework.m.e j() {
      return this.a.ay;
   }

   public com.corrodinggames.rts.gameFramework.m.e h(am var1) {
      return this.a.aB != null && var1 instanceof j && !an.a(this.a.aB, (y)((j)var1))?null:this.a.az;
   }

   public int J() {
      return this.a.aA;
   }

   public am i(am var1) {
      if(this.a.aC != null) {
         am var2 = this.a.aC.getUnitOrSharedUnit(var1);
         return var2;
      } else {
         return null;
      }
   }

   public boolean s(am var1) {
      return this.a.aD;
   }

   public boolean t(am var1) {
      return this.a.aE;
   }

   public boolean a(am var1) {
      return this.a.aF != null?an.a(this.a.aF, (y)((j)var1)):false;
   }

   public boolean Q() {
      return this.a.o;
   }

   public void a(y var1) {
      if(this.a.ae != null) {
         an.b(var1, this.a.ae);
      }

   }
}
