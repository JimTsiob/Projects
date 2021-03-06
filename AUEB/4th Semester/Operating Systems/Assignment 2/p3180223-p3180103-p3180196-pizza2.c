#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include "p3180223-p3180103-p3180196-pizza2.h"

pthread_mutex_t lock; // gia lock tou screen.
pthread_cond_t cond;
int cookC; // akeraia metavlhth gia to mutex mageirwn.
int ovenC; // akeraia metavlhth gia to mutex fournwn.
int delC; // akeraia metavlhth gia to mutex dianomewn.
pthread_mutex_t cookLock; // mutex mageirwn.
pthread_mutex_t ovenLock; // mutex fournwn.
pthread_mutex_t delLock; // mutex dianomewn.
int cooks = Ncook; // plithos mageirwn.
int ovens = Noven; // plithos fournwn.
int delivery = Ndeliverer; // plithos dianomewn.
int Ncust = 0; // global variable gia arithmo pelatwn.
int pizzaCount[1]; // global variable (pinakas) gia arithmo pizzwn pou paraggelnei o kathe pelaths.Aytes oi 2 einai global giati prepei na xrhsimopoihthoun sthn synarthsh.
double max = 0; // megistos xronos anamonhs.
double avg = 0; // mesos xronos anamonhs.
double sum = 0; // athroisma gia na vroume ton meso xrono anamonhs.
unsigned int *p; // unsigned int pointer , tha xrhsimopoihthei gia to seed ths rand_r, edw egine global gia na xrhsimopoihthei kai gia thn delTime. 
int max2 = 0; // megistos xronos krywmatos.
double avg2 = 0; // mesos xronos krywmatos.
double sum2 = 0; // athroisma gia an vroume ton meso xrono krywmatos.

void* order(void* arg){
	int id = *(int*)arg;
	int rc;
	struct timespec start,end;
	// tuxaios arithmos gia ton xrono twn dianomewn sto diasthma [Tlow,Thigh].	
	int delTime = (rand_r(p) % (Thigh-Tlow+1))+Tlow;
	printf("order %d has been received!\n",id);
	cookC = pthread_mutex_lock(&cookLock);
	// metrame poso xrono pairnei h exyphrethsh.
	clock_gettime(CLOCK_REALTIME,&start);
	while (cooks == 0){
		printf("H paraggelia %d den vrhke paraskeyasth.Blocked..\n",id);
		cookC = pthread_cond_wait(&cond,&cookLock);
	} 
	printf("H paraggelia %d eksyphreteitai.\n",id);
	cooks--;
	cookC = pthread_mutex_unlock(&cookLock);
	// etoimazei tis pizzes kai koimatai mexri na vrethei eleytheros fournos.
	printf("This order wants %d pizzas! 1 minute for each.\n",pizzaCount[id-1]);
	// pollaplasiazoume Tprep epi ton arithmo twn pizzwn pou thelei h paraggelia wste na kanei 1 lepto gia kathe pizza.
	sleep(Tprep*pizzaCount[id-1]);  
	ovenC = pthread_mutex_lock(&ovenLock);
	while (ovens == 0){
		printf("H paraggelia %d den vrhke diathesimo fourno.Blocked..\n",id);
		ovenC = pthread_cond_wait(&cond,&ovenLock);
	}
	printf("Oi pizzes ths paraggelias %d pshnontai gia 10 lepta.\n",id);
	ovens--; 
	ovenC = pthread_mutex_unlock(&ovenLock);
	cooks++; // to cooks++ ginetai edw dioti twra oi paraskeyastes den xreiazetai na perimenoun na psithei h pizza.
	//psinei thn pizza gia 10 lepta.
	sleep(Tbake);
	clock_gettime(CLOCK_REALTIME,&end);
	delC = pthread_mutex_lock(&delLock);
	while (delivery == 0){
		printf("h paraggelia %d den vrhke dianomea.Blocked..\n",id);
		delC = pthread_cond_wait(&cond,&delLock);
	}
	printf("h paraggelia %d metaferetai gia %d lepta\n",id,delTime);
	delivery--;
	delC = pthread_mutex_unlock(&delLock);
	ovens++; // o fournos twra apeleytherwnetai edw kathws o dianomeas bgazei thn pizza apo ton fourno kai thn phgainei ston pelath.
	sleep(delTime);
	printf("O dianomeas ths paraggelias %d epistrefei.\n",id);
	sleep(delTime);
	delivery++;
	rc = pthread_mutex_lock(&lock);
	double calcTime = (end.tv_sec-start.tv_sec);
	calcTime = calcTime + delTime; // edw ginetai h prosthesh.
	if (calcTime > max){
		max = calcTime; // edw vriskoume to megisto xrono anamonhs.
	}
	if (delTime > max2){
		max2 = delTime; // edw vriskoume to megisto xrono krywmatos.
	}
	sum = sum + calcTime; // gia ton meso xrono anamonhs.
	sum2 = sum2 + delTime; // gia to meso xrono krywmatos.
	printf("H paraggelia %d paradothike epityxws se %.2f lepta kai krywne gia %d lepta!\n",id,calcTime,delTime);
	rc = pthread_cond_signal(&cond);
	rc = pthread_mutex_unlock(&lock);
	pthread_exit(NULL);
}

int main(int argc, char ** argv){
	if (argc < 3){
		printf("the program needs 2 arguments to run.\n");
		return 1;	
	}
	Ncust = atoi(argv[1]); // arithmos pelatwn.
	unsigned int seed = atoi(argv[2]);
        p = &seed;
	srand(time(NULL));
	pthread_t threads[Ncust];
	int rc;
	pthread_mutex_init(&lock,NULL);
	pthread_mutex_init(&cookLock,NULL);
	pthread_mutex_init(&ovenLock,NULL);
	pthread_mutex_init(&delLock,NULL);
	pthread_cond_init(&cond,NULL);
	int id[Ncust];
	pizzaCount[Ncust]; // to plithos twn pizzwn pou thelei o kathe pelaths.
	for (int i = 0; i<Ncust; i++){
	// tyxaios arithmos pizzwn gia kathe pelath sto diasthma [Norderlow,Norderhigh]
		pizzaCount[i] = (rand_r(p) % (Norderhigh-Norderlow+1))+Norderlow;
		id[i] = i+1;
		printf("Creating thread %d\n",i+1);
		printf("thread %d wants %d pizzas!\n",i+1,pizzaCount[i]);
		rc = pthread_create(&threads[i],NULL,order,&id[i]);
		// tyxaios arithmos (ousiastika deyterolepta) sto diasthma 			[Torderlow,Torderhigh].
		int orderTime = (rand_r(p) % (Torderhigh-Torderlow+1)) + Torderlow;
		// sleep mexri thn epomenh paraggelia.H 1h paraggelia ginetai 	apeytheias (xronikh stigmh 0).
		sleep(orderTime);
	}
	for (int i = 0; i< Ncust; i++){
		pthread_join(threads[i],NULL);
	}
	pthread_mutex_destroy(&lock);
	pthread_mutex_destroy(&cookLock);
	pthread_mutex_destroy(&ovenLock);
	pthread_mutex_destroy(&delLock);
	pthread_cond_destroy(&cond);
	printf("megistos xronos anamonhs : %.2f lepta\n",max);
	avg = sum/Ncust;
	printf("mesos xronos anamonhs : %.2f lepta\n",avg);
	printf("megistos xronos krywmatos : %d lepta\n",max2);
	avg2 = sum2/Ncust;
	printf("mesos xronos krywmatos : %.2f lepta\n",avg2);
	return 0;
}




