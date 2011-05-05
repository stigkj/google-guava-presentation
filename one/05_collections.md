!SLIDE content full-page
# **	
## collections - Multi*

    @@@ java
    ImmutableList<Integer> numbers =
        ImmutableList.of(2, 3, 0, 1, 0, 1, 2, 3, 2, 3);
    Multiset<Integer> multiset =
        TreeMultiset.create(numbers);
    multiset.remove(0); // Decreasing count for 0 by 1
    multiset.add(1, 4); // Increasing count for 1 by 4

    Multimap<Color, Fruit> colorIndex =
        HashMultimap.create();
    for (Fruit fruit : fruits) {
      colorIndex.put(fruit.getColor(), fruit);
    }
    Collection<Fruit> redFruits =
        colorIndex.get(Color.RED);
    assertTrue("Tomato is a red fruit",
        redFruits.contains(TOMATO));

!SLIDE content full-page
# **
## collections - BiMap

    @@@ java
    ImmutableBiMap<Integer, String> biMap =
        ImmutableBiMap.of(0, "Zero", 1, "One",
        2, "Two", 3, "Three");
    assertEquals("2 should be mapped to Two",
        "Two", biMap.get(2));

    BiMap<String, Integer> inverseBiMap =
        biMap.inverse();
    assertEquals("Two should be mapped to 2",
        Integer.valueOf(2), inverseBiMap.get("Two"));

!SLIDE content full-page incremental
# collections - Much more

* Sets.union(...)
* Table
* Iterables.partition(...)
* Iterables.concat(...)
* Iterables.filter(...)
* Immutable*
* Constraints
* Ordering
