# plants-gui-app
A simple GUI window displaying information about plants

Requirements
Before running the project
NetBeans IDE
MySQL Server 8
MySQL Workbench
MySQL JDBC Driver (mysql-connector-j-9.x.x.jar) Or the database is not connected with NetBeans


How to prepare the Database

Open mySQL Workbench
Click the (Execute) button to create the database 
Make sure the database name is PlantReferenceSystem


How to Run the Project in NetBeans
Open NetBeans 
open the project folder
Right-click the project -> Properties -> Libraries
Click 
 next to Classpath → Add JAR/Folder
Select the file mysql-connector-j-9.x.x.jar
 then Click OK
Open Main.java
Press F6 or click Run to start the application


How to Log In
Role Email Password Admin shahha@gmail.com123Useruser1@gmail.com1234

Project Structure
src
 javaproject
Main.java                 <-Starting point
DatabaseConnection.java
Plant.java 
FlowerPlant.java 
HerbPlant.java 
SucculentPlant.java 
IndoorPlant.java 
Person.java 
Admin.java
RegularUser.java
CareGuide.java
Disease.java
PlantDAO.java
UserDAO.java
CareGuideDAO.java
DiseaseDAO.java
FileUtil.java




Features

Login with role (Admin / User)
Browse and search plants
View care guides
Diagnose diseases by symptoms
Admin: Add, Update, Delete plants
Export / Import CSV files
Serialize and load .ser files
