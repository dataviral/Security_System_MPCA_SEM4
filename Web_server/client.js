var express = require("express");
var bodyParser = require('body-parser');
var PythonShell = require('python-shell');
var spawn = require("child_process").spawn;
//
// var app = express();
//
//
// app.use(bodyParser.urlencoded());
// app.use(bodyParser.json());
//
// app.get('/',function(req, res) {
//     console.log(req.body);
//     // var process = spawn('python',["rpi_gpio.py", req.body.angle]);
// });
//
// app.listen(3031)
op = "";
var process = spawn('python',["../rpi_gpio.py", 10]);
process.stdout.on("data", function(data) {
    op += data;
});

process.on("close", function(data) {
    p(op);
});

function p(data){
    console.log(data);
}
