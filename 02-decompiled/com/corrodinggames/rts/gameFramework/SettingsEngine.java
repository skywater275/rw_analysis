/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.net.Uri
 */
package com.corrodinggames.rts.gameFramework;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import com.corrodinggames.rts.appFramework.c;
import com.corrodinggames.rts.gameFramework.ad;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.a.a;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SettingsEngine {
    public boolean enableSounds;
    public boolean enableMouseCapture;
    public boolean androidNoSoundPrioritiesDebug;
    public boolean disableDigitGrouping;
    public boolean resizeFontWithUIScale = true;
    public String slick2dResolution;
    public int settingsGameVersion;
    public int settingsGameVersionFirst;
    public String slick2dFullScreenResolution;
    public boolean upgradedToNoPublicStorage;
    public boolean upgradedToNoPublicStorageWarningShown;
    public boolean slick2dFullScreen;
    public boolean slick2dBorderless;
    public boolean autosaving = true;
    public float masterVolume;
    public float gameVolume;
    public float interfaceVolume;
    public float musicVolume;
    public float scrollSpeed;
    public float edgeScrollSpeed;
    public boolean hasPlayedGameOrSeenHelp;
    public boolean onscreenControls;
    public boolean trackpad;
    public boolean dpad;
    public boolean batterySaving;
    public boolean highRefreshRate;
    public boolean renderBackground;
    public boolean renderExtraLayers;
    public boolean immersiveFullScreen;
    public boolean displayOverCutout;
    public boolean unlockedScreenRotation;
    public boolean renderDoubleScale;
    public float renderDensity = 1.0f;
    public float uiRenderScale = 1.0f;
    public boolean renderExtraShadows = true;
    public boolean renderFancyWater = false;
    public boolean renderClouds = false;
    public boolean renderWithLineWidth;
    public boolean softFogFading = false;
    public boolean showActionInfoHoverNearMouse = true;
    public boolean disableModLazyLoad = false;
    public boolean showUnitGroups;
    public boolean allowGameRecording;
    public boolean renderAntiAlias = true;
    public boolean renderControls;
    public boolean showHp;
    public boolean showHpChanges;
    public boolean showUnitIcons;
    public boolean gestureZoom;
    public boolean showSelectedUnitsList = true;
    public boolean useCircleSelect;
    public boolean showZoomButton;
    public boolean showFps;
    public boolean newRender;
    public boolean shaderEffects;
    public boolean teamShaders;
    public boolean showUnitWaypoints;
    public boolean useMinimapAllyColors;
    public boolean showWarLogOnScreen;
    public boolean classicInterface;
    public boolean quickRally;
    public boolean doubleClickToAttackMove = false;
    public boolean showMapPingsOnBattlefield = true;
    public boolean showMapPingsOnMinimap = true;
    public boolean showPlayerChatInGame = true;
    public boolean sendReports;
    public boolean shownAudioWarning;
    public boolean mouseSupport;
    public boolean keyboardSupport;
    public boolean forceEnglish;
    public String overrideLanguageCode;
    public boolean saveMultiplayerReplays;
    public boolean replaysShowRecordedChat;
    public int nextBackgroundMap;
    public String lastNetworkPlayerName;
    public String lastNetworkIP;
    public String lastDebugOption;
    public String teamColors = "#00ff00,#d02013,#0463f3,#ffff40,#00ffff,#d0f8f7,#000000,#ff00ea,#ff7f18,#9368c4";
    public String teamColorsNames = "GREEN,RED,BLUE,YELLOW,CYAN,WHITE,BLACK,PINK,ORANGE,PURPLE";
    public boolean landscapeOrientation;
    public int aiDifficulty;
    public int locationAction;
    public int locationDpad;
    public int keyAction;
    public int keyJump;
    public int keyLeft;
    public int keyRight;
    public int keyDown;
    public String uuid;
    public String networkClientId;
    public String networkClientIdMachineKey;
    public String networkServerId;
    public int numIncompleteLoadAttempts;
    public int numLoadsSinceRunningGameOrNormalExit;
    public int lastSeenMessageId;
    public String lastSeenMessageIds;
    public int networkPort;
    public boolean udpInMultiplayer;
    public int banTimeInSecondsAfterKick = 60;
    public int numberOfWins;
    public boolean rateGameShown;
    public boolean highGraphics = true;
    public int mouseOrders;
    public int mousePlacement;
    public boolean liveReloading;
    public boolean renderVsync;
    public boolean renderSmoothDelta;
    public int teamUnitCapSinglePlayer = 1000;
    public int teamUnitCapHostedGame = 250;
    public boolean showChatAndPingShortcuts = true;
    public String modSettings;
    public int modSettingsVersion;
    public int storageType;
    public boolean hasSelectedAStorageType;
    public boolean hadStoragePermissionInPast;
    public boolean loadDisabledModData;
    public int lastModCount;
    public String modSAFlinks;
    public boolean externalSAFWorking;
    public String externalSAFLink;
    public String externalSAFPathShown;
    public String externalSAFPathExtra;
    public boolean smartSelection_v2;
    public boolean replayTracing;
    SharedPreferences prefs;
    static SettingsEngine settingsEngine = null;
    HashMap settingFields = new HashMap();

    public static SettingsEngine getInstance(Context context) {
        if (settingsEngine == null) {
            settingsEngine = new SettingsEngine(context);
        }
        return settingsEngine;
    }

    public boolean getBooleanPref(String string, boolean bl) {
        if (l.aU) {
            return bl;
        }
        return this.prefs.getBoolean(string, bl);
    }

    public int getIntPref(String string, int n2) {
        if (l.aU) {
            return n2;
        }
        return this.prefs.getInt(string, n2);
    }

    public float getFloatPref(String string, float f2) {
        if (l.aU) {
            return f2;
        }
        return this.prefs.getFloat(string, f2);
    }

    public String getStringPref(String string, String string2) {
        if (l.aU) {
            return string2;
        }
        return this.prefs.getString(string, string2);
    }

    public String getValueDynamic(String string) {
        Object object;
        try {
            Field field = (Field)this.settingFields.get(string);
            if (field == null) {
                throw new RuntimeException("Could not find: " + string);
            }
            object = field.get(this);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw new RuntimeException(illegalArgumentException);
        }
        catch (IllegalAccessException illegalAccessException) {
            throw new RuntimeException(illegalAccessException);
        }
        if (object == null) {
            return null;
        }
        return object.toString();
    }

    public boolean setValueDynamic(String string, String string2) {
        try {
            Field field = (Field)this.settingFields.get(string);
            Object object = string2;
            if (field.getType().equals(Boolean.TYPE)) {
                if (string2 == null) {
                    throw new RuntimeException("value==null");
                }
                object = Boolean.parseBoolean(string2);
            }
            if (field.getType().equals(Float.TYPE)) {
                if (string2 != null && string2.contains(",")) {
                    string2 = string2.replace(",", ".");
                }
                object = Float.valueOf(Float.parseFloat(string2));
            }
            if (field.getType().equals(Integer.TYPE)) {
                object = Integer.parseInt(string2);
            }
            field.set(this, object);
        }
        catch (IllegalAccessException illegalAccessException) {
            throw new RuntimeException(illegalAccessException);
        }
        return true;
    }

    public String getPreferencesPath() {
        String string = "/SD/rustedWarfare/preferences.ini";
        String string2 = com.corrodinggames.rts.gameFramework.e.a.e(string);
        return string2;
    }

    public boolean saveToFileSystem() {
        l l2 = l.B();
        File file = new File(this.getPreferencesPath());
        l.e("Saving settings to: " + file.getAbsolutePath());
        try {
            String string;
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println("[settings]");
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.settingFields.keySet());
            Collections.sort(arrayList);
            for (Object object : arrayList) {
                string = this.getValueDynamic((String)object);
                if (string == null) {
                    string = "";
                }
                string = string.replace("\\", "\\\\");
                string = string.replace("\n", "\\n");
                printWriter.println((String)object + ":" + string);
            }
            printWriter.println("[keys]");
            for (Object object : l2.bT.al) {
                if (!((ad)object).b || ((ad)object).d()) continue;
                string = "";
                string = string + ((ad)object).e();
                String string2 = l2.bT.a((ad)object);
                printWriter.println(string + ":" + string2);
            }
            printWriter.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            l.B().a("Failed to save preferences, IO error", 1);
            return false;
        }
        return true;
    }

    public void loadFromFileSystem() {
        l l2 = l.B();
        String string = this.getPreferencesPath();
        File file = new File(string);
        if (!file.exists()) {
            l.e("preferences not found, not loading (" + string + ")");
            return;
        }
        l.e("loadFromFileSystem filepath: " + file.getAbsolutePath());
        String string2 = "settings";
        try {
            String string3;
            String string4;
            ab ab2 = new ab(string);
            for (Map.Entry object : this.settingFields.entrySet()) {
                string4 = (String)object.getKey();
                string3 = ab2.b(string2, string4, (String)null);
                if (l.aZ) {
                    l.e(string4 + "= " + string3);
                }
                if (string3 == null || "".equals(string3)) continue;
                if (string4.startsWith("key.")) {
                    string4 = string4.replace("key.", "");
                    l.e("loading keybind:" + string4);
                    l2.bT.a(string4, string3);
                    continue;
                }
                this.setValueDynamic(string4, string3);
            }
            for (ad ad2 : l2.bT.al) {
                if (!ad2.b || ad2.d() || (string3 = ab2.b("keys", string4 = ad2.e(), (String)null)) == null || "".equals(string3)) continue;
                l2.bT.a(string4, string3);
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            l.B().a("Failed to load preferences, IO error", 1);
        }
    }

    private SettingsEngine(Context context) {
        Field[] fieldArray;
        for (Field field : fieldArray = this.getClass().getFields()) {
            String string = field.getName();
            if (this.settingFields.get(string) != null) {
                l.e("SettingsEngine: fields: " + string + " already exists");
            }
            if (l.aZ) {
                l.e("SettingsEngine: field:" + string);
            }
            this.settingFields.put(string, field);
        }
        if (!l.aU) {
            this.prefs = context.a("rts_settings", 0);
        }
        int n2 = this.getIntPref("settingVersion", 1);
        this.settingsGameVersion = this.getIntPref("settingsGameVersion", 0);
        this.settingsGameVersionFirst = this.getIntPref("settingsGameVersionFirst", 0);
        if (this.settingsGameVersionFirst == 0) {
            this.settingsGameVersionFirst = this.settingsGameVersion != 0 ? this.settingsGameVersion : 176;
        }
        int n3 = 0;
        if (this.settingsGameVersionFirst <= 160) {
            n3 = 1;
        }
        this.upgradedToNoPublicStorage = this.getBooleanPref("upgradedToNoPublicStorage", n3 != 0);
        this.upgradedToNoPublicStorageWarningShown = this.getBooleanPref("upgradedToNoPublicStorageWarningShown", false);
        this.slick2dResolution = this.getStringPref("slick2dResolution", "native");
        this.slick2dFullScreenResolution = this.getStringPref("slick2dFullScreenResolution", "native");
        this.slick2dFullScreen = this.getBooleanPref("slick2dFullScreen", false);
        this.hasPlayedGameOrSeenHelp = this.getBooleanPref("hasPlayedGameOrSeenHelp", false);
        this.enableSounds = this.getBooleanPref("enableSounds", true);
        this.enableMouseCapture = this.getBooleanPref("enableMouseCapture", false);
        this.androidNoSoundPrioritiesDebug = this.getBooleanPref("androidNoSoundPrioritiesDebug", false);
        this.disableDigitGrouping = this.getBooleanPref("disableDigitGrouping", false);
        this.musicVolume = this.getFloatPref("musicVolume", 0.25f);
        float f2 = 1.0f;
        if (l.av()) {
            f2 = 0.5f;
        }
        this.masterVolume = this.getFloatPref("masterVolume", f2);
        this.gameVolume = this.getFloatPref("gameVolume", 1.0f);
        this.interfaceVolume = this.getFloatPref("interfaceVolume", 0.8f);
        this.scrollSpeed = this.getFloatPref("scrollSpeed", 1.0f);
        this.edgeScrollSpeed = this.getFloatPref("edgeScrollSpeed", 1.0f);
        this.onscreenControls = this.getBooleanPref("onscreenControls", true);
        this.trackpad = this.getBooleanPref("trackpad", true);
        this.dpad = this.getBooleanPref("dpad", true);
        this.batterySaving = this.getBooleanPref("batterySaving", false);
        boolean bl = false;
        if (l.av()) {
            bl = true;
        }
        this.highRefreshRate = this.getBooleanPref("highRefreshRate", bl);
        this.unlockedScreenRotation = this.getBooleanPref("unlockedScreenRotation", false);
        this.renderBackground = this.getBooleanPref("renderBackground", true);
        this.renderExtraLayers = this.getBooleanPref("renderExtraLayers", true);
        this.renderControls = this.getBooleanPref("renderControls", true);
        this.immersiveFullScreen = this.getBooleanPref("immersiveFullScreen", true);
        this.displayOverCutout = this.getBooleanPref("displayOverCutout", false);
        this.renderDoubleScale = this.getBooleanPref("renderDoubleScale", false);
        this.showUnitGroups = this.getBooleanPref("showUnitGroups", true);
        boolean bl2 = false;
        if (l.av()) {
            bl2 = true;
        }
        if (l.aZ) {
            bl2 = true;
        }
        this.renderClouds = this.getBooleanPref("renderClouds", bl2);
        this.renderWithLineWidth = this.getBooleanPref("renderWithLineWidth", true);
        boolean bl3 = false;
        if (l.av()) {
            bl3 = true;
        }
        if (l.aZ) {
            bl3 = true;
        }
        this.softFogFading = this.getBooleanPref("softFogFading", bl3);
        this.showUnitWaypoints = this.getBooleanPref("showUnitWaypoints", true);
        this.useMinimapAllyColors = this.getBooleanPref("useMinimapAllyColors", true);
        boolean bl4 = false;
        if (l.av()) {
            bl4 = true;
        }
        this.showWarLogOnScreen = this.getBooleanPref("showWarLogOnScreen", bl4);
        boolean bl5 = false;
        this.classicInterface = this.getBooleanPref("classicInterface", bl5);
        boolean bl6 = false;
        if (l.av()) {
            bl6 = true;
        }
        this.quickRally = this.getBooleanPref("quickRally", bl6);
        if (n2 <= 1 && !l.av()) {
            this.quickRally = bl6;
        }
        this.doubleClickToAttackMove = this.getBooleanPref("doubleClickToAttackMove", true);
        this.showMapPingsOnBattlefield = this.getBooleanPref("showMapPingsOnBattlefield", true);
        this.showMapPingsOnMinimap = this.getBooleanPref("showMapPingsOnMinimap", true);
        this.showPlayerChatInGame = this.getBooleanPref("showPlayerChatInGame", true);
        this.allowGameRecording = false;
        this.showHp = this.getBooleanPref("showHp", true);
        this.showHpChanges = this.getBooleanPref("showHpChanges", true);
        this.showUnitIcons = this.getBooleanPref("showUnitIcons", true);
        this.gestureZoom = this.getBooleanPref("gestureZoom", true);
        this.useCircleSelect = this.getBooleanPref("useCircleSelect", false);
        this.showZoomButton = this.getBooleanPref("showZoomButton", true);
        this.showFps = this.getBooleanPref("showFps", false);
        this.newRender = this.getBooleanPref("newRender", false);
        this.shaderEffects = this.getBooleanPref("shaderEffects", false);
        this.teamShaders = this.getBooleanPref("teamShaders", false);
        this.sendReports = this.getBooleanPref("sendReports", true);
        this.shownAudioWarning = this.getBooleanPref("shownAudioWarning", false);
        this.mouseSupport = this.getBooleanPref("mouseSupport", !l.d(context));
        this.keyboardSupport = this.getBooleanPref("keyboardSupport", true);
        boolean bl7 = false;
        if (l.aZ) {
            bl7 = true;
        }
        this.forceEnglish = this.getBooleanPref("forceEnglish", bl7);
        boolean bl8 = false;
        if (l.av()) {
            bl8 = true;
        }
        this.saveMultiplayerReplays = this.getBooleanPref("saveMultiplayerReplays", bl8);
        if (n2 <= 1) {
            this.saveMultiplayerReplays = bl8;
        }
        this.replaysShowRecordedChat = this.getBooleanPref("replaysShowRecordedChat", true);
        this.lastNetworkPlayerName = this.getStringPref("lastNetworkPlayerName", null);
        this.lastNetworkIP = this.getStringPref("lastNetworkIP", null);
        this.lastDebugOption = this.getStringPref("lastDebugOption", null);
        this.aiDifficulty = this.getIntPref("aiDifficulty", 0);
        this.locationDpad = this.getIntPref("locationDpad", 0);
        this.locationAction = this.getIntPref("locationAction", 3);
        this.keyAction = this.getIntPref("keyAction", 23);
        this.keyJump = this.getIntPref("keyJump", 19);
        this.keyLeft = this.getIntPref("keyLeft", 21);
        this.keyRight = this.getIntPref("keyRight", 22);
        this.keyDown = this.getIntPref("keyDown", 20);
        this.landscapeOrientation = this.getBooleanPref("landscapeOrientation", true);
        this.networkPort = this.getIntPref("networkPort", 5123);
        if (this.networkPort < 1024 || this.networkPort > 65535) {
            this.networkPort = 5123;
        }
        this.udpInMultiplayer = this.getBooleanPref("udpInMultiplayer", false);
        this.banTimeInSecondsAfterKick = this.getIntPref("banTimeInSecondsAfterKick", 60);
        this.numIncompleteLoadAttempts = this.getIntPref("numIncompleteLoadAttempts", 0);
        this.numLoadsSinceRunningGameOrNormalExit = this.getIntPref("numLoadsSinceRunningGameOrNormalExit", 0);
        this.numberOfWins = this.getIntPref("numberOfWins", 0);
        this.rateGameShown = this.getBooleanPref("rateGameShown", false);
        this.uuid = this.getStringPref("uuid", null);
        this.networkClientId = this.getStringPref("networkClientId", null);
        this.networkServerId = this.getStringPref("networkServerId", null);
        this.lastSeenMessageId = this.getIntPref("lastSeenMessageId", -1);
        this.lastSeenMessageIds = this.getStringPref("lastSeenMessageIds", "");
        this.nextBackgroundMap = this.getIntPref("nextBackgroundMap", 1);
        this.showChatAndPingShortcuts = this.getBooleanPref("showChatAndPingShortcuts", true);
        this.teamUnitCapSinglePlayer = this.getIntPref("teamUnitCapSinglePlayer", 1000);
        this.teamUnitCapHostedGame = this.getIntPref("teamUnitCapHostedGame", 250);
        this.modSettings = this.getStringPref("modSettings", "");
        this.modSettingsVersion = this.getIntPref("modSettingsVersion", 0);
        boolean bl9 = false;
        if (l.at() && c.b(c.a())) {
            bl9 = true;
        }
        int n4 = 0;
        if (bl9) {
            n4 = 2;
        }
        this.storageType = this.getIntPref("storageType", n4);
        this.hadStoragePermissionInPast = this.getBooleanPref("hadStoragePermissionInPast", false);
        if (bl9) {
            this.hadStoragePermissionInPast = true;
        }
        this.hasSelectedAStorageType = this.getBooleanPref("hasSelectedAStorageType", false);
        this.loadDisabledModData = this.getBooleanPref("loadDisabledModData", false);
        this.lastModCount = this.getIntPref("lastModCount", -1);
        this.modSAFlinks = this.getStringPref("modSAFlinks", null);
        this.externalSAFWorking = this.getBooleanPref("externalSAFWorking", false);
        this.externalSAFLink = this.getStringPref("externalSAFLink", null);
        this.externalSAFPathShown = this.getStringPref("externalSAFPathShown", null);
        this.externalSAFPathExtra = this.getStringPref("externalSAFPathExtra", null);
        this.smartSelection_v2 = this.getBooleanPref("smartSelection_v2", true);
        this.mouseOrders = this.getIntPref("mouseOrders", 1);
        this.mousePlacement = this.getIntPref("mousePlacement", 1);
        this.autosaving = this.getBooleanPref("autosaving", true);
        if (l.aW) {
            this.loadFromFileSystem();
        }
        if (this.settingsGameVersion < 174) {
            this.uiRenderScale = 1.0f;
        }
    }

    public synchronized boolean save() {
        this.settingsGameVersion = 176;
        if (l.aU) {
            if (l.aW) {
                return this.saveToFileSystem();
            }
            return true;
        }
        SharedPreferences.Editor editor = this.prefs.edit();
        editor.putInt("settingVersion", 2);
        editor.putInt("settingsGameVersion", this.settingsGameVersion);
        editor.putInt("settingsGameVersionFirst", this.settingsGameVersionFirst);
        editor.putBoolean("upgradedToNoPublicStorage", this.upgradedToNoPublicStorage);
        editor.putBoolean("upgradedToNoPublicStorageWarningShown", this.upgradedToNoPublicStorageWarningShown);
        editor.putBoolean("hasPlayedGameOrSeenHelp", this.hasPlayedGameOrSeenHelp);
        editor.putString("slick2dResolution", this.slick2dResolution);
        editor.putString("slick2dFullScreenResolution", this.slick2dFullScreenResolution);
        editor.putBoolean("slick2dFullScreen", this.slick2dFullScreen);
        editor.putBoolean("enableSounds", this.enableSounds);
        editor.putBoolean("enableMouseCapture", this.enableMouseCapture);
        editor.putBoolean("androidNoSoundPrioritiesDebug", this.androidNoSoundPrioritiesDebug);
        editor.putBoolean("disableDigitGrouping", this.disableDigitGrouping);
        Log.d("RustedWarfare", "put mv:" + this.musicVolume);
        editor.putFloat("musicVolume", this.musicVolume);
        editor.putFloat("masterVolume", this.masterVolume);
        editor.putFloat("gameVolume", this.gameVolume);
        editor.putFloat("interfaceVolume", this.interfaceVolume);
        editor.putFloat("scrollSpeed", this.scrollSpeed);
        editor.putFloat("edgeScrollSpeed", this.edgeScrollSpeed);
        editor.putBoolean("onscreenControls", this.onscreenControls);
        editor.putBoolean("trackpad", this.trackpad);
        editor.putBoolean("dpad", this.dpad);
        editor.putBoolean("batterySaving", this.batterySaving);
        editor.putBoolean("highRefreshRate", this.highRefreshRate);
        editor.putBoolean("unlockedScreenRotation", this.unlockedScreenRotation);
        editor.putBoolean("renderBackground", this.renderBackground);
        editor.putBoolean("renderExtraLayers", this.renderExtraLayers);
        editor.putBoolean("renderControls", this.renderControls);
        editor.putBoolean("immersiveFullScreen", this.immersiveFullScreen);
        editor.putBoolean("displayOverCutout", this.displayOverCutout);
        editor.putBoolean("renderDoubleScale", this.renderDoubleScale);
        editor.putBoolean("showUnitGroups", this.showUnitGroups);
        editor.putBoolean("renderWithLineWidth", this.renderWithLineWidth);
        editor.putBoolean("renderClouds", this.renderClouds);
        editor.putBoolean("softFogFading", this.softFogFading);
        editor.putBoolean("showUnitWaypoints", this.showUnitWaypoints);
        editor.putBoolean("useMinimapAllyColors", this.useMinimapAllyColors);
        editor.putBoolean("showWarLogOnScreen", this.showWarLogOnScreen);
        editor.putBoolean("classicInterface", this.classicInterface);
        editor.putBoolean("quickRally", this.quickRally);
        editor.putBoolean("doubleClickToAttackMove", this.doubleClickToAttackMove);
        editor.putBoolean("showMapPingsOnBattlefield", this.showMapPingsOnBattlefield);
        editor.putBoolean("showMapPingsOnMinimap", this.showMapPingsOnMinimap);
        editor.putBoolean("showPlayerChatInGame", this.showPlayerChatInGame);
        editor.putBoolean("allowGameRecording", this.allowGameRecording);
        editor.putBoolean("showHp", this.showHp);
        editor.putBoolean("showHpChanges", this.showHpChanges);
        editor.putBoolean("showUnitIcons", this.showUnitIcons);
        editor.putBoolean("gestureZoom", this.gestureZoom);
        editor.putBoolean("useCircleSelect", this.useCircleSelect);
        editor.putBoolean("showZoomButton", this.showZoomButton);
        editor.putBoolean("showFps", this.showFps);
        editor.putBoolean("newRender", this.newRender);
        editor.putBoolean("shaderEffects", this.shaderEffects);
        editor.putBoolean("teamShaders", this.teamShaders);
        editor.putBoolean("sendReports", this.sendReports);
        editor.putBoolean("shownAudioWarning", this.shownAudioWarning);
        editor.putBoolean("mouseSupport", this.mouseSupport);
        editor.putBoolean("keyboardSupport", this.keyboardSupport);
        editor.putBoolean("forceEnglish", this.forceEnglish);
        editor.putBoolean("saveMultiplayerReplays", this.saveMultiplayerReplays);
        editor.putBoolean("replaysShowRecordedChat", this.replaysShowRecordedChat);
        editor.putString("lastNetworkPlayerName", this.lastNetworkPlayerName);
        editor.putString("lastNetworkIP", this.lastNetworkIP);
        editor.putString("lastDebugOption", this.lastDebugOption);
        editor.putInt("aiDifficulty", this.aiDifficulty);
        editor.putInt("locationDpad", this.locationDpad);
        editor.putInt("locationAction", this.locationAction);
        editor.putInt("keyAction", this.keyAction);
        editor.putInt("keyJump", this.keyJump);
        editor.putInt("keyLeft", this.keyLeft);
        editor.putInt("keyRight", this.keyRight);
        editor.putInt("keyDown", this.keyDown);
        editor.putBoolean("landscapeOrientation", this.landscapeOrientation);
        editor.putInt("networkPort", this.networkPort);
        editor.putBoolean("udpInMultiplayer", this.udpInMultiplayer);
        editor.putInt("banTimeInSecondsAfterKick", this.banTimeInSecondsAfterKick);
        editor.putInt("numIncompleteLoadAttempts", this.numIncompleteLoadAttempts);
        editor.putInt("numLoadsSinceRunningGameOrNormalExit", this.numLoadsSinceRunningGameOrNormalExit);
        editor.putInt("numberOfWins", this.numberOfWins);
        editor.putBoolean("rateGameShown", this.rateGameShown);
        editor.putString("uuid", this.uuid);
        editor.putString("networkClientId", this.networkClientId);
        editor.putString("networkServerId", this.networkServerId);
        editor.putInt("lastSeenMessageId", this.lastSeenMessageId);
        editor.putString("lastSeenMessageIds", this.lastSeenMessageIds);
        editor.putInt("nextBackgroundMap", this.nextBackgroundMap);
        editor.putBoolean("showChatAndPingShortcuts", this.showChatAndPingShortcuts);
        editor.putString("modSettings", this.modSettings);
        editor.putInt("modSettingsVersion", this.modSettingsVersion);
        editor.putInt("storageType", this.storageType);
        editor.putBoolean("hasSelectedAStorageType", this.hasSelectedAStorageType);
        editor.putBoolean("hadStoragePermissionInPast", this.hadStoragePermissionInPast);
        editor.putInt("teamUnitCapSinglePlayer", this.teamUnitCapSinglePlayer);
        editor.putInt("teamUnitCapHostedGame", this.teamUnitCapHostedGame);
        editor.putBoolean("loadDisabledModData", this.loadDisabledModData);
        editor.putInt("lastModCount", this.lastModCount);
        editor.putString("modSAFlinks", this.modSAFlinks);
        editor.putBoolean("externalSAFWorking", this.externalSAFWorking);
        editor.putString("externalSAFLink", this.externalSAFLink);
        editor.putString("externalSAFPathShown", this.externalSAFPathShown);
        editor.putString("externalSAFPathExtra", this.externalSAFPathExtra);
        editor.putBoolean("smartSelection_v2", this.smartSelection_v2);
        editor.putInt("mouseOrders", this.mouseOrders);
        editor.putInt("mousePlacement", this.mousePlacement);
        editor.putBoolean("autosaving", this.autosaving);
        editor.commit();
        return true;
    }

    public boolean loadMainExternalFolder(boolean bl) {
        if (!l.at()) {
            return false;
        }
        l.e("loadMainExternalFolder..");
        l l2 = l.B();
        if (bl && l2.c()) {
            l.e("Not loading due to extra safe mode");
            return false;
        }
        String string = l2.bQ.externalSAFLink;
        String string2 = l2.bQ.externalSAFPathShown;
        String string3 = l2.bQ.externalSAFPathExtra;
        if (string == null) {
            l.e("No external folder set");
            return false;
        }
        l.e("External saf link: " + string);
        l.e("External saf shown path: " + string2);
        l.e("External saf extra: " + string3);
        try {
            Uri uri = Uri.parse((String)string);
            boolean bl2 = false;
            String string4 = null;
            String string5 = a.a(uri, true);
            l.e("safVirualPathBase: " + string5);
            if (string5 == null) {
                l.e("createSAFLink failed for uri: " + uri);
                bl2 = true;
            } else {
                string4 = string5 + string3;
                l.e("safVirualPath: " + string4);
                if (!com.corrodinggames.rts.gameFramework.e.a.f(string4)) {
                    l.e("isDirectory failed for: " + string4);
                    bl2 = true;
                }
            }
            if (bl2) {
                l.e("error for uri: " + uri);
                l2.bQ.externalSAFWorking = false;
                String string6 = "Failed to read: " + string2 + " - Folder might have moved or permission expired. Please setup again under in-game settings.";
                if (com.corrodinggames.rts.gameFramework.i.a.a == null) {
                    com.corrodinggames.rts.gameFramework.i.a.a = "";
                }
                com.corrodinggames.rts.gameFramework.i.a.a = com.corrodinggames.rts.gameFramework.i.a.a + string6;
                com.corrodinggames.rts.gameFramework.i.a.b = string6;
                return false;
            }
            l.e("Using external path");
            l2.bQ.externalSAFWorking = true;
            com.corrodinggames.rts.gameFramework.e.a.d = string4;
            return true;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
