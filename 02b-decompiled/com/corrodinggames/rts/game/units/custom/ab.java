package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.l;

public class ab {

   String a;
   String b;
   int c;
   int d;
   String e;
   l f;


   public strictfp String a() {
      String var1 = "from internal units";
      if(this.a != null) {
         var1 = "from mod:\'" + this.a + "\'";
      }

      com.corrodinggames.rts.gameFramework.i.b var2 = com.corrodinggames.rts.gameFramework.l.B().bZ.f(this.a);
      if(var2 != null) {
         if(!var2.m()) {
            var1 = var1 + " (You seem to have this mod but it is not enabled)";
         } else {
            var1 = var1 + " (You seem to have this mod but it might be a different version)";
         }
      }

      String var3 = "";
      if(this.f != null && this.e != null) {
         if(this.f.I == null) {
            var3 = " (Extra debug not enabled)";
         } else {
            String[] var4 = this.e.split("\n");
            String[] var5 = this.f.I.split("\n");
            int var6 = com.corrodinggames.rts.gameFramework.f.c(var4.length, var5.length);
            if(var4.length != var5.length) {
               var3 = var3 + "Line length difference: " + var4.length + " vs " + var5.length;
            }

            for(int var7 = 0; var7 < var6; ++var7) {
               if(!var4[var7].equals(var5[var7])) {
                  var3 = var3 + "Difference on line " + var7 + ": \'" + var4[var7] + "\' vs \'" + var5[var7] + "\'";
                  break;
               }
            }
         }
      }

      return this.d == -1?"The server requires the unit:" + this.b + " that was not found " + var1 + var3:"Found unit:" + this.b + " but it does not match the server\'s copy " + var1 + var3 + " (checksum c:" + this.d + " s:" + this.c + ")";
   }
}
