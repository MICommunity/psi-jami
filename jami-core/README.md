# jami-core
Core package for JAMI. 

Includes:

 - the model
 - expansion handlers
 - factories
 - listeners
 - and a whole lot of utils that should be somewhere else.

## Notes on the miTab 2.8 columns

- The `Biological Effect A` and `Biological Effect B` columns are modelled as properties of each respective `Participant`
- The `Causal Regulatory Mechanism` column is a property of every class that implements the `BinaryInteraction` interface
- The `Causal Statement` is a `relationType` CvTerm object of every class that implements the `CausalRelationship` interface
- A, being the first participant, is considered as the source participant always and B as the target participant of a binary interaction (A => B). So, when reading and writing miTab 2.8 files the A's `CausalRelationship` property `relationType` is the `Causal Statement` column and the Entity `target` property is the `Participant B` object. B's `CasualRelationship`-respective properties remain null in this case.
