package com.corrodinggames.rts.gameFramework.n;

import com.corrodinggames.rts.gameFramework.n.e$1;

public enum e {

   a("objective", 0),
   b("event_move", 1),
   c("event_changeCredits", 2),
   d("event_teamTags", 3),
   e("event_unitAdd", 4),
   f("event_unitRemove", 5),
   g("mapText", 6),
   h("moveCamera", 7),
   i("trigger_unitDetect", 8),
   j("trigger_teamTagDetect", 9),
   k("trigger_basic", 10);
   // $FF: synthetic field
   private static final e[] l = new e[]{a, b, c, d, e, f, g, h, i, j, k};


   private e(String var1, int var2) {}

   public abstract String a();

   public static e a(String var0) {
      e[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         e var4 = var1[var3];
         if(var4.a().equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      return null;
   }

   // $FF: synthetic method
   e(String var1, int var2, e$1 var3) {
      this(var1, var2);
   }

}
