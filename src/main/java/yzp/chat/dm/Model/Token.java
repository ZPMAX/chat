package yzp.chat.dm.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/16 12:47
 * @author:多堕大手笔的萨克
 **/
@Entity
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid;
    private String token;
}
