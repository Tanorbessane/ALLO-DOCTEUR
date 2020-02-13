------------------  REPONSES AUX QUESTIONS  ---------------------------

    - QUESTION 1 :  Que veut dire IOC ? Quel est son intérêt ?
         - IOC: inversion de contrôle, est un principe abstrait qui définit un motif de conception dans lequel le flux de contrôle d'un système est inversé par rapport à un développement procédural.
                    implémentée par le mécanisme, l'injection de dépendances


    - QUESTION 2 : Quelle est la différence entre les annotations Component, Repository et Service ? A quoi servent exactement ces différentes annotations ?
    
                    - @Component est un stéréotype générique pour tout   Composant géré par un ressort. pour   exemple, dans les couches de persistance, de service et de présentation,   respectivement.
                    - @Repository l'annotation est un marqueur pour   toute classe qui remplit le rôle ou le stéréotype (également connu sous le nom   Access Object ou DAO) d'un référentiel.
                    -@Service est une spécialisation de l' annotation @Component pour les couche de service, elle est clairement meilleur choix. 