# iaa
Hausarbeit Internet Anwendungssysteme

# Software Requirements

1. Intellij
2. H2 Database (http://www.h2database.com/html/main.html)
3. Tomcat 8.5.34 (https://tomcat.apache.org/download-80.cgi) choose windows zip version

# Installation

1. Checkout repo with intellij or download zip and import into intellij
2. Install maven dependencies with intellij
3. Add Configuration -> + -> Tomcat Server Local: Set your tomcat folder as application server.
4. In the same Config window you see "No artifacts marked for deployment". Click fix and choose library.orm exploded.
5. If needed, set the project sdk in the "project structure" window.
6. If you dont have the sdk installed, you can download it here: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
