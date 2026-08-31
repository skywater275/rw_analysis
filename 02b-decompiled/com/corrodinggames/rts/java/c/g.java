package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamNativeHandle;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamRemoteStorage$PublishedFileVisibility;
import com.codedisaster.steamworks.SteamRemoteStorage$WorkshopFileType;
import com.codedisaster.steamworks.SteamUGC;
import com.codedisaster.steamworks.SteamUGC$ItemDownloadInfo;
import com.codedisaster.steamworks.SteamUGC$ItemInstallInfo;
import com.codedisaster.steamworks.SteamUGC$ItemState;
import com.codedisaster.steamworks.SteamUGCCallback;
import com.codedisaster.steamworks.SteamUGCQuery;
import com.codedisaster.steamworks.SteamUGCUpdateHandle;
import com.corrodinggames.rts.java.c.b;
import com.corrodinggames.rts.java.c.h;
import com.corrodinggames.rts.java.c.i;
import com.corrodinggames.rts.java.c.j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class g {

   boolean a = false;
   b b;
   SteamUGCCallback c;
   SteamUGC d;
   com.corrodinggames.rts.gameFramework.i.b e;
   boolean f;
   com.corrodinggames.rts.gameFramework.i.b g;
   ArrayList h = new ArrayList();
   HashMap i = new HashMap();
   ArrayList j = new ArrayList();
   int k;


   private void e() {
      if(this.j.size() != 0) {
         SteamUGCQuery var1 = this.d.createQueryUGCDetailsRequest((Collection)this.j);
         this.j.clear();
         this.d.sendQueryUGCRequest(var1);
         this.d.releaseQueryUserUGCRequest(var1);
         SteamAPI.runCallbacks();
      }
   }

   private void e(SteamPublishedFileID var1) {
      com.corrodinggames.rts.gameFramework.l.e("Adding request for workshop details: " + var1);
      this.j.add(var1);
   }

   public h a(SteamPublishedFileID var1) {
      h var2 = (h)this.i.get(var1);
      if(var2 == null) {
         com.corrodinggames.rts.gameFramework.l.e("New ItemInfo: " + var1);
         var2 = new h();
         var2.a = var1;
         this.i.put(var1, var2);
      }

      return var2;
   }

   public String b(SteamPublishedFileID var1) {
      h var2 = this.a(var1);
      if(var2.b == 0L && !var2.c) {
         var2.b = System.currentTimeMillis();
         this.e(var1);
      }

      if(var2.c) {
         com.corrodinggames.rts.gameFramework.l.e("Got data for: " + var1 + " - " + var2.d);
         return var2.d;
      } else {
         com.corrodinggames.rts.gameFramework.l.e("No data for: " + var1);
         return null;
      }
   }

   public i c(SteamPublishedFileID var1) {
      Iterator var2 = this.h.iterator();

      i var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (i)var2.next();
      } while(!var3.a.equals(var1));

      return var3;
   }

   public g(b var1) {
      this.b = var1;
      this.c = new j(this);
   }

   public void a(SteamUGC var1) {
      this.d = var1;
   }

   public SteamUGCCallback a() {
      return this.c;
   }

   public void a(com.corrodinggames.rts.gameFramework.i.b var1, String var2, boolean var3) {
      com.corrodinggames.rts.gameFramework.l.e("workshop: " + var2);
      if(var3) {
         com.corrodinggames.rts.gameFramework.l.B().i(var2);
      }

   }

   public boolean a(com.corrodinggames.rts.gameFramework.i.b var1) {
      if(var1.s == null) {
         this.a(var1, "A title is required in the file \'mod-info.txt\'", true);
         return false;
      } else if(!var1.r) {
         this.a(var1, "Please add and setup the file \'mod-info.txt\' to this mod before uploading", true);
         return false;
      } else {
         String var2 = var1.p();
         if(var2 != null && !com.corrodinggames.rts.gameFramework.e.a.i(var2)) {
            this.a(var1, "Could not find thumbnail file: " + var2 + " referenced mod-info.txt", true);
            return false;
         } else {
            return true;
         }
      }
   }

   public void b(com.corrodinggames.rts.gameFramework.i.b var1) {
      if(this.e != null) {
         this.a(var1, "A mod is already pending publishing", false);
      } else if(var1.k != 0L) {
         this.a(var1, "This mod has already been published", false);
      } else if(this.a(var1)) {
         if(!var1.a(0L)) {
            this.a(var1, "Failed to write metadata to mod, check file permissions", true);
         } else {
            this.e = var1;
            int var2 = this.b.j.getAppID();
            this.d.createItem(var2, SteamRemoteStorage$WorkshopFileType.Community);
         }
      }
   }

   public void a(com.corrodinggames.rts.gameFramework.i.b var1, boolean var2, String var3) {
      if(this.a(var1)) {
         int var4 = this.b.j.getAppID();
         SteamPublishedFileID var5 = new SteamPublishedFileID(var1.k);
         SteamUGCUpdateHandle var6 = this.d.startItemUpdate(var4, var5);
         if(var2) {
            if(var1.s != null) {
               this.d.setItemTitle(var6, var1.s);
            }

            if(var1.u != null) {
               this.d.setItemDescription(var6, var1.u);
            }
         }

         if(var2) {
            this.d.setItemVisibility(var6, SteamRemoteStorage$PublishedFileVisibility.Public);
         }

         String var7 = var1.p();
         if(var7 != null) {
            this.d.setItemPreview(var6, var7);
         }

         String var8 = var1.c("tags");
         if(var8 != null) {
            String[] var9 = var8.split(",");

            for(int var10 = 0; var10 < var9.length; ++var10) {
               var9[var10] = var9[var10].trim();
               com.corrodinggames.rts.gameFramework.l.e("Adding tag:" + var9[var10]);
            }

            this.d.setItemTags(var6, var9);
         }

         String var11 = var1.i();
         com.corrodinggames.rts.gameFramework.l.e("convertedAbsolutePath:" + var11);
         this.d.setItemContent(var6, var11);
         var1.S = "Uploading to workshop";
         this.f = var2;
         this.g = var1;
         this.d.submitItemUpdate(var6, var3);
         com.corrodinggames.rts.gameFramework.l.e("submitted item update for:" + var1.k);
      }
   }

   public void b() {
      int var1 = this.b.j.getAppID();
      this.b.c.activateGameOverlayToWebPage("http://steamcommunity.com/workshop/browse/?appid=" + var1);
   }

   public void c(com.corrodinggames.rts.gameFramework.i.b var1) {
      this.b.c.activateGameOverlayToWebPage("steam://url/CommunityFilePage/" + var1.k);
   }

   public long d(SteamPublishedFileID var1) {
      return SteamNativeHandle.getNativeHandle(var1);
   }

   public void c() {
      if(this.a) {
         this.a = false;
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         var1.bZ.m();
      }

      int var3 = this.d.getNumSubscribedItems();
      if(var3 != this.k) {
         com.corrodinggames.rts.gameFramework.l.e("Number of subscribed items changed from: " + this.k + " to: " + var3);
         this.k = var3;
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         this.d();
         this.a = true;
      }

   }

   public void d() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      SteamAPI.runCallbacks();
      com.corrodinggames.rts.gameFramework.l.e("--------------");
      com.corrodinggames.rts.gameFramework.l.e("Steam: loadWorkshopMods");
      int var2 = this.d.getNumSubscribedItems();
      this.k = var2;
      SteamPublishedFileID[] var3 = new SteamPublishedFileID[var2];
      this.d.getSubscribedItems(var3);
      SteamPublishedFileID[] var4 = var3;
      int var5 = var3.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         SteamPublishedFileID var7 = var4[var6];
         Collection var8 = this.d.getItemState(var7);
         long var9 = this.d(var7);
         boolean var11 = false;
         boolean var12 = false;
         boolean var13 = false;
         boolean var14 = false;
         boolean var15 = false;
         String var16 = null;
         Iterator var17 = var8.iterator();

         while(var17.hasNext()) {
            SteamUGC$ItemState var18 = (SteamUGC$ItemState)var17.next();
            if(var18 != SteamUGC$ItemState.None) {
               if(var16 == null) {
                  var16 = "" + var18;
               } else {
                  var16 = var16 + ", " + var18;
               }

               if(var18 == SteamUGC$ItemState.Downloading) {
                  var12 = true;
               }

               if(var18 == SteamUGC$ItemState.DownloadPending) {
                  var12 = true;
                  var13 = true;
               }

               if(var18 == SteamUGC$ItemState.Installed) {
                  var11 = true;
               }

               if(var18 == SteamUGC$ItemState.NeedsUpdate) {
                  var14 = true;
               }

               if(var18 == SteamUGC$ItemState.Subscribed) {
                  var15 = true;
               }
            }
         }

         com.corrodinggames.rts.gameFramework.l.e("Found workshop item " + var9 + " with state: " + var16 + "");
         String var27 = "sw" + var9;
         String var28 = "(Workshop item - " + var9 + ")";
         if(!var11) {
            String var19 = this.b(var7);
            if(var19 != null) {
               var28 = var19;
            }
         }

         String var20 = null;
         String var21 = null;
         if(!var15) {
            com.corrodinggames.rts.gameFramework.l.e("Skipping: " + var9 + " as it is not subscribed");
         } else {
            if(var11) {
               SteamUGC$ItemInstallInfo var22 = new SteamUGC$ItemInstallInfo();
               this.d.getItemInstallInfo(var7, var22);
               var20 = var22.getFolder();
               com.corrodinggames.rts.gameFramework.l.e(" Installed at: " + var20);
            } else {
               com.corrodinggames.rts.gameFramework.l.e(" Not installed");
               var21 = "Not installed.";
               if(var14) {
                  var21 = "Update needed..";
               }

               if(var13) {
                  var21 = "Download pending in steam...";
               } else if(var12) {
                  var21 = "Steam is downloading files..";
                  SteamUGC$ItemDownloadInfo var29 = new SteamUGC$ItemDownloadInfo();
                  if(this.d.getItemDownloadInfo(var7, var29)) {
                     double var23 = (double)var29.getBytesDownloaded() / (double)var29.getBytesTotal();
                     var21 = var21 + " " + com.corrodinggames.rts.gameFramework.f.c(var23 * 100.0D) + "%";
                  }
               }
            }

            boolean var30 = true;
            boolean var31 = true;
            boolean var24 = false;
            byte var25 = 0;
            if(!var11) {
               var25 = -1;
            }

            com.corrodinggames.rts.gameFramework.i.b var26 = var1.bZ.a(var27, var27, var20, var27, var30, var31, var24, var25);
            if(var26.s == null) {
               var26.t = var28;
            }

            if(var21 == null && var14) {
               if(var13) {
                  var21 = "An update is pending download in Steam.";
               } else if(var13) {
                  var21 = "An update is downloading...";
               } else {
                  var21 = "An update is available.";
               }
            }

            var26.T = var21;
            if((!var11 || var14) && (!var12 || var13)) {
               com.corrodinggames.rts.gameFramework.l.e("Queuing download on: " + var7);
               this.d.downloadItem(var7, false);
            }
         }
      }

      this.e();
   }
}
