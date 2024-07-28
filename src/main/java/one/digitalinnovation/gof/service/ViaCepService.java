package one.digitalinnovation.gof.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import one.digitalinnovation.gof.model.Endereco;

/**
 * 
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 * 
 * Em termos de arquitetura de software e comunicação entre sistemas, 
 * um "cliente" é um componente ou aplicação que consome serviços ou 
 * recursos fornecidos por um servidor. O cliente inicia solicitações 
 * ao servidor, que então processa essas solicitações e retorna as respostas.
 * 
 * @GetMapping("/{cep}/json/"): Define que este método faz uma 
 * solicitação HTTP GET para o endpoint /{cep}/json/. {cep} é 
 * um placeholder para o CEP que será passado como parâmetro.
 * 
 * @PathVariable("cep"): Anota o parâmetro do método cep, que 
 * será inserido na URL da solicitação.
 * 
 * Endereco consultarCep(String cep): Método que faz a 
 * solicitação HTTP e retorna um objeto Endereco.
 * 
 * Um exemplo completo de url para a requisição é:
 * https://viacep.com.br/ws/37540000/json/
 */
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @GetMapping("/{cep}/json/")
    Endereco consultarCep(@PathVariable("cep") String cep);
}
