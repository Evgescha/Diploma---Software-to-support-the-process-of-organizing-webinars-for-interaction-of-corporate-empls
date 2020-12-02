package com.diplom.webinar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.diplom.webinar.entity.Role;
import com.diplom.webinar.entity.User;
import com.diplom.webinar.repository.UserRepository;

@Service
public class UserServiceImpl extends CrudImpl<User> {

	private final static String DEFAULT_ROLE = "ROLE_USER";

	@Autowired
	private RoleServiceImpl roleService;

	public UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public User findByUsername(String username) {
		return repository.findByUsernameIgnoreCase(username);
	}

//registration
	public boolean userRegistration(User entity) {

		if (repository.findByUsernameIgnoreCase(entity.getUsername()) != null)
			return false;
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		try {
			if (roleService.findByName(DEFAULT_ROLE) == null)
				roleService.create(new Role(DEFAULT_ROLE));
			// просто создание
			create(entity);

			// добавление роли и сохранение
			Role role = roleService.findByName(DEFAULT_ROLE);
			entity = repository.findByUsernameIgnoreCase(entity.getUsername());
			entity.getRoles().add(role);
			update(entity);

			// сохранение в списке ролей пользователя
			role.getUsers().add(entity);
			roleService.update(role);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
