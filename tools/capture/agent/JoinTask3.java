import java.lang.reflect.Field;
import java.util.List;

/** 多人游戏大厅任务 — 回主菜单 → 刷新服务器列表 → 显示大厅 → 报告服务器. */
public class JoinTask3 implements Runnable {
    public void run() {
        try {
            Class<?> libA = Class.forName("com.corrodinggames.librocket.a");
            Object gui = libA.getMethod("a").invoke(null);
            Object libB = gui.getClass().getField("b").get(gui);
            Object se = libB.getClass().getField("c").get(libB);
            Object root = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine")
                    .getMethod("getRoot").invoke(se);
            // 1. 回主菜单
            root.getClass().getMethod("showMainMenu").invoke(root);
            System.out.println("[LOBBY] showMainMenu OK");
            Thread.sleep(1500);
            // 打开服务器大厅 UI (multiplayerLobby.rml — rml 资源铁证)
            try {
                root.getClass().getMethod("open", String.class, Object.class).invoke(root, "multiplayerLobby.rml", null);
                System.out.println("[LOBBY] multiplayerLobby.rml 已打开");
            } catch (Throwable t) {
                System.out.println("[LOBBY] open 异常: " + t.getCause());
            }
            Thread.sleep(2000);
            // 2. 刷新服务器列表 (网络请求)
            try {
                root.getClass().getMethod("refreshServerList").invoke(root);
                System.out.println("[LOBBY] refreshServerList 已发起");
            } catch (Throwable t) {
                System.out.println("[LOBBY] refreshServerList 异常: " + t.getCause());
            }
            Thread.sleep(5000);
            // 3. 显示服务器大厅 UI
            try {
                root.getClass().getMethod("displayServerList").invoke(root);
                System.out.println("[LOBBY] displayServerList OK");
            } catch (Throwable t) {
                System.out.println("[LOBBY] displayServerList 异常: " + t.getCause());
            }
            Thread.sleep(2000);
            // 4. 报告发现的服务器
            Field lf = root.getClass().getDeclaredField("lastSortedDiscoveredServers");
            lf.setAccessible(true);
            List<?> servers = (List<?>) lf.get(root);
            System.out.println("[LOBBY] 发现服务器: " + (servers == null ? "null" : servers.size()));
            // 全字段 dump + 随机选真实服务器 join
            java.util.List<Object> candidates = new java.util.ArrayList<Object>();
            if (servers != null) {
                Class<?> gCls = Class.forName("com.corrodinggames.rts.gameFramework.j.g");
                int i = 0;
                for (Object s : servers) {
                    String d = (String) gCls.getField("d").get(s);
                    String e = (String) gCls.getField("e").get(s);
                    String f = (String) gCls.getField("f").get(s);
                    int g = gCls.getField("g").getInt(s);
                    System.out.println("[LOBBY] d=" + d + " e=" + e + " f=" + f + " g=" + g);
                    // 真实服务器: 排除官方/本地
                    if (!d.equals("127.0.0.1") && !d.startsWith("9DDA") && !d.equals("0.0.0.0")) {
                        candidates.add(s);
                    }
                    if (++i >= 12) break;
                }
            }
            if (!candidates.isEmpty()) {
                Class<?> gCls = Class.forName("com.corrodinggames.rts.gameFramework.j.g");
                // 轮试最多 6 个不同地址 (每个 ~7 秒超时)
                int tried = 0;
                for (Object s : candidates) {
                    if (tried >= 6) break;
                    String d = (String) gCls.getField("d").get(s);
                    int g = gCls.getField("g").getInt(s);
                    String addr = d + ":" + g;
                    System.out.println("[JOIN] 尝试 " + (tried + 1) + "/6 → " + addr);
                    try {
                        root.getClass().getMethod("joinServer", String.class).invoke(root, addr);
                    } catch (Throwable t) {
                        System.out.println("[JOIN] joinServer 异常: " + t.getCause());
                    }
                    Thread.sleep(7000);
                    // 检查当前文档: battleroom = 成功
                    Object doc = root.getClass().getMethod("getCurrentDocumentPath").invoke(root);
                    if (doc != null && doc.toString().contains("battleroom")) {
                        System.out.println("[JOIN] 加入成功! 当前文档: " + doc);
                        break;
                    }
                    tried++;
                }
                if (tried >= 6) System.out.println("[JOIN] 6 个服务器全部超时 (海外网络)");
            } else {
                System.out.println("[JOIN] 无候选真实服务器");
            }
            // 5. 当前文档确认
            Object doc = root.getClass().getMethod("getCurrentDocumentPath").invoke(root);
            System.out.println("[LOBBY] 当前文档: " + doc);
        } catch (Throwable t) {
            System.out.println("[LOBBY] 任务失败:"); t.printStackTrace(System.out);
        }
    }
}
