#include <pthread.h>
#include <stdio.h>

#ifndef P3180223_P3180103_P3180196_PIZZA_H_
#define P3180223_P3180103_P3180196_PIZZA_H_


#define Ncook 6 // 6 mageires.
#define Noven 5 // 5 fournoi.
#define Torderlow 1 // 1 lepto.
#define Torderhigh 5 // 5 lepta.
#define Norderlow 1 // 1 pizza.
#define Norderhigh 5 // 5 pizzes.
#define Tprep 1 // 1 lepto.
#define Tbake 10 // 10 lepta.

void* order(void* arg);

#endif
