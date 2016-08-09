#jami-core
##graph

The factory package contains factories for create new interactions instances or to create/define your datasource and your writer.
Home of the clique finder. We use the clique finder to set inferred interactions.

```
    protected Collection<Set<Feature>> collectInferredInteractionsFrom(T object){
        BindingSiteCliqueFinder<T,Feature> cliqueFinder = new BindingSiteCliqueFinder<T, Feature>(object);
        return cliqueFinder.getAllMaximalCliques();
    }
```

  It can find all maximal cliques in a graph of binding feature pairs (can retrieve complete graph of binding sites).
  This BronKerboschCliqueFinder implements Bron-Kerbosch clique detection algorithm as it is described in
  [Samudrala R.,Moult J.:A Graph-theoretic Algorithm for comparative Modeling of Protein Structure; J.Mol. Biol. (1998); vol 279; pp. 287-302]
 

