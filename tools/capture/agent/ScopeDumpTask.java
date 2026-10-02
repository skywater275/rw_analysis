import java.lang.reflect.Field;
import java.util.Map;

/** M5 dump — VariableScope$VariableName.existingVariableName HashMap 全部变量名 (游戏自带名称注册表). */
public class ScopeDumpTask implements Runnable {
    public void run() {
        try {
            Class<?> vn = Class.forName("com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName");
            Field f = vn.getDeclaredField("existingVariableName");
            f.setAccessible(true);
            Object map = f.get(null);
            System.out.println("[M5] existingVariableName 类型: " + (map == null ? "null" : map.getClass().getName()));
            if (map instanceof Map) {
                Map<?, ?> m = (Map<?, ?>) map;
                System.out.println("[M5] 变量名总数: " + m.size());
                int i = 0;
                for (Object k : m.keySet()) {
                    System.out.println("[M5] name: " + k);
                    if (++i >= 80) { System.out.println("[M5] ... (截断)"); break; }
                }
            }
            // VariableScope 的 mapping 表 (meta→mapping)
            Class<?> vs = Class.forName("com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope");
            Field mm = vs.getDeclaredField("mapping");
            mm.setAccessible(true);
            Object m2 = mm.get(null);
            System.out.println("[M5] VariableScope.mapping: " + (m2 == null ? "null" : m2.getClass().getName() + " 条目=" + (m2 instanceof Map ? ((Map<?, ?>) m2).size() : "?")));
            if (m2 instanceof Map && ((Map<?, ?>) m2).size() > 0) {
                int i = 0;
                for (Object k : ((Map<?, ?>) m2).keySet()) {
                    System.out.println("[M5] scope: " + k);
                    if (++i >= 30) break;
                }
            }
        } catch (Throwable t) {
            System.out.println("[M5] 失败:"); t.printStackTrace(System.out);
        }
    }
}
