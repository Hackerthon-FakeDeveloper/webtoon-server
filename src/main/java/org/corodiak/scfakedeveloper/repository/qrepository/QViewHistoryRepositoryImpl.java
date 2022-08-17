package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;

import org.corodiak.scfakedeveloper.type.entity.QUser;
import org.corodiak.scfakedeveloper.type.entity.QViewHistory;
import org.corodiak.scfakedeveloper.type.entity.QWebtoon;
import org.corodiak.scfakedeveloper.type.entity.ViewHistory;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QViewHistoryRepositoryImpl implements QViewHistoryRepository {

	private final JPAQueryFactory queryFactory;
	private QViewHistory qViewHistory = QViewHistory.viewHistory;
	private QUser qUser = QUser.user;
	private QWebtoon qWebtoon = QWebtoon.webtoon;

	@Override
	public List<ViewHistory> findByUserSeq(Long seq) {
		List<ViewHistory> results = queryFactory.selectFrom(qViewHistory)
			.where(qViewHistory.user.seq.eq(seq))
			.innerJoin(qViewHistory.user, qUser)
			.innerJoin(qViewHistory.webtoon, qWebtoon)
			.fetchJoin()
			.fetch();
		return results;
	}
}
