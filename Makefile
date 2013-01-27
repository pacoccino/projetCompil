CXX=gcc
CXXFLAGS=-Wall -g 

compiler: java printer

example: compiler compex asm
	
interpreter: java
	java -cp build:antlr-lib.jar Run 

asm: printer
	llc ll.ll -o build/ll.s
	gcc build/ll.s build/print.o -o compiled
	
compex: java
	java -cp build:antlr-lib.jar Run < example.rub

	
java: grammar
	mkdir -p build
	javac src/Run.java -sourcepath src -cp antlr-lib.jar -d build -Xlint:none

printer: 
	mkdir -p build
	gcc -c src/print.c -o build/print.o

grammar:
	java -cp antlr-lib.jar org.antlr.Tool src/Expr.g
clean:
	rm build/*
