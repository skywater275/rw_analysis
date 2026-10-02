import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** 放置差分任务 — ar.a 四态放置 4 个 tank, 读回 cq/cr/co/ct/ci/bM/bN 状态位. */
public class PlaceTask4 implements Runnable {
    public void run() {
        try {
            Class<?> arCls = Class.forName("com.corrodinggames.rts.game.units.ar");
            Class<?> amCls = Class.forName("com.corrodinggames.rts.game.units.am");
            // 1. 找 tank 枚举 (Enum.name 语义名保留)
            Object tankType = null;
            for (Field f : arCls.getFields()) {
                if (f.getType() == arCls && java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    Object v = f.get(null);
                    if (((Enum<?>) v).name().equals("tank")) { tankType = v; break; }
                }
            }
            if (tankType == null) { System.out.println("[PLD] tank 枚举未找到"); return; }
            System.out.println("[PLD] tank 类型: " + ((Enum<?>) tankType).name());
            // 2. 玩家 (当前 bs) — 第 1 队伍
            Class<?> lCls = Class.forName("com.corrodinggames.rts.gameFramework.l");
            Object gs = lCls.getMethod("B").invoke(null);
            Object me = gs.getClass().getField("bs").get(gs);
            // 3. 四态放置 (x 递增)
            Method place = null;
            for (Method m : arCls.getDeclaredMethods()) {
                if (m.getName().equals("a") && m.getParameterTypes().length == 12) { place = m; break; }
            }
            Class<?> wCls0 = Class.forName("com.corrodinggames.rts.gameFramework.w");
            java.util.List<Object> before = (java.util.List<Object>) wCls0.getField("er").get(null);
            System.out.println("[PLD] 放置前列表大小: " + before.size());
            float baseX = 3650.0F;
            String[] states = {"FF", "TF", "FT", "TT"};
            for (int i = 0; i < 4; ++i) {
                boolean v8 = states[i].charAt(0) == 'T';
                boolean v9 = states[i].charAt(1) == 'T';
                // 先记录放置前的对象数
                place.invoke(null, tankType, baseX + i * 200.0F, 1500.0F, 0.0F, 0.0F,
                        me, 50.0F, 150.0F, v8, v9, 1, null); // y=1500 陆地
                System.out.println("[PLD] 放置 #" + i + " (" + states[i] + ") @x=" + (baseX + i * 200.0F));
            }
            // 4. 读回: 遍历对象列表找新 tank (坐标匹配)
            Class<?> wCls = Class.forName("com.corrodinggames.rts.gameFramework.w");
            @SuppressWarnings("unchecked")
            java.util.List<Object> objs = (java.util.List<Object>) wCls.getField("er").get(null);
            System.out.println("[PLD] 放置后列表大小: " + objs.size());
            Field eo = amCls.getField("eo");
            Field[] flags = new Field[7];
            String[] names = {"cq", "cr", "co", "ct", "ci", "bM", "bN"};
            for (int i = 0; i < 7; ++i) flags[i] = amCls.getField(names[i]);
            int found = 0;
            for (Object o : objs) {
                if (!amCls.isInstance(o)) continue;
                float x = eo.getFloat(o);
                // 打印所有 tank 位置 (诊断)
                if (x > 3550 && x < 4550) {
                    System.out.println("[PLD] 对象 @x=" + x + " class=" + o.getClass().getSimpleName());
                }
                for (int i = 0; i < 4; ++i) {
                    if (Math.abs(x - (baseX + i * 200.0F)) < 5.0F) {
                        StringBuilder sb = new StringBuilder("[PLD] tank#" + i + "(" + states[i] + "):");
                        for (int j = 0; j < 7; ++j) {
                            sb.append(" ").append(names[j]).append("=").append(flags[j].getBoolean(o));
                        }
                        System.out.println(sb);
                        ++found;
                    }
                }
            }
            System.out.println("[PLD] 找到 " + found + "/4");
            // 5. 存档差分
            Class<?> saverCls = Class.forName("com.corrodinggames.rts.gameFramework.y");
            Field pf = saverCls.getDeclaredField("a");
            pf.setAccessible(true);
            pf.setBoolean(null, true);
            Object saver = gs.getClass().getField("ca").get(gs);
            saver.getClass().getMethod("b", String.class, boolean.class).invoke(saver, "placetest" + System.currentTimeMillis()%100000, false);
            System.out.println("[PLD] 存档 placetest 完成");
        } catch (Throwable t) {
            System.out.println("[PLD] 失败:"); t.printStackTrace(System.out);
        }
    }
}
