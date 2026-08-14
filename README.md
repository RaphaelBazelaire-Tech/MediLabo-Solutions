# MediLabo-Solutions

Application de gestion médicale contre le diabète de type 2.

## Green Code

À la demande du client, nous allons expliquer les enjeux du Green Code et lister les pistes d'amélioration "Green" dans le projet Medilabo-Solutions.

-----

### Qu'est-ce que le Green Code, et pourquoi ?

Le Green Code, son objectif est d'écrire du code qui minimise l'usage des ressources (CPU, mémoire, stockage, réseau) pendant la phase d'éxecution.
Pratiquer le Green Code permet la réduction de l'empreinte énergétique du logiciel et les émissions de gaz à effet de serre associés.


*Deux principales idées dans le Green Code*

- **La sobriété numérique** : Faire mieux avec moins. Moins de code, moins de dépendances, moins de calculs et donc moins de données inutiles.


- **L'anticipation** : Selon *l'Institut du Numérique Responsable* (INR - https://institutnr.org/), 80% de l'empreinte environnementale d'un service numérique se prépare dès la conception.
Il est important d'y penser rapidement plutôt que d'optimiser son projet une fois terminé.

À noter : L'efficience énergétique **n'est pas** synonyme de performance. - Un logiciel rapide peut être énergivore.


Pour se renseigner sur le Green Code, il existe plusieurs références en France.

- **RGESN** - Référentiel Général d'Écoconception de Services Numériques (version 2024 - piloté par la DINUM, le Ministère de la Transition Écologique, l'ADEME et l'INR).


- **GR491** - Guide de Référence de Conception Responsable de Services Numériques (INR).

Leurs objectifs communs sont de réduire la consommation de ressources informatiques et énergétiques, ainsi que lutter contre l'obsolescence des équipements.

-----

### Comment identifier du code qui consomme des ressources inutilement ?

Vous pouvez appliquer plusieurs réflexes qui vous permettront de repérer facilement les problèmes.

- **Profiler avant d'optimiser** - Savoir où partent réellement les ressources du CPU et de la mémoire. (VisualVM, Java Flight Recorder, JProfiler)


- **Traquer les fuites de ressources** - Connexions bases de données, flux (streams) ou clients HTTP qui ne sont pas fermés qui peuvent retenir de la mémoire inutilement. 


- **Repérer le chargement massif en mémoire** - Charger une collection entière alors qu'une pagination / streaming suffirait. Récupérer les champs nécessaires plutôt que les objets complets.


- **Surveiller le Garbage Collector** - des GC fréquents qui trahissent souvent une création excessive d'objets (Exemple : Concaténation de chaînes dans une boucle > préférence pour un "**StringBuilder**").


- **Analyse statique orientée sobriété** - Plugin *ecoCode* pour **SonarQube** permet la détection automatique d'anti-patterns énergivores dans le code Java.

-----

### Ce que Medilabo-Solutions fait déjà avec le Green Code.

L'application respecte plusieurs principes de sobriété que l'on a énoncé précédemment.

- **Images Docker multi-stage** - Chaque microservice est packagé en deux temps (Build avec le JDK, exécution avec le seul JRE).
L'image finale ne contient ni Maven, ni le code source, ni le JDK, elle est donc plus légère, ce qui permet moins de stockage et de transfert.


- **Base de données normalisée (3NF)** - Cette normalisation évite la duplication de données, donc moins de stockage et des écritures plus légères.


- **Client HTTP moderne et léger** (```RestClient```) pour la communication inter-services.


- **Interface volontairement sobre** - Peu d'assets, peu de bande passante côté navigateur.

-----

### Des pistes d'amélioration "Green" pour l'application.

Il s'agit d'une liste possible d'amélioration sur l'application ```Medilabo-Solutions```, cependant il n'existe aucun ordre d'impact / effort dans cette liste.
L'ordre de réalisation importe peu.

- **Réduire les données transférées depuis MongoDB** - Le microservice ```microservice-note``` renvoie des objets ```Note``` complets alors que le calcul de risque ne demande que le texte des notes.
Une projection qui récupère que le champ ```note``` pourrait alléger ce qui concerne les requêtes et le réseau.


- **Éviter un aller-retour réseau superflu** - Actuellement, le microservice ```microservice-risque``` interroge les services ```patient``` et ```note``` depuis le gateway.
Un appel direct sur le réseau interne de Docker pourrait faire réduire le trafic ainsi que la latence, et donc par définition l'énergie consommé.


- **Indexer ```patientId``` dans MongoDB** - Un index sur le champ interrogé (```findByPatientId```) pourrait réduire le travail du serveur à chaque requête.


- **Paginer la liste des patients** - Actuellement, la liste charge tous les patients en mémoire. Produire une pagination éviterait de charger tous les patients quand le volume grandit.


- **Désactiver les logs verbeux en production** - Le formatage SQL actuel génère une log à chaque requête, l'objectif serait de réserver ce type de log au développement.

-----

### Des outils de mesures utiles pour le Green Code.

- **ecoCode (Plugin pour SonarQube)** - Analyse statique du code Java orientée sobriété.


- **VisualVM, Java Flight Recorder** - Profilage de la mémoire et du CPU de la JVM.


- **Spring Boot Actuator** - Suivi complet des métriques d'exécution.

-----

### Sources utilisés

- **Institut du Numérique Responsable** - Green code : écrivez du code vert ! 
(https://institutnr.org/green-code-ecrivez-du-code-vert)


- **IBM** - Qu'est-ce que le codage vert et pourquoi est-il important ?
(https://www.ibm.com/fr-fr/think/topics/green-coding)


- **Synapsys Groupe** - Green Coding : Comment développer de façon écoresponsable ?
(https://synapsys-groupe.com/blog/green-coding/)


- **RGESN 2024** - Référentiel Général d'Écoconception de Services Numériques
(https://ecoresponsable.numerique.gouv.fr/publications/referentiel-general-ecoconception/)


- **GR491** - Guide de Référence de Conception Responsable de Services Numériques (INR)
(https://gr491.isit-europe.org/)