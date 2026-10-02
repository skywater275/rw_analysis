package com.corrodinggames.rts.gameFramework;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.ad;
import com.corrodinggames.rts.gameFramework.l;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
   public float renderDensity = 1.0F;
   public float uiRenderScale = 1.0F;
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


   public static SettingsEngine getInstance(Context var0) {
      if(settingsEngine == null) {
         settingsEngine = new SettingsEngine(var0);
      }

      return settingsEngine;
   }

   public boolean getBooleanPref(String var1, boolean var2) {
      return l.aU?var2:this.prefs.getBoolean(var1, var2);
   }

   public int getIntPref(String var1, int var2) {
      return l.aU?var2:this.prefs.getInt(var1, var2);
   }

   public float getFloatPref(String var1, float var2) {
      return l.aU?var2:this.prefs.getFloat(var1, var2);
   }

   public String getStringPref(String var1, String var2) {
      return l.aU?var2:this.prefs.getString(var1, var2);
   }

   public String getValueDynamic(String var1) {
      Object var2;
      try {
         Field var3 = (Field)this.settingFields.get(var1);
         if(var3 == null) {
            throw new RuntimeException("Could not find: " + var1);
         }

         var2 = var3.get(this);
      } catch (IllegalArgumentException var4) {
         throw new RuntimeException(var4);
      } catch (IllegalAccessException var5) {
         throw new RuntimeException(var5);
      }

      return var2 == null?null:var2.toString();
   }

   public boolean setValueDynamic(String var1, String var2) {
      try {
         Field var3 = (Field)this.settingFields.get(var1);
         Object var4 = var2;
         if(var3.getType().equals(Boolean.TYPE)) {
            if(var2 == null) {
               throw new RuntimeException("value==null");
            }

            var4 = Boolean.valueOf(Boolean.parseBoolean(var2));
         }

         if(var3.getType().equals(Float.TYPE)) {
            if(var2 != null && var2.contains(",")) {
               var2 = var2.replace(",", ".");
            }

            var4 = Float.valueOf(Float.parseFloat(var2));
         }

         if(var3.getType().equals(Integer.TYPE)) {
            var4 = Integer.valueOf(Integer.parseInt(var2));
         }

         var3.set(this, var4);
         return true;
      } catch (IllegalAccessException var5) {
         throw new RuntimeException(var5);
      }
   }

   public String getPreferencesPath() {
      String var1 = "/SD/rustedWarfare/preferences.ini";
      String var2 = com.corrodinggames.rts.gameFramework.e.a.e(var1);
      return var2;
   }

   public boolean saveToFileSystem() {
      l var1 = l.B();
      File var2 = new File(this.getPreferencesPath());
      l.e("Saving settings to: " + var2.getAbsolutePath());

      try {
         PrintWriter var3 = new PrintWriter(var2);
         var3.println("[settings]");
         ArrayList var4 = new ArrayList();
         var4.addAll(this.settingFields.keySet());
         Collections.sort(var4);
         Iterator var5 = var4.iterator();

         String var7;
         while(var5.hasNext()) {
            String var6 = (String)var5.next();
            var7 = this.getValueDynamic(var6);
            if(var7 == null) {
               var7 = "";
            }

            var7 = var7.replace("\\", "\\\\");
            var7 = var7.replace("\n", "\\n");
            var3.println(var6 + ":" + var7);
         }

         var3.println("[keys]");
         var5 = var1.bT.al.iterator();

         while(var5.hasNext()) {
            ad var10 = (ad)var5.next();
            if(var10.b && !var10.d()) {
               var7 = "";
               var7 = var7 + var10.e();
               String var8 = var1.bT.a(var10);
               var3.println(var7 + ":" + var8);
            }
         }

         var3.close();
         return true;
      } catch (IOException var9) {
         var9.printStackTrace();
         l.B().a("Failed to save preferences, IO error", 1);
         return false;
      }
   }

   public void loadFromFileSystem() {
      l var1 = l.B();
      String var2 = this.getPreferencesPath();
      File var3 = new File(var2);
      if(!var3.exists()) {
         l.e("preferences not found, not loading (" + var2 + ")");
      } else {
         l.e("loadFromFileSystem filepath: " + var3.getAbsolutePath());
         String var4 = "settings";

         try {
            com.corrodinggames.rts.gameFramework.utility.ab var5 = new com.corrodinggames.rts.gameFramework.utility.ab(var2);
            Iterator var6 = this.settingFields.entrySet().iterator();

            String var8;
            String var9;
            while(var6.hasNext()) {
               Entry var7 = (Entry)var6.next();
               var8 = (String)var7.getKey();
               var9 = var5.b(var4, var8, (String)null);
               if(l.aZ) {
                  l.e(var8 + "= " + var9);
               }

               if(var9 != null && !"".equals(var9)) {
                  if(var8.startsWith("key.")) {
                     var8 = var8.replace("key.", "");
                     l.e("loading keybind:" + var8);
                     var1.bT.a(var8, var9);
                  } else {
                     this.setValueDynamic(var8, var9);
                  }
               }
            }

            var6 = var1.bT.al.iterator();

            while(var6.hasNext()) {
               ad var11 = (ad)var6.next();
               if(var11.b && !var11.d()) {
                  var8 = var11.e();
                  var9 = var5.b("keys", var8, (String)null);
                  if(var9 != null && !"".equals(var9)) {
                     var1.bT.a(var8, var9);
                  }
               }
            }
         } catch (IOException var10) {
            var10.printStackTrace();
            l.B().a("Failed to load preferences, IO error", 1);
         }

      }
   }

   private SettingsEngine(Context var1) {
      Field[] var2 = this.getClass().getFields();
      Field[] var3 = var2;
      int var4 = var2.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Field var6 = var3[var5];
         String var7 = var6.getName();
         if(this.settingFields.get(var7) != null) {
            l.e("SettingsEngine: fields: " + var7 + " already exists");
         }

         if(l.aZ) {
            l.e("SettingsEngine: field:" + var7);
         }

         this.settingFields.put(var7, var6);
      }

      if(!l.aU) {
         this.prefs = var1.a("rts_settings", 0);
      }

      int var16 = this.getIntPref("settingVersion", 1);
      this.settingsGameVersion = this.getIntPref("settingsGameVersion", 0);
      this.settingsGameVersionFirst = this.getIntPref("settingsGameVersionFirst", 0);
      if(this.settingsGameVersionFirst == 0) {
         if(this.settingsGameVersion != 0) {
            this.settingsGameVersionFirst = this.settingsGameVersion;
         } else {
            this.settingsGameVersionFirst = 176;
         }
      }

      boolean var17 = false;
      if(this.settingsGameVersionFirst <= 160) {
         var17 = true;
      }

      this.upgradedToNoPublicStorage = this.getBooleanPref("upgradedToNoPublicStorage", var17);
      this.upgradedToNoPublicStorageWarningShown = this.getBooleanPref("upgradedToNoPublicStorageWarningShown", false);
      this.slick2dResolution = this.getStringPref("slick2dResolution", "native");
      this.slick2dFullScreenResolution = this.getStringPref("slick2dFullScreenResolution", "native");
      this.slick2dFullScreen = this.getBooleanPref("slick2dFullScreen", false);
      this.hasPlayedGameOrSeenHelp = this.getBooleanPref("hasPlayedGameOrSeenHelp", false);
      this.enableSounds = this.getBooleanPref("enableSounds", true);
      this.enableMouseCapture = this.getBooleanPref("enableMouseCapture", false);
      this.androidNoSoundPrioritiesDebug = this.getBooleanPref("androidNoSoundPrioritiesDebug", false);
      this.disableDigitGrouping = this.getBooleanPref("disableDigitGrouping", false);
      this.musicVolume = this.getFloatPref("musicVolume", 0.25F);
      float var18 = 1.0F;
      if(l.av()) {
         var18 = 0.5F;
      }

      this.masterVolume = this.getFloatPref("masterVolume", var18);
      this.gameVolume = this.getFloatPref("gameVolume", 1.0F);
      this.interfaceVolume = this.getFloatPref("interfaceVolume", 0.8F);
      this.scrollSpeed = this.getFloatPref("scrollSpeed", 1.0F);
      this.edgeScrollSpeed = this.getFloatPref("edgeScrollSpeed", 1.0F);
      this.onscreenControls = this.getBooleanPref("onscreenControls", true);
      this.trackpad = this.getBooleanPref("trackpad", true);
      this.dpad = this.getBooleanPref("dpad", true);
      this.batterySaving = this.getBooleanPref("batterySaving", false);
      boolean var19 = false;
      if(l.av()) {
         var19 = true;
      }

      this.highRefreshRate = this.getBooleanPref("highRefreshRate", var19);
      this.unlockedScreenRotation = this.getBooleanPref("unlockedScreenRotation", false);
      this.renderBackground = this.getBooleanPref("renderBackground", true);
      this.renderExtraLayers = this.getBooleanPref("renderExtraLayers", true);
      this.renderControls = this.getBooleanPref("renderControls", true);
      this.immersiveFullScreen = this.getBooleanPref("immersiveFullScreen", true);
      this.displayOverCutout = this.getBooleanPref("displayOverCutout", false);
      this.renderDoubleScale = this.getBooleanPref("renderDoubleScale", false);
      this.showUnitGroups = this.getBooleanPref("showUnitGroups", true);
      boolean var20 = false;
      if(l.av()) {
         var20 = true;
      }

      if(l.aZ) {
         var20 = true;
      }

      this.renderClouds = this.getBooleanPref("renderClouds", var20);
      this.renderWithLineWidth = this.getBooleanPref("renderWithLineWidth", true);
      boolean var8 = false;
      if(l.av()) {
         var8 = true;
      }

      if(l.aZ) {
         var8 = true;
      }

      this.softFogFading = this.getBooleanPref("softFogFading", var8);
      this.showUnitWaypoints = this.getBooleanPref("showUnitWaypoints", true);
      this.useMinimapAllyColors = this.getBooleanPref("useMinimapAllyColors", true);
      boolean var9 = false;
      if(l.av()) {
         var9 = true;
      }

      this.showWarLogOnScreen = this.getBooleanPref("showWarLogOnScreen", var9);
      boolean var10 = false;
      this.classicInterface = this.getBooleanPref("classicInterface", var10);
      boolean var11 = false;
      if(l.av()) {
         var11 = true;
      }

      this.quickRally = this.getBooleanPref("quickRally", var11);
      if(var16 <= 1 && !l.av()) {
         this.quickRally = var11;
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
      this.mouseSupport = this.getBooleanPref("mouseSupport", !l.d(var1));
      this.keyboardSupport = this.getBooleanPref("keyboardSupport", true);
      boolean var12 = false;
      if(l.aZ) {
         var12 = true;
      }

      this.forceEnglish = this.getBooleanPref("forceEnglish", var12);
      boolean var13 = false;
      if(l.av()) {
         var13 = true;
      }

      this.saveMultiplayerReplays = this.getBooleanPref("saveMultiplayerReplays", var13);
      if(var16 <= 1) {
         this.saveMultiplayerReplays = var13;
      }

      this.replaysShowRecordedChat = this.getBooleanPref("replaysShowRecordedChat", true);
      this.lastNetworkPlayerName = this.getStringPref("lastNetworkPlayerName", (String)null);
      this.lastNetworkIP = this.getStringPref("lastNetworkIP", (String)null);
      this.lastDebugOption = this.getStringPref("lastDebugOption", (String)null);
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
      if(this.networkPort < 1024 || this.networkPort > '\uffff') {
         this.networkPort = 5123;
      }

      this.udpInMultiplayer = this.getBooleanPref("udpInMultiplayer", false);
      this.banTimeInSecondsAfterKick = this.getIntPref("banTimeInSecondsAfterKick", 60);
      this.numIncompleteLoadAttempts = this.getIntPref("numIncompleteLoadAttempts", 0);
      this.numLoadsSinceRunningGameOrNormalExit = this.getIntPref("numLoadsSinceRunningGameOrNormalExit", 0);
      this.numberOfWins = this.getIntPref("numberOfWins", 0);
      this.rateGameShown = this.getBooleanPref("rateGameShown", false);
      this.uuid = this.getStringPref("uuid", (String)null);
      this.networkClientId = this.getStringPref("networkClientId", (String)null);
      this.networkServerId = this.getStringPref("networkServerId", (String)null);
      this.lastSeenMessageId = this.getIntPref("lastSeenMessageId", -1);
      this.lastSeenMessageIds = this.getStringPref("lastSeenMessageIds", "");
      this.nextBackgroundMap = this.getIntPref("nextBackgroundMap", 1);
      this.showChatAndPingShortcuts = this.getBooleanPref("showChatAndPingShortcuts", true);
      this.teamUnitCapSinglePlayer = this.getIntPref("teamUnitCapSinglePlayer", 1000);
      this.teamUnitCapHostedGame = this.getIntPref("teamUnitCapHostedGame", 250);
      this.modSettings = this.getStringPref("modSettings", "");
      this.modSettingsVersion = this.getIntPref("modSettingsVersion", 0);
      boolean var14 = false;
      if(l.at() && com.corrodinggames.rts.appFramework.c.b(com.corrodinggames.rts.appFramework.c.a())) {
         var14 = true;
      }

      byte var15 = 0;
      if(var14) {
         var15 = 2;
      }

      this.storageType = this.getIntPref("storageType", var15);
      this.hadStoragePermissionInPast = this.getBooleanPref("hadStoragePermissionInPast", false);
      if(var14) {
         this.hadStoragePermissionInPast = true;
      }

      this.hasSelectedAStorageType = this.getBooleanPref("hasSelectedAStorageType", false);
      this.loadDisabledModData = this.getBooleanPref("loadDisabledModData", false);
      this.lastModCount = this.getIntPref("lastModCount", -1);
      this.modSAFlinks = this.getStringPref("modSAFlinks", (String)null);
      this.externalSAFWorking = this.getBooleanPref("externalSAFWorking", false);
      this.externalSAFLink = this.getStringPref("externalSAFLink", (String)null);
      this.externalSAFPathShown = this.getStringPref("externalSAFPathShown", (String)null);
      this.externalSAFPathExtra = this.getStringPref("externalSAFPathExtra", (String)null);
      this.smartSelection_v2 = this.getBooleanPref("smartSelection_v2", true);
      this.mouseOrders = this.getIntPref("mouseOrders", 1);
      this.mousePlacement = this.getIntPref("mousePlacement", 1);
      this.autosaving = this.getBooleanPref("autosaving", true);
      if(l.aW) {
         this.loadFromFileSystem();
      }

      if(this.settingsGameVersion < 174) {
         this.uiRenderScale = 1.0F;
      }

   }

   public synchronized boolean save() {
      this.settingsGameVersion = 176;
      if(l.aU) {
         return l.aW?this.saveToFileSystem():true;
      } else {
         Editor var1 = this.prefs.edit();
         var1.putInt("settingVersion", 2);
         var1.putInt("settingsGameVersion", this.settingsGameVersion);
         var1.putInt("settingsGameVersionFirst", this.settingsGameVersionFirst);
         var1.putBoolean("upgradedToNoPublicStorage", this.upgradedToNoPublicStorage);
         var1.putBoolean("upgradedToNoPublicStorageWarningShown", this.upgradedToNoPublicStorageWarningShown);
         var1.putBoolean("hasPlayedGameOrSeenHelp", this.hasPlayedGameOrSeenHelp);
         var1.putString("slick2dResolution", this.slick2dResolution);
         var1.putString("slick2dFullScreenResolution", this.slick2dFullScreenResolution);
         var1.putBoolean("slick2dFullScreen", this.slick2dFullScreen);
         var1.putBoolean("enableSounds", this.enableSounds);
         var1.putBoolean("enableMouseCapture", this.enableMouseCapture);
         var1.putBoolean("androidNoSoundPrioritiesDebug", this.androidNoSoundPrioritiesDebug);
         var1.putBoolean("disableDigitGrouping", this.disableDigitGrouping);
         Log.d("RustedWarfare", "put mv:" + this.musicVolume);
         var1.putFloat("musicVolume", this.musicVolume);
         var1.putFloat("masterVolume", this.masterVolume);
         var1.putFloat("gameVolume", this.gameVolume);
         var1.putFloat("interfaceVolume", this.interfaceVolume);
         var1.putFloat("scrollSpeed", this.scrollSpeed);
         var1.putFloat("edgeScrollSpeed", this.edgeScrollSpeed);
         var1.putBoolean("onscreenControls", this.onscreenControls);
         var1.putBoolean("trackpad", this.trackpad);
         var1.putBoolean("dpad", this.dpad);
         var1.putBoolean("batterySaving", this.batterySaving);
         var1.putBoolean("highRefreshRate", this.highRefreshRate);
         var1.putBoolean("unlockedScreenRotation", this.unlockedScreenRotation);
         var1.putBoolean("renderBackground", this.renderBackground);
         var1.putBoolean("renderExtraLayers", this.renderExtraLayers);
         var1.putBoolean("renderControls", this.renderControls);
         var1.putBoolean("immersiveFullScreen", this.immersiveFullScreen);
         var1.putBoolean("displayOverCutout", this.displayOverCutout);
         var1.putBoolean("renderDoubleScale", this.renderDoubleScale);
         var1.putBoolean("showUnitGroups", this.showUnitGroups);
         var1.putBoolean("renderWithLineWidth", this.renderWithLineWidth);
         var1.putBoolean("renderClouds", this.renderClouds);
         var1.putBoolean("softFogFading", this.softFogFading);
         var1.putBoolean("showUnitWaypoints", this.showUnitWaypoints);
         var1.putBoolean("useMinimapAllyColors", this.useMinimapAllyColors);
         var1.putBoolean("showWarLogOnScreen", this.showWarLogOnScreen);
         var1.putBoolean("classicInterface", this.classicInterface);
         var1.putBoolean("quickRally", this.quickRally);
         var1.putBoolean("doubleClickToAttackMove", this.doubleClickToAttackMove);
         var1.putBoolean("showMapPingsOnBattlefield", this.showMapPingsOnBattlefield);
         var1.putBoolean("showMapPingsOnMinimap", this.showMapPingsOnMinimap);
         var1.putBoolean("showPlayerChatInGame", this.showPlayerChatInGame);
         var1.putBoolean("allowGameRecording", this.allowGameRecording);
         var1.putBoolean("showHp", this.showHp);
         var1.putBoolean("showHpChanges", this.showHpChanges);
         var1.putBoolean("showUnitIcons", this.showUnitIcons);
         var1.putBoolean("gestureZoom", this.gestureZoom);
         var1.putBoolean("useCircleSelect", this.useCircleSelect);
         var1.putBoolean("showZoomButton", this.showZoomButton);
         var1.putBoolean("showFps", this.showFps);
         var1.putBoolean("newRender", this.newRender);
         var1.putBoolean("shaderEffects", this.shaderEffects);
         var1.putBoolean("teamShaders", this.teamShaders);
         var1.putBoolean("sendReports", this.sendReports);
         var1.putBoolean("shownAudioWarning", this.shownAudioWarning);
         var1.putBoolean("mouseSupport", this.mouseSupport);
         var1.putBoolean("keyboardSupport", this.keyboardSupport);
         var1.putBoolean("forceEnglish", this.forceEnglish);
         var1.putBoolean("saveMultiplayerReplays", this.saveMultiplayerReplays);
         var1.putBoolean("replaysShowRecordedChat", this.replaysShowRecordedChat);
         var1.putString("lastNetworkPlayerName", this.lastNetworkPlayerName);
         var1.putString("lastNetworkIP", this.lastNetworkIP);
         var1.putString("lastDebugOption", this.lastDebugOption);
         var1.putInt("aiDifficulty", this.aiDifficulty);
         var1.putInt("locationDpad", this.locationDpad);
         var1.putInt("locationAction", this.locationAction);
         var1.putInt("keyAction", this.keyAction);
         var1.putInt("keyJump", this.keyJump);
         var1.putInt("keyLeft", this.keyLeft);
         var1.putInt("keyRight", this.keyRight);
         var1.putInt("keyDown", this.keyDown);
         var1.putBoolean("landscapeOrientation", this.landscapeOrientation);
         var1.putInt("networkPort", this.networkPort);
         var1.putBoolean("udpInMultiplayer", this.udpInMultiplayer);
         var1.putInt("banTimeInSecondsAfterKick", this.banTimeInSecondsAfterKick);
         var1.putInt("numIncompleteLoadAttempts", this.numIncompleteLoadAttempts);
         var1.putInt("numLoadsSinceRunningGameOrNormalExit", this.numLoadsSinceRunningGameOrNormalExit);
         var1.putInt("numberOfWins", this.numberOfWins);
         var1.putBoolean("rateGameShown", this.rateGameShown);
         var1.putString("uuid", this.uuid);
         var1.putString("networkClientId", this.networkClientId);
         var1.putString("networkServerId", this.networkServerId);
         var1.putInt("lastSeenMessageId", this.lastSeenMessageId);
         var1.putString("lastSeenMessageIds", this.lastSeenMessageIds);
         var1.putInt("nextBackgroundMap", this.nextBackgroundMap);
         var1.putBoolean("showChatAndPingShortcuts", this.showChatAndPingShortcuts);
         var1.putString("modSettings", this.modSettings);
         var1.putInt("modSettingsVersion", this.modSettingsVersion);
         var1.putInt("storageType", this.storageType);
         var1.putBoolean("hasSelectedAStorageType", this.hasSelectedAStorageType);
         var1.putBoolean("hadStoragePermissionInPast", this.hadStoragePermissionInPast);
         var1.putInt("teamUnitCapSinglePlayer", this.teamUnitCapSinglePlayer);
         var1.putInt("teamUnitCapHostedGame", this.teamUnitCapHostedGame);
         var1.putBoolean("loadDisabledModData", this.loadDisabledModData);
         var1.putInt("lastModCount", this.lastModCount);
         var1.putString("modSAFlinks", this.modSAFlinks);
         var1.putBoolean("externalSAFWorking", this.externalSAFWorking);
         var1.putString("externalSAFLink", this.externalSAFLink);
         var1.putString("externalSAFPathShown", this.externalSAFPathShown);
         var1.putString("externalSAFPathExtra", this.externalSAFPathExtra);
         var1.putBoolean("smartSelection_v2", this.smartSelection_v2);
         var1.putInt("mouseOrders", this.mouseOrders);
         var1.putInt("mousePlacement", this.mousePlacement);
         var1.putBoolean("autosaving", this.autosaving);
         var1.commit();
         return true;
      }
   }

   public boolean loadMainExternalFolder(boolean var1) {
      if(!l.at()) {
         return false;
      } else {
         l.e("loadMainExternalFolder..");
         l var2 = l.B();
         if(var1 && var2.c()) {
            l.e("Not loading due to extra safe mode");
            return false;
         } else {
            String var3 = var2.bQ.externalSAFLink;
            String var4 = var2.bQ.externalSAFPathShown;
            String var5 = var2.bQ.externalSAFPathExtra;
            if(var3 == null) {
               l.e("No external folder set");
               return false;
            } else {
               l.e("External saf link: " + var3);
               l.e("External saf shown path: " + var4);
               l.e("External saf extra: " + var5);

               try {
                  Uri var6 = Uri.parse(var3);
                  boolean var7 = false;
                  String var8 = null;
                  String var9 = com.corrodinggames.rts.gameFramework.utility.a.a.a(var6, true);
                  l.e("safVirualPathBase: " + var9);
                  if(var9 == null) {
                     l.e("createSAFLink failed for uri: " + var6);
                     var7 = true;
                  } else {
                     var8 = var9 + var5;
                     l.e("safVirualPath: " + var8);
                     if(!com.corrodinggames.rts.gameFramework.e.a.f(var8)) {
                        l.e("isDirectory failed for: " + var8);
                        var7 = true;
                     }
                  }

                  if(var7) {
                     l.e("error for uri: " + var6);
                     var2.bQ.externalSAFWorking = false;
                     String var10 = "Failed to read: " + var4 + " - Folder might have moved or permission expired. Please setup again under in-game settings.";
                     if(com.corrodinggames.rts.gameFramework.i.a.a == null) {
                        com.corrodinggames.rts.gameFramework.i.a.a = "";
                     }

                     com.corrodinggames.rts.gameFramework.i.a.a = com.corrodinggames.rts.gameFramework.i.a.a + var10;
                     com.corrodinggames.rts.gameFramework.i.a.b = var10;
                     return false;
                  } else {
                     l.e("Using external path");
                     var2.bQ.externalSAFWorking = true;
                     com.corrodinggames.rts.gameFramework.e.a.d = var8;
                     return true;
                  }
               } catch (Exception var11) {
                  var11.printStackTrace();
                  return false;
               }
            }
         }
      }
   }

}
