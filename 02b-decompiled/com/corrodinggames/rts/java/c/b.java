package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamFriends;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamMatchmaking;
import com.codedisaster.steamworks.SteamMatchmaking$LobbyType;
import com.codedisaster.steamworks.SteamNetworking;
import com.codedisaster.steamworks.SteamNetworking$API;
import com.codedisaster.steamworks.SteamUGC;
import com.codedisaster.steamworks.SteamUtils;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.java.c.b$1;
import com.corrodinggames.rts.java.c.c;
import com.corrodinggames.rts.java.c.d;
import com.corrodinggames.rts.java.c.e;
import com.corrodinggames.rts.java.c.f;
import com.corrodinggames.rts.java.c.g;
import com.corrodinggames.rts.java.c.k;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;

public class b extends com.corrodinggames.rts.gameFramework.o.a {

   d b;
   SteamFriends c;
   SteamMatchmaking d;
   e e;
   f f;
   g g;
   SteamNetworking h;
   c i;
   SteamUtils j;
   boolean k = false;
   HashMap l = new HashMap();
   ByteBuffer m;
   SteamID n;
   boolean o;
   SteamID p;


   public g n() {
      return this.g;
   }

   public void b() {
      if(this.k) {
         com.corrodinggames.rts.gameFramework.l.e("SteamEngine - init already called");
      } else {
         this.k = true;
         com.corrodinggames.rts.gameFramework.l.e("SteamEngine - java steamEngine init()");

         try {
            if(!SteamAPI.init()) {
               com.corrodinggames.rts.gameFramework.l.b("steamAPI init failed");
               this.d();
               return;
            }

            this.m = ByteBuffer.allocateDirect(100000);
            this.b = new d(this);
            this.c = new SteamFriends(this.b);
            this.e = new e(this);
            this.d = new SteamMatchmaking(this.e);
            this.f = new f(this);
            this.h = new SteamNetworking(this.f, SteamNetworking$API.Client);
            this.g = new g(this);

            SteamUGC var1;
            try {
               var1 = new SteamUGC(this.g.a());
            } catch (RuntimeException var3) {
               var3.printStackTrace();
               throw new SteamException("Failed to create workshop");
            }

            this.g.a(var1);
            this.i = new c(this);
            this.j = new SteamUtils(this.i);
         } catch (SteamException var4) {
            var4.printStackTrace();
            this.d();
         }

      }
   }

   public void a(float var1) {
      SteamAPI.runCallbacks();
      if(this.h != null) {
         if(com.corrodinggames.rts.gameFramework.l.aK != null) {
            com.corrodinggames.rts.gameFramework.l.e("Joining game from commandline invite:" + com.corrodinggames.rts.gameFramework.l.aK);
            long var2 = Long.parseLong(com.corrodinggames.rts.gameFramework.l.aK);
            com.corrodinggames.rts.gameFramework.l.aK = null;
            SteamID var4 = SteamID.createFromNativeHandle(var2);
            this.d.joinLobby(var4);
         }

         while(true) {
            int var9 = this.h.isP2PPacketAvailable(0);
            if(var9 == 0) {
               break;
            }

            if(var9 > this.m.capacity()) {
               com.corrodinggames.rts.gameFramework.l.b("nextPacketSize:" + var9 + " larger then byteBuffer:" + this.m.capacity() + " resizing");
               this.m = ByteBuffer.allocateDirect(var9);
            }

            SteamID var3 = new SteamID();

            try {
               this.m.clear();
               int var10 = this.h.readP2PPacket(var3, this.m, 0);
               if(var10 == 0) {
                  com.corrodinggames.rts.gameFramework.l.b("readP2PPacket with rtn==" + var10);
               }

               k var5 = (k)this.l.get(var3);
               if(var5 != null && var5.isClosed()) {
                  com.corrodinggames.rts.gameFramework.l.b("Removing stale steam socket");
                  this.l.remove(var3);
                  var5 = null;
               }

               if(var5 == null) {
                  this.b(var3);
                  var5 = (k)this.l.get(var3);
               }

               if(var5 == null) {
                  com.corrodinggames.rts.gameFramework.l.e("Could not find remote ID steamSocket: " + var3);
               } else {
                  int var6 = this.m.limit();
                  byte[] var7 = new byte[var6];
                  this.m.get(var7);
                  var5.c.a(var7);
               }
            } catch (SteamException var8) {
               var8.printStackTrace();
            }
         }
      }

   }

