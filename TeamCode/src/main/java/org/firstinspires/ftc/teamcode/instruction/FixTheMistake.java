package org.firstinspires.ftc.teamcode.instruction;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Common Mistakes", group = "Linear Opmode")
public class FixTheMistake extends LinearOpMode {

    // Declaring the Variables. These are fine.
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

        neckPosition = hardwareMap.get(Servo.class, "neck_position_servo");
        leftShoulder = hardwareMap.get(Servo.class, "left_shoulder_servo");
        rightShoulder = hardwareMap.get(Servo.class, "right_shoulder_servo");
        leftElbow = hardwareMap.get(Servo.class, "left_elbow_servo");
        rightElbow = hardwareMap.get(Servo.class, "right_elbow_servo");

        leftDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveOne.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart(); // This method pauses the OpMode until the user presses Start. Don't delete this.

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


    //All of the completed code will go here.
    private void readGamepadCommands() {

        //TODO 1: Set the power of all the drives to the value of left_stick_y.


        //TODO 2: When the user presses the a button, the left shoulder position is set to 0.1
        //TODO : Otherwise it is set to 1.0


        //TODO 3: Find and fix the error with this code. HINT: remember the elbows are servos.

        if (gamepad1.b) {
            leftElbow.setPower(0.8);
        } else {
            leftElbow.setPower(0.0);
        }

        //TODO 4: Explain what is wrong with the following line of code. You do not need to fix it, just explain what is wrong.

        rightElbow.setPosition(gamepad1.x);

        /*
        Write your explanation here:


         */
    }


    /*


   if(gamepad1.a){

    } else{

    }




   gamepad1.right_stick_y


    leftDriveOne.setPower();
    leftDriveTwo.setPower();
    rightDriveOne.setPower();
    rightDriveTwo.setPower();

    leftShoulder.setPosition();


    rightShoulder.setPosition();

     */


}
