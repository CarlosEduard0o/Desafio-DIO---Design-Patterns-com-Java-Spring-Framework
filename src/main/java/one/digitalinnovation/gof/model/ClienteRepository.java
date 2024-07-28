package one.digitalinnovation.gof.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * CrudRepository é a interface base do Spring Data JPA para operações CRUD 
 * (Create, Read, Update, Delete). Ela fornece um conjunto básico de métodos 
 * para essas operações. Aqui estão alguns dos métodos principais oferecidos 
 * pelo CrudRepository:
 * save(S entity): Salva a entidade fornecida.
 * findById(ID id): Encontra uma entidade pelo seu ID.
 * findAll(): Retorna todas as entidades.
 * deleteById(ID id): Exclui a entidade pelo seu ID.
 * delete(S entity): Exclui a entidade fornecida.
 * count(): Retorna a contagem total de entidades.
 * 
 * Essa interface me oferece métodos para realizar operações
 * CRUD, graça ao extends CrudRepository.
 * 
 * <Cliente, Long> indica o tipo da entidade que este repositório gerencia 
 * e o tipo da chave primária (ID) dessa entidade.
 */
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}