package com.corrodinggames.rts.gameFramework.j;

import android.graphics.PointF;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.ax;
import com.corrodinggames.rts.gameFramework.j.c;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.ListIterator;

public class aw extends as {

   ByteArrayOutputStream a;
   PrintStream e;
   private PrintStream f;
   private LinkedList g = new LinkedList();


   public strictfp void a() {
      ListIterator var1 = this.g.listIterator(this.g.size());

      while(var1.hasPrevious()) {
         ax var2 = (ax)var1.previous();
         var2.a();
      }

      this.e.flush();
      if(this.a != null) {
         this.a.flush();
      }

   }

   strictfp void b() {
      this.f = this.e;
   }

   public strictfp aw() {
      this.a = new ByteArrayOutputStream();
      this.e = new PrintStream(this.a);
      this.b();
   }

   public strictfp aw(PrintStream var1) {
      this.e = var1;
      this.b();
   }

   public strictfp au b(int var1) {
      return this.a(var1, -1);
   }

   public strictfp au a(int var1, int var2) {
      try {
         this.a();
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }

      au var3 = new au(var1);
      var3.c = this.a.toByteArray();
      var3.d = var2;
      return var3;
   }

   public strictfp String c() {
      try {
         this.a();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }

      return this.a.toString();
   }

   public strictfp byte[] d() {
      try {
         this.a();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }

      return this.a.toByteArray();
   }

   public strictfp void c(int var1) {
      this.f.println(var1);
   }

   public strictfp void a(boolean var1) {
      this.f.println(var1);
   }

   public strictfp void a(int var1) {
      this.f.println("#int:");
      this.f.println(var1);
   }

   public strictfp void a(float var1) {
      this.f.println("#writeFloat");
      this.f.println(var1);
   }

   public strictfp void a(long var1) {
      this.f.println("#writeLong");
      this.f.println(var1);
   }

   public strictfp void b(String var1) {
      this.a(var1 != null);
      if(var1 != null) {
         this.c(var1);
      }

   }

   public strictfp void c(String var1) {
      this.f.println(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.w var1) {
      this.f.println("#writeGameObject:");
      if(var1 == null) {
         this.f.println(-1);
      } else {
         this.f.println(var1.eh);
      }

   }

   public strictfp void b(com.corrodinggames.rts.gameFramework.w var1) {
      this.f.println("#writeExistingGameObject:");
      if(var1 != null && !var1.ej) {
         this.f.println(var1.eh);
      } else {
         this.f.println(-1);
      }
   }

   public strictfp void b(com.corrodinggames.rts.game.units.am var1) {
      if(var1 != null && !var1.ej && !var1.bV) {
         this.a((com.corrodinggames.rts.gameFramework.w)var1);
      } else {
         this.a((com.corrodinggames.rts.gameFramework.w)null);
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.units.am var1) {
      if(var1 != null && !var1.ej) {
         this.a((com.corrodinggames.rts.gameFramework.w)var1);
      } else {
         this.a((com.corrodinggames.rts.gameFramework.w)null);
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.units.y var1) {
      if(var1 != null && !var1.ej) {
         this.a((com.corrodinggames.rts.gameFramework.w)var1);
      } else {
         this.a((com.corrodinggames.rts.gameFramework.w)null);
      }
   }

   public strictfp void a(PointF var1) {
      this.f.println("#PointF:");
      this.a(var1 != null);
      if(var1 != null) {
         this.a(var1.a);
         this.a(var1.b);
      }

   }

   public strictfp void a(Enum var1) {
      if(var1 == null) {
         this.f.println("#Enum: null");
         this.f.println(-1);
      } else {
         this.f.println("#Enum:" + var1.getClass().getSimpleName() + " : " + var1.toString());
         this.f.println(var1.ordinal());
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.units.as var1) {
      this.f.println("#unitType:");
      if(var1 == null) {
         this.f.println(-1);
      } else if(var1 instanceof com.corrodinggames.rts.game.units.custom.l) {
         this.f.println(-2);
         this.c(((com.corrodinggames.rts.game.units.custom.l)var1).M);
      } else {
         this.f.println(((com.corrodinggames.rts.game.units.ar)var1).ordinal());
      }

   }

   public strictfp void a(c var1) {
      if(var1 == null) {
         this.f.println(0);
      } else {
         this.f.println(var1.c);
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.n var1) {
      this.f.println("#team:");
      this.f.println(var1.k);
   }

   public strictfp void a(File var1) {
      com.corrodinggames.rts.gameFramework.utility.j var2 = com.corrodinggames.rts.gameFramework.e.a.a(var1);

      try {
         this.a(var2, (int)var1.length());
      } finally {
         var2.close();
      }

   }

   public strictfp void a(InputStream var1, int var2) {
      int var3 = 0;
      this.a(var2);

      int var4;
      for(byte[] var5 = new byte[16384]; (var4 = var1.read(var5, 0, var5.length)) != -1; var3 += var4) {
         if(var3 + var4 > var2) {
            int var6 = var2 - var3;
            if(var6 < 0) {
               ad.g("writeStream: bytesTillFull is " + var6);
               return;
            }

            this.f.write(var5, 0, var6);
            return;
         }

         this.f.write(var5, 0, var4);
      }

   }

   public strictfp void a(ByteArrayOutputStream var1) {
      this.a(var1.size());
      var1.writeTo(this.f);
   }

   public strictfp void a(byte[] var1) {
      this.a(var1.length);
      this.f.write(var1);
   }

   public strictfp void a(short var1) {
      this.f.println("#writeShort");
      this.f.println(var1);
   }

   public strictfp void e() {
      this.f.println("#writeMark:");
      this.a((short)12345);
   }

   public strictfp void d(String var1) {
      this.f.println("#writeIfDebugOnly: " + var1);
   }

   public strictfp boolean f() {
      return true;
   }

   public strictfp void e(String var1) {
      this.a(var1, false);
   }

   public strictfp void a(String var1, boolean var2) {
      ax var3 = new ax(var2);
      var3.b = var1;
      this.g.add(var3);
      this.f = ((ax)this.g.getLast()).d;
   }

   public strictfp void a(String var1) {
      ax var2 = (ax)this.g.removeLast();
      if(!var2.b.equals(var1)) {
         com.corrodinggames.rts.gameFramework.l.b("OutputNetStream:endBlock", "Name does not match: expected" + var1 + " , got:" + var2.b);
      }

      var2.a();
      if(this.g.isEmpty()) {
         this.f = this.e;
      } else {
         this.f = ((ax)this.g.getLast()).d;
      }

      String var3 = "";
      String var4 = "";

      for(int var5 = 0; var5 < this.g.size(); ++var5) {
         var3 = var3 + ">";
         var4 = var4 + "<";
      }

      this.f.println(var3 + ">>>> Start of block: " + var2.b);
      this.a(var2.c);
      this.f.println(var4 + "<<<< End of block: " + var2.b);
      var2.b();
   }
}
