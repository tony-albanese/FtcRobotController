package org.firstinspires.ftc.teamcode.Besnik;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Claw Robot", group = "Besnik")
public class ClawRobot extends LinearOpMode {
    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor arm;

    //Servo arm;
    Servo claw;

    double clawPosition = 0.0;
    double armPower = 0.0;


    @Override
    public void runOpMode() throws InterruptedException {

        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "arm");
        claw = hardwareMap.get(Servo.class, "claw");

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        claw.setPosition(0.0);


        waitForStart();

        while (opModeIsActive()){
            runRobot();
        }

        resetRobot();
    }

    private void runRobot(){
        leftDrive.setPower(gamepad1.left_stick_y);
        rightDrive.setPower(gamepad1.right_stick_y);

        setArmPower();
        setClawPosition();

        telemetry.update();

    }


    private void setClawPosition(){
        if(gamepad1.b){
            clawPosition = clawPosition + 0.1;
            if(clawPosition > 1.0){
                clawPosition = 1.0;
            }
        } else if (gamepad1.y) {

            clawPosition = clawPosition - 0.1;
            if(clawPosition < 0.0){
                clawPosition = 0.0;
            }
        }

        telemetry.addData("Claw Servo Position", clawPosition);

        claw.setPosition(clawPosition);
        sleep(100);


    }

    private void setArmPower(){
        if(gamepad1.right_bumper){
            armPower = armPower - 0.1;
            if(armPower < 0.0){
                armPower = 0.0;
            }
        }

        if(gamepad1.left_bumper){
            armPower = armPower + 0.1;
            if(armPower > 1.0){
                armPower = 1.0;
            }

        }

        telemetry.addData("Arm Power",armPower);
        arm.setPower(armPower);

    }



    private void resetRobot(){
        arm.setPower(0.0);
        leftDrive.setPower(0.0);
        claw.setPosition(0.0);

    }
}
