package com.corrodinggames.rts.game.units.f;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.f.b;

public class a {

   public final b a = new b();
   public final b[] b;
   public final b c;
   public final b d;
   float e;


   public a() {
      this.b = new b[n.e];
      this.c = new b();
      this.d = new b();

      for(int var1 = 0; var1 < this.b.length; ++var1) {
         this.b[var1] = new b();
      }

   }

   public void a(am var1) {
      this.a.a(var1);
      int var2 = var1.dn;
      if(var2 >= 0) {
         this.b[var2].a(var1);
      } else if(var2 == -1) {
         this.d.a(var1);
      } else if(var2 == -2) {
         this.c.a(var1);
      }

      if(var1.cj > this.e) {
         this.e = var1.cj;
      }

   }

   public void b(am var1) {
      this.a.b(var1);
      int var2 = var1.dn;
      if(var2 >= 0) {
         this.b[var2].b(var1);
      } else if(var2 == -1) {
         this.d.b(var1);
      } else if(var2 == -2) {
         this.c.b(var1);
      }

      if(this.a.b == 0) {
         this.e = 0.0F;
      }

   }
}
