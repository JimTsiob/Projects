# ΠΧ6. Επισκόπηση Project (View Project)

**Πρωτεύων Actor** : Developer

**Ενδιαφερόμενοι** 

**Project Owner** : ο Project Owner θα μπορεί να δει τη λίστα των developers που δουλεύουν στο πρότζεκτ, τις λεπτομέρειες του πρότζεκτ και τα σφάλματά του.

**Component Owner** : ο Component Owner θα μπορεί να δει τη λίστα των developers που δουλεύουν στο πρότζεκτ, τις λεπτομέρειες του πρότζεκτ και τα σφάλματά του.

**Developer** : ο Developer θα μπορεί να δει τη λίστα των developers που δουλεύουν στο πρότζεκτ μαζί του, τις λεπτομέρειες του πρότζεκτ και τα σφάλματά του.

**Προυποθέσεις** : Να έχει ανατεθεί ο Developer σε ένα πρότζεκτ.

## Βασική Ροή

### Α) Αναζήτηση και Προβολή Project
1. O Developer αναζητά τα project σε μια λίστα από projects
2. Ο Developer επιλέγει το project που επιθυμεί να δει λεπτομέρειες.
3. Εμφανίζονται οι λεπτομέρειες του project

**Εναλλακτικές ροές**

*1α) ο Developer έχει Component Owner role*

1. Εμφάνιση των project που έχει ο Component Owner
2. τέλος ΠΧ.

*1β) ο Developer έχει Project Owner role*

1. Εμφάνιση όλων των project του έργου που επιβλέπει ο Project Owner
2. τέλος ΠΧ.

