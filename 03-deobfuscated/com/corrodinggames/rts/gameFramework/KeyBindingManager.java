/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.KeyBinding;
import com.corrodinggames.rts.gameFramework.ShaderProgram;
import com.corrodinggames.rts.gameFramework.TextureCache;
import com.corrodinggames.rts.gameFramework.InputProvider;
import com.corrodinggames.rts.gameFramework.AbstractInputConfig;
import com.corrodinggames.rts.gameFramework.DefaultInputConfig;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$MissingKey;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

public class KeyBindingManager {
    public static AbstractInputConfig a = new DefaultInputConfig();
    public static InputProvider b = new InputProvider();
    public KeyBinding c;
    public KeyBinding d;
    public KeyBinding debugLeftAlt;
    public KeyBinding debugRightAlt;
    public KeyBinding debugLeftCtrl;
    public KeyBinding debugRightCtrl;
    public KeyBinding debugLeftShift;
    public KeyBinding debugRightShift;
    public KeyBinding keyRepeatTimer;
    public KeyBinding keyRepeatRate = this.digitToKeycode("Debug Left");
    public KeyBinding isKeyRepeatEnabled = this.digitToKeycode("Debug Right");
    public KeyBinding cameraUpKey = this.createEditableBinding("Camera Up");
    public KeyBinding cameraDownKey = this.createEditableBinding("Camera Down");
    public KeyBinding cameraLeftKey = this.createEditableBinding("Camera Left");
    public KeyBinding cameraRightKey = this.createEditableBinding("Camera Right");
    public KeyBinding zoomInKey = this.createEditableBinding("Zoom In");
    public KeyBinding zoomOutKey = this.createEditableBinding("Zoom Out");
    public KeyBinding sendChatKey = this.createEditableBinding("Send Chat");
    public KeyBinding sendTeamChat = this.createEditableBinding("Send Team Chat");
    public KeyBinding pingMap = this.createEditableBinding("Ping Map");
    public KeyBinding showMenu = this.createEditableBinding("Show Menu");
    public KeyBinding saveGameKey = this.createEditableBinding("Save Game");
    public KeyBinding deselectKey = this.createEditableBinding("Deselect units");
    public KeyBinding gotoNotification = this.createEditableBinding("Go to notification");
    public KeyBinding selectAllKey = this.createEditableBinding("Select Whole Army");
    public KeyBinding selectCommandCenter = this.createEditableBinding("Select Command Center");
    public KeyBinding cycleBuilders = this.createEditableBinding("Cycle Builders");
    public KeyBinding cycleExtractors = this.createEditableBinding("Cycle Extractors");
    public KeyBinding cycleFactories = this.createEditableBinding("Cycle Upgradable Fabricators");
    public KeyBinding cycleLandFactories = this.createEditableBinding("Cycle Land Factories");
    public KeyBinding cycleAirFactories = this.createEditableBinding("Cycle Air Factories");
    public KeyBinding nextTrackKey = this.createEditableBinding("Next Music Track");
    public KeyBinding speedCategory = this.createAxisBinding("Game Speed (Single player)");  // 02b ae 轴 (ShaderProgram 为幻觉名)
    public KeyBinding slowerSpeed = this.createEditableBinding("Slower");
    public KeyBinding fasterSpeed = this.createEditableBinding("Faster");
    public KeyBinding pauseGame = this.createEditableBinding("Pause Game");
    public KeyBinding unitActionsCategory = this.createAxisBinding("Unit Actions");
    public KeyBinding attackMoveKey = this.createEditableBinding("Attack Move");
    public KeyBinding stopKey = this.createEditableBinding("Stop");
    public KeyBinding guardKey = this.createEditableBinding("Guard Unit");
    public KeyBinding patrolKey = this.createEditableBinding("Patrol");
    public KeyBinding reclaimKey = this.createEditableBinding("Reclaim");
    public KeyBinding upgradeKey = this.createEditableBinding("Action - Upgrade");
    public KeyBinding rallyKey = this.createEditableBinding("Action - Set Rally");
    public KeyBinding editorKey = this.digitToKeycode("Debug Editor");
    public KeyBinding ad44 = this.digitToKeycode("Debug Pause");
    public KeyBinding ad45 = this.digitToKeycode("Debug Slow");
    public KeyBinding ad46 = this.digitToKeycode("Debug HideInterface");
    public KeyBinding ad47 = this.digitToKeycode("Debug HideInterface Temp");
    public KeyBinding ad48 = this.digitToKeycode("Debug InvincibleUnits");
    public KeyBinding quickLoadKey = this.digitToKeycode("debugPrintSelectedUnit");
    public KeyBinding ad50 = this.digitToKeycode("debugDevModeSwitch");
    public KeyBinding ad51 = this.digitToKeycode("debugAIViewSwitch");
    public KeyBinding ad52 = this.digitToKeycode("debugMapSwitch");
    public KeyBinding ad53 = this.digitToKeycode("Debug Take Screenshot");
    public KeyBinding ad54 = this.digitToKeycode("Debug Take Screenshot High");
    public KeyBinding[] unitActionKeys;
    public KeyBinding ae3;  // 02b ac.ah (ae 轴)
    public KeyBinding[] selectGroupKeys;
    public KeyBinding[] addToGroupKeys;
    public KeyBinding[] createGroupKeys;
    public ArrayList allBindings;
    Properties savedKeyConfig;
    int controllerCount;
    int lastControllerCount;

