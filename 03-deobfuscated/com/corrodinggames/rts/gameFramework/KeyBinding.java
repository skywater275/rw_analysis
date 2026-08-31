/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.KeyBindingManager;
import com.corrodinggames.rts.gameFramework.TextureCache;
import com.corrodinggames.rts.gameFramework.KeyTrigger;
import com.corrodinggames.rts.gameFramework.AxisTrigger;
import com.corrodinggames.rts.gameFramework.NullInput;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$MissingKey;
import java.util.ArrayList;
import java.util.Locale;

public class KeyBinding {
    public String bindingName;
    public boolean isActive = false;
    public ArrayList keyCodes = new ArrayList();
    public ArrayList mouseButtons = new ArrayList();

    public boolean a() {
        for (TextureCache af2 : (java.util.Collection<TextureCache>) (java.util.Collection) this.keyCodes) {
            if (af2.textureId != -1 || !af2.a()) continue;
            return true;
        }
        return false;
    }

    public boolean b() {
        for (TextureCache af2 : (java.util.Collection<TextureCache>) (java.util.Collection) this.keyCodes) {
            if (af2 == null || af2.textureId != -1 || !af2.b()) continue;
            return true;
        }
        return false;
    }

    public String c() {
        for (TextureCache af2 : (java.util.Collection<TextureCache>) (java.util.Collection) this.keyCodes) {
            if (af2 == null) continue;
            return af2.c().toUpperCase();
        }
        return "";
    }

    public TextureCache a(int n) {
        if (this.keyCodes.size() > n) {
            TextureCache af2 = (TextureCache) this.keyCodes.get(n);
            return af2;
        }
        return null;
    }

    public String b(int n) {
        if (this.keyCodes.size() > n) {
            TextureCache af2 = (TextureCache) this.keyCodes.get(n);
            if (af2 == null) {
                return "<null>";
            }
            return af2.c().toUpperCase();
        }
        return "";
    }

    public KeyBinding c(int n) {
        int n2 = 0;
        return this.a(n, 0, n2, false);
    }

    public KeyBinding a(int n, int n2, int n3, boolean bl) {
        KeyTrigger ag2 = new KeyTrigger();
        ag2.e = n;
        ag2.textureId = -1;
        ag2.b = n3;
        if (bl) {
            ag2.d = true;
        }
        if (this.keyCodes.size() <= n2) {
            this.keyCodes.add(new NullInput());
        }
        if (this.keyCodes.size() <= n2) {
            this.keyCodes.add(new NullInput());
        }
        this.keyCodes.set(n2, ag2);
        return this;
    }

    public KeyBinding a(String string) {
        return this.a(string, -1);
    }

    public KeyBinding a(String string, int n) {
        if (string == null) {
            throw new RuntimeException("key==null");
        }
        return this.a(-1, string, n);
    }

    public KeyBinding a(int n2, String string, int n3) {
        block10: {
            if (string == null) {
                throw new RuntimeException("key==null");
            }
            KeyTrigger ag2 = new KeyTrigger();
            ag2.textureId = n2;
            ag2.b = 0;
            if ((string = string.toLowerCase(Locale.ENGLISH)).contains("alt+")) {
                string = string.replace("alt+", "");
                ag2.b += 4;
            }
            if (string.contains("ctrl+")) {
                string = string.replace("ctrl+", "");
                ++ag2.b;
            }
            if (string.contains("shift+")) {
                string = string.replace("shift+", "");
                ag2.b += 2;
            }
            try {
                ag2.e = SlickToAndroidKeycodes.a(string);
                if (n3 == -1) {
                    this.keyCodes.add(ag2);
                } else {
                    if (this.keyCodes.size() <= n3) {
                        this.keyCodes.add(new NullInput());
                    }
                    if (this.keyCodes.size() <= n3) {
                        this.keyCodes.add(new NullInput());
                    }
                    this.keyCodes.set(n3, ag2);
                }
            }
            catch (SlickToAndroidKeycodes$MissingKey slickToAndroidKeycodes$MissingKey) {
                slickToAndroidKeycodes$MissingKey.printStackTrace();
                GlobalState l2 = GlobalState.B();
                if (l2 == null) break block10;
                l2.a(slickToAndroidKeycodes$MissingKey.getMessage(), 1);
            }
        }
        return this;
    }

    public KeyBinding b(int n2, int n3, int n4, boolean bl) {
        AxisTrigger ah2 = new AxisTrigger();
        ah2.textureId = n2;
        ah2.e = n3;
        ah2.f = n4;
        ah2.g = bl;
        try {
            ah2.i = ah2.a(true);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            GlobalState.b("Failed to bind Axis:" + n4 + " on joystick:" + n3);
            return this;
        }
        this.keyCodes.add(ah2);
        return this;
    }

    public boolean d() {
        return false;
    }

    public String e() {
        return this.bindingName.replace("-", "").replace("  ", " ").replace("  ", " ").replace(" ", "_").toLowerCase(Locale.ENGLISH);
    }
}
