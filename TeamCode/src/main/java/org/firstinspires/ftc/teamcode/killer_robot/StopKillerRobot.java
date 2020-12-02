package org.firstinspires.ftc.teamcode.killer_robot;

public class StopKillerRobot extends KillerRobot {


    @Override
    public void stopKillerRobot(){
        sleep(5000);
        objectIsClose = true;
    }

}
