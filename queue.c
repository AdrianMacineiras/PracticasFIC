#include <stdlib.h>

// circular array
typedef struct _queue {
    int size;
    int used;
    int first;
    void **data;
    pthread_mutex_t  count_mutex;
	pthread_cond_t  buffer_full;
	pthread_cond_t buffer_empty;
} _queue;

#include "queue.h"

queue q_create(int size) {
    queue q = malloc(sizeof(_queue));
    
    q->size  = size;
    q->used  = 0;
    q->first = 0;
    q->data = malloc(size*sizeof(void *));
    
    return q;
}

int q_elements(queue q) {
    return q->used;
}

int q_insert(queue q, void *elem) {
    //if(q->size==q->used) return 0;
	//else{
	//while(1){
		pthread_mutex_lock(&q->count_mutex); //????
		while(q->used==q->size)
			pthread_cond_wait(&q->buffer_full,&q->count_mutex); //???

		//if(q->used<q->size){
			q->data[(q->first+q->used) % q->size] = elem;    
			q->used++;
		
	//	}
		if(q->used == 1) 
			pthread_cond_broadcast(&q->buffer_empty);
		
		pthread_mutex_unlock(&q->count_mutex); //?????			
	printf("INSERTADO\n");	
	//}
    return 1;
}

void *q_remove(queue q) {
    void *res;
    

	//while(1){
		pthread_mutex_lock(&q->count_mutex); //????
		while(q->used == 0)
			pthread_cond_wait(&q->buffer_empty, &q->count_mutex);
					
		//if(q->used > 0){
			res=q->data[q->first];
			q->first=(q->first+1) % q->size;
			q->used--;	
		//}
		if(q->used == q->size-1)
			pthread_cond_broadcast(&q->buffer_full);
		pthread_mutex_unlock(&q->count_mutex); //?????	
   //}
   
    return res; //devuelve un buffer para trabajar
}

void q_destroy(queue q) {
    free(q->data);
    free(q);
}
