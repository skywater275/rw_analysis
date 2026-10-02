package com.corrodinggames.rts.gameFramework.j;

import android.graphics.PointF;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.at;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.k;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.zip.DataFormatException;

public class as {

   ByteArrayOutputStream b;
   DataOutputStream c;
   private DataOutputStream a;
   private LinkedList e;
   public int d;


   public strictfp void a() {
      ListIterator var1 = this.e.listIterator(this.e.size());

      while(var1.hasPrevious()) {
         at var2 = (at)var1.previous();
         var2.a();
      }

      this.c.flush();
      if(this.b != null) {
         this.b.flush();
      }

   }

   strictfp void b() {
      this.a = this.c;
   }

   public strictfp as(int var1) {
      this();
      this.d = var1;
   }

   public strictfp as() {
      this.e = new LinkedList();
      this.d = 999999;
      this.b = new ByteArrayOutputStream();
      this.c = new DataOutputStream(this.b);
      this.b();
   }

   public strictfp as(DataOutputStream var1) {
      this.e = new LinkedList();
      this.d = 999999;
      this.c = var1;
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
      var3.c = this.b.toByteArray();
      var3.d = var2;
      return var3;
   }

   public strictfp String c() {
      try {
         this.a();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }

      return this.b.toString();
   }

   public strictfp byte[] d() {
      try {
         this.a();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }

      return this.b.toByteArray();
   }

   public strictfp void c(int var1) {
      this.a.writeByte(var1);
   }

   public strictfp void a(boolean var1) {
      this.a.writeBoolean(var1);
   }

   public strictfp void a(int var1) {
      this.a.writeInt(var1);
   }

   public strictfp void a(float var1) {
      this.a.writeFloat(var1);
   }

   public strictfp void a(double var1) {
      this.a.writeDouble(var1);
   }

   public strictfp void a(long var1) {
      this.a.writeLong(var1);
   }

   public strictfp void b(String var1) {
      this.a(var1 != null);
      if(var1 != null) {
         this.c(var1);
      }

   }

   public strictfp void a(Integer var1) {
      this.a(var1 != null);
      if(var1 != null) {
         this.a(var1.intValue());
      }

   }

   public strictfp void c(String var1) {
      this.a.writeUTF(var1);
   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.g var1) {
      if(var1 == null) {
         this.a.writeUTF("");
      }

      this.a.writeUTF(var1.toString());
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.w var1) {
      if(var1 == null) {
         this.a.writeLong(-1L);
      } else {
         this.a.writeLong(var1.eh);
      }

   }

   public strictfp void b(com.corrodinggames.rts.gameFramework.w var1) {
      if(var1 != null && !var1.ej) {
         this.a.writeLong(var1.eh);
      } else {
         this.a.writeLong(-1L);
      }
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.utility.m var1) {
      if(var1 == null) {
         this.a((int)0);
      } else {
         this.a(var1.size());
         Iterator var2 = var1.iterator();

         while(var2.hasNext()) {
            Object var3 = var2.next();
            com.corrodinggames.rts.gameFramework.w var4 = (com.corrodinggames.rts.gameFramework.w)var3;
            this.b(var4);
         }

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
      this.a(var1 != null);
      if(var1 != null) {
         this.a(var1.a);
         this.a(var1.b);
      }

   }

   public strictfp void a(Enum var1) {
      if(var1 == null) {
         this.a.writeInt(-1);
      } else {
         this.a.writeInt(var1.ordinal());
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.units.as var1) {
      if(var1 == null) {
         this.a.writeInt(-1);
      } else if(var1 instanceof com.corrodinggames.rts.game.units.custom.l) {
         this.a.writeInt(-2);
         this.c(((com.corrodinggames.rts.game.units.custom.l)var1).M);
      } else {
         this.a.writeInt(((com.corrodinggames.rts.game.units.ar)var1).ordinal());
      }

   }

   public strictfp void a(c var1) {
      if(var1 == null) {
         this.a.writeInt(0);
      } else {
         this.a.writeInt(var1.c);
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.n var1) {
      this.a.writeByte(var1.k);
   }

   public strictfp void a(File var1) {
      com.corrodinggames.rts.gameFramework.utility.j var2 = com.corrodinggames.rts.gameFramework.e.a.a(var1);
      if(var2 == null) {
         throw new IOException("Failed to read save file data");
      } else {
         try {
            this.a(var2, (int)var1.length());
         } finally {
            if(var2 != null) {
               var2.close();
            }

         }

      }
   }

   public strictfp void a(k var1) {
      InputStream var2 = var1.w();

      try {
         var2.reset();
      } catch (IOException var4) {
         var4.printStackTrace();
      }

      this.a(var2, var2.available());
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

            this.a.write(var5, 0, var6);
            return;
         }

         this.a.write(var5, 0, var4);
      }

   }

   public strictfp void a(ByteArrayOutputStream var1) {
      this.a(var1.size());
      var1.writeTo(this.a);
   }

   public strictfp void a(byte[] var1) {
      this.a(var1.length);
      this.a.write(var1);
   }

   public strictfp void b(byte[] var1) {
      this.a.write(var1);
   }

   public strictfp void a(short var1) {
      this.a.writeShort(var1);
   }

   public strictfp void e() {
      this.a((short)12345);
   }

   public strictfp void d(String var1) {}

   public strictfp boolean f() {
      return false;
   }

   public strictfp void e(String var1) {
      this.a(var1, false);
   }

   public strictfp void a(String var1, boolean var2) {
      at var3 = new at(var2);
      var3.c = var1;
      this.e.add(var3);
      this.a = ((at)this.e.getLast()).e;
   }

   public strictfp void a(String var1) {
      at var2 = (at)this.e.removeLast();
      if(!var2.c.equals(var1)) {
         com.corrodinggames.rts.gameFramework.l.b("OutputNetStream:endBlock", "Name does not match: expected" + var1 + " , got:" + var2.c);
      }

      var2.a();
      if(this.e.isEmpty()) {
         this.a = this.c;
      } else {
         this.a = ((at)this.e.getLast()).e;
      }

      this.a.writeUTF(var2.c);
      this.a(var2.d);

      try {
         var2.b();
      } catch (Exception var4) {
         if(var4 instanceof DataFormatException) {
            if(!com.corrodinggames.rts.gameFramework.l.aZ) {
               com.corrodinggames.rts.gameFramework.l.b("DataFormatException error calling streamBlock.close() (this is expected on android 4.4)");
            }
         } else {
            com.corrodinggames.rts.gameFramework.l.b("Error calling streamBlock.close() to clean up memory");
            var4.printStackTrace();
         }
      }

   }

   public strictfp int g() {
      return this.d;
   }

   public strictfp void h() {
      this.c = null;
      this.a = null;

      try {
         if(this.b != null) {
            this.b.close();
         }
      } catch (IOException var2) {
         var2.printStackTrace();
      }

      this.b = null;
   }
}
