package com.corrodinggames.rts.game.units.custom.e;

import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.e.b;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class d {

   public String a;
   public a b;
   public float c;
   public Integer d;
   public boolean e;
   public boolean f;
   bb g;
   bb h;
   boolean i;
   boolean j;
   public boolean k;
   public boolean l;
   public float m;
   public boolean n;
   public boolean o;
   public boolean p;
   public boolean q;
   public b r;
   public int s;
   public bb t;
   public bb u;
   public String v;
   public a w;
   public boolean x;
   public boolean y;
   public String z;
   public a A;
   public com.corrodinggames.rts.gameFramework.m.e B;
   public boolean C;


   public strictfp d(boolean var1) {
      this.r = b.a;
      this.f = var1;
   }

   public strictfp void a(l var1, ab var2, String var3, String var4) {
      this.a = var4;
      this.g = ag.a(var2, var3, "displayName", (String)null);
      this.h = ag.a(var2, var3, "displayNameShort", (String)null);
      if(this.h == null) {
         this.h = this.g;
      }

      this.i = var2.a(var3, "displayNameHideWhenIconShownInHUD", Boolean.valueOf(false)).booleanValue();
      this.j = var2.a(var3, "displayNameHideWhenIconShownInText", Boolean.valueOf(false)).booleanValue();
      this.l = var2.a(var3, "hidden", Boolean.valueOf(false)).booleanValue();
      float var6 = 1.0F;
      boolean var7 = var2.a(var3, "includeInStats", Boolean.valueOf(true)).booleanValue();
      if(!var7) {
         var6 = 0.0F;
      }

      if(this.l || !this.f) {
         var6 = 0.0F;
      }

      this.m = var2.a(var3, "valueInStats", Float.valueOf(var6)).floatValue();
      if(!var7 && this.m != 0.0F) {
         throw new bo("[" + var3 + "]includeInStats==false expects valueInStats==0");
      } else if(this.m < 0.0F) {
         throw new bo("[" + var3 + "]valueInStats cannot be < 0 (is:" + this.m + ")");
      } else {
         this.k = var2.a(var3, "stackHorizontal", Boolean.valueOf(false)).booleanValue();
         this.c = var2.a(var3, "priority", Float.valueOf(0.0F)).floatValue();
         this.d = var2.a(var3, "displayColor", (Integer)null);
         this.e = var2.a(var3, "displayColorUseInText", Boolean.valueOf(true)).booleanValue();
         this.n = var2.a(var3, "displayWithRounding", Boolean.valueOf(true)).booleanValue();
         this.o = var2.a(var3, "displayRoundedDown", Boolean.valueOf(false)).booleanValue();
         this.p = var2.a(var3, "displayWhenZero", Boolean.valueOf(false)).booleanValue();
         boolean var8 = !this.l && this.f;
         this.q = var2.a(var3, "displayInHud", Boolean.valueOf(var8)).booleanValue();
         if(this.q && !this.f) {
            throw new bo("[" + var3 + "]displayInHud:true currently only supported on global resources");
         } else if(this.q && this.l) {
            throw new bo("[" + var3 + "]displayInHud:true only supported non-hidden resources");
         } else {
            this.s = var2.b(var3, "displayPos", Integer.valueOf(0)).intValue();
            this.r = (b)var2.a(var3, "displayDigitGrouping", (Enum)b.a, b.class);
            this.t = ag.a(var2, var3, "displayTextPrefix", (String)null);
            this.u = ag.a(var2, var3, "displayTextPostfix", (String)null);
            bb var9 = ag.a(var2, var3, "displayPrefixInHUD", (String)null);
            if(var9 != null) {
               if(this.t != null) {
                  throw new bo("[" + var3 + "]displayPrefixInHUD and displayTextPrefix are aliases, don\'t use both");
               }

               this.t = var9;
            }

            bb var10 = ag.a(var2, var3, "displayPostfixInHUD", (String)null);
            if(var10 != null) {
               if(this.u != null) {
                  throw new bo("[" + var3 + "]displayPostfixInHUD and displayTextPostfix are aliases, don\'t use both");
               }

               this.u = var10;
            }

            this.v = var2.b(var3, "displayTextAppendResource", (String)null);
            String var11 = var2.b(var3, "appendResourceInHUD", (String)null);
            if(var11 != null) {
               if(this.v != null) {
                  throw new bo("[" + var3 + "]displayTextAppendResource and appendResourceInHUD are aliases, don\'t use both");
               }

               this.v = var11;
            }

            this.x = var2.a(var3, "displayTextAppendResourceWithGap", Boolean.valueOf(false)).booleanValue();
            this.y = var2.a(var3, "appendResourceInHUD_whenThisZero", Boolean.valueOf(true)).booleanValue();
            this.B = var1.a(var2, var3, "iconImage", true);
            if(this.B != null && (this.B.m() > 100 || this.B.l() > 100)) {
               throw new bo("[" + var3 + "]iconImage: Image is too big, keep under 100x100");
            } else {
               this.C = var2.a(var3, "iconImageUseInText", Boolean.valueOf(true)).booleanValue();
               if(this.i && this.B == null) {
                  throw new bo("[" + var3 + "]displayNameHideWhenIconShownInHUD: Cannot use without iconImage");
               } else if(this.j && this.B == null) {
                  throw new bo("[" + var3 + "]displayNameHideWhenIconShownInText: Cannot use without iconImage");
               } else {
                  String var5 = this.f?"g_":"l_";
                  var5 = var5 + this.a;
                  this.b = a.a(var5, false, this.f);
                  if(this.b.u) {
                     throw new RuntimeException("Cannot define resource with a built-in name: " + var5);
                  } else {
                     if(!this.f) {
                        String var12 = var2.b(var3, "equivalentGlobalResourceForAI", (String)null);
                        this.z = var12;
                     }

                  }
               }
            }
         }
      }
   }

   public strictfp void a(l var1) {
      if(this.z != null) {
         this.A = var1.k(this.z);
         if(this.A == null) {
            throw new bo("[resource]equivalentGlobalResourceForAI: Failed to find resource: " + this.z);
         }

         if(!this.A.t) {
            throw new bo("[resource]equivalentGlobalResourceForAI: Expected global resource for: " + this.z);
         }
      }

      if(this.v != null) {
         this.w = var1.k(this.v);
         if(this.w == null) {
            throw new bo("[resource]displayTextAppendResource: Failed to find resource: " + this.v);
         }
      }

   }
}
