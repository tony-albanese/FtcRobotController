package org.firstinspires.ftc.teamcode.MrRobot;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

//TODO 1 : Include your name in the OpMode
@TeleOp(name = "Schipporeit", group = "Linear Opmode")
public class LuisaMrRobot extends LinearOpMode {

    // Declaring the Variables
    DcMotor leftDriveOne;
    DcMotor leftDriveTwo;
    DcMotor rightDriveOne;
    DcMotor rightDriveTwo;

    Servo neckPosition;
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

        neckPosition = hardwareMap.get(Servo.class, "neck_servo");
        leftShoulder = hardwareMap.get(Servo.class, "left_shoulder_servo");
        rightShoulder = hardwareMap.get(Servo.class, "right_shoulder_servo");
        leftElbow = hardwareMap.get(Servo.class, "left_elbow_servo");
        rightElbow = hardwareMap.get(Servo.class, "right_elbow_servo");

        leftDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart(); // This method pauses the OpMode untilthe user presses Start. Don't delete this.

        /*
         * This is called a while loop. The commands inside the braces will run as long
         * as the OpMode is active. (The OpMode becomes inactive when the user presses
         * stop. In other words, this program will run all of the code in the
         * readGamePadCommands() method until the user presses the stop button.
         */
        while (opModeIsActive()) {
            readGamepadCommands();
        }

    }

    // This is the method you will program today. Your task is to decide what to do
    // when the user
    // presses a button on the gamepad.
    private void readGamepadCommands() {

        /*
         * TODO 2: Program the buttons that control the drive. Remember you have to
         * program the leftDrive and the rightDrive motors to work together.
         */
        if (gamepad1.dpad_up) {
            leftDriveOne.setPower(0.9);
            leftDriveTwo.setPower(0.9);
            rightDriveOne.setPower(0.9);
            rightDriveTwo.setPower(0.9);
        } else {
            leftDriveOne.setPower(0.0);
            leftDriveTwo.setPower(0.0);
            rightDriveOne.setPower(0.0);
            rightDriveTwo.setPower(0.0);
        }

        if (gamepad1.dpad_down) {
            leftDriveOne.setPower(-0.9);
            leftDriveTwo.setPower(-0.9);
            rightDriveOne.setPower(-0.9);
            rightDriveTwo.setPower(-0.9);
        } else {
            leftDriveOne.setPower(0.0);
            leftDriveTwo.setPower(0.0);
            rightDriveTwo.setPower(0.0);
            rightDriveTwo.setPower(0.0);
        }

        /*
         * TODO 3: Program the buttons that control the neck servo.
         */
        if (gamepad1.x) {
            neckPosition.setPosition(0.0);
        } else {
            neckPosition.setPosition(0.5);

        }

        if (gamepad1.b) {
            neckPosition.setPosition(0.9);
        } else {
            neckPosition.setPosition(0.5);

        }
        /*
         * TODO 4: Program the buttons that control the shoulder servos.
         */

        leftShoulder.setPosition(gamepad1.left_stick_y);
        rightShoulder.setPosition(gamepad1.right_stick_y);

        // TODO 5: Program the buttons that control the elbow servos.

        if (gamepad1.dpad_left) {
            leftElbow.setPosition(0.7);
        } else {
            leftElbow.setPosition(0.0);

        }

        if (gamepad1.dpad_right) {
            rightElbow.setPosition(0.7);
        } else {
            rightElbow.setPosition(0.0);

        }

    }

}