var http = require('http');

var server = http.createServer(function(req,res){
    if(req.url == '/data') {
        res.writeHead(200, {'Content-type' : 'application/json'});
        res.write(JSON.stringify({message: "HelloWorld"}));
        res.end();
    }
});
server.listen(9000);
console.log("Completed");