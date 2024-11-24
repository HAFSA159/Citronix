# Projet Citronix

## Contexte du Projet
Le projet Citronix consiste à développer une application de gestion pour une ferme de citrons, permettant aux fermiers de suivre la production, la récolte, et la vente de leurs produits. L'application doit faciliter la gestion des fermes, champs, arbres, récoltes et ventes, tout en optimisant le suivi de la productivité des arbres en fonction de leur âge.

## Fonctionnalités Principales
- **Gestion des Fermes :**
    - Créer, modifier et consulter les informations d'une ferme (nom, localisation, superficie, date de création).
    - Recherche multicritère (Criteria Builder).
- **Gestion des Champs :**
    - Associer des champs à une ferme avec une superficie définie.
    - Assurer la cohérence des superficies : la somme des superficies des champs d'une ferme doit être strictement inférieure à celle de la ferme.
- **Gestion des Arbres :**
    - Suivre les arbres avec leur date de plantation, âge, et champ d'appartenance.
    - Calculer l'âge des arbres.
    - Déterminer la productivité annuelle en fonction de l'âge de l'arbre :
        - Arbre jeune (< 3 ans) : 2,5 kg / saison.
        - Arbre mature (3-10 ans) : 12 kg / saison.
        - Arbre vieux (> 10 ans) : 20 kg / saison.
- **Gestion des Récoltes :**
    - Suivre les récoltes par saison (hiver, printemps, été, automne).
    - Une seule récolte par saison (tous les 3 mois).
    - Enregistrer la date de récolte et la quantité totale récoltée.
- **Détail des Récoltes :**
    - Suivre la quantité récoltée par arbre pour une récolte donnée.
    - Associer chaque détail de récolte à un arbre spécifique.
- **Gestion des Ventes :**
    - Enregistrer les ventes avec la date, le prix unitaire, le client, et la récolte associée.
    - Calcul du revenu : `Revenu = quantité * prixUnitaire`.

## Contraintes
- Superficie minimale des champs : La superficie d'un champ doit être au minimum de 0.1 hectare (1 000 m²).
- Superficie maximale des champs : Aucun champ ne peut dépasser 50% de la superficie totale de la ferme.
- Nombre maximal de champs : Une ferme ne peut contenir plus de 10 champs.
- Espacement entre les arbres : Chaque champ doit contenir un nombre d'arbres tel que la densité maximale est de 100 arbres par hectare (10 arbres par 1 000 m²).
- Durée de vie maximale des arbres : Un arbre ne peut être productif au-delà de 20 ans ; après cet âge, il est considéré comme non productif.
- Période de plantation : Les arbres ne peuvent être plantés qu'entre les mois de mars et mai.
- Limite saisonnière : Chaque champ ne peut être associé qu'à une seule récolte par saison.
- Arbres non récoltés deux fois : Un arbre ne peut pas être inclus dans plus d’une récolte pour une même saison.

## Exigences Techniques
- **Spring Boot** : Utilisé pour créer l'API REST.
- **Architecture en couches** (Controller, Service, Repository, Entity).
- **Validation des données** avec annotations Spring.
- **Utilisation des interfaces et implémentation**.
- **Gestion centralisée des exceptions**.
- **Tests unitaires** avec JUnit et Mockito.
- **Lombok et Builder Pattern** pour simplifier la gestion des entités.
- **MapStruct** pour la conversion entre les entités, DTO et View Models.

## Installation

1. Clonez le projet dans votre répertoire local :
   ```bash
   git clone https://github.com/HAFSA159/Citronix
   
   cd citronix

### Diagramme de classe
Voici un diagramme UML représentant les principales classes du projet, leur relations et les attributs associés. 
![Diagramme de Classe](UML/DiagClasse.PNG)
## Utilisation
- L'API expose des endpoints pour gérer les fermes, champs, arbres, récoltes et ventes.
- Vous pouvez interagir avec l'API via des requêtes HTTP (GET, POST, PUT, DELETE).

### Exemple d'Endpoint

#### Créer une ferme
- **Méthode HTTP** : `POST`
- **Endpoint** : `/fermes`
- **Corps de la requête** :

  ```json
  {
    "name": "Farm B",
    "area": 500.0,
    "location": "Location B"
  }
  ```

## Architecture
L'architecture de l'application est basée sur une structure en couches, incluant les composants suivants :

- **Controller** : Gère les requêtes HTTP et interagit avec les services. C'est ici que les utilisateurs ou les systèmes externes interagissent avec l'application.

- **Service** : Contient la logique métier de l'application. Les services font appel aux repositories pour interagir avec la base de données et contiennent les règles de gestion liées aux entités métier.

- **Repository** : Gère la persistance des données. Cette couche est responsable de l'accès aux données, qu'il s'agisse d'une base de données relationnelle ou d'un autre mécanisme de stockage.

- **Entity** : Définit les objets persistants qui sont mappés directement à la base de données. Une entité représente une table dans la base de données.

- **DTO (Data Transfer Object)** : Les DTO sont utilisés pour transférer des données entre les couches de l'application, notamment entre le Controller et le Service. Ils permettent de découpler l'interface de l'API des entités métier, et d'assurer une communication plus claire et efficace des données nécessaires.

- **Mapper** : Cette couche contient les mappers qui servent à convertir les objets **Entity** en **DTO** et vice versa. Les mappers permettent de simplifier la transformation des données et d'éviter la duplication de code. Un outil comme **MapStruct** est souvent utilisé pour générer automatiquement ces mappers.

### Exemple de flux de données :
1. Lorsqu'un utilisateur envoie une requête via le Controller, le Controller utilise le Service pour traiter la demande.
2. Le Service peut appeler le Repository pour récupérer des données de la base de données sous forme d'**Entity**.
3. Avant d'envoyer les données au Controller, les entités sont converties en **DTO** via la couche Mapper.
4. Le Controller renvoie ensuite les **DTO** au client sous forme de réponse JSON.


