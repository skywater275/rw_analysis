/*
 * v19.133f58 整写: 02b gameFramework/n/a/b.java 直译 (队伍标签检测条件, 迁移 aicore 包)
 * 修复: a→PlayerState/b→TeamTag 类型还原; FileSystem 误标清理; TeamTag.parseSingleTag (02b g.b(String));
 *       TeamTag.a (02b g.a(g,h) = 03 L176)
 */
package com.corrodinggames.rts.gameFramework.aicore;

import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.bo;

public class TeamTagDetect
extends TaskCondition {
    PlayerState a;
    TeamTag b;

    public static TeamTagDetect d(AITask a2) throws MapException {
        TeamTagDetect b2 = new TeamTagDetect();
        b2.a = a2.a();
        if (b2.a == null) {
            throw new MapException("teamTagDetect requires a team set");
        }
        String string = a2.b("teamTag");
        if (string != null && !string.equals("")) {
            try {
                b2.b = TeamTag.parseSingleTag(string);
                return b2;
            }
            catch (bo bo2) {
                throw new MapException(bo2.getMessage());
            }
        }
        throw new MapException("teamTagDetect requires a teamTag set");
    }

    public boolean b(AITask a2) {
        return TeamTag.a(this.b, this.a.U());
    }
}
