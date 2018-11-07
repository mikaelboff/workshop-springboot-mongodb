package br.com.mikaelboff.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mikaelboff.workshopmongo.domain.User;
import br.com.mikaelboff.workshopmongo.dto.UserDTO;
import br.com.mikaelboff.workshopmongo.repositories.UserRepository;
import br.com.mikaelboff.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {
		return repo.save(user);
	}

	public void delete(String id) {
		User obj = this.findById(id);

		repo.deleteById(obj.getId());
	}

	public User update(User obj) {
		User newObj = this.findById(obj.getId());

		updateData(newObj, obj);

		return repo.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setNome(obj.getNome());
	}

	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getNome(), dto.getEmail());
	}
}
