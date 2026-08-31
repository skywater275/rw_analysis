package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.o;
import com.corrodinggames.rts.java.audio.a.q;
import com.corrodinggames.rts.java.audio.a.r;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class p extends r {

   q a = new q();


   public p(o var1) {
      super(var1);
   }

   public q a() {
      if(!this.b) {
         throw new NoSuchElementException();
      } else if(!this.f) {
         throw new c("#iterator() cannot be used nested.");
      } else {
         Object[] var1 = this.c.b;
         this.a.a = var1[this.d];
         this.a.b = this.c.c[this.d];
         this.e = this.d;
         this.d();
         return this.a;
      }
   }

   public boolean hasNext() {
      if(!this.f) {
         throw new c("#iterator() cannot be used nested.");
      } else {
         return this.b;
      }
   }

   public p b() {
      return this;
   }

   // $FF: synthetic method
   public Iterator iterator() {
      return this.b();
   }

   // $FF: synthetic method
   public Object next() {
      return this.a();
   }
}
