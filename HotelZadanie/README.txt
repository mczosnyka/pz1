IN ORDER TO LAUNCH THE PROGRAM:
1. Open CMD/Bash/PowerShell
2. Go to "HotelZadanie" directory
3. Type "mvn clean package"
4. Go to HotelZadanie/main/target
5. Type java -jar HotelZadanie-1.0-SNAPSHOT-jar-with-dependecies
6. This should open the program!

FILES & DIRECTORIES:
main - directory containing the program
utils - directory containing utilities needed for the program
JAVADOC - directory with the javadoc.
JAVADOC/indexall.html - javadoc.
HotelZadanie/main/target/hotelOutput - csv file. If you launch the program through jar, that's where the hotel info will save
HotelZadanie/differentHotelInput - csv file. You can use it to create a new hotel based on it.
'hotel' - overview - SonarQube.pdf - pdf file. It's a sonarqube raport