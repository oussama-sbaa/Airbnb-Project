# Description du projet

Le projet est une application de réservation de séjours en ligne. Elle est accessible en 3 modes différents: non authentifié, user et hôte. Chaque mode permet d'accéder à des fonctionnalités différentes.

Le mode non authentifié permet à l'utilisateur d'accéder à la page d'accueil de l'application. Cette page affiche une liste de tous les séjours ainsi qu'une barre de recherche. Elle permet également d'accéder au formulaire de connexion en tant que voyageur ou hôte.

Une fois authentifié en tant que user, l'utilisateur accède à la page d'accueil. Cette page affiche une barre de recherche ainsi qu'une liste de tous les séjours disponibles. Le user peut écrire un commentaire relatif à un séjour spécifique. Il peut également ajouter un ou plusieurs voyages à son panier. Le panier affiche la liste de tous les voyages sélectionnés par le user. Il a la possibilité de supprimer des séjours et de valider son panier pour effectuer des demandes de réservations aux hôtes concernés. Enfin, le user a accès à une page récapitulant tous les séjours qu'il a réservé (réservations validées et demandes en cours).

En tant qu'hôte, l'utilisateur accède à la page d'accueil contenant un formulaire de recherche et la liste des séjours. Il a également accès à une liste des séjours qu'il propose uniquement. L'hôte peut accéder à une liste de toutes les demandes de réservation en cours qu'il peut valider ou annuler. Enfin, l'hôte a accès à un planning récapitulant toutes les réservations validées pour les séjours qu'il propose.

## Le projet est développé en JavaFX, et est lié à une base de données MySQL.

# Installation et exécution

- Cloner le projet depuis GitHub
- Importer le projet dans un environnement de développement compatible avec JavaFX (par exemple, IntelliJ IDEA)
- Configurer les paramètres de connexion à la base de données dans le fichier application.properties
- Exécuter l'application en lançant la classe principale Main.java

# Crédits

Ce projet a été développé par Oussama SBAA et Ali MTAHRI.
