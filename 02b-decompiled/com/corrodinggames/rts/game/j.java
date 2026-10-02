package com.corrodinggames.rts.game;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.y;
import java.io.IOException;

public class j {

   public com.corrodinggames.rts.gameFramework.m.e a;
   y b;
   ag c;
   ae d;
   Paint e;
   Rect f;
   boolean g;


   public strictfp j() {
      this.e = new Paint();
      this.f = new Rect(-101, 0, -1, 100);
      this.c = new ag();
   }

   public strictfp j(String var1) {
      this();

      try {
         this.d = new ae(var1);
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }

      this.c.a(this.d);
      if(this.d.o != 0) {
         this.g = true;
      }

   }

   public strictfp boolean a() {
      return this.d != null && this.d.o != 0?true:this.g;
   }

   public strictfp void a(y var1) {
      this.a(var1, var1.m(), var1.n(), 10);
   }

   public strictfp void a(y var1, int var2, int var3, int var4) {
      if(!this.g) {
         if(this.a != null && (var2 > this.a.m() || var3 > this.a.l())) {
            this.a.o();
            this.a = null;
            this.b = null;
         }

         if(this.a == null) {
            try {
               this.a = var1.a(var2 + var4, var3 + var4, true);
               this.b = var1.a(this.a);
            } catch (OutOfMemoryError var6) {
               this.g = true;
               com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.b, (Throwable)var6);
               return;
            }
         }

         this.b.a(var2, var3);
      }
   }

   public strictfp void b() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bO.b(this.f, this.e);
      var1.bO.b(this.a, 0.0F, 0.0F, this.c);
   }
}
