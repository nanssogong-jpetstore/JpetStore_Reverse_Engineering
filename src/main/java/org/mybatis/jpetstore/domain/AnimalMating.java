package org.mybatis.jpetstore.domain;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AnimalMating {
    private static final long serialVersionUID = 8751282104532159742L;


    private String userId;
    private String imgUrl;
    private String title;
    private String contents;
    private String characters;
    private String categoryid;

}
