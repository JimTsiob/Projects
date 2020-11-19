# Dimitrios Tsiompikas 3180223 , Olga Margeli 3180103
.data
Num: .word 10 
keys: .word 0
hash: .space 40 # o pinakas.
message1: .asciiz " Menu\n"
message2: .asciiz "1. Insert Key\n"
message3: .asciiz "2. Find Key\n"
message4: .asciiz "3. Display Hash Table\n"
message5: .asciiz "4. Exit\n" # to menu mas.
choice: .asciiz "Choice?: "
key: .asciiz "Give new key(greater than zero): "
searching: .asciiz "Give new key to search for: "
choice1Failure: .asciiz "Key must be greater than zero.\n"
poskey: .asciiz "\npos key\n"
space: .asciiz " " # <- keno gia to displaytable.
newline: .asciiz "\n"
nokey: .asciiz "Key not in Hash Table."
value: .asciiz "Key value = " 
tablePos: .asciiz "Table position = "
already: .asciiz "Key is already in hash table. \n"
fulltable: .asciiz "hash table is full. \n"
.text
.globl main
main:
	add $t0,$t0,$zero # i = 0;
	add $t2,$t2,$zero # deikths pinaka = 0;
	add $s0,$s0,$zero # gia na topothetoume se kathe keli to 0.
	j forLoop
forLoop: # arxikopoihsh tou hash.
	bge $t0,10,menuMode # if (t0 > 10) go to menuMode.
	sw $s0,hash($t2) # hash[i] = 0;
	addi $t0,$t0,1 # i++
	addi $t2,$t2,4 # deikths pinaka + 4.
	j forLoop

	add $t6,$t6,$zero # key = 0;.
	add $t8,$t8,$zero # telos = 0;
	add $t9,$t9,$zero # pos = 0;
	add $t1,$t1,$zero # choice = 0;
	
menuMode:	# to do while loop gia to menu.
	li $v0,4
	la $a0,message1 # Menu
	syscall
	
	li $v0,4
	la $a0,message2 # 1. Insert Key
	syscall 
	
	li $v0,4
	la $a0,message3 # 2. Find Key
	syscall
	
	li $v0,4
	la $a0,message4 # 3. Display Hash Table
	syscall
	
	li $v0,4
	la $a0,message5 # 4. Exit
	syscall
	
	li $v0,4
	la $a0,choice # Choice?: 
	syscall
	
	li $v0,5
	syscall
	move $t1,$v0 # t1 = input tou xrhsth , choice.
	
	beq $t1,1,choice1 # if (choice == 1) go to choice1.
	beq $t1,2,choice2 # if (choice == 2) go to choice2.
	beq $t1,3,choice3 # if (choice == 3) go to choice3.
	beq $t1,4,exit    # if (choice == 4) go to Exit.
	
bne $t8,0,end # if telos == 1 go to end.
	
choice1:
	li $v0,4
	la $a0,key # Give new key(greater than zero): 
	syscall
	
	li $v0,5
	syscall
	move $t3,$v0 # move to input ston t3.
	
	
	ble $t3,0,choice1Fail # if t3 <= 0 go to choice1Fail.
	la $t2,hash
	move $a1,$t2 # hash
	move $a2,$t3 # key
	sub $sp,$sp,4
	sw $a1,0($sp)
	sub $sp,$sp,4
	sw $a2,0($sp)
	sub $sp,$sp,4
	sw $ra,0($sp) # proetoimasia.
	jal insertkey # kanoume update ton pinaka kathe fora pou kanoume insert key me thn $sp.
	lw $ra,0($sp) # ta xanapairnoume pisw updated.
	addi $sp,$sp,4
	lw $a2,0($sp)
	addi $sp,$sp,4
	lw $a1,0($sp)
	addi $sp,$sp,4
	
	j menuMode

	

choice1Fail:
	li $v0,4
	la $a0,choice1Failure # Key must be greater than zero.
	syscall 
	
	j menuMode
	





choice2:
	li $v0,4
	la $a0,searching # give new key to search for :
	syscall
	
	li $v0,5
	syscall # metaferoume to kleidi ston t3.
	move $t3,$v0 
	
	la $t2,hash # pairnoume th dieythinsh.
	move $a1,$t2 # move ton deikth toy pinaka ston a1.
	move $a2,$t3 # move to key ston a2.
	sub $sp,$sp,4
	sw $a1,0($sp)
	sub $sp,$sp,4
	sw $a2,0($sp)
	sub $sp,$sp,4
	sw $ra,0($sp) # proetoimasia.
	jal findkey
	lw $ra,0($sp) # ta xanapairnoume pisw updated.
	addi $sp,$sp,4
	lw $a2,0($sp)
	addi $sp,$sp,4
	lw $a1,0($sp)
	addi $sp,$sp,4
	
	move $t7,$v1 # move to apotelesma ston t7 (pos).
	bne $t7,-1,gotem # if pos != -1 go to gotem.
	
	li $v0,4
	la $a0,nokey # Key not in Hash Table.
	syscall
	
	li $v0,4
	la $a0,newline # \n
	syscall
	
	j menuMode
