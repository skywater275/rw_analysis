package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad$1;

public enum ai {

   a("skirmishMap", 0),
   b("customMap", 1),
   c("savedGame", 2);
   // $FF: synthetic field
   private static final ai[] d = new ai[]{a, b, c};


   private strictfp ai(String var1, int var2) {}

   public abstract String a();

   // $FF: synthetic method
   ai(String var1, int var2, ad$1 var3) {
      this(var1, var2);
   }

}
