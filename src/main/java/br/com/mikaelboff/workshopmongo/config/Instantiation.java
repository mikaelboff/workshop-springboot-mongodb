package br.com.mikaelboff.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.mikaelboff.workshopmongo.domain.Post;
import br.com.mikaelboff.workshopmongo.domain.User;
import br.com.mikaelboff.workshopmongo.dto.AuthorDTO;
import br.com.mikaelboff.workshopmongo.repositories.PostRepository;
import br.com.mikaelboff.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;

	@Override
	public void run(String... args) throws Exception {
		userRepo.deleteAll();
		postRepo.deleteAll();
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepo.saveAll(Arrays.asList(maria, alex, bob));

		Post post1= new Post(null,sdf.parse("21/03/2018"), "Partiu viagem","Vou viajar para  São Paulo. Abraços!",new AuthorDTO(maria));
		Post post2= new Post(null,sdf.parse("23/03/2018"), "Bom dia","Acordei feliz hoje!",new AuthorDTO(maria));
		
		postRepo.saveAll(Arrays.asList(post1,post2));
	}

}