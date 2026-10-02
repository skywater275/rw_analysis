package com.corrodinggames.rts.game.units.custom.f;

import java.util.HashMap;

public class d {

   HashMap a = new HashMap();


   public void a(String var1, String var2) {
      this.a.put(var1, var2);
   }

   public String a(String var1) {
      return (String)this.a.get(var1);
   }

   public void a() {
      this.a.clear();
   }
}
