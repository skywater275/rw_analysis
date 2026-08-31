package com.corrodinggames.rts.gameFramework.e;

import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.e.c;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.File;

public class e extends c {

   c g;
   c h;
   String i;
   String j;


   public e(c var1, String var2, c var3, String var4) {
      this.g = var1;
      this.i = var2;
      this.h = var3;
      this.j = var4;
   }

   public String a() {
      String var1 = this.g.a();
      String var2 = this.h.a();
      return var1 != null?var1:var2;
   }

   public void a(String var1) {
      this.g.a(var1);
      this.h.a(var1);
   }

   public String a(String var1, String var2) {
      return this.s(var1).a(var1, var2);
   }

   public boolean b(String var1) {
      return this.s(var1).b(this.q(var1));
   }

   public boolean c(String var1) {
      return this.s(var1).c(this.q(var1));
   }

   public String d(String var1) {
      return this.s(var1).d(this.q(var1));
   }

   public String f(String var1) {
      return this.s(var1).f(this.q(var1));
   }

   public String e(String var1) {
      return this.s(var1).e(this.q(var1));
   }

   public boolean a(String var1, boolean var2) {
      c var3 = this.r(var1);
      var1 = this.q(var1);
      if(var3 != null) {
         return var3.a(this.q(var1), var2);
      } else {
         boolean var4 = this.g.a(this.q(var1), var2);
         if(this.h.a(this.q(var1), var2)) {
            var4 = true;
         }

         return var4;
      }
   }

   public boolean g(String var1) {
      return this.s(var1).g(this.q(var1));
   }

   private String q(String var1) {
      if(var1 == null) {
         return null;
      } else {
         int var2 = var1.indexOf(this.i);
         if(var2 != -1) {
            String var5 = var1.substring(0, var2) + var1.substring(var2 + this.i.length());
            if(var5.contains(this.i) || var5.contains(this.j)) {
               l.e("fixPath: double tag for: " + var1);
            }

            return var5;
         } else {
            int var3 = var1.indexOf(this.j);
            if(var3 == -1) {
               return var1;
            } else {
               String var4 = var1.substring(0, var3) + var1.substring(var3 + this.j.length());
               if(var4.contains(this.i) || var4.contains(this.j)) {
                  l.e("fixPath: double tag for: " + var1);
               }

               return var4;
            }
         }
      }
   }

   private c r(String var1) {
      return var1 == null?null:(var1.contains(this.i)?this.g:(var1.contains(this.j)?this.h:null));
   }

   private c s(String var1) {
      c var2 = this.r(var1);
      return var2 != null?var2:this.g;
   }

   public String[] b(String var1, boolean var2) {
      c var3 = this.r(var1);
      var1 = this.q(var1);
      if(var3 != null) {
         return var3.b(var1, var2);
      } else {
         String[] var4 = this.g.b(var1, var2);
         String[] var5 = this.h.b(var1, var2);
         if(var4 == null && var5 == null) {
            return null;
         } else {
            if(var4 == null) {
               var4 = new String[0];
            }

            if(var5 == null) {
               var5 = new String[0];
            }

            String[] var6 = new String[var4.length + var5.length];

            int var7;
            for(var7 = 0; var7 < var4.length; ++var7) {
               var6[var7] = this.i + var4[var7];
            }

            for(var7 = 0; var7 < var5.length; ++var7) {
               var6[var7 + var4.length] = this.j + var5[var7];
            }

            return var6;
         }
      }
   }

   public j i(String var1) {
      return this.g.i(var1);
   }

   public j j(String var1) {
      return this.s(var1).j(this.q(var1));
   }

   public String b() {
      return this.g.b();
   }

   public File a(String var1, String var2, boolean var3) {
      return this.s(var1).a(this.q(var1), var2, var3);
   }

   public String m(String var1) {
      return this.s(var1).m(var1);
   }

   public String d() {
      String var1 = this.g.d();
      if(this.h.d() != null) {
         var1 = var1 + " and " + this.h.d();
      }

      return var1;
   }

   public boolean e() {
      return this.g.e() || this.h.e();
   }

   public String o(String var1) {
      if(var1.startsWith("/") && this.i.endsWith("/")) {
         var1 = var1.substring(1);
         return "/" + this.i + var1;
      } else {
         return this.i + var1;
      }
   }
}
