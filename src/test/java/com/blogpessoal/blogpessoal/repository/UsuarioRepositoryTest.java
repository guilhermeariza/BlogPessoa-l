package com.blogpessoal.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.blogpessoal.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();
		usuarioRepository.save(new Usuario(0L, "Zé Colmeia", "roba_lanche@email.com", "123456119", "https://image1.com/seilaonde.jpg"));
		usuarioRepository.save(new Usuario(0L, "Luana Tenguan", "luana_tenguan@email.com", "123400789", "https://image1.com/luana.jpg"));
		usuarioRepository.save(new Usuario(0L, "Guilherme Carlos", "fuba_cremoso@email.com", "123433789", "https://image1.com/toesperando.jpg"));
		usuarioRepository.save(new Usuario(0L, "Jefferson da Silva Souza", "jefferson_souza@email.com", "567456789", "https://image1.com/jefferson.jpg"));

	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario(){
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("luana_tenguan@email.com");
		assertTrue(usuario.get().getUsuario().equals("luana_tenguan@email.com"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Zé Colmeia");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Zé Colmeia"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Luana Tenguan"));
		assertTrue(listaDeUsuarios.get(3).getNome().equals("Guilherme Carlos"));

	}

}
