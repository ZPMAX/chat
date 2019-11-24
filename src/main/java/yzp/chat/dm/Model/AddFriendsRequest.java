package yzp.chat.dm.Model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/16 14:49
 * @author:多堕大手笔的萨克
 **/
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class AddFriendsRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long aid;
    private Long toaid;
    private String verifilnfo;
    private Integer operation;
    @CreatedDate
    private LocalDate date;
}
