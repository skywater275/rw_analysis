/*
 * v19.115k 新建: 02b custom/a/a.java 11 行直译 (custom 动作抽象父类)
 * 03 CustomActionBase/CustomRepairAction/CustomSpawnAction/CustomTransportAction/CustomWeaponAction 的 extends 目标
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;

public abstract class ActionBase {

   public abstract boolean a(CustomUnitType var1, GameAction var2, PointF var3, UnitInstance var4, int var5);
}
