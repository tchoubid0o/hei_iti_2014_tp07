package tp07.front.controller;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import tp07.front.data.Slide;

@ServerEndpoint(
		value = "/control",
		decoders={SlideDecoder.class},
		encoders={SlideEncoder.class})
public class RevealController {

	private Slide currentSlide;
	
	@OnOpen
	public void open(Session s){
		if(currentSlide!=null){
			send(s,currentSlide);
		}
	}
	
	
	@OnMessage
	public void slide(Slide slide, Session session) throws IOException {
		System.out.println("Message recu de "+session.getId());
		currentSlide=slide;
		for (Session s : session.getOpenSessions()) {
			if (s.isOpen() && !s.getId().equals(session.getId())) {
				System.out.println("Envoi à "+s.getId()+", suite au message de "+session.getId());
				send(s, slide);
			}
		}

	}

	private void send(Session session, Slide slide) {
		try {
			session.getBasicRemote().sendObject(slide);
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}
}
