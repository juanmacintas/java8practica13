/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Práctica 13_1 - Java I/O Fundamentals
 * @author juanma
 */
public class FileScanInteractive {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java FileScanInteractive <fichero>");
            System.exit(-1);
        }
        String file = args[0];
        FileScanInteractive scan = new FileScanInteractive();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String search = "";
            System.out.println("Por favor introduzca la palabra a buscar en el fichero:");
            while(true) {
                System.out.println("Buscando en el fichero:" + file);
                search = in.readLine().trim();
                if (search.equalsIgnoreCase("q")) 
                    break;
                int count = countTokens(file,search);
                System.out.printf("La palabra %s aparece %d veces en el  fichero", search,count);
            }
            
        } catch (IOException ioEx) {
            System.out.println("Ha ocurrido una excepcion:" + ioEx);
            System.exit(-1);
        }
    }
   
      
    public static int countTokens(String file, String search) throws IOException {
        int instanceCount = 0;
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr);
             Scanner s = new Scanner(br)) {
            
            s.useDelimiter("\\W");
            while(s.hasNext()) {
                if(search.equalsIgnoreCase(s.next().trim())) {
                    instanceCount++;
                }
            }
        }
        
        return instanceCount;
    }
}