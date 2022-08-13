package org.corodiak.scfakedeveloper.type.vo;

import lombok.Getter;
import lombok.ToString;
import org.corodiak.scfakedeveloper.type.entity.Tag;

@Getter
@ToString
public class TagVo {
    private Long seq;
    private String name;

    public TagVo(Tag entity) {
        this.seq = entity.getSeq();
        this.name = entity.getName();
    }
}
