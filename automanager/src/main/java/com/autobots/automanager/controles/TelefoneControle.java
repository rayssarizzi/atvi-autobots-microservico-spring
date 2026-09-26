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
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
	@Autowired
	private TelefoneRepositorio repositorio;
	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@GetMapping("/telefone/{id}")
	public Telefone obterTelefone(@PathVariable long id) {
		return repositorio.getById(id);
	}

	@GetMapping("/telefones")
	public List<Telefone> obterTelefones() {
		return repositorio.findAll();
	}

	@PostMapping("/cadastro/{clienteId}")
	public void cadastrarTelefone(@PathVariable long clienteId, @RequestBody Telefone telefone) {
		Cliente cliente = clienteRepositorio.getById(clienteId);
		cliente.getTelefones().add(telefone);
		clienteRepositorio.save(cliente);
	}

	@PutMapping("/atualizar")
	public void atualizarTelefone(@RequestBody Telefone atualizacao) {
		Telefone telefone = repositorio.getById(atualizacao.getId());
		TelefoneAtualizador atualizador = new TelefoneAtualizador();
		atualizador.atualizar(telefone, atualizacao);
		repositorio.save(telefone);
	}

	@DeleteMapping("/excluir/{clienteId}")
	public void excluirTelefone(@PathVariable long clienteId, @RequestBody Telefone exclusao) {
		Cliente cliente = clienteRepositorio.getById(clienteId);
		cliente.getTelefones().removeIf(t -> t.getId() == exclusao.getId());
		clienteRepositorio.save(cliente);
	}
}