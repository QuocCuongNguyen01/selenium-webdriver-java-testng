package JavaTester;

import java.io.File;

public class Topic_06_System {
	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		String osName = System.getProperty("os.name");
		
		String vietnam = "VietNam.jpeg";
		String thailan = "ThaiLan.jpeg";
		String indonesia = "Indonesia.jpeg";
		//image path
		String uploadFileFolder = projectPath + File.separator + "/UploadFile/" + File.separator;
		//image path
		String vietnamFilePath = uploadFileFolder+ vietnam;
		String thailanFilePath = uploadFileFolder+ thailan;
		String indonesiaFilePath = uploadFileFolder+ indonesia;
		
		System.out.println(vietnamFilePath);
		System.out.println(thailanFilePath);
		System.out.println(indonesiaFilePath);
		
		 //       File file = new File(uploadFileFolder);
		  //      String[] fileList = file.list();
		   //     for(String name:fileList){
		    //        System.out.println(name);
		    //    }
		//    }
	File directoryPath = new File(uploadFileFolder);
    //List of all files and directories
    String contents[] = directoryPath.list();
    System.out.println("List of files and directories in the specified directory:");
    for(int i=0; i<contents.length; i++) {
       System.out.println(contents[i]);
	}
}
}