package curso.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;

@RestController
@RequestMapping(value = "/usuario")
public class IndexController {

	/**
	 * Este método manipula solicitações GET para a rota "/".
	 * O método init() pega os parâmetros "nome" e "salario" da solicitação HTTP e retorna uma resposta.
	 * 
	 * @param nome é um parâmetro requerido para a solicitação. Se não for fornecido, será usado o valor padrão 'Nome não informado'.
	 * @param salario é um parâmetro opcional para a solicitação.
	 * @return ResponseEntity que contém a mensagem com o nome e o salário, junto com o status HTTP 200 (OK).
	 
	@GetMapping(value = "/", produces = "aplication/json")
	public ResponseEntity init(
			@RequestParam (value = "nome", required = true, defaultValue = "Nome não informado") String nome,
			@RequestParam (value = "salario") Long salario 
			) {
		
		// Imprime o valor do parâmetro 'nome' na saída padrão (console).
		System.out.println("Parametro: " + nome);
		
		// Cria uma nova ResponseEntity com uma mensagem contendo o 'nome' e 'salario',
		// juntamente com o status HTTP OK (200).
		return new ResponseEntity("Seu nome é: " + nome + " salário é: " + salario, HttpStatus.OK);
	}
	*/
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> init() {
		
		Usuario usuario = new Usuario();
		usuario.setId(50L);
		usuario.setLogin("victor@gmail.com");
		usuario.setNome("Víctor");
		usuario.setSenha("123456");
		
		// Cria uma nova ResponseEntity com uma mensagem contendo o 'nome' e 'salario',
		// juntamente com o status HTTP OK (200).
		return ResponseEntity.ok(usuario);
	}
}
