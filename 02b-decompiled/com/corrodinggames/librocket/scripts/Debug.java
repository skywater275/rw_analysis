package com.corrodinggames.librocket.scripts;

import a.a.h;
import com.corrodinggames.librocket.scripts.Debug$1;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.ScriptContext;
import com.corrodinggames.rts.a.a;
import com.corrodinggames.rts.a.a.b;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.ah;
import com.corrodinggames.rts.gameFramework.j.ai;
import com.corrodinggames.rts.gameFramework.j.ao;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.k.d;
import com.corrodinggames.rts.gameFramework.k.m;
import com.corrodinggames.rts.gameFramework.k.p;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import sun.management.VMManagement;

public class Debug extends ScriptContext {

   Root root;
   boolean allFeatures;
   ConcurrentLinkedQueue backgroundClientConnections;
   Thread backgroundConnectionThread;
   Runnable backgroundConnectionRunnable = new Debug$1(this);
   boolean forceNonThreaded = true;


   Debug(Root var1) {
      this.root = var1;
   }

   public int currentPid() {
      try {
         RuntimeMXBean var1 = ManagementFactory.getRuntimeMXBean();
         Field var2 = var1.getClass().getDeclaredField("jvm");
         var2.setAccessible(true);
         VMManagement var3 = (VMManagement)var2.get(var1);
         Method var4 = var3.getClass().getDeclaredMethod("getProcessId", new Class[0]);
         var4.setAccessible(true);
         int var5 = ((Integer)var4.invoke(var3, new Object[0])).intValue();
         return var5;
      } catch (Exception var6) {
         var6.printStackTrace();
         return -1;
      }
   }

   public void setLocalPlayerName(String var1) {
      l var2 = l.B();
      var2.bX.a(var1);
   }

   public void setDdosProtection(boolean var1) {
      ao.b = var1;
   }

   public void lookAt(float var1, float var2) {
      l var3 = l.B();
      var3.b(var1, var2);
   }

   public void createManyUnits(String var1, float var2, float var3, int var4, boolean var5, int var6) {
      int var7 = 0;
      int var8 = 0;

      for(int var9 = 0; var9 < var6; ++var9) {
         var7 += 9;
         if(var7 > 400) {
            var7 = 0;
            var8 += 9;
         }

         this.createUnit(var1, var2 + (float)var7, var3 + (float)var8, var4, var9 == 0?var5:false);
      }

   }

   public Long createUnit(String var1, float var2, float var3, int var4, boolean var5) {
      l var6 = l.B();
      as var7 = ar.a(var1);
      if(var7 == null) {
         this.root.logWarn("Could not find type:" + var1);
         return null;
      } else {
         am var8 = var7.a();
         var8.eo = var2;
         var8.ep = var3;

         try {
            var8.Q(var4);
         } catch (f var10) {
            throw new RuntimeException(var10);
         }

         n.c(var8);
         var8.cK = true;
         if(var5) {
            var6.b(var2, var3);
         }

         return Long.valueOf(var8.eh);
      }
   }

   public int getMaxCustomUnitTypeId() {
      return com.corrodinggames.rts.game.units.custom.l.d.size();
   }

   public Long createCustomUnitFromTypeId(int var1, float var2, float var3, int var4, boolean var5) {
      l var6 = l.B();
      com.corrodinggames.rts.game.units.custom.l var7 = (com.corrodinggames.rts.game.units.custom.l)com.corrodinggames.rts.game.units.custom.l.d.get(var1);
      am var8 = var7.a();
      var8.eo = var2;
      var8.ep = var3;

      try {
         var8.Q(var4);
      } catch (f var10) {
         throw new RuntimeException(var10);
      }

      n.c(var8);
      var8.cK = true;
      if(var5) {
         var6.b(var2, var3);
      }

      return Long.valueOf(var8.eh);
   }

