package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.utility.m$1;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class n implements Iterator {

   private int b;
   private int c;
   private int d;
   // $FF: synthetic field
   final m a;


   private n(m var1) {
      this.a = var1;
      this.b = this.a.a;
      this.c = -1;
      this.d = m.a(this.a);
   }

   public boolean hasNext() {
      return this.b != 0;
   }

   public Object next() {
      m var1 = this.a;
      int var2 = this.b;
      if(m.b(var1) != this.d) {
         throw new ConcurrentModificationException();
      } else if(var2 == 0) {
         throw new NoSuchElementException();
      } else {
         this.b = var2 - 1;
         return var1.b[this.c = var1.a - var2];
      }
   }

   public void remove() {
      Object[] var1 = this.a.b;
      int var2 = this.c;
      if(m.c(this.a) != this.d) {
         throw new ConcurrentModificationException();
      } else if(var2 < 0) {
         throw new IllegalStateException();
      } else {
         System.arraycopy(var1, var2 + 1, var1, var2, this.b);
         var1[--this.a.a] = null;
         this.c = -1;
         this.d = m.d(this.a);
      }
   }

   // $FF: synthetic method
   n(m var1, m$1 var2) {
      this(var1);
   }
}
