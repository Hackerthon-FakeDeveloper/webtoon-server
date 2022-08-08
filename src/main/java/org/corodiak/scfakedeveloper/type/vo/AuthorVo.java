package org.corodiak.scfakedeveloper.type.vo;

import lombok.Getter;
import lombok.ToString;
import org.corodiak.scfakedeveloper.type.entity.Author;

@Getter
@ToString
public class AuthorVo {

    private Long seq;
    private String name;
    private String description;

    public AuthorVo(Author entity) {
        this.seq = entity.getSeq();
        this.name = entity.getName();
        this.description = entity.getDescription();
    }
}
