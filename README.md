# Projet Slither

## Utiliser le projet

### Decompresser le ficier slither.zip
Après avoir télécharger le fichier _slither.zip_, placer le dans le répertoire souhaité et décompresser le.

### Compiler le projet
Placer vous dans le répertoire que vous venez de décompresser et utiliser la commande suivante:
```mvn compile```. Le projet est maintenant compiler

### Executer le projet
Après avoir compiler le projet, toujours dans le même répertoire, utilisez la commande:
```maven package```. Un fichier executable _.jar_ est alors créer à la racine du projet. C'est l'executable du projet. Il suffit allors d'executer ce fichier _.jar_.

### Utiliser l'interface
A l'execution le projet s'ouvre sur un menu avec différent bouton. Selectionner les options deux jeu que vous souhaitez et appuyer sur _"Play"_ pour lancer le jeu. A la mort du joeur principale, le jeu se ferme.

## Diagramme des classes & Structure du projet
A la racine du projet, vous pourrez trouver le fichier _diagram.png_. Ce fichier image contient un diagrame des classes permettant de mieux saisir la structure du projet.
Sur ce diagrame, on peut remarquer la structure principale du projet:
- Les GraphicalObject sont l'ensemble des objets que l'on peut afficher et avec lesquels on peut interagir. On y retrouve notament les Fruit, les Snake et les SnakeCell.
- Les GameController sont les "chef d'orchestre" du jeu. Ils permettent d'initialiser la scène et de faire jouer chaque étape du jeu.
- La SlitherScene, est la classe permettant d'initialize les handlers pour les ControllableSnake et permet de transferer une représentation du jeu (Les listes d'objets graphiques principalement) afin que les serpents puissent choisir leur déplacement de manière plus avisé.
- Screen sert à représenter et identifier l'ecran.
- Position sert de type construit pour une paire de coordonné voir des vecteurs.
- GameLoopTimer sert à la gestion du temps entre chaque mise à jour du jeu.
- Utils est un ensemble de méthode static permettant de faire certain calcule plus ou moins complexe.
