package pi4droid.client.app.async;

import pi4droid.client.app.AsyncConstants;

/**
 * GPIO request for pin state
 * <p/>
 * Created by jc on 4. 7. 2014.
 */
public class GpioRequestAsyncTask extends RequestAsyncTask {

    public GpioRequestAsyncTask(AsyncCallback asyncCallback) {
        super(asyncCallback);
    }

    public void setPinNumber(Integer pinNumber) {
        result.put(AsyncConstants.PIN_NUMBER, pinNumber.toString());
    }
}
