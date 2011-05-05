!SLIDE content full-page
# **	
## base - CharMatcher class

	@@@ java
    String input = "ABC1-34-5j6";
    final CharMatcher matcher =
        CharMatcher.DIGIT.or(CharMatcher.is('-'));

    matcher.retainFrom(input);
    matcher.countIn(input);
    matcher.matches('a');

    CharMatcher.anyOf("aeiuo").negate();
    CharMatcher.inRange('a', 'z').or(inRange('A', 'Z'));

!SLIDE content full-page
# **
## base - Joiner class

    @@@ java
    String s = Joiner.on(", ").join(episodesOnDisc);

    StringBuilder sb = new StringBuilder();
    Joiner.on("|").skipNulls().appendTo(sb, attrs);

    Joiner.MapJoiner MAP_JOINER =
        Joiner.on("; ").
            useForNull("NODATA").
            withKeyValueSeparator(":");

    Map<String, String> nameToPerson = Maps.newHashMap();
    MAP_JOINER.join(nameToPerson);

!SLIDE content full-page
# **
## base - Splitter class

    @@@ java
    // yields ["foo", "bar", "quux"]
    Splitter.on(',').
        trimResults().
        omitEmptyStrings().
        split(" foo, ,bar, quux,");

    Splitter.onPattern(",\\s*");
    Splitter.on(CharMatcher.DIGIT);

!SLIDE content full-page
# **
## base - Predicates/Functions class

Lets get the names of users that have logins which do not contain digits

    @@@ java
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

!SLIDE content full-page
# **
## base - Predicates/Functions class

    @@@ java
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