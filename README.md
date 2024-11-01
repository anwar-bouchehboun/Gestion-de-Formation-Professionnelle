# API REST de Gestion des Formations

Cette API REST est destinée à digitaliser la gestion des formations d'un centre de formation. Elle facilite la gestion des apprenants, formateurs, formations et classes via des endpoints organisés et documentés.

## Table des matières

- [Fonctionnalités](#fonctionnalités)
- [Architecture](#architecture)
- [Technologies Utilisées](#technologies-utilisées)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Documentation de l'API](#documentation-de-lapi)

## Fonctionnalités

L'API inclut des fonctionnalités complètes de gestion de centre de formation, telles que :

- **Gestion des Apprenants** : Inscription, modification, suppression, et visualisation des apprenants.
- **Gestion des Formateurs** : Création, modification, suppression, et visualisation des formateurs.
- **Gestion des Formations** : Planification, modification, suppression, et visualisation des formations.
- **Gestion des Classes** : Création, modification, suppression, et visualisation des classes.

Chaque entité possède des endpoints CRUD complets, ainsi que des options avancées comme la pagination et la recherche personnalisée.

## Architecture

Le projet est structuré en couches selon l'architecture MVC pour garantir une meilleure organisation du code :

1. **Entités** : Représentation des objets `Apprenant`, `Formateur`, `Formation`, et `Classe` avec des annotations JPA et des validations de données.
2. **Repositories** : Interfaces pour l'accès aux données, étendant `JpaRepository`.
3. **Services** : Contient la logique métier de l'application.
4. **Contrôleurs (REST)** : Fournit des endpoints pour les opérations CRUD sur chaque entité.
5. **Exceptions** : Gestion des erreurs centralisée.
6. **Utilitaire** : Fonctions auxiliaires et helpers.
7. **Tests** : Tests unitaires et d'intégration pour assurer la qualité du code.

## Technologies Utilisées

Le projet utilise les technologies et outils suivants :

- **Spring Boot** pour la gestion de la configuration et des dépendances.
- **API REST** avec les opérations CRUD basées sur les standards RESTful.
- **Base de Données** :
  - **H2** en environnement de développement pour des tests rapides.
  - **PostgreSQL** pour l'environnement de production.
- **Documentation d'API** : Swagger pour faciliter l'exploration et la documentation de l'API.
- **Maven** pour la gestion des dépendances.
- **Java** 8, avec des fonctionnalités modernes (Stream API, Lambdas, Java Time API).
- **JUnit et Mockito** pour les tests.
- **JaCoCo** pour la mesure de la couverture du code.

## Prérequis

Avant de commencer, assurez-vous d'avoir installé les éléments suivants :

- **Java 8** ou version supérieure
- **Maven** 3.x ou version supérieure
- **PostgreSQL** pour l'environnement de production

## Installation

Clonez le dépôt et installez les dépendances Maven avec les commandes suivantes :

```bash
git clone https://github.com/anwar-bouchehboun/Gestion-de-Formation-Professionnelle
cd Formation
mvn install
```

## Documentation de l'API SWAGGER

```
  http://localhost:8080/swagger-ui/index.html#/

```

## Planification Jira

```
https://anouarab95.atlassian.net/jira/software/projects/FOR/boards/13/backlog
```
