package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.m.af;
import com.corrodinggames.rts.gameFramework.m.e;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ae {

   public String c;
   public String d = "";
   public String e;
   public String f;
   public int g;
   public int h;
   String i;
   String j;
   long k;
   long l;
   public boolean m;
   public int n;
   public int o;
   public af[] p = new af[0];
   public Object q;
   public int r;
   int s;


   public void a(String var1, float var2) {
      this.a(var1).a(var2);
   }

   public void a(String var1, float var2, float var3) {
      this.a(var1).a(var2, var3);
   }

   public void a(String var1, int var2) {
      float var3 = (float)(var2 >> 16 & 255) * 0.003921569F;
      float var4 = (float)(var2 >> 8 & 255) * 0.003921569F;
      float var5 = (float)(var2 & 255) * 0.003921569F;
      float var6 = (float)(var2 >>> 24) * 0.003921569F;
      this.a(var1).a(var3, var4, var5, var6);
   }

   public void a(String var1, e var2) {
      af var3 = this.a(var1);
      var3.a(var2);
   }

   public void b(String var1, e var2) {
      af var3 = this.a(var1);
      var3.b(var2);
   }

   public af a(String var1) {
      af[] var2 = this.p;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         af var5 = var2[var4];
         if(var5.a.equals(var1)) {
            return var5;
         }
      }

      af var6 = new af();
      var6.a = var1;
      af[] var7 = (af[])Arrays.copyOf(this.p, this.p.length + 1);
      var7[var7.length - 1] = var6;
      this.p = var7;
      return var6;
   }

   public ae(String var1) {
      String var2 = "assets/shaders/plain.vert";
      if(com.corrodinggames.rts.gameFramework.l.aY) {
         var2 = "assets/shaders/plainGDX.vert";
      }

      this.a(var2, var1);
   }

   public void a(String var1, String var2) {
      this.c = com.corrodinggames.rts.gameFramework.f.g(var2);
      this.i = var1;
      this.j = var2;
      this.d();
      this.e();
   }

   public ae() {
      this.c = "Invalid";
      this.o = 1;
   }

   public void d() {
      com.corrodinggames.rts.gameFramework.utility.j var1 = com.corrodinggames.rts.gameFramework.e.a.k(this.i);
      if(var1 == null) {
         throw new IOException("Cannot find: " + this.i);
      } else {
         this.e = com.corrodinggames.rts.gameFramework.f.a((InputStream)var1);
         com.corrodinggames.rts.gameFramework.utility.j var2 = com.corrodinggames.rts.gameFramework.e.a.k(this.j);
         if(var2 == null) {
            throw new IOException("Cannot find: " + this.j);
         } else {
            this.f = com.corrodinggames.rts.gameFramework.f.a((InputStream)var2);
         }
      }
   }

   public void b(String var1) {
      com.corrodinggames.rts.gameFramework.l.e("shader(" + this.c + "): " + var1);
   }

   public void c(String var1) {
      if(this.r < 3) {
         ++this.r;
         com.corrodinggames.rts.gameFramework.l.p("shader(" + this.c + "): " + var1);
      }

      com.corrodinggames.rts.gameFramework.l.a("shader(" + this.c + "): " + var1);
      this.o = 1;
   }

   public boolean a() {
      return false;
   }

   public boolean b() {
      return false;
   }

   public boolean a(Paint var1, e var2) {
      return false;
   }

   public boolean e() {
      long var1 = com.corrodinggames.rts.gameFramework.j.a(this.i, false);
      long var3 = com.corrodinggames.rts.gameFramework.j.a(this.j, false);
      boolean var5 = var1 != this.k || var3 != this.l;
      this.k = var1;
      this.l = var3;
      return var5;
   }

   public void f() {
      ++this.s;
      if(this.s >= 100) {
         this.s = 0;
         if(this.e()) {
            this.b("Reloading shader");

            try {
               this.d();
               this.m = true;
               this.o = 0;
               af[] var1 = this.p;
               int var2 = var1.length;

               for(int var3 = 0; var3 < var2; ++var3) {
                  af var4 = var1[var3];
                  var4.c = true;
                  var4.b = -1;
               }
            } catch (IOException var5) {
               var5.printStackTrace();
            }
         }

      }
   }

   public void c() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bO.a(this);
   }
}
