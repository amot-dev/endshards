if [ $# -lt 2 ]
then
  echo "Run install_tests <world_name> <player_name>"
  exit
fi

rm -rf $1/datapacks/tests
cp -r tests $1/datapacks/tests

for i in $(find $1/datapacks/ -type f -name "*.mctest")
do
    python3 mctranspiler -s $i -p $2
done