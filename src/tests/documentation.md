# End Shards Test Suite
The suite is implemented using a datapack with mcfunctions. It is very janky, but it's automated ¯\_(ツ)_/¯

## Advancements
These tests ensure that advancement criteria is properly triggered (or not triggered).
Note that the purpose of these tests is NOT to test the actual abilities/mechanisms they represent,
but rather to ensure the achievement is given when the ability/mechanism is triggered. As an example,
these tests don't care if the player dies when the Ender Armor ability is triggered. All that matters
is that the advancement is properly granted.

### ender_armor_fall
| Case            | Pass Condition               | Requirements                                |
|-----------------|------------------------------|---------------------------------------------|
| Base            | ender_armor_fall granted     | <li>Ender Armor</li>                        |
| Active Cooldown | ender_armor_fall not granted | <li>Ender Armor</li><li>Ender Cooldown</li> |
| No Armor        | ender_armor_fall not granted | None                                        |

### ender_armor_played_self
| Case         | Pass Condition                      | Requirements                                                             |
|--------------|-------------------------------------|--------------------------------------------------------------------------|
| Base         | ender_armor_played_self granted     | <li>Ender Armor</li><li>Ender Cooldown 59s</li>                          |
| Totem        | ender_armor_played_self granted     | <li>Ender Armor</li><li>Ender Cooldown 59s</li><li>Totem of Undying</li> |
| Low Cooldown | ender_armor_played_self not granted | <li>Ender Armor</li><li>Ender Cooldown <59s</li>                         |
| No Cooldown  | ender_armor_played_self not granted | <li>Ender Armor</li>                                                     |
| No Armor     | ender_armor_played_self not granted | None                                                                     |

### ender_sword_warp
| Case            | Pass Condition               | Requirements                                |
|-----------------|------------------------------|---------------------------------------------|
| Base            | ender_sword_warp granted     | <li>Ender Sword</li>                        |
| Active Cooldown | ender_sword_warp not granted | <li>Ender Sword</li><li>Ender Cooldown</li> |

### ender_tool_warp
| Case            | Pass Condition              | Requirements                                  |
|-----------------|-----------------------------|-----------------------------------------------|
| Base            | ender_tool_warp granted     | <li>Ender Pickaxe</li>                        |
| Active Cooldown | ender_tool_warp not granted | <li>Ender Pickaxe</li><li>Ender Cooldown</li> |

### netherite_armor_protect
| Case            | Pass Condition                      | Requirements                                        |
|-----------------|-------------------------------------|-----------------------------------------------------|
| Base            | netherite_armor_protect granted     | <li>Netherite Armor</li>                            |
| Active Cooldown | netherite_armor_protect not granted | <li>Netherite Armor</li><li>Netherite Cooldown</li> |
| No Armor        | netherite_armor_protect not granted | None                                                |

### netherite_sword_flame
| Case            | Pass Condition                    | Requirements                                        |
|-----------------|-----------------------------------|-----------------------------------------------------|
| Base            | netherite_sword_flame granted     | <li>Netherite Sword</li>                            |
| Active Cooldown | netherite_sword_flame not granted | <li>Netherite Sword</li><li>Netherite Cooldown</li> |

### netherite_sword_sacrifice
| Case          | Pass Condition                        | Requirements                               |
|---------------|---------------------------------------|--------------------------------------------|
| Base          | netherite_sword_sacrifice granted     | <li>Netherite Sword</li><li>100 kills</li> |
| Too Few Kills | netherite_sword_sacrifice not granted | <li>Netherite Sword</li><li>99 kills</li>  |
| No Kills      | netherite_sword_sacrifice not granted | <li>Netherite Sword</li><li>0 kills</li>   |

### sculk_armor_light
| Case     | Pass Condition                | Requirements         |
|----------|-------------------------------|----------------------|
| Base     | sculk_armor_light granted     | <li>Sculk Armor</li> |
| No Armor | sculk_armor_light not granted | None                 |

### sculk_sword_enthrall
| Case                | Pass Condition                   | Requirements                                |
|---------------------|----------------------------------|---------------------------------------------|
| Base                | sculk_sword_enthrall granted     | <li>Sculk Sword</li>                        |
| Active Cooldown     | sculk_sword_enthrall not granted | <li>Sculk Sword</li><li>Sculk Cooldown</li> |
| Out of Thrall Slots | sculk_sword_enthrall not granted | <li>Sculk Sword</li><li>3 Thralls</li>      |
| Invalid Target      | sculk_sword_enthrall not granted | <li>Sculk Sword</li><li>Invalid Mob</li>    |

### sculk_tool_mending_break
| Case                | Pass Condition                       | Requirements                       |
|---------------------|--------------------------------------|------------------------------------|
| Base                | sculk_tool_mending_break granted     | <li>Sculk Pickaxe w/ Mending</li>  |
| Out of Thrall Slots | sculk_tool_mending_break not granted | <li>Sculk Pickaxe w/o Mending</li> |

### sculk_tool_xp
| Case | Pass Condition        | Requirements           |
|------|-----------------------|------------------------|
| Base | sculk_tool_xp granted | <li>Sculk Pickaxe</li> |
