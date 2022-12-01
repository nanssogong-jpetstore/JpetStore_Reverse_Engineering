package org.mybatis.jpetstore.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardLike {
    private static final long serialVersionUID = 6620528781626504350L;
    private int boardId;
    private String userId;
}