   public void enableFeatures(String var1) {
      String var2 = com.corrodinggames.rts.gameFramework.f.e(var1);
      if(var2.startsWith("221FC410BD29D786")) {
         this.allFeatures = true;
         a.d = true;
      } else {
         throw new RuntimeException("unknown");
      }
   }

   public void selectNextUnit() {
      l var1 = l.B();
      am var2 = null;
      boolean var3 = false;
      Iterator var4 = am.bF().iterator();

      while(var4.hasNext()) {
         am var5 = (am)var4.next();
         if(var5 instanceof am && !(var5 instanceof al) && !var5.t()) {
            if(var2 == null) {
               var2 = var5;
            }

            if(var3) {
               var2 = var5;
               break;
            }

            var3 = var5.cG;
         }
      }

      var1.bS.y();
      if(var2 != null) {
         var1.bS.j(var2);
      }

   }

   public void removeAllUnits() {
      Iterator var1 = w.dK().iterator();

      while(var1.hasNext()) {
         w var2 = (w)var1.next();
         var2.a();
      }

   }

   public void killAllUnits() {
      Iterator var1 = am.bF().iterator();

      while(var1.hasNext()) {
         am var2 = (am)var1.next();
         if(var2 instanceof am) {
            var2.cu = -1.0F;
         }
      }

   }

   public boolean backgroundCurrentClientConnection() {
      if(!this.allFeatures) {
         return false;
      } else {
         l var1 = l.B();
         if(!var1.bX.B) {
            l.e("Not networked");
            return false;
         } else if(var1.bX.C) {
            throw new RuntimeException("server=true");
         } else {
            if(this.backgroundConnectionThread == null) {
               this.backgroundConnectionThread = new Thread(this.backgroundConnectionRunnable);
               this.backgroundConnectionThread.start();
            }

            if(this.backgroundClientConnections == null) {
               this.backgroundClientConnections = new ConcurrentLinkedQueue();
            }

            Iterator var2 = var1.bX.aM.iterator();

            while(var2.hasNext()) {
               c var3 = (c)var2.next();
               var3.t = true;
               this.backgroundClientConnections.add(var3);
               var1.bX.aM.remove(var3);
            }

            var1.bX.b("backgrounded");
            var1.bX.B = true;
            return true;
         }
      }
   }

   public boolean isTeamWipedOut(int var1) {
      n var2 = n.k(var1);
      if(var2 == null) {
         this.root.logWarn("Could not find team:" + var1);
         return true;
      } else {
         return var2.G;
      }
   }

   public boolean isTeamDefeated(int var1) {
      n var2 = n.k(var1);
      if(var2 == null) {
         this.root.logWarn("Could not find team:" + var1);
         return true;
      } else {
         return var2.G;
      }
   }

   public boolean isTeamInVictory(int var1) {
      n var2 = n.k(var1);
      if(var2 == null) {
         this.root.logWarn("Could not find team:" + var1);
         return false;
      } else {
         return var2.H;
      }
   }

   public String getPlayerName(int var1) {
      n var2 = n.k(var1);
      if(var2 == null) {
         this.root.logWarn("Could not find team:" + var1);
         return null;
      } else {
         return var2.v;
      }
   }

   public String getQueryStringOfPlayer(int var1) {
      l var2 = l.B();
      n var3 = n.k(var1);
      if(var3 == null) {
         this.root.logWarn("Could not find team:" + var1);
         return null;
      } else {
         c var4 = var2.bX.c(var3);
         if(var4 == null) {
            this.root.logWarn("Found team but could not find connection for team:" + var1);
            return null;
         } else {
            return var4.o;
         }
      }
   }

   public boolean setTeamCredits(int var1, int var2) {
      n var3 = n.k(var1);
      if(var3 == null) {
         this.root.logWarn("Could not find team:" + var1);
         return false;
      } else {
         var3.o = (double)var2;
         return true;
      }
   }

