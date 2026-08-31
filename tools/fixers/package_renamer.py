#!/usr/bin/env python3
"""
Phase B: Package-level renaming — obfuscated packages → readable names.
Moves files, updates package declarations, and rewrites all imports.
"""

import os, re, sys, shutil
from pathlib import Path
from collections import defaultdict

ROOT = Path(__file__).resolve().parent.parent.parent  # tools/fixers/ → ROOT
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DEOBFUSCATED_DIR as DEOBF, MAPPINGS_DIR
DEOBF = ROOT / "03-deobfuscated"

# Complete package mapping: obfuscated → readable
# Based on docs/ domain documentation + class-discoveries.csv
PKG_MAP = {
    # gameFramework → framework (main engine)
    "com.corrodinggames.rts.gameFramework.m": "com.corrodinggames.rts.gameFramework.rendering",
    "com.corrodinggames.rts.gameFramework.j": "com.corrodinggames.rts.gameFramework.network",
    "com.corrodinggames.rts.gameFramework.f": "com.corrodinggames.rts.gameFramework.ui",
    "com.corrodinggames.rts.gameFramework.b": "com.corrodinggames.rts.gameFramework.opengl",
    "com.corrodinggames.rts.gameFramework.d": "com.corrodinggames.rts.gameFramework.effects",
    "com.corrodinggames.rts.gameFramework.k": "com.corrodinggames.rts.gameFramework.pathfinding",
    "com.corrodinggames.rts.gameFramework.n": "com.corrodinggames.rts.gameFramework.ai",
    "com.corrodinggames.rts.gameFramework.a": "com.corrodinggames.rts.gameFramework.platform",
    "com.corrodinggames.rts.gameFramework.e": "com.corrodinggames.rts.gameFramework.filesystem",
    "com.corrodinggames.rts.gameFramework.g": "com.corrodinggames.rts.gameFramework.audio",
    "com.corrodinggames.rts.gameFramework.h": "com.corrodinggames.rts.gameFramework.steam",
    "com.corrodinggames.rts.gameFramework.c": "com.corrodinggames.rts.gameFramework.commands",
    # UI sub-packages
    "com.corrodinggames.rts.gameFramework.f.a": "com.corrodinggames.rts.gameFramework.ui.panels",
    # Rendering sub-packages
    "com.corrodinggames.rts.gameFramework.m.e": "com.corrodinggames.rts.gameFramework.rendering.textures",
    # OpenGL sub-packages
    "com.corrodinggames.rts.gameFramework.b.a": "com.corrodinggames.rts.gameFramework.opengl.batch",
    "com.corrodinggames.rts.gameFramework.b.a.a": "com.corrodinggames.rts.gameFramework.opengl.batch.resources",
    # Pathfinding sub-packages
    "com.corrodinggames.rts.gameFramework.k.a": "com.corrodinggames.rts.gameFramework.pathfinding.nodes",

    # game → game engine core
    "com.corrodinggames.rts.game.a": "com.corrodinggames.rts.game.ai",
    "com.corrodinggames.rts.game.b": "com.corrodinggames.rts.game.map",
    "com.corrodinggames.rts.game.d": "com.corrodinggames.rts.game.players",
    "com.corrodinggames.rts.game.e": "com.corrodinggames.rts.game.teams",
    "com.corrodinggames.rts.game.f": "com.corrodinggames.rts.game.projectiles",
    "com.corrodinggames.rts.game.g": "com.corrodinggames.rts.game.config",
    "com.corrodinggames.rts.game.h": "com.corrodinggames.rts.game.filters",
    "com.corrodinggames.rts.game.i": "com.corrodinggames.rts.game.screens",
    # AI sub-packages
    "com.corrodinggames.rts.game.a.a": "com.corrodinggames.rts.game.ai.strategies",

    # units → game units
    "com.corrodinggames.rts.game.units.a": "com.corrodinggames.rts.game.units.actions",
    "com.corrodinggames.rts.game.units.b": "com.corrodinggames.rts.game.units.buildings",
    "com.corrodinggames.rts.game.units.d": "com.corrodinggames.rts.game.units.commands",
    "com.corrodinggames.rts.game.units.e": "com.corrodinggames.rts.game.units.projectiles",
    "com.corrodinggames.rts.game.units.f": "com.corrodinggames.rts.game.units.pathfinding",
    "com.corrodinggames.rts.game.units.g": "com.corrodinggames.rts.game.units.weapons",
    "com.corrodinggames.rts.game.units.h": "com.corrodinggames.rts.game.units.debug",
    "com.corrodinggames.rts.game.units.i": "com.corrodinggames.rts.game.units.transport",
    # units sub-packages
    "com.corrodinggames.rts.game.units.d.a": "com.corrodinggames.rts.game.units.commands.slots",
    "com.corrodinggames.rts.game.units.e.a": "com.corrodinggames.rts.game.units.projectiles.config",
    "com.corrodinggames.rts.game.units.e.j": "com.corrodinggames.rts.game.units.projectiles.spawns",

    # custom units
    "com.corrodinggames.rts.game.units.custom.a": "com.corrodinggames.rts.game.units.custom.actions",
    "com.corrodinggames.rts.game.units.custom.b": "com.corrodinggames.rts.game.units.custom.animation",
    "com.corrodinggames.rts.game.units.custom.c": "com.corrodinggames.rts.game.units.custom.conditions",
    "com.corrodinggames.rts.game.units.custom.d": "com.corrodinggames.rts.game.units.custom.resources",
    "com.corrodinggames.rts.game.units.custom.e": "com.corrodinggames.rts.game.units.custom.effects",
    "com.corrodinggames.rts.game.units.custom.f": "com.corrodinggames.rts.game.units.custom.config",
    "com.corrodinggames.rts.game.units.custom.g": "com.corrodinggames.rts.game.units.custom.teams",
    # custom sub-packages
    "com.corrodinggames.rts.game.units.custom.a.a": "com.corrodinggames.rts.game.units.custom.actions.base",
    "com.corrodinggames.rts.game.units.custom.e.a": "com.corrodinggames.rts.game.units.custom.effects.config",

    # appFramework
    "com.corrodinggames.rts.appFramework.a": "com.corrodinggames.rts.appFramework.testing",

    # java platform
    "com.corrodinggames.rts.java.a": "com.corrodinggames.rts.java.platform",
    "com.corrodinggames.rts.java.b": "com.corrodinggames.rts.java.graphics",
    "com.corrodinggames.rts.java.c": "com.corrodinggames.rts.java.input",
    "com.corrodinggames.rts.java.d": "com.corrodinggames.rts.java.filesystem",
    "com.corrodinggames.rts.java.e": "com.corrodinggames.rts.java.steam",
    # java audio
    "com.corrodinggames.rts.java.audio.a": "com.corrodinggames.rts.java.audio.backend",

    # network (a.a.*) — external lib, keep prefix but make readable
    "a.a": "network.reliableudp",
    "a.a.a": "network.reliableudp.core",
    "a.a.a.g": "network.reliableudp.packets",

    # rts root — platform/network
    "com.corrodinggames.rts.a": "com.corrodinggames.rts.platform",
    "com.corrodinggames.rts.a.a": "com.corrodinggames.rts.platform.net",
}

