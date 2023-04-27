# spring-microservices-GaoLIU2023ESTIA
## Context
- L'objectif du projet est de créer un site web d'achat.
## Outils
- L'architecture est microservice. Framework est spring boot.
## Structure
- Les services sont répartis en 4 paquet:
  - ms-product
  - ms-cart
  - ms-order
  - client
- Dans les trois premiers microservices, les fichiers sont répartis en 3 paquet:
  - model
  - controllers
  - repositories
- Dans le microservice client, les fichier sont répartis en 3 paquet:
  - beans
  - controllers
  - proxies
## Functions réalisées
- Affichier tous les produit
- Cliquer sur un produit et aller à une nouvelle page pour consulter les détails
- Cliquer sur un button pour ajouter un produit dans un cart
- Cliquer sur un button et ajouter tous les produits qui étaient dans cart dans un order
