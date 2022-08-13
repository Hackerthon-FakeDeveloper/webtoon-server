package org.corodiak.scfakedeveloper.type.vo;

import lombok.Getter;
import lombok.ToString;
import org.corodiak.scfakedeveloper.type.entity.Genre;

@Getter
@ToString
public class GenreVo {
    private Long seq;
    private String genre;

    public GenreVo(Genre entity) {
        this.seq = entity.getSeq();
        this.genre = entity.getGenre();
    }
}
