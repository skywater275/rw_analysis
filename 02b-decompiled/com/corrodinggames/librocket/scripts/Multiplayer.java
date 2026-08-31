package com.corrodinggames.librocket.scripts;

import com.Element;
import com.ElementDocument;
import com.corrodinggames.librocket.scripts.Multiplayer$1;
import com.corrodinggames.librocket.scripts.Multiplayer$DropdownOption;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.Root$TableCell;
import com.corrodinggames.librocket.scripts.Root$TableData;
import com.corrodinggames.librocket.scripts.Root$TableRow;
import com.corrodinggames.librocket.scripts.ScriptContext;
import com.corrodinggames.librocket.scripts.ScriptEngine$Action;
import com.corrodinggames.rts.appFramework.i;
import com.corrodinggames.rts.appFramework.j;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.j.ah;
import com.corrodinggames.rts.gameFramework.j.ai;
import com.corrodinggames.rts.gameFramework.j.am;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Multiplayer extends ScriptContext {

   Root root;
   String[] currentDropdownRawArray;
   Root$TableData lastPlayerTable;
   boolean useMapDropdown = false;


   Multiplayer(Root var1) {
      this.root = var1;
   }

   void updateMapDropdown(Element var1, String var2, String var3) {
      l var4 = l.B();
      Element var5 = var1.getElementById(var3);
      int var6 = var5.getValueAsInt(Integer.valueOf(0)).intValue();
      this.currentDropdownRawArray = null;
      ArrayList var7 = new ArrayList();
      String[] var8;
      int var9;
      int var10;
      String var11;
      String var12;
      if(var6 == 0) {
         this.currentDropdownRawArray = a.a("maps/skirmish", true);
         Arrays.sort(this.currentDropdownRawArray);
         var8 = this.currentDropdownRawArray;
         var9 = var8.length;

         for(var10 = 0; var10 < var9; ++var10) {
            var11 = var8[var10];
            var12 = i.e(var11);
            var7.add(var12);
         }
      } else if(var6 == 1) {
         this.currentDropdownRawArray = a.a("/SD/rusted_warfare_maps", true);
         if(this.currentDropdownRawArray == null) {
            var4.a("Could not find folder: /SD/rusted_warfare_maps", 1);
            this.currentDropdownRawArray = new String[0];
         }

         Arrays.sort(this.currentDropdownRawArray);
         var8 = this.currentDropdownRawArray;
         var9 = var8.length;

         for(var10 = 0; var10 < var9; ++var10) {
            var11 = var8[var10];
            var12 = i.e(var11);
            var7.add(var12);
         }
      } else {
         if(var6 != 2) {
            throw new RuntimeException("Unknown typeIndex:" + var6);
         }

         this.currentDropdownRawArray = j.l();
         if(this.currentDropdownRawArray == null) {
            var4.a("Could not find a save folder on SD card", 1);
            this.currentDropdownRawArray = new String[0];
         }

         var8 = this.currentDropdownRawArray;
         var9 = var8.length;

         for(var10 = 0; var10 < var9; ++var10) {
            var11 = var8[var10];
            var12 = i.e(var11);
            var7.add(var12);
         }
      }

      String var18 = "";
      String var19 = "maps/skirmish";
      var10 = 0;
      int var20 = 1;
      String[] var21 = this.currentDropdownRawArray;
      int var13 = var21.length;

      int var14;
      String var15;
      for(var14 = 0; var14 < var13; ++var14) {
         var15 = var21[var14];
         ++var10;
         if(var6 == 0 && var15.equalsIgnoreCase("[p8]Many Islands (8p).tmx")) {
            var20 = var10;
         }
      }

      var10 = 0;
      var21 = this.currentDropdownRawArray;
      var13 = var21.length;

      for(var14 = 0; var14 < var13; ++var14) {
         var15 = var21[var14];
         ++var10;
         String var16 = this.root.convertMapName(var15);
         boolean var17 = var10 == var20;
         var18 = var18 + this.generateOption(var15, var16, var17) + "\n";
      }

      l.e("mapList:" + var18);
      if(var6 != 2) {
         ;
      }

      Element var22 = var1.getElementById("mapsSelectorParent");
      String var23 = "<p data-workaround=\'this stops disappearing select\'></p><select id=\'mapsSelector\' class=\'mapsSelector\'><option value=\'0\'>...</option></select>";
      var22.setInnerRML(var23);
      Element var24 = this.getMapDropdown();
      var24.setInnerRML(var18);
   }

   String generateOption(String var1, String var2, boolean var3) {
      return this.generateOption(var1, var2, var3, (Integer)null, false);
   }

   String generateOption(String var1, String var2, boolean var3, Integer var4, boolean var5) {
      String var6 = "";
      if(var3) {
         var6 = var6 + " selected=\'selected\'";
      }

      String var7 = this.root.htmlString(var2);
      String var8 = "";
      if(var4 != null) {
         var8 = var8 + " style=\'color:" + f.h(var4.intValue()) + ";\'";
      }

      if(var5) {
         var8 = var8 + " class=\'disabled-option\'";
      }

      if(var8 != null && !"".equals(var8)) {
         var7 = "<span " + var8 + ">" + var7 + "</span>";
      }

      return "<option value=" + this.root.escapedString(var1) + " " + var6 + ">" + var7 + "</option>";
   }

   Element getMapDropdown() {
      ElementDocument var1 = this.libRocket.c();
      Element var2 = var1.findByClassName("mapsSelector");
      return var2;
   }

   String getMapDropdownSelected() {
      return this.getMapDropdown().getAttribute("value");
   }

   void readInterfaceIntoNetworkSettings() {
      l var1 = l.B();
      if(var1.bX.C) {
         String var2 = this.getMapDropdownSelected();
         if(var2 == null) {
            var2 = "<No Map>";
         }

         var1.bX.ay.b = var2;
         byte var3 = 0;
         var1.bX.ay.a = ai.values()[var3];
      }

   }

   public void multiplayerStart() {
      l var1 = l.B();
      if(var1.bX.C) {
         if(var1.bX.ay.a == ai.a) {
            String var2 = "maps/skirmish/" + var1.bX.ay.b;
            var1.bX.az = var2;
         } else if(var1.bX.ay.a == ai.b) {
            var1.bX.az = "/SD/rusted_warfare_maps/" + var1.bX.ay.b;
         } else {
            if(var1.bX.ay.a != ai.c) {
               this.libRocket.c("Error: No map type selected");
               return;
            }

            var1.bX.az = null;
         }

         if(var1.bX.ay.b == null || "".equals(var1.bX.ay.b) || var1.bX.ay.b.equals("<No Map>")) {
            this.libRocket.c("Error: No map selected");
            return;
         }

         var1.bX.ae();
      } else if(var1.bX.H) {
         var1.bX.k("-start");
      } else {
         l.b("startNetButton.setOnClickListener", "Clicked but not server or proxy controller");
      }

   }

   public void battleroomSetup() {
      l var1 = l.B();
      this.lastPlayerTable = null;
      this.refreshUI();
      this.root.refreshChat();
      ElementDocument var2 = this.libRocket.getActiveDocument();
      if(var2 != null && var1.bX.F) {
         var2.addClass("singlePlayer");
      }

      var1.bX.as();
   }

   public void refreshUI() {
      l var1 = l.B();
      Element var2 = this.libRocket.getActiveElementById("infoDiv");
      if(var2 == null) {
         l.e("refreshUI: infoTextElement==null");
      } else {
         ElementDocument var3 = this.libRocket.getActiveDocument();
         boolean var4 = var1.bX.C || var1.bX.H;
         boolean var5 = var1.bX.C;
         boolean var6 = !var4 && !var1.bX.ay.m;
         Iterator var7 = var3.findElementsByClassName("forHostOnly").iterator();

         Element var8;
         while(var7.hasNext()) {
            var8 = (Element)var7.next();
            var8.show(var4);
         }

         var7 = var3.findElementsByClassName("forLocalHostOnly").iterator();

         while(var7.hasNext()) {
            var8 = (Element)var7.next();
            var8.show(var5);
         }

         var7 = var3.findElementsByClassName("forUnlockedTeamsNonHost").iterator();

         while(var7.hasNext()) {
            var8 = (Element)var7.next();
            var8.show(var6);
         }

         if(var1.P()) {
            var7 = var3.findElementsByClassName("forRealNetworkOnly").iterator();

            while(var7.hasNext()) {
               var8 = (Element)var7.next();
               var8.show(false);
            }
         }

         String var12 = var1.bX.at();
         var2.compareAndSetText(var12);
         String var13 = var1.bX.av();
         if(var1.bX.ay.a == ai.c) {
            var13 = "saves/" + var1.bX.ay.b;
         }

         Element var9 = this.libRocket.getActiveElementById("mapImage");
         if(var1.bX.v) {
            var9.hide();
         }

         String var10 = var9.getAttribute("src");
         if(var13 == null) {
            if(!"".equals(var10)) {
               var9.setAttribute("src", "");
            }
         } else {
            String var11 = this.root.getMapThumbnail(var13);
            if(var11 == null) {
               var11 = "";
            }

            if(!var11.equals(var10)) {
               var9.setAttribute("src", var11);
            }
         }

         this.refreshPlayerTable();
      }
   }

   public void refreshPlayerTable() {
      Root$TableData var1 = this.getPlayerTable();
      String var2 = "playersDiv";
      if(this.lastPlayerTable != null) {
         if(this.lastPlayerTable.same(var1, false)) {
            return;
         }

         if(this.lastPlayerTable.same(var1, true)) {
            this.root.updateTableTextOnly(var2, var1, this.lastPlayerTable);
            return;
         }
      }

      this.root.refreshTable(var2, var1);
      this.lastPlayerTable = var1;
   }

   public Root$TableData getPlayerTable() {
      l var1 = l.B();
      Root$TableData var2 = new Root$TableData();
      ArrayList var3 = var2.rows;
      int var5 = -1;
      int var6 = 0;
      ArrayList var7 = n.a(true);
      Iterator var8 = var7.iterator();

      n var9;
      while(var8.hasNext()) {
         var9 = (n)var8.next();
         if(var9 != null) {
            if(var5 != -1 && var5 != var9.r) {
               ++var6;
            }

            var5 = var9.r;
         }
      }

      var5 = -1;
      var8 = var7.iterator();

      Root$TableRow var4;
      while(var8.hasNext()) {
         var9 = (n)var8.next();
         if(var9 != null) {
            if(var5 != -1 && var5 != var9.r && var6 <= 3) {
               Root$TableRow var10 = new Root$TableRow();

               for(int var11 = 0; var11 < 4; ++var11) {
                  Root$TableCell var12 = var10.addCell("");
                  var12.addClass("spacer");
               }

               var3.add(var10);
            }

            var5 = var9.r;
            String var20 = "unnamed";
            if(var9.v != null) {
               var20 = var9.v;
            }

            String var22 = var9.z();
            String var23 = Integer.toString(var9.k + 1);
            boolean var13 = var9.b();
            if(var13) {
               var23 = "S";
            }

            if(!var13 && var9.A != null && var9.A.intValue() != var1.bX.ay.g) {
               var23 = var23 + " - " + var1.bX.d(var9.A.intValue());
            }

            String var14 = var9.h();
            var4 = new Root$TableRow();
            Root$TableCell var15 = var4.addCell(var20);
            if(var9.C != null) {
               var15.color = Integer.valueOf(n.i(var9.C.intValue()));
            }

            if(var9 == var1.bX.z) {
               var15.addClass("boldText");
            }

            Root$TableCell var16 = var4.addCell(var23);
            var16.color = Integer.valueOf(var9.M());
            Root$TableCell var17 = var4.addCell(var14);
            var17.color = Integer.valueOf(n.i(var9.r));
            var4.addCell(var22);
            var4.setLibrocketOnClick("mp.showPlayerConfig(\'" + var9.k + "\')");
            var3.add(var4);
         }
      }

      if(!var1.bX.C && var1.bX.S == null) {
         var3.clear();
         String var18 = "Connecting...";
         if(var1.bX.aM.size() == 0) {
            var18 = "Disconnected";
         }

         var4 = new Root$TableRow();
         var4.addCell(var18);
         Root$TableCell var19 = var4.addCell("");
         Root$TableCell var21 = var4.addCell("");
         var4.addCell("");
         var3.add(var4);
      }

      return var2;
   }

   public void showSetTeamsDialog() {
      l var1 = l.B();
      ElementDocument var2 = this.root.createAndShowPopup("battleroom_setTeams.rml", (Object)null, "Set Teams");
      if(var2 != null) {
         ;
      }

   }

   public void showPlayerConfigForSelf() {
      l var1 = l.B();
      if(var1.bX.z != null) {
         this.showPlayerConfig("" + var1.bX.z.k);
      }

   }

   public void showPlayerConfig(String var1) {
      l var2 = l.B();
      ScriptEngine$Action var3 = this.scriptEngine.addRunnableToQueue(new Multiplayer$1(this, var1));
   }

   public void showPlayerConfigNow(String var1) {
      l var2 = l.B();
      int var3 = Integer.parseInt(var1);
      n var4 = n.k(var3);
      if(var4 == null) {
         this.root.logWarn("showPlayerConfig: " + var1 + "==null");
      } else if(var2.bX.aw() || var2.bX.z == var4 && !var2.bX.ay.m) {
         ElementDocument var5 = this.root.createAndShowPopup("battleroom_player.rml", var4, var4.v);
         if(var5 != null) {
            Element var6 = var5.getElementById("team_id");
            Element var7 = var5.getElementById("spawnPoint");
            Element var8 = var5.getElementById("allyTeam");
            Element var9 = var5.getElementById("aiDifficulty");
            Element var10 = var5.getElementById("startingUnits");
            Element var11 = var5.getElementById("playerColor");
            Element var12 = var5.getElementById("playerOverridesSection");
            Element var13 = var5.getElementById("aiDifficultySelection");
            if(!l.o("sd")) {
               this.setupStartingUnitDropDown(var10, true);
               this.setupPlayerColorDropDown(var11, true, true, var4);
            } else {
               l.e("sd");
            }

            var6.setValue("" + var4.k);
            String var14 = "" + (var4.k + 1);
            if(var4.b()) {
               var14 = "-2";
            }

            var7.setValue(var14);
            if(var4.u) {
               var8.setValue("" + (var4.r + 1));
            } else {
               var8.setValue("fromSpawn2");
            }

            if(var12 == null) {
               throw new RuntimeException("playerOverridesSection==null");
            }

            if(!var2.bX.C) {
               var12.hide();
            }

            if(var13 == null) {
               throw new RuntimeException("aiDifficultySelection==null");
            }

            if(!l.o("s1")) {
               if(var4.w) {
                  if(var4.z == null) {
                     var9.setValue("-99");
                  } else {
                     var9.setValue("" + var4.z);
                  }
               } else {
                  var13.hide();
               }
            } else {
               l.e("s1");
            }

            if(!l.o("s2")) {
               if(var4.A == null) {
                  var10.setValue("-99");
               } else {
                  l.e("startingUnitOverride: " + var4.A);
                  var10.setValue("" + var4.A);
               }
            } else {
               l.e("s2");
            }

            if(!l.o("s3")) {
               if(var4.C == null) {
                  var11.setValue("-99");
               } else {
                  l.e("playerColor: " + var4.C);
                  var11.setValue("" + var4.C);
               }
            } else {
               l.e("s3");
            }
         }

      }
   }

   public void teamsSet_apply() {
      l var1 = l.B();
      if(!var1.bX.C) {
         l.e("Not server");
      } else {
         l.e("playerConfig_kick");
         String var2 = this.libRocket.c().getElementById("teamLayout").getValue();
         if("2t".equalsIgnoreCase(var2)) {
            var1.bX.a(am.a);
         } else if("3t".equalsIgnoreCase(var2)) {
            var1.bX.a(am.b);
         } else if("FFA".equalsIgnoreCase(var2)) {
            var1.bX.a(am.c);
         } else if("spectators".equalsIgnoreCase(var2)) {
            var1.bX.a(am.d);
         } else {
            l.b("teamsSet_apply: unknown layout: " + var2);
         }

         this.refreshUI();
      }
   }

   public void playerConfig_kick() {
      l var1 = l.B();
      l.e("playerConfig_kick");
      String var2 = this.libRocket.c().getElementById("team_id").getValue();
      int var3 = Integer.parseInt(var2);
      n var4 = n.k(var3);
      if(var4 == null) {
         this.root.logWarn("playerConfig_kick: " + var2 + "==null");
      } else {
         var1.bX.e(var4);
      }
   }

   public void playerConfig_apply() {
      l var1 = l.B();
      l.e("playerConfig_kick");
      String var2 = this.libRocket.c().getElementById("team_id").getValue();
      int var3 = Integer.parseInt(var2);
      n var4 = n.k(var3);
      if(var4 == null) {
         this.root.logWarn("playerConfig_apply: " + var2 + "==null");
      } else {
         ElementDocument var5 = this.libRocket.c();
         Element var6 = var5.getElementById("spawnPoint");
         Element var7 = var5.getElementById("allyTeam");
         Element var8 = var5.getElementById("aiDifficulty");
         Element var9 = var5.getElementById("startingUnits");
         Element var10 = var5.getElementById("playerColor");
         String var11 = var6.getValue();
         String var12 = var7.getValue();
         int var13 = Integer.valueOf(var11).intValue() - 1;
         boolean var14 = false;
         if(var13 == -3) {
            var14 = true;
         } else {
            if(var13 < 0) {
               var13 = 1;
            }

            if(var13 > n.c - 1) {
               var13 = n.c - 1;
            }
         }

         boolean var15 = false;
         int var16;
         boolean var17;
         if(var14) {
            var16 = -3;
            var17 = true;
         } else if(var12.equals("fromSpawn2")) {
            var16 = var13 % 2;
            var4.u = false;
            var17 = true;
         } else {
            var17 = false;
            var16 = var4.r;

            try {
               var16 = Integer.valueOf(var12).intValue() - 1;
            } catch (NumberFormatException var22) {
               var22.printStackTrace();
            }

            var4.u = true;
         }

         if(var4.r != var16) {
            if(var1.bX.C) {
               var15 = true;
            } else if(!var1.bX.H && var1.bX.z != var4) {
               l.b("row.setOnClickListener", "Clicked but not server or proxy controller");
            } else {
               var15 = true;
            }
         }

         int var18;
         try {
            if(var4.k != var13) {
               if(var1.bX.C) {
                  var15 = false;
                  var1.bX.a(var4, var13);
                  var4.r = var16;
               } else if(!var1.bX.H && var1.bX.z != var4) {
                  l.b("row.setOnClickListener", "Clicked but not server or proxy controller");
               } else {
                  var15 = false;
                  var18 = var16;
                  if(var17) {
                     var18 = -1;
                  }

                  var1.bX.a(var4, var13, Integer.valueOf(var18));
               }
            }
         } catch (NumberFormatException var23) {
            var23.printStackTrace();
         }

         Integer var19;
         if(var4.w) {
            var18 = var8.getValueAsInt(Integer.valueOf(-99)).intValue();
            if(var18 == -99) {
               var19 = null;
            } else {
               var19 = Integer.valueOf(var18);
            }

            if(var4.z != var19) {
               if(var1.bX.C) {
                  var4.z = var19;
               } else {
                  l.e("aiDifficultyOverride: not server or proxy controller");
               }
            }
         }

         var18 = var9.getValueAsInt(Integer.valueOf(-99)).intValue();
         l.e("startingUnits now: " + var18);
         if(var18 == -99) {
            var19 = null;
         } else {
            var19 = Integer.valueOf(var18);
         }

         if(var4.A != var19) {
            if(var1.bX.C) {
               var4.A = var19;
            } else {
               l.e("startingUnitOverride: not server or proxy controller");
            }
         }

         int var20 = var10.getValueAsInt(Integer.valueOf(-99)).intValue();
         l.e("playerColor now: " + var20);
         Integer var21;
         if(var20 == -99) {
            var21 = null;
         } else {
            var21 = Integer.valueOf(var20);
         }

         if(var4.C != var21) {
            if(var1.bX.C) {
               var4.C = var21;
            } else {
               l.e("colorOverride: not server or proxy controller");
            }
         }

         if(var15) {
            if(var1.bX.C) {
               var4.r = var16;
            } else if(var17) {
               var1.bX.b(var4, -1);
            } else {
               var1.bX.b(var4, var16);
            }
         }

         var1.bX.f();
         var1.bX.M();
         this.refreshUI();
      }
   }

   public void disconnect(String var1) {
      l var2 = l.B();
      var2.bX.b(var1);
   }

   public void multiplayerBackPrompt() {
      String var1 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.title", new Object[0]);
      String var2 = "What would you like to do?";
      String var3 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.disconnectButton", new Object[0]) + ":";
      var3 = var3 + "closePopup(); mp.disconnect(\'exited\'); back();";
      boolean var4 = true;
      this.root.showPopup(var1, var2, var4, var3, (String)null);
   }

   public void surrenderPrompt() {
      String var1 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.surrender.title", new Object[0]);
      String var2 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.surrender.message", new Object[0]);
      String var3 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.surrender.surrenderButton", new Object[0]) + ":";
      var3 = var3 + "closePopup(); mp.surrender();";
      boolean var4 = true;
      this.root.showPopup(var1, var2, var4, var3, (String)null);
   }

   public void surrender() {
      l.e("Surrender requested");
      this.root.sendChatMessage("-surrender");
   }

   public void multiplayerExitPrompt() {
      String var1 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.titleDisconnect", new Object[0]);
      String var2 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.messageDisconnect", new Object[0]);
      l var3 = l.B();
      String var4 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.disconnectButton", new Object[0]) + ":";
      var4 = var4 + "closePopup(); mp.disconnect(\'exited\'); showMainMenu();";
      String var5 = null;
      if(var3.bX.C) {
         var1 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.title", new Object[0]);
         var2 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.messageEndGame", new Object[0]);
         var4 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.exitGame", new Object[0]) + ":";
         var4 = var4 + "closePopup(); mp.disconnect(\'exited\'); showMainMenu();";
         var5 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerClose.returnToBattleroom", new Object[0]) + ":";
         var5 = var5 + "closePopup(); mp.sendReturnToBattleroomEvent();";
      }

      boolean var6 = true;
      this.root.showPopup(var1, var2, var6, var4, var5);
   }

   public void sendReturnToBattleroomEvent() {
      l.e("mp.sendReturnToBattleroomEvent()");
      l var1 = l.B();
      var1.bX.ag();
      var1.bS.u = false;
   }

   public void addAI() {
      l var1 = l.B();
      if(var1.bX.C) {
         var1.bX.ap();
      } else if(var1.bX.H) {
         var1.bX.k("-addai");
      } else {
         this.root.logWarn("addAI(): Clicked but not server or proxy controller");
      }

   }

   public String _getRandomDefaultPlayerName() {
      return "Unnamed" + f.a(0, 999);
   }

   public void loadUsername() {
      l.e("mp.loadUsername()");
      l var1 = l.B();
      String var2 = var1.bQ.lastNetworkPlayerName;
      Element var3 = this.libRocket.getActiveElementById("username");
      String var4 = com.corrodinggames.rts.gameFramework.o.a.a().c();
      l.e("steamName:" + var4);
      if(var4 != null && var2 == null) {
         var2 = var4;
      }

      if(var2 == null || "".equals(var2)) {
         var2 = this._getRandomDefaultPlayerName();
      }

      var3.loadCharsetIfNeeded(var2);
      var3.setAttribute("value", var2);
   }

   public void getUsernameFromInterface() {
      l var1 = l.B();
      String var2 = this.root.getValueById("username");
      if(var2 == null) {
         l.b("getUsernameFromInterface: Cannot find username");
      } else {
         var2 = var2.trim();
         l.e("set username:" + var2);
         if(var2.equals("")) {
            var2 = this._getRandomDefaultPlayerName();
         }

         var1.bX.a(var2);
      }
   }

   public void gameOptionsGet() {
      this.gameOptionsGetOrPush(false);
   }

   public void gameOptionsPush() {
      this.gameOptionsGetOrPush(true);
   }

   public void gameOptionsRefreshTypes() {
      l var1 = l.B();
      ElementDocument var2 = this.libRocket.c();
      if(this.useMapDropdown) {
         this.updateMapDropdown(var2, "mapsSelector", "typeSelector");
      }

   }

   public void gameOptionsGetOrPush(boolean var1) {
      l var2 = l.B();
      ElementDocument var3 = this.libRocket.c();
      Element var4 = var3.getElementById("fogMode");
      Element var5 = var3.getElementById("startingCredits");
      Element var6 = var3.getElementById("incomeMultiplier");
      Element var7 = var3.getElementById("noNukes");
      Element var8 = var3.getElementById("sharedControl");
      Element var9 = var3.getElementById("aiDifficulty");
      Element var10 = var3.getElementById("startingUnits");
      if(!var1) {
         this.setupStartingUnitDropDown(var10, false);
      }

      Element var11 = var3.getElementById("typeSelector");
      Element var12 = this.getMapDropdown();
      if(!var1) {
         if(var2.bX.ay.a == null) {
            l.e("gameOptionsGetOrPush: game.network.setup.currentType==null");
         } else {
            var11.setValue("" + var2.bX.ay.a.ordinal());
         }

         if(this.useMapDropdown) {
            this.updateMapDropdown(var3, "mapsSelector", "typeSelector");
            var12 = this.getMapDropdown();
            l.e("new currentMapSelection=" + var2.bX.ay.b);
            var12.setValue("" + var2.bX.ay.b);
         }

         var11 = var3.getElementById("typeSelector");
         var4.setValue("" + var2.bX.ay.d);
         var5.setValue("" + var2.bX.ay.c);
         var10.setValue("" + var2.bX.ay.g);
         var2.bX.ay.e = true;
         var7.setCheckbox(var2.bX.ay.i);
         var8.setCheckbox(var2.bX.ay.l);
         var6.setValue("" + f.a(var2.bX.ay.h, 1) + "x");
         var9.setValue("" + var2.bX.ay.f);
      } else {
         ah var13 = var2.bX.e();
         if(var13 != null) {
            String var14 = null;
            if(this.useMapDropdown) {
               var14 = var12.getValue();
               if(var14 == null) {
                  l.e("gameOptionsGetOrPush: mapDropdownSelected==null");
                  var14 = "<No Map>";
               }
            }

            int var15 = var11.getValueAsInt(Integer.valueOf(0)).intValue();
            ai var16 = var13.a;
            var13.a = ai.values()[var15];
            if(this.useMapDropdown) {
               var13.b = var14;
            } else if(var16 != var13.a) {
               var13.b = null;
            }

            var13.d = var4.getValueAsInt((Integer)null).intValue();
            var13.c = var5.getValueAsInt((Integer)null).intValue();
            String var17 = var6.getValue();
            var17 = var17.replace("x", "");
            float var18 = 1.0F;

            try {
               var18 = Float.parseFloat(var17);
            } catch (NumberFormatException var20) {
               var20.printStackTrace();
            }

            var13.h = var18;
            var13.i = var7.getCheckbox();
            var13.l = var8.getCheckbox();
            var13.f = var9.getValueAsInt((Integer)null).intValue();
            var13.g = var10.getValueAsInt(Integer.valueOf(1)).intValue();
            var2.bX.a(var13);
         }

      }
   }

   public void closeBattleroomIfOpen() {
      l var1 = l.B();
      Element var2 = this.libRocket.getActiveElementById("battleroomPage");
      if(var2 == null) {
         l.e("closeBattleroomIfOpen: battleroomPage==null");
      } else {
         this.libRocket.backToLastDocument();
      }
   }

   public void reinviteAsk() {
      String var1 = com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerReinvite.title", new Object[0]);
      String var2 = "While in-game you can only reinvite players who were in-game before but dropped out";
      String var3 = "reInvite:";
      var3 = var3 + "closePopup(); mp.showSteamInviteDialog();";
      boolean var4 = true;
      this.root.showPopup(var1, var2, var4, var3, (String)null);
   }

   public void showSteamInviteDialog() {
      com.corrodinggames.rts.gameFramework.o.a var1 = com.corrodinggames.rts.gameFramework.o.a.a();
      var1.g();
   }

   public void setMapFromPopup(String var1) {
      if(!this.isInControlOfServer()) {
         String var5 = this.root.getMapNameFromPath(var1);
         String var6 = "clicked on \'" + var5 + "\'";
         this.root.sendChatMessage(var6);
         this.root.closePopup();
      } else {
         l var2 = l.B();
         ah var3 = var2.bX.e();
         if(var3 != null) {
            String var4 = var1;
            if(!var1.contains("MOD|")) {
               var4 = f.k(var1);
            }

            var3.b = var4;
            var2.bX.a(var3);
         }

         this.root.closePopup();
      }
   }

   public void showMapSelect() {
      String var1 = this.root.getModeMapPath((Element)null, (String)null);
      this.root.showMapPopup(var1, "mp.setMapFromPopup");
   }

   public boolean isInControlOfServer() {
      l var1 = l.B();
      boolean var2 = var1.bX.C || var1.bX.H;
      return var2;
   }

   public void askPassword() {
      l.e("mp.askPassword()");
      l var1 = l.B();
      String var2 = "Password Required";
      String var3 = "This server requires a password to join";
      String var4 = "";
      this.root.showInputPopupNonClose(var2, var3, var4, "Close:mp.cancelPaswordAsk()", "[onenter]Join:mp.askPasswordEntered(getPopupText())");
   }

   public void askPasswordEntered(String var1) {
      l.e("mp.askPasswordEntered()");
      l var2 = l.B();
      var2.bX.n = var1;
      var2.bX.X();
      this.root.closePopup();
   }

   public void cancelPaswordAsk() {
      l var1 = l.B();
      if(var1.bX.C) {
         this.root.logWarn("cancelPaswordAsk: we are the server");
      } else {
         var1.bX.b("Cancel password");
         this.closeBattleroomIfOpen();
      }

      this.root.closePopup();
   }

   public void setupStartingUnitDropDown(Element var1, boolean var2) {
      String var3 = "";
      if(var2) {
         var3 = var3 + this.generateOption("-99", com.corrodinggames.rts.gameFramework.h.a.a("menus.settings.option.default", new Object[0]), false);
      }

      Multiplayer$DropdownOption var5;
      for(Iterator var4 = this.getStartingUnitOptions().iterator(); var4.hasNext(); var3 = var3 + this.generateOption(var5.key, var5.value, false)) {
         var5 = (Multiplayer$DropdownOption)var4.next();
      }

      var1.setInnerRML(var3);
   }

   public void setupPlayerColorDropDown(Element var1, boolean var2, boolean var3, n var4) {
      l var5 = l.B();
      String var6 = "";
      if(var2) {
         var6 = var6 + this.generateOption("-99", com.corrodinggames.rts.gameFramework.h.a.a("menus.settings.option.default", new Object[0]), false);
      }

      for(int var7 = 0; var7 < 10; ++var7) {
         boolean var8 = false;
         if(var3 && var5.bX.a(var7, var4)) {
            var8 = true;
         }

         String var9 = n.j(var7);
         var9 = al.d(var9);
         int var10 = var7;
         int var11 = var7;
         if(var8) {
            var9 = var9 + " (used)";
            var10 = -7829368;
            var11 = -99;
         }

         var6 = var6 + this.generateOption("" + var11, var9, false, Integer.valueOf(n.i(var10)), var8);
      }

      var1.setInnerRML(var6);
   }

   public ArrayList getStartingUnitOptions() {
      l var1 = l.B();
      ArrayList var2 = new ArrayList();
      Iterator var3 = var1.bX.i().iterator();

      while(var3.hasNext()) {
         Integer var4 = (Integer)var3.next();
         String var5 = var1.bX.d(var4.intValue());
         var2.add(new Multiplayer$DropdownOption(var4.toString(), var5));
      }

      return var2;
   }
}
