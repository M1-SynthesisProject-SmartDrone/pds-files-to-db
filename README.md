# Files to database ![build image](https://github.com/M1-SynthesisProject-SmartDrone/pds-files-to-db/workflows/Java%20CI%20with%20Maven/badge.svg)



This is a simple Java 11 Maven project that read in files in folder, extract their data and add them to the databse.

## How to launch (Eclipse)

After clonning the repository, go into "File > Import > Existing Maven Projects", and choose the right folder 
(a "pom.xml" must have been discovered if the project is valid). 
The only thing remaining is to click on "Finish" and see the project download dependencies itself.

Go into the src/main/resources folder and duplicate "application.properties.template" removing the ".template" extension from name 
(this will be your own properties for the project).

Now you can run the App class (in package "main") or the unit tests with FullTestSuite

> It is better to find using the "*CTRL+SHIFT+R*" shortcut

## How to compile (with command line)

Go at the root of the project folder with a terminal and type the command "*mvn clean install*" (you may have to download maven package).

> You need an internet connection in order to compile and build the project 

## Command line arguments

This is a command line application that waits for two (one optional) arguments :

 1. The path of the folder to parse
 2. (optional) the id where to start. Useful if we have a folder that contains already parsed files.

## The filesystem (WIP)
