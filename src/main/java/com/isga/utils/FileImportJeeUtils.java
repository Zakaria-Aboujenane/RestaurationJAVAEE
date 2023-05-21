package com.isga.utils;



import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

public class FileImportJeeUtils {
    public static String pathOfFiles = "";
    public static String[] fileTypes = {"png","pdf","jpg","svg","wordx","file","ppt"};
    public static final String IMAGES_FOLDER = "/home/zoygberd/Documents/IDWM-s1/JEE/app_jee/restauration/src/main/webapp/images/";

    public static String CreateAndGetUploadPath(ServletContext servletContext){
        String uploadPath = servletContext.getRealPath( IMAGES_FOLDER );
        File uploadDir = new File( uploadPath );
        if ( ! uploadDir.exists() ) { System.out.println("creation"); uploadDir.mkdir();}
        return IMAGES_FOLDER;
    }
    public static String getFileName( Part part ) {
        for ( String content : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( content.trim().startsWith( "filename" ) )
                return content.substring( content.indexOf( "=" ) + 2, content.length() - 1 );
        }
        return "Default.file";
    }


    /**
     * Cette methode prend le part et le nom du dossier de l'upload
     * elle retourne une map avec le nom de fichier et le path ou il'est enregistre dans le serveur
     * @param parts : en general en passe le request.getParts()
     * @param uploadPath : path pour enregisrer les fichiers dans le serveur
     * @param fileSuffix : suffixe en cas de plusieurs fichiers
     * @return savedPathsWithTheirNames
     * @throws IOException
     */
    public static Map<String,String> storeFiles(Collection<Part> parts, String uploadPath,String fileSuffix) throws IOException {
        Map<String,String> savedPathsWithTheirNames = new HashMap<String,String>();
        for ( Part part : parts ) {
            String fileName = getFileName(part);
            String fileNameWithSuffix = "";

            if(fileSuffix != null && !fileSuffix.equals(""))
                fileNameWithSuffix = setSuffixToFileName(fileName,fileSuffix);
            else
                fileNameWithSuffix = fileName;

            String fullPath = uploadPath + File.separator + fileNameWithSuffix;
            part.write( fullPath );
            savedPathsWithTheirNames.put(fileName,fullPath);
        }
        return savedPathsWithTheirNames;
    }
    public static String storeSingleFile(Part part, String uploadPath,String fileSuffix) throws IOException {
            String fileName = getFileName(part);
            String fileNameWithSuffix = "";

            if(fileSuffix != null && !fileSuffix.equals(""))
                fileNameWithSuffix = setSuffixToFileName(fileName,fileSuffix);
            else
                fileNameWithSuffix = fileName;

            String fullPath = uploadPath + File.separator + fileNameWithSuffix;
            part.write( fullPath + File.separator);
            return fileNameWithSuffix;
    }
    private static String setSuffixToFileName(String fileName,String fileSuffix){
        String fileNameWithSuffix = "";
        String ext="";
        String name="";
        for(int i = 0; i< fileTypes.length; i++){
            if(fileName.endsWith(fileTypes[i])){
                int extSize = fileName.length() - (fileTypes[i].length()+1);
                ext = fileName.substring(extSize,fileName.length());
                name = fileName.substring(0,extSize);
            }
        }
        fileNameWithSuffix = name + "_" + fileSuffix+""+ext;
        return fileNameWithSuffix;
    }
}
