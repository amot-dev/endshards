tellraw @a {"text":"[-- Test Run Complete --]","color":"aqua"}

scoreboard objectives remove iter
scoreboard objectives remove loop
scoreboard objectives remove pass
gamerule doImmediateRespawn false
gamerule sendCommandFeedback true

function endshards_tests:clear