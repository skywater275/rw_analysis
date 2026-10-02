package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.o;
import com.corrodinggames.rts.gameFramework.utility.o$1;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class p implements Iterator {

   private int b;
   private int c;
   private int d;
   // $FF: synthetic field
   final o a;


   private p(o var1) {
      this.a = var1;
      this.b = this.a.c;
      this.c = -1;
      this.d = o.a(this.a);
   }

   public boolean hasNext() {
      return this.b != 0;
   }

   public Object next() {
      o var1 = this.a;
      int var2 = this.b;
      if(o.b(var1) != this.d) {
         throw new ConcurrentModificationException();
      } else if(var2 == 0) {
         throw new NoSuchElementException();
      } else {
         this.b = var2 - 1;
         return var1.d[this.c = var1.c - var2];
      }
   }

   public void remove() {
      Object[] var1 = this.a.d;
      int var2 = this.c;
      if(o.c(this.a) != this.d) {
         throw new ConcurrentModificationException();
      } else if(var2 < 0) {
         throw new IllegalStateException();
      } else {
         System.arraycopy(var1, var2 + 1, var1, var2, this.b);
         var1[--this.a.c] = null;
         this.c = -1;
         this.d = o.d(this.a);
      }
   }

   // $FF: synthetic method
   p(o var1, o$1 var2) {
      this(var1);
   }
}
