package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.as;

public class f extends as {

   public String a = "";


   public strictfp void a(int var1) {
      this.a = this.a + "|" + var1;
      super.a(var1);
   }

   public strictfp void a(float var1) {
      this.a = this.a + "|" + var1;
      super.a(var1);
   }

   public strictfp void a(short var1) {
      this.a = this.a + "|" + var1;
      super.a(var1);
   }

   public strictfp void a(boolean var1) {
      this.a = this.a + "|" + var1;
      super.a(var1);
   }

   public strictfp void a(String var1, boolean var2) {
      this.a = this.a + "<" + var1 + ">";
      super.a(var1, var2);
   }

   public strictfp void a(String var1) {
      this.a = this.a + "</" + var1 + ">";
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.game.units.am var1) {
      this.a = this.a + "|u:" + var1;
      super.a(var1);
   }
}
