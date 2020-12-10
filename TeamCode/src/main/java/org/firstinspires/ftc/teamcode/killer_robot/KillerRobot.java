package org.firstinspires.ftc.teamcode.killer_robot;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREV2mDistance;
import org.firstinspires.ftc.teamcode.Models.Eye;

@TeleOp(name = "Killer Robot Base Class", group = "Linear Opmode")
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
    private SensorREV2mDistance distanceSensor;

    private double MOTOR_POWER = 0.5;
    public boolean objectIsClose = false;


    @Override
    public void runOpMode() throws InterruptedException {
        initializeBaseHardware();
        initializeColorSensors();
        initializeTouchSensor();
        initializeDistanceSensor();


        waitForStart();

    }


    private void initializeBaseHardware() {

        //Initialize the motors
        leftDriveOne = hardwareMap.get(DcMotor.class, "left_drive_one");
        leftDriveTwo = hardwareMap.get(DcMotor.class, "left_drive_two");
        rightDriveOne = hardwareMap.get(DcMotor.class, "right_drive_one");
        rightDriveTwo = hardwareMap.get(DcMotor.class, "right_drive_two");

        //Initialize the servos.
        neckPosition = hardwareMap.get(Servo.class, "neck_position_servo");
        leftShoulder = hardwareMap.get(Servo.class, "left_shoulder_servo");
        rightShoulder = hardwareMap.get(Servo.class, "right_shoulder_servo");
        leftElbow = hardwareMap.get(Servo.class, "left_elbow_servo");
        rightElbow = hardwareMap.get(Servo.class, "right_elbow_servo");


        leftDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void initializeColorSensors() {
        NormalizedColorSensor leftColorSensor;
        NormalizedColorSensor rightColorSensor;
        try {
            leftColorSensor = hardwareMap.get(NormalizedColorSensor.class, "left_color_sensor");
            rightColorSensor = hardwareMap.get(NormalizedColorSensor.class, "right_color_sensor");
            leftEye = new Eye(leftColorSensor, telemetry);
            rightEye = new Eye(rightColorSensor, telemetry);
        } catch (IllegalArgumentException e) {
            telemetry.addData("ERROR", "Color sensors not initialized.");
            leftEye = null;
            rightEye = null;
            telemetry.update();
        }

    }

    private void initializeTouchSensor() {
        try {
            touchSensor = hardwareMap.get(DigitalChannel.class, "touch_sensor");
            touchSensor.setMode(DigitalChannel.Mode.INPUT);
        } catch (IllegalArgumentException e) {
            telemetry.addData("ERROR", "Touch sensor not initialized.");
            touchSensor = null;
            telemetry.update();
        }

    }

    private void initializeDistanceSensor() {
        try {
            distanceSensor = hardwareMap.get(SensorREV2mDistance.class, "distance_sensor");
        } catch (IllegalArgumentException e) {
            telemetry.addData("ERROR", "Distance sensor not initialized.");
            distanceSensor = null;
            telemetry.update();
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
        sleep(1000);
        leftShoulder.setPosition(1.0);
        rightElbow.setPosition(0.0);
        neckPosition.setPosition(0.9);
        sleep(800);
        leftShoulder.setPosition(0.5);
        rightShoulder.setPosition(1.0);
        sleep(1000);
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

        objectIsClose = true;
    }


}