    public KeyBindingManager() {
        int cameraUpKey;
        int n2;
        int n3;
        this.cameraUpKey.a("UP").a("NUMPAD8");
        this.cameraDownKey.a("DOWN").a("NUMPAD2");
        this.cameraLeftKey.a("LEFT").a("NUMPAD4");
        this.cameraRightKey.a("RIGHT").a("NUMPAD6");
        this.keyRepeatRate.a("F5");
        this.isKeyRepeatEnabled.a("F6");
        this.saveGameKey.a("CTRL+S");
        this.sendChatKey.a("ENTER").a("T");
        this.sendTeamChat.a("SHIFT+ENTER").a("Y");
        this.pingMap.a("CTRL+M").a("CTRL+P");
        this.showMenu.a("ESCAPE").a("F10");
        this.deselectKey.a("SPACE");
        this.gotoNotification.a("CTRL+SPACE");
        this.selectAllKey.a("CTRL+A");
        this.cycleBuilders.a("CTRL+B");
        this.cycleExtractors.a("CTRL+E");
        this.cycleFactories.a("CTRL+F");
        this.cycleLandFactories.a("CTRL+L");
        this.cycleAirFactories.a("CTRL+K");
        this.selectCommandCenter.a("CTRL+C");
        this.nextTrackKey.a("CTRL+N");
        this.attackMoveKey.a("A");
        this.pauseGame.a("BREAK");
        this.stopKey.a("S");
        this.guardKey.a("G");
        this.patrolKey.a("P");
        this.upgradeKey.a("U");
        this.rallyKey.a("R");
        this.editorKey.a("CTRL+SHIFT+E");
        this.ad44.a("CTRL+SHIFT+P");
        this.ad45.a("CTRL+SHIFT+S");
        this.ad46.a("CTRL+SHIFT+H");
        this.ad47.a("CTRL+H");
        this.ad48.a("CTRL+SHIFT+I");
        this.quickLoadKey.a("CTRL+SHIFT+L");
        this.ad50.a("CTRL+SHIFT+D");
        this.ad51.a("SHIFT+F3");
        this.ad52.a("SHIFT+F4");
        this.ad53.a("CTRL+SHIFT+ALT+S");
        this.ad54.a("CTRL+SHIFT+ALT+D");
        this.slowerSpeed.a("minus").a("NUMPADSUBTRACT");
        this.fasterSpeed.a("equals").a("NUMPADADD");
        int[] nArray = new int[]{54, 52, 31, 50, 30, 42, 41, 38, 39, 40, 37, 43};
        this.unitActionKeys = new KeyBinding[10];
        for (n3 = 0; n3 < this.unitActionKeys.length; ++n3) {
            this.unitActionKeys[n3] = this.createEditableBinding("unit action " + (n3 + 1));
            this.unitActionKeys[n3].c(nArray[n3]);
        }
        this.ae3 = this.createAxisBinding("Unit Groups");
        this.createGroupKeys = new KeyBinding[10];
        for (n3 = 0; n3 < this.createGroupKeys.length; ++n3) {
            this.createGroupKeys[n3] = this.createEditableBinding("create group " + (n3 + 1));
            n2 = this.digitToKeycode(n3 == 9 ? 0 : n3 + 1);
            cameraUpKey = 1;
            this.createGroupKeys[n3].a(n2, 0, cameraUpKey, false);
        }
        this.selectGroupKeys = new KeyBinding[10];
        for (n3 = 0; n3 < this.selectGroupKeys.length; ++n3) {
            this.selectGroupKeys[n3] = this.createEditableBinding("select group " + (n3 + 1));
            n2 = this.digitToKeycode(n3 == 9 ? 0 : n3 + 1);
            this.selectGroupKeys[n3].c(n2);
        }
        this.addToGroupKeys = new KeyBinding[10];
        for (n3 = 0; n3 < this.addToGroupKeys.length; ++n3) {
            this.addToGroupKeys[n3] = this.createEditableBinding("Add group to selection " + (n3 + 1));
            n2 = this.digitToKeycode(n3 == 9 ? 0 : n3 + 1);
            cameraUpKey = 2;
            this.addToGroupKeys[n3].a(n2, 0, cameraUpKey, false);
        }
        this.savedKeyConfig = new Properties();
        this.controllerCount = 0;
        this.lastControllerCount = 0;
    }

