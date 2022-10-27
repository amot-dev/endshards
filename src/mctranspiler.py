import sys, getopt
from math import gcd


def interpret_wait(infile, outfile):
	waits = dict()
	wait_time = 0
	total_wait = 0
	wait_gcd = 1
	iteration = 0

	# Parse for waits, find gcd of wait, store wait time of each line (except comments and wait commands)
	with open(infile, "r") as source:
		for num, line in enumerate(source):
			if line.startswith("#"):
				continue
			if line.startswith("wait"):
				wait_time_str = line[5:]
				try:
					wait_time = int(wait_time_str)
				except ValueError:
					print("Syntax Error near 'wait'")
					sys.exit(1)
				wait_gcd = gcd(wait_gcd, wait_time)
				total_wait += wait_time
				continue
			waits[num] = total_wait

	if total_wait == 0:
		return

	# Remove wait statements and assign iterations to lines
	with open(infile, "r") as source:
		with open(outfile, "w") as output:
			for num, line in enumerate(source):
				if line.startswith("wait"):
					print(line)
					continue
				elif line.startswith("#") or not line.strip():
					output.write(line)
				elif num in waits:
					iteration = int(waits[num] / wait_gcd)
					newline = "execute if score $PLAYERNAME iter matches " + str(iteration) + " run " + line
					print(waits[num])
					output.write(newline)

	# Add looping
	test_name = infile
	with open(outfile, "a") as output:
		output.write("\n\n")
		output.write("# Loop control\n")
		output.write("scoreboard players add $PLAYERNAME iter 1\n")
		output.write("execute if score $PLAYERNAME iter matches " + str(
			iteration + 1) + ".. run scoreboard players set $PLAYERNAME loop 0\n")
		output.write("execute if score $PLAYERNAME iter matches .." + str(
			iteration) + " run scoreboard players set $PLAYERNAME loop 1\n")
		output.write(
			"execute if score $PLAYERNAME loop matches 1 run schedule function endshards_tests:" + test_name + " " + str(
				wait_gcd) + "\n")
		output.write("execute if score $PLAYERNAME iter matches " + str(
			iteration + 1) + " run scoreboard players set $PLAYERNAME iter 0\n")
		output.write("\n# This test runs for " + str(total_wait + wait_gcd) + " ticks\n")


def process_playername(infile, outfile, playername):
	with open(infile, "r") as source:
		with open(outfile, "w") as output:
			for line in source:
				output.write(line.replace("$PLAYERNAME", playername))


def main(argv):
	source = ""
	playername = ""
	try:
		opts, args = getopt.getopt(argv, "h:s:p:", ["source=", "playername="])
	except getopt.GetoptError:
		print("test.py -f <mctest source> -p <playername>")
		sys.exit(1)
	for opt, arg in opts:
		if opt == "-h":
			print("test.py -s <mctest source> -p <playername>")
			sys.exit(0)
		elif opt in ("-s", "--source"):
			source = arg
		elif opt in ("-p", "--playername"):
			playername = arg

	output = source.replace(".mctest", ".mcfunction")
	interpret_wait(source, output)
	process_playername(output, output, playername)


if __name__ == "__main__":
	main(sys.argv[1:])
