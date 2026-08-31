package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.bh;
import java.util.ArrayList;
import java.util.Iterator;

public class bi extends ArrayList {

   public strictfp int a(int var1) {
      if(this.isEmpty()) {
         return 0;
      } else {
         int var2 = ((bh)this.get(0)).b;

         bh var4;
         for(Iterator var3 = this.iterator(); var3.hasNext(); var2 = var4.b) {
            var4 = (bh)var3.next();
            if(var4.a > var1) {
               return var2;
            }
         }

         return var2;
      }
   }
}
