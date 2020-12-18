package org.firstinspires.ftc.teamcode.Besnik;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Mr Robot Tank Mode", group = "Besnik")
public class MrRobotTankMode extends LinearOpMode {


    // Declaring the Variables
    DcMotor leftDriveOne;
    DcMotor leftDriveTwo;
    DcMotor rightDriveOne;
    DcMotor rightDriveTwo;

    Servo head;
    Servo leftShoulder;
    Servo rightShoulder;
    Servo leftElbow;
    Servo rightElbow;

    @Override
    public void runOpMode() throws InterruptedException {

        // Connecting the variables to the hardware on the control hub.
        leftDriveOne = hardwareMap.get(DcMotor.class, "left_drive_one");
        leftDriveTwo = hardwareMap.get(DcMotor.class, "left_drive_two");
        rightDriveOne = hardwareMap.get(DcMotor.class, "right_drive_one");
        rightDriveTwo = hardwareMap.get(DcMotor.class, "right_drive_two");

        head = hardwareMap.get(Servo.class, "neck_position_servo");
        leftShoulder = hardwareMap.get(Servo.class, "left_shoulder_servo");
        rightShoulder = hardwareMap.get(Servo.class, "right_shoulder_servo");
        leftElbow = hardwareMap.get(Servo.class, "left_elbow_servo");
        rightElbow = hardwareMap.get(Servo.class, "right_elbow_servo");

        leftDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            readGamepadCommands();
        }

    }


    private void readGamepadCommands() {

        leftDriveOne.setPower(gamepad1.left_stick_y);
        leftDriveTwo.setPower(gamepad1.left_stick_y);
        rightDriveOne.setPower(gamepad1.right_stick_y);
        rightDriveTwo.setPower(gamepad1.right_stick_y);


        if (gamepad1.dpad_left) {
            head.setPosition(1);
        }
        if (gamepad1.dpad_right) {
            head.setPosition(0);
        }
        if (gamepad1.dpad_up) {
            head.setPosition(0.5);
        }

        if (gamepad1.right_bumper) {
            rightShoulder.setPosition(0);

        } else {
            rightShoulder.setPosition(1);
        }
        if (gamepad1.left_bumper) {
            leftShoulder.setPosition(0.7);
        } else {
            leftShoulder.setPosition(0);
        }

        if (gamepad1.b) {
            rightElbow.setPosition(0.6);
        } else {
            rightElbow.setPosition(0);
        }
        if (gamepad1.x) {
            leftElbow.setPosition(0.6);
        } else {
            leftElbow.setPosition(0);
        }
    }


}
