package com.javabom.producerconsumer.service;

import com.javabom.producerconsumer.domain.FailRequestRepository;
import com.javabom.producerconsumer.event.message.PayEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FailRequestService {

    private final FailRequestRepository failRequestRepository;

    public void recordFailRequest(PayEvent payEvent) {
        failRequestRepository.save(payEvent.toFail());
    }
}
