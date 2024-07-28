package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;

/**
 * 
 * Essa classe é um controlador REST no contexto de uma aplicação Spring Boot. 
 * Este controlador, ClienteRestController, gerencia as operações CRUD (Create,
 * Read, Update, Delete) para a entidade Cliente. Vamos analisar o código em detalhes.
 * 
 * @ RestController: Indica que esta classe é um controlador REST, onde cada 
 * método lida com uma solicitação HTTP.
 * 
 * @ RequestMapping("clientes"): Define a rota base para todas as solicitações 
 * deste controlador como /clientes.
 * 
 * @ Autowired: Injeta a dependência ClienteService, que é responsável pelas 
 * operações de negócios relacionadas ao Cliente.
 * 
 * O controlador ClienteRestController fornece uma interface RESTful para gerenciar 
 * clientes, abstraindo a lógica de negócios e integração com banco de dados e APIs 
 * externas através do serviço ClienteService.
 */
@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    // Encapsular o retorno em um ResponseEntity oferece várias vantagens 
    // em uma aplicação Spring RESTful. Vamos explorar os principais motivos 
    // e benefícios dessa prática.

    // 1. Controle Completo sobre a Resposta HTTP:
    //     - Status HTTP: Permite definir o status HTTP da resposta, como 200 OK, 404 Not Found, 201 Created, etc.
    //     - Cabeçalhos HTTP: Permite adicionar cabeçalhos HTTP personalizados à resposta.
    //     - Corpo da Resposta: Permite retornar o corpo da resposta, que pode ser um objeto, uma lista de objetos, ou mesmo vazio (no caso de uma operação de exclusão bem-sucedida).
    
    // 2. Consistência e Clareza:
    //     - Encapsular o retorno em um ResponseEntity torna explícito que estamos retornando uma resposta 
    //       HTTP completa, incluindo status, cabeçalhos e corpo, o que facilita a leitura e manutenção do código.
    
    // 3. Facilidade de Manipulação de Erros:
    //     - Permite retornar mensagens de erro apropriadas com status HTTP correspondentes, como 400 Bad Request, 
    //       404 Not Found, 500 Internal Server Error, etc.
    
    // 4. Personalização e Extensibilidade:
    //     - Podemos criar respostas personalizadas, por exemplo, retornando um ResponseEntity com cabeçalhos de cache, 
    //       redirecionamentos, ou outros metadados HTTP específicos.

	@Autowired
	private ClienteService clienteService;

    /**
     * @GetMapping: Mapeia solicitações HTTP GET para este método.
     * @return Retorna uma lista de todos os clientes encapsulada em um ResponseEntity com status HTTP 200 (OK).
     */
	@GetMapping
	public ResponseEntity<Iterable<Cliente>> buscarTodos() {
		return ResponseEntity.ok(clienteService.buscarTodos());
	}

    /**
     * @GetMapping("/{id}"): Mapeia solicitações HTTP GET para o endpoint /clientes/{id}.
     * @PathVariable Long id: Vincula o valor do {id} da URL ao parâmetro id do método.
     * @param id
     * @return Retorna o cliente correspondente ao ID fornecido, encapsulado em um ResponseEntity com status HTTP 200 (OK).
     */
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.buscarPorId(id));
	}

    /**
     * @PostMapping: Mapeia solicitações HTTP POST para este método.
     * @RequestBody Cliente cliente: Vincula o corpo da solicitação HTTP ao parâmetro cliente do método.
     * @param cliente
     * @return Retorna o cliente recém-criado, encapsulado em um ResponseEntity com status HTTP 200 (OK).
     */
	@PostMapping
	public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
		clienteService.inserir(cliente);
		return ResponseEntity.ok(cliente);
	}

    /**
     * @PutMapping("/{id}"): Mapeia solicitações HTTP PUT para o endpoint /clientes/{id}.
     * @PathVariable Long id: Vincula o valor do {id} da URL ao parâmetro id do método.
     * @RequestBody Cliente cliente: Vincula o corpo da solicitação HTTP ao parâmetro cliente do método.
     * @param id
     * @param cliente
     * @return Retorna o cliente atualizado, encapsulado em um ResponseEntity com status HTTP 200 (OK).
     */
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(cliente);
    }

    /**
     * @DeleteMapping("/{id}"): Mapeia solicitações HTTP DELETE para o endpoint /clientes/{id}.
     * @PathVariable Long id: Vincula o valor do {id} da URL ao parâmetro id do método.
     * @param id
     * @return Retorna um ResponseEntity com status HTTP 200 (OK), sem corpo, indicando que a operação foi bem-sucedida.
     */
    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.ok().build();
	}

}
