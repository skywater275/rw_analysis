package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.b.n;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.gameFramework.utility.am;

public class UnitReference$AttachmentUnitReference extends UnitReference {

   l meta;
   g _withTag;
   short attachmentId = -1;


   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(var4 != null && var4 != LogicBooleanLoader.defaultContextReader && this.attachmentId != -1) {
         throw new BooleanParseException("Function:" + var1 + " only supports use with \'self.\' when using \'slot\'");
      }
   }

   public void forMeta(l var1) {
      if(var1 == null) {
         throw new am("AttachmentUnitReference requires metadata");
      } else {
         this.meta = var1;
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

   public com.corrodinggames.rts.game.units.am getSingleRaw(y var1) {
      if(!(var1 instanceof j)) {
         return null;
      } else {
         j var2 = (j)var1;
         if(var2.C == null) {
            return null;
         } else {
            Object[] var3 = var2.C.a();
            int var4 = var2.C.a - 1;

            y var5;
            while(true) {
               if(var4 < 0) {
                  return null;
               }

               var5 = (y)var3[var4];
               if(var5 != null && (this.attachmentId == -1 || var4 == this.attachmentId)) {
                  if(this._withTag == null) {
                     break;
                  }

                  h var6 = var5.de();
                  if(g.a(this._withTag, var6)) {
                     break;
                  }
               }

               --var4;
            }

            return var5;
         }
      }
   }

   public String getClassDebugName() {
      return "attachment";
   }
}
