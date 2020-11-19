#Dimitrios Tsiompikas 3180223 , Olga Margelh 3180103
.data
	message1: .asciiz "Enter number of objects in the set(n): "
	message2: .asciiz "\nEnter number to be chosen(k): "
	result: .asciiz "\nC("
	comma: .asciiz ", "
	result2: .asciiz ") = "	
	false: .asciiz "\nPlease enter n >= k >= 0"
.text
main:  #ousiastika to run
	#prwtos arithmos (n)
	li $v0,4
	la $a0,message1
	syscall
	
	#input tou n
	li $v0,5
	syscall
	
	
	#move ton n se register (a gia parametro)
	move $a1,$v0
	
	#deyteros arithmos (k)
	li $v0,4
	la $a0,message2
	syscall
	
	#input tou k
	li $v0,5
	syscall
	
	#move ton k se register (a gia parametro)
	move $a2,$v0
	
	 
	addi $t2,$zero,1 # to i
	addi $t3,$zero,1 # to factorial_n
	addi $t4,$zero,1 # to factorial_k
	addi $t5,$zero,1 # to factorial_n_k
	sub $t6,$a1,$a2  # to n-k
	
	jal combinations # edw kaloume thn synarthsh combinations
	move $t8,$v1 # metaferoume to apotelesma ston register t8
	
	# edw kanoume if (n>=k && k >=0)
	blt $a1,$a2,ELSE # if n<k phgaine sto ELSE
	blt $a2,0,ELSE   # if k<0 phgaine sto ELSE
	
	#display
	li $v0,4
	la $a0,result
	syscall
	
	#print to C(n
	li $v0,1
	move $a0,$a1
	syscall
	
	
	#print to , k
	li $v0,4
	la $a0,comma
	syscall
	
	li $v0,1
	move $a0,$a2
	syscall
	
	#print to ) = 
	li $v0,4
	la $a0,result2
	syscall
	
	#print to apotelesma
	li $v0,1
	move $a0,$t8
	syscall
	
	j endpr #ayto mpainei gia na apofygoume
			#thn ELSE an einai ola swsta.
	
	
ELSE: 
	#proetoimasia gia display string sfalmatos
	li $v0,4
	la $a0,false
	syscall
	
endpr:	
	li $v0,10
	syscall
	
combinations: #h synarthsh combinations
loop1:
	bgt $t2,$a1,reset1 # for(int i=1; i<=n; i++)
 	mul $t3,$t3,$t2  # factorial_n *= i
	addi $t2,$t2,1   # i++
	j loop1
	
reset1: addi $t2,$zero,1 # edw kanw reset to deikth 
		beq $t2,1,loop2	 # gia na ginei pali 1 kai pame 
						 # sto epomeno loop

loop2:
	bgt $t2,$a2,reset2 # for(int i=1; i<=k; i++)
	mul $t4,$t4,$t2  # factorial_k *= i
	addi $t2,$t2,1   # i++
	j loop2
	
reset2: addi $t2,$zero,1 # omoia me thn reset1 gia
		beq $t2,1,loop3	 # na pame sto loop3
loop3:
	bgt $t2,$t6,Exit # for(int i=1; i<=n-k; i++)
	mul $t5,$t5,$t2  # factorial_n_k *= i
	addi $t2,$t2,1   # i++
	j loop3
Exit:
	mul $t7,$t4,$t5 # $t7 = factorial_k*factorial_n_k
	div $v1,$t3,$t7 # $v1 = factorial_n / (factorial_k*factorial_n_k)
	j done
done: jr $ra
	
	
	
	