#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h> 
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <stdio.h>
#include <pthread.h>
#include <errno.h>
#include "compress.h"
#include "chunk_archive.h"
#include "queue.h"
#include "options.h"

#define CHUNK_SIZE (1024*1024)
#define QUEUE_SIZE 100

#define COMPRESS 1
#define DECOMPRESS 0

struct thread_info {
	pthread_t       thread_id;        // id returned by pthread_create()
	int             thread_num;       // application defined thread #
};

struct args {
	queue in;
	queue out;
	chunk (*process)(chunk);		  
};
// take chunks from queue in, run them through process (compress or decompress), send them to queue out
void *worker(void *ptr){                               //(queue in, queue out, chunk (*process)(chunk)) {  //es el thread??
	struct args *args =  ptr;
    chunk ch, res;
    
    while(q_elements(args->in)>0) {
        ch = q_remove(args->in);
        res = args->process(ch);
        res->num = ch->num;
        free_chunk(ch);  
        q_insert(args->out,res);
    }
    return NULL;
}


// Compress file taking chunks of opt.size from the input file,
// inserting them into the in queue, running them using a worker,
// and sending the output from the out queue into the archive file
void comp(struct options opt) {
    int fd, chunks, i;
    struct stat st;
    char comp_file[256];
    archive ar;
    queue in, out;
    chunk ch;
    struct thread_info *threads;
    struct args *args;
    
    if((fd=open(opt.file, O_RDONLY))==-1) {
        printf("Could not open %s\n", opt.file);
        exit(0);
    }
    
    fstat(fd, &st);
    chunks = st.st_size/opt.size+(st.st_size % opt.size ? 1:0);
    printf("ahhh-%i-",chunks);
    strncpy(comp_file, opt.file, 256);
    strncat(comp_file, ".ch", 256);
   
    ar = create_archive_file(comp_file);
	
    in  = q_create(QUEUE_SIZE); //b
    out = q_create(QUEUE_SIZE);  //bz
	
    // read input file and send chunks to the in queue
    for(i=0; i<chunks; i++) {
        ch = alloc_chunk(opt.size); //asigna 10 megas
        ch->size = read(fd, ch->data, opt.size); //lee
        ch->num = i;
        q_insert(in, ch);
        
    }
   
    // compression of chunks from in to out
    
    args = malloc(sizeof(struct args) * opt.num_threads);
    threads = malloc(sizeof(struct thread_info) * opt.num_threads);
    
   
    for (i = 0; i < opt.num_threads; i++) {
		threads[i].thread_num = i;
		args[i].in = in;
		args[i].out = out;
		args[i].process     = compress;
		
		
		if ( 0 != pthread_create(&threads[i].thread_id, NULL,
					 worker, &args[i])) {
			printf("Could not create thread #%d", i);
			exit(1);
		}
	}
    // send chunks to the output archive file
    for(i=0; i<chunks; i++) {
        ch = q_remove(out);
        
        add_chunk(ar, ch, ch->num); //los escribe (write)
        free_chunk(ch);
    }
    for (i = 0; i < opt.num_threads; i++)
		pthread_join(threads[i].thread_id, NULL);
	
	free(args);
	free(threads);
	pthread_exit(NULL);
    close_archive_file(ar);
    close(fd);
    q_destroy(in);
    q_destroy(out);
}

  

// Decompress file taking chunks of opt.size from the input file,
// inserting them into the in queue, running them using a worker,
// and sending the output from the out queue into the decompressed file

void decomp(struct options opt) {
    int fd, i;
    //struct stat st;
    char uncomp_file[256];
    archive ar;
    queue in, out;
    chunk ch;
    struct thread_info *threads;
    struct args *args;
    
    
    if((ar=open_archive_file(opt.file))==NULL) {
        printf("Could not open archive file\n");
        exit(0);
    };
    
    strncpy(uncomp_file, opt.file, strlen(opt.file) -3);
    uncomp_file[strlen(opt.file)-3] = '\0';

    if((fd=open(uncomp_file, O_RDWR | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP | S_IROTH | S_IWOTH))== -1) {
        printf("Could not create %s: %s\n", uncomp_file, strerror(errno));
        exit(0);
    } 

    in  =q_create(QUEUE_SIZE);
    out =q_create(QUEUE_SIZE);
	
    // read chunks with compressed data
    for(i=0; i<chunks(ar); i++) {
        ch = get_chunk(ar, i);
        ch->num= i;
        q_insert(in, ch);
    }
    printf("CHUNKS: %i\n",chunks(ar));
    chunk  array[chunks(ar)];
   
    args = malloc(sizeof(struct args) * opt.num_threads);
    threads = malloc(sizeof(struct thread_info) * opt.num_threads);

    for (i = 0; i < opt.num_threads; i++) {
		threads[i].thread_num = i;
		args[i].in = in;
		args[i].out = out;
		args[i].process     = decompress;
		
		if ( 0 != pthread_create(&threads[i].thread_id, NULL,
					 worker, &args[i])) {
			printf("Could not create thread #%d", i);
			exit(1);
		}
	}
	
    for (i = 0; i < opt.num_threads; i++)
		pthread_join(threads[i].thread_id, NULL);
    
    
    
    for(i=0; i<chunks(ar); i++) {
        ch=q_remove(out);
        array[ch->num]= ch; //guardamos los chunks en un array de manera ordenada
        
    }
   for(i=0; i<chunks(ar); i++) {
        write(fd, array[i]->data, array[i]->size);   
        free_chunk(array[i]);
    }
    

    close_archive_file(ar);    
    close(fd);
    q_destroy(in);
    q_destroy(out);
    free(args);
	free(threads);
	pthread_exit(NULL);
}

int main(int argc, char *argv[]) {    
    struct options opt;
    
    opt.compress=COMPRESS;
    opt.num_threads=3;
    opt.size=CHUNK_SIZE;
    opt.queue_size=QUEUE_SIZE;
    
    read_options(argc, argv, &opt);
    
    if(opt.compress==COMPRESS) comp(opt);
    else decomp(opt);
    return 0;
    
}
    
    
