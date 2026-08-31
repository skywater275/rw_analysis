/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.ComponentCallbacks2
 *  android.content.Intent
 *  android.content.res.Configuration
 *  android.os.Bundle
 *  android.util.AttributeSet
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.LayoutInflater$Factory2
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnCreateContextMenuListener
 *  android.view.Window
 *  android.view.Window$Callback
 *  android.view.WindowManager$LayoutParams
 *  android.view.accessibility.AccessibilityEvent
 */
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

public class Activity
extends ContextWrapper
implements ComponentCallbacks2,
KeyEvent$Callback,
LayoutInflater.Factory2,
View.OnCreateContextMenuListener,
Window.Callback {
    public Activity() {
        super(null);
    }

    public View onCreateView(String string, Context context, AttributeSet attributeSet) {
        return null;
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int n) {
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return false;
    }

    public void onActionModeFinished(ActionMode actionMode) {
    }

    public void onActionModeStarted(ActionMode actionMode) {
    }

    public void onAttachedToWindow() {
    }

    public void onContentChanged() {
    }

    public boolean onCreatePanelMenu(int n, Menu menu) {
        return false;
    }

    public View onCreatePanelView(int n) {
        return null;
    }

    public void onDetachedFromWindow() {
    }

    public boolean onMenuItemSelected(int n, MenuItem menuItem) {
        return false;
    }

    public boolean onMenuOpened(int n, Menu menu) {
        return false;
    }

    public void onPanelClosed(int n, Menu menu) {
    }

    public boolean onPreparePanel(int n, View view, Menu menu) {
        return false;
    }

    public boolean onSearchRequested() {
        return false;
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
    }

    public void onWindowFocusChanged(boolean bl) {
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    public View onCreateView(View view, String string, Context context, AttributeSet attributeSet) {
        return null;
    }

    public void a(Intent intent, int n) {
        this.a(intent, n, null);
    }

    public void a(Intent intent, int n, Bundle bundle) {
    }

    public Window a() {
        return null;
    }

    @Deprecated
    public final void a(int n) {
        this.a(n, null);
    }

    @Deprecated
    public final boolean a(int n, Bundle bundle) {
        return true;
    }

    @Deprecated
    public final void b(int n) {
    }

    public void b() {
    }

    public boolean c() {
        return false;
    }

    public void a(int n, int n2) {
    }

    public boolean a(Menu menu) {
        return true;
    }
}
