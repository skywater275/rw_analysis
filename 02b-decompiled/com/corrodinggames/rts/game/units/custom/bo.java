package com.corrodinggames.rts.game.units.custom;


public class bo extends Exception {

   public String b;
   public String c;
   public String d;


   public bo(String var1, String var2) {
      super(var1);
      this.b = var2;
   }

   public bo(String var1) {
      super(var1);
   }

   public bo(String var1, String var2, String var3) {
      super(var1);
      this.c = var2;
      this.d = var3;
   }

   public bo(String var1, Exception var2) {
      super(var1, var2);
   }
}
