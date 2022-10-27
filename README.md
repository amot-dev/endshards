[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/asd1o1/endshards/build)
![Endshards Version](https://img.shields.io/github/v/release/asd1o1/endshards?include_prereleases)
![Endshards Source](https://img.shields.io/badge/source-v0.2.5-orange)
![Minecraft Version](https://img.shields.io/badge/Minecraft-1.19.0-blue)
![FabricMC Version](https://img.shields.io/badge/FabricMC-0.56.0%2B1.19.0-blue)
# End Shards
## About
Endshards is a mod for those players who are bored in the endgame.
Sure, building has its appeal, but you want challenge! Adventure!
Well, you need not look further. Endshards extends the late game by
making it more difficult to acquire gear beyond diamond, each set
requiring you to defeat a boss. This includes the existing Netherite,
which will require defeating the Wither. If that seems a bit mean,
don't worry, Netherite and the new gear added by Endshards all have
special abilities to make your gameplay more fun!

Finally, Endshards adds a new dimension, the Nightmare Realm, somewhat
inspired by the Spirit World from Witchery. This new dimension is still
in the works, and even I don't know what will be in it yet, but I can promise
a variety of biomes and an exciting new boss fight!

More info on the [wiki](https://github.com/asd1o1/endshards/wiki).

## Downloads
Available on GitHub. Will soon be available on Modrinth and Curseforge.

## Installation
 - Install Fabric for the desired Minecraft version
 - Install the latest Fabric API version
 - Copy the endshards jar file into `.minecraft/mods`

## Testing
I've included a test suite I made using a data pack and mcfunctions. If you've used mcfunctions before,
you'll know that anything involving time delays is horrendous to work with. To remedy that, I've created
a new pseudo-language, mctest, to write tests. Tests can be transpiled to mcfunctions with `mctranspiler.py`,
which is also handled automatically when installing tests with `install_tests.sh`. To try out the tests:
 - Ensure Python 3 is installed
 - Run `.install_tests.sh <path_to_world_folder> <playername>`
 - Open the world in Minecraft (with the mod installed)
 - Start a test run with `/function endshards_tests:dispatch`
   - You can optionally also start tests individually
   - First, run `/function endshards_tests:init`
   - Then, run the test(s). For example, `/function endshards_tests/advancements/ender_armor_played_self`
   - When done, run `/function endshards_tests:deinit`