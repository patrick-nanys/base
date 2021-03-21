package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrainSensorTest {

    TrainSensorImpl sensor;
    TrainController controller;

    @Before
    public void before() {
        controller = new TrainController() {
            @Override
            public void followSpeed() {}

            @Override
            public int getReferenceSpeed() { return 0; }

            @Override
            public void setSpeedLimit(int speedLimit) {}

            @Override
            public void setJoystickPosition(int joystickPosition) {}
        };
        sensor = new TrainSensorImpl(controller, null);
    }

    @Test
    public void testDefaultSensorValue() {
        assertEquals(10, sensor.getSpeedLimit());
    }

    @Test
    public void testSettingSensorValue() {
        sensor.overrideSpeedLimit(12);
        assertEquals(12, sensor.getSpeedLimit());
    }
}
