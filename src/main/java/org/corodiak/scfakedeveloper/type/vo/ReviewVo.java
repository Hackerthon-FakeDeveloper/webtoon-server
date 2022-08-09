package org.corodiak.scfakedeveloper.type.vo;

import lombok.Getter;
import lombok.ToString;
import org.corodiak.scfakedeveloper.type.entity.Review;

@Getter
@ToString
public class ReviewVo {

    private Long seq;
    private int scoreFirst;
    private int scoreSecond;
    private int scoreThird;
    private String content;

    public ReviewVo(Review entity) {
        this.seq = entity.getSeq();
        this.scoreFirst = entity.getScoreFirst();
        this.scoreSecond = entity.getScoreSecond();
        this.scoreThird = entity.getScoreThird();
        this.content = entity.getContent();
    }

    @Getter
    @ToString(callSuper = true)
    public static class ReviewVoWithUserAndWebtoon extends ReviewVo {

        private UserVo user;
        private WebtoonVo webtoon;

        public ReviewVoWithUserAndWebtoon(Review entity) {
            super(entity);
            this.user = new UserVo(entity.getUser());
            this.webtoon = new WebtoonVo(entity.getWebtoon());
        }
    }
}
