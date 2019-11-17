package com.vinsys.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.PropertyPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class LoginPage extends BasePage {
	
	public LoginPage() {
		
		// Modal Window to display Copyright page
		ModalWindow helpWindow = new ModalWindow("help");
		helpWindow.setPageCreator(new ModalWindow.PageCreator() {

			@Override
			public Page createPage() {
				
				return new CopyrightPage();
			}
			
		});
		helpWindow.setTitle(new Model("Help"));
		helpWindow.setOutputMarkupId(true);
		
		// if rendered using ajax AjaxLink is mandatory
		AjaxLink help = new AjaxLink("help_link") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				helpWindow.show(target);
			}			
		};
		Form loginForm = new Form("loginForm");
		User user = new User();
		Label usernameLabel = new Label("usernameLabel", "UserName");
		Label passwordLabel = new Label("passwordLabel", "Password");
		TextField usernameTextField = new TextField("usernameTextField", new PropertyModel(user, "userName"));
		usernameTextField.setRequired(true);
		usernameTextField.add(new UsernameValidator());
		TextField passwordTextField = new TextField("passwordTextField", new PropertyModel(user, "passWord"));
		passwordTextField.setRequired(true);
		Button loginButton = new Button("loginButton") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				getApplication().getSessionStore().setAttribute(getRequest(), "logged_in", "logged_in");
				setResponsePage(HomePage.class);
			}
		};
		
		FeedbackPanel feedbackUserName = new FeedbackPanel("feedbackUserName");
		
		loginForm.add(usernameLabel);
		loginForm.add(feedbackUserName);
		loginForm.add(passwordLabel);
		loginForm.add(usernameTextField);
		loginForm.add(passwordTextField);
		loginForm.add(loginButton);
		add(loginForm);
		add(helpWindow);
		add(help);
		
		// Paginated Repeatable View 
		IColumn[] columns = new IColumn[2];
		columns[0] = new PropertyColumn( new Model("Username"), "Username", "Username");
		columns[1] = new PropertyColumn( new Model("Password"), "Password", "Password");
		DefaultDataTable table = new DefaultDataTable("dataTable", columns, new UserDataProvider(), 10);
		add(table);
		
		//
		List<ICellPopulator<User>> columns = new ArrayList<>();
		columns.add(new PropertyPopulator<User>("Username"));
		columns.add(new PropertyPopulator<User>("Password"));
		DataGridView view = new DataGridView("rows", columns, new UserDataProvider());
		add(view);
	}

}
