package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleComp", group = "TeleOp")
public class MyFIRSTJavaOpMode extends LinearOpMode {

    private DcMotor fR, fL, bR, bL, vLift, hLift, yeeter; //front right, front left, back right, back left
    private Servo foun1, claw;
    double joyScale = 0.77; //power limit
    double motorMax = 0.75; //power limit

    @Override
    public void runOpMode () {

        fR = hardwareMap.get(DcMotor.class, "frontRight");
        fL = hardwareMap.get(DcMotor.class, "frontLeft");
        bR = hardwareMap.get(DcMotor.class, "backRight");
        bL = hardwareMap.get(DcMotor.class, "backLeft");
        hLift = hardwareMap.get(DcMotor.class, "hLift");
        vLift = hardwareMap.get(DcMotor.class, "vLift");
        yeeter = hardwareMap.get(DcMotor.class, "yeeter");


        foun1 = hardwareMap.get(Servo.class, "foun1");
        claw = hardwareMap.get(Servo.class, "claw");

        foun1.setDirection(Servo.Direction.FORWARD);
        claw.setDirection(Servo.Direction.FORWARD);


        fL.setDirection(DcMotor.Direction.FORWARD);
        bL.setDirection(DcMotor.Direction.FORWARD);
        fR.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.REVERSE);
        hLift.setDirection(DcMotor.Direction.REVERSE);
        vLift.setDirection(DcMotor.Direction.REVERSE);
        yeeter.setDirection(DcMotor.Direction.FORWARD);



        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        yeeter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




       fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        vLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        vLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (!isStopRequested()) {
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            double flPower, blPower, frPower, brPower;
            double move, turn, strafe;

            move = -gamepad1.left_stick_y * joyScale;
            strafe = gamepad1.left_stick_x * joyScale;
            turn = gamepad1.right_stick_x * joyScale;

            flPower = move + strafe + turn;
            blPower = move - strafe + turn;
            frPower = move - strafe - turn;
            brPower = move + strafe - turn;

            flPower = Math.max(-motorMax -.08, Math.min(flPower, motorMax +.08));
            frPower = Math.max(-motorMax, Math.min(frPower, motorMax));
            blPower = Math.max(-motorMax, Math.min(blPower, motorMax));
            brPower = Math.max(-motorMax, Math.min(brPower, motorMax));

            fL.setPower(flPower);
            bL.setPower(blPower);
            fR.setPower(frPower);
            bR.setPower(brPower);


            if(gamepad1.a) {
                foun1.setPosition(.05);
            }
            if(gamepad1.b) {
                foun1.setPosition(.7);
            }


            if(gamepad2.a){
                claw.setPosition(.5);
            }
            if(gamepad2.b){
                claw.setPosition(0);
            }


            if(gamepad2.right_trigger != 0) {
                vLift.setPower(gamepad2.right_trigger);
            } else if(gamepad2.left_trigger != 0) {
                vLift.setPower(-gamepad2.left_trigger);
            }else if(vLift.getCurrentPosition() > 100 && gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0){
                vLift.setPower(.2);
            } else
                vLift.setPower(0);


            if(gamepad2.dpad_right)
                hLift.setPower(1);
            else if(gamepad2.dpad_left)
                hLift.setPower(-1);
            else
                hLift.setPower(0);

            if(gamepad1.right_trigger != 0)
                yeeter.setPower(gamepad1.right_trigger);
            else if(gamepad1.left_trigger != 0)
                yeeter.setPower(-gamepad1.left_trigger);
            else
                yeeter.setPower(0);


            if(gamepad2.x)
                vLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



            telemetry.addData("foun1pos", "%.3f", foun1.getPosition());
            telemetry.addData("fr post", fR.getCurrentPosition());
            telemetry.addData("fl post", fL.getCurrentPosition());
            telemetry.addData("br post", bR.getCurrentPosition());
            telemetry.addData("bl post", bL.getCurrentPosition());
            telemetry.addData("vlift post", vLift.getCurrentPosition());
            telemetry.addData("vlift power", vLift.getPower());

            telemetry.update();
        }


    }


}