package org.firstinspires.ftc.teamcode.killer_robot;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Models.Eye;

@TeleOp(name = "Killer Robot Base Class", group = "Killer Robot")
@Disabled
public class KillerRobot extends LinearOpMode {


    DcMotor leftDriveOne;
    DcMotor leftDriveTwo;
    DcMotor rightDriveOne;
    DcMotor rightDriveTwo;

    Servo neckPosition;
    Servo leftShoulder;
    Servo rightShoulder;
    Servo leftElbow;
    Servo rightElbow;

    Eye leftEye;
    Eye rightEye;
    private DigitalChannel touchSensor;
    private DistanceSensor distanceSensor;

    public boolean killerMode = true;
    private boolean baseHardwareConfigured = false;
    private double MOTOR_POWER = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        initializeBaseHardware();
        initializeColorSensors();
        initializeTouchSensor();
        initializeDistanceSensor();


        waitForStart();

        while (baseHardwareConfigured && opModeIsActive() && killerMode) {
            runKillerRobot();
            stopKillerRobot(); //An interface might be better here.

            if (gamepad1.a) {
                killerMode = false;
            }

            float green = rightEye.detectColors().greenValue;
            float red = leftEye.detectColors().redValue;

            telemetry.addData("RED", red);
            telemetry.addData("GREEN", green);
            telemetry.update();
        }

        resetRobot();

    }

    private void initializeBaseHardware() {

        try {
            //Initialize the motors
            leftDriveOne = hardwareMap.get(DcMotor.class, "left_drive_one");
            leftDriveTwo = hardwareMap.get(DcMotor.class, "left_drive_two");
            rightDriveOne = hardwareMap.get(DcMotor.class, "right_drive_one");
            rightDriveTwo = hardwareMap.get(DcMotor.class, "right_drive_two");

            //Initialize the servos.
            neckPosition = hardwareMap.get(Servo.class, "neck_servo");
            leftShoulder = hardwareMap.get(Servo.class, "left_shoulder_servo");
            rightShoulder = hardwareMap.get(Servo.class, "right_shoulder_servo");
            leftElbow = hardwareMap.get(Servo.class, "left_elbow_servo");
            rightElbow = hardwareMap.get(Servo.class, "right_elbow_servo");


            leftDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);
            rightDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);

            baseHardwareConfigured = true;
            telemetry.addData("BASE HARDWARE", "Base hardware initialized.");
            telemetry.update();
            sleep(1000);

        } catch (IllegalArgumentException e) {

            baseHardwareConfigured = false;
            telemetry.addData("BASE HARDWARE", "Base hardware is not configured.");
            telemetry.update();
            sleep(500);

        }


    }

    private void initializeColorSensors() {
        NormalizedColorSensor leftColorSensor;
        NormalizedColorSensor rightColorSensor;
        try {
            leftColorSensor = hardwareMap.get(NormalizedColorSensor.class, "left_color_sensor");
            rightColorSensor = hardwareMap.get(NormalizedColorSensor.class, "right_color_sensor");
            leftEye = new Eye(leftColorSensor, telemetry);
            rightEye = new Eye(rightColorSensor, telemetry);
            telemetry.addData("SENSORS", "Color sensors initialized.");
        } catch (IllegalArgumentException e) {
            telemetry.addData("ERROR", "Color sensors not initialized.");
            leftEye = null;
            rightEye = null;

        }
        telemetry.update();
        sleep(1000);
    }

    private void initializeTouchSensor() {
        try {
            touchSensor = hardwareMap.get(DigitalChannel.class, "touch_sensor");
            touchSensor.setMode(DigitalChannel.Mode.INPUT);
            telemetry.addData("SENSORS: ", "Touch sensor initialized.");
        } catch (IllegalArgumentException e) {
            telemetry.addData("ERROR", "Touch sensor not initialized.");
            touchSensor = null;

        }
        telemetry.update();
        sleep(1000);

    }

    private void initializeDistanceSensor() {
        try {
            distanceSensor = hardwareMap.get(DistanceSensor.class, "distance_sensor");
            telemetry.addData("SENSORS: ", "Distance sensor initialized.");
        } catch (IllegalArgumentException e) {
            telemetry.addData("ERROR", "Distance sensor not initialized.");
            distanceSensor = null;
        }
        telemetry.update();
        sleep(1000);

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

    private final void runKillerRobot() {
        leftDriveOne.setPower(MOTOR_POWER);
        leftDriveTwo.setPower(MOTOR_POWER);
        rightDriveOne.setPower(MOTOR_POWER);
        rightDriveTwo.setPower(MOTOR_POWER);

        leftShoulder.setPosition(0.7);
        rightShoulder.setPosition(0.4);
        leftElbow.setPosition(0.3);
        sleep(500);
        leftShoulder.setPosition(1.0);
        rightElbow.setPosition(0.0);
        neckPosition.setPosition(0.9);
        sleep(500);
        leftShoulder.setPosition(0.5);
        rightShoulder.setPosition(1.0);
        neckPosition.setPosition(0.2);
        leftElbow.setPosition(0.9);
        rightElbow.setPosition(0.9);
        sleep(800);
    }

    private final void resetRobot(){
        leftDriveOne.setPower(0.0);
        leftDriveTwo.setPower(0.0);
        rightDriveOne.setPower(0.0);
        rightDriveTwo.setPower(0.0);
        leftElbow.setPosition(0.0);
        rightElbow.setPosition(0.0);
        neckPosition.setPosition(0.5);
        rightShoulder.setPosition(1.0);
        leftShoulder.setPosition(1.0);
    }

    protected void stopKillerRobot(){
    }


}
