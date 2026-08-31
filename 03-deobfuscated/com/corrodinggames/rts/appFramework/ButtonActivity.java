/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.MenuItem
 *  android.view.View
 *  android.widget.Button
 */
package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.corrodinggames.rts.appFramework.GameActivity;
import com.corrodinggames.rts.appFramework.AndroidUIHelper;
import com.corrodinggames.rts.appFramework.ReplayComparator;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import java.util.ArrayList;
import java.util.Collections;

public class ButtonActivity
extends GameActivity {
    String[] c;

    @Override
    public void b() {
        super.b();  // 02b j L20: super.b()
        AndroidUIHelper.a((Activity)this, true);
    }

    public static String[] l() {
        String[] stringArray = FileLoader.a("/SD/rustedWarfare/saves/", false);
        if (stringArray == null) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : stringArray) {
            if (string.endsWith(".map") || string.endsWith(".tmp")) continue;
            arrayList.add(string);
        }
        Collections.sort(arrayList, new ReplayComparator());
        return arrayList.toArray(new String[0]);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        Button button = (Button)view;
        contextMenu.setHeaderTitle(button.getText());
        contextMenu.add(0, view.getId(), 0, (CharSequence)"Share");
        contextMenu.add(1, view.getId(), 0, (CharSequence)"Rename");
        contextMenu.add(2, view.getId(), 0, (CharSequence)"Delete");
        if (this.c != null && this.c.length > 0) {
            String string = this.c[view.getId()];
            String string2 = FileLoader.n(string);
            MenuItem menuItem = contextMenu.add(3, view.getId(), 0, (CharSequence)("Storage: " + string2));
            if (menuItem != null) {
                menuItem.setEnabled(false);
            }
        }
    }
}
