# Customize

###Description

  The HUD in Minecraft can be invasive, especially when you have more than 20 health. This mod: "Customize," allows for the player
to adjust the many aspects that makes up the HUD, including moving and rotating the bars.
  This mod is made for the developer in mind with an easy to implement API. Simply make and register a HUDItem class and the
mod takes care of the rest. 

###Installation

  To install this mod, first install Forge for Minecraft 1.8.9. For the best results use the same Forge as the mod. Then, download the master branch  and move the zip file to mods directory in the .minecraft file in %AppData%. 

###Development

  Fork this repository then, clone the fork to your computer. Download forge for development. Copy and replace the eclipse file and then, open up the command prompt at the mod directory. Type the command "gradlew setupDecompWorkspace --refresh-dependencies". Allow for the program to finish and then, type "gradlew eclipse". If you wish to use IntelliJ import the project using the build.gradle file. After IntelliJ finishes, type "gradlew genIntellijRuns".
  This setup has a built in workspace however, if you would like to have it elsewhere you can change these settings in the run configurations. You can also have git build into eclipse for more steamlined workflow. For eclipse do as follows: If you wish to import it into a different eclipse workspace or want to use eclipse's egit, open up that workspace in eclipse, goto to File > Import. Select Existing Project and click Next. Navigate to the mod directory and click OK. Make sure "Search for nested projects" is checked to get git.
  To be able to test the mod you'll need to set up two launch configurations. Right click on the project and click Run As > Run Configuations. Double Click on "Java Application". Name the Configuation "<ModName> Client" Select the appropiate Mod, and in the Main Class Type "GradleStart". Click the Arguments Tab. Under "VM Arguments" type "-Xincgc -Xms1024M -Xmx1024M". Xms is the minimum memory and Xmx is the maximum memory. Set these to whatever you like (M = MB, G = GB). Under "Working Directory," click other and type "&(workspace_loc)" or click Variables and select workspcae_loc. To make the second Launch Configuration follow the same instructions except name it "<ModName> Server" and the Main Class should be "GradleStartServer". Of coarse what you name the configurations is up to you, however I recomend naming them something meaningful.

###Disclaimer

  If you plan to use this mod please take in mind that this mod is currently in Alpha. This means that there are sure to be problems. As Alpha, the mod is NOT is full operation. Only the bare minimum to test the functions of the mod without changing code. Additionally, this is first and foremost an API. This means this operates at a lower level than other mods. If I get enough requests for a particular mod I may make a class to accomadate it however, I would NOT be responsible for that class's upkeep.
