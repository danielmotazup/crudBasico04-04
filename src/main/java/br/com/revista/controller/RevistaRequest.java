package br.com.revista.controller;

import br.com.revista.model.Revista;
import br.com.revista.model.Tag;
import br.com.revista.repository.TagRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RevistaRequest {

    @NotNull
    @Length(max = 120)
    private String titulo;

    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;

    private Set<Long> idTags = new HashSet<>();

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Set<Long> getIdTags() {
        return idTags;
    }

    public Revista paraRevista(TagRepository repository){


        Set<Tag> tags = idTags.stream().map(idee -> repository.findById(idee).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND))).collect(Collectors.toSet());


        Revista revista = new Revista(titulo,dataPublicacao);
        revista.adicionar(tags);

        return revista;


    }
}
