import java.lang.reflect.Field;
import java.util.List;

/** 多人游戏大厅任务 — 回主菜单 → 刷新服务器列表 → 显示大厅 → 报告服务器. */
public class LobbyTask2 implements Runnable {
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
            if (servers != null) {
                int i = 0;
                for (Object s : servers) {
                    System.out.println("[LOBBY]   " + s);
                    if (++i >= 10) { System.out.println("[LOBBY]   ..."); break; }
                }
            }
            // 5. 当前文档确认
            Object doc = root.getClass().getMethod("getCurrentDocumentPath").invoke(root);
            System.out.println("[LOBBY] 当前文档: " + doc);
        } catch (Throwable t) {
            System.out.println("[LOBBY] 任务失败:"); t.printStackTrace(System.out);
        }
    }
}
