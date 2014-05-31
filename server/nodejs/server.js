/*
    DEPENDENCIES:
    https://github.com/rakeshpai/pi-gpio
    https://github.com/quick2wire/quick2wire-gpio-admin
*/

/* Libraries */
var http = require('http');
var director = require('director');
var handlers = require('./handlers.js');

/* Server definition */
var router = new director.http.Router();

var server = http.createServer(function (req, res) {
    router.dispatch(req, res, function (err) {
        if (err) {
            res.writeHead(404);
            res.end();
        }
    });
});

/* Handles definition */
router.get('/index', handlers.indexHandler);
router.get('/set/pin/:pin/value/:value', handlers.setPinValueHandler);
router.get('/get/pin/:pin', handlers.getPinValueHandler);

/* Start server */
server.listen(8080);
console.log('Server running!');
