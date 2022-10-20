# Prepare test
execute if score playername iter matches 0 run tellraw @a {"text":"[-- advancements/ender_armor_played_self/no_totem --]","color":"aqua"}
execute if score playername iter matches 0 run function endshards_tests:clear
execute if score playername iter matches 0 run item replace entity playername armor.head with endshards:ender_helmet
execute if score playername iter matches 0 run item replace entity playername armor.chest with endshards:ender_chestplate
execute if score playername iter matches 0 run item replace entity playername armor.legs with endshards:ender_leggings
execute if score playername iter matches 0 run item replace entity playername armor.feet with endshards:ender_boots
execute if score playername iter matches 0 run attribute playername minecraft:generic.max_health base set 1
execute if score playername iter matches 0 run effect give playername minecraft:poison 1 1

# Run test
execute if score playername iter matches 1 at playername run tp playername ~ ~4 ~
execute if score playername iter matches 1 run effect give playername endshards:ender_armor_cooldown

# Check conditions
execute if score playername iter matches 2 as @a[advancements={endshards:ender_armor_played_self=true}] run scoreboard players playername set pass 1
execute if score playername iter matches 2 if score playername pass matches 0 run /tellraw @a {"text":"[---- Test Failed ----]","color":"red"}
execute if score playername iter matches 2 if score playername pass matches 1 run /tellraw @a {"text":"[---- Test Passed ----]","color":"green"}

# Cleanup
execute if score playername iter matches 2 run attribute playername minecraft:generic.max_health base set 20

# Loop control
scoreboard players add playername iter 1
execute if score playername iter matches 3 run scoreboard players set playername loop 0
execute unless score playername iter matches 3 run scoreboard players set playername loop 1
execute if score playername loop matches 1 run function endshards_tests:advancements/ender_armor_played_self/no_totem
execute if score playername iter matches 3 run scoreboard players set playername iter 0