words = ['καλικατζαρος','κορακι','νεραιδα','διαστημοπλοιο','υποβρυχιο','ταξι',\
         'πλοιαρχος','καραβι','αυτοκινητο','τροχοφορο','Σουηδια','Ιαπωνια',\
         'Ελλαδα','Ωμ','Επιστημη','Υπολογιστων','νονος','τραινο','κρεμαλα',\
         'φιγουρα','παιχτης','ριγανη','φωτια','χιονι','νερο','γη','αερας',\
         'πρωταθλητης','Οικονομικο','Πανεπιστημιο','Αθηνων','ραγες','κορυφη',\
         'Μαθηματικα','ισοζυγιο','ελλειμμα','αστυνομια','στρατος','Φυσικη',\
         'κυκλωμα','αεροπλανο','Καλοκαιρι','Χειμωνας','Ανοιξη','Φθινοπωρο',\
         'Ποσειδωνας','Προμηθεας','Αριστοτελης','ενεργεια']

hangman=[' ',\
         '|---------|\n|         O\n|\n|\n|\n|\n|\n|',\
         '|----------|\n|          O\n|         /|\ \n|\n|\n|\n|\n|',\
         '|----------|\n|          O\n|         /|\ \n|          |\n|\n|\n|',\
         '|----------|\n|          O\n|         /|\ \n|          |\n|        _/\_\n|\n|\n|',\
         '|----------|\n|          O\n|         /|\ \n|          |\n|        _/\_\n|       ## ##\n|',\
         '|----------|\n|          O\n|         /|\ \n|          |\n|        _/\_\n|       ## ##\n|        fire']
alphabet = 'ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩαβγδεζηθικλμνξοπρστυφχψως'
import random 
index=0
previous='-'

def ask_for_players():
    global namelist
    count = int(input('Δώστε αριθμό παιχτών: '))
    namelist = []
    while len(namelist)<count:
        name = input('Δώσε όνομα: ')
        namelist.append(name)

def beginning():
    global player
    player=namelist[index-len(namelist)]
    print (player,'έχεις 5 ευκαιρίες για λάθος γράμμα.Στο 6ο βγαίνεις εκτός.')

def random_word(words):
    global randword
    global blanks
    global printword
    word=random.choice(words)
    while word==previous:
        word=random.choice(words)
    printword=len(word)*' __ '
    randword=word.lower()
    blanks=[' __ ']*len(randword)
    print('Η λέξη που πρέπει να μαντέψεις είναι',printword)

guess=''
def guessing():
    global letters
    letters=[]
    global wrong_guesses
    wrong_guesses=1
    while wrong_guesses<6:
        guess=input('Δώσε γράμμα: ')
        while guess not in alphabet:
            guess=input('Παρακαλώ δώσε γράμμα: ')
        while guess in letters:
            print('Έχεις ήδη μαντέψει αυτό το γράμμα')
            guess=input('Δώσε νέο γράμμα: ')
        letters.append(guess)
        if guess in randword:
            for i,x in enumerate(randword):
                if x==guess:
                    blanks[i]=guess
            print(' '.join(blanks))
        elif guess not in randword:
            wrong_guesses+=1
            print(hangman[::-1][(len(hangman))-wrong_guesses])
            print('Έχεις ακόμα',6-wrong_guesses,'ζωές.')
            print('η λέξη που πρέπει να μαντέψεις είναι: ',' '.join(blanks))
        if 6-wrong_guesses==0:
            guess=input('Δώσε νέο γράμμα: ')
        if ' __ ' not in blanks:
            break

  
        
 

def ending():
    global index
    global guess
    if wrong_guesses==6:
        print(hangman[len(hangman)-1])
        print('Έχασες και βγαίνεις εκτός παιχνιδιού')
        print('Η λέξη που ψάχναμε ήταν: ',randword)
        namelist.remove(player)
    else:
        print('Μπράβο',player,'βρήκες τη λέξη η οποία ήταν',randword)
        index=index+1
    

def next_player():
    global index
    beginning()
    random_word(words)
    guessing()
    ending()

def next_round():
    if len(namelist)==0:
        print('Κανείς δεν νίκησε.')
    elif len(namelist)==1:
        print('Μπράβο',namelist[0],'κερδισες το παιχνίδι!')
    else:
        for i in namelist:
            next_player()
        if len(namelist)==1:
            print('Μπράβο',namelist[0],'κερδισες το παιχνίδι!')
        elif len(namelist)==0:
            print('Κανείς δεν νίκησε')
        else:
            next_player()
                    
            
            
                    


ask_for_players()
beginning()
random_word(words)
guessing()
ending()
next_player()
next_round()
