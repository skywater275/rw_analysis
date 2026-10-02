package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.b;
import java.util.ArrayList;
import java.util.Iterator;

public class a {

   public b a = this.a((byte)1);
   public b b = this.a((byte)2);
   public b c = this.a((byte)3);
   public b d = this.a((byte)4);
   public b e = this.a((byte)10);
   public b f = this.a((byte)11);
   public b g = this.a((byte)13);
   public b h = this.a((byte)21);
   public b i = this.a((byte)35);
   public b j = this.a((byte)40);
   public b k = this.a((byte)45);
   public b l = this.a((byte)52);
   public b m = this.a((byte)60);
   ArrayList n = new ArrayList();


   public strictfp a() {
      b var1 = null;
      var1 = this.a;
      var1.a(var1);
      var1.a(this.a((byte)3));
      var1.a(this.a((byte)4));
      var1.a(this.a((byte)10));
      var1.a(this.a((byte)11));
      var1.a(this.a((byte)13));
      var1.a(this.a((byte)21));
      var1 = this.k;
      var1.a(this.a((byte)52));
      var1 = this.m;
      var1.a(var1);
      var1.a(this.a((byte)3));
      var1.a(this.a((byte)4));
      var1.a(this.a((byte)10));
      var1.a(this.a((byte)11));
      var1.a(this.a((byte)13));
      var1.a(this.a((byte)21));
      var1 = this.a((byte)10);
      var1.a(this.a((byte)3));
      var1.a(this.a((byte)4));
      var1.a(this.a((byte)10));
      var1.a(this.a((byte)13));
      var1.a(this.a((byte)40));
      var1 = this.a((byte)11);
      var1.a(this.a((byte)3));
      var1.a(this.a((byte)10));
      var1.a(this.a((byte)13));
      var1.a(this.a((byte)40));
      var1 = this.a((byte)3);
      var1.a(this.a((byte)3));
      var1.a(this.a((byte)4));
      var1.a(this.a((byte)10));
      var1.a(this.a((byte)13));
      var1 = this.a((byte)4);
      var1.a(this.a((byte)3));
      var1.a(this.a((byte)4));
      var1.a(this.a((byte)10));
      var1.a(this.a((byte)13));
      var1 = this.a((byte)13);
      var1.a(this.a((byte)3));
      var1.a(this.a((byte)4));
      var1.a(this.a((byte)10));
      var1.a(this.a((byte)13));
      var1 = this.a((byte)21);
      var1.a(this.a((byte)3));
      var1.a(this.a((byte)4));
      var1.a(this.a((byte)10));
      var1.a(this.a((byte)13));
      var1 = this.i;
      var1.a(this.a((byte)10));
      var1.a(this.a((byte)13));
   }

   public strictfp b a(byte var1) {
      Iterator var2 = this.n.iterator();

      b var3;
      do {
         if(!var2.hasNext()) {
            b var4 = new b();
            var4.a = var1;
            this.n.add(var4);
            return var4;
         }

         var3 = (b)var2.next();
      } while(var3.a != var1);

      return var3;
   }
}
