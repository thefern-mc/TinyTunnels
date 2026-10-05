
Tiny Tunnels: Development Status
=======

**Heads up for contributors:** active development is not happening on `main` right now.

All current work is on the [`mc1.21.1/dev`](https://github.com/thefern-mc/TinyTunnels/tree/mc1.21.1/dev) branch. Please base any issues, PRs, or testing on that branch.

The rough plan:

1. **Now: 1.21.1 beta.** The 1.21.1 build is in beta, with bug fixes and remaining features landing on `mc1.21.1/dev`.
2. **After beta (about 4–8 weeks, no fixed date):** once the major bugs are fixed and the planned features are done, I'll come back to `main` and finish the core mod for 26.x.
3. **Later:** if Create has been released for 26.x by then, the Create addon will be added there too.

### Downloads (1.21.1 beta)

| Mod | CurseForge | Modrinth |
| --- | --- | --- |
| Tiny Tunnels (core) | [Beta](https://www.curseforge.com/minecraft/mc-mods/tinytunnels) | [Under review](https://modrinth.com/mod/tiny-tunnels) |
| Tiny Tunnels: Create addon | [Beta](https://www.curseforge.com/minecraft/mc-mods/tiny-tunnels-create) | [Coming soon](https://modrinth.com/project/tiny-tunnels-create), waiting on core approval so it can be listed as a dependency |

`main` (26.x) will stay mostly quiet until then.

Questions? Open a thread in [GitHub Discussions](https://github.com/thefern-mc/TinyTunnels/discussions) or email fernandobe+git@protonmail.com.

Installation information
=======

This template repository can be directly cloned to get you started with a new
mod. Simply create a new repository cloned from this one, by following the
instructions provided by [GitHub](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template).

Once you have your clone, simply open the repository in the IDE of your choice. The usual recommendation for an IDE is either IntelliJ IDEA or Eclipse.

If at any point you are missing libraries in your IDE, or you've run into problems you can
run `gradlew --refresh-dependencies` to refresh the local cache. `gradlew clean` to reset everything 
{this does not affect your code} and then start the process again.

Mapping Names:
============
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields 
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Additional Resources: 
==========
Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/
