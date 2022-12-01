package org.mybatis.jpetstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardLike {
    private static final long serialVersionUID = 6620528781626504367L;
    private int boardId;
    private String userId;
}
