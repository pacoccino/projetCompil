declare void @print(i32)
define i32 @main() {
%a = alloca i32
store i32 2,i32* %a
%b = alloca i32
store i32 4,i32* %b
%t0 = load i32* %a
%t1 = load i32* %b
%t2 = icmp eq i32 %t0, %t1
br i1 %t2, label %ifT0, label %ifF0
ifT0:
store i32 2,i32* %a
br label %ifE0
ifF0:
store i32 4,i32* %a
br label %ifE0
ifE0:
ret i32 0
}
