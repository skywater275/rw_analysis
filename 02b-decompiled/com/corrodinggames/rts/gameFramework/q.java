package com.corrodinggames.rts.gameFramework;

import java.util.Iterator;

public class q {

   com.corrodinggames.rts.gameFramework.utility.m a = new com.corrodinggames.rts.gameFramework.utility.m();


   public strictfp void a(Runnable var1) {
      this.a.add(var1);
   }

   public strictfp void a() {
      if(this.a.a > 0) {
         Iterator var1 = this.a.iterator();

         while(var1.hasNext()) {
            Runnable var2 = (Runnable)var1.next();
            var2.run();
         }
      }

   }

   public strictfp void b() {
      if(this.a.a > 0) {
         Iterator var1 = this.a.iterator();

         while(var1.hasNext()) {
            Runnable var2 = (Runnable)var1.next();
            var2.run();
         }

         this.a.clear();
      }

   }
}
