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

public class ContextWrapper extends Context {

   Context a;


   public ContextWrapper(Context var1) {
      this.a = var1;
   }

   public Context k() {
      return this.a;
   }

   public AssetManager d() {
      return this.a.d();
   }

   public Resources e() {
      return this.a.e();
   }

   public PackageManager f() {
      return this.a.f();
   }

   public Context g() {
      return this.a.g();
   }

   public String h() {
      return this.a.h();
   }

   public SharedPreferences a(String var1, int var2) {
      return this.a.a(var1, var2);
   }

   public FileInputStream a(String var1) {
      return this.a.a(var1);
   }

   public FileOutputStream b(String var1, int var2) {
      return this.a.b(var1, var2);
   }

   public File b(String var1) {
      return this.a.b(var1);
   }

   public File i() {
      return this.a.i();
   }

   public File j() {
      return this.a.j();
   }

   public void a(Intent var1) {
      this.a.a(var1);
   }

   public Object c(String var1) {
      return this.a.c(var1);
   }
}
