# Profil Compose

## Présentation

Cette application Android affiche un écran de profil réalisé avec Jetpack Compose.
Le profil principal présente Melvin Didier qui est développeur Java/Angular, sa description, ses compétences et ses coordonnées.
Le bouton « Contacter » (qui ne fait rien) représente une action utilisateur.

## Notions utilisées

- Composable
- Modifiers
- Paramètres (name, role...)
- Padding interne et externe
- Callback vide
- Previews

## Composables créés

- `MainScreen` : organise l’écran et reçoit les données du profil.
- `ProfileCard` : affiche la fiche de profil.
- `ProfileHeader` : affiche le nom et le rôle.
- `Skill` : affiche une compétence.
- `Infos` : affiche une information avec son libellé, ici les coordonnées.
- `Action` : affiche un bouton avec un texte et un callback.
- `ApprenticeProfile` et `FootballerProfile` : previews des deux variantes.

## Réponses aux questions

### 1. Quels composables avez-vous créés ?

J’ai créé `MainScreen`, `ProfileCard`, `ProfileHeader`, `Skill`, `Infos` et `Action`.
J’ai également créé les previews `ApprenticeProfile` et `FootballerProfile`.

### 2. Quel composable représente l’écran principal ?

`MainScreen` représente l’écran principal. Il reçoit les informations du profil et assemble les autres composables.

### 3. Où utilisez-vous un padding externe ?

Dans `MainActivity`, le parent applique `Modifier.padding(innerPadding)` à `MainScreen` pour tenir compte de l’espace fourni par le `Scaffold`.
Dans `MainScreen`, la `Column` applique aussi `Modifier.padding(15.dp)` autour de son contenu : cet espace éloigne les éléments des bords de l’écran.

### 4. Où utilisez-vous un padding interne ?

Dans `ProfileCard`, la `Column` applique `Modifier.padding(20.dp)`. Ce padding crée un espace entre le bord de la fiche et son contenu, notamment le nom, le rôle et la description.
Dans `Skill`, le `Text` a également un padding horizontal et vertical.

### 5. Quel composant reçoit un callback ?

`Action` reçoit le callback `onClick: () -> Unit` et le transmet au composant `Button`. Dans `MainScreen`, le callback fourni est vide pour le moment.

### 6. Quelle information d’accessibilité avez-vous ajoutée ?

J'ai ajouté un bouton « Contacter » ou « Voir les statistiques ». Je n’ai pas créé de zone cliquable personnalisée.

### 7. Pourquoi vos composants peuvent-ils être considérés comme réutilisables ?

Les composables reçoivent leurs données par paramètres, par exemple le nom, le rôle, la description, les compétences ou le texte du bouton.
Ils acceptent aussi un paramètre `modifier`, utilisé pour adapter leur placement. Les deux previews réutilisent `MainScreen` avec des données différentes.

## Aperçu

- **ApprenticeProfile** : profil de développeur Java/Angular qui s’oriente vers un master en alternance.
- **FootballerProfile** : profil fictif de footballeur professionnel avec une compétence et une action différentes.
