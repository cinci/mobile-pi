/* Libraries */
var gpio = require("pi-gpio");

/* Set pin value */
function setPinValue(pin, value) {
    var pi4jPinNumber = pi4jPinMapper(pin);
    var pinValue = parseInt(value);

    console.log("Set pin: " + pi4jPinNumber + " to value: " + pinValue);

    gpio.open(pi4jPinNumber, "output", function(err) {
        gpio.write(pi4jPinNumber, pinValue, function() {
            gpio.close(pi4jPinNumber);
        });
    });

    return pi4jStateMapper(pinValue);
}

/* Get pin value */
function getPinValue(pin, readCallback) {
    var pi4jPinNumber = pi4jPinMapper(pin);

    console.log("Get pin: " + pi4jPinNumber);

    gpio.open(pi4jPinNumber, "output", function(err) {
        gpio.read(pi4jPinNumber, function(err, value) {
            readCallback(pi4jStateMapper(value));
            gpio.close(pi4jPinNumber);
        });
    });
}

function pi4jStateMapper(value) {
    return value == 1 ? "HIGH" : "LOW";
}

function pi4jPinMapper(pin) {
    pin = parseInt(pin);

    switch(pin) {
        case 0:
            return 11;
        case 1:
            return 12;
        case 2:
            return 13;
        case 3:
            return 15;
        case 4:
            return 16;
        case 5:
            return 18;
        case 6:
            return 22;
        case 7:
            return 7;
        default:
            return -1;
    }
}

/* Exports */
module.exports.setPinValue = setPinValue;
module.exports.getPinValue = getPinValue;
