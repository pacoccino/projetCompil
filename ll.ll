declare void @print(i32)
define i32 @main() {
%a = alloca i32
store i32 2,i32* %a
%b = alloca i32
store i32 3,i32* %b
%t0 = load i32* %a
%t1 = load i32* %b
%t2= icmp eq i32 %t0, %t1
store i32 4,i32* %a
br i1 %t0, label %ifT0, label %ifF0
goto %ifF0
ifT0:
ifF0:
ret i32 0
}
