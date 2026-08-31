package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.gameFramework.aq;
import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.gameFramework.as;
import com.corrodinggames.rts.gameFramework.aw;
import com.corrodinggames.rts.gameFramework.ax;
import com.corrodinggames.rts.gameFramework.l;

public class av extends aq {

   boolean a = false;


   public ar a(String var1) {
      return new aw(var1, this);
   }

   public as a() {
      ax var1 = new ax(this);
      return var1;
   }

   public void a(am var1) {
      l.e("Null musicFactory - load");
      this.e = var1;
   }

   public void b() {}
}
