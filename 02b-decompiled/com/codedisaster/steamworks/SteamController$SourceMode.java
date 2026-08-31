package com.codedisaster.steamworks;


public enum SteamController$SourceMode {

   None("None", 0),
   Dpad("Dpad", 1),
   Buttons("Buttons", 2),
   FourButtons("FourButtons", 3),
   AbsoluteMouse("AbsoluteMouse", 4),
   RelativeMouse("RelativeMouse", 5),
   JoystickMove("JoystickMove", 6),
   JoystickMouse("JoystickMouse", 7),
   JoystickCamera("JoystickCamera", 8),
   ScrollWheel("ScrollWheel", 9),
   Trigger("Trigger", 10),
   TouchMenu("TouchMenu", 11),
   MouseJoystick("MouseJoystick", 12),
   MouseRegion("MouseRegion", 13),
   RadialMenu("RadialMenu", 14),
   Switches("Switches", 15);
   private static final SteamController$SourceMode[] values = values();
   // $FF: synthetic field
   private static final SteamController$SourceMode[] $VALUES = new SteamController$SourceMode[]{None, Dpad, Buttons, FourButtons, AbsoluteMouse, RelativeMouse, JoystickMove, JoystickMouse, JoystickCamera, ScrollWheel, Trigger, TouchMenu, MouseJoystick, MouseRegion, RadialMenu, Switches};


   private SteamController$SourceMode(String var1, int var2) {}

   static SteamController$SourceMode byOrdinal(int var0) {
      return values[var0];
   }

}
