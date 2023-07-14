package curso.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

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
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Usuario> init(@PathVariable (value = "id") Long id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> usuario() {
		
		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		
		for (int pos = 0; pos < usuario.getTelefones().size(); pos++) {
			usuario.getTelefones().get(pos).setUsuario(usuario);
		}
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	@PutMapping(value = "/", produces = "application/json" )
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete (@PathVariable("id") Long id) {
		
		usuarioRepository.deleteById(id);
		
		return (ResponseEntity) ResponseEntity.ok();
	}
	
}
