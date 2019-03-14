# Protokoll

### Schritt 1
##  Lokalen CraftBukkit Server aufsetzen

Schritt 1 unseres Projekts war es auf unseren lokalen Rechnern einen CraftBukkit Server aufzusetzen und zum laufen zu bekommen.
Hierzu wurde einfach auf Windows eine Batch-file und auf Linux ein Shell-script ausgeführt.

Um Plugins auf dem Server verwenden zu können musste im Server-Ordner ein "plugins" Ordner erstellt werden.

![Ordnerstruktur vom Server auf Windows](https://lh3.googleusercontent.com/fhV2x3DQ5OxFwsZPPsEK0odGyOQnwZhNmaRFm1S1CaTA_6i0HW5GP_TN-l3NFs88ISk73FA1QY7g "Ordnerstruktur vom Server auf Windows")

Ordnerstruktur vom Server auf Windows

### Schritt 2
## Erstes Plugin Schreiben

Das erste Plugin sollte erstmal nicht viel können. Es soll lediglich einen Text (in diesem Fall "Hello World") in die Server-Console schreiben, wenn der Server gestartet wird.

Um ein Plugin auf dem Server benutzen zu können, muss das Plugin als [filename].jar exportiert werden und in den plugins-Ordner auf dem Server kopiert werden.
Falls die .jar-Datei verändert oder erneuert wird muss ein Operator des Servers den Befehl "/reload" ausführen.

![first Plugin output](https://lh3.googleusercontent.com/PlSJKs03lmLS_3vZOWHsYKERUv85VUEv230vgS2ZwB6tdzB0ynPOqzGDdOso1cPWQO0AX1xf_Dg_ "first Plugin output")

So sieht der Output in der Console aus

![first Plugin code](https://lh3.googleusercontent.com/SdCSetHVLTjF2p1sRdD6dTZFf8Fn6n7Nm45xTHxbHt8GyegsRb3d77JIRJhBGt1BywxRA8K-bk-t "first Plugin code")

Für dieses Plugin wird lediglich in der "onEnable"-Methode eine Nachricht per logger gesendet.


### Schritt 3
## Erstes richtiges Plugin
Das erste richtige Plugin soll es dem Benutzer erlauben eine TNT-Box beliebiger Größe erschaffen zu lassen. Dies soll über den Befehl "/boom [size]" funktionieren.
Wird keine beliebige Größe eingegeben, so wird eine Box der Standardgröße (4x4x4) erschaffen.

Dies soll dann so aussehen:

![boom command](https://lh3.googleusercontent.com/41kHN6GDpvK9-wW0Tbe3goL_ITudWCWPYSPTwukhVOx4UITVc4rOo_IVWvoMMta_NjKFALFirlTJ "boom command")

![TNT Box](https://lh3.googleusercontent.com/Vft97PX4bP2_zvIEVSQJI2JHZ_K9a_39wjPFi_8WMZ_0r2nfufKc-W2XMDgNg-qo5jpa-RRyh4QD "TNT Box")

**Der Code:**

![buildBox code](https://lh3.googleusercontent.com/Dw_rNBLHddqBEmaX-HIj_XP0kKMgE-bHZJuv3kfSlIpZC6_IHOeva-1FYh_T3ovoR3aCzRZjlJzP "buildBox code")

Am Anfang wird überprüft ob eine beliebige Größe eingegeben wurde bzw. ob diese einer Zahl entspricht. Ansonsten wird im Ingame-Chat ein Fehler ausgegeben.
Im zweiten Teil des Codes wird mit 3 verschachtelten For-Schleifen die Box gebaut.
Zuletzt wird noch eine Rückmeldung gegeben, wie viele TNT erschaffen worden sind und ob ein Feuerzeug in das Inventar des Spielers gegeben wurde.

### Schritt 4
## Hausbau-Plugin
Wie der Name schon sagt, soll das Plugin auf Befehl eines Spielers ein Haus der gewünschten Größe bauen. Das Haus soll eine Tür beinhalten, ein einfaches Dach besitzen und so aussehen:

![build command](https://lh3.googleusercontent.com/S_caQtVOenlYhRrZn-vP8CQFjIVbWdr4ei8niWMX6URO26dzIthhZdKcYXzIQ5w1z8OwxmPHepIb "build command")

![house](https://lh3.googleusercontent.com/vXBYzxbQTjSd0Fn19H9H4n9nt1RqFSw3Y3PinKBsjXupDwHthc3zFRH7RmnNBA9FpVRDhUdSXTWA "house")

**Der Code:**

**Klasse House:**

![](https://lh3.googleusercontent.com/Yer0Cdl4sS56LGoBcveyBXe5w3KOCWw8XJDSR4Sws1_vI50NZMNpCuw4Ma7gZlhdEKPVgmJsqNV9 "class House1")

![](https://lh3.googleusercontent.com/yc1eTn_kbw0-XXCJMeJl9i0pde79cEbI_7bDrlP3Y9wrDSCYVHQBKOuE_2St4w6IQk21rYuLRD4H "Class House2")

In einem House Objekt werden die Dimensionen, die Koordinaten und ein Array in dem Alle zum Haus gehörigen Blöcke gespeichert sind. Außerdem verfügt es über eine destroy() Methode, die das Haus aus der Spielwelt entfernt.

**Klasse HouseManager:**

![enter image description here](https://lh3.googleusercontent.com/Rsx8SVro6X76Rx9Epf-Es-0U19-xijeTEtLleao39FqsYDZpEYgxwDqwKFCWnjKkxfZJLcB90r1b "housemanager")

Der HouseManager verwalted eine Liste von Häusern. Mit den Methoden addHouse() und destroyHouse() können Häuser hinzugefügt und wieder entfernt werden. Alle Häuser sind in einer HashMap abgespeichert. Die Methode listHouses() ermöglich es die Informationen (ID, Dimensionen, Koordinaten) aller existierenden Häuser im Chat des Spielers auszugeben.

**Klasse HouseBuilder:**

![enter image description here](https://lh3.googleusercontent.com/gexCU4XRJq5Inp5KIijCU6OYWs7K5ysysNO4iQJkQyJJLTREGvhWxbo8veWKcnNavSy57gu5moaz "housebuilder1")

![enter image description here](https://lh3.googleusercontent.com/0BddxN2eagbL5Gshmfe9kdnlI9h0dlHZebVNzyPm-xyogm6TKvM2omJc9KzK4c0WCnv9k6-LHFh7 "housebuilder2")

Das HouseBuilder Objekt, welches im HouseManager angelegt wird, ist dafür zuständig alle Blöcke eines Hauses in die Spielwelt zu setzten. Dafür verwendet es einen Thread, welcher bei jedem Update immer einen Block des Hauses setzt. Dadurch taucht das Haus nicht von einem Moment auf den Anderen auf, sondern wird Block für Block vor dem Spieler aufgebaut.
