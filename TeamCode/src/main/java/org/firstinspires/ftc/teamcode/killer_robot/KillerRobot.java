package org.firstinspires.ftc.teamcode.killer_robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREV2mDistance;

@TeleOp(name = "Killer Robot Base Class", group = "Linear Opmode")
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

    ColorSensor leftColorSensor;
    ColorSensor rightColorSensor;

    TouchSensor touchSensor;

    SensorREV2mDistance distanceSensor = null;


    private double MOTOR_POWER = 0.5;
    public boolean objectIsClose = false;


    @Override
    public void runOpMode() throws InterruptedException {
        System.out.println("Hello, world!");
        initializeHardware();
        waitForStart();

        while (opModeIsActive() && !objectIsClose) {
            runKillerRobot();

            if (gamepad1.a || touchSensor.isPressed()) {
                stopKillerRobot();
            }

        }

        resetRobot();
    }


    private final void initializeHardware() {

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

        //Initialize the sensors.
        leftColorSensor = hardwareMap.get(ColorSensor.class, "left_color_sensor");
        rightColorSensor = hardwareMap.get(ColorSensor.class, "right_color_sensor");

        touchSensor = hardwareMap.get(TouchSensor.class, "touch_sensor");
        distanceSensor = hardwareMap.get(SensorREV2mDistance.class, "distance_sensor");

        leftDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private final void runKillerRobot(){
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
