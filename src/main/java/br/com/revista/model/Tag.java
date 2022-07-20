package br.com.revista.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String nome;

    @ManyToMany(mappedBy = "tags")
    private Set<Revista> revistas = new HashSet<>();

    @Deprecated
    public Tag() {
    }

    public Tag(String nome) {
        this.nome = nome;
    }

    public void adicionarRevista(Revista revista){
        this.revistas.add(revista);

    }

    public Long getId() {
        return id;
    }
}
