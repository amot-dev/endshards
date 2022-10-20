tellraw @a {"text":"[-- Test Run Starting --]","color":"aqua"}

scoreboard objectives add iter dummy
scoreboard objectives add loop dummy
scoreboard objectives add pass dummy
gamerule doImmediateRespawn true
gamerule sendCommandFeedback true

function endshards_tests:clear