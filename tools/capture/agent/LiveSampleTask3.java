import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/** 回放 AI 行为全程采样 — 自重现任务: 每 300 帧采样 AI 状态, 6000 帧后停止. */
public class LiveSampleTask3 implements Runnable {
    static int count = 0;
    static List<Object> queue = null;
    static Object action = null;  // RunnableAction 实例 (自投递用)

    public void run() {
        try {
            count++;
            
            if (queue == null) {
                Class<?> libA = Class.forName("com.corrodinggames.librocket.a");
                Object gui = libA.getMethod("a").invoke(null);
                Object libB = gui.getClass().getField("b").get(gui);
                Object se = libB.getClass().getField("c").get(libB);
                for (Field f : se.getClass().getDeclaredFields()) {
                    if (List.class.isAssignableFrom(f.getType())) { f.setAccessible(true); queue = (List<Object>) f.get(se); break; }
                }
                action = this;  // 本任务实例即 RunnableAction 包装的 Runnable — 自投递用 new RunnableAction(this)
            }
            if (count % 300 == 1) {
                sample();
            }
            if (count < 30000) {  // v19.113t: 长对局 (~17 分钟)
                // 自投递 (每帧一个, 采样内部节流)
                Class<?> ra = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine$RunnableAction");
                java.lang.reflect.Constructor<?> c = ra.getDeclaredConstructor(Runnable.class);
                c.setAccessible(true);
                queue.add(c.newInstance(this));
            } else {
                System.out.println("[LIVE] 采样结束 (30000 帧)");
            }
        } catch (Throwable t) {
            System.out.println("[LIVE] 任务失败:"); t.printStackTrace(System.out);
        }
    }

    void sample() {
        try {
            Class<?> nCls = Class.forName("com.corrodinggames.rts.game.n");
            Field pb = nCls.getDeclaredField("b");  // n.b 静态包私有 (javap: static n[] b)
            pb.setAccessible(true);
            Object[] players = (Object[]) pb.get(null);
            Class<?> aiCls = Class.forName("com.corrodinggames.rts.game.a.a");
            Class<?> cmCls = Class.forName("com.corrodinggames.rts.game.a.i");
            StringBuilder sb = new StringBuilder("[LIVE] frame=" + count + " ");
            int aiCount = 0;
            // 单位分桶 (gameFramework.w.er 按 bX=player 计数)
            java.util.Map<Object, Integer> unitCount = new java.util.HashMap<Object, Integer>();
            Class<?> wCls = Class.forName("com.corrodinggames.rts.gameFramework.w");
            List<?> objs = (List<?>) wCls.getField("er").get(null);
            Class<?> amCls = Class.forName("com.corrodinggames.rts.game.units.am");
            for (Object o : objs) {
                if (!amCls.isInstance(o)) continue;
                Object owner = amCls.getField("bX").get(o);
                if (owner != null) unitCount.merge(owner, 1, Integer::sum);
            }
            for (Object p : players) {
                if (p == null || !aiCls.isInstance(p)) continue;
                aiCount++;
                Field kF = nCls.getField("k");
                int team = kF.getInt(p);
                Field creditsF = nCls.getField("o");  // v19.113g 铁证: o=credits
                int credits = creditsF.getInt(p);
                Field bmF = aiCls.getDeclaredField("bm");  // 包私有
                bmF.setAccessible(true);
                ConcurrentLinkedQueue<?> bm = (ConcurrentLinkedQueue<?>) bmF.get(p);
                sb.append("AI").append(team)
                  .append(":c").append(bm.size())
                  .append(":cr").append(credits)
                  .append(":u").append(unitCount.getOrDefault(p, 0));
                int i = 0;
                for (Object cm : bm) {
                    if (!cmCls.isInstance(cm)) { sb.append("[非CombatMain:").append(cm.getClass().getSimpleName()).append("]"); continue; }  // 队列混类防御
                    Field bF = cmCls.getDeclaredField("b");  // a.i.b 包私有 (javap: a.j b 无 public)
                    bF.setAccessible(true);
                    Object zone = bF.get(cm);
                    sb.append("[").append(zone == null ? "-" : ((Enum<?>) zone).name()).append("]");
                    if (++i >= 5) { sb.append(".."); break; }
                }
                sb.append(" ");
            }
            if (aiCount == 0) {
                // 回放可能未开始或 AI 未加载
                Class<?> lCls = Class.forName("com.corrodinggames.rts.gameFramework.l");
                Object gs = lCls.getMethod("B").invoke(null);
                Object bs = gs.getClass().getField("bs").get(gs);
                sb.append("noAI bs=").append(bs == null ? "null" : bs.getClass().getSimpleName());
            }
            System.out.println(sb);
        } catch (Throwable t) {
            System.out.println("[LIVE] 采样异常: " + t + " @" + t.getStackTrace()[0]);
        }
    }
}
