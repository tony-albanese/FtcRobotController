package org.firstinspires.ftc.teamcode.Models;

import com.qualcomm.robotcore.hardware.NormalizedRGBA;

public class DetectedColor {

    public float greenValue;
    public float redValue;
    public float blueValue;
    private NormalizedRGBA rgba;

    public DetectedColor(NormalizedRGBA rgba) {
        this.rgba = rgba;

        blueValue = rgba.blue;
        redValue = rgba.red;
        greenValue = rgba.green;


    }


}
