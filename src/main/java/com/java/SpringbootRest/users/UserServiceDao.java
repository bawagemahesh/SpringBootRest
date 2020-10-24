package com.java.SpringbootRest.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserServiceDao {

	private static List<User> listOfUser = new ArrayList<>();

	private static int UserCount;
	static {
		listOfUser.add(new User("Mahesh", 101, new Date()));
		listOfUser.add(new User("Sayali", 102, new Date()));
		listOfUser.add(new User("Sony", 103, new Date()));
		listOfUser.add(new User("Kittu", 104, new Date()));
		listOfUser.add(new User("Fruitu", 105, new Date()));
	}

	public List<User> findAllUser() {
		return listOfUser;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++UserCount);
		}
		listOfUser.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : listOfUser) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = listOfUser.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
			}
			return user;
		}
		return null;
	}

}
