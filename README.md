
# DISLOMAN 
*Progetto dell'Università degli Studi di Torino*
## DOCKER IMAGE BUILDING
### LOCAL
#### Configurazione
* Assicurarsi che il **docker file** sia presente nella directory del **pom.xml**.
* Assicurarsi che il file **pom.xml** contenga il seguente plugin:
```
<build>

  ...
  
  <plugin>
    <groupId>com.spotify</groupId>
    <artifactId>dockerfile-maven-plugin</artifactId>
    <version>VERSION</version>
  </plugin> 
  
</build>
```
In modo che venga eseguito la build dell'immagine *docker* durante la *build* di Maven

Eseguire il seguente comando
```
$ eval $(minikube docker-env)
```
che consente di fare riferimento al processo *docker* di *minikube* quando si esegue un comando del tipo: 
```
$ docker [OPTIONS] COMMAND
```

#### Build di Maven
Eseguire la build di Maven
```
$ mvn clean package
```
Durante il ```mvn package``` viene eseguita la build di una nuova immagine docker direttamente nella macchina virtuale di *minikube*, in modo che **non** sia **necessario** effettuare **```push```** della nuova immagine e  **```pull```** da *minikube*. Questo implica che sia possibile lavorare anche in locale per testare piccole modifiche del progetto che non necessitano l'aggiornamento dei repository remoti.

