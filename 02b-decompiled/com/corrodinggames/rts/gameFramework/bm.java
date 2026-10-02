package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.bo;
import java.util.ArrayList;
import java.util.Iterator;

public class bm extends bo {

   public strictfp bm(ArrayList var1) {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         bo var3 = (bo)var2.next();
         this.a += var3.a;
         this.b += var3.b;
         this.c += var3.c;
         this.d += var3.d;
         this.e += var3.e;
         this.f += var3.f;
         this.g += var3.g;
         this.h += var3.h;
         this.i += var3.i;
         this.j = Math.max(this.j, var3.j);
         this.k += var3.k;
         this.l.a(var3.l);
      }

   }
}
