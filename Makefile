CXX=gcc
CXXFLAGS=-Wall -g 


compiler: java printer

asm: java printer
	llc ll.ll
	gcc ll.s print.o -o prog

interpreter: java
	java -cp build:antlr-lib.jar Run 
	
example: java printer ll.ll ll.s 
	gcc ll.s print.o -o prog
	
java:
	mkdir -p build
	javac src/Run.java -sourcepath src -cp antlr-lib.jar -d build

ll.ll: java
	java -cp build:antlr-lib.jar Run < aw

printer: 
	gcc -c src/print.c
	
ll.s: ll.ll
	llc ll.ll



clean:
	rm build/* prog print.o ll.ll ll.s
