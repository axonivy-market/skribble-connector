# Skribble-Connector

[Skribble](https://www.skribble.com/de-de/) ist eine moderne Plattform für
digitale Signaturen, die rechtsverbindliche elektronische Signaturen gemäß den
europäischen Gesetzen bereitstellt. Nutzen Sie unseren Skribble-Konnektor, um
Zugriff auf die Skribble-Funktionen zu erhalten, wie z. B.

- Erstellen von Signaturen auf sichere und rechtskonforme Weise
- Überwachung von Signaturprozessen

Laden Sie einfach den Connector in Ihren Axon Ivy Designer herunter und starten
Sie den Demo-Prozess!

## Demo

Über die Demo können neue Signaturanfragen erstellt und überwacht werden. Wenn
eine Signaturanfrage erstellt wird, wird die Signaturüberprüfung über die
Plattform abgewickelt. Jeder Teilnehmer hat vollen Zugriff auf seine eigenen
Dokumente und kann diese unterzeichnen oder ablehnen.

In der Demo gibt es zwei Prozesse: Der erste Prozess ist ein einfacher Dialog
zum Erstellen einer neuen Signaturanforderung, und der zweite zeigt alle
Signaturanforderungen mit ihrem jeweiligen Status an.

Erstellen Sie eine neue Signaturanforderung, laden Sie Ihr zu signierendes
Dokument hoch und fügen Sie Ihren Unterzeichner hinzu.
![image](images/request1.png)

Loads all your documents from the Skribble platform. If you created successfully
a Request you will see it here. ![image](images/overview1.png)

Document view on the Skribble platform as a signer
![image](images/skribble_doc_view1.png)

Refresh the overview page and you will see the status on the signer and the
overall status are changed. ![image](images/overview2.png)

To get a Sign from a signer there are two easy options: -set the notify
parameter off the signer on true and he will receive directly a mail from the
skribble platform shorty after the request. -or if you want notify himself you
can disable the notify and send him the url over your own custom styled email.

Es gibt drei Optionen für die Signaturüberprüfung: SES, AES und QES.


## Setup

Bevor Signaturvorgänge zwischen den Diensten „ **” (Axon Ivy Engine,** ) und „
**” (Skribble-Plattform,** ) ausgeführt werden können, müssen diese Dienste
einander vorgestellt werden. Dies kann wie folgt erfolgen:

1. Erstellen Sie ein **-Konto** -Konto:
   **[hier](https://my.skribble.com/business/signup/?lang=en) **

2. ** Erstellen Sie einen Demo-API-Schlüssel unter **. Weitere Informationen
   finden Sie in der Admin-Dokumentation:
   **[hier](https://docs.skribble.com/business-admin/api/apicreate#create-api-keys).
   **

3. Open the `Configuration/variables.yaml` in your Designer and set the username
   and the apikey from which you get on the skribble platform

   ```
   # == Variables ==
    Variables:
      #set all parameters for Skribble-connector
      skribbleConnector:
        #username
        username: 'api_demo_xxxxx'   #<-- paste here your username
        #apikey
        #[password]
        authKey: ${decrypt:\u00AF\u00A8...}   #<-- paste here your apikey and encrypt it
   ```

4. Speichern Sie die geänderten Einstellungen und starten Sie einen
   Demo-Prozess.
