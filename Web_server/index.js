var express = require('express');
var bodyParser = require('body-parser');
var PythonShell = require('python-shell');
var fs = require('fs');
var app = express();
var http = require("http");

var usernames = {}

var spawn = require("child_process").spawn;
var process = spawn('python2',["../door_lock.py"]);

app.use(bodyParser.urlencoded());
app.use(bodyParser.json());

imageUrl = '/image';

app.use(express.static(__dirname + '/image'));

app.post('/', function (req, res) {

reset(req.body.username, usernames[req.body.username]);
console.log("inccoming request, ", req.body);
 u_name = req.body.username
    var options = {
        mode: 'text',
        pythonOptions: ['-u'],
        scriptPath: '../',
        args: [u_name]
    };


    PythonShell.run('qr_generator.py', options, function(err, results){

        if(err) throw err;
        console.log("results: %j", results);
        usernames[req.body.username] = {};
        usernames[req.body.username]["key"] = results[0];
        usernames[req.body.username]["location"] = req.body.location;
        image_name = "/"+ results[0] + ".png";
        setTimeout(reset, 8000, req.body.username, results[0]);
        res.end(JSON.stringify({"image" : image_name}));

    });

});


app.listen(3030, function() {
    console.log('Express App Running at port 3030 ');
});


var options = {
    host: '192.168.43.124',
    port: 3031,
    method: 'POST',
    path: '/the',
    headers: {
        'Content-Type': 'application/json',
    }
};

process.stdout.on('data', function(data){

    data = data.toString('utf-8').slice(0,-1);
    var username = data.match(/[a-z]*/)[0];

    if(usernames[username] && data === usernames[username]["key"]){

        console.log("Accessing...., username :",username, "  key :",data );
        var req = http.request(options, function(res) {
            console.log("\t****Access Granted*****\n");
        });
        req.on("error", function(err){
            console.log("Error occured while communicating with the Pi ");
        });
        req.write(JSON.stringify({angle:90,location:usernames[username]["location"]}));
        req.end();

        reset(username, data);

    } else {
        console.log(`\n-----------The QR-Code is Invalid or has Expired !!-----------\n`);
    }

});

process.on('SIGINT', function() {
    console.log('SIGINT');
    process.exit();
});

function reset(username, data){
    console.log("Reseting :", username, " ", data);
    usernames[username] = null;
    fs.unlink(`./image/${data}.png`, function(err) {
        console.log(err);
    });
}
