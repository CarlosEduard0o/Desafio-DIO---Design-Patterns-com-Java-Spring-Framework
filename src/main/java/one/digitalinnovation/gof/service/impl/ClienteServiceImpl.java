package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
        // Buscar todos os Clientes.
        return clienteRepository.findAll();
    }

    // Optional é uma classe do Java 8 que representa um
    // contêiner que pode ou não conter um valor não nulo.
    // Usar Optional ajuda a lidar com valores nulos de 
    // forma mais segura, evitando NullPointerException.
    //  Optional<Cliente> indica que o contêiner pode conter um
    // objeto Cliente ou estar vazio.
    @Override
    public Cliente buscarPorId(Long id) {
        // Buscar Cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Buscar Cliente por ID, caso exista:
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar Cliente por ID.
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        
        // A linha completa faz o seguinte:
        // Busca no Repositório: Tenta encontrar um endereço no repositório local usando o CEP fornecido.
        // Se Encontrado: Se o endereço for encontrado, ele é retornado.
        // Se Não Encontrado: Se o endereço não for encontrado:
        // Consulta a API do ViaCEP para obter o endereço correspondente ao CEP.
        // Salva o novo endereço obtido no repositório.
        // Retorna o novo endereço.
        
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            System.out.println("BAIRRO: " + novoEndereco.getBairro());
            System.out.println("CEP: " + novoEndereco.getCep());
            System.out.println("COMPLEMENTO: " + novoEndereco.getComplemento());
            System.out.println("DDD: " + novoEndereco.getDdd());
            System.out.println("GUIA: " + novoEndereco.getGia());
            System.out.println("IBGE: " + novoEndereco.getIbge());
            System.out.println("LOCALIDADE: " + novoEndereco.getLocalidade());
            System.out.println("LOGRADOURO: " + novoEndereco.getLogradouro());
            System.out.println("SIAFI: " + novoEndereco.getSiafi());
            System.out.println("UF: " + novoEndereco.getUf());
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }

}
