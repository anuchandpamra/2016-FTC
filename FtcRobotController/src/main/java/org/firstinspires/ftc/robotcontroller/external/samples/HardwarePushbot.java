package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwarePushbot
{
    /* Public OpMode members. */
    /* Motor Controller 2 */
    //public DcMotorController motorController2 = null;

    /* motors per Motor Controller 2, back */
    public DcMotor    leftMotorBack    = null;
    public DcMotor    rightMotorBack   = null;

    /* Motor Contoller 3 */
    //public DcMotorController motorController3 = null;

    /* motors per Motor Controller 3, front */
    public DcMotor  leftMotor   = null;
    public DcMotor  rightMotor  = null;
    public DcMotor  spinMotor    = null;
    //public DcMotor  picker = null;

    public static final double MID_SERVO       =  0.5 ;
    //public static final double ARM_UP_POWER    =  0.45 ;
    //public static final double ARM_DOWN_POWER  = -0.45 ;
    public Servo   servo1        = null;
   // public Servo   servo2        = null;
   // public Servo   servo3        = null;
   // public Servo   servo4        = null;
        public CRServo crservo2      = null;
        public CRServo crservo3      = null;
        public CRServo crservo4       = null;



    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwarePushbot(){

    }


    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
       // motorController2 = hwMap.dcMotorController.get("Motor Controller 2");
        leftMotorBack = hwMap.dcMotor.get("lB");
        rightMotorBack = hwMap.dcMotor.get("rB");
       // motorController3 = hwMap.dcMotorController.get("Motor Controller 3");
        leftMotor   = hwMap.dcMotor.get("lF");
        rightMotor  = hwMap.dcMotor.get("rF");


        //picker = hwMap.dcMotor.get("picker");
        spinMotor    = hwMap.dcMotor.get("spin");
        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        leftMotorBack.setDirection(DcMotor.Direction.FORWARD);
        rightMotorBack.setDirection(DcMotor.Direction.REVERSE);
        // Set all motors to zero power
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotorBack.setPower(0);
        rightMotorBack.setPower(0);
        spinMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        spinMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.

        servo1 = hwMap.servo.get("s1"); //beacon arm - port 3
        servo1.setPosition(0);
        //servo1.setPosition(MID_SERVO);
       // servo2 = hwMap.servo.get("s4"); //back most servo - port 6
       // servo2.setPosition(MID_SERVO);
      //  servo3 = hwMap.servo.get("s3"); // middle servo - port 2
      //  servo3.setPosition(MID_SERVO);
      //  servo4 = hwMap.servo.get("s2"); // launch servo - port 4
      //  servo4.setPosition(MID_SERVO);

        crservo2 = hwMap.crservo.get("s4"); //back most servo - port 6
        //crservo2.setPosition(MID_SERVO);
        crservo2.setPower(0);
        crservo3 = hwMap.crservo.get("s3"); // middle servo - port 2
        crservo3.setPower(0);
        //crservo3.setPosition(MID_SERVO);
        crservo4 = hwMap.crservo.get("s2"); // launch servo - port 4
        //crservo4.setPosition(MID_SERVO);
        crservo4.setPower(0);

        //rightClaw = hwMap.servo.get("right_hand");
       // leftClaw.setPosition(MID_SERVO);
       // rightClaw.setPosition(MID_SERVO);
    }




    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

