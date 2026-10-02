/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.input;
import com.corrodinggames.rts.gameFramework.mods.VersionChecker;

import com.codedisaster.steamworks.SteamNativeHandle;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCCallback;
import com.codedisaster.steamworks.SteamUGCDetails;
import com.codedisaster.steamworks.SteamUGCQuery;
import com.corrodinggames.rts.gameFramework.mods.ModInfo;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.input.SteamWorkshop;
import com.corrodinggames.rts.java.input.h;
import com.corrodinggames.rts.java.input.i;

public class j
implements SteamUGCCallback {
    final /* synthetic */ SteamWorkshop a;

    public j(SteamWorkshop g2) {
        this.a = g2;
    }

    @Override
    public void onUGCQueryCompleted(SteamUGCQuery steamUGCQuery, int n2, int n3, boolean bl, SteamResult steamResult) {
        GlobalState.e("Got workshop callback: onUGCQueryCompleted (" + (Object)((Object)steamResult) + ") numResultsReturned:" + n2);
        if (steamResult != SteamResult.OK) {
            return;
        }
        for (int k = 0; k < n2; ++k) {
            SteamUGCDetails steamUGCDetails = new SteamUGCDetails();
            boolean bl2 = this.a.d.getQueryUGCResult(steamUGCQuery, k, steamUGCDetails);
            if (!bl2) {
                GlobalState.e("getQueryUGCResult failed for index: " + k);
                continue;
            }
            SteamPublishedFileID steamPublishedFileID = steamUGCDetails.getPublishedFileID();
            GlobalState.e("getQueryUGCResult: " + steamPublishedFileID);
            h h2 = this.a.a(steamPublishedFileID);
            h2.d = steamUGCDetails.getTitle();
            h2.c = true;
            this.a.a = true;
        }
    }

    @Override
    public void onSubscribeItem(SteamPublishedFileID steamPublishedFileID, SteamResult steamResult) {
        this.a("onSubscribeItem", steamResult);
        GlobalState l2 = GlobalState.B();
    }

    @Override
    public void onUnsubscribeItem(SteamPublishedFileID steamPublishedFileID, SteamResult steamResult) {
        this.a("onUnsubscribeItem", steamResult);
        GlobalState l2 = GlobalState.B();
    }

    @Override
    public void onRequestUGCDetails(SteamUGCDetails steamUGCDetails, SteamResult steamResult) {
        this.a("onRequestUGCDetails", steamResult);
    }

    @Override
    public void onCreateItem(SteamPublishedFileID steamPublishedFileID, boolean bl, SteamResult steamResult) {
        this.a("onCreateItem", steamResult);
        if (this.a.e == null) {
            this.a.a(null, "Error no mod pending creation!", false);
            return;
        }
        if (this.a.e.k != 0L) {
            this.a.a(this.a.e, "This mod has already been published", true);
            return;
        }
        this.a.e.a(SteamNativeHandle.getNativeHandle(steamPublishedFileID));
        this.a.a(this.a.e, true, "Created.");
        this.a.e = null;
        GlobalState l2 = GlobalState.B();
        l2.bZ.m();
    }

    @Override
    public void onSubmitItemUpdate(boolean bl, SteamResult steamResult) {
        ModInfo b2 = this.a.g;
        this.a.g = null;
        this.a("onSubmitItemUpdate", steamResult);
        String string = "";
        if (steamResult == SteamResult.OK) {
            string = "Workshop item updated.";
        } else if (this.a.f) {
            string = "Error. Workshop returned: " + (Object)((Object)steamResult) + " while trying to upload workshop data.";
        } else {
            string = "Error. Workshop returned: " + (Object)((Object)steamResult) + " while trying to update existing workshop item.";
            if (steamResult == SteamResult.FileNotFound) {
                string = string + " (If you want to create VersionChecker new workshop item instead of updating, delete steam.dat from this mod.)";
            }
        }
        if (bl) {
            string = string + "WLA agreement needs to be accepted on the workshop site.";
        }
        GlobalState.B().i(string);
        GlobalState l2 = GlobalState.B();
        l2.bZ.m();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onDownloadItemResult(int n2, SteamPublishedFileID steamPublishedFileID, SteamResult steamResult) {
        this.a("onDownloadItemResult", steamResult);
        Object object = this.a.h;
        synchronized (object) {
            i i2 = this.a.c(steamPublishedFileID);
            if (i2 != null) {
                i2.a(steamResult);
                this.a.h.remove(i2);
            }
        }
        object = GlobalState.B();
        ((GlobalState) object).bZ.m();
    }

    @Override
    public void onUserFavoriteItemsListChanged(SteamPublishedFileID steamPublishedFileID, boolean bl, SteamResult steamResult) {
        this.a("onUserFavoriteItemsListChanged", steamResult);
    }

    @Override
    public void onSetUserItemVote(SteamPublishedFileID steamPublishedFileID, boolean bl, SteamResult steamResult) {
        this.a("onSetUserItemVote", steamResult);
    }

    @Override
    public void onGetUserItemVote(SteamPublishedFileID steamPublishedFileID, boolean bl, boolean bl2, boolean bl3, SteamResult steamResult) {
        this.a("onGetUserItemVote", steamResult);
    }

    @Override
    public void onStartPlaytimeTracking(SteamResult steamResult) {
        this.a("onStartPlaytimeTracking", steamResult);
    }

    @Override
    public void onStopPlaytimeTracking(SteamResult steamResult) {
        this.a("onStopPlaytimeTracking", steamResult);
    }

    @Override
    public void onStopPlaytimeTrackingForAllItems(SteamResult steamResult) {
        this.a("onStopPlaytimeTrackingForAllItems", steamResult);
    }

    public void a(String string, SteamResult steamResult) {
        GlobalState.e("Got workshop callback: " + string + " (" + (Object)((Object)steamResult) + ")");
    }
}
