package br.com.mikaelboff.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mikaelboff.workshopmongo.domain.Post;
import br.com.mikaelboff.workshopmongo.repositories.PostRepository;
import br.com.mikaelboff.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<Post> findByTitulo(String texto) {
		// return repo.findByTitle(texto);
		return repo.findByTituloContainingIgnoreCase(texto);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);

		return repo.fullSearch(text, minDate, maxDate);
	}

}
