#include <bzlib.h>
#include <stdio.h>
#include <stdlib.h>
#include "compress.h"

chunk compress(chunk ch) {
    bz_stream st;
    chunk res;
    
    res=malloc(sizeof(*res));
    res->data=malloc(ch->size*2);
    res->size=0;
    
    st.bzalloc=NULL;
    st.bzfree =NULL;
    st.opaque =NULL;
    
    if(BZ2_bzCompressInit(&st, 9, 0, 0)!=BZ_OK) {
        printf("Could not initialize the bzip2 library\n");
        exit(0);
    }
    
    st.next_in  = ch->data;
    st.avail_in = ch->size;
    st.next_out = res->data;
    st.avail_out= ch->size*2;
    
    BZ2_bzCompress(&st, BZ_RUN);
    while(BZ2_bzCompress(&st, BZ_FINISH)!=BZ_STREAM_END) ;
    res->size=ch->size*2-st.avail_out;
    BZ2_bzCompressEnd(&st);
    
    return res;
}

chunk decompress(chunk ch) {
    bz_stream st;
    chunk res;
    int out_size=ch->size*2;
    
    res=malloc(sizeof(*res));
    res->data=malloc(out_size);
    
    st.bzalloc=NULL;
    st.bzfree =NULL;
    st.opaque =NULL;
    
    if(BZ2_bzDecompressInit(&st, 0, 0)!=BZ_OK) {
        printf("Could not initialize the bzip2 library\n");
        exit(0);
    }
    
    st.next_in  = ch->data;
    st.avail_in = ch->size;
    st.next_out = res->data;
    st.avail_out= ch->size*2;
    
    while(1) {
        if(BZ2_bzDecompress(&st) ==BZ_STREAM_END) break;
        if(st.avail_out==0) {
            res->data=realloc(res->data, out_size+ch->size*2);
            st.next_out=res->data+out_size;
            st.avail_out=ch->size*2;
            out_size+=ch->size*2;
        }
    }
    res->size=out_size-st.avail_out;

    BZ2_bzDecompressEnd(&st);
    
    return res;
}
