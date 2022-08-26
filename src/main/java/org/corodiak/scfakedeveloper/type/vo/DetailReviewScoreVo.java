package org.corodiak.scfakedeveloper.type.vo;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailReviewScoreVo {
	public Map<String, Double> detailReview = new HashMap<>();
	public void addData(String key, Double value) {
		detailReview.put(key, value);
	}
}
