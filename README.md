# 2 Minutes Du Peuple Discord Bot
## Concept
Avoir tout (du moins ceux que j'ai pu trouver) les épisodes des deux minutes du peuple disponible sur un serveur discord

## Inviter sur votre serveur
Vous pouvez utiliser ce lien [Inviter](https://discord.com/api/oauth2/authorize?client_id=569517089845542912&permissions=104188992&scope=bot)
cela évite de le host vous même

## Self host
Vous pouvez héberger le bot par vous même. Dans ce cas, il faut aller dans l'onglet [releases](https://github.com/msterhuj/2MinutesDuPeupleDiscordBot/releases) pour téléchager la dernière version et télécharger les sketchs ici [Sketches](https://cdn.netbytes.space/2MinutesDuPeuple.rar)
pour démarrer le bot il faut avoir java, un token de bot discord et évidemment les sketchs et taper la commande suivante
```shell script
java -jar <jarfile>.jar <token> <dossier avec les stechs>
```

## Les commandes du bot
**Bot Prefix:** &2

_<> : argument obligatoire_

_[] : argument optionnel_
```
&2help : afficher le panneau d'aides
&2list : lister tout les catégories 
&2list [num] : passer en argument le numéro du dossier pour avoir les sketchs de la catégorie
&2play <dossier> [num/all] : donner le dossier suivi du num pour écouter un seul ou all pour écouter tout la catégorie
&2skip : passe au suivant
&2stop : arrête la lecture et fait quitter le bot du channel
```
## Bug connus
Impossibilité de lister le dossier 20 (divers) dû aux limitations de discord

## Futures Update
* fix du bug pour le dossier 20 (divers)
* ajout dun system play random
* auto déconnexion
* plus d'informations lors du &2skip
* auto téléchargement des sketchs

### Credit
Merci a François Pérusse pour avoir créée cette série :) [Twitter](https://twitter.com/Franpeuple)
