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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ContextWrapper
extends Context {
    Context a;

    public ContextWrapper(Context context) {
        this.a = context;
    }

    public Context k() {
        return this.a;
    }

    @Override
    public AssetManager d() {
        return this.a.d();
    }

    @Override
    public Resources e() {
        return this.a.e();
    }

    @Override
    public PackageManager f() {
        return this.a.f();
    }

    @Override
    public Context g() {
        return this.a.g();
    }

    @Override
    public String h() {
        return this.a.h();
    }

    @Override
    public SharedPreferences a(String string, int n) {
        return this.a.a(string, n);
    }

    @Override
    public FileInputStream a(String string) {
        return this.a.a(string);
    }

    @Override
    public FileOutputStream b(String string, int n) {
        return this.a.b(string, n);
    }

    @Override
    public File b(String string) {
        return this.a.b(string);
    }

    @Override
    public File i() {
        return this.a.i();
    }

    @Override
    public File j() {
        return this.a.j();
    }

    @Override
    public void a(Intent intent) {
        this.a.a(intent);
    }

    @Override
    public Object c(String string) {
        return this.a.c(string);
    }
}
