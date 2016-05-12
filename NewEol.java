
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.io.*;

public class NewEol{
	public static void main(String[] args){
        String stringa_path = "";
        String stringa_force = "";
        Boolean force = false;
        String format = "";
        String v = "";
        String format_result = "";
        Boolean verbose = false;

        if(args.length == 1 && String.valueOf(args[0]).equalsIgnoreCase("-h")){
            System.out.println("Uso:    java -jar ReplaceEol [-opzioni]");
            System.out.println("dove le opzioni sono:");
            System.out.println("    -h : help");
            System.out.println("    -w : windows eol format");
            System.out.println("    -u : unix eol format");
            System.out.println("    -f : forced mode");
            System.out.println("    -v : verbose mode");
            return;
        }
        
        if(args.length != 3 && args.length != 4 && args.length != 2){
            System.out.println("Errore di sintassi. java subsEol <path> <format> <forced_mode> <verbose>");
        }

        try{
            for(int i = 0; i < args.length ; i++){
                if(i==0){
                    stringa_path = String.valueOf(args[0]);
                }                
                if(i==1){
                    format = String.valueOf(args[1]);
                }
                if(i==2){
                    stringa_force = String.valueOf(args[2]); 
                }
                if(i==3){
                    v = String.valueOf(args[3]);
                }
            }

            if(format.equalsIgnoreCase("-w")){
                format_result = "\\n\\r";
            }else{
                format_result = "\\n";
            }

            if(stringa_force.equalsIgnoreCase("-f")){
                force = true;
            }

            if(v.equalsIgnoreCase("-v")){
                verbose = true;
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
            if(format.equalsIgnoreCase("\\n\\r")){
                content = content.replaceAll("\\n",format);
            }else{
                content = content.replaceAll("\\n\\r", format);   
            }
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