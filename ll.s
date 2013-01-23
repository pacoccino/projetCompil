	.file	"ll.ll"
	.text
	.globl	main
	.align	16, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# BB#0:
	movl	$3, -4(%rsp)
	movl	$3, -8(%rsp)
	cmpl	$3, -4(%rsp)
	jne	.LBB0_2
# BB#1:                                 # %ifT0
	xorl	%eax, %eax
	ret
.LBB0_2:                                # %ifF0
	movl	$1, %eax
	ret
.Ltmp0:
	.size	main, .Ltmp0-main
	.cfi_endproc


	.section	".note.GNU-stack","",@progbits
