package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Cliente;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 * 
 */
public interface ClienteService {

    // Iterable<Cliente>: O tipo de retorno Iterable indica que a 
    // implementação do método retornará uma coleção de objetos do 
    // tipo Cliente, permitindo a iteração sobre esses objetos.

    Iterable<Cliente> buscarTodos();

	Cliente buscarPorId(Long id);

	void inserir(Cliente cliente);

	void atualizar(Long id, Cliente cliente);

	void deletar(Long id);

}
