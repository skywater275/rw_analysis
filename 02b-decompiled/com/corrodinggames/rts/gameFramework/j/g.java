package com.corrodinggames.rts.gameFramework.j;


public class g {

   public boolean a;
   public String b;
   public String c;
   public String d;
   public String e;
   public String f;
   public int g;
   public boolean h;
   public long i = -1L;
   public String j;
   public String k;
   public int l;
   public boolean m;
   public String n;
   public long o;
   public int p;
   public String q;
   public String r;
   public String s;
   public String t;
   public String u;
   public int v = -1;
   public int w = 8;
   public boolean x;
   public boolean y;
   public String z;
   public int A;


   public boolean a() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      String var2 = var1.bX.bw;
      return var2 != null?var2.equals(this.b):false;
   }

   public String b() {
      String var1 = this.c();
      String var2;
      String var3;
      if(var1 != null) {
         var2 = "";
         var3 = this.f;
         if(var3 != null) {
            var3 = var3.replace("\\n", "\n");
         }

         var2 = var2 + var3 + "\n";
         var2 = var2 + "Url: " + var1 + "\n";
         return var2;
      } else {
         var2 = "";
         if(this.a) {
            var2 = var2 + "Lan: " + this.d + ":" + this.g + "\n";
         }

         var2 = var2 + "User: " + this.n + "\n";
         var3 = com.corrodinggames.rts.appFramework.i.e(this.q);
         var2 = var2 + "Map: " + var3 + "\n";
         if(this.m) {
            var2 = var2 + "Password Required\n";
         }

         if(!this.h && !this.a) {
            var2 = var2 + "Port: not open (Connecting over the internet may fail)\n";
         }

         if("ANY".equalsIgnoreCase(this.k)) {
            var2 = var2 + "Version: " + this.k + "\n";
         } else {
            var2 = var2 + "Version: v" + this.k + (this.g()?"":" (different game version!)") + "\n";
         }

         if(this.z != null && !this.z.equals("")) {
            var2 = var2 + "Mods Needed: " + this.z + "\n";
         }

         return var2;
      }
   }

   public String c() {
      return this.e;
   }

   public boolean d() {
      return this.e != null;
   }

   public String e() {
      return this.A == 0?this.c + ":" + this.g:"get|" + this.b.replace("|", ".") + "|" + this.A + "|" + this.m + "|" + this.g;
   }

   public String f() {
      return this.d + ":" + this.g;
   }

   public boolean g() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return this.x && "ANY".equals(this.k)?true:(this.x && this.k != null && this.k.contains("+") && var1.c(true) >= this.l?true:var1.c(true) == this.l);
   }
}
