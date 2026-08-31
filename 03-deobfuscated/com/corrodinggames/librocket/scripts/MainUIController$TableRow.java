/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.MainUIController$TableCell;
import com.corrodinggames.rts.gameFramework.GameUtils;
import java.util.ArrayList;

public class MainUIController$TableRow {
    public ArrayList tableCells = new ArrayList();
    public Runnable androidOnclick;
    public String librocketOnClick;
    public String extraClasses;

    public void addClass(String string) {
        this.extraClasses = this.extraClasses == null ? string : this.extraClasses + " " + string;
    }

    public MainUIController$TableCell addCell(String string) {
        MainUIController$TableCell root$TableCell = new MainUIController$TableCell(string);
        this.tableCells.add(root$TableCell);
        return root$TableCell;
    }

    public void setLibrocketOnClick(String string) {
        this.librocketOnClick = string;
    }

    public void setAndroidOnClick(Runnable runnable) {
        this.androidOnclick = runnable;
    }

    public boolean same(MainUIController$TableRow root$TableRow, boolean bl) {
        if (!GameUtils.d(this.librocketOnClick, root$TableRow.librocketOnClick) || !GameUtils.d(this.extraClasses, root$TableRow.extraClasses)) {  // 02b: f.d(String,String)
            return false;
        }
        if (this.tableCells.size() != root$TableRow.tableCells.size()) {
            return false;
        }
        for (int i = 0; i < this.tableCells.size(); ++i) {
            MainUIController$TableCell root$TableCell;
            MainUIController$TableCell root$TableCell2 = (MainUIController$TableCell)this.tableCells.get(i);
            if (root$TableCell2.same(root$TableCell = (MainUIController$TableCell)root$TableRow.tableCells.get(i), bl)) continue;
            return false;
        }
        return true;
    }
}
