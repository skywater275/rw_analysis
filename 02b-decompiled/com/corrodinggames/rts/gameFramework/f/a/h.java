package com.corrodinggames.rts.gameFramework.f.a;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.f.a.i;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.y;

public class h {

   public static final h j = new h();
   public static final h k = new h();
   public static final h l = new h();
   public static final h m = new h();
   public static final h n = new h();
   Paint o = new ag();
   com.corrodinggames.rts.gameFramework.m.e p;
   Paint q = new ag();
   public h r;
   public int s = 3;
   public int t = 3;
   public int u;
   public h v;
   static Rect w = new Rect();
   static Rect x = new Rect();
   static Rect y = new Rect();


   public void a(com.corrodinggames.rts.gameFramework.m.e var1) {
      this.p = var1;
   }

   public void a(h var1) {
      this.p = var1.p;
      if(var1.o != null) {
         this.o = new Paint(var1.o);
      } else {
         this.o = null;
      }

      if(var1.q != null) {
         this.q = new Paint(var1.q);
      } else {
         this.q = null;
      }

   }

   public static void b() {
      h var0 = j;
      var0.o.b(Color.a(140, 100, 100, 100));
      var0.q.b(-16777216);
      var0.q.a(Paint$Style.b);
      var0 = k;
      var0.o.b(Color.a(180, 100, 100, 190));
      var0.q.b(-16777216);
      var0.q.a(Paint$Style.b);
      var0 = l;
      var0.o = null;
      var0.q = null;
      var0 = m;
      var0.o = null;
      var0.q.b(-65536);
      var0.q.c(127);
      var0.q.a(Paint$Style.b);
      var0 = n;
      var0.o.c(255);
      var0.p = com.corrodinggames.rts.gameFramework.l.B().bS.bl;
      var0.q.b(-7829368);
      var0.q.c(255);
      var0.q.a(Paint$Style.b);
   }

   public void a(y var1, RectF var2) {
      x.a = (int)var2.a;
      x.b = (int)var2.b;
      x.c = (int)var2.c;
      x.d = (int)var2.d;
      this.a(var1, x, i.a);
   }

   public void c(y var1, Rect var2) {
      this.a(var1, var2, i.a);
   }

   public void a(y var1, Rect var2, i var3) {
      if(this.u > 0) {
         y.a(var2);
         var2 = y;
         com.corrodinggames.rts.gameFramework.f.a(var2, (float)this.u);
      }

      if(this.r != null) {
         w.a(var2);
         w.a(this.s, this.t);
         this.r.a(var1, w);
      }

      if(var3 == i.b && this.v != null) {
         this.v.a(var1, var2);
      } else {
         this.a(var1, var2);
      }
   }

   public void a(y var1, Rect var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.p != null) {
         var3.bO.a(this.p, var2, this.o, 0, 0, 0, 0);
      } else if(this.o != null) {
         var1.b(var2, this.o);
      }

      if(this.q != null) {
         var1.b(var2, this.q);
      }

   }

}
