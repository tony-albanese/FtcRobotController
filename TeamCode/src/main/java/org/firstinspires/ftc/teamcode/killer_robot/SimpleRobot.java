package org.firstinspires.ftc.teamcode.killer_robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "SimpleRobot", group = "Linear Opmode")
public class SimpleRobot extends LinearOpMode {

    DcMotor motor;
    public boolean objectIsClose = false;
    private float POWER = 0.7f;
    private String TAG = "Status: ";


    @Override
    public void runOpMode() throws InterruptedException {
    motor = hardwareMap.get(DcMotor.class, "drive");

    initializeSensors();

    while(opModeIsActive() && !objectIsClose){
        telemetry.addData("Status: ", "Starting loop.");
        telemetry.update();
        runMotor();
        stopRobot();
    }

    }


    public void runMotor(){
        motor.setPower(POWER);
    }

    protected void stopRobot(){


    }

    protected void initializeSensors(){
        telemetry.addData(TAG, "Initilize Sensors called.");
        telemetry.update();

    }
}
