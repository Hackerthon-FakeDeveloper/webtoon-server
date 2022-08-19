package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;

import org.corodiak.scfakedeveloper.type.entity.QTag;
import org.corodiak.scfakedeveloper.type.entity.QWebtoon;
import org.corodiak.scfakedeveloper.type.entity.QWebtoonTag;
import org.corodiak.scfakedeveloper.type.entity.WebtoonTag;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QWebtoonTagRepositoryImpl implements QWebtoonTagRepository {

	private final JPAQueryFactory queryFactory;
	private QWebtoonTag qWebtoonTag = QWebtoonTag.webtoonTag;
	private QWebtoon qWebtoon = QWebtoon.webtoon;
	private QTag qTag = QTag.tag;

	@Override
	public List<WebtoonTag> findByTagSeq(Long seq) {
		List<WebtoonTag> results = queryFactory.selectFrom(qWebtoonTag)
			.where(qWebtoonTag.tag.seq.eq(seq))
			.innerJoin(qWebtoonTag.webtoon, qWebtoon)
			.innerJoin(qWebtoonTag.tag, qTag)
			.fetchJoin()
			.fetch();
		return results;
	}
}
