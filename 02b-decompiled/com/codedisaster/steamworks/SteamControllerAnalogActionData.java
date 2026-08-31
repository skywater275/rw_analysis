package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamController$SourceMode;

public class SteamControllerAnalogActionData {

   int mode;
   float x;
   float y;
   boolean active;


   public SteamController$SourceMode getMode() {
      return SteamController$SourceMode.byOrdinal(this.mode);
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public boolean getActive() {
      return this.active;
   }
}
