package com.corrodinggames.rts.gameFramework.j;

import android.graphics.PointF;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.l;
import com.corrodinggames.rts.gameFramework.j.m;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class k {

   ByteArrayInputStream a;
   private DataInputStream e;
   private DataInputStream f;
   private LinkedList g = new LinkedList();
   int b = 999999;
   int c = 999999;
   int d = 0;


   strictfp void a() {
      this.f = this.e;
   }

   public strictfp k(au var1) {
      this.a = new ByteArrayInputStream(var1.c);
      this.e = new DataInputStream(this.a);
      this.a();
   }

   public strictfp k(DataInputStream var1) {
      this.e = var1;
      this.a();
   }

   public strictfp k(String var1) {
      this.a = new ByteArrayInputStream(var1.getBytes());
      this.e = new DataInputStream(this.a);
      this.a();
   }

   public strictfp k(byte[] var1) {
      this.a = new ByteArrayInputStream(var1);
      this.e = new DataInputStream(this.a);
      this.a();
   }

   public strictfp void a(int var1) {
      this.b = var1;
   }

   public strictfp int b() {
      return this.b;
   }

   public strictfp void b(int var1) {
      this.c = var1;
   }

   public strictfp int c() {
      return this.c;
   }

   public strictfp byte d() {
      return this.f.readByte();
   }

   public strictfp boolean e() {
      return this.f.readBoolean();
   }

   public strictfp int f() {
      return this.f.readInt();
   }

   public strictfp float g() {
      return this.f.readFloat();
   }

   public strictfp double h() {
      return this.f.readDouble();
   }

   public strictfp long i() {
      return this.f.readLong();
   }

   public strictfp String j() {
      return !this.e()?null:this.l();
   }

   public strictfp Integer k() {
      return !this.e()?null:Integer.valueOf(this.f());
   }

   public strictfp String l() {
      String var1 = this.f.readUTF();
      return var1;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.g m() {
      String var1 = this.f.readUTF();
      return var1.equals("")?null:com.corrodinggames.rts.game.units.custom.g.c(var1);
   }

   public strictfp long n() {
      long var1 = this.f.readLong();
      return var1;
   }

   public strictfp com.corrodinggames.rts.gameFramework.w a(Class var1) {
      long var2 = this.f.readLong();
      return com.corrodinggames.rts.gameFramework.w.a(var2, var1, false);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.utility.m var1, Class var2) {
      int var3 = this.f();

      for(int var4 = 0; var4 < var3; ++var4) {
         com.corrodinggames.rts.gameFramework.w var5 = this.a(var2);
         if(var5 != null) {
            var1.add(var5);
         }
      }

   }

   public strictfp com.corrodinggames.rts.game.units.am o() {
      return this.a(m.b);
   }

   public strictfp com.corrodinggames.rts.game.units.am a(m var1) {
      long var2 = this.f.readLong();
      boolean var4 = var1 == m.a;
      return com.corrodinggames.rts.gameFramework.w.a(var2, var4);
   }

   public strictfp com.corrodinggames.rts.game.units.y p() {
      long var1 = this.f.readLong();
      return com.corrodinggames.rts.gameFramework.w.b(var1, false);
   }

   public strictfp Enum b(Class var1) {
      int var2 = this.f.readInt();
      if(var2 == -1) {
         return null;
      } else {
         Object[] var3 = var1.getEnumConstants();
         if(var2 >= 0 && var2 < var3.length) {
            return (Enum)var3[var2];
         } else {
            ad.g("readEnum:" + var2 + " is out of range for " + var1.toString());
            return null;
         }
      }
   }

   public strictfp com.corrodinggames.rts.game.units.as q() {
      int var1 = this.f.readInt();
      if(var1 == -1) {
         return null;
      } else if(var1 == -2) {
         String var5 = this.l();
         com.corrodinggames.rts.game.units.custom.l var3 = com.corrodinggames.rts.game.units.custom.l.n(var5);
         if(var3 == null) {
            ad.g("readUnitType: Could not find customUnitMetadata:" + var5);
         }

         com.corrodinggames.rts.game.units.as var4 = com.corrodinggames.rts.game.units.custom.l.c((com.corrodinggames.rts.game.units.as)var3);
         if(var4 != null) {
            if(var4 instanceof com.corrodinggames.rts.game.units.custom.l) {
               var3 = (com.corrodinggames.rts.game.units.custom.l)var4;
            } else {
               com.corrodinggames.rts.gameFramework.l.b("replacement not a custom unit:" + var4.i());
            }
         }

         return var3;
      } else {
         Object[] var2 = com.corrodinggames.rts.game.units.ar.class.getEnumConstants();
         if(var1 >= 0 && var1 < var2.length) {
            return (com.corrodinggames.rts.game.units.ar)var2[var1];
         } else {
            ad.g("readUnitType:" + var1 + " is out of range for UnitType");
            return null;
         }
      }
   }

   public strictfp com.corrodinggames.rts.game.n r() {
      byte var1 = this.f.readByte();
      com.corrodinggames.rts.game.n var2 = com.corrodinggames.rts.game.n.k(var1);
      if(var2 == null) {
         throw new IOException("Error loading save data, could not find referenced team:" + var1 + "");
      } else {
         return var2;
      }
   }

   public strictfp com.corrodinggames.rts.game.n s() {
      byte var1 = this.f.readByte();
      return com.corrodinggames.rts.game.n.k(var1);
   }

   public strictfp byte[] t() {
      int var1 = 0;
      int var2 = this.f();

      byte[] var3;
      int var4;
      for(var3 = new byte[var2]; var1 < var2; var1 += var4) {
         var4 = this.f.read(var3, var1, var2 - var1);
         if(var4 == -1) {
            break;
         }
      }

      return var3;
   }

   public strictfp k u() {
      byte[] var1 = this.t();
      return new k(var1);
   }

   public strictfp short v() {
      return this.f.readShort();
   }

   public strictfp void a(String var1) {
      short var2 = this.v();
      if(var2 != 12345) {
         ad.g("Mark wasn\'t read for:" + var1);
         if(com.corrodinggames.rts.gameFramework.l.B().aa()) {
            throw new RuntimeException("Mark wasn\'t read for:" + var1);
         }
      }

   }

   public strictfp InputStream w() {
      return this.f;
   }

   public strictfp void b(String var1) {
      this.a(var1, false);
   }

   public strictfp String x() {
      return this.a(false, false);
   }

   public strictfp void a(String var1, boolean var2) {
      this.a(var1, var2, false);
   }

   public strictfp void a(String var1, boolean var2, boolean var3) {
      if(this.b < 11) {
         com.corrodinggames.rts.gameFramework.l.e("Skipping start block:" + var1);
      } else {
         String var4 = this.a(var2, var3);
         if(!var4.equals(var1)) {
            com.corrodinggames.rts.gameFramework.l.b("InputNetStream:endBlock", "Name does not match: expected:" + var1 + " , got:" + var4);
         }

      }
   }

   public strictfp byte[] c(String var1) {
      String var2 = this.f.readUTF();
      if(!var2.equals(var1)) {
         com.corrodinggames.rts.gameFramework.l.b("getBlockRaw", "Name does not match: expected:" + var1 + " , got:" + var2);
      }

      byte[] var3 = this.t();
      return var3;
   }

   public strictfp String a(boolean var1, boolean var2) {
      if(this.b < 11) {
         com.corrodinggames.rts.gameFramework.l.e("Skipping start block: startBlockAndGetName()");
         return "<skipped>";
      } else {
         String var3 = this.f.readUTF();
         byte[] var4 = this.t();
         l var5 = new l(var4, var1, var2);
         var5.a = var3;
         this.g.add(var5);
         this.f = ((l)this.g.getLast()).c;
         return var3;
      }
   }

   public strictfp void d(String var1) {
      if(this.b < 11) {
         com.corrodinggames.rts.gameFramework.l.e("Skipping end block:" + var1);
      } else {
         l var2 = (l)this.g.removeLast();
         if(!var2.a.equals(var1)) {
            com.corrodinggames.rts.gameFramework.l.b("InputNetStream:endBlock", "Name does not match: expected" + var1 + " ," + var2.a);
         }

         if(this.g.isEmpty()) {
            this.f = this.e;
         } else {
            this.f = ((l)this.g.getLast()).c;
         }

      }
   }

   public strictfp PointF y() {
      if(!this.e()) {
         return null;
      } else {
         PointF var1 = new PointF();
         var1.a = this.g();
         var1.b = this.g();
         return var1;
      }
   }
}
