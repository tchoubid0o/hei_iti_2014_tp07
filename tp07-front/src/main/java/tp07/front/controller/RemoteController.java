package tp07.front.controller;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import tp07.front.data.Singleton;
import tp07.front.data.Slide;

@ServerEndpoint(
			value="/control",
			decoders = {SlideDecoder.class},
			encoders = {SlideEncoder.class})
public class RemoteController {
    @OnMessage
    public void slide(Slide slide, Session session) throws IOException {
    	System.out.println("Message recu de "+session.getId());
    	for (Session s : session.getOpenSessions()) {
    		if (s.isOpen() && !s.getId().equals(session.getId())) {
    			System.out.println("Envoi à "+s.getId()+", suite au message de "+session.getId());
    			send(s, slide);
    			Singleton.getInstance().setSlide(slide);
    		}
    	}
    }
    private void send(Session session, Slide slide) {
    	try {
    		session.getBasicRemote().sendObject(slide);
    	} catch(Exception ex) {
    	}
    }
    
    @OnOpen
    public void initSlide(Session session){
    	send(session, Singleton.getInstance().getSlide());
    }
}
