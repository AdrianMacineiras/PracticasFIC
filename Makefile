CFLAGS=-Wall -pthread -g
OBJS=compress.o chunk_archive.o options.o queue.o comp.o
LIBS=-lbz2
CC=gcc

all: comp

comp: $(OBJS)
	$(CC) $(CFLAGS) -o $@ $(OBJS) $(LIBS)

clean: 
	rm -f *.o comp
