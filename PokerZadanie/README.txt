UWAGI:
W programie brakuje javadoca, testow do serwera i klienta, obslugi wyjatkow i komunikacji przy uzyciu tokenow. Do konca semestru sprobuje doslac poprawiona wersje, to co jest tutaj to stan programu na 3.01.2024.

IN ORDER TO LAUNCH THE PROGRAM:
1. Open CMD/Bash/PowerShell
2. Go to "HotelZadanie" directory
3. Type "mvn clean install"
4. Go to PokerZadanie/poker-server/main/target
5. Type java -jar poker-server-1.0-SNAPSHOT-jar-with-dependecies
6. Type in the desired number of players
7. Go to PokerZadanie/poker-client/main/target
8. Type java -jar poker-client-1.0-SNAPSHOT-jar-with-dependecies 
repeat step 7 and 8 for the desired number of players
enjoy the game :)

FILES & DIRECTORIES:
poker-server - server files
poker-client - client files
poker-common - game files
sonarqube.pdf - sonarqube raport (stan na 3.01.2024)

