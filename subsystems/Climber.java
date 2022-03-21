package org.usfirst.frc.team5752.robot.subsystems;

/*import org.usfirst.frc.team5752.robot.OI;*/
import org.usfirst.frc.team5752.robot.RobotMap;


import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	private static Talon ClimberMotor = new Talon(RobotMap.CLIMBER_MOTOR);
	private static Spark ClimberMotor2 = new Spark( RobotMap.CLIMBER_MOTOR2);
	private static Spark HookMotor = new Spark(RobotMap.HOOK_MOTOR);
	//private static Spark
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() { 
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//if( OI.stick.getRawButton(7)) hookUp();
    	//if( OI.stick.getRawButton(8)) hookDown();\
		ClimberMotor.setSafetyEnabled(false);
		ClimberMotor2.setSafetyEnabled(false);
    } 
    public void hookUp(double dPrecision)
    {
    	HookMotor.set(dPrecision);
   
    }
    public void hookDown(double dPrecision)
    {
    	HookMotor.set(-dPrecision);
    }
    public void hookStop()
    {
    	HookMotor.set(0);    	
    }
    public void ClimberUp(double dPrecision)
    {
    	ClimberMotor.set(0.9);
    	ClimberMotor2.set(0.9);
    	//ClimberMotor.set(dPrecision*2);
    	//ClimberMotor2.set(dPrecision*2);
    	//ClimberMotor.set(1);
    }
    public void ClimberDown(double dPrecision)
    {
    	ClimberMotor.set(-dPrecision*2);
    	ClimberMotor2.set(-dPrecision*2);
    }
    public void ClimberStop()
    {    	
    	ClimberMotor.set(0);
    	ClimberMotor2.set(0);
    	
    }
    public void StopSubsystem()
    {
    	ClimberMotor.set(0);
    	ClimberMotor2.set(0);
    	HookMotor.set(0);
    }
}