   public boolean setTeamAllyGroup(int var1, int var2) {
      n var3 = n.k(var1);
      if(var3 == null) {
         this.root.logWarn("Could not find team:" + var1);
         return false;
      } else {
         var3.r = var2;
         return true;
      }
   }

   public void giveUpgradeToAllUnits() {
      Iterator var1 = am.bF().iterator();

      while(var1.hasNext()) {
         am var2 = (am)var1.next();
         if(var2 instanceof y) {
            y var3 = (y)var2;
            com.corrodinggames.rts.game.units.a.c var4 = var3.cm();
            s var5 = var3.a(var4);
            if(var5 != null) {
               var3.a(var5, false);
            }
         }
      }

   }

   public void giveAllActionsToAllUnits() {
      Iterator var1 = am.bF().iterator();

      while(var1.hasNext()) {
         am var2 = (am)var1.next();
         if(var2 instanceof y) {
            y var3 = (y)var2;
            Iterator var4 = var3.N().iterator();

            while(var4.hasNext()) {
               s var5 = (s)var4.next();
               var3.a(var5, false);
            }
         }
      }

   }

   public void completeAllUnitsQueues() {
      Iterator var1 = am.bF().iterator();

      while(var1.hasNext()) {
         am var2 = (am)var1.next();
         if(var2 instanceof com.corrodinggames.rts.game.units.d.l) {
            com.corrodinggames.rts.game.units.d.l var3 = (com.corrodinggames.rts.game.units.d.l)var2;
            var3.dz();
         }
      }

   }

   public boolean moveAllUnitsOnTeam(int var1, float var2, float var3) {
      n var4 = n.k(var1);
      if(var4 == null) {
         this.root.logWarn("Could not find team:" + var1);
         return false;
      } else {
         l var5 = l.B();
         e var6 = var5.cf.b(var4);
         Iterator var7 = am.bF().iterator();

         while(var7.hasNext()) {
            am var8 = (am)var7.next();
            if(var8 instanceof y) {
               y var9 = (y)var8;
               if(var9.bX == var4) {
                  var6.a(var9);
               }
            }
         }

         var6.a(var2, var3);
         return true;
      }
   }

   public void showMessage(String var1) {
      l var2 = l.B();
      if(var1 != null && !var1.trim().equals("")) {
         var1 = var1.replace("\\n", "\n");
         var2.bX.m(var1);
      }
   }

   public String unicodeTest1() {
      return "start ¥123 ؜ end";
   }

   public void setZoom(float var1) {
      l var2 = l.B();
      var2.cV = var1;
   }

   public boolean isNetworkGameActive() {
      l var1 = l.B();
      return var1.N();
   }

   public int getLocalPlayerId() {
      l var1 = l.B();
      return var1.bX.z.k;
   }

   public int numberOfHumanPlayers() {
      l var1 = l.B();
      return var1.bX.an();
   }

   public int numberOfPlayersPlusAI() {
      l var1 = l.B();
      return var1.bX.ao();
   }

   public int numberOfPlayerConnections() {
      l var1 = l.B();
      return var1.bX.B();
   }

   public boolean enableFastSync() {
      l var1 = l.B();
      var1.bX.ai = 30;
      return true;
   }

   public boolean enableExtraNetworkDebug() {
      l var1 = l.B();
      var1.bX.g = true;
      return true;
   }

   public boolean throwIfAnyPlayerNotInSync() {
      l var1 = l.B();
      var1.bX.x();
      return true;
   }

   public boolean enableFastResyncTimer() {
      ad.c = true;
      return true;
   }

   public boolean enablePauseOnDesync() {
      l var1 = l.B();
      var1.bX.aj = true;
      return true;
   }

   public boolean networkSetIncomeMultiplier(float var1) {
      l var2 = l.B();
      ah var3 = var2.bX.e();
      var3.h = var1;
      var2.bX.a(var3);
      return true;
   }

   public boolean networkSetPortNumber(int var1) {
      l var2 = l.B();
      var2.bQ.networkPort = var1;
      return true;
   }

