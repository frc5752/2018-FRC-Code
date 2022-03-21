package org.usfirst.frc.team5752.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5752.robot.*;
import org.usfirst.frc.team5752.robot.subsystems.DriveTrain;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ForwardUntilBump extends Command {
	boolean bCollisionDetected;
	double x1;
	double x0;
	double y1;
	double y0;
	double jerkX;
	double jerkY;
	double threshX=0.5;
	double threshY=0.5;

    public ForwardUntilBump() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	//requires(Robot.myFlipper);
    	setTimeout(5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		bCollisionDetected=false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// drive takes X, Rotation, -Y
    	Robot.drivetrain.drive(0,-0.4,0);    	
    } 

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	
    	bCollisionDetected=isTimedOut();
    	
    	x1=Robot.ahrs.getWorldLinearAccelX();
    	jerkX=x1-x0;
    	x0=x1;
    	
    	y1=Robot.ahrs.getWorldLinearAccelY();
    	jerkY=y1-y0;
    	y1=y0;
    	
    	if( (Math.abs(jerkX) > threshX) || (Math.abs(jerkY) > threshY ))
    	{
    		bCollisionDetected = true;
    		SmartDashboard.putString("DB/String 6", "COLLISION");
    	}
    	  	
        return bCollisionDetected;
    	//return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
