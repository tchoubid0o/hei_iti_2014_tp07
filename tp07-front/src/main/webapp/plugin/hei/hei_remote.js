var url = window.location.origin+window.location.pathname;
url = url.replace("http","ws")
var urlParts = url.split("/");
urlParts.pop();
var newUrl = urlParts.join("/")+"/control";


var ws = new WebSocket(newUrl);


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
