#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include "chunk_archive.h"

#define CHUNK_LIST_DEFAULT_SIZE 1000

typedef struct {
    int size;
    int num;
    char *data;
} disk_chunk;

archive create_archive_file(char *filename) {
    int fd;
    int chunks=0;
    
    archive ar;
    
    if((fd=open(filename, O_RDWR | O_CREAT | O_EXCL, S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP | S_IROTH | S_IWOTH))==-1) {
        printf("Could not create file %s: %s\n", filename, strerror(errno));
        exit(0);
    }
    
    write(fd, "CHUNK", 5);
    write(fd, &chunks, sizeof(unsigned int));
    
    ar=malloc(sizeof(*ar));
    
    ar->fd = fd;
    ar->chunks=0;
    ar->name=strdup(filename);
    ar->chunk_offset=NULL;
    ar->chunk_size  =NULL;
    
    return ar;
}

void check_chunk_list_size(archive ar) {
    if(ar->chunks % CHUNK_LIST_DEFAULT_SIZE == 0) {
        ar->chunk_offset = realloc(ar->chunk_offset, ar->chunks+CHUNK_LIST_DEFAULT_SIZE*sizeof(unsigned int));
        ar->chunk_size   = realloc(ar->chunk_size  , ar->chunks+CHUNK_LIST_DEFAULT_SIZE*sizeof(unsigned int));
    }
}

archive open_archive_file(char *filename) {
    int i, fd;
    char magic[5];
    int chunks;
    archive ar;

    if((fd=open(filename, O_RDWR))==-1) {
        printf("Could not open file %s: %s\n", filename, strerror(errno));
        exit(0);
    }    
    
    if(read(fd, magic, 5) < 5) {
        printf("Could not read %s\n", filename);
        exit(0);
    }
    
    if(strncmp(magic, "CHUNK", 5)) {
        printf("%s is not an archive file\n", filename);
        exit(0);
    }

    if(read(fd, &chunks, sizeof(unsigned int))<sizeof(unsigned int)) {
        printf("Could not read %s\n", filename);
        exit(0);
    }
    
    ar = malloc(sizeof(*ar));
    
    ar->fd = fd;
    ar->chunks=0;
    ar->name=strdup(filename);
    ar->chunk_offset=NULL;
    ar->chunk_size  =NULL;
    
    for(i=0; i<chunks; i++) {
        check_chunk_list_size(ar);
        
        int size, chunk_num;
        read(fd, &size, sizeof(unsigned int));
        read(fd, &chunk_num, sizeof(unsigned int));
        
        ar->chunk_offset[chunk_num] = lseek(fd, 0, SEEK_CUR);
        ar->chunk_size[chunk_num]   = size;
        
        ar->chunks++;
        
        lseek(fd, size, SEEK_CUR);
    }
    
    return ar;
}

void close_archive_file(archive ar) {
    close(ar->fd);
    free(ar->chunk_offset);
    free(ar->chunk_size);
    free(ar->name);
    free(ar);
}

int add_chunk(archive ar,chunk ch, int chunk_num) {
    check_chunk_list_size(ar);

    lseek(ar->fd, 0, SEEK_END);
    write(ar->fd, &ch->size, sizeof(unsigned int));
    write(ar->fd, &chunk_num, sizeof(unsigned int));
    
    ar->chunk_offset[chunk_num] = lseek(ar->fd, 0, SEEK_CUR);
    write(ar->fd, ch->data, ch->size);

    ar->chunk_size[chunk_num] = ch->size;
    ar->chunks++;

    lseek(ar->fd, 5, SEEK_SET);
    write(ar->fd, &ar->chunks, sizeof(unsigned int));
    return 0;
}

chunk get_chunk(archive ar, int chunk_num) {
    chunk res;
    
    res=malloc(sizeof(*res));
    if(chunk_num > ar->chunks) {
        res->size=0;
        res->data=NULL;
        return res;
    }
        
    res->size=ar->chunk_size[chunk_num];
    res->data=malloc(res->size);
    res->num = chunk_num;
    lseek(ar->fd, ar->chunk_offset[chunk_num], SEEK_SET);
    read(ar->fd, res->data, res->size); 
    
    return res;
}

int chunks(archive ar) {
    return ar->chunks;
}

chunk alloc_chunk(int size) {
    chunk res;
    res = malloc(sizeof(*res));
    res->data = malloc(size);
    res->size = size;
    
    return res;
}

void free_chunk(chunk ch) {
    free(ch->data);
    free(ch);
}