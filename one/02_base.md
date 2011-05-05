!SLIDE content full-page
# **	
## base - Objects class

	@@@ java
    public class Person {
      final String name, nick;
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
    }

!SLIDE content full-page
# **
## base - Preconditions class
### makes this
    @@@ java
    if (state != State.PLAYABLE) {
      throw new IllegalStateException(
          "Can't play movie; state is " + state);
    }

### into this

    @@@ java
    Preconditions.checkState(
      state == State.PLAYABLE,
      "Can't play movie; state is %s", state);

!SLIDE content full-page
# **
## base - Preconditions class
### makes this
    @@@ java
    public void setRating(StarRating rating) {
      if (rating == null) {
        throw new NullPointerException();
      }
      this.rating = rating;
    }

### into this

    @@@ java
    public void setRating(StarRating rating) {
      this.rating = checkNotNull(rating);
    }
