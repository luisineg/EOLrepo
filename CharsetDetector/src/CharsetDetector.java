
import java.io.File;
import java.io.FileInputStream;

public class CharsetDetector {
	
	public static String guessEncoding(byte[] bytes) {
		org.mozilla.universalchardet.UniversalDetector detector = new org.mozilla.universalchardet.UniversalDetector(null);
		detector.handleData(bytes, 0, bytes.length);
		detector.dataEnd();
		String encoding = detector.getDetectedCharset();
		detector.reset();
		if (encoding == null) {
			encoding = "Encoding not recognized.";
		}
		return encoding;
	}
	
	public static void detect(String path){
		File[] file = new File(path).listFiles();
		FileInputStream fileInputStream=null;
		try {
			for(File f : file){
				if(!f.isDirectory()){
					byte[] bFile = new byte[(int) f.length()];
					//convert file into array of bytes
					fileInputStream = new FileInputStream(f);
					fileInputStream.read(bFile);
					fileInputStream.close();
			
					String asd = guessEncoding(bFile);
					System.out.println("File : \""+f.getName()+"\" encoded with : "+asd);
				}else{
					detect(f.getAbsolutePath());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
        if(args.length < 1){
            System.out.println("Error, no parameters inserted. Insert the directory path.");
            System.out.println("Usage : java -jar CharsetDetector.jar <-Path_Directory>");
            return;
        }

        if(args.length > 1){
        	System.out.println("Error, too many parameters. Insert only the directory path.");
            System.out.println("Usage : java -jar CharsetDetector.jar <-Path_Directory>");
            return;
        }
        
        if(String.valueOf(args[0]).equalsIgnoreCase("-h")){
        	System.out.println("CharsetDetector is a program able to detect the charset of a file.");
        	System.out.println("Usage : java -jar CharsetDetector.jar <-Path_Directory> [-option]");
        	System.out.println("options :");
        	System.out.println("          -h   help");
        	return;
        }
        
        String s = String.valueOf(args[0]);
		
		detect(s);	
		
	}
}