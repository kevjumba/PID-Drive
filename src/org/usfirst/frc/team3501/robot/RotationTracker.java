/**
 * Copyright (c) 2015, www.techhounds.com
 * All rights reserved.
 *
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * </p>
 * <ul>
 * <li>Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.</li>
 * <li>Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.</li>
 * <li>Neither the name of the www.techhounds.com nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.</li>
 * </ul>
 *
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * </p>
 */

package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.PIDSource;

/**
 * A rotation tracker allows you to keep track of how far the robot has rotated
 * since the object was constructed or zeroed.
 * 
 * <p>
 * This object is most useful when you want to turn your robot a particular
 * number of degrees, or when you want to detect whether your robot is tipping.
 * </p>
 * 
 * <p>
 * You will typically use the {@link #getRotationTracker} method associated with
 * the gyro class associated with your hardware. For example
 * {@link GyroItg3200.getRotationTrackerZ()}.
 * </p>
 */
public interface RotationTracker extends PIDSource {

  /**
   * Returns the angle in signed decimal degrees since construction or zeroing.
   * 
   * <p>
   * This value is used as the PID sensor value.
   * </p>
   * 
   * @return Number of degrees the robot has rotated in the range of [-INF,
   *         +INF]. Positive values indicate clockwise rotation (720 means it
   *         has spun around twice and is facing the same direction).
   */
  public double getAngle();

  /**
   * Zeros the rotation tracker so the current direction we are pointing now
   * becomes the zero point.
   */
  public void zero();

}
