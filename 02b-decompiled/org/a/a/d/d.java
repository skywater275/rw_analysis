package org.a.a.d;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.a.a.d.c;

public interface d extends org.a.a.a.d, c {

   org.a.a.c.d a();

   org.a.a.e.d b();

   org.a.a.e.d a(int var1);

   d a(int var1, int var2);

   @Deprecated
   Short b(int var1);

   // $FF: synthetic method
   Iterator iterator() {
      return this.a();
   }

   // $FF: synthetic method
   List subList(int var1, int var2) {
      return this.a(var1, var2);
   }

   // $FF: synthetic method
   ListIterator listIterator(int var1) {
      return this.a(var1);
   }

   // $FF: synthetic method
   ListIterator listIterator() {
      return this.b();
   }

   @Deprecated
   // $FF: synthetic method
   Object remove(int var1) {
      return this.b(var1);
   }
}