   public void d() {
      com.corrodinggames.rts.gameFramework.l.b("JavaSteamEngine: disableSteam");
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1 != null) {
         var1.i("Steam connection failed.");
      } else {
         com.corrodinggames.rts.gameFramework.l.e("cannot show alert game has not been created");
      }

      com.corrodinggames.rts.gameFramework.o.a.a = new com.corrodinggames.rts.gameFramework.o.a();
   }

   public String c() {
      return this.c.getPersonaName();
   }

   public boolean f() {
      return false;
   }

   public void a(String var1) {
      com.corrodinggames.rts.gameFramework.l.e("Steam: " + var1);
   }

   public void b(String var1) {
      com.corrodinggames.rts.gameFramework.l.b("Steam: " + var1);
   }

   public void i() {
      this.a("createLobby");
      if(this.n != null) {
         this.b("createLobby: activeLobby!=null");
      }

      this.d.createLobby(SteamMatchmaking$LobbyType.FriendsOnly, 10);
   }

   public synchronized void a(SteamID var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      this.n = var1;
   }

   public com.corrodinggames.rts.gameFramework.j.c b(SteamID var1) {
      com.corrodinggames.rts.gameFramework.l.e("addPeer: " + var1);
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      k var3 = (k)this.l.get(var1);
      if(var3 != null) {
         if(var3.isClosed()) {
            this.l.remove(var1);
         } else {
            this.b("addPeer, user already exists");

            try {
               var3.close();
            } catch (IOException var8) {
               var8.printStackTrace();
            }
         }
      }

      k var4 = new k(this, var1);
      com.corrodinggames.rts.gameFramework.j.c var5 = new com.corrodinggames.rts.gameFramework.j.c(var2.bX, var4);

      try {
         var5.i = true;
         var5.d();
         var2.bX.aM.add(var5);
         this.l.put(var1, var4);
         var2.bX.Q();
         return var5;
      } catch (IOException var7) {
         var7.printStackTrace();
         var5.a("crash");
         return null;
      }
   }

   public void c(SteamID var1) {
      com.corrodinggames.rts.gameFramework.l.e("connectTo: " + var1);
      k var2 = (k)this.l.get(var1);
      if(var2 != null) {
         if(var2.isClosed()) {
            this.l.remove(var1);
         } else {
            this.b("connectTo, user already exists");

            try {
               var2.close();
            } catch (IOException var6) {
               var6.printStackTrace();
            }
         }
      }

      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(!this.o) {
         b$1 var5 = new b$1(this, var1);
         ScriptEngine.getInstance().addRunnableToQueue(var5);
      } else {
         this.a("connectTo as server?");
         this.b(var1);
      }

   }

   public void j() {
      this.a("stopLobby");
      if(this.n == null) {
         this.b("stopLobby: activeLobby==null");
      } else {
         this.d.leaveLobby(this.n);
      }

      this.a("stopLobby: activeSteamSockets:" + this.l.size());
      Iterator var1 = this.l.values().iterator();

      while(var1.hasNext()) {
         k var2 = (k)var1.next();

         try {
            var2.close();
         } catch (IOException var4) {
            var4.printStackTrace();
         }
      }

      this.l.clear();
      this.n = null;
      this.p = null;
   }

   public void g() {
      if(this.n == null) {
         ;
      }

      if(this.n == null) {
         com.corrodinggames.rts.gameFramework.l.B().i("Error: No steam lobby has been started");
      } else {
         this.c.activateGameOverlayInviteDialog(this.n);
      }
   }

   public void k() {
      this.g.c();
   }

   public void l() {
      this.g.d();
   }

   public void m() {
      this.n().b();
   }

   public void a(com.corrodinggames.rts.gameFramework.i.b var1) {
      this.n().c(var1);
   }

   public void b(com.corrodinggames.rts.gameFramework.i.b var1) {
      this.n().b(var1);
   }

   public void a(com.corrodinggames.rts.gameFramework.i.b var1, boolean var2, String var3) {
      this.n().a(var1, var2, var3);
   }
}
