package com.ascencio.dev.springwebfluxapi.service.impl;

import com.ascencio.dev.springwebfluxapi.domain.dto.ProgramsRequestDto;
import com.ascencio.dev.springwebfluxapi.domain.dto.ProgramsResponseDto;
import com.ascencio.dev.springwebfluxapi.domain.mapper.ProgramsMapper;
import com.ascencio.dev.springwebfluxapi.exception.ResourceNotFoundException;
import com.ascencio.dev.springwebfluxapi.repository.ProgramsRepository;
import com.ascencio.dev.springwebfluxapi.service.ProgramsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ascencio.dev.springwebfluxapi.domain.mapper.ProgramsMapper.toModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProgramsServiceImpl implements ProgramsService {

    private final ProgramsRepository programsRepository;

    @Override
    public Mono<ProgramsResponseDto> findById(Integer id) {
        return this.programsRepository.findById(id)
                .map(ProgramsMapper::toDto);
    }

    @Override
    public Flux<ProgramsResponseDto> findAll() {
        return this.programsRepository.findAll()
                .filter(programa -> programa.getCondition().equals("A"))
                .map(ProgramsMapper::toDto);
    }

    @Override
    public Flux<ProgramsResponseDto> findInactive() {
        return this.programsRepository.findAll()
                .filter(programa -> programa.getCondition().equals("I"))
                .map(ProgramsMapper::toDto);
    }

    @Override
    public Mono<ProgramsResponseDto> create(ProgramsRequestDto request) {
        return this.programsRepository.save(toModel(request))
                .map(ProgramsMapper::toDto);
    }

    @Override
    public Mono<ProgramsResponseDto> update(Integer id, ProgramsRequestDto request) {
        return this.programsRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("El id : " + id + " no fue encontrado")))
                .flatMap(programs -> this.programsRepository.save(toModel(programs.getId(), request)))
                .map(ProgramsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return this.programsRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("El id : " + id + " no fue encontrado")))
                .flatMap(programa -> {
                    programa.setCondition("I");
                    return this.programsRepository.save(programa);
                })
                .doOnSuccess(unused -> log.info("Se elimino el programa con id: {}", id))
                .then();

    }

    @Override
    public Mono<ProgramsResponseDto> restore(Integer id) {
        return this.programsRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("El id : " + id + " no fue encontrado")))
                .flatMap(programa -> {
                    programa.setCondition("A"); // Cambia la condición a "A" (activo)
                    return this.programsRepository.save(programa);
                })
                .map(ProgramsMapper::toDto);
    }

}
