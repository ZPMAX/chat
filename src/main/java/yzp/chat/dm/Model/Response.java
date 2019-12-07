package yzp.chat.dm.Model;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/12/7 20:19
 * @author:多堕大手笔的萨克
 **/
public class Response<T> {
    String msg;
    T data;
    int code = 200;
}
