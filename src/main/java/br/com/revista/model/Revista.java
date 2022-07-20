package br.com.revista.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Revista {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 120)
    @Length(max = 120)
    private String titulo;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past
    private LocalDate dataPublicacao;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Tag> tags = new HashSet<>();

    @Deprecated
    public Revista() {
    }

    public Revista(String titulo, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
    }

    public void adicionar(Set<Tag> novasTags){
        tags.addAll(novasTags);
        novasTags.forEach(e->e.adicionarRevista(this));

    }

    public Long getId() {
        return id;
    }
}
