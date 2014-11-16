var ws = new WebSocket("ws://localhost:8080/tp07-front/control");
var notifyServer = function(event) {
	data = {
		indexv : Reveal.getIndices().v,
		indexh : Reveal.getIndices().h,
		indexf : Reveal.getIndices().f || 0
	}
	ws.send(JSON.stringify(data));
}
Reveal.addEventListener("slidechanged", notifyServer);
Reveal.addEventListener("fragmentshown", notifyServer);
Reveal.addEventListener("fragmenthidden", notifyServer);

ws.onopen = function(){
    console.log("Connecte en Websockets !");
};

ws.onmessage = function (evt){ 
    var slide = JSON.parse(evt.data);
    console.log("Message is received...",slide );
};

ws.onclose = function(){ 
 console.log("Connection is closed..."); 
};
