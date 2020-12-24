package org.firstinspires.ftc.teamcode.killer_robot;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "SimpleRobot Base Class", group = "Linear Opmode")
@Disabled
public class SimpleRobot extends LinearOpMode {

    String SENSOR_TAG = "Sensors";
    String HARDWARE_TAG = "Hardware";

    
    DcMotor leftDrive;
    DcMotor rightDrive;
    Servo leftShoulder;
    Servo rightShoulder;

    boolean baseHardwareInitialized = false;

    @Override
    public void runOpMode() throws InterruptedException {
        initializeBaseHardware();

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


}