   public boolean networkSetUdp(boolean var1) {
      l var2 = l.B();
      var2.bQ.udpInMultiplayer = var1;
      return true;
   }

   public boolean networkDisconnect() {
      l var1 = l.B();
      var1.bX.b("debug");
      return true;
   }

   public boolean networkAbort() {
      l var1 = l.B();
      Iterator var2 = var1.bX.aM.iterator();

      while(var2.hasNext()) {
         c var3 = (c)var2.next();
         if(var3.d instanceof h) {
            l.e("Closing: " + var3.g());
            ((h)var3.d).d();
         }
      }

      var1.bX.b("debug");
      return true;
   }

   public boolean disableNetworkOwnInfo() {
      ad.r = false;
      return true;
   }

   public boolean networkPause() {
      l var1 = l.B();
      var1.bX.aj = true;
      var1.bX.ak = true;
      return true;
   }

   public boolean plainTextDebugSave(boolean var1) {
      l var2 = l.B();
      com.corrodinggames.rts.gameFramework.y.a = var1;
      return true;
   }

   public boolean checkDesync(int var1) {
      l var2 = l.B();
      if(var2.bX.ap != 0) {
         throw new RuntimeException("numberOfDesyncErrors==" + var2.bX.ap);
      } else if(var2.bX.aq < var1) {
         throw new RuntimeException("game.network.numberOfDesyncPasses:" + var2.bX.aq + "<" + var1);
      } else {
         this.root.logDebug("numberOfDesyncPasses:" + var2.bX.aq);
         return true;
      }
   }

   public int getNumberOfDesyncErrors() {
      l var1 = l.B();
      return var1.bX.ap;
   }

   public int getNumberOfDesyncPasses() {
      l var1 = l.B();
      return var1.bX.aq;
   }

   public int getNumberOfResyncSendsOrRecv() {
      l var1 = l.B();
      return var1.bX.ar;
   }

   public boolean setMultiplayerMap(int var1, String var2) {
      l var3 = l.B();
      ah var4 = var3.bX.ay;
      var4.a = ai.values()[var1];
      var4.b = var2;
      return true;
   }

   public boolean setMultiplayerSave(String var1) {
      l var2 = l.B();
      ah var3 = var2.bX.ay;
      var3.a = ai.c;
      var3.b = var1;
      return true;
   }

   public void generateNewClientId() {
      l var1 = l.B();
      var1.bX.Y();
   }

   public void disableFog() {
      l var1 = l.B();
   }

   public void overrideDeltaSpeed(float var1) {
      l var2 = l.B();
      var2.bu = var1;
   }

   public void setGameSetting(String var1, String var2) {
      l var3 = l.B();
      var3.bQ.setValueDynamic(var1, var2);
   }

   public void setNetworkaiDifficulty(int var1) {
      l var2 = l.B();
      ah var3 = var2.bX.e();
      var3.f = var1;
      var2.bX.a(var3);
   }

   public void setNetworkStartingUnits(int var1) {
      l var2 = l.B();
      ah var3 = var2.bX.e();
      var3.g = var1;
      var2.bX.a(var3);
   }

   public void startRandomUnitDesyncTest() {
      l var1 = l.B();
      e var2 = var1.cf.b();
      var2.i = n.i;
      var2.r = true;
      var2.u = 1;
      var1.bX.a(var2);
   }

   public void startRandomUnitStressTest() {
      l var1 = l.B();
      e var2 = var1.cf.b();
      var2.i = n.i;
      var2.r = true;
      var2.u = 2;
      var1.bX.a(var2);
   }

   public void runAllUnitTests() {
      this.root.logWarn("Running unit tests..");
      com.corrodinggames.rts.a.a.n var1 = new com.corrodinggames.rts.a.a.n();
      var1.a();
   }

   public void runAllLeakTests() {
      this.root.logWarn("Running leak tests..");
      b var1 = new b();
      var1.a();
   }

