package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.f.af;
import com.corrodinggames.rts.gameFramework.f.ag;
import com.corrodinggames.rts.gameFramework.f.ah;
import com.corrodinggames.rts.gameFramework.f.ai;
import com.corrodinggames.rts.gameFramework.f.aj;
import com.corrodinggames.rts.gameFramework.f.ak;
import com.corrodinggames.rts.gameFramework.f.d;
import java.util.Iterator;

public class ae {

   static com.corrodinggames.rts.gameFramework.m.ag a = new com.corrodinggames.rts.gameFramework.m.ag();
   static com.corrodinggames.rts.gameFramework.m.ag b = new com.corrodinggames.rts.gameFramework.m.ag();
   static com.corrodinggames.rts.gameFramework.m.ag c = new com.corrodinggames.rts.gameFramework.m.ag();
   public Paint d;
   public Paint e;
   static Paint f = new Paint();
   public Paint g;
   static Paint h;
   com.corrodinggames.rts.gameFramework.utility.m i;


   public ae() {
      this.d = a;
      this.e = a;
      this.g = this.d;
      this.i = new com.corrodinggames.rts.gameFramework.utility.m();
   }

   public void a(Paint var1) {
      if(var1 == null) {
         this.g = this.d;
      } else {
         this.g = var1;
      }
   }

   public void a(boolean var1) {
      if(var1) {
         this.g = this.e;
      } else {
         this.g = this.d;
      }

   }

   public String a() {
      StringBuilder var1 = new StringBuilder();
      Iterator var2 = this.i.iterator();

      while(var2.hasNext()) {
         af var3 = (af)var2.next();
         if(var3 instanceof ai) {
            var1.append(((ai)var3).d);
         }
      }

      return var1.toString();
   }

   public void a(String var1) {
      if(this.i.size() > 0) {
         int var2 = this.i.size() - 1;
         af var3 = (af)this.i.get(var2);
         if(var3 instanceof ai) {
            ai var4 = (ai)var3;
            String var5 = com.corrodinggames.rts.gameFramework.f.a(var4.d, var1);
            if(!var4.d.equals(var5)) {
               this.i.set(var2, var4.b(var5));
            }
         }
      }

   }

   public void b() {
      this.i.clear();
   }

   public void a(af var1) {
      this.i.add(var1);
   }

   public void b(String var1) {
      if(this.g != null && this.g != this.d) {
         this.a(var1, this.g);
      } else {
         this.a((af)(new ai(this, var1)));
      }
   }

   public void a(String var1, Paint var2) {
      this.a((af)(new ag(this, var1, var2)));
   }

   public void a(String var1, int var2) {
      if(this.g != null && this.g != this.d) {
         this.a((af)(new ag(this, var1, this.g, var2)));
      } else {
         this.a((af)(new ag(this, var1, (Paint)null, var2)));
      }
   }

   public void a(String var1, int var2, boolean var3) {
      Paint var4 = this.d;
      if(var3) {
         var4 = this.e;
      }

      this.a((af)(new ag(this, var1, var4, var2)));
   }

   public void a(com.corrodinggames.rts.gameFramework.m.e var1, int var2, int var3) {
      ah var4 = new ah(this);
      var4.a = var1;
      float var5 = d.a(var1, (float)var2, (float)var3);
      var4.c = (int)((float)var1.p * var5);
      var4.d = (int)((float)var1.q * var5);
      var4.b = var5;
      this.i.add(var4);
   }

   public int c() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return var1.bO.a("A", this.g);
   }

   public aj a(int var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      Rect var4 = new Rect(-var1 / 2, 0, var1 / 2, 10);
      com.corrodinggames.rts.gameFramework.utility.m var5 = new com.corrodinggames.rts.gameFramework.utility.m();
      ak var6 = new ak();
      Paint var7 = this.d;
      int var8 = var1 - 5;
      Iterator var9 = this.i.iterator();

      while(var9.hasNext()) {
         af var10 = (af)var9.next();
         if(var6.b >= var8 - 5) {
            if(var6.a.size() > 0) {
               var5.add(var6);
            }

            var6 = new ak();
         }

         if(!(var10 instanceof ai)) {
            var6.a(var10);
            var6.b += var10.a(this.d);
         } else {
            ai var11 = (ai)var10;
            String var12 = var11.d;
            int var13 = 0;

            while(var13 < var12.length()) {
               if(var12.charAt(var13) == 10) {
                  ++var13;
                  var5.add(var6);
                  var6 = new ak();
               } else {
                  int var14 = var7.a(var12, var13, var12.length(), true, (float)(var8 - var6.b), (float[])null);
                  if(var14 == 0) {
                     break;
                  }

                  boolean var15 = true;
                  int var16 = var12.indexOf("\n", var13 + 1);
                  String var17;
                  if(var16 != -1 && var16 < var13 + var14) {
                     var14 = var16 - var13;
                  } else {
                     if(var13 + var14 < var12.length()) {
                        var17 = var12.substring(var13, var13 + var14);
                        int var18 = var17.lastIndexOf(" ");
                        if(var18 != -1 && var18 != 0) {
                           var14 = var18;
                        }
                     }

                     if(var13 + var14 == var12.length()) {
                        var15 = false;
                     }
                  }

                  var17 = var12.substring(var13, var13 + var14);
                  if(com.corrodinggames.rts.gameFramework.f.c(var17, "\\n")) {
                     var17 = var17.replaceAll("(\\n)", "");
                  }

                  ai var25 = var11.b(var17);
                  var6.a(var25);
                  var6.b += var25.a(this.d);
                  var13 += var14;
                  if(var13 < var12.length() && var12.charAt(var13) == 10) {
                     ++var13;
                  }

                  if(var15 || var6.b >= var8 - 5) {
                     if(var6.a.size() > 0) {
                        var5.add(var6);
                     }

                     var6 = new ak();
                  }
               }
            }
         }
      }

      if(var6.a.size() > 0) {
         var5.add(var6);
      }

      var6 = null;
      if(var5.size() > 0) {
         ak var19 = (ak)var5.get(var5.size() - 1);
         if(var19.a.size() == 0) {
            var5.remove(var5.size() - 1);
         }
      }

      var4.d = var4.b + var5.size() * d.a(var7);
      if(var2) {
         float var20 = (float)var4.d();
         float var22 = 0.0F;
         Iterator var23 = var5.iterator();

         while(var23.hasNext()) {
            ak var24 = (ak)var23.next();
            if((float)var24.b > var22) {
               var22 = (float)var24.b;
            }
         }

         if(var22 < (float)var4.b()) {
            var4.a = (int)(var20 - var22 / 2.0F);
            var4.c = (int)(var20 + var22 / 2.0F);
         }
      }

      aj var21 = new aj();
      var21.a = var5;
      var21.b = var4;
      var21.c = this.d;
      var21.d = this.e;
      return var21;
   }

   static {
      c.a(true);
      h = new Paint();
      h.b(-65536);
      h.a(Paint$Style.b);
   }
}
