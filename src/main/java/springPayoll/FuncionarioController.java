package springPayoll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuncionarioController {

	private final FuncionarioRepository funcionarioRepository;
	
	FuncionarioController(FuncionarioRepository funcionarioRepository){
		this.funcionarioRepository = funcionarioRepository;
	}
	
	//Localização Padrão
	/*
	 * @GetMapping("/funcionario") public List<Funcionario> listarFuncionario(){
	 * return funcionarioRepository.findAll(); }
	 */
	
	@GetMapping("/funcionario")
	public CollectionModel<EntityModel<Funcionario>> listarFuncionario(){
		
		List<EntityModel<Funcionario>> listaFuncionarios = funcionarioRepository.findAll().stream().map(funcionario -> //
			EntityModel.of(funcionario, 
					linkTo(FuncionarioController.class).slash("/funcionario").slash(funcionario.getId()).withSelfRel(),
					linkTo(FuncionarioController.class).withRel("funcionario"))).collect(Collectors.toList());
		
		return CollectionModel.of(listaFuncionarios,
				linkTo(FuncionarioController.class).slash("/funcionario").withSelfRel());
	
	}
	
	
	@PostMapping("/funcionario")
	public Funcionario salvarFuncionario(@RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	//Especificos
	
	@GetMapping("/funcionario/{id}")
	public EntityModel<Funcionario> buscarFuncionario(@PathVariable Long id) {
		
		Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioNaoEncontradoException(id));
		
		 return EntityModel.of(funcionario,
				 linkTo(FuncionarioController.class).slash("/funcionario").slash(funcionario.getId()).withSelfRel(),
				 linkTo(FuncionarioController.class).slash("/funcionario").withRel("funcionario"));
		 		
	}

	@PutMapping("/funcionario/{id}")
	public Funcionario substituiFuncionario(@RequestBody Funcionario novoFuncionario, @PathVariable Long id) {
		
		return funcionarioRepository.findById(id).map(funcionario -> {
			funcionario.setNome(novoFuncionario.getNome());
			funcionario.setFuncao(novoFuncionario.getFuncao());
			return funcionarioRepository.save(funcionario);
		}).orElseGet(() ->{ novoFuncionario.setId(id);
			return funcionarioRepository.save(novoFuncionario);});
	}
	
	@DeleteMapping("/funcionario/{id}")
	public void deletarFuncionario(@PathVariable Long id) {
		funcionarioRepository.deleteById(id);
	}
	
}
