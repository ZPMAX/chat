package yzp.chat.dm.Servlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/20 15:50
 * @author:多堕大手笔的萨克
 **/
@Service
public class FileSystemStorageService {
    @Value("${com.example.chat.dm.Servlet.FileSystemStorageService.rootLocationStr}")
    String rootLocationStr;
    public Path getRootLocation(){
        if(rootLocation==null){
            init();
        }
        return  rootLocation;
    }
    public void setRootLocation(Path rootLocation){
        this.rootLocation=rootLocation;
    }

    public FileSystemStorageService(){

    }
    private Path rootLocation;
    public void store(String filename,MultipartFile file) {
        if (filename == null){
            filename = StringUtils.cleanPath(file.getOriginalFilename());
        }
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new RuntimeException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.getRootLocation().resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }

    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new RuntimeException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.getRootLocation().resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }
    public Stream<Path> loadAll(String filename){
        try{
            return Files.walk(this.getRootLocation(),1)
                    .filter(path -> !path.equals(this.getRootLocation()))
                    .map(this.getRootLocation()::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stored files",e);
        }
    }
      public Path load(String filename){
        return  getRootLocation().resolve(filename);
      }
      public Resource loadAsResource(String fliename){
        try{
            Path file = load(fliename);
            Resource resource = new UrlResource(file.toUri());
            System.out.println(resource.exists()||resource.isReadable());
            if(resource.exists()||resource.isReadable()){
                System.out.println("yes");
                return resource;
            }else{
                System.out.println("no");
                throw new RuntimeException(
                        "Could not read file : "+fliename
                );
            }
        } catch (MalformedURLException e) {
            System.out.println("fk");
            throw new RuntimeException("Could not read file :"+fliename,e);
        }
      }
      public void deleteAll(){
          FileSystemUtils.deleteRecursively(getRootLocation().toFile());
      }
      public void init(){
        try{
            rootLocation = Paths.get(rootLocationStr);
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not initialize storage",e);
        }
      }
}
