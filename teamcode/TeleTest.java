package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TeleTest extends LinearOpMode {

    private DcMotor test;


    @Override
    public void runOpMode () {

        test = hardwareMap.get(DcMotor.class, "test");






        test.setDirection(DcMotor.Direction.REVERSE);

        test.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       /* fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); */


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        double power = 0;

        // run until the end of the match (driver presses STOP)
        while (!isStopRequested()) {


            if(gamepad1.a)
                power = .4;
            if(gamepad1.b)
                power = .6;
            if(gamepad1.y)
                power = .8;
            if(gamepad1.x)
                power = 1.0;

            if(gamepad1.dpad_up)
                test.setPower(power);
            else
                test.setPower(0);

            if(gamepad1.dpad_down)
                test.setPower(-power);
            else
                test.setPower(0);
        }


    }


}