package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.e;
import com.corrodinggames.rts.java.audio.a.g;
import com.corrodinggames.rts.java.audio.a.h;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class f extends h implements Iterable, Iterator {

   private g f = new g();


   public f(e var1) {
      super(var1);
   }

   public g a() {
      if(!this.a) {
         throw new NoSuchElementException();
      } else if(!this.e) {
         throw new c("#iterator() cannot be used nested.");
      } else {
         int[] var1 = this.b.b;
         if(this.c == -1) {
            this.f.a = 0;
            this.f.b = this.b.f;
         } else {
            this.f.a = var1[this.c];
            this.f.b = this.b.c[this.c];
         }

         this.d = this.c;
         this.c();
         return this.f;
      }
   }

   public boolean hasNext() {
      if(!this.e) {
         throw new c("#iterator() cannot be used nested.");
      } else {
         return this.a;
      }
   }

   public Iterator iterator() {
      return this;
   }

   public void remove() {
      super.remove();
   }

   // $FF: synthetic method
   public Object next() {
      return this.a();
   }
}
