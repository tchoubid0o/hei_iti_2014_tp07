var url = window.location.origin+window.location.pathname;
url = url.replace("http","ws")
var urlParts = url.split("/");
urlParts.pop();
var newUrl = urlParts.join("/")+"/control";


var ws = new WebSocket(newUrl);

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
