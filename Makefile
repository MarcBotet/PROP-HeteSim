#
# A simple makefile for compiling three java classes
#

# define a makefile variable for the java compiler
#
JCC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -g  -XDignore.symbol.file=true 
# typing 'make' will invoke the first target entry in the makefile 
# (the default one in this case)
#
# this target entry builds the Average class
# the Average.class file is dependent on the Average.java file
# and the rule associated with this entry gives the command to create it
#
*.class: 
	$(JCC) $(JFLAGS) */*/*.java */*/*.java */*/*/*.java */*/*/*/*.java */*/*/*/*/*.java
# To start over from scratch, type 'make clean'.  
# Removes all .class files, so that the next make rebuilds them
#
clean: 
	$(RM) */*/*.class */*/*.class */*/*/*.class */*/*/*/*.class */*/*/*/*/*.class

run:
	java -cp src Propies.Domini.Controladors.Main
	
	
Hetesim_Driver:
	java -cp src Propies.Domini.Controladors.Drivers.Hetesim_Driver
	
PerfilHetesim_Driver:
	java -cp src Propies.Domini.Controladors.Drivers.PerfilHetesim_Driver
	
PerfilHetesim_Stub:
	java -cp src Propies.Domini.Controladors.Drivers.PerfilHetesim_Stub

HIN_driver:
	java -cp src Propies.Domini.Controladors.Drivers.HIN_Driver
	
NewEdit_driver:
	java -cp src Propies.Domini.Controladors.Drivers.NewEdit_driver
	
ControllerPresentacioDomini_Driver:
	java -cp src Propies.Domini.Controladors.Drivers.ControllerPresentacioDomini_Driver

Perfil_Driver:
	java -cp src Propies.Domini.Controladors.Drivers.Perfil_Driver
	
	

Author_driver:
	java -cp src cluster.Drivers.Author_driver

Conference_driver:
	java -cp src cluster.Drivers.Conference_driver

Node_driver:
	java -cp src cluster.Drivers.Node_driver

Paper_driver:
	java -cp src cluster.Drivers.Paper_driver

Term_driver:
	java -cp src cluster.Drivers.Term_driver

Matrix_driver:
	java -cp src cluster.Drivers.Matrix_driver

Vertex_driver:
	java -cp src cluster.Drivers.Vertex_driver