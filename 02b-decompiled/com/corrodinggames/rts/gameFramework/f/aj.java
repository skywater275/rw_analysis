package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.f.af;
import com.corrodinggames.rts.gameFramework.f.ah;
import com.corrodinggames.rts.gameFramework.f.ai;
import com.corrodinggames.rts.gameFramework.f.ak;
import com.corrodinggames.rts.gameFramework.f.d;
import java.util.Iterator;

public class aj {

   com.corrodinggames.rts.gameFramework.utility.m a;
   Rect b;
   Paint c;
   Paint d;


   public void a(float var1, float var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      int var4 = 0;
      int var5 = d.a(this.c);

      for(Iterator var6 = this.a.iterator(); var6.hasNext(); ++var4) {
         ak var7 = (ak)var6.next();
         int var8 = 0;
         af var9 = null;
         Iterator var10 = var7.a.iterator();

         while(var10.hasNext()) {
            af var11 = (af)var10.next();
            if(var9 != null) {
               var8 += var9.a(this.c);
            }

            int var12 = (int)(var1 + (float)var8 + (float)this.b.d());
            var12 -= var7.b / 2;
            int var13 = (int)(var2 + (float)this.b.b + (float)(var5 / 2) + (float)(var4 * var5));
            if(!(var11 instanceof ai)) {
               if(var11 instanceof ah) {
                  ah var14 = (ah)var11;
                  com.corrodinggames.rts.gameFramework.m.e var15 = var14.a;
                  var3.bO.a(var15, (float)var12, (float)var13 - (float)var15.q * var14.b, ae.c, 0.0F, var14.b);
               }

               var9 = var11;
            } else {
               ai var16 = (ai)var11;
               Paint var17 = var16.b(this.c);
               var3.bO.a(var16.d, (float)var12, (float)var13, var17);
               var9 = var11;
            }
         }
      }

   }
}
