package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SwerveConstants;

public class SwerveDrive extends SubsystemBase {
    private static final SwerveDrive instance;
    
    static {
        instance = new SwerveDrive(
            /***
             * We need to initialize 4 swerve modules here. Get familiar with the SwerveModule... notation.
             */
        );
    }
    
    public static SwerveDrive getInstance() {
        return instance;
    }
    
    private final SwerveModule[] modules;
    
    private final SwerveDriveOdometry odometry;
    
    private double gyroAngleRad = 0.0;
    
    private final Field2d field;
    
    public SwerveDrive(SwerveModule... modules) {
        this.modules = modules;
        
        odometry = new SwerveDriveOdometry(
            SwerveConstants.KINEMATICS,
            getRotation2d(),
            new SwerveModulePosition[] {
                modules[0].getModulePosition(),
                modules[1].getModulePosition(),
                modules[2].getModulePosition(),
                modules[3].getModulePosition()
            }
        );
        
        field = new Field2d();
        SmartDashboard.putData("Field", field);
    }

    public void drive(Translation2d velocity, double rot) {
        /***
         * Look into the ChassisSpeeds constructor. Think about the velocity_x, velocity_y, and rotation
         */
        ChassisSpeeds speeds = ;
        
        /***
         * Hint: Look for SwerveDriveKinematics in this project. Might need to do some research.
         */
        SwerveModuleState[] states = ;
        
        /***
         * What's missing here? Think about what the point of this method is?
         */
        SwerveDriveKinematics.desaturateWheelSpeeds(states, );
        
        setDesiredStates(states);
    }

    public void setXMode() {
        SwerveModuleState[] states = {
            /***
             * Look at the constructors for SwerveModuleState.
             * Visualize what the angles and speeds of each swerve module should be.
             */
        };
        
        setDesiredStates(states);
    }
    
    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }
    
    public void setDesiredStates(SwerveModuleState[] states) {
        /***
         * Use the modules array and the states array + the setDesiredState method from SwerveModule class
         */
    }
    
    public void resetOdometry(Pose2d newPose) {
        odometry.resetPosition(
            getRotation2d(),
            new SwerveModulePosition[] {
                modules[0].getModulePosition(),
                modules[1].getModulePosition(),
                modules[2].getModulePosition(),
                modules[3].getModulePosition()
            },
            newPose
        );
        
        gyroAngleRad = newPose.getRotation().getRadians();
    }
    
    public Rotation2d getRotation2d() {
        return new Rotation2d(gyroAngleRad);
    }
    
    public void zeroHeading() {
        gyroAngleRad = 0.0;
    }
    
    @Override
    public void periodic() {
        odometry.update(
            getRotation2d(),
            new SwerveModulePosition[] {
                modules[0].getModulePosition(),
                modules[1].getModulePosition(),
                modules[2].getModulePosition(),
                modules[3].getModulePosition()
            }
        );

        field.setRobotPose(getPose());
        
        SmartDashboard.putNumber("Robot X", getPose().getX());
        SmartDashboard.putNumber("Robot Y", getPose().getY());
        SmartDashboard.putNumber("Robot Angle", getPose().getRotation().getDegrees());
        SmartDashboard.putNumber("Gyro Angle", Math.toDegrees(gyroAngleRad));
    }
}