package com.vinsys.app;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public abstract class BasePage extends WebPage{
	
	public BasePage() {
		
		//ModalWindow 
		// all the components in the base page
		Link tvLink = new Link("television") {
			@Override
			public void onClick() {
				setResponsePage(TeleVisionPage.class);
			}
		};
		
		Link movieLink = new Link("movies") {
			@Override
			public void onClick() {
				setResponsePage(MoviePage.class);
			}
		};
		
		Link eventLink = new Link("events") {
			@Override
			public void onClick() {
				setResponsePage(EventsPage.class);
			}
		};
		
		Link loginLink = new Link("login") {
			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		};
		
		this.add(eventLink);
		this.add(movieLink);
		this.add(tvLink);
		this.add(loginLink);
	}

}
