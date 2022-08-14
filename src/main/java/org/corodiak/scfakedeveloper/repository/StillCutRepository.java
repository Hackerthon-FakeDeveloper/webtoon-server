package org.corodiak.scfakedeveloper.repository;

import org.corodiak.scfakedeveloper.repository.qrepository.QStillCutRepository;
import org.corodiak.scfakedeveloper.type.entity.StillCut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StillCutRepository extends JpaRepository<StillCut, Long>, QStillCutRepository {
}
