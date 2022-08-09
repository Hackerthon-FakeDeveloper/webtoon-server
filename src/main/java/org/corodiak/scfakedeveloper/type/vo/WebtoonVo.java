package org.corodiak.scfakedeveloper.type.vo;

import lombok.Getter;
import lombok.ToString;
import org.corodiak.scfakedeveloper.type.entity.Webtoon;

import java.time.LocalDate;

@Getter
@ToString
public class WebtoonVo {

    private Long seq;
    private String title;
    private String platform;
    private boolean isAdult;
    private String thumbnail;
    private String url;
    private String description;
    private LocalDate startDate;

    public WebtoonVo(Webtoon entity) {
        this.seq = entity.getSeq();
        this.title = entity.getTitle();
        this.platform = entity.getPlatform();
        this.isAdult = entity.isAdult();
        this.thumbnail = entity.getThumbnail();
        this.url = entity.getUrl();
        this.description = entity.getDescription();
        this.startDate = entity.getStartDate();
    }

    @Getter
    @ToString(callSuper = true)
    public static class WebtoonVoWithAuthor extends WebtoonVo {

        private AuthorVo author;

        public WebtoonVoWithAuthor(Webtoon entity) {
            super(entity);
            this.author = new AuthorVo(entity.getAuthor());
        }
    }

}
