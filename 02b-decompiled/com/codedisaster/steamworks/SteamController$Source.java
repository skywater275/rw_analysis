package com.codedisaster.steamworks;


public enum SteamController$Source {

   None("None", 0),
   LeftTrackpad("LeftTrackpad", 1),
   RightTrackpad("RightTrackpad", 2),
   Joystick("Joystick", 3),
   ABXY("ABXY", 4),
   Switch("Switch", 5),
   LeftTrigger("LeftTrigger", 6),
   RightTrigger("RightTrigger", 7),
   Gyro("Gyro", 8),
   CenterTrackpad("CenterTrackpad", 9),
   RightJoystick("RightJoystick", 10),
   DPad("DPad", 11);
   // $FF: synthetic field
   private static final SteamController$Source[] $VALUES = new SteamController$Source[]{None, LeftTrackpad, RightTrackpad, Joystick, ABXY, Switch, LeftTrigger, RightTrigger, Gyro, CenterTrackpad, RightJoystick, DPad};


   private SteamController$Source(String var1, int var2) {}

}
