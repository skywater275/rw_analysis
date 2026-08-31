/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import android.graphics.Color;
import com.Element;
import com.ElementDocument;
import com.corrodinggames.librocket.a;
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
import com.corrodinggames.rts.appFramework.i;
import com.corrodinggames.rts.appFramework.p;
import com.corrodinggames.rts.appFramework.q;
import com.corrodinggames.rts.gameFramework.ad;
import com.corrodinggames.rts.gameFramework.bt;
import com.corrodinggames.rts.gameFramework.g.c;
import com.corrodinggames.rts.gameFramework.g.f;
import com.corrodinggames.rts.gameFramework.h;
import com.corrodinggames.rts.gameFramework.j;
import com.corrodinggames.rts.gameFramework.j.ah;
import com.corrodinggames.rts.gameFramework.j.ai;
import com.corrodinggames.rts.gameFramework.j.an;
import com.corrodinggames.rts.gameFramework.j.g;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Root
extends ScriptContext {
    public static final boolean DEBUG_TIMING = true;
    public Multiplayer multiplayer;
    public Mods mods;
    bt openDocumentTimer = new bt("openDocument", true);
    an threadedGameConnector;
    ElementDocument lastConnectingPopup;
    static bt convertTextStopwatch = new bt("ConvertText", true);
    static bt loadSettingsStopwatch = new bt("LoadSettings", true);
    ArrayList lastSortedDiscoveredServers;

    public void logDebug(String string) {
        l.e("ui[debug]: " + string);
    }

    public void logWarn(String string) {
        l.e("ui[warn]: " + string);
    }

    public void back() {
        this.libRocket.backToLastDocument();
        if (this.libRocket.getActiveDocument() == null) {
            l.b("back: libRocket.getActiveDocument()==null");
            l l2 = l.B();
            if (l2 == null || !l2.bq) {
                l.b("back: showing main menu!");
                this.showMainMenu();
            } else {
                l.b("back: resuming game");
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
        if (l.B().ar) {
            return "notInDemo";
        }
        return "";
    }

    public void openIfNotDemo(String string, Object object, String string2) {
        if (l.B().ar) {
            this.alert(string2);
            return;
        }
        this.open(string, object);
    }

    public String getVersionName() {
        l l2 = l.B();
        return l2.t();
    }

    public void delayedOpenNoHistory(String string, Object object) {
        ScriptEngine$Action scriptEngine$Action = this.scriptEngine.addRunnableToQueue(new Root$1(this, string));
        scriptEngine$Action.framesDelay = 1;
    }

    public void openAfterHelpPopup(String string) {
        l l2 = l.B();
        if (l.au() && !l2.bQ.hasPlayedGameOrSeenHelp) {
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
        b.a.f();
        HashMap<String, Object> hashMap = null;
        if (object != null) {
            hashMap = new HashMap<String, Object>();
            hashMap.put("mode", object);
        }
        this.libRocket.setDocument(string, hashMap);
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
        l l2 = l.B();
        String string = "Direct Join";
        String string2 = "Enter IP address or host name";
        String string3 = "";
        if (l2.bQ.lastNetworkIP != null) {
            string3 = l2.bQ.lastNetworkIP;
        }
        this.showInputPopup(string, string2, string3, "[onenter]Join:joinServerFromPopup(getPopupText())", null);
    }

    public void joinServerFromPopup(String string) {
        this.closePopup();
        this.hideKeyboard();
        if (string == null) {
            this.logDebug("joinAddress==null");
            return;
        }
        string = string.trim();
        l l2 = l.B();
        l2.bQ.lastNetworkIP = string;
        l2.bQ.save();
        this.joinServerWithId(string, null);
    }

    public void joinServerWithId(String string, String string2) {
        l l2 = l.B();
        l2.bX.bw = string2;
        this.joinServer(string);
    }

    public void joinServer(String string) {
        if (ScriptEngine.inDebugScript && !com.corrodinggames.rts.a.a.d) {
            return;
        }
        this.logDebug("joinAddress=" + string);
        Root$2 root$2 = new Root$2(this);
        l l2 = l.B();
        this.threadedGameConnector = l2.bX.a(string, false, root$2);
        this.lastConnectingPopup = this.createAndShowPopup("multiplayerLobby_connecting.rml", null, "Please wait");
    }

    public void joinServerCallback() {
        this.logDebug("joinServerCallback");
        l l2 = l.B();
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
            try {
                l2.bX.b("starting new");
                l2.bX.a(this.threadedGameConnector.g);
                this.logDebug("connected");
                this.showBattleroom();
            }
            catch (IOException iOException) {
                String string = iOException.getMessage();
                l2.c(string, "Connection failed");
                iOException.printStackTrace();
            }
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
        l.b("old version of hostStart");
        this.hostStartWithPassword(bl, null);
    }

    public void hostStartWithPassword(boolean bl, String string) {
        l.b("old version of hostStartWithPassword");
        this.hostStartWithPasswordAndMods(bl, string, true);
    }

    public void hostStartWithPasswordAndMods(boolean bl, String string, boolean bl2) {
        l l2 = l.B();
        l2.bX.b("starting new");
        l2.bX.n = string;
        l2.bX.o = bl2;
        l2.bX.q = bl;
        if (l2.bX.b(false)) {
            this.logDebug("-Hosting-");
            this.logDebug("using password: " + (l2.bX.n != null));
            this.logDebug("using mods: " + l2.bX.o);
            this.logDebug("public: " + l2.bX.q);
            String string2 = l2.bX.av();
            if (string2 != null && !com.corrodinggames.rts.gameFramework.e.a.i(string2)) {
                l.b("hostStart: map does not exist: " + string2 + " reseting");
                string2 = null;
            }
            if (string2 == null) {
                int n2 = 0;
                l2.bX.ay.a = ai.values()[n2];
                l2.bX.az = "maps/skirmish/[p8]Many Islands (8p).tmx";
                l2.bX.ay.b = "[p8]Many Islands (8p).tmx";
            }
            this.libRocket.setDocument("battleroom.rml", null);
        } else {
            this.logWarn("hosting failed");
        }
    }

    public void exit() {
        l l2 = l.B();
        if (l2.bQ.numLoadsSinceRunningGameOrNormalExit != 0) {
            l2.bQ.numLoadsSinceRunningGameOrNormalExit = 0;
            l2.bQ.save();
        }
        this.scriptEngine.addRunnableToQueue(new Root$3(this));
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
        string2 = com.corrodinggames.rts.gameFramework.h.a.b(string2);
        return string2;
    }

    public String convertMapNameWithoutTranslation(String string) {
        String string2 = i.e(string);
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
        String string3 = com.corrodinggames.rts.appFramework.c.c(string);
        string2 = "thumbnail:assets:" + string3;
        if (!com.corrodinggames.rts.gameFramework.e.a.i(string3)) {
            if (l.aZ) {
                l.a("getMapThumbnail: Failed to find: " + string3);
            }
            return "drawable:error_missingmap.png";
        }
        return string2;
    }

    public boolean isMapSkirmish(String string) {
        return i.f(string);
    }

    public void showLevelOptions() {
        Element element2;
        l l2 = l.B();
        String string = (String)this.libRocket.b("mode");
        if (string == null) {
            l.g("levelPath==null");
            return;
        }
        boolean bl = true;
        if (!this.isMapSkirmish(string)) {
            bl = false;
        }
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        for (Element element2 : elementDocument.findElementsByClassName("skirmishOnly")) {
            element2.show(bl);
        }
        Element element3 = elementDocument.getElementById("advancedButton");
        if (element3 != null) {
            element3.show(bl || i.g(string));
        }
        element2 = elementDocument.getElementById("aiDifficulty");
        element2.setValue("" + l2.bQ.aiDifficulty);
    }

    public void loadConfigAndStartSwitchToAdvanced(String string) {
        boolean bl = true;
        l l2 = l.B();
        l2.bv = false;
        this.loadConfigCommon(string, bl);
        this._startAdvancedMode(false);
    }

    private void _startAdvancedMode(boolean bl) {
        l l2 = l.B();
        l2.bX.b("starting singleplayer");
        l2.bX.y = "You";
        l2.bX.o = true;
        boolean bl2 = bl ? l2.bX.R() : l2.bX.S();
        if (bl2) {
            this.logDebug("started startSinglePlayerServer");
            ah ah2 = l2.bX.e();
            if (ah2 != null) {
                ah2.f = l2.bQ.aiDifficulty;
                l2.bX.a(ah2);
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
        l.e("loadConfigAndStartNewSandboxInAdvanced");
        this._loadConfigAndStartNewSandboxCommon(string, false);
        this._startAdvancedMode(true);
        l l2 = l.B();
        l.e("editorMode:" + l2.bv);
    }

    private void _loadConfigAndStartNewSandboxCommon(String string, boolean bl) {
        boolean bl2 = false;
        if (string.startsWith("saves/")) {
            l.e("Starting sandbox from save: " + string);
            this.loadGame(string.substring("saves/".length()));
        } else {
            l.e("Starting sandbox from map: " + string);
            this.loadConfigCommon(string, bl2);
        }
        l l2 = l.B();
        l2.bL.E = false;
        l2.bS.y();
        l2.bv = true;
        if (bl) {
            this.guiEngine.f();
        }
        this.libRocket.closeActiveDocument();
        this.libRocket.clearHistory();
    }

    public void loadConfigAndStartNew(String string) {
        l l2 = l.B();
        l2.bv = false;
        l2.bX.b("starting singleplayer");
        boolean bl = false;
        this.loadConfigCommon(string, bl);
        this.guiEngine.f();
        this.libRocket.closeActiveDocument();
        this.libRocket.clearHistory();
    }

    public void loadConfigCommon(String string, boolean bl) {
        l l2 = l.B();
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
        i.a(string2, bl2, n2, n4, bl3, bl);
    }

    public void showMapPopup(String string, String string2) {
        boolean bl = false;
        ElementDocument elementDocument = this.libRocket.a("levelSelect.rml", (Object)string, "Map Select", bl);
        if (elementDocument != null) {
            elementDocument.setMetadata("mapClickFunction", string2);
            for (Element element : elementDocument.findElementsByClassName("noStyleInPopup")) {
                element.setAttribute("class", "");
            }
            if (this.showMapsWithDoc(elementDocument)) {
                l.e("showMapsWithDoc passed");
                this.libRocket.h();
                this.libRocket.a(elementDocument);
            }
        }
    }

    public void refreshAfterFileImport() {
        l.e("refreshAfterFileImport");
        l l2 = l.B();
        ArrayList arrayList = l2.bZ.k();
        this.libRocket.reloadDocument();
    }

    public boolean showMaps() {
        ElementDocument elementDocument = this.libRocket.f();
        return this.showMapsWithDoc(elementDocument);
    }

    public boolean showMapsWithDoc(ElementDocument elementDocument) {
        String[] stringArray;
        l l2 = l.B();
        l.e("showMaps");
        if (elementDocument == null) {
            l.e("showMaps: elementDocument==null");
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
            stringArray = com.corrodinggames.rts.appFramework.j.l();
            if (stringArray == null) {
                l2.a("No saves", 1);
                return false;
            }
        } else if (bl2) {
            stringArray = q.l();
            if (!l2.bQ.saveMultiplayerReplays) {
                this.alert("Note: Multiplayer replay recordings are not turned on. You can enable them in the settings.");
            }
            if (stringArray == null) {
                if (l2.bQ.saveMultiplayerReplays) {
                    l2.a("No replays yet", 1);
                }
                return false;
            }
        } else {
            stringArray = com.corrodinggames.rts.gameFramework.e.a.a(string3, true);
            if ((stringArray = l2.bZ.a(stringArray, string3)) == null) {
                l2.a("Could not find folder: " + com.corrodinggames.rts.gameFramework.e.a.e(string3), 1);
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
            boolean bl4 = i.a(string6, string5 + string6);
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
                n3 = 2;
            }
            if (n2 > n3) {
                string13 = "lazy:" + string13;
            }
            String string14 = string7 = com.corrodinggames.rts.appFramework.c.c(string5 + string6);
            if (l.aZ) {
                // empty if block
            }
            String string15 = string13 + string7;
            if (!com.corrodinggames.rts.gameFramework.e.a.i(string14)) {
                if (l.aZ) {
                    l.a("List: Failed to find: " + string14 + " after converting:" + string7 + " ( " + string15 + " )");
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
        if (bl3 && string4 == null && l.au()) {
            elementDocument.addClass("showImportButton");
        }
        return true;
    }

    public void convertTextOnPage() {
        l l2 = l.B();
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
        for (Element element : arrayList) {
            String string;
            int n2 = element.getNumAttributes();
            for (int i2 = 0; i2 < n2; ++i2) {
                Object object;
                String string2 = element.getAttributeKey(i2);
                String string3 = element.getAttributeValue(i2);
                if (string2 == null) continue;
                if (string2.equals("nestedclone") && !string3.equalsIgnoreCase("false")) {
                    l.e("nested clone:" + element.getId());
                    element.setAttribute(string2, "false");
                    object = element.clone();
                    element.prependChild((Element)object);
                    ((Element)object).removeReference();
                    continue;
                }
                if (string2.equals("childclone") && !string3.equalsIgnoreCase("false")) {
                    element.setAttribute(string2, "false");
                    if (element.getNumChildren() < 1) {
                        l.e("child clone failed no children:" + element.getId());
                    }
                    object = element.getChild(0).clone();
                    ((Element)object).addClass("clone");
                    element.prependChild((Element)object);
                    ((Element)object).removeReference();
                    continue;
                }
                object = this.libRocket.d(string3);
                if (object == null) continue;
                l.e("convertTextOnPage:" + string2 + ": '" + string3 + "' to '" + (String)object + "'");
                if (string2.equals("_html")) {
                    l.e("setting html:" + string2);
                    element.setInnerRML((String)object);
                    continue;
                }
                if (string2.startsWith("_")) {
                    string2 = string2.substring("_".length());
                    l.e("converted key to:" + string2);
                }
                element.setAttribute(string2, (String)object);
            }
            if (!elementDocument.translatedToUnicode || !(string = element.getTagName()).equals("p") && !string.startsWith("h") && !string.startsWith("label") && !string.startsWith("button") && !string.startsWith("select")) continue;
            boolean bl = element.loadCharsetIfNeededWithCurrentText();
        }
        convertTextStopwatch.d();
    }

    public void keyBindingPopup_apply(boolean bl) {
        l l2 = l.B();
        ElementDocument elementDocument = this.libRocket.c();
        if (elementDocument == null) {
            this.logWarn("showKeyBindingActionPopup: popup==null");
            return;
        }
        String string = (String)elementDocument.getMetadata("mode");
        String[] stringArray = string.split(":");
        int n2 = Integer.parseInt(stringArray[0]);
        int n3 = Integer.parseInt(stringArray[1]);
        ArrayList arrayList = l2.bT.al;
        ad ad2 = (ad)arrayList.get(n2);
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
        l l2 = l.B();
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
        string = string + l.j(n3);
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
        l l2 = l.B();
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
        ArrayList arrayList = l2.bT.al;
        ad ad2 = (ad)arrayList.get(n2);
        String string2 = "Press a key..";
        element.setText(string2);
    }

    public String getKeyBindingAction(int n2, ad ad2, int n3) {
        String string = n2 + ":" + n3;
        return "createAndShowPopup('settingsKeyBindingSet.rml', " + this.escapedString(string) + ", " + this.escapedString(ad2.a) + "); showKeyBindingPopup();";
    }

    public void backWarnIfOverlappingKeyBinding() {
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        boolean bl = (Boolean)elementDocument.getMetadata("hasOverlappingKeys", false);
        if (bl) {
            String string = "One or more keys are overlapping and have been highlighted in red. These can cause problems.";
            e e2 = new e("Ignore", new Root$4(this));
            e e3 = new e("Fix", new Root$5(this));
            boolean bl2 = false;
            this.showPopupWithButtons(null, string, bl2, e2, e3);
            return;
        }
        this.back();
    }

    public void showKeyBinding() {
        l l2 = l.B();
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        elementDocument.setMetadata("event_onkeydown", "keyBindingPopup_onKeydown");
        Root$TableData root$TableData = new Root$TableData();
        ArrayList arrayList = root$TableData.rows;
        ArrayList arrayList2 = l2.bT.al;
        boolean bl = false;
        for (int i2 = 0; i2 < arrayList2.size(); ++i2) {
            ad ad2 = (ad)arrayList2.get(i2);
            if (!ad2.b) continue;
            Root$TableRow root$TableRow = new Root$TableRow();
            root$TableRow.addCell(ad2.a);
            if (ad2.d()) {
                root$TableRow.addClass("rowHeader");
            } else {
                for (int i3 = 0; i3 <= 1; ++i3) {
                    boolean bl2 = l2.bT.a(ad2, i3);
                    Root$TableCell root$TableCell = root$TableRow.addCell(ad2.b(i3));
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
        l l2 = l.B();
        loadSettingsStopwatch.a();
        this.logDebug("loadSettings");
        Element element = this.libRocket.getActiveElementById("body");
        ArrayList arrayList = element.getAllNestedChildren();
        for (Element element2 : arrayList) {
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
        l l2 = l.B();
        this.logDebug("loadLeaderboard");
        Element element = this.libRocket.getActiveElementById("leaderboardType");
        Element element2 = this.libRocket.getActiveElementById("leaderboardGrouping");
        if (element == null || element2 == null) {
            l.a("loadLeaderboard: Failed to find elements. (For page: " + this.libRocket.getActiveDocumentPath() + ")");
            return;
        }
        element.setAttribute("value", l2.cg.e().name());
        element2.setAttribute("value", l2.cg.f().name());
    }

    public void saveLeaderboard() {
        l l2 = l.B();
        f f2 = f.a;
        c c2 = c.a;
        this.logDebug("saveLeaderboard");
        Element element = this.libRocket.getActiveElementById("leaderboardType");
        Element element2 = this.libRocket.getActiveElementById("leaderboardGrouping");
        if (element == null || element2 == null) {
            l.a("saveLeaderboard: Failed to find elements. (For page: " + this.libRocket.getActiveDocumentPath() + ")");
            return;
        }
        f2 = f.valueOf(element.getAttribute("value"));
        c2 = c.valueOf(element2.getAttribute("value"));
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
        l l2 = l.B();
        Element element2 = this.libRocket.getActiveElementById("uiRenderScale");
        Element element3 = this.libRocket.getActiveElementById("renderDensity");
        Float f2 = null;
        Float f3 = null;
        if (element2 == null) {
            this.logDebug("updateRenderScaleInSettings: uiRenderScale==null");
        } else {
            f2 = element2.getValueAsFloat(Float.valueOf(1.0f));
            element = this.libRocket.getActiveElementById("uiRenderScaleDisplay");
            element.compareAndSetText("x" + com.corrodinggames.rts.gameFramework.f.b((double)(f2.floatValue() + 0.01f), 1));
        }
        if (element3 == null) {
            this.logDebug("updateRenderScaleInSettings: renderDensity==null");
        } else {
            f3 = element3.getValueAsFloat(Float.valueOf(1.0f));
            element = this.libRocket.getActiveElementById("renderDensityDisplay");
            element.compareAndSetText("x" + com.corrodinggames.rts.gameFramework.f.b((double)(f3.floatValue() + 0.01f), 1));
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
        l l2 = l.B();
        this.logDebug("saveSettings");
        Element element = this.libRocket.getActiveElementById("body");
        ArrayList arrayList = element.getAllNestedChildren();
        for (Element element2 : arrayList) {
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
        j.a();
        com.corrodinggames.rts.gameFramework.h.a.e();
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
        if (l.au()) {
            return "hide";
        }
        return "";
    }

    public boolean canResume() {
        l l2 = l.B();
        return l2 != null && l2.bG && !l2.bH;
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
        if (l.aZ) {
            l l2 = l.B();
            return l2.bZ.c() > 0;
        }
        return true;
    }

    public boolean hasWorkshopSupport() {
        return l.av();
    }

    public boolean hasReloadSupport() {
        return !l.aZ;
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
        l l2 = l.B();
        if (string3 != null) {
            object = this.libRocket.getActiveElementById(string3);
            ((Element)object).setText("Refreshing");
        }
        object = new Root$6(this, string, string2, string3);
        l2.bX.bh = null;
        n.a((Runnable)object);
    }

    public void displayServerListRaw(String string, String string2, String string3) {
        Boolean bl;
        l l2 = l.B();
        Element element = this.libRocket.getActiveElementById(string);
        Element element2 = this.libRocket.getActiveElementById(string2);
        if (element == null) {
            l.b("serverListData is null, we may have changed page");
            return;
        }
        Element element3 = element;
        ArrayList<g> arrayList = p.m();
        this.lastSortedDiscoveredServers = arrayList;
        String string4 = com.corrodinggames.rts.gameFramework.h.a.a("menus.lobby.gameState.battleroom", new Object[0]);
        String string5 = com.corrodinggames.rts.gameFramework.h.a.a("menus.lobby.gameState.ingame", new Object[0]);
        String string6 = com.corrodinggames.rts.gameFramework.h.a.a("menus.lobby.gameState.chat", new Object[0]);
        if (element3.getNumChildren() > arrayList.size()) {
            for (int i2 = element3.getNumChildren() - 1; i2 >= arrayList.size(); --i2) {
                l.e("removing rowIndex:" + i2);
                element3.removeChild(element3.getChild(i2));
            }
            if (element3.getNumChildren() != arrayList.size()) {
                l.b("-- Non matching size after clean up:" + element3.getNumChildren() + " vs " + arrayList.size());
            }
        }
        if ((bl = (Boolean)this.libRocket.b("showFullServerList")) == null) {
            bl = false;
        }
        int n2 = 0;
        int n3 = 50;
        if (!bl.booleanValue() && arrayList.size() > n3) {
            ArrayList<g> arrayList2 = new ArrayList<g>();
            for (g object2 : arrayList) {
                arrayList2.add(object2);
                if (arrayList2.size() <= n3) continue;
                break;
            }
            n2 = arrayList.size() - arrayList2.size();
            arrayList = arrayList2;
        }
        int n4 = 0;
        Object object3 = arrayList.iterator();
        while (object3.hasNext()) {
            String string7;
            g element7 = (g)object3.next();
            Element element4 = null;
            if (n4 < element3.getNumChildren()) {
                element4 = element3.getChild(n4);
            }
            if (element4 != null && element4.hasClassName("serverRowMessage")) {
                l.e("removing non rowIndex:" + n4);
                element3.removeChild(element4);
                element4 = null;
            }
            if (element4 != null && element4.findByClassName("rState") == null) {
                l.e("removing non rowIndex with no rState:" + n4);
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
                if (element7.d()) {
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
            string9 = string9 + "color:" + com.corrodinggames.rts.gameFramework.f.h(n5) + ";";
            if (bl6) {
                string9 = string9 + "font-weight: bold;";
                string8 = string8 + "boldRow ";
            }
            element4.compareAndSetClassNames(string8);
            element4.findByClassName("rState").compareAndSetText(string7);
            String string10 = com.corrodinggames.rts.gameFramework.f.a(element7.n, 15);
            element4.findByClassName("rHost").compareAndSetText(string10);
            String string11 = element7.t == "?" ? "?" : element7.t + "\\" + element7.u;
            element4.findByClassName("rPlayers").compareAndSetText(com.corrodinggames.rts.gameFramework.f.a(string11, 15));
            String string12 = com.corrodinggames.rts.gameFramework.f.a(i.e(element7.q), 40);
            if (string12 == null) {
                string12 = "";
            }
            element4.findByClassName("rMap").compareAndSetText(string12);
            String string13 = "ANY".equalsIgnoreCase(element7.k) ? element7.k : "v" + com.corrodinggames.rts.gameFramework.f.a(element7.k, 8);
            Element element5 = element4.findByClassName("rVersion");
            element5.compareAndSetText(string13);
            String string14 = "";
            String string15 = "cell rVersion ";
            if (bl4) {
                string14 = "color:" + com.corrodinggames.rts.gameFramework.f.h(Color.a(255, 155, 147, 147)) + ";";
                string15 = string15 + "nonMatchingRow ";
            } else {
                string14 = "color:" + com.corrodinggames.rts.gameFramework.f.h(n5) + ";";
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
                string17 = "color:" + com.corrodinggames.rts.gameFramework.f.h(Color.a(255, 155, 147, 147)) + ";";
                string18 = string18 + "notOpenRow ";
            } else {
                string17 = "color:" + com.corrodinggames.rts.gameFramework.f.h(n5) + ";";
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
        l.b("DONE");
    }

    public void clickedServerRow(int n2) {
        g g2 = (g)this.lastSortedDiscoveredServers.get(n2);
        this.clickedServer(g2.b);
    }

    public void clickedServer(String string) {
        String string2;
        g g2;
        if (this.getAlertOrPopup() != null) {
            this.logWarn("clickedServer: getAlertOrPopup!=null");
            return;
        }
        try {
            g2 = n.b(string);
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
        if (g2.d()) {
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
        l l2 = l.B();
        l2.ca.b(string, false);
    }

    public void exportMap(String string) {
        this.closePopup();
        l l2 = l.B();
        string = string.replace(".", "_");
        string = string.replace("/", "_");
        string = string.replace("\\", "_");
        string = string.replace("|", "_");
        string = string.replace("?", "_");
        try {
            l2.bL.b(l2.dl, "/SD/rusted_warfare_maps/" + string + ".tmx");
        }
        catch (com.corrodinggames.rts.game.b.f f2) {
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
        l l2 = l.B();
        l2.bX.b("loading new save");
        l2.bv = false;
        if (l2.ca.c(string, false)) {
            this.resumeNonMenu();
        }
    }

    public void loadGameEdit(String string) {
        l l2 = l.B();
        String string2 = string;
        e e2 = null;
        if (com.corrodinggames.rts.gameFramework.l.a.b()) {
            e2 = new e("Share", new Root$7(this, l2, string));
        }
        e e3 = new e("Delete", new Root$8(this, l2, string));
        boolean bl = true;
        this.showPopupWithButtons(null, string2, bl, e2, e3);
    }

    public void loadReplay(String string) {
        l l2 = l.B();
        l2.bv = false;
        if (l2.cb.c(string)) {
            this.resumeNonMenu();
        }
    }

    public void loadReplayEdit(String string) {
        l l2 = l.B();
        String string2 = string;
        e e2 = null;
        if (com.corrodinggames.rts.gameFramework.l.a.b()) {
            e2 = new e("Share", new Root$9(this, l2, string));
        }
        e e3 = new e("Delete", new Root$10(this, l2, string));
        boolean bl = true;
        this.showPopupWithButtons(null, string2, bl, e2, e3);
    }

    public void makeSaveGamePopup(String string) {
        String string2;
        l l2 = l.B();
        String string3 = "Save Game";
        String string4 = "Enter a name to save the game under";
        if (string == null) {
            string2 = l2.al() + " (" + com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy HH-mm-ss") + ")";
            string2 = string2.replace("  ", " ");
        } else {
            string2 = string;
        }
        this.showInputPopup(string3, string4, string2, "[onenter]Save:saveGame(getPopupText())", null);
    }

    public void makeExportMapGamePopup(String string) {
        String string2;
        l l2 = l.B();
        String string3 = "Export Map";
        String string4 = "Enter a name to export the map as";
        if (string == null) {
            string2 = "New " + l2.al() + " - " + com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy");
            string2 = string2.replace("  ", " ");
        } else {
            string2 = string;
        }
        this.showInputPopup(string3, string4, string2, "[onenter]Export:exportMap(getPopupText())", null);
    }

    public void makeSendMessagePopup() {
        l l2 = l.B();
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
        l l2 = l.B();
        String string2 = "Send Team Message";
        String string3 = "+ Ping Map:sendTeamChatMessageAndPing(getPopupText()); closePopup();";
        String string4 = "";
        this.showInputPopup(string2, string4, string, "[onenter]Send Team:sendTeamChatMessage(getPopupText()); closePopup();", string3);
    }

    public void sendChatMessage(String string) {
        l l2 = l.B();
        this.guiEngine.m();
        if (string == null || string.trim().equals("")) {
            return;
        }
        l2.bX.m(string);
        l2.bS.u = false;
    }

    public void sendTeamChatMessageAndPing(String string) {
        this.sendTeamChatMessage(string);
        l l2 = l.B();
        l2.bS.I();
    }

    public void sendTeamChatMessage(String string) {
        l l2 = l.B();
        this.guiEngine.m();
        if (string == null || string.trim().equals("")) {
            return;
        }
        l2.bX.l(string);
    }

    public void receiveChatMessage(int n2, String string, String string2, com.corrodinggames.rts.gameFramework.j.c c2) {
        this.refreshChat();
    }

    public void refreshChat() {
        Object object;
        l l2 = l.B();
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
        object = l2.bX.aC.b();
        StringBuffer stringBuffer = new StringBuffer();
        Object object2 = ((ConcurrentLinkedQueue)object).iterator();
        while (object2.hasNext()) {
            com.corrodinggames.rts.gameFramework.j.b b2 = (com.corrodinggames.rts.gameFramework.j.b)object2.next();
            if (bl) {
                stringBuffer.insert(0, "<div>" + b2.b() + "</div>");
                continue;
            }
            stringBuffer.append("<div>" + b2.b() + "</div>");
        }
        stringBuffer.append("<div id='chatLastRowSpacer'></div>");
        element.setInnerRML(stringBuffer.toString());
        element.loadCharsetIfNeededWithCurrentText();
        object2 = this.libRocket.getActiveElementById("chatLastRowSpacer");
        if (object2 != null) {
            ((Element)object2).scrollIntoView(false);
        }
    }

    public void trace(String string) {
        l.e("Trace:" + string);
    }

    public void updateTableTextOnly(String string, Root$TableData root$TableData, Root$TableData root$TableData2) {
        ArrayList arrayList = root$TableData.rows;
        Element element = this.libRocket.getActiveElementById(string);
        if (element == null) {
            l.b("updateTableText: tableElement:" + string + " is null, we may have changed page");
            return;
        }
        Element element2 = element.getElementById("tableListData");
        for (int i2 = 0; i2 < arrayList.size(); ++i2) {
            Root$TableRow root$TableRow = (Root$TableRow)arrayList.get(i2);
            for (int i3 = 0; i3 < root$TableRow.tableCells.size(); ++i3) {
                Root$TableCell root$TableCell = (Root$TableCell)root$TableRow.tableCells.get(i3);
                Element element3 = element2.getChild(i2);
                if (element3 == null) {
                    l.b("updateTableText failed to get row " + i2);
                    return;
                }
                Element element4 = element3.getChild(i3);
                if (element4 == null) {
                    l.b("updateTableText failed to get cell " + i3);
                    return;
                }
                element4.compareAndSetText(root$TableCell.text);
            }
        }
    }

    public void refreshTable(String string, Root$TableData root$TableData) {
        ArrayList arrayList = root$TableData.rows;
        Element element = this.libRocket.getActiveElementById(string);
        if (element == null) {
            l.b("refreshTable: tableElement:" + string + " is null, we may have changed page");
            return;
        }
        Element element2 = element.getElementById("tableRowTemplateHolder");
        Element element3 = element.getElementById("tableListData");
        Element element4 = element2.findByClassName("rowTemplate").getChild(0);
        Element element5 = element2.findByClassName("cellTemplate").getChild(0);
        element3.setInnerRML("");
        for (Root$TableRow root$TableRow : arrayList) {
            Element element6 = element4.cloneAndFix();
            if (root$TableRow.librocketOnClick != null) {
                element6.setAttribute("onclick", root$TableRow.librocketOnClick);
            }
            if (root$TableRow.extraClasses != null) {
                element6.addClass(root$TableRow.extraClasses);
            }
            for (Root$TableCell root$TableCell : root$TableRow.tableCells) {
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
                    element7.setAttribute("style", "color:" + com.corrodinggames.rts.gameFramework.f.h(root$TableCell.color) + ";");
                }
                element6.appendChild(element7);
                element7.removeReference();
            }
            element3.appendChild(element6);
            element6.removeReference();
        }
    }

    public ElementDocument createAndShowPopup(String string, Object object, String string2) {
        return this.libRocket.a(string, object, string2, true);
    }

    public ElementDocument createPopupHidden(String string, Object object, String string2) {
        return this.libRocket.a(string, object, string2, false);
    }

    public boolean tryToShowPopupDocument(ElementDocument elementDocument) {
        return this.libRocket.b(elementDocument);
    }

    public void showMainMenu() {
        l.B().bS.u = false;
        a.a().b();
    }

    public void onEnter() {
        ElementDocument elementDocument = this.libRocket.g();
        if (elementDocument == null) {
            l.e("onEnter: elementDocument==null");
            return;
        }
        ArrayList arrayList = elementDocument.getAllNestedChildren();
        for (Element element : arrayList) {
            String string = element.getAttribute("onenter");
            if (string == null || !element.isFocused()) continue;
            this.scriptEngine.processScript(string);
        }
    }

    public void scrollFromFocusedElement(float f2) {
        ElementDocument elementDocument = this.libRocket.g();
        if (elementDocument == null) {
            l.e("onEnter: elementDocument==null");
            return;
        }
        Element element = elementDocument.getTopLevelFocusedElement();
        if (element == null) {
            l.e("focusedElement: Not found");
            return;
        }
        ArrayList arrayList = elementDocument.getChainFromChildElement(element);
        if (arrayList == null) {
            l.e("scrollFromFocusedElement: Failed to find chain");
            return;
        }
        for (Element element2 : arrayList) {
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
        l.e("Found no slider element to offset");
    }

    public boolean isSliderOrUIElementSelected() {
        ElementDocument elementDocument = this.libRocket.g();
        if (elementDocument == null) {
            l.e("onEnter: elementDocument==null");
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
                l.e("Slider element: true");
                return true;
            }
            l.e("Slider element: false");
        }
        l.e("Slider element: no element focused");
        return false;
    }

    public void onTouch() {
        ElementDocument elementDocument = this.libRocket.g();
        if (elementDocument == null) {
            l.e("onEnter: elementDocument==null");
            return;
        }
        ArrayList arrayList = elementDocument.getAllNestedChildren();
        for (Element element : arrayList) {
            String string = element.getAttribute("type");
            if (!"text".equals(string) || !element.isFocused()) continue;
            this.guiEngine.l();
        }
    }

    public void onEscape() {
        ElementDocument elementDocument = this.libRocket.g();
        if (elementDocument == null) {
            l.e("onEscape: elementDocument==null");
            return;
        }
        boolean bl = false;
        ArrayList arrayList = elementDocument.getAllNestedChildren();
        for (Element element : arrayList) {
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
        return com.corrodinggames.rts.gameFramework.g.a() == h.c;
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
            l.e("Already on battleroom page");
            bl = false;
        }
        this.libRocket.setDocument(string, null, bl);
    }

    public void setDocument(String string) {
        this.libRocket.setDocument(string);
    }

    public void playNextMusicTrack() {
        l.B().bN.e();
    }

    public void toggleMusic() {
        l.B().bN.u = !l.B().bN.u;
    }

    public void updateMusicButton(String string) {
        Element element = this.libRocket.getActiveElementById(string);
        if (element != null) {
            if (l.B().bN.u) {
                element.setText(">");
            } else {
                element.setText("||");
            }
        }
    }

    public void setSandboxMapFromPopup(String string) {
        l l2 = l.B();
        this.closePopup();
        String string2 = string;
        this.libRocket.getActiveDocument().setMetadata("mode", string);
        this.showLevelOptions();
        this.libRocket.getActiveDocument().findByClassName("mapImage").setAttribute("src", this.getMapThumbnail(string2));
        this.libRocket.getActiveDocument().findByClassName("mapText").setText(this.getMapNameFromPath(string2));
    }

    public void showSandboxMapSelectOnChange() {
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        Element element = elementDocument.getElementById("typeSelector");
        int n2 = Integer.parseInt(element.getValue());
        int n3 = (Integer)elementDocument.getMetadata("lastTypeSelector", 0);
        this.libRocket.getActiveDocument().setMetadata("lastTypeSelector", n2);
        if (n2 != n3) {
            this.showSandboxMapSelect();
        }
    }

    public void showSandboxMapSelect() {
        String string = this.getModeMapPath(this.libRocket.getActiveDocument(), "typeSelector");
        this.showMapPopup(string, "setSandboxMapFromPopup");
    }

    public String getModeMapPath(Element element, String string) {
        int n2;
        l l2 = l.B();
        if (string == null) {
            if (l2.bX.ay.a == null) {
                l.b("getModeMapPath: currentType==0");
                n2 = 0;
            } else {
                n2 = l2.bX.ay.a.ordinal();
            }
        } else {
            Element element2 = element.getElementById(string);
            if (element2 == null) {
                l.g("getModeMapPath: typeSelector==null");
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
        if (n2 == 2) {
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
                l.e("event_unicodeEntered: missing textinput");
            }
        } else {
            l.e("event_unicodeEntered: missing document");
        }
    }

    public boolean isVersionBeta() {
        l l2 = l.B();
        return l2.n();
    }

    public Object ifCondition(boolean bl, Object object, Object object2) {
        return bl ? object : object2;
    }

    public String i(String string) {
        return com.corrodinggames.rts.gameFramework.h.a.a(string, new Object[0]);
    }

    public void openLinkToCG(String string) {
        String string2 = "http://corrodinggames.com/" + string;
        this.openWhitelistedLink(string2);
    }

    public void openWhitelistedLink(String string) {
        l.e("Opening link:" + string);
        if (!(string.startsWith("http://corrodinggames.com/") || string.startsWith("https://corrodinggames.com/") || string.startsWith("http://corrodinggames.net/") || string.startsWith("https://corrodinggames.net/"))) {
            l.e("Not in whitelist");
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
        Object object = a.a().k();
        if (object == null) {
            bl = true;
        } else {
            LinkedList linkedList = object;
            synchronized (linkedList) {
                int n2 = com.corrodinggames.rts.gameFramework.f.b(0, ((LinkedList)object).size() - 3000);
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
        l.e("writeGameLog ready");
        object = this.libRocket.getActiveElementById(string);
        if (object == null) {
            l.e("Failed to find: " + string);
            return;
        }
        ((Element)object).setInnerRML(stringBuffer.toString());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void exportGameLog() {
        Object object;
        Object object2;
        StringBuffer stringBuffer = new StringBuffer();
        boolean bl = false;
        Object object3 = a.a().k();
        if (object3 == null) {
            bl = true;
        } else {
            object2 = object3;
            synchronized (object2) {
                int n2 = com.corrodinggames.rts.gameFramework.f.b(0, ((LinkedList)object3).size() - 3000);
                object = ((LinkedList)object3).listIterator(n2);
                while (object.hasNext()) {
                    stringBuffer.append(Element.excapeHTML((String)object.next()));
                    stringBuffer.append("\n");
                }
            }
        }
        if (bl) {
            this.alert("Internal game logging not active");
            return;
        }
        try {
            object3 = "/SD/rustedWarfare/RustedWarfareLog-" + com.corrodinggames.rts.gameFramework.f.a("d_MMM_yyyy_HH.mm.ss") + ".txt";
            object2 = com.corrodinggames.rts.gameFramework.e.a.e((String)object3);
            File file = new File((String)object2);
            object = new FileWriter(file);
            ((Writer)object).append(stringBuffer.toString());
            ((OutputStreamWriter)object).flush();
            ((OutputStreamWriter)object).close();
            com.corrodinggames.rts.gameFramework.l.a.a(file);
            file.deleteOnExit();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            this.alert("Failed to export logs: " + exception.getMessage());
            return;
        }
    }

    public void setPageMinWidthAndHeight(float f2, float f3) {
        l.e("setPageMinWidthAndHeight(" + f2 + ", " + f3 + ")");
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        if (elementDocument == null) {
            l.e("setPageMinWidthAndHeight - no page");
            return;
        }
        elementDocument.setMetadataFloat("minWidth", Float.valueOf(f2));
        elementDocument.setMetadataFloat("minHeight", Float.valueOf(f3));
        this.guiEngine.n();
    }

    public void importFilePopup() {
        Root$11 root$11 = new Root$11(this);
        com.corrodinggames.rts.gameFramework.l.a.a(root$11);
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
