package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bk;

public class bj extends com.corrodinggames.rts.game.units.f.i {

   com.corrodinggames.rts.game.f a;
   bk b;
   com.corrodinggames.rts.game.units.am c;
   com.corrodinggames.rts.game.f d;
   com.corrodinggames.rts.game.units.am e;


   public void setup(com.corrodinggames.rts.game.units.y var1, float var2) {}

   public int excludeTeam(com.corrodinggames.rts.game.units.y var1) {
      return -2;
   }

   public com.corrodinggames.rts.game.n onlyEnemiesOfTeam(com.corrodinggames.rts.game.units.y var1) {
      return null;
   }

   public com.corrodinggames.rts.game.n onlyTeam(com.corrodinggames.rts.game.units.y var1) {
      return null;
   }

   public void callback(com.corrodinggames.rts.game.units.y var1, float var2, com.corrodinggames.rts.game.units.am var3) {}
}
