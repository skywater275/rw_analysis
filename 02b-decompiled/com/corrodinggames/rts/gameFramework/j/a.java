package com.corrodinggames.rts.gameFramework.j;

import android.text.Html;
import android.text.Spanned;
import com.corrodinggames.rts.gameFramework.j.b;
import com.corrodinggames.rts.gameFramework.j.c;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class a {

   private ConcurrentLinkedQueue a = new ConcurrentLinkedQueue();


   public String a(String var1) {
      return com.corrodinggames.rts.gameFramework.f.i(var1);
   }

   public void a(int var1, String var2, String var3, c var4) {
      var3 = var3.trim();
      b var5 = new b(this, var1, var2, var3, var4);
      this.a.add(var5);
      if(this.a.size() > 45) {
         this.a.poll();
      }

   }

   public int a(c var1, int var2) {
      if(var1 == null) {
         return 0;
      } else {
         int var3 = var1.c;
         int var4 = 0;
         Iterator var5 = this.a.iterator();

         while(var5.hasNext()) {
            b var6 = (b)var5.next();
            if(var6.d == var3 && com.corrodinggames.rts.gameFramework.f.a(var6.e, System.nanoTime()) < (long)var2 && !var6.c.startsWith("-i ") && !var6.c.startsWith("-qc ")) {
               ++var4;
               if(var6.c != null) {
                  if(com.corrodinggames.rts.gameFramework.f.a(var6.c, '\n') >= 3) {
                     var4 += 2;
                  }

                  if(com.corrodinggames.rts.gameFramework.f.a(var6.c, '\n') >= 6) {
                     var4 += 2;
                  }

                  if(com.corrodinggames.rts.gameFramework.f.a(var6.c, '\n') >= 9) {
                     var4 += 2;
                  }
               }
            }
         }

         return var4;
      }
   }

   public String a() {
      String var1 = "";

      b var3;
      for(Iterator var2 = this.a.iterator(); var2.hasNext(); var1 = var1 + var3.a() + "\n") {
         var3 = (b)var2.next();
      }

      return var1;
   }

   public ConcurrentLinkedQueue b() {
      return this.a;
   }

   public String a(boolean var1) {
      String var2 = "";
      Iterator var3;
      b var4;
      if(!var1) {
         for(var3 = this.a.iterator(); var3.hasNext(); var2 = var2 + var4.b() + "<br/>\n") {
            var4 = (b)var3.next();
         }
      } else {
         for(var3 = this.a.iterator(); var3.hasNext(); var2 = var4.b() + "<br/>\n" + var2) {
            var4 = (b)var3.next();
         }
      }

      return "<pre>" + var2 + "</pre>";
   }

   public Spanned b(boolean var1) {
      return Html.fromHtml(this.a(var1));
   }

   public void c() {
      this.a.clear();
   }
}
