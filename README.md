# Mini Jeu de Calcul Mental - Projet Développement Mobile

## Introduction

Ce projet consiste à développer une application mobile sous Android 9.0, offrant un mini-jeu de calcul mental. L'application est multilingue (Français + une autre langue) et comporte plusieurs activités principales, telles que le jeu, les scores, et une page d'informations.

## Fonctionnalités

### Activité Menu Principal

- **Bouton vers l'écran de jeu** : Lance l'activité du jeu.
- **Bouton vers la page highscore** : Affiche les meilleurs scores.

### Activité Jeu

- **Toolbar** : Affiche le score actuel et le nombre de vies restantes.
- **TextView** : Affiche un calcul généré aléatoirement.
- **Mécanisme de jeu** :
  - Chaque bonne réponse : Score +1
  - Chaque mauvaise réponse : Vie -1
  - Si le nombre de vies atteint 0, redirection vers l'activité d'enregistrement du score.

### Activité Enregistrement

- **Champ de saisie de texte** : Pour entrer le pseudo du joueur.
- **Bouton Enregistrer** : Sauvegarde le score et le pseudo dans la base de données.

### Activité High Score

- **Affichage du meilleur score** : Au chargement, affiche les informations du meilleur score.

### Activité À Propos

- **Informations des développeurs** : Affiche les noms des membres du binôme.
- **Feature en plus** : Peut contenir des fonctionnalités supplémentaires.

## Notes et Critères d'Évaluation

1. **Partie Visuelle : User Expérience**
   - Évaluation de l'interface utilisateur et de l'expérience globale.
   
2. **Partie Java : Fonctionnement Général de l'Application**
   - Évaluation du code Java et du fonctionnement de l'application.

3. **Partie Base de Données**
   - Initialisation de la base de données.
   - Sauvegarde des données.
   - Récupération des données.

## Installation

1. **Cloner le dépôt** :
   ```sh
   git clone https://github.com/Antto0509/ProjetMobile
2. **Ouvrir le projet dans Android Studio.**
3. **Compiler et exécuter l'application sur un émulateur ou un appareil physique sous Android 9.0.**

## Utilisation

### Menu Principal
L'application démarre sur le menu principal avec un champ de saisie et deux boutons :
- **Pseudo** : Le joueur rentre son pseudo
- **Jouer** : Lance l'activité de jeu.
- **Score** : Affiche le meilleur score enregistré.

### Jeu
- Un calcul aléatoire apparaît à l'écran.
- Le joueur entre sa réponse et valide.
- Pour chaque bonne réponse, le score augmente de 1.
- Pour chaque mauvaise réponse, le nombre de vies diminue de 1.
- Lorsque le joueur n'a plus de vies, l'application passe à l'écran d'enregistrement du score.

### Enregistrement du Score
- Le score et le pseudo sont sauvegardés dans la base de données.

### High Score
- Affiche le meilleur score enregistré avec le pseudo associé.

### À Propos
- Affiche les noms des développeurs et des informations supplémentaires.

## Membres du Binôme

- Antoine COUTREEL
- Antoine PISSON

---

### Features

- Multilingue (FR + une autre langue).
- Sauvegarde des scores en base de données.
- Interface utilisateur simple et intuitive.
- Fonctionnalités supplémentaires dans la section "À Propos".

Ce projet vise à offrir une expérience utilisateur agréable tout en testant les compétences de calcul mental du joueur. L'implémentation doit suivre les meilleures pratiques de développement Android, en mettant l'accent sur la performance, la sécurité et la maintenabilité du code.
   
