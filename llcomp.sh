gcc -c src/print.c -o build/print.o
llc ll.ll
gcc ll.s build/print.o -o prog

