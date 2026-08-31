/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;
import com.corrodinggames.rts.gameFramework.MusicFactory;

import android.graphics.Color;
import com.Element;
import com.ElementDocument;
import com.corrodinggames.librocket.LibRocketContext;
import com.corrodinggames.librocket.LibRocketBridge;
import com.corrodinggames.librocket.e;
import com.corrodinggames.librocket.scripts.ModsUIUI;
import com.corrodinggames.librocket.scripts.MultiplayerUIUI;
import com.corrodinggames.librocket.scripts.MainUIController$1;
import com.corrodinggames.librocket.scripts.MainUIController$10;
import com.corrodinggames.librocket.scripts.MainUIController$11;
import com.corrodinggames.librocket.scripts.MainUIController$2;
import com.corrodinggames.librocket.scripts.MainUIController$3;
import com.corrodinggames.librocket.scripts.MainUIController$4;
import com.corrodinggames.librocket.scripts.MainUIController$5;
import com.corrodinggames.librocket.scripts.MainUIController$6;
import com.corrodinggames.librocket.scripts.MainUIController$7;
import com.corrodinggames.librocket.scripts.MainUIController$8;
import com.corrodinggames.librocket.scripts.MainUIController$9;
import com.corrodinggames.librocket.scripts.MainUIController$TableCell;
import com.corrodinggames.librocket.scripts.MainUIController$TableData;
import com.corrodinggames.librocket.scripts.MainUIController$TableRow;
import com.corrodinggames.librocket.scripts.ScriptContext;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.librocket.scripts.ScriptEngine$Action;
import com.corrodinggames.rts.appFramework.ContextMenuActivity;
import com.corrodinggames.rts.appFramework.p;
import com.corrodinggames.rts.appFramework.q;
import com.corrodinggames.rts.gameFramework.KeyBinding;
import com.corrodinggames.rts.gameFramework.PerformanceTimer;
import com.corrodinggames.rts.gameFramework.audio.DataFieldInt;
import com.corrodinggames.rts.gameFramework.audio.DataFieldProvider;
import com.corrodinggames.rts.gameFramework.OSEnum;
import com.corrodinggames.rts.gameFramework.FileWatcher;
import com.corrodinggames.rts.gameFramework.network.MatchConfig;
import com.corrodinggames.rts.gameFramework.network.GameModeEnum;
import com.corrodinggames.rts.gameFramework.network.ServerConnector;
import com.corrodinggames.rts.gameFramework.network.GameServerInfo;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.commands.DebugServer;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MainUIController
extends ScriptContext {
    public static final boolean DEBUG_TIMING = true;
    public MultiplayerUI multiplayer;
    public ModsUI mods;
    PerformanceTimer openDocumentTimer = new PerformanceTimer("openDocument", true);
    ServerConnector threadedGameConnector;  // 02b j/an (Root L60: an threadedGameConnector) — 错标 MusicFactory 修正
    ElementDocument lastConnectingPopup;
    static PerformanceTimer convertTextStopwatch = new PerformanceTimer("ConvertText", true);
    static PerformanceTimer loadSettingsStopwatch = new PerformanceTimer("LoadSettings", true);
    ArrayList lastSortedDiscoveredServers;

    public void logDebug(String string) {
        GlobalState.e("ui[debug]: " + string);
    }

    public void logWarn(String string) {
        GlobalState.e("ui[warn]: " + string);
    }

    public void back() {
        this.libRocket.backToLastDocument();
        if (this.libRocket.getActiveDocument() == null) {
            GlobalState.b("back: libRocket.getActiveDocument()==null");
            GlobalState l2 = GlobalState.B();
            if (l2 == null || !l2.bq) {
                GlobalState.b("back: showing main menu!");
                this.showMainMenu();
            } else {
                GlobalState.b("back: resuming game");
                this.guiEngine.a(false);
            }
        }
    }

    public void backOrClose() {
        if (this.closePopup()) {
            return;
        }
        this.libRocket.backToLastDocument();
    }

    public String fullVersionOnlyStyle() {
        if (GlobalState.B().ar) {
            return "notInDemo";
        }
        return "";
    }

    public void openIfNotDemo(String string, Object object, String string2) {
        if (GlobalState.B().ar) {
            this.alert(string2);
            return;
        }
        this.open(string, object);
    }

    public String getVersionName() {
        GlobalState l2 = GlobalState.B();
        return l2.t();
    }

    public void delayedOpenNoHistory(String string, Object object) {
        ScriptEngine$Action scriptEngine$Action = this.scriptEngine.addRunnableToQueue(new MainUIController$1(this, string));
        scriptEngine$Action.framesDelay = 1;
    }

    public void openAfterHelpPopup(String string) {
        GlobalState l2 = GlobalState.B();
        if (GlobalState.au() && !l2.bQ.hasPlayedGameOrSeenHelp) {
            l2.bQ.hasPlayedGameOrSeenHelp = true;
            l2.bQ.save();
            String string2 = "";
            String string3 = "First time playing? Would you like to view the quick help slides?";
            String string4 = "[onenter]View Help:";
            string4 = string4 + "closePopup(); open('help_quick_mobile.rml');";
            String string5 = "Skip:";
            string5 = string5 + "closePopup(); open(" + this.restrictedString(string) + ");";
            boolean bl = false;
            this.showPopup(string2, string3, bl, string4, string5);
            return;
        }
        this.open(string, null);
    }

    public void open(String string, Object object) {
        this.openDocumentTimer.a();
        LibRocketBridge.a.f();  // 02b b.a.f(): 静态 PerformanceTimer("LoadResources")
        HashMap<String, Object> hashMap = null;
        if (object != null) {
            hashMap = new HashMap<String, Object>();
            hashMap.put("mode", object);
        }
        this.libRocket.setDocument(string, hashMap);
        this.onShowNewScreen();
        this.openDocumentTimer.d();
        LibRocketBridge.a.e();  // 02b b.a.e(): 静态 PerformanceTimer
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

    public void startNew(String string) {
        this.guiEngine.a(string);
        this.libRocket.closeActiveDocument();
        this.libRocket.clearHistory();
    }

    public void showRangeValue(String string, String string2, boolean bl) {
        Element element = this.libRocket.getActiveElementById(string);
        if (element == null) {
            this.logWarn("Could not find:" + string);
            return;
        }
        String string3 = element.getAttribute("value");
        string3 = bl ? new Float(string3).toString() : Integer.toString((int)Float.parseFloat(string3));
        Element element2 = this.libRocket.getActiveElementById(string2);
        if (element2 == null) {
            this.logWarn("Could not find:" + string2);
            return;
        }
        element2.setText(string3);
    }

    public String getValueById(String string) {
        Element element = this.libRocket.getActiveElementById(string);
        if (element == null) {
            this.logWarn("Could not find:" + string);
            return null;
        }
        String string2 = element.getAttribute("value");
        return string2;
    }

    public void setValueById(String string, String string2) {
        Element element = this.libRocket.getActiveElementById(string);
        if (element == null) {
            this.logWarn("Could not find:" + string);
            return;
        }
        element.setAttribute("value", string2);
    }

    public Element getElementById(String string) {
        Element element = this.libRocket.getActiveElementById(string);
        if (element == null) {
            this.logWarn("Could not find:" + string);
            return null;
        }
        return element;
    }

    public boolean clickElement(Element element) {
        if (element == null) {
            this.logWarn("element is null");
            return false;
        }
        element.click();
        return true;
    }

    public void directJoinPopup() {
        GlobalState l2 = GlobalState.B();
        String string = "Direct Join";
        String string2 = "Enter IP address or host name";
        String string3 = "";
        if (l2.bQ.lastNetworkIP != null) {
            string3 = l2.bQ.lastNetworkIP;
        }
        this.showInputPopup(string, string2, string3, "[onenter]Join:joinServerFromPopup(getPopupText())", null);
    }

    /* 02b Root.java 对应: 调 joinServerWithId 抛 IOException */
    public void joinServerFromPopup(String string) throws IOException {
        this.closePopup();
        this.hideKeyboard();
        if (string == null) {
            this.logDebug("joinAddress==null");
            return;
        }
        string = string.trim();
        GlobalState l2 = GlobalState.B();
        l2.bQ.lastNetworkIP = string;
        l2.bQ.save();
        this.joinServerWithId(string, null);
    }

    /* 02b Root.java 对应: 调 joinServer 抛 IOException */
    public void joinServerWithId(String string, String string2) throws IOException {
        GlobalState l2 = GlobalState.B();
        l2.bX.bw = string2;
        this.joinServer(string);
    }

    /* 02b Root.java L267: 调 createAndShowPopup 抛 IOException */
    public void joinServer(String string) throws IOException {
        if (ScriptEngine.inDebugScript && !DebugServer.d) {  // 02b a.d (Root L268); reliableudp 幻觉包修正
            return;
        }
        this.logDebug("joinAddress=" + string);
        MainUIController$2 root$2 = new MainUIController$2(this);
        GlobalState l2 = GlobalState.B();
        this.threadedGameConnector = l2.bX.registerRelayServer(string, false, root$2);  // 02b ad.a(String,boolean,Runnable) L3538
        this.lastConnectingPopup = this.createAndShowPopup("multiplayerLobby_connecting.rml", null, "Please wait");
    }

    /* 02b Root.java 对应: registerRelayServer(Socket) 抛 IOException */
    public void joinServerCallback() throws IOException {
        this.logDebug("joinServerCallback");
        GlobalState l2 = GlobalState.B();
        if (this.threadedGameConnector == null) {
            this.logDebug("threadedGameConnector==null");
            return;
        }
        this.closePopupOnly();
        if (this.threadedGameConnector.e != null) {
            if (this.threadedGameConnector.e.equals("CANCELLED")) {
                this.logDebug("Server join cancelled");
                return;
            }
            this.logWarn("Server join failed");
            boolean bl = true;
            String string = "Connection failed";
            this.showPopup(string, this.threadedGameConnector.e, bl, null, null);
        } else {
            l2.bX.m("starting new");
            l2.bX.registerRelayServer(this.threadedGameConnector.g);  // 02b ad.a(Socket)
            this.logDebug("connected");
            this.showBattleroom();
        }
        this.threadedGameConnector = null;
    }

    public void cancelJoinServer() {
        if (this.threadedGameConnector != null) {
            if (this.threadedGameConnector.a()) {
                this.closePopup();
            }
        } else {
            this.closePopup();
        }
    }

    public void alert(String string) {
        this.showAlert(string);
    }

    public void showAlert(String string) {
        this.logDebug("alert:" + string);
        if (string == null) {
            string = "null";
        }
        this.libRocket.c(string);
    }

    public void showPopupWithButtons(String string, String string2, boolean bl, e e2, e e3) {
        this.logDebug("showPopup:" + string2);
        if (string2 == null) {
            string2 = "null";
        }
        String string3 = null;
        this.libRocket.a(string, string2, string3, e2, e3, bl);
    }

    public void showPopupWithButtonsAndInput(String string, String string2, boolean bl, String string3, e e2, e e3) {
        this.logDebug("showPopup:" + string2);
        if (string2 == null) {
            string2 = "null";
        }
        if (string3 == null) {
            string3 = "";
        }
        if ("".equals(string3)) {
            this.guiEngine.l();
        }
        this.libRocket.a(string, string2, string3, e2, e3, true, bl);
    }

    public void showPopup(String string, String string2, boolean bl, String string3, String string4) {
        this.logDebug("showPopup:" + string2);
        if (string2 == null) {
            string2 = "null";
        }
        String string5 = null;
        this.libRocket.a(string, string2, string5, string3, string4, bl);
    }

    public void showInputPopup(String string, String string2, String string3, String string4, String string5) {
        String string6;
        this.logDebug("showInputPopup:" + string2);
        if (string2 == null) {
            string2 = "null";
        }
        if ((string6 = string3) == null) {
            string6 = "";
        }
        if ("".equals(string6)) {
            this.guiEngine.l();
        }
        this.libRocket.a(string, string2, string6, string4, string5, true);
    }

    public void showInputPopupNonClose(String string, String string2, String string3, String string4, String string5) {
        String string6;
        this.logDebug("showInputPopup:" + string2);
        if (string2 == null) {
            string2 = "null";
        }
        if ((string6 = string3) == null) {
            string6 = "";
        }
        if ("".equals(string6)) {
            this.guiEngine.l();
        }
        this.libRocket.a(string, string2, string6, string4, string5, true, false);
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
        this.libRocket.a("Host game", this.i("menus.hostgame.info_pc"), null, "Host Private:closePopup();hostStart(false);", "Host Public:closePopup();hostStart(true);", true);
    }

    public void hostStartFromPopup(boolean bl) {
        Object object;
        ElementDocument elementDocument = this.libRocket.g();
        Element element = elementDocument.getElementById("password");
        Object object2 = null;
        if (element == null) {
            this.logWarn("hostStartFromPopup: password==null");
        } else {
            object = element.getValue();
            if (object != null && !((String)object).trim().equals("")) {
                object2 = object;
            }
        }
        object = elementDocument.getElementById("useMods");
        boolean bl2 = ((Element)object).getCheckbox();
        this.closePopup();
        this.hostStartWithPasswordAndMods(bl, (String)object2, bl2);
    }

    public void hostStart(boolean bl) {
        GlobalState.b("old version of hostStart");
        this.hostStartWithPassword(bl, null);
    }

    public void hostStartWithPassword(boolean bl, String string) {
        GlobalState.b("old version of hostStartWithPassword");
        this.hostStartWithPasswordAndMods(bl, string, true);
    }

    public void hostStartWithPasswordAndMods(boolean bl, String string, boolean bl2) {
        GlobalState l2 = GlobalState.B();
        l2.bX.m("starting new");
        l2.bX.n = string;
        l2.bX.o = bl2;
        l2.bX.q = bl;
        if (l2.bX.m(false)) {
            this.logDebug("-Hosting-");
            this.logDebug("using password: " + (l2.bX.n != null));
            this.logDebug("using mods: " + l2.bX.o);
            this.logDebug("public: " + l2.bX.q);
            String string2 = l2.bX.getNetworkMapPath();
            if (string2 != null && !com.corrodinggames.rts.gameFramework.filesystem.FileLoader.i(string2)) {
                GlobalState.b("hostStart: map does not exist: " + string2 + " reseting");
                string2 = null;
            }
            if (string2 == null) {
                int n2 = 0;
                l2.bX.ay.a = GameModeEnum.values()[n2];  // 02b ai.values(): j/ai=GameModeEnum
                l2.bX.az = "maps/skirmish/[p8]Many Islands (8p).tmx";
                l2.bX.ay.b = "[p8]Many Islands (8p).tmx";
            }
            this.libRocket.setDocument("battleroom.rml", null);
        } else {
            this.logWarn("hosting failed");
        }
    }

    public void exit() {
        GlobalState l2 = GlobalState.B();
        if (l2.bQ.numLoadsSinceRunningGameOrNormalExit != 0) {
            l2.bQ.numLoadsSinceRunningGameOrNormalExit = 0;
            l2.bQ.save();
        }
        this.scriptEngine.addRunnableToQueue(new MainUIController$3(this));
    }

    public String getMapDetails(String string) {
        return "hello 123";
    }

    public String getHTMLMapNameFromPath(String string) {
        return this.htmlString(this.getMapNameFromPath(string));
    }

    public String getMapNameFromPath(String string) {
        File file = new File(string);
        return this.convertMapName(file.getName());
    }

    public String convertMapName(String string) {
        String string2 = this.convertMapNameWithoutTranslation(string);
        string2 = com.corrodinggames.rts.gameFramework.steam.Localization.b(string2);
        return string2;
    }

    public String convertMapNameWithoutTranslation(String string) {
        String string2 = ContextMenuActivity.getString2(string);  // 02b i.e(String)=c.b → ContextMenuActivity.getString2
        return string2;
    }

    public String getHTMLMapThumbnail(String string) {
        return this.escapedString(this.getMapThumbnail(string));
    }

    public String getMapThumbnail(String string) {
        String string2 = null;
        if (string.startsWith("saves/")) {
            string2 = "drawable:icon_save.png";
        }
        String string3 = com.corrodinggames.rts.appFramework.AndroidUIHelper.c(string);
        string2 = "thumbnail:assets:" + string3;
        if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.i(string3)) {
            if (GlobalState.aZ) {
                GlobalState.a("getMapThumbnail: Failed to find: " + string3);
            }
            return "drawable:error_missingmap.png";
        }
        return string2;
    }

    public boolean isMapSkirmish(String string) {
        return ContextMenuActivity.f(string);  // 02b i.f(String)=contains("skirmish/")
    }

    public void showLevelOptions() {
        Element element2;
        GlobalState l2 = GlobalState.B();
        String string = (String)this.libRocket.b("mode");
        if (string == null) {
            GlobalState.isKeyJustPressed("levelPath==null");
            return;
        }
        boolean bl = true;
        if (!this.isMapSkirmish(string)) {
            bl = false;
        }
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        for (Element element2_548 : (java.util.Collection<Element>) (java.util.Collection) elementDocument.findElementsByClassName("skirmishOnly")) {
            element2_548.show(bl);
        }
        Element element3 = elementDocument.getElementById("advancedButton");
        if (element3 != null) {
            element3.show(bl || ContextMenuActivity.isEnabled2(string));  // 02b i.g(String)=contains("SD/")
        }
        element2 = elementDocument.getElementById("aiDifficulty");
        element2.setValue("" + l2.bQ.aiDifficulty);
    }

    public void loadConfigAndStartSwitchToAdvanced(String string) {
        boolean bl = true;
        GlobalState l2 = GlobalState.B();
        l2.bv = false;
        this.loadConfigCommon(string, bl);
        this._startAdvancedMode(false);
    }

    private void _startAdvancedMode(boolean bl) {
        GlobalState l2 = GlobalState.B();
        l2.bX.m("starting singleplayer");
        l2.bX.y = "You";
        l2.bX.o = true;
        boolean bl2 = bl ? l2.bX.processPackets() : l2.bX.updateConnections();
        if (bl2) {
            this.logDebug("started startSinglePlayerServer");
            MatchConfig ah2 = l2.bX.kickTeam();
            if (ah2 != null) {
                ah2.f = l2.bQ.aiDifficulty;
                l2.bX.registerRelayServer(ah2);  // 02b ad.a(MatchConfig) L613
            }
            this.libRocket.setDocument("battleroom.rml", null);
        } else {
            this.logWarn("failed startSinglePlayerServer");
        }
    }

    public void loadConfigAndStartNewSandbox(String string) {
        this._loadConfigAndStartNewSandboxCommon(string, true);
    }

    public void loadConfigAndStartNewSandboxInAdvanced(String string) {
        GlobalState.e("loadConfigAndStartNewSandboxInAdvanced");
        this._loadConfigAndStartNewSandboxCommon(string, false);
        this._startAdvancedMode(true);
        GlobalState l2 = GlobalState.B();
        GlobalState.e("editorMode:" + l2.bv);
    }

    private void _loadConfigAndStartNewSandboxCommon(String string, boolean bl) {
        boolean bl2 = false;
        if (string.startsWith("saves/")) {
            GlobalState.e("Starting sandbox from save: " + string);
            this.loadGame(string.substring("saves/".length()));
        } else {
            GlobalState.e("Starting sandbox from map: " + string);
            this.loadConfigCommon(string, bl2);
        }
        GlobalState l2 = GlobalState.B();
        l2.bL.tileHeight = false;
        l2.bS.y();
        l2.bv = true;
        if (bl) {
            this.guiEngine.f();
        }
        this.libRocket.closeActiveDocument();
        this.libRocket.clearHistory();
    }

    public void loadConfigAndStartNew(String string) {
        GlobalState l2 = GlobalState.B();
        l2.bv = false;
        l2.bX.m("starting singleplayer");
        boolean bl = false;
        this.loadConfigCommon(string, bl);
        this.guiEngine.f();
        this.libRocket.closeActiveDocument();
        this.libRocket.clearHistory();
    }

    public void loadConfigCommon(String string, boolean bl) {
        GlobalState l2 = GlobalState.B();
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        Element element = elementDocument.getElementById("aiDifficulty");
        l2.bQ.aiDifficulty = element.getValueAsInt(0);
        l2.bQ.save();
        this.guiEngine.b(true);
        this.guiEngine.c(false);
        String string2 = string;
        boolean bl2 = this.isMapSkirmish(string2);
        int n2 = elementDocument.getElementById("numberOfAIs").getValueAsInt(4);
        boolean bl3 = true;
        int n3 = elementDocument.getElementById("aiTeams").getValueAsInt(1);
        int n4 = n3 - 1;
        if (n3 == 5) {
            n4 = 0;
            bl3 = false;
        }
        ContextMenuActivity.a(string2, bl2, n2, n4, bl3, bl);  // 02b i.a(String,boolean,int,int,boolean,boolean)
    }

    /* 02b Root.java L689: 调 libRocket.a(...) 抛 IOException (R8 移除 throws) */
    public void showMapPopup(String string, String string2) throws IOException {
        boolean bl = false;
        ElementDocument elementDocument = this.libRocket.a("levelSelect.rml", (Object)string, "Map Select", bl);
        if (elementDocument != null) {
            elementDocument.setMetadata("mapClickFunction", string2);
            for (Element element : (java.util.Collection<Element>) (java.util.Collection) elementDocument.findElementsByClassName("noStyleInPopup")) {
                element.setAttribute("class", "");
            }
            if (this.showMapsWithDoc(elementDocument)) {
                GlobalState.e("showMapsWithDoc passed");
                this.libRocket.h();
                this.libRocket.a(elementDocument);
            }
        }
    }

    public void refreshAfterFileImport() {
        GlobalState.e("refreshAfterFileImport");
        GlobalState l2 = GlobalState.B();
        ArrayList arrayList = l2.bZ.k();
        this.libRocket.reloadDocument();
    }

    public boolean showMaps() {
        ElementDocument elementDocument = this.libRocket.f();
        return this.showMapsWithDoc(elementDocument);
    }

    public boolean showMapsWithDoc(ElementDocument elementDocument) {
        String[] stringArray;
        GlobalState l2 = GlobalState.B();
        GlobalState.e("showMaps");
        if (elementDocument == null) {
            GlobalState.e("showMaps: elementDocument==null");
            return false;
        }
        Element element = elementDocument.getElementById("mapTemplateHolder");
        Element element2 = elementDocument.getElementById("mapHolder");
        String string = element.getInnerRML();
        String string2 = "";
        String string3 = (String)elementDocument.getMetadata("mode");
        String string4 = (String)elementDocument.getMetadata("mapClickFunction");
        boolean bl = string3.equals("saves");
        boolean bl2 = string3.equals("replays");
        boolean bl3 = string3.startsWith("/SD/");
        if (bl) {
            stringArray = com.corrodinggames.rts.appFramework.ButtonActivity.l();
            if (stringArray == null) {
                l2.a("No saves", 1);
                return false;
            }
        } else if (bl2) {
            stringArray = q.l();
            if (!l2.bQ.saveMultiplayerReplays) {
                this.alert("Note: MultiplayerUI replay recordings are not turned on. You can enable them in the settings.");
            }
            if (stringArray == null) {
                if (l2.bQ.saveMultiplayerReplays) {
                    l2.a("No replays yet", 1);
                }
                return false;
            }
        } else {
            stringArray = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(string3, true);
            if ((stringArray = l2.bZ.a(stringArray, string3)) == null) {
                l2.a("Could not find folder: " + com.corrodinggames.rts.gameFramework.filesystem.FileLoader.e(string3), 1);
                return false;
            }
        }
        String string5 = string3 + "/";
        int n2 = 0;
        for (String string6 : stringArray) {
            String string7;
            String string8;
            String string9;
            String string10 = string;
            String string11 = this.convertMapName(string6);
            boolean bl4 = ContextMenuActivity.a(string6, string5 + string6);  // 02b i.a(String,String)
            String string12 = string11 + "";
            if (l2.ar && !bl4) {
                string12 = "[LOCKED] " + string12;
            }
            string10 = string10.replace("_NAME_", this.htmlString(string12));
            if (bl) {
                string9 = "loadGame(" + this.escapedString(string6) + ")";
                string8 = "loadGameEdit(" + this.escapedString(string6) + ")";
            } else if (bl2) {
                string9 = "loadReplay(" + this.escapedString(string6) + ")";
                string8 = "loadReplayEdit(" + this.escapedString(string6) + ")";
            } else {
                string9 = "open('levelOptions.rml', " + this.escapedString(string5 + string6) + ")";
                string8 = "";
            }
            if (string4 != null) {
                string9 = string4 + "(" + this.escapedString(string5 + string6) + ")";
                string8 = "";
            }
            string10 = string10.replace("_ONCLICK_", string9);
            string10 = string10.replace("_ONCLICKEDIT_", string8);
            String string13 = "thumbnail:assets:";
            int n3 = 18;
            if (bl3) {
                n3 = 2;  // 02b L805: var26=2; MainUIController$2 类名污染数字修正
            }
            if (n2 > n3) {
                string13 = "lazy:" + string13;
            }
            String string14 = string7 = com.corrodinggames.rts.appFramework.AndroidUIHelper.c(string5 + string6);
            if (GlobalState.aZ) {
                // empty if block
            }
            String string15 = string13 + string7;
            if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.i(string14)) {
                if (GlobalState.aZ) {
                    GlobalState.a("List: Failed to find: " + string14 + " after converting:" + string7 + " ( " + string15 + " )");
                }
                string15 = "drawable:error_missingmap.png";
            }
            if (bl || bl2) {
                string15 = "";
            }
            string10 = string10.replace("_IMG_", this.htmlString(string15));
            ++n2;
            string2 = string2 + string10;
        }
        element2.setInnerRML(string2);
        element2.loadCharsetIfNeeded(string2);
        if (bl || bl2) {
            element2.addClass("savesOnly");
        } else {
            element2.addClass("notSavesOnly");
        }
        if (bl3 && string4 == null && GlobalState.au()) {
            elementDocument.addClass("showImportButton");
        }
        return true;
    }

    public void convertTextOnPage() {
        GlobalState l2 = GlobalState.B();
        this.logDebug("convertTextOnPage");
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        if (this.isMobile()) {
            elementDocument.addClass("mobile");
        }
        if (this.libRocket.getHeight() < 800) {
            elementDocument.addClass("smallScreen");
        }
        convertTextStopwatch.a();
        ArrayList arrayList = elementDocument.getAllNestedChildren();
        for (Element element : (java.util.Collection<Element>) (java.util.Collection) arrayList) {
            String string;
            int n2 = element.getNumAttributes();
            for (int i2 = 0; i2 < n2; ++i2) {
                Object object;
                String string2 = element.getAttributeKey(i2);
                String string3 = element.getAttributeValue(i2);
                if (string2 == null) continue;
                if (string2.equals("nestedclone") && !string3.equalsIgnoreCase("false")) {
                    GlobalState.e("nested clone:" + element.getId());
                    element.setAttribute(string2, "false");
                    object = element.clone();
                    element.prependChild((Element)object);
                    ((Element)object).removeReference();
                    continue;
                }
                if (string2.equals("childclone") && !string3.equalsIgnoreCase("false")) {
                    element.setAttribute(string2, "false");
                    if (element.getNumChildren() < 1) {
                        GlobalState.e("child clone failed no children:" + element.getId());
                    }
                    object = element.getChild(0).clone();
                    ((Element)object).addClass("clone");
                    element.prependChild((Element)object);
                    ((Element)object).removeReference();
                    continue;
                }
                object = this.libRocket.d(string3);
                if (object == null) continue;
                GlobalState.e("convertTextOnPage:" + string2 + ": '" + string3 + "' to '" + (String)object + "'");
                if (string2.equals("_html")) {
                    GlobalState.e("setting html:" + string2);
                    element.setInnerRML((String)object);
                    continue;
                }
                if (string2.startsWith("_")) {
                    string2 = string2.substring("_".length());
                    GlobalState.e("converted key to:" + string2);
                }
                element.setAttribute(string2, (String)object);
            }
            if (!elementDocument.translatedToUnicode || !(string = element.getTagName()).equals("p") && !string.startsWith("h") && !string.startsWith("label") && !string.startsWith("button") && !string.startsWith("select")) continue;
            boolean bl = element.loadCharsetIfNeededWithCurrentText();
        }
        convertTextStopwatch.d();
    }

    public void keyBindingPopup_apply(boolean bl) {
        GlobalState l2 = GlobalState.B();
        ElementDocument elementDocument = this.libRocket.c();
        if (elementDocument == null) {
            this.logWarn("showKeyBindingActionPopup: popup==null");
            return;
        }
        String string = (String)elementDocument.getMetadata("mode");
        String[] stringArray = string.split(":");
        int n2 = Integer.parseInt(stringArray[0]);
        int n3 = Integer.parseInt(stringArray[1]);
        ArrayList arrayList = l2.bT.allBindings;  // 02b ac.al
        KeyBinding ad2 = (KeyBinding) arrayList.get(n2);
        if (!bl) {
            Object object = elementDocument.getMetadata("lastKeyPressed");
            if (object == null) {
                this.logWarn("keyBindingPopup_apply: no last key entered");
                return;
            }
            int n4 = (Integer)object;
            int n5 = 0;
            Object object2 = elementDocument.getMetadata("lastKeyModifiersPressed");
            if (object2 != null) {
                n5 = (Integer)object2;
            }
            ad2.a(n4, n3, n5, true);
        } else {
            int n6 = 0;
            ad2.a(0, n3, n6, true);
        }
        this.showKeyBinding();
        this.closePopup();
    }

    public void keyBindingPopup_onKeydown(int n2) {
        GlobalState l2 = GlobalState.B();
        ElementDocument elementDocument = this.libRocket.c();
        if (elementDocument == null) {
            this.logWarn("showKeyBindingActionPopup: popup==null");
            return;
        }
        Element element = elementDocument.getElementById("keyBindMessage");
        if (element == null) {
            this.logWarn("showKeyBindingActionPopup: keyBindMessage==null");
            return;
        }
        String string = "";
        int n3 = this.guiEngine.i();
        string = string + GlobalState.modifierMaskToString(n3);
        if (n2 == 111) {
            // empty if block
        }
        if (n2 == 0) {
            this.logWarn("keyBindingPopup_onKeydown: skipping keycode 0");
            return;
        }
        if (!l2.i(n2)) {
            elementDocument.setMetadata("lastKeyPressed", n2);
            elementDocument.setMetadata("lastKeyModifiersPressed", n3);
            string = string + SlickToAndroidKeycodes.a(n2);
            this.keyBindingPopup_apply(false);
            return;
        }
        String string2 = "Key: " + string;
        element.setText(string2);
    }

    public void showKeyBindingPopup() {
        GlobalState l2 = GlobalState.B();
        ElementDocument elementDocument = this.libRocket.c();
        if (elementDocument == null) {
            this.logWarn("showKeyBindingActionPopup: popup==null");
            return;
        }
        Element element = elementDocument.getElementById("keyBindMessage");
        if (element == null) {
            this.logWarn("showKeyBindingActionPopup: keyBindMessage==null");
            return;
        }
        String string = (String)elementDocument.getMetadata("mode");
        String[] stringArray = string.split(":");
        int n2 = Integer.parseInt(stringArray[0]);
        int n3 = Integer.parseInt(stringArray[1]);
        ArrayList arrayList = l2.bT.allBindings;  // 02b ac.al
        KeyBinding ad2 = (KeyBinding) arrayList.get(n2);
        String string2 = "Press LibRocketContext key..";
        element.setText(string2);
    }

    public String getKeyBindingAction(int n2, KeyBinding ad2, int n3) {
        String string = n2 + ":" + n3;
        return "createAndShowPopup('settingsKeyBindingSet.rml', " + this.escapedString(string) + ", " + this.escapedString(ad2.bindingName) + "); showKeyBindingPopup();";
    }

    public void backWarnIfOverlappingKeyBinding() {
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        boolean bl = (Boolean)elementDocument.getMetadata("hasOverlappingKeys", false);
        if (bl) {
            String string = "One or more keys are overlapping and have been highlighted in red. These can cause problems.";
            e e2 = new e("Ignore", new MainUIController$4(this));
            e e3 = new e("Fix", new MainUIController$5(this));
            boolean bl2 = false;
            this.showPopupWithButtons(null, string, bl2, e2, e3);
            return;
        }
        this.back();
    }

    public void showKeyBinding() {
        GlobalState l2 = GlobalState.B();
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        elementDocument.setMetadata("event_onkeydown", "keyBindingPopup_onKeydown");
        MainUIController$TableData root$TableData = new MainUIController$TableData();
        ArrayList arrayList = root$TableData.rows;
        ArrayList arrayList2 = l2.bT.allBindings;  // 02b ac.al
        boolean bl = false;
        for (int i2 = 0; i2 < arrayList2.size(); ++i2) {
            KeyBinding ad2 = (KeyBinding) arrayList2.get(i2);
            if (!ad2.isActive) continue;
            MainUIController$TableRow root$TableRow = new MainUIController$TableRow();
            root$TableRow.addCell(ad2.bindingName);
            if (ad2.d()) {
                root$TableRow.addClass("rowHeader");
            } else {
                for (int i3 = 0; i3 <= 1; ++i3) {
                    boolean bl2 = l2.bT.digitToKeycode(ad2, i3);  // 02b ac.a(ad,int)
                    MainUIController$TableCell root$TableCell = root$TableRow.addCell(ad2.b(i3));
                    root$TableCell.setLibrocketOnClick(this.getKeyBindingAction(i2, ad2, i3));
                    if (!bl2) continue;
                    root$TableCell.color = -65536;
                    bl = true;
                }
            }
            arrayList.add(root$TableRow);
        }
        elementDocument.setMetadata("hasOverlappingKeys", bl);
        this.refreshTable("keysDiv", root$TableData);
    }

    public void loadSettings() {
        GlobalState l2 = GlobalState.B();
        loadSettingsStopwatch.a();
        this.logDebug("loadSettings");
        Element element = this.libRocket.getActiveElementById("body");
        ArrayList arrayList = element.getAllNestedChildren();
        for (Element element2 : (java.util.Collection<Element>) (java.util.Collection) arrayList) {
            String string = element2.getAttribute("data-settings");
            if (string == null) continue;
            String string2 = element2.getId();
            String string3 = element2.getAttribute("type", "unknown");
            String string4 = l2.bQ.getValueDynamic(string2);
            if (string3.equals("checkbox")) {
                if (Boolean.parseBoolean(string4)) {
                    element2.setAttribute("checked", "");
                    continue;
                }
                element2.setAttribute("checked", null);
                continue;
            }
            element2.setAttribute("value", string4);
        }
        loadSettingsStopwatch.d();
    }

    public void loadLeaderboard() {
        GlobalState l2 = GlobalState.B();
        this.logDebug("loadLeaderboard");
        Element element = this.libRocket.getActiveElementById("leaderboardType");
        Element element2 = this.libRocket.getActiveElementById("leaderboardGrouping");
        if (element == null || element2 == null) {
            GlobalState.a("loadLeaderboard: Failed to find elements. (For page: " + this.libRocket.getActiveDocumentPath() + ")");
            return;
        }
        element.setAttribute("value", l2.cg.e().name());
        element2.setAttribute("value", l2.cg.f().name());
    }

    public void saveLeaderboard() {
        GlobalState l2 = GlobalState.B();
        DataFieldProvider f2 = DataFieldProvider.a;  // 02b g.f.a (Root L1114)
        DataFieldInt c2 = DataFieldInt.a;  // 02b g.c.a (Root L1115); NetworkPlayer 幻觉名修正
        this.logDebug("saveLeaderboard");
        Element element = this.libRocket.getActiveElementById("leaderboardType");
        Element element2 = this.libRocket.getActiveElementById("leaderboardGrouping");
        if (element == null || element2 == null) {
            GlobalState.a("saveLeaderboard: Failed to find elements. (For page: " + this.libRocket.getActiveDocumentPath() + ")");
            return;
        }
        f2 = DataFieldProvider.valueOf(element.getAttribute("value"));
        c2 = DataFieldInt.valueOf(element2.getAttribute("value"));
        l2.a(f2, c2);
    }

    public void applyResolution() {
        this.guiEngine.g();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updateRenderScaleInSettings(boolean bl) {
        Element element;
        GlobalState l2 = GlobalState.B();
        Element element2 = this.libRocket.getActiveElementById("uiRenderScale");
        Element element3 = this.libRocket.getActiveElementById("renderDensity");
        Float f2 = null;
        Float f3 = null;
        if (element2 == null) {
            this.logDebug("updateRenderScaleInSettings: uiRenderScale==null");
        } else {
            f2 = element2.getValueAsFloat(Float.valueOf(1.0f));
            element = this.libRocket.getActiveElementById("uiRenderScaleDisplay");
            element.compareAndSetText("x" + com.corrodinggames.rts.gameFramework.GameUtils.b((double)(f2.floatValue() + 0.01f), 1));
        }
        if (element3 == null) {
            this.logDebug("updateRenderScaleInSettings: renderDensity==null");
        } else {
            f3 = element3.getValueAsFloat(Float.valueOf(1.0f));
            element = this.libRocket.getActiveElementById("renderDensityDisplay");
            element.compareAndSetText("x" + com.corrodinggames.rts.gameFramework.GameUtils.b((double)(f3.floatValue() + 0.01f), 1));
        }
        if (bl) {
            float f4 = l2.bQ.uiRenderScale;
            if (f2 != null) {
                l2.bQ.uiRenderScale = f2.floatValue();
            }
            float f5 = l2.bQ.renderDensity;
            if (f3 != null) {
                l2.bQ.renderDensity = f3.floatValue();
            }
            try {
                this.applyResolution();
            }
            finally {
                l2.bQ.uiRenderScale = f4;
                l2.bQ.renderDensity = f5;
            }
        }
    }

    public void closeSettings() {
        this.applyResolution();
    }

    public void saveSettings() {
        GlobalState l2 = GlobalState.B();
        this.logDebug("saveSettings");
        Element element = this.libRocket.getActiveElementById("body");
        ArrayList arrayList = element.getAllNestedChildren();
        for (Element element2 : (java.util.Collection<Element>) (java.util.Collection) arrayList) {
            String string;
            String string2 = element2.getAttribute("data-settings");
            if (string2 == null) continue;
            String string3 = element2.getId();
            String string4 = element2.getAttribute("type", "unknown");
            String string5 = null;
            string5 = string4.equals("checkbox") ? ((string = element2.getAttribute("checked")) == null || "false".equals(string) ? "false" : "true") : element2.getAttribute("value");
            try {
                l2.bQ.setValueDynamic(string3, string5);
            }
            catch (NumberFormatException numberFormatException) {
                this.alert("Error:" + numberFormatException.getMessage());
            }
        }
        this.guiEngine.g();
        FileWatcher.a();  // 02b gameFramework/j.java L37: static a() (FileChangeEngine)
        com.corrodinggames.rts.gameFramework.steam.Localization.e();
        this.guiEngine.h();
    }

    public String hideStyle(boolean bl) {
        if (bl) {
            return "";
        }
        return "display:none;";
    }

    public String hideIf(boolean bl) {
        return this.hideClass(!bl);
    }

    public String hideUnless(boolean bl) {
        return this.hideClass(bl);
    }

    public String hideClass(boolean bl) {
        if (bl) {
            return "";
        }
        return "hide";
    }

    public String hideIfMobile() {
        if (GlobalState.au()) {
            return "hide";
        }
        return "";
    }

    public boolean canResume() {
        GlobalState l2 = GlobalState.B();
        return l2 != null && l2.bG && !l2.bH;
    }

    public boolean isMobile() {
        return GlobalState.au();
    }

    public boolean isIOS() {
        return GlobalState.aZ;
    }

    public boolean isDesktop() {
        return GlobalState.av();
    }

    public boolean isMac() {
        return com.corrodinggames.rts.game.GameEngine.c;  // 02b game/i.c (Root L1257 isMac); screens 幻觉包修正
    }

    public boolean hasModSupport() {
        return !GlobalState.aZ;
    }

    public boolean usingMods() {
        if (GlobalState.aZ) {
            GlobalState l2 = GlobalState.B();
            return l2.bZ.c() > 0;
        }
        return true;
    }

    public boolean hasWorkshopSupport() {
        return GlobalState.av();
    }

    public boolean hasReloadSupport() {
        return !GlobalState.aZ;
    }

    String restrictedString(String string) {
        if (string == null) {
            return null;
        }
        string = string.replace("'", ".");
        string = string.replace("\"", ".");
        string = string.replace("(", ".");
        string = string.replace(")", ".");
        string = string.replace(",", ".");
        string = string.replace("<", ".");
        string = string.replace(">", ".");
        return "'" + string + "'";
    }

    String escapedString(String string) {
        string = string.replace("&", "&amp;");
        string = string.replace("<", "&lt;");
        string = string.replace(">", "&gt;");
        string = string.replace("'", "&apos;");
        string = string.replace("\"", "&quot;");
        string = string.replace("${", "$ {");
        return "'" + string + "'";
    }

    String htmlString(String string) {
        string = string.replace("&", "&amp;");
        string = string.replace("<", "&lt;");
        string = string.replace(">", "&gt;");
        string = string.replace("\"", "&quot;");
        string = string.replace("${", "$ {");
        return "" + string + "";
    }

    String htmlStringWithNewlines(String string) {
        string = this.htmlString(string);
        string = string.replace("\n", "<br/>");
        return "" + string + "";
    }

    public void checkServerListScroll() {
        Element element = this.libRocket.getActiveElementById("serverScrollDiv");
        if (element == null) {
            this.logWarn("serverScrollDiv==null");
            return;
        }
        Boolean bl = (Boolean)this.libRocket.b("showFullServerList");
        if (bl == null) {
            bl = false;
        }
        if (!bl.booleanValue() && element.getScrollTop() > 200.0f) {
            this.libRocket.getActiveDocument().setMetadata("showFullServerList", true);
            this.scriptEngine.addScriptToQueue("displayServerList()");
        }
    }

    public void refreshServerList() {
        this.refreshServerListRaw("serverListData", "serverRowTemplateHolder", "refreshButton");
    }

    public void displayServerList() {
        this.displayServerListRaw("serverListData", "serverRowTemplateHolder", "refreshButton");
    }

    public void refreshServerListRaw(String string, String string2, String string3) {
        Object object;
        GlobalState l2 = GlobalState.B();
        if (string3 != null) {
            object = this.libRocket.getActiveElementById(string3);
            ((Element)object).setText("Refreshing");
        }
        object = new MainUIController$6(this, string, string2, string3);
        l2.bX.bh = null;
        WebAPIClient.a((Runnable)object);  // 02b j/n.a(Runnable) L314
    }

    public void displayServerListRaw(String string, String string2, String string3) {
        Boolean bl;
        GlobalState l2 = GlobalState.B();
        Element element = this.libRocket.getActiveElementById(string);
        Element element2 = this.libRocket.getActiveElementById(string2);
        if (element == null) {
            GlobalState.b("serverListData is null, we may have changed page");
            return;
        }
        Element element3 = element;
        ArrayList<GameServerInfo> arrayList = p.m();
        this.lastSortedDiscoveredServers = arrayList;
        String string4 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.lobby.gameState.battleroom", new Object[0]);
        String string5 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.lobby.gameState.ingame", new Object[0]);
        String string6 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.lobby.gameState.chat", new Object[0]);
        if (element3.getNumChildren() > arrayList.size()) {
            for (int i2 = element3.getNumChildren() - 1; i2 >= arrayList.size(); --i2) {
                GlobalState.e("removing rowIndex:" + i2);
                element3.removeChild(element3.getChild(i2));
            }
            if (element3.getNumChildren() != arrayList.size()) {
                GlobalState.b("-- Non matching size after clean up:" + element3.getNumChildren() + " vs " + arrayList.size());
            }
        }
        if ((bl = (Boolean)this.libRocket.b("showFullServerList")) == null) {
            bl = false;
        }
        int n2 = 0;
        int n3 = 50;
        if (!bl.booleanValue() && arrayList.size() > n3) {
            ArrayList<GameServerInfo> arrayList2 = new ArrayList<GameServerInfo>();
            for (GameServerInfo object2 : arrayList) {
                arrayList2.add(object2);
                if (arrayList2.size() <= n3) continue;
                break;
            }
            n2 = arrayList.size() - arrayList2.size();
            arrayList = arrayList2;
        }
        Object object3 = null;
        int n4 = 0;
        Iterator iterator4 = arrayList.iterator();  // 02b Root L1390: Iterator var16
        while (iterator4.hasNext()) {
            String string7;
            GameServerInfo element7 = (GameServerInfo) iterator4.next();
            Element element4 = null;
            if (n4 < element3.getNumChildren()) {
                element4 = element3.getChild(n4);
            }
            if (element4 != null && element4.hasClassName("serverRowMessage")) {
                GlobalState.e("removing non rowIndex:" + n4);
                element3.removeChild(element4);
                element4 = null;
            }
            if (element4 != null && element4.findByClassName("rState") == null) {
                GlobalState.e("removing non rowIndex with no rState:" + n4);
                element3.removeChild(element4);
                element4 = null;
            }
            if (element4 == null) {
                element4 = element2.clone();
                element3.appendChild(element4);
                element4.removeReference();
                element4.setAttribute("onclick", "clickedServerRow(" + n4 + ")");
            }
            if ((string7 = element7.s) != null) {
                string7 = string7.replace("battleroom", string4);
                string7 = string7.replace("ingame", string5);
                string7 = string7.replace("chat", string6);
            }
            boolean bl2 = false;
            boolean bl3 = false;
            boolean bl4 = false;
            boolean bl5 = false;
            if (element7 != null && element7.x) {
                bl2 = true;
                if ("chat".equalsIgnoreCase(element7.s)) {
                    bl3 = true;
                }
                if (element7.isEnabled2()) {  // 02b j/g.d(): e != null
                    bl5 = true;
                }
            }
            int n5 = Color.a(255, 255, 255, 255);
            String string8 = "serverRow serverRowData realServerRow ";
            boolean bl6 = false;
            if (element7 != null) {
                if (bl2) {
                    string8 = string8 + "dedicatedServerRow ";
                    if (bl3 || bl5) {
                        n5 = Color.a(255, 152, 236, 249);
                        string8 = string8 + "chatRow ";
                    }
                } else {
                    if (element7.h) {
                        n5 = Color.a(255, 240, 240, 240);
                        string8 = string8 + "openRow ";
                    }
                    if (element7.a) {
                        n5 = Color.a(255, 229, 149, 35);
                        string8 = string8 + "lanRow ";
                    }
                }
                if (element7.a()) {
                    string8 = string8 + "lastConnectedRow ";
                }
                if (!(bl3 || bl5 || ("" + l2.c(true)).equals(element7.j))) {
                    bl4 = true;
                }
            }
            String string9 = "";
            string9 = string9 + "color:" + com.corrodinggames.rts.gameFramework.GameUtils.h(n5) + ";";
            if (bl6) {
                string9 = string9 + "font-weight: bold;";
                string8 = string8 + "boldRow ";
            }
            element4.compareAndSetClassNames(string8);
            element4.findByClassName("rState").compareAndSetText(string7);
            String string10 = com.corrodinggames.rts.gameFramework.GameUtils.a(element7.n, 15);
            element4.findByClassName("rHost").compareAndSetText(string10);
            String string11 = element7.t == "?" ? "?" : element7.t + "\\" + element7.u;
            element4.findByClassName("rPlayers").compareAndSetText(com.corrodinggames.rts.gameFramework.GameUtils.a(string11, 15));
            String string12 = com.corrodinggames.rts.gameFramework.GameUtils.a(ContextMenuActivity.getString2(element7.q), 40);  // 02b Root L1508: i.e(var17.q)
            if (string12 == null) {
                string12 = "";
            }
            element4.findByClassName("rMap").compareAndSetText(string12);
            String string13 = "ANY".equalsIgnoreCase(element7.k) ? element7.k : "v" + com.corrodinggames.rts.gameFramework.GameUtils.a(element7.k, 8);  // 02b Root L1518: (int)8; $8 类名污染数字修正
            Element element5 = element4.findByClassName("rVersion");
            element5.compareAndSetText(string13);
            String string14 = "";
            String string15 = "cell rVersion ";
            if (bl4) {
                string14 = "color:" + com.corrodinggames.rts.gameFramework.GameUtils.h(Color.a(255, 155, 147, 147)) + ";";
                string15 = string15 + "nonMatchingRow ";
            } else {
                string14 = "color:" + com.corrodinggames.rts.gameFramework.GameUtils.h(n5) + ";";
            }
            element5.compareAndSetClassNames(string15);
            String string16 = "";
            string16 = element7.h ? (element7.m ? "P" : "Y") : "N";
            if (element7.a) {
                string16 = "L";
            }
            Element element6 = element4.findByClassName("rOpen");
            element6.compareAndSetText(string16);
            String string17 = "";
            String string18 = "cell rOpen ";
            if (!element7.h && !element7.a) {
                string17 = "color:" + com.corrodinggames.rts.gameFramework.GameUtils.h(Color.a(255, 155, 147, 147)) + ";";
                string18 = string18 + "notOpenRow ";
            } else {
                string17 = "color:" + com.corrodinggames.rts.gameFramework.GameUtils.h(n5) + ";";
            }
            element6.compareAndSetClassNames(string18);
            ++n4;
        }
        if (arrayList.size() == 0 && l2.bX.bh != null) {
            object3 = "ERROR: " + l2.bX.bh;
            Object var17_22 = null;
            Element element7 = element2.clone();
            element3.appendChild(element7);
            element7.removeReference();
            element7.setText((String)object3);
        }
        if ((object3 = this.libRocket.getActiveElementById("padding")) == null && n2 > 0) {
            object3 = element2.clone();
            element3.appendChild((Element)object3);
            ((Element)object3).removeReference();
            ((Element)object3).setAttribute("id", "padding");
            ((Element)object3).addClass("serverRowMessage");
        }
        if (object3 != null && n2 > 0) {
            int n6 = 18;
            ((Element)object3).setStyle("height:" + n6 * n2 + "px;");
        }
        if (string3 != null) {
            Element element8 = this.libRocket.getActiveElementById(string3);
            element8.setText("Refresh");
        }
        GlobalState.b("DONE");
    }

    public void clickedServerRow(int n2) {
        GameServerInfo g2 = (GameServerInfo) this.lastSortedDiscoveredServers.get(n2);
        this.clickedServer(g2.b);
    }

    public void clickedServer(String string) {
        String string2;
        GameServerInfo g2;
        if (this.getAlertOrPopup() != null) {
            this.logWarn("clickedServer: getAlertOrPopup!=null");
            return;
        }
        try {
            g2 = WebAPIClient.b(string);  // 02b j/n.b(String) L276
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        if (g2 == null) {
            this.logWarn("clickedServer: server==null");
            this.alert("That server no longer exists");
            return;
        }
        String string3 = g2.b();
        String string4 = "Join Server?";
        if (g2.isEnabled2()) {  // 02b g.d()
            string2 = "[onenter]Open Link:";
            string2 = string2 + "closePopup(); openWhitelistedLink(" + this.restrictedString(g2.c()) + ");";
        } else if (!g2.a) {
            string2 = "[onenter]Join:";
            string2 = string2 + "closePopup(); joinServerWithId(" + this.restrictedString(g2.e()) + "," + this.restrictedString(g2.b) + ");";
        } else {
            string2 = "[onenter]Join over LAN:";
            string2 = string2 + "closePopup(); joinServerWithId(" + this.restrictedString(g2.f()) + "," + this.restrictedString(g2.b) + ");";
        }
        boolean bl = true;
        this.showPopup(null, string3, bl, string2, null);
    }

    public void hideKeyboard() {
        this.guiEngine.m();
    }

    public void saveGame(String string) {
        this.closePopup();
        this.hideKeyboard();
        string = string.replace(".", "_");
        string = string.replace("/", "_");
        string = string.replace("\\", "_");
        GlobalState l2 = GlobalState.B();
        l2.ca.b(string, false);
    }

    public void exportMap(String string) {
        this.closePopup();
        GlobalState l2 = GlobalState.B();
        string = string.replace(".", "_");
        string = string.replace("/", "_");
        string = string.replace("\\", "_");
        string = string.replace("|", "_");
        string = string.replace("?", "_");
        try {
            l2.bL.b(l2.dl, "/SD/rusted_warfare_maps/" + string + ".tmx");
        }
        catch (com.corrodinggames.rts.game.map.MapException f2) {
            this.showAlert("Failed to export map. error: " + f2.getMessage());
            return;
        }
        catch (IOException iOException) {
            this.showAlert("Failed to export map. IO error: " + iOException.getMessage());
            return;
        }
        this.showAlert("Map exported");
    }

    public void loadGame(String string) {
        GlobalState l2 = GlobalState.B();
        l2.bX.m("loading new save");
        l2.bv = false;
        if (l2.ca.c(string, false)) {
            this.resumeNonMenu();
        }
    }

    public void loadGameEdit(String string) {
        GlobalState l2 = GlobalState.B();
        String string2 = string;
        e e2 = null;
        if (com.corrodinggames.rts.gameFramework.core.PlatformExtension.b()) {  // 02b l/a.b()
            e2 = new e("Share", new MainUIController$7(this, l2, string));
        }
        e e3 = new e("Delete", new MainUIController$8(this, l2, string));
        boolean bl = true;
        this.showPopupWithButtons(null, string2, bl, e2, e3);
    }

    public void loadReplay(String string) {
        GlobalState l2 = GlobalState.B();
        l2.bv = false;
        if (l2.cb.c(string)) {
            this.resumeNonMenu();
        }
    }

    public void loadReplayEdit(String string) {
        GlobalState l2 = GlobalState.B();
        String string2 = string;
        e e2 = null;
        if (com.corrodinggames.rts.gameFramework.core.PlatformExtension.b()) {  // 02b l/a.b()
            e2 = new e("Share", new MainUIController$9(this, l2, string));
        }
        e e3 = new e("Delete", new MainUIController$10(this, l2, string));
        boolean bl = true;
        this.showPopupWithButtons(null, string2, bl, e2, e3);
    }

    public void makeSaveGamePopup(String string) {
        String string2;
        GlobalState l2 = GlobalState.B();
        String string3 = "Save Game";
        String string4 = "Enter LibRocketContext name to save the game under";
        if (string == null) {
            string2 = l2.getDisplayMapName() + " (" + com.corrodinggames.rts.gameFramework.GameUtils.a("d MMM yyyy HH-mm-ss") + ")";
            string2 = string2.replace("  ", " ");
        } else {
            string2 = string;
        }
        this.showInputPopup(string3, string4, string2, "[onenter]Save:saveGame(getPopupText())", null);
    }

    public void makeExportMapGamePopup(String string) {
        String string2;
        GlobalState l2 = GlobalState.B();
        String string3 = "Export Map";
        String string4 = "Enter LibRocketContext name to export the map as";
        if (string == null) {
            string2 = "New " + l2.getDisplayMapName() + " - " + com.corrodinggames.rts.gameFramework.GameUtils.a("d MMM yyyy");
            string2 = string2.replace("  ", " ");
        } else {
            string2 = string;
        }
        this.showInputPopup(string3, string4, string2, "[onenter]Export:exportMap(getPopupText())", null);
    }

    public void makeSendMessagePopup() {
        GlobalState l2 = GlobalState.B();
        String string = "Send Message";
        String string2 = "[onenter]Send: sendChatMessage(getPopupText()); closePopup();";
        String string3 = "Switch - Team only: makeSendTeamMessagePopupWithDefaultText(getPopupText()); ";
        String string4 = "";
        this.showInputPopup(string, string4, "", string2, string3);
    }

    public void makeSendTeamMessagePopup() {
        this.makeSendTeamMessagePopupWithDefaultText("");
    }

    public void makeSendTeamMessagePopupWithDefaultText(String string) {
        GlobalState l2 = GlobalState.B();
        String string2 = "Send Team Message";
        String string3 = "+ Ping Map:sendTeamChatMessageAndPing(getPopupText()); closePopup();";
        String string4 = "";
        this.showInputPopup(string2, string4, string, "[onenter]Send Team:sendTeamChatMessage(getPopupText()); closePopup();", string3);
    }

    public void sendChatMessage(String string) {
        GlobalState l2 = GlobalState.B();
        this.guiEngine.m();
        if (string == null || string.trim().equals("")) {
            return;
        }
        l2.bX.m(string);
        l2.bS.u = false;
    }

    public void sendTeamChatMessageAndPing(String string) {
        this.sendTeamChatMessage(string);
        GlobalState l2 = GlobalState.B();
        l2.bS.setPingAction();
    }

    public void sendTeamChatMessage(String string) {
        GlobalState l2 = GlobalState.B();
        this.guiEngine.m();
        if (string == null || string.trim().equals("")) {
            return;
        }
        l2.bX.prepareChatMessage(string);
    }

    public void receiveChatMessage(int n2, String string, String string2, com.corrodinggames.rts.gameFramework.network.PacketDecoder c2) {
        this.refreshChat();
    }

    public void refreshChat() {
        Object object;
        GlobalState l2 = GlobalState.B();
        if (this.libRocket.getActiveDocument() == null) {
            return;
        }
        Element element = this.libRocket.getActiveElementById("chatLogHistory");
        if (element == null) {
            return;
        }
        boolean bl = element.getAttributeBoolean("reversed", false);
        if (l2.bX.F && (object = this.libRocket.getActiveElementById("chatBox")) != null) {
            ((Element)object).hide();
        }
        element.setInnerRML("");
        object = l2.bX.packetBuffer.b();  // 02b aC.b() (ChatSystem)
        StringBuffer stringBuffer = new StringBuffer();
        Iterator object2 = ((ConcurrentLinkedQueue)object).iterator();
        while (object2.hasNext()) {
            com.corrodinggames.rts.gameFramework.network.ChatMessage b2 = (com.corrodinggames.rts.gameFramework.network.ChatMessage)object2.next();
            if (bl) {
                stringBuffer.insert(0, "<div>" + b2.b() + "</div>");
                continue;
            }
            stringBuffer.append("<div>" + b2.b() + "</div>");
        }
        stringBuffer.append("<div id='chatLastRowSpacer'></div>");
        element.setInnerRML(stringBuffer.toString());
        element.loadCharsetIfNeededWithCurrentText();
        Element element10 = this.libRocket.getActiveElementById("chatLastRowSpacer");  // 02b: 独立变量 (Iterator object2 复用冲突)
        if (element10 != null) {
            element10.scrollIntoView(false);
        }
    }

    public void trace(String string) {
        GlobalState.e("Trace:" + string);
    }

    public void updateTableTextOnly(String string, MainUIController$TableData root$TableData, MainUIController$TableData root$TableData2) {
        ArrayList arrayList = root$TableData.rows;
        Element element = this.libRocket.getActiveElementById(string);
        if (element == null) {
            GlobalState.b("updateTableText: tableElement:" + string + " is null, we may have changed page");
            return;
        }
        Element element2 = element.getElementById("tableListData");
        for (int i2 = 0; i2 < arrayList.size(); ++i2) {
            MainUIController$TableRow root$TableRow = (MainUIController$TableRow)arrayList.get(i2);
            for (int i3 = 0; i3 < root$TableRow.tableCells.size(); ++i3) {
                MainUIController$TableCell root$TableCell = (MainUIController$TableCell)root$TableRow.tableCells.get(i3);
                Element element3 = element2.getChild(i2);
                if (element3 == null) {
                    GlobalState.b("updateTableText failed to get row " + i2);
                    return;
                }
                Element element4 = element3.getChild(i3);
                if (element4 == null) {
                    GlobalState.b("updateTableText failed to get cell " + i3);
                    return;
                }
                element4.compareAndSetText(root$TableCell.text);
            }
        }
    }

    public void refreshTable(String string, MainUIController$TableData root$TableData) {
        ArrayList arrayList = root$TableData.rows;
        Element element = this.libRocket.getActiveElementById(string);
        if (element == null) {
            GlobalState.b("refreshTable: tableElement:" + string + " is null, we may have changed page");
            return;
        }
        Element element2 = element.getElementById("tableRowTemplateHolder");
        Element element3 = element.getElementById("tableListData");
        Element element4 = element2.findByClassName("rowTemplate").getChild(0);
        Element element5 = element2.findByClassName("cellTemplate").getChild(0);
        element3.setInnerRML("");
        Iterator iterator2 = arrayList.iterator();  // 02b Root L1878: Iterator var9 (raw 集合显式迭代)
        while (iterator2.hasNext()) {
            MainUIController$TableRow root$TableRow = (MainUIController$TableRow)iterator2.next();
            Element element6 = element4.cloneAndFix();
            if (root$TableRow.librocketOnClick != null) {
                element6.setAttribute("onclick", root$TableRow.librocketOnClick);
            }
            if (root$TableRow.extraClasses != null) {
                element6.addClass(root$TableRow.extraClasses);
            }
            Iterator iterator3 = root$TableRow.tableCells.iterator();  // 02b L1891: Iterator var12
            while (iterator3.hasNext()) {
                MainUIController$TableCell root$TableCell = (MainUIController$TableCell)iterator3.next();
                Element element7 = element5.cloneAndFix();
                element7.compareAndSetText(root$TableCell.text);
                if (root$TableCell.librocketOnClick != null) {
                    element7.setAttribute("onclick", root$TableCell.librocketOnClick);
                    element7.addClass("clickablecell");
                }
                if (root$TableCell.classes != null) {
                    element7.addClass(root$TableCell.classes);
                }
                if (root$TableCell.color != null) {
                    element7.setAttribute("style", "color:" + com.corrodinggames.rts.gameFramework.GameUtils.h(root$TableCell.color) + ";");
                }
                element6.appendChild(element7);
                element7.removeReference();
            }
            element3.appendChild(element6);
            element6.removeReference();
        }
    }

    /* 02b Root.java L1921: 调 libRocket.a(...) 抛 IOException */
    public ElementDocument createAndShowPopup(String string, Object object, String string2) throws IOException {
        return this.libRocket.a(string, object, string2, true);
    }

    /* 02b Root.java L1925: 调 libRocket.a(...) 抛 IOException */
    public ElementDocument createPopupHidden(String string, Object object, String string2) throws IOException {
        return this.libRocket.a(string, object, string2, false);
    }

    public boolean tryToShowPopupDocument(ElementDocument elementDocument) {
        return this.libRocket.b(elementDocument);
    }

    public void showMainMenu() {
        GlobalState.B().bS.u = false;
        LibRocketContext.a().b();  // 02b librocket/a.a().b()
    }

    public void onEnter() {
        ElementDocument elementDocument = this.libRocket.g();
        if (elementDocument == null) {
            GlobalState.e("onEnter: elementDocument==null");
            return;
        }
        ArrayList arrayList = elementDocument.getAllNestedChildren();
        for (Element element : (java.util.Collection<Element>) (java.util.Collection) arrayList) {
            String string = element.getAttribute("onenter");
            if (string == null || !element.isFocused()) continue;
            this.scriptEngine.processScript(string);
        }
    }

    public void scrollFromFocusedElement(float f2) {
        ElementDocument elementDocument = this.libRocket.g();
        if (elementDocument == null) {
            GlobalState.e("onEnter: elementDocument==null");
            return;
        }
        Element element = elementDocument.getTopLevelFocusedElement();
        if (element == null) {
            GlobalState.e("focusedElement: Not found");
            return;
        }
        ArrayList arrayList = elementDocument.getChainFromChildElement(element);
        if (arrayList == null) {
            GlobalState.e("scrollFromFocusedElement: Failed to find chain");
            return;
        }
        for (Element element2 : (java.util.Collection<Element>) (java.util.Collection) arrayList) {
            boolean bl = false;
            if ("scrollDiv".equals(element2.getId())) {
                bl = true;
            }
            if (element2.hasClassName("slider")) {
                bl = true;
            }
            if (!bl) continue;
            float f3 = element2.getScrollTop();
            element2.setScrollTop(f3 += f2);
            return;
        }
        GlobalState.e("Found no slider element to offset");
    }

    public boolean isSliderOrUIElementSelected() {
        ElementDocument elementDocument = this.libRocket.g();
        if (elementDocument == null) {
            GlobalState.e("onEnter: elementDocument==null");
            return false;
        }
        Element element = elementDocument.getTopLevelFocusedElement();
        if (element != null) {
            Element element2 = element;
            String string = element2.getTagName();
            boolean bl = false;
            if ("scrollDiv".equals(element2.getId())) {
                bl = true;
            }
            if (element2.hasClassName("slider")) {
                bl = true;
            }
            if ("input".equals(string) && "range".equals(element2.getAttribute("type", "text"))) {
                bl = true;
            }
            if (bl) {
                GlobalState.e("Slider element: true");
                return true;
            }
            GlobalState.e("Slider element: false");
        }
        GlobalState.e("Slider element: no element focused");
        return false;
    }

    public void onTouch() {
        ElementDocument elementDocument = this.libRocket.g();
        if (elementDocument == null) {
            GlobalState.e("onEnter: elementDocument==null");
            return;
        }
        ArrayList arrayList = elementDocument.getAllNestedChildren();
        for (Element element : (java.util.Collection<Element>) (java.util.Collection) arrayList) {
            String string = element.getAttribute("type");
            if (!"text".equals(string) || !element.isFocused()) continue;
            this.guiEngine.l();
        }
    }

    public void onEscape() {
        ElementDocument elementDocument = this.libRocket.g();
        if (elementDocument == null) {
            GlobalState.e("onEscape: elementDocument==null");
            return;
        }
        boolean bl = false;
        ArrayList arrayList = elementDocument.getAllNestedChildren();
        for (Element element : (java.util.Collection<Element>) (java.util.Collection) arrayList) {
            String string = element.getAttribute("click_on_escape");
            if (string == null) continue;
            element.click();
            bl = true;
            break;
        }
        if (bl) {
            return;
        }
        if (this.closePopup()) {
            return;
        }
    }

    public void askQuitGame() {
        this.closePopup();
        String string = "Are you sure you want to quit?";
        String string2 = "";
        String string3 = "[onenter]Quit:";
        string3 = string3 + "closePopup(); exit();";
        boolean bl = true;
        this.showPopup(string, string2, bl, string3, null);
    }

    public String getCurrentDocumentPath() {
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        if (elementDocument == null) {
            return null;
        }
        return elementDocument.documentPath;
    }

    public String getCurrentPopupPath() {
        ElementDocument elementDocument = this.libRocket.c();
        if (elementDocument == null) {
            return null;
        }
        return elementDocument.documentPath;
    }

    public String getCreditsText() {
        return "Credits goes here";
    }

    public void runRunnable(Runnable runnable) {
        this.logDebug("runRunnable");
        if (runnable == null) {
            this.logDebug("runnable==null");
        }
        runnable.run();
    }

    public boolean isLinux() {
        return com.corrodinggames.rts.gameFramework.FileSystem.a() == com.corrodinggames.rts.gameFramework.OSEnum.c;  // 02b g.a()==h.c
    }

    public boolean not(boolean bl) {
        return !bl;
    }

    public boolean and(boolean bl, boolean bl2) {
        return bl && bl2;
    }

    public boolean or(boolean bl, boolean bl2) {
        return bl || bl2;
    }

    public void showBattleroom() {
        String string = "battleroom.rml";
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        boolean bl = true;
        if (elementDocument != null && string.equals(elementDocument.documentPath)) {
            GlobalState.e("Already on battleroom page");
            bl = false;
        }
        this.libRocket.setDocument(string, null, bl);
    }

    public void setDocument(String string) {
        this.libRocket.setDocument(string);
    }

    public void playNextMusicTrack() {
        GlobalState.B().bN.e();
    }

    public void toggleMusic() {
        GlobalState.B().bN.u = !GlobalState.B().bN.u;
    }

    public void updateMusicButton(String string) {
        Element element = this.libRocket.getActiveElementById(string);
        if (element != null) {
            if (GlobalState.B().bN.u) {
                element.setText(">");
            } else {
                element.setText("||");
            }
        }
    }

    public void setSandboxMapFromPopup(String string) {
        GlobalState l2 = GlobalState.B();
        this.closePopup();
        String string2 = string;
        this.libRocket.getActiveDocument().setMetadata("mode", string);
        this.showLevelOptions();
        this.libRocket.getActiveDocument().findByClassName("mapImage").setAttribute("src", this.getMapThumbnail(string2));
        this.libRocket.getActiveDocument().findByClassName("mapText").setText(this.getMapNameFromPath(string2));
    }

    /* 02b Root.java 对应: 调 showSandboxMapSelect 抛 IOException */
    public void showSandboxMapSelectOnChange() throws IOException {
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        Element element = elementDocument.getElementById("typeSelector");
        int n2 = Integer.parseInt(element.getValue());
        int n3 = (Integer)elementDocument.getMetadata("lastTypeSelector", 0);
        this.libRocket.getActiveDocument().setMetadata("lastTypeSelector", n2);
        if (n2 != n3) {
            this.showSandboxMapSelect();
        }
    }

    /* 02b Root.java 对应: 调 showMapPopup 抛 IOException */
    public void showSandboxMapSelect() throws IOException {
        String string = this.getModeMapPath(this.libRocket.getActiveDocument(), "typeSelector");
        this.showMapPopup(string, "setSandboxMapFromPopup");
    }

    public String getModeMapPath(Element element, String string) {
        int n2;
        GlobalState l2 = GlobalState.B();
        if (string == null) {
            if (l2.bX.ay.a == null) {
                GlobalState.b("getModeMapPath: currentType==0");
                n2 = 0;
            } else {
                n2 = l2.bX.ay.a.ordinal();
            }
        } else {
            Element element2 = element.getElementById(string);
            if (element2 == null) {
                GlobalState.isKeyJustPressed("getModeMapPath: typeSelector==null");
                n2 = 0;
            } else {
                n2 = element2.getValueAsInt(0);
            }
        }
        if (n2 == 0) {
            return "maps/skirmish";
        }
        if (n2 == 1) {
            return "/SD/rusted_warfare_maps";
        }
        if (n2 == 2) {  // $2 类名污染数字修正 (02b: typeIndex 2 = saves)
            return "saves";
        }
        throw new RuntimeException("Unknown typeIndex:" + n2);
    }

    public void event_unicodeEntered() {
        ElementDocument elementDocument = this.libRocket.g();
        if (elementDocument != null) {
            Element element = elementDocument.findByClassName("textinputUnicodeWrap");
            if (element != null) {
                element.compareAndAddClass("unicodeWasTyped");
            } else {
                GlobalState.e("event_unicodeEntered: missing textinput");
            }
        } else {
            GlobalState.e("event_unicodeEntered: missing document");
        }
    }

    public boolean isVersionBeta() {
        GlobalState l2 = GlobalState.B();
        return l2.n();
    }

    public Object ifCondition(boolean bl, Object object, Object object2) {
        return bl ? object : object2;
    }

    public String i(String string) {
        return com.corrodinggames.rts.gameFramework.steam.Localization.a(string, new Object[0]);
    }

    public void openLinkToCG(String string) {
        String string2 = "http://corrodinggames.com/" + string;
        this.openWhitelistedLink(string2);
    }

    public void openWhitelistedLink(String string) {
        GlobalState.e("Opening link:" + string);
        if (!(string.startsWith("http://corrodinggames.com/") || string.startsWith("https://corrodinggames.com/") || string.startsWith("http://corrodinggames.net/") || string.startsWith("https://corrodinggames.net/"))) {
            GlobalState.e("Not in whitelist");
            return;
        }
        if (this.guiEngine.b(string)) {
            this.alert("Opened link: " + string);
            return;
        }
        this.alert("Sorry couldn't load browser to: " + string + " please navigate manually");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void writeGameLog(String string) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean bl = false;
        LinkedList object = LibRocketContext.a().k();  // 02b librocket/a.a().k() L2269
        if (object == null) {
            bl = true;
        } else {
            LinkedList linkedList = object;
            synchronized (linkedList) {
                int n2 = com.corrodinggames.rts.gameFramework.GameUtils.b(0, ((LinkedList)object).size() - 3000);
                ListIterator listIterator = ((LinkedList)object).listIterator(n2);
                while (listIterator.hasNext()) {
                    stringBuffer.append(Element.excapeHTML((String)listIterator.next()));
                    stringBuffer.append("<br/>");
                }
            }
        }
        if (bl) {
            this.alert("Internal game logging not active");
            return;
        }
        GlobalState.e("writeGameLog ready");
        Element element9 = this.libRocket.getActiveElementById(string);  // 02b L2288: Element var10
        if (element9 == null) {
            GlobalState.e("Failed to find: " + string);
            return;
        }
        element9.setInnerRML(stringBuffer.toString());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void exportGameLog() {
        Object object;
        Object object2;
        StringBuffer stringBuffer = new StringBuffer();
        boolean bl = false;
        LinkedList object3 = LibRocketContext.a().k();  // 02b librocket/a.a().k() L2300
        if (object3 == null) {
            bl = true;
        } else {
            object2 = object3;
            synchronized (object2) {
                int n2 = com.corrodinggames.rts.gameFramework.GameUtils.b(0, ((LinkedList)object3).size() - 3000);
                ListIterator listIterator2 = ((LinkedList)object3).listIterator(n2);  // 02b L2306: ListIterator var6
                while (listIterator2.hasNext()) {
                    stringBuffer.append(Element.excapeHTML((String)listIterator2.next()));
                    stringBuffer.append("\n");
                }
            }
        }
        if (bl) {
            this.alert("Internal game logging not active");
            return;
        }
        try {
            String string17 = "/SD/rustedWarfare/RustedWarfareLog-" + com.corrodinggames.rts.gameFramework.GameUtils.a("d_MMM_yyyy_HH.mm.ss") + ".txt";  // 02b L2319
            object2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.e(string17);
            File file = new File((String)object2);
            object = new FileWriter(file);
            ((Writer)object).append(stringBuffer.toString());
            ((OutputStreamWriter)object).flush();
            ((OutputStreamWriter)object).close();
            com.corrodinggames.rts.gameFramework.core.PlatformExtension.a(file);  // 02b l/a.a(File)
            file.deleteOnExit();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            this.alert("Failed to export logs: " + exception.getMessage());
            return;
        }
    }

    public void setPageMinWidthAndHeight(float f2, float f3) {
        GlobalState.e("setPageMinWidthAndHeight(" + f2 + ", " + f3 + ")");
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        if (elementDocument == null) {
            GlobalState.e("setPageMinWidthAndHeight - no page");
            return;
        }
        elementDocument.setMetadataFloat("minWidth", Float.valueOf(f2));
        elementDocument.setMetadataFloat("minHeight", Float.valueOf(f3));
        this.guiEngine.n();
    }

    public void importFilePopup() {
        MainUIController$11 root$11 = new MainUIController$11(this);
        com.corrodinggames.rts.gameFramework.core.PlatformExtension.a(root$11);  // 02b l/a.a(FilePickerCallback b)
    }

    protected void setDocumentUpdate(ElementDocument elementDocument, Runnable runnable) {
        elementDocument.setMetadata("onUpdateFunction", runnable);
    }

    public void onFrameUpdate(float f2) {
        Object object;
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        if (elementDocument != null && (object = elementDocument.getMetadata("onUpdateFunction")) != null) {
            Runnable runnable = (Runnable)object;
            runnable.run();
        }
    }
}
