package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainUser user;
    TrainController controller;
    TrainSensorImpl sensor;

    @Before
    public void before() {
        user = mock(TrainUser.class);
        controller = mock(TrainController.class);
        sensor = new TrainSensorImpl(controller, user);
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

    @Test
    public void testSpeedLimitLessThanAbsoluteMinimum() {
        sensor.overrideSpeedLimit(-1);
        verify(user, times(2)).setAlarmState(true);
    }

    @Test
    public void testSpeedLimitMoreThanAbsoluteMaximum() {
        sensor.overrideSpeedLimit(1000);
        verify(user, times(2)).setAlarmState(true);
    }

    @Test
    public void testSpeedLimitDecreasesALot() {
        sensor.overrideSpeedLimit(2);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void testSpeedLimitIncreasesALot() {
        sensor.overrideSpeedLimit(20);
        verify(user, times(1)).setAlarmState(true);
    }
}
