/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.input;
import com.corrodinggames.rts.java.GameWindow;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAPICall;
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
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.input.SteamManager;
import com.corrodinggames.rts.java.input.h;
import com.corrodinggames.rts.java.input.i;
import com.corrodinggames.rts.java.input.j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SteamWorkshop {
    boolean a = false;
    SteamManager b;
    SteamUGCCallback c;
    SteamUGC d;
    com.corrodinggames.rts.gameFramework.mods.ModInfo e;
    boolean f;
    com.corrodinggames.rts.gameFramework.mods.ModInfo g;
    ArrayList h = new ArrayList();
    HashMap i = new HashMap();
    ArrayList j = new ArrayList();
    int k;

    private void e() {
        if (this.j.size() == 0) {
            return;
        }
        SteamUGCQuery steamUGCQuery = this.d.createQueryUGCDetailsRequest(this.j);
        this.j.clear();
        this.d.sendQueryUGCRequest(steamUGCQuery);
        this.d.releaseQueryUserUGCRequest(steamUGCQuery);
        SteamAPI.runCallbacks();
    }

    private void e(SteamPublishedFileID steamPublishedFileID) {
        GlobalState.e("Adding request for workshop details: " + steamPublishedFileID);
        this.j.add(steamPublishedFileID);
    }

    public h a(SteamPublishedFileID steamPublishedFileID) {
        h h2 = (h)this.i.get(steamPublishedFileID);
        if (h2 == null) {
            GlobalState.e("New ItemInfo: " + steamPublishedFileID);
            h2 = new h();
            h2.a = steamPublishedFileID;
            this.i.put(steamPublishedFileID, h2);
        }
        return h2;
    }

    public String b(SteamPublishedFileID steamPublishedFileID) {
        h h2 = this.a(steamPublishedFileID);
        if (h2.b == 0L && !h2.c) {
            h2.b = System.currentTimeMillis();
            this.e(steamPublishedFileID);
        }
        if (h2.c) {
            GlobalState.e("Got data for: " + steamPublishedFileID + " - " + h2.d);
            return h2.d;
        }
        GlobalState.e("No data for: " + steamPublishedFileID);
        return null;
    }

    public i c(SteamPublishedFileID steamPublishedFileID) {
        for (i i2 : (java.util.Collection<i>) (java.util.Collection) this.h) {
            if (!i2.a.equals(steamPublishedFileID)) continue;
            return i2;
        }
        return null;
    }

    public SteamWorkshop(SteamManager b2) {
        this.b = b2;
        this.c = new j(this);
    }

    public void a(SteamUGC steamUGC) {
        this.d = steamUGC;
    }

    public SteamUGCCallback a() {
        return this.c;
    }

    public void a(com.corrodinggames.rts.gameFramework.mods.ModInfo b2, String string, boolean bl) {
        GlobalState.e("workshop: " + string);
        if (bl) {
            GlobalState.B().i(string);
        }
    }

    public boolean a(com.corrodinggames.rts.gameFramework.mods.ModInfo b2) {
        if (b2.workshopId == null) {
            this.a(b2, "A title is required in the file 'mod-info.txt'", true);
            return false;
        }
        if (!b2.modInfoParsed) {
            this.a(b2, "Please add and setup the file 'mod-info.txt' to this mod before uploading", true);
            return false;
        }
        String string = b2.p();
        if (string != null && !com.corrodinggames.rts.gameFramework.filesystem.FileLoader.i(string)) {
            this.a(b2, "Could not find thumbnail file: " + string + " referenced mod-info.txt", true);
            return false;
        }
        return true;
    }

    public void b(com.corrodinggames.rts.gameFramework.mods.ModInfo b2) {
        if (this.e != null) {
            this.a(b2, "A mod is already pending publishing", false);
            return;
        }
        if (b2.k != 0L) {
            this.a(b2, "This mod has already been published", false);
            return;
        }
        if (!this.a(b2)) {
            return;
        }
        if (!b2.a(0L)) {
            this.a(b2, "Failed to write metadata to mod, check file permissions", true);
            return;
        }
        this.e = b2;
        int n2 = this.b.j.getAppID();
        SteamAPICall steamAPICall = this.d.createItem(n2, SteamRemoteStorage$WorkshopFileType.Community);
    }

    public void a(com.corrodinggames.rts.gameFramework.mods.ModInfo b2, boolean bl, String string) {
        String[] stringArray;
        String string2;
        String string3;
        if (!this.a(b2)) {
            return;
        }
        int n2 = this.b.j.getAppID();
        SteamPublishedFileID steamPublishedFileID = new SteamPublishedFileID(b2.k);
        SteamUGCUpdateHandle steamUGCUpdateHandle = this.d.startItemUpdate(n2, steamPublishedFileID);
        if (bl) {
            if (b2.workshopId != null) {
                this.d.setItemTitle(steamUGCUpdateHandle, b2.workshopId);
            }
            if (b2.modCategory != null) {
                this.d.setItemDescription(steamUGCUpdateHandle, b2.modCategory);
            }
        }
        if (bl) {
            this.d.setItemVisibility(steamUGCUpdateHandle, SteamRemoteStorage$PublishedFileVisibility.Public);
        }
        if ((string3 = b2.p()) != null) {
            this.d.setItemPreview(steamUGCUpdateHandle, string3);
        }
        if ((string2 = b2.c("tags")) != null) {
            stringArray = string2.split(",");
            for (int k = 0; k < stringArray.length; ++k) {
                stringArray[k] = stringArray[k].trim();
                GlobalState.e("Adding tag:" + stringArray[k]);
            }
            this.d.setItemTags(steamUGCUpdateHandle, stringArray);
        }
        String string6 = b2.i();
        GlobalState.e("convertedAbsolutePath:" + string6);
        this.d.setItemContent(steamUGCUpdateHandle, string6);
        b2.S = "Uploading to workshop";
        this.f = bl;
        this.g = b2;
        this.d.submitItemUpdate(steamUGCUpdateHandle, string);
        GlobalState.e("submitted item update for:" + b2.k);
    }

    public void b() {
        int n2 = this.b.j.getAppID();
        this.b.c.activateGameOverlayToWebPage("http://steamcommunity.com/workshop/browse/?appid=" + n2);
    }

    public void c(com.corrodinggames.rts.gameFramework.mods.ModInfo b2) {
        this.b.c.activateGameOverlayToWebPage("steam://url/CommunityFilePage/" + b2.k);
    }

    public long d(SteamPublishedFileID steamPublishedFileID) {
        return SteamNativeHandle.getNativeHandle(steamPublishedFileID);
    }

    public void c() {
        int n2;
        if (this.a) {
            this.a = false;
            GlobalState l2 = GlobalState.B();
            l2.bZ.m();
        }
        if ((n2 = this.d.getNumSubscribedItems()) != this.k) {
            GlobalState.e("Number of subscribed items changed from: " + this.k + " to: " + n2);
            this.k = n2;
            GlobalState l3 = GlobalState.B();
            this.d();
            this.a = true;
        }
    }

    public void d() {
        int n2;
        GlobalState l2 = GlobalState.B();
        SteamAPI.runCallbacks();
        GlobalState.e("--------------");
        GlobalState.e("Steam: loadWorkshopMods");
        this.k = n2 = this.d.getNumSubscribedItems();
        SteamPublishedFileID[] steamPublishedFileIDArray = new SteamPublishedFileID[n2];
        this.d.getSubscribedItems(steamPublishedFileIDArray);
        for (SteamPublishedFileID steamPublishedFileID : steamPublishedFileIDArray) {
            String string;
            String object2;  // 02b g.java L288: String var28
            Collection collection = this.d.getItemState(steamPublishedFileID);
            long l3 = this.d(steamPublishedFileID);
            boolean bl = false;
            boolean bl2 = false;
            boolean bl3 = false;
            boolean bl4 = false;
            boolean bl5 = false;
            String string2 = null;
            for (Object object2_242 : collection) {
                if (object2_242 == SteamUGC$ItemState.None) continue;
                string2 = string2 == null ? "" + (Object)object2_242 : string2 + ", " + (Object)object2_242;
                if (object2_242 == SteamUGC$ItemState.Downloading) {
                    bl2 = true;
                }
                if (object2_242 == SteamUGC$ItemState.DownloadPending) {
                    bl2 = true;
                    bl3 = true;
                }
                if (object2_242 == SteamUGC$ItemState.Installed) {
                    bl = true;
                }
                if (object2_242 == SteamUGC$ItemState.NeedsUpdate) {
                    bl4 = true;
                }
                if (object2_242 != SteamUGC$ItemState.Subscribed) continue;
                bl5 = true;
            }
            GlobalState.e("Found workshop item " + l3 + " with state: " + string2 + "");
            String string3 = "sw" + l3;
            object2 = "(Workshop item - " + l3 + ")";
            if (!bl && (string = this.b(steamPublishedFileID)) != null) {
                object2 = string;
            }
            string = string3;
            String string4 = null;
            String string5 = null;
            if (!bl5) {
                GlobalState.e("Skipping: " + l3 + " as it is not subscribed");
                continue;
            }
            if (bl) {
                SteamUGC$ItemInstallInfo steamUGC$ItemInstallInfo = new SteamUGC$ItemInstallInfo();
                this.d.getItemInstallInfo(steamPublishedFileID, steamUGC$ItemInstallInfo);
                string4 = steamUGC$ItemInstallInfo.getFolder();
                GlobalState.e(" Installed at: " + string4);
            } else {
                GlobalState.e(" Not installed");
                string5 = "Not installed.";
                if (bl4) {
                    string5 = "Update needed..";
                }
                if (bl3) {
                    string5 = "Download pending in steam...";
                } else if (bl2) {
                    string5 = "Steam is downloading files..";
                    SteamUGC$ItemDownloadInfo steamUGC$ItemDownloadInfo = new SteamUGC$ItemDownloadInfo();
                    if (this.d.getItemDownloadInfo(steamPublishedFileID, steamUGC$ItemDownloadInfo)) {
                        double d2 = (double)steamUGC$ItemDownloadInfo.getBytesDownloaded() / (double)steamUGC$ItemDownloadInfo.getBytesTotal();
                        string5 = string5 + " " + GameUtils.c(d2 * 100.0) + "%";
                    }
                }
            }
            boolean bl6 = true;
            boolean bl7 = true;
            boolean bl8 = false;
            int n3 = 0;
            if (!bl) {
                n3 = -1;
            }
            com.corrodinggames.rts.gameFramework.mods.ModInfo b2 = l2.bZ.a(string3, string3, string4, string, bl6, bl7, bl8, n3);
            if (b2.workshopId == null) {
                b2.T = object2;
            }
            if (string5 == null && bl4) {
                string5 = bl3 ? "An update is pending download in Steam." : (bl3 ? "An update is downloading..." : "An update is available.");
            }
            b2.T = string5;
            if (bl && !bl4 || bl2 && !bl3) continue;
            GlobalState.e("Queuing download on: " + steamPublishedFileID);
            this.d.downloadItem(steamPublishedFileID, false);
        }
        this.e();
    }
}
