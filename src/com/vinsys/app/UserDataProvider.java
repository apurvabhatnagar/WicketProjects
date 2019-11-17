package com.vinsys.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class UserDataProvider extends SortableDataProvider<User> {
	
	private List<User> userList = new ArrayList();

	public UserDataProvider() {
		for(int i=0; i<100; i++) {
			User user = new User();
			user.setUserName("user" + i);
			user.setPassWord("pass" + i);
			userList .add(user);
		}
	}

	@Override
	public Iterator<? extends User> iterator(int first, int count) {
		List<User> newList = new ArrayList<User>(userList);
		return newList.subList(first, first+count).iterator();
	}

	@Override
	public IModel<User> model(User user) {
		return new LoadableDetachableModel<User>() {

			@Override
			protected User load() {
				// TODO Auto-generated method stub
				return user;
			}

		};
	}

	@Override
	public int size() {
		return userList.size();
	}

}
