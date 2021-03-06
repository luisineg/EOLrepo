import java.nio.file.Files;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.io.*;

public class NewEol{
	

	static final String 	EOL_WINDOWS = "\\\r\\\n",
							EOL_UNIX = "\\\n";
	
	public static void main(String[] args){
        String stringa_path = "";
        String stringa_force = "";
        Boolean force = false;
        String format = "";
        String v = "";
        String format_result = "";
        Boolean verbose = false;

        if(args.length == 1 && String.valueOf(args[0]).equalsIgnoreCase("-h")){
            System.out.println("Dettagli di utilizzo del tool : Il presente tool ha il compito di sostituire gli end of lines ");
            System.out.println("dei file contenuti in un dato percorso con l\'end of line previsto dal formato Windows");
            System.out.println("oppure Unix, scelto dall'utente.");
            System.out.println("");
            System.out.println("Uso:    java -jar ReplaceEol <-Path> <-format> [-opzioni]");
            System.out.println("dove le opzioni sono:");
            System.out.println("    -h : help");
            System.out.println("    -w : windows eol format");
            System.out.println("    -u : unix eol format");
            System.out.println("    -f : force mode");
            System.out.println("    -v : verbose mode");
            return;
        }
        
        if(args.length != 3 && args.length != 4 && args.length != 2){
            System.out.println("Dettagli di utilizzo del tool : Il presente tool ha il compito di sostituire gli end of lines ");
            System.out.println("dei file contenuti in un dato percorso con l\'end of line previsto dal formato Windows");
            System.out.println("oppure Unix, scelto dall'utente.");
            System.out.println("");
            System.out.println("Errore di sintassi. java subsEol <-Path> <-format> [-option]");
            System.out.println("Uso:    java -jar ReplaceEol <Path> [-opzioni]");
            System.out.println("dove le opzioni sono:");
            System.out.println("    -h : help");
            System.out.println("    -w : windows eol format");
            System.out.println("    -u : unix eol format");
            System.out.println("    -f : force mode");
            System.out.println("    -v : verbose mode");
        }
        
        try{
			stringa_path = String.valueOf(args[0]);
			/*Modifica nuova*/
            for(int i = 1; i < args.length; i++){
                switch (String.valueOf(args[i])){
					
					case "-w":
						format_result = EOL_WINDOWS;
						break;
					case "-u":
						format_result = EOL_UNIX;
						break;
					case "-v":
						verbose = true;
						break;
					case "-f":
						force = true;
				}
            }
            
            navigate(stringa_path,force,verbose,format_result);
            return;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
	}

	public static Boolean replaceeol(String s,String format){
		Boolean result = false;
		try{
			
			Path path = Paths.get(s);	
			Charset charset = StandardCharsets.UTF_8;
			String content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll((EOL_UNIX+"|"+EOL_WINDOWS),format);
			Files.write(path, content.getBytes(charset));
			result = true;
			return result;
		}catch(Exception e){
            System.out.println(e.getMessage());
			return result;
		}
	}

    public static String navigate (String path, Boolean f, Boolean v, String format){

        File[] files = new File(path).listFiles();
        int count_file = 0;
        int count_folder = 1;
        Boolean valid = true;
        try {
            for (File file: files){
                if (file.isDirectory()){
                    navigate(file.getAbsolutePath(), f, v, format);
                    count_folder++;
                }
                else{
                    //Richiamo primitiva
                    if (v){
                        System.out.println("Percorso corrente: "+file.getAbsolutePath());
                    }
                    valid = replaceeol(file.getAbsolutePath(),format);
                         
                    if (!valid){
                        if (f){
                            continue;
                        }
                        else{
                            return "Ops, errore!";
                        }
                    }
                        
                    count_file++;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "File modificati : "+count_file+" - Cartelle/Sottocartelle : "+count_folder;
    }    
    
}