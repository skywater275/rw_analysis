package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.o;
import java.util.Iterator;

abstract class r implements Iterable, Iterator {

   public boolean b;
   final o c;
   int d;
   int e;
   boolean f = true;


   public r(o var1) {
      this.c = var1;
      this.c();
   }

   public void c() {
      this.e = -1;
      this.d = -1;
      this.d();
   }

   void d() {
      this.b = false;
      Object[] var1 = this.c.b;
      int var2 = this.c.d + this.c.e;

      while(++this.d < var2) {
         if(var1[this.d] != null) {
            this.b = true;
            break;
         }
      }

   }

   public void remove() {
      if(this.e < 0) {
         throw new IllegalStateException("next must be called before remove.");
      } else {
         if(this.e >= this.c.d) {
            this.c.a(this.e);
            this.d = this.e - 1;
            this.d();
         } else {
            this.c.b[this.e] = null;
            this.c.c[this.e] = null;
         }

         this.e = -1;
         --this.c.a;
      }
   }
}
