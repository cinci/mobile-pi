/* Libraries */
var fs = require('fs');
var gpio = require('./gpio.js');

/* Index handler methods */
function indexHandler() {
    this.res.writeHeader(200, {'Content-Type': 'text/html'})

    var indexFile = fs.readFileSync("./index.html", 'utf8');

    this.res.write(indexFile);
    this.res.end();
}

/* Set pin value handler */
function setPinValueHandler(pin, value) {
    this.res.writeHead(200, {'Content-Type': 'text/plain'})

    var realValue = gpio.setPinValue(pin, value);

    this.res.end(realValue);
}

/* Get pin value handler */
function getPinValueHandler(pin) {
    this.res.writeHead(200, {'Content-Type': 'text/plain'})

    var response = this.res;
    var a = gpio.getPinValue(pin, function(pinState) {
        response.end(pinState);
    });
}

/* Exports */
module.exports.indexHandler = indexHandler;
module.exports.setPinValueHandler = setPinValueHandler;
module.exports.getPinValueHandler = getPinValueHandler;
