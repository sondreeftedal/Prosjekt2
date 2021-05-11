# Tic tac toe

#Fremgangsmåte
I denne oppgaven fikk vi på forhånd laget en backend for oss. For å benytte oss av denne brukte vi API-kall gjennom Volley. Disse kallene ble plassert i funksjoner i en egen Service-klasse. Appen består av 3 aktiviteter og to ulike dialog fragments. Brukeren vil først få opp en startside der de kan velge mellom å lage et nytt spill eller bli med i et eksisterende spill. 

Nøkkelen til kommunikasjonen mellom spillerene ligger i POLL-requesten til APIen. Hvert 5.sekund vil en countdownTimer utløse denne poll-funksjonen og oppdatere verdiene slik at to enheter kan spille mot hverandre. Først vil en spiller opprette et spill, et textview med teksten "Waiting for opponent.." vil oppdateres med motstanderens navn når vi får et callback med en ny spiller. Denne callbacken vil også oppdatere symbolene i rutene, sjekke om en spiller har vunnet og holder kontroll på hvilken spiller som skal ha neste trekk. 

Når en spiller har fått tre på rad, vil det startes opp en ny activity som annonserer vinneren. Dersom det blir uavgjort, vil det bare stå "Draw" på skjermen.

#Skjermbilder: 
Startskjerm:
![Screenshot_1620729480](https://user-images.githubusercontent.com/66576015/117802683-11c53300-b256-11eb-8b83-48aad04c8510.png)

Dialog Fragment for å opprette nytt spill:
![Screenshot_1620729503](https://user-images.githubusercontent.com/66576015/117802783-2bff1100-b256-11eb-9e42-e5bd50f7a9c9.png)

Venter på motstander:
![Screenshot_1620729520](https://user-images.githubusercontent.com/66576015/117802860-40dba480-b256-11eb-8439-f87349169e16.png)

Motstander har joinet spillet:
![Screenshot_1620729550](https://user-images.githubusercontent.com/66576015/117802913-50f38400-b256-11eb-8132-41d4d7d7d5b7.png)

Tre på rad: 
![Screenshot_1620729600](https://user-images.githubusercontent.com/66576015/117802949-59e45580-b256-11eb-941d-42f7572441de.png)

Vinner annonseres:
![Screenshot_1620729444](https://user-images.githubusercontent.com/66576015/117802982-62d52700-b256-11eb-8e6c-385d3418ec0f.png)


Link til apk: https://appdistribution.firebase.dev/i/cd8f36e2c0246c2d
