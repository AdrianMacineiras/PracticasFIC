package ejercicio2;

import java.util.Objects;


public class Cancion implements Comparable<Cancion> {
    
    private String title;
    private String author;
    private String albm;
    private String style;
    
    
    public  Cancion(String  titulo , String  autor  , String  album , String  estilo){
        this.title  =   titulo;
        this.author =   autor;
        this.albm   =   album;
        this.style  =   estilo;
    }
    
//  Getters ...
    public  String  getTitulo () { 
        return this.title;
    }
    
    public  String  getAutor () { 
        return this.author;
    }
    
    public  String  getAlbum () { 
        return this.albm;
    }
    
    public  String  getEstilo () { 
        return this.style;
    }

@Override
public  String  toString () {
    String titulo, autor ,album;
    if(this.title.length()> 20){
        titulo = this.title.substring(0, Math.min(this.title.length(), 20));
    } else {
        titulo = this.title;
    }
    
    if(this.author.length()> 20){
        autor = this.author.substring(0, Math.min(this.author.length(), 20));
    } else {
        autor = this.author;
    }
    
    if(this.albm.length()> 20){
        album = this.albm.substring(0, Math.min(this.albm.length(), 20));
    } else {
        album = this.albm;
    }
    return titulo + " - "+ autor +" - "+ album;
}

@Override
public  boolean  equals(Object  obj) { 
    if(this == obj){
        return true;
    }
    
    if(obj == null){
        return false;
    }
    
    if (getClass() != obj.getClass()) {
        return false;
    }
    
    final Cancion other = (Cancion) obj;
        if(!this.title.equals(other.title)){
            return false;
        }
        
        if(!this.author.equals(other.author)){
            return false;
        }
        
    return true;
}

@Override
public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.author);
        return hash;
    }

    @Override
    public int compareTo(Cancion o) {
        int resultado = title.compareToIgnoreCase(o.title);
        if (resultado == 0) resultado = albm.compareToIgnoreCase(o.albm); 
        return resultado;
    }
    
}
