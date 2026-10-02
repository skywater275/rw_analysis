package com.corrodinggames.rts.game.units.f;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.utility.u;
import java.util.Iterator;

public class f implements Iterable, Iterator {

   int a;
   am[] b;


   public boolean hasNext() {
      return this.a > 0;
   }

   public am a() {
      --this.a;
      return this.b[this.a];
   }

   public void remove() {
      throw new RuntimeException("Not supported");
   }

   public Iterator iterator() {
      return this;
   }

   public void a(u var1) {
      this.b = var1.a();
      this.a = var1.b;
   }

   // $FF: synthetic method
   public Object next() {
      return this.a();
   }
}