    public int digitToKeycode(int n) {
        if (n >= 10) {
            throw new RuntimeException("number:" + n + " too high");
        }
        if (n == 0) {
            return 7;
        }
        return 8 + (n - 1);
    }

    public KeyBinding digitToKeycode(String string) {
        if (this.allBindings == null) {
            this.allBindings = new ArrayList();
        }
        KeyBinding ad2 = new KeyBinding();
        ad2.bindingName = string;
        ad2.isActive = false;
        this.allBindings.add(ad2);
        return ad2;
    }

    public KeyBinding createEditableBinding(String string) {
        if (this.allBindings == null) {
            this.allBindings = new ArrayList();
        }
        KeyBinding ad2 = new KeyBinding();
        ad2.bindingName = string;
        ad2.isActive = true;
        this.allBindings.add(ad2);
        return ad2;
    }

    public KeyBinding createAxisBinding(String string) {  // 02b ac.java c(String) L205-215 (ae=KeyBinding 子类简化)
        if (this.allBindings == null) {
            this.allBindings = new ArrayList();
        }
        KeyBinding ae2 = new KeyBinding();
        ae2.bindingName = string;
        ae2.isActive = true;
        this.allBindings.add(ae2);
        return ae2;
    }

    public void digitToKeycode(String string, String string2) {
        string = string.toLowerCase(Locale.ENGLISH).trim();
        KeyBinding ad2 = null;
        for (KeyBinding ad3 : (java.util.Collection<KeyBinding>) (java.util.Collection) this.allBindings) {
            if (ad3.bindingName == null || !ad3.e().equals(string)) continue;
            ad2 = ad3;
        }
        if (ad2 == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("loadKey: could not find:" + string);
            return;
        }
        String[] stringArray = string2.split(",");
        for (int debugLeftShift = 0; debugLeftShift <= 1 && debugLeftShift < stringArray.length; ++debugLeftShift) {
            String string3 = stringArray[debugLeftShift];
            if (string3.equalsIgnoreCase("DEFAULT")) continue;
            ad2.a(string3, debugLeftShift);
            if (ad2.keyCodes.size() > debugLeftShift && ad2.keyCodes.get(debugLeftShift) != null) {
                ((TextureCache) ad2.keyCodes.get(debugLeftShift)).isDirty = true;  // 02b af.d
                continue;
            }
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("out of range");
        }
    }

