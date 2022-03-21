package org.usfirst.frc.team5752.robot.subsystems;

import org.usfirst.frc.team5752.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.DriverStation.*;

/**
 *
 */
public class Camera extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Spark CameraMotor = new Spark(RobotMap.CAMERA_MOTOR);
	private static DigitalInput leftLimit = new DigitalInput(0);
	private static DigitalInput rightLimit = new DigitalInput(1);
	//private static AnalogInput range = new AnalogInput(2);
	private static AnalogInput range = new AnalogInput(0);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	range.setAverageBits(1024);
    	range.setOversampleBits(1024);


    }
     
    public double getRange()
    {
    	double dMeanVolts = range.getAverageVoltage();
    	double inches = Math.round(dMeanVolts*4096)/100;
    	if( inches <= 11.1 ) inches=-1;
    	
    	//SmartDashboard.putString("DB/String 1", "inches: " + inches);
    	//SmartDashboard.putString("DB/String 2",  "dMeanVolts: " + range.getAverageVoltage() );
    	return inches;
    }
    public void Left()
    {
    	
    	if( leftLimit.get() == false )
    	{
    		CameraMotor.set(0.13);
    	}
    	else
    	{
    		Stop();
    	}
    }
    public void Right()
    {
    	if( rightLimit.get() == false)
    	{
    		CameraMotor.set(-0.12);
    	}
    	else
    	{
    		Stop();
    	}
    }
    public void Stop()
    {
    	CameraMotor.set(0);
    }
    
    public void StopSubsystem()
    {
    	CameraMotor.set(0);
    }
 }


