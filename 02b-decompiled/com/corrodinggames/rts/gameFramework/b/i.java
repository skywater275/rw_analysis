package com.corrodinggames.rts.gameFramework.b;

import com.corrodinggames.rts.gameFramework.b.af;
import com.corrodinggames.rts.gameFramework.b.b;
import com.corrodinggames.rts.gameFramework.b.c;
import com.corrodinggames.rts.gameFramework.b.j;
import com.corrodinggames.rts.gameFramework.b.k;
import com.corrodinggames.rts.gameFramework.b.x;
import java.util.Iterator;
import java.util.List;

public class i extends c {

   protected List a;
   private final List b;
   private b c;
   private b d;


   private void a(b var1) {
      this.a();

      for(int var2 = 0; var2 < this.a.size(); ++var2) {
         this.b.add(new x(var1.b(), var1.c(), false));
      }

   }

   private void a() {
      Iterator var1 = this.b.iterator();

      while(var1.hasNext()) {
         x var2 = (x)var1.next();
         var2.j();
      }

      this.b.clear();
   }

   public b a(b var1, k var2, j var3) {
      if(var1 instanceof x) {
         if(!((x)var1).k()) {
            return this.c;
         }
      } else if(this.d == var1 && this.c != null) {
         return this.c;
      }

      if(this.b.size() != this.a.size() || this.d != var1) {
         this.a(var1);
      }

      this.d = var1;
      Object var4 = var1;
      int var5 = 0;

      for(int var6 = this.b.size(); var5 < var6; ++var5) {
         x var7 = (x)this.b.get(var5);
         af var8 = (af)this.a.get(var5);
         var2.c(var7);
         var3.a((b)var4, var8, var5 == 0);
         var2.d();
         var4 = var7;
         com.corrodinggames.rts.gameFramework.l.e("FilterGroup: renderTarget");
      }

      this.c = (b)var4;
      return (b)var4;
   }
}
