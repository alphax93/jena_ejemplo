/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jena1;

import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.vocabulary.DC_11;
import org.apache.jena.vocabulary.VCARD;

/**
 *
 * @author Entrar
 */
public class Jena1 {

    static String libroURI = "http://web.com/libro1234";
    static String autorURI = "http://web.com/autor1234";
    static String titulo = "Crepúsculo";
    static String nombre = "Stephanie";
    static String apellido = "Meyer";
    static String fecha = "2005";
    
    public static void main(String[] args) {
        
        LogCtl.setCmdLogging();
        Model modelo = ModelFactory.createDefaultModel();
        
        modelo.setNsPrefix("web","http://web.com/");
        
        Resource autor1 = modelo.createResource(autorURI);
        autor1.addProperty(VCARD.NAME, nombre);
        autor1.addProperty(VCARD.Family, apellido);
        
        Resource libro1 = modelo.createResource(libroURI);
        libro1.addProperty(DC_11.title, titulo);
        libro1.addProperty(DC_11.creator, autor1);
        libro1.addProperty(DC_11.title, titulo);
        libro1.addProperty(DC_11.date, fecha);
        
        //modelo.write(System.out); //impresión básica en rdf, mejor usar la de la línea siguiente
        //RDFDataMgr.write(System.out, modelo, RDFFormat.TURTLE_PRETTY);
        
        //El archivo se encuentra en la carpeta del proyecto
        Model modelo2 = RDFDataMgr.loadModel("ejemplo.ttl");
        //RDFDataMgr.write(System.out, modelo2, RDFFormat.TURTLE_PRETTY);
    
        Model modeloUnido = modelo.union(modelo2);
        Resource recurso = modeloUnido.getResource("http://www.si2.com/si/libro4");
        Resource autorTmp = recurso.getProperty(DC_11.creator).getResource();
        System.out.println("Autor libro4: " + autorTmp.toString());
        
        /*
        
        StmtIterator iter = modelo.listStatements();
        while (iter.hasNext()){
            Statement declaracion = iter.next();
            System.out.println(declaracion.toString());
            Resource sujeto = declaracion.getSubject();
            System.out.println("\tsujeto: " + sujeto.toString());
            Property propiedad = declaracion.getPredicate();
            System.out.println("\tpropiedad: " + propiedad.toString());
            RDFNode objeto = declaracion.getObject();
            if(objeto instanceof Resource){
                System.out.println("\tobjeto tipo recurso: " + objeto.toString());
            } else {
                System.out.println("\tobjeto literal: " + objeto.toString());
            }
        }
        
        //Si sabemos la uri del recurso podemos obtenerlo
        Resource recurso = modelo.getResource(libroURI);
        String tituloTmp = recurso.getProperty(DC_11.title).getString();
        System.out.println("Titulo: " + tituloTmp);
        Resource autorTmp = recurso.getProperty(DC_11.creator).getResource();
        System.out.println("Autor: " + autorTmp.toString());
        
        String nombreTmp = autorTmp.getProperty(VCARD.NAME).getString();
        System.out.println("Nombre: " + nombreTmp);
        
        String apellidoTmp = autorTmp.getProperty(VCARD.Family).getString();
        System.out.println("Apellido: " + apellidoTmp);
        
        */
        
        
    }
    
}
