/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.gameFramework.StatsRecord;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;

public class GameResult {
    public String resultLabel;
    public String resultValue;
    public float scoreValue;
    public float survivalWaves;

    public GameResult(String string, String string2) {
        this.resultLabel = string;
        this.resultValue = string2;
    }

    public GameResult(String string, float f) {
        this.resultLabel = string;
        this.scoreValue = f;
        this.resultValue = null;
    }

    public static ArrayList a() {
        GlobalState l2 = GlobalState.B();
        ArrayList<GameResult> arrayList = new ArrayList<GameResult>();
        StatsRecord bo2 = null;
        if (l2.bs != null) {
            bo2 = l2.bY.a(l2.bs);
        }
        if (bo2 != null) {
            GameResult e2;
            if (l2.ce != null && l2.ce.q) {  // 02b n/f L41: k (03 q 保序对齐)
                e2 = new GameResult("Lasted till wave: " + l2.ce.waveIndex, "");  // 02b n/f L48: r (03 waveIndex)
                arrayList.add(e2);
                if (!l2.ce.aiEnabled) {  // 02b n/f L42: l (03 aiEnabled 顺延)
                    e2 = new GameResult("Wave difficulty: " + l2.bX.c(l2.ce.waveDifficultyBase), "");  // 02b n/f L55: y (03 waveDifficultyBase 语义)
                    arrayList.add(e2);
                }
            }
            e2 = new GameResult("Game Time", GameUtils.a((long)(l2.by / 1000)));  // 02b f/e L44: f.a(long) (UIScrollBar 为幻觉名)
            arrayList.add(e2);
            e2 = new GameResult("=============================", "");
            arrayList.add(e2);
            e2 = new GameResult("Units Killed", bo2.unitsKilled);
            arrayList.add(e2);
            e2 = new GameResult("Buildings Killed", bo2.buildingsKilled);
            arrayList.add(e2);
            e2 = new GameResult("Experimentals Killed", bo2.experimentalsKilled);
            arrayList.add(e2);
            e2 = new GameResult("=============================", "");
            arrayList.add(e2);
            e2 = new GameResult("Units Lost", bo2.unitsLost);
            arrayList.add(e2);
            e2 = new GameResult("Buildings Lost", bo2.buildingsLost);
            arrayList.add(e2);
            e2 = new GameResult("Experimentals Lost", bo2.experimentalsLost);
            arrayList.add(e2);
            e2 = new GameResult("=============================", "");
            arrayList.add(e2);
        }
        return arrayList;
    }
}
