package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {
	@Autowired
	private EnderecoRepositorio repositorio;
	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@GetMapping("/endereco/{id}")
	public Endereco obterEndereco(@PathVariable long id) {
		return repositorio.getById(id);
	}

	@GetMapping("/enderecos")
	public List<Endereco> obterEnderecos() {
		return repositorio.findAll();
	}

	@PostMapping("/cadastro/{clienteId}")
	public void cadastrarEndereco(@PathVariable long clienteId, @RequestBody Endereco endereco) {
		Cliente cliente = clienteRepositorio.getById(clienteId);
		cliente.setEndereco(endereco);
		clienteRepositorio.save(cliente);
	}

	@PutMapping("/atualizar")
	public void atualizarEndereco(@RequestBody Endereco atualizacao) {
		Endereco endereco = repositorio.getById(atualizacao.getId());
		EnderecoAtualizador atualizador = new EnderecoAtualizador();
		atualizador.atualizar(endereco, atualizacao);
		repositorio.save(endereco);
	}

	@DeleteMapping("/excluir/{clienteId}")
	public void excluirEndereco(@PathVariable long clienteId) {
		Cliente cliente = clienteRepositorio.getById(clienteId);
		cliente.setEndereco(null);
		clienteRepositorio.save(cliente);
	}
}