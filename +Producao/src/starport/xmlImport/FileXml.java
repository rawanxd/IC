package starport.xmlImport;
import java.io.*;
import java.util.*;

public class FileXml {
    private String xmlPathname;     
    
    public FileXml( String xmlPathname ) {
        this.xmlPathname=xmlPathname;     
    }
    
    
    public Vector listaArquivo(){

    File dirCorrente = new File(xmlPathname);
    File[] filho = dirCorrente.listFiles();
    
    Vector v = new Vector();
    for(int i=0; i<filho.length; i++)
      if(filho[i].isFile()){
        if(filho[i].toString().toLowerCase().endsWith(".xml")){
          v.add(filho[i].toString());	
        }
      }
    return v;
    }    

 }