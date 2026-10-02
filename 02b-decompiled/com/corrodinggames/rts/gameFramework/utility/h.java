package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.g;
import com.corrodinggames.rts.gameFramework.utility.g$1;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class h implements Iterator {

   private int b;
   private int c;
   private int d;
   // $FF: synthetic field
   final g a;


   private h(g var1) {
      this.a = var1;
      this.b = g.a(this.a);
      this.c = g.b(this.a);
      this.d = -1;
   }

   public boolean hasNext() {
      return this.b != this.c;
   }

   public Object next() {
      if(this.b == this.c) {
         throw new NoSuchElementException();
      } else {
         Object var1 = g.c(this.a)[this.b];
         if(g.b(this.a) == this.c && var1 != null) {
            this.d = this.b;
            this.b = this.b + 1 & g.c(this.a).length - 1;
            return var1;
         } else {
            throw new ConcurrentModificationException();
         }
      }
   }

   public void remove() {
      if(this.d < 0) {
         throw new IllegalStateException();
      } else {
         if(g.a(this.a, this.d)) {
            this.b = this.b - 1 & g.c(this.a).length - 1;
            this.c = g.b(this.a);
         }

         this.d = -1;
      }
   }

   // $FF: synthetic method
   h(g var1, g$1 var2) {
      this(var1);
   }
}
