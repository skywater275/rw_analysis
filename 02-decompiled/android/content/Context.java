/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.pm.PackageManager
 *  android.content.res.Resources
 */
package android.content;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public abstract class Context {
    public abstract AssetManager d();

    public abstract Resources e();

    public abstract PackageManager f();

    public abstract Context g();

    public abstract String h();

    public abstract SharedPreferences a(String var1, int var2);

    public abstract FileInputStream a(String var1);

    public abstract FileOutputStream b(String var1, int var2);

    public abstract File b(String var1);

    public abstract File i();

    public abstract File j();

    public abstract void a(Intent var1);

    public abstract Object c(String var1);
}