   public boolean loadSaveFromSystemPath(String var1) {
      l var2 = l.B();

      try {
         File var3 = new File(var1);
         FileInputStream var4 = new FileInputStream(var3);
         BufferedInputStream var5 = new BufferedInputStream(var4);
         DataInputStream var6 = new DataInputStream(var5);
         k var7 = new k(var6);

         boolean var8;
         try {
            var8 = var2.ca.a(var7, false, false, false);
         } finally {
            var6.close();
            var5.close();
            var4.close();
         }

         return var8;
      } catch (IOException var13) {
         throw new RuntimeException(var13);
      }
   }

   public void checkTeamCaches() {
      Iterator var1 = n.c().iterator();

      n var2;
      do {
         if(!var1.hasNext()) {
            return;
         }

         var2 = (n)var1.next();
      } while(!var2.t());

      throw new RuntimeException("Team cache difference on team:" + var2.k);
   }

   public void setPathSpeedConf(boolean var1) {
      this.forceNonThreaded = var1;
   }

   public float getPathSpeed(int var1, float var2, float var3, float var4, float var5) {
      l var6 = l.B();
      com.corrodinggames.rts.game.b.b var7 = var6.bL;
      ArrayList var8 = new ArrayList();
      var7.a(var4, var5);
      int var9 = var7.T;
      int var10 = var7.U;
      long var11 = br.a();
      d.a = 0;
      d.b = 0;
      d.c = 0;
      d.d = 0;
      d.e = 0;
      d.f = 0;
      d.g = 0;
      d.h = 0.0D;
      d.i = 0.0D;
      m.c = 0;
      d.u = 0;

      for(int var13 = 0; var13 < var1; ++var13) {
         com.corrodinggames.rts.gameFramework.k.k var14 = var6.bU.a(false);
         var7.a(var2, var3);
         var14.a(com.corrodinggames.rts.game.units.ao.b, (short)var7.T, (short)var7.U, (Float)null, false);
         var7.a(var4, var5);
         var14.a((short)var7.T, (short)var7.U, (short)0);
         var14.p = true;
         var14.q = 0;
         var14.r = false;
         var6.bU.a(var14, false, this.forceNonThreaded);
         var8.add(var14);
      }

      if(!this.forceNonThreaded) {
         return -1.0F;
      } else {
         float var21 = br.a(var11);
         int var22 = -1;

         int var18;
         for(Iterator var15 = var8.iterator(); var15.hasNext(); var22 = var18) {
            com.corrodinggames.rts.gameFramework.k.k var16 = (com.corrodinggames.rts.gameFramework.k.k)var15.next();
            LinkedList var17 = var16.a();
            var18 = 0;

            for(Iterator var19 = var17.iterator(); var19.hasNext(); ++var18) {
               p var20 = (p)var19.next();
            }

            if(var22 != -1 && var22 != var18) {
               String var23 = "pathDistance inconsistency detected:" + var22 + "!=" + var18;
               l.b(var23);
            }

            p var24 = (p)var17.getLast();
            if(var24.a != var9 || var24.b != var10) {
               String var25 = "path did not react goal, got to:" + var24.a + "," + var24.b + " (vs " + var9 + ", " + var10 + ")";
               l.b(var25);
            }
         }

         l.b("hotBufferWatermark:" + d.a + ", nodesAdded:" + d.d + ", mainQueueWatermark:" + d.b + ", backlogWatermark:" + d.c + ", scannedA:" + d.e + ", scannedB:" + d.f + ", scannedC:" + d.g + ", time:" + br.a(d.i) + "/" + br.a(d.h) + ", dirtyPeak:" + d.u + ", dis:" + var22);
         if(m.c != 0) {
            l.b("newNodesCreated:" + m.c);
         }

         return var21;
      }
   }

   public void muteSounds() {
      l var1 = l.B();
      var1.bM.b = true;
      var1.bN.f();
   }

   public void pong() {}
}
