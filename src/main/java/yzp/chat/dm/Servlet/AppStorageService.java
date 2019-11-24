package yzp.chat.dm.Servlet;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.StringReader;
import java.util.UUID;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/22 18:38
 * @author:多堕大手笔的萨克
 **/
@Service
public class AppStorageService {
    @Resource
    FileSystemStorageService fileSystemStorageService;
    public String store(MultipartFile file){
        String[] split = file.getOriginalFilename().trim().split("\\.");
        String uuid = UUID.randomUUID().toString();
        String file_id = uuid + "." + split[split.length-1];
        fileSystemStorageService.store(file_id,file);
        return file_id;
    }
    public org.springframework.core.io.Resource load(String file_id){
        return fileSystemStorageService.loadAsResource(file_id);
    }
}
