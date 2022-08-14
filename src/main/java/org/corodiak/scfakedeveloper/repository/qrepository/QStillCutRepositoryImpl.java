package org.corodiak.scfakedeveloper.repository.qrepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.type.entity.QStillCut;
import org.corodiak.scfakedeveloper.type.entity.QWebtoon;
import org.corodiak.scfakedeveloper.type.entity.StillCut;

import java.util.List;

@RequiredArgsConstructor
public class QStillCutRepositoryImpl implements QStillCutRepository {

    private final JPAQueryFactory queryFactory;
    private QStillCut qStillCut = QStillCut.stillCut;
    private QWebtoon qWebtoon = QWebtoon.webtoon;

    @Override
    public List<StillCut> findByWebtoonSeq(Long seq) {
        List<StillCut> results = queryFactory.selectFrom(qStillCut)
                .where(qStillCut.webtoon.seq.eq(seq))
                .innerJoin(qStillCut.webtoon, qWebtoon)
                .fetchJoin()
                .fetch();
        return results;
    }
}
