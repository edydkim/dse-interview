package com.app_creative.poc.repository;

import com.app_creative.poc.model.CreditScore;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Eddie Kim
 * @project dse-interview
 * @package com.app_creative.poc.repository
 */
public interface CreditScoreRepository extends JpaRepository<CreditScore, Long> {
    List<CreditScore> findDistinctByAge(Integer age, Pageable pageable);

}