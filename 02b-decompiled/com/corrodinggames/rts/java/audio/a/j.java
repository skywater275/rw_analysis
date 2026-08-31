package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.i;
import com.corrodinggames.rts.java.audio.a.k;
import com.corrodinggames.rts.java.audio.a.l;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class j extends l implements Iterable, Iterator {

   private k f = new k();


   public j(i var1) {
      super(var1);
   }

   public k a() {
      if(!this.a) {
         throw new NoSuchElementException();
      } else if(!this.e) {
         throw new c("#iterator() cannot be used nested.");
      } else {
         long[] var1 = this.b.b;
         if(this.c == -1) {
            this.f.a = 0L;
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
