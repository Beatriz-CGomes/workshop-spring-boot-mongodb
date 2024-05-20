package com.nelioalves.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.AuthorDTO;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		// deletando a coleção lá no mongodb
		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		// salvando no banco de dados
		// salvandos os usuarios primeira para a classe author pegar a copia dos objetos
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2024"), "Partiu viagem", "Vou viajar para SP abraços",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2024"), "Bom dia", "Acordei Feliz!", new AuthorDTO(maria));

		// salvando no banco de dados
		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPost().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);

	}

}
