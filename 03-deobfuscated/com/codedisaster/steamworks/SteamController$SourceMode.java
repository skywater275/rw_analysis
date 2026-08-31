/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamController$SourceMode {
    None,
    Dpad,
    Buttons,
    FourButtons,
    AbsoluteMouse,
    RelativeMouse,
    JoystickMove,
    JoystickMouse,
    JoystickCamera,
    ScrollWheel,
    Trigger,
    TouchMenu,
    MouseJoystick,
    MouseRegion,
    RadialMenu,
    Switches;

    private static final SteamController$SourceMode[] values;

    static SteamController$SourceMode byOrdinal(int n) {
        return values[n];
    }

    static {
        values = SteamController$SourceMode.values();
    }
}
