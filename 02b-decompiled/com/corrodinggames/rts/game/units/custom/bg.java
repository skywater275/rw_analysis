package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.l;

public final class bg {

   String a;
   g b;
   h c;
   com.corrodinggames.rts.game.q d;
   float e;
   float f;
   float g;
   float h;
   boolean i;
   boolean j;
   int k;
   int l;
   boolean m;
   boolean n;
   bb o;
   boolean p;


   public boolean a() {
      return this.n || this.m;
   }

   public void a(l var1, com.corrodinggames.rts.gameFramework.utility.ab var2, String var3) {
      this.b = var2.a(var3, "anyRuleInGroup", (g)null);
      this.c = var2.a(var1, var3, "searchTags", (h)null);
      this.d = (com.corrodinggames.rts.game.q)var2.a(var3, "searchTeam", (Enum)com.corrodinggames.rts.game.q.a, com.corrodinggames.rts.game.q.class);
      this.e = var2.i(var3, "searchDistance");
      this.f = this.e * this.e;
      this.g = var2.a(var3, "searchOffsetX", Float.valueOf(0.0F)).floatValue();
      this.h = var2.a(var3, "searchOffsetY", Float.valueOf(0.0F)).floatValue();
      this.i = var2.a(var3, "excludeIncompleteBuildings", Boolean.valueOf(false)).booleanValue();
      this.j = var2.a(var3, "excludeNonBuildings", Boolean.valueOf(false)).booleanValue();
      this.k = var2.b(var3, "minCount", Integer.valueOf(Integer.MIN_VALUE)).intValue();
      this.l = var2.b(var3, "maxCount", Integer.valueOf(Integer.MAX_VALUE)).intValue();
      this.p = var2.a(var3, "checkEachTile", Boolean.valueOf(true)).booleanValue();
      this.m = var2.a(var3, "aiSuggestionOnly", Boolean.valueOf(false)).booleanValue();
      this.n = var2.a(var3, "blocksPlacement", Boolean.valueOf(!this.m)).booleanValue();
      if(this.m && this.n) {
         throw new bo("[" + var3 + "]: Cannot use aiSuggestionOnly and blocksPlacement at the same time");
      } else {
         this.o = ag.a(var2, var3, "cannotPlaceMessage", (String)null);
      }
   }
}
