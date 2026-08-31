package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.a$1;
import com.corrodinggames.rts.gameFramework.utility.b;

class c extends Throwable {

   // $FF: synthetic field
   final b a;


   private c(b var1, c var2) {
      super(b.a(var1), var2);
      this.a = var1;
   }

   public Throwable fillInStackTrace() {
      this.setStackTrace(b.b(this.a));
      return this;
   }

   // $FF: synthetic method
   c(b var1, c var2, a$1 var3) {
      this(var1, var2);
   }
}
