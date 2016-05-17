import java.nio.file.Files;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.io.*;
import com.google.common.base.*;

/*Erre Cristiano*/
public class VerifyAschii {
	
	public static final String UTF8_BOM = "\uFEFF";

	public static void main(String[] args){
		
		isAnsi(String.valueOf(args[0]));
	}
	
	public static void isAnsi (String path){

        File[] files = new File(path).listFiles();
		
        try {
			System.out.println("");
            for (File file: files){
                if (file.isDirectory()){
                    isAnsi(file.getAbsolutePath());
                    
                }
                else{
					
					//counters
					int charsCount = 0;
					int stringCharsCount = 0;

					Scanner in = null;
					
					in = new Scanner(file);

					while (in.hasNext()) {
						String tmpStr = in.nextLine();
						tmpStr = tmpStr.replace("\uFEFF", "");
						tmpStr = tmpStr.replace("\uFEFF", "");
						if(!tmpStr.equalsIgnoreCase("")){
							
							String[] a = tmpStr.split("");
							charsCount += a.length;
							//
							byte[] by = tmpStr.getBytes("UTF-8");
							stringCharsCount += by.length;
						}
						
					}
					
					System.out.println(""+file.getName());
					System.out.println("N of byte in ANSI	: " + charsCount);
					System.out.println("N of byte in UTF8 	: " + stringCharsCount);
					
					if (charsCount != stringCharsCount){
						System.out.println("ATTENTION! Conversion doesn't match!");
					}
					else {
						System.out.println("Safe conversion.");
					}
					System.out.println("");
					
					in.close();
					
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }    
}