package org.firstinspires.ftc.teamcode.killer_robot;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Models.Eye;


@TeleOp(name = "SimpleRobot Base Class", group = "Linear Opmode")
@Disabled
public class SimpleRobot extends LinearOpMode {

    String SENSOR_TAG = "Sensors";
    String HARDWARE_TAG = "Hardware";

    Eye eye;

    DcMotor leftDrive;
    DcMotor rightDrive;
    Servo leftShoulder;
    Servo rightShoulder;
    private double MOTOR_POWER = 0.5;
    private DigitalChannel touchSensor;
    private DistanceSensor distanceSensor;


    boolean baseHardwareInitialized = false;

    @Override
    public void runOpMode() throws InterruptedException {
        initializeBaseHardware();
        initializeSensors();

        waitForStart();

        while (opModeIsActive()) {

        }

    }

    private final void initializeBaseHardware() {

        try {
            leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
            rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
            leftShoulder = hardwareMap.get(Servo.class, "left_shoulder");
            rightShoulder = hardwareMap.get(Servo.class, "right_shoulder");
            baseHardwareInitialized = true;
            telemetry.addData(HARDWARE_TAG, "Hardware setup");
            telemetry.update();
            sleep(1000);
        } catch (IllegalArgumentException e) {
            baseHardwareInitialized = false;
            telemetry.addData(HARDWARE_TAG, "Error with hardware");
            telemetry.update();
            sleep(1000);


        }

    }

    private final void initializeSensors() {

        try {
            touchSensor = hardwareMap.get(DigitalChannel.class, "touch_sensor");
            touchSensor.setMode(DigitalChannel.Mode.INPUT);

            distanceSensor = hardwareMap.get(DistanceSensor.class, "distance_sensor");


            NormalizedColorSensor colorSensor = hardwareMap.get(NormalizedColorSensor.class, "color_sensor");
            eye = new Eye(colorSensor, telemetry);

            telemetry.addData(SENSOR_TAG, "Sensors initializded.");
            sleep(1000);


        } catch (IllegalArgumentException e) {
            telemetry.addData(SENSOR_TAG, "Sensors not initialized.");
            sleep(1000);

        }

    }


    public double measureDistance() {
        if (distanceSensor != null) {
            double distance = distanceSensor.getDistance(DistanceUnit.CM);
            telemetry.addData("Disance in CM: ", distance);
            return distance;
        } else {
            return -1;
        }

    }

    public boolean touchIsPressed() {
        if (touchSensor != null) {
            telemetry.addData("Touch: ", touchSensor.getState());
            telemetry.update();
            sleep(100);
            return !touchSensor.getState();
        } else {
            return false;
        }
    }
}
