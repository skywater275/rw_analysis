package com.corrodinggames.rts.gameFramework.k;

import android.graphics.Paint;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.k.c;
import com.corrodinggames.rts.gameFramework.k.i;
import com.corrodinggames.rts.gameFramework.k.l;
import com.corrodinggames.rts.gameFramework.k.p;
import java.util.LinkedList;

public class k {

   private l a;
   protected int e;
   protected static int f;
   public int g;
   protected short h;
   protected short i;
   protected Float j;
   protected boolean k;
   protected short l;
   protected short m;
   protected short n;
   protected ao o;
   public boolean p;
   public int q;
   public boolean r;
   public float s;
   public float t;
   public boolean u;
   protected boolean v;
   protected boolean w;
   protected LinkedList x;
   public byte[] y;
   public byte[] z;
   public byte[] A;
   public short[] B;
   public byte[] C;


   public strictfp k(l var1, boolean var2) {
      this.a = var1;
      if(var2) {
         this.e = f++;
      }

      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      this.g = var3.bx;
   }

   public strictfp void a(as var1) {
      if(this.x == null) {
         var1.a(false);
      } else {
         var1.a(true);
         var1.a("p", true);
         var1.a(this.x.size());
         if(this.x.size() != 0) {
            p var2 = (p)this.x.get(0);
            var1.a(var2.a);
            var1.a(var2.b);

            for(int var3 = 1; var3 < this.x.size(); ++var3) {
               p var4 = (p)this.x.get(var3);
               int var5 = var4.a - var2.a;
               int var6 = var4.b - var2.b;
               boolean var7 = com.corrodinggames.rts.gameFramework.f.d(var5) > 1 || com.corrodinggames.rts.gameFramework.f.d(var6) > 1;
               int var8;
               if(var7) {
                  com.corrodinggames.rts.gameFramework.l.e("writeOutCompressedPath: out of range:" + var5 + "," + var6);
                  var8 = 128;
               } else {
                  var8 = var5 + 1 + (var6 + 1 << 2);
               }

               var1.c(var8);
               if(var7) {
                  var1.a(var4.a);
                  var1.a(var4.b);
               }

               var2 = var4;
            }
         }

         var1.a("p");
      }

   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      boolean var2 = var1.e();
      if(!var2) {
         this.x = null;
      } else {
         var1.a("p", true);
         int var3 = var1.f();
         if(var3 > 160000 || var3 < 0) {
            com.corrodinggames.rts.gameFramework.l.e("readInCompressedPath: Path too big at:" + var3);
            var3 = -1;
         }

         if(var3 != -1) {
            this.u = true;
            if(this.x == null) {
               this.x = new LinkedList();
            }

            this.x.clear();
         }

         if(var3 > 0) {
            short var4 = var1.v();
            short var5 = var1.v();
            this.x.add(new p(var4, var5));

            for(int var6 = 1; var6 < var3; ++var6) {
               byte var7 = var1.d();
               byte var8 = 3;
               byte var9 = 12;
               if(var7 < 128) {
                  int var10 = (var7 & var8) - 1;
                  int var11 = ((var7 & var9) >> 2) - 1;
                  boolean var12 = com.corrodinggames.rts.gameFramework.f.d(var10) > 1 || com.corrodinggames.rts.gameFramework.f.d(var11) > 1;
                  if(var12) {
                     com.corrodinggames.rts.gameFramework.l.e("readInCompressedPath: out of range but shouldn\'t be:" + var10 + "," + var11 + " for: " + var7);
                  }

                  var4 = (short)(var4 + var10);
                  var5 = (short)(var5 + var11);
                  this.x.add(new p(var4, var5));
               } else {
                  com.corrodinggames.rts.gameFramework.l.e("readInCompressedPath: out of range unpack:" + var4 + "," + var5);
                  var4 = var1.v();
                  var5 = var1.v();
                  this.x.add(new p(var4, var5));
               }
            }
         }

         var1.d("p");
      }

   }

