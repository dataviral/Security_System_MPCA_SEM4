var express = require("express");
var bodyParser = require('body-parser');
var PythonShell = require('python-shell');
var spawn = require("child_process").spawn;

var app = express();


app.use(bodyParser.urlencoded());
app.use(bodyParser.json());

app.post('/the',function(req, res) {
    console.log(req);
    // var process = spawn('python',["rpi_gpio.py", req.body.angle]);
});

app.listen(3031)
