# End Shards Test Suite
The suite is implemented using a datapack with mcfunctions. It is very janky, but it's automated ¯\_(ツ)_/¯

## Abilities
These tests are meant to ensure the various abilities work as advertised on the wiki.

### ender_armor
| Case            | Pass Condition                          | Requirements                                |
|-----------------|-----------------------------------------|---------------------------------------------|
| Base            | No damage taken when using ability      | <li>Ender Armor</li>                        |
| Active Cooldown | Damage taken when trying to use ability | <li>Ender Armor</li><li>Ender Cooldown</li> |

### ender_sword
| Case            | Pass Condition                                  | Requirements                                |
|-----------------|-------------------------------------------------|---------------------------------------------|
| Base            | Mob is displaced when using ability             | <li>Ender Sword</li>                        |
| Active Cooldown | Mob is not displaced when trying to use ability | <li>Ender Sword</li><li>Ender Cooldown</li> |
| Invalid Target  | Mob is not displaced when trying to use ability | <li>Ender Sword</li><li>Invalid Mob</li>    |

### ender_tool
| Case           | Pass Condition                   | Requirements           |
|----------------|----------------------------------|------------------------|
| Pickaxe        | Broken block warps to inventory  | <li>Ender Pickaxe</li> |
| Shovel         | Broken block warps to inventory  | <li>Ender Shovel</li>  |
| Axe            | Broken block warps to inventory  | <li>Ender Axe</li>     |
| Hoe            | Broken block warps to inventory  | <li>Ender Hoe</li>     |
| Sword          | Broken block drops on the ground | <li>Ender Sword</li>   |
| Full Inventory | Broken block drops on the ground | <li>Ender Pickaxe</li> |

### netherite_armor
| Case            | Pass Condition                  | Requirements                                        |
|-----------------|---------------------------------|-----------------------------------------------------|
| Base            | Resistance effect is given      | <li>Netherite Armor</li><li>5 Hearts</li>           |
| Active Cooldown | Resistance effect is not given  | <li>Netherite Armor</li><li>Netherite Cooldown</li> |
| Healthy         | Resistance effect is not given  | <li>Netherite Armor</li><li>5.5 Hearts</li>         |

### netherite_sword
| Case            | Pass Condition                                    | Requirements                                        |
|-----------------|---------------------------------------------------|-----------------------------------------------------|
| Base            | Mob is set on fire when using ability             | <li>Netherite Sword</li>                            |
| Active Cooldown | Mob is not set on fire when trying to use ability | <li>Netherite Sword</li><li>Netherite Cooldown</li> |
| Invalid Target  | Mob is not set on fire when trying to use ability | <li>Netherite Sword</li><li>Invalid Mob</li>        |

### netherite_tool
| Case     | Pass Condition                             | Requirements               |
|----------|--------------------------------------------|----------------------------|
| Pickaxe  | Ore drop is smelted and xp is dropped      | <li>Netherite Pickaxe</li> |
| Shovel   | Clay drop is smelted and xp is dropped     | <li>Netherite Shovel</li>  |
| Axe      | Wood drop is smelted and xp is dropped     | <li>Netherite Axe</li>     |
| Hoe      | Wet sponge drop is dried and xp is dropped | <li>Netherite Hoe</li>     |
| Sword    | Wood drop is raw                           | <li>Netherite Sword</li>   |
| No Smelt | Dirt drop is raw                           | <li>Netherite Pickaxe</li> |

### sculk_armor
| Case          | Pass Condition        | Requirements                    |
|---------------|-----------------------|---------------------------------|
| Base          | Night vision is given | <li>Sculk Armor</li>            |
| Missing Piece | Night vision expires  | <li>Sculk Armor (3 pieces)</li> |

### sculk_sword
| Case                | Pass Condition                                   | Requirements                                |
|---------------------|--------------------------------------------------|---------------------------------------------|
| Base                | Target becomes thrall when using ability         | <li>Sculk Sword</li>                        |
| Active Cooldown     | Target does not become thrall when using ability | <li>Sculk Sword</li><li>Sculk Cooldown</li> |
| Out of Thrall Slots | Target does not become thrall when using ability | <li>Sculk Sword</li><li>3 Thralls</li>      |
| Invalid Target      | Target does not become thrall when using ability | <li>Sculk Sword</li><li>Invalid Mob</li>    |
*These tests only check the Sculk Sword's ability to create Thralls.
See the dedicated section for testing Thrall behaviour.*

### sculk_tool
| Case           | Pass Condition                                | Requirements                              |
|----------------|-----------------------------------------------|-------------------------------------------|
| Pickaxe        | XP is sometimes dropped when breaking non-ore | <li>Sculk Pickaxe</li>                    |
| Shovel         | XP is sometimes dropped when breaking non-ore | <li>Sculk Shovel</li>                     |
| Axe            | XP is sometimes dropped when breaking non-ore | <li>Sculk Axe</li>                        |
| Hoe            | XP is sometimes dropped when breaking non-ore | <li>Sculk Hoe</li>                        |
| Sword          | XP is not dropped when breaking non-ore       | <li>Sculk Sword</li>                      |
| Ore            | Regular XP is dropped when breaking ore       | <li>Sculk Pickaxe</li>                    |
| Silk Touch     | XP is sometimes dropped when breaking non-ore | <li>Sculk Pickaxe</li><li>Silk Touch</li> |
| Silk Touch Ore | No XP is dropped when breaking ore            | <li>Sculk Pickaxe</li><li>Silk Touch</li> |

## Challenges
These tests ensure that challenge advancement criteria is properly triggered (or not triggered).
The reason for these tests is that these particular advancements are not always easy to reliably
test, and can be easy to overlook. Regular advancements are not being tested as those can easily
be seen while running tests from the Abilities section.

### ender_armor_played_self
| Case         | Pass Condition                      | Requirements                                                             |
|--------------|-------------------------------------|--------------------------------------------------------------------------|
| Base         | ender_armor_played_self granted     | <li>Ender Armor</li><li>Ender Cooldown 59s</li>                          |
| Totem        | ender_armor_played_self granted     | <li>Ender Armor</li><li>Ender Cooldown 59s</li><li>Totem of Undying</li> |
| Low Cooldown | ender_armor_played_self not granted | <li>Ender Armor</li><li>Ender Cooldown <59s</li>                         |
| No Cooldown  | ender_armor_played_self not granted | <li>Ender Armor</li>                                                     |
| No Armor     | ender_armor_played_self not granted | None                                                                     |

### netherite_sword_sacrifice
| Case          | Pass Condition                        | Requirements                               |
|---------------|---------------------------------------|--------------------------------------------|
| Base          | netherite_sword_sacrifice granted     | <li>Netherite Sword</li><li>100 kills</li> |
| Too Few Kills | netherite_sword_sacrifice not granted | <li>Netherite Sword</li><li>99 kills</li>  |
| No Kills      | netherite_sword_sacrifice not granted | <li>Netherite Sword</li><li>0 kills</li>   |

### sculk_tool_mending_break
| Case       | Pass Condition                       | Requirements                       |
|------------|--------------------------------------|------------------------------------|
| Base       | sculk_tool_mending_break granted     | <li>Sculk Pickaxe w/ Mending</li>  |
| No Mending | sculk_tool_mending_break not granted | <li>Sculk Pickaxe w/o Mending</li> |

## Thralls
Will be added once Thrall behavior is finalized and less buggy.

## World Generation
May or may not be added. If added, it will likely be as a test will manual visual inspection,
as it may be hard to check proper conditions automatically.