package org.usfirst.frc.team5752.robot.commands;

import org.usfirst.frc.team5752.robot.Robot;
import org.usfirst.frc.team5752.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class autoDrive extends Command {
	
	private double xDirection, yDirection, rotation, time;
	private Timer myTimer = new Timer();
	/**
	 * 
	 * @param xDirection xSpeed
	 * @param yDirection ySpeed
	 * @param rotation rotationSpeed
	 * @param time time(seconds)
	 */
    public autoDrive(double xDirection, double yDirection, double rotation, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.xDirection = xDirection;
    	this.yDirection = -1 * yDirection;
    	this.rotation = rotation;
    	this.time = time;
    	myTimer.start();
    }


    public autoDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	myTimer.reset();
    	myTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.driveOverRide(xDirection, yDirection, rotation);
    	SmartDashboard.putString("DB/String 4", "time: " + myTimer.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return myTimer.get() > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
