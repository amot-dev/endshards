import sys
import os
import getopt
import shutil
from math import gcd

def print_error(message, temp_file):
	print(message)
	os.remove(temp_file)
	sys.exit(1)


def process_tests(outfile):
	test_name = ""
	case_name = ""

	infile = outfile + ".temp"
	shutil.copyfile(outfile, infile)
	with open(infile, "r") as source:
		with open(outfile, "w") as output:
			for line in source:
				# Print test start message
				if line.startswith("test "):
					if test_name != "":
						print_error("Error: Multiple tests in one file", infile)
					test_name = line.replace("test ", "").strip("\n")
					output.write('tellraw @a ["",{"text":"[Test]","color":"aqua"},{"text":" Started ","color":"light_purple"},{"text":"'
						+ test_name + '","color":"white"}]\n')
				# Print case start message
				elif line.startswith("case "):
					if test_name == "":
						print_error("Error: Case outside of test", infile)
					elif case_name != "":
						print_error("Error: Pass not set by case", infile)
					case_name = line.replace("case ", "").strip("\n")
					output.write('tellraw @a ["",{"text":"[Test]","color":"aqua"},{"text":" ' + case_name + ' Starting","color":"yellow"}]\n')
				# Print case pass/fail messages after pass is set
				elif "pass 1" in line:
					if case_name == "":
						print_error("Error: Pass set outside of case", infile)
					output.write(line.strip("\n") + "\n")
					output.write('execute if score $PLAYERNAME pass matches 0 run tellraw @a ["",{"text":"[Test]","color":"aqua"},{"text":" '
						+ case_name + ' Failed","color":"red"}]\n')
					output.write('execute if score $PLAYERNAME pass matches 1 run tellraw @a ["",{"text":"[Test]","color":"aqua"},{"text":" '
						+ case_name + ' Passed","color":"green"}]\n')
					case_name = ""
				else:
					output.write(line)

	# Print test completed message
	if test_name != "":
		with open(outfile, "a") as output:
			output.write("\n")
			output.write('tellraw @a ["",{"text":"[Test]","color":"aqua"},{"text":" Finished ","color":"light_purple"},{"text":"'
				+ test_name + '","color":"white"}]\n')
	os.remove(infile)


def interpret_wait(outfile):
	waits = dict()
	wait_time = 0
	total_wait = 0
	wait_gcd = 0
	iteration = 0

	infile = outfile + ".temp"
	shutil.copyfile(outfile, infile)

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
					print_error("Error: invalid wait", infile)
				if wait_gcd == 0:
					wait_gcd = wait_time
				else:
					wait_gcd = gcd(wait_gcd, wait_time)
				total_wait += wait_time
				continue
			waits[num] = total_wait

	if total_wait == 0:
		os.remove(infile)
		return

	# Remove wait statements and assign iterations to lines
	with open(infile, "r") as source:
		with open(outfile, "w") as output:
			for num, line in enumerate(source):
				if line.startswith("wait"):
					continue
				elif line.startswith("#") or not line.strip():
					output.write(line)
				elif num in waits:
					iteration = int(waits[num] / wait_gcd)
					newline = "execute if score $PLAYERNAME iter matches " + str(iteration) + " run " + line
					output.write(newline)

	# Add looping
	test_name = outfile.partition("functions/")[2].replace(".mcfunction", "")
	with open(outfile, "a") as output:
		output.write("\n")
		output.write("# Loop control\n")
		output.write("scoreboard players add $PLAYERNAME iter 1\n")
		output.write("execute if score $PLAYERNAME iter matches "
			+ str(iteration + 1) + ".. run scoreboard players set $PLAYERNAME loop 0\n")
		output.write("execute if score $PLAYERNAME iter matches .."
			+ str(iteration) + " run scoreboard players set $PLAYERNAME loop 1\n")
		output.write("execute if score $PLAYERNAME loop matches 1 run schedule function endshards_tests:"
			+ test_name + " " + str(wait_gcd) + "\n")
		output.write("execute if score $PLAYERNAME iter matches "
			+ str(iteration + 1) + " run function endshards_tests:cleanup\n")
		output.write("\n# This test runs for " + str(total_wait + wait_gcd) + " ticks")

	os.remove(infile)


def process_playername(outfile, playername):
	infile = outfile + ".temp"
	shutil.copyfile(outfile, infile)
	with open(infile, "r") as source:
		with open(outfile, "w") as output:
			for line in source:
				output.write(line.replace("$PLAYERNAME", playername))
	os.remove(infile)


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

	# Convert to mcfunction
	output = source.replace(".mctest", ".mcfunction")
	shutil.copyfile(source, output)

	# Process mctest
	process_tests(output)
	interpret_wait(output)
	process_playername(output, playername)

if __name__ == "__main__":
	main(sys.argv[1:])
