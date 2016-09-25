all: compile run

compile:
	mvn compile assembly:single -e
run:
	mvn exec:java -Dexec.mainClass=com.birdseye.Main -e
ngrok:
	ngrok http 8080
clean:
	mvn clean
