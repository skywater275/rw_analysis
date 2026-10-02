package android.app;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.KeyEvent$Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater.Factory2;
import android.view.View.OnCreateContextMenuListener;
import android.view.Window.Callback;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;

public class Activity extends ContextWrapper implements ComponentCallbacks2, KeyEvent$Callback, Factory2, OnCreateContextMenuListener, Callback {

   public Activity() {
      super((Context)null);
   }

   public View onCreateView(String var1, Context var2, AttributeSet var3) {
      return null;
   }

   public void onConfigurationChanged(Configuration var1) {}

   public void onLowMemory() {}

   public void onTrimMemory(int var1) {}

   public void onCreateContextMenu(ContextMenu var1, View var2, ContextMenuInfo var3) {}

   public boolean dispatchGenericMotionEvent(MotionEvent var1) {
      return false;
   }

   public boolean dispatchKeyEvent(KeyEvent var1) {
      return false;
   }

   public boolean dispatchKeyShortcutEvent(KeyEvent var1) {
      return false;
   }

   public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent var1) {
      return false;
   }

   public boolean dispatchTouchEvent(MotionEvent var1) {
      return false;
   }

   public boolean dispatchTrackballEvent(MotionEvent var1) {
      return false;
   }

   public void onActionModeFinished(ActionMode var1) {}

   public void onActionModeStarted(ActionMode var1) {}

   public void onAttachedToWindow() {}

   public void onContentChanged() {}

   public boolean onCreatePanelMenu(int var1, Menu var2) {
      return false;
   }

   public View onCreatePanelView(int var1) {
      return null;
   }

   public void onDetachedFromWindow() {}

   public boolean onMenuItemSelected(int var1, MenuItem var2) {
      return false;
   }

   public boolean onMenuOpened(int var1, Menu var2) {
      return false;
   }

   public void onPanelClosed(int var1, Menu var2) {}

   public boolean onPreparePanel(int var1, View var2, Menu var3) {
      return false;
   }

   public boolean onSearchRequested() {
      return false;
   }

   public void onWindowAttributesChanged(LayoutParams var1) {}

   public void onWindowFocusChanged(boolean var1) {}

   public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback var1) {
      return null;
   }

   public View onCreateView(View var1, String var2, Context var3, AttributeSet var4) {
      return null;
   }

   public void a(Intent var1, int var2) {
      this.a(var1, var2, (Bundle)null);
   }

   public void a(Intent var1, int var2, Bundle var3) {}

   public Window a() {
      return null;
   }

   @Deprecated
   public final void a(int var1) {
      this.a(var1, (Bundle)null);
   }

   @Deprecated
   public final boolean a(int var1, Bundle var2) {
      return true;
   }

   @Deprecated
   public final void b(int var1) {}

   public void b() {}

   public boolean c() {
      return false;
   }

   public void a(int var1, int var2) {}

   public boolean a(Menu var1) {
      return true;
   }
}
