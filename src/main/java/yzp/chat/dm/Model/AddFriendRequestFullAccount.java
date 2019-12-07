package yzp.chat.dm.Model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/12/7 20:06
 * @author:多堕大手笔的萨克
 **/
@Data
public class AddFriendRequestFullAccount {
    private Long id;

    private Long aid;
    private Account account;

    private Long toaid;
    private String verifiInfo;
    private Integer operation;

    private LocalDateTime createdDate;
}
