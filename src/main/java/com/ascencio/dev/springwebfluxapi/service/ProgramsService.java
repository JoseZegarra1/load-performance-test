package com.ascencio.dev.springwebfluxapi.service;

import com.ascencio.dev.springwebfluxapi.domain.dto.ProgramsRequestDto;
import com.ascencio.dev.springwebfluxapi.domain.dto.ProgramsResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ProgramsService {

    Mono<ProgramsResponseDto> findById(Integer id);

    Flux<ProgramsResponseDto> findAll();

    Flux<ProgramsResponseDto> findInactive();

    Mono<ProgramsResponseDto> create(ProgramsRequestDto request);

    Mono<ProgramsResponseDto> update(Integer id, ProgramsRequestDto request);

    Mono<Void> delete(Integer id);

    Mono<ProgramsResponseDto> restore(Integer id);

}
