/*
 * v19.133f3: 02b f/aj.java 全文直译 (单位信息面板渲染, 59 行)
 * 幻觉名修正: StatsPanel→CustomArrayList / UILabel→TextFormatter / GameResult→Texture / ae→ThemeColors
 */
package com.corrodinggames.rts.gameFramework.ui;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.ui.ThemeColors;
import com.corrodinggames.rts.gameFramework.ui.af;
import com.corrodinggames.rts.gameFramework.ui.ThemeColorEntry;
import com.corrodinggames.rts.gameFramework.ui.ThemeFontEntry;
import com.corrodinggames.rts.gameFramework.ui.ak;
import com.corrodinggames.rts.gameFramework.ui.TextFormatter;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.Iterator;

public class UnitInfoPanel {  // 02b f/aj.java (v19.133f3 整写)

    com.corrodinggames.rts.gameFramework.utility.CustomArrayList a;  // 02b L15: utility/m a
    Rect b;
    Paint c;
    Paint d;

    public void a(float f2, float f3) {
        GlobalState l2 = GlobalState.B();
        int n2 = 0;
        int n3 = TextFormatter.a(this.c);  // 02b L24: d.a(Paint)
        for (Iterator iterator = this.a.iterator(); iterator.hasNext(); ++n2) {
            ak ak2 = (ak) iterator.next();  // 02b L27: (ak)var6.next()
            int n4 = 0;
            af af2 = null;
            Iterator iterator2 = ak2.a.iterator();
            while (iterator2.hasNext()) {
                af af3 = (af) iterator2.next();  // 02b L33
                if (af2 != null) {
                    n4 += af2.a(this.c);
                }
                int n5 = (int) (f2 + (float) n4 + (float) this.b.d());
                n5 -= ak2.b / 2;
                int n6 = (int) (f3 + (float) this.b.b + (float) (n3 / 2) + (float) (n2 * n3));
                if (!(af3 instanceof ThemeFontEntry)) {
                    if (af3 instanceof ThemeColorEntry) {
                        ThemeColorEntry themeColorEntry = (ThemeColorEntry) af3;
                        Texture texture = themeColorEntry.a;
                        l2.bO.a(texture, (float) n5, (float) n6 - (float) texture.q * themeColorEntry.b, ThemeColors.accentColor, 0.0f, themeColorEntry.b);  // 02b L45: ae.c
                    }
                    af2 = af3;
                } else {
                    ThemeFontEntry themeFontEntry = (ThemeFontEntry) af3;
                    Paint paint = themeFontEntry.b(this.c);
                    l2.bO.a(themeFontEntry.d, (float) n5, (float) n6, paint);  // 02b L52
                    af2 = af3;
                }
            }
        }
    }
}
