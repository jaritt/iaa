# IAA
Hausarbeit Internet Anwendungssysteme

# Benötigte Software

-	Java SDK 1.8.0_181
- H2 Datenbank 1.4.197
-	Tomcat 8.5.34
-	Intellij (optional, wird aber für diese Anleitung vorausgesetzt)

Versionen mit der gleichen Major-Versionsnummer funktionieren mit hoher Wahrscheinlichkeit ebenfalls, werden jedoch nicht unterstützt.
Die Folgenden Installationsschritte erfordern die Intellij IDE. Prinzipiell ist das Ausführen der Anwendung aber auch ohne möglich.

# Installation 

1.	Den Quellcode von https://github.com/jaritt/iaa herunterladen, bzw. mit Intellij auschecken. Alternativ: Den Code aus dem eingereichten zip-Verzeichnis installieren.
2.	Externe Bibliotheken mit Maven installieren.
3.	Sicherstellen, dass das Java Software Development Kit 1.8 für das Projekt eingetragen ist unter „Project Structure“ -> „Project“ -> „Project SDK“.
4.	Tomcat als Server hinzufügen
5.	Rechts oben auf „Edit Configurations…“ klicken.
6.	Eine neue lokale Tomcat Konfiguration erstellen. 
7.	Einen sprechenden Namen eingeben (optional).
8.	Als Application Server den Tomcat auswählen.
9.	Falls nicht vorausgewählt, als JRE das Project SDK 1.8 auswählen.
10.	Unter Deployment das Artefakt „library.app:war exploded“ zur Liste hinzufügen. 
11.	Starten der Anwendung über die soeben eingerichtete Tomcat Konfiguration.
