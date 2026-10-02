package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.u;
import com.corrodinggames.rts.gameFramework.utility.u$1;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class v implements Iterator {

   private int b;
   private int c;
   private int d;
   // $FF: synthetic field
   final u a;


   private v(u var1) {
      this.a = var1;
      this.b = this.a.b;
      this.c = -1;
      this.d = u.a(this.a);
   }

   public boolean hasNext() {
      return this.b != 0;
   }

   public com.corrodinggames.rts.game.units.am a() {
      u var1 = this.a;
      int var2 = this.b;
      if(u.b(var1) != this.d) {
         throw new ConcurrentModificationException();
      } else if(var2 == 0) {
         throw new NoSuchElementException();
      } else {
         this.b = var2 - 1;
         return var1.c[this.c = var1.b - var2];
      }
   }

   public void remove() {
      com.corrodinggames.rts.game.units.am[] var1 = this.a.c;
      int var2 = this.c;
      if(u.c(this.a) != this.d) {
         throw new ConcurrentModificationException();
      } else if(var2 < 0) {
         throw new IllegalStateException();
      } else {
         System.arraycopy(var1, var2 + 1, var1, var2, this.b);
         var1[--this.a.b] = null;
         this.c = -1;
         this.d = u.d(this.a);
      }
   }

   // $FF: synthetic method
   public Object next() {
      return this.a();
   }

   // $FF: synthetic method
   v(u var1, u$1 var2) {
      this(var1);
   }
}
