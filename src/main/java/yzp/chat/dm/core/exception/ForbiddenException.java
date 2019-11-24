package yzp.chat.dm.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/17 10:18
 * @author:多堕大手笔的萨克
 **/
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException  extends RuntimeException{
}
