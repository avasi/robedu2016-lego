nxjc config/Globals.java
nxjc behaviours/Turn.java config/Globals.java
nxjc behaviours/Forward.java config/Globals.java
nxjc behaviours/AvoidObstacle.java config/Globals.java
nxjc behaviours/DetectObstacle.java config/Globals.java
nxjc Robot.java behaviours/Forward.java behaviours/Turn.java behaviours/AvoidObstacle.java behaviours/DetectObstacle.java config/Globals.java
nxjlink -o Robot.nxj Robot
nxjupload -r Robot.nxj