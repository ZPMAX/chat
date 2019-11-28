package yzp.chat.dm.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/28 17:44
 * @author:多堕大手笔的萨克
 **/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequsetException extends Throwable {
}
