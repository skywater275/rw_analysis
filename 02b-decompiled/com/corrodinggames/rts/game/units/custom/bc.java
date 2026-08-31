package com.corrodinggames.rts.game.units.custom;


public class bc {

   public String a;
   public String b;


   public bc() {}

   public bc(String var1, String var2) {
      this.a = var1;
      this.b = var2;
   }

   public void a(String var1, String var2) {
      if(this.b != null) {
         this.b = this.b.replaceAll(var1, var2);
      }

   }
}
