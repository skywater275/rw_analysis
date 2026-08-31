package com.corrodinggames.rts.gameFramework.b;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import com.corrodinggames.rts.gameFramework.b.a;
import com.corrodinggames.rts.gameFramework.b.af;
import com.corrodinggames.rts.gameFramework.b.ah;
import com.corrodinggames.rts.gameFramework.b.b;
import com.corrodinggames.rts.gameFramework.b.e;
import com.corrodinggames.rts.gameFramework.b.f$1;
import com.corrodinggames.rts.gameFramework.b.g;
import com.corrodinggames.rts.gameFramework.b.i;
import com.corrodinggames.rts.gameFramework.b.k;
import com.corrodinggames.rts.gameFramework.b.n;
import com.corrodinggames.rts.gameFramework.b.v;
import java.util.Iterator;
import java.util.Map;

public class f {

   protected final k a;
   private Map b;
   private a c;
   private g d;
   private af e;


   public void a(b var1) {
      this.a.c(var1);
   }

   public void a() {
      this.a.d();
   }

   public k b() {
      return this.a;
   }

   public b a(Bitmap var1, com.corrodinggames.rts.gameFramework.m.e var2, af var3) {
      this.e = var3;
      b var4 = this.a(var1, var2);
      if(var3 instanceof i) {
         i var5 = (i)var3;
         var4 = var5.a(var4, this.a, new f$1(this));
      }

      return var4;
   }

   public void a(Bitmap var1) {
      b var2 = (b)this.b.get(var1);
      if(var2 != null && var2 instanceof ah) {
         ((ah)var2).l();
      }

      this.b().a(var1);
   }

   public b a(Bitmap var1, com.corrodinggames.rts.gameFramework.m.e var2) {
      Object var3 = (b)this.b.get(var1);
      if(var3 == null) {
         this.a.e();
         this.c();
         var3 = new e(var1);
         ((b)var3).c(this.b());
         ((b)var3).j = var2.d();
         n.b(((b)var3).e, ((b)var3).f);
         this.b.put(var1, var3);
         this.d();
      }

      return (b)var3;
   }

   public void a(float var1, float var2, float var3, v var4) {
      if(var4.c() == Paint$Style.a) {
         this.d.a(0.5F);
      } else {
         float var5 = var4.b();
         if(var5 == 0.0F) {
            var5 = 1.0F;
         }

         this.d.a(var5 / (2.0F * var3));
      }

      this.a.a(var1 - var3, var2 - var3, var3, var4, this.d);
   }

   public void a(float var1, float var2, float var3, float var4, v var5) {
      this.a.a(var1, var2, var3, var4, var5, this.c);
   }

   public void c() {
      this.a.b();
   }

   public void d() {
      this.a.c();
   }

   public void e() {
      Iterator var1 = this.b.values().iterator();

      while(var1.hasNext()) {
         b var2 = (b)var1.next();
         var2.j();
      }

      this.b.clear();
   }

   protected void finalize() {
      super.finalize();
      this.e();
   }

   public void a(int var1, int var2, int var3, int var4) {
      this.a.a(var1, var2, var3, var4);
   }

   public void a(String var1, float var2, float var3, Paint var4) {
      this.a.a(var1, var2, var3, var4);
   }

   public void a(float[] var1, int var2, int var3, v var4) {
      this.a.a(var1, var2, var3, var4, this.c);
   }
}
