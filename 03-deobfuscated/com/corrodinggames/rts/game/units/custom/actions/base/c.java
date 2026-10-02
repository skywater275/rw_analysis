/*
 * v19.115k 重建: 02b custom/a/a/c.java 60 行直译 (waypoint 空间查询回调; extends units.f.i=SpatialQuery)
 * 类型映射: h=UnitConfig, g=TeamTag, q=ResourceType, f=GameUtils, utility.y=PathfindingUtils
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

public class c extends SpatialQuery {

   public boolean a;
   public UnitConfig b;
   public float c;
   public boolean d;
   public com.corrodinggames.rts.game.units.custom.UnitTypeComparator e;
   public boolean f;
   public CustomArrayList g = new CustomArrayList();
   public UnitInstance h;

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
      if(this.b == null || var4 != null && TeamTag.b(this.b, var4)) {
         float var5 = GameUtils.a(var1.eo, var1.ep, var3.eo, var3.ep);
         if(var5 < this.c) {
            if(var3.cm < 1.0F && !this.d) {
               return;
            }
            if(this.e != null && !var1.player.a(this.e, var3.player)) {
               return;
            }
            if(this.a && !PathfindingUtils.b(var1, var3.eo, var3.ep)) {
               return;
            }
            if(!this.f) {
               this.h = var3;
               this.c = var5;
            } else {
               this.g.add(var3);
            }
         }
      }
   }
}
