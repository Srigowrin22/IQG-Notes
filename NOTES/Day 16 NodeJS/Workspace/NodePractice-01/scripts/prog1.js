var http = require('http');
http.createServer(function(req, res){
    res.writeHead(200, {'Content-type': 'text/html'});
    var dobj = new Date();
    res.end("<h2> Welcome From Http Server from Node , The current date and time is: " + dobj + "</h2>") ;
}).listen(9999);
