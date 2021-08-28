# ΠΧ3. Διαχείριση Έργου (Manage Project)

**Πρωτεύων Actor** : Project Owner

**Ενδιαφερόμενοι**

**Project Owner** : ο Project Owner θα αναθέτει σε Component Owners components του project για να υλοποιήσουν.

**Component Owner** : ο Component Owner θα πρέπει να υλοποιεί το component που του ανατέθηκε από τον Project Owner.

**Προυποθέσεις** : ο Project Owner και οι Component Owners να έχουν ταυτοποιηθεί από το σύστημα και να έχουν σωστό role.
O Project Owner να έχει πραγματοσποιήσει Login

## Βασική Ροή

### A) Δημιουργία Project
1. Ο Project Owner εισάγει το όνομα του Project και το χωρίζει σε components.
2. Το σύστημα ενημερώνεται ότι δημιουργήθηκε νέο Project.

### Β) Ανάθεση Component
1. ο Project Owner αναθέτει σε κάθε Component Owner components προς υλοποίηση.
2. Το σύστημα ενημερώνεται για την ανάθεση του Component Owner στο αντίστοιχο component.
3. ο Component Owner ενημερώνεται από το σύστημα ότι ανατέθηκε σε ένα component.
   
**Εναλλακτικές ροές**

*1α) Λανθασμένη ανάθεση Component Owner*

1. Ενημερώνεται ο Component Owner από το σύστημα ότι θα αλλάξει θέση και θα δουλέψει σε άλλο component.
2. η ΠΧ επιστρέφει στο βήμα 1.


### Γ) Ολοκλήρωση Component
1. ο Project Manager παίρνει τον Component Owner που δούλευε σε αυτό το component και τον τοποθετεί σε άλλο component.
2. Οι Component Owners ενημερώνονται από το σύστημα ότι μεταφέρονται σε άλλο component για να δουλέψουν.
3. Το σύστημα ενημερώνεται για την αλλαγή θέσης του Component Owner.
