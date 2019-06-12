Umgebungsvariablensetzen
Beispiel: Java-Version
cf set-env pal-tracker JBP_CONFIG_OPEN_JDK_JRE '{ jre: { version: 11.+ } }'

Einloggen
cf login

Anwendung starten
cf start $NAME

Anwendung neustarten
cf restart $NAME

Anwendung neu stagen
cf restage $NAME

Unterschied zwischen restart und restage: Für reine Umgebungsänderungen (zB Umgebungsvariablen ändern) reicht restart, für Änderungen innerhalb des "Containers" wird restage benötigt.


Anwendung pushen
cf push $ANWENDUNGSNAME -p path/to/jarfile.jar --random-route 
Es bietet sich an, das über eine manifest.yml zu automatisieren. Diese wird von cf push automatisch beachtet. 
Beispiel:
	---
	applications:
	  - name: pal-tracker
	    path: build/libs/pal-tracker.jar
	    routes:
	      - route: pal-tracker-rebecca-cramer.apps.evans.pal.pivotal.io
	    env:
	      WELCOME_MESSAGE: Hello from the review environment
	      JBP_CONFIG_OPEN_JDK_JRE: '{ jre: { version: 11.+ } }'

Routen
Routen müssen Foundation-eindeutig sein. Dies kann erreicht werden über random-routen und überprüft werden mit:
cf check-route route-zur-anwendung link.to.foundation.io
Bsp: cf check-route pal-tracker-12315364358438 apps.evans.pal.pivotal.io
Es ist im allgemeinen einfacher, die Routen über die Weboberfläche zu konfigurieren. Es können mehrere Routen auf eine Anwendung zeigen.

