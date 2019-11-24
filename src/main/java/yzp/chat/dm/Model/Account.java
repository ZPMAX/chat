package yzp.chat.dm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/16 12:15
 * @author:多堕大手笔的萨克
 **/
@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"phone"})
                           }
)
@EntityListeners(AuditingEntityListener.class)
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(length = 130)
    private String phone;
    private String name;
    @JsonIgnore
    private String pass;
    private String bio;
    private String avatar;
    private LocalDate birthday;
    private Integer gender;
}
