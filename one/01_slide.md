!SLIDE full-page
# Guava
## Google's core libraries for Java

!SLIDE content full-page incremental
# Consists of

* com.google.common.base
* com.google.common.collect
* com.google.common.io
* com.google.common.net
* com.google.common.primitives
* com.google.common.util.concurrent

!SLIDE content full-page incremental
# "I can just write that myself"

* These things are much easier to mess up than it seems
* With a library, other people will make your code faster for you
* When you use a popular library, your code is in the mainstream
* When you find an improvement to your private library, how many people did you help?

!SLIDE full-page
# base

@@@ ruby
public class Person {
    final String name, nickname;
    final Movie favMovie;

    @Override
    public boolean equals(Object object) {
        if (object instanceof Person) {
            Person that = (Person) object;
            return Objects.equal(this.name, that.name)
                && Objects.equal(this.nickname, that.nickname) 
                && Objects.equal(this.favMovie, that.favMovie);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, nickname, favMovie);
    }
}

