package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import com.corrodinggames.rts.gameFramework.m.ae;

public class e {

   public e[] a;
   public e[] b;
   public e[] c;
   private static int x;
   public int d;
   public int e;
   public int f;
   public String g;
   public Integer h;
   ae i;
   public int[] j;
   protected Bitmap k;
   public boolean l = true;
   public boolean m;
   public boolean n;
   public boolean o = false;
   public int p;
   public int q;
   public int r;
   public int s;
   public float t;
   public float u;
   public boolean v;
   boolean w = false;


   public e() {
      this.d = x++;
      this.e = 1;
   }

   public e[] a(com.corrodinggames.rts.game.o var1) {
      if(var1 == com.corrodinggames.rts.game.o.a) {
         return this.a;
      } else if(var1 == com.corrodinggames.rts.game.o.b) {
         return this.b;
      } else if(var1 == com.corrodinggames.rts.game.o.d) {
         return this.c;
      } else {
         com.corrodinggames.rts.gameFramework.l.b("getTeamImageCache coloringMode:" + var1);
         return this.a;
      }
   }

   public void a(com.corrodinggames.rts.game.o var1, e[] var2) {
      if(var1 == com.corrodinggames.rts.game.o.a) {
         this.a = var2;
      } else if(var1 == com.corrodinggames.rts.game.o.b) {
         this.b = var2;
      } else if(var1 == com.corrodinggames.rts.game.o.d) {
         this.c = var2;
      } else {
         com.corrodinggames.rts.gameFramework.l.b("setTeamImageCache coloringMode:" + var1);
         this.a = var2;
      }
   }

   public void a(String var1) {
      this.g = var1;
   }

   public String a() {
      return this.g;
   }

   public Bitmap b() {
      return this.k;
   }

   public e c() {
      return this;
   }

   public void a(boolean var1) {
      this.o = var1;
      this.e();
   }

   public void b(boolean var1) {
      this.w = var1;
   }

   public boolean d() {
      return this.w;
   }

   protected void e() {}

   public boolean f() {
      return this.m;
   }

   public void a(Bitmap var1) {
      this.k = var1;
      this.p = this.k.b();
      this.q = this.k.c();
      this.g();
   }

   public void g() {
      this.r = this.p / 2;
      this.s = this.q / 2;
      this.t = (float)this.p / 2.0F;
      this.u = (float)this.q / 2.0F;
   }

   public void a(e var1) {
      var1.o = this.o;
      var1.p = this.p;
      var1.q = this.q;
      var1.r = this.r;
      var1.s = this.s;
      var1.t = this.t;
      var1.u = this.u;
   }

   public e h() {
      e var1 = new e();
      var1.o = this.o;
      if(this.k != null) {
         Bitmap var2 = this.k.a(this.k.d(), true);
         if(var2 == null) {
            throw new OutOfMemoryError("Failed to copy bitmap: " + this.k.d());
         }

         var1.a(var2);
      }

      return var1;
   }

   public e a(int var1, int var2, boolean var3) {
      e var4 = new e();
      var4.o = this.o;
      if(this.k != null) {
         Bitmap var5 = Bitmap.a(var1, var2, this.k.d());
         var4.a(var5);
         if(var3) {
            for(int var6 = 0; var6 < var5.b(); ++var6) {
               for(int var7 = 0; var7 < var5.c(); ++var7) {
                  var5.a(var6, var7, this.k.a(var6, var7));
               }
            }
         }
      }

      return var4;
   }

   public void i() {
      if(this.j == null) {
         this.j();
      }

   }

   public void j() {
      if(this.k != null || !com.corrodinggames.rts.gameFramework.l.aU || com.corrodinggames.rts.gameFramework.l.aX) {
         if(this.j == null) {
            this.j = new int[this.p * this.q];
         }

         this.k.a(this.j, 0, this.p, 0, 0, this.p, this.q);
      }
   }

   public boolean k() {
      return true;
   }

   public int a(int var1, int var2) {
      return this.j != null?this.j[var1 + var2 * this.p]:this.k.a(var1, var2);
   }

   public void a(int var1, int var2, int var3) {
      if(this.j != null) {
         this.j[var1 + var2 * this.p] = var3;
      } else {
         this.k.a(var1, var2, var3);
      }
   }

   public int l() {
      return this.q;
   }

   public int m() {
      return this.p;
   }

   public void n() {}

   public void o() {
      if(this.k != null) {
         this.k = null;
      }

      if(this.w) {
         com.corrodinggames.rts.gameFramework.l.b("remove with keepInGPUMemory=true");
      }

   }

   public void p() {
      if(this.k != null || !com.corrodinggames.rts.gameFramework.l.aU || com.corrodinggames.rts.gameFramework.l.aW) {
         if(this.j != null) {
            this.k.b(this.j, 0, this.p, 0, 0, this.p, this.q);
            this.j = null;
         }

         ++this.e;
      }
   }

   public void q() {}

   public void r() {
      this.j = null;
   }

   public void s() {
      this.r();
   }

   public void t() {}

   public int u() {
      return this.p * this.q * 8;
   }

   public void v() {
      this.a = null;
      this.b = null;
      this.c = null;
      ++this.e;
   }

   public void w() {}

   public void x() {}

   public void y() {}

   public void z() {}

   public boolean A() {
      return false;
   }

   public ae B() {
      return this.i;
   }

   public void a(ae var1) {
      this.i = var1;
   }

   // $FF: synthetic method
   public Object clone() {
      return this.h();
   }
}
