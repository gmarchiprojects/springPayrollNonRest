package springPayoll;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Funcionario {

	private @Id @GeneratedValue Long  id;
	
	private String nome;
	
	private String funcao;

	public Funcionario(){}
	
	public Funcionario(String nome, String funcao){
		this.nome = nome;
		this.funcao = funcao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(this ==  o) {
			return true;
		}
		
		if(!(o instanceof Object)) {
			return false;
		}
		
		Funcionario funcionario = (Funcionario) o;
		
		return Objects.equals(this.id, funcionario.id) && Objects.equals(this.nome,funcionario.nome) && Objects.equals(this.funcao,funcionario.funcao);
		
			}
	@Override
	public int hashCode() {
		return Objects.hash(this.id,this.nome,this.funcao);
	}
	
	public String toString() {
		return "Funcionario{" + "id=" + this.id + ", nome='" + this.nome + '\'' + ", função='" + this.funcao + '\'' + '}';
	}
}
