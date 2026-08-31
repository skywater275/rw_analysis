# docs/generated — 自动生成文档

> 本目录内容由 tools/ 脚本生成, 不手工编辑。修改一律通过重新生成, 手动改动会在下次生成时被覆盖。

## 分类索引

| 目录 | 内容 | 生成工具 | 可重生成 |
|------|------|----------|----------|
| method-catalog/ | 方法目录 (24 类: GameAction/ActionWrapper/BuildAction 等) | `python tools/utils/method_catalog.py` | ✅ 是 |
| runtime-logs/ | 运行日志 (JFR 性能分析 + 动态测试 stdout/log) | 动态测试平台 (v19.113+) | ✅ 是 (⚠️ v19.133f98 起解除 git 跟踪, 不入库) |

## method-catalog/

方法目录 — 从 **02-decompiled** 源码提取的方法签名/行号/字符串常量/调用关系。
02 是混淆名真源 (不受 03 改名影响), 目录内容长期有效。

生成方式:
```bash
python tools/method_catalog.py --class GameAction        # 单类
python tools/method_catalog.py --package game/units/a   # 整包
```

> 注意: 类名为 02 混淆名 (如 `game/units/a/s.java` = GameAction),
> 对应关系查 mappings/class-discoveries.csv。

## runtime-logs/

动态测试运行日志。v19.133f98 项目整理: 76 个产物 (JFR×3 + log×45 + stdout×28) 解除 git 跟踪并删磁盘
(二进制/大日志不入库), 见 .gitignore `docs/generated/runtime-logs/`。重新运行动态测试即可再生成。

## 规则

1. **禁止手改本目录任何文件** — 需修正时改生成工具后重新生成
2. 新产物入 git 前: 更新本索引 (D3) + 确认 .gitignore 覆盖
