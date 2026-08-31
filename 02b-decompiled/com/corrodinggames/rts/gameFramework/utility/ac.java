package com.corrodinggames.rts.gameFramework.utility;


public final class ac {

   String a;
   String b;


   public ac(String var1, String var2) {
      this.a = var1;
      this.b = var2;
   }

   public String toString() {
      return "[" + this.a + "]" + this.b;
   }

   public boolean equals(Object var1) {
      if(!(var1 instanceof ac)) {
         return false;
      } else {
         ac var2 = (ac)var1;
         return this.b.equals(var2.b) && this.a.equals(var2.a);
      }
   }

   public String a() {
      return this.a;
   }

   public String b() {
      return this.b;
   }
}
