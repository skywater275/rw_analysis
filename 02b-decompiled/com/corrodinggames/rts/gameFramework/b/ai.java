package com.corrodinggames.rts.gameFramework.b;

import android.graphics.Bitmap$Config;
import com.corrodinggames.rts.gameFramework.b.ah$1;

class ai implements Cloneable {

   public boolean a;
   public Bitmap$Config b;
   public int c;


   private ai() {}

   public int hashCode() {
      int var1 = this.b.hashCode() ^ this.c;
      return this.a?var1:-var1;
   }

   public boolean equals(Object var1) {
      if(!(var1 instanceof ai)) {
         return false;
      } else {
         ai var2 = (ai)var1;
         return this.a == var2.a && this.b == var2.b && this.c == var2.c;
      }
   }

   public ai a() {
      try {
         return (ai)super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new AssertionError(var2);
      }
   }

   // $FF: synthetic method
   public Object clone() {
      return this.a();
   }

   // $FF: synthetic method
   ai(ah$1 var1) {
      this();
   }
}
