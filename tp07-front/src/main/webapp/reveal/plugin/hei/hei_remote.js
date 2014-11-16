var ws = new WebSocket("ws://localhost:8080/tp07-front/control");


ws.onopen = function(){
    console.log("Connecte en Websockets !");
};

ws.onmessage = function (evt){ 
    var slide = JSON.parse(evt.data);
    console.log("Message is received...",slide );
    Reveal.slide(slide.indexh, slide.indexv, slide.indexf);
};

ws.onclose = function(){ 
 console.log("Connection is closed..."); 
};
