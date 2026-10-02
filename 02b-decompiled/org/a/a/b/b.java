package org.a.a.b;

import java.util.Iterator;

public interface b extends Iterable {

   org.a.a.c.b a();

   // $FF: synthetic method
   Iterator iterator() {
      return this.a();
   }
}
