package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.y;
import java.util.HashMap;

public class c {

   HashMap a = new HashMap();
   HashMap b = new HashMap();
   HashMap c = new HashMap();


   public Integer a(boolean var1, as var2, boolean var3) {
      return var1?(Integer)this.c.get(var2):(!var3?(Integer)this.b.get(var2):(Integer)this.a.get(var2));
   }

   public void a(boolean var1, as var2, boolean var3, Integer var4) {
      if(var1) {
         this.c.put(var2, var4);
      } else if(!var3) {
         this.b.put(var2, var4);
      } else {
         this.a.put(var2, var4);
      }

   }

   public void a() {
      this.a.clear();
      this.b.clear();
   }

   public void a(as var1) {
      this.a.put(var1, (Object)null);
      this.b.put(var1, (Object)null);
   }

   public void a(y var1) {
      this.c.put(var1.dz, (Object)null);
   }

   public void b() {
      this.c.clear();
   }
}
