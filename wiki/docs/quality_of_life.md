# Quality of Life
Endshards adds a few gamerules that can make life easier or harder for you.

## Inventory Equipment Switch
`/gamerule doInventoryEquipmentSwitch [true | false]`

This gamerule allows shift-clicking armor pieces in the inventory to replace the currently equipped armor. Very useful
as the armor sets in this mod provide more benefit in some situations than others.

Default: `true`

## Night Vision Flicker
`/gamerule doNightVisionFlicker [true | false]`

This gamerule can disable the flickering effect when Night Vision is about to end. Sculk Armor gives the player Night
Vision in 1 second increments, meaning the flicker would constantly happen unless this gamerule is false.

Default: `false`

## Thralls Attack Creepers
`/gamerule thrallsAttackCreepers [true | false]`

This gamerule can allow your Thralls to attack creepers. Best combined with `/gamerule mobGriefing false` as otherwise
creepers will retaliate against your thralls, most likely causing terrain damage. If set to false, Thralls will
blissfully watch as creepers sneak up on you, so be careful!

Default: `false`