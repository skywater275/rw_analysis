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

public class ServerContext extends Context {

   AssetManager a = new AssetManager();


   public AssetManager d() {
      return this.a;
   }

   public Resources e() {
      return null;
   }

   public PackageManager f() {
      return null;
   }

   public Context g() {
      return this;
   }

   public String h() {
      return null;
   }

   public SharedPreferences a(String var1, int var2) {
      return null;
   }

   public FileInputStream a(String var1) {
      return null;
   }

   public FileOutputStream b(String var1, int var2) {
      return null;
   }

   public File b(String var1) {
      return null;
   }

   public File i() {
      return null;
   }

   public File j() {
      return null;
   }

   public void a(Intent var1) {}

   public Object c(String var1) {
      return null;
   }
}
