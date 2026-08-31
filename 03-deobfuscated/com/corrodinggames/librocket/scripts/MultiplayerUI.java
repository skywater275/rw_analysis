/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.Element;
import com.ElementDocument;
import com.corrodinggames.librocket.scripts.MultiplayerUI$1;
import com.corrodinggames.librocket.scripts.MultiplayerUI$DropdownOption;
import com.corrodinggames.librocket.scripts.MainUIController;
import com.corrodinggames.librocket.scripts.MainUIController$TableCell;
import com.corrodinggames.librocket.scripts.MainUIController$TableData;
import com.corrodinggames.librocket.scripts.MainUIController$TableRow;
import com.corrodinggames.librocket.scripts.ScriptContext;
import com.corrodinggames.librocket.scripts.ScriptEngine$Action;
import com.corrodinggames.rts.appFramework.ContextMenuActivity;
import com.corrodinggames.rts.appFramework.ButtonActivity;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.MatchConfig;
import com.corrodinggames.rts.gameFramework.network.GameModeEnum;
import com.corrodinggames.rts.gameFramework.network.ConnectionState;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.io.IOException;

public class MultiplayerUI
extends ScriptContext {
    MainUIController root;
    String[] currentDropdownRawArray;
    MainUIController$TableData lastPlayerTable;
    boolean useMapDropdown = false;

    public MultiplayerUI(MainUIController root) {
        this.root = root;
    }

    /*
     * WARNING - void declaration
     */
    void updateMapDropdown(Element element, String string, String string2) {

        GlobalState l2 = GlobalState.B();
        Element element2 = element.getElementById(string2);
        int n2 = element2.getValueAsInt(0);
        this.currentDropdownRawArray = null;
        ArrayList<String> arrayList = new ArrayList<String>();  // 02b var7 raw ArrayList; String[] 鏍囨敞閿欒淇
        if (n2 == 0) {
            this.currentDropdownRawArray = FileLoader.a("maps/skirmish", true);  // 02b: a.a(String,boolean) (e.a=FileLoader)
            Arrays.sort(this.currentDropdownRawArray);
            for (String string3 : this.currentDropdownRawArray) {
                arrayList.add(ContextMenuActivity.getString2(string3));  // 02b: i.e(String)
            }
        } else if (n2 == 1) {  // $1 绫诲悕姹℃煋鏁板瓧淇
            this.currentDropdownRawArray = FileLoader.a("/SD/rusted_warfare_maps", true);
            if (this.currentDropdownRawArray == null) {
                l2.a("Could not find folder: /SD/rusted_warfare_maps", 1);
                this.currentDropdownRawArray = new String[0];
            }
            Arrays.sort(this.currentDropdownRawArray);
            for (String string3 : this.currentDropdownRawArray) {
                arrayList.add(ContextMenuActivity.getString2(string3));
            }
        } else if (n2 == 2) {
            this.currentDropdownRawArray = ButtonActivity.l();  // 02b: appFramework/j.l()
            if (this.currentDropdownRawArray == null) {
                l2.a("Could not find FileLoader save folder on SD card", 1);
                this.currentDropdownRawArray = new String[0];
            }
            for (String string3 : this.currentDropdownRawArray) {
                arrayList.add(ContextMenuActivity.getString2(string3));
            }
        } else {
            throw new RuntimeException("Unknown typeIndex:" + n2);
        }
        String string4 = "";
        String string5 = "maps/skirmish";
        int n3 = 0;
        int n4 = 1;
        for (String string6 : this.currentDropdownRawArray) {
            ++n3;
            if (n2 != 0 || !string6.equalsIgnoreCase("[p8]Many Islands (8p).tmx")) continue;
            n4 = n3;
        }
        String string8 = "";  // 02b var18: 閫夐」 HTML 绱姞 (var8_13 鎹熷潖鍙橀噺鍚嶄慨姝?
        n3 = 0;
        for (String string6 : this.currentDropdownRawArray) {
            String string7 = this.root.convertMapName(string6);
            boolean bl = ++n3 == n4;
            string8 = string8 + this.generateOption(string6, string7, bl) + "\n";
        }
        GlobalState.e("mapList:" + string8);
        if (n2 != 2) {
            // empty if block
        }
        element.getElementById("mapsSelectorParent").setInnerRML("<p data-workaround='this stops disappearing select'></p><select id='mapsSelector' class='mapsSelector'><option value='0'>...</option></select>");
        Element element3 = this.getMapDropdown();
        element3.setInnerRML(string8);
    }

    String generateOption(String string, String string2, boolean bl) {
        return this.generateOption(string, string2, bl, null, false);
    }

    String generateOption(String string, String string2, boolean bl, Integer n2, boolean bl2) {
        String string3 = "";
        if (bl) {
            string3 = string3 + " selected='selected'";
        }
        String string4 = this.root.htmlString(string2);
        String string5 = "";
        if (n2 != null) {
            string5 = string5 + " style='color:" + GameUtils.h(n2) + ";'";  // 02b: f.h(Integer)
        }
        if (bl2) {
            string5 = string5 + " class='disabled-option'";
        }
        if (string5 != null && !"".equals(string5)) {
            string4 = "<span " + string5 + ">" + string4 + "</span>";
        }
        return "<option value=" + this.root.escapedString(string) + " " + string3 + ">" + string4 + "</option>";
    }

    Element getMapDropdown() {
        ElementDocument elementDocument = this.libRocket.c();
        Element element = elementDocument.findByClassName("mapsSelector");
        return element;
    }

    String getMapDropdownSelected() {
        return this.getMapDropdown().getAttribute("value");
    }

    void readInterfaceIntoNetworkSettings() {
        GlobalState l2 = GlobalState.B();
        if (l2.bX.C) {
            String string = this.getMapDropdownSelected();
            if (string == null) {
                string = "<No Map>";
            }
            l2.bX.ay.b = string;
            int n2 = 0;
            l2.bX.ay.a = GameModeEnum.values()[n2];  // 02b: ai.values() (j/ai=GameModeEnum)
        }
    }

    public void multiplayerStart() {
        GlobalState l2 = GlobalState.B();
        if (l2.bX.C) {
            if (l2.bX.ay.a == GameModeEnum.a) {  // 02b: ai.a
                String string;
                l2.bX.az = string = "maps/skirmish/" + l2.bX.ay.b;
            } else if (l2.bX.ay.a == GameModeEnum.b) {
                l2.bX.az = "/SD/rusted_warfare_maps/" + l2.bX.ay.b;
            } else if (l2.bX.ay.a == GameModeEnum.c) {
                l2.bX.az = null;
            } else {
                this.libRocket.c("Error: No map type selected");
                return;
            }
            if (l2.bX.ay.b == null || "".equals(l2.bX.ay.b) || l2.bX.ay.b.equals("<No Map>")) {
                this.libRocket.c("Error: No map selected");
                return;
            }
            l2.bX.finalizeGameStart();
        } else if (l2.bX.useSteamRelay) {  // 02b: bX.H (H=useSteamRelay)
            l2.bX.k("-start");
        } else {
            GlobalState.b("startNetButton.setOnClickListener", "Clicked but not server or proxy controller");
        }
    }

    public void battleroomSetup() {
        GlobalState l2 = GlobalState.B();
        this.lastPlayerTable = null;
        this.refreshUI();
        this.root.refreshChat();
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        if (elementDocument != null && l2.bX.F) {
            elementDocument.addClass("singlePlayer");
        }
        l2.bX.startLobbyKickTimer();
    }

    public void refreshUI() {
        Object object2;
        GlobalState l2 = GlobalState.B();
        Element element = this.libRocket.getActiveElementById("infoDiv");
        if (element == null) {
            GlobalState.e("refreshUI: infoTextElement==null");
            return;
        }
        ElementDocument elementDocument = this.libRocket.getActiveDocument();
        boolean bl = l2.bX.C || l2.bX.useSteamRelay;  // 02b: bX.C || bX.H (H=useSteamRelay)
        boolean bl2 = l2.bX.C;
        boolean bl3 = !bl && !l2.bX.ay.m;
        for (Object object2_205 : elementDocument.findElementsByClassName("forHostOnly")) {
            ((Element)object2_205).show(bl);
        }
        for (Object object2_208 : elementDocument.findElementsByClassName("forLocalHostOnly")) {
            ((Element)object2_208).show(bl2);
        }
        for (Object object2_211 : elementDocument.findElementsByClassName("forUnlockedTeamsNonHost")) {
            ((Element)object2_211).show(bl3);
        }
        if (l2.P()) {
            for (Object object2_215 : elementDocument.findElementsByClassName("forRealNetworkOnly")) {
                ((Element)object2_215).show(false);
            }
        }
        String iterator = l2.bX.getServerStatusText();  // 02b: bX.at() 杩斿洖 String (Iterator 閿欐爣淇)
        element.compareAndSetText(iterator);
        object2 = l2.bX.getNetworkMapPath();
        if (l2.bX.ay.a == GameModeEnum.c) {  // 02b: ai.c
            object2 = "saves/" + l2.bX.ay.b;
        }
        Element element2 = this.libRocket.getActiveElementById("mapImage");
        if (l2.bX.v) {
            element2.hide();
        }
        String string = element2.getAttribute("src");
        if (object2 == null) {
            if (!"".equals(string)) {
                element2.setAttribute("src", "");
            }
        } else {
            String string2 = this.root.getMapThumbnail((String)object2);
            if (string2 == null) {
                string2 = "";
            }
            if (!string2.equals(string)) {
                element2.setAttribute("src", string2);
            }
        }
        this.refreshPlayerTable();
    }

    public void refreshPlayerTable() {
        MainUIController$TableData root$TableData = this.getPlayerTable();
        String string = "playersDiv";
        if (this.lastPlayerTable != null) {
            if (this.lastPlayerTable.same(root$TableData, false)) {
                return;
            }
            if (this.lastPlayerTable.same(root$TableData, true)) {
                this.root.updateTableTextOnly(string, root$TableData, this.lastPlayerTable);
                return;
            }
        }
        this.root.refreshTable(string, root$TableData);
        this.lastPlayerTable = root$TableData;
    }

    public MainUIController$TableData getPlayerTable() {
        MainUIController$TableRow root$TableRow;
        Object object;
        GlobalState l2 = GlobalState.B();
        MainUIController$TableData root$TableData = new MainUIController$TableData();
        ArrayList arrayList = root$TableData.rows;
        int n2 = -1;
        int n3 = 0;
        ArrayList arrayList2 = PlayerState.a(true);
        for (Object object2 : arrayList2) {
            if (object2 == null) continue;
            if (n2 != -1 && n2 != ((PlayerState) object2).r) {
                ++n3;
            }
            n2 = ((PlayerState) object2).r;
        }
        n2 = -1;
        for (Object object2 : arrayList2) {
            Object object3;
            if (object2 == null) continue;
            if (n2 != -1 && n2 != ((PlayerState) object2).r && n3 <= 3) {
                object = new MainUIController$TableRow();
                for (int i2 = 0; i2 < 4; ++i2) {
                    object3 = ((MainUIController$TableRow)object).addCell("");
                    ((MainUIController$TableCell)object3).addClass("spacer");
                }
                arrayList.add(object);
            }
            n2 = ((PlayerState) object2).r;
            object = "unnamed";
            if (((PlayerState) object2).v != null) {
                object = ((PlayerState) object2).v;
            }
            String string = ((PlayerState) object2).getHostDisplayString();
            object3 = Integer.toString(((PlayerState) object2).k + 1);
            boolean bl = ((PlayerState) object2).b();
            if (bl) {
                object3 = "S";
            }
            if (!bl && ((PlayerState) object2).A != null && ((PlayerState) object2).A != l2.bX.ay.g) {
                object3 = (String)object3 + " - " + l2.bX.sendIncorrectPassword(((PlayerState) object2).A);
            }
            String string2 = ((PlayerState) object2).h();
            root$TableRow = new MainUIController$TableRow();
            MainUIController$TableCell root$TableCell = root$TableRow.addCell((String)object);
            if (((PlayerState) object2).C != null) {
                root$TableCell.color = PlayerState.i(((PlayerState) object2).C);
            }
            if (object2 == l2.bX.z) {
                root$TableCell.addClass("boldText");
            }
            MainUIController$TableCell root$TableCell2 = root$TableRow.addCell((String)object3);
            root$TableCell2.color = ((PlayerState) object2).getUsedUnitCapacity();
            MainUIController$TableCell root$TableCell3 = root$TableRow.addCell(string2);
            root$TableCell3.color = PlayerState.i(((PlayerState) object2).r);
            root$TableRow.addCell(string);
            root$TableRow.setLibrocketOnClick("mp.showPlayerConfig('" + ((PlayerState) object2).k + "')");
            arrayList.add(root$TableRow);
        }
        if (!l2.bX.C && l2.bX.S == null) {
            Object object2;
            arrayList.clear();
            Object object4 = "Connecting...";
            if (l2.bX.aM.size() == 0) {
                object4 = "Disconnected";
            }
            root$TableRow = new MainUIController$TableRow();
            root$TableRow.addCell((String)object4);
            object2 = root$TableRow.addCell("");
            object = root$TableRow.addCell("");
            root$TableRow.addCell("");
            arrayList.add(root$TableRow);
        }
        return root$TableData;
    }

    /* 02b Multiplayer.java L421: 调 createAndShowPopup 抛 IOException */
    public void showSetTeamsDialog() throws IOException {
        GlobalState l2 = GlobalState.B();
        ElementDocument elementDocument = this.root.createAndShowPopup("battleroom_setTeams.rml", null, "Set Teams");
        if (elementDocument != null) {
            // empty if block
        }
    }

    public void showPlayerConfigForSelf() {
        GlobalState l2 = GlobalState.B();
        if (l2.bX.z != null) {
            this.showPlayerConfig("" + l2.bX.z.k);
        }
    }

    public void showPlayerConfig(String string) {
        GlobalState l2 = GlobalState.B();
        ScriptEngine$Action action = this.scriptEngine.addRunnableToQueue(new MultiplayerUI$1(this, string));
    }

    /* 02b Multiplayer.java L443: 调 createAndShowPopup 抛 IOException */
    public void showPlayerConfigNow(String string) throws IOException {
        GlobalState l2 = GlobalState.B();
        int n2 = Integer.parseInt(string);
        PlayerState n3 = PlayerState.k(n2);  // 02b: n.k(int) (n=PlayerState)
        if (n3 == null) {
            this.root.logWarn("showPlayerConfig: " + string + "==null");
            return;
        }
        if (!l2.bX.isServerOrRelay() && (l2.bX.z != n3 || l2.bX.ay.m)) {
            return;
        }
        ElementDocument elementDocument = this.root.createAndShowPopup("battleroom_player.rml", n3, n3.v);
        if (elementDocument != null) {
            Element element = elementDocument.getElementById("team_id");
            Element element2 = elementDocument.getElementById("spawnPoint");
            Element element3 = elementDocument.getElementById("allyTeam");
            Element element4 = elementDocument.getElementById("aiDifficulty");
            Element element5 = elementDocument.getElementById("startingUnits");
            Element element6 = elementDocument.getElementById("playerColor");
            Element element7 = elementDocument.getElementById("playerOverridesSection");
            Element element8 = elementDocument.getElementById("aiDifficultySelection");
            if (!GlobalState.hasPermission("sd")) {
                this.setupStartingUnitDropDown(element5, true);
                this.setupPlayerColorDropDown(element6, true, true, n3);
            } else {
                GlobalState.e("sd");
            }
            element.setValue("" + n3.k);
            String string2 = "" + (n3.k + 1);
            if (n3.b()) {
                string2 = "-2";
            }
            element2.setValue(string2);
            if (n3.u) {
                element3.setValue("" + (n3.r + 1));
            } else {
                element3.setValue("fromSpawn2");
            }
            if (element7 == null) {
                throw new RuntimeException("playerOverridesSection==null");
            }
            if (!l2.bX.C) {
                element7.hide();
            }
            if (element8 == null) {
                throw new RuntimeException("aiDifficultySelection==null");
            }
            if (!GlobalState.hasPermission("s1")) {
                if (n3.w) {
                    if (n3.z == null) {
                        element4.setValue("-99");
                    } else {
                        element4.setValue("" + n3.z);
                    }
                } else {
                    element8.hide();
                }
            } else {
                GlobalState.e("s1");
            }
            if (!GlobalState.hasPermission("s2")) {
                if (n3.A == null) {
                    element5.setValue("-99");
                } else {
                    GlobalState.e("startingUnitOverride: " + n3.A);
                    element5.setValue("" + n3.A);
                }
            } else {
                GlobalState.e("s2");
            }
            if (!GlobalState.hasPermission("s3")) {
                if (n3.C == null) {
                    element6.setValue("-99");
                } else {
                    GlobalState.e("playerColor: " + n3.C);
                    element6.setValue("" + n3.C);
                }
            } else {
                GlobalState.e("s3");
            }
        }
    }

    public void teamsSet_apply() {
        GlobalState l2 = GlobalState.B();
        if (!l2.bX.C) {
            GlobalState.e("Not server");
            return;
        }
        GlobalState.e("playerConfig_kick");
        String string = this.libRocket.c().getElementById("teamLayout").getValue();
        if ("2t".equalsIgnoreCase(string)) {
            l2.bX.m(ConnectionState.a);  // 02b: bX.a(am.a) (j/am=ConnectionState)
        } else if ("3t".equalsIgnoreCase(string)) {
            l2.bX.m(ConnectionState.b);
        } else if ("FFA".equalsIgnoreCase(string)) {
            l2.bX.m(ConnectionState.c);
        } else if ("spectators".equalsIgnoreCase(string)) {
            l2.bX.m(ConnectionState.d);
        } else {
            GlobalState.b("teamsSet_apply: unknown layout: " + string);
        }
        this.refreshUI();
    }

    public void playerConfig_kick() {
        GlobalState l2 = GlobalState.B();
        GlobalState.e("playerConfig_kick");
        String string = this.libRocket.c().getElementById("team_id").getValue();
        int n2 = Integer.parseInt(string);
        PlayerState n3 = PlayerState.k(n2);  // 02b: n.k(int) (n=PlayerState)
        if (n3 == null) {
            this.root.logWarn("playerConfig_kick: " + string + "==null");
            return;
        }
        l2.bX.kickTeam(n3);
    }

    public void playerConfig_apply() {
        int n2;
        Integer n3;
        boolean bl;
        int n4;
        GlobalState l2 = GlobalState.B();
        GlobalState.e("playerConfig_kick");
        String string = this.libRocket.c().getElementById("team_id").getValue();
        int n5 = Integer.parseInt(string);
        PlayerState n6 = PlayerState.k(n5);  // 02b: n.k(int)
        if (n6 == null) {
            this.root.logWarn("playerConfig_apply: " + string + "==null");
            return;
        }
        ElementDocument elementDocument = this.libRocket.c();
        Element element = elementDocument.getElementById("spawnPoint");
        Element element2 = elementDocument.getElementById("allyTeam");
        Element element3 = elementDocument.getElementById("aiDifficulty");
        Element element4 = elementDocument.getElementById("startingUnits");
        Element element5 = elementDocument.getElementById("playerColor");
        String string2 = element.getValue();
        String string3 = element2.getValue();
        int n7 = Integer.valueOf(string2) - 1;
        boolean bl2 = false;
        if (n7 == -3) {
            bl2 = true;
        } else {
            if (n7 < 0) {
                n7 = 0;  // $1 类名污染数字修正
            }
            if (n7 > PlayerState.c - 1) {  // 02b: n.c (PlayerState.c 静态)
                n7 = PlayerState.c - 1;
            }
        }
        boolean bl3 = false;
        if (bl2) {
            n4 = -3;
            bl = true;
        } else if (string3.equals("fromSpawn2")) {
            n4 = n7 % 2;
            n6.u = false;
            bl = true;
        } else {
            bl = false;
            n4 = n6.r;
            try {
                n4 = Integer.valueOf(string3) - 1;
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
            n6.u = true;
        }
        if (n6.r != n4) {
            if (l2.bX.C) {
                bl3 = true;
            } else if (l2.bX.useSteamRelay || l2.bX.z == n6) {  // 02b: bX.H
                bl3 = true;
            } else {
                GlobalState.b("row.setOnClickListener", "Clicked but not server or proxy controller");
            }
        }
        try {
            if (n6.k != n7) {
                if (l2.bX.C) {
                    bl3 = false;
                    l2.bX.registerRelayServer(n6, n7);  // 02b: bX.a(n,int)
                    n6.r = n4;
                } else if (l2.bX.useSteamRelay || l2.bX.z == n6) {  // 02b: bX.H
                    bl3 = false;
                    int n8 = n4;
                    if (bl) {
                        n8 = -1;
                    }
                    l2.bX.registerRelayServer(n6, n7, (Integer)n8);  // 02b: bX.a(n,int,Integer)
                } else {
                    GlobalState.b("row.setOnClickListener", "Clicked but not server or proxy controller");
                }
            }
        }
        catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
        }
        if (n6.w && n6.z != (n3 = (n2 = element3.getValueAsInt(-99).intValue()) == -99 ? null : Integer.valueOf(n2))) {
            if (l2.bX.C) {
                n6.z = n3;
            } else {
                GlobalState.e("aiDifficultyOverride: not server or proxy controller");
            }
        }
        int n9 = element4.getValueAsInt(-99);
        GlobalState.e("startingUnits now: " + n9);
        n3 = n9 == -99 ? null : Integer.valueOf(n9);
        if (n6.A != n3) {
            if (l2.bX.C) {
                n6.A = n3;
            } else {
                GlobalState.e("startingUnitOverride: not server or proxy controller");
            }
        }
        int n10 = element5.getValueAsInt(-99);
        GlobalState.e("playerColor now: " + n10);
        Integer n11 = n10 == -99 ? null : Integer.valueOf(n10);
        if (n6.C != n11) {
            if (l2.bX.C) {
                n6.C = n11;
            } else {
                GlobalState.e("colorOverride: not server or proxy controller");
            }
        }
        if (bl3) {
            if (l2.bX.C) {
                n6.r = n4;
            } else if (bl) {
                l2.bX.m(n6, -1);
            } else {
                l2.bX.m(n6, n4);
            }
        }
        l2.bX.kickTeamImpl();
        l2.bX.connectToServer();
        this.refreshUI();
    }

    public void disconnect(String string) {
        GlobalState l2 = GlobalState.B();
        l2.bX.m(string);
    }

    public void multiplayerBackPrompt() {
        String string = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.multiplayerClose.title", new Object[0]);
        String string2 = "What would you like to do?";
        String string3 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.multiplayerClose.disconnectButton", new Object[0]) + ":";
        string3 = string3 + "closePopup(); mp.disconnect('exited'); back();";
        boolean bl = true;
        this.root.showPopup(string, string2, bl, string3, null);
    }

    public void surrenderPrompt() {
        String string = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.surrender.title", new Object[0]);
        String string2 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.surrender.message", new Object[0]);
        String string3 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.surrender.surrenderButton", new Object[0]) + ":";
        string3 = string3 + "closePopup(); mp.surrender();";
        boolean bl = true;
        this.root.showPopup(string, string2, bl, string3, null);
    }

    public void surrender() {
        GlobalState.e("Surrender requested");
        this.root.sendChatMessage("-surrender");
    }

    public void multiplayerExitPrompt() {
        String string = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.multiplayerClose.titleDisconnect", new Object[0]);
        String string2 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.multiplayerClose.messageDisconnect", new Object[0]);
        GlobalState l2 = GlobalState.B();
        String string3 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.multiplayerClose.disconnectButton", new Object[0]) + ":";
        string3 = string3 + "closePopup(); mp.disconnect('exited'); showMainMenu();";
        String string4 = null;
        if (l2.bX.C) {
            string = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.multiplayerClose.title", new Object[0]);
            string2 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.multiplayerClose.messageEndGame", new Object[0]);
            string3 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.exitGame", new Object[0]) + ":";
            string3 = string3 + "closePopup(); mp.disconnect('exited'); showMainMenu();";
            string4 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.multiplayerClose.returnToBattleroom", new Object[0]) + ":";
            string4 = string4 + "closePopup(); mp.sendReturnToBattleroomEvent();";
        }
        boolean bl = true;
        this.root.showPopup(string, string2, bl, string3, string4);
    }

    public void sendReturnToBattleroomEvent() {
        GlobalState.e("mp.sendReturnToBattleroomEvent()");
        GlobalState l2 = GlobalState.B();
        l2.bX.scheduleReturnToBattleroom();
        l2.bS.u = false;
    }

    public void addAI() {
        GlobalState l2 = GlobalState.B();
        if (l2.bX.C) {
            l2.bX.addAIToGame();
        } else if (l2.bX.useSteamRelay) {  // 02b: bX.H
            l2.bX.k("-addai");
        } else {
            this.root.logWarn("addAI(): Clicked but not server or proxy controller");
        }
    }

    public String _getRandomDefaultPlayerName() {
        return "Unnamed" + GameUtils.a(0, 999);  // 02b: f.a(0,999)
    }

    public void loadUsername() {
        GlobalState.e("mp.loadUsername()");
        GlobalState l2 = GlobalState.B();
        String string = l2.bQ.lastNetworkPlayerName;
        Element element = this.libRocket.getActiveElementById("username");
        String string2 = com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a().c();  // 02b: o.a.a().c()
        GlobalState.e("steamName:" + string2);
        if (string2 != null && string == null) {
            string = string2;
        }
        if (string == null || "".equals(string)) {
            string = this._getRandomDefaultPlayerName();
        }
        element.loadCharsetIfNeeded(string);
        element.setAttribute("value", string);
    }

    public void getUsernameFromInterface() {
        GlobalState l2 = GlobalState.B();
        String string = this.root.getValueById("username");
        if (string == null) {
            GlobalState.b("getUsernameFromInterface: Cannot find username");
            return;
        }
        string = string.trim();
        GlobalState.e("set username:" + string);
        if (string.equals("")) {
            string = this._getRandomDefaultPlayerName();
        }
        l2.bX.a(string);
    }

    public void gameOptionsGet() {
        this.gameOptionsGetOrPush(false);
    }

    public void gameOptionsPush() {
        this.gameOptionsGetOrPush(true);
    }

    public void gameOptionsRefreshTypes() {
        GlobalState l2 = GlobalState.B();
        ElementDocument elementDocument = this.libRocket.c();
        if (this.useMapDropdown) {
            this.updateMapDropdown(elementDocument, "mapsSelector", "typeSelector");
        }
    }

    public void gameOptionsGetOrPush(boolean bl) {
        GlobalState l2 = GlobalState.B();
        ElementDocument elementDocument = this.libRocket.c();
        Element element = elementDocument.getElementById("fogMode");
        Element element2 = elementDocument.getElementById("startingCredits");
        Element element3 = elementDocument.getElementById("incomeMultiplier");
        Element element4 = elementDocument.getElementById("noNukes");
        Element element5 = elementDocument.getElementById("sharedControl");
        Element element6 = elementDocument.getElementById("aiDifficulty");
        Element element7 = elementDocument.getElementById("startingUnits");
        if (!bl) {
            this.setupStartingUnitDropDown(element7, false);
        }
        Element element8 = elementDocument.getElementById("typeSelector");
        Element element9 = this.getMapDropdown();
        if (!bl) {
            if (l2.bX.ay.a == null) {
                GlobalState.e("gameOptionsGetOrPush: game.network.setup.currentType==null");
            } else {
                element8.setValue("" + l2.bX.ay.a.ordinal());
            }
            if (this.useMapDropdown) {
                this.updateMapDropdown(elementDocument, "mapsSelector", "typeSelector");
                element9 = this.getMapDropdown();
                GlobalState.e("new currentMapSelection=" + l2.bX.ay.b);
                element9.setValue("" + l2.bX.ay.b);
            }
            element8 = elementDocument.getElementById("typeSelector");
            element.setValue("" + l2.bX.ay.d);
            element2.setValue("" + l2.bX.ay.c);
            element7.setValue("" + l2.bX.ay.g);
            l2.bX.ay.e = true;
            element4.setCheckbox(l2.bX.ay.i);
            element5.setCheckbox(l2.bX.ay.l);
            element3.setValue("" + GameUtils.a(l2.bX.ay.h, 1) + "x");  // 02b: f.a(ay.h, 1) (float,int 格式化)
            element6.setValue("" + l2.bX.ay.f);
            return;
        }
        MatchConfig ah2 = l2.bX.kickTeam();
        if (ah2 != null) {
            String string = null;
            if (this.useMapDropdown && (string = element9.getValue()) == null) {
                GlobalState.e("gameOptionsGetOrPush: mapDropdownSelected==null");
                string = "<No Map>";
            }
            int n2 = element8.getValueAsInt(0);
            GameModeEnum ai2 = ah2.a;
            ah2.a = GameModeEnum.values()[n2];  // 02b: ai.values()
            if (this.useMapDropdown) {
                ah2.b = string;
            } else if (ai2 != ah2.a) {
                ah2.b = null;
            }
            ah2.d = element.getValueAsInt(null);
            ah2.c = element2.getValueAsInt(null);
            String string2 = element3.getValue();
            string2 = string2.replace("x", "");
            float f2 = 1.0f;
            try {
                f2 = Float.parseFloat(string2);
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
            ah2.h = f2;
            ah2.i = element4.getCheckbox();
            ah2.l = element5.getCheckbox();
            ah2.f = element6.getValueAsInt(null);
            ah2.g = element7.getValueAsInt(1);
            l2.bX.registerRelayServer(ah2);  // 02b: bX.a(MatchConfig)
        }
    }

    public void closeBattleroomIfOpen() {
        GlobalState l2 = GlobalState.B();
        Element element = this.libRocket.getActiveElementById("battleroomPage");
        if (element == null) {
            GlobalState.e("closeBattleroomIfOpen: battleroomPage==null");
            return;
        }
        this.libRocket.backToLastDocument();
    }

    public void reinviteAsk() {
        String string = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.multiplayerReinvite.title", new Object[0]);
        String string2 = "While in-game you can only reinvite players who were in-game before but dropped out";
        String string3 = "reInvite:";
        string3 = string3 + "closePopup(); mp.showSteamInviteDialog();";
        boolean bl = true;
        this.root.showPopup(string, string2, bl, string3, null);
    }

    public void showSteamInviteDialog() {
        com.corrodinggames.rts.gameFramework.steamworks.SteamEngine a2 = com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a();  // 02b: o.a.a()
        a2.g();
    }

    public void setMapFromPopup(String string) {
        if (!this.isInControlOfServer()) {
            String string2 = this.root.getMapNameFromPath(string);
            String string3 = "clicked on '" + string2 + "'";
            this.root.sendChatMessage(string3);
            this.root.closePopup();
            return;
        }
        GlobalState l2 = GlobalState.B();
        MatchConfig ah2 = l2.bX.kickTeam();
        if (ah2 != null) {
            String string4 = string;
            if (!string4.contains("MOD|")) {
                string4 = GameUtils.cosFast(string4);  // 02b: f.k(String) = 文件 basename (String 重载)
            }
            ah2.b = string4;
            l2.bX.registerRelayServer(ah2);  // 02b: bX.a(MatchConfig)
        }
        this.root.closePopup();
    }

    /* 02b Multiplayer.java L975: 调 showMapPopup 抛 IOException */
    public void showMapSelect() throws IOException {
        String string = this.root.getModeMapPath(null, null);
        this.root.showMapPopup(string, "mp.setMapFromPopup");
    }

    public boolean isInControlOfServer() {
        GlobalState l2 = GlobalState.B();
        boolean bl = l2.bX.C || l2.bX.useSteamRelay;  // 02b: bX.C || bX.H
        return bl;
    }

    public void askPassword() {
        GlobalState.e("mp.askPassword()");
        GlobalState l2 = GlobalState.B();
        String string = "Password Required";
        String string2 = "This server requires FileLoader password to join";
        String string3 = "";
        this.root.showInputPopupNonClose(string, string2, string3, "Close:mp.cancelPaswordAsk()", "[onenter]Join:mp.askPasswordEntered(getPopupText())");
    }

    public void askPasswordEntered(String string) {
        GlobalState.e("mp.askPasswordEntered()");
        GlobalState l2 = GlobalState.B();
        l2.bX.n = string;
        l2.bX.registerAllConnections();
        this.root.closePopup();
    }

    public void cancelPaswordAsk() {
        GlobalState l2 = GlobalState.B();
        if (l2.bX.C) {
            this.root.logWarn("cancelPaswordAsk: we are the server");
        } else {
            l2.bX.m("Cancel password");
            this.closeBattleroomIfOpen();
        }
        this.root.closePopup();
    }

    public void setupStartingUnitDropDown(Element element, boolean bl) {
        String string = "";
        if (bl) {
            string = string + this.generateOption("-99", com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.settings.option.default", new Object[0]), false);
        }
        for (MultiplayerUI$DropdownOption multiplayer$DropdownOption : this.getStartingUnitOptions()) {
            string = string + this.generateOption(multiplayer$DropdownOption.key, multiplayer$DropdownOption.value, false);
        }
        element.setInnerRML(string);
    }

    public void setupPlayerColorDropDown(Element element, boolean bl, boolean bl2, PlayerState n2) {
        GlobalState l2 = GlobalState.B();
        String string = "";
        if (bl) {
            string = string + this.generateOption("-99", com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.settings.option.default", new Object[0]), false);
        }
        for (int i2 = 0; i2 < 10; ++i2) {
            boolean bl3 = false;
            if (bl2 && l2.bX.registerRelayServer(i2, n2)) {  // 02b: bX.a(int,n) 颜色占用检查
                bl3 = true;
            }
            String string2 = PlayerState.j(i2);  // 02b: n.j(int) 颜色名
            string2 = al.d(string2);
            int n3 = i2;
            int n4 = i2;
            if (bl3) {
                string2 = string2 + " (used)";
                n3 = -7829368;
                n4 = -99;
            }
            string = string + this.generateOption("" + n4, string2, false, PlayerState.i(n3), bl3);
        }
        element.setInnerRML(string);
    }

    public ArrayList<MultiplayerUI$DropdownOption> getStartingUnitOptions() {  // 02b: ArrayList raw (泛型化消 enhanced-for 错误)
        GlobalState l2 = GlobalState.B();
        ArrayList<MultiplayerUI$DropdownOption> arrayList = new ArrayList<MultiplayerUI$DropdownOption>();  // Multiplayer$DropdownOption 幻觉名修正
        Iterator iterator = l2.bX.cancelNotification().iterator();  // 02b: bX.i() 起始单位选项 (raw 集合显式迭代)
        while (iterator.hasNext()) {
            Integer n2 = (Integer)iterator.next();
            String string = l2.bX.sendIncorrectPassword(n2);
            arrayList.add(new MultiplayerUI$DropdownOption(n2.toString(), string));
        }
        return arrayList;
    }
}
