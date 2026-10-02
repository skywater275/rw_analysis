package org.a.a.d;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.a.a.d.c;

public interface b extends org.a.a.a.b, c {

   org.a.a.c.b a();

   org.a.a.e.b b();

   org.a.a.e.b a(int var1);

   b a(int var1, int var2);

   @Deprecated
   Integer b(int var1);

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
