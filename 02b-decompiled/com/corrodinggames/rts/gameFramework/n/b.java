package com.corrodinggames.rts.gameFramework.n;

import com.corrodinggames.rts.gameFramework.n.a;
import java.util.Iterator;

public class b {

   com.corrodinggames.rts.gameFramework.utility.m a = new com.corrodinggames.rts.gameFramework.utility.m();
   boolean b;


   public void a(a var1) {
      this.a.add(var1);
   }

   public boolean a() {
      return this.a.a > 0;
   }

   public boolean b() {
      boolean var1 = false;
      boolean var2 = true;
      Iterator var3 = this.a.iterator();

      while(var3.hasNext()) {
         a var4 = (a)var3.next();
         if(var4.j) {
            var1 = true;
         } else {
            var2 = false;
         }
      }

      if(this.b && !var2) {
         var1 = false;
      }

      return var1;
   }
}
