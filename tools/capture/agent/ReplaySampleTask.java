import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/** 回放 AI 行为全程采样 — 自重现任务: 每 300 帧采样 AI 状态, 6000 帧后停止. */
public class ReplaySampleTask implements Runnable {
    static int count = 0;
    static List<Object> queue = null;
    static Object action = null;  // RunnableAction 实例 (自投递用)

    public void run() {
        try {
            count++;
            if (count == 1) {
                // 第一次运行: 在游戏线程加载回放 (attach 线程无 GL 上下文 — v19.113 教训⑤)
                Class<?> libA0 = Class.forName("com.corrodinggames.librocket.a");
                Object gui0 = libA0.getMethod("a").invoke(null);
                Object libB0 = gui0.getClass().getField("b").get(gui0);
                Object se0 = libB0.getClass().getField("c").get(libB0);
                Object root0 = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine")
                        .getMethod("getRoot").invoke(se0);
                try {
                    root0.getClass().getMethod("loadReplay", String.class).invoke(
                            root0, "Crossing Large (10p) [v1.15] (10 2月 2026 21.49.55).replay");
                    System.out.println("[RPL] 回放已加载 (游戏线程)");
                } catch (Throwable t) {
                    System.out.println("[RPL] loadReplay 失败: " + t.getCause());
                }
            }
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
            if (count < 6000) {
                // 自投递 (每帧一个, 采样内部节流)
                Class<?> ra = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine$RunnableAction");
                java.lang.reflect.Constructor<?> c = ra.getDeclaredConstructor(Runnable.class);
                c.setAccessible(true);
                queue.add(c.newInstance(this));
            } else {
                System.out.println("[RPL] 采样结束 (6000 帧)");
            }
        } catch (Throwable t) {
            System.out.println("[RPL] 任务失败:"); t.printStackTrace(System.out);
        }
    }

    void sample() {
        try {
            Class<?> nCls = Class.forName("com.corrodinggames.rts.game.n");
            Object[] players = (Object[]) nCls.getField("b").get(null);
            Class<?> aiCls = Class.forName("com.corrodinggames.rts.game.a.a");
            Class<?> cmCls = Class.forName("com.corrodinggames.rts.game.a.i");
            StringBuilder sb = new StringBuilder("[RPL] frame=" + count + " ");
            int aiCount = 0;
            for (Object p : players) {
                if (p == null || !aiCls.isInstance(p)) continue;
                aiCount++;
                Field kF = nCls.getField("k");
                int team = kF.getInt(p);
                Field bmF = aiCls.getField("bm");
                ConcurrentLinkedQueue<?> bm = (ConcurrentLinkedQueue<?>) bmF.get(p);
                sb.append("AI").append(team).append(":c").append(bm.size());
                int i = 0;
                for (Object cm : bm) {
                    Field bF = cmCls.getField("b");
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
            System.out.println("[RPL] 采样异常: " + t.getMessage());
        }
    }
}
