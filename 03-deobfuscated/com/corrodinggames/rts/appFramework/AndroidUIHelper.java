/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog$Builder
 *  android.content.ActivityNotFoundException
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Build$VERSION
 *  android.os.Parcelable
 *  android.widget.Toast
 */
package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import com.corrodinggames.rts.appFramework.AppState;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndroidUIHelper {
    static Handler activity;
    static volatile Context dialogHelper;
    public static final AppState toastQueue;
    public static AppState lastToastTime;

    public static int a(String string) {
        Pattern pattern;
        Matcher matcher;
        String string2 = null;
        if (string != null) {
            string2 = GameUtils.k(string);
        }
        if (string2 != null && (matcher = (pattern = Pattern.compile("^ *\\[([^\\]]*)\\].*")).matcher(string2)).matches()) {
            String[] stringArray;
            String string3 = matcher.group(1);
            for (String string4 : stringArray = string3.split(";")) {
                int n2;
                if (!string4.startsWith("p") || string4.length() < 2) continue;
                String string5 = string4.substring(1);
                try {
                    n2 = Integer.parseInt(string5);
                }
                catch (NumberFormatException numberFormatException) {
                    GlobalState.e("getNumberOfPlayersInMap: NumberFormatException:" + string5);
                    return -1;
                }
                return n2;
            }
        }
        GlobalState.e("getNumberOfPlayersInMap: fail to match:" + string2);
        return -1;
    }

    public static String b(String string) {
        Pattern pattern;
        Matcher matcher;
        Object object;
        if (string == null) {
            return null;
        }
        if (string.contains(File.separator)) {
            object = string.split(Pattern.quote(File.separator));
            string = ((String[])object)[((String[])object).length - 1];
        }
        if (string.contains("/")) {
            object = string.split("/");
            string = ((String[])object)[((String[])object).length - 1];
        }
        if ((object = null) == null && (matcher = (pattern = Pattern.compile("^l\\d*;\\[.*\\](.+)\\.tmx")).matcher(string)).matches() && ((String)(object = matcher.group(1))).length() >= 1) {
            object = ((String)object).substring(0, 1).toUpperCase() + ((String)object).substring(1);
        }
        if (object == null && (matcher = (pattern = Pattern.compile("^l\\d*;(.+)\\.tmx")).matcher(string)).matches() && ((String)(object = matcher.group(1))).length() >= 1) {
            object = ((String)object).substring(0, 1).toUpperCase() + ((String)object).substring(1);
        }
        if (object == null && (matcher = (pattern = Pattern.compile("^ *\\[.*\\](.+)\\.tmx")).matcher(string)).matches() && ((String)(object = matcher.group(1))).length() >= 1) {
            object = ((String)object).substring(0, 1).toUpperCase() + ((String)object).substring(1);
        }
        if (object == null && (matcher = (pattern = Pattern.compile("(.*)\\.tmx")).matcher(string)).matches() && ((String)(object = matcher.group(1))).length() >= 1) {
            object = ((String)object).substring(0, 1).toUpperCase() + ((String)object).substring(1);
        }
        if (object == null) {
            object = string;
        }
        if (((String)(object = ((String)object).replace('_', ' '))).endsWith(".rwsave")) {
            object = ((String)object).replace(".rwsave", "");
        }
        return (String) object;
    }

    public static String c(String string) {
        String string2 = string.replace(".tmx", "");
        string2 = string2 + "_map.png";
        return string2;
    }

    private static void c(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            activity.a().getDecorView().setSystemUiVisibility(5894);
        }
    }

    private static void d(Activity activity) {
    }

    public static void a(Runnable runnable) {
        if (activity == null) {
            activity = new Handler();  // game-lib Handler stub 无 getMainLooper
        }
        runnable.run();  // game-lib Handler stub 无 post; 桌面直接执行
    }

    public static Context a() {
        if (dialogHelper == null) {
            throw new RuntimeException("ApplicationContext==null");
        }
        return dialogHelper;
    }

    public static void a(Activity activity) {
        if (dialogHelper == null) {
            dialogHelper = activity.g();
        }
    }

    public static void a(Context context) {
        if (dialogHelper == null) {
            dialogHelper = context.g();
        }
    }

    public static void a(Activity activity, boolean bl, boolean bl2) {
        GlobalState l2;
        AndroidUIHelper.a(activity);
        if (bl2) {
            l2 = GlobalState.B();
            if (l2 != null && l2.bQ.immersiveFullScreen) {
                c(activity);
            }
        } else {
            AndroidUIHelper.d(activity);
        }
        l2 = GlobalState.B();
        if (l2 != null) {
            l2.ab();
        }
        if (bl) {
            activity.a().setBackgroundDrawable(null);
        }
    }

    public static void a(Activity activity, boolean bl) {
        if (bl) {
            activity.a(0, 0);
        }
    }

    public static boolean a(Activity activity, Runnable runnable) {
        GlobalState l2 = GlobalState.B();
        boolean bl = AndroidUIHelper.a(activity, runnable, false);
        return bl;
    }

    public static boolean a(Activity activity, Runnable runnable, boolean bl) {
        GlobalState l2 = GlobalState.B();
        if (bl || !l2.bQ.hasSelectedAStorageType) {
            if (Build.VERSION.SDK_INT < 19) {
                return false;
            }
            AndroidUIHelper$1 c$1 = new AndroidUIHelper$1(l2, runnable);
            AndroidUIHelper$2 c$2 = new AndroidUIHelper$2(activity, l2, runnable);
            String string = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.mods.androidStorageSetupTitle", new Object[0]);
            String string2 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.mods.androidStorageSetupMessage", new Object[0]);
            String string3 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.mods.androidStorageSetupInternal", new Object[0]);
            String string4 = com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.mods.androidStorageSetupExternal", new Object[0]);
            new AlertDialog.Builder((Context)activity).setIcon(17301543).setTitle((CharSequence)string).setMessage((CharSequence)string2).setPositiveButton((CharSequence)string3, (DialogInterface.OnClickListener)c$1).setNeutralButton((CharSequence)string4, (DialogInterface.OnClickListener)c$2).show();
            GlobalState.e("Showing storage setup");
            return true;
        }
        return false;
    }

    public static boolean b(Context context) {
        if (GlobalState.aU) {
            return true;
        }
        if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.isEnabled4()) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return ContextCompat.a(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        }
        return true;
    }

    public static boolean b(Activity activity) {
        GlobalState l2 = GlobalState.B();
        if (GlobalState.aU) {
            return true;
        }
        if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.isEnabled4()) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (l2.getContext() == null) {
                // empty if block
            }
            if (ContextCompat.a(activity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                l2.bQ.hadStoragePermissionInPast = true;
                GlobalState.e("File Permission is granted");
                return true;
            }
            GlobalState.e("Permission is revoked");
            ActivityCompat.a(activity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
            return false;
        }
        return true;
    }

    public static void a(Intent intent) {
        intent.addFlags(65536);
    }

    public static void a(Activity activity, int n2, boolean bl, String string, Uri uri) {
        GlobalState.e("Show folder chooser. Write:" + bl);
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        intent.addFlags(64);
        intent.addFlags(1);
        if (bl) {
            intent.addFlags(2);
        }
        if (uri != null) {
            intent.putExtra("android.provider.extra.INITIAL_URI", (Parcelable)uri);
        }
        intent.putExtra("android.content.extra.SHOW_ADVANCED", true);
        try {
            activity.a(Intent.createChooser((Intent)intent, (CharSequence)string), n2);
        }
        catch (ActivityNotFoundException activityNotFoundException) {
            Toast.makeText((Context)activity, (CharSequence)"Failed to open file list. Please install FileLoader File Manager.", (int)0).show();
        }
    }

    static {
        lastToastTime = toastQueue = AppState.f;
    }
}
