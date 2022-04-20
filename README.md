# Rectangle Exercise

## Problem: Implement the following algorithms used to analyze rectangles

* Intersection
  * Determines if two rectangles have one or more intersecting lines
  * Returns any points of intersection should they exist
* Containment
  * Determine if one rectangle is completely contained in another rectangle.
* Adjacency
  * Detect if two rectangles share at least one side and what type of side they share including the following:
    * Proper
      * The rectangle shares the complete length of the side with the other rectangle.
    * Sub-line
      * The rectangle shares a side with the other, but one rectangle's side is shorter and completely adjacent within
      the other
    * Partial
      * The rectangle shares a sided with the other, but neither sharing side is completely adjacent with the other

# Running Tests

* From the root of the project, run 'mvn clean install'