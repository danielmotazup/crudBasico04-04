package br.com.revista.controller;

import br.com.revista.model.Revista;
import br.com.revista.model.Tag;
import br.com.revista.repository.RevistaRepository;
import br.com.revista.repository.TagRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/revistas")
public class NovaRevistaController {

    private final RevistaRepository revistaRepository;
    private final TagRepository tagRepository;

    public NovaRevistaController(RevistaRepository revistaRepository, TagRepository tagRepository) {
        this.revistaRepository = revistaRepository;
        this.tagRepository = tagRepository;
    }

    @PostMapping
    public ResponseEntity<Void> cadastraRevista(@RequestBody @Valid RevistaRequest request, UriComponentsBuilder uriBuilder){

        Revista revista = request.paraRevista(tagRepository);

        revistaRepository.save(revista);

        URI location = uriBuilder.path("/revistas/{id}").buildAndExpand(revista.getId()).toUri();

        return ResponseEntity.created(location).build();




    }
}
