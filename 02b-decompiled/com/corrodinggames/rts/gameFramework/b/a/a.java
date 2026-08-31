package com.corrodinggames.rts.gameFramework.b.a;


public enum a {

   a("A_Position", 0, 1, "a_Position"),
   b("A_TexCoordinate", 1, 2, "a_TexCoordinate");
   private int c;
   private String d;
   // $FF: synthetic field
   private static final a[] e = new a[]{a, b};


   private a(String var1, int var2, int var3, String var4) {
      this.c = var3;
      this.d = var4;
   }

   public int a() {
      return this.c;
   }

   public String b() {
      return this.d;
   }

}
