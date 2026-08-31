## 变更说明

<!-- 简述本 PR 做了什么 (中文) -->

## 证据锚点

<!-- 每个重命名/修复的证据: 02/02b 行号 或 javap 字节码事实 (铁律 1) -->

## 验证结果

<!-- gate 前后错误数对比 + 其他验证 (manager check / split_mappings) -->

- [ ] `python tools/gates/javac_gate.py` 前后对比: 净收益非负 (纯可读性改动注明"编译零变化")
- [ ] `python tools/manager.py check`: 0 错误
- [ ] supplement 变化后已重跑 `split_mappings.py`

## 规则 7 收尾

- [ ] D1 口径实测同步 (CLAUDE/README/STATUS)
- [ ] D2 会话记录 (sessions/ 新篇)
- [ ] D5 PLAN 会话行 + PENDING 流转
- [ ] D6 新工具登记 (如有)

## 审查自查

- [ ] 无运行产物/无关文件入库
- [ ] 广播原子提交 (跨文件改动同 commit)
