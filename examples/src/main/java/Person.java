import com.google.common.base.*;
import com.google.common.collect.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.google.common.base.CharMatcher.inRange;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;

public class Person {
  String name, nick;

  @Override
  public boolean equals(Object object) {
    if (object instanceof Person) {
      Person o = (Person) object;
      return Objects.equal(this.name, o.name)
          && Objects.equal(this.nick, o.nick);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, nick);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
        .add("name", name).add("nick", nick)
        .toString();
  }

  public void testPreconditions(String state) {
    if (state != State.PLAYABLE) {
      throw new IllegalStateException(
          "Can't play movie; state is " + state);
    }

    Preconditions.checkState(
        state == State.PLAYABLE,
        "Can't play movie; state is %s", state);
  }

  public void setRating(StarRating rating) {
    if (rating == null) {
      throw new NullPointerException();
    }
    this.rating = rating;
  }

  public void setRating(StarRating rating) {
    this.rating = checkNotNull(rating);
  }

  public void testCharMatcher() {
    String input = "ABC1-34-5j6";
    final CharMatcher matcher =
        CharMatcher.DIGIT.or(CharMatcher.is('-'));

    matcher.retainFrom(input);
    matcher.countIn(input);
    matcher.matches('a');

    CharMatcher.anyOf("aeiuo").negate();
    CharMatcher.inRange('a', 'z').or(inRange('A', 'Z'));
  }

  public void testJoiner(List episodesOnDisc, List attrs) {
    String s = Joiner.on(", ").join(episodesOnDisc);

    StringBuilder sb = new StringBuilder();
    Joiner.on("|").skipNulls().appendTo(sb, attrs);

    Joiner.MapJoiner MAP_JOINER =
        Joiner.on("; ").
            useForNull("NODATA").
            withKeyValueSeparator(":");

    Map<String, String> nameToPerson = Maps.newHashMap();
    MAP_JOINER.join(nameToPerson);
  }

  public void testSplitter() {
    // yields ["foo", "bar", "quux"]
    Splitter.on(',').
        trimResults().
        omitEmptyStrings().
        split(" foo, ,bar, quux,");

    Splitter.onPattern(",\\s*");
    Splitter.on(CharMatcher.DIGIT);
  }

  public void testPredicates() {
    List<User> users = newArrayList(
        new User(1L, "sylwek", "stall", "rambo"),
        new User(2L, "arnold", "schwartz", "commando"),
        new User(3L, "hans", "kloss", "jw23"));

    Collection<User> usersWithoutDigitsInLogin =
        filter(users,
            new ShouldNotHaveDigitsInLoginPredicate());
    String names = Joiner.on("\n").join(
        transform(usersWithoutDigitsInLogin,
            new FullNameFunction()));
  }

  public class ShouldNotHaveDigitsInLoginPredicate
      implements Predicate<User> {
    @Override
    public boolean apply(User user) {
        checkNotNull(user);
        return CharMatcher.DIGIT.
            retainFrom(user.login).length() == 0;
    }
  }

  public class FullNameFunction
      implements Function<User, String> {
    @Override
    public String apply(User user) {
      checkNotNull(user);
      return user.getFirstName() +
          " " + user.getLastName();
    }
  }

  private class User {
    public User(long l, String sylwek, String stall, String rambo) {
    }
  }

  public void testCollections() {
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

    ImmutableBiMap<Integer, String> biMap =
        ImmutableBiMap.of(0, "Zero", 1, "One",
        2, "Two", 3, "Three");
    assertEquals("2 should be mapped to Two",
        "Two", biMap.get(2));

    BiMap<String, Integer> inverseBiMap =
        biMap.inverse();
    assertEquals("Two should be mapped to 2",
        Integer.valueOf(2), inverseBiMap.get("Two"));
  }
}