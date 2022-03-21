package org.usfirst.frc.team5752.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team5752.robot.Robot;
import org.usfirst.frc.team5752.robot.RobotMap;
import org.usfirst.frc.team5752.robot.commands.mecanumDrive;

import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.drive;
import edu.wpi.first.wpilibj.Spark;
/*import edu.wpi.first.wpilibj.Talon;*/
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static Spark frontLeft = new Spark(RobotMap.MOTOR_FRONT_LEFT);
	private static Spark frontRight = new Spark(RobotMap.MOTOR_FRONT_RIGHT);
	private static Spark backLeft = new Spark(RobotMap.MOTOR_BACK_LEFT);
	private static Spark backRight = new Spark(RobotMap.MOTOR_BACK_RIGHT);
	
	/*
	private static Spark frontLeft = new Spark(RobotMap.MOTOR_FRONT_LEFT);
	private static Spark frontRight = new Spark(RobotMap.MOTOR_FRONT_RIGHT);
	private static Spark backLeft = new Spark(RobotMap.MOTOR_BACK_LEFT);
	private static Spark backRight = new Spark(RobotMap.MOTOR_BACK_RIGHT);
	*/
	
	
	
	@SuppressWarnings("deprecation")
	private static RobotDrive myDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new mecanumDrive());
    	frontLeft.setSafetyEnabled( false );
    	frontRight.setSafetyEnabled( false );
    	backLeft.setSafetyEnabled( false );
    	backRight.setSafetyEnabled( false );
    }
    
	public static void stop(){
		myDrive.mecanumDrive_Cartesian(0, 0, 0, 0);
		
		frontLeft.set(0);
		backLeft.set(0);
		frontRight.set(0);
		backRight.set(0);
	}
	
	public void StopSubsystem()
	{
		stop();
	}

	
	//public static void driveForward()
	//{
	//	drive(1,0,0);
	//}
	
	public static void rotateTo( double d)
	{
		boolean done=false;
		// driveOverRide inputs are: X, Rotation, -Y
		while( !done )
		{
			if( Robot.ahrs.getYaw() < (d - 0.5 ))
			{
				driveOverRide( 0,0.5,0 );
			}
			else if( Robot.ahrs.getYaw() > (d + 0.5 ))
			{
				driveOverRide( 0,-0.5,0 );
			}
			else
			{
				stop();
				done=true;
			}
			
		}
	}
	
	public static void driveOverRide(double X_DIRECTION, double Y_DIRECTION, double ROTATION) {
		myDrive.mecanumDrive_Cartesian(X_DIRECTION, Y_DIRECTION, ROTATION, 0);
	}
	

	public static void drive(double X_DIRECTION, double Y_DIRECTION, double ROTATION) {
		
		double number1 = 1 / (1 - RobotMap.XY_DEADBAND);
		double number2 = 1 / (1 - RobotMap.ROTATION_DEADBAND);

		if (Math.abs(ROTATION) < RobotMap.ROTATION_DEADBAND) {
			ROTATION = 0;
		} else if (ROTATION > 0) {
			ROTATION -= RobotMap.ROTATION_DEADBAND;
			ROTATION *= number2;
		} else if (ROTATION < 0) {
			ROTATION += RobotMap.ROTATION_DEADBAND;
			ROTATION *= number2;
		}
		

		if (Math.abs(X_DIRECTION) < RobotMap.XY_DEADBAND) {
			X_DIRECTION = 0;
		} else if (X_DIRECTION > 0) {
			X_DIRECTION -= RobotMap.XY_DEADBAND;
			X_DIRECTION *= number1; // * 1.175 to be on the safe side
		} else if (X_DIRECTION < 0 ) {
			X_DIRECTION += RobotMap.XY_DEADBAND;
			X_DIRECTION *= number1;// * 1.175 to be on the safe side
		}

		if (Math.abs(Y_DIRECTION) < RobotMap.XY_DEADBAND) {
			Y_DIRECTION = 0;
		} else if (Y_DIRECTION > 0) {
			Y_DIRECTION -= RobotMap.XY_DEADBAND;
			Y_DIRECTION *= number1;// * 1.175 to be on the safe side
		} else if (Y_DIRECTION < 0) {
			Y_DIRECTION += RobotMap.XY_DEADBAND;
			Y_DIRECTION *= number1; // * 1.175 to be on the safe side
		} 

		//SmartDashboard.putString("DB/String 2", "X:" + Double.toString(X_DIRECTION));
		//SmartDashboard.putString("DB/String 3", "Y:" + Double.toString(Y_DIRECTION));
		//SmartDashboard.putString("DB/String 4", "ROTATE:" + Double.toString(ROTATION));
		myDrive.mecanumDrive_Cartesian(X_DIRECTION*0.85, -ROTATION/3, -Y_DIRECTION*0.85, 0); //ROTATION and Y_DIRECTION are both FLIPPED and BACKWARDS
		}
	
}

