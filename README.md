# EOLrepo
 Descrizione ReplaceEol.jar

L’applicazione ReplaceEol.jar ha il compito di sostituire gli end of line dei file contenuti in un dato percorso con l’end of line previsto per dal formato Windows oppure Unix, scelto dall’utente.

L’applicazione sviluppata prende in ingresso due parametri obbligatori :
-	Il Path, che indica il percorso della cartella dal quale iniziare l’analisi e l’elaborazione;
-	L’opzione -w o -u, che indicano rispettivamente il formato dell’end of line per Windows ed il formato dell’end of line per Unix.
L’applicazione esamina tutti i file presente nel percorso indicato sostituendo opportunamente l’end of line con quello indicato dal parametro, se i file trovati fossero delle cartelle verrebbero analizzati i file all’interno.

Requisiti

Java 7 or newer.

How to use

1.	Aprire una nuova finestra del terminale
2.	Utilizzare il comando cd per posizionarsi nella cartella in cui è presente il file jar 
3.	Una volta raggiunta la cartella contenente il file jar, per avere informazioni sui parametri di ingresso del jar scrivere questa riga di comando:
java -jar ReplaceEol.jar -h
4.	Per eseguire il jar scrivere la seguente riga di comando:
java -jar ReplaceEol.jar <Path> [option1] [option2] [option3]
dove
-	Path indica il percorso della cartella di partenza da analizzare;
-	option1 può essere -w o -u a seconda che si voglia convertire l’attuale end of line nel formato Windows (con -w) o Unix (con -u);
-	option2 può essere -f che indica la modalità forced, se viene definita l’opzione -f in caso di eventuali errori l’applicazione si ferma e termina l’esecuzione; se non viene definito –f allora in caso di eventuali errori l’applicazione continua ad elaborare i successivi files tralasciando le eccezioni;
-	option3 può essere -v che indica verbose mode, cioè visualizzando i path di ogni file o di ogni cartella che viene analizzata ed elaborata.
5.	Se è necessario eseguire l’analisi su altri percorsi non inclusi in quello precedente, riavviare il jar inserendo il Path desiderato.


