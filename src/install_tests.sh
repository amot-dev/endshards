if [ $# -lt 2 ] || [ $# -gt 2 ]
then
  echo "Run install_tests <world_name> <player_name>"
  exit
fi

rm -rf "$1"/datapacks/tests
cp -r tests "$1"/datapacks/tests

for i in "$(find "$1"/datapacks/ -type f -name "*.mctest")"
do
    python3 mctranspiler.py -s "$i" -p "$2"
    rm -f "$i"
done