/*
 * v19.115l 重建: 02b custom/a/a/n.java 48 行直译 (范围收集回调; extends units.f.i=SpatialQuery)
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import com.corrodinggames.rts.game.ResourceType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;

public class n extends SpatialQuery {

   public UnitConfig a;
   public float b;
   public boolean c;
   public com.corrodinggames.rts.game.units.custom.UnitTypeComparator d;
   public CustomArrayList e;

   public void setup(UnitType var1, float var2) {}

   public int excludeTeam(UnitType var1) {
      return -2;
   }

   public com.corrodinggames.rts.game.PlayerState onlyEnemiesOfTeam(UnitType var1) {
      return null;
   }

   public com.corrodinggames.rts.game.PlayerState onlyTeam(UnitType var1) {
      return null;
   }

   public void callback(UnitType var1, float var2, UnitInstance var3) {
      UnitConfig var4 = var3.de();
      if(this.a == null || var4 != null && TeamTag.b(this.a, var4)) {
         float var5 = GameUtils.a(var1.eo, var1.ep, var3.eo, var3.ep);
         if(var5 < this.b) {
            if(var3.cm < 1.0F && !this.c) {
               return;
            }
            if(this.d != null && !var1.player.a(this.d, var3.player)) {
               return;
            }
            this.e.add(var3);
         }
      }
   }
}