   public strictfp void e() {
      i var1 = this.a.a(this.o);
      if(var1 == null) {
         throw new RuntimeException("Could not get costs for:" + this.o.toString());
      } else {
         this.y = var1.d;
         this.z = var1.e;
         this.A = var1.f;
         this.B = var1.g;
         this.C = var1.j;
      }
   }

   public strictfp void f() {
      this.y = null;
      this.z = null;
      this.A = null;
      this.B = null;
      this.C = null;
   }

   public strictfp void a(ao var1, short var2, short var3, Float var4, boolean var5) {
      if(var1 == null) {
         throw new RuntimeException("MovementType is null");
      } else {
         this.o = var1;
         this.h = var2;
         this.i = var3;
         this.j = var4;
         this.k = var5;
         if(this.h < 0) {
            this.h = 0;
         }

         if(this.i < 0) {
            this.i = 0;
         }

         if(this.h > this.a.s - 1) {
            this.h = (short)(this.a.s - 1);
         }

         if(this.i > this.a.t - 1) {
            this.i = (short)(this.a.t - 1);
         }

         if(this.a.a(var1) == null) {
            throw new RuntimeException("Could not get costs for:" + var1.toString());
         }
      }
   }

   public strictfp void a(short var1, short var2, short var3) {
      if(var1 < 0) {
         var1 = 0;
      }

      if(var2 < 0) {
         var2 = 0;
      }

      if(var1 > this.a.s - 1) {
         var1 = (short)(this.a.s - 1);
      }

      if(var2 > this.a.t - 1) {
         var2 = (short)(this.a.t - 1);
      }

      this.l = var1;
      this.m = var2;
      this.n = var3;
   }

   public strictfp boolean b() {
      return false;
   }

   public strictfp boolean a(k var1) {
      return this == var1;
   }

   public strictfp c a(am var1) {
      return null;
   }

   public strictfp LinkedList a() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return !var1.bX.B && !var1.cb.i()?this.x:(this.u?this.x:null);
   }

   protected strictfp boolean c() {
      return this.x != null;
   }

   protected strictfp void a(LinkedList var1) {
      this.x = var1;
   }

   public strictfp void g() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var2 = var1.bL;
      Paint var3 = new Paint();
      var3.a(2.0F);
      var3.a(100, 0, 100, 0);
      var1.bO.a((float)(this.l * var2.n + var2.p - com.corrodinggames.rts.gameFramework.l.B().cu), (float)(this.m * var2.o + var2.q - com.corrodinggames.rts.gameFramework.l.B().cv), (float)(this.n * var2.n), var3);
      var3.a(225, 0, 0, 255);
      var1.bO.a((float)(this.h * var2.n + var2.p - com.corrodinggames.rts.gameFramework.l.B().cu), (float)(this.i * var2.o + var2.q - com.corrodinggames.rts.gameFramework.l.B().cv), (float)(this.l * var2.n + var2.p - com.corrodinggames.rts.gameFramework.l.B().cu), (float)(this.m * var2.o + var2.q - com.corrodinggames.rts.gameFramework.l.B().cv), var3);
   }

   public strictfp void h() {
      if(this.x != null) {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         com.corrodinggames.rts.game.b.b var2 = var1.bL;
         if(this.x.size() >= 1) {
            for(int var3 = 1; var3 < this.x.size(); ++var3) {
               p var4 = (p)this.x.get(var3);
               p var5 = (p)this.x.get(var3 - 1);
               Paint var6 = new Paint();
               var6.a(255, 0, 255, 0);
               var6.a(2.0F);
               int var7 = var4.a * var2.n + var2.p - com.corrodinggames.rts.gameFramework.l.B().cu;
               int var8 = var4.b * var2.o + var2.q - com.corrodinggames.rts.gameFramework.l.B().cv;
               int var9 = var5.a * var2.n + var2.p - com.corrodinggames.rts.gameFramework.l.B().cu;
               int var10 = var5.b * var2.o + var2.q - com.corrodinggames.rts.gameFramework.l.B().cv;
               var1.bO.a((float)var7, (float)var8, (float)var9, (float)var10, var6);
            }
         }
      }

   }
}
