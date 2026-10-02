import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/** dF (playerIndicators) 运行时验证 — 触发 setDiscoveredBy + 读数组 + 存档差分. */
public class IndTask4 implements Runnable {
    public void run() {
        try {
            // 1. 拿当前玩家 (GlobalState.bs)
            Class<?> lCls = Class.forName("com.corrodinggames.rts.gameFramework.l");
            Object gs = lCls.getMethod("B").invoke(null);
            Object me = gs.getClass().getField("bs").get(gs);
            System.out.println("[IND4] 当前玩家: " + me + " (k=" + me.getClass().getField("k").get(me) + ")");
            // 2. 拿游戏对象列表 (gameFramework.w.er — fastGameObjectList, 02b y.java:225 存档循环同源)
            Class<?> wCls = Class.forName("com.corrodinggames.rts.gameFramework.w");
            Field er = wCls.getField("er");
            @SuppressWarnings("unchecked")
            List<Object> shells = (List<Object>) er.get(null);
            System.out.println("[IND4] 游戏对象列表: " + shells.size());
            // 3. 找一个活单位 (instanceof 判断 — shells 里是子类实例)
            Object target = null;
            Class<?> amCls = Class.forName("com.corrodinggames.rts.game.units.am");
            for (Object s : shells) {
                if (s == null) continue;
                if (amCls.isInstance(s)) {
                    Field bV = amCls.getField("bV");
                    if (!bV.getBoolean(s)) { target = s; break; }
                }
            }
            if (target == null) { System.out.println("[IND4] 无活单位, 终止"); return; }
            Field eo = amCls.getField("eo"), ep = amCls.getField("ep");
            System.out.println("[IND4] 目标单位 @(" + eo.getFloat(target) + "," + ep.getFloat(target) + ")");
            // 4. 调 g(PlayerState) = setDiscoveredBy — 标记当前玩家发现
            Method g = null;
            for (Method m : amCls.getDeclaredMethods()) {
                if (m.getName().equals("g") && m.getParameterTypes().length == 1
                        && m.getParameterTypes()[0] == Class.forName("com.corrodinggames.rts.game.n")) { g = m; break; }
            }
            g.invoke(target, me);
            System.out.println("[IND4] setDiscoveredBy(当前玩家) 已调用");
            // 5. 读 dF 数组
            Field dF = amCls.getDeclaredField("dF");
            dF.setAccessible(true);
            Object[] arr = (Object[]) dF.get(target);
            System.out.println("[IND4] dF 长度: " + (arr == null ? "null" : arr.length));
            if (arr != null && arr.length > 0) {
                Object e0 = arr[0];
                Field aF = e0.getClass().getDeclaredField("a");
                aF.setAccessible(true);
                Field bF = e0.getClass().getDeclaredField("b");
                bF.setAccessible(true);
                System.out.println("[IND4] dF[0]: a(discovered)=" + aF.getBoolean(e0) + " b(typeValue)=" + bF.getInt(e0));
            }
            // 6. 存档 (验证序列化 dF 段)
            Class<?> saverCls = Class.forName("com.corrodinggames.rts.gameFramework.y");
            Field pf = saverCls.getDeclaredField("a");
            pf.setAccessible(true);
            pf.setBoolean(null, true);
            Object saver = gs.getClass().getField("ca").get(gs);
            saver.getClass().getMethod("b", String.class, boolean.class).invoke(saver, "indtest", false);
            System.out.println("[IND4] 存档 indtest 完成");
        } catch (Throwable t) {
            System.out.println("[IND4] 失败:"); t.printStackTrace(System.out);
        }
    }
}
