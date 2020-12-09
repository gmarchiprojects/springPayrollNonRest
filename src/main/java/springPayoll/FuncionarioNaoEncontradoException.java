package springPayoll;

public class FuncionarioNaoEncontradoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1736169727748208695L;

	public FuncionarioNaoEncontradoException(Long id) {
		super("O Funcionario não pode ser encontrado "+id);
	}

}
