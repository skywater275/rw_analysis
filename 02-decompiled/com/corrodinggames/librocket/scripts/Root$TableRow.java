/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Root$TableCell;
import com.corrodinggames.rts.gameFramework.f;
import java.util.ArrayList;

public class Root$TableRow {
    public ArrayList tableCells = new ArrayList();
    public Runnable androidOnclick;
    public String librocketOnClick;
    public String extraClasses;

    public void addClass(String string) {
        this.extraClasses = this.extraClasses == null ? string : this.extraClasses + " " + string;
    }

    public Root$TableCell addCell(String string) {
        Root$TableCell root$TableCell = new Root$TableCell(string);
        this.tableCells.add(root$TableCell);
        return root$TableCell;
    }

    public void setLibrocketOnClick(String string) {
        this.librocketOnClick = string;
    }

    public void setAndroidOnClick(Runnable runnable) {
        this.androidOnclick = runnable;
    }

    public boolean same(Root$TableRow root$TableRow, boolean bl) {
        if (!f.d(this.librocketOnClick, root$TableRow.librocketOnClick) || !f.d(this.extraClasses, root$TableRow.extraClasses)) {
            return false;
        }
        if (this.tableCells.size() != root$TableRow.tableCells.size()) {
            return false;
        }
        for (int i = 0; i < this.tableCells.size(); ++i) {
            Root$TableCell root$TableCell;
            Root$TableCell root$TableCell2 = (Root$TableCell)this.tableCells.get(i);
            if (root$TableCell2.same(root$TableCell = (Root$TableCell)root$TableRow.tableCells.get(i), bl)) continue;
            return false;
        }
        return true;
    }
}
