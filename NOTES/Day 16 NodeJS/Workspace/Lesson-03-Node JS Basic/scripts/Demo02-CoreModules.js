var http = require('http');

var server = http.createServer(function(rer, res){
    res.head(200, {'Content-Type' : 'text/html'});
    res.end("Hello from server");
});

server.listen(7777);