gotem:
	lb $t4,hash($t7) # t4 = hash[pos];
	
	li $v0,4
	la $a0,value # Key value = 
	syscall
	
	li $v0,1
	move $a0,$t4 # print hash[pos].
	syscall
	
	li $v0,4
	la $a0,newline # \n
	syscall
	
	li $v0,4
	la $a0,tablePos # Table position = 
	syscall 	

	li $v0,1
	move $a0,$t7 # print pos.
	syscall
	
	li $v0,4
	la $a0,newline # \n
	syscall
	
	j menuMode
	
choice3:
	move $a1,$t2 # fortwnoume thn dieythinsh se kataxwrhth arguement (a1).
	jal displaytable
	j menuMode
	
	

insertkey:
	li $t9,0 # position = 0;
	add $t5,$t5,0 # keys = 0;
	move $t2,$a1
	move $t3,$a2
	move $a1,$t2
	move $a2,$t3
	sub $sp,$sp,4
	sw $t2,0($sp)
	sub $sp,$sp,4
	sw $t3,0($sp)
	sub $sp,$sp,4
	sw $ra,0($sp)
	jal findkey # update kathe fora opws kai panw.
	lw $ra,0($sp)
	addi $sp,$sp,4
	lw $t3,0($sp)
	addi $sp,$sp,4
	lw $t2,0($sp)
	addi $sp,$sp,4
	move $t9,$v1 # move to apotelesma ston t9 (position = findkey(hash,k)).
	beq $t9,-1,inselse # if position == -1 go to inselse.
	li $v0,4
	la $a0,already # Key is already in hash table.
	syscall
	j done3
inselse:
	li $s0,0 # arxikopoioume ton s0.
	add $s0,$s0,$a2 # s0 = k.	
	move $t2,$a1
	move $t3,$a2
	move $a1,$t2 # ta xanavazoume se argument registers gia na ginei swsta h klhsh.
	move $a2,$t3
	sub $sp,$sp,4
	sw $a1,0($sp)
	sub $sp,$sp,4
	sw $a2,0($sp)
	sub $sp,$sp,4
	sw $ra,0($sp)
	jal hashfunction # epishs update.
	lw $ra,0($sp)
	addi $sp,$sp,4
	lw $a2,0($sp)
	addi $sp,$sp,4
	lw $a1,0($sp)
	addi $sp,$sp,4	
	bge $t5,10,full # if keys >= N go to full.
	move $t9,$v1 # move to apotelesma ths hashfunction ston t9.
	sb $s0,hash($t9) # hash[position] = k;
	addi $t5,$t5,1 # keys++;
	j done3 
full:
	li $v0,4
	la $a0,fulltable # hash table is full.
	syscall 
	j done3
done3:
	jr $ra



hashfunction:
	li $t9,0 # position = 0;
	rem $t9,$a2,10 # position = k % N;
	lb $t3,hash($t9) # t3 = hash[position];
	beq $t3,0,hashEnd
	addi $t9,$t9,1 # position++;
	rem $t9,$t9,10 # position %= N;
hashEnd:
	move $v1,$t9 # move to apotelesma ston v1.
	jr $ra






findkey:
	li $t9,0 # position = 0;
	rem $t9,$a2,10 # position = k % N;
	li $t0,0 # i = 0;
	li $t1,0 # found = 0;
	loop3:
		bge $t0,10,endKey # if i >= 10 go to endKey.
		bne $t1,0,endKey # if found != 0 go to endKey.
		addi $t0,$t0,1 # i++;
		lb $t3,hash($t9) # t3 = hash[position];
		bne $t3,$a2,ELSE # if hash[position] != k go to ELSE.
		li $t1,1 # found == 1;
		j loop3
	ELSE:
		addi $t9,$t9,1 # position++;
		rem $t9,$t9,10 # position %= N;
		j loop3
	endKey:
		bne $t1,1,failSearch # if found != 1 go to failSearch.
		move $v1,$t9
		j done2
	failSearch: # epestrepse -1.
		li $v1,-1
		j done2
	done2:
		jr $ra

displaytable:
		li $t4,0 # to i.	
		li $v0,4
		la $a0,poskey # pos key
		syscall

	loop2:
		bge $t4,10,done # if i >= 10 go to done.
		
		
		li $v0,4
		la $a0,space
		syscall
		
		li $v0,1
		move $a0,$t4 # print to i.
		syscall
		
		
		li $v0,4
		la $a0,space
		syscall
		
		lb $t7,hash($t4)
		
		li $v0,1
		move $a0,$t7 # print to hash[i].
		syscall
		
		li $v0,4
		la $a0,newline
		syscall
		
		
		addi $t4,$t4,1 # i++
		j loop2
		
	done:
		jr $ra
	
	
exit:
	li $t8,1 # telos == 1 
end:
	li $v0,10
	syscall


	
	
	
	
	
	