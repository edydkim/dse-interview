package com.app_creative.poc.service;

import com.app_creative.poc.model.CreditScore;
import com.app_creative.poc.repository.CreditScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Eddie Kim
 * @project dse-interview
 * @package com.app_creative.poc.service
 */
@Transactional
@Service
public class CreditScoreService {

    @Autowired
    private CreditScoreRepository creditScoreRepository;

    public List<CreditScore> getCreditScores(int age, int page) {
        return creditScoreRepository.findDistinctByAge(age, Pageable.ofSize(10).withPage(page));
    }
}
