package cqu.wjj.springbuck.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
public class BaseEntity implements Serializable {
    @Id
    private String Id;
    private Date createTime;
    private Date updateTime;
}
