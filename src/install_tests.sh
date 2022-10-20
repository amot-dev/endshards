if [ $# -lt 2 ]
then
  echo "Run install_tests <world_name> <player_name>"
  exit
fi

rm -rf $1/datapacks/tests
cp -r tests $1/datapacks/tests

export PLAYERNAME=$2

for i in $(find $1/datapacks/ -type f -name "*.mcfunction")
do
    envsubst < $i | sponge $i
done