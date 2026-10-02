package com.corrodinggames.rts.game.units.a;

import android.graphics.Rect;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.k;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import java.util.ArrayList;
import java.util.Iterator;

public class j extends s {

   public k a;
   static ArrayList b = new ArrayList();
   static Rect c;


   public j() {
      this(k.a);
   }

   public j(k var1) {
      super("c_6_" + var1.name());
      this.a = var1;
   }

   public int b(am var1, boolean var2) {
      return -1;
   }

   public int c() {
      return 0;
   }

   public ar w() {
      return null;
   }

   public u e() {
      return u.j;
   }

   public t f() {
      return t.a;
   }

   public boolean g() {
      return false;
   }

   public String a() {
      return "Ping Map" + this.a.a();
   }

   public String b() {
      return this.a.b();
   }

   public String K() {
      return this.a.c();
   }

   public boolean h_() {
      return false;
   }

   public boolean s() {
      return true;
   }

   public static j a(c var0) {
      Iterator var1 = b.iterator();

      s var2;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         var2 = (s)var1.next();
      } while(!var2.d(var0));

      return (j)var2;
   }

   public ArrayList q(am var1) {
      return b;
   }

   public com.corrodinggames.rts.gameFramework.m.e j() {
      return com.corrodinggames.rts.gameFramework.d.c.s[9].i;
   }

   public Rect v() {
      int var1 = 7 + this.a.ordinal();
      c.a(29 * var1, 0, 29 * var1 + 28, 28);
      return c;
   }

   // $FF: synthetic method
   public as i() {
      return this.w();
   }

   static {
      k[] var0 = k.values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         k var3 = var0[var2];
         b.add(new j(var3));
      }

      c = new Rect();
   }
}
