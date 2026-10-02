package com.corrodinggames.rts.a.a;

import com.corrodinggames.rts.a.a.l;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.gameFramework.br;

public class m extends l {

   public void a() {
      com.corrodinggames.rts.gameFramework.l.e("Unit Reference tests");
      com.corrodinggames.rts.game.units.custom.l var1 = com.corrodinggames.rts.game.units.custom.l.b;
      com.corrodinggames.rts.game.units.custom.j var2 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var2.b(com.corrodinggames.rts.game.n.i);
      com.corrodinggames.rts.game.units.custom.j var3 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var3.b(com.corrodinggames.rts.game.n.i);
      var3.eo = 2.0F;
      com.corrodinggames.rts.game.units.custom.j var4 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var4.b(com.corrodinggames.rts.game.n.i);
      var4.eo = 3.0F;
      com.corrodinggames.rts.game.units.custom.j var5 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var5.b(com.corrodinggames.rts.game.n.i);
      var5.eo = 3.0F;
      var3.C(var4);
      var3.C(var5);
      com.corrodinggames.rts.game.units.custom.j var6 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var6.b(com.corrodinggames.rts.game.n.i);
      com.corrodinggames.rts.game.units.custom.j var7 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var7.b(com.corrodinggames.rts.game.n.i);
      com.corrodinggames.rts.game.units.custom.j var8 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var8.b(com.corrodinggames.rts.game.n.i);
      var2.bu = var6;
      var6.bv = var7;
      var4.bv = var7;
      var3.bu = var8;
      byte var9 = 2;
      com.corrodinggames.rts.gameFramework.l.e("=== unit reference tests == (runs:" + var9 + ")");
      Long var10 = Long.valueOf(br.a());

      for(int var11 = 0; var11 < var9; ++var11) {
         this.a(var2, this.a("self"), var2);
         this.a(var2, this.a("self.parent"), (am)null);
         this.a("self.unknown", true);
         this.a(var2, this.a("self.parent"), (am)null);
         this.a(var2, this.a("nullUnit"), (am)null);
         this.a(var2, this.a("self.customTarget1"), var6);
         this.a(var2, this.a("self.customTarget1.customTarget2"), var7);
         this.a(var2, this.a("self.customTarget2"), (am)null);
         this.a(var2, this.a("self.nullUnit"), (am)null);
         this.a(var2, this.a("nullUnit.nullUnit"), (am)null);
         this.a(var4, this.a("self.parent.customTarget1"), var8);
         this.a(var3, this.a("self.transporting(slot=0)"), var4);
         this.a(var3, this.a("self.transporting(SLOT=0)"), var4);
         this.a("self.transporting(MISS=0)", true);
         this.a(var3, this.a("self.transporting(slot=3)"), (am)null);
         this.a(var3, this.a("self.transporting"), var4);
         this.a(var3, this.a("self.transporting(slot=0).customTarget2"), var7);
         this.a(var3, this.a("self.self.transporting(slot=0).customTarget2"), var7);
         this.a(var3, this.a("self.SELF.TRANsporting(slot=0).customTarget2"), var7);
         this.a(var3, this.a("self.SELF.transporting(slot=0).customTarget2"), var7);
         this.a(var2, this.a("self.nearestUnit(withinRange=500, withTag=\'test\', relation=\'any\')"));
         this.a("", true);
      }

      Long var14 = Long.valueOf(br.a());
      double var12 = br.a(var10.longValue(), var14.longValue());
      com.corrodinggames.rts.gameFramework.l.e("Took: " + var12);
   }

   public void a(String var1, boolean var2) {
      try {
         com.corrodinggames.rts.game.units.custom.l var3 = com.corrodinggames.rts.game.units.custom.l.b;
         UnitReference.parseSingleUnitReferenceBlock(var3, var1);
      } catch (RuntimeException var5) {
         if(var5.getClass() != RuntimeException.class && var5.getClass() != BooleanParseException.class) {
            throw new RuntimeException(var5);
         }

         if(var2) {
            com.corrodinggames.rts.gameFramework.l.e("(debug)assertCreateError: " + var1 + " expected-error:" + var5.getMessage());
         }

         return;
      }

      throw new RuntimeException("assertCreateError got no error for: " + var1);
   }

   public UnitReference a(String var1) {
      try {
         com.corrodinggames.rts.game.units.custom.l var2 = com.corrodinggames.rts.game.units.custom.l.b;
         UnitReference var3 = UnitReference.parseSingleUnitReferenceBlock(var2, var1);
         if(var3 == null) {
            throw new RuntimeException("Null when parsing [" + var1 + "]");
         } else {
            return var3;
         }
      } catch (RuntimeException var4) {
         throw new RuntimeException("Error: " + var4.getMessage() + " parsing [" + var1 + "]", var4);
      }
   }

   public void a(y var1, UnitReference var2, am var3) {
      am var4 = var2.get(var1);
      if(var4 != var3) {
         throw new RuntimeException("assertSame type expected:" + am.A(var3) + " got: " + am.A(var4));
      }
   }

   public void a(y var1, UnitReference var2) {
      var2.get(var1);
   }
}
