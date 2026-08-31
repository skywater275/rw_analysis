package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public final class q {

   public final com.corrodinggames.rts.gameFramework.utility.m a = new com.corrodinggames.rts.gameFramework.utility.m();
   public int b;
   public Class c;


   public q(Class var1) {
      this.c = var1;
   }

   public final Rect a(Rect var1) {
      if(this.b >= this.a.a) {
         this.a.add(new Rect());
      }

      Rect var2 = (Rect)this.a.a(this.b);
      var2.b = var1.b;
      var2.d = var1.d;
      var2.a = var1.a;
      var2.c = var1.c;
      ++this.b;
      return var2;
   }

   public final RectF a(RectF var1) {
      if(this.b >= this.a.a) {
         this.a.add(new RectF());
      }

      RectF var2 = (RectF)this.a.a(this.b);
      var2.b = var1.b;
      var2.d = var1.d;
      var2.a = var1.a;
      var2.c = var1.c;
      ++this.b;
      return var2;
   }

   public final Paint a(Paint var1) {
      if(var1 == null) {
         return null;
      } else {
         if(this.b >= this.a.a) {
            this.a.add(new Paint());
         }

         Paint var2 = (Paint)this.a.a(this.b);
         var2.a(var1);
         ++this.b;
         return var2;
      }
   }
}
