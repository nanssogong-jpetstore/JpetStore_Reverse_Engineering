package org.mybatis.jpetstore.domain;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class AnimalMating implements Serializable {
    private static final long serialVersionUID = 8751282104532159742L;

    private int id;
    private String userId;
    private String imgUrl;
    private String title;
    private String contents;
    private String characters;
    private String categoryid;
    private Timestamp createdate;


}
