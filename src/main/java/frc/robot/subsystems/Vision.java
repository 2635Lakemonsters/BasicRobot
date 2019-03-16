// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.cscore.CvSource;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.MjpegServer;


//import edu.wpi.first.wpilibj.vision.CameraServer;
//import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 *
 */
public class Vision extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	UsbCamera camera;
	
	public Vision() {

		// Creates UsbCamera and MjpegServer [1] and connects them
		//CameraServer.getInstance().startAutomaticCapture();

		// Creates the CvSource and MjpegServer [2] and connects them
		//CameraServer.getInstance().putVideo("Blur", 640, 480);
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setFPS(10);
		camera.setResolution(160, 120);
		
		//Creates the CvSink and connects it to the UsbCamera 
		//CvSink cvSink = CameraServer.getInstance().getVideo();
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
