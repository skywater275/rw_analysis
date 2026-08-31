package com.corrodinggames.librocket.scripts;

import android.graphics.Color;
import com.Element;
import com.ElementDocument;
import com.corrodinggames.librocket.b;
import com.corrodinggames.librocket.e;
import com.corrodinggames.librocket.scripts.Mods;
import com.corrodinggames.librocket.scripts.Multiplayer;
import com.corrodinggames.librocket.scripts.Root$1;
import com.corrodinggames.librocket.scripts.Root$10;
import com.corrodinggames.librocket.scripts.Root$11;
import com.corrodinggames.librocket.scripts.Root$2;
import com.corrodinggames.librocket.scripts.Root$3;
import com.corrodinggames.librocket.scripts.Root$4;
import com.corrodinggames.librocket.scripts.Root$5;
import com.corrodinggames.librocket.scripts.Root$6;
import com.corrodinggames.librocket.scripts.Root$7;
import com.corrodinggames.librocket.scripts.Root$8;
import com.corrodinggames.librocket.scripts.Root$9;
import com.corrodinggames.librocket.scripts.Root$TableCell;
import com.corrodinggames.librocket.scripts.Root$TableData;
import com.corrodinggames.librocket.scripts.Root$TableRow;
import com.corrodinggames.librocket.scripts.ScriptContext;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.librocket.scripts.ScriptEngine$Action;
import com.corrodinggames.rts.a.a;
import com.corrodinggames.rts.appFramework.c;
import com.corrodinggames.rts.appFramework.i;
import com.corrodinggames.rts.appFramework.j;
import com.corrodinggames.rts.appFramework.p;
import com.corrodinggames.rts.appFramework.q;
import com.corrodinggames.rts.gameFramework.ad;
import com.corrodinggames.rts.gameFramework.bt;
import com.corrodinggames.rts.gameFramework.h;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.g.f;
import com.corrodinggames.rts.gameFramework.j.ah;
import com.corrodinggames.rts.gameFramework.j.ai;
import com.corrodinggames.rts.gameFramework.j.an;
import com.corrodinggames.rts.gameFramework.j.g;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Root extends ScriptContext {

   public static final boolean DEBUG_TIMING = true;
   public Multiplayer multiplayer;
   public Mods mods;
   bt openDocumentTimer = new bt("openDocument", true);
   an threadedGameConnector;
   ElementDocument lastConnectingPopup;
   static bt convertTextStopwatch = new bt("ConvertText", true);
   static bt loadSettingsStopwatch = new bt("LoadSettings", true);
   ArrayList lastSortedDiscoveredServers;


   public void logDebug(String var1) {
      l.e("ui[debug]: " + var1);
   }

   public void logWarn(String var1) {
      l.e("ui[warn]: " + var1);
   }

   public void back() {
      this.libRocket.backToLastDocument();
      if(this.libRocket.getActiveDocument() == null) {
         l.b("back: libRocket.getActiveDocument()==null");
         l var1 = l.B();
         if(var1 != null && var1.bq) {
            l.b("back: resuming game");
            this.guiEngine.a(false);
         } else {
            l.b("back: showing main menu!");
            this.showMainMenu();
         }
      }

   }

   public void backOrClose() {
      if(!this.closePopup()) {
         this.libRocket.backToLastDocument();
      }
   }

   public String fullVersionOnlyStyle() {
      return l.B().ar?"notInDemo":"";
   }

   public void openIfNotDemo(String var1, Object var2, String var3) {
      if(l.B().ar) {
         this.alert(var3);
      } else {
         this.open(var1, var2);
      }
   }

   public String getVersionName() {
      l var1 = l.B();
      return var1.t();
   }

   public void delayedOpenNoHistory(String var1, Object var2) {
      ScriptEngine$Action var3 = this.scriptEngine.addRunnableToQueue(new Root$1(this, var1));
      var3.framesDelay = 1;
   }

   public void openAfterHelpPopup(String var1) {
      l var2 = l.B();
      if(l.au() && !var2.bQ.hasPlayedGameOrSeenHelp) {
         var2.bQ.hasPlayedGameOrSeenHelp = true;
         var2.bQ.save();
         String var3 = "";
         String var4 = "First time playing? Would you like to view the quick help slides?";
         String var5 = "[onenter]View Help:";
         var5 = var5 + "closePopup(); open(\'help_quick_mobile.rml\');";
         String var6 = "Skip:";
         var6 = var6 + "closePopup(); open(" + this.restrictedString(var1) + ");";
         boolean var7 = false;
         this.showPopup(var3, var4, var7, var5, var6);
      } else {
         this.open(var1, (Object)null);
      }
   }

   public void open(String var1, Object var2) {
      this.openDocumentTimer.a();
      b.a.f();
      HashMap var3 = null;
      if(var2 != null) {
         var3 = new HashMap();
         var3.put("mode", var2);
      }

      this.libRocket.setDocument(var1, var3);
      this.onShowNewScreen();
      this.openDocumentTimer.d();
      b.a.e();
   }

   public void onShowNewScreen() {
      this.guiEngine.a(true);
   }

   public void resume() {
      this.libRocket.closeActiveDocument();
      this.libRocket.clearHistory();
      this.guiEngine.f();
   }

   public void resumeNonMenu() {
      this.libRocket.closeActiveDocument();
      this.libRocket.clearHistory();
      this.guiEngine.a(false);
   }

   public void startNew(String var1) {
      this.guiEngine.a(var1);
      this.libRocket.closeActiveDocument();
      this.libRocket.clearHistory();
   }

   public void showRangeValue(String var1, String var2, boolean var3) {
      Element var4 = this.libRocket.getActiveElementById(var1);
      if(var4 == null) {
         this.logWarn("Could not find:" + var1);
      } else {
         String var5 = var4.getAttribute("value");
         if(var3) {
            var5 = (new Float(var5)).toString();
         } else {
            var5 = Integer.toString((int)Float.parseFloat(var5));
         }

         Element var6 = this.libRocket.getActiveElementById(var2);
         if(var6 == null) {
            this.logWarn("Could not find:" + var2);
         } else {
            var6.setText(var5);
         }
      }
   }

   public String getValueById(String var1) {
      Element var2 = this.libRocket.getActiveElementById(var1);
      if(var2 == null) {
         this.logWarn("Could not find:" + var1);
         return null;
      } else {
         String var3 = var2.getAttribute("value");
         return var3;
      }
   }

   public void setValueById(String var1, String var2) {
      Element var3 = this.libRocket.getActiveElementById(var1);
      if(var3 == null) {
         this.logWarn("Could not find:" + var1);
      } else {
         var3.setAttribute("value", var2);
      }
   }

   public Element getElementById(String var1) {
      Element var2 = this.libRocket.getActiveElementById(var1);
      if(var2 == null) {
         this.logWarn("Could not find:" + var1);
         return null;
      } else {
         return var2;
      }
   }

   public boolean clickElement(Element var1) {
      if(var1 == null) {
         this.logWarn("element is null");
         return false;
      } else {
         var1.click();
         return true;
      }
   }

   public void directJoinPopup() {
      l var1 = l.B();
      String var2 = "Direct Join";
      String var3 = "Enter IP address or host name";
      String var4 = "";
      if(var1.bQ.lastNetworkIP != null) {
         var4 = var1.bQ.lastNetworkIP;
      }

      this.showInputPopup(var2, var3, var4, "[onenter]Join:joinServerFromPopup(getPopupText())", (String)null);
   }

   public void joinServerFromPopup(String var1) {
      this.closePopup();
      this.hideKeyboard();
      if(var1 == null) {
         this.logDebug("joinAddress==null");
      } else {
         var1 = var1.trim();
         l var2 = l.B();
         var2.bQ.lastNetworkIP = var1;
         var2.bQ.save();
         this.joinServerWithId(var1, (String)null);
      }
   }

   public void joinServerWithId(String var1, String var2) {
      l var3 = l.B();
      var3.bX.bw = var2;
      this.joinServer(var1);
   }

   public void joinServer(String var1) {
      if(!ScriptEngine.inDebugScript || a.d) {
         this.logDebug("joinAddress=" + var1);
         Root$2 var2 = new Root$2(this);
         l var3 = l.B();
         this.threadedGameConnector = var3.bX.a(var1, false, var2);
         this.lastConnectingPopup = this.createAndShowPopup("multiplayerLobby_connecting.rml", (Object)null, "Please wait");
      }
   }

   public void joinServerCallback() {
      this.logDebug("joinServerCallback");
      l var1 = l.B();
      if(this.threadedGameConnector == null) {
         this.logDebug("threadedGameConnector==null");
      } else {
         this.closePopupOnly();
         String var3;
         if(this.threadedGameConnector.e != null) {
            if(this.threadedGameConnector.e.equals("CANCELLED")) {
               this.logDebug("Server join cancelled");
               return;
            }

            this.logWarn("Server join failed");
            boolean var2 = true;
            var3 = "Connection failed";
            this.showPopup(var3, this.threadedGameConnector.e, var2, (String)null, (String)null);
         } else {
            try {
               var1.bX.b("starting new");
               var1.bX.a(this.threadedGameConnector.g);
               this.logDebug("connected");
               this.showBattleroom();
            } catch (IOException var4) {
               var3 = var4.getMessage();
               var1.c(var3, "Connection failed");
               var4.printStackTrace();
            }
         }

         this.threadedGameConnector = null;
      }
   }

   public void cancelJoinServer() {
      if(this.threadedGameConnector != null) {
         if(this.threadedGameConnector.a()) {
            this.closePopup();
         }
      } else {
         this.closePopup();
      }

   }

   public void alert(String var1) {
      this.showAlert(var1);
   }

   public void showAlert(String var1) {
      this.logDebug("alert:" + var1);
      if(var1 == null) {
         var1 = "null";
      }

      this.libRocket.c(var1);
   }

   public void showPopupWithButtons(String var1, String var2, boolean var3, e var4, e var5) {
      this.logDebug("showPopup:" + var2);
      if(var2 == null) {
         var2 = "null";
      }

      Object var6 = null;
      this.libRocket.a(var1, var2, (String)var6, var4, var5, var3);
   }

   public void showPopupWithButtonsAndInput(String var1, String var2, boolean var3, String var4, e var5, e var6) {
      this.logDebug("showPopup:" + var2);
      if(var2 == null) {
         var2 = "null";
      }

      if(var4 == null) {
         var4 = "";
      }

      if("".equals(var4)) {
         this.guiEngine.l();
      }

      this.libRocket.a(var1, var2, var4, var5, var6, true, var3);
   }

   public void showPopup(String var1, String var2, boolean var3, String var4, String var5) {
      this.logDebug("showPopup:" + var2);
      if(var2 == null) {
         var2 = "null";
      }

      Object var6 = null;
      this.libRocket.a(var1, var2, (String)var6, var4, var5, var3);
   }

   public void showInputPopup(String var1, String var2, String var3, String var4, String var5) {
      this.logDebug("showInputPopup:" + var2);
      if(var2 == null) {
         var2 = "null";
      }

      String var6 = var3;
      if(var3 == null) {
         var6 = "";
      }

      if("".equals(var6)) {
         this.guiEngine.l();
      }

      this.libRocket.a(var1, var2, var6, var4, var5, true);
   }

   public void showInputPopupNonClose(String var1, String var2, String var3, String var4, String var5) {
      this.logDebug("showInputPopup:" + var2);
      if(var2 == null) {
         var2 = "null";
      }

      String var6 = var3;
      if(var3 == null) {
         var6 = "";
      }

      if("".equals(var6)) {
         this.guiEngine.l();
      }

      this.libRocket.a(var1, var2, var6, var4, var5, true, false);
   }

   public ElementDocument getPopup() {
      return this.libRocket.c();
   }

   public ElementDocument getAlertOrPopup() {
      return this.libRocket.e();
   }

   public boolean closePopup() {
      return this.libRocket.h();
   }

   public boolean closePopupOnly() {
      return this.libRocket.j();
   }

   public boolean closeAlertOnly() {
      return this.libRocket.i();
   }

   public String getPopupText() {
      return this.libRocket.k();
   }

   public void showHostOptions() {
      this.libRocket.a("Host game", this.i("menus.hostgame.info_pc"), (String)null, "Host Private:closePopup();hostStart(false);", "Host Public:closePopup();hostStart(true);", true);
   }

   public void hostStartFromPopup(boolean var1) {
      ElementDocument var2 = this.libRocket.g();
      Element var3 = var2.getElementById("password");
      String var4 = null;
      if(var3 == null) {
         this.logWarn("hostStartFromPopup: password==null");
      } else {
         String var5 = var3.getValue();
         if(var5 != null && !var5.trim().equals("")) {
            var4 = var5;
         }
      }

      Element var7 = var2.getElementById("useMods");
      boolean var6 = var7.getCheckbox();
      this.closePopup();
      this.hostStartWithPasswordAndMods(var1, var4, var6);
   }

   public void hostStart(boolean var1) {
      l.b("old version of hostStart");
      this.hostStartWithPassword(var1, (String)null);
   }

   public void hostStartWithPassword(boolean var1, String var2) {
      l.b("old version of hostStartWithPassword");
      this.hostStartWithPasswordAndMods(var1, var2, true);
   }

   public void hostStartWithPasswordAndMods(boolean var1, String var2, boolean var3) {
      l var4 = l.B();
      var4.bX.b("starting new");
      var4.bX.n = var2;
      var4.bX.o = var3;
      var4.bX.q = var1;
      if(var4.bX.b(false)) {
         this.logDebug("-Hosting-");
         this.logDebug("using password: " + (var4.bX.n != null));
         this.logDebug("using mods: " + var4.bX.o);
         this.logDebug("public: " + var4.bX.q);
         String var5 = var4.bX.av();
         if(var5 != null && !com.corrodinggames.rts.gameFramework.e.a.i(var5)) {
            l.b("hostStart: map does not exist: " + var5 + " reseting");
            var5 = null;
         }

         if(var5 == null) {
            byte var6 = 0;
            var4.bX.ay.a = ai.values()[var6];
            var4.bX.az = "maps/skirmish/[p8]Many Islands (8p).tmx";
            var4.bX.ay.b = "[p8]Many Islands (8p).tmx";
         }

         this.libRocket.setDocument("battleroom.rml", (HashMap)null);
      } else {
         this.logWarn("hosting failed");
      }

   }

   public void exit() {
      l var1 = l.B();
      if(var1.bQ.numLoadsSinceRunningGameOrNormalExit != 0) {
         var1.bQ.numLoadsSinceRunningGameOrNormalExit = 0;
         var1.bQ.save();
      }

      this.scriptEngine.addRunnableToQueue(new Root$3(this));
   }

   public String getMapDetails(String var1) {
      return "hello 123";
   }

   public String getHTMLMapNameFromPath(String var1) {
      return this.htmlString(this.getMapNameFromPath(var1));
   }

   public String getMapNameFromPath(String var1) {
      File var2 = new File(var1);
      return this.convertMapName(var2.getName());
   }

   public String convertMapName(String var1) {
      String var2 = this.convertMapNameWithoutTranslation(var1);
      var2 = com.corrodinggames.rts.gameFramework.h.a.b(var2);
      return var2;
   }

   public String convertMapNameWithoutTranslation(String var1) {
      String var2 = i.e(var1);
      return var2;
   }

   public String getHTMLMapThumbnail(String var1) {
      return this.escapedString(this.getMapThumbnail(var1));
   }

   public String getMapThumbnail(String var1) {
      String var2 = null;
      if(var1.startsWith("saves/")) {
         var2 = "drawable:icon_save.png";
      }

      String var3 = c.c(var1);
      var2 = "thumbnail:assets:" + var3;
      if(!com.corrodinggames.rts.gameFramework.e.a.i(var3)) {
         if(l.aZ) {
            l.a("getMapThumbnail: Failed to find: " + var3);
         }

         return "drawable:error_missingmap.png";
      } else {
         return var2;
      }
   }

   public boolean isMapSkirmish(String var1) {
      return i.f(var1);
   }

   public void showLevelOptions() {
      l var1 = l.B();
      String var2 = (String)this.libRocket.b("mode");
      if(var2 == null) {
         l.g("levelPath==null");
      } else {
         boolean var3 = true;
         if(!this.isMapSkirmish(var2)) {
            var3 = false;
         }

         ElementDocument var4 = this.libRocket.getActiveDocument();
         Iterator var5 = var4.findElementsByClassName("skirmishOnly").iterator();

         Element var6;
         while(var5.hasNext()) {
            var6 = (Element)var5.next();
            var6.show(var3);
         }

         Element var7 = var4.getElementById("advancedButton");
         if(var7 != null) {
            var7.show(var3 || i.g(var2));
         }

         var6 = var4.getElementById("aiDifficulty");
         var6.setValue("" + var1.bQ.aiDifficulty);
      }
   }

   public void loadConfigAndStartSwitchToAdvanced(String var1) {
      boolean var2 = true;
      l var3 = l.B();
      var3.bv = false;
      this.loadConfigCommon(var1, var2);
      this._startAdvancedMode(false);
   }

   private void _startAdvancedMode(boolean var1) {
      l var2 = l.B();
      var2.bX.b("starting singleplayer");
      var2.bX.y = "You";
      var2.bX.o = true;
      boolean var3;
      if(var1) {
         var3 = var2.bX.R();
      } else {
         var3 = var2.bX.S();
      }

      if(var3) {
         this.logDebug("started startSinglePlayerServer");
         ah var4 = var2.bX.e();
         if(var4 != null) {
            var4.f = var2.bQ.aiDifficulty;
            var2.bX.a(var4);
         }

         this.libRocket.setDocument("battleroom.rml", (HashMap)null);
      } else {
         this.logWarn("failed startSinglePlayerServer");
      }

   }

   public void loadConfigAndStartNewSandbox(String var1) {
      this._loadConfigAndStartNewSandboxCommon(var1, true);
   }

   public void loadConfigAndStartNewSandboxInAdvanced(String var1) {
      l.e("loadConfigAndStartNewSandboxInAdvanced");
      this._loadConfigAndStartNewSandboxCommon(var1, false);
      this._startAdvancedMode(true);
      l var2 = l.B();
      l.e("editorMode:" + var2.bv);
   }

   private void _loadConfigAndStartNewSandboxCommon(String var1, boolean var2) {
      boolean var3 = false;
      if(var1.startsWith("saves/")) {
         l.e("Starting sandbox from save: " + var1);
         this.loadGame(var1.substring("saves/".length()));
      } else {
         l.e("Starting sandbox from map: " + var1);
         this.loadConfigCommon(var1, var3);
      }

      l var4 = l.B();
      var4.bL.E = false;
      var4.bS.y();
      var4.bv = true;
      if(var2) {
         this.guiEngine.f();
      }

      this.libRocket.closeActiveDocument();
      this.libRocket.clearHistory();
   }

   public void loadConfigAndStartNew(String var1) {
      l var2 = l.B();
      var2.bv = false;
      var2.bX.b("starting singleplayer");
      boolean var3 = false;
      this.loadConfigCommon(var1, var3);
      this.guiEngine.f();
      this.libRocket.closeActiveDocument();
      this.libRocket.clearHistory();
   }

   public void loadConfigCommon(String var1, boolean var2) {
      l var3 = l.B();
      ElementDocument var4 = this.libRocket.getActiveDocument();
      Element var5 = var4.getElementById("aiDifficulty");
      var3.bQ.aiDifficulty = var5.getValueAsInt(Integer.valueOf(0)).intValue();
      var3.bQ.save();
      this.guiEngine.b(true);
      this.guiEngine.c(false);
      boolean var7 = this.isMapSkirmish(var1);
      int var8 = var4.getElementById("numberOfAIs").getValueAsInt(Integer.valueOf(4)).intValue();
      boolean var9 = true;
      int var10 = var4.getElementById("aiTeams").getValueAsInt(Integer.valueOf(1)).intValue();
      int var11 = var10 - 1;
      if(var10 == 5) {
         var11 = 0;
         var9 = false;
      }

      i.a(var1, var7, var8, var11, var9, var2);
   }

   public void showMapPopup(String var1, String var2) {
      boolean var3 = false;
      ElementDocument var4 = this.libRocket.a("levelSelect.rml", var1, "Map Select", var3);
      if(var4 != null) {
         var4.setMetadata("mapClickFunction", var2);
         Iterator var5 = var4.findElementsByClassName("noStyleInPopup").iterator();

         while(var5.hasNext()) {
            Element var6 = (Element)var5.next();
            var6.setAttribute("class", "");
         }

         if(this.showMapsWithDoc(var4)) {
            l.e("showMapsWithDoc passed");
            this.libRocket.h();
            this.libRocket.a(var4);
         }
      }

   }

   public void refreshAfterFileImport() {
      l.e("refreshAfterFileImport");
      l var1 = l.B();
      ArrayList var2 = var1.bZ.k();
      this.libRocket.reloadDocument();
   }

   public boolean showMaps() {
      ElementDocument var1 = this.libRocket.f();
      return this.showMapsWithDoc(var1);
   }

   public boolean showMapsWithDoc(ElementDocument var1) {
      l var2 = l.B();
      l.e("showMaps");
      if(var1 == null) {
         l.e("showMaps: elementDocument==null");
         return false;
      } else {
         Element var3 = var1.getElementById("mapTemplateHolder");
         Element var4 = var1.getElementById("mapHolder");
         String var5 = var3.getInnerRML();
         String var6 = "";
         String var7 = (String)var1.getMetadata("mode");
         String var8 = (String)var1.getMetadata("mapClickFunction");
         boolean var9 = var7.equals("saves");
         boolean var10 = var7.equals("replays");
         boolean var11 = var7.startsWith("/SD/");
         String[] var12;
         if(var9) {
            var12 = j.l();
            if(var12 == null) {
               var2.a("No saves", 1);
               return false;
            }
         } else if(var10) {
            var12 = q.l();
            if(!var2.bQ.saveMultiplayerReplays) {
               this.alert("Note: Multiplayer replay recordings are not turned on. You can enable them in the settings.");
            }

            if(var12 == null) {
               if(var2.bQ.saveMultiplayerReplays) {
                  var2.a("No replays yet", 1);
               }

               return false;
            }
         } else {
            var12 = com.corrodinggames.rts.gameFramework.e.a.a(var7, true);
            var12 = var2.bZ.a(var12, var7);
            if(var12 == null) {
               var2.a("Could not find folder: " + com.corrodinggames.rts.gameFramework.e.a.e(var7), 1);
               return false;
            }
         }

         String var13 = var7 + "/";
         int var14 = 0;
         String[] var15 = var12;
         int var16 = var12.length;

         for(int var17 = 0; var17 < var16; ++var17) {
            String var18 = var15[var17];
            String var20 = this.convertMapName(var18);
            boolean var21 = i.a(var18, var13 + var18);
            String var22 = var20 + "";
            if(var2.ar && !var21) {
               var22 = "[LOCKED] " + var22;
            }

            String var19 = var5.replace("_NAME_", this.htmlString(var22));
            String var23;
            String var24;
            if(var9) {
               var23 = "loadGame(" + this.escapedString(var18) + ")";
               var24 = "loadGameEdit(" + this.escapedString(var18) + ")";
            } else if(var10) {
               var23 = "loadReplay(" + this.escapedString(var18) + ")";
               var24 = "loadReplayEdit(" + this.escapedString(var18) + ")";
            } else {
               var23 = "open(\'levelOptions.rml\', " + this.escapedString(var13 + var18) + ")";
               var24 = "";
            }

            if(var8 != null) {
               var23 = var8 + "(" + this.escapedString(var13 + var18) + ")";
               var24 = "";
            }

            var19 = var19.replace("_ONCLICK_", var23);
            var19 = var19.replace("_ONCLICKEDIT_", var24);
            String var25 = "thumbnail:assets:";
            byte var26 = 18;
            if(var11) {
               var26 = 2;
            }

            if(var14 > var26) {
               var25 = "lazy:" + var25;
            }

            String var27 = c.c(var13 + var18);
            if(l.aZ) {
               ;
            }

            String var29 = var25 + var27;
            if(!com.corrodinggames.rts.gameFramework.e.a.i(var27)) {
               if(l.aZ) {
                  l.a("List: Failed to find: " + var27 + " after converting:" + var27 + " ( " + var29 + " )");
               }

               var29 = "drawable:error_missingmap.png";
            }

            if(var9 || var10) {
               var29 = "";
            }

            var19 = var19.replace("_IMG_", this.htmlString(var29));
            ++var14;
            var6 = var6 + var19;
         }

         var4.setInnerRML(var6);
         var4.loadCharsetIfNeeded(var6);
         if(!var9 && !var10) {
            var4.addClass("notSavesOnly");
         } else {
            var4.addClass("savesOnly");
         }

         if(var11 && var8 == null && l.au()) {
            var1.addClass("showImportButton");
         }

         return true;
      }
   }

   public void convertTextOnPage() {
      l var1 = l.B();
      this.logDebug("convertTextOnPage");
      ElementDocument var2 = this.libRocket.getActiveDocument();
      if(this.isMobile()) {
         var2.addClass("mobile");
      }

      if(this.libRocket.getHeight() < 800) {
         var2.addClass("smallScreen");
      }

      convertTextStopwatch.a();
      ArrayList var3 = var2.getAllNestedChildren();
      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
         Element var5 = (Element)var4.next();
         int var6 = var5.getNumAttributes();

         for(int var7 = 0; var7 < var6; ++var7) {
            String var8 = var5.getAttributeKey(var7);
            String var9 = var5.getAttributeValue(var7);
            if(var8 != null) {
               Element var13;
               if(var8.equals("nestedclone") && !var9.equalsIgnoreCase("false")) {
                  l.e("nested clone:" + var5.getId());
                  var5.setAttribute(var8, "false");
                  var13 = var5.clone();
                  var5.prependChild(var13);
                  var13.removeReference();
               } else if(var8.equals("childclone") && !var9.equalsIgnoreCase("false")) {
                  var5.setAttribute(var8, "false");
                  if(var5.getNumChildren() < 1) {
                     l.e("child clone failed no children:" + var5.getId());
                  }

                  var13 = var5.getChild(0).clone();
                  var13.addClass("clone");
                  var5.prependChild(var13);
                  var13.removeReference();
               } else {
                  String var10 = this.libRocket.d(var9);
                  if(var10 != null) {
                     l.e("convertTextOnPage:" + var8 + ": \'" + var9 + "\' to \'" + var10 + "\'");
                     if(var8.equals("_html")) {
                        l.e("setting html:" + var8);
                        var5.setInnerRML(var10);
                     } else {
                        if(var8.startsWith("_")) {
                           var8 = var8.substring("_".length());
                           l.e("converted key to:" + var8);
                        }

                        var5.setAttribute(var8, var10);
                     }
                  }
               }
            }
         }

         if(var2.translatedToUnicode) {
            String var11 = var5.getTagName();
            if(var11.equals("p") || var11.startsWith("h") || var11.startsWith("label") || var11.startsWith("button") || var11.startsWith("select")) {
               boolean var12 = var5.loadCharsetIfNeededWithCurrentText();
            }
         }
      }

      convertTextStopwatch.d();
   }

   public void keyBindingPopup_apply(boolean var1) {
      l var2 = l.B();
      ElementDocument var3 = this.libRocket.c();
      if(var3 == null) {
         this.logWarn("showKeyBindingActionPopup: popup==null");
      } else {
         String var4 = (String)var3.getMetadata("mode");
         String[] var5 = var4.split(":");
         int var6 = Integer.parseInt(var5[0]);
         int var7 = Integer.parseInt(var5[1]);
         ArrayList var8 = var2.bT.al;
         ad var9 = (ad)var8.get(var6);
         if(!var1) {
            Object var10 = var3.getMetadata("lastKeyPressed");
            if(var10 == null) {
               this.logWarn("keyBindingPopup_apply: no last key entered");
               return;
            }

            int var11 = ((Integer)var10).intValue();
            int var12 = 0;
            Object var13 = var3.getMetadata("lastKeyModifiersPressed");
            if(var13 != null) {
               var12 = ((Integer)var13).intValue();
            }

            var9.a(var11, var7, var12, true);
         } else {
            byte var14 = 0;
            var9.a(0, var7, var14, true);
         }

         this.showKeyBinding();
         this.closePopup();
      }
   }

   public void keyBindingPopup_onKeydown(int var1) {
      l var2 = l.B();
      ElementDocument var3 = this.libRocket.c();
      if(var3 == null) {
         this.logWarn("showKeyBindingActionPopup: popup==null");
      } else {
         Element var4 = var3.getElementById("keyBindMessage");
         if(var4 == null) {
            this.logWarn("showKeyBindingActionPopup: keyBindMessage==null");
         } else {
            String var5 = "";
            int var6 = this.guiEngine.i();
            var5 = var5 + l.j(var6);
            if(var1 == 111) {
               ;
            }

            if(var1 == 0) {
               this.logWarn("keyBindingPopup_onKeydown: skipping keycode 0");
            } else if(!var2.i(var1)) {
               var3.setMetadata("lastKeyPressed", Integer.valueOf(var1));
               var3.setMetadata("lastKeyModifiersPressed", Integer.valueOf(var6));
               var5 + SlickToAndroidKeycodes.a(var1);
               this.keyBindingPopup_apply(false);
            } else {
               String var7 = "Key: " + var5;
               var4.setText(var7);
            }
         }
      }
   }

   public void showKeyBindingPopup() {
      l var1 = l.B();
      ElementDocument var2 = this.libRocket.c();
      if(var2 == null) {
         this.logWarn("showKeyBindingActionPopup: popup==null");
      } else {
         Element var3 = var2.getElementById("keyBindMessage");
         if(var3 == null) {
            this.logWarn("showKeyBindingActionPopup: keyBindMessage==null");
         } else {
            String var4 = (String)var2.getMetadata("mode");
            String[] var5 = var4.split(":");
            int var6 = Integer.parseInt(var5[0]);
            int var7 = Integer.parseInt(var5[1]);
            ArrayList var8 = var1.bT.al;
            ad var9 = (ad)var8.get(var6);
            String var10 = "Press a key..";
            var3.setText(var10);
         }
      }
   }

   public String getKeyBindingAction(int var1, ad var2, int var3) {
      String var4 = var1 + ":" + var3;
      return "createAndShowPopup(\'settingsKeyBindingSet.rml\', " + this.escapedString(var4) + ", " + this.escapedString(var2.a) + "); showKeyBindingPopup();";
   }

   public void backWarnIfOverlappingKeyBinding() {
      ElementDocument var1 = this.libRocket.getActiveDocument();
      boolean var2 = ((Boolean)var1.getMetadata("hasOverlappingKeys", Boolean.valueOf(false))).booleanValue();
      if(var2) {
         String var3 = "One or more keys are overlapping and have been highlighted in red. These can cause problems.";
         e var4 = new e("Ignore", new Root$4(this));
         e var5 = new e("Fix", new Root$5(this));
         boolean var6 = false;
         this.showPopupWithButtons((String)null, var3, var6, var4, var5);
      } else {
         this.back();
      }
   }

   public void showKeyBinding() {
      l var1 = l.B();
      ElementDocument var2 = this.libRocket.getActiveDocument();
      var2.setMetadata("event_onkeydown", "keyBindingPopup_onKeydown");
      Root$TableData var3 = new Root$TableData();
      ArrayList var4 = var3.rows;
      ArrayList var5 = var1.bT.al;
      boolean var6 = false;

      for(int var7 = 0; var7 < var5.size(); ++var7) {
         ad var8 = (ad)var5.get(var7);
         if(var8.b) {
            Root$TableRow var9 = new Root$TableRow();
            var9.addCell(var8.a);
            if(var8.d()) {
               var9.addClass("rowHeader");
            } else {
               for(int var10 = 0; var10 <= 1; ++var10) {
                  boolean var11 = var1.bT.a(var8, var10);
                  Root$TableCell var12 = var9.addCell(var8.b(var10));
                  var12.setLibrocketOnClick(this.getKeyBindingAction(var7, var8, var10));
                  if(var11) {
                     var12.color = Integer.valueOf(-65536);
                     var6 = true;
                  }
               }
            }

            var4.add(var9);
         }
      }

      var2.setMetadata("hasOverlappingKeys", Boolean.valueOf(var6));
      this.refreshTable("keysDiv", var3);
   }

   public void loadSettings() {
      l var1 = l.B();
      loadSettingsStopwatch.a();
      this.logDebug("loadSettings");
      Element var2 = this.libRocket.getActiveElementById("body");
      ArrayList var3 = var2.getAllNestedChildren();
      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
         Element var5 = (Element)var4.next();
         String var6 = var5.getAttribute("data-settings");
         if(var6 != null) {
            String var7 = var5.getId();
            String var8 = var5.getAttribute("type", "unknown");
            String var9 = var1.bQ.getValueDynamic(var7);
            if(var8.equals("checkbox")) {
               if(Boolean.parseBoolean(var9)) {
                  var5.setAttribute("checked", "");
               } else {
                  var5.setAttribute("checked", (String)null);
               }
            } else {
               var5.setAttribute("value", var9);
            }
         }
      }

      loadSettingsStopwatch.d();
   }

   public void loadLeaderboard() {
      l var1 = l.B();
      this.logDebug("loadLeaderboard");
      Element var2 = this.libRocket.getActiveElementById("leaderboardType");
      Element var3 = this.libRocket.getActiveElementById("leaderboardGrouping");
      if(var2 != null && var3 != null) {
         var2.setAttribute("value", var1.cg.e().name());
         var3.setAttribute("value", var1.cg.f().name());
      } else {
         l.a("loadLeaderboard: Failed to find elements. (For page: " + this.libRocket.getActiveDocumentPath() + ")");
      }
   }

   public void saveLeaderboard() {
      l var1 = l.B();
      f var2 = f.a;
      com.corrodinggames.rts.gameFramework.g.c var3 = com.corrodinggames.rts.gameFramework.g.c.a;
      this.logDebug("saveLeaderboard");
      Element var4 = this.libRocket.getActiveElementById("leaderboardType");
      Element var5 = this.libRocket.getActiveElementById("leaderboardGrouping");
      if(var4 != null && var5 != null) {
         var2 = f.valueOf(var4.getAttribute("value"));
         var3 = com.corrodinggames.rts.gameFramework.g.c.valueOf(var5.getAttribute("value"));
         var1.a(var2, var3);
      } else {
         l.a("saveLeaderboard: Failed to find elements. (For page: " + this.libRocket.getActiveDocumentPath() + ")");
      }
   }

   public void applyResolution() {
      this.guiEngine.g();
   }

   public void updateRenderScaleInSettings(boolean var1) {
      l var2 = l.B();
      Element var3 = this.libRocket.getActiveElementById("uiRenderScale");
      Element var4 = this.libRocket.getActiveElementById("renderDensity");
      Float var5 = null;
      Float var6 = null;
      Element var7;
      if(var3 == null) {
         this.logDebug("updateRenderScaleInSettings: uiRenderScale==null");
      } else {
         var5 = var3.getValueAsFloat(Float.valueOf(1.0F));
         var7 = this.libRocket.getActiveElementById("uiRenderScaleDisplay");
         var7.compareAndSetText("x" + com.corrodinggames.rts.gameFramework.f.b((double)(var5.floatValue() + 0.01F), 1));
      }

      if(var4 == null) {
         this.logDebug("updateRenderScaleInSettings: renderDensity==null");
      } else {
         var6 = var4.getValueAsFloat(Float.valueOf(1.0F));
         var7 = this.libRocket.getActiveElementById("renderDensityDisplay");
         var7.compareAndSetText("x" + com.corrodinggames.rts.gameFramework.f.b((double)(var6.floatValue() + 0.01F), 1));
      }

      if(var1) {
         float var12 = var2.bQ.uiRenderScale;
         if(var5 != null) {
            var2.bQ.uiRenderScale = var5.floatValue();
         }

         float var8 = var2.bQ.renderDensity;
         if(var6 != null) {
            var2.bQ.renderDensity = var6.floatValue();
         }

         try {
            this.applyResolution();
         } finally {
            var2.bQ.uiRenderScale = var12;
            var2.bQ.renderDensity = var8;
         }
      }

   }

   public void closeSettings() {
      this.applyResolution();
   }

   public void saveSettings() {
      l var1 = l.B();
      this.logDebug("saveSettings");
      Element var2 = this.libRocket.getActiveElementById("body");
      ArrayList var3 = var2.getAllNestedChildren();
      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
         Element var5 = (Element)var4.next();
         String var6 = var5.getAttribute("data-settings");
         if(var6 != null) {
            String var7 = var5.getId();
            String var8 = var5.getAttribute("type", "unknown");
            String var9 = null;
            if(var8.equals("checkbox")) {
               String var10 = var5.getAttribute("checked");
               if(var10 != null && !"false".equals(var10)) {
                  var9 = "true";
               } else {
                  var9 = "false";
               }
            } else {
               var9 = var5.getAttribute("value");
            }

            try {
               var1.bQ.setValueDynamic(var7, var9);
            } catch (NumberFormatException var11) {
               this.alert("Error:" + var11.getMessage());
            }
         }
      }

      this.guiEngine.g();
      com.corrodinggames.rts.gameFramework.j.a();
      com.corrodinggames.rts.gameFramework.h.a.e();
      this.guiEngine.h();
   }

   public String hideStyle(boolean var1) {
      return var1?"":"display:none;";
   }

   public String hideIf(boolean var1) {
      return this.hideClass(!var1);
   }

   public String hideUnless(boolean var1) {
      return this.hideClass(var1);
   }

   public String hideClass(boolean var1) {
      return var1?"":"hide";
   }

   public String hideIfMobile() {
      return l.au()?"hide":"";
   }

   public boolean canResume() {
      l var1 = l.B();
      return var1 != null && var1.bG && !var1.bH;
   }

   public boolean isMobile() {
      return l.au();
   }

   public boolean isIOS() {
      return l.aZ;
   }

   public boolean isDesktop() {
      return l.av();
   }

   public boolean isMac() {
      return com.corrodinggames.rts.game.i.c;
   }

   public boolean hasModSupport() {
      return !l.aZ;
   }

   public boolean usingMods() {
      if(l.aZ) {
         l var1 = l.B();
         return var1.bZ.c() > 0;
      } else {
         return true;
      }
   }

   public boolean hasWorkshopSupport() {
      return l.av();
   }

   public boolean hasReloadSupport() {
      return !l.aZ;
   }

   String restrictedString(String var1) {
      if(var1 == null) {
         return null;
      } else {
         var1 = var1.replace("\'", ".");
         var1 = var1.replace("\"", ".");
         var1 = var1.replace("(", ".");
         var1 = var1.replace(")", ".");
         var1 = var1.replace(",", ".");
         var1 = var1.replace("<", ".");
         var1 = var1.replace(">", ".");
         return "\'" + var1 + "\'";
      }
   }

   String escapedString(String var1) {
      var1 = var1.replace("&", "&amp;");
      var1 = var1.replace("<", "&lt;");
      var1 = var1.replace(">", "&gt;");
      var1 = var1.replace("\'", "&apos;");
      var1 = var1.replace("\"", "&quot;");
      var1 = var1.replace("${", "$ {");
      return "\'" + var1 + "\'";
   }

   String htmlString(String var1) {
      var1 = var1.replace("&", "&amp;");
      var1 = var1.replace("<", "&lt;");
      var1 = var1.replace(">", "&gt;");
      var1 = var1.replace("\"", "&quot;");
      var1 = var1.replace("${", "$ {");
      return "" + var1 + "";
   }

   String htmlStringWithNewlines(String var1) {
      var1 = this.htmlString(var1);
      var1 = var1.replace("\n", "<br/>");
      return "" + var1 + "";
   }

   public void checkServerListScroll() {
      Element var1 = this.libRocket.getActiveElementById("serverScrollDiv");
      if(var1 == null) {
         this.logWarn("serverScrollDiv==null");
      } else {
         Boolean var2 = (Boolean)this.libRocket.b("showFullServerList");
         if(var2 == null) {
            var2 = Boolean.valueOf(false);
         }

         if(!var2.booleanValue() && var1.getScrollTop() > 200.0F) {
            this.libRocket.getActiveDocument().setMetadata("showFullServerList", Boolean.valueOf(true));
            this.scriptEngine.addScriptToQueue("displayServerList()");
         }

      }
   }

   public void refreshServerList() {
      this.refreshServerListRaw("serverListData", "serverRowTemplateHolder", "refreshButton");
   }

   public void displayServerList() {
      this.displayServerListRaw("serverListData", "serverRowTemplateHolder", "refreshButton");
   }

   public void refreshServerListRaw(String var1, String var2, String var3) {
      l var4 = l.B();
      if(var3 != null) {
         Element var5 = this.libRocket.getActiveElementById(var3);
         var5.setText("Refreshing");
      }

      Root$6 var6 = new Root$6(this, var1, var2, var3);
      var4.bX.bh = null;
      n.a((Runnable)var6);
   }

   public void displayServerListRaw(String var1, String var2, String var3) {
      l var4 = l.B();
      Element var5 = this.libRocket.getActiveElementById(var1);
      Element var6 = this.libRocket.getActiveElementById(var2);
      if(var5 == null) {
         l.b("serverListData is null, we may have changed page");
      } else {
         Element var7 = var5;
         ArrayList var8 = p.m();
         this.lastSortedDiscoveredServers = var8;
         String var9 = com.corrodinggames.rts.gameFramework.h.a.a("menus.lobby.gameState.battleroom", new Object[0]);
         String var10 = com.corrodinggames.rts.gameFramework.h.a.a("menus.lobby.gameState.ingame", new Object[0]);
         String var11 = com.corrodinggames.rts.gameFramework.h.a.a("menus.lobby.gameState.chat", new Object[0]);
         if(var5.getNumChildren() > var8.size()) {
            for(int var12 = var5.getNumChildren() - 1; var12 >= var8.size(); --var12) {
               l.e("removing rowIndex:" + var12);
               var7.removeChild(var7.getChild(var12));
            }

            if(var7.getNumChildren() != var8.size()) {
               l.b("-- Non matching size after clean up:" + var7.getNumChildren() + " vs " + var8.size());
            }
         }

         Boolean var39 = (Boolean)this.libRocket.b("showFullServerList");
         if(var39 == null) {
            var39 = Boolean.valueOf(false);
         }

         int var13 = 0;
         byte var14 = 50;
         Iterator var16;
         g var17;
         if(!var39.booleanValue() && var8.size() > var14) {
            ArrayList var15 = new ArrayList();
            var16 = var8.iterator();

            while(var16.hasNext()) {
               var17 = (g)var16.next();
               var15.add(var17);
               if(var15.size() > var14) {
                  break;
               }
            }

            var13 = var8.size() - var15.size();
            var8 = var15;
         }

         int var40 = 0;

         for(var16 = var8.iterator(); var16.hasNext(); ++var40) {
            var17 = (g)var16.next();
            Element var18 = null;
            if(var40 < var7.getNumChildren()) {
               var18 = var7.getChild(var40);
            }

            if(var18 != null && var18.hasClassName("serverRowMessage")) {
               l.e("removing non rowIndex:" + var40);
               var7.removeChild(var18);
               var18 = null;
            }

            if(var18 != null && var18.findByClassName("rState") == null) {
               l.e("removing non rowIndex with no rState:" + var40);
               var7.removeChild(var18);
               var18 = null;
            }

            if(var18 == null) {
               var18 = var6.clone();
               var7.appendChild(var18);
               var18.removeReference();
               var18.setAttribute("onclick", "clickedServerRow(" + var40 + ")");
            }

            String var19 = var17.s;
            if(var19 != null) {
               var19 = var19.replace("battleroom", var9);
               var19 = var19.replace("ingame", var10);
               var19 = var19.replace("chat", var11);
            }

            boolean var20 = false;
            boolean var21 = false;
            boolean var22 = false;
            boolean var23 = false;
            if(var17 != null && var17.x) {
               var20 = true;
               if("chat".equalsIgnoreCase(var17.s)) {
                  var21 = true;
               }

               if(var17.d()) {
                  var23 = true;
               }
            }

            int var24 = Color.a(255, 255, 255, 255);
            String var25 = "serverRow serverRowData realServerRow ";
            boolean var26 = false;
            if(var17 != null) {
               if(var20) {
                  var25 = var25 + "dedicatedServerRow ";
                  if(var21 || var23) {
                     var24 = Color.a(255, 152, 236, 249);
                     var25 = var25 + "chatRow ";
                  }
               } else {
                  if(var17.h) {
                     var24 = Color.a(255, 240, 240, 240);
                     var25 = var25 + "openRow ";
                  }

                  if(var17.a) {
                     var24 = Color.a(255, 229, 149, 35);
                     var25 = var25 + "lanRow ";
                  }
               }

               if(var17.a()) {
                  var25 = var25 + "lastConnectedRow ";
               }

               if(!var21 && !var23 && !("" + var4.c(true)).equals(var17.j)) {
                  var22 = true;
               }
            }

            String var27 = "";
            var27 = var27 + "color:" + com.corrodinggames.rts.gameFramework.f.h(var24) + ";";
            if(var26) {
               var27 = var27 + "font-weight: bold;";
               var25 = var25 + "boldRow ";
            }

            var18.compareAndSetClassNames(var25);
            var18.findByClassName("rState").compareAndSetText(var19);
            String var28 = com.corrodinggames.rts.gameFramework.f.a(var17.n, (int)15);
            var18.findByClassName("rHost").compareAndSetText(var28);
            String var29;
            if(var17.t == "?") {
               var29 = "?";
            } else {
               var29 = var17.t + "\\" + var17.u;
            }

            var18.findByClassName("rPlayers").compareAndSetText(com.corrodinggames.rts.gameFramework.f.a(var29, (int)15));
            String var30 = com.corrodinggames.rts.gameFramework.f.a(i.e(var17.q), (int)40);
            if(var30 == null) {
               var30 = "";
            }

            var18.findByClassName("rMap").compareAndSetText(var30);
            String var31;
            if("ANY".equalsIgnoreCase(var17.k)) {
               var31 = var17.k;
            } else {
               var31 = "v" + com.corrodinggames.rts.gameFramework.f.a(var17.k, (int)8);
            }

            Element var32 = var18.findByClassName("rVersion");
            var32.compareAndSetText(var31);
            String var33 = "";
            String var34 = "cell rVersion ";
            if(var22) {
               var33 = "color:" + com.corrodinggames.rts.gameFramework.f.h(Color.a(255, 155, 147, 147)) + ";";
               var34 = var34 + "nonMatchingRow ";
            } else {
               var33 = "color:" + com.corrodinggames.rts.gameFramework.f.h(var24) + ";";
            }

            var32.compareAndSetClassNames(var34);
            String var35 = "";
            if(var17.h) {
               if(var17.m) {
                  var35 = "P";
               } else {
                  var35 = "Y";
               }
            } else {
               var35 = "N";
            }

            if(var17.a) {
               var35 = "L";
            }

            Element var36 = var18.findByClassName("rOpen");
            var36.compareAndSetText(var35);
            String var37 = "";
            String var38 = "cell rOpen ";
            if(!var17.h && !var17.a) {
               var37 = "color:" + com.corrodinggames.rts.gameFramework.f.h(Color.a(255, 155, 147, 147)) + ";";
               var38 = var38 + "notOpenRow ";
            } else {
               var37 = "color:" + com.corrodinggames.rts.gameFramework.f.h(var24) + ";";
            }

            var36.compareAndSetClassNames(var38);
         }

         Element var43;
         if(var8.size() == 0 && var4.bX.bh != null) {
            String var41 = "ERROR: " + var4.bX.bh;
            var17 = null;
            var43 = var6.clone();
            var7.appendChild(var43);
            var43.removeReference();
            var43.setText(var41);
         }

         Element var42 = this.libRocket.getActiveElementById("padding");
         if(var42 == null && var13 > 0) {
            var42 = var6.clone();
            var7.appendChild(var42);
            var42.removeReference();
            var42.setAttribute("id", "padding");
            var42.addClass("serverRowMessage");
         }

         if(var42 != null && var13 > 0) {
            byte var44 = 18;
            var42.setStyle("height:" + var44 * var13 + "px;");
         }

         if(var3 != null) {
            var43 = this.libRocket.getActiveElementById(var3);
            var43.setText("Refresh");
         }

         l.b("DONE");
      }
   }

   public void clickedServerRow(int var1) {
      g var2 = (g)this.lastSortedDiscoveredServers.get(var1);
      this.clickedServer(var2.b);
   }

   public void clickedServer(String var1) {
      if(this.getAlertOrPopup() != null) {
         this.logWarn("clickedServer: getAlertOrPopup!=null");
      } else {
         g var2;
         try {
            var2 = n.b(var1);
         } catch (IOException var7) {
            throw new RuntimeException(var7);
         }

         if(var2 == null) {
            this.logWarn("clickedServer: server==null");
            this.alert("That server no longer exists");
         } else {
            String var3 = var2.b();
            String var4 = "Join Server?";
            String var5;
            if(var2.d()) {
               var5 = "[onenter]Open Link:";
               var5 = var5 + "closePopup(); openWhitelistedLink(" + this.restrictedString(var2.c()) + ");";
            } else if(!var2.a) {
               var5 = "[onenter]Join:";
               var5 = var5 + "closePopup(); joinServerWithId(" + this.restrictedString(var2.e()) + "," + this.restrictedString(var2.b) + ");";
            } else {
               var5 = "[onenter]Join over LAN:";
               var5 = var5 + "closePopup(); joinServerWithId(" + this.restrictedString(var2.f()) + "," + this.restrictedString(var2.b) + ");";
            }

            boolean var6 = true;
            this.showPopup((String)null, var3, var6, var5, (String)null);
         }
      }
   }

   public void hideKeyboard() {
      this.guiEngine.m();
   }

   public void saveGame(String var1) {
      this.closePopup();
      this.hideKeyboard();
      var1 = var1.replace(".", "_");
      var1 = var1.replace("/", "_");
      var1 = var1.replace("\\", "_");
      l var2 = l.B();
      var2.ca.b(var1, false);
   }

   public void exportMap(String var1) {
      this.closePopup();
      l var2 = l.B();
      var1 = var1.replace(".", "_");
      var1 = var1.replace("/", "_");
      var1 = var1.replace("\\", "_");
      var1 = var1.replace("|", "_");
      var1 = var1.replace("?", "_");

      try {
         var2.bL.b(var2.dl, "/SD/rusted_warfare_maps/" + var1 + ".tmx");
      } catch (com.corrodinggames.rts.game.b.f var4) {
         this.showAlert("Failed to export map. error: " + var4.getMessage());
         return;
      } catch (IOException var5) {
         this.showAlert("Failed to export map. IO error: " + var5.getMessage());
         return;
      }

      this.showAlert("Map exported");
   }

   public void loadGame(String var1) {
      l var2 = l.B();
      var2.bX.b("loading new save");
      var2.bv = false;
      if(var2.ca.c(var1, false)) {
         this.resumeNonMenu();
      }

   }

   public void loadGameEdit(String var1) {
      l var2 = l.B();
      e var4 = null;
      if(com.corrodinggames.rts.gameFramework.l.a.b()) {
         var4 = new e("Share", new Root$7(this, var2, var1));
      }

      e var5 = new e("Delete", new Root$8(this, var2, var1));
      boolean var6 = true;
      this.showPopupWithButtons((String)null, var1, var6, var4, var5);
   }

   public void loadReplay(String var1) {
      l var2 = l.B();
      var2.bv = false;
      if(var2.cb.c(var1)) {
         this.resumeNonMenu();
      }

   }

   public void loadReplayEdit(String var1) {
      l var2 = l.B();
      e var4 = null;
      if(com.corrodinggames.rts.gameFramework.l.a.b()) {
         var4 = new e("Share", new Root$9(this, var2, var1));
      }

      e var5 = new e("Delete", new Root$10(this, var2, var1));
      boolean var6 = true;
      this.showPopupWithButtons((String)null, var1, var6, var4, var5);
   }

   public void makeSaveGamePopup(String var1) {
      l var2 = l.B();
      String var3 = "Save Game";
      String var4 = "Enter a name to save the game under";
      String var5;
      if(var1 == null) {
         var5 = var2.al() + " (" + com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy HH-mm-ss") + ")";
         var5 = var5.replace("  ", " ");
      } else {
         var5 = var1;
      }

      this.showInputPopup(var3, var4, var5, "[onenter]Save:saveGame(getPopupText())", (String)null);
   }

   public void makeExportMapGamePopup(String var1) {
      l var2 = l.B();
      String var3 = "Export Map";
      String var4 = "Enter a name to export the map as";
      String var5;
      if(var1 == null) {
         var5 = "New " + var2.al() + " - " + com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy");
         var5 = var5.replace("  ", " ");
      } else {
         var5 = var1;
      }

      this.showInputPopup(var3, var4, var5, "[onenter]Export:exportMap(getPopupText())", (String)null);
   }

   public void makeSendMessagePopup() {
      l var1 = l.B();
      String var2 = "Send Message";
      String var3 = "[onenter]Send: sendChatMessage(getPopupText()); closePopup();";
      String var4 = "Switch - Team only: makeSendTeamMessagePopupWithDefaultText(getPopupText()); ";
      String var5 = "";
      this.showInputPopup(var2, var5, "", var3, var4);
   }

   public void makeSendTeamMessagePopup() {
      this.makeSendTeamMessagePopupWithDefaultText("");
   }

   public void makeSendTeamMessagePopupWithDefaultText(String var1) {
      l var2 = l.B();
      String var3 = "Send Team Message";
      String var4 = "+ Ping Map:sendTeamChatMessageAndPing(getPopupText()); closePopup();";
      String var5 = "";
      this.showInputPopup(var3, var5, var1, "[onenter]Send Team:sendTeamChatMessage(getPopupText()); closePopup();", var4);
   }

   public void sendChatMessage(String var1) {
      l var2 = l.B();
      this.guiEngine.m();
      if(var1 != null && !var1.trim().equals("")) {
         var2.bX.m(var1);
         var2.bS.u = false;
      }
   }

   public void sendTeamChatMessageAndPing(String var1) {
      this.sendTeamChatMessage(var1);
      l var2 = l.B();
      var2.bS.I();
   }

   public void sendTeamChatMessage(String var1) {
      l var2 = l.B();
      this.guiEngine.m();
      if(var1 != null && !var1.trim().equals("")) {
         var2.bX.l(var1);
      }
   }

   public void receiveChatMessage(int var1, String var2, String var3, com.corrodinggames.rts.gameFramework.j.c var4) {
      this.refreshChat();
   }

   public void refreshChat() {
      l var1 = l.B();
      if(this.libRocket.getActiveDocument() != null) {
         Element var2 = this.libRocket.getActiveElementById("chatLogHistory");
         if(var2 != null) {
            boolean var3 = var2.getAttributeBoolean("reversed", false);
            if(var1.bX.F) {
               Element var4 = this.libRocket.getActiveElementById("chatBox");
               if(var4 != null) {
                  var4.hide();
               }
            }

            var2.setInnerRML("");
            ConcurrentLinkedQueue var8 = var1.bX.aC.b();
            StringBuffer var5 = new StringBuffer();
            Iterator var6 = var8.iterator();

            while(var6.hasNext()) {
               com.corrodinggames.rts.gameFramework.j.b var7 = (com.corrodinggames.rts.gameFramework.j.b)var6.next();
               if(var3) {
                  var5.insert(0, "<div>" + var7.b() + "</div>");
               } else {
                  var5.append("<div>" + var7.b() + "</div>");
               }
            }

            var5.append("<div id=\'chatLastRowSpacer\'></div>");
            var2.setInnerRML(var5.toString());
            var2.loadCharsetIfNeededWithCurrentText();
            Element var9 = this.libRocket.getActiveElementById("chatLastRowSpacer");
            if(var9 != null) {
               var9.scrollIntoView(false);
            }

         }
      }
   }

   public void trace(String var1) {
      l.e("Trace:" + var1);
   }

   public void updateTableTextOnly(String var1, Root$TableData var2, Root$TableData var3) {
      ArrayList var4 = var2.rows;
      Element var5 = this.libRocket.getActiveElementById(var1);
      if(var5 == null) {
         l.b("updateTableText: tableElement:" + var1 + " is null, we may have changed page");
      } else {
         Element var6 = var5.getElementById("tableListData");

         for(int var7 = 0; var7 < var4.size(); ++var7) {
            Root$TableRow var8 = (Root$TableRow)var4.get(var7);

            for(int var9 = 0; var9 < var8.tableCells.size(); ++var9) {
               Root$TableCell var10 = (Root$TableCell)var8.tableCells.get(var9);
               Element var11 = var6.getChild(var7);
               if(var11 == null) {
                  l.b("updateTableText failed to get row " + var7);
                  return;
               }

               Element var12 = var11.getChild(var9);
               if(var12 == null) {
                  l.b("updateTableText failed to get cell " + var9);
                  return;
               }

               var12.compareAndSetText(var10.text);
            }
         }

      }
   }

   public void refreshTable(String var1, Root$TableData var2) {
      ArrayList var3 = var2.rows;
      Element var4 = this.libRocket.getActiveElementById(var1);
      if(var4 == null) {
         l.b("refreshTable: tableElement:" + var1 + " is null, we may have changed page");
      } else {
         Element var5 = var4.getElementById("tableRowTemplateHolder");
         Element var6 = var4.getElementById("tableListData");
         Element var7 = var5.findByClassName("rowTemplate").getChild(0);
         Element var8 = var5.findByClassName("cellTemplate").getChild(0);
         var6.setInnerRML("");
         Iterator var9 = var3.iterator();

         while(var9.hasNext()) {
            Root$TableRow var10 = (Root$TableRow)var9.next();
            Element var11 = var7.cloneAndFix();
            if(var10.librocketOnClick != null) {
               var11.setAttribute("onclick", var10.librocketOnClick);
            }

            if(var10.extraClasses != null) {
               var11.addClass(var10.extraClasses);
            }

            Iterator var12 = var10.tableCells.iterator();

            while(var12.hasNext()) {
               Root$TableCell var13 = (Root$TableCell)var12.next();
               Element var14 = var8.cloneAndFix();
               var14.compareAndSetText(var13.text);
               if(var13.librocketOnClick != null) {
                  var14.setAttribute("onclick", var13.librocketOnClick);
                  var14.addClass("clickablecell");
               }

               if(var13.classes != null) {
                  var14.addClass(var13.classes);
               }

               if(var13.color != null) {
                  var14.setAttribute("style", "color:" + com.corrodinggames.rts.gameFramework.f.h(var13.color.intValue()) + ";");
               }

               var11.appendChild(var14);
               var14.removeReference();
            }

            var6.appendChild(var11);
            var11.removeReference();
         }

      }
   }

   public ElementDocument createAndShowPopup(String var1, Object var2, String var3) {
      return this.libRocket.a(var1, var2, var3, true);
   }

   public ElementDocument createPopupHidden(String var1, Object var2, String var3) {
      return this.libRocket.a(var1, var2, var3, false);
   }

   public boolean tryToShowPopupDocument(ElementDocument var1) {
      return this.libRocket.b(var1);
   }

   public void showMainMenu() {
      l.B().bS.u = false;
      com.corrodinggames.librocket.a.a().b();
   }

   public void onEnter() {
      ElementDocument var1 = this.libRocket.g();
      if(var1 == null) {
         l.e("onEnter: elementDocument==null");
      } else {
         ArrayList var2 = var1.getAllNestedChildren();
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            Element var4 = (Element)var3.next();
            String var5 = var4.getAttribute("onenter");
            if(var5 != null && var4.isFocused()) {
               this.scriptEngine.processScript(var5);
            }
         }

      }
   }

   public void scrollFromFocusedElement(float var1) {
      ElementDocument var2 = this.libRocket.g();
      if(var2 == null) {
         l.e("onEnter: elementDocument==null");
      } else {
         Element var3 = var2.getTopLevelFocusedElement();
         if(var3 == null) {
            l.e("focusedElement: Not found");
         } else {
            ArrayList var4 = var2.getChainFromChildElement(var3);
            if(var4 == null) {
               l.e("scrollFromFocusedElement: Failed to find chain");
            } else {
               Iterator var5 = var4.iterator();

               Element var6;
               boolean var7;
               do {
                  if(!var5.hasNext()) {
                     l.e("Found no slider element to offset");
                     return;
                  }

                  var6 = (Element)var5.next();
                  var7 = false;
                  if("scrollDiv".equals(var6.getId())) {
                     var7 = true;
                  }

                  if(var6.hasClassName("slider")) {
                     var7 = true;
                  }
               } while(!var7);

               float var8 = var6.getScrollTop();
               var8 += var1;
               var6.setScrollTop(var8);
            }
         }
      }
   }

   public boolean isSliderOrUIElementSelected() {
      ElementDocument var1 = this.libRocket.g();
      if(var1 == null) {
         l.e("onEnter: elementDocument==null");
         return false;
      } else {
         Element var2 = var1.getTopLevelFocusedElement();
         if(var2 != null) {
            String var4 = var2.getTagName();
            boolean var5 = false;
            if("scrollDiv".equals(var2.getId())) {
               var5 = true;
            }

            if(var2.hasClassName("slider")) {
               var5 = true;
            }

            if("input".equals(var4) && "range".equals(var2.getAttribute("type", "text"))) {
               var5 = true;
            }

            if(var5) {
               l.e("Slider element: true");
               return true;
            }

            l.e("Slider element: false");
         }

         l.e("Slider element: no element focused");
         return false;
      }
   }

   public void onTouch() {
      ElementDocument var1 = this.libRocket.g();
      if(var1 == null) {
         l.e("onEnter: elementDocument==null");
      } else {
         ArrayList var2 = var1.getAllNestedChildren();
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            Element var4 = (Element)var3.next();
            String var5 = var4.getAttribute("type");
            if("text".equals(var5) && var4.isFocused()) {
               this.guiEngine.l();
            }
         }

      }
   }

   public void onEscape() {
      ElementDocument var1 = this.libRocket.g();
      if(var1 == null) {
         l.e("onEscape: elementDocument==null");
      } else {
         boolean var2 = false;
         ArrayList var3 = var1.getAllNestedChildren();
         Iterator var4 = var3.iterator();

         while(var4.hasNext()) {
            Element var5 = (Element)var4.next();
            String var6 = var5.getAttribute("click_on_escape");
            if(var6 != null) {
               var5.click();
               var2 = true;
               break;
            }
         }

         if(!var2) {
            if(!this.closePopup()) {
               ;
            }
         }
      }
   }

   public void askQuitGame() {
      this.closePopup();
      String var1 = "Are you sure you want to quit?";
      String var2 = "";
      String var3 = "[onenter]Quit:";
      var3 = var3 + "closePopup(); exit();";
      boolean var4 = true;
      this.showPopup(var1, var2, var4, var3, (String)null);
   }

   public String getCurrentDocumentPath() {
      ElementDocument var1 = this.libRocket.getActiveDocument();
      return var1 == null?null:var1.documentPath;
   }

   public String getCurrentPopupPath() {
      ElementDocument var1 = this.libRocket.c();
      return var1 == null?null:var1.documentPath;
   }

   public String getCreditsText() {
      return "Credits goes here";
   }

   public void runRunnable(Runnable var1) {
      this.logDebug("runRunnable");
      if(var1 == null) {
         this.logDebug("runnable==null");
      }

      var1.run();
   }

   public boolean isLinux() {
      return com.corrodinggames.rts.gameFramework.g.a() == h.c;
   }

   public boolean not(boolean var1) {
      return !var1;
   }

   public boolean and(boolean var1, boolean var2) {
      return var1 && var2;
   }

   public boolean or(boolean var1, boolean var2) {
      return var1 || var2;
   }

   public void showBattleroom() {
      String var1 = "battleroom.rml";
      ElementDocument var2 = this.libRocket.getActiveDocument();
      boolean var3 = true;
      if(var2 != null && var1.equals(var2.documentPath)) {
         l.e("Already on battleroom page");
         var3 = false;
      }

      this.libRocket.setDocument(var1, (HashMap)null, var3);
   }

   public void setDocument(String var1) {
      this.libRocket.setDocument(var1);
   }

   public void playNextMusicTrack() {
      l.B().bN.e();
   }

   public void toggleMusic() {
      l.B().bN.u = !l.B().bN.u;
   }

   public void updateMusicButton(String var1) {
      Element var2 = this.libRocket.getActiveElementById(var1);
      if(var2 != null) {
         if(l.B().bN.u) {
            var2.setText(">");
         } else {
            var2.setText("||");
         }
      }

   }

   public void setSandboxMapFromPopup(String var1) {
      l var2 = l.B();
      this.closePopup();
      this.libRocket.getActiveDocument().setMetadata("mode", var1);
      this.showLevelOptions();
      this.libRocket.getActiveDocument().findByClassName("mapImage").setAttribute("src", this.getMapThumbnail(var1));
      this.libRocket.getActiveDocument().findByClassName("mapText").setText(this.getMapNameFromPath(var1));
   }

   public void showSandboxMapSelectOnChange() {
      ElementDocument var1 = this.libRocket.getActiveDocument();
      Element var2 = var1.getElementById("typeSelector");
      int var3 = Integer.parseInt(var2.getValue());
      int var4 = ((Integer)var1.getMetadata("lastTypeSelector", Integer.valueOf(0))).intValue();
      this.libRocket.getActiveDocument().setMetadata("lastTypeSelector", Integer.valueOf(var3));
      if(var3 != var4) {
         this.showSandboxMapSelect();
      }

   }

   public void showSandboxMapSelect() {
      String var1 = this.getModeMapPath(this.libRocket.getActiveDocument(), "typeSelector");
      this.showMapPopup(var1, "setSandboxMapFromPopup");
   }

   public String getModeMapPath(Element var1, String var2) {
      l var3 = l.B();
      int var4;
      if(var2 == null) {
         if(var3.bX.ay.a == null) {
            l.b("getModeMapPath: currentType==0");
            var4 = 0;
         } else {
            var4 = var3.bX.ay.a.ordinal();
         }
      } else {
         Element var5 = var1.getElementById(var2);
         if(var5 == null) {
            l.g("getModeMapPath: typeSelector==null");
            var4 = 0;
         } else {
            var4 = var5.getValueAsInt(Integer.valueOf(0)).intValue();
         }
      }

      if(var4 == 0) {
         return "maps/skirmish";
      } else if(var4 == 1) {
         return "/SD/rusted_warfare_maps";
      } else if(var4 == 2) {
         return "saves";
      } else {
         throw new RuntimeException("Unknown typeIndex:" + var4);
      }
   }

   public void event_unicodeEntered() {
      ElementDocument var1 = this.libRocket.g();
      if(var1 != null) {
         Element var2 = var1.findByClassName("textinputUnicodeWrap");
         if(var2 != null) {
            var2.compareAndAddClass("unicodeWasTyped");
         } else {
            l.e("event_unicodeEntered: missing textinput");
         }
      } else {
         l.e("event_unicodeEntered: missing document");
      }

   }

   public boolean isVersionBeta() {
      l var1 = l.B();
      return var1.n();
   }

   public Object ifCondition(boolean var1, Object var2, Object var3) {
      return var1?var2:var3;
   }

   public String i(String var1) {
      return com.corrodinggames.rts.gameFramework.h.a.a(var1, new Object[0]);
   }

   public void openLinkToCG(String var1) {
      String var2 = "http://corrodinggames.com/" + var1;
      this.openWhitelistedLink(var2);
   }

   public void openWhitelistedLink(String var1) {
      l.e("Opening link:" + var1);
      if(!var1.startsWith("http://corrodinggames.com/") && !var1.startsWith("https://corrodinggames.com/") && !var1.startsWith("http://corrodinggames.net/") && !var1.startsWith("https://corrodinggames.net/")) {
         l.e("Not in whitelist");
      } else if(this.guiEngine.b(var1)) {
         this.alert("Opened link: " + var1);
      } else {
         this.alert("Sorry couldn\'t load browser to: " + var1 + " please navigate manually");
      }
   }

   public void writeGameLog(String var1) {
      StringBuffer var2 = new StringBuffer();
      boolean var3 = false;
      LinkedList var4 = com.corrodinggames.librocket.a.a().k();
      if(var4 == null) {
         var3 = true;
      } else {
         synchronized(var4) {
            int var6 = com.corrodinggames.rts.gameFramework.f.b(0, var4.size() - 3000);
            ListIterator var7 = var4.listIterator(var6);

            while(var7.hasNext()) {
               var2.append(Element.excapeHTML((String)var7.next()));
               var2.append("<br/>");
            }
         }
      }

      if(var3) {
         this.alert("Internal game logging not active");
      } else {
         l.e("writeGameLog ready");
         Element var10 = this.libRocket.getActiveElementById(var1);
         if(var10 == null) {
            l.e("Failed to find: " + var1);
         } else {
            var10.setInnerRML(var2.toString());
         }
      }
   }

   public void exportGameLog() {
      StringBuffer var1 = new StringBuffer();
      boolean var2 = false;
      LinkedList var3 = com.corrodinggames.librocket.a.a().k();
      if(var3 == null) {
         var2 = true;
      } else {
         synchronized(var3) {
            int var5 = com.corrodinggames.rts.gameFramework.f.b(0, var3.size() - 3000);
            ListIterator var6 = var3.listIterator(var5);

            while(var6.hasNext()) {
               var1.append(Element.excapeHTML((String)var6.next()));
               var1.append("\n");
            }
         }
      }

      if(var2) {
         this.alert("Internal game logging not active");
      } else {
         try {
            String var10 = "/SD/rustedWarfare/RustedWarfareLog-" + com.corrodinggames.rts.gameFramework.f.a("d_MMM_yyyy_HH.mm.ss") + ".txt";
            String var4 = com.corrodinggames.rts.gameFramework.e.a.e(var10);
            File var11 = new File(var4);
            FileWriter var12 = new FileWriter(var11);
            var12.append(var1.toString());
            var12.flush();
            var12.close();
            com.corrodinggames.rts.gameFramework.l.a.a(var11);
            var11.deleteOnExit();
         } catch (Exception var8) {
            var8.printStackTrace();
            this.alert("Failed to export logs: " + var8.getMessage());
         }
      }
   }

   public void setPageMinWidthAndHeight(float var1, float var2) {
      l.e("setPageMinWidthAndHeight(" + var1 + ", " + var2 + ")");
      ElementDocument var3 = this.libRocket.getActiveDocument();
      if(var3 == null) {
         l.e("setPageMinWidthAndHeight - no page");
      } else {
         var3.setMetadataFloat("minWidth", Float.valueOf(var1));
         var3.setMetadataFloat("minHeight", Float.valueOf(var2));
         this.guiEngine.n();
      }
   }

   public void importFilePopup() {
      Root$11 var1 = new Root$11(this);
      com.corrodinggames.rts.gameFramework.l.a.a((com.corrodinggames.rts.gameFramework.l.b)var1);
   }

   protected void setDocumentUpdate(ElementDocument var1, Runnable var2) {
      var1.setMetadata("onUpdateFunction", var2);
   }

   public void onFrameUpdate(float var1) {
      ElementDocument var2 = this.libRocket.getActiveDocument();
      if(var2 != null) {
         Object var3 = var2.getMetadata("onUpdateFunction");
         if(var3 != null) {
            Runnable var4 = (Runnable)var3;
            var4.run();
         }
      }

   }

}
