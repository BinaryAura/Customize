#Customize

###Description

  The HUD in Minecraft can be invasive, especially when you have more than 20 health. This mod: "Customize," allows for the player
to adjust the many aspects that makes up the HUD, including moving and rotating the bars.

  This mod is made for the developer in mind with an easy to implement API. Simply make and register a HUDItem class and the
mod takes care of the rest. 

###Installation

  Download the customize-xxx-x.jar file, where xxx-x is the mod version, for the release you wish from the releases tab on GitHub. Download install-win/install for the version listed on the release from files.minecraftforge.net.
  
  To install this mod, first you must run the Minecraft listed, to do this click "New Profile" or "Edit Profile". In "Use version" Click on the minecraft version listed then, "Save Profile". Click "Play". This will download the version you need to install Forge. Now, run the install program for forge and install for client. Move the customize-xxx-x.jar to the mods directory in the .minecraft file in %AppData%. 

###Development

  Fork this repository then, clone the fork to your computer. Download forge for development. Copy and replace the eclipse file and then, open up the command prompt at the mod directory. Type the command `gradlew setupDecompWorkspace --refresh-dependencies`. Allow for the program to finish and then, type `gradlew eclipse`. If you wish to use IntelliJ import the project using the build.gradle file. After IntelliJ finishes, type `gradlew genIntellijRuns`.
  
  This setup has a built in workspace however, if you would like to have it elsewhere you can change these settings in the run configurations. You can also have git build into eclipse for more steamlined workflow. For eclipse do as follows: If you wish to import it into a different eclipse workspace or want to use eclipse's Egit, open up that workspace in eclipse, goto to File > Import. Select Existing Project and click Next. Navigate to the mod directory and click OK. Make sure "Search for nested projects" is checked to get git.
  
  To be able to test the mod you'll need to set up two launch configurations. Right click on the project and click Run As > Run Configurations. Double Click on "Java Application". Name the Configuration "<ModName> Client" Select the appropriate mod, and in the Main Class Type "GradleStart". Click the Arguments Tab. Under "VM Arguments" type `-Xincgc -Xms1024M -Xmx1024M`. Xms is the minimum memory and Xmx is the maximum memory. Set these to whatever you like (M = MB, G = GB). Under "Working Directory," click other and type "&(workspace_loc)" or click Variables and select workspcae_loc. To make the second Launch Configuration follow the same instructions except name it "<ModName> Server" and the Main Class should be "GradleStartServer". Of coarse what you name the configurations is up to you, however I recommend naming them something meaningful.

###Disclaimer

  If you plan to use this mod please take in mind that this mod is currently in Alpha. This means that there are sure to be problems. As Alpha, the mod is NOT is full operation. Only the bare minimum to test the functions of the mod without changing code. Additionally, this is first and foremost an API. This means this operates at a lower level than other mods. In other words, this mod will NOT be updated to operate with other mods. It is the modder's responsibility to adhere to the API. This being said I may make an additional child mod to accommodate other mods.
