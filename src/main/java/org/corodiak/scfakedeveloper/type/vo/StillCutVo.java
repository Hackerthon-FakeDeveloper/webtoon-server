package org.corodiak.scfakedeveloper.type.vo;

import lombok.Getter;
import lombok.ToString;
import org.corodiak.scfakedeveloper.type.entity.StillCut;

@Getter
@ToString
public class StillCutVo {

    private Long seq;
    private String url;
    private String description;

    public StillCutVo(StillCut entity) {
        this.seq = entity.getSeq();
        this.url = entity.getUrl();
        this.description = entity.getDescription();
    }

    @Getter
    @ToString(callSuper = true)
    public static class StillCutVoWithWebtoon extends StillCutVo {

        private WebtoonVo webtoon;

        public StillCutVoWithWebtoon(StillCut entity) {
            super(entity);
            this.webtoon = new WebtoonVo.WebtoonVoWithAuthor(entity.getWebtoon());
        }
    }
}
