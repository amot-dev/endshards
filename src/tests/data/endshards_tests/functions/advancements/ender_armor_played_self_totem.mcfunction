# Prepare test
execute if score $PLAYERNAME iter matches 0 run tellraw @a {"text":"[-- advancements/ender_armor_played_self_totem --]","color":"aqua"}
execute if score $PLAYERNAME iter matches 0 run function endshards_tests:clear
execute if score $PLAYERNAME iter matches 0 run item replace entity $PLAYERNAME armor.head with endshards:ender_helmet
execute if score $PLAYERNAME iter matches 0 run item replace entity $PLAYERNAME armor.chest with endshards:ender_chestplate
execute if score $PLAYERNAME iter matches 0 run item replace entity $PLAYERNAME armor.legs with endshards:ender_leggings
execute if score $PLAYERNAME iter matches 0 run item replace entity $PLAYERNAME armor.feet with endshards:ender_boots
execute if score $PLAYERNAME iter matches 0 run item replace entity $PLAYERNAME weapon.offhand with minecraft:totem_of_undying
execute if score $PLAYERNAME iter matches 0 run attribute $PLAYERNAME minecraft:generic.max_health base set 1
execute if score $PLAYERNAME iter matches 0 run effect give $PLAYERNAME minecraft:poison 1 1

# Run test
execute if score $PLAYERNAME iter matches 1 at $PLAYERNAME run tp $PLAYERNAME ~ ~4 ~
execute if score $PLAYERNAME iter matches 1 run effect give $PLAYERNAME endshards:ender_cooldown 60 1

# Check conditions
execute if score $PLAYERNAME iter matches 2 as @a[advancements={endshards:ender_armor_played_self=true}] run scoreboard players set @s pass 1
execute if score $PLAYERNAME iter matches 2 if score $PLAYERNAME pass matches 0 run tellraw @a {"text":"[-- Test Failed --]","color":"red"}
execute if score $PLAYERNAME iter matches 2 if score $PLAYERNAME pass matches 1 run tellraw @a {"text":"[-- Test Passed --]","color":"green"}

# Cleanup
execute if score $PLAYERNAME iter matches 2 run attribute $PLAYERNAME minecraft:generic.max_health base set 20

# Loop control
scoreboard players add $PLAYERNAME iter 1
execute if score $PLAYERNAME iter matches 3.. run scoreboard players set $PLAYERNAME loop 0
execute if score $PLAYERNAME iter matches ..2 run scoreboard players set $PLAYERNAME loop 1
execute if score $PLAYERNAME loop matches 1 run schedule function endshards_tests:advancements/ender_armor_played_self_totem 20
execute if score $PLAYERNAME iter matches 3 run scoreboard players set $PLAYERNAME iter 0