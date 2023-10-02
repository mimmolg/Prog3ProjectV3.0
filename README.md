# Prog3ProjectV3.0
Questo progetto mira a sviluppare un’applicazione dedicata al campo della logistica. L’obiettivo principale è quello di offrire un sistema efficiente per gestire la consegna delle merci, avvalendosi della collaborazione di diverse aziende di trasporto, o corrieri.
Ogni azienda di trasporto, o corriere, possiede una serie di veicoli, ciascuno identificato da un codice univoco, tipo di veicolo, e capacità del container. Quest’ultimo dettaglio è fondamentale, in quanto determina il numero di colli, o pacchi, che il veicolo può trasportare. 
Ogni collo, a sua volta, è identificato da un insieme di informazioni, tra cui un codice univoco, il mittente, il destinatario e il suo peso.
L’applicazione, oltre a gestire queste informazioni di base, implementa anche un algoritmo approssimato, noto come “Next Fit”, per risolvere il problema del Bin Packing. Questo algoritmo garantisce i veicoli siano caricati in modo ottimale, minimizzando gli spazi inutilizzati e massimizzando la capacità di carico.
Ulteriormente, il sistema permette ai corrieri di aggiornare lo stato dei colli ad ogni centro di smistamento. Questa funzionalità è fondamentale, poiché permette ai destinatari di rintracciare i loro pacchi in tempo reale, utilizzando un codice di spedizione univoco.