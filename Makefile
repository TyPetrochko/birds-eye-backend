all: compile run

compile:
	mvn compile -e
run:
	mvn exec:java -Dexec.mainClass=com.birdseye.Main -e
clean:
	mvn clean
