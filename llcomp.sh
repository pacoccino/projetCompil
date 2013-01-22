gcc -c print.c
llc ll.ll
gcc ll.s print.o -o prog

