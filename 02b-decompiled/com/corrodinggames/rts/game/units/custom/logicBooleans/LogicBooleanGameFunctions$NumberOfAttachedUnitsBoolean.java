package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.b.n;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.gameFramework.utility.am;

public final class LogicBooleanGameFunctions$NumberOfAttachedUnitsBoolean extends LogicBoolean$AbstractNumberBoolean {

   public g _withTag;
   short attachmentId = -1;
   l meta;


   public void forMeta(l var1) {
      if(var1 == null) {
         throw new am("NumberOfAttachedUnitsBoolean requires metadata");
      } else {
         this.meta = var1;
      }
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(var4 != null && var4 != LogicBooleanLoader.defaultContextReader && this.attachmentId != -1) {
         throw new BooleanParseException("Function:" + var1 + " only supports use with \'self.\' when using \'slot\'");
      }
   }

   @LogicBoolean$Parameter
   public void withTag(String var1) {
      this._withTag = g.c(var1);
   }

   @LogicBoolean$Parameter
   public void slot(String var1) {
      n var2 = this.meta.i(var1);
      if(var2 == null) {
         throw new am("No attachment slot with name: " + var1 + " found");
      } else {
         this.attachmentId = var2.a();
      }
   }

   public String getName() {
      String var1 = "";
      if(this._withTag != null) {
         var1 = var1 + "tag=" + this._withTag;
      }

      if(this.attachmentId != -1) {
         var1 = var1 + " attachmentId=" + this.attachmentId;
      }

      return "NumberOfAttachedUnits(" + var1 + ")";
   }

   public float getValue(y var1) {
      if(!(var1 instanceof j)) {
         return 0.0F;
      } else {
         j var2 = (j)var1;
         if(var2.C == null) {
            return 0.0F;
         } else {
            int var3 = 0;
            Object[] var4 = var2.C.a();

            for(int var5 = var2.C.a - 1; var5 >= 0; --var5) {
               y var6 = (y)var4[var5];
               if(var6 != null && (this.attachmentId == -1 || var5 == this.attachmentId)) {
                  if(this._withTag != null) {
                     h var7 = var6.de();
                     if(!g.a(this._withTag, var7)) {
                        continue;
                     }
                  }

                  ++var3;
               }
            }

            return (float)var3;
         }
      }
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }
}
