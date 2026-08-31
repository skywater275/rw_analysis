package com.corrodinggames.rts.game.units.custom.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.aj;
import com.corrodinggames.rts.game.units.custom.p;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.f.an;

public class c extends com.corrodinggames.rts.game.units.a.a {

   public LogicBoolean b;
   public LogicBoolean c;
   public aj d;
   public LogicBoolean e;
   public aj f;
   public LogicBoolean g;
   public aj h;
   public boolean i;
   public LogicBoolean j;
   public com.corrodinggames.rts.game.units.custom.d.b k;
   public com.corrodinggames.rts.game.units.custom.d.b l;
   public com.corrodinggames.rts.game.units.custom.d.b m;


   public static com.corrodinggames.rts.game.units.a.a a(d var0) {
      boolean var1 = false;
      if(var0.z != null && var0.z != LogicBoolean.falseBoolean) {
         var1 = true;
      }

      if(var0.B != null && var0.B != LogicBoolean.falseBoolean) {
         var1 = true;
      }

      if(var0.D != null && var0.D != LogicBoolean.falseBoolean) {
         var1 = true;
      }

      if(var0.v != null && var0.v != LogicBoolean.trueBoolean) {
         var1 = true;
      }

      if(var0.aF != null && var0.aF != LogicBoolean.falseBoolean) {
         var1 = true;
      }

      if(var0.ae != null) {
         var1 = true;
      }

      if(var0.q != null) {
         var1 = true;
      }

      if(!var1) {
         return com.corrodinggames.rts.game.units.a.a.a;
      } else {
         c var2 = new c();
         var2.c = var0.z;
         var2.d = var0.A;
         var2.e = var0.B;
         var2.f = var0.C;
         var2.g = var0.D;
         var2.h = var0.E;
         var2.b = var0.v;
         var2.j = var0.aF;
         var2.l = var0.ae;
         var2.k = var0.q;
         var2.m = var0.r;
         var2.i = var0.y;
         return var2;
      }
   }

   public static com.corrodinggames.rts.game.units.a.a a(p var0) {
      boolean var1 = false;
      if(var0.f != null && var0.f != LogicBoolean.falseBoolean) {
         var1 = true;
      }

      if(!var1) {
         return com.corrodinggames.rts.game.units.a.a.a;
      } else {
         c var2 = new c();
         var2.c = var0.f;
         var2.d = aj.a(var0.g);
         return var2;
      }
   }

   public boolean a(am var1) {
      return this.i;
   }

   public boolean b(am var1) {
      return this.a(var1, -1);
   }

   public boolean a(am var1, int var2) {
      if(this.c != null && (var2 == -1 || var2 == 1)) {
         if(!(var1 instanceof y)) {
            l.n("CustomActionConfig lockedInGame:" + var1.r().i() + " is not a OrderableUnit unit");
            return false;
         }

         if(this.c.read((y)var1)) {
            return true;
         }
      }

      if(this.e != null && (var2 == -1 || var2 == 2)) {
         if(!(var1 instanceof y)) {
            l.n("CustomActionConfig lockedInGame:" + var1.r().i() + " is not a OrderableUnit unit");
            return false;
         }

         if(this.e.read((y)var1)) {
            return true;
         }
      }

      if(this.g != null && (var2 == -1 || var2 == 3)) {
         if(!(var1 instanceof y)) {
            l.n("CustomActionConfig lockedInGame:" + var1.r().i() + " is not a OrderableUnit unit");
            return false;
         }

         if(this.g.read((y)var1)) {
            return true;
         }
      }

      return false;
   }

   public String c(am var1) {
      return this.a(var1, 1) && this.d != null?this.d.b(var1):(this.a(var1, 2) && this.f != null?this.f.b(var1):(this.a(var1, 3) && this.h != null?this.h.b(var1):null));
   }

   public boolean a(am var1, boolean var2) {
      if(this.b != null) {
         if(!(var1 instanceof y)) {
            l.n("CustomActionConfig isAvailable:" + var1.r().i() + " is not a OrderableUnit unit");
            return true;
         } else {
            return var2?an.a(this.b, (y)var1):this.b.read((y)var1);
         }
      } else {
         return true;
      }
   }

   public boolean d(am var1) {
      if(this.j != null) {
         if(!(var1 instanceof y)) {
            l.n("CustomActionConfig isGuiBlinking:" + var1.r().i() + " is not a OrderableUnit unit");
            return true;
         } else {
            return this.j.read((y)var1);
         }
      } else {
         return false;
      }
   }

   public void a(am var1, am var2) {
      if(this.l != null) {
         this.l.h(var1);
      }

   }

   public com.corrodinggames.rts.game.units.custom.d.b a() {
      return this.k;
   }

   public com.corrodinggames.rts.game.units.custom.d.b b() {
      return this.m;
   }
}
