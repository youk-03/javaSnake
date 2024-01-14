# Projet Slither
[le git du projet](https://gaufre.informatique.univ-paris-diderot.fr/nico/slither)

## Utiliser le projet

### Décompresser le ficier slither.zip
Après avoir téléchargé le fichier _slither.zip_, placez le dans le répertoire souhaité et décompressez le.

### Compiler le projet
Placez vous dans le répertoire que vous venez de décompresser et utilisez la commande suivante:
```mvn compile```. Le projet est maintenant compilé

### Executer le projet
Après avoir compilé le projet, toujours dans le même répertoire, utilisez la commande:
```maven package```. Un fichier éxecutable _.jar_ est alors créé à la racine du projet. C'est l'éxecutable du projet. Il suffit alors d'éxecuter ce fichier _.jar_.

### Utiliser l'interface
A l'éxecution le projet s'ouvre sur un menu avec différents boutons. Selectionnez les options de jeu que vous souhaitez et appuyez sur _"Play"_ pour lancer le jeu. A la mort du joueur principal, le jeu se ferme.

## Diagramme des classes & Structure du projet
A la racine du projet, vous pourrez trouver le fichier _diagram.png_. Ce fichier image contient un diagramme des classes permettant de mieux saisir la structure du projet.
Sur ce diagramme, on peut remarquer la structure principale du projet:
- Les **GraphicalObject** sont l'ensemble des objets que l'on peut afficher et avec lesquels on peut intéragir. On y retrouve notamment les **Fruit**, les **Snake** et les **SnakeCell**.
- Les **GameController** sont les "chef d'orchestre" du jeu. Ils permettent d'initialiser la scène et de faire jouer chaque étapes du jeu.
- La **SlitherScene**, est la classe permettant d'initialiser les handlers pour les ControllableSnake et permet de transférer une représentation du jeu (Les listes d'objets graphiques principalement) afin que les serpents puissent choisir leurs déplacements de manière plus avisé.
- **Screen** sert à représenter et identifier l'écran.
- **Position** sert de type construit pour une paire de coordonnées voir des vecteurs.
- **GameLoopTimer** sert à la gestion du temps entre chaque mise à jour du jeu.
- **Utils** est un ensemble de méthodes static permettant de faire certain calculs plus ou moins complexe.
