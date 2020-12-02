package org.firstinspires.ftc.teamcode.killer_robot;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class StopSimpleRobot extends SimpleRobot {

    DistanceSensor distanceSensor;

    @Override
    protected void stopRobot() {
        sleep(5000);
        objectIsClose = true;

        /*
        double distance = distanceSensor.getDistance(DistanceUnit.CM);

        if(distance < 6.0){

        }

        
         */
    }

    @Override
    protected void initializeSensors() {
        super.initializeSensors();
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distance_sensor");


    }
}
