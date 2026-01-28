package frc.robot.commands;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
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
        this.swerveDrive = swerveDrive;
        this.controller = controller;

        addRequirements(swerveDrive);
    }
    
    @Override
    public void execute() {
        /***
         * Look into controller.getRawAxis() method
         * Also look into OIConstants in Constants.java to make sure the controller syncs correctly
         * Might have to negate something 🤔🤔🤔. Do some trial and error.
         */
        double xInput = controller.getRawAxis(Constants.OIConstants.LEFT_X_AXIS);
        double yInput = controller.getRawAxis(Constants.OIConstants.LEFT_Y_AXIS);
        double rotInput = controller.getRawAxis(Constants.OIConstants.RIGHT_X_AXIS);

        /***
         * Apply a deadband to these inputs. Look at OIConstants.
         */
        xInput = MathUtil.applyDeadband(xInput, Constants.OIConstants.DEADBAND);
        yInput = MathUtil.applyDeadband(yInput, Constants.OIConstants.DEADBAND);
        rotInput = MathUtil.applyDeadband(rotInput, Constants.OIConstants.DEADBAND);
        
        // Challenge: square the inputs while preserving the signs

        /***
         * Use the inputs and look at constants for the following: the max speed, the speed multipler. What do we do with these?
         */
        double xSpeed = xInput * Constants.SwerveConstants.MAX_SPEED_MPS * Constants.OIConstants.SPEED_MULTIPLIER;
        double ySpeed = yInput * Constants.SwerveConstants.MAX_SPEED_MPS * Constants.OIConstants.SPEED_MULTIPLIER;
        double rotSpeed = rotInput * Constants.SwerveConstants.MAX_ANGULAR_SPEED * Constants.OIConstants.SPEED_MULTIPLIER;
        
        /***
         * Fix the error here. What are the arguments to the method? Hint: look at the end method below...
         */
        swerveDrive.drive(new Translation2d(xSpeed,ySpeed), rotSpeed);
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