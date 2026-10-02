package com.corrodinggames.rts.game.units.a;


public enum k {

   a("normal", 0),
   b("attack", 1),
   c("defend", 2),
   d("nuke", 3),
   e("build", 4),
   f("upgrade", 5),
   g("ok", 6),
   h("no", 7),
   i("happy", 8),
   j("sad", 9),
   k("retreat", 10);
   // $FF: synthetic field
   private static final k[] l = new k[]{a, b, c, d, e, f, g, h, i, j, k};


   private k(String var1, int var2) {}

   public String a() {
      return " - " + this.b();
   }

   public String b() {
      return com.corrodinggames.rts.gameFramework.h.a.a(this.c(), new Object[0]);
   }

   public String c() {
      return "menus.ingame.ping.type." + this.name();
   }

}
