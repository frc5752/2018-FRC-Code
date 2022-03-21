package org.usfirst.frc.team5752.robot.subsystems;
/*import org.usfirst.frc.team5752.robot.OI;*/
import org.usfirst.frc.team5752.robot.RobotMap;

//import edu.wpi.first.wpilibj.DigitalInput;

/*import edu.wpi.first.wpilibj.Spark;*/
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flipper extends Subsystem {

	private static Talon Flipper = new Talon(RobotMap.FLIPPER_MOTOR);

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	Flipper.setSafetyEnabled(false);
    }

    public void Raise(double dPrecision)
    {
    	//if( diLimit1.get() == false ) 
    		Flipper.set(dPrecision);
    }
    public void Lower(double dPrecision)
    {
    	Flipper.set(-dPrecision);
    	//LifterDown.set(0.1);
    }
    public void Stop()
    {
    	Flipper.set(0);
    }
    
    public void StopSubsystem()
    {
    	Stop();
    }
   
}

