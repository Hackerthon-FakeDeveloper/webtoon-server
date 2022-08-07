package org.corodiak.scfakedeveloper.repository;

import java.util.List;
import java.util.Optional;

import org.corodiak.scfakedeveloper.type.entity.OAuthUser;
import org.corodiak.scfakedeveloper.type.etc.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthUserRepository extends JpaRepository<OAuthUser, Long> {
	Optional<OAuthUser> findByProviderUserIdAndOap(String providerUserId, OAuthProvider oap);
	List<OAuthUser> findByUserSeq(Long userSeq);
}
