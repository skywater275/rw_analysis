package com.corrodinggames.rts.game.units.custom.f;


final class b$1 {

   int a;
   int b;
   // $FF: synthetic field
   final String c;


   b$1(String var1) {
      this.c = var1;
      this.a = -1;
   }

   void a() {
      this.b = ++this.a < this.c.length()?this.c.charAt(this.a):-1;
   }

   boolean a(int var1) {
      while(this.b == 32) {
         this.a();
      }

      if(this.b == var1) {
         this.a();
         return true;
      } else {
         return false;
      }
   }

   double b() {
      this.a();
      double var1 = this.c();
      if(this.a < this.c.length()) {
         throw new RuntimeException("Unexpected: " + (char)this.b);
      } else {
         return var1;
      }
   }

   double c() {
      double var1 = this.d();

      while(true) {
         while(!this.a(43)) {
            if(!this.a(45)) {
               return var1;
            }

            var1 -= this.d();
         }

         var1 += this.d();
      }
   }

   double d() {
      double var1 = this.e();

      while(true) {
         while(!this.a(42)) {
            if(this.a(47)) {
               var1 /= this.e();
            } else {
               if(!this.a(37)) {
                  return var1;
               }

               var1 %= this.e();
            }
         }

         var1 *= this.e();
      }
   }

   double e() {
      if(this.a(43)) {
         return this.e();
      } else if(this.a(45)) {
         return -this.e();
      } else {
         int var3 = this.a;
         double var1;
         if(this.a(40)) {
            var1 = this.c();
            this.a(41);
         } else if((this.b < 48 || this.b > 57) && this.b != 46) {
            if(this.b < 97 || this.b > 122) {
               throw new RuntimeException("Unexpected: " + (char)this.b);
            }

            while(this.b >= 97 && this.b <= 122) {
               this.a();
            }

            String var4 = this.c.substring(var3, this.a);
            var1 = this.e();
            if(var4.equals("sqrt")) {
               var1 = Math.sqrt(var1);
            } else if(var4.equals("sin")) {
               var1 = Math.sin(Math.toRadians(var1));
            } else if(var4.equals("cos")) {
               var1 = Math.cos(Math.toRadians(var1));
            } else if(var4.equals("tan")) {
               var1 = Math.tan(Math.toRadians(var1));
            } else {
               if(!var4.equals("int")) {
                  throw new RuntimeException("Unknown function: " + var4);
               }

               var1 = (double)((int)var1);
            }
         } else {
            while(this.b >= 48 && this.b <= 57 || this.b == 46) {
               this.a();
            }

            var1 = Double.parseDouble(this.c.substring(var3, this.a));
         }

         if(this.a(94)) {
            var1 = Math.pow(var1, this.e());
         }

         return var1;
      }
   }
}
