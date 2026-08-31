package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.bk;
import java.util.Iterator;

public class bl {

   com.corrodinggames.rts.gameFramework.utility.m a = new com.corrodinggames.rts.gameFramework.utility.m();


   public strictfp void a(com.corrodinggames.rts.game.units.am var1, com.corrodinggames.rts.game.units.am var2) {
      if(this.a.a > 0) {
         Iterator var3 = this.a.iterator();

         while(var3.hasNext()) {
            bk var4 = (bk)var3.next();
            var4.a(var1, var2, (Object)null);
         }
      }

   }
}
