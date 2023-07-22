package com.prog4.progtd.repository;

import com.prog4.progtd.modele.RefIncrementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncrementationRepository extends JpaRepository<RefIncrementation, String> {

}
