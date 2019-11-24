package yzp.chat.dm.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yzp.chat.dm.Servlet.AppStorageService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/22 18:06
 * @author:多堕大手笔的萨克
 **/
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    AppStorageService appStorageService;
    @PostMapping("/upload")
    Object upload(@RequestParam("file") MultipartFile file){
        String id=appStorageService.store(file);
        Map<String,String> resp =new HashMap<>();
        resp.put("filed",id);
        return  resp;
    }
    @GetMapping("/{fid}")
    Object upload(@PathVariable String fid){
        org.springframework.core.io.Resource f= appStorageService.load(fid);
        return ResponseEntity.ok().header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" +f.getFilename() + "\""
        ).body(f);
    }
}