def main():
    dry_run = "--dry-run" in sys.argv
    action = "DRY RUN" if dry_run else "APPLY"
    print(f"Package Renamer v1.0 — {action}")
    print(f"  {len(PKG_MAP)} package mappings")

    # Step 1: Collect all .java files
    all_files = {}
    for root, dirs, files in os.walk(DEOBF):
        for f in files:
            if f.endswith(".java"):
                fp = os.path.join(root, f)
                rel = os.path.relpath(fp, DEOBF)
                pkg = os.path.dirname(rel).replace(os.sep, ".")
                all_files[fp] = (pkg, f)

    print(f"  {len(all_files)} total .java files")

    # Step 2: Determine which files to move
    moves = []  # (old_path, new_path, old_pkg, new_pkg)
    for fp, (pkg, fname) in all_files.items():
        if pkg in PKG_MAP:
            new_pkg = PKG_MAP[pkg]
            # Build new path: DEOBF/new_pkg_parts/fname
            new_pkg_parts = new_pkg.split(".")
            new_dir = os.path.join(str(DEOBF), *new_pkg_parts)
            new_path = os.path.join(new_dir, fname)
            moves.append((fp, new_path, pkg, new_pkg))

    print(f"  {len(moves)} files to move ({len(set(m[2] for m in moves))} packages)")

    if dry_run:
        print("\n  Package moves:")
        for old_pkg in sorted(set(m[2] for m in moves)):
            new_pkg = PKG_MAP[old_pkg]
            cnt = sum(1 for m in moves if m[2] == old_pkg)
            print(f"    {cnt:4d}  {old_pkg} → {new_pkg}")
        print("\n  Use --apply to execute")
        return

    # Step 3: Move files + update package declarations
    moved = 0
    for fp, new_path, old_pkg, new_pkg in moves:
        # Read file content
        with open(fp, "r", encoding="utf-8", errors="replace") as f:
            content = f.read()

        # Update package declaration
        old_pkg_line = f"package {old_pkg};"
        new_pkg_line = f"package {new_pkg};"
        if old_pkg_line in content:
            content = content.replace(old_pkg_line, new_pkg_line)
        else:
            # Might use different formatting
            content = re.sub(rf"^package\s+{re.escape(old_pkg)}\s*;", f"package {new_pkg};", content, flags=re.MULTILINE)

        # Create target directory
        os.makedirs(os.path.dirname(new_path), exist_ok=True)

        # Write to new location
        with open(new_path, "w", encoding="utf-8") as f:
            f.write(content)

        # Remove old file
        os.remove(fp)
        moved += 1

    print(f"  Moved {moved} files")

    # Step 4: Update ALL import statements across ALL files
    # Build reverse map: old_pkg → new_pkg
    for old_pkg, new_pkg in PKG_MAP.items():
        old_prefix = old_pkg + "."
        new_prefix = new_pkg + "."

        # Update imports in ALL .java files (including ones we already moved)
        import_fixes = 0
        for fp in all_files:
            # File might have been moved
            if not os.path.exists(fp):
                # Check new location
                for m in moves:
                    if m[0] == fp:
                        fp = m[1]
                        break
                else:
                    continue

            with open(fp, "r", encoding="utf-8", errors="replace") as f:
                content = f.read()

            if old_prefix in content:
                content = content.replace(old_prefix, new_prefix)
                with open(fp, "w", encoding="utf-8") as f:
                    f.write(content)
                import_fixes += 1

        if import_fixes > 0:
            print(f"    Import fix: {old_pkg} → {new_pkg} ({import_fixes} files)")

    print(f"\n  Done. Run javac_gate to verify.")


if __name__ == "__main__":
    main()
