package com.corrodinggames.rts.gameFramework.g;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.gameFramework.g.d;
import com.corrodinggames.rts.gameFramework.g.f;
import java.util.ArrayList;
import java.util.Iterator;

public class b extends d {

   private final String a;
   private final int b;
   private final ArrayList c;


   public b(int var1, ArrayList var2) {
      this.b = var1;
      this.c = var2;
      this.a = "Team " + n.a(var1);
   }

   public boolean a() {
      return true;
   }

   public String b() {
      return this.a;
   }

   public int c() {
      return n.i(this.b);
   }

   public int d() {
      return n.i(this.b);
   }

   public int a(f var1) {
      int var2 = 0;

      n var4;
      for(Iterator var3 = this.c.iterator(); var3.hasNext(); var2 += var1.a(var4)) {
         var4 = (n)var3.next();
      }

      return var2;
   }
}
