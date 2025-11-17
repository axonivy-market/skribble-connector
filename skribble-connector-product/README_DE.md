# Skribble-Anschluss

[Skribble](https://www.skribble.com/de-de/) ist eine moderne digitale
Unterschrift Bahnsteig versieht jener legal verbindlich elektronische
Unterschriften konform mit europäisch Gesetze. Benutzen unser Skribble Anschluss
zu gewinnen zugreifen zu den Skribble Charakterzüge, mögen

- Schaffend herein Unterschriften #ein befestigen und legal konformen Weg
- Überwachend Unterschrift Arbeitsgänge

Einfaches #Herunterladen der Anschluss hinein euren #Axon Efeu Designer und
starten den Demo Arbeitsgang!

## Demo

Eine neue Unterschrift Bitten können sein geschafft und überwacht via die Demo.
Ob eine Unterschrift Bitte ist geschafft, die Unterschrift Verifikation will
sein bedient über den Bahnsteig. Jeder Teilnehmer hat vollen Zugang zu ihr
besitzen Dokumente und können signieren oder aussterben ihnen.

Auf der Demo ist dort zwei verarbeitet: Der erste Arbeitsgang zu schaffen ein
leichtes Zwiegespräch eine neue Unterschrift Bitte und die zweiten Vorstellungen
alle die Unterschrift Bitte mit ihr besitzen Zustand.

Schaffend einen neuen #Unterfertigter Bitte, #hochladen eure Dokument #welche
muss sein signieren und zufügen euren #Unterfertigter.
![Image](images/request1.png)

Ladungen alle eure Dokumente von die Skribbleplatform. Ob du schafftest
erfolgreich eine Bitte willst du sehen ihm hier. ![Image](images/overview1.png)

Dokumentieren #anschauen weiter das Skribbleplatform da einen #Unterfertigter
![Image](images/skribble_doc_view1.png)

Erfrisch das overviewpage und du willst den Zustand auf dem #Unterfertigter
sehen und dem overallstatus ist gewechselt. ![Image](images/overview2.png)

Zu bekommen ein Indiz von einem #Unterfertigter ist dort zwei leichte Optionen:
-Setzen das #bekannt geben ab Parameter den #Unterfertigter weiter wahr und er
will recieve direkt eine Post von die skribble Bahnsteig shorty nach der Bitte.
-Oder ob du willst #bekannt geben #er selbst du können ausschalten das notiy und
senden ihm das url über eure besitzen Brauch stylte #mailen.

Dort sind drei Optionen für signieren Verifikation: SES, AES Und QES


## Einrichtung

Vor irgendwelche #Unterschrift Interaktionen zwischen die **#Axon Efeu Motor**
und der **Skribble-Bahnsteig** Bedienungen können sein gerannt, sie müssen sein
hereingebracht zu #jede #andere. Dies kann sein getan folgendermaßen:

1. Schaff ein **Verrechnen** verrechnen:
   **[Hier](https://my.skribble.com/business/signup/?lang=en) **

2. Schaff ein **Demo API #eintasten** für #mehr Auskunft Sprung zu den admin
   Dokumentation:
   **[Hier](https://docs.skribble.com/business-admin/api/apicreate#create-api-keys)
   **

3. Öffnen das `Konfiguration/Variablen.yaml` In eurem Designer und setzen den
   Benutzernamen und den apikey von welcher du machst weiter den
   skribbleplattform

   ```
    # == Variables ==
    Variables:
      #set all paramaters for Skribble-connector
      skribbleConnector:
        #username
        username: 'api_demo_xxxxx'   #<-- paste here your username
        #apikey
        #[password]
        authKey: ${decrypt:\u00AF\u00A8...}   #<-- paste here your apikey and encrypt it

   ```

4. Speicher die #abgeändert Lagen und starten einen Demo Arbeitsgang
