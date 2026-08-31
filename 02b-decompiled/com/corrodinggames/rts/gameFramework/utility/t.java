package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.s;
import com.corrodinggames.rts.gameFramework.utility.s$1;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class t implements Iterator {

   private int b;
   private int c;
   private int d;
   // $FF: synthetic field
   final s a;


   private t(s var1) {
      this.a = var1;
      this.b = this.a.b;
      this.c = -1;
      this.d = s.a(this.a);
   }

   public boolean hasNext() {
      return this.b != 0;
   }

   public com.corrodinggames.rts.gameFramework.w a() {
      s var1 = this.a;
      int var2 = this.b;
      if(s.b(var1) != this.d) {
         throw new ConcurrentModificationException("on:" + this.a.d + " (modCount:" + s.c(this.a) + " expectedModCount:" + this.d + ")");
      } else if(var2 == 0) {
         throw new NoSuchElementException();
      } else {
         this.b = var2 - 1;
         return var1.c[this.c = var1.b - var2];
      }
   }

   public void remove() {
      com.corrodinggames.rts.gameFramework.w[] var1 = this.a.c;
      int var2 = this.c;
      if(s.d(this.a) != this.d) {
         throw new ConcurrentModificationException("on:" + this.a.d + " (modCount:" + s.e(this.a) + " expectedModCount:" + this.d + ")");
      } else if(var2 < 0) {
         throw new IllegalStateException();
      } else {
         System.arraycopy(var1, var2 + 1, var1, var2, this.b);
         var1[--this.a.b] = null;
         this.c = -1;
         this.d = s.f(this.a);
      }
   }

   // $FF: synthetic method
   public Object next() {
      return this.a();
   }

   // $FF: synthetic method
   t(s var1, s$1 var2) {
      this(var1);
   }
}
