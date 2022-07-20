package br.com.revista.repository;

import br.com.revista.model.Revista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevistaRepository extends JpaRepository<Revista,Long> {
}
