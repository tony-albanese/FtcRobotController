package org.firstinspires.ftc.teamcode.Models;

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Eye {

    NormalizedColorSensor eye;
    Telemetry telemetry;

    public Eye(NormalizedColorSensor eye, Telemetry telemetry) {
        this.eye = eye;
        this.telemetry = telemetry;
    }

    public Eye(Telemetry telemetry) {
        telemetry.addData("Initializing Eye: ", "Eye is null");
        telemetry.update();
        eye = null;
    }

    public DetectedColor detectColors() {
        if (eye != null) {
            return new DetectedColor(eye.getNormalizedColors());
        } else {
            NormalizedRGBA blankRGBA = new NormalizedRGBA();
            blankRGBA.green = (float) 0.0;
            blankRGBA.alpha = (float) 0.0;
            blankRGBA.blue = 0.0f;
            blankRGBA.red = 0.0f;
            return new DetectedColor(blankRGBA);
        }

    }
}
