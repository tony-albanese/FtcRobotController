package org.firstinspires.ftc.teamcode.killer_robot;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "SimpleRobot", group = "Linear Opmode")
public class SimpleRobot extends LinearOpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;

    Servo leftShoulder;
    Servo rightShoulder;

    ColorSensor colorSensor;

    DigitalChannel touchSensor;

    Rev2mDistanceSensor distanceSensor;

    public boolean objectIsClose = false;
    private float POWER = 0.7f;
    private String TAG = "Status: ";


    @Override
    public void runOpMode() throws InterruptedException {

        initializeHardware();
        initializeSensors();


        waitForStart();
        while (opModeIsActive() && !objectIsClose) {
            telemetry.addData(TAG, "Opmode is running.");
            telemetry.update();
            leftShoulder.setPosition(0.4);
            rightShoulder.setPosition(0.5);
            //leftDrive.setPower(POWER);
            //rightDrive.setPower(POWER);
            stopRobot();
            sleep(1000);
        }

    }


    protected void stopRobot() {

        telemetry.addData(TAG, touchSensor.getState());
        telemetry.update();

        if (touchSensor.getState() == true) {
            telemetry.addData(TAG, "Touch pressed.");
            objectIsClose = true;
            telemetry.update();
            resetRobot();
        }

    }

    protected void resetRobot() {
        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);
        leftShoulder.setPosition(0.0);
        rightShoulder.setPosition(0.0);
    }


    protected void initializeHardware() {

        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        leftShoulder = hardwareMap.get(Servo.class, "left_shoulder");
        rightShoulder = hardwareMap.get(Servo.class, "right_shoulder");

        leftShoulder.setPosition(0.0);
        rightShoulder.setPosition(0.0);

    }


    protected void initializeSensors() {
        touchSensor = hardwareMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);

        distanceSensor = hardwareMap.get(Rev2mDistanceSensor.class, "distance_sensor");
        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");

    }

}