    public String digitToKeycode(KeyBinding ad2) {
        String string = "";
        boolean bl = true;
        for (Object object : ad2.keyCodes) {
            TextureCache af2 = (TextureCache) object;
            if (bl) {
                bl = false;
            } else {
                string = string + ",";
            }
            if (af2.isDirty) {
                if (af2.d()) {
                    string = string + "CLEARED";
                    continue;
                }
                string = string + af2.c();
                continue;
            }
            string = string + "DEFAULT";
        }
        return string;
    }

    public boolean digitToKeycode(KeyBinding ad2, int n2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        TextureCache af2 = ad2.a(n2);
        if (af2 == null) {
            return false;
        }
        ArrayList arrayList = l2.bT.allBindings;
        for (int debugRightShift = 0; debugRightShift < arrayList.size(); ++debugRightShift) {
            KeyBinding ad3 = (KeyBinding) arrayList.get(debugRightShift);
            if (ad3 == ad2) continue;
            for (Object object2 : ad3.keyCodes) {
                TextureCache af3 = (TextureCache) object2;
                if (!af2.a(af3)) continue;
                return true;
            }
        }
        return false;
    }

    public void digitToKeycode() {
        this.c = this.digitToKeycode("shoot");
        this.d = this.digitToKeycode("move up");
        this.debugLeftAlt = this.digitToKeycode("move down");
        this.debugRightAlt = this.digitToKeycode("move left");
        this.debugLeftCtrl = this.digitToKeycode("move right");
        this.debugRightCtrl = this.digitToKeycode("aim up");
        this.debugLeftShift = this.digitToKeycode("aim down");
        this.debugRightShift = this.digitToKeycode("aim left");
        this.keyRepeatTimer = this.digitToKeycode("aim right");
        int n2 = 0;
        this.c.a(n2, "enter", -1);
        this.c.a(n2, "space", -1);
        this.d.a(n2, "w", -1);
        this.debugLeftAlt.a(n2, "s", -1);
        this.debugRightAlt.a(n2, "a", -1);
        this.debugLeftCtrl.a(n2, "d", -1);
        this.debugRightCtrl.a(n2, "UP", -1);
        this.debugLeftShift.a(n2, "DOWN", -1);
        this.debugRightShift.a(n2, "LEFT", -1);
        this.keyRepeatTimer.a(n2, "RIGHT", -1);
        com.corrodinggames.rts.gameFramework.GlobalState.e("getControllerCount:" + b.a());  // 02b L323: 静态字段 b=ai (InputProvider)
        int n3 = 1;
        this.d.b(n2, n3, 0, true);
        this.debugLeftAlt.b(n2, n3, 0, false);
        this.debugRightAlt.b(n2, n3, 1, true);
        this.debugLeftCtrl.b(n2, n3, 1, false);
        this.debugRightCtrl.b(n2, n3, 2, true);
        this.debugLeftShift.b(n2, n3, 2, false);
        this.debugRightShift.b(n2, n3, 3, true);
        this.keyRepeatTimer.b(n2, n3, 3, false);
        this.c.b(n2, n3, 4, true);
    }

    public void createEditableBinding() {
        if (b.a() != this.lastControllerCount) {
            this.lastControllerCount = b.a();
            com.corrodinggames.rts.gameFramework.GlobalState.e("Number of controllers changed, now:" + this.lastControllerCount);
        }
    }

    public static int parseKeycode(String string) throws SlickToAndroidKeycodes$MissingKey {
        if (string.equalsIgnoreCase("CLEARED")) {
            return 0;
        }
        return SlickToAndroidKeycodes.a(string);
    }
}
