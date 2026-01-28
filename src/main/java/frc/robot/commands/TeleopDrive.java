package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.SwerveConstants;
import frc.robot.subsystems.SwerveDrive;

public class TeleopDrive extends Command {
    private final SwerveDrive swerveDrive;
    private final CommandXboxController controller;
    
    public TeleopDrive(SwerveDrive swerveDrive, CommandXboxController controller) {
        /***
         * Fill out this command constructor
         */
    }
    
    @Override
    public void execute() {
        /***
         * Look into controller.getRawAxis() method
         * Also look into OIConstants in Constants.java to make sure the controller syncs correctly
         * Might have to negate something 🤔🤔🤔. Do some trial and error.
         */
        double xInput = ;
        double yInput = ;
        double rotInput = ;

        /***
         * Apply a deadband to these inputs. Look at OIConstants.
         */
        xInput = MathUtil.applyDeadband(xInput, );
        yInput = MathUtil.applyDeadband(yInput, );
        rotInput = MathUtil.applyDeadband(rotInput, );
        
        // Challenge: square the inputs while preserving the signs

        /***
         * Use the inputs and look at constants for the following: the max speed, the speed multipler. What do we do with these?
         */
        double xSpeed = xInput * ;
        double ySpeed = yInput * ;
        double rotSpeed = rotInput * ;
        
        /***
         * Fix the error here. What are the arguments to the method? Hint: look at the end method below...
         */
        swerveDrive.drive();
    }
    
    @Override
    public void end(boolean interrupted) {
        swerveDrive.drive(new Translation2d(0, 0), 0);
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}