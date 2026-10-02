package com.corrodinggames.rts.gameFramework.k;

import com.corrodinggames.rts.gameFramework.k.a;
import com.corrodinggames.rts.gameFramework.k.a$1;
import com.corrodinggames.rts.gameFramework.k.n;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class b implements Iterator {

   private int b;
   private int c;
   private int d;
   // $FF: synthetic field
   final a a;


   private b(a var1) {
      this.a = var1;
      this.b = this.a.b;
      this.c = -1;
      this.d = a.a(this.a);
   }

   public boolean hasNext() {
      return this.b != 0;
   }

   public n a() {
      a var1 = this.a;
      int var2 = this.b;
      if(a.b(var1) != this.d) {
         throw new ConcurrentModificationException();
      } else if(var2 == 0) {
         throw new NoSuchElementException();
      } else {
         this.b = var2 - 1;
         return var1.c[this.c = var1.b - var2];
      }
   }

   public void remove() {
      n[] var1 = this.a.c;
      int var2 = this.c;
      if(a.c(this.a) != this.d) {
         throw new ConcurrentModificationException();
      } else if(var2 < 0) {
         throw new IllegalStateException();
      } else {
         System.arraycopy(var1, var2 + 1, var1, var2, this.b);
         var1[--this.a.b] = null;
         this.c = -1;
         this.d = a.d(this.a);
      }
   }

   // $FF: synthetic method
   public Object next() {
      return this.a();
   }

   // $FF: synthetic method
   b(a var1, a$1 var2) {
      this(var1);
   }
}